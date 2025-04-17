package com.sachin.Cards.service;
import com.sachin.Cards.dto.CardsDto;
import com.sachin.Cards.entity.Cards;

public interface ICardsService
{
    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}