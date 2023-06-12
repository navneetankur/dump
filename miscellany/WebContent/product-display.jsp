<%@ include file="./includes/header.jsp" %>
<%@page import="com.glecom.handlers.Tracking"%>
<%@page import="com.glecom.products.models.Feedback"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.glecom.products.models.Product"%>
<%
	Product product = (Product)request.getAttribute("product");
	if(product == null) return;
	ArrayList<Feedback> feedbackList = (ArrayList<Feedback>)request.getAttribute("feedbacks");
    ArrayList<Product> recentList = (ArrayList<Product>)request.getAttribute("recents");
   ArrayList<Product> similarList = (ArrayList<Product>)request.getAttribute("similars");
   int discount = product.getDiscount();
%>
<%
	tracking.addToRecentlyViewed(product.getId());
%>

<div class="container">
<div class="women_main">
	<!-- start content -->
			<div class="row single">
				<div class="col-md-9">
				  <div class="single_left">
					<div class="grid images_3_of_2">
						<ul id="etalage">
							<li>
									<img class="etalage_thumb_image" src="<%=product.getImagePath() %>" class="img-responsivee" width="280px" height="402px" />
									  <!-- <img class="etalage_source_image" src="<%=product.getImagePath() %>" class="img-responsive" title="" /> -->
							</li>
						</ul>
						 <div class="clearfix"></div>		
				  </div>
				  <div class="desc1 span_3_of_2">
					<h3><%=product.getName()%></h3>
					<%if(discount == 0) { %>
					<p>Rs. <%=product.getPrice()%></p>
					<% } else {%>
					<span class="oldprice">Rs. <%=product.getPrice()%></span>
					<p>Rs. <%=product.getDiscountedPrice()%></p>
					<% } %>
					<div class="det_nav">
						<h4>Similar :</h4>
						<ul>
                            <% for(Product p : similarList) { %>
    
    
                                <li><a href="./ProductDisplay?product-id=<%= p.getId()%>">
                                	<img src="<%=p.getImagePath() %>" class="img-responsivee" alt=""/ width="270px" height="270px"></a></li>

                             <% } %>
						</ul>
					</div>
					<div class="det_nav1">
						<h4 style="display:inline; margin-right:3em">Size : <%=product.getSize() %></h4>
						<%@include file="./includes/rating.jsp" %>
					</div>
					<div class="btn_form">
						<a href="./CheckOutInit?product-id=<%=product.getId() %>">buy</a>
						<!-- sanyam -->
						<% if(tracking.isGuest()) {%>
									<a href="./Cart?id=<%= product.getId()%>"> Add to cart</a>
								<%} else{ %>
									<a href="./AddToCart?id=<%= product.getId()%>">Add to cart</a>
								<%} %>
						
					<%
						String usId= (String) session.getAttribute("userId");
						if(usId!=null){
						if(usId.equals("guest")){}else{
					%>
					
					<a href="./Wishlist?id=<%= product.getId()%>">Save in wishlist </a>
					<%}} %>
			   	 </div></div>
			   	 <!-- sanyam -->

          	    <div class="clearfix"></div>
          	   </div>
          	    <div class="single-bottom1">
					<h6>Details</h6>
					<p class="prod-desc"><%=product.getDescription()%></p>
				</div>
				<div class="single-bottom2">


                        <h6>Recently Viewed</h6>
                            <%if (recentList != null)
                        	  for(Product p : recentList) { %>
                            	<div class="product">
                                   <div class="product-desc">
                                        <div class="product-img">
                                           <img src="<%=p.getImagePath() %>" class="img-responsivee" width="270px" height="270px"/>
                                       </div>
                                       <div class="prod1-desc">
                            				<h5><a class="product_link" 
                                                href="./ProductDisplay?product-id=<%= p.getId()%>">
                                                <%=p.getName()%></a>
                                             </h5>
                                           <p class="product_descr"> <%=p.getDescription()%> </p>									
                                       </div>
                                      <div class="clearfix"></div>
                                  </div>
                                  <div class="product_price">
                                  <%if(p.getDiscount() == 0) { %>
                                        <span class="price-access">Rs. <%=p.getPrice()%></span>	
                                   <% } else {%>
                                   		<span class="oldprice">Rs. <%=p.getPrice()%></span>	
                                   		<span class="price-access">Rs.<%=p.getDiscountedPrice()%></span>
                                   		<% } %>	
                                       <div class="btn_form">
						<a href="./CheckOutInit?product-id=<%=p.getId() %>">buy</a>		</div>
						 <div class="btn_form">
						 			<% if(tracking.isGuest()) {%>
									<a href="./Cart?id=<%= product.getId()%>"> cart</a>
								<%} else{ %>
									<a href="./AddToCart?id=<%= product.getId()%>">cart</a>
								<%} %>
						
					
						 	
					     </div>				
                                    
                                  </div>
                                 <div class="clearfix"></div>
                        	 </div>
              			<% } %>
		   	  </div>
	       </div>	
	       			<div class="col-md-3">
	  	  <%@include file="./includes/filters.jsp" %>
	<!-- end content -->
</div></div>
<%@ include file="./includes/feedback.jsp" %>
 </div>


<%@ include file="./includes/footer.jsp" %>