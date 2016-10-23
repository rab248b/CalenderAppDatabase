<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html>
  <head>
     <meta charset="UTF-8">
     <title>Home Page</title>
  </head>
  <body>
 
     <jsp:include page="_header.jsp"></jsp:include>
    <%--  <jsp:include page="_menu.jsp"></jsp:include> --%>
    
      <h3>Welcome</h3>
   <p style="color: red;">${errorString}</p>
    <a href="${pageContext.request.contextPath}/login">Login</a>
    <a href="${pageContext.request.contextPath}/createUser" >Register</a>
  
          
   <!--    This is demo Simple web application using jsp,servlet &amp; Jdbc. <br><br>
      <b>It includes the following functions:</b>
      <ul>
         <li>Login</li>
         <li>Storing user information in cookies</li>
         <li>Product List</li>
         <li>Create Product</li>
         <li>Edit Product</li>
         <li>Delete Product</li>
      </ul> -->
 
     <jsp:include page="_footer.jsp"></jsp:include>
 
  </body>
</html>