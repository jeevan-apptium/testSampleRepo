package com.apptium.data.eportal.spi.jpa;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.util.SerializationUtils;

import com.apptium.serviceproviders.jpa.beans.DataObjectEntity;
import com.apptium.serviceproviders.jpa.repos.DataObjectRepo;
import com.catify.processengine.core.data.dataobjects.DataObjectSPI;

public class JpaDataObjectSpi extends DataObjectSPI{
	
	private static final Logger LOG = Logger.getLogger(JpaDataObjectSpi.class.getName()); 
	
	private EntityManagerFactory factory = null; 
	


	public JpaDataObjectSpi() {
		this.implementationId = "jpa-data-object"; // set this id in spring
		try{
    		factory = Persistence.createEntityManagerFactory("data-spi"); 
   		
    	}catch(Exception ex){
    		LOG.log(Level.SEVERE,ex.getMessage(),ex);
    		ex.printStackTrace();
    	}
	}
	
	@Override
	public void saveObject(String uniqueProcessId, String objectId,
			String instanceId, Object dataObject) {
		
	    EntityManager entityManager = factory.createEntityManager(); 
	    EntityTransaction transaction = entityManager.getTransaction();

		try{
    		transaction.begin();
    		DataObjectEntity entity = new DataObjectEntity(getObjectKey(
    				uniqueProcessId, objectId, instanceId), uniqueProcessId,
    				objectId, instanceId, dataObject);
    		entityManager.persist(entity);
    		transaction.commit();
    	}catch(Exception ex){
    		if(transaction.isActive()) transaction.rollback(); 
    		LOG.log(Level.SEVERE,ex.getMessage(),ex);
    		ex.printStackTrace();
    	}finally{
    		if(entityManager != null) entityManager.close();
    	}
		
	}

	@Override
	public Object loadObject(String uniqueProcessId, String objectId,
			String instanceId) {
		
		  EntityManager entityManager = factory.createEntityManager(); ; 
		  Object result = null; 
			try{
	    		String rowID = getObjectKey(uniqueProcessId, objectId, instanceId); 
	    		TypedQuery<DataObjectEntity> query =  entityManager.createQuery("from DataObjectEntity where id = :id", DataObjectEntity.class)
	    				.setParameter("id", rowID); 
	    		DataObjectEntity entity = query.getSingleResult(); 
	    		if(entity != null){
	    			result = SerializationUtils.deserialize(entity.getDataObject()); 
	    		}
	    		
	    	}catch(Exception ex){
	    		LOG.log(Level.SEVERE,ex.getMessage(),ex);
	    		ex.printStackTrace();
	    	}finally{
	    		if(entityManager != null) entityManager.close();
	    	}
		return result;
	}

	@Override
	public void deleteObject(String uniqueProcessId, String objectId,
			String instanceId) {
		
		  EntityManager entityManager = factory.createEntityManager(); 
		  EntityTransaction transaction = entityManager.getTransaction();

			try{
				transaction.begin();
	    		String rowID = getObjectKey(uniqueProcessId, objectId, instanceId); 
	    		TypedQuery<DataObjectEntity> query =  entityManager.createQuery("from DataObjectEntity where id = :id", DataObjectEntity.class)
	    				.setParameter("id", rowID); 
	    		DataObjectEntity entity = query.getSingleResult(); 
	    		entityManager.remove(entity);
	    		transaction.commit();
	    	}catch(Exception ex){
	    		if(transaction.isActive()) transaction.rollback(); 
	    		LOG.log(Level.SEVERE,ex.getMessage(),ex);
	    		ex.printStackTrace();
	    	}finally{
	    		if(entityManager != null) entityManager.close();
	    	}
		
	}

}
