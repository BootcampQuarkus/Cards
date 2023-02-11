package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.CardType;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardTypeD;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CardTypeMapper implements IMapper<CardType, CardTypeD> {
  /**
   * Transforma el objeto de CardType a CardTypeD.
   *
   * @param cardType Objeto de la clase CardType que se desea transformar.
   * @return Objeto de la clase CardTypeD.
   * @throws NullPointerException
   */
  @Override
  public CardTypeD toDto(CardType cardType) throws NullPointerException {
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setName(cardType.getName());
    return cardTypeD;
  }

  /**
   * Transforma el objeto de CardTypeD a CardType.
   *
   * @param cardTypeD Objeto de la clase CardTypeD que se desea transformar.
   * @return Objeto de la clase CardType.
   * @throws NullPointerException
   */
  @Override
  public CardType toEntity(CardTypeD cardTypeD) throws NullPointerException {
    CardType cardType = new CardType();
    cardType.setId(cardTypeD.id);
    cardType.setName(cardTypeD.getName());
    return cardType;
  }
}
