<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="/resources/css/product.css">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<script>
		$(function() {
			$('select[name=product]').chosen();
		});
	</script>

	<div class="row">
		<div class="col-md-12">
			<nav class="navbar navbar-default">
				<div class="container-fluid">
					<div class="collapse navbar-collapse" id="">
						<ul class="nav navbar-nav">
							<li><a href="/admin/country">Country</a></li>
							<li><a href="/admin/manufacturer">Manufacturer</a></li>
							<li><a href="/admin/colour">Colour</a></li>
							<li><a href="/admin/size">Size</a></li>
							<li><a href="/admin/subcategory">Subcategory</a></li>
							<li><a href="/admin/category">Category</a></li>
							<li class="active"><a href="/admin/product">Product</a><span
								class="sr-only">(current)</span></li>
							<li><a href="/admin/user">User</a></li>
							<li><a href="/admin/role">Role</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>

	<div class="col-md-2 col-xs-12">
		<form:form action="/admin/product" class="form-inline" method="get"
			modelAttribute="filter">
			<custom:hiddenInputs
				excludeParams="min, max, subcategoryIds, colourIds, manufacturerIds, countryIds, _countryIds, _manufacturerIds _colourIds,_subcategoryIds" />
			<div class="form-group">
				<form:input path="min" placeholder="min" class="form-control" />
				<form:input path="max" placeholder="max" class="form-control" />
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
			<div class="form-group">
				<h4>Manufacturer</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${manufacturers}" path="manufacturerIds"
					itemLabel="name" itemValue="id" />
			</div>
			<div class="form-group">
				<h4>Colour</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${colors}" path="colourIds" itemLabel="name"
					itemValue="id" />
			</div>
			<div class="form-group">
				<h4>Country</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${countrys}" path="countryIds"
					itemLabel="name" itemValue="id" />
			</div>
			<div class="form-group">
				<h4>Subcategory</h4>
			</div>
			<div class="form-group">
				<form:checkboxes items="${subcategorys}" path="subcategoryIds"
					itemLabel="name" itemValue="id" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Ok</button>
			</div>
		</form:form>
	</div>

	<div class="col-md-8 col-xs-12">
		<form:form action="/admin/product" method="post" modelAttribute="form">
			<form:hidden path="id" />
			<custom:hiddenInputs
				excludeParams="country, colour, manufacturer, subcategory, name, cost, id" />
			<div class="form-group">
				<label for="name"><form:errors path="name" /></label>
				<form:input path="name" class="form-control" />
				<label for="cost"><form:errors path="cost" /></label>
				<form:input path="cost" class="form-control" />
				<form:select path="country" items="${countrys}" itemLabel="name"
					itemValue="id">
				</form:select>
				<form:select path="colour" items="${colors}" itemLabel="name"
					itemValue="id">
				</form:select>
				<form:select path="manufacturer" items="${manufacturers}"
					itemLabel="name" itemValue="id">
				</form:select>
				<form:select path="subcategory" items="${subcategorys}"
					itemLabel="name" itemValue="id">
				</form:select>
				<button type="submit" class="btn btn-primary">Create</button>
			</div>
		</form:form>

		<form action="/admin/product/addSize" method="post">
			<div class="form-group">
				<div class="col-md-5">
					Product Name <select name="productId">
						<c:forEach items="${page.content}" var="product">
							<option value="${product.id}">${product.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="col-md-5">
					Add size for product <select name="sizeName">
						<c:forEach items="${sizes}" var="size">
							<option value="${size.name}">${size.name}</option>
						</c:forEach>
					</select>

				</div>
				<div class="col-md-2">
					<button type="submit" class="btn btn-primary">Add</button>
				</div>
			</div>
		</form>

		<div class="row">
		<br>
		<br>
		<br>
			<div class="col-md-1">Name</div>
			<div class="col-md-1">Cost</div>
			<div class="col-md-1">Colour</div>
			<div class="col-md-2">Manufacturer</div>
			<div class="col-md-2">Subcategory</div>
			<div class="col-md-1">Country</div>
			<div class="col-md-4">
				<div class="col-md-2"></div>
				<div class="col-md-2"></div>
				<div class="col-md-8">Delete size</div>
			</div>
		</div>
<br>
<br>
		<c:forEach items="${page.content}" var="product">
			<div class="row">
				<div class="col-md-1">${product.name}</div>
				<div class="col-md-1">${product.cost}</div>
				<div class="col-md-1">${product.colour.name}</div>
				<div class="col-md-2">${product.manufacturer.name}</div>
				<div class="col-md-2">${product.subcategory.name}</div>
				<div class="col-md-1">${product.country.name}</div>
				
				<div class="col-md-4">
					<div class="col-md-2">
						<a href="/admin/product/delete/${product.id}<custom:allParams/>">delete</a>
					</div>
					<div class="col-md-2">
						<a href="/admin/product/update/${product.id}<custom:allParams/>">update</a>
					</div>
					<div class="col-md-8">
						<form action="/admin/product/deliteSize/${product.id}"
							method="post">
							<table>
								<tr>
									<td><select name="sizeId">
											<c:forEach items="${product.sizes}" var="sizes">
												<option value="${sizes.id}">${sizes.name}</option>
											</c:forEach>
									</select>
									<input type="submit">
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</c:forEach>
		<div class="col-md-12 text-center">
			<custom:pageable page="${page}" cell="<li></li>"
				container="<ul class='pagination'></ul>" />
		</div>
	</div>
	<div class="col-md-2 col-xs-12">
		<div class="col-md-6">
			<div class="dropdown">
				<button class="btn btn-primary dropdown-toggle" type="button"
					data-toggle="dropdown">
					Sort <span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<custom:sort innerHtml="Country asc" paramValue="country.name" />
					<custom:sort innerHtml="Country desc"
						paramValue="country.name,desc" />
					<custom:sort innerHtml="Subcategory name asc"
						paramValue="subcategory.name" />
					<custom:sort innerHtml="Subcategory name desc"
						paramValue="subcategory.name,desc" />
					<custom:sort innerHtml="Colour name asc"
						paramValue="colour.name" />
					<custom:sort innerHtml="Colour name desc"
						paramValue="colour.name,desc" />
					<custom:sort innerHtml="Manufacturer name asc"
						paramValue="manufacturer.name" />
					<custom:sort innerHtml="Manufacturer name desc"
						paramValue="manufacturer.name,desc" />
				</ul>
			</div>
		</div>
		<div class="col-md-6">
			<custom:size posibleSizes="1,2,5,20" size="${page.size}" title="Size" />
		</div>
	</div>
</body>
</html>