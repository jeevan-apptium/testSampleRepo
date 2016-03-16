package com.apptium.serviceproviders.jpa.beans;

import com.apptium.processengine.core.data.dataobjects.TimerBean;

/**
 * The Class TimerEntity.
 * 
 * @author claus straube
 * @author christopher köster
 * 
 */
public class TimerEntity {

	
	private String id;
	
	
	private long timeToFire;
	
	private String actorRef;
	private String processInstanceId;
	
	public TimerEntity() {
		
	}
	
	public TimerEntity(TimerBean timer) {
		super();
		this.timeToFire = timer.getTimeToFire();
		this.actorRef = timer.getActorRef();
		this.processInstanceId = timer.getProcessInstanceId();
	}
	
	public TimerEntity(long timeToFire, String actorRef,
			String processInstanceId) {
		super();
		this.timeToFire = timeToFire;
		this.actorRef = actorRef;
		this.processInstanceId = processInstanceId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getTimeToFire() {
		return timeToFire;
	}
	public void setTimeToFire(long timeToFire) {
		this.timeToFire = timeToFire;
	}
	public String getActorRef() {
		return actorRef;
	}
	public void setActorRef(String actorRef) {
		this.actorRef = actorRef;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	
	
	
}
