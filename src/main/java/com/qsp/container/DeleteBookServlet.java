package com.qsp.container;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.dao.BookDao;

@WebServlet("/delete")
public class DeleteBookServlet extends HttpServlet {
	
	private BookDao bookDao=BookDao.getInstance();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id=Integer.parseInt(req.getParameter("id"));
		bookDao.deleteById(id);
		RequestDispatcher rd=req.getRequestDispatcher("allbook.jsp");
		rd.forward(req, resp);
	}
}
