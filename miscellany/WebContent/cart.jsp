<%@ include file="./includes/header.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.glecom.products.models.Product" %>
<% ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products"); %>
<% float sum=0,total=0,tax=0;int c=0;%>
<!-- content -->
<div class="container">
<div class="main">
	<div class="shoping_bag">
	
		<h4><img src="images/bag1.png" >my shopping bag </h4>
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
					<!-- <li><a href="#"><img src="images/s_icon1.png" alt=""></a></li> -->
					<% 
							
							if(((String)session.getAttribute("accessType")).equals("guest")){ %>
							<li><a href="./RemoveFromCart?id=<%= product.getId()%>"><img src="images/s_icon3.png" alt=""></a></li>
							<%} else{ %>
								<li><a href="./MoveWishlist?a=c2w&id=<%= product.getId()%>"><img src="images/s_icon2.png" alt=""></a></li>
								<li><a href="./RemoveFromDbCart?id=<%= product.getId()%>"><img src="images/s_icon3.png" alt=""></a></li>
							<%} %>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
		<div class="shoping_right">
			<%if(product.getDiscount() == 0) { %>
			<p><span> Rs. <%= product.getPrice()%></span></p>
			<% } else { %>
				<p><span class="oldprice"> Rs.<%= product.getPrice()%></span>
				<br /><span> Rs.<%= product.getDiscountedPrice()%></span></p>
			
			<% } %>
		</div>
		<div class="clearfix"></div>
	</div>
	<%
			sum=sum+product.getDiscountedPrice();
	%>
	<%} %>
	
	<%
		if(sum!=0){
			tax=sum/(12);
			//tax=(float) Math.round(tax * 100) / 100;
			total=sum+tax;
		}
	%>
	<% if(c==1){ %>
	<div class="shoping_bag1">
		
		<div class="shoping_right">
			<p>sub total &nbsp;<span> Rs.<%= sum%></span></p>
			<p>vat collected(12%) &nbsp;<span> Rs.<%= tax%></span></p>
			<p>delivery &nbsp;<a href="#">free</a>&nbsp;<span> Rs. 0</span></p>
		</div>
		<div class="clearfix"></div>
	</div>
	
	<div class="shoping_bag2">
		<div class="shoping_left">
			<a class="btn1" href="./AddressBook">place order</a>
		</div>
		<div class="shoping_right">
			<p class="tot">total &nbsp;<span class="color"> Rs.<%= total %></span></p>
		</div>
		<div class="clearfix"></div>
	</div>
</div>
</div>
<% c =0;}else{ %>
No items in cart
<% } %>
<%@ include file="./includes/footer.jsp" %>