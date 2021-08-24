<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Best Deals!</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<br>
		<div class="row">
<%-- 			<div class="col-md-2">
				<a class="btn btn-primary" role="button"
					href="${pageContext.request.contextPath}">Search another</a>
			</div>
 --%>			<div class="col-md-2">
				<form method="get"
					action="${pageContext.request.contextPath}/DownloadController">
					<input type="hidden" name="query" value="${param.query}"> <input
						type="hidden" name="sortby" value="${param.sortby}">
					<button class="btn btn-primary" type="submit">Download
						results</button>
				</form>
			</div>
			<div class="col-md-2">
				<form action="${pageContext.request.contextPath}/SearchController">
					<label for="rank">Change ranking criteria-:</label> <select
						class="form-control" id="rank" name="sortby">
						<c:choose>
							<c:when test="${param.sortby == 'Price' }">
								<option selected="selected">Price</option>
							</c:when>
							<c:otherwise>
								<option>Price</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${param.sortby == 'Number of Reviews' }">
								<option selected="selected">Number of Reviews</option>
							</c:when>
							<c:otherwise>
								<option>Number of Reviews</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${param.sortby == 'Rating' }">
								<option selected="selected">Rating</option>
							</c:when>
							<c:otherwise>
								<option>Rating</option>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${param.sortby == 'Discount Percentage' }">
								<option selected="selected">Discount Percentage</option>
							</c:when>
							<c:otherwise>
								<option>Discount Percentage</option>
							</c:otherwise>
						</c:choose>
					</select> <input type="hidden" name="query" value="${param.query}">
					<input type="hidden" name="oldsortby" value="${param.sortby}">
					<c:if test="${param.outofstock == 'on'}">
						<input type="hidden" name="outofstock" value="${param.outofstock}">
					</c:if>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<h4>The results are -</h4>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<br>
				<table class="table table-bordered">
					<thead>
						<tr>
							<th scope="col">Name</th>
							<th scope="col">Out of Stock</th>
							<th scope="col">Price (in Rs)</th>
							<th scope="col">Number of Reviews</th>
							<th scope="col">Rating</th>
							<th scope="col">Discount Percentage</th>
							<th scope="col">Seller</th>
							<th scope="col">Link</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${itemResults}">
							<c:if
								test="${(param.outofstock == 'on') || (param.outofstock == null && item.outOfStock == false)}">
								<tr>
									<td>${item.itemName}</td>
									<td>${item.outOfStock ? 'Yes' : 'No'}</td>
									<td>${item.price}</td>
									<td>${item.numberOfReviews}</td>
									<td>${item.rating}</td>
									<td>${item.discountPercentage}%</td>
									<td>${item.seller}</td>
									<td><a class="btn btn-primary btn-sm" href="${item.link}"
										role="button" target="_blank">See on site</a></td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>