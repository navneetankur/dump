package com.data;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.glecom.products.models.dao.SessionDao;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	PrintWriter printer=response.getWriter(); 
	
	
		HttpSession session = request.getSession(false);
		if(session==null) response.sendRedirect("./home");
//		session.invalidate();
//		request.getRequestDispatcher("Login.jsp").include(request,response);
//		printer.print("<script>alert('you are logged out')</script>");
//		printer.close();
		//HttpSession session = request.getSession(true);
		SessionDao sessionDao = new SessionDao();
		try {
			sessionDao.forgetMe(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				sessionDao.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
        session.setAttribute("userId", "guest");
		session.setAttribute("accessType", "guest");
		String goBack = (String)session.getAttribute("goBack");
		session.invalidate();
		if(goBack==null) goBack = "./home";
		response.sendRedirect(goBack);
		
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
