package com.apptium.data.eportal.spi.jpa;

import java.util.logging.Logger;

import com.apptium.processengine.core.data.dataobjects.DataObjectSPI;

public class JpaDataObjectSpi extends DataObjectSPI{
	
	private static final Logger LOG = Logger.getLogger(JpaDataObjectSpi.class.getName()); 
	
	


	public JpaDataObjectSpi() {
		this.implementationId = "jpa-data-object"; // set this id in spring
	
	}
	
	@Override
	public void saveObject(String uniqueProcessId, String objectId,
			String instanceId, Object dataObject) {
		LOG.info("called JpaDataObjectSpi->SaveObject");
	}

	@Override
	public Object loadObject(String uniqueProcessId, String objectId,
			String instanceId) {
		
		Object result = null; 
		LOG.info("called JpaDataObjectSpi->loadObject");
		return result;
	}

	@Override
	public void deleteObject(String uniqueProcessId, String objectId,
			String instanceId) {
		LOG.info("called JpaDataObjectSpi->deleteObject");
	}

}
