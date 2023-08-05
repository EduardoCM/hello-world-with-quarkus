package org.codigorupestre.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.codigorupestre.model.CreditCard;
import org.codigorupestre.model.PaymentNetworks;
import org.jboss.logging.Logger;

@ApplicationScoped
public class CreditCardJDBCRepository {
	
	@Inject
	DataSource dataSource;
	
	@Inject
	Logger log;
	
	
	public CreditCard create(CreditCard creditCard) {
		log.info("Start Insert new credit card: " + creditCard);
		
		try {
			Connection conn = dataSource.getConnection();
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.INSERT_CREDIT_CARD)){
				
				ps.setString(1, creditCard.cardHolderName);
				ps.setString(2, creditCard.creditCardNumber);
				ps.setString(3, creditCard.expirationDate);
				ps.setString(4, creditCard.paymentNetworks.toString());
				ps.setString(5, creditCard.cvv);
				
				ps.executeUpdate();
				log.info("Finish Insert new credit card: " + creditCard);
			}
		
		conn.close();
		
		} catch (SQLException e) {
			log.error("Error al guardar tarjeta de credito");
			log.error(e.getMessage(), e);
		}
		
		return creditCard;
	}
	
	
	public List<CreditCard> getAll() {
		List<CreditCard> creditCardList = new ArrayList<>();
		
		try {
			Connection conn = dataSource.getConnection();
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.FIND_ALL_CREDIT_CARDS)){
			
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					CreditCard creditCard = new CreditCard();
					
					creditCard.id = rs.getInt("id");
					creditCard.cardHolderName = rs.getString("cardHolderName");
					creditCard.creditCardNumber = rs.getString("creditCardNumber");
					creditCard.expirationDate = rs.getString("expirationDate");
					String value = rs.getString("paymentNetworks");
					log.info("ValuePayment: " + value);
					creditCard.paymentNetworks = PaymentNetworks.valueOf(rs.getString("paymentNetworks"));
					creditCard.cvv = rs.getString("cvv");
					creditCard.created = rs.getTimestamp("created");
					creditCardList.add(creditCard);
				}	
			}
			
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		
		
		return creditCardList;
	}
	
	
	
	public CreditCard findByCreditCardNumber(String creditCardNumber){
		CreditCard cCard = null;
		
		try {
			Connection conn = dataSource.getConnection();
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.FIND_ALL_CREDIT_CARDS_BY_CREDITCARDNUMBER)){
			
				ps.setString(1, creditCardNumber);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) {
					CreditCard creditCard = new CreditCard();
					
					creditCard.id = rs.getInt("id");
					creditCard.cardHolderName = rs.getString("cardHolderName");
					creditCard.creditCardNumber = rs.getString("creditCardNumber");
					creditCard.expirationDate = rs.getString("expirationDate");
					String value = rs.getString("paymentNetworks");
					log.info("ValuePayment: " + value);
					creditCard.paymentNetworks = PaymentNetworks.valueOf(rs.getString("paymentNetworks"));
					creditCard.cvv = rs.getString("cvv");
					creditCard.created = rs.getTimestamp("created");
					
					cCard = creditCard;
					
				}	
			}
			
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		
		
		return cCard;
		
	}
	
	
	public int delete(String creditCardNumber) {
		int registrosEliminados = 0;
		
		try {
			Connection conn = dataSource.getConnection();
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.DELETE_CREDIT_CARD_BY_CREDITCARDNUMBER)){
				ps.setString(1, creditCardNumber);
				
				registrosEliminados = ps.executeUpdate();
			}
			
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		return registrosEliminados;
	}
	
	
	
	
	public int updateCreditCard(int id, CreditCard creditCard) {
		int cantidadRegistrosActualizados = 0;
		try {
			Connection conn = dataSource.getConnection();
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_CREDIT_CARD)){
				
				ps.setString(1, creditCard.cardHolderName);
				ps.setString(2, creditCard.creditCardNumber);
				ps.setString(3, creditCard.expirationDate);
				ps.setString(4, creditCard.paymentNetworks.toString());
				ps.setString(5, creditCard.cvv);
				ps.setInt(6, id);
				
				cantidadRegistrosActualizados = ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		return cantidadRegistrosActualizados;
	}
	
	
	
	
	
	public int updateExpirationDate(int id, String expirationDate) {
		int cantidadRegistrosActualizados = 0;
		try {
			Connection conn = dataSource.getConnection();
			
			try(PreparedStatement ps = conn.prepareStatement(SQLConstants.UPDATE_EXPIRATION_DATE)){
				
				ps.setString(1, expirationDate);
				ps.setInt(2, id);
				
				cantidadRegistrosActualizados = ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		
		return cantidadRegistrosActualizados;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
