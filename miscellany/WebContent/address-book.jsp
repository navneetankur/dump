<%@page import="com.glecom.products.models.Address"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./includes/header.jsp" %>
<div class="container">
<div class="main">
<div class="delivery-address-box">Delivery Address</div>
<br />
<%
if(tracking.isGuest()) {
	response.sendRedirect("./home");
	return;
}
ArrayList<Address> addressList = (ArrayList<Address>)request.getAttribute("addressList");
if(addressList == null) {
	response.sendRedirect("./home");
	return;
}
if(addressList.size()==0) {
	response.sendRedirect("./AddAddress");
	return;
}
for(Address address : addressList) {
%>
<div class="fixed-size-square">
<span>
<%=address.getName() %><br />
<%=address.getAddress() %><br />
Pin-code: <%=address.getPincode() %><br />
Phone: <%=address.getPhone() %>
</span>
<form action="./ForwardAddress">
<input type="hidden" name="address-id" value="<%=address.getId() %>" />
<input type="submit" value="Deliver Here" style="width:100%"/>
</form>
</div>
<% } %>

<br />

<div class="btn_form">
<a href="./AddAddress" style="width:100%">Add Address</a>
</div>




</div>
</div>
<%@ include file="./includes/footer.jsp" %>