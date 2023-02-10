package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.CardType;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IService;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardMapper;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardTypeMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardTypeD;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.CardTypeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CardTypeService implements IService<CardType, CardType> {
  @Inject
  CardTypeRepository repository;
  @Inject
  CardTypeMapper ctMapper;
  @Inject
  CardMapper cMapper;

  @Override
  public List<CardType> getAll() {
    return repository.getAll()
          .stream()
          .filter(p -> (p.getDeletedAt() == null))
          .map(p -> {
            CardType cardType = ctMapper.toEntity(p);
            cardType.setCards(p.getCardDs()
                  .stream()
                  .map(q -> cMapper.toEntity(q))
                  .collect(Collectors.toSet()));
            return cardType;
          })
          .toList();
  }

  @Override
  public CardType getById(Long id) {
    return repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .map(p -> {
            CardType cardType = ctMapper.toEntity(p);
            cardType.setCards(p.getCardDs()
                  .stream()
                  .map(q -> cMapper.toEntity(q))
                  .collect(Collectors.toSet()));
            return cardType;
          })
          .orElseThrow(() -> new NotFoundException());
  }

  @Override
  public CardType create(CardType cardType) {
    return ctMapper.toEntity(repository.save(ctMapper.toDto(cardType)));
  }

  @Override
  public CardType update(Long id, CardType cardType) {
    CardTypeD cardTypeD = repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .orElseThrow(() -> new NotFoundException());
    cardTypeD.setName(cardType.getName());
    return ctMapper.toEntity(repository.save(cardTypeD));
  }

  @Override
  public CardType delete(Long id) {
    CardTypeD cardTypeD = repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .orElseThrow(() -> new NotFoundException());
    return ctMapper.toEntity(repository.softDelete(cardTypeD));
  }
}