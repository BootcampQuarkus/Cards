package com.quarkus.bootcamp.nttdata.infraestructure.Resources;

import com.quarkus.bootcamp.nttdata.infraestructure.entity.LineOfCreditD;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient
@Path("/linesofcredit")
public interface ILineOfCreditApi {

  @GET
  @Path("/")
  List<LineOfCreditD> getAll();

  @GET
  @Path("/{id}")
  @Fallback(fallbackMethod = "fallbackGetById")
  LineOfCreditD getById(@PathParam("id") Long id);

  default LineOfCreditD fallbackGetById(Long id) {
    return new LineOfCreditD();
  }
}
