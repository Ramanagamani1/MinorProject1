package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.TransactionType;
import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InitiateTransactionRequest {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer bookId;

    @NotNull
    private Integer adminId;

    @NotNull
    private TransactionType transactionType;
}
