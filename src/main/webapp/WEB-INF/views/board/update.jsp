<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 글수정</title>
</head>
<body>
<div class="in-content">
	<div class="write">
		<form id="frm" class="write">
			<table>
				<tr>
					<td><input type="text" id="subject" name="subject" value="${detail.subject}"></td>
				</tr>
				<tr>
					<td><input type="text" id="name" name="name" value="${detail.name}"></td>
				</tr>
				<tr>
					<td><textarea id="content" name="content">${detail.content}</textarea></td>
				</tr> 
			</table>
			<input type="hidden" id="seq" name="seq" value="${detail.seq}">
			<div class="write-bottom">
				<a href="#" class="btn-blue btn-square" onclick="fn_boardUpdate()">등록</a>
				<a href="#" class="btn-grey btn-square" onclick="fn_goDetail(${detail.seq})">취소</a>
			</div>
		</form>
	</div>
</div>
<form id="action-frm">
	<input type="hidden" value="${cri.pg}" name="pg">
	<input type="hidden" value="${cri.type}" name="type">
	<input type="hidden" value="${cri.keyword}" name="keyword">
	<input type="hidden" value="" name="seq">
</form>
</body>
</html>