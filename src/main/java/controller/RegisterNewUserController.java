package controller;

import dao.ProductDao;
import dao.UserDao;
import dao.WorkToDoInterface.UnitOfWork;
import dao.exception.DaoException;
import dao.exception.NoSuchEntityException;
import dao.impl.jdbc.TransactionManager;
import entity.User;
import org.springframework.context.ApplicationContext;
import validator.UserValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class RegisterNewUserController extends HttpServlet {
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String PARAMETER_EMAIL = "email";
    public static final String PARAMETER_ERROR_MAP = "errorMap";

    public static final String PAGE_REGISTERED = "registered.jsp";
    public static final String PAGE_MORE_INFO = "register.jsp";

    TransactionManager txManager;
    UserDao userDao;
    UserValidator validator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContext ac = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

        this.userDao = (UserDao)ac.getBean("userDao");
        this.txManager = (TransactionManager)ac.getBean("txManager");
        this.validator = (UserValidator)ac.getBean("validator");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String login = req.getParameter(PARAMETER_LOGIN);
        String password = req.getParameter(PARAMETER_PASSWORD);
        final String email = req.getParameter(PARAMETER_EMAIL);

        System.out.println(">> doGet");
        if (login == null && password == null && email == null) {
            req.getRequestDispatcher(PAGE_MORE_INFO).forward(req, resp);
            System.out.println(" null");
            return;
        }
        System.out.println("create user");
        final User tmpUser = new User(-1, login, password, email);
        final Map<String, String> errorMap = validator.validate(tmpUser);
        System.out.println("after validate");
        if (errorMap.isEmpty()){
            User user;
            System.out.println("error map empty");
            try {
                user = txManager.doInTransaction(new UnitOfWork<User, DaoException>() {
                    @Override
                    public User doInTx() throws DaoException {
                        try {
                            if (userDao.selectByLogin(login) != null){
                                errorMap.put("login", "Such login exists");
                            }
                        } catch (NoSuchEntityException e){
                            /*NOF*/
                        }
                        try {
                            if (userDao.selectByEmail(email) != null){
                                errorMap.put("email", "Such email exists");
                            }
                        } catch (NoSuchEntityException e){
                            /*NOF*/
                        }
                        if (errorMap.isEmpty()){

                            System.out.println("before insert");
                            return userDao.insertNew(tmpUser);
                        } else {
                            return null;
                        }

                    }
                });

                if (errorMap.isEmpty()) {
                    System.out.println("GOOD");

                    System.out.println("before insert");
//                    return userDao.insertNew(tmpUser);
                    req.setAttribute("user", user);
                    req.getRequestDispatcher(PAGE_REGISTERED).forward(req, resp);
                }

            } catch (SQLException | DaoException e) {
                System.out.println("problem");
                e.printStackTrace();
            }

        }
            System.out.println("error map not empty");
            req.setAttribute(PARAMETER_LOGIN, login);
            req.setAttribute(PARAMETER_PASSWORD, password);
            req.setAttribute(PARAMETER_EMAIL, email);
            req.setAttribute(PARAMETER_ERROR_MAP, errorMap);

            req.getRequestDispatcher(PAGE_MORE_INFO).forward(req, resp);

    }
}
