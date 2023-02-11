package com.quarkus.bootcamp.nttdata.domain.services;

public class CartTypeNotFoundException extends Exception {
  public CartTypeNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
