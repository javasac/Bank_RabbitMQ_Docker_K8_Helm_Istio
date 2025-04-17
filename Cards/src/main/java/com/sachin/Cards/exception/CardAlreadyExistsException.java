package com.sachin.Cards.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CardAlreadyExistsException extends RuntimeException {

    public CardAlreadyExistsException(String message){
        super(message);
    }

}
