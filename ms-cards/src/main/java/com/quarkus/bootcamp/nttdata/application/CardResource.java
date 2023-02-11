package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.Card;
import com.quarkus.bootcamp.nttdata.domain.services.CardService;
import com.quarkus.bootcamp.nttdata.domain.services.CartTypeNotFoundException;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/cards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CardResource {
  @Inject
  CardService service;

  @GET
  public Response getAll() {
    return Response.ok(service.getAll()).build();
  }

  @GET
  @Path("/{id}")
  public Response getById(@PathParam("id") Long id) {
    return Response.ok(service.getById(id)).build();
  }

  @POST
  @Transactional
  public Response create(Card card) {
    Card cardNew;
    try {
      cardNew = service.create(card);
    } catch (CartTypeNotFoundException e) {
      return Response.ok(e.getMessage()).status(404).build();
    }
    return Response.ok(cardNew).status(201).build();
  }

  @PUT
  @Path("{id}")
  @Transactional
  public Response update(@PathParam("id") Long id, Card card) {
    Card cardUpdate;
    try {
      cardUpdate = service.update(id, card);
    } catch (CartTypeNotFoundException e) {
      return Response.ok(e.getMessage()).status(404).build();
    }
    return Response.ok(cardUpdate).status(201).build();
  }

  @DELETE
  @Path("{id}")
  @Transactional
  public Response delete(@PathParam("id") Long id) {
    return Response.ok(service.delete(id)).build();
  }
}
