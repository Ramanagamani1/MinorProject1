package com.example.minorproject1.controllers;

import com.example.minorproject1.dtos.CreateBookRequest;
import com.example.minorproject1.dtos.SearchBookRequest;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    // admin can create book
    @PostMapping("/book")
    public void createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
        bookService.createOrUpdate(createBookRequest.to());
    }

    @GetMapping("/book")
    public List<Book> getBooks(@RequestBody @Valid SearchBookRequest searchBookRequest) throws Exception {
        return bookService.getBooks(searchBookRequest.getSearchKey(),searchBookRequest.getSearchValue());
    }
}
