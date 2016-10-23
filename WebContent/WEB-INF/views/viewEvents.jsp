<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
 <head>
    <meta charset="UTF-8">
    <title>My Calendar</title>
 </head>
 <body>
 
    <jsp:include page="_header.jsp"></jsp:include>
   <%--  <jsp:include page="_menu.jsp"></jsp:include> --%>
 <form method="POST" >
   <table style="width:50%"  >
  <tr>
    <td align= "left"><b> <a href="${pageContext.request.contextPath}/createEvent">Create Event</a></b></td>
   
  </tr>
  <tr>
    <td>  <b>Select Date to View Events</b></td>
   
  </tr>
  <tr>
    <td>  Start Date:
  <input type="date" name="startdate" value= "${event.startDate}"></td>
    <td>  End Date:
  <input type="date" name="enddate" value= "${event.endDate}"></td> 
    <td><a href="${pageContext.request.contextPath}/doListEvents">Go</a></td>
  </tr>
</table>
 
 <jsp:include page="_footer.jsp"></jsp:include>
   
    </form>
 

    
 
 </body>
</html>