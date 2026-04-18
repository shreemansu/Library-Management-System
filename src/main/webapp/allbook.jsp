<%@ page import="com.qsp.entity.Book" %>
<%@ page import="java.util.List" %>
<%@ page import="com.qsp.dao.BookDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All Books</title>
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

    table {
        width: 95%;
        margin: 0 auto;
        border-collapse: collapse;
        background-color: #ffffff;
        box-shadow: 0 6px 15px rgba(0, 0, 0, 0.12);
        border-radius: 8px;
        overflow: hidden;
    }

    thead {
        background-color: #343a40;
        color: #ffffff;
    }

    th, td {
        padding: 12px 15px;
        text-align: center;
    }

    th {
        font-size: 14px;
        font-weight: 600;
        text-transform: uppercase;
    }

    tbody tr {
        border-bottom: 1px solid #dddddd;
    }

    tbody tr:nth-child(even) {
        background-color: #f8f9fa;
    }

    tbody tr:hover {
        background-color: #eef4ff;
        transition: background 0.3s ease;
    }

    td {
        font-size: 14px;
        color: #333;
    }

    /* Update button */
    a.update-btn {
        padding: 6px 12px;
        background-color: #007bff;
        color: #ffffff;
        text-decoration: none;
        border-radius: 4px;
        font-size: 13px;
        font-weight: 600;
    }

    a.update-btn:hover {
        background-color: #0056b3;
    }

    /* Delete button */
    a.delete-btn {
        padding: 6px 12px;
        background-color: #dc3545;
        color: #ffffff;
        text-decoration: none;
        border-radius: 4px;
        font-size: 13px;
        font-weight: 600;
    }

    a.delete-btn:hover {
        background-color: #b02a37;
    }
</style>
    
</head>
<body>

<h1>All Books</h1>

<%
    BookDao bookdao = BookDao.getInstance();
    List<Book> books = bookdao.getAllBooks();
%>

<table border="1">
    <thead>
        <tr>
            <th>Book Id</th>
            <th>Book Name</th>
            <th>Book Price</th>
            <th>Book Author</th>
            <th>Book Publication</th>
            <th>Update</th>
            <th>Delete</th>
        </tr>
    </thead>

    <tbody>
        <%
            for (Book book : books) {
        %>
        <tr>
            <td><%= book.getBookId() %></td>
            <td><%= book.getBookName() %></td>
            <td><%= book.getPrice() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getPublication() %></td>
            <td><a class="update-btn" href="update.jsp?id=<%= book.getBookId() %>">Update</a></td>
            <td><a class="delete-btn" href="delete?id=<%= book.getBookId() %>"
                   onclick="return confirm('Are you sure you want to delete this book?')">Delete</a> </td>
        </tr>
        <%
            }
        %>
    </tbody>
</table>

</body>
</html>