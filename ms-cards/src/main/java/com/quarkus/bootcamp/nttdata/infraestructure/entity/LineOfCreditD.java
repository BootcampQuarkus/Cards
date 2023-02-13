package com.quarkus.bootcamp.nttdata.infraestructure.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LineOfCreditD {
  protected Long id;
  protected Double amount;
  protected Long customerId;
  protected Double available;
  protected Double costs;
  protected String closingDate;
  protected String paymentDueDate;
}
