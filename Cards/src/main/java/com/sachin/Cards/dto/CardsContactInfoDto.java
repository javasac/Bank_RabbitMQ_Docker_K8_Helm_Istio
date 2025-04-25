package com.sachin.Cards.dto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "cards")
//public record CardsContactInfoDto(String message, Map<String, String> contactDetails, List<String> onCallSupport)
public class CardsContactInfoDto
{
    private String message;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}