<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script src="${path}/resources/js/board.js"></script>
	<script src="${path}/resources/js/member.js"></script>
	<link rel="stylesheet" href="${path}/resources/css/tiles.css">
	<link rel="stylesheet" href="${path}/resources/css/board.css">
	<link rel="stylesheet" href="${path}/resources/css/member.css">
</head>
<body>
	<div id="body-wrap">
		<div id="content-wrap">
			<header id="header">
				<tiles:insertAttribute name="header"/>
			</header>
		
			<div id="body">
				<div class="rap-content">
					<tiles:insertAttribute name="body"/>
				</div>
			</div>
	 	</div>
		<footer id="footer">
			<tiles:insertAttribute name="footer"/>
		</footer>
	</div>
</body>
</html>