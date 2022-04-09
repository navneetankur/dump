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
<script type="text/javascript">
function changeBackground(ddbox) {
	

	 if ( ddbox.options[ddbox.selectedIndex].value == "aliceblue" ) {
		 document.body.style.backgroundColor = "rgb(239, 247, 253)";
	  } else if ( ddbox.options[ddbox.selectedIndex].value == "silver" ) {
		  document.body.style.backgroundColor = "rgb(190, 189, 189)";
	  } else if ( ddbox.options[ddbox.selectedIndex].value == "whitesmoke" ) {
		  document.body.style.backgroundColor = "rgb(244, 244, 244)";
 	 } else if ( ddbox.options[ddbox.selectedIndex].value == "wheat" ) {
 		document.body.style.backgroundColor = "rgb(242, 219, 175)";
 	 } else if ( ddbox.options[ddbox.selectedIndex].value == "seashell" ) {
 		document.body.style.backgroundColor = "rgb(253, 243, 236)";
 	 } else if ( ddbox.options[ddbox.selectedIndex].value == "oldlace" ) {
 		document.body.style.backgroundColor = "rgb(255, 246, 229)";
	} else if ( ddbox.options[ddbox.selectedIndex].value == "mintcream" ) {
		document.body.style.backgroundColor = "rgb(247, 255, 251)";
	} else if ( ddbox.options[ddbox.selectedIndex].value == "linen" ) {
		document.body.style.backgroundColor = "rgb(247, 237, 227)";
	} else if ( ddbox.options[ddbox.selectedIndex].value == "lavanderblush" ) {
		document.body.style.backgroundColor = "rgb(255, 242, 246)";
	} else if ( ddbox.options[ddbox.selectedIndex].value == "lightgrey" ) {
		document.body.style.backgroundColor = "rgb(210, 210, 210)";
	} else if ( ddbox.options[ddbox.selectedIndex].value == "white" ) {
		document.body.style.backgroundColor = "rgb(255, 255, 255)";
	}
}
</script>
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
String loginmsg=(String)session.getAttribute("msg");
	 session.removeAttribute("msg"); 
if(loginmsg==null){

loginmsg = "";
}%>
<!-- header_top -->
<div class="top_bg">
<div class="container">
<div class="header_top">

	<div class="top_left">
	<!-- <select name="background" id="background" onchange="changeBackground(this)"> 
             <option selected="selected" value="white">White</option>
             <option value="silver">Silver</option>
             <option value="whitesmoke">White Smoke</option>
             <option value="aliceblue">Alice blue</option>
             <option value="wheat">Wheat</option>
             <option value="seashell">Seashell</option>
             <option value="oldlace">Old Lace</option>
             <option value="mintcream">Mint Cream</option>
             <option value="linen">Linen</option>
             <option value="lavanderblush">Lavander Blush</option>
             <option value="lightgrey">Light Grey</option>
		</select> -->
		<h2><%= loginmsg %> </h2>
 	</div> 
	<div class="top_right">
		<ul>
			<%if(tracking.isAdmin()) { %>
			<li><a href="./DisplayUserCat.jsp">User Reports</a></li>|
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
			<!-- <li><a href="file:///G%7C/shoppe-web/web/contact.html">Contact</a></li>| -->
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
						                	<label for="checkbox"><input id="checkbox" type="checkbox" name="remember-me" value="true"> <i>Remember me</i></label>
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
	<div class="header">
		<div class="logo">
			<a href="./home"><img src="./Emart_files/logo.png" /> </a>
		</div>
		<!-- start header_right -->
	  <div class="header_right">

		<ul class="icon1 sub-icon1 profile_img">
					<!-- sanyam -->
		<% if(tracking.isGuest()) {%>
			<li><a class="active-icon c2" href="./DisplayCart"> </a>
		<%} else{ %>
			<li><a class="active-icon c2" href="./DisplayCartDb"> </a>
		<%} %>
				<!-- <ul class="sub-icon1 list">
					<li><h3>shopping cart empty</h3><a href=""></a></li>
				</ul> -->
			</li>
		</ul>
		<ul class="icon1 sub-icon1 profile_img">
		<!-- sanyam -->
			<li><a class="active-icon c1" href="./DisplayWishlist"> </a>
				<!-- <ul class="sub-icon1 list">
					<li><h3>wishlist empty</h3><a href=""></a></li>
				</ul> -->
			</li>
		</ul>
		<!-- sanyam -->
		<div class="search">
		    <form action="./Search">
		    	<input placeholder="search..." type="text" name="box" required>
				<input value="" type="submit">
			</form>
		</div>
		<div class="clearfix"></div>
		</div>
		<!-- start header menu -->
	</div>
    <nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#defaultNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
      <a class="navbar-brand" href="./home">Home</a></div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="defaultNavbar1">
      <ul class="nav navbar-nav">
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Men<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="./SubCategoryWise?category=men&sub-category=ethnic_wear">Ethnic Wear</a></li>
            <li><a href="./SubCategoryWise?category=men&sub-category=western_wear">Western Wear</a></li>
            <li><a href="./SubCategoryWise?category=men&sub-category=footwear">Footwear</a></li>
            <li><a href="./SubCategoryWise?category=men&sub-category=bags_belts_and_wallets">Bags Belts & Wallets</a></li>
            <li><a href="./SubCategoryWise?category=men&sub-category=accessories">Accessories</a></li>
            <li class="divider"></li>
            <li><a href="./CategoryWise?category=men">View All</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Women<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="./SubCategoryWise?category=women&sub-category=ethnic_wear">Ethnic Wear</a></li>
            <li><a href="./SubCategoryWise?category=women&sub-category=western_wear">Western Wear</a></li>
            <li><a href="./SubCategoryWise?category=women&sub-category=footwear">Footwear</a></li>
            <li><a href="./SubCategoryWise?category=women&sub-category=bags_belts_and_wallets">Bags Belts & Wallets</a></li>
            <li><a href="./SubCategoryWise?category=women&sub-category=jewellery">Jewellery</a></li>
            <li class="divider"></li>
            <li><a href="./CategoryWise?category=women">View All</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Electronics<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="./SubCategoryWise?category=electronics&sub-category=laptops">Laptops</a></li>
            <li><a href="./SubCategoryWise?category=electronics&sub-category=mobile_phones">Mobile Phones</a></li>
            <li><a href="./SubCategoryWise?category=electronics&sub-category=tablets">Tablets</a></li>
            <li><a href="./SubCategoryWise?category=electronics&sub-category=printers">Printers</a></li>
            <li><a href="./SubCategoryWise?category=electronics&sub-category=mobile_accessories">Mobile Accessories</a></li>
            <li class="divider"></li>
            <li><a href="./CategoryWise?category=electronics">View All</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Home & Kitchen<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="./SubCategoryWise?category=home_and_kitchen&sub-category=cookware">Cookware</a></li>
            <li><a href="./SubCategoryWise?category=home_and_kitchen&sub-category=bed_and_living">Bed & Living</a></li>
            <li><a href="./SubCategoryWise?category=home_and_kitchen&sub-category=tools_and_hardware">Tools & Hardware</a></li>
            <li><a href="./SubCategoryWise?category=home_and_kitchen&sub-category=home_decor">Home Decor</a></li>
            <li class="divider"></li>
            <li><a href="./CategoryWise?category=home_and_kitchen">View All</a></li>
          </ul>
        </li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Books and Media<span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="./SubCategoryWise?category=books_and_media&sub-category=books">Books</a></li>
            <li><a href="./SubCategoryWise?category=books_and_media&sub-category=movies">Movies</a></li>
            <li><a href="./SubCategoryWise?category=books_and_media&sub-category=music">Music</a></li>
            <li><a href="./SubCategoryWise?category=books_and_media&sub-category=stationery">Stationery</a></li>
            <li class="divider"></li>
            <li><a href="./CategoryWise?category=books_and_media">View All</a></li>
          </ul>
        </li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
</div>

</div>