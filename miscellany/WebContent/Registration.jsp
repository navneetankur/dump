<!DOCTYPE html>
<%@page import="net.tanesha.recaptcha.ReCaptchaFactory"%>
<%@page import="net.tanesha.recaptcha.ReCaptcha"%>
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






<style>
body{
color:#1C1C1C;
font-size:16px;
font-family:'Open Sans', Arial, Verdana;
line-height:1.5em;
}
h1{font-size:1em;}
h1,p{margin-bottom:10px;}
#registration{
/* background-color:grey;
 */
 border:1px solid black;
 margin:50px auto;
width:350px;}

form fieldset input[type="text"], input[type="password"]{
background-color: #1C1C1C;
border: none;
border-radius: 3px;
-moz-border-radius: 3px;
-webkit-border-radius: 3px;
color: white;
font-family: 'Open Sans', Arial, Helvetica, sans-serif;
font-size: 14px;
height: 50px;
outline: none;
padding: 0px 10px;
width: 280px;
-webkit-appearance:none;
}
form fieldset input[type="submit"] {
background-color: green;
border: none;
border-radius: 3px;
-moz-border-radius: 3px;
-webkit-border-radius: 3px;
color: #f4f4f4;
cursor: pointer;
font-family: 'Open Sans', Arial, Helvetica, sans-serif;
height: 50px;
text-transform: uppercase;
width: 300px;
-webkit-appearance:none;
}
</style>
<script type="text/javascript">
function ValidateContactForm()
{
    var name = document.ContactForm.user_name;
	var email = document.ContactForm.user_email;
    var phone = document.ContactForm.user_contact;
	var userid = document.ContactForm.userId;
	var pass = document.ContactForm.password;
	var letter = /^[a-zA-Z ]+$/;
	var mail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    if (name.value == "")
    {
        window.alert("Please enter your name.");
        name.focus();
        return false;
    }
	if (!name.value.match(letter))
	{	
        window.alert("Please enter Valid Name");
        name.focus();
        return false;
    }
	
	if (userid.value == "")
    {
        window.alert("Please enter your User_ID.");
        userid.focus();
        return false;
    }
	if(userid.value.length <= 5 || userid.value.length >= 15)
    {
        window.alert("Please enter userid between 6-15 characters.");
        userid.focus();
        return false;
    }
    
    if (email.value == "")
    {
        window.alert("Please enter a valid e-mail address.");
        email.focus();
        return false;
    }
    if (email.value.indexOf("@", 0) < 0)
    {
        window.alert("Please enter a valid e-mail address.");
        email.focus();
        return false;
    }
    if (email.value.indexOf(".", 0) < 0)
    {
        window.alert("Please enter a valid e-mail address.");
        email.focus();
        return false;
    }
    if (!email.value.match(mail))
	{	
        window.alert("Please enter Valid Email");
        email.focus();
        return false;
    }
	if (pass.value == "")
    {
        window.alert("Please enter your Password.");
        pass.focus();
        return false;
    }
	if(pass.value.length <= 5 || pass.value.length >= 20)
    {
        window.alert("Please enter Password between 6-20 character.");
        pass.focus();
        return false;
    }
	    
    if (phone.value == "")
    {
        window.alert("Please enter your Contact number.");
        phone.focus();
        return false;
		
    }  
      
       	 if(isNaN(phone.value)|| phone.value.indexOf(" ")!=-1)
	{
              			window.alert("Enter numeric value");
						phone.focus();
			return false;
                }
       			 if (phone.value.length != 10)
			{
                			window.alert("Enter Valid 10-digits Contact");
                            phone.focus();							
				return false;
          			 }
 
    return true;
}

</script>
<link href="./Emart_files/er/jquery.datepick.css" rel="stylesheet">
<script src="./Emart_files/er/jquery-1.js"></script>
<script src="./Emart_files/er/jquery.plugin.js"></script>
<script src="./Emart_files/er/jquery.datepick.js"></script>
<script>
$(function() {
	$('#popupDatepicker').datepick();
	$('#inlineDatepicker').datepick({onSelect: showDate});
});

function showDate(date) {
	alert('The date chosen is ' + date);
}
</script>
</head>
<body>
<div id="registration">
<center>
<form action =./U_AddServlet name="ContactForm" onsubmit="return ValidateContactForm();" method="post">
<fieldset><br>
<h1>New Customer</h1>
<h1>Sign Up Here:</h1>
<%
HttpSession ses = request.getSession(true);
if(ses.getAttribute("msg1")!=null)
	out.println((String)ses.getAttribute("msg1"));
%>
<br><input type = "text" name="user_name" placeholder="Name" maxlength="32"><br><br>
<p><input type ="text" name="userId" placeholder="User_ID" maxlength="15"></p> <br>
<p><input type="text" name="user_email" placeholder="example@gmail.com" maxlength="50"></p><br>

<p><input type="password" name="password" placeholder="password" id="password" maxlength="20"></p><br>
<p><input type="text" name="user_contact" placeholder="Contact" maxlength = "10"></p><br>
<%@ include file="./includes/date.jsp" %>
<input type="radio" name="sex" value="male" checked> Male
&nbsp 
<input type="radio" name="sex" value="female"> Female
<input type="hidden" name="checkClaptcha" value="enabled" />

<%-- <%
      ReCaptcha c = ReCaptchaFactory.newReCaptcha("recaptcha-something-here", "recaptcha-key-here", false);
      out.print(c.createRecaptchaHtml(null, null));
   %> --%>

</form> 
<p><input type="submit" value="GET STARTED"></p>
<h3><a href="./Login.html"> Already Registered? Login</a></h3><br>
</fieldset>
</form>
</center>
</div>
<%@ include file="./includes/footer.jsp" %>
