package com.kce.book.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.kce.book.bean.BookBean;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out =response.getWriter();
		HttpSession session=request.getSession();
		BookBean bookBean =(BookBean) session.getAttribute("book");
		out.print("<html><body>");
		out.print("Book title:"+bookBean.getBookName());
		out.print("Author Name:"+bookBean.getAuthor().getAuthorName());
		out.print("Author Contact:"+bookBean.getAuthor().getContactNo());
		out.print("Book Price :"+bookBean.getCost());
		out.print("Book isbn:"+bookBean.getIsbn());
		out.print("</body></html>");
	}

}