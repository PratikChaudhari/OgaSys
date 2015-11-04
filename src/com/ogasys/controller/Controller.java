package com.ogasys.controller;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ogasys.util.ObjectCreator;
import java.io.PrintWriter;


public class Controller extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        try 
        {
            String theAction = request.getParameter("action");
            //PrintWriter out = response.getWriter();
            //out.println( this.getServletContext().getRealPath(".") );

            if (theAction == null) {
                theAction = "index";
            }

            Action action = getActionFromConfig(theAction);

            String view = action.execute(request, response);

            RequestDispatcher rd = request.getRequestDispatcher(view);
            System.err.println("\n\n\n\n\n\n" + view + "\n\n\n");
            rd.forward(request, response);
            return;
        } 
        catch (Exception e) 
        {
            System.err.println("\n\n\n\n\n\n" + e.getMessage() + "\n\n\n");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }

    private Action getActionFromConfig(String theAction) throws ServletException, IOException 
    {
        Properties map = new Properties();
        map.load(this.getClass().getClassLoader().getResourceAsStream(ACTION_MAPPING));
        String action_class = map.getProperty(theAction.toLowerCase());
        Action action = (Action) ObjectCreator.createObject(action_class);
        return action;
    }

    private final static String ACTION_MAPPING = "com/ogasys/controller/ActionMapping.properties";

}
