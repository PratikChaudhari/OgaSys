package com.ogasys.filters;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Authenticator implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String action = req.getParameter("action");
        Object userid= req.getSession().getAttribute("userid");
        String name = (String) req.getSession().getAttribute("name");

        if (action == null) 
        {
            action = "index";
            chain.doFilter(request, response);
        }
        //if ( (user != null && !user.equals("")) 
        if (userid != null
                || action.equals("signin") 
                || action.equals("signinsubmit") 
                || action.equals("signup") 
                || action.equals("signupsubmit")
                || action.equals("forgotpassword")
                || action.equals("forgotpasswordsubmit")) 
        {
            chain.doFilter(request, response);
        } 
        else 
        {
            req.getSession().setAttribute("userid", null);
            //HttpServletResponse res = (HttpServletResponse) response;
            //res.sendRedirect("login_failure.jsp");

            RequestDispatcher rd = req.getRequestDispatcher("signin.jsp");
            rd.forward(request, response);
        }
        //chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
    private FilterConfig filterConfig = null;
}
