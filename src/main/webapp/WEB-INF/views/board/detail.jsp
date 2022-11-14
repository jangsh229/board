<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
	<script src="https://cdn.ckeditor.com/ckeditor5/35.2.1/classic/ckeditor.js"></script>
	<script src="${path}/resources/js/reply.js"></script>
<title>${detail.subject}</title>
</head>
<body>
<div class="in-content">
	<div class="detail">
		<form id="frm">
			<div class="detail-header bd-btm">
				<div class="flex-wrap">
					<div class="fl"><h2>${detail.subject}</h2></div>
				</div>
				<div class="flex-wrap mb-10 header-btm border-btm">
					<div class="fl"><span class="text-mid">작성자 : ${detail.name}</span></div>
					<div class="fr"><span class="text-sm font-grey"><fmt:formatDate value="${detail.reg_date}" pattern="yy.MM.dd HH:mm"/></span></div>
					<div class="fr ml-10">
						<img src="${path}/resources/images/view.png">
						<span class="text-sm ml-03 font-grey"> ${detail.readCount}</span>
					</div>
				</div>
			</div>
			<div class="content border-btm">${detail.content}</div>
			<input type="hidden" value="${detail.seq}" name="seq" id="seq">
		</form>
		<div class="flex-mn">
			<div class="fl">
				<a href="#" class="btn-blue btn-square" onclick="fn_goList(${cri.pg});">목록</a>			
			</div>
			<c:if test="${isWriter}">
				<div class="fr">
					<a href="#" class="btn-grey btn-square" onclick="fn_goUpdate();">수정</a>
					<a href="#" class="btn-grey btn-square" onclick="fn_delete();">삭제</a>	
				</div>
			</c:if>
		</div>
		<div class="mt-20" id="refresh-area">
			<div class="fl text-mid header-btm border-btm2">
				<img src="${path}/resources/images/reply.png">
				<span class="ml-03 text-mid">댓글 <strong>${detail.replyCount}</strong>개</span>
			</div>
			<ul>
				<c:forEach var="re" items="${reply}"> 
					<li class="border-btm ${login != null && login.mem_seq == re.mem_seq ? 'reply-mine' : ''}">
						<div class="mb-05 flex-wrap">
							<div class="fl">
								<span class="text-mid font-bold">${re.mem_name}</span>
								<span class="text-sm ml-10 font-grey"><fmt:formatDate value="${re.reg_date}" pattern="yyyy.MM.dd HH:mm"/></span>
							</div>
							<c:if test= "${login != null && login.mem_seq == re.mem_seq && re.deleted == 0}">
							<div class="fr">
								<span class="text-sm pointer updateRep">수정</span>
								<span class="text-sm ml-10 pointer deleteRep">삭제</span>
							</div>
							</c:if>
						</div>
						<div>
							<span class="reply-content">${re.rep_content}</span>
						</div>
						<input type="hidden" value="${re.rep_seq}" id="repSeq">
					</li>
	        	</c:forEach>
			</ul>
		</div>
		<div class="mt-20 reply">
			<form id="reply-frm">
				<sec:authorize access="isAnonymous()">
					<textarea class="reply font-grey" readonly>댓글을 쓰려면 로그인이 필요합니다.</textarea>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<div id="editor" name="content"></div>
				</sec:authorize>
				<button type="button" disabled class="btn-blue${login == null ? '-disabled' : ' pointer' } mt-05 btn-submit" id="replyBtn">댓글 쓰기 (단축키 Ctrl + S)</button>
			</form>
		</div>
	</div>
</div>
<jsp:include page="/WEB-INF/views/board/list.jsp" />
<form id="action-frm">
	<input type="hidden" value="${cri.pg}" name="pg">
	<input type="hidden" value="${cri.type}" name="type">
	<input type="hidden" value="${cri.keyword}" name="keyword">
	<input type="hidden" value="" name="seq">
</form>
</body>
</html>