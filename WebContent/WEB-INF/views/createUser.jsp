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
 
    <h3>Registration Page</h3>
 
    <p style="color: red;">${errorString}</p>
 
    <form method="POST" action="doCreateUser">
       <table border="0">
          <tr>
             <td>First Name</td>
             <td><input type="text" name="firstname" value= "${user.firstName}" /> </td>
          </tr>
          <tr>
             <td>Last Name</td>
             <td><input type="text" name="lastname" value= "${user.lastName}" /> </td>
          </tr>
          <tr>
             <td>User Name</td>
             <td><input type="text" name="userName" value= "${user.userName}" /> </td>
          </tr>
          <tr>
             <td>Password</td>
             <td><input type="password" name="password" value= "${user.password}" /> </td>
          </tr>
          <tr>
             <td>Email Id</td>
             <td><input type="text" name="emailId" value= "${user.emailId}" /> </td>
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