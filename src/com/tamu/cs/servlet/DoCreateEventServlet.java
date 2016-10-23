package com.tamu.cs.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

@WebServlet(urlPatterns = { "/doCreateEvent" })
public class DoCreateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DoCreateEventServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		// Check User has logged on
		User loggedInUser = MyUtils.getloggedInUser(session);
		String eventName = request.getParameter("eventname");
		String eventStartDateString = request.getParameter("eventstartdate");
		String eventEndDateString = request.getParameter("eventenddate");
		String eventStartTimeString = request.getParameter("eventstarttime");
		String eventEndTimeString = request.getParameter("eventendtime");
		String location = request.getParameter("location");
		String isrecurring = "N";
		String createdBy = loggedInUser.getUniqueId();
		System.out.println(eventStartDateString);
		System.out.println(eventStartTimeString);
		java.util.Date javaEventStartDate = null, javaEventStartTime = null, javaEventEndDate = null,
				javaEventEndTime = null;

		boolean hasError = false;
		String errorString = null;
		Date eventStartDate = null, eventEndDate = null;
		Timestamp eventStartTime = null,eventEndTime = null;
		if (eventName == null || eventName.length() == 0) {
			hasError = true;
			errorString = "Required event name! ";
		}

		if (eventStartDateString == null || eventEndDateString == null ||
				eventStartDateString == "" || eventEndDateString == "") {
			hasError = true;
			errorString = "Event Start date and end date required!";
		}
		// else if(eventStartDate.after(eventEndDate)){
		// hasError = true;
		// errorString = "Start date is greater than end date!";
		// }
		
		else if (eventStartTimeString == null || eventEndTimeString == null || 
				eventStartTimeString == "" || eventEndTimeString == "") {
			hasError = true;
			errorString = "Required event start time and end time !";
		} else if (location == null || location.length() == 0) {
			hasError = true;
			errorString = "Required event location !";
		} else {
			System.out.println(eventStartDateString);
			System.out.println(eventStartTimeString);
			try {
				javaEventStartDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(eventStartDateString);
				javaEventEndDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(eventEndDateString);
				// eventStartTimeString =
				// Long.toString(javaEventStartDate.getTime() +
				// Long.parseLong(eventStartTimeString));
				javaEventStartTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
						.parse(eventStartDateString + " " + eventStartTimeString);

				// eventEndTimeString = Long.toString(javaEventEndDate.getTime()
				// + Long.parseLong(eventEndTimeString));
				javaEventEndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)
						.parse(eventEndDateString + " " + eventEndTimeString);

			} catch (ParseException e) {
				hasError = true;
				errorString = "Required fields empty or invalid! ";
				e.printStackTrace();
			}

			eventStartDate = new Date(javaEventStartDate.getTime());
			eventEndDate = new Date(javaEventEndDate.getTime());

			eventStartTime = new Timestamp(javaEventStartTime.getTime());
			eventEndTime = new Timestamp(javaEventEndTime.getTime());
			System.out.println(eventStartDate);
			System.out.println(eventStartTime);
			if (eventStartTime.after(eventEndTime)) {
				hasError = true;
				errorString = "Start time is greater than end time!";
			}

			else {
				Connection conn = MyUtils.getStoredConnection(request);
				try {

					Event event = new Event();
					event.setEventName(eventName);
					event.setEventStartDate(eventStartDate);
					event.setEventEndDate(eventEndDate);
					event.setEventStartTime(eventStartTime);
					event.setEventEndTime(eventEndTime);
					event.setLocation(location);
					event.setIsRecurring(isrecurring.charAt(0));
					event.setCreatedBy(createdBy);
					DBUtils.createEvent(conn, event);

				} catch (SQLException e) {
					e.printStackTrace();
					hasError = true;
					errorString = e.getMessage();
				}
			}
		}

		// If error, forward to /WEB-INF/views/login.jsp
		if (hasError) {
			Event event = new Event();
			event.setEventName(eventName);
			 event.setEventStartDate(eventStartDate);
				event.setEventEndDate(eventEndDate);
				event.setEventStartTime(eventStartTime);
				event.setEventEndTime(eventEndTime);
				event.setLocation(location);
				event.setIsRecurring(isrecurring.charAt(0));
				event.setCreatedBy(createdBy);
			// Store information in request attribute, before forward.
			request.setAttribute("errorString", errorString);
			request.setAttribute("event", event);

			// Forward to /WEB-INF/views/login.jsp//
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/createEvent.jsp");

			dispatcher.forward(request, response);
		}

		// If no error
		// Store user information in Session
		// And redirect to userInfo page.
		else {
			// HttpSession session = request.getSession();
			// MyUtils.storeloggedInUser(session, user);

			// If user checked "Remember me".
			//// if(remember) {
			// MyUtils.storeUserCookie(response,user);
			// }

			// Else delete cookie.
			// else {
			// MyUtils.deleteUserCookie(response);
			// }

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
