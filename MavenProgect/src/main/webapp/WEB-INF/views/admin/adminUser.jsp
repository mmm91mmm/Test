<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<form:form action="/admin/user" method="post" modelAttribute="form">
		<form:hidden path="id" />
		<table>
			<tr>
				<td><form:select path="role">
						<c:forEach items="${roles}" var="role">
							<c:choose>
								<c:when test="${role.id eq form.role.id}">
									<option value="${role.id}" selected="selected">${role.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${role.id}">${role.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:errors path="login"/></td>
			</tr>
			<tr>
				<td><form:input path="login" placeholder="User login" /></td>
			</tr>
			<tr>
				<td><form:errors path="mail"/></td>
			</tr>
			<tr>
				<td><form:input path="mail" placeholder="User mail" /></td>
			</tr>
			<tr>
				<td><form:input path="password" placeholder="User password" /></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>User name</th>
			<th>User mail</th>
			<th>User role</th>
		</tr>
		<c:forEach items="${page.content}" var="user">
			<tr>
				<td>${user.login}</td>
				<td>${user.mail}</td>
				<td>${user.role.name}</td>
				<td><a href="/admin/user/delete/${user.id}">delete</a></td>
				<td><a href="/admin/user/update/${user.id}">update</a></td>
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