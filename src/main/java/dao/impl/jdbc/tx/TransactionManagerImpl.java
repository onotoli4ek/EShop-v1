package dao.impl.jdbc.tx;

import com.mysql.jdbc.Driver;
import dao.WorkToDoInterface.UnitOfWork;
import dao.impl.jdbc.TransactionManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionManagerImpl extends BaseDataSourse implements TransactionManager{
    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop?user=root&password=tolibasik";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public <T, E extends Exception> T doInTransaction (UnitOfWork<T, E> unitOfWork) throws E, SQLException {
        System.out.println("inside TransactionManagerExternalTxImpl.doInTransaction:");
        Connection conn = null;
        T result = null;
        try {
//            DriverManager.registerDriver(new Driver());
//            conn = DriverManager.getConnection(JDBC_URL);
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            connectionHolder.set(conn);
            result = unitOfWork.doInTx();
            conn.commit();
            System.out.println("commit");
            return result;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException | NullPointerException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtils.closeQuietly(conn);
            connectionHolder.remove();
        }
        return result;

    }

    @Override
    public Connection getConnection() throws SQLException {
        return connectionHolder.get();
    }
}
