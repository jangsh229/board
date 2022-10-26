$(function(){
    
    //입력창 변경사항 생길 시 회원가입 유효성 검사
    $(".check").on('change', function(){
        switch($(this).attr("name")){
            case "mem_id":
                checkId();
                break;
            case "mem_pwd":
                checkPwd();
                break;
            case "mem_pwd-chk":
                checkPwdChk();
                break;
            case "mem_email":
               checkEmail();
                break;
            case "cert-num":
                checkCertNum();
                break;
            case "mem_name":
                checkName();
                break;
        }
    });

    // 회원가입 버튼 클릭 시 유효성 검사
    $('#mem-regist').click(function(){
        checkId();
        checkPwd();
        checkPwdChk();
        checkEmail();
        checkCertNum();
        checkName();

        if($('.vali').length == 0){
            alert('가입 성공!');
        } else {
            alert('필수항목을 작성해주세요.');
        }
    });
})

function onOffVali(checkText, target){
    var realTarget = target.parent("div").siblings("p");
    if(checkText != ""){
        realTarget.html(checkText);
        realTarget.addClass("vali");
        realTarget.slideDown();
    } else {
        realTarget.slideUp(function(){
            realTarget.removeClass("vali");
        });
    }
}

function checkId(){
    var reg_id = /^[a-z0-9_-]{6,15}$/;
    var reg_space = /\s/g;
    var checkText = '';
    var idVal = $('#member-id').val();
    if(idVal.length == 0){
        checkText = "아이디를 입력해주세요.";
    } else if(!reg_id.test(idVal)){
        checkText = "아이디를 6~15자 이내로 입력해주세요.";
    } else if(reg_space.test(idVal)){
        checkText = "공백을 사용할 수 없는 항목입니다.";
    } else {
        //중복검사
    }
    onOffVali(checkText, $('#member-id'));
}

function checkPwd(){
    var reg_pwd = /(?=.*\d)(?=.*[a-zA-ZS]).{8,}/;
    var reg_space = /\s/g;
    var checkText = '';
    var pwdVal = $('#member-pwd').val();
    if(pwdVal.length == 0){
        checkText = "비밀번호를 입력해주세요.";
    } else if(!reg_pwd.test(pwdVal)){
        checkText = "비밀번호는 영문, 숫자를 포함한 8자리 이상이어야 합니다.";
    } else if(reg_space.test(pwdVal)){
        checkText = "공백을 사용할 수 없는 항목입니다.";
    }
    onOffVali(checkText, $('#member-pwd'));
}

function checkPwdChk(){
    var checkText = '';
    var pwdChkVal = $('#member-pwd-chk').val();
    if(pwdChkVal.length == 0){
        checkText = "비밀번호를 한 번 더 입력해주세요.";
    } else if(pwdChkVal != $('#member-pwd').val()){
        checkText = "비밀번호가 일치하지 않습니다.";
    }
    onOffVali(checkText, $('#member-pwd-chk'));
}

function checkEmail(){
    var reg_email = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
    var reg_space = /\s/g;
    var checkText = '';
    var emailVal = $('#member-email').val();
    if(emailVal.length == 0){
        checkText = "이메일을 입력해주세요.";
    } else if(!reg_email.test(emailVal)){
        checkText = "올바른 이메일 형식이 아닙니다.";
    } else if(reg_space.test(emailVal)){
        checkText = "공백을 사용할 수 없는 항목입니다.";
    } else {
        //중복검사
    }
    onOffVali(checkText, $('#member-email'));
}

function checkCertNum(){
    var checkText = '';
    var CertNumVal = $('#cert-num').val();
    if(CertNumVal.length == 0){
        checkText = "인증번호를 입력해주세요.";
    } else {
        //이메일 인증번호 확인
    }
    onOffVali(checkText, $('#cert-num'));
}

function checkName(){
    var reg_name = /^[가-힣a-zA-Z0-9]{2,20}$/;
    var reg_space = /\s/g;
    var checkText = '';
    var nameVal = $('#member-name').val();
    if(nameVal.length == 0){
        checkText = "닉네임을 입력해주세요.";
    } else if(!reg_name.test(nameVal)){
        checkText = "닉네임은 2~8자 이내로 입력해주세요.";
    } else if(reg_space.test(nameVal)){
        checkText = "공백을 사용할 수 없는 항목입니다.";
    } else {
        //중복검사
    }
    onOffVali(checkText, $('#member-name'));
}