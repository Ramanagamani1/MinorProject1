package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Admin;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdminRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    public Admin to() {
        return Admin.builder()
                .name(this.name)
                .email(this.email)
                .build();
    }
}
