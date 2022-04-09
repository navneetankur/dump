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
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses = request.getSession(false); 
		String id = request.getParameter("id");
		String userId= (String) ses.getAttribute("userId");
		StringBuffer cart = new StringBuffer();
		String data =FetchData.fetchCart(userId);
		if(data.equals("0"))
		{
		
				cart.append(id);
		}
		else{
			
				cart.append(data);
				cart.append(","+request.getParameter("id"));
			
		}
		 cart=Remove.remove(cart);
		 Update.updateCart(String.valueOf(cart), userId);
		 RequestDispatcher requestDispatcher = request.getRequestDispatcher((String)ses.getAttribute("goBack"));
		 requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
