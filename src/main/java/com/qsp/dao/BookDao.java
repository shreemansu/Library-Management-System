package com.qsp.dao;

import java.util.List;

import com.qsp.entity.Book;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public final class BookDao extends AbstractDao {
	
	private static BookDao object=new BookDao();
	
	private BookDao() {
		
	}
	public static BookDao getInstance() {
		return object;
	}
	
	public String addBook(String name,String price,String author,String publication) {
		if(name==null|| price==null ||author==null || publication==null) {
			return "Please Enter The Correct Value";  
		}
		try {
			Book book=Book.builder()
					.bookName(name)
					.price(Integer.parseInt(price))
					.author(author)
					.publication(publication).build();
			EntityTransaction et=super.em.getTransaction();
			et.begin();
			super.em.persist(book);
			et.commit();
			return name +" Saved in Library";
		} catch (Exception e) {
			return "Exception Occured";
		}
	}
	public String findBook(Integer id) {
		Book book=em.find(Book.class, id);
		if(book==null) return "No Book Exist With Id "+id;
		StringBuffer buffer=new StringBuffer();
		buffer.append("book name : "); buffer.append(book.getBookName()+"\n");
		buffer.append("book price : "); buffer.append(book.getPrice()+"\n");
		buffer.append("book author : "); buffer.append(book.getAuthor()+"\n");
		buffer.append("book publication : "); buffer.append(book.getPublication());
		return buffer.toString();
	}  
	public Book getSingleBook(String id) {
		int bookid=Integer.parseInt(id);
		Book book=em.find(Book.class, bookid);
		return book; 
	}
	public List<Book> getAllBooks(){
		Query query=em.createQuery("select b from Book b");
		List<Book> books=query.getResultList();
		return books; 
	} 
	public Book getById(int id) {
		return em.find(Book.class, id);
	}
	public void updateBook(Integer id, String name, Integer price, String author, String publication) {
		Book book=em.find(Book.class, id);
		book.setBookName(name);
		book.setPrice(price);
		book.setAuthor(author);
		book.setPublication(publication);
		EntityTransaction et=em.getTransaction();
		et.begin();
		em.merge(book);
		et.commit();
	}
	public void deleteById(Integer id) {
		Book book=em.find(Book.class, id);
		if(book!=null) {
			EntityTransaction et=em.getTransaction();
			et.begin();
			em.remove(book);
			et.commit();
		}
	}
} 
