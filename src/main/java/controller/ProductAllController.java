package controller;

import dao.ProductDao;
import dao.WorkToDoInterface.UnitOfWork;
import dao.exception.DaoException;
import dao.impl.jdbc.TransactionManager;
import entity.Product;
import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductAllController extends HttpServlet {
    public static final String ATTRIBUTE_MODEL_TO_VIEW = "productList";
    public static final String PAGE_OK = "productAll.jsp";
    public static final String PAGE_ERROR = "error.jsp";

    TransactionManager txManager;
//    @Inject("productDao")
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
        try {
//            List<Product> model = productDao.selectAll();

            List<Product> model = txManager.doInTransaction(new UnitOfWork<List<Product>, DaoException>() {
                public List<Product> doInTx() throws DaoException {
                    return productDao.selectAll();
                }
            });

            req.setAttribute(ATTRIBUTE_MODEL_TO_VIEW, model);
            // OK
            RequestDispatcher dispatcher = req.getRequestDispatcher(PAGE_OK);
            dispatcher.forward(req, resp);
            return;
        } catch (SQLException | DaoException e){
            /*NOP*/
        }
        //FAIL
        resp.sendRedirect(PAGE_ERROR);
    }
}
