package org.codigorupestre;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

@Path("/hello")
public class GreetingResource {
	
	@Inject
	Logger log;

    @GET
    @Path("{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@PathParam("name") String name) {
    	
    	log.info("Hello World with Quarkus: " + name);
        
    	
    	return "Hello World with Quarkus: " + name + " Fecha: " + LocalDateTime.now();
    }
}