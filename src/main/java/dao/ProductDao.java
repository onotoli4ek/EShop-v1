package dao;

import entity.Product;
import dao.exception.DaoSystemException;
import dao.exception.NoSuchEntityException;

import java.util.List;

// CRUD operations
// create = SQL/ insert = List/add
//read = SQL/ select = List/get
//update = SQL/ update = List/set
//delete = SQL/ delete = List/remove
public interface ProductDao {

    /**
     * Never reurn null
     */
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException;
    public List<Product> selectAll() throws DaoSystemException;
}
