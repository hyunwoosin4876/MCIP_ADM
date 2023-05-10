function fn_page_move(formName, objName, pageNo) {
	eval(formName +"."+ objName).value = pageNo;
	eval(formName).submit();
}


function changePageSize(val){
	$("#pageSize").val(val);
	$("input[name=pageNo]").val(1);
	$("#frm").submit();
}


//fn_page_display('${userLogDTO.pageSize}', '${userLogDTO.rowCount}', "frm", "pageNo");
function fn_page_display(pageSize, rowCount, formName, objName) {
	
	/****************
	displayCount : 화면에 보여주는 페이지번호 갯수
	pageSize     : 한 페이지당 레코드 갯수
	pageCount    : 실제 페이지 갯수
	pageIndex
	***/
	displayCount = 10;
	currPage    = Number(eval(formName +"."+ objName).value);
	startPageNo = Math.floor((currPage-1)/displayCount) * displayCount + 1;
	pageCount   = Math.ceil(rowCount / pageSize);


	if (pageCount < 1) return;
	
	text = "";
	
	text +- "<ul class='pagination'>"
	
	if (startPageNo > 1) 
		text += "<li class='page-item'><a class='page-link' href=javascript:fn_page_move('"+ formName +"','"+ objName +"'," + 1 +")><i class='icon-first'>처음</i></a></li>";

	if (currPage > 1) 
		text += "<li class='page-item'><a class='page-link' href=javascript:fn_page_move('"+ formName +"','"+ objName +"'," + (currPage - 1) +")><i class='icon-first'>이전</i></a></li>";

	var classTmp = "";
	for (pageIndex=startPageNo; pageIndex<startPageNo+displayCount && pageIndex<=pageCount; pageIndex++){
		if (pageIndex == currPage){
			text += "<li class='page-item'><a class='page-link active' href=javascript:fn_page_move('"+ formName +"','"+ objName +"',"+ pageIndex +")>" + pageIndex + "</a></li>";
		}else{
			text += "<li class='page-item'><a class='page-link' href=javascript:fn_page_move('"+ formName +"','"+ objName +"',"+ pageIndex +")>" + pageIndex + "</a></li>";	
		}
	}
	if (pageCount > currPage) {
		text += "<li class='page-item'><a class='page-link' href=javascript:fn_page_move('"+ formName +"','"+ objName +"',"+ (currPage + 1) +")><i class='icon-first'>다음</i></a></li>";

		if (currPage != pageCount){
			text += "<li class='page-item'><a class='page-link' href=javascript:fn_page_move('"+ formName +"','"+ objName +"',"+ pageCount +")><i class='icon-first'>끝</i></a></li>";
		} 
	}
	
	text += "</ul>";
	
	
	document.write(text);
}

// ajax 용 페이징
function fn_page_display_ajax(pageSize, rowCount, formName, objName, fncName) {
	
	/****************
	displayCount : 화면에 보여주는 페이지번호 갯수
	pageSize     : 한 페이지당 레코드 갯수
	pageCount    : 실제 페이지 갯수
	pageIndex
	***/
	displayCount = 10;
	currPage    = Number(eval(formName +"."+ objName).value);
	startPageNo = Math.floor((currPage-1)/displayCount) * displayCount + 1;
	pageCount   = Math.ceil(rowCount / pageSize);
	
	if (pageCount < 1) return;
	
	text = "";
	
	if (startPageNo > 1) 
		text += "<a href=javascript:"+fncName+"(" + 1 +") class='pre_end'>처음</a>";

	if (currPage > 1) 
		text += "<a href=javascript:"+fncName+"(" + (currPage - 1) + ") class='pre'>이전</a>";

	var classTmp = "";
	for (pageIndex=startPageNo; pageIndex<startPageNo+displayCount && pageIndex<=pageCount; pageIndex++){
		if (pageIndex == currPage){
			text += "<strong>" + pageIndex + "</strong>";
		}else{
			text += "<a href=javascript:"+fncName+"("+ pageIndex +")>" + pageIndex + "</a>";	
		}
	}
	if (pageCount > currPage) {
		text += "<a href=javascript:"+fncName+"("+ (currPage + 1) +") class='next'>다음</a>";

		if (currPage != pageCount){
			text += "<a href=javascript:"+fncName+"("+ pageCount +") class='next_end'>끝</a>";
		} 
	}
	
	
	$('.paginate').html(text);
}