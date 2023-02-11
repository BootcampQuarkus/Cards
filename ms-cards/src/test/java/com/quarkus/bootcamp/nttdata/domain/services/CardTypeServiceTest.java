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

import java.util.*;

@QuarkusTest
class CardTypeServiceTest {
  @Inject
  CardTypeService service;
  @InjectMock
  CardTypeRepository repository;
  @Inject
  CardTypeMapper mapper;

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
    for (int i = 0 ; i <= 3 ;  i++){
      CardTypeD cardTypeD = new CardTypeD();
      Set<CardD> listCard = new HashSet<>();
      for(int j = 0 ; j <= 3 ; j++){
        CardD cardD = new CardD();
        cardD.setCardTypeD(new CardTypeD());
        listCard.add(cardD);
      }
      cardTypeD.setCardDs(listCard);
      list.add(cardTypeD);
    }
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
    for (int i = 0 ; i <= 3 ;  i++){
      CardTypeD cardTypeD = new CardTypeD();
      Set<CardD> listCard = new HashSet<>();
      for(int j = 0 ; j <= 3 ; j++){
        CardD cardD = new CardD();
        cardD.setCardTypeD(new CardTypeD());
        listCard.add(cardD);
      }
      cardTypeD.setCardDs(listCard);
      list.add(cardTypeD);
    }
    Mockito.when(repository.getAll()).thenReturn(list);
    List<CardType> actual = service.getAll();
    Long expected = 4L;
    Assertions.assertEquals(expected, actual.stream().count());
  }

  /**
   * Cuando hay elementos en la BD que han sido eliminados (softdelete) estos no se deben
   * retornar en la lista.
   */
  @Test
  void getAllValidNotDelete() {
    List<CardTypeD> list = new ArrayList<>();
    for (int i = 0 ; i <= 2 ;  i++){
      CardTypeD cardTypeD = new CardTypeD();
      Set<CardD> listCard = new HashSet<>();
      for(int j = 0 ; j <= 3 ; j++){
        CardD cardD = new CardD();
        cardD.setCardTypeD(new CardTypeD());
        listCard.add(cardD);
      }
      cardTypeD.setCardDs(listCard);
      list.add(cardTypeD);
    }

    // Objeto no valido
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setDeletedAt("2023.10.10");
    Set<CardD> listCard = new HashSet<>();
    for(int j = 0 ; j <= 3 ; j++){
      CardD cardD = new CardD();
      cardD.setCardTypeD(new CardTypeD());
      listCard.add(cardD);
    }
    cardTypeD.setCardDs(listCard);
    list.add(cardTypeD);

    Mockito.when(repository.getAll()).thenReturn(list);
    List<CardType> actual = service.getAll();
    Long expected = 3L;
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
  void getByIdReturnAccount() {
    Long id = 101L;

    CardTypeD cardTypeD = new CardTypeD();
    Set<CardD> listCard = new HashSet<>();
    for(int j = 0 ; j <= 3 ; j++){
      CardD cardD = new CardD();
      cardD.setCardTypeD(new CardTypeD());
      listCard.add(cardD);
    }
    cardTypeD.setCardDs(listCard);

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

    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.id = id;
    cardTypeD.setName(name);
    Set<CardD> listCardD = new HashSet<>();
    CardD cardD = new CardD();
    cardD.id = id;
    cardD.setSerial("1234-1234-1234-1324");
    cardD.setCardTypeD(new CardTypeD());
    listCardD.add(cardD);
    cardTypeD.setCardDs(listCardD);

    // Resultado esperado
    CardType expected = new CardType();
    expected.setId(id);
    expected.setName(name);
    List<Card> listCard = new ArrayList<>();
    Card card = new Card();
    card.setId(id);
    card.setSerial("1234-1234-1234-1324");
    card.setCardType(null);
    listCard.add(card);
    expected.setCards(listCard);

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

    // Input
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setDeletedAt("2023.01.01");

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
    String name = "Debit";

    // Input
    CardType cardType = new CardType();
    cardType.setName(name);
    CardType expected = cardType;

    // Resultado esperado
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setName(name);

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
    CardType cardType = new CardType();
    cardType.setName(name);
    CardType expected = cardType;

    // Resultado esperado
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setName(name);
    cardTypeD.setCreatedAt("2023.01.01");
    // --
    CardTypeD cardTypeDNew = new CardTypeD();
    cardTypeDNew.setName(nameNew);
    cardTypeDNew.setCreatedAt("2023.01.01");

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
    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setDeletedAt("2023.10.10");

    Mockito.when(repository.findByIdOptional(101L)).thenReturn(Optional.of(cardTypeD));
    Assertions.assertThrows(NotFoundException.class, () -> service.delete(101L));
  }

  /**
   * Cuando se envía un Id valido, el metodo delete debe retornar el Entity eliminado.
   */
  @Test
  void deleteValid() {
    // Variables
    String name = "Debit";

    CardType expected = new CardType();
    expected.setName(name);

    CardTypeD cardTypeD = new CardTypeD();
    cardTypeD.setName(name);

    Mockito.when(repository.findByIdOptional(101L)).thenReturn(Optional.of(cardTypeD));
    Mockito.when(repository.softDelete(cardTypeD)).thenReturn(cardTypeD);

    CardType actual = service.delete(101L);
    Assertions.assertEquals(expected, actual);
  }
}