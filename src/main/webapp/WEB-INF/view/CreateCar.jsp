
<!DOCTYPE html>
<html>
<head>
    <%@ page language="java" contentType="text/html; charset=UTF-8"
             pageEncoding="UTF-8"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="jakarta.tags.core" prefix="c" %>
    <meta charset="UTF-8">
    <title>Cars</title>
</head>
<body>
<h1>Cars</h1>

<h2>Add a new car</h2>
<form:form method="POST" action="/api/cars">
    <table>
        <tr>
            <td><form:label path="make">Make:</form:label></td>
            <td><form:input path="make"/></td>
        </tr>
        <tr>
            <td><form:label path="model">Model:</form:label></td>
            <td><form:input path="model"/></td>
        </tr>
        <tr>
            <td><form:label path="year">Year:</form:label></td>
            <td><form:input path="year"/></td>
        </tr>
        <td><form:label path="price">Year:</form:label></td>
        <td><form:input path="price"/></td>
        <tr>
            <td colspan="2"><input type="submit" value="Add car"/></td>
        </tr>
    </table>
</form:form>

<h2>List of cars</h2>
<table>
    <tr>
        <th>ID</th>
        <th>Make</th>
        <th>Model</th>
        <th>Year</th>
        <th>price</th>
        <th>Action</th>
    </tr>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.id}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>

            <td>
                <form:form method="GET" action="/api/cars/${car.id}">
                    <input type="submit" value="Edit"/>
                </form:form>
                <form:form method="POST" action="/api/cars/${car.id}" onsubmit="return confirm('Are you sure you want to delete this car?')">
                    <input type="hidden" name="_method" value="DELETE"/>
                    <input type="submit" value="Delete"/>
                </form:form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
