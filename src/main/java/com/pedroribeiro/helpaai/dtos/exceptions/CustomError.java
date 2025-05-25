package com.pedroribeiro.helpaai.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomError {

    private Integer statusCode;
    private String message;
}
