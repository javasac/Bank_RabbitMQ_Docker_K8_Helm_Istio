package com.sachin.Accounts.service.client;
import com.sachin.Accounts.dto.CardsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient
{
    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationID, String mobileNumber)
    {
        return null;
    }
}
