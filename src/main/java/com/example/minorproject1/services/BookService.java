package com.example.minorproject1.services;

import com.example.minorproject1.models.Author;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Genre;
import com.example.minorproject1.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorService authorService;

    public void createOrUpdate(Book book) {
        Author author = book.getMy_author();
        Author savedAuthor = authorService.getOrCreate(author);

        book.setMy_author(savedAuthor);
        bookRepository.save(book);
    }


    public List<Book> getBooks(String searchKey, String searchValue) throws Exception {
        switch(searchKey) {

            case "id": {
                Optional<Book> book = bookRepository.findById(Integer.parseInt(searchValue));
                if (book.isPresent()){
                    return Arrays.asList(book.get());
                } else {
                    return new ArrayList<>();
                }
            }

            case "genre": return bookRepository.findByGenre(Genre.valueOf(searchValue));

            case "author_name" : return bookRepository.findByMy_Author_Name(searchValue);

            case "name": return bookRepository.findByName(searchValue);

            default:
                throw new Exception("Invalid search key "+ searchKey);
        }
    }
}
