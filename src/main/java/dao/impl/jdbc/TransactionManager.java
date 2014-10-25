package dao.impl.jdbc;

import dao.WorkToDoInterface.UnitOfWork;

import java.sql.SQLException;


public interface TransactionManager {
    public <T, E extends Exception> T doInTransaction (UnitOfWork<T, E> unitOfWork) throws E, SQLException;
}
