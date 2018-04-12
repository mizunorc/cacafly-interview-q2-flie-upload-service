<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>	
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
	<script src="http://malsup.github.com/jquery.form.js"></script>
</head>
<body>
	<button onClick="logout()" class="btn">Logout</button>
	<div>
		<h3>Profile:</h3>
		<div>Name: <span id="user"></span></div>
		<div>Email: <span id="email"></span></div>
	</div>
	<div>
		<h3>Upload file to Google Drive:</h3>
		<form method="POST" action="/upload" enctype="multipart/form-data">
	        <div>Select files:</div>
	        <input type="file" name="file" accept="image/*"  multiple="multiple">	
	        <input type="submit" value="Upload">
	    </form>
	</div>
	<div>
		<h3>Google Drive Files:</h3>
		<ul>
		<c:forEach var="fileName" items="${fileNames}">
			<li>${fileName}</li>
		</c:forEach>
		</ul>
	</div>
	<script>
		$.get("/user", function(data){
			console.log(data);
			$("#user").html(data.userAuthentication.details.name);
			$("#email").html(data.userAuthentication.details.email);
		});
		var logout = function() {
			$.post("/logout",function(){
				window.location = "/";
			});
			return true;
		}
	</script>
</body>

</html>