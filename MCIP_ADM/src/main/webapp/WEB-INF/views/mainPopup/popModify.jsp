<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
/*
 * 상세 페이지 이동
 * */
function goPopDetail() {
	goAction("frm","/mainPopup/popDetail.do", "POST");
}

/*
 * 폼 밸리데이션 체크
 * */
function frmValidateChk() {
	var msg = "";
	var isBool = true;
		
	if(!$("#popTitle").val()?.trim()) {
		msg += "제목을 입력해주세요.\n";
		isBool = false;
	}
	if(!$("#popSizeWidth").val()?.trim()) {
		msg += "팝업사이즈(가로)를 입력해주세요.\n"; 
		isBool = false;	
	}
	if(!$("#popSizeHeight").val()?.trim()) {
		msg += "팝업사이즈(세로)를 입력해주세요.\n";
		isBool = false;	
	}
	if(!$("#popLocationX").val()?.trim()) {
		msg += "팝업위치(상단)을 입력해주세요.\n";
		isBool = false;
	}
	if(!$("#popLocationY").val()?.trim()) {
		msg += "팝업위치(왼쪽)을 입력해주세요.\n";
		isBool = false;
	}
	if(!$("#popUseStartDate").val()?.trim()) {
		msg += "팝업노출기간(시작)을 입력해주세요.\n";
		isBool = false;
	}
	if(!$("#popUseEndDate").val()?.trim()) {
		msg += "팝업노출기간(종료)을 입력해주세요.\n";
		isBool = false;
	}
	if(!$("#popContent").val()?.trim()) {
		msg += "내용을 입력해주세요.\n";
		isBool = false;
	}	
	if(!isBool) {
		alert(msg);
		return false;
	}
	
	return true;
}

/*
 * 수정
 * */
function popModify() {
	if(frmValidateChk()) {
		commonAjaxSubmit("/mainPopup/popModifyAjax.do", "frm", "json", succRegListener);
	}
}

/*
 * 수정 성공 콜백
 * */
function succRegListener(data) {
	if(data.result.msgCode === '0000') {
		goAction("frm","/mainPopup/popDetail.do", "POST");
	}
}

/*
 * 이미지 업로드 후 썸네일 보기
 * */
function showThumbImg(event) {
	var reader = new FileReader();			
	reader.onload = function(event) {
		var img = new Image();
		img.src = event.target.result;
		$("#thumbImg").attr("src", img.src);		
		img.onload = function() {
            $("#popSizeWidth").val($("#thumbImg").width());
    		$("#popSizeHeight").val($("#thumbImg").height());
		}
	}
	reader.readAsDataURL(event.target.files[0]);
	$("#updImgFileYn").val("Y");
	$("#imgContainer").show();
}

/*
 * 이미지 삭제 
 * */
function delImg() {
	var agent = navigator.userAgent.toLowerCase();	
	if ((navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1)) {
	    $("#image").replaceWith($("#image").clone(true));
	}else{
	    $("#image").val("");
	}	
	$("#thumbImg").attr("src", "");	
	$("#updImgFileYn").val("Y");
}
</script>

<!-- s : container -->
<div id="container" class="sub_container">
	<div class="inner">
		<div class="content_area">
			<div class="location">
				<h3 class="page_title">팝업관리</h3>
				<p class="local"><span class="home">HOME</span><span>사이트관리</span><span class="current">팝업관리</span></p>
			</div>
			<div class="content">
			<!-- 내용 : s -->

			<div class="bundle">
				<div class="heading">
					<h4 class="title">팝업 수정</h4>
				</div>
				<table class="tbl_view">
					<caption>상세</caption> 						
					<colgroup>
						<col style="width:200px">
						<col>
						<col style="width:200px">
						<col>
					</colgroup>
					<tbody>
					<form name="frm" id="frm" enctype="multipart/form-data">
						<input type="hidden" name="updImgFileYn" id="updImgFileYn" value="N">
						<input type="hidden" name="popUldFileNm" id="popUldFileNm" value="${popMgmt.popUldFileNm}">
						<input type="hidden" name="popOrgnlFileNm" id="popOrgnlFileNm" value="${popMgmt.popOrgnlFileNm}">
						<input type="hidden" name="popFileSize" id="popFileSize" value="${popMgmt.popFileSize}">
						<input type="hidden" name="popFilePath" id="popFilePath" value="${popMgmt.popFilePath}">						
						<input type="hidden" name="popSeq" id="popSeq" value="${popMgmt.popSeq}">
						<tr>
							<th scope="row">작성자</th>
							<td>${popMgmt.regId}</td>
							<th scope="row">등록/최종수정 일시</th>
							<c:choose>
								<c:when test="${popMgmt.updDt == '' || empty popMgmt.updDt}">
									<td><fmt:formatDate value="${popMgmt.regDt}" pattern="yyyy.MM.dd HH:mm"/></td>
								</c:when>
								<c:otherwise>
									<td><fmt:formatDate value="${popMgmt.updDt}" pattern="yyyy.MM.dd HH:mm"/></td>
								</c:otherwise>
							</c:choose>
						</tr>
						<tr>
							<th scope="row">팝업제목<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td><input type="text" id="popTitle" name="popTitle" value="${popMgmt.popTitle}" class="w100"></td>
							<th scope="row">노출여부<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td>
							${popUseYn}
							<span class="field_radio"><label><input type="radio" id="popUseYnY" name="popUseYn" value="Y" ${popMgmt.popUseYn eq "Y" ? "checked" : ""}><span>노출</span></label></span>
							<span class="field_radio"><label><input type="radio" id="popUseYnN" name="popUseYn" value="N" ${popMgmt.popUseYn eq "N" ? "checked" : ""}><span>미노출</span></label></span>
							</td>
						</tr>
						<tr>
							<th scope="row">팝업사이즈<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td>								
								<input type="text" id="popSizeWidth" name="popSizeWidth" value="${popMgmt.popSizeWidth}" placeholder="가로" class="wpx100">
								<input type="text" id="popSizeHeight" name="popSizeHeight" value="${popMgmt.popSizeHeight}" placeholder="세로" class="wpx100">
							</td>
							<th scope="row">팝업위치<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td>
								<input type="text" id="popLocationX" name="popLocationX" value="${popMgmt.popLocationX}" placeholder="상단" class="wpx100">
								<input type="text" id="popLocationY" name="popLocationY" value="${popMgmt.popLocationY}" placeholder="왼쪽" class="wpx100">								
								<p class="txt_type_01">팝업위치는 상단/좌측 기준 위치</p>
							</td>
						</tr>
						<tr>
							<th scope="row">노출기간<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td>
								<div class="field_period">
									<span class="dateNum">										
										<input type="text" id="popUseStartDate" name="popUseStartDate" value="${popMgmt.popUseStartDate}" title="시작">
										<button type="button" class="btn_calendar"><i class="fas fa-sharp fa-light fa-calendar"></i><span class="blind">달력</span></button>
									</span>
									<span class="symbol">~</span>
									<span class="dateNum">
										<input type="text" id="popUseEndDate" name="popUseEndDate" value="${popMgmt.popUseEndDate}" title="종료">										
										<button type="button" class="btn_calendar"><i class="fas fa-sharp fa-light fa-calendar"></i><span class="blind">달력</span></button>
									</span>
								</div>
							</td>
							<th scope="row">링크타입<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td>
								<span class="field_radio"><label><input type="radio" id="popLinkType1" name="popLinkType" value="1" ${popMgmt.popLinkType eq 1 ? "checked" : ""}><span>새창</span></label></span>
								<span class="field_radio"><label><input type="radio" id="popLinkType2" name="popLinkType" value="2" ${popMgmt.popLinkType eq 2 ? "checked" : ""}><span>현재창 (부모창)</span></label></span>
							</td>
						</tr>
						<tr>
							<th scope="row">링크URL</th>
							<td colspan="3">
								<input type="text" id="popLinkUrl" name="popLinkUrl" value="${popMgmt.popLinkUrl}" class="w100">
								<p class="txt_type_01">링크 주소를 입력하시면 해당 주소의 웹페이지로 이동합니다.</p>
							</td>
						</tr>
						<tr>
							<th scope="row">팝업이미지<span class="ico_necessary"><span class="blind">필수</span></span></th>
							<td colspan="3">
								<div class="btnR">
									<button type="button" class="btn_default bg_red btn_mid mb5" onclick="javascript:delImg();">삭제</button>
								</div>
								<input type="file" class="w100" name="image" id="image" onchange="javascript:showThumbImg(event);"/>
								
								<p class="txt_type_01">이미지 등록시 팝업 사이즈와 동일한 사이즈로 등록해 주세요.</p>
							</td>							
							<div id="imgContainer" ${popMgmt.imgFileYn eq "N" ? "style='display: none;'" : ""}>																
								<img id="thumbImg" src="${popMgmt.popFilePath}${popMgmt.popUldFileNm}" alt="${popMgmt.popContent}" />								
							</div>
							
						</tr>
						<tr>
							<th scope="row">내용입력</th>
							<td colspan="3">
								<textarea name="popContent" id="popContent" style="width:100%;height:300px;">${popMgmt.popContent}</textarea>
								<p class="txt_type_01">팝업 이미지를 등록한 경우, 작성한 내용은 이미지의 대체 텍스프로 사용 됩니다.</p>
							</td>
						</tr>
					</tbody>
					</form>
				</table>
				<div class="btnC">
					<button type="button" class="btn_default bd_red btn_bbs" onclick="javascript:goPopDetail();">취소</button>					
					<button type="button" class="btn_default bg_navy btn_bbs" onclick="javascript:popModify();">저장</button>
				</div>
			</div>

			<!-- 내용 : e -->
			</div>
		</div>
		<!-- //content_area -->
	</div>	
</div>
<!-- e : container -->
