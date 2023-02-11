package com.quarkus.bootcamp.nttdata.domain.mapper;

import com.quarkus.bootcamp.nttdata.domain.entity.Card;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardTypeD;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CardMapperTest {
  @Inject
  CardMapper mapper;

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
    Assertions.assertInstanceOf(CardD.class, mapper.toDto(new Card()));
  }

  /**
   * El metodo toDto debe retornar un Entity.
   */
  @Test
  public void toEntityReturnEntity() {
    String serial = "4970-1100-0000-1029";
    String pin = "1234";
    Integer month = 6;
    Integer year = 2025;
    Integer cvv = 123;
    Long customerId = 101L;
    Long productId = 101L;

    String name = "Credit";

    CardD cardD = new CardD();
    cardD.id = 101L;
    cardD.setSerial(serial);
    cardD.setPin(pin);
    cardD.setMonth(month);
    cardD.setYear(year);
    cardD.setCvv(cvv);
    cardD.setCustomerId(customerId);
    cardD.setProductId(productId);

    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.id = 101L;
    cardTypeD.setName(name);

    cardD.setCardTypeD(cardTypeD);

    Assertions.assertInstanceOf(Card.class, mapper.toEntity(cardD));
  }

  /**
   * Cuando se envia un elemento vacio al metodo toDto debe retornar un Dto vacio.
   */
  @Test
  public void toDtoVoid() {
    Card card = new Card();
    CardD expected = new CardD();
    CardD actual = mapper.toDto(card);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envia un elemento vacio al metodo toEntity debe retornar un Entity vacio.
   */
  @Test
  public void toEntityVoid() {
    String serial = "4970-1100-0000-1029";
    String pin = "1234";
    Integer month = 6;
    Integer year = 2025;
    Integer cvv = 123;
    Long customerId = 101L;
    Long productId = 101L;

    String name = "Credit";

    CardD cardD = new CardD();
    cardD.id = 101L;
    cardD.setSerial(serial);
    cardD.setPin(pin);
    cardD.setMonth(month);
    cardD.setYear(year);
    cardD.setCvv(cvv);
    cardD.setCustomerId(customerId);
    cardD.setProductId(productId);

    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.id = 101L;
    cardTypeD.setName(name);

    Card expected = new Card();
    expected.setId(101L);
    expected.setSerial(serial);
    expected.setPin(pin);
    expected.setMonth(month);
    expected.setYear(year);
    expected.setCvv(cvv);
    expected.setCustomerId(customerId);
    expected.setProductId(productId);
    expected.setCardTypeId(101L);

    cardD.setCardTypeD(cardTypeD);
    Card actual = mapper.toEntity(cardD);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envia un elemento valido al metodo toDto debe retornar un Dto con los mismos datos en los campos
   * amount, customerId y cardId.
   */
  @Test
  public void toDtoValid() {
    String serial = "4970-1100-0000-1029";
    String pin = "1234";
    Integer month = 6;
    Integer year = 2025;
    Integer cvv = 123;
    Long customerId = 101L;
    Long productId = 101L;

    Card card = new Card();
    card.setSerial(serial);
    card.setPin(pin);
    card.setMonth(month);
    card.setYear(year);
    card.setCvv(cvv);
    card.setCustomerId(customerId);
    card.setProductId(productId);

    CardD expected = new CardD();
    expected.setSerial(serial);
    expected.setPin(pin);
    expected.setMonth(month);
    expected.setYear(year);
    expected.setCvv(cvv);
    expected.setCustomerId(customerId);
    expected.setProductId(productId);

    // Ejecución
    CardD actual = mapper.toDto(card);

    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envia un elemento valido al metodo toEntity debe retornar un Entity con los mismos datos en los campos
   * amount, customerId y cardId.
   */
  @Test
  public void toEntityValid() {
    String serial = "4970-1100-0000-1029";
    String pin = "1234";
    Integer month = 6;
    Integer year = 2025;
    Integer cvv = 123;
    Long customerId = 101L;
    Long productId = 101L;

    String name = "Credit";

    CardD cardD = new CardD();
    cardD.id = 101L;
    cardD.setSerial(serial);
    cardD.setPin(pin);
    cardD.setMonth(month);
    cardD.setYear(year);
    cardD.setCvv(cvv);
    cardD.setCustomerId(customerId);
    cardD.setProductId(productId);

    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.id = 101L;
    cardTypeD.setName(name);

    cardD.setCardTypeD(cardTypeD);

    Card expected = new Card();
    expected.setId(101L);
    expected.setSerial(serial);
    expected.setPin(pin);
    expected.setMonth(month);
    expected.setYear(year);
    expected.setCvv(cvv);
    expected.setCustomerId(customerId);
    expected.setProductId(productId);
    expected.setCardTypeId(101L);

    // Ejecución
    Card actual = mapper.toEntity(cardD);

    Assertions.assertEquals(expected, actual);
  }
}