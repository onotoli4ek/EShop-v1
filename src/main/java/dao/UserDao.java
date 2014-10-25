package dao;

import dao.exception.DaoException;
import dao.exception.NotUniqueUserEmailException;
import dao.exception.NotUniqueUserLoginException;
import entity.User;

import java.util.List;

public interface UserDao {
    public List<User> selectAll() throws DaoException;

    public int deleteById(int id) throws DaoException;

    public void insert(User user) throws DaoException, NotUniqueUserLoginException, NotUniqueUserEmailException;
    
}
