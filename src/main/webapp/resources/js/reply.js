$(function(){
    // 댓글창 생성
    fn_createEditor();

    // 댓글 작성 버튼 활성화
	if($('.btn-blue-disabled').length == 0){
		$('#replyBtn').removeAttr("disabled");		
	}
})

// 댓글 등록
$(document).on('click', '#replyBtn', function(){
    var rep_content = editor.getData();

    if(rep_content.replace(/\s/g, "").length == 0) {
        alert('내용은 필수 입력값입니다.')
    } else {
        $.ajax({
            type : "POST",
            url : "/reply/regi",
            data : {rep_content : rep_content, board_seq : $('#seq').val()},
            success: function(data){
                if(data == "Y"){
                    fn_refreshReply();
                }
            },
            error: function(data){
                alert("댓글 등록 실패");
                console.log(data);
            }
        });
    }
});

// ctrl + s 단축키로 댓글 등록
var isCtrl = false;
$(document).on('keydown', '#reply-frm .ck-content', function(e){
    if(e.keyCode == 17) {
		isCtrl = true; // ctrl를 누르고 있는 중이면 true
	}
	if(e.keyCode == 83 && isCtrl) {
		e.preventDefault(); // 브라우저의 default 기능 실행 방지
		$('#replyBtn').trigger('click'); 
	}
});
$(document).on('keyup', '#reply-frm .ck-content', function(e){
	isCtrl = false; //ctrl에서 손을 떼면 false
});

// 댓글 삭제
$(document).on('click', '.deleteRep', function(){
    var con = confirm("댓글을 삭제하시겠습니까?");	

    if(con == true) {
        var rep_seq = $(this).parent().parent().siblings('input').val();
        $.ajax({
            type : "POST",
            url : "/reply/delete",
            data : {rep_seq : rep_seq},
            success: function(data){
                if(data == "Y"){
                    fn_refreshReply();
                }
            },
            error: function(data){
                alert("댓글 삭제 실패");
                console.log(data);
            }
        });
    }
});

// 댓글 수정 에디터 생성
let isEditing = false; //다른 댓글 수정 중인지 판단
$(document).on('click', '.updateRep', function(){
    // 다른 댓글을 수정 중이였을 경우 그 댓글은 수정 취소
    if(isEditing){
        $('.undo').trigger('click'); 
    }
	var currentReplyDiv = $(this).parent().parent().next();
    var currentReplyContent = $(this).parent().parent().next().children().html();
    
    // 수정/삭제 span 태그 숨김
    currentReplyDiv.children().css('display', 'none');
    // 기존 댓글 숨김
    $(this).parent().css('display', 'none');
    
    // 수정할 댓글 영역에 에디터 추가
    currentReplyDiv.append('<div id="updateEditor"></div>');
    ClassicEditor
    .create( document.querySelector( '#updateEditor' ), {
        toolbar: [ 'bold', 'italic', 'link', 'numberedList', 'bulletedList', 'undo', 'redo' ]
    } )
    .then( newEditor => {
        editor = newEditor;
        editor.setData(currentReplyContent);
    } )
    .catch( error => {
        console.error( error );
    } );

    // 수정 완료/취소 버튼 추가
    currentReplyDiv.append($('<div/>', {
        class: 'flex-wrap mt-05',
    }).append($('<span/>', {
        class: 'btn-grey btn-square pointer undo',
        text: '취소'
        })).append($('<span/>', {
            class: 'btn-blue btn-square pointer updateReply',
            text: '수정 완료'
        }))
    );

    isEditing = true;
});

// 댓글 수정 취소
$(document).on("click", '.undo', function(){
    var undo = $('.undo');
	// 수정/삭제 span 다시 보여주기
	undo.parent().parent().prev().children('.fr').css('display', 'flex');
	// 기존 댓글 다시 보여주기
	undo.parent().siblings('span').css('display', '');
	
	// editor, 수정완료, 취소 버튼 지우기
	$('#updateEditor').remove();
	undo.parent().prev().remove();
	undo.parent().remove();
    isEditing = false;
});

// 댓글 수정 완료
$(document).on('click', '.updateReply', function(){
	var rep_seq = $(this).parent().parent().siblings('input').val();
	var rep_content = editor.getData();

	if(rep_content.replace(/\s/g, "").length == 0) {
		alert('내용은 필수 입력값입니다.')
	} else {
		$.ajax({
			type : "POST",
			url : "/reply/update",
			data : {rep_seq: rep_seq, rep_content : rep_content},
			success: function(data){
				if(data == "Y"){
					alert("수정 완료");
					fn_refreshReply(); // 댓글 목록 새로고침
				}
			},
			error: function(data){
				alert("댓글 수정 실패");
				console.log(data);
			}
		});
	}
});

// 댓글 새로고침
function fn_refreshReply(){
    // 요청 url에 들어갈 파라미터 설정, 정리
	var seq = $('#seq').val();
	var formData = $('#action-frm');
	formData.find('input[name="seq"]').val(seq);
	fn_clearBlankParam(formData);
	var param = formData.serialize();

    // jQuery.load()로 댓글 목록 구역만 load
	$('#refresh-area').load("/board/detail?"+param+" #refresh-area");
	
    // load 후 댓글 입력란 비우고 커서 포커스 아웃 시키기
	editor.setData('');
	$('.ck-content').blur();
}

// 댓글 에디터 생성
let editor;
function fn_createEditor(){
	$(function(){
        if($('#editor').length > 0 ){
            ClassicEditor
            .create( document.querySelector( '#editor' ), {
                toolbar: [ 'bold', 'italic', 'link', 'numberedList', 'bulletedList', 'undo', 'redo' ]
            } )
            .then( newEditor => {
                editor = newEditor;
            } )
            .catch( error => {
                console.error( error );
            } );
        }
	});
}