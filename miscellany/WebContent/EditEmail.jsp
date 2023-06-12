<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import = "com.database.*"%>
<%@ page import="java.sql.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function ValidateContactForm()
{
   
	var email = document.ContactForm.user_email;
	var mail = /\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
    
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
        window.alert("Please enter a Valid e-mail address.");
        email.focus();
        return false;
    }
    if (!email.value.match(mail))
    {	
        window.alert("Please enter a Valid e-mail address.");
        email.focus();
        return false;
    }
	
    return true;
}

</script>
</head>
<body>
<h4 align="right"><a href="Logout.jsp">Home</a><br></h4>
<table border="2">
<tr>
<td><b>Current Email_id</b></td>
</tr>
<%
session = request.getSession();
session.getAttribute("username");
String userId = (String) session.getAttribute("username");
	%>
<%try{
DBConnect db=new DBConnect();
ResultSet rst = db.getEmail(userId);
while(rst.next()){
%>
<tr>
<td><%=rst.getString("user_email") %></td>
</tr>
<%} %>
</table>
<%
db.close();
}catch(Exception e){
	e.printStackTrace();
}
%>
<form action=./EditEmail name="ContactForm" onsubmit="return ValidateContactForm();">
Enter New Email ID: 
<p><input type="text" name="user_email" maxlength="25"></p>
<p><input type="submit" value="Change Email Address"></p>
</form>

</body>
</html>
