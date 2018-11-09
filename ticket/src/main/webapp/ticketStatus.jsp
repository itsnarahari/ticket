<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Tickets</title>
</head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Ticket Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Mobile</th>
                <th>Problem type</th>
                <th>Problem Desc</th>
                <th>Department</th>
                <th>Status</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${ticket}" var="ticket">
                <tr>
                    <td><c:out value="${ticket.ticketId}" /></td>
                    <td><c:out value="${ticket.name}" /></td>
                    <td><c:out value="${ticket.email}" /></td>
                    <td><c:out value="${ticket.mobile}" /></td>
                    <td><c:out value="${ticket.problem_type}" /></td>
                    <td><c:out value="${ticket.problem_desc}" /></td>
                    <td><c:out value="${ticket.dept}" /></td>
                    <td><c:out value="${ticket.status}" /></td>
                    <td><a href="UserController?action=edit&userId=<c:out value="${user.userid}"/>">Update</a></td>
                    <td><a href="UserController?action=delete&userId=<c:out value="${user.userid}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="UserController?action=insert">Add User</a></p>
</body>
</html>