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

public class ProductDaoJDBCExternalTxImpl implements ProductDao{
    public static final String JDBC_URL =
            "jdbc:mysql://127.0.0.1:3306/eshop?user=root&password=tolibasik";
    public static final String SELECT_ALL_SQL =  "SELECT id, productName FROM Products";
    public static final String SELECT_BY_ID_SQL = "SELECT productName FROM Products WHERE id = ?";
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }
    /**
     * Never return nul
     */

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
//        Connection conn = null;
        System.out.println("inside selectById");
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
//            conn = DriverManager.getConnection(JDBC_URL);
//            conn.setAutoCommit(false);
//            stmt = conn.prepareStatement(SELECT_BY_ID_SQL);
            stmt = dataSource.getConnection().prepareStatement(SELECT_BY_ID_SQL);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if(!rs.next()){
                throw new NoSuchElementException("No product for id: " + id);
            }
            Product product = new Product(id, rs.getString("productName"));
            System.out.println(product);
            return product;

        } catch (SQLException e){
            throw new DaoSystemException("Some exception: " + e.getMessage());
        } finally {
            JDBCUtils.closeQuietly(rs, stmt);
        }
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
        try{
            Statement stmt = dataSource.getConnection().createStatement();
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
//class MyClassForDb {
//    public static final String JDBC_URL =
//            "jdbc:mysql://127.0.0.1:3306/eshop?user=root&password=tolibasik";
//    public static final String SELECT_ALL_SQL =  "SELECT id, productName FROM products";
//    public static void main(String[] args) throws SQLException {
//        Connection connection = null;
//        try{
//            connection = DriverManager.getConnection(JDBC_URL);
//            PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_SQL);
//            stmt.executeUpdate(
//                    "INSERT INTO products(productName) values('Helen');");
//            ResultSet result1 = stmt.executeQuery(
//                    "SELECT * FROM products;");
//            while (result1.next()) {
//                System.out.println("Id" +  result1.getInt("id"));
//            }
//        } finally {
//            connection.close();
//        }
//
//    }
//}