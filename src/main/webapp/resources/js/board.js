function fn_list(){
	location.href = "/board/list";
}

function fn_goList(pg){
	var f = $('#action-frm');
	f.find('input[name="pg"]').val(pg);
	fn_clearBlankParam(f);
	f.attr("action", "/board/list");
	f.submit();
}

function fn_goDetail(seq){
	var f = $('#action-frm');
	f.find('input[name="seq"]').val(seq);
	fn_clearBlankParam(f);
	f.attr("action", "/board/detail");
	f.submit();
}

function fn_goWrite(){
	location.href = "/board/goWrite";
}

function fn_goUpdate(){
	var seq = $('#seq').val();
	var f = $('#action-frm');
	f.find('input[name="seq"]').val(seq);
	fn_clearBlankParam(f);
	f.attr("action", "/board/goUpdate");
	f.attr("method", "POST");
	f.submit();
}

function fn_write(){
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = editor.getData();
	
	if(subject.replace(/\s/g, "").length == 0) {
		alert('제목은 필수 입력값입니다.')
	} else if(content.replace(/\s/g, "").length == 0){
		alert('내용은 필수 입력값입니다.')
	} else {
		$.ajax({
			type : "POST",
			url : "/board/write",
			data : {subject : subject, name : name, content : content},
			success: function(data){
				if(data == "Y"){
					alert("글 등록이 완료되었습니다.");
					location.href = "/board/list";		
				}
			},
			error: function(data){
				alert("실패");
				console.log(data);
			}
		});
	}
};

function fn_boardUpdate(){
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = editor.getData();
	var seq = $("#seq").val();
	
	if(subject.replace(/\s/g, "").length == 0) {
		alert('제목은 필수 입력값입니다.')
	} else if(content.replace(/\s/g, "").length == 0){
		alert('내용은 필수 입력값입니다.')
	} else {
		$.ajax({
			type : "POST",
			url : "/board/update",
			data : {subject : subject, name : name, content : content, seq : seq},
			success: function(data){
				if(data == "Y"){
					alert("글 수정이 완료되었습니다.");
					fn_goDetail(seq);
				}
			},
			error: function(data){
				alert("실패");
				console.log(data);
			}
		});
	}
};

function fn_delete(seq) {
	var con = confirm("이 게시글을 삭제하시겠습니까?");

	if(con == true) {
		$.ajax({
			type : "POST",
			url : "/board/delete",
			data : {seq : $('#seq').val()},
			success: function(data){
				if(data == "Y"){
					alert("삭제되었습니다.")
					location.href = "/board/list";
				}
			},
			error: function(data){
				alert("실패");
				console.log(data);
			}
		});
	}
}

function fn_search(){
	if($('#search-keyword').val()==""){
		alert('검색어 값은 필수입니다.');
	} else {
		$('#search-pg').val('1'); // 검색시 무조건 1페이지로 이동
		var f = $('#list-search-frm')
		f.attr("action", "/board/list");
		f.submit();
	}
}

$(function(){
	// 엔터키로 검색
	$('#search-keyword').keydown(function(key) { 
		if (key.keyCode == 13) {
			fn_search();
		}
	});
});

// url에서 필요없는 파라미터를 삭제해주는 메소드
function fn_clearBlankParam(form){ 
	var keyword = form.find('input[name="keyword"]').val();
	var seq = form.find('input[name="seq"]').val();
	if(keyword == ''){
		form.find('input[name="type"]').remove();
		form.find('input[name="keyword"]').remove();
	}
	if(seq == ''){
		form.find('input[name="seq"]').remove();
	}
}