package org.codigorupestre.repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.codigorupestre.model.CreditCard;
import org.codigorupestre.model.CreditCardEntity;
import org.jboss.logging.Logger;


@Transactional
@ApplicationScoped
public class CreditCardJPARepository {
	
	@Inject
	EntityManager em;
	
	@Inject
	Logger log;
	
	
	public CreditCardEntity create(CreditCardEntity creditCard) {
		log.info("Guardando en JPA: " +  creditCard); 
		em.persist(creditCard);
		return creditCard;
	}
	
	
	public CreditCardEntity findById(Integer id) {
		log.info("Recuperando por id JPA " + id);
		return em.find(CreditCardEntity.class, id);
	}

}
