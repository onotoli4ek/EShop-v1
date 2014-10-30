package dao.impl.jdbc;

import dao.UserDao;
import dao.exception.*;
import entity.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao{

    public static final String SELECT_BY_LOGIN = "SELECT id, login, password, email FROM Users WHERE login = ?";
    public static final String INSERT_NEW_USER = "INSERT INTO Users(login, password, email) VALUES (?, ?, ?)";
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public List<User> selectAll() throws DaoException {
        return null;
    }

    @Override
    public User selectByLogin(String byLogin) throws DaoException {

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            System.out.println(">>  inside UserDaoJDBCImpl");
            stmt = dataSource.getConnection().prepareStatement(SELECT_BY_LOGIN);
            System.out.println(">>  connect" + byLogin);
            stmt.setString(1, byLogin);
            rs = stmt.executeQuery();
            System.out.println(">>  inside UserDaoJDBCImpl 2");
            if (!rs.next()){
                System.out.println("NoSuchEntityException");
                throw new NoSuchEntityException("No user for login: " + byLogin);
            } else {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String password = rs.getString("password");
                String email = rs.getString("email");
                if (rs.next()){
                    throw new DaoSystemException("DB contains more than 1 user:" + byLogin);
                }
                System.out.println("Id - " + id);
                return new User(id, login, password, email);
            }

        } catch (SQLException e) {
            throw new DaoSystemException("some JDBC problem", e);
        }
    }

    @Override
    public User selectByEmail(String email) throws DaoException {
        return null;
    }

    @Override
    public int deleteById(int id) throws DaoException {
        return 0;
    }

    @Override
    public User insertNew(User user) throws DaoException, NotUniqueUserLoginException, NotUniqueUserEmailException {
        try {
            System.out.println("inside insertNew");
//            Statement stmt = dataSource.getConnection().createStatement();
            PreparedStatement stmt = dataSource.getConnection().prepareStatement(INSERT_NEW_USER);
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.executeUpdate();
            return selectByLogin(user.getLogin());
        } catch (SQLException | NoSuchEntityException e){
            throw new DaoSystemException("Some jdbc error",e);
        }
    }
}
