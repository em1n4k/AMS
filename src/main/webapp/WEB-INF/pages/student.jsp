
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix ="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta name = "viewport" content = "width = device-width, initial-scale = 1.0">
    <title></b>Студенты<b></title>
</head>
<body>
    <h2>Список студентов</h2>
    <table border = 1>
        <tr>
            <th>ID</th>
            <th>Имя</th>
            <th>Фамилия</th>
            <th>Возраст</th>
            <th>Почта</th>
            <th>Номер факультета</th>
        </tr>
        <c:forEach var="student" items="${students}">
            <tr>
                <td>${student.id}</td>
                <td>${student.firstName}</td>
                <td>${student.lastName}</td>
                <td>${student.age}</td>
                <td>${student.email}</td>
                <td>${student.facultyNumber}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
