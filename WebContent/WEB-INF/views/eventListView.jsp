<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>Event List</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Event List</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <table border="1" cellpadding="5" cellspacing="1" >
       <tr>
          <th>Event Name</th>
          <th>Location</th>
          <th>Start Date</th>
          <th>End Date</th>
          <th>Start Time</th>
          <th>End Time</th>
          <th>Created On</th>
          <th>Update</th>
          <th>Delete</th>
       </tr>
       <c:forEach items="${eventList}" var="event" >
          <tr>
             <td><c:out value="${event.eventName}"/></td>
             <td><c:out value="${event.location}"/></td>
             <td><c:out value="${event.eventStartDate}"/></td>
             <td><c:out value="${event.eventEndDate}"/></td>
              <td><c:out value="${event.eventStartTime}"/></td>
               <td><c:out value="${event.eventEndTime}"/></td>
               <td><c:out value="${event.createdOn}"/></td>
               
               
               
             <td>
                <a href="${pageContext.request.contextPath}/updateEvent?code=${event.eventId}">Edit</a>
             </td>
             <td>
                <a href="${pageContext.request.contextPath}/deleteEvent?code=${event.eventId}">Delete</a>
             </td>
          </tr>
       </c:forEach>
    </table>
 
    <a href="${pageContext.request.contextPath}/createEvent" >Create Event</a>
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>