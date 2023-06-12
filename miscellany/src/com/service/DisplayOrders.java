package com.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.glecom.products.models.Product;
import com.glecom.products.models.dao.ProductDao;
/**
 * @author Sanyam tyagi
 * 
 */
/**
 * Servlet implementation class DisplayOrders
 */
@WebServlet("/DisplayOrders")
public class DisplayOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(true);
		String userId=(String)ses.getAttribute("userId");
		if(userId!=null){
/*			 ArrayList<Product> prod=new ArrayList<>();
*/			 ArrayList<Product> list=new ArrayList<>();
			 String orderId="";
			 ProductDao pdo=new ProductDao();
			 String string = FetchData.fetchProducts(userId);
			 if(string.equals("")||string==null){
			        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayOrder.jsp");
					request.setAttribute("products", list);
					request.setAttribute("orders", orderId);

					requestDispatcher.forward(request, response);
					return;
			}
			 String []s=string.split("/");
			 if(s[1]!=null){
				  orderId=s[1];
			 }
			 
			 
/*			    String query = new String();
*/				String []str=(String.valueOf(s[0])).split(",");
		        int i=0;
				for (i = 0; i < str.length; i++) {
					if(!str[i].equals("")){
						Product prod=null;
						try {
							prod = pdo.getProduct(Integer.parseInt(str[i]));
						} catch (NumberFormatException | SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						list.add(prod);
					}
					
				}	
				 /*String []str=(String.valueOf(string)).split(",");
			        int i=0;
					if(str.length>1){
			        	for (i = 0; i < str.length-1; i++) {
			        		if(!str[i].equals("")){
			        			query.append("(id="+str[i]+")or");
			        		}
			        		
			    		}
			       
			        }
			        
			        if(!str[i].equals("")){
		    			query.append("(id="+str[i]+")");
		    		}
			        if(String.valueOf(query).equalsIgnoreCase("select * from productc where ")){
						 RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayOrder.jsp");
							request.setAttribute("products", prod);
							requestDispatcher.forward(request, response);
							return;
					}
					prod=FetchData.fetchData(String.valueOf(query));*/		
				        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayOrder.jsp");
						request.setAttribute("products", list);
						request.setAttribute("orders", orderId);
						requestDispatcher.forward(request, response);
		}
		else{
			String goBack=(String)ses.getAttribute("goBack");
			if(goBack==null)goBack="./home";
			RequestDispatcher rd=request.getRequestDispatcher(goBack);
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
