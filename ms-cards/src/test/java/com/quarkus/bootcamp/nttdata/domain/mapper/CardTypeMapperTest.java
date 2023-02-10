package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.CardType;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardTypeD;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CardTypeMapperTest {
  @Inject
  CardTypeMapper mapper;

  /**
   * Cuando se envia un elemento null al metodo toDto debe retornar un NullPointerException.
   */
  @Test
  public void toDtoNull() {
    Assertions.assertThrows(NullPointerException.class, () -> mapper.toDto(null));
  }

  /**
   * Cuando se envia un elemento null al metodo toEntity debe retornar un NullPointerException.
   */
  @Test
  public void toEntityNull() {
    Assertions.assertThrows(NullPointerException.class, () -> mapper.toEntity(null));
  }

  /**
   * El metodo toDto debe retornar un Dto.
   */
  @Test
  public void toDtoReturnDto() {
    Assertions.assertInstanceOf(CardTypeD.class, mapper.toDto(new CardType()));
  }

  /**
   * El metodo toDto debe retornar un Entity.
   */
  @Test
  public void toEntityReturnEntity() {
    Assertions.assertInstanceOf(CardType.class, mapper.toEntity(new CardTypeD()));
  }

  /**
   * Cuando se envia un elemento vacio al metodo toDto debe retornar un Dto vacio.
   */
  @Test
  public void toDtoVoid() {
    CardType cardType = new CardType();
    CardTypeD expected = new CardTypeD();
    CardTypeD actual = mapper.toDto(cardType);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envia un elemento vacio al metodo toEntity debe retornar un Entity vacio.
   */
  @Test
  public void toEntityVoid() {
    CardTypeD cardTypeD = new CardTypeD();
    CardType expected = new CardType();
    CardType actual = mapper.toEntity(cardTypeD);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envia un elemento valido al metodo toDto debe retornar un Dto con los mismos datos en los campos
   * amount, customerId y cardId.
   */
  @Test
  public void toDtoValid() {
    String name = "Credit";
    CardType cardType = new CardType();
    cardType.setName(name);
    CardTypeD expected = new CardTypeD();
    expected.setName(name);

    // Ejecución
    CardTypeD actual = mapper.toDto(cardType);

    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envia un elemento valido al metodo toEntity debe retornar un Entity con los mismos datos en los campos
   * amount, customerId y cardId.
   */
  @Test
  public void toEntityValid() {
    String name = "Debit";
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setName(name);
    CardType expected = new CardType();
    expected.setName(name);

    // Ejecución
    CardType actual = mapper.toEntity(cardTypeD);

    Assertions.assertEquals(expected, actual);
  }
}