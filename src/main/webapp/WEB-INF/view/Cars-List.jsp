



<!DOCTYPE html>

<html>
<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cars</title>
</head>
<body>
<h1>this All Cars</h1>
<table>
    <thead>
    <tr>
        <th>Make</th>
        <th>Model</th>
        <th>Year</th>

        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="car" items="${cars}">
        <tr>
            <td>${car.id}</td>
            <td>${car.make}</td>
            <td>${car.model}</td>
            <td>${car.year}</td>
            <td>${car.price}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
