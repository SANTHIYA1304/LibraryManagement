package com.kce.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kce.book.bean.AuthorBean;
import com.kce.book.util.DBUtil;

public class AuthorDAO {

    public AuthorBean getAuthor(int authorCode) {

        String query =
            "SELECT * FROM Authors_Tbl WHERE Author_code=?";

        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps =
                 connection.prepareStatement(query)) {

            ps.setInt(1, authorCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                AuthorBean author = new AuthorBean();

                author.setAuthorCode(
                        rs.getInt("Author_code"));
                author.setAuthorName(
                        rs.getString("Author_name"));
                author.setContactNo(
                        rs.getLong("Contact_no"));

                return author;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AuthorBean getAuthor(String authorName) {

        if (authorName == null ||
            authorName.trim().isEmpty())
            return null;

        String query =
        "SELECT * FROM Authors_Tbl " +
        "WHERE LOWER(Author_name)=LOWER(?)";

        try (Connection connection = DBUtil.getDBConnection();
             PreparedStatement ps =
                 connection.prepareStatement(query)) {

            ps.setString(1, authorName.trim());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                AuthorBean author = new AuthorBean();

                author.setAuthorCode(
                        rs.getInt("Author_code"));
                author.setAuthorName(
                        rs.getString("Author_name"));
                author.setContactNo(
                        rs.getLong("Contact_no"));

                return author;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
