package lang;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.Locale;


public class LangFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        /*NOP*/
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        final String lang = servletRequest.getParameter("lang");
//        HttpServletRequest langRequest = new HttpServletRequestWrapper((HttpServletRequest)servletRequest){
//            @Override
//            public Locale getLocale() {
//                return new Locale(lang);
//            }
//        };
//        System.out.println("> " + new Locale(lang));
//        filterChain.doFilter(langRequest, servletResponse);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        final String path = httpRequest.getServletPath();
        String newPath = path.replace("/ru/", "/").replace("/en", "/");
        HttpServletRequest langRequest = new HttpServletRequestWrapper((HttpServletRequest)servletRequest){
            @Override
            public Locale getLocale() {
                if (path.contains("/ru/")){
                    return new Locale("ru");
                } else if (path.contains("/en/")) {
                    return new Locale("en");
                } else {
                    return new Locale("en");
                }
            }
        };
        System.out.println("fhgfhf");
        filterChain.doFilter(langRequest, servletResponse);



    }

    @Override
    public void destroy() {
        /*NOP*/
    }
}
