package dao;

import dao.exception.DaoException;
import dao.exception.NotUniqueUserEmailException;
import dao.exception.NotUniqueUserLoginException;
import entity.User;

import java.util.List;

public interface UserDao {

    public List<User> selectAll() throws DaoException;

    public User selectByLogin(String login) throws DaoException;

    public User selectByEmail(String email) throws DaoException;

    public int deleteById(int id) throws DaoException;

    public void insertNew(User user) throws DaoException, NotUniqueUserLoginException, NotUniqueUserEmailException;
    
}
