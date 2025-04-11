package com.sachin.Accounts.controller;
import com.sachin.Accounts.constants.AccountsConstants;
import com.sachin.Accounts.dto.CustomerDto;
import com.sachin.Accounts.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api", produces = {MediaType.APPLICATION_JSON_VALUE})
public class AccountsController
{
    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hi World.";
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto cd)
    {
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
