package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.entity.Card;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IService;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardMapper;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardTypeMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardD;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.CardRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.CardTypeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

import java.util.List;

@ApplicationScoped
public class CardService implements IService<Card, Card> {
  @Inject
  CardRepository repository;
  @Inject
  CardTypeRepository ctRepository;
  @Inject
  CardTypeMapper ctMapper;
  @Inject
  CardMapper cMapper;

  @Override
  public List<Card> getAll() {
    return repository.getAll()
          .stream()
          .filter(p -> (p.getDeletedAt() == null))
          .map(p -> {
            Card card = cMapper.toEntity(p);
            card.setCardType(ctMapper.toEntity(p.getCardTypeD()));
            return card;
          })
          .toList();
  }

  @Override
  public Card getById(Long id) {
    return repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .map(p -> {
            Card card = cMapper.toEntity(p);
            card.setCardType(ctMapper.toEntity(p.getCardTypeD()));
            return card;
          })
          .orElseThrow(() -> new NotFoundException());
  }

  @Override
  public Card create(Card card) {
    CardD cardD = cMapper.toDto(card);
    cardD.setCardTypeD(ctRepository.getById(card.getCardTypeId()));
    return cMapper.toEntity(repository.save(cardD));
  }

  @Override
  public Card update(Long id, Card card) {
    CardD cardD = repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .orElseThrow(() -> new NotFoundException());
    cardD.setPin(card.getPin());
    cardD.setSerial(card.getSerial());
    cardD.setCardTypeD(ctMapper.toDto(card.getCardType()));
    return cMapper.toEntity(repository.save(cardD));
  }

  @Override
  public Card delete(Long id) {
    CardD cardD = repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .orElseThrow(() -> new NotFoundException());
    return cMapper.toEntity(repository.softDelete(cardD));
  }
}
