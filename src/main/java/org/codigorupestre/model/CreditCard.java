package org.codigorupestre.model;

import java.sql.Timestamp;

public class CreditCard {
	
	
	public Integer id;
	
	public String cardHolderName;
	
	public String creditCardNumber;
	
	public String expirationDate;
	
	public PaymentNetworks paymentNetworks;
	
	public String cvv;
	
	public Timestamp created;

	@Override
	public String toString() {
		return "CreditCard [id=" + id + ", cardHolderName=" + cardHolderName + ", creditCardNumber=" + creditCardNumber
				+ ", expirationDate=" + expirationDate + ", paymentNetworks=" + paymentNetworks + ", cvv=" + cvv + "]";
	}
	
	

}
