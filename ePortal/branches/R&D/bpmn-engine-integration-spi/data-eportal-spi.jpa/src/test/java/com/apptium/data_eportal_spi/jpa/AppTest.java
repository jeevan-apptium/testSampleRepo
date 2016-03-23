package com.apptium.data_eportal_spi.jpa;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.apptium.data.eportal.spi.jpa.JpaTimerSpi;
import com.apptium.processengine.core.data.dataobjects.TimerBean;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	
	private static final DateTimeFormatter 	dateFormatter = ISODateTimeFormat.dateTimeNoMillis();
	
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
   
	public void testJPADataObjectSPI() {

//		JpaDataObjectSpi sp = new JpaDataObjectSpi(); 
//		
//		sp.saveObject("uniqueProcessId", "objectId", "instanceId", "dataObject");
//
//		assertEquals("dataObject", sp.loadObject("uniqueProcessId", "objectId", "instanceId"));
//		
//		sp.deleteObject("uniqueProcessId", "objectId", "instanceId");
		assertTrue( true );
	}
	
	public void testJPATimerSPI(){
		
		JpaTimerSpi  sp = new JpaTimerSpi(); 
		DateTime dt = new DateTime(); 
		String isoDate = dateFormatter.print(dt); 
		TimerBean timer = new TimerBean(); 
		timer.setActorRef("actorRef");
		timer.setProcessInstanceId("processInstance");
		timer.setTimeToFire(calculateTimeToFireForDate(isoDate));
		sp.saveTimer(timer);
		List<TimerBean> myTimer = sp.loadDueTimers(); 
		assertEquals(myTimer.size(), 1); 
		sp.deleteTimer("actorRef", "processInstance");
		
	}
	
	
	/**
	 * Calculates the time stamp in millis for a 
	 * given ISO 8601 date (e.g. 2013-04-09T16:34:08Z). 
	 * The date must have a time zone.
	 * 
	 * @param isoDate ISO 8601 date as {@link String}
	 * @return time stamp in millis
	 */
	public static long calculateTimeToFireForDate(String isoDate) {
		DateTime time = dateFormatter.parseDateTime(isoDate);
		return time.getMillis();
	}
}
