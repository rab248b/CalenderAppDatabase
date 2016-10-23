package com.tamu.cs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

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
 
@WebServlet(urlPatterns = { "/doListEvents" })
public class DoListEventsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public DoListEventsServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();
        
        
        // Check User has logged on
        User loggedInUser = MyUtils.getloggedInUser(session);
        
       
//        String startDate = request.getParameter("startdate");
//        System.out.println(startDate);
//        String endDate =  request.getParameter("enddate");
//        
//        
//        
//        java.util.Date javaEventStartDate=null, javaEventEndDate=null;
//		try {
//			javaEventStartDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(startDate);
//		
//			javaEventEndDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(endDate);
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		Date eventStartDate = new Date(javaEventStartDate.getTime());
//        Date eventEndDate = new Date(javaEventEndDate.getTime());
        
 
        String errorString = null;
        List<Event> list = null;
        try {
//            list = DBUtils.queryEvents(conn, loggedInUser.getUniqueId(), eventStartDate,eventEndDate);
        	list = DBUtils.listEvent(conn, loggedInUser.getUniqueId());
            request.setAttribute("user", loggedInUser);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
   
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("eventList", list);
         
     
        // Forward to /WEB-INF/views/productListView.jsp
        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/eventListView.jsp");
        dispatcher.forward(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}