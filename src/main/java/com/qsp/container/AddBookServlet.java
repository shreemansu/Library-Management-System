package com.qsp.container;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.dao.BookDao;

@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
	
	private BookDao bookDao=BookDao.getInstance();
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ADD BOOK BUSINESS LOGIC");
		String bookName=req.getParameter("bname");
		String price=req.getParameter("price");
		String author=req.getParameter("author");
		String publish=req.getParameter("publication");
//		System.out.println(bookName+" "+price+" "+author+" "+publish);
		String res=bookDao.addBook(bookName, price, author, publish);
		PrintWriter pw=resp.getWriter();
		pw.println(res);
		
		
	}
} 
