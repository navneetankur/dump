<%@ include file="./includes/header.jsp" %>
<div class="container">
<div class="main">
<%String msg = (String)request.getAttribute("msg");
if(msg!=null)
	out.print("<h2>"+msg+"</h2>");
%>




















</div>
</div>
<%@ include file="./includes/footer.jsp" %>