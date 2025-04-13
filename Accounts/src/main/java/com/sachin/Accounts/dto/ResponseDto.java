package com.sachin.Accounts.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name="Response", description="Schema to hold successful response Information.")
@Data
public class ResponseDto
{
    @Schema(description="Status code in the Response.", example="200")
    private String statusCode;

    @Schema(description="Schema to hold successful response Information.", example="Request processed successfully.")
    private String statusMsg;

    public ResponseDto(){
    }

    public ResponseDto(String statusCode,  String statusMsg)
    {
        this.statusCode = statusCode;
        this.statusMsg = statusMsg;
    }
}
