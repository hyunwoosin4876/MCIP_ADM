<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
/*
 * 목록 페이지 이동
 * */
function goPopMgmt() {
	location.href='/mainPopup/popMgmt.do';	
}

/*
 * 수정 페이지 이동
 * */
function goPopModify() {
	goAction("detailFrm","/mainPopup/popModify.do", "POST");
}
</script>

<!-- 다른 페이지로의 이동을 위한 폼 -->
<form id="detailFrm" name="detailFrm">
	<input type="hidden" name="popSeq" id="popSeq" value="${popMgmt.popSeq}">
</form>

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
					<h4 class="title">팝업 상세</h4>
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
								<span class="field_radio"><label><input type="radio" id="" name="" value=""><span>노출</span></label></span>
								<span class="field_radio"><label><input type="radio" id="" name="" value=""><span>미노출</span></label></span>
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
								<span class="field_radio"><label><input type="radio" id="" name="" value=""><span>새창</span></label></span>
								<span class="field_radio"><label><input type="radio" id="" name="" value=""><span>현재창 (부모창)</span></label></span>
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
									<button type="button" class="btn_default bg_red btn_mid mb5">삭제</button>
								</div>
								<input type="file" class="w100">
								
								<p class="txt_type_01">이미지 등록시 팝업 사이즈와 동일한 사이즈로 등록해 주세요.</p>
							</td>							
							<div id="imgContainer" ${popMgmt.imgFileYn eq "N" ? "style='display: none;'" : ""}>
								<img src="${popMgmt.popFilePath}${popMgmt.popUldFileNm}" alt="${popMgmt.popContent}"/>
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
				</table>
				<div class="btnC">
					<button type="button" class="btn_default bd_red btn_bbs" onclick="javascript:goPopMgmt();">취소</button>
					<button type="button" class="btn_default bg_blue btn_bbs" onclick="javascript:goPopModify();">수정</button>					
				</div>
			</div>

			<!-- 내용 : e -->
			</div>
		</div>
		<!-- //content_area -->
	</div>	
</div>
<!-- e : container -->
