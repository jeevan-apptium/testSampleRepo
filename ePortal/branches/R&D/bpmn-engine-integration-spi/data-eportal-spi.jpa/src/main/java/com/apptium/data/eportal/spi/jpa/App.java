package com.apptium.data.eportal.spi.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final Logger LOG = Logger.getLogger("JPA"); 
	
    public static void main( String[] args )
    {
    	EntityManagerFactory factory = null; 
    	EntityManager entityManager = null; 
    	
    	try{
    		factory = Persistence.createEntityManagerFactory("data-spi"); 
    		entityManager = factory.createEntityManager(); 
    		
    	}catch(Exception ex){
    		LOG.log(Level.SEVERE,ex.getMessage(),ex);
    		ex.printStackTrace();
    	}finally{
    		if(entityManager != null) entityManager.close();
    		if(factory != null) factory.close();
    	}
    }
    
}
