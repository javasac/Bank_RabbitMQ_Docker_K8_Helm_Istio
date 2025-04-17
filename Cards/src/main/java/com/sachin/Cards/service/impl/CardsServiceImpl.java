package com.sachin.Cards.service.impl;
import com.sachin.Cards.constants.CardsConstants;
import com.sachin.Cards.dto.CardsDto;
import com.sachin.Cards.entity.Cards;
import com.sachin.Cards.exception.CardAlreadyExistsException;
import com.sachin.Cards.exception.ResourceNotFoundException;
import com.sachin.Cards.repository.CardsRepository;
import com.sachin.Cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

import com.sachin.Cards.mapper.CardsMapper;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService
{
    @Autowired
    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards>  card = cardsRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()){
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber "+mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    @Override
    public boolean deleteCard(String mobileNumber)
    {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
