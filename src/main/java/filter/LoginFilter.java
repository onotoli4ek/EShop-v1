package filter;

import dao.ProductDao;
import dao.UserDao;
import dao.WorkToDoInterface.UnitOfWork;
import dao.exception.DaoBusinessException;
import dao.exception.DaoException;
import dao.impl.jdbc.TransactionManager;
import entity.User;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFilter extends BaseFilter {
    public static final String PARAMETER_REDIRECT_TO = "redirectTo";
    public static final String PARAMETER_LOGIN = "login";
    public static final String PARAMETER_PASSWORD = "password";
    public static final String USER = "user";

    public static final String PAGE_DENY = "index.jsp";
    public static final String PAGE_DEFAULT = "index.jsp";

    TransactionManager txManager;
    UserDao userDao;

    @Override
    public void init(FilterConfig config) throws ServletException {
        super.init(config);
        ApplicationContext ac = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

//        this.userDao = (ProductDao)ac.getBean("productDao");
        this.txManager = (TransactionManager)ac.getBean("txManager");
    }



    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // читаем из запроса login/password
        final String login = request.getParameter(PARAMETER_LOGIN);
        String password = request.getParameter(PARAMETER_PASSWORD);
        // проверяем есть ли такой зарегистрированый пользователь
        if (login != null && password != null) {
            User user = null;
            try {
                user = txManager.doInTransaction(new UnitOfWork<User, DaoException>() {
                    @Override
                    public User doInTx() throws DaoException {
                        return userDao.selectByLogin(login);
                    }
                });
            } catch (SQLException | DaoException e) {
                /*NOP*/
                throw new RuntimeException(e);
            }
            if (user.getPassword().equals(password)) {
                // берем сессию
                HttpSession session = request.getSession(true);
                // сохраняем пользователя в сессии
                session.setAttribute(USER, user);
                //смотрим есть ли в запросе параметр куда возвращать пользователя
                String redirectToEncoded = request.getParameter(PARAMETER_REDIRECT_TO);
                if (redirectToEncoded != null) {
                    String redirectToOriginal = decode(redirectToEncoded);
                    response.sendRedirect(redirectToOriginal);
                } else {
                    response.sendRedirect(PAGE_DEFAULT);
                }

            }
        }
    }
    private static String decode (String getString){
        return getString; // todo: implement this method!
    }
}
