<!DOCTYPE html>
<%@page import="com.glecom.handlers.Tracking"%>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=windows-1252">
<title>Miscellany</title>
<link href="Emart_files/bootstrap.css" rel="stylesheet" type="text/css">
<!-- jQuery (necessary JavaScript plugins) -->
<!-- <script type="text/javascript" src="./Emart_files/jquery-1.11.1.min.js"></script> -->
<script src="Emart_files/jquery-1.js" type="text/javascript"></script>

<!-- Custom Theme files -->
<link href="Emart_files/style.css" rel="stylesheet" type="text/css">
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--webfont-->
<link href="Emart_files/css.css" rel="stylesheet" type="text/css">
<!-- start menu -->
<link href="Emart_files/megamenu.css" rel="stylesheet" type="text/css" media="all">
<link href="Emart_files/mystylesheet.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="Emart_files/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="Emart_files/menu_jquery.js"></script>
<link rel="stylesheet" href="css/etalage.css">
<script src="js/jquery.etalage.min.js"></script>
<script>
			jQuery(document).ready(function($){

				$('#etalage').etalage({
					thumb_image_width: 300,
					thumb_image_height: 400,
					source_image_width: 900,
					source_image_height: 1200,
					show_hint: true,
					click_callback: function(image_anchor, instance_id){
						alert('Callback example:\nYou clicked on an image with the anchor: "'+image_anchor+'"\n(in Etalage instance: "'+instance_id+'")');
					}
				});

			});
		</script>
<!-- the jScrollPane script -->
<script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
		<script type="text/javascript" id="sourcecode">
			$(function()
			{
				$('.scroll-pane').jScrollPane();
			});
		</script> 
<link href="./Emart_files/address.css" rel="stylesheet" type="text/css">
</head>
<body>
<%
Tracking tracking = new Tracking(request, response);
%>
<!-- header_top -->
<div class="top_bg">
<div class="container">
<div class="header_top">
	<!-- <div class="top_left">
		<h2><a href="#">50%off</a> use coupon code "big61" and get extra 33% off on orders above rs 2,229 </h2>
	</div> -->
	<div class="top_right">
		<ul>
			<%if(tracking.isAdmin()) { %>
			<li><a href="./DisplayUserCat.jsp">Users Reports</a></li>|
			<li><a href="./AdminVendor">Vendors Reports</a></li>|
			<% } 
			else if(tracking.isVendor()) {
			%>
			<li><a href="./add-product.jsp">Add-Product</a></li>|
			<li><a href="./VendorViewProduct">Your Products</a></li>|
			<% } 
			else if(tracking.isUser()) {
				%>
				<li><a href="./sell.jsp">Sell</a></li>|
				<% }%>
			<% if(tracking.isGuest()) {%>
			<li><a href="./Registration.jsp">Sign up</a></li>|
			<li class="login"><!-- login form start -->
						<div id="loginContainer"><a id="loginButton" onclick="showLoginBox(this)"><span>Login</span></a>
						    <div id="loginBox">                
						        <form id="loginForm" action="./InServlet" method="post">
						                <fieldset id="body">
						                	<fieldset>
						                          <label for="email">User Id</label>
						                          <input name="userId" id="email" type="text" required>
						                    </fieldset>
						                    <fieldset>
						                            <label for="password">Password</label>
						                            <input name="password" id="password" type="password" required>
						                     </fieldset>
						                    <input id="login" value="Sign in" type="submit">
						                	<label for="checkbox"><input id="checkbox" type="checkbox"> <i>Remember me</i></label>
						            	</fieldset>
						            <span><a href="./ForgotPassword">Forgot your password?</a></span>
							 </form>
				        </div>
			      </div>
			</li><!-- login form end -->
			<% }  /* endif */ 
			else {
			%>
			<li><a href="./MyProfile.jsp">My Account</a></li>|
			<li><a href="./Logout">Logout</a></li>
			<li><%= session.getAttribute("userId") %></li>
			<% } %>
		</ul>
	</div>
	<div class="clearfix"></div>
</div>
</div>
</div>
<!-- header -->
<div class="header_bg">
<div class="container">
<div class="logo">
			<a href="./home"><img src="./Emart_files/logo.jpg" /> </a>
			
		</div>
	
		<!-- start header menu -->
	</div>
    
</div>

</div>