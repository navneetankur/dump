<%@ include file="./includes/header.jsp" %>
<%String msg = (String)request.getAttribute("msg"); %>
<div class="container">
<div class="main">
<div style="width:600px; margin:auto">
<div class="contact">				
					<div class="contact_info">
				  <div class="contact-form">
				  <%if(msg != null) out.println(msg); %>
			 	  	 	<h2>Enter your email address</h2>
			 	 	    <form action="./ForgotPassword" method="get">
					    	<div>
						    	<span><input name="email" class="textbox" required="" type="email"></span>
						    </div>
						   <div>
						   		<span><input class="" value="Submit" type="submit"></span>
						  </div>
					    </form>
				    </div>
  				<div class="clearfix"></div>	
			  </div>
</div>
</div>










</div>
</div>
<%@ include file="./includes/footer.jsp" %>