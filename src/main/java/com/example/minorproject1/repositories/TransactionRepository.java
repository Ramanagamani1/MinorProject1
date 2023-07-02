package com.example.minorproject1.repositories;

import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.Transaction;
import com.example.minorproject1.models.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findTopByStudentAndBookAndTransactionTypeOrderByIdDesc(Student student, Book book, TransactionType issue);

    Transaction findByTxnId(String txnId);
}
