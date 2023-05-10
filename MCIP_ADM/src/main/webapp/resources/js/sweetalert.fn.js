function do_reset(){
	swal({
		title: "이벤트 리셋",
		text: "이벤트를 리셋 하십니까?",
		icon: "warning",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "리셋되었습니다.",
			});
		}
	});
}

function do_reset_alert(){
	swal({
		title: "알림해제",
		text: "알림을 해제 하십니까?",
		icon: "warning",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "해제되었습니다.",
			});
		}
	});
}

function do_open(){
	swal({
		title: "열기확인",
		text: "문열기를 실행 하십니까?",
		icon: "warning",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "해제되었습니다.",
			});
		}
	});
}

function do_close(){
	swal({
		title: "닫기확인",
		text: "문닫기를 실행 하십니까?",
		icon: "warning",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "닫혔습니다.",
			});
		}
	});
}


function del_msg(){
	swal({
		title: "삭제",
		icon: "warning",
		text: "삭제하겠습니까?",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "삭제되었습니다",
			});
		}
	});
}

function modi_msg(){
	swal({
		title: "수정",
		icon: "warning",
		text: "수정하시겠습니까?",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "수정되었습니다",
			});
		}
	});
}

function add_msg(){
	swal({
		title: "등록",
		icon: "warning",
		text: "등록하시겠습니까?",
		buttons: ["취소", true],
		dangerMode: true,
	})
	.then((val) => {
		if (val == true){
			swal({
				icon: "success",
				title: "등록되었습니다",
			});
		}
	});
}


function error_msg(){
	swal({
	  title: "에러발생!",
	  text: "요청하신 작업이 실패했습니다.",
	  icon: "error",
	});
}
