package com.airline.webservices.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.airline.models.Passenger;
import com.airline.service.PassengerService;

@Path("/passengers")
@Transactional
public class PassengersWebService {

    @PersistenceContext(unitName = "airline")
    EntityManager em;

    @EJB
    PassengerService ps;

    @Context
    UriInfo pUriInfo;

    public PassengersWebService() {
    }

    /* Method to extract the entire passenger list from the database */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Passenger> getPassengers() {
	List<Passenger> pList = ps.getPassengers();
	return pList;
    }

    /* Method to extract single result from database */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{passenger_id}") // This will add a dynamic value at the end of the url path
    public Passenger getPassenger(@PathParam("passenger_id") Integer passengerId) {
	Passenger p = ps.getPassenger(passengerId);
	if (p == null) {
	    throw new NotFoundException("Passenger with passenger id " + passengerId + " not exist");
	}
	return p;
    }

    /* Updating existing database table */   
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPassenger(Passenger p) {
	p = ps.addPassenger(p);
	UriBuilder pUriBuilder = pUriInfo.getAbsolutePathBuilder();
	URI pUri = pUriBuilder.path(String.valueOf(p.getId()) ).build();
	return Response.created(pUri).build();
    }
    
    @PUT
    @Path("/edit/{pId}")
    @Consumes("application/json")
    public Response updatedPassenger(@PathParam("pId") Integer passengerId, Passenger pUpdated) {
	
	pUpdated = ps.upDatePassenger(passengerId, pUpdated);
	if(pUpdated == null) {
	    throw new NotFoundException("The passenger with an id "+ passengerId + "not found");
	}
	return Response.ok(pUpdated).build();
    }
    
    @PUT
    @Path("/edit2/{pId}")
    @Consumes("application/json")
    public Response updatedPassenger2(@PathParam("pId") Integer passengerId, Passenger pUpdated) {
	
	pUpdated = ps.upDatePassenger2(passengerId, pUpdated);
	if(pUpdated == null) {
	    throw new NotFoundException("The passenger with an id "+ passengerId + "not found");
	}
	return Response.ok(pUpdated).build();
    }

    @DELETE
    @Path("{passenger_id}")
    public Response deleteFlight(@PathParam("passenger_id") Integer passengerId) {
	Passenger passengerToRemove = em.find(Passenger.class, passengerId);
	if (passengerToRemove == null) {
	    throw new NotFoundException("The flight with an id of" + passengerId + "was not found");
	}
	em.remove(passengerToRemove);
	return Response.noContent().build();
    }
}





















