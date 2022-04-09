<%@page import="com.glecom.products.models.Order"%>
<%@page import="com.glecom.products.models.Address"%>
<%@page import="com.glecom.products.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./includes/header.jsp" %>

<div class="container">
<div class="main">

<div class="shoping_bag">
	
		<h4><img src="images/avatar_2x.png" height =60 ><%= session.getAttribute("userId") %></h4>
		<div class="shoping_bag2">
		<div class="shoping_left" >
			<a class="btn1" href="./UserProfile" style="width:11em" >&nbsp &nbsp &nbsp Edit Profile</a><br>
			<a class="btn1" name="butn" href="./ChangePassword.jsp" style="width:11em" >Change Password</a>
			
		</div>
		
	    </div>
		<div class="clearfix"></div>
	</div>

		
			
		<div class="shoping_bag2">
		<div class="shoping_left" align="center">
			<a class="btn1" href="./DisplayCartDb" style="width:10em">Cart</a>
		</div>
	    </div>
	    <div class="shoping_bag2">
		<div class="shoping_left" align="center">
			<a class="btn1" href="./DisplayWishlist" style="width:10em">Wishlist</a>
		</div>
	    </div>
	    <div class="shoping_bag2">
		<div class="shoping_left" align="center">
			<a class="btn1" href="./DisplayOrders" style="width:10em">My orders</a><br><br>
		</div>
		</div>
		<!-- <div class="shoping_bag2">
		<div class="shoping_left" align="center">
			<a class="btn1" href="#" style="width:10em">Address Book</a>
		</div> -->
		<div class="clearfix"></div>
	    </div>
		
</div>
</div>
<%@ include file="./includes/footer.jsp" %>