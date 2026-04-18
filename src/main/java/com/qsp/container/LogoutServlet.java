package com.qsp.container;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.util.LogoutService;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	private LogoutService logoutService=LogoutService.getInstsnce();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Log out request came");
		logoutService.logout(req, resp);
		PrintWriter pw=resp.getWriter();
		pw.println("<H1>Logged out successfully</H1>");
		RequestDispatcher rd=req.getRequestDispatcher("Home.html");
		rd.include(req, resp);
	}
}
