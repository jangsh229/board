<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
<title>게시판 목록</title>
</head>
<body>
<div class="in-content">
	<div class="list">	
		<table>
			<thead>
				<tr>
					<th class="seq">번호</th>
					<th class="subject">제목</th>
					<th class="name">작성자</th>
					<th>날짜</th>
					<th class="readCnt">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list">
					<tr class="list-row ${detail.seq == list.seq ? 'select' : ''}">
						<td class="seq">
							<c:if test="${detail.seq eq list.seq}">
								<span>&gt;</span>
							</c:if>
							<c:if test="${detail.seq ne list.seq}">
								<span>${list.seq}</span>
							</c:if>
						</td>
						<td class="subject">
							<a href="#" onclick="fn_goDetail(${list.seq})"><span>${list.subject}</span></a>
							<a href="#" class="ml-05 replyCnt" onclick="fn_goDetail(${list.seq})">${list.replyCount}</a>
						</td>
						<td class="name">
							<span>${list.name}</span>
						</td>
		 				<td>
							<span><fmt:formatDate value="${list.reg_date}" pattern="MM/dd"/></span>
						</td>
						<td>
							<span>${list.readCount}</span>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="flex-mn">
		<div class="fl">
			<a href="#" class="btn-blue btn-square" onclick="fn_list();">목록</a>			
		</div>
		<sec:authorize access="isAuthenticated()">
			<a href="#" class="fr btn-blue btn-square" onclick="fn_goWrite()">작성하기</a>
		</sec:authorize>
	</div>
	<div class="list-search">
		<form class="list-search-frm" id="list-search-frm" >
			<input type="hidden" id="search-pg" name="pg" value="${pageMaker.cri.pg}">
			<select id="search-type" name="type">
				<option value="B" 
						<c:out value='${pageMaker.cri.type eq "B" ? "selected" : ""}' />>전체</option>
				<option value="S"
						<c:out value='${pageMaker.cri.type eq "S" ? "selected" : ""}' />>제목</option>
				<option value="C"
						<c:out value='${pageMaker.cri.type eq "C" ? "selected" : ""}' />>내용</option>
			</select>
			<input type="text" id="search-keyword" name="keyword" size="20" placeholder="검색어를 입력하세요" value="${pageMaker.cri.keyword}">
			<a href="#" class="btn-blue btn-square" id="search-btn" onclick="fn_search()">검색</a>
		</form>
	</div>
	<div class="pagination"> 
	    <ul> 
	        <c:if test="${pageMaker.prev}"> 
	            <li class="page-btn btn-prev">
	                <a class="page-link" href="#" onclick="fn_goList(${pageMaker.startPage-1})">&lt;</a>
	            </li> 
	        </c:if> 
	        <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }"> 
	            <li class="page-btn ${pageMaker.cri.pg == num ? 'active' : '' }">
	                <a class="page-link" href="#" onclick="fn_goList(${num})">${num}</a> 
	            </li>
	        </c:forEach>
	        <c:if test="${pageMaker.next}"> 
	            <li class="page-btn btn-next">
	                <a class="page-link" href="#" onclick="fn_goList(${pageMaker.endPage+1})"> &gt;</a>
	            </li>
	        </c:if> 
	    </ul> 
	</div> 
</div>
<form id="action-frm">
	<input type="hidden" value="${pageMaker.cri.pg}" name="pg">
	<input type="hidden" value="${pageMaker.cri.type}" name="type">
	<input type="hidden" value="${pageMaker.cri.keyword}" name="keyword">
	<input type="hidden" value="" name="seq">
</form>
</body>
</html>