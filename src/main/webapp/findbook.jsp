<%@page import="java.io.PrintWriter"%>
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

    table {
        width: 80%;
        margin: 0 auto;
        border-collapse: collapse;
        background-color: #ffffff;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
        border-radius: 8px;
        overflow: hidden;
    }

    thead {
        background-color: #007bff;
        color: #ffffff;
    }

    th, td {
        padding: 12px 15px;
        text-align: center;
    }

    th {
        font-weight: 600;
        text-transform: uppercase;
        font-size: 14px;
    }

    tbody tr {
        border-bottom: 1px solid #dddddd;
    }

    tbody tr:nth-child(even) {
        background-color: #f8f9fa;
    }

    tbody tr:hover {
        background-color: #e9f2ff;
        transition: background 0.3s ease;
    }

    td {
        color: #333;
        font-size: 14px;
    }
</style>

</head>
<body>
        <h1>Student Data JSP</h1>
      <%! 
          BookDao bookDao=BookDao.getInstance(); 
      %>
      <%
          String id=request.getParameter("bookid");
          System.out.println(id);
          Book book=bookDao.getSingleBook(id);
          if(book==null){
          RequestDispatcher rd=request.getRequestDispatcher("findbook.html");
          PrintWriter pw=response.getWriter();
          String text="No Book found with id "+id;
          pw.println("<H1>"+text+"</H1>");
          rd.include(request, response);
          return;
          }
      %>
      <table>
         <thead>
             <tr>
             <th>Book Id</th>
             <th>Book Name</th>
             <th>Book Author</th>
             <th>Book Publication</th>
             <th>Book Price</th>
             </tr>
         </thead>
         <tbody>
            <tr>
                <td><%= book.getBookId() %></td>
                <td><%= book.getBookName() %></td>
                <td><%= book.getAuthor() %></td>
                <td><%= book.getPublication() %></td>
                <td><%= book.getPrice() %></td>                  
            </tr>
         </tbody>
      </table>
</body>
</html>