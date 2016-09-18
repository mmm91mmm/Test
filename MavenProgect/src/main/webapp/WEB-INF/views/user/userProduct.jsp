<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form action="/user/product" method="post">
		<table>
			<tr>
				<th>Product name</th>
				<th>Product cost</th>
				<th>Product manufacturer</th>
				<th>Product country</th>
				<th>Product subcategory</th>
			</tr>
			<c:forEach items="${products}" var="product">
				<tr>
					<td>${product.name}</td>
					<td>${product.cost}</td>
					<td>${product.manufacturer.name}</td>
					<td>${product.country.name}</td>
					<td>${product.subcategory.name}</td>
					<td><a href="">Bay</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td><a href="/user">Back to user panel</a></td>
			</tr>
		</table>
	</form>
</body>
</html>