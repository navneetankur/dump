<%@ include file="./includes/header.jsp" %>
<%String msg = (String)request.getAttribute("msg"); %>
<div class="container">
<div class="main">
<div style="width:600px; margin:auto">
<div class="contact">				
					<div class="contact_info">
				  <div class="contact-form">
				  <%if(msg != null) out.println(msg); %>
			 	  	 	<h2>Enter new password</h2>
			 	 	    <form action="./ForgetPass" method="get">
					    	<div>
						    	<span><input name="npass" class="textbox" required="" type="password"></span>
						    </div>
						   <div>
						   		<span><input class="" value="change" type="submit"></span>
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