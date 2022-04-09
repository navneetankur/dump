package com.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

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
@WebServlet("/Search")
public class Search extends HttpServlet {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String box=request.getParameter("box").trim();
		if(box.equals(""))
		{
			RequestDispatcher rd = request.getRequestDispatcher("");
			rd.forward(request, response);
		}
			StringBuffer query = new StringBuffer();
			query.append("select * from productc where name like '%"+box+"%' or category like '%"+box+"%' or sub_category like '%"+box+"%' or description  like '%"+box+"%' or brand like '%"+box+"%' LIMIT 12");
			ArrayList<Product> list = FetchData.fetchData(String.valueOf(query));

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("./product-list.jsp");
			request.setAttribute("products", list);
			requestDispatcher.forward(request, response);
	}
	

}
