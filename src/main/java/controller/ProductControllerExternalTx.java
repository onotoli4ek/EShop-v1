package controller;

import dao.ProductDao;
import dao.WorkToDoInterface.UnitOfWork;
import dao.exception.DaoException;
import dao.impl.jdbc.TransactionManager;
import entity.Product;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ProductControllerExternalTx extends HttpServlet {
    public static final String PARAM_ID = "id";
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "product";
    public static final String PAGE_OK = "product.jsp";
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
        System.out.println(">> Inside ProductsAllController.doGet():");
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null){
            try {
                final Integer id = Integer.valueOf(idStr);
                Product model = txManager.doInTransaction(new UnitOfWork<Product, DaoException>() {
                    public Product doInTx() throws DaoException {
                        return productDao.selectById(id);
                    }
                });
                req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
                // OK
                req.getRequestDispatcher(PAGE_OK).forward(req,resp);
            } catch (SQLException | DaoException | NumberFormatException  e) {
                /*NOP*/
            } /*catch (Exception e) {
                *//*NOP*//*
            }*/
        }
        // FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}
