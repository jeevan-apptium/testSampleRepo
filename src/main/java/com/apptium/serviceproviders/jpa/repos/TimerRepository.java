package com.apptium.serviceproviders.jpa.repos;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;

import com.apptium.serviceproviders.jpa.beans.TimerEntity;

/**
 * The Interface TimerRepository.
 * 
 * @author claus straube
 * @author christopher köster
 * 
 */
public interface TimerRepository extends CrudRepository<TimerEntity, Long> {

	TimerEntity findByActorRefAndProcessInstanceId (String actorRef, String processInstanceId);
	
	@Query("select t from TimerEntity t where t.actorRef = ?1 and t.timeToFire < ?2")
	List<TimerEntity> findByActorRefAndTimeToFireLessThanTimeNow (String actorRef, long timeNow);
	
}
