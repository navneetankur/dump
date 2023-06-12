<%@ include file="./includes/header.jsp" %>
<script type="text/javascript">

function showTextBox(ddbox) {

	 document.getElementById("men-subcategory").style.display = "none";
	 document.getElementById("women-subcategory").style.display = "none";
	 document.getElementById("electronics-subcategory").style.display = "none";
	 document.getElementById("home-and-kitchen-subcategory").style.display = "none";
	 document.getElementById("books-and-media-subcategory").style.display = "none";
	 document.getElementById("men-subcategory").setAttribute('name', 'n1');
	 document.getElementById("women-subcategory").setAttribute('name', 'n2');
	 document.getElementById("electronics-subcategory").setAttribute('name', 'n3');
	 document.getElementById("home-and-kitchen-subcategory").setAttribute('name', 'n4');
	 document.getElementById("books-and-media-subcategory").setAttribute('name', 'n5');
	 
	 
	 if ( ddbox.options[ddbox.selectedIndex].value == "men" ) {
      document.getElementById("men-subcategory").style.display = "block";
			document.getElementById("men-subcategory").setAttribute('name', 'sub-category');
   } else if ( ddbox.options[ddbox.selectedIndex].value == "women" ) {
      document.getElementById("women-subcategory").style.display = "block";
			document.getElementById("women-subcategory").setAttribute('name', 'sub-category');
   } else if ( ddbox.options[ddbox.selectedIndex].value == "electronics" ) {
      document.getElementById("electronics-subcategory").style.display = "block";
			document.getElementById("electronics-subcategory").setAttribute('name', 'sub-category');
   } else if ( ddbox.options[ddbox.selectedIndex].value == "home_and_kitchen" ) {
      document.getElementById("home-and-kitchen-subcategory").style.display = "block";
			document.getElementById("home-and-kitchen-subcategory").setAttribute('name', 'sub-category');
   } else if ( ddbox.options[ddbox.selectedIndex].value == "books_and_media" ) {
      document.getElementById("books-and-media-subcategory").style.display = "block";
			document.getElementById("books-and-media-subcategory").setAttribute('name', 'sub-category');
   }
}

</script>
<div class="container">
<div class="main">
<div class="contact">				
					<div class="contact_info">
				  <div class="contact-form">
			 	  	 	<h2>Add Product</h2>
			 	 	    <form action="./AddProduct" method="post" enctype="multipart/form-data">
			 	 	    	<input type="hidden" name="vendor" value="<%=tracking.getUserId() %>" />
					    	<div>
						    	<span><label>Name</label></span>
						    	<span><input name="name" class="textbox" required="" type="text"></span>
						    </div>
						    <div>
						    	<span><label>Brand</label></span>
						    	<span><input name="brand" class="textbox" type="text"></span>
						    </div>
                            <div>
						    	<span><label>Price</label></span>
						    	<span><input name="price" class="textbox" required="" type="number"></span>
						    </div>
						    <div>
						     	<span><label>Size</label></span>
						    	<span><input name="size" class="textbox" type="number"></span>
						    </div>
						    <div>
						     	<span><label>Discount</label></span>
						    	<span><input name="discount" class="textbox" type="number" required></span>
						    </div>
						    <div>
						    	<span><label>Description</label></span>
						    	<span><textarea name="description"> </textarea></span>
						    </div>
                            <div>
						     	<span><label>Color</label></span>
						    	<span><input value="#000000" name="color" class="textbox" type="color"></span>
						    </div>
						    <div>
						     	<span><label>Quantity</label></span>
						    	<span><input value="10" name="quantity" class="textbox" type="number"></span>
						    </div>
                            <div>
						     	<span><label>Category</label></span>
						    	<span><select name="category" onchange="showTextBox(this)"> 
                                            <option selected="selected" value="men">Men</option>
                                            <option value="women">Women</option>
                                            <option value="electronics">Electronics</option>
                                            <option value="home_and_kitchen">Home and Kitchen</option>
											<option value="books_and_media">Books and Media</option>
                   					 </select>
                                 </span>
						    </div>
                            <div>
						     	<span><label>Sub Category</label></span>
						    	<span>
                                	<select name="sub-category" id="men-subcategory"> 
                                        <option selected="selected" value="ethnic_wear">Ethnkic Wear</option>
                                        <option value="western_wear">Western Wear</option>
                                        <option value="footwear">Footwear</option>
                                        <option value="bags_belts_and_wallets">Bags belts and wallets</option>
                                        <option value="accessories">Accessories</option>
									</select>
									<select name="n2" id="women-subcategory" style="display:none"> 
                                        <option selected="selected" value="ethnic_wear">Ethnkic Wear</option>
                                        <option value="western_wear">Western Wear</option>
                                        <option value="footwear">Footwear</option>
                                        <option value="bags_belts_and_wallets">Bags belts and wallets</option>
                                        <option value="jewellery">Jewellery</option>
									</select>
									<select name="n3" id="electronics-subcategory" style="display:none"> 
                                        <option selected="selected" value="laptops">Laptops</option>
                                        <option value="mobile_phones">Mobile Phones</option>
                                        <option value="tablets">Tablets</option>
                                        <option value="printers">Printers</option>
                                        <option value="mobile_accessories">Mobile Accessories</option>
									</select>
									<select name="n4" id="home-and-kitchen-subcategory" style="display:none"> 
                                        <option selected="selected" value="cookware">cookware</option>
                                        <option value="bed_and_living">bed and living</option>
                                        <option value="tools_and_hardware">tools and hardware</option>
                                        <option value="home_decor">home decor</option>
									</select>
									<select name="sub-category" id="books-and-media-subcategory" style="display:none"> 
                                        <option selected="selected" value="watch">books</option>
                                        <option value="music">music</option>
                                        <option value="movies">movies</option>
                                        <option value="stationery">stationery</option>
									</select>
                                </span>
						    </div>
						    <div>
						    <span>Image:<br />
							<input type="file" name="file" /></span>
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
<%@ include file="./includes/footer.jsp" %>