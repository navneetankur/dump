<!-- footer_top -->
<%@page import="com.glecom.handlers.Tracking"%>
<div class="footer_top">
 <div class="container">
	<div class="span_of_4">
		<div class="span1_of_4">
			<h4>Shop</h4>
			<ul class="f_nav">
				<li><a href="./CategoryWise?category=men">men</a></li>
				<li><a href="./CategoryWise?category=women">women</a></li>
				<li><a href="./CategoryWise?category=electronics">electronics</a></li>
				<li><a href="./CategoryWise?category=home_and_kitchen">home and kitchen</a></li>
				<li><a href="./CategoryWise?category=books_and_media">books and media</a></li>
			</ul>	
		</div>
		<div class="span1_of_4">
			<h4>help</h4>
			<ul class="f_nav">
				<li><a href="./payments.jsp">payments</a></li>
				<li><a href="./shipping.jsp">shipping</a></li>
				<li><a href="./cancellation.jsp">cancellation</a></li>
				<li><a href="./aboutus.jsp">about us</a></li>
				<li><a href="./contactus.jsp">contact us</a></li>
			</ul>				

		</div>
		<div class="span1_of_4">
			<h4>account</h4>
			<ul class="f_nav">
				<% if(!tracking.isGuest()){ %>
				<li><a href="./MyProfile.jsp">account</a></li><%} %>
				<li><a href="./DisplayWishlist">wishlist</a></li>
				<li><a href="./DisplayCart">my shopping bag</a></li>
				
			</ul>
			
		</div>
		<div class="span1_of_4">
			<h4>popular</h4>
			<ul class="f_nav">
				<li><a href="./SubCategoryWise?category=electronics&sub-category=mobile_phones">mobile phones</a></li>
				<li><a href="./SubCategoryWise?category=electronics&sub-category=mobile_accessories">mobile accessories</a></li>
				<li><a href="./SubCategoryWise?category=books_and_media&sub-category=books">books</a></li>
				<li><a href="./SubCategoryWise?category=women&sub-category=jewellery">jewellery</a></li>
				<li><a href="./SubCategoryWise?category=men&sub-category=accessories">men accessories</a></li>
			</ul>			
		</div>
		<div class="clearfix"></div>
		</div>
		<!-- start span_of_2 -->
		<div class="span_of_2"> </div>
  </div>
</div>

<!-- footer -->
<div class="footer">

<div class="container">
      <div class="copy">
      <p class="link">© All rights reserved </p>
      </div>
    </div>
</div>
</div>
<script src="Emart_files/bootstrap.js" type="text/javascript"></script>

</body></html>