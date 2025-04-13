package com.sachin.Accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto
{
    @NotEmpty(message = "Account # cannot be empty or null.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account # must be 10 digits.")
    private Long accountNumber;

    @NotEmpty(message="Account Type cannot be empty or null.")
    @Schema(description="Account Type of Customer", example = "Savings")
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be empty or null.")
    @Schema(description="Branch Address of Bank", example = "123 Main St, New York")
    private String branchAddress;

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }
}
