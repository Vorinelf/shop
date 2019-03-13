<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<title>Sport Shop</title>
</head>
<body>
Введите данные для создания товара: <p>
<form method = "POST">
Тип: <input type = "text" name = "type" maxlength = "20" required value = "${product.type}"/> <p>
Имя: <input type = "text" name = "name" maxlength = "20" required value = "${product.name}"/> <p>
Цена: <input type = "number" name = "price" min = "1" max = "500" required value = "${product.price}"/> <p>
<c:choose>
<c:when test = "${product.id > 0}">
<input type="hidden" name = "id" value = "${product.id}"/>
<input type="hidden" name = "command" value = "edit"/>
</c:when>
<c:otherwise>
<input type="hidden" name = "command" value = "create"/>
</c:otherwise>
</c:choose>
<input type = "submit" value = "Сохранить"/>
</form>

Распеределение по бренду: <p>
<c:forEach var = "brand" items= "Nike,Adidas,Puma">
${brand}: <p>
<c:forEach var = "product" items = "${products}">
<c:if test = "${product.name == brand}">
Product: ${product.type} name: ${product.name} price: ${product.price}
<form method = "POST">
<input type = "hidden" name = "id" value = "${product.id}"/>
<input type = "hidden" name = "command" value = "to_edit"/>
<input type="submit" value = "Редактировать"/>
</form>
<form method = "POST">
<input type="hidden" name = "id" value = "${product.id}"/>
<input type="hidden" name = "command" value = "delete"/>
<input type="submit" value = "Удалить"/>
</form>
</c:if>
</c:forEach>
</c:forEach>


Прочие бренды: <p>
<c:forEach var = "product" items = "${products}">
<c:if test = "${(product.name != 'Puma') && (product.name != 'Adidas') && (product.name != 'Nike')}">
Product: ${product.type} name: ${product.name} price: ${product.price}
<form method = "POST">
<input type = "hidden" name = "id" value = "${product.id}"/>
<input type = "hidden" name = "command" value = "to_edit"/>
<input type="submit" value = "Редактировать"/>
</form>
<form method = "POST">
<input type="hidden" name = "id" value = "${product.id}"/>
<input type="hidden" name = "command" value = "delete"/>
<input type="submit" value = "Удалить"/>
</form>
</c:if>
</c:forEach>
</body>
</html>