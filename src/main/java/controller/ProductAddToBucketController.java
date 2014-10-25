package controller;

import dao.ProductDao;
import dao.WorkToDoInterface.UnitOfWork;
import dao.exception.DaoException;
import dao.impl.jdbc.TransactionManager;
import entity.Product;
import org.springframework.context.ApplicationContext;

import static controller.SessionAttributes.*;
import static java.util.Collections.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ProductAddToBucketController extends HttpServlet {
    public static final String PARAM_ID = "id";
    public static final String PAGE_ERROR = "error.jsp";

    TransactionManager txManager;
    ProductDao productDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContext ac = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

        this.productDao = (ProductDao)ac.getBean("productDao");
        this.txManager = (TransactionManager)ac.getBean("txManager");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        try {
            final Integer id = Integer.valueOf(idStr);
            Product product = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                public Product doInTx() throws DaoException {
                    return productDao.selectById(id);
                }
            });

            HttpSession session = req.getSession(true);
            Map<Product, Integer> oldBucket = (Map<Product, Integer>) session.getAttribute(PRODUCTS_IN_BUCKET);
            if (oldBucket == null) {
                session.setAttribute(PRODUCTS_IN_BUCKET, singletonMap(product,1));
            } else {
                Map<Product, Integer> newBucket = new LinkedHashMap<>(oldBucket);
                if (!oldBucket.containsKey(product)){
                    newBucket.put(product, 1);
                } else {
                    newBucket.put(product, newBucket.get(product) + 1);
                }
                session.setAttribute(PRODUCTS_IN_BUCKET, unmodifiableMap(newBucket));
            }
            // OK
            String newLocation = "product.do?id=" + id;
            resp.sendRedirect(newLocation);
            return;
        } catch (SQLException | DaoException | NumberFormatException  e){
            /*NOP*/
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
