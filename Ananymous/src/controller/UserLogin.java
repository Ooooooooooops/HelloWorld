package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLogin {
	
	ModelAndView mv = new ModelAndView();
	
	@RequestMapping("/login.htm")
	public ModelAndView searchUser(HttpServletRequest request,HttpServletResponse response)
	{
		String userName = request.getParameter("Username").toString();
		String Password = request.getParameter("loginuserpass").toString();
		System.out.println("inside servlet");
		System.out.println(userName);
		System.out.println(Password);
		if(userName.equals("Dev") && Password.equals("123"))
		{
			mv.addObject("VALIDATION","Successfully Logged in");
			mv.setViewName("/login.jsp");
			return mv;
		}else{
			mv.addObject("VALIDATION","Invalid User name or password");
			mv.setViewName("/login.jsp");
			return mv;
		}
		
		
	}
}
