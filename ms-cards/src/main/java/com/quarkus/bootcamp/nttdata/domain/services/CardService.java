package com.quarkus.bootcamp.nttdata.domain.services;

import com.quarkus.bootcamp.nttdata.domain.Exceptions.AccountNotFoundException;
import com.quarkus.bootcamp.nttdata.domain.Exceptions.CartTypeNotFoundException;
import com.quarkus.bootcamp.nttdata.domain.Exceptions.LineOfCreditNotFoundException;
import com.quarkus.bootcamp.nttdata.domain.entity.Card;
import com.quarkus.bootcamp.nttdata.domain.interfaces.IService;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardMapper;
import com.quarkus.bootcamp.nttdata.domain.mapper.CardTypeMapper;
import com.quarkus.bootcamp.nttdata.infraestructure.Resources.IAccountApi;
import com.quarkus.bootcamp.nttdata.infraestructure.Resources.ILineOfCreditApi;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.AccountD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.CardD;
import com.quarkus.bootcamp.nttdata.infraestructure.entity.LineOfCreditD;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.CardRepository;
import com.quarkus.bootcamp.nttdata.infraestructure.repository.CardTypeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class CardService implements IService<Card, Card> {
  @Inject
  CardRepository repository;
  @Inject
  CardTypeRepository ctRepository;
  @Inject
  CardTypeService ctService;
  @Inject
  CardTypeMapper ctMapper;
  @Inject
  CardMapper cMapper;
  @RestClient
  IAccountApi accountApi;
  @RestClient
  ILineOfCreditApi lineOfCreditApi;

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
  public Card create(Card card) throws CartTypeNotFoundException, AccountNotFoundException, LineOfCreditNotFoundException {
    CardD cardD = cMapper.toDto(card);
    try {
      cardD.setCardTypeD(ctRepository.getById(card.getCardTypeId()));
    } catch (NotFoundException e) {
      throw new CartTypeNotFoundException("Cart Type not found.");
    }
    if (cardD.getCardTypeD().getName().equals("Credit")) {
      validateLineOfCreditD(card.getProductId());
    } else if (cardD.getCardTypeD().getName().equals("Debit")) {
      validateAccountD(card.getProductId());
    }
    card = cMapper.toEntity(repository.save(cardD));
    updateAccountD(card);
    return card;
  }

  @Override
  public Card update(Long id, Card card) throws CartTypeNotFoundException, AccountNotFoundException, LineOfCreditNotFoundException {
    validateAccountD(card.getProductId());
    CardD cardD = repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .orElseThrow(() -> new NotFoundException());
    cardD.setSerial(card.getSerial());
    cardD.setPin(card.getPin());
    cardD.setMonth(card.getMonth());
    cardD.setYear(card.getYear());
    cardD.setCvv(card.getCvv());
    cardD.setCustomerId(card.getCustomerId());
    cardD.setProductId(card.getProductId());
    try {
      cardD.setCardTypeD(ctRepository.getById(card.getCardTypeId()));
    } catch (NotFoundException e) {
      throw new CartTypeNotFoundException("Cart Type not found.");
    }
    if (cardD.getCardTypeD().getName().equals("Credit")) {
      validateLineOfCreditD(card.getProductId());
    } else if (cardD.getCardTypeD().getName().equals("Debit")) {
      validateAccountD(card.getProductId());
    }
    return cMapper.toEntity(repository.save(cardD));
  }

  @Override
  public Card delete(Long id) {
    CardD cardD = repository.findByIdOptional(id)
          .filter(p -> (p.getDeletedAt() == null))
          .orElseThrow(() -> new NotFoundException());
    return cMapper.toEntity(repository.softDelete(cardD));
  }

  public Boolean validateAccountD(Long productId) throws AccountNotFoundException {
    AccountD accountD = accountApi.getById(productId);
    if (accountD.getId() == null) {
      throw new AccountNotFoundException("Account not found.");
    }
    return true;
  }

  public Boolean validateLineOfCreditD(Long productId) throws LineOfCreditNotFoundException {
    LineOfCreditD lineOfCreditD = lineOfCreditApi.getById(productId);
    if (lineOfCreditD.getId() == null) {
      throw new LineOfCreditNotFoundException("LineOfCredit not found.");
    }
    return true;
  }
  public void updateAccountD(Card card) throws AccountNotFoundException {
    AccountD accountD = accountApi.getById(card.getProductId());
    if (accountD.getId() == null) {
      throw new AccountNotFoundException("Account not found.");
    }
    accountD.setCardId(card.getId());
    accountApi.update(accountD.getId(),accountD);
  }
}

