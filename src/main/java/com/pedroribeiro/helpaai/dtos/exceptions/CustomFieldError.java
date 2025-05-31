package com.pedroribeiro.helpaai.dtos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomFieldError extends  CustomError {

    public CustomFieldError(Integer statusCode, String message) {
        super(statusCode, message);
    }

    private List<CustomFieldErrorMessage> errors = new ArrayList<CustomFieldErrorMessage>();
}
