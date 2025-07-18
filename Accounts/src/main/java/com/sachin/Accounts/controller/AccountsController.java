package com.sachin.Accounts.controller;
import com.sachin.Accounts.constants.AccountsConstants;
import com.sachin.Accounts.dto.AccountsContactInfoDto;
import com.sachin.Accounts.dto.CustomerDto;
import com.sachin.Accounts.dto.ResponseDto;
import com.sachin.Accounts.service.IAccountsService;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeoutException;

@Tag(
        name="CRUD REST APIs for Accounts in Bank.",
        description = "CRUD REST APIs - CREATE, UPDATE AND DELETE for Accounts in Bank."
)
@RestController
@RequestMapping("/accounts")
@Validated
public class AccountsController
{
    private static final Logger logger = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private IAccountsService iAccountsService;

    @Autowired
    private AccountsContactInfoDto accountsContactInfoDto;

    @Retry(name="getBuildInfo", fallbackMethod = "getBuildInfoFallback")
    @GetMapping("/build")
    public ResponseEntity<String> getBuildInfo() throws TimeoutException
    {
        logger.debug("getBuildInfo() method Invoked().");
        System.out.println("getBuildInfo() method Invoked().");

        throw new TimeoutException();
        //return ResponseEntity.status(HttpStatus.OK).body("1.5");
    }

    public ResponseEntity<String> getBuildInfoFallback(Throwable th)
    {
        logger.debug("getBuildInfoFallback() method Invoked().");
        System.out.println("getBuildInfoFallback() method Invoked().");
        return ResponseEntity.status(HttpStatus.OK).body("1.0 fallBack");
    }

    @GetMapping("/contact")
    public ResponseEntity<AccountsContactInfoDto> getContactInfo()
    {
        return ResponseEntity.
                status(HttpStatus.OK).body(accountsContactInfoDto);
    }

    @Operation(
            summary = "Delete Account REST API.",
            description = "REST APIs to delete Customer & Account inside Bank."
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description = "Http Status OK."),
            @ApiResponse(responseCode="500", description = "Http Status - Internal Server Error."),
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile # must be 10 digits.") String mobileNumber)
    {
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);

        if (isDeleted)
        {
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
                    body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST APIs to fetch Customer & Account inside Bank."
    )
    @ApiResponses({
            @ApiResponse(responseCode="200", description = "Http Status get record."),
            @ApiResponse(responseCode="417", description = "Expectation failed."),
            @ApiResponse(responseCode="500", description = "Http Status-Internal Server Error.")
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto)
    {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);

        if (isUpdated)
        {
            return ResponseEntity.status(HttpStatus.OK).
                    body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).
                    body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Fetch Account REST API",
            description = "REST APIs to fetch Customer & Account inside Bank."
    )
    @ApiResponse(
            responseCode = "200", description = "Http Status get record."
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile # must be 10 digits.") String mobileNumber)
    {
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Create Account REST API",
            description = "REST APIs to create new Customer & Account inside Bank."
    )
    @ApiResponse(
            responseCode = "201", description = "Http Status Created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto cd)
    {
        iAccountsService.createAccount(cd);
        return ResponseEntity.status(HttpStatus.CREATED).
                body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
}
