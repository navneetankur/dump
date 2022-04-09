<%@page import="com.service.Calc"%>
<%@ include file="./includes/HeaderAdmin.jsp" %>
<%@page import="com.DAO.UserBean"%>
<%@page import="java.util.ArrayList"%>

<%@ page import="com.glecom.products.models.Product" %>
<% ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("users"); %>
<% ArrayList<Product> productList = (ArrayList<Product>)request.getAttribute("products"); %>
<%  	System.out.print(String.valueOf(request.getAttribute("countUserCat")));	
		int countUser=Integer.valueOf(String.valueOf(request.getAttribute("countUser")));
		 int countUserCat=Integer.valueOf(String.valueOf(request.getAttribute("countUserCat")));
		 float ratio=(((float)countUserCat/countUser)*100);
		 int count=0;
%><div class="container">
<div class="main">

<%if(list.size()==0){ %>


	<div class="shoping_bag">
	
		<font size="5">No customers.</font>
		<div class="clearfix"></div>
	</div>
<%}else if(productList.size()==0){ %>
	<div class="shoping_bag">
	
		Number of customers: <%= list.size() %><br>
	</div>
	<div class="shoping_bag">
		Total number of products bought: 0
		
		<div class="clearfix"></div>
	</div>
<%}else{ %>

	<div class="shoping_bag">
	
		Number of customers: <%= list.size() %><br>
	</div>
	<div class="shoping_bag">
		Total number of products bought: <%= productList.size() %><br>
	</div>
	<div class="shoping_bag">
		Total Amount: Rs.<%= Calc.sum(productList) %><br>
	</div>
	<div class="shoping_bag">
		Ratio of users: <%=ratio %>%<br>
	</div>
	<div class="shoping_bag">
		Recently Ordered Products:		
		<div class="clearfix"></div>
	</div>
	
	<div class="grids_of_4">
					<%	while(productList.size()>4){
			        	productList.remove(0);
			        } %>
					<%for(Product product:productList) { count++; %>
				
				  <div class="grid1_of_4">
						<div class="content_box"><a href="./ProductDisplay?product-id=<%= product.getId()%>">
					   	  <div class="view view-fifth">
					   	   	 <img src="<%=product.getImagePath() %>" class="img-responsivee" width="100px" height="100px"/>
						   	  </a>
						   </div><!-- sanyam -->
						    <h4><a href="./ProductDisplay?product-id=<%= product.getId()%>"> <%=product.getName()%></a></h4>  
						     
				   	</div><!-- sanyam -->
				</div>
				<% if(count==4) {
					count=0; 
				%>
				<div class="clearfix"></div>
				
				<div class="grids_of_4">
				<% }} %>
	

<%} %>
</div>
</div>
</div>
<%@ include file="./includes/footer.jsp" %>