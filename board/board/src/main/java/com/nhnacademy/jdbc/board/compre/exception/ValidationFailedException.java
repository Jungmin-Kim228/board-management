package com.nhnacademy.jdbc.board.compre.exception;

import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ValidationFailedException extends RuntimeException {
    public ValidationFailedException(BindingResult bindingResult) {
        super(bindingResult.getAllErrors()
                           .stream()
                           .map(error -> new StringBuilder().append("ObjectName=").append(error.getObjectName())
                                                            .append(",Message=").append(error.getDefaultMessage())
                                                            .append(",code=").append(error.getCode()))
                           .collect(Collectors.joining(" | ")));
    }
}
