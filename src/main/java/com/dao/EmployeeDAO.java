package com.dao;

import java.sql.*;
import java.util.*;
import com.model.Employee;

public class EmployeeDAO {

    private String url = "jdbc:mysql://localhost:3306/EmployeeDB";
    private String user = "root";
    private String pass = "password";

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, pass);
    }

    // ADD
    public void addEmployee(Employee emp) throws Exception {
        String sql = "INSERT INTO Employee VALUES (?, ?, ?, ?, ?)";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, emp.getEmpno());
        ps.setString(2, emp.getEmpName());
        ps.setDate(3, emp.getDoj());
        ps.setString(4, emp.getGender());
        ps.setDouble(5, emp.getBsalary());

        ps.executeUpdate();
        con.close();
    }

    // UPDATE
    public void updateEmployee(Employee emp) throws Exception {
        String sql = "UPDATE Employee SET EmpName=?, DoJ=?, Gender=?, Bsalary=? WHERE Empno=?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, emp.getEmpName());
        ps.setDate(2, emp.getDoj());
        ps.setString(3, emp.getGender());
        ps.setDouble(4, emp.getBsalary());
        ps.setInt(5, emp.getEmpno());

        ps.executeUpdate();
        con.close();
    }

    // DELETE
    public void deleteEmployee(int empno) throws Exception {
        String sql = "DELETE FROM Employee WHERE Empno=?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, empno);
        ps.executeUpdate();
        con.close();
    }

    // GET ONE
    public Employee getEmployee(int empno) throws Exception {
        Employee emp = null;

        String sql = "SELECT * FROM Employee WHERE Empno=?";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ps.setInt(1, empno);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            emp = new Employee(
                rs.getInt(1),
                rs.getString(2),
                rs.getDate(3),
                rs.getString(4),
                rs.getDouble(5)
            );
        }

        con.close();
        return emp;
    }

    // 🔴 DISPLAY ALL (IMPORTANT)
    public List<Employee> getAllEmployees() throws Exception {

        List<Employee> list = new ArrayList<>();

        String sql = "SELECT * FROM Employee";
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Employee(
                rs.getInt("Empno"),
                rs.getString("EmpName"),
                rs.getDate("DoJ"),
                rs.getString("Gender"),
                rs.getDouble("Bsalary")
            ));
        }

        con.close();
        return list;
    }
}