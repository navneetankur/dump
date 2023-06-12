package com.data;


	import java.io.IOException;
	import java.io.PrintWriter;

	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	@WebServlet("/FailureServlet")
	public class FailureServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			if(request.getParameter("userId")!=null){
				response.sendRedirect("./Error.html");
				PrintWriter out = response.getWriter();
				out.println("User_ID or Password is Invalid");
				
				return;
				}
			
		}
		
	}



