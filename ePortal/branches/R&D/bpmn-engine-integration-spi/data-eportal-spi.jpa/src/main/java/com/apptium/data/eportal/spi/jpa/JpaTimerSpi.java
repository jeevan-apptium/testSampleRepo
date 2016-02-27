package com.apptium.data.eportal.spi.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apptium.serviceproviders.jpa.beans.TimerEntity;
import com.apptium.serviceproviders.jpa.repos.TimerRepository;
import com.catify.processengine.core.data.dataobjects.TimerBean;
import com.catify.processengine.core.data.dataobjects.TimerSPI;

/**
 * JPA implementation of the {@link TimerSPI}.
 * 
 * @author claus straube
 * @author christopher köster
 * 
 */
public class JpaTimerSpi extends TimerSPI {

	private TimerRepository timerRepository;
	
	public JpaTimerSpi() {
		this.implementationId = "jpa-timer"; // set this id in spring
		//this.timerRepository = (TimerRepository) AppContextFactory.load(TimerRepository.class);
	}
	
	@Override
	public void saveTimer(TimerBean timer) {
		timerRepository.save(new TimerEntity(timer));
	}

	@Override
	public List<TimerBean> loadDueTimers(String actorRef) {
		List<TimerEntity> timerEntities = timerRepository.findByActorRefAndTimeToFireLessThanTimeNow(actorRef, new Date().getTime());
		timerRepository.delete(timerEntities);
		return createTimerBeans(timerEntities);
	}

	@Override
	public void deleteTimer(String actorRef, String processInstanceId) {
		TimerEntity timerEntity = timerRepository.findByActorRefAndProcessInstanceId(actorRef, processInstanceId);
		// only try to delete timer entity existing timers
		// this method needs to safely call the delete method for non existing nodes,
		// because a 'cycle timer start event' will try to delete a trigger that has not been saved to db
		if (timerEntity != null) {
			timerRepository.delete(timerEntity);
		}
	}
	
	/**
	 * Creates timer beans from a list of timer entities.
	 *
	 * @param timerEntities the timer entities
	 * @return the list of timer beans
	 */
	private List<TimerBean> createTimerBeans(List<TimerEntity> timerEntities) {
		List<TimerBean> timerBeans = new ArrayList<TimerBean>();	
		for (TimerEntity timerEntity : timerEntities) {
			timerBeans.add(new TimerBean(timerEntity.getTimeToFire(), timerEntity.getActorRef(), timerEntity.getProcessInstanceId()));
		}
		return timerBeans;
	}

	public TimerRepository getRepository() {
		return timerRepository;
	}

}
