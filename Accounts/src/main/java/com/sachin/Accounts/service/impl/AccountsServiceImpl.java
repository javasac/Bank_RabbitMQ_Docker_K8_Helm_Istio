package com.sachin.Accounts.service.impl;
import com.sachin.Accounts.constants.AccountsConstants;
import com.sachin.Accounts.dto.CustomerDto;
import com.sachin.Accounts.entity.Accounts;
import com.sachin.Accounts.entity.Customer;
import com.sachin.Accounts.exception.CustomerAlreadyExistsException;
import com.sachin.Accounts.mapper.CustomerMapper;
import com.sachin.Accounts.repository.AccountsRepository;
import com.sachin.Accounts.repository.CustomerRepository;
import com.sachin.Accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService
{
    private AccountsRepository accountsRepository;
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

        return newAccount;
    }
}
