function fn_list(){
	location.href = "/board/list";
}

function fn_goDetail(seq){
	location.href = "/board/detail?seq="+seq;
}

function fn_goWrite(){
	location.href = "/board/goWrite";
}

function fn_goUpdate(seq){
	$('#seq').val(seq);

	var f = $('#frm');
	f.attr("action", "/board/goUpdate");
	f.attr("method", "POST");
	f.submit();
}

function fn_write(){
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = $("#content").val();
	
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
};

function fn_boardUpdate(){
	var subject = $("#subject").val();
	var name = $("#name").val();
	var content = $("#content").val();
	var seq = $("#seq").val();
	
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
};

function fn_delete(seq) {
	var con = confirm("이 게시글을 삭제하시겠습니까?");

	if(con == true) {
		$.ajax({
			type : "POST",
			url : "/board/delete",
			data : {seq : seq},
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

function fn_goList(pg){
	location.href = "/board/list?pg="+pg;
}