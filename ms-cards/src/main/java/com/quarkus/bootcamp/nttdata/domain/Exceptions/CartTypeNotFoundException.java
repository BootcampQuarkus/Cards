package com.quarkus.bootcamp.nttdata.domain.Exceptions;

public class CartTypeNotFoundException extends Exception {
  public CartTypeNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
