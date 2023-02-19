package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidad de base de datos para los tipo de tarjetas.
 *
 * @pdiaz
 */
@Entity
@Table(name = "cardType")
@Data
@NoArgsConstructor
public class CardTypeD extends PanacheEntity {
  /**
   * Nombre con el cual nos referimos al tipo de tarjeta.
   * Ejemplo:
   * - Debito
   * - Credito
   */
  protected String name;
  /**
   * Collección de tarjetas pertenecientes a este tipo.
   * El campo para hacer el mapping es cardTypeD.
   */
  @OneToMany(mappedBy = "cardTypeD", orphanRemoval = true)
  protected List<CardD> cardDs;
  /**
   * Fecha en la que se creó la linea de crédito.
   */
  @Column(nullable = true)
  protected String createdAt = null;
  /**
   * Fecha de la ultima actualización la linea de crédito.
   */
  @Column(nullable = true)
  protected String updatedAt = null;
  /**
   * Fecha en la que se eliminó la linea de crédito.
   */
  @Column(nullable = true)
  protected String deletedAt = null;
}
