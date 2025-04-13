package com.sachin.Accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account Information."
)
@Data
public class CustomerDto
{
    @NotEmpty(message = "Name cannot be empty or null.")
    @Size(min=5, max=30, message="Length should be between 5 and 30.")
    private String name;

    @NotEmpty(message = "Name cannot be empty or null.")
    @Email(message = "Email format incorrect.")
    @Schema(description = "Email address Customer", example = "kansal_java@gmail.com")
    private String email;

    @Schema(description = "Cell # of Customer", example = "8129008765")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile # must be 10 digits.")
    private String mobileNumber;

    private AccountsDto accountsDto;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public AccountsDto getAccountsDto() {
        return accountsDto;
    }

    public void setAccountsDto(AccountsDto accountsDto) {
        this.accountsDto = accountsDto;
    }
}
