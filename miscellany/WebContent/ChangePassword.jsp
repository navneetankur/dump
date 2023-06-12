<%@page import="com.glecom.products.models.Order"%>
<%@page import="com.glecom.products.models.Address"%>
<%@page import="com.glecom.products.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./includes/header.jsp" %>
<script type="text/javascript">
function ValidateContactForm()
{
	var pass = document.ContactForm.npass;
	var con=document.ContactForm.ncpass;
	if (pass.value == "")
    {
        window.alert("Please enter your Password.");
        pass.focus();
        return false;
    }
	if(pass.value.length <= 5 || pass.value.length >= 20)
    {
        window.alert("Please enter between 6-20 character.");
        pass.focus();
        return false;
    }
	if(!pass.value.match(con.value)){
		window.alert("New password and Confirm password do not match.")
		pass.focus();
		con.focus();
		return false;
	} 
	
    return true;
}

</script>

<div class="container">
<div class="main">
		<div class="shoping_bag2">
			<% if(request.getAttribute("msg")!=null){
			
				if((request.getAttribute("msg")).equals("wrong")){ %>
				<p align="center">Wrong Password.Try again.</p>
			<%}} %>
		    <form action=./EditPass name="ContactForm" onsubmit="return ValidateContactForm();">
			<p align="center">
			<input type = "password" name="password" placeholder = "Enter Old Password" maxlength="20"><br><br>
			<input type ="password" name="npass" placeholder = "Enter New Password" maxlength="20"><br><br>
			<input type ="password" name="ncpass" placeholder = "Confirm New Password" maxlength="20"><br><br>
			<input type = "submit" value="Submit"><br></p>
			</form>
	    </div>
</div>
</div>
<%@ include file="./includes/footer.jsp" %>