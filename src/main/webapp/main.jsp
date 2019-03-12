<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<title>Sport Shop</title>
</head>
<body>
<c:forEach var = "product" items = "${products}">
 <h2> Product: ${product.type} name: ${product.name} price: $${product.price} <p> <h2>
</c:forEach>
</body>
</html>