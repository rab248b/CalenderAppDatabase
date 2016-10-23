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

import com.tamu.cs.beans.Event;
import com.tamu.cs.beans.User;
import com.tamu.cs.utils.DBUtils;
import com.tamu.cs.utils.MyUtils;

/**
 * Servlet implementation class DeleteEventServlet
 */
@WebServlet("/updateEvent")
public class UpdateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateEventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 User user = null;
	        boolean hasError = false;
	        String errorString = null;
		 String eventId = request.getParameter("code");
		 HttpSession session = request.getSession();
		 User loggedInUser = MyUtils.getloggedInUser(session);
		 Connection conn = MyUtils.getStoredConnection(request);
		 Event event = new Event();
		 try {
//             
			 event = DBUtils.findEvent(conn, loggedInUser.getUniqueId(), eventId);
//              
         } catch (SQLException e) {
             e.printStackTrace();
             hasError = true;
             errorString = e.getMessage();
         }
		 request.setAttribute("errorString", errorString);
		 request.setAttribute("event", event);
	         
	     
	        // Forward to /WEB-INF/views/productListView.jsp
	        RequestDispatcher dispatcher = request.getServletContext()
	                .getRequestDispatcher("/WEB-INF/views/updateEvent.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
