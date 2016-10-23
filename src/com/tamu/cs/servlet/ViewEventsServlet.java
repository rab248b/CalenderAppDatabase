package com.tamu.cs.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.tamu.cs.beans.User;
import com.tamu.cs.beans.Event;
import com.tamu.cs.utils.MyUtils;
 
@WebServlet(urlPatterns = { "/viewEvents" })
public class ViewEventsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    public ViewEventsServlet() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
 
 
        // Check User has logged on
        User loggedInUser = MyUtils.getloggedInUser(session);
        Date startDate, endDate;
 
  
        // Not logged in
        if (loggedInUser == null) {
       
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
  
        // Store info in request attribute
        request.setAttribute("user", loggedInUser);
 
  
        // Logined, forward to /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/viewEvents.jsp");
        dispatcher.forward(request, response);
 
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
}
