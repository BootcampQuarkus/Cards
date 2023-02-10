package com.quarkus.bootcamp.nttdata.infraestructure.repository;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardTypeD;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class CardTypeRepository implements IRepository<CardTypeD> {
  /**
   * Se encarga de devolver todos los elementos no eliminados (softdelete)
   * de la BD.
   *
   * @return Lista de elementos no eliminados.
   */
  @Override
  public List<CardTypeD> getAll() {
    return CardTypeD.listAll();
  }
  /**
   * Se encarga de devolver el elemento solicitado por el identificador
   * siempre y cuando no este eliminado (softDelete).
   *
   * @param id Identificador del elemento a devolver.
   * @return Elemento encontrado.
   */
  @Override
  public CardTypeD getById(Long id) {
    Optional<CardTypeD> cardTypeD = CardTypeD.findByIdOptional(id);
    if (cardTypeD.isEmpty()) {
      throw new NotFoundException("CardType not found");
    }
    return cardTypeD.get();
  }
  /**
   * Guarda el elemento en la base de datos (creacion y actualización).
   *
   * @param cardTypeD Elemento a guardar.
   * @return Retorna el elemento guardado.
   */
  @Override
  public CardTypeD save(CardTypeD cardTypeD) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    String date = ZonedDateTime.now(ZoneId.systemDefault()).format(formatter);
    if (cardTypeD.getCreatedAt() == null) {
      cardTypeD.setCreatedAt(date);
    } else {
      cardTypeD.setUpdatedAt(date);
    }
    cardTypeD.persist();
    return cardTypeD;
  }
  /**
   * Realiza la eliminación logica del elemento
   *
   * @param cardTypeD Elemento a borrar logicamente.
   * @return Elemento borrado logicamente.
   */
  @Override
  public CardTypeD softDelete(CardTypeD cardTypeD) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu.MM.dd.HH:mm:ss");
    cardTypeD.setDeletedAt(ZonedDateTime.now(ZoneId.systemDefault()).format(formatter));
    return this.save(cardTypeD);
  }
}