<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form action="/admin/subcategory" method="post"
		modelAttribute="form">
		<form:hidden path="id" />
		<form:errors path="*"/>
		<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<input type="hidden" name="${parameter.key}" value="${value}">
			</c:forEach>
		</c:forEach>
		<table>
			<tr>
				<td><form:select path="category">
						<c:forEach items="${categorys}" var="category">
							<c:choose>
								<c:when test="${category.id eq form.category.id}">
									<option value="${category.id}" selected="selected">${category.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${category.id}">${category.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td><form:errors path="name"/></td>
			</tr>
			<tr>
				<td><form:input path="name" placeholder="Subcategory name" /></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>
	</form:form>
	<form:form action="/admin/subcategory" method="get" modelAttribute="filter">
		<c:forEach items="${param}" var="parameter">
			<c:forEach items="${parameter.value}" var="value">
				<c:if test="${parameter.key ne 'search'}">
					<input type="hidden" name="${parameter.key}" value="${value}">
				</c:if>
			</c:forEach>
		</c:forEach>
		<table>
			<tr>
				<td><form:input path="search" placeholder="search"/><input type="submit" value="ok"></td>
			</tr>
		</table>
	</form:form>
	<table>
		<tr>
			<th>Subcategory name</th>
			<th>Category name</th>
		</tr>
		<c:forEach items="${page.content}" var="subcategory">
			<tr>
				<td>${subcategory.name}</td>
				<td>${subcategory.category.name}</td>
				<td><a href="/admin/subcategory/delete/${subcategory.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">delete</a>
				</td>
				<td><a href="/admin/subcategory/update/${subcategory.id}?page=${page.number+1}&size=${page.size}&sort=${param['sort']}&search=${param['search']}">update</a>
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
				<td><a href="?page=1&size=1&sort=${param['sort']}&search=${param['search']}">1</a></td>
				<td><a href="?page=1&size=5&sort=${param['sort']}&search=${param['search']}">5</a></td>
				<td><a href="?page=1&size=10&sort=${param['sort']}&search=${param['search']}">10</a></td>
				<td><a href="?page=1&size=20&sort=${param['sort']}&search=${param['search']}">20</a></td>
			</tr>
			<tr>
				<td><a href="?page=1&size=${page.size}&sort=name&search=${param['search']}">Name asc</a></td>
				<td><a href="?page=1&size=${page.size}&sort=name,desc&search=${param['search']}">Name desc</a></td>
			</tr>
		<tr>
		<td><a href="/admin">Back to admin</a></td>
		</tr>
	</table>
	<div class="col-md-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>"/>
	</div>
</body>
</html>