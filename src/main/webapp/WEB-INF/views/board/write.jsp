<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 글작성</title>
</head>
<body>
<div class="in-content">
	<div class="write">
		<form id="frm" class="write">
			<table>
				<tr>
					<td><input type="text" placeholder="제목" id="subject" name="subject"></td>
				</tr>
				<tr>
					<td><input type="text" placeholder="작성자" id="name" name="name"></td>
				</tr>
				<tr>
					<td><textarea placeholder="내용" id="content" name="content"></textarea></td>
				</tr> 
			</table>
			<div class="write-bottom">
				<a href="#" class="btn-blue btn-square" onclick="fn_write()">등록</a>
				<a href="#" class="btn-grey btn-square" onclick="fn_list()">취소</a>
			</div>
		</form>
	</div>
</div>
</body>
</html>