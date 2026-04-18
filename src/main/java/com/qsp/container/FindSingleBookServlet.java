package com.qsp.container;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qsp.dao.BookDao;
import com.qsp.entity.Book;

@WebServlet("/find")
public class FindSingleBookServlet extends HttpServlet {
	private BookDao bookdao=BookDao.getInstance();
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("bookid");
		System.out.println(id);
		Book book=bookdao.getSingleBook(id);
		String res="id:"+book.getBookId()+" name:"+book.getBookName()+" price:"+book.getPrice()+" author:"+book.getAuthor()+" publication:"+book.getPublication();
		PrintWriter pw=resp.getWriter();
		pw.println(res); 
	}
}
