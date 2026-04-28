package com.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.EmployeeDAO;

public class DeleteEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int empno = Integer.parseInt(req.getParameter("empno"));

            new EmployeeDAO().deleteEmployee(empno);

            res.sendRedirect("display");

        } catch (Exception e) {
            res.getWriter().println(e);
        }
    }
}