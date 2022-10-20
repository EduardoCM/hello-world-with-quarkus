package org.codigorupestre.api;

import java.util.Optional;

import javax.inject.Inject;
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

import org.codigorupestre.model.CreditCard;
import org.codigorupestre.repository.CreditCardRepository;

@Path("/api/creditcards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CreditCardResource {

	@Inject
	private CreditCardRepository repository;

	@GET
	public Response getAllCreditCards() {
		return Response.ok(repository.getAll()).build();
	}

	@POST
	public Response createNew(CreditCard creditCard) {
		return Response.ok(repository.create(creditCard)).build();
	}
	
	@PUT
	@Path("{creditCardNumber}" )
	public Response update(@PathParam("creditCardNumber") String creditCardNumber, CreditCard creditCard) {
		return Response.ok(repository.updateCreditCard(creditCardNumber, creditCard)).build();
	}
	
	@PATCH
	@Path("{creditCardNumber}/{cvv}")
	public Response updateCvv(@PathParam("creditCardNumber") String creditCardNumber,
			@PathParam("cvv") String cvv) {
		return Response.ok(repository.updateCvv(creditCardNumber, cvv)).build();
	}
	
	
	@DELETE
	@Path("{creditCardNumber}")
	public Response deleteCreditCard(@PathParam("creditCardNumber") String creditCardNumber) {
		return Response.ok(repository.delete(creditCardNumber)).build();
	}
	
	
	@HEAD
	@Path("{creditCardNumber}")
	public Response validarExistencia(@PathParam("creditCardNumber") String creditCardNumber) {
		
		Optional<CreditCard> creditCard = repository.findByCreditCardNumber(creditCardNumber);
		
		if(!creditCard.isEmpty()) {
			return Response.ok(creditCard.get()).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	
	@GET
	@Path("{creditCardNumber}")
	public Response findCreditCard(@PathParam("creditCardNumber") String creditCardNumber) {
		
		Optional<CreditCard> creditCard = repository.findByCreditCardNumber(creditCardNumber);
		
		if(!creditCard.isEmpty()) {
			return Response.ok(creditCard.get()).build();
		} else {
			return Response.noContent().build();
		}
	}
	
	
	
	
	
	
	
	
	//React for Java Enterprise Developers 9/20 - Formulario Material UI Axios REST
	
	

}
