<%@ page import="java.util.*, com.model.Employee" %>

<h2>Employee List</h2>

<%
List<Employee> list = (List<Employee>) request.getAttribute("list");

if (list != null) {
    for (Employee e : list) {
%>

<p><%= e.getEmpno() %> - <%= e.getEmpName() %></p>

<%
    }
}
%>