<%@ include file="./includes/header.jsp" %>

<style type = "text/css">
body {
color: #1C1C1C;
font-family: 'Open Sans', Arial, Helvetica, Verdana;
font-size: 16px;
line-height: 1.5em;
}
h1{font-size:2em;}
h1,p{margin-bottom:10px;}

#login{
border:1px solid black;
margin: 50px auto;
width: 300px;
}

form fieldset input[type="text"], input[type="password"] {
background-color: #e5e5e5;
border: none;
border-radius: 3px;
-moz-border-radius: 3px;
-webkit-border-radius: 3px;
color: black;
font-family: Arial;
font-size: 14px;
height: 50px;
outline: none;
padding: 0px 10px;
width: 250px;
-webkit-appearance:none;
margin:auto;

}

form fieldset input[type="submit"] {
background-color: blue;
border: none;
border-radius: 3px;
-moz-border-radius: 3px;
-webkit-border-radius: 3px;
color: #f4f4f4;
cursor: pointer;
font-family: 'Open Sans', Arial, Helvetica, sans-serif;
height: 50px;
text-transform: uppercase;
width: 270px;
-webkit-appearance:none;
}
</style>
<script type="text/javascript">
function ValidateForm()
{
	var userid = document.Form.userId;
	var pass = document.Form.password;
    if (userid.value == "")
    {
        window.alert("Please enter your User_ID.");
        userid.focus();
        return false;
    }
    
    
	if (pass.value == "")
    {
        window.alert("Please enter your Password.");
        pass.focus();
        return false;
    }

    
    return true;
}
</script>
 <title>Step In for Everything</title>
</head>
<body>
<div id = "login">
<center>
<form action=./InServlet name="Form" onsubmit="return ValidateForm();">
<fieldset>	
<center><h1> Welcome!!</h1></center>
<center><h1>Login Yourself</h1></center>
<%
HttpSession ses = request.getSession(true);
if(ses.getAttribute("msg")!=null)
	out.println((String)ses.getAttribute("msg"));
%>

<p><input type ="text" name="userId" placeholder="User_ID" id="userId"></p><br>
<input type ="password" name="password" placeholder="Password" id="password"><br><br>
<p><input type="submit" value="Login"></p>
<p><i></i><a href="./Registration.jsp">Not Registered Yet? Sign Up Here</a></i></p>
</fieldset>
</form>
</center>
</div>
<%@ include file="./includes/footer.jsp" %>