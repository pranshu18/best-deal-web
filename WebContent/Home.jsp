<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Best Deals!</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h3>This is a simple application to get you the best deals for
			any product across the most popular Indian e-commerce web-sites.</h3>
		<h5>It takes a few seconds to load the results, so please be patient!</h5>
		<h5>Created by Pranshu Pandey</h5>
		<br>
		<form action="${pageContext.request.contextPath}/SearchController"
			method="get">
			<div class="form-group">
				<label for="query">Name of item:</label> <input type="text"
					class="form-control" id="query" placeholder="Enter query"
					name="query" required>
			</div>
			<b>Rank results by:</b>
			<div class="radio">
				<label><input type="radio" name="sortby" value="Price"
					checked>Price</label>
			</div>
			<div class="radio">
				<label><input type="radio" name="sortby"
					value="Number of Reviews">Number of Reviews</label>
			</div>
			<div class="radio">
				<label><input type="radio" name="sortby" value="Rating">Rating</label>
			</div>
			<div class="radio">
				<label><input type="radio" name="sortby"
					value="Discount Percentage">Discount Percentage</label>
			</div>
			<br>
			<div class="checkbox">
				<label><input type="checkbox" name="outofstock">
					Display out of stock results?</label>
			</div>
			<br>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
		<br>
		<footer><small>&copy; Copyright 2020, Pranshu Pandey. All Rights Reserved.</small></footer>
	</div>

</body>
</html>