package com.ogasys.controller.action;

import com.ogasys.controller.Action;
import com.ogasys.dao.OgaSysDAOImpl;
import com.ogasys.model.SignInModel;
import com.ogasys.util.Encryptor;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionSignIn implements Action {

    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String view = "index.jsp";

        try 
        {
            String email, pass;
            email = req.getParameter("email");
            pass = req.getParameter("pass");
            pass=Encryptor.encrypt(pass);
            SignInModel sim = new SignInModel(email, pass);
            OgaSysDAOImpl ogasysdb = new OgaSysDAOImpl();
            sim=ogasysdb.SignInValidate(sim);
            if (sim != null)
            {
                req.getSession().setAttribute("userid",sim.getId());
                req.getSession().setAttribute("name",sim.getName());
                view = "index.jsp";
            } else {
                req.getSession().setAttribute("userid", null);
                //view = "index.jsp";
                view = "login_failure.jsp";
            }
        } catch (Exception e) {
            //req.setAttribute("error", e.toString() + ";[ShowCat.java]");
            System.err.println(e.getMessage());
            return "error.jsp?err=" + e.getMessage();
        }
        return view;
    }
}
