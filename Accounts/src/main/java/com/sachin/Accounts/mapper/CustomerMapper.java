package com.sachin.Accounts.mapper;
import com.sachin.Accounts.dto.CustomerDetailsDto;
import com.sachin.Accounts.dto.CustomerDto;
import com.sachin.Accounts.entity.Customer;

public class CustomerMapper
{
    public static CustomerDetailsDto maptoCustomerDetailsDto(Customer customer,
                                                             CustomerDetailsDto customerDetailsDto)
    {
        customerDetailsDto.setName(customer.getName());
        customerDetailsDto.setEmail(customer.getEmail());
        customerDetailsDto.setMobileNumber(customer.getMobileNumber());

        return customerDetailsDto;
    }

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
