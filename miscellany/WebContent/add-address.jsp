<%@ include file="./includes/header.jsp" %>
<div class="container">
<div class="main">
<div style="width:600px; margin:auto">
<div class="contact">				
					<div class="contact_info">
				  <div class="contact-form">
			 	  	 	<h2>Add Address</h2>
			 	 	    <form action="./AddAddress" method="get">
					    	<div>
						    	<span><label>Name</label></span>
						    	<span><input name="name" class="textbox" required="" type="text"></span>
						    </div>
						    <div>
						    	<span><label>Pin-code</label></span>
						    	<span><input name="pincode" class="textbox" type="number" min="100000" max="999999"></span>
						    </div>
						    <div>
						     	<span><label>Phone</label></span>
						    	<span><input name="phone" class="textbox" type="number" min="7000000000" max="9999999999" required></span>
						    </div>
						    <div>
						    	<span><label>Address</label></span>
						    	<span><textarea name="address"> </textarea></span>
						    </div>
                            
						   <div>
						   		<span><input class="" value="Add" type="submit"></span>
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