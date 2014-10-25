package dao.impl.jdbc.tx;

import com.jolbox.bonecp.BoneCPDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class BCPDataSource extends BaseDataSourse {
    private String driverClass;
    private String jdbcUrl;
    private String user;
    private String password;
    private BoneCPDataSource dataSource;

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void init() throws SQLException {
        dataSource = new BoneCPDataSource();
        // jdbc
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(user);
        dataSource.setPassword(password);
        //
        dataSource.setAcquireIncrement(4);          //per partition settings
        dataSource.setAcquireRetryAttempts(5);
        dataSource.setAcquireRetryDelayInMs(100);
        dataSource.setStatementsCacheSize(200);
        //development mode
        dataSource.setDetectUnclosedStatements(true);
        dataSource.setCloseOpenStatements(true);
        dataSource.setDetectUnresolvedTransactions(true);
        dataSource.setLogStatementsEnabled(true);
        // ??
        dataSource.setTransactionRecoveryEnabled(false);
        dataSource.setStatisticsEnabled(false);
        // test connection
        dataSource.setConnectionTestStatement("/* ping */ SELECT 1");
        dataSource.setIdleConnectionTestPeriod(1, TimeUnit.MINUTES);
        // pool
        dataSource.setPoolStrategy("CACHED"); // connection for pool to every thread (ThreadLocal)

        dataSource.setLazyInit(true);
        dataSource.setIdleMaxAgeInMinutes(10);


    }

    /**
     * Close the datasource (i.e. shut down the entire pool).
     *
     */
    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void shutdown() {

        if (dataSource != null) {
            dataSource.close();
        }
    }


    }
