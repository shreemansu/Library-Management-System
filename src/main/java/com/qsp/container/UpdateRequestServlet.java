package com.qsp.container;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.dao.BookDao;

@WebServlet("/updaterequest")
public class UpdateRequestServlet extends HttpServlet {
	
    BookDao bookDao=BookDao.getInstance();
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Update Requested !!!");
		int id=Integer.parseInt(req.getParameter("id"));
		String bookname=req.getParameter("name");
		String author=req.getParameter("author");
		Integer price=Integer.parseInt(req.getParameter("price"));
		String publication=req.getParameter("publication");
		bookDao.updateBook(id, bookname, price, author, publication);
		RequestDispatcher rd=req.getRequestDispatcher("allbook.jsp");
		rd.forward(req, resp);
	}
}
