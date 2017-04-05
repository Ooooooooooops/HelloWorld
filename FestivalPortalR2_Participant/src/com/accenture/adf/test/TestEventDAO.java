package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.accenture.adf.businesstier.dao.EventDAO;
import com.accenture.adf.businesstier.entity.Event;
import com.accenture.adf.businesstier.entity.EventCoordinator;
import com.accenture.adf.businesstier.entity.Visitor;
import com.accenture.adf.exceptions.FERSGenericException;
import com.accenture.adf.helper.FERSDataConnection;

/**
 * Junit test class for EventDAO class
 * 
 */
public class TestEventDAO {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;
	private ArrayList<Object[]> showAllEvents;
	private EventDAO dao;

	/**
	 * Sets up database connection before other methods are executed in this
	 * class
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpDatabaseConnection() throws Exception {
		connection = FERSDataConnection.createConnection();
	}

	/**
	 * Closes the database connection after all the methods are executed
	 * 
	 * @throws Exception
	 */
	@AfterClass
	public static void tearDownDatabaseConnection() throws Exception {
		/**
		 * @TODO: Close connection object here  
		 */
			FERSDataConnection.closeConnection();
	}

	/**
	 * Sets up the objects required in other methods
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		showAllEvents = new ArrayList<Object[]>();
		dao = new EventDAO();
	}

	/**
	 * Deallocate the resources after execution of method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		connection = null;
		resultSet = null;
		showAllEvents = null;
		dao = null;
	}

	/**
	 * Positive test case to test the method showAllEvents
	 */
	@Test
	public void testShowAllEvents_Positive() {
		/**
		 * @TODO: Call showAllEvents method and assert it for
		 * size of returned type list
		 */	
		try {
			showAllEvents = dao.showAllEvents();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(8, showAllEvents.size());
	}
	
	/**
	 * Junit test case to test positive case for updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventDeletions for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 more then testSeatsAvailableAfter
		 */		
		int before = 0;
		int after = 0;
		try {
			//Fetching current no of Events from DB
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
			statement.setInt(1, 1002);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				before =  resultSet.getInt("seatsavailable");
			}
			
			//Call to updateEventDeletions : UpdateDeleteEvent from applicationContext.xml: SEATAVAILABLE= SEATAVAILABLE +1
			dao.updateEventDeletions(1002, 10002);		
			
			//No of seats available - Re-fetching  from DB
				statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENTSESSION WHERE EVENTID = ?");
				statement.setInt(1, 1002);
				resultSet = statement.executeQuery();
				if(resultSet.next()){
					after =  resultSet.getInt("seatsavailable");
				}
				//Asserting to check if actual and expected results are same or not.
				assertEquals(before+1, after);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		

	/**
	 * Negative test case for method updateEventDeletions
	 */
	@Test
	public void testUpdateEventDeletions_Negative() {
		/**
		 * @TODO: Call updateEventDeletions for incorrect eventid and it should
		 * throw an exception
		 */
		try {
			dao.updateEventDeletions(1234, 10002);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

	/**
	 * Positive test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Positive() {
		/**
		 * @TODO: Find out seats available for an event by opening a connection
		 * and calling the query SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?
		 * Call the updateEventNominations for eventId
		 * Again find out the seats available for this event
		 * testSeatsAvailableBefore should be 1 less then testSeatsAvailableAfter
		 */	
		int before = 0;
		int after = 0;
		try {
			//Fetching current no of Events from DB
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
			statement.setInt(1, 1002);
			resultSet  = statement.executeQuery();
			if(resultSet.next()){
				before =  resultSet.getInt("seatsavailable");
			}
			//Call to updateEventNominations : updateEvent from applicationContext.xml: SEATAVAILABLE= SEATAVAILABLE -1
			dao.updateEventNominations(1002, 10002);
						
			//no of seats available - Re-fetching  from DB
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
			statement.setInt(1, 1002);
			resultSet = statement.executeQuery();
			if(resultSet.next()){
				after = resultSet.getInt("seatavailable");
			}
			
			//Asserting to check if actual and expected results are same or not.
			assertEquals(before, after+1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Negative test case for method updateEventNominations
	 */
	@Test
	public void testUpdateEventNominations_Negative() {
		/**
		 * @TODO: Call updateEventNominations for incorrect eventid and it should
		 * throw an exception
		 */	
/*		try {
			statement = connection.prepareStatement("SELECT SEATSAVAILABLE FROM EVENT WHERE EVENTID = ?");
			statement.setInt(1, 1234);			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		try {
			dao.updateEventNominations(1234, 10002);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Records not updated properly", e.getMessage());
		}
	}

	/**
	 * Positive test case for method checkEventsofVisitor
	 */
	@Test
	public void testCheckEventsOfVisitor_Positive() {
		/**
		 * @TODO: Create visitor object by setting appropriate values
		 * Call checkEventsofVisitor method by passing this visitor object and
		 * valid eventId
		 * Assert the value of return type 
		 */	
		Visitor visitor = new Visitor();
		visitor.setVisitorId(1002);
		try {
			assertTrue(dao.checkEventsofVisitor(visitor, 1002, 10002));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Junit test case for getEventCoordinator
	 */
	@Test
	public void testGetEventCoordinator(){
		/**
		 * @TODO: Call getEventCoordinator method
		 * Assert the size of return type arraylist
		 */		
		try {
			List <EventCoordinator> eventCoList = dao.getEventCoordinator();
			assertEquals(5, eventCoList.size());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Junit test case for getEvent
	 */
	@Test
	public void testGetEvent(){
		/**
		 * @TODO: Call getEvent method 
		 * Assert the returned Event type with the passed value of event id
		 */		
		try {
			Event event = new Event();
			event = dao.getEvent(1002, 10002);
			assertEquals(1002, event.getEventid());
			assertEquals(10002, event.getEventSession());			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testInsertEvent(){
		/**
		 * @TODO: Create Event object by setting appropriate values
		 * Call insertEvent method by passing this event object
		 * Assert the status of return type of this insertEvent method
		 */		
		Event event = new Event();
		try {
			
			event.setName("OutPlay");
			event.setDescription("This is");
			event.setPlace("Bangalore");
			event.setDuration("2");
			event.setEventtype("Outdoor game");
			event.setEventSession(3);
			event.setEventCoordinatorId(105);
			event.setSeatsavailable("200");
			int status = dao.insertEvent(event);
			assertEquals(1, status);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}	
				
	}
	
	/**
	 * Junit test case for updateEvent
	 */
	@Test
	public void testUpdateEvent(){
		/**
		 * @TODO: Fetch Event object by calling showAllEvents method
		 * Update the values of event object
		 * Call updateEvent method by passing this modified event as object
		 * Assert the status of return type of updateEvent method
		 */		
		try {
			Event e=new Event();
			e.setEventid(1001);
			e.setName("Event I/O");
			e.setDescription("Google's");
			e.setDuration("10Hours");
			e.setEventtype("Tech Meet");
			e.setPlace("US");
			e.setSeatsavailable("300");
			e.setSessionId(10001);		

			int status = dao.updateEvent(e);
			assertEquals(1,status);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Junit test case for deleteEvent
	 */
	@Test
	public void testDeleteEvent(){
		/**
		 * @TODO: Fetch Event object by calling showAllEvents method		 * 
		 * Call deleteEvent method by passing this event id and event session id as object
		 * Assert the status of return type of updateEvent method
		 */		
		
		ArrayList<Object[]> eventList = new ArrayList<Object[]>();
		Event e = new Event();
		
		//eventList = dao.showAllEvents("");
		//dao.deleteEvent(eventId, sessionId);
		//dao.updateEvent(updateEvent);
		
	}

}
