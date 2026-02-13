package com.kce.book.service;

import com.kce.book.bean.BookBean;
import com.kce.book.dao.BookDAO;

public class Administrator {

    public String addBook(BookBean bookBean) {

        if (bookBean == null)
            return "INVALID";

        if (bookBean.getBookName() == null ||
            bookBean.getBookName().trim().isEmpty())
            return "INVALID";

        if (bookBean.getIsbn() == null ||
            bookBean.getIsbn().trim().isEmpty())
            return "INVALID";

        if (bookBean.getAuthor() == null)
            return "INVALID";

        if (bookBean.getCost() <= 0)
            return "INVALID";

        char type =
            Character.toUpperCase(bookBean.getBookType());

        if (type != 'G' && type != 'T')
            return "INVALID";

        bookBean.setBookType(type);

        int res = new BookDAO().createBook(bookBean);

        if (res == 1)
            return "SUCCESS";

        return "FAILURE";
    }

    public BookBean viewBook(String isbn) {

        if (isbn == null ||
            isbn.trim().isEmpty())
            return null;

        return new BookDAO().fetchBook(isbn.trim());
    }
}
