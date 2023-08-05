package org.codigorupestre.model;

import java.sql.Timestamp;

import javax.annotation.PreDestroy;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;


@Entity
@Table(name = "CreditCards")
public class CreditCardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	public String cardHolderName;

	public String creditCardNumber;

	public String expirationDate;

	public PaymentNetworks paymentNetworks;

	public String cvv;

	public Timestamp created;
	
	@PrePersist
	public void setTime() {
		created = new Timestamp(System.currentTimeMillis());
	}

}
