package com.quarkus.bootcamp.nttdata.infraestructure.Resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.AccountD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/accounts")
public interface IAccountApi {

  @GET
  @Path("/")
  List<AccountD> getAll();

  @GET
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackGetById")
  AccountD getById(@PathParam("id") Long id);

  default AccountD fallbackGetById(Long id) {
    return new AccountD();
  }

  @PUT
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackupdate")
  AccountD update(@PathParam("id")Long id, AccountD accountD);
  default AccountD fallbackupdate(Long id, AccountD accountD) {
    return new AccountD();
  }
}
