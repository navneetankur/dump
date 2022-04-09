package com.service;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.FetchData;
import com.DAO.UserBean;
import com.glecom.products.models.Product;
/**
 * @author Sanyam tyagi
 * 
 */
/**
 * Servlet implementation class viewOrders
 */
@WebServlet("/ViewOrders")
public class ViewOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 int countUser=Integer.valueOf(String.valueOf(request.getAttribute("countUser")));
			request.setAttribute("countUser", countUser);

		 int countUserCat=0;
		 StringBuffer products= new StringBuffer();
		 products.append("");
		 String string = new String();
		 ArrayList<UserBean> list = (ArrayList<UserBean>)request.getAttribute("users");
		 ArrayList<Product> prod=new ArrayList<>();
		 request.setAttribute("countUserCat", countUserCat);
		 if(list.size()>0||list==null){
		 for (UserBean userBean : list) {
			 countUserCat++;
			 string = new String();
			 string=FetchData.fetchProducts(userBean.getUserId());
			 if(string!=null&& !string.equals("/")){
			 products.append(string);
			 }
		 }
			request.setAttribute("countUserCat", countUserCat);
			
			

			if(products.equals("")){
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayUser.jsp");
				request.setAttribute("products", prod);
				request.setAttribute("users", list);
				requestDispatcher.forward(request, response);
				return;
			}
			String []str1=(String.valueOf(products)).split("/");
			StringBuffer query = new StringBuffer();
			query.append("select * from productc where ");
			
			
			 
			String []str=str1[0].split(",");
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
				 RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayUser.jsp");
					request.setAttribute("products", prod);
					request.setAttribute("users", list);
					requestDispatcher.forward(request, response);
					return;
			}
			System.out.println("view"+query);
			prod=FetchData.fetchData(String.valueOf(query));		
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayUser.jsp");
		        
				request.setAttribute("products", prod);
				request.setAttribute("users", list);
				requestDispatcher.forward(request, response);
			
		}
		 else{
		        RequestDispatcher requestDispatcher = request.getRequestDispatcher("./DisplayUser.jsp");
				request.setAttribute("products", prod);
				request.setAttribute("users", list);
				requestDispatcher.forward(request, response);
			 
		 }
	}

}
