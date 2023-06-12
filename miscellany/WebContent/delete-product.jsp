<%@page import="java.util.ArrayList"%>
<%@page import="com.glecom.products.models.Product"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("productList");%>
	 <%!Product product; %>
	<%for(int i=0;i<productList.size();i++) {%>
		<form action="DeleteProduct">
		<table>
			<tr><td><%=productList.get(i).getName() %></td>
			<td><input type="hidden" name="delete" value=<%=productList.get(i).getId() %>>
			<input type="submit" value="delete"></td></tr>
		</table>
		</form>
	<% } %>
</body>
</html>