package com.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DAO.FetchData;
import com.DAO.Update;
/**
 * @author Sanyam tyagi
 * 
 */
/**
 * Servlet implementation class Wishlist
 */
@WebServlet("/Wishlist")
public class Wishlist extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				HttpSession ses = request.getSession(false); 
				String userId= (String) ses.getAttribute("userId");
				if(userId!=null){
					String id = request.getParameter("id");
					StringBuffer list = new StringBuffer();
					String data =FetchData.fetchWishlist(userId);
					if(data.equals("0"))
					{
							list.append(id);
					}
					else{
						
							list.append(data);
							list.append(","+id);
					}
					list=Remove.remove(list);

					Update.updateWishlist(String.valueOf(list), userId);
				}
				RequestDispatcher requestDispatcher = request.getRequestDispatcher((String) ses.getAttribute("goBack"));
				requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
