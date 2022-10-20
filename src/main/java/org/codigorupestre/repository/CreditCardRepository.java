package org.codigorupestre.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.codigorupestre.model.CreditCard;
import org.codigorupestre.model.PaymentNetworks;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CreditCardRepository {
	
	@Inject
	private Logger log;
	
	private static List<CreditCard> creditCards = new ArrayList<>();
	
	
	
	static {
		CreditCard creditCard = new CreditCard();
		
		creditCard.id = 1;
		creditCard.cardHolderName = "Eduardo Castillo Mendoza";
		creditCard.creditCardNumber = "6584-2346-1235-7685";
		creditCard.expirationDate = "10/40";
		creditCard.paymentNetworks = PaymentNetworks.VISA;
		creditCard.cvv = "235";
		
		creditCards.add(creditCard);
	}
	
	public List<CreditCard> getAll(){
		log.info("Get all credit cards");
		return creditCards;
	}
	
	public CreditCard create(CreditCard creditCard) {
		log.info("Create New: " + creditCard);
		creditCard.id = creditCards.size() + 1;
		creditCards.add(creditCard);
		return creditCard;
	}
	
	public CreditCard updateCreditCard(String creditCardNumber, CreditCard creditCard) {
		
		log.info("Update credit card number " + creditCardNumber);
		log.info("New: " + creditCard);
		
		creditCards.set(creditCard.id - 1, creditCard);
		
		return creditCard;
	}
	
	public CreditCard updateCvv(String creditCardNumber, String cvv) {
		
		CreditCard creditCard = creditCards.stream()
		.filter(card -> card.creditCardNumber.equals(creditCardNumber))
		.findFirst()
		.get();
		
		creditCard.cvv = cvv;
		
		creditCards.set(creditCard.id - 1, creditCard);
		
		return creditCard;	
	}
	
	public CreditCard delete(String creditCardNumber) {
		
		CreditCard creditCard = creditCards.stream()
				.filter(card -> card.creditCardNumber.equals(creditCardNumber))
				.findFirst()
				.get();
		
		creditCards.remove(creditCard);
		
		return creditCard;
	}
	
	
	public Optional<CreditCard> findByCreditCardNumber(String creditCardNumber){
		return creditCards.stream()
		.filter(card -> card.creditCardNumber.equals(creditCardNumber))
		.findFirst();
	}

}








