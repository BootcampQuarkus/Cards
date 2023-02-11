package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Card;
import com.quarkus.bootcamp.nttdata.domain.entity.CardType;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardTypeMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardTypeD;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.CardTypeRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@QuarkusTest
class CardTypeServiceTest {
  @Inject
  CardTypeService service;
  @InjectMock
  CardTypeRepository repository;
  @Inject
  CardTypeMapper mapper;

  public CardTypeD createCardTypeDWithoutCardDs(Long id, String name) {
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.id = id;
    cardTypeD.setName(name);
    cardTypeD.setCardDs(new ArrayList<>());
    cardTypeD.setCreatedAt("2023.01.01");
    return cardTypeD;
  }

  public CardType createCardTypeWithoutCards(Long id, String name) {
    CardType cardType = new CardType();
    cardType.setId(id);
    cardType.setName(name);
    return cardType;
  }

  public CardTypeD createCardTypeDSoftDelet(Long id, String name) {
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD = this.createCardTypeDWithoutCardDs(id, name);
    cardTypeD.setDeletedAt("2023.01.01");
    return cardTypeD;
  }

  public CardTypeD createCardTypeDWithCardD(Long id, String name) {
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.id = id;
    cardTypeD.setName(name);
    List<CardD> list = new ArrayList<>();
    list.add(this.createCardDFake(1L));
    cardTypeD.setCardDs(list);
    cardTypeD.setCreatedAt("2023.01.01");
    return cardTypeD;
  }

  public CardType createCardTypeWithCard(Long id, String name) {
    CardType cardType = new CardType();
    cardType.setId(id);
    cardType.setName(name);
    List<Card> list = new ArrayList<>();
    list.add(this.createCardFake(1L));
    cardType.setCards(list);
    return cardType;
  }

  public CardD createCardDFake(Long id) {
    CardD cardD = new CardD();
    cardD.id = id;
    cardD.setSerial("1234-1234-1234-1324");
    cardD.setPin("1234");
    cardD.setMonth(10);
    cardD.setYear(2023);
    cardD.setCvv(123);
    cardD.setProductId(1L);
    cardD.setCreatedAt("2023.01.01");
    cardD.setCardTypeD(new CardTypeD());
    return cardD;
  }

  public Card createCardFake(Long id) {
    Card card = new Card();
    card.setId(id);
    card.setSerial("1234-1234-1234-1324");
    card.setPin("1234");
    card.setMonth(10);
    card.setYear(2023);
    card.setCvv(123);
    card.setProductId(1L);
    return card;
  }

  /**
   * Cuando no se tiene elementos en la BD el metodo getAll debe retornar una lista vacia.
   */
  @Test
  void getAllEmpty() {
    List<CardTypeD> cardTypeList = new ArrayList<>();
    Mockito.when(repository.getAll()).thenReturn(cardTypeList);
    List<CardType> actual = service.getAll();
    Assertions.assertTrue(actual.isEmpty());
  }

  /**
   * El metodo getAll debe retornar una lista.
   */
  @Test
  void getAllReturnListAccount() {
    List<CardTypeD> cardTypeList = new ArrayList<>();
    Mockito.when(repository.getAll()).thenReturn(cardTypeList);
    Assertions.assertInstanceOf(List.class, service.getAll());
  }

  /**
   * Cuando hay elementos en la BD el metodo getAll no retorna una lista vacia.
   */
  @Test
  void getAllValid() {
    List<CardTypeD> list = new ArrayList<>();
    list.add(createCardTypeDWithoutCardDs(101L, "Credit"));
    list.add(createCardTypeDWithoutCardDs(102L, "Debit"));

    Mockito.when(repository.getAll()).thenReturn(list);
    List<CardType> actual = service.getAll();
    Assertions.assertTrue(!actual.isEmpty());
  }

  /**
   * Cuando hay elementos en la BD el metodo getAll retorna una lista con tantos elementos como
   * elementos validos (no eliminados) hay en la bd.
   */
  @Test
  void getAllValidCount() {
    List<CardTypeD> list = new ArrayList<>();
    list.add(createCardTypeDWithoutCardDs(101L, "Credit"));
    list.add(createCardTypeDWithoutCardDs(102L, "Debit"));

    Mockito.when(repository.getAll()).thenReturn(list);
    List<CardType> actual = service.getAll();
    Long expected = 2L;
    Assertions.assertEquals(expected, actual.stream().count());
  }

  /**
   * Cuando hay elementos en la BD que han sido eliminados (softdelete) estos no se deben
   * retornar en la lista.
   */
  @Test
  void getAllValidNotDelete() {
    List<CardTypeD> list = new ArrayList<>();
    list.add(createCardTypeDWithoutCardDs(101L, "Credit"));
    list.add(createCardTypeDWithoutCardDs(102L, "Debit"));
    list.add(createCardTypeDSoftDelet(103L, "Delete"));

    Mockito.when(repository.getAll()).thenReturn(list);
    List<CardType> actual = service.getAll();
    Long expected = 2L;
    Assertions.assertEquals(expected, actual.stream().count());
  }

  /**
   * Cuando no se encuentra el elemento en la BD el metodo getById debe retornar un NotFoundException.
   */
  @Test
  void getByIdEmpty() {
    Long id = 101L;
    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.empty());
    Assertions.assertThrows(NotFoundException.class, () -> service.getById(id));
  }

  /**
   * El metodo getById debe retornar un Dto.
   */
  @Test
  void getByIdReturnCardType() {
    Long id = 101L;
    String name = "Credit";
    CardTypeD cardTypeD = createCardTypeDWithoutCardDs(id, name);

    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.of(cardTypeD));
    Assertions.assertInstanceOf(CardType.class, service.getById(id));
  }

  /**
   * Cuando se encuentra el elemento en la BD se debe retornar el Dto con los valores guardados
   * en la BD.
   */
  @Test
  void getByIdValid() {
    Long id = 101L;
    String name = "Credit";

    CardTypeD cardTypeD = createCardTypeDWithCardD(id, name);
    CardType expected = createCardTypeWithCard(id, name);

    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.of(cardTypeD));
    CardType actual = service.getById(id);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se encuentra el elemento en la BD, pero este se encuentra eliminado (softdelete) se
   * debe retornar un NotFoundException.
   */
  @Test
  void getByIdDelete() {
    Long id = 101L;
    String name = "Credit";
    // Input
    CardTypeD cardTypeD = createCardTypeDSoftDelet(id, name);

    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.of(cardTypeD));
    Assertions.assertThrows(NotFoundException.class, () -> service.getById(id));
  }

  /**
   * El metodo create debe retornar un Dto.
   */
  @Test
  void createReturnAccount() {
    Mockito.when(repository.save(mapper.toDto(new CardType()))).thenReturn(new CardTypeD());
    Assertions.assertInstanceOf(CardType.class, service.create(new CardType()));
  }

  /**
   * Cuando se envia un Dto para guardar, el metodo create retorna el Dto guardado.
   */
  @Test
  void createValid() {
    // Variables
    Long id = 101L;
    String name = "Debit";
    // Input
    CardType cardType = createCardTypeWithCard(id, name);
    CardType expected = createCardTypeWithoutCards(id, name);
    // Resultado esperado
    CardTypeD cardTypeD = createCardTypeDWithCardD(id, name);

    Mockito.when(repository.save(mapper.toDto(cardType))).thenReturn(cardTypeD);
    CardType actual = service.create(cardType);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * EL metodo update retorna un Dto.
   */
  @Test
  void updateReturnAccount() {
    Mockito.when(repository.findByIdOptional(101L)).thenReturn(Optional.of(new CardTypeD()));
    Mockito.when(repository.save(new CardTypeD())).thenReturn(new CardTypeD());
    Assertions.assertInstanceOf(CardType.class, service.update(101L, new CardType()));
  }

  /**
   * Cuando se envía un Dto para actualizar y se encuentra en la BD, el metodo update
   * retorna el Dto actualizado.
   */
  @Test
  void updateValid() {
    // Variables
    Long id = 101L;
    String name = "Credit";
    String nameNew = "Debit";

    // Input
    CardType cardType = createCardTypeWithoutCards(id, nameNew);
    CardType expected = createCardTypeWithoutCards(id, nameNew);

    // Resultado esperado
    CardTypeD cardTypeD = createCardTypeDWithCardD(id, name);
    CardTypeD cardTypeDNew = createCardTypeDWithCardD(id, nameNew);

    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.of(cardTypeD));
    Mockito.when(repository.save(cardTypeDNew)).thenReturn(cardTypeDNew);

    CardType actual = service.update(id, cardType);
    Assertions.assertEquals(expected, actual);
  }

  /**
   * Cuando se envía un Dto para actualizar y si no se encuentra en la BD, el metodo update
   * retorna un NotFoundException.
   */
  @Test
  void updateNotFound() {
    // Variables
    Long id = 101L;
    Mockito.when(repository.findByIdOptional(id)).thenThrow(new NotFoundException());
    Assertions.assertThrows(NotFoundException.class, () -> service.update(id, new CardType()));
  }

  /**
   * El metodo delete retorna un Entity.
   */
  @Test
  void deleteReturnAccount() {
    Mockito.when(repository.findByIdOptional(101L)).thenReturn(Optional.of(new CardTypeD()));
    Mockito.when(repository.softDelete(new CardTypeD())).thenReturn(new CardTypeD());
    Assertions.assertInstanceOf(CardType.class, service.delete(101L));
  }

  /**
   * Cuando se envía un Id de un elemento que no existe al metodo delete, se debe retornar
   * NotFoundException.
   */
  @Test
  void deleteNotFound() {
    Mockito.when(repository.findByIdOptional(101L)).thenReturn(Optional.empty());
    Assertions.assertThrows(NotFoundException.class, () -> service.delete(101L));
  }

  /**
   * Cuando se envía un Id de un elemento que ya se eliminó al metodo delete, se debe retornar
   * NotFoundException.
   */
  @Test
  void deleteSoftDelete() {
    Long id = 101L;
    String name = "Credit";
    CardTypeD cardTypeD = createCardTypeDSoftDelet(id, name);
    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.of(cardTypeD));
    Assertions.assertThrows(NotFoundException.class, () -> service.delete(id));
  }

  /**
   * Cuando se envía un Id valido, el metodo delete debe retornar el Entity eliminado.
   */
  @Test
  void deleteValid() {
    Long id = 101L;
    String name = "Debit";
    CardType expected = createCardTypeWithoutCards(id, name);
    CardTypeD cardTypeD = createCardTypeDWithoutCardDs(id, name);
    Mockito.when(repository.findByIdOptional(id)).thenReturn(Optional.of(cardTypeD));
    Mockito.when(repository.softDelete(cardTypeD)).thenReturn(cardTypeD);
    CardType actual = service.delete(id);
    Assertions.assertEquals(expected, actual);
  }
}