$(function(){
    
    // 회원가입 - 입력창 변경사항 생길 시 회원가입 유효성 검사
    $(".check").on('focusout', function(){
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
        checkCertNum(certCode);
        checkName();

        if($('.vali').length != 0){
            alert('필수항목을 작성해주세요.');
        } else {
            var id = $('#member-id').val();
            var pwd = $('#member-pwd').val();
            var email = $('#member-email').val();
            var name = $('#member-name').val();
            $.ajax({
                type : "POST",
                url : "/member/regist",
                data : {
                    mem_id: id, 
                    mem_pwd: pwd, 
                    mem_email: email, 
                    mem_name: name},
                success: function(result){
                    if(result){
                        alert('가입 완료! 환영합니다! 😊');
                        location.href="/";
                    } else {
                        alert('회원가입에 실패했습니다. 다시 시도해주세요.');
                    }
                },
                error: function(result){
                    console.log(result);
                }
            });
        }
    });

    // 회원가입 - 이메일 인증번호 전송
    var certCode = '';
    $('#emaiChk').click(function(){
        checkEmail();
        var msg = $(this).parent().siblings("p").html();
        if(msg != ''){
            alert('인증을 위해 정확한 이메일 주소를 작성해주세요.');
        } else {
            $.ajax({
                type : "GET",
                url : "/member/certifyEmail",
                async: false,
                data : {email : $('#member-email').val()},
                success: function(data){
                    certCode = data;
                    $('#cert-num').attr("disabled", false);
                    alert('인증번호가 발송되었습니다.');
                },
                error: function(result){
                    console.log(result);
                }
            });
        }
    });

    // 회원가입 - 이메일 인증번호 확인
    $('#certChk').click(function(){
        checkCertNum(certCode);
        if($('#cert-num').parent('div').siblings('p').html() == ''){
            alert('인증이 완료되었습니다.');
        }
    });

    // 회원가입 - 이메일 인증번호 재전송
    $('#emailChk2').click(function(){
        $('#emaiChk').trigger('click');
    });

    // 로그인 - 로그인 버튼 클릭
    $('#mem-login').click(function(){
        $('#login-form').attr("action", "login");
        $('#login-form').submit();
    });
})

// 회원가입 - 항목별 안내문구 표시, 숨김
function onOffVali(checkText, target){
    var realTarget = target.parent("div").siblings("p");
    if(checkText != ""){
        realTarget.html(checkText);
        realTarget.addClass("vali");
        realTarget.slideDown();
    } else {
        realTarget.html("");
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
        $.ajax({
			type : "GET",
			url : "/member/checkAvailability",
            async: false,
			data : {field : "mem_id", value : idVal},
			success: function(result){
                if(!result){
                    checkText = "이미 사용중인 아이디 입니다.";
				}
			},
			error: function(result){
				console.log(result);
			}
		});
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
        $.ajax({
			type : "GET",
			url : "/member/checkAvailability",
            async: false,
			data : {field : "mem_email", value : emailVal},
			success: function(result){
                if(!result){
                    checkText = "이미 사용중인 이메일 입니다.";
				}
			},
			error: function(result){
				console.log(result);
			}
		});
    }
    onOffVali(checkText, $('#member-email'));
}

function checkCertNum(certCode){
    var checkText = '';
    var certNumVal = $('#cert-num').val();
    if(certNumVal.length == 0){
        checkText = "이메일 인증 후 인증번호를 입력해주세요.";
    } else if(certNumVal != certCode){
        checkText = "인증번호가 일치하자 않습니다.";
    } else {
        $('#cert-num').attr("disabled", true);
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
        $.ajax({
			type : "GET",
			url : "/member/checkAvailability",
            async: false,
			data : {field : "mem_name", value : nameVal},
			success: function(result){
                if(!result){
                    checkText = "이미 사용중인 닉네임 입니다.";
				}
			},
			error: function(result){
				console.log(result);
			}
		});
    }
    onOffVali(checkText, $('#member-name'));
}