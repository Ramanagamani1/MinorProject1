package com.example.minorproject1.services;

import com.example.minorproject1.dtos.InitiateTransactionRequest;
import com.example.minorproject1.models.*;
import com.example.minorproject1.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookService bookService;

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @Value("${student.allowed.max-books}")
    Integer maxBooksAllowed;

    @Value("${student.allowed.duration}")
    Integer duration;

    public String initiateTxn(InitiateTransactionRequest request,Integer adminId) throws Exception {

        return request.getTransactionType() == TransactionType.ISSUE ?
                issueBook(request,adminId) : returnBook(request,adminId);
    }

    private String issueBook(InitiateTransactionRequest request, Integer adminId) throws Exception {
        /**
         * Issuance
         * 1. If the book is available or not and student is valid or not
         * 2. entry in the Txn
         * 3. we need to check if student has reached the maximum limit of issuance
         * 4. book to be assigned to a student ==> update in book table
         *
         */
         Student student = studentService.getStudent(request.getStudentId());
         Admin admin = adminService.getAdmin(adminId);
         List<Book> books = bookService.getBooks("id", String.valueOf(request.getBookId()));

         Book book = books.size() > 0 ? books.get(0) : null;

         if(student==null ||
                 admin == null ||
                 book==null ||
                 book.getStudent()!=null ||
                 student.getBookList().size() >= maxBooksAllowed) {
             throw new Exception("Invalid request");
         }

        Transaction transaction = null;

         try {
             transaction = Transaction.builder()
                     .txnId(UUID.randomUUID().toString())
                     .student(student)
                     .book(book)
                     .admin(admin)
                     .transactionType(TransactionType.ISSUE)
                     .transactionStatus(TransactionStatus.PENDING)
                     .build();
             transactionRepository.save(transaction);

             book.setStudent(student);
             bookService.createOrUpdate(book);
             transaction.setTransactionStatus(TransactionStatus.SUCCESS);

         } catch(Exception e) {
             transaction.setTransactionStatus(TransactionStatus.FAILURE);
         } finally {
             transactionRepository.save(transaction);
         }

         return  transaction.getTxnId();
    }

    private String returnBook(InitiateTransactionRequest request, Integer adminId) throws Exception {
        /**
         * Return
         * 1. If the book is valid or not and student is valid or not
         * 2. entry in the Txn table
         * 3. due date check and fine calculation
         * 4. if there is no fine, then de-allocate the book from student's name ==> book table
         */

        Student student = studentService.getStudent(request.getStudentId());

        Admin admin = adminService.getAdmin(adminId);
        List<Book> books = bookService.getBooks("id", String.valueOf(request.getBookId()));

        Book book = books.size() > 0 ? books.get(0) : null;

        if(student==null ||
                admin == null ||
                book==null ||
                book.getStudent()==null ||
                book.getStudent().getId()!= student.getId()) {
            throw new Exception("Invalid request");
        }

        Transaction issuanceTxn = transactionRepository.findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(student,book, TransactionType.ISSUE);

        if(issuanceTxn == null) {
            throw new Exception("Invalid request");
        }
        Transaction transaction = null;

        try {
            Integer fine = calculateFine(issuanceTxn.getCreatedOn());
            transaction = Transaction.builder()
                    .txnId(UUID.randomUUID().toString())
                    .student(student)
                    .book(book)
                    .admin(admin)
                    .fine(fine)
                    .transactionType(TransactionType.RETURN)
                    .transactionStatus(TransactionStatus.PENDING)
                    .build();

            transactionRepository.save(transaction);

            if(fine == 0) {
                book.setStudent(null);
                bookService.createOrUpdate(book);
                transaction.setTransactionStatus(TransactionStatus.SUCCESS);
            }


        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILURE);
        } finally {
            transactionRepository.save(transaction);
        }

        return transaction.getTxnId();
    }

    private Integer calculateFine(Date issuanceTime) {
        long issueTimeInMillis = issuanceTime.getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        long diff = currentTimeInMillis-issueTimeInMillis;

        long daysPassed = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(daysPassed > duration) {
            return (int)((daysPassed - duration));
        }

        return 0;
    }

    public void payment(Integer amount, Integer studentId, String txnId) throws Exception {
        Transaction returnTxn = transactionRepository.findByTxnId(txnId);

        Book book = returnTxn.getBook();

        if(returnTxn.getFine() == amount && book.getStudent()!=null && book.getStudent().getId()==studentId) {
            returnTxn.setTransactionStatus(TransactionStatus.SUCCESS);
            book.setStudent(null);
            bookService.createOrUpdate(book);
            transactionRepository.save(returnTxn);
        } else {
            throw new Exception("Invalid request");
        }
    }
}
