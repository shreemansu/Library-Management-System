<%@page import="com.qsp.entity.Book"%>
<%@page import="com.qsp.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    body {
        font-family: "Segoe UI", Arial, sans-serif;
        background-color: #f4f6f9;
        margin: 0;
        padding: 20px;
    }

    h1 {
        text-align: center;
        color: #333;
        margin-bottom: 30px;
    }

    form {
        width: 420px;
        margin: 0 auto;
        background: #ffffff;
        padding: 30px;
        border-radius: 8px;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.12);
    }

    label {
        display: block;
        font-weight: 600;
        margin-bottom: 6px;
        color: #555;
    }

    input[type="text"] {
        width: 100%;
        padding: 10px;
        margin-bottom: 20px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 14px;
    }

    input[type="text"]:focus {
        border-color: #007bff;
        outline: none;
        box-shadow: 0 0 4px rgba(0, 123, 255, 0.4);
    }

    input[type="submit"] {
        width: 100%;
        padding: 12px;
        background-color: #28a745;
        border: none;
        border-radius: 5px;
        color: #ffffff;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: background 0.3s ease;
    }

    input[type="submit"]:hover {
        background-color: #1e7e34;
    }
</style>

</head>
<body>
    <h1>Update Jsp</h1>
    <%
    
    int id=Integer.parseInt(request.getParameter("id"));
    BookDao bookDao=BookDao.getInstance();
    Book book=bookDao.getById(id);
    
    %>
    
    <form action="updaterequest">

    <input type="hidden" name="id" value="<%= book.getBookId() %>">

    <label>Book Name</label>
    <input type="text" name="name" value="<%= book.getBookName() %>">

    <label>Book Price</label>
    <input type="text" name="price" value="<%= book.getPrice() %>">

    <label>Book Author</label>
    <input type="text" name="author" value="<%= book.getAuthor() %>">

    <label>Book Publication</label>
    <input type="text" name="publication" value="<%= book.getPublication() %>">

    <input type="submit" value="Update Book">

</form>
    
   
    
</body>
</html>