package listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SpringApplicationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("/appContext-dao-external-Tx.xml");

        servletContextEvent.getServletContext().setAttribute("applicationContext", ac);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
