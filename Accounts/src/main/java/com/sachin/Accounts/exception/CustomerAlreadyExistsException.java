package com.sachin.Accounts.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class CustomerAlreadyExistsException extends RuntimeException
{
    public CustomerAlreadyExistsException(String msg)
    {
        super(msg);
    }
}
