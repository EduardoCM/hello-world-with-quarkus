package org.codigorupestre.repository;

public class SQLConstants {

	public static final String INSERT_CREDIT_CARD = "INSERT INTO SpaceMoneyCreditCards.dbo.CreditCards "
			+ "(cardHolderName, creditCardNumber, expirationDate, paymentNetworks, cvv, created) "
			+ "VALUES(?, ?, ?, ?, ?, GETDATE())";
	
	
	public static final String FIND_ALL_CREDIT_CARDS = "SELECT id, cardHolderName, creditCardNumber, expirationDate, paymentNetworks, cvv, created FROM CreditCards";

	public static final String FIND_ALL_CREDIT_CARDS_BY_CREDITCARDNUMBER = "SELECT id, cardHolderName, creditCardNumber, expirationDate, paymentNetworks, cvv, created FROM CreditCards WHERE creditCardNumber  = ?";
	
	public static final String DELETE_CREDIT_CARD_BY_CREDITCARDNUMBER = "DELETE FROM CreditCards  WHERE creditCardNumber = ?";
	
	public static final String UPDATE_CREDIT_CARD = "UPDATE CreditCards SET creditCardNumber=?, cardHolderName=?, expirationDate=?, paymentNetworks=?, cvv=?, created=GETDATE() WHERE id = ?";
	
	public static final String UPDATE_EXPIRATION_DATE = "UPDATE CreditCards SET expirationDate=? WHERE id=?";
}
