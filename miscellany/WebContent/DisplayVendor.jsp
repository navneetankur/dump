<%@ include file="./includes/HeaderAdmin.jsp" %>
<%@page import="com.DAO.UserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.glecom.products.models.Product" %>
<% ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("users"); int c=0;%>
<div class="container">
<div class="main">

<% for(UserBean user:list) { c=1;%>

<div class="shoping_bag">
	
		Vendor id: <%=user.getUserId() %><br>
		Vendor Name:<%=user.getUserName() %><br>
		<div class="shoping_left">
			<a class="btn1" href="./DeleteUser.jsp?userId=<%=user.getUserId() %>">Remove Vendor</a>
			<a class="btn1" href="./VendorProducts?userId=<%=user.getUserId() %>">View Products</a>
		</div>
		<div class="clearfix"></div>
	</div>
<%} %>
<%if(c==0){%>
<br>
No Vendors registered.
<br>
<%} %>

</div>
</div>
<%@ include file="./includes/footer.jsp" %>