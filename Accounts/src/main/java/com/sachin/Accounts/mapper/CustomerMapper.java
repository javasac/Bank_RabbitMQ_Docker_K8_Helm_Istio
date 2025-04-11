package com.sachin.Accounts.mapper;
import com.sachin.Accounts.dto.CustomerDto;
import com.sachin.Accounts.entity.Customer;

public class CustomerMapper
{
    public static CustomerDto maptoCustomerDto(Customer customer, CustomerDto customerDto)
    {
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());

        return customerDto;
    }

    public static Customer maptoCustomer(CustomerDto customerDto, Customer customer)
    {
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;
    }
}
