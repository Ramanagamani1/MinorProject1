package com.example.minorproject1.dtos;

import com.example.minorproject1.models.SecuredUser;
import com.example.minorproject1.models.Student;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {

    @NotBlank
    private String name;

    private Integer age;

    @NotBlank
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Student to() {
        return Student
                .builder()
                .name(this.name)
                .age(this.age)
                .email(this.email)
                .securedUser(SecuredUser.builder()
                        .username(username)
                        .password(password)
                        .build())
                .build();
    }
}
