package com.apptium.serviceproviders.jpa.beans;

import org.springframework.util.SerializationUtils;


public class DataObjectEntity {
	
	
	private String id;
	private String uniqueProcessId;
	private String objectId;
	private String instanceId;

	private byte[] dataObject;
	
	public DataObjectEntity() {
		super();
	}
	
	public DataObjectEntity(String id, String uniqueProcessId, String objectId,
			String instanceId, Object dataObject) {
		super();
		this.id = id;
		this.uniqueProcessId = uniqueProcessId;
		this.objectId = objectId;
		this.instanceId = instanceId;
		this.dataObject = SerializationUtils.serialize(dataObject);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUniqueProcessId() {
		return uniqueProcessId;
	}
	public void setUniqueProcessId(String uniqueProcessId) {
		this.uniqueProcessId = uniqueProcessId;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	
	
	public byte[] getDataObject() {
		return dataObject;
	}
	public void setDataObject(byte[] dataObject) {
		this.dataObject = dataObject;
	}
	
}
