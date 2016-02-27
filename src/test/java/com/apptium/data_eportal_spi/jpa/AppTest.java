package com.apptium.data_eportal_spi.jpa;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Before;

import com.apptium.data.eportal.spi.jpa.JpaDataObjectSpi;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
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

		JpaDataObjectSpi sp = new JpaDataObjectSpi(); 
		
		sp.saveObject("uniqueProcessId", "objectId", "instanceId", "dataObject");

		assertEquals("dataObject", sp.loadObject("uniqueProcessId", "objectId", "instanceId"));
		
		sp.deleteObject("uniqueProcessId", "objectId", "instanceId");
	}
}
