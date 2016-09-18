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
	<form action="/user/myOrder" method="post">
		<table>
				<tr>
					<td>Colour Name
						<select name="colourId">
							<c:forEach items="${colors}" var="colour">
								<option value="${colour.id}">${colour.colour}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Size Name
						<select name="sizeId">
							<c:forEach items="${sizes}" var="size">
								<option value="${size.id}">${size.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td>Product Name
						<select name="productId">
							<c:forEach items="${products}" var="product">
								<option value="${product.id}">${product.name} |cost ${product.cost}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>