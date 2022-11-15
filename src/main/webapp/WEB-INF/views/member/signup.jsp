<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Board 회원가입</title>
</head>
<body>
<div class="in-content"> 
    <div class="block-center">
        <a href="/" class="header__logo">
            <img src="${path}/resources/images/board_logo.png" alt="BOARD Logo">
            <h1>BOARD</h1>
        </a>
        <h2 class="text-center">BOARD에 오신 것을 환영합니다.</h2>
        <p class="text-center mt-05">회원가입에 필요한 기본 정보를 입력해주세요.</p>
        <form id="signup-form">
            <div>
                <div class="row mt-05">
                    <label for="member-id">아이디</label>
                    <div>
                        <input type="text" class="check" id="member-id" placeholder="6~15자 이내로 입력해주세요." name="mem_id">
                    </div>
                    <p></p>
                </div>
                <div class="row mt-05">
                    <label for="member-pwd">비밀번호</label>
                    <div>
                        <input type="password" class="check" id="member-pwd" placeholder="영문, 숫자를 포함한 8자 이상을 입력해주세요." name="mem_pwd">
                    </div>
                    <p></p>
                </div>
                <div class="row mt-05">
                    <label for="member-pwd-chk">비밀번호 확인</label>
                    <div>
                        <input type="password" class="check" id="member-pwd-chk" placeholder="비밀번호를 한 번 더 입력해주세요." name="mem_pwd-chk">
                    </div>
                    <p></p>
                </div>
                <div class="row mt-05">
                    <label for="member-email">이메일</label>
                    <div class="fr">
                        <input type="text" class="check" id="member-email" placeholder="abcd@board.kr" name="mem_email">
                        <a href="#" id="emaiChk" class="btn-grey btn-square mt-2">인증번호 전송</a>
                    </div>
                    <p></p>
                </div>
                <div class="row mt-05">
                    <label for="cert-num">인증번호 확인</label>
                    <div class="fr">
                        <input type="text" class="check" id="cert-num" placeholder="인증번호 입력" disabled="disabled" name="cert-num" maxlength="6">
                        <a href="#" id="certChk" class="btn-grey btn-square mt-2">확인</a>
                        <a href="#" id="emailChk2" class="btn-grey btn-square mt-2">재전송</a>
                    </div>
                    <p></p>
                </div>
                <div class="row mt-05">
                    <label for="member-name">닉네임</label>
                    <div>
                        <input type="text" class="check" id="member-name" placeholder="별명을 한글, 숫자, 알파벳 8자 이하로 입력해주세요." name="mem_name">
                    </div>
                    <p></p>
                </div>
            </div>
        </form>
        <div class="regist-btm">
            <a href="#" class="btn-blue btn-square" id="mem-regist">회원가입</a>
        </div>
        <div class="login-btm mt-05">
            <span>이미 회원이신가요? </span><a href="/member/login">로그인</a>
        </div>
    </div>   
</div>
</body>
</html>