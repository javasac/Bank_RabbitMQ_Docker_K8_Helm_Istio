package com.sachin.Accounts.service.impl;
import com.sachin.Accounts.constants.AccountsConstants;
import com.sachin.Accounts.dto.AccountsDto;
import com.sachin.Accounts.dto.CustomerDto;
import com.sachin.Accounts.entity.Accounts;
import com.sachin.Accounts.entity.Customer;
import com.sachin.Accounts.exception.CustomerAlreadyExistsException;
import com.sachin.Accounts.exception.ResourceNotFoundException;
import com.sachin.Accounts.mapper.CustomerMapper;
import com.sachin.Accounts.repository.AccountsRepository;
import com.sachin.Accounts.repository.CustomerRepository;
import com.sachin.Accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import com.sachin.Accounts.mapper.AccountsMapper;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService
{
    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void createAccount(CustomerDto cd)
    {
        Customer customer = CustomerMapper.maptoCustomer(cd, new Customer());

        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(cd.getMobileNumber());
        if (optionalCustomer.isPresent())
        {
            throw new CustomerAlreadyExistsException("Customer already exits with given Mobile # " + cd.getMobileNumber());
        }

        //customer.setCreatedAt(LocalDateTime.now());
        //customer.setCreatedBy(AccountsConstants.CREATED_BY);

        Customer newCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(newCustomer));
    }

    private Accounts createNewAccount(Customer customer)
    {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAcctNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAcctNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        //newAccount.setCreatedBy(AccountsConstants.CREATED_BY);
        //newAccount.setCreatedAt(LocalDateTime.now());

        return newAccount;
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber)
    {
        Customer cust = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(cust.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", cust.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.maptoCustomerDto(cust, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    public boolean updateAccount(CustomerDto customerDto)
    {
        Customer customer = null;
        Accounts accounts = null;
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();

        if (accountsDto != null)
        {
            accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString())
            );

            CustomerMapper.maptoCustomer(customerDto, customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber)
    {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());

        return true;
    }
}
