package com.example.minorproject1.services;

import com.example.minorproject1.models.Author;
import com.example.minorproject1.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    public Author getOrCreate(Author author) {
        Author authorRetrieved = authorRepository.findByEmail(author.getEmail());

        if(authorRetrieved == null) {
            authorRetrieved = authorRepository.save(author);
        }
        return authorRetrieved;
    }
}
