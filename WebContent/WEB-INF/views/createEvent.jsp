<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>SignUp</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
    <jsp:include page="_menu.jsp"></jsp:include>
 
    <h3>Create Event Page</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <form method="POST" action="doCreateEvent">
       <table border="0">
          <tr>
             <td>Event Name</td>
             <td><input type="text" name="eventname" value= "${event.eventName}" /> </td>
          </tr>
          <tr>
             <td>Location</td>
             <td><input type="text" name="location" value= "${event.location}" /> </td>
          </tr>
          <tr>
             <td>Event Start Date</td>
             <td><input type="date" name="eventstartdate" value= "${event.eventStartDate}" /> </td>
          </tr>
          <tr>
             <td>Event End Date</td>
             <td><input type="date" name="eventenddate" value= "${event.eventEndDate}" /> </td>
          </tr>
          <tr>
             <td>Event Start Time</td>
             <td><input type="time" name="eventstarttime" value= "${event.eventStartTime}" /> </td>
          </tr>
          <tr>
             <td>Event Stop Time</td>
             <td><input type="time" name="eventendtime" value= "${event.eventEndTime}" /> </td>
          </tr>
          
          <tr>
             <td colspan ="2">
                <input type="submit" value= "Submit" />
                <a href="${pageContext.request.contextPath}/">Cancel</a>
             </td>
          </tr>
       </table>
    </form>
 
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>