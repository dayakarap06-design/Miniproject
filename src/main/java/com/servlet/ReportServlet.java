package com.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.dao.EmployeeDAO;
import com.model.Employee;

public class ReportServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            String letter = req.getParameter("letter");
            double salary = Double.parseDouble(req.getParameter("salary"));

            List<Employee> all = new EmployeeDAO().getAllEmployees();
            List<Employee> result = new ArrayList<>();

            for (Employee e : all) {
                if (e.getEmpName().startsWith(letter) && e.getBsalary() > salary) {
                    result.add(e);
                }
            }

            req.setAttribute("list", result);
            req.getRequestDispatcher("report_result.jsp").forward(req, res);

        } catch (Exception e) {
            res.getWriter().println(e);
        }
    }
}