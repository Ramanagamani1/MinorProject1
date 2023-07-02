package com.example.minorproject1.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchBookRequest {

    @NotBlank
    private String searchKey;

    @NotBlank
    private String searchValue;

}
