<%@ include file="./includes/header.jsp" %>
<script type="text/javascript">

function showTextBox(ddbox) {

	 document.getElementById("bank-name").style.visibility = "hidden";
	 document.getElementById("card-select").style.visibility = "hidden";
	 
	 
	 if ( ddbox.options[ddbox.selectedIndex].value == "net-banking" ) {
      document.getElementById("bank-name").style.visibility = "visible";
   } else if ( ddbox.options[ddbox.selectedIndex].value == "credit-card" ) {
      document.getElementById("card-select").style.visibility = "visible";
   } else if ( ddbox.options[ddbox.selectedIndex].value == "debit-card" ) {
	   document.getElementById("card-select").style.visibility = "visible";
   }
}

</script>
<div class="container">
<div class="main">
<br /><br />
<div class="contact">
<div class="contact_info">
<div class="contact-form">	
<form action="./Checkout" method="post">
<div id="fixed-size-square-p">
<div id="payment-mode-box"><label>Choose Payment Mode</label></div><br />
<span><select name="payment-mode" onchange="showTextBox(this)"> 
      <option selected="selected" value="net-banking">Net Banking</option>
      <option value="credit-card">Credit Card</option>
      <option value="debit-card">Debit Card</option>
 	  <option value="cod">Cash On Delivery</option>
</select>
</span><div  style="visibility:hidden">
<div>
	<span><label>Brand</label></span>
	<span><input name="brand" style="display:hidden" class="textbox" type="text"></span>
</div>
<div>
	<span><label>Brand</label></span>
	<span><input name="brand" class="textbox" type="text"></span>
</div>
<div>
	<span><label>Brand</label></span>
	<span><input name="brand" class="textbox" type="text"></span>
</div>
<div>
	<span><label>Brand</label></span>
	<span><input name="brand" class="textbox" type="text"></span>
</div>
<div>
						   		<span><input class="" value="Add" type="submit"></span>
						  </div>
</div>
</div>

<div id="fixed-size-square-pd">
<div>
						   		<span><input class="" value="Pay" type="submit"></span>
						  </div><br />
<span>
<select name="bank-name" id="bank-name"> 
   <option value="citi">Citi Bank</option>
   <option value="icici">ICICI Bank</option>
   <option value="hdfc">HDFC Bank</option>
   <option value="kotak">Kotak Mahindra Bank</option>
   <option value="Axis">Axis Bank</option>
</select>
</span>
<div id="card-select" style="visibility:hidden">
<div>
	<span><label>Name on card</label></span>
	<span><input name="name" class="textbox" type="text"></span>
</div>
<div>
	<span><label>Card Number</label></span>
	<span><input name="number" class="textbox" min="4000000000000000" max="4999999999999999" type="number"></span>
</div>
<div>
	<span><label>Date of Expiry</label></span>
	<span><input name="brand" class="textbox" type="date"></span>
</div>
<div>
	<span><label>cvv</label></span>
	<span><input name="cvv" class="textbox" type="password" maxlength="3"></span>
</div>

</div>









</div>











</form>
</div></div></div>
</div>
</div>
<%@ include file="./includes/footer.jsp" %>