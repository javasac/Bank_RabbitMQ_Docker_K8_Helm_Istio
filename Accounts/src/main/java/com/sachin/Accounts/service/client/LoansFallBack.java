package com.sachin.Accounts.service.client;
import com.sachin.Accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallBack implements LoansFeignClient
{
    @Override
    public ResponseEntity<LoansDto> fetchLoanDetails(String correlationID, String mobileNumber)
    {
        return null;
    }
}
