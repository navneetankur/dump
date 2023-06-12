<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function ValidateContactForm()
{
	var pass = document.ContactForm.npass;
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
	    
    
    return true;
}

</script>
</head>
<body>
<form action=./EditPass name="ContactForm" onsubmit="return ValidateContactForm();">
<input type = "password" name="password" placeholder = "Enter Old Password" maxlength="20"><br>
<input type ="password" name="npass" placeholder = "Enter New Password" maxlength="20"><br>
<input type = "submit" value="Submit"><br>
</form>
</body>
</html>