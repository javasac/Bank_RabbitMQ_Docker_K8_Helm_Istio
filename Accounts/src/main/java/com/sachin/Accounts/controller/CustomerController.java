package com.sachin.Accounts.controller;
import com.sachin.Accounts.dto.CustomerDetailsDto;
import com.sachin.Accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="CRUD REST APIs for Customers in Bank.",
        description = "Rest APIs in Bank to fetch Customer Details."
)
@RestController
@RequestMapping(path = "/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated
public class CustomerController
{
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final ICustomersService iCustomersService;

    public CustomerController(ICustomersService iCustomersService)
    {
        this.iCustomersService = iCustomersService;
    }

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("Bank-Correlation-ID") String correlationID,
            @RequestParam @Pattern(regexp = "(^$|[0-9]{10})",
            message = "Mobile # must be 10 digits.") String mobileNumber)
    {
        logger.debug("BANK-CORRELATION-ID in CustomerController = ", correlationID);
        System.out.println("Method is fetchCustomerDetails and Mobile is " + mobileNumber);
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(mobileNumber, correlationID);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}