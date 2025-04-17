package com.sachin.Cards.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Data
@Schema(name="ErrorResponse",description="Schema to hold error response Information.")
public class ErrorResponseDto
{
    @Schema(description="API Path invoked by Client.")
    private String apiPath;

    @Schema(description="Error Code representing error code.")
    private HttpStatus errorCode;

    @Schema(description="Error Message representing error happened.")
    private String errorMessage;

    private LocalDateTime errorTime;

    public ErrorResponseDto(String apiPath, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
        this.apiPath = apiPath;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorTime = errorTime;
    }
}
