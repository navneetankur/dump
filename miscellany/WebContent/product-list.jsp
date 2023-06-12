<%@ include file="./includes/header.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.glecom.products.models.Product" %>
<% ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products"); %>
<% int c=0; %>
<!-- content -->
<div class="container">
<div class="women_main">
	<!-- start sidebar -->
	<div class="col-md-3">
	  <%@include file="./includes/filters.jsp" %>
   </div>
	<!-- start content -->
	<!--sanyam  -->
	<div class="col-md-9 w_content">
		<div class="women">
			<ul class="w_nav">
						<!-- <li>Sort : </li>
		     			<li><a href="#">discount</a></li> |price:
		     			<li><a href="#"> Low|</a></li>
		     			<li><a href="#">High </a></li>  
		     			<div class="clear"></div>	 -->
		     </ul>
		     <div class="clearfix"></div>	
		</div>
		<!--sanyam  -->
		<!-- grids_of_4 -->
		<div class="grids_of_4">
		<% int count =0;
			for(Product product:productList) { count++;c=1; %>
				  <div class="grid1_of_4">
						<div class="content_box"><a href="./ProductDisplay?product-id=<%= product.getId()%>">
					   	  <div class="view view-fifth">
					   	   	 <img src="<%=product.getImagePath() %>" class="img-responsivee" width="210px" height="285px"/>
						   	  </a>
						   </div><!-- sanyam -->
						    <h4><a href="./ProductDisplay?product-id=<%= product.getId()%>"> <%=product.getName()%></a></h4>
						    <%if(product.getDiscount() == 0) { %>
						     Rs.<%=product.getPrice()%>
						     <% } else { %>
						     	<span class="oldprice">Rs.<%=product.getPrice()%></span> Rs.<%=product.getDiscountedPrice()%>
						     <% } %>
						      &nbsp <a href="./CheckOutInit?product-id=<%=product.getId() %>">Buy</a>&nbsp&nbsp&nbsp
						     <% if(tracking.isGuest()) {%>
									<a href="./Cart?id=<%= product.getId()%>"> <img src="./Emart_files/cart.png" width="40px" height="40px"/></a>
								<%} else{ %>
									<a href="./AddToCart?id=<%= product.getId()%>"><img src="./Emart_files/cart.png" width="40px" height="40px"/></a>
								<%} %>
						     
				   	</div><!-- sanyam -->
				</div>
				<% if(count==4) {
					count = 0; 
				%>
				<div class="clearfix"></div>
				</div>
				<div class="grids_of_4">
				<% }} %> <!-- endif and for both -->
			
		</div>
		<!-- end grids_of_4 -->
		<% if(c==0){ %>
			No Products
		<%} %>
		
	</div>
	<div class="clearfix"></div>
	
	<!-- end content -->
</div>
</div>
<%@ include file="./includes/footer.jsp" %>