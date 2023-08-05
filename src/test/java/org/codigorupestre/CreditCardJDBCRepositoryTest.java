package org.codigorupestre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.codigorupestre.model.CreditCard;
import org.codigorupestre.model.PaymentNetworks;
import org.codigorupestre.repository.CreditCardJDBCRepository;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(OrderAnnotation.class)
public class CreditCardJDBCRepositoryTest {
	
	@Inject
	private CreditCardJDBCRepository repository;
	
	@Test
	@Order(1)
	public void createCreditCardTest() {
		CreditCard creditCard = new CreditCard();
		
		creditCard.cardHolderName = "Eduardo Castillo Mendoza";
		creditCard.creditCardNumber = "1111-1111-1111-1111";
		creditCard.expirationDate = "10/25";
		creditCard.paymentNetworks = PaymentNetworks.MASTERCARD;
		creditCard.cvv = "368";
		
		repository.create(creditCard);
	}
	
	@Test
	@Order(2)
	public void getAllTest() {
		List<CreditCard> creditCards = repository.getAll();
		assertNotNull(creditCards);
	}
	
	
	@Test
	@Order(3)
	public void getByCreditCardNumberTest() {
		CreditCard creditCard = repository.findByCreditCardNumber("1111-1111-1111-1111");
		assertNotNull(creditCard);
	}
	
	@Test
	@Order(4)
	public void updateCreditCard() {
		CreditCard creditCard = repository.findByCreditCardNumber("1111-1111-1111-1111");

		creditCard.cardHolderName = "Eduardo Castillo Mendoza";
		creditCard.creditCardNumber = "1111-1111-2222-2222";
		creditCard.expirationDate = "10/30";
		creditCard.paymentNetworks = PaymentNetworks.VISA;
		creditCard.cvv = "678";
		
		int camposActualizados = repository.updateCreditCard(creditCard.id, creditCard);
		
		assertEquals(1, camposActualizados);
	}
	
	@Test
	@Order(5)
	public void updateCreditCardExpirationDate() {

		int camposActualizados = repository.updateExpirationDate(2, "50/50");
		
		assertEquals(1, camposActualizados);
	}
	
	/*
	@Test
	@Order(4)
	public void deleteCreditCardNumber() {
	 int registrosEliminados =	repository.delete("1111-1111-1111-1111");
	 assertEquals(1, registrosEliminados);
	}
	*/

}
