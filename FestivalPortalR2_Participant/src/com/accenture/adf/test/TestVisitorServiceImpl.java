package com.accenture.adf.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.businesstier.service.VisitorServiceImpl;

/**
 * Junit test class for VisitorServiceImpl
 *
 */
public class TestVisitorServiceImpl {

	private List<Object[]> visitorList;	
	private Visitor visitor;
	private VisitorServiceImpl visitorServiceImpl;

	/**
	 * Set up the initial methods 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {		
		visitorServiceImpl = new VisitorServiceImpl();
		visitor = new Visitor();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */

		visitor=null;
		visitorList=null;
		visitorServiceImpl=null;
		}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testCreateVisitor() {
		/**
		 * @TODO: Set the appropriate values for visitor object and
		 * call the method createVisitor by passing an argument of this visitor 
		 * object and then asserting the returned type of this method
		 */		
	
		visitor.setUserName("bsmith");
		visitor.setPassword("amlan");
		visitor.setFirstName("Bob");
		visitor.setLastName("Smith");
		visitor.setEmail("bsmith@email.com");
		visitor.setPhoneNumber("8123985383");
		visitor.setAddress("null");
	}

	/**
	 * Test case for method createVisitor
	 */
	@Test
	public void testSearchVisitor() {
		/**
		 * @TODO: Call searchVisitor method by passing the appropriate arguments 
		 * and then asserting the returned type visitor username with the argument passed
		 */		
	
	
		visitor=visitorServiceImpl.searchVisitor("bsmith", "password");
		assertEquals("bsmith",visitor.getUserName());
	
		}

	/**
	 * Test case for method RegisterVisitor
	 */
	@Test
	public void testRegisterVisitor() {
		/**
		 * @TODO: Call RegisterVisitor method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of RegisterVisitor method 
		 */		
	
		visitor=visitorServiceImpl.searchVisitor("bsmith", "password");
		visitorServiceImpl.RegisterVisitor(visitor, 1001, 10001);
		//System.out.println(visitor.getFirstName());
		visitorList=visitorServiceImpl.showRegisteredEvents(visitor);
		//System.out.println(visitorList.size());
		boolean flag=false;
		for (Object[] e : visitorList) {
			int i=(Integer)e[0];
			if(i==1001){
				flag=true; 
				break;
			}
		
		}
			
	}
	/**
	 * Test case for method showRegisteredEvents
	 */
	@Test
	public void testShowRegisteredEvents() {
		/**
		 * @TODO: Call showRegisteredEvents method by passing visitor object which 
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of showRegisteredEvents method 
		 */		
	
		visitor=visitorServiceImpl.searchVisitor("bsmith", "password");
		visitorServiceImpl.showRegisteredEvents(visitor);
		assertTrue("Registered events", true);

		
	}

	/**
	 * Test case for method updateVisitorDetails
	 */
	@Test
	public void testUpdateVisitorDetails() {
		/**
		 * @TODO: Call updateVisitorDetails method by passing the visitor object which
		 * can be retrieved using searchVisitor method and then asserting the returned
		 * type of updateVisitorDetails
		 */		
	
	
		int status=0;
		visitor=visitorServiceImpl.searchVisitor("bsmith", "password");
		status=visitorServiceImpl.updateVisitorDetails(visitor);
		boolean b=false;
		if(status>0){
			b=true;
		}
		assertTrue(b);
		}

	/**
	 * Test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent() {
		/**
		 * @TODO: Call unregisterEvent method by passing the visitor object which can be
		 * retrieved using searchVisitor method and then asserting the returned type 
		 * of unregisterEvent
		 */		
		visitor=visitorServiceImpl.searchVisitor("bsmith", "password");
		visitorServiceImpl.unregisterEvent(visitor, 1001, 0);
		
		visitorList=visitorServiceImpl.showRegisteredEvents(visitor);
		boolean flag=true;
		for (Object[] e : visitorList) {
			if(e.length==1001){
				flag=false; 
				break;
			}
		}
		assertTrue(flag);

	}

}