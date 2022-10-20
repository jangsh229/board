<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>${detail.subject}</title>
</head>
<body>
<div class="in-content">
	<div class="detail">
		<form id="frm">
			<div class="detail-header bd-btm">
				<div class="flex-wrap">
					<input type="hidden" value="" name="seq" id="seq">	 	
					<div class="fl"><h2>${detail.subject}</h2></div>
					<div class="fr"><span class="text-mid">조회수 ${detail.readCount}</span></div>
				</div>
				<div class="flex-wrap header-bottom">
					<div class="fl"><span class="text-mid">작성자 : ${detail.name}</span></div>
					<div class="fr"><span class="text-sm"><fmt:formatDate value="${detail.reg_date}" pattern="yy.MM.dd hh:mm"/></span></div>
				</div>
			</div>
			<div class="content bd-btm">${detail.content}</div>
		</form>
		<div class="flex-mn">
			<div class="fl">
				<a href="#" class="btn-grey btn-square" onclick="fn_goList(${cri.pg});">목록</a>			
			</div>
			<div class="fr">
				<a href="#" class="btn-grey btn-square" onclick="fn_goUpdate(${detail.seq});">수정</a>
				<a href="#" class="btn-grey btn-square" onclick="fn_delete(${detail.seq});">삭제</a>	
			</div>
		</div>
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