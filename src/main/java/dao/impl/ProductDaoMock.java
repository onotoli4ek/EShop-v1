package dao.impl;

import entity.Product;
import dao.ProductDao;
import dao.exception.DaoSystemException;
import dao.exception.NoSuchEntityException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductDaoMock implements ProductDao {
    private final Map<Integer, Product> memory = new ConcurrentHashMap<>();

    public ProductDaoMock() {
        this.memory.put(1, new Product(1, "Bread"));
        this.memory.put(2, new Product(2, "Paper"));
        this.memory.put(3, new Product(3, "Sugar"));

    }

    @Override
    public Product selectById(int id) throws DaoSystemException, NoSuchEntityException {
        if (id == 123) {
            throw new Error("Hello from magic bad id");
        }

        if (!memory.containsKey(id)) {
            throw new NoSuchEntityException("No product for id = " + id);
        }
        return memory.get(id);
    }

    @Override
    public List<Product> selectAll() throws DaoSystemException {
//        List<Product> list = new ArrayList<>();
//        for (Map.Entry<Integer, Product> entry : memory.entrySet()) {
//            list.add(entry.getValue());
//        }
        return new ArrayList<>(memory.values());
    }
}
