package com.servlet;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Date;
import com.dao.EmployeeDAO;
import com.model.Employee;

public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            Employee emp = new Employee(
                Integer.parseInt(req.getParameter("empno")),
                req.getParameter("name"),
                Date.valueOf(req.getParameter("doj")),
                req.getParameter("gender"),
                Double.parseDouble(req.getParameter("salary"))
            );

            new EmployeeDAO().addEmployee(emp);

            res.sendRedirect("display");

        } catch (Exception e) {
            res.getWriter().println(e);
        }
    }
}