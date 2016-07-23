package com.epam.ok.storeCenter.dao.jdbc;

import com.epam.ok.storeCenter.dao.DaoException;
import com.epam.ok.storeCenter.model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcAuthorDao extends AbstractDao<Author> {
    private static final String TABLE_NAME = "Author";
    private static final String INSERT_AUTHOR = "INSERT INTO Author (lastname, firstname, patronymic) VALUES (?,?,?)";
    private static final String UPDATE_AUTHOR = "UPDATE Author SET firstname = ?, lastname = ?, patronymic = ?, isDelete = ? WHERE id = ?";

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String getQueryForInsert() {
        return INSERT_AUTHOR;
    }

    @Override
    protected String getQueryForUpdate() {
        return UPDATE_AUTHOR;
    }

    @Override
    protected Author getObjectFromResultSet(ResultSet rs) throws DaoException {
        Author author = new Author();
        try {
            author.setId(rs.getInt("id"));
            author.setFirstname(rs.getString("firstname"));
            author.setLastname(rs.getString("lastname"));
            author.setPatronymic(rs.getString("patronymic"));
            author.setDeleted(rs.getBoolean("isDelete"));
        } catch (SQLException e) {
            throw new DaoException();
        }
        return author;
    }

    @Override
    protected void setVariablesForPreparedStatementExceptId(Author author, PreparedStatement ps) throws DaoException {
        try {
            ps.setString(1, author.getFirstname());
            ps.setString(2, author.getLastname());
            ps.setString(3, author.getPatronymic());
            ps.setBoolean(4, author.isDeleted());
        } catch (SQLException e) {
            throw new DaoException();
        }
    }
}
