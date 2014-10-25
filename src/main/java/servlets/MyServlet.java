package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/s")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Servlet sample</title></head>");
        out.println("<body>");
        out.println("<p>Запрошенный ресурс: " + request.getRequestURI() + "</p>");
        out.println("<p>Протокол: " + request.getProtocol() + "</p>");
        out.println("<p>Адрес сервера: " + request.getRemoteAddr() + "</p>");
        out.println("</body></html>");
    }
}
