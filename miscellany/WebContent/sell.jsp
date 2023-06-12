<%@ include file="./includes/header.jsp" %>
<script type="text/javascript">
	function showButton(cb) {
		if(cb.checked == true){
			document.getElementsByName("butn")[0].style.display = "inline";
		}
		else {
			document.getElementsByName("butn")[0].style.display = "none";
		}
	}
</script>
<meta charset="UTF-8">
<div class="container">
<div class="main">
	<div class="shoping_bag">
	
		<b><i><font size="5">The Seller Hub</font></i></b>
		<div class="clearfix"></div>
	</div>
	<div class="shoping_bag1">
	
	
	
		<b><i><font >Become a seller at Miscellany<br></font></i></b>
		<h4>Read our terms and conditions:</h4>
		
	
	<br>
	
		<div  style="height:250px;width:1000px;border:1px solid #ccc;font:16px/26px Georgia, Garamond, Serif;overflow:auto;">

<%@ include file="./includes/terms.txt" %>

</div>
<input type="checkbox" name="show-submit" onchange="showButton(this)" /> I agree
		<div class="clearfix"></div>
	</div>

	
	<div class="shoping_bag2">
		<div class="shoping_left" align="right">
			<a class="btn1" name="butn" href="./VendorFunc" style="display:none">Register</a>
		</div>

		<div class="clearfix"></div>
	</div>

</div>
</div>

<%@ include file="./includes/footer.jsp" %>