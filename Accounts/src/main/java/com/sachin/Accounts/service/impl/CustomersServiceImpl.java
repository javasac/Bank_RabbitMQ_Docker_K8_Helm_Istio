package com.sachin.Accounts.service.impl;
import com.sachin.Accounts.dto.AccountsDto;
import com.sachin.Accounts.dto.CardsDto;
import com.sachin.Accounts.dto.CustomerDetailsDto;
import com.sachin.Accounts.dto.LoansDto;
import com.sachin.Accounts.entity.Accounts;
import com.sachin.Accounts.entity.Customer;
import com.sachin.Accounts.exception.ResourceNotFoundException;
import com.sachin.Accounts.mapper.AccountsMapper;
import com.sachin.Accounts.mapper.CustomerMapper;
import com.sachin.Accounts.repository.AccountsRepository;
import com.sachin.Accounts.repository.CustomerRepository;
import com.sachin.Accounts.service.ICustomersService;
import com.sachin.Accounts.service.client.CardsFeignClient;
import com.sachin.Accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService
{
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationID)
    {
        System.out.println("==========CustomersServiceImpl=====fetchCustomerDetails========" + mobileNumber);
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).
                orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).
                orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.maptoCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationID, mobileNumber);
        if (null != loansDtoResponseEntity)
        {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationID, mobileNumber);
        if (null != cardsDtoResponseEntity)
        {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
