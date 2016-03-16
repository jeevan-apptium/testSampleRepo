package com.apptium.data.eportal.spi.jpa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.bcel.generic.F2D;
import org.apache.commons.lang.math.RandomUtils;

import scala.Function1;

import com.apptium.processengine.core.data.dataobjects.TimerBean;
import com.apptium.processengine.core.data.dataobjects.TimerSPI;
import com.apptium.serviceproviders.jpa.beans.TimerEntity;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * JPA implementation of the {@link TimerSPI}.
 * 
 * @author claus straube
 * @author christopher köster
 * 
 */
public class JpaTimerSpi extends TimerSPI {

	private static final Logger LOG = Logger.getLogger(JpaDataObjectSpi.class.getName()); 
	private static final String SKEDFILE = "bpmnSchedule.jsonTimer"; 
	
	private Map <String,TimerEntity> bpmnSked = null; 

	
	public JpaTimerSpi() {
		this.implementationId = "jpa-timer"; // set this id in spring
		if(bpmnSked == null) {
			bpmnSked = new HashMap<String,TimerEntity>(); 
			readPersistanceSked(); 
		}
		
	}
	protected void peristanceSked() throws IOException{
		Gson gson = new Gson(); 
		FileWriter f1 = new FileWriter(SKEDFILE,false); 
		String sked = gson.toJson(bpmnSked);
		f1.write(sked);
		f1.flush();
		f1.close();
	}
	
	
	protected void readPersistanceSked(){
		Gson gson = new Gson(); 
		File fi = new File(SKEDFILE); 
		if(!fi.exists()) return; 
		try{
			JsonParser parser = new JsonParser(); 
	    Object obj = parser.parse(new FileReader(SKEDFILE)); 
		JsonObject jsonObject = (JsonObject)obj; 
		for(Entry<String, JsonElement> test : jsonObject.entrySet()){
			String key = test.getKey(); 
			TimerEntity myRec = gson.fromJson(test.getValue(), TimerEntity.class); 
			bpmnSked.put(key, myRec); 
		}
		
		}catch(Exception ex){
			ex.printStackTrace();
			LOG.info(ex.getMessage());
		}
	}
	
	@Override
	public void saveTimer(TimerBean timer) {
		 try{
		
			 TimerEntity myEntity = new TimerEntity(timer); 
			 myEntity.setId(UUID.randomUUID().toString());
			 bpmnSked.put(myEntity.getId(), myEntity); 
			 peristanceSked(); 
			 
		 }catch(FileNotFoundException e){
				e.printStackTrace();
				LOG.info(e.getMessage());
		 }catch(Exception e){
			 e.printStackTrace();
			 LOG.info(e.getMessage());
		 }
	}


	
	@Override
	public List<TimerBean> loadDueTimers(String actorRef) {
		List<TimerBean> result = new ArrayList<TimerBean>(); 
		List<TimerEntity> timerEntities = new ArrayList<TimerEntity>(); 
		List<String> expiredSked = new ArrayList<String>(); 
		long currentTimeStamp = new Date().getTime(); 
		
		try{
			  for(Entry<String, TimerEntity> aRec : bpmnSked.entrySet()){			  
				  if(aRec.getValue().getActorRef().equalsIgnoreCase(actorRef)){
					  if(aRec.getValue().getTimeToFire() <= currentTimeStamp){
						  timerEntities.add(aRec.getValue()); 	
						  expiredSked.add(aRec.getKey()); 
							LOG.info(aRec.getValue().getProcessInstanceId()+" >>  "+ aRec.getValue().getTimeToFire()+" <= "+ currentTimeStamp);
					  }  
				  }
				
			  }	  
			
			result = this.createTimerBeans(timerEntities); 
			for(String target : expiredSked)
				if(bpmnSked.containsKey(target))bpmnSked.remove(target); 
			peristanceSked(); 
		}catch(FileNotFoundException e){
			e.printStackTrace();
			LOG.info(e.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			LOG.info(e.getMessage());
		}
		return result;
	}


	
	@Override
	public void deleteTimer(String actorRef, String processInstanceId){
		try{
    		
    		File file = new File(String.format("%s.jsonTimer",processInstanceId)); 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
    	   
    	}catch(Exception e){
    		
    		e.printStackTrace();
    		
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



}
