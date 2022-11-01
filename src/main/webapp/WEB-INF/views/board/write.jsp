<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdn.ckeditor.com/ckeditor5/35.2.1/classic/ckeditor.js"></script>
<title>게시판 글작성</title>
</head>
<body>
<div class="in-content">
	<div class="write">
		<form id="frm" class="write">
			<div class="rows">
				<div class="row">
					<span>제목</span>
					<input type="text" id="subject" name="subject">
				</div>
			</div>
			<div id="editor" name="content"></div>
			<div class="write-bottom">
				<a href="#" class="btn-blue btn-square" onclick="fn_write()">등록</a>
				<a href="#" class="btn-grey btn-square" onclick="fn_list()">취소</a>
			</div>
		</form>
	</div>
</div>
</body>
<script>
	ClassicEditor
		.create( document.querySelector( '#editor' ) )
		.then( newEditor => {
			editor = newEditor;
		} )
		.catch( error => {
			console.error( error );
		} );	
</script>
</html>