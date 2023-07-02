package com.example.minorproject1.repositories;

import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    List<Book> findByGenre(Genre genre);

    @Query("select b from Book b, Author a where b.my_author.id= a.id and a.name=?1")
    List<Book> findByMy_Author_Name(String authorName);

    List<Book> findByName(String searchValue);
}
