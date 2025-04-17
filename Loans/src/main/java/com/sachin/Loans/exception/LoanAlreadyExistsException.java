package com.sachin.Loans.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

public class LoanAlreadyExistsException extends RuntimeException {

    public LoanAlreadyExistsException(String message){
        super(message);
    }

}
