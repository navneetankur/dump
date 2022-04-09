package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.FetchData;
import com.glecom.products.models.Product;
/**
 * @author Sanyam tyagi
 * 
 */
@WebServlet("/ApplyFilters")
public class ApplyFilters extends HttpServlet {
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Enumeration en = request.getParameterNames();
		String[] filterList= new String[20];
		int i=0;
		while (en.hasMoreElements()) {
			filterList[i]=String.valueOf(en.nextElement());
			i++;
		}
		//showing result using list
		/*Product p=new Product();
		ArrayList<Product> list = FetchData.fetchData();
		for (int j = 0; j < i; j++) {
			for (int k = (list.size()-1); k >= 0; k--) {
				p = list.get(k);
				if(request.getParameter(filterList[j]).equalsIgnoreCase(p.getName())){
				
				}
				else{
					list.remove(k);
					}
				
			}
		}
		
		for (int j = 0; j <= (list.size()-1); j++) {
			p = list.get(j);
			out.print(p.getName());
		}*/
	//	Product p=new Product();
		StringBuffer query = new StringBuffer();
		query.append("select * from productc");
		int j=1;
		boolean whereCounter=true,brandCounter=false,priceCounter=false,categoryCounter=false,discountCounter=false;
		while(filterList[j]!=null){
			if(whereCounter){
				query.append(" where (");
				whereCounter=false;;
			}
			 if(filterList[j].contains("category")){
					if(categoryCounter){
						query.append("OR");
					}
//					else if(brandCounter){
//						query.append(" ) AND (");
//						categoryCounter=true;
//					}
//					else if(priceCounter){
//						query.append(" ) AND (");
//						categoryCounter=true;
//					}
					
					query.append("( category= '"+request.getParameter(filterList[j])+"' )");
					categoryCounter=true;
				}
			 else if(filterList[j].contains("brand")){
				if(brandCounter){
					query.append("OR");
				}
				else if(categoryCounter){
					query.append(" ) AND (");
					brandCounter=true;
				}
				else{
					brandCounter=true;
				}
				query.append("( brand= '"+request.getParameter(filterList[j])+"' )");
				//brandCounter=true;
			}
			else if(filterList[j].contains("price")){
				if(priceCounter){
					query.append("OR");
				}
				else if(categoryCounter){
					query.append(" ) AND (");
					priceCounter=true;
				}
				else if(brandCounter){
					query.append(" ) AND (");
					priceCounter=true;
				}
				else{
					priceCounter=true;
				}
				query.append("( price "+request.getParameter(filterList[j])+" )");
			}
			else if(filterList[j].contains("discount")){
				if(discountCounter){
					query.append("OR");
				}
				else if(categoryCounter){
					query.append(" ) AND (");
					discountCounter=true;
				}
				else if(brandCounter){
					query.append(" ) AND (");
					discountCounter=true;
				}
				else if(priceCounter){
					query.append(" ) AND (");
					discountCounter=true;
				}
				else{
					discountCounter=true;
				}
				query.append("( discount "+request.getParameter(filterList[j])+" )");
			}
			
			j++;
		}
		
		if(whereCounter){	
		}
		else{
			query.append(" )");
		}
	
		ArrayList<Product> list = FetchData.fetchData(String.valueOf(query));
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("./product-list.jsp");
		request.setAttribute("products", list);
		requestDispatcher.forward(request, response);
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
