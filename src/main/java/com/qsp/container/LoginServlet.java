package com.qsp.container;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.util.AuthenticateUserUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private AuthenticateUserUtil authutil=AuthenticateUserUtil.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username=req.getParameter("username");
		if(username==null||username.isEmpty()) {
			RequestDispatcher rd=req.getRequestDispatcher("/exception");
			req.setAttribute("error", "Username is empty");
			rd.forward(req, resp);
			return;
		}
		
		String password=req.getParameter("password");
		if(password==null||password.isEmpty()) {
			req.setAttribute("error", "Password is empty");
			RequestDispatcher rd=req.getRequestDispatcher("/exception");
			rd.forward(req, resp);
			return;
		}
		
		String response=authutil.loginAuthentication(req, resp);
		PrintWriter pw=resp.getWriter();
		pw.println("<H1>"+response+"</H1>");
	}
}
