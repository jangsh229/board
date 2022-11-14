<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<header class="header">
        <div class="header__wrap">
            <nav class="header__nav">
	            <a href="/" class="header__logo">
	                <img src="${path}/resources/images/board_logo.png" alt="BOARD Logo">
                    <h1>BOARD</h1>
	            </a>
	            <div class="nav__menu">
	                <a href="#">게시판1</a>
	                <a href="#">게시판2</a>
	                <a href="#">게시판3</a>	            
	            </div>
            </nav>
            <div class="nav__right">
                <sec:authorize access="isAnonymous()">
                    <a href="/member/login" class="btn-white">로그인</a>
                    <a href="/member/signup" class="btn-blue">회원가입</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a href="#" class="btn-blue" style="display: none;">마이페이지</a>
                    <a href="/logout" class="btn-white">로그아웃</a>
                </sec:authorize>
            </div>
        </div>
    </header>
</body>
</html>