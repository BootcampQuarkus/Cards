package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad de base de datos para las tarjetas.
 *
 * @pdiaz
 */
@Entity
@Table(name = "card")
@Data
@NoArgsConstructor
public class CardD extends PanacheEntity {
  /**
   * Numero de la tarjeta de 16 digitos en grupos de 4 caracteres
   * separados por "-".
   * El numero de la tarjeta debe cumplir el algoritmo de
   * Luhn.
   */
  protected String serial;
  /**
   * Contraseña de la tarjeta.
   */
  protected String pin;
  /**
   * Mes de vencimiento de la tarjta.
   */
  protected Integer month;
  /**
   * Año de vencimiento de la tarjeta.
   */
  protected Integer year;
  /**
   * Cvv
   */
  protected Integer cvv;
  /**
   * Identificador del cliente dueño de la tarjeta.
   */
  protected Long customerId;
  /**
   * Identificador del producto principal asociado a la tarjeta.
   */
  protected Long productId;
  /**
   * Tipo de tarjeta.
   */
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cardTypeId")
  protected CardTypeD cardTypeD;
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
