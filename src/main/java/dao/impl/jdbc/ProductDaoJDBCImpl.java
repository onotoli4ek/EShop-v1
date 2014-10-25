package dao.impl.jdbc;

import dao.ProductDao;
import dao.exception.DaoSystemException;
import dao.exception.NoSuchEntityException;
import dao.impl.jdbc.tx.JDBCUtils;
import entity.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProductDaoJDBCImpl implements ProductDao{
    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop?user=root&password=tolibasik";
    public static final String SELECT_ALL_SQL =  "SELECT id, productName FROM products";
    public static final String SELECT_BY_ID_SQL = "SELECT productName FROM products WHERE id = ?";

    /**
     * Never return nul
     */

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(JDBC_URL);
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                throw new NoSuchElementException("No product for id: " + id);
            }
            Product result = new Product(rs.getInt("id"), rs.getString("productName"));
            return new Product(id, rs.getString("productName"));

        } catch (SQLException e){
            throw new DaoSystemException("Some exception: " + e.getMessage());
        } finally {
            JDBCUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        try{
            Statement stmt = DriverManager.getConnection(JDBC_URL).createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()){
                Product product = new Product(rs.getInt("id"), rs.getString("productName"));
                products.add(product);
            }
            return products;
        } catch (SQLException e){
            throw new DaoSystemException("Some exception: " + e.getMessage());
        }
    }
}
