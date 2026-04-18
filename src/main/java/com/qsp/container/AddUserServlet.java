package com.qsp.container;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.dao.UserDao;

@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {
	private UserDao userdao=UserDao.getInstance();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username=req.getParameter("uname");
		String role=req.getParameter("role");
		String password=req.getParameter("password");
		System.out.println(username+" "+role+" "+password);
		String response=userdao.addUser(username, password, role);
		PrintWriter pw=resp.getWriter();
		pw.println("<H1>"+response+"</H1");
	}
}
