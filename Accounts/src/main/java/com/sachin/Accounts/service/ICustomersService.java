package com.sachin.Accounts.service;
import com.sachin.Accounts.dto.CustomerDetailsDto;

public interface ICustomersService
{
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
