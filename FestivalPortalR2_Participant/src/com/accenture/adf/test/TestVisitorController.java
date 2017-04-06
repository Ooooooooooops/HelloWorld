package com.accenture.adf.test;

import static org.junit.Assert.assertEquals;
import javax.servlet.http.HttpServletResponse;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.web.servlet.ModelAndView;

import com.accenture.adf.businesstier.controller.VisitorController;
import com.accenture.adf.businesstier.dao.VisitorDAO;
import com.accenture.adf.businesstier.entity.Visitor;

/**
 * Junit test case to test the class VisitorController
 * 
 */
public class TestVisitorController {

	private MockHttpServletRequest request;
	private MockHttpServletResponse response;
	private MockHttpSession session;
	private ModelAndView modelAndView;
	private VisitorController controller;
	private VisitorDAO visitorDao;

	/**
	 * Set up initial methods required before execution of every method
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		modelAndView = new ModelAndView();
		controller = new VisitorController();
		session = new MockHttpSession();
		response = new MockHttpServletResponse();
		visitorDao = new VisitorDAO();
	}

	/**
	 * Deallocate objects after execution of every method
	 * 
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		/**
		 * @TODO: Release all the objects here by assigning them null  
		 */
		
		modelAndView = null;
		controller = null;
		session = null;
		response = null;
		visitorDao = null;
		
	}

	/**
	 * Positive test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor_Positive() {
		try {
			request = new MockHttpServletRequest("GET", "/newVistor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			request.setParameter("FIRSTNAME", "TestVFname");
			request.setParameter("LASTNAME", "lname");
			request.setParameter("EMAIL", "mail");
			request.setParameter("PHONENO", "11111");
			request.setParameter("ADDRESS", "testAddress");
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/registration.jsp", modelAndView.getViewName());
	}

	/**
	 * Negative test case to test the method newVisitor
	 */
	@Test
	public void testNewVisitor_Negative() {
		/**
		 * @TODO: Call newVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			request = null;
			modelAndView = controller.newVisitor(request, response);
		} catch (Exception exception) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", exception.getMessage());
		}
		
	}

	/**
	 * Positive test case to test the method searchVisitor
	 */
	@Test
	public void testSearchVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call searchVisitor method and assert model view name 
		 */	
		
		try {
			request = new MockHttpServletRequest("GET", "/searchVisitor.htm");

			request.setParameter("USERNAME", "ylee");
			request.setParameter("PASSWORD", "password");
			
			modelAndView = controller.searchVisitor(request, response);

		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Negative test case of invalid user for method searchVisitor
	 */
	@Test
	public void testSearchVisitor_Negative_InvalidUser() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set request parameters for USERNAME and PASSWORD for invalid values
		 * Call searchVisitor method and assert model view name 
		 */	
		
		try {
			request = new MockHttpServletRequest("GET", "/searchVisitor.htm");

			request.setParameter("USERNAME", "ylee1");
			request.setParameter("PASSWORD", "password2");
			
			modelAndView = controller.searchVisitor(request, response);

		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/index.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Negative test case for method searchVisitor
	 */
	@Test
	public void testSearchVisitor_Negative() {
		/**
		 * @TODO: Call searchVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			request = null;			
			modelAndView = controller.searchVisitor(request, response);

		} catch (Exception exception) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", exception.getMessage());
		}
		
	}

	/**
	 * Positive test case for method registerVisitor
	 */
	@Test
	public void testRegisterVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for USERNAME and PASSWORD for valid values
		 * Call registerVisitor method and assert model view name 
		 */		
		

		try {
			request=new MockHttpServletRequest("GET", "/eventreg.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			request.setParameter("USERNAME", visitor.getUserName());
			request.setParameter("PASSWORD", visitor.getPassword());
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			modelAndView=controller.registerVisitor(request, response);

		} catch (Exception exception) {
			fail("Exception");
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
		
	}	

	/**
	 * Negative test case for method registerVisitor
	 */
	@Test
	public void testRegisterVisitor_Negative() {
		/**
		 * @TODO: Call registerVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		

		try {
			request=null;
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			modelAndView=controller.registerVisitor(request, response);

		} catch (Exception exception) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", exception.getMessage());
		}
		
	}

	/**
	 * Positive test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all valid user values
		 * Call updateVisitor method and assert model view name 
		 */	
		
		request=new MockHttpServletRequest("GET","/updateVisitor.htm");
		try {
			Visitor visitor=visitorDao.searchUser("bsmith","password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			request.setParameter("username", visitor.getUserName());
			request.setParameter("password", visitor.getPassword());
			request.setParameter("firstname", visitor.getFirstName());
			request.setParameter("lastname", visitor.getLastName());
			request.setParameter("email", visitor.getEmail());
			request.setParameter("phoneno", "98989898");
			request.setParameter("address", visitor.getAddress());
			modelAndView = controller.updateVisitor(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/updatevisitor.jsp", modelAndView.getViewName());

		
	}

	/**
	 * Negative test case for method updateVisitor
	 */
	@Test
	public void testUpdateVisitor_Negative() {
		/**
		 * @TODO: Call updateVisitor method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		request=new MockHttpServletRequest("GET","/updateVisitor.htm");
		try {
			Visitor visitor=visitorDao.searchUser("bsmith","password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			request.setParameter("username", visitor.getUserName());
			request.setParameter("password", visitor.getPassword());
			request.setParameter("firstname", visitor.getFirstName());
			request.setParameter("lastname", visitor.getLastName());
			request.setParameter("email", visitor.getEmail());
			request.setParameter("phoneno", "98989898");
			request.setParameter("address", visitor.getAddress());
			modelAndView = controller.updateVisitor(request, response);
		} catch (Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		} 
		
	}

	/**
	 * Positive test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for all USERNAME, PASSWORD and eventId values
		 * Call unregisterEvent method and assert model view name 
		 */	
		
		try {
			request=new MockHttpServletRequest("GET", "/eventunreg.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			request.setParameter("USERNAME", visitor.getUserName());
			request.setParameter("PASSWORD", visitor.getPassword());
			request.setParameter("eventId", "1001");
			request.setParameter("sessionId", "10001");
			modelAndView=controller.registerVisitor(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Negative test case for method unregisterEvent
	 */
	@Test
	public void testUnregisterEvent_Negative() {
		/**
		 * @TODO: Call unregisterEvent method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try{
			request=new MockHttpServletRequest("GET", "/eventunreg.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			request.setParameter("USERNAME", visitor.getUserName());
			request.setParameter("PASSWORD", visitor.getPassword());
			request.setParameter("eventId", "1001");
			modelAndView=controller.registerVisitor(null, response);
			}
			catch (Exception e) {
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}
		
	}

	/**
	 * Positive test case for search events by name
	 */
	@Test
	public void testSearchEventsByName_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for eventname
		 * Call searchEventsByName method and assert model view name 
		 */
		
		try {
			request=new MockHttpServletRequest("GET", "/searchEventByName.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.searchEventsByName(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
	}

	/**
	 * Positive test case for search events by name catalog
	 */
	@Test
	public void testSearchEventsByNameCatalog_Positive() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for eventname
		 * Call searchEventsByNameCatalog method and assert model view name 
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/searchEventByNameCatalog.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.searchEventsByNameCatalog(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events in asc order
	 */
	@Test
	public void testShowEventsAsc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsAsc method and assert model view name 
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displayasc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsAsc(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events in desc order
	 */
	@Test
	public void testShowEventsDesc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsDesc method and assert model view name 
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displaydesc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsDesc(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/visitormain.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events catalog asc order
	 */
	@Test
	public void testShowEventsCatalogAsc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsCatalogAsc method and assert model view name 
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displaycatalogasc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsCatalogAsc(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Test case for show events catalog desc
	 */
	@Test
	public void testShowEventsCatalogDesc() {
		/**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		
		 * Call showEventsCatalogDesc method and assert model view name 
		 */	
		
		try {
			request=new MockHttpServletRequest("GET", "/displaycatalogdesc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsCatalogDesc(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("/eventCatalog.jsp", modelAndView.getViewName());
		
	}

	/**
	 * Negative test case for search events by name
	 */
	@Test
	public void testSearchEventsByName_Negative() {
		/**
		 * @TODO: Call searchEventsByName method by passing request object as null and 
		 * asserting the model view name
		 */	
		
		try {
			request=new MockHttpServletRequest("GET", "/searchEventByName.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.searchEventsByName(null, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}
		
		
	}

	/**
	 * Negative test case for search events by name catalog
	 */
	@Test
	public void testSearchEventsByNameCatalog_Negative() {
		/**
		 * @TODO: Call searchEventsByNameCatalog method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/searchEventByNameCatalog.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.searchEventsByNameCatalog(null, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}
		
		
	}

	/**
	 * Negative test case for show events in asc order
	 */
	@Test
	public void testShowEventsAsc_Negative() {
		/**
		 * @TODO: Call showEventsAsc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displayasc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsAsc(null, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}
		
	}

	/**
	 * Negative test case for show events in desc order
	 * 
	 */
	@Test
	public void testShowEventsDesc_Negative() {
		/**
		 * @TODO: Call showEventsDesc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displaycatalogdesc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsCatalogDesc(null, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}
		
	}

	/**
	 * Negative test case for show events catalog in asc order
	 */
	@Test
	public void testShowEventsCatalogAsc_Negative() {
		/**
		 * @TODO: Call showEventsCatalogAsc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displaycatalogasc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsCatalogAsc(null, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}		
	}

	/**
	 * Negative test case for show events catalog in desc order
	 */
	@Test
	public void testShowEventsCatalogDesc_Negative() {
		/**
		 * @TODO: Call showEventsCatalogDesc method by passing request object as null and 
		 * asserting the model view name
		 */		
		
		try {
			request=new MockHttpServletRequest("GET", "/displaycatalogdesc.htm");
			Visitor visitor=visitorDao.searchUser("bsmith", "password");
			session.setAttribute("VISITOR",visitor);
			request.setSession(session);
			modelAndView=controller.showEventsCatalogDesc(null, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertEquals("Error in Transaction, Please re-Try. for more information check Logfile in C:\\FERSLOG folder", e.getMessage());
		}		
		
	}
	
	
	/**
	 * Positive test case for change password
	 */
	/*@Test
	public void testChangePassword_Positive(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*		
	}
	
	*//**
	 * Negative test case for change password with password as null
	 *//*
	@Test
	public void testChangePassword_PasswordNull(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Do not set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*	
	}
	
	*//**
	 * Negative test case for change password with visitor as null
	 *//*
	@Test
	public void testChangePassword_VisitorNull(){
		*//**
		 * @TODO: Create MockHttpServletRequest object 
		 * Do not set visitor object in VISITOR session by calling searchUser method from visitorDAO		 
		 * Set request parameters for password
		 * Call changePassword method and assert status as success
		 *//*		
	}*/
	
	/**
	 * Positive test case for change password
	 */
	@Test
	public void testChangePassword_Positive(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");	
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("password", "password3");
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("success", modelAndView.getModelMap().get("status"));
		request.setParameter("password", "password");
		modelAndView = controller.changePassword(request, response);
	}
	
	/**
	 * Negative test case for change password with password as null
	 */
	@Test
	public void testChangePassword_PasswordNull(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = visitorDao.searchUser("ylee", "password");			
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);			
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("error", modelAndView.getModelMap().get("status"));
	}
	
	/**
	 * Negative test case for change password with visitor as null
	 */
	@Test
	public void testChangePassword_VisitorNull(){
		try{
			request = new MockHttpServletRequest("GET", "/changePWD.htm");
			Visitor visitor = new Visitor();			
			session.setAttribute("VISITOR", visitor);
			request.setSession(session);
			request.setParameter("password", "password");
			modelAndView = controller.changePassword(request, response);		
		}catch(Exception exception){
			fail("Exception");
		}
		assertEquals("error", modelAndView.getModelMap().get("status"));
	}
}
