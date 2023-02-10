package com.quarkus.bootcamp.nttdata.domain.interfaces;

public interface IMapper<T, U> {
  U toDto(T t) throws NullPointerException;

  T toEntity(U u) throws NullPointerException;
}
