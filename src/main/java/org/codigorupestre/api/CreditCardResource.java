package org.codigorupestre.api;

import java.util.Optional;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codigorupestre.model.CreditCard;
import org.codigorupestre.model.CreditCardEntity;
import org.codigorupestre.model.CreditCardPanacheEntity;
import org.codigorupestre.repository.CreditCardJDBCRepository;
import org.codigorupestre.repository.CreditCardJPARepository;
import org.codigorupestre.repository.CreditCardRepository;

@Path("/api/creditcards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class CreditCardResource {

	@Inject
	private CreditCardRepository repository;
	
	@Inject
	private CreditCardJDBCRepository jdbcRepository;
	
	@Inject
	private CreditCardJPARepository jpaRepository;
	

	@GET
	public Response getAllCreditCards() {
		return Response.ok(jdbcRepository.getAll()).build();
	}

	@POST
	public Response createNew(CreditCardPanacheEntity creditCard) {
		creditCard.persist();
		return Response.status(Status.CREATED).entity(creditCard).build();
	}
	
	@GET
	@Path("/id/{id}")
	public Response findCreditCard(@PathParam("id") Integer id) {
		
		CreditCardEntity creditCard = jpaRepository.findById(id);
		
		if(creditCard != null) {
			return Response.ok(creditCard).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	@PUT
	@Path("{creditCardNumber}" )
	public Response update(@PathParam("creditCardNumber") int id, CreditCard creditCard) {
		return Response.ok(jdbcRepository.updateCreditCard(id, creditCard)).build();
	}
	
	@PATCH
	@Path("{id}/{cvv}")
	public Response updateCvv(@PathParam("id") int id,
			@PathParam("cvv") String cvv) {
		return Response.ok(jdbcRepository.updateExpirationDate(id, cvv)).build();
	}
	
	
	@DELETE
	@Path("{creditCardNumber}")
	public Response deleteCreditCard(@PathParam("creditCardNumber") String creditCardNumber) {
		return Response.ok(jdbcRepository.delete(creditCardNumber)).build();
	}
	
	
	@HEAD
	@Path("{creditCardNumber}")
	public Response validarExistencia(@PathParam("creditCardNumber") String creditCardNumber) {
		
		CreditCard creditCard = jdbcRepository.findByCreditCardNumber(creditCardNumber);
		
		if(creditCard != null) {
			return Response.ok(creditCard).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	
	@GET
	@Path("{creditCardNumber}")
	public Response findCreditCard(@PathParam("creditCardNumber") String creditCardNumber) {
		
		CreditCard creditCard = jdbcRepository.findByCreditCardNumber(creditCardNumber);
		
		if(creditCard != null) {
			return Response.ok(creditCard).build();
		} else {
			return Response.noContent().build();
		}
	}

}
