package org.codigorupestre.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "CreditCards")
public class CreditCardPanacheEntity extends PanacheEntityBase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	public String cardHolderName;

	public String creditCardNumber;

	public String expirationDate;

	public String paymentNetworks;

	public String cvv;

	public Timestamp created;
	
	@PrePersist
	public void setTime() {
		created = new Timestamp(System.currentTimeMillis());
	}
}
