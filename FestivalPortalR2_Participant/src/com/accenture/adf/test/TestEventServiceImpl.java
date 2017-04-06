package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.EventCoordinator;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.businesstier.service.EventServiceImpl;
import com.accenture.adf.exceptions.FERSGenericException;
import com.accenture.adf.helper.FERSDataConnection;

/**
 * Junit test case to test class EventServiceImpl
 * 
 */
public class TestEventServiceImpl {

	private List<Object[]> eventList;
	private Visitor visitor;
	private EventServiceImpl eventServiceImpl;
	private static Connection connection = null;
	private static PreparedStatement statement = null;
	ResultSet resultset;
	 int afterUpdate ;
	 Event e=new Event();


	/**
	 * Set up the objects required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		eventServiceImpl = new EventServiceImpl();
		visitor = new Visitor();
		EventDAO dao = new EventDAO();
		ArrayList<Object[]> showEvents = new ArrayList<Object[]>();	
		TestEventServiceImpl test1=new TestEventServiceImpl();
	}

	/**
	 * Deallocates the objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
	
		eventServiceImpl =null;
		visitor=null;
		eventList=null;
	
	
	
	}

	/**
	 * Test case to test the method getAllEvents
	 */
	@Test
	public void testGetAllEvents() {
		/**
		 * @TODO: Call getAllEvents method and assert it for the size of returned array
		 */	
		boolean status=false;
		List<Object[]> 	showEvents=eventServiceImpl.getAllEvents("rose parade");
		if(showEvents.size()> 0)
		{
			status=true;
		}
		else status=false;
		
		assert(status);
		
		
		
		
	}

	/**
	 * Test case to test the method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsofVisitor() {
		/**
		 * @TODO: Call checkEventsofVisitor and assert the returned type of this method
		 * for appropriate return type
		 */	
		boolean status=eventServiceImpl.checkEventsofVisitor(visitor,1001,1001);
		
		assert(status);
		
	}

	/**
	 * Test case to test the method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions() {
		/**
		 * @TODO: Call updateEventDeletions and assert the return type of this method
		 */	
		
		int afterUpdate=0;
		int beforeupdate=0;
		
		try {
			
		       connection = FERSDataConnection.createConnection();

				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ? ");

				statement.setInt(1, 1001);

				resultset = statement.executeQuery();

				if(resultset.next()){
				beforeupdate = resultset.getInt(1);
				}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		eventServiceImpl.updateEventDeletions(1001,10001);
		
		try {
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ? ");

			statement.setInt(1, 1001);
		
			resultset = statement.executeQuery();

			if(resultset.next()){
			afterUpdate = resultset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		assertEquals(beforeupdate+1,afterUpdate);
		
		
		}
		
		
		


	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator() {
		/**
		 * @TODO: Call getAllEventCoordinators and assert the size of return type of this method
		 */		
		
		boolean status= false;
		 List<EventCoordinator> e =eventServiceImpl.getAllEventCoordinators();
		
		 if(e.size()>0)
		 {
			status=true; 
		 }
		assertTrue(status);
			 
		
		
		
		
	}

	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent() {
		/**
		 * @TODO: Call getEvent and assert the event id of this event with 
		 * passed event id 
		 */		
		Event event= new Event();
		event=eventServiceImpl.getEvent(1001,10001);
		System.out.println(event.getEventid());
        assertEquals(1001,event.getEventid());		
		
		
		
		
		
		
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testInsertEvent() {
		/**
		 * @TODO: Call insertEvent
		 * Create event object by setting appropriate values
		 * Assert the status of insertEvent method
		 */		
		Event e1=new Event();
		e1.setEventid(1110);
		e1.setName("ganapati");
		e1.setPlace("bangalore");
		e1.setSessionId(10001);
		e1.setSeatsavailable("1002");
		e1.setDescription("sportman");
		e1.setDuration("hi");
		e1.setEventCoordinatorId(101);
		e1.setEventtype("sports");
		int status=eventServiceImpl.insertEvent(e1);
		boolean temp=false;
		if(status>0)
		{
			temp =true;
		}
		System.out.println(temp);
		assertTrue(temp);
		
		
		
		
		
		
		
		
	}

	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent() {
		/**
		 * @TODO: Fetch Event object by calling getAllEvents method 
		 * Update event object by setting appropriate values
		 * Call updateEvent method
		 * Assert the status of updateEvent method
		 */	
		
		
		eventList=eventServiceImpl.getAllEvents();
		
		for (Object[] objects: eventList) {
			int eid=(Integer)objects[0];
			e.setEventid(eid);
			String s=(String)objects[1];
			e.setName(s);
			String k=(String)objects[2];
			e.setDescription(k);
			String m=(String)objects[3];
			e.setPlace(m);
			String n=(String)objects[3];
			e.setEventtype(n);
			String o=(String)objects[4];
			e.setSeatsavailable(o);
			String t=(String)objects[5];
			e.setDuration(t);
			int p=(Integer)objects[6];
			e.setSessionId(p);
			int q=(Integer)objects[7];
			e.setSessionId(q);
		 break;
		}
		
		int m=eventServiceImpl.updateEvent(e);
		 boolean status=false;
	 if(m>0)
	 {
		 status=true;
	 }
	 else
		 status=false;
		
		assert(status);
	}

	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent() {
		/**
		 * @TODO: Fetch Event object by calling getAllEvents method 
		 * Update event object by setting appropriate values
		 * Call deleteEvent method
		 * Assert the status of deleteEvent method
		 */	
		
		eventList=eventServiceImpl.getAllEvents();
		for (Object[] objects: eventList) {
			int eid=(Integer)objects[0];
			e.setEventid(eid);
			String s=(String)objects[1];
			e.setName(s);
			String k=(String)objects[2];
			e.setDescription(k);
			String m=(String)objects[3];
			e.setPlace(m);
			String n=(String)objects[3];
			e.setEventtype(n);
			String o=(String)objects[4];
			e.setSeatsavailable(o);
			String t=(String)objects[5];
			e.setDuration(t);
			int p=(Integer)objects[6];
			e.setSessionId(p);
			int q=(Integer)objects[7];
			e.setSessionId(q);
		 break;
		}
		
		
		int m=eventServiceImpl.deleteEvent(e.getEventid(),e.getSessionId());
		 boolean status=false;
	 if(m>0)
	 {
		 status=true;
	 }
	 else
		 status=false;
		
		assert(status);
	
		
		
		
		
		
	}

}
