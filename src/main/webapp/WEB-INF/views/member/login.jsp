<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>Board 로그인</title>
</head>
<body>
<div class="in-content"> 
    <div class="block-center">
        <a href="/" class="header__logo">
            <h1>💙BOARD</h1>
        </a>
        <h2 class="text-center mt-05">BOARD에 오신 것을 환영합니다.</h2>
        <p class="text-center mt-05"></p>
        <form id="login-form">
            <div>
                <div class="row mt-05">
                    <label for="member-id">아이디</label>
                    <div>
                        <input type="text" id="member-id" name="mem_id" placeholder="아이디를 입력해주세요.">
                    </div>
                    <p></p>
                </div>
                <div class="row mt-05">
                    <label for="member-pwd">비밀번호</label>
                    <div>
                        <input type="password" id="member-pwd" name="mem_pwd" placeholder="비밀번호를 입력해주세요.">
                    </div>
                    <p></p>
                </div>
                <c:if test="${error}">
                    <div class="row mt-10">
                        <p class="valiAlert">${exception}</p>
                    </div>
                </c:if>
            </div>
        </form>
        <div class="regist-btm">
            <a href="#" class="btn-blue btn-square" id="mem-login">로그인</a>
        </div>
        <div class="login-btm mt-05">
            <span>아직 회원이 아니신가요? </span><a href="/member/signup">회원가입</a>
        </div>
    </div>   
</div>
</body>
</html>