package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.Card;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardMapper implements IMapper<Card, CardD> {
  /**
   * Transforma el objeto de Card a CardD.
   *
   * @param card Objeto de la clase Card que se desea transformar.
   * @return Objeto de la clase CardD.
   * @throws NullPointerException
   */
  @Override
  public CardD toDto(Card card) throws NullPointerException {
    CardD cardD = new CardD();
    cardD.setSerial(card.getSerial());
    cardD.setPin(card.getPin());
    cardD.setPin(card.getPin());
    cardD.setMonth(card.getMonth());
    cardD.setYear(card.getYear());
    cardD.setCvv(card.getCvv());
    cardD.setCustomerId(card.getCustomerId());
    cardD.setProductId(card.getProductId());
    return cardD;
  }

  /**
   * Transforma el objeto de CardD a Card.
   *
   * @param cardD Objeto de la clase CardD que se desea transformar.
   * @return Objeto de la clase Card.
   * @throws NullPointerException
   */
  @Override
  public Card toEntity(CardD cardD) throws NullPointerException {
    Card card = new Card();
    card.setId(cardD.id);
    card.setSerial(cardD.getSerial());
    card.setPin(cardD.getPin());
    card.setMonth(cardD.getMonth());
    card.setYear(cardD.getYear());
    card.setCvv(cardD.getCvv());
    card.setCustomerId(cardD.getCustomerId());
    card.setProductId(cardD.getProductId());
    card.setCardTypeId(cardD.getCardTypeD().id);
    return card;
  }
}
