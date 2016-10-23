package com.tamu.cs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tamu.cs.beans.User;
import com.tamu.cs.utils.DBUtils;
import com.tamu.cs.utils.MyUtils;

@WebServlet(urlPatterns = { "/doCreateUser" })
public class DoCreateUserServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 
	    public DoCreateUserServlet() {
	        super();
	    }
	    
		@Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	 
	        String userName = request.getParameter("userName");
	        String password = request.getParameter("password");
	        String rememberMeStr = request.getParameter("rememberMe");
	        String firstName = request.getParameter("firstname");
	        System.out.println(firstName);
	        String lastName = request.getParameter("lastname");
	        String emailId = request.getParameter("emailId");
	        boolean remember= "Y".equals(rememberMeStr);
	 
	         
	        User user = null;
	        boolean hasError = false;
	        String errorString = null;
	 
	        if (userName == null || password == null
	                 || userName.length() == 0 || password.length() == 0) {
	            hasError = true;
	            errorString = "Required username and password! ";
	        } 
	        else if(password.length()<8){
	        	hasError = true;
	        	errorString = "Password should be atleast 8 characters";
	        }
	        else if(firstName == null || lastName == null
	                 || firstName.length() == 0 || lastName.length() == 0){
	        	hasError = true;
	        	errorString = "Required first name and last name";
	        }
	        else {
	            Connection conn = MyUtils.getStoredConnection(request);
	            try {
	              
	                user = DBUtils.findUser(conn, userName, password);
	                 
	                if (user != null) {
	                    hasError = true;
	                    errorString = "User Name or password invalid";
	                }
	                else {
	                	User createUser = new User();
	                	createUser.setFirstName(firstName);
	                	createUser.setLastName(lastName);
	                	createUser.setUserName(userName);
	                	createUser.setPassword(password);
	                	createUser.setEmailId(emailId);
	                	DBUtils.createUser(conn, createUser);
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	                hasError = true;
	                errorString = e.getMessage();
	            }
	        }
	        
	        // If error, forward to /WEB-INF/views/login.jsp
	        if (hasError) {
	            user = new User();
	            user.setUserName(userName);
	            user.setPassword(password);
	            user.setFirstName(firstName);
	            user.setLastName(lastName);
	            user.setEmailId(emailId);
	        
	            // Store information in request attribute, before forward.
	            request.setAttribute("errorString", errorString);
	            request.setAttribute("user", user);
	 
	            // Forward to /WEB-INF/views/login.jsp//
	            RequestDispatcher dispatcher 
	            = this.getServletContext().getRequestDispatcher("/WEB-INF/views/createUser.jsp");
	 
	            dispatcher.forward(request, response);
	        }
	     
	        // If no error
	        // Store user information in Session
	        // And redirect to userInfo page.
	        else {
	            HttpSession session = request.getSession();
	            MyUtils.storeloggedInUser(session, user);
	             
	             // If user checked "Remember me".
	            if(remember)  {
	                MyUtils.storeUserCookie(response,user);
	            }
	    
	            // Else delete cookie.
	            else  {
	                MyUtils.deleteUserCookie(response);
	            }                       
	      
	            // Redirect to userInfo page.
	            response.sendRedirect(request.getContextPath() + "/viewEvents");
	        }
	    }
	 
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        doGet(request, response);
	    }
	 
}
