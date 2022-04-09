<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<div class="w_sidebar">
			<div class="w_nav1">
		
			<ul>
				<li></li>
			</ul>	
		</div>
		<form action="./ApplyFilters">
		<input type="hidden" name = "table" value ="pr">
		<h3>filter by</h3>
		<input type="submit" value="Apply filters" style="width:100%">
		<section  class="sky-form">
		
					<h4>categories</h4>
						<div class="row1 scroll-pane">
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="category_f1" value ="men"><i></i>men</label>
							</div>
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="category_f2" value ="women"><i></i>women</label>
								<label class="checkbox"><input type="checkbox" name="category_f3" value ="electronics"><i></i>electronics</label>
								<label class="checkbox"><input type="checkbox" name="category_f4" value ="home_and_kitchen"><i></i>home and kitchen</label>
								<label class="checkbox"><input type="checkbox" name="category_f5" value ="books_and_media"><i></i>books and media</label>
							
							</div>
						</div>
		</section>
		<section  class="sky-form">
					<h4>brand</h4>
						<div class="row1 scroll-pane">
							<div class="col col-4">
								
								<label class="checkbox"><input type="checkbox" name="brand_f1" value ="Abhishti"><i></i>Abhishti</label>
							</div>
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="brand_f2" value ="Acer"><i></i>Acer</label>
								<label class="checkbox"><input type="checkbox" name="brand_f3" value ="Apple"><i></i>Apple</label>
								<label class="checkbox"><input type="checkbox" name="brand_f4" value ="Aquire"><i></i>Aquire</label>
								<label class="checkbox"><input type="checkbox" name="brand_f5" value ="Asus"><i></i>Asus</label>
								<label class="checkbox"><input type="checkbox" name="brand_f6" value ="Bigfivedeals"><i></i>Bigfivedeals</label>								<label class="checkbox"><input type="checkbox" name="brand_f7"  value ="Bosch"><i></i>Bosch</label>
								<label class="checkbox"><input type="checkbox" name="brand_f8" value ="Lenovo"><i></i>Lenovo</label>
								<label class="checkbox"><input type="checkbox" name="brand_f9" value ="Mi"><i></i>Mi</label>
								<label class="checkbox"><input type="checkbox" name="brand_f10" value ="PUMA"><i></i>PUMA</label>
								<label class="checkbox"><input type="checkbox" name="brand_f11" value ="Samsung"><i></i>Samsung</label>
								<label class="checkbox"><input type="checkbox" name="brand_f12" value ="HP"><i></i>HP</label>																								
							</div>
						</div>
		</section>
		
		<section class="sky-form">
						<h4>price</h4>
						<div class="row1 scroll-pane" style="height:auto">
							<div class="col col-4">
									<label class="checkbox"><input type="checkbox" name ="price_f1" value ="<=499"><i></i>Below 500</label>
									
			
									<label class="checkbox"><input type="checkbox" name ="price_f2" value ="between 500 and 999"><i></i>500-999</label>
									<label class="checkbox"><input type="checkbox" name ="price_f3" value ="> 999"><i></i>Above 1000</label> 
							</div>
						</div>						
		</section>
		<section class="sky-form">
						<h4>discount</h4>
						<div class="row1 scroll-pane" style="height:auto">
							<div class="col col-4">
							
								<label class="checkbox"><input type="checkbox" name="discount_f2" value=">50"><i></i>40 % and above</label>
							</div>
							<div class="col col-4">
								<label class="checkbox"><input type="checkbox" name="discount_f3" value="between 30 and 40"><i></i>30 % to 40%</label>
								<label class="checkbox"><input type="checkbox" name="discount_f4" value="between 20 and 30"><i></i>20 % to 30%</label>
								<label class="checkbox"><input type="checkbox" name="discount_f5" value="between 10 and 20"><i></i>10 % to 20%</label>
							</div>
						</div>						
		</section>
		
		<input type="submit" value="Apply filters" style="width:100%">
		</form>
	</div>
<%ArrayList<String> filterList = new ArrayList<String>(); %>
<%Enumeration<String> filters = request.getParameterNames();
while (filters.hasMoreElements()) {
	filterList.add(filters.nextElement());
}
filterList.remove("table");
%>
<script type="text/javascript">
	<%for(String filter : filterList) { %>
		document.getElementsByName("<%=filter%>")[0].checked = true;
	<% } %>
</script>