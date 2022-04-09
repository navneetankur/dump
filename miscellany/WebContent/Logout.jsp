<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
             response.setHeader("Cache-Control","no-cache");
                   response.setHeader("Cache-Control","no-store");
                response.setHeader("Pragma","no-cache");
                 response.setDateHeader ("Expires", 0);
                    if(session.getAttribute("username")==null)
                     {
                     response.sendRedirect("Login.jsp");
                     }                   
                     
                   %>
 <div id="top">
<!-- this assumes your User class has a username field with a valid getter -->
<h3>Hello <%= session.getAttribute("username") %>, Welcome!! Login Successful.</h3>
</div> 
<a href="MyProfile.html">My Profile</a><br>
<a href="./Logout"> Logout</a><br>
</body>
</html>