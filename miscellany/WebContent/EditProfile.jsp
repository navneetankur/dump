<%@page import="com.DAO.UserBean"%>
<%@page import="com.glecom.products.models.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./includes/header.jsp" %>
<script type="text/javascript">
function ValidateContactForm()
{
    var name = document.ContactForm.user_name;
	var email = document.ContactForm.user_email;
    var phone = document.ContactForm.user_contact;
	
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
<script type="text/javascript">
	function showButton(cb) {
			document.getElementsByName("butn")[0].style.display = "inline";
		
		
	}
</script>

<% ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("users"); %>
<% if(list!=null){ %>
<% UserBean user=list.get(0); %>
<div class="container">
<div class="main">

<div class="shoping_bag">
<%= user.getUserName() %> change your details here:
</div>

<div class="shoping_bag2">
<form action="./UpdateUserProfile"  name="ContactForm" onsubmit="return ValidateContactForm();" method="post">
<p align="center">&nbsp &nbsp &nbsp Name:<input type = "text" name="user_name" value="<%=user.getUserName() %>" maxlength="32" onclick="showButton(this)"><br><br>
&nbsp &nbsp &nbsp Email:<input type="text" name="user_email" value=<%= user.geteMail()%> maxlength="50" onclick="showButton(this)"><br><br>
&nbsp &nbsp &nbsp &nbsp Dob:<input type="text" id="popupDatepicker" name="bday" value=<%= user.getDob() %> onclick="showButton(this)"><br><br>
Contact:<input type="text" name="user_contact" value=<%= user.getContact()%> maxlength = "10" onclick="showButton(this)"><br><br>
<%if(user.getSex().equals("male")){ %>
<input type="radio" name="sex" onclick="showButton(this)" value="male" checked> Male
&nbsp 
<input type="radio" name="sex" value="female" onclick="showButton(this)"> Female
<%}else{ %>
<input type="radio" name="sex" onclick="showButton(this)" value="male"> Male
&nbsp 
<input type="radio" name="sex"  onclick="showButton(this)" value="female" checked> Female
<%} %>
</P>
<p><input type="submit" name ="butn" value="SAVE" style="display:none"></p>
</form>
</div>
</div>
</div>
<%}else{%>
<%} %>
<%@ include file="./includes/footer.jsp" %>