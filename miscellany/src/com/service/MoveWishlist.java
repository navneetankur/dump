package com.service;

import java.io.IOException;

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
 * Servlet implementation class MoveWishlist
 */
@WebServlet("/MoveWishlist")
public class MoveWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession ses= request.getSession(false);
		String userId=(String)ses.getAttribute("userId");
		String action=request.getParameter("a");
		StringBuffer entry = new StringBuffer();
		String data,list,id=request.getParameter("id");
		data =FetchData.fetchCart(userId);
		list =FetchData.fetchWishlist(userId);
		StringBuffer sb=new StringBuffer();
		
		if(action.equals("c2w")){
			sb=new StringBuffer();
			sb.append(data);
			sb=Remove.removeDup(sb,id);
			if(list.equals("0"))
			{
					entry.append(id);
			}
			else{
				
					entry.append(list);
					entry.append(","+id);
			}
			entry=Remove.remove(entry);
			Update.updateCart(String.valueOf(sb), userId);
			Update.updateWishlist(String.valueOf(entry), userId);
			response.sendRedirect("./DisplayCartDb");
		}
		else if(action.equals("w2c")){
			 sb=new StringBuffer();
			sb.append(list);
			sb=Remove.removeDup(sb,id);
			System.out.println(sb);
			if(data.equals("0"))
			{
					entry.append(id);
			}
			else{
				
					entry.append(data);
					entry.append(","+id);
			}
			entry=Remove.remove(entry);
			Update.updateCart(String.valueOf(entry), userId);
			Update.updateWishlist(String.valueOf(sb), userId);
			response.sendRedirect("./DisplayWishlist");
		}
		else{
			 sb=new StringBuffer();
			sb.append(list);
			sb=Remove.removeDup(sb,id);
			Update.updateWishlist(String.valueOf(sb), userId);
			response.sendRedirect("./DisplayWishlist");
		}
	}

}
