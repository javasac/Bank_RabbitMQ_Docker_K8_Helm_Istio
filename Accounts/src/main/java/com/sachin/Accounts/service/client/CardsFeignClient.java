package com.sachin.Accounts.service.client;
import com.sachin.Accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="cards", fallback = CardsFallback.class)
public interface CardsFeignClient
{
    @GetMapping("/cards/detail/{mobileNumber}")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("Bank-Correlation-ID") String correlationID,
                                                     @PathVariable String mobileNumber);
}
