package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Author;
import com.example.minorproject1.models.Book;
import com.example.minorproject1.models.Genre;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookRequest {

    @NotBlank
    private String name;

    @NotNull
    private Genre genre;

    @NotBlank
    private String authorName;

    @NotBlank
    private String authorEmail;

    public Book to() {
        return Book.builder()
                .name(this.name)
                .genre(this.genre)
                .my_author(Author.builder()
                        .name(authorName)
                        .email(authorEmail)
                        .build())
                .build();
    }
}
