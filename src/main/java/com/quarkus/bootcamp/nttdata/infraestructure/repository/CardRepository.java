package com.quarkus.bootcamp.nttdata.infraestructure.repository;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.AccountD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardD;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CardRepository implements IRepository<CardD> {
  /**
   * Se encarga de devolver todos los elementos no eliminados (softdelete)
   * de la BD.
   *
   * @return Lista de elementos no eliminados.
   */
  @Override
  public List<CardD> getAll() {
    return CardD.listAll();
  }

  /**
   * Se encarga de devolver el elemento solicitado por el identificador
   * siempre y cuando no este eliminado (softDelete).
   *
   * @param id Identificador del elemento a devolver.
   * @return Elemento encontrado.
   */
  @Override
  public CardD getById(Long id) {
    Optional<CardD> cardD = CardD.findByIdOptional(id);
    if (cardD.isEmpty()) {
      throw new NotFoundException("Account not found");
    }
    return cardD.get();
  }

  /**
   * Guarda el elemento en la base de datos (creacion y actualización).
   *
   * @param cardD Elemento a guardar.
   * @return Retorna el elemento guardado.
   */
  @Override
  public CardD save(CardD cardD) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    String date = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
    if (cardD.getCreatedAt() == null) {
      cardD.setCreatedAt(date);
    } else {
      cardD.setUpdatedAt(date);
    }
    cardD.persist();
    return cardD;
  }

  /**
   * Realiza la eliminación logica del elemento
   *
   * @param cardD Elemento a borrar logicamente.
   * @return Elemento borrado logicamente.
   */
  @Override
  public CardD softDelete(CardD cardD) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    cardD.setDeletedAt(ZonedDateTime.now(ZoneId.systemDefault()).format(formatter));
    return this.save(cardD);
  }
}