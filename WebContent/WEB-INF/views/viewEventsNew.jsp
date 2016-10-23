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
 
    <form method="POST" action="doListEvents">
       <table border="0">
         
          <tr>
             <td>Event Start Date</td>
             <td><input type="date" name="eventstartdate" value= "${event.eventStartDate}" /> </td>
          </tr>
          <tr>
             <td>Event End Date</td>
             <td><input type="date" name="eventenddate" value= "${event.eventEndDate}" /> </td>
          </tr>
          
          
          <tr>
             <td colspan ="2">
                <input type="submit" value= "Submit" />
              
             </td>
          </tr>
       </table>
    </form>
 
 
    <jsp:include page="_footer.jsp"></jsp:include>
 
 </body>
</html>