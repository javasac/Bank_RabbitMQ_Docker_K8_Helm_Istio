package com.sachin.Loans.service;
import com.sachin.Loans.dto.LoansDto;

public interface ILoansService
{
    void createLoan(String mobileNumber);
    LoansDto fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDto loansDto);
    boolean deleteLoan(String mobileNumber);
}