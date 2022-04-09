<%@page import="com.DAO.DeleteUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String userId =request.getParameter("userId");
	DeleteUser del = new DeleteUser();
	del.deleteUser(userId);
	/* if(userId.equals("user")){
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("./AdminUsers");
	requestDispatcher.forward(request, response);
	}
	if(userId.equals("vendor")){ */
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("./AdminVendor");
	requestDispatcher.forward(request, response);
	/* } */
%>
</body>
</html>