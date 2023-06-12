<%@page import="com.glecom.products.models.Order"%>
<%@page import="com.glecom.products.models.Address"%>
<%@page import="com.glecom.products.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./includes/header.jsp" %>
<div class="container">
<div class="main">
<%ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products");
ArrayList<Order> orderList = (ArrayList<Order>)request.getAttribute("orders");
Address address = (Address)request.getAttribute("address");
int sum = 0;Product product;
%>
<h1>order successful</h1>
<%for(int i=0;i<productList.size();i++) { 
	product = productList.get(i);
	sum += Integer.parseInt(product.getPrice());
%>
Order Id: <%=orderList.get(i).getId() %><br />
Name: <%=product.getName() %><br />
Price: <%=product.getPrice() %><br />
Tracking id: TR<%=orderList.get(i).getId()*541 %>
<hr />
<% } %>
Total: Rs. <%=sum %>
<br /><br /><br />
Deliver to address:<br />
<%=address.getName() %><br />
<%=address.getAddress() %><br />
Pin: <%=address.getPincode() %><br />
Phone: <%=address.getPhone() %><br />























</div>
</div>
<%@ include file="./includes/footer.jsp" %>