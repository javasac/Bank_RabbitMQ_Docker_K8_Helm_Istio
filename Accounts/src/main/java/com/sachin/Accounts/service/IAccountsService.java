package com.sachin.Accounts.service;
import com.sachin.Accounts.dto.CustomerDto;

public interface IAccountsService
{
    void createAccount(CustomerDto cd);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
