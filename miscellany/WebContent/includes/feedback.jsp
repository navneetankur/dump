
<%for(Feedback feedback : feedbackList) { %>	
	<div class="content_textt" >
				<h3><%=feedback.getName() %> Said:</h3>
				<h4><%=feedback.getTitle() %></h4>
				<p><%=feedback.getComment() %></p>
	</div>
<% } %>

<div class="contact">				
				  <div class="contact-formm">
			 	  	 	<h2>Comment:</h2>
			 	 	    <form method="get" action="./AddFeedback">
			 	 	    <input type="hidden" name="product-id" value="<%=product.getId() %>" />
			 	 	    <%if(tracking.isGuest()) { %>
					    	<div>
						    	<span><label>Name</label></span>
						    	<span><input name="name" type="text" class="textbox"></span>
						    </div>
						 <% } 
			 	 	    else {
						 %>
						 <input type="hidden" name="name" value="<%=tracking.getUserId() %>" />
						 <% } %>
						    <div>
						    	<span><label>Title</label></span>
						    	<span><input name="title" type="text" class="textbox" required></span>
						    </div>
						    <div>
						    	<span><label>Comment</label></span>
						    	<span><textarea name="comment" required> </textarea></span>
						    </div>
						   <div>
						   		<span><input type="submit" class="" value="Submit"></span>
						  </div>
					    </form>
				    </div>
  				<div class="clearfix"></div>		
			  </div>