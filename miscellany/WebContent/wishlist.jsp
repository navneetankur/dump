<%@ include file="./includes/header.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.glecom.products.models.Product" %>
<% int c=0;%>
<!-- content -->
<div class="container">
<div class="main">
	<%
			HttpSession ses = request.getSession(false);
			if(ses.getAttribute("accessType").equals("guest")){
	%>
	Please log in to create your wishlist
	<%}else{ %>
	<div class="shoping_bag">
	
		<h4><img src="images/icon_wishlist.png" >my wishlist </h4>
		<div class="clearfix"></div>
	</div>
	
	<% 		
			 ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products");
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
					<li><a href="./MoveWishlist?a=w2c&id=<%= product.getId()%>"><img src="images/cart.png" width="25px" height="26px"></a></li>
					<li><a href="./MoveWishlist?a=cut&id=<%= product.getId()%>"><img src="images/s_icon3.png" alt="" ></a></li>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="shoping_right">
			<%if(product.getDiscount() == 0) { %>
			<p><span> Rs. <%= product.getPrice()%></span></p>
		<% } else { %>
			<p><span class="oldprice"> Rs.<%= product.getPrice()%></span><br />
			<span> Rs. <%= product.getDiscountedPrice()%></span></p>
		<% } %>
		</div>
		<div class="clearfix"></div>
	</div>
	
	<%} %>
	
</div>
</div>
<% if(c==0){ %>
No items in your wishlist
<% } %>
<% } %>
<%@ include file="./includes/footer.jsp" %>