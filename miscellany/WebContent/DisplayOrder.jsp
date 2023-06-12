<%@page import="com.glecom.products.models.Order"%>
<%@page import="com.glecom.products.models.Address"%>
<%@page import="com.glecom.products.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./includes/header.jsp" %>
<% ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products"); %>
<% String orderId=(String)request.getAttribute("orders");%>
<% int c=0,i=0; %>
<% String []str=(orderId).split(","); %>
<div class="container">
<div class="main">
	<div class="shoping_bag">
	
		<h4><img src="images/avatar_2x.png" height =60 ><%= session.getAttribute("userId") %>'s orders:</h4>
		<div class="shoping_bag2">
		
	    </div>
		<div class="clearfix"></div>
	</div>
	<% 		
			for(Product product:productList) { c=1; %>
				
	<div class="shoping_bag1">
		<div class="shoping_left">
			<div class="shoping1_of_1">
					<img src="<%=product.getImagePath() %>"  class="img-responsivee" width="100px" height="100px"/>
			</div>
			<div class="shoping1_of_2">
				<h4><a href="./ProductDisplay?product-id=<%= product.getId()%>"> <%=product.getName()%></a></h4>
				<span>size <b><%= product.getSize()%></b>&nbsp;&nbsp; qty <b>1</b></span>
				<ul class="s_icons">
								</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="shoping_right">
			<a href="./TrackOrder.jsp?orderId=<%= str[i]%>">Order Id:<br><%= str[i]%></a>
			<%i++; %>
		</div>
		<div class="clearfix"></div>
	</div>
	<%} %>
</div>
</div>
<% if(c==0){ %>
No orders placed
<% } %>
<%@ include file="./includes/footer.jsp" %>