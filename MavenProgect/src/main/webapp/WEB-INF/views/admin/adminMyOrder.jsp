<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/admin/myOrder" method="post" modelAttribute="form">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><form:select path="user">
						<c:forEach items="${users}" var="user">
							<c:choose>
								<c:when test="${user.id eq form.user.id}">
									<option value="${user.id}" selected="selected">${user.login}</option>
								</c:when>
								<c:otherwise>
									<option value="${user.id}">${user.login}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:select path="size">
						<c:forEach items="${sizes}" var="size">
							<c:choose>
								<c:when test="${size.id eq form.size.id}">
									<option value="${size.id}" selected="selected">${size.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${size.id}">${size.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:select path="product">
						<c:forEach items="${products}" var="product">
							<c:choose>
								<c:when test="${product.id eq form.product.id}">
									<option value="${product.id}" selected="selected">${product.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${product.id}">${product.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
	<c:forEach items="${myOrders}" var="myOrder">
		<tr>
			<td>${myOrder.id}</td>

			<td><a href="/admin/manufacturer/delete/${myOrder.id}">delete</a>
			</td>
			<td><a href="/admin/manufacturer/update/${myOrder.id}">update</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
				<c:if test="${!page.isFirst()}">
					<td><a href="?page=${page.number}&size=${page.size}&sort=${param['sort']}">Previous</a></td>
				</c:if>
				<c:if test="${!page.isLast()}">
					<td><a href="?page=${page.number+2}&size=${page.size}&sort=${param['sort']}">Next</a></td>
				</c:if>
			</tr>
			<tr>
				<td><a href="?page=1&size=1&sort=${param['sort']}">1</a></td>
				<td><a href="?page=1&size=5&sort=${param['sort']}">5</a></td>
				<td><a href="?page=1&size=10&sort=${param['sort']}">10</a></td>
				<td><a href="?page=1&size=20&sort=${param['sort']}">20</a></td>
			</tr>
			<tr>
				<td><a href="?page=1&size=${page.size}&sort=name">Name asc</a></td>
				<td><a href="?page=1&size=${page.size}&sort=name,desc">Name desc</a></td>
			</tr>
		<tr>
	<tr>
		<td><a href="/admin">Back to admin</a></td>
	</tr>
	</table>
	<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
	</div>
</body>
</html>