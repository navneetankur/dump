 <script src="./Emart_files/rating/jRate.min.js"></script> 
Rate: <div id="jRate" style="display:inline-block"></div>
<div id="onset-value" style="display:inline-block"></div>
<script>
var rater = $("#jRate").jRate({
	rating: <%=product.getRatingMax5() %> ,
	precision: .5 ,
	onSet: function(rating) {
		$('#onset-value').text("Your rating : "+rating);
		$.post( "./Rate", { rating: rating * 2, id: "<%=product.getId() %>" } )
		  .done(function( data ) {
		    rater.setRating(data/2);
		    rater.setReadOnly(true);
		  });
	}
});
</script>