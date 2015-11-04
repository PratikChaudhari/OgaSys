package com.ogasys.controller.action;

import com.ogasys.controller.Action;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionSignInView implements Action {
    public String execute(HttpServletRequest req, HttpServletResponse res) {

        String view = "signin.jsp";
        
        try {
            
        }
        catch(Exception e) {
            //req.setAttribute("error", e.toString() + ";[ShowCat.java]");
            return "error.jsp?err="+e.getMessage();
        }

        return view;

    }
}
