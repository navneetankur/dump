package com.data;


import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.tanesha.recaptcha.ReCaptchaImpl;
import net.tanesha.recaptcha.ReCaptchaResponse;

import com.DAO.FetchData;
import com.DAO.Update;
import com.database.DBConnect;
import com.service.Remove;

@WebServlet("/U_AddServlet")
public class U_AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       String user_name, userId, user_email, password, user_contact, enp,accessType,dob,sex,active="1";
  	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("userId")==null){
			response.sendRedirect("./Registration.jsp");
			return ;}
		response.setContentType("text/html");
			user_name=request.getParameter("user_name");
			userId=request.getParameter("userId");
			user_email=request.getParameter("user_email");
			password=request.getParameter("password");
			user_contact=request.getParameter("user_contact");
			dob=request.getParameter("bday");
			System.out.println(dob);
			sex=request.getParameter("sex");
			
			HttpSession ses = request.getSession();
			String s=null;//(String)ses.getAttribute("accessType");
			if(s!=null){
				accessType = s;
			}
			else{
				accessType ="user";
			}
			
			Pass_Encrp en = new Pass_Encrp();
			try {
				 enp = (String) en.MD5(password);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBConnect db= new DBConnect();
			PrintWriter out = response.getWriter();
			RequestDispatcher dispatcher;

			if(db.addUser(userId, user_name, user_email, user_contact, enp,accessType,sex,dob,active)){
				  HttpSession session = request.getSession(true);
	              session.setAttribute("userId", userId);
				  session.setAttribute("accessType", accessType);
				//sanyam
			      Cookie[] cookies = request.getCookies();
			      StringBuffer cart=new StringBuffer();
				  if(cookies != null)
		            	for(Cookie cookie : cookies)
		            		if(cookie.getName().equals("cart"))
		            			cart.append(cookie.getValue());
				  if(cart!=null){
					  String data =FetchData.fetchCart(userId);	
					  cart.append(","+data);
					  cart=Remove.remove(cart);
					  Update.updateCart(String.valueOf(cart), userId);
					  cart=new StringBuffer();
					  cart.append("0");
					  Cookie c = new Cookie("cart",String.valueOf(cart));
			            response.addCookie(c);
			          }
	        	  dispatcher=request.getRequestDispatcher((String)session.getAttribute("goBack"));
			}
				else{
					  HttpSession session = request.getSession(true);
		              session.setAttribute("userId", "guest");
					  session.setAttribute("accessType", "guest");
					  session.setAttribute("msg", "User Id already exists");

					  //out.print("<script>alert('User Id already exists')</script>");
		        	  dispatcher=request.getRequestDispatcher("./Registration.jsp");
				}
			dispatcher.forward(request, response);
			db.close();
	}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if(request.getParameter("userId")==null){
		response.sendRedirect("./Registration.jsp");
		return ;}
	response.setContentType("text/html");
		user_name=request.getParameter("user_name");
		userId=request.getParameter("userId");
		user_email=request.getParameter("user_email");
		password=request.getParameter("password");
		user_contact=request.getParameter("user_contact");
		dob=request.getParameter("month")+"/"+request.getParameter("day")+"/"+request.getParameter("year");
		System.out.println(dob);
		sex=request.getParameter("sex");
		String msg = "User Id already exists";
		HttpSession ses = request.getSession();
			accessType ="user";

		
		Pass_Encrp en = new Pass_Encrp();
		try {
			 enp = (String) en.MD5(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBConnect db= new DBConnect();
		PrintWriter out = response.getWriter();
		RequestDispatcher dispatcher;
		String checkCaptcha = request.getParameter("checkCaptcha");
		if(checkCaptcha != null) {
			String remoteAddr = request.getRemoteAddr();
	        ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
	        reCaptcha.setPrivateKey("recaptcha-key-here");

	        String challenge = request.getParameter("recaptcha_challenge_field");
	        String uresponse = request.getParameter("recaptcha_response_field");
	        ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, challenge, uresponse);

	        if (!reCaptchaResponse.isValid()) {
				  HttpSession session = request.getSession(true);
	              session.setAttribute("userId", "guest");
				  session.setAttribute("accessType", "guest");
				  session.setAttribute("msg1", "Invalid Captcha");
	        	  dispatcher=request.getRequestDispatcher("./Registration.jsp");
	        	  dispatcher.forward(request, response);
	        	  return;
	        }
		}

		if(db.addUser(userId, user_name, user_email, user_contact, enp,accessType,sex,dob,active)){
			  HttpSession session = request.getSession(true);
              session.setAttribute("userId", userId);
			  session.setAttribute("accessType", accessType);
			//sanyam
		      Cookie[] cookies = request.getCookies();
		      StringBuffer cart=new StringBuffer();
			  if(cookies != null)
	            	for(Cookie cookie : cookies)
	            		if(cookie.getName().equals("cart"))
	            			cart.append(cookie.getValue());
			  if(cart!=null){
				  String data =FetchData.fetchCart(userId);	
				  cart.append(","+data);
				  cart=Remove.remove(cart);
				  Update.updateCart(String.valueOf(cart), userId);
				  cart=new StringBuffer();
				  cart.append("0");
				  Cookie c = new Cookie("cart",String.valueOf(cart));
		            response.addCookie(c);
		          }
			  String goBack = (String)session.getAttribute("goBack");
			  if(goBack == null) goBack = "./home";
			  response.sendRedirect(goBack);
			  return;
		} else {
				  HttpSession session = request.getSession(true);
	              session.setAttribute("userId", "guest");
				  session.setAttribute("accessType", "guest");
				  session.setAttribute("msg1", "User Id already exists");
	        	  dispatcher=request.getRequestDispatcher("./Registration.jsp");
	        	  dispatcher.forward(request, response);
		}

		
		db.close();
}
}
