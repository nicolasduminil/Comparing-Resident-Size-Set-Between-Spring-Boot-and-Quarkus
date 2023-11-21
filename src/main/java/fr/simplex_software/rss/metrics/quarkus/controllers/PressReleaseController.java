package fr.simplex_software.rss.metrics.quarkus.controllers;

import fr.simplex_software.rss.metrics.quarkus.interceptors.LogTime;
import fr.simplex_software.rss.metrics.quarkus.model.*;
import fr.simplex_software.rss.metrics.quarkus.services.*;

import javax.inject.*;
import javax.validation.*;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.*;

@Path("/rss")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@LogTime
public class PressReleaseController
{
  @Inject
  PressReleaseService pressReleaseService;

  @GET
  @Path("/all")
  public Collection<PressRelease> getAllPressReleases()
  {
    return pressReleaseService.getPressReleases();
  }

  @GET
  @Path("/pressRelease/{id}")
  public Response getPressRelease(@PathParam("id") Long id)
  {
    return pressReleaseService.getPressReleaseById(id).map(Response::ok).orElseGet(() -> Response.status(Response.Status.NOT_FOUND)).build();
  }

  @POST
  @Path("/add")
  public Response addPressRelease(@Valid PressRelease pressRelease)
  {
    pressReleaseService.createPressRelease(pressRelease);
    return Response.ok().build();
  }

  @PUT
  @Path("/update")
  public Response editPressRelease(@Valid PressRelease pressRelease)
  {
    pressReleaseService.updatePressRelease(pressRelease);
    return Response.ok().build();
  }

  @DELETE
  @Path("/delete/{id}")
  public Response deletePressRelease(@PathParam("id") Long pressReleaseId)
  {
    return pressReleaseService.deletePressReleaseById(pressReleaseId) ?
      Response.ok("PressRelease is removed").build() :
      Response.status(Response.Status.EXPECTATION_FAILED).build();
  }
}
