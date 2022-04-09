<%@ include file="./includes/header.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@ page import="com.glecom.products.models.Product" %>
<% ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products"); %>
<!-- content -->
<div class="container">
<div class="women_main">
	<!-- start sidebar -->
	<div class="col-md-3">
	  <%@include file="./includes/filters.jsp" %>
   </div>
	<!-- start content -->
	<div class="col-md-9 w_content">
<div class="grids_of_4">
		<% int count =0;
			for(Product product:productList) { count++; %>
				  <div class="grid1_of_4">
						<div class="content_box"><a href="./ProductDisplay?product-id=<%= product.getId()%>">
					   	  <div class="view view-fifth">
					   	   	 <img src="<%=product.getImagePath() %>" class="img-responsivee" width="210px" height="285px"/>
						   	  </a>
						   </div><!-- sanyam -->
						    <h4><a href="./ProductDisplay?product-id=<%= product.getId()%>"> <%=product.getName()%></a></h4>
						     Rs. <%=product.getPrice()%> &nbsp 
						     <form action="./DeleteProduct">
				<input type="hidden" name="delete" value=<%=product.getId() %>>
				<input type="submit" value="Remove this product">
						     
				   	</div><!-- sanyam -->
				
				</div>
				<% if(count==4) {
					count = 0; 
				%>
								<div class="clearfix"></div>
				
				</div>
				<div class="grids_of_4">
				<% }} %> <!-- endif and for both -->
			<%if(count==0){ %>
	<br>
	No Products.
	<br>
	<%} %>
		</div>
		<!-- end grids_of_4 -->
		
		
	</div>
	<div class="clearfix"></div>
	
	<!-- end content -->
	
</div>
</div>
<%@ include file="./includes/footer.jsp" %>