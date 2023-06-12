<%@ include file="./includes/HeaderAdmin.jsp" %>
<% long id=541*Integer.parseInt(request.getParameter("orderId")); %>
<div class="container">
<div class="main">
	<div class="shoping_bag">
		Tracking Id: TM<%= id %>
		<div class="clearfix"></div>
		Status: In transit.
	</div>
	<br><br>
<div class="fixed-size-square">
<span>
In transit
</span>
</div>
<div class="fixed-size-square">
<span>
Being Shipped
</span>
</div>

<div class="fixed-size-square">
<span>
Out for delivery
</span>
</div>
<div class="fixed-size-square">
<span>
Delivered
</span>
</div>
</div>
</div>

<%@ include file="./includes/footer.jsp" %>