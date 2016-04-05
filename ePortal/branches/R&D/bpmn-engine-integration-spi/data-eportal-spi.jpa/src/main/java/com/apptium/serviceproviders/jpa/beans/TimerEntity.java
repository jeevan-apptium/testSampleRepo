package com.apptium.serviceproviders.jpa.beans;

import com.apptium.processengine.core.data.dataobjects.TimerBean;
import com.apptium.processengine.core.nodes.eventdefinition.TimerTypes;

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
	private Boolean repatBounded; 
	private String modelName; 
	private String timerType;
	private String isoDate;
	private Long scheduledInstance; 
	private String timerSource; 

	public TimerEntity() {
		
	}
	
	public TimerEntity(TimerBean timer) {
		super();
		this.timeToFire = timer.getTimeToFire();
		this.actorRef = timer.getActorRef();
		this.processInstanceId = timer.getProcessInstanceId();
		this.setRepatBounded(timer.getRepeatBounded());  
		this.setModelName(timer.getModelName()); 
		this.setScheduledInstance(timer.getScheduledInstance());
		this.setTimerSource(timer.getTimerSource());
		
	}
	
	public TimerEntity(long timeToFire, String actorRef,
			String processInstanceId,Boolean repeatBounded, String modelName,TimerTypes String,String isoDate,Long scheduledInstance) {
		super();
		this.timeToFire = timeToFire;
		this.actorRef = actorRef;
		this.processInstanceId = processInstanceId;
		this.setRepatBounded(repeatBounded);  
		this.setModelName(modelName); 
		this.setTimerType(timerType);
		this.setIsoDate(isoDate);
		this.setScheduledInstance(scheduledInstance);
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

	public Boolean getRepatBounded() {
		return repatBounded;
	}

	public void setRepatBounded(Boolean repatBounded) {
		this.repatBounded = repatBounded;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getTimerType() {
		return timerType;
	}

	public void setTimerType(String timerType) {
		this.timerType = timerType;
	}

	public String getIsoDate() {
		return isoDate;
	}

	public void setIsoDate(String isoDate) {
		this.isoDate = isoDate;
	}

	public Long getScheduledInstance() {
		return scheduledInstance;
	}

	public void setScheduledInstance(Long scheduledInstance) {
		this.scheduledInstance = scheduledInstance;
	}

	public String getTimerSource() {
		return timerSource;
	}

	public void setTimerSource(String timerSource) {
		this.timerSource = timerSource;
	}
	
	
	
}
