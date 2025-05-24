package com.sachin.Accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(name = "CustomerDetails",
        description = "Schema to hold Customer, Account, Loans and Cards information."
)
public class CustomerDetailsDto
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

    @Schema(description = "Account Details of the Customer.")
    private AccountsDto accountsDto;

    @Schema(description = "Loan Details of the Customer.")
    private LoansDto loansDto;

    @Schema(description = "Card Details of the Customer.")
    private CardsDto cardsDto;
}
