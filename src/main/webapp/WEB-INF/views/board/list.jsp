<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>게시판 목록</title>
</head>
<body>
<div class="in-content">
	<div class="list">	
		<table>
			<colgroup>
				<col style="width:5%;">
				<col style="width:78%;">
				<col style="width:10%;">
				<col style="width:5%;">
				<col style="width:5%;">
			</colgroup>	
			<thead>
				<tr>
					<th>번호</th>
					<th class="subject">제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="list">
					<tr>
						<td>${list.seq}</td>
						<td class="subject"><a href="#" onclick="fn_goDetail(${list.seq})">${list.subject}</a></td>
						<td>${list.name}</td>
		 				<td><fmt:formatDate value="${list.reg_date}" pattern="MM/dd"/></td>
						<td>${list.readCount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="flex-mn">
		<a href="#" class="fr btn-blue btn-square" onclick="fn_goWrite()">작성하기</a>
	</div>
	<div class="pagination"> 
	    <ul> 
	        <c:if test="${pageMaker.prev}"> 
	            <li class="page-btn btn-prev">
	                <a class="page-link" href="#" onclick="fn_goList(${pageMaker.startPage-1})">◀</a>
	            </li> 
	        </c:if> 
	        <c:forEach var="num" begin="${pageMaker.startPage }" end="${pageMaker.endPage }"> 
	            <li class="page-btn ${pageMaker.cri.pg == num ? 'active' : '' }">
	                <a class="page-link" href="#" onclick="fn_goList(${num})">${num}</a> 
	            </li>
	        </c:forEach>
	        <c:if test="${pageMaker.next}"> 
	            <li class="page-btn btn-next">
	                <a class="page-link" href="#" onclick="fn_goList(${pageMaker.endPage+1})">▶</a>
	            </li>
	        </c:if> 
	    </ul> 
	</div> 
</div>
<form id="frm">
	<input type="hidden" value="" name="seq" id="seq">	 			
</form>
</body>
</html>