<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
	<c:if test="${param.fail}">
		<div class="col-md-12 col-xs-12">
			<p style="color: red;">Fail</p>
		</div>
	</c:if>
		<form:form action="/registration" class="form-group" method="post" modelAttribute="user">
				<div class="form-group">
					<input name="login" placeholder="Login" class="form-control" />
					<input name="mail" placeholder="E-mail" class="form-control" />
					<input name="password" type="password" placeholder="Some like ***" class="form-control" />
					<button type="submit" class="btn btn-primary">Ok</button>
				</div>
		</form:form>
	</div>
</div>