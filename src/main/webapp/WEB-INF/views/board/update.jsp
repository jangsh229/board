<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdn.ckeditor.com/ckeditor5/35.2.1/classic/ckeditor.js"></script>
<title>게시판 글수정</title>
</head>
<body>
<div class="in-content">
	<div class="write">
		<form id="frm" class="write">
			<div class="rows">
				<div class="row">
					<span>제목</span>
					<input type="text" id="subject" name="subject" value="${detail.subject}">
				</div>
				<div class="row">
					<span>작성자</span>
					<input type="text" id="name" name="name" value="${detail.name}">
				</div>
			</div>
			<div id="editor" name="content"></div>
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
<input type="hidden" id="content" name="current-content" value="${detail.content}">
</body>
<script>
	//글작성 에디터(CKEditor) 생성
	let editor;
	ClassicEditor
		.create( document.querySelector( '#editor' ) )
		.then( newEditor => {
			editor = newEditor;
			editor.setData($("#content").val());
		} )
		.catch( error => {
			console.error( error );
		} );	
</script>
</html>