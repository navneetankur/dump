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
    var phone = document.ContactForm.user_contact;
    
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
</head>
<body>
<h4 align="right"><a href="Logout.jsp">Home</a><br></h4>
<table border="2">
<tr>
<td><b>Current Contact</b></td>
</tr>
<%
session = request.getSession();
session.getAttribute("username");
String userId = (String) session.getAttribute("username");

	%>
<%try{
DBConnect db=new DBConnect();
ResultSet rst = db.getUser(userId);
while(rst.next()){
%>
<tr>
<td><%=rst.getString("user_contact") %></td>
</tr>
<%} %>
</table>
<%
db.close();
}catch(Exception e){
	e.printStackTrace();
}
%>
<form action=./EditCnt name="ContactForm" onsubmit="return ValidateContactForm();">
Enter New Contact: 
<p><input type="text" name="user_contact" maxlength = "10"></p>
<p><input type="submit" value="Change Mobile Number"></p>
</form>

</body>
</html>
