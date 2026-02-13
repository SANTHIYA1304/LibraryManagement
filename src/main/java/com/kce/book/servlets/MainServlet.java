package com.kce.book.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.AuthorDAO;
import com.kce.book.service.Administrator;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String operation =
                request.getParameter("operation");

        if ("AddBook".equals(operation)) {

            String res = addBook(request);

            if ("SUCCESS".equals(res))
                response.sendRedirect("Menu.html");

            else if ("INVALID".equals(res))
                response.sendRedirect("Invalid.html");

            else
                response.sendRedirect("Failure.html");
        }

        else if ("Search".equals(operation)) {

            String isbn =
                request.getParameter("isbn");

            BookBean bookBean =
                viewBook(isbn);

            if (bookBean == null) {
                response.sendRedirect("Invalid.html");
            } else {

                HttpSession session =
                        request.getSession();

                session.setAttribute(
                        "book", bookBean);

                RequestDispatcher rd =
                    request.getRequestDispatcher(
                            "ViewServlet");

                rd.forward(request, response);
            }
        }
    }

    public String addBook(HttpServletRequest request) {

        String isbn =
            request.getParameter("isbn");

        String bookName =
            request.getParameter("bookName");

        String bookType =
            request.getParameter("bookType");

        String authorName =
            request.getParameter("authorName");

        String cost =
            request.getParameter("cost");

        if (isbn == null ||
            bookName == null ||
            bookType == null ||
            authorName == null ||
            cost == null)
            return "INVALID";

        isbn = isbn.trim();
        bookName = bookName.trim();
        bookType = bookType.trim();
        authorName = authorName.trim();

        if (isbn.isEmpty() ||
            bookName.isEmpty() ||
            bookType.isEmpty() ||
            authorName.isEmpty())
            return "INVALID";

        BookBean bookBean =
                new BookBean();

        bookBean.setIsbn(isbn);
        bookBean.setBookName(bookName);

        char type =
            Character.toUpperCase(
                    bookType.charAt(0));

        bookBean.setBookType(type);

        try {
            bookBean.setCost(
                Float.parseFloat(cost));
        } catch (Exception e) {
            return "INVALID";
        }

        bookBean.setAuthor(
            new AuthorDAO()
                .getAuthor(authorName));

        if (bookBean.getAuthor() == null)
            return "INVALID";

        return new Administrator()
                .addBook(bookBean);
    }

    public BookBean viewBook(String isbn) {

        if (isbn == null ||
            isbn.trim().isEmpty())
            return null;

        return new Administrator()
                .viewBook(isbn.trim());
    }
}
