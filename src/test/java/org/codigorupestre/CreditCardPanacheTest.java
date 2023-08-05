package org.codigorupestre;

import org.codigorupestre.model.CreditCardPanacheEntity;
import org.codigorupestre.model.PaymentNetworks;
import org.junit.jupiter.api.Test;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestTransaction
public class CreditCardPanacheTest {
	
	
	@Test
	public void createCreditCard() {
		
		CreditCardPanacheEntity creditCard = new CreditCardPanacheEntity();
		creditCard.cardHolderName = "Eduardo Castillo Mendoza";
		creditCard.creditCardNumber = "6654-2342-1231-4563-2341";
		creditCard.expirationDate = "50/50";
		creditCard.paymentNetworks = PaymentNetworks.VISA.toString();
		creditCard.cvv = "542";
		
		creditCard.persist();
	}

}
