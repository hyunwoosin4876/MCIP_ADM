<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script type="text/javascript">
/****************
currPage : 현재 페이지 전역변수
pageSize : 한 페이지에 보여지는 로우 개수 			
***/
var currPage = 0;
var pageSize = 10;

$(document).ready(function() {
	getMainPopupList(1);
});

/*
 * 초기화
 * */
function refresh() {
	$("#frm")[0].reset();
}

/*
 * 검색
 * */
function getMainPopupList(page) {
	/****************
		currPage : 현재 페이지
		limit 	 : 조회 시작 번호 설정 ex) 0번 부터, 10번 부터 조회
		offset   : 노출 개수 ex) 10개 노출
	***/
	currPage 	= page;
	var limit 	= 0;
	var offset 	= pageSize;
	
	if(page !== 1) {
		limit = (page - 1) * offset;
	}
	
	var params = $("#frm").serialize();
	params += "&pageLimit=" + limit;
	params += "&pageOffset=" + offset;
	
	commonAjax("/mainPopup/getPopMgmtListAjax.do", params, 
		function(data){
			refreshBody(data.popMgmtListCnt);
			if(data.popMgmtListCnt > 0) {
				setMainPopupList(data.popMgmtList);
				setMainPopupPaging(data.popMgmtListCnt);
			}
		}
	);
}

/*
 * 팝업 목록 초기화
 * */
function refreshBody(data) {
	$("#totCnt").html(data);
	$("#popupList").html("");
	$(".paging").html("");
	$(".current_page").html("1");
	$(".total_page").html("1");
}

/*
 * 팝업 목록 세팅
 * */
function setMainPopupList(data) {
	var listHtml = "";	
	$(data).each(function(idx, val){
		listHtml += "<tr style=\"cursor:pointer;\" data-popSeq="+ val.popSeq +" onclick=\"javascript:goPopDetail(this);\">";
		listHtml += 	 "<td>" + val.popSeq + "</td>";
		listHtml += 	 "<td>" + val.popTitle + "</td>";
		listHtml += 	 "<td>" + val.regId + "</td>";
		listHtml += 	 "<td>" + stringToDateFormat(val.popUseStartDate) + " ~<br>" + stringToDateFormat(val.popUseEndDate) + "</td>";
		listHtml += 	 "<td>" + (val.popUseYn === "Y" ? "예" : "아니오") + "</td>";
		listHtml += 	 "<td>" + dateToStringF(val.regDt, "yyyy.MM.dd hh:mm") + "</td>";
		listHtml += "</tr>";		
	})
	$("#popupList").html(listHtml);
}

/*
 * 스트링을 날짜 형식으로 포맷만 변경 ex)202001010930 -> 2020.01.01 09:30
 * */
function stringToDateFormat(str) {
	if(!str?.trim()) return;	
    var sYear 	= str.substring(0,4);
    var sMonth 	= str.substring(4,6);
    var sDay 	= str.substring(6,8);
    var sHour 	= str.substring(8,10);
    var sMinute = str.substring(10,12);
    
    return sYear + "." + sMonth + "." + sDay + " " + sHour + ":" + sMinute;
}

/*
 * 팝업 목록 페이징 세팅
 * */
function setMainPopupPaging(data) {
	/****************
		rowCount	 : 총 로우 개수
		displayCount : 화면에 보여주는 페이지번호 개수
		pageSize     : 한 페이지당 레코드 개수
		pageCount    : 실제 페이지 개수
		pagingHtml	 : 세팅 할 html
	***/
	var rowCount 	 = parseInt(data);
	var displayCount = 5;
	var startPageNo  = Math.floor((currPage-1)/displayCount) * displayCount + 1;
	var pageCount    = Math.ceil(rowCount / pageSize);
	var pagingHtml 	 = "";
	
	if (pageCount < 1) {
		return;
	}
	if (currPage > 1) {
		pagingHtml += "<a href=javascript:getMainPopupList("+ 1 +") class='pre_end'>처음</a>";
		pagingHtml += "<a href=javascript:getMainPopupList("+ (currPage - 1) +") class='pre'>이전</a>";
	}
	for (pageIndex = startPageNo; pageIndex < startPageNo + displayCount && pageIndex <= pageCount; pageIndex++){
		if (pageIndex == currPage){
			pagingHtml += "<strong>" + pageIndex + "</strong>";
		}else{
			pagingHtml += "<a href=javascript:getMainPopupList("+ pageIndex +")>" + pageIndex + "</a>";	
		}
	}	
	if (pageCount > currPage) {
		pagingHtml += "<a href=javascript:getMainPopupList("+ (currPage + 1) +") class='next'>다음</a>";
		pagingHtml += "<a href=javascript:getMainPopupList("+ pageCount +") class='next_end'>끝</a>";		
	}
	
	$(".paging").html(pagingHtml);
	$(".current_page").html(currPage);
	$(".total_page").html(pageCount);	
}

/*
 * 페이지 사이즈 설정
 * */
function setPageSize(val) {
	pageSize = val;
	getMainPopupList(1);
}

/*
 * 상세 페이지 이동
 * */
function goPopDetail(data) {
	var popSeq = $(data).attr("data-popSeq");	
	$("#popSeq").val(popSeq);
	goAction("mgmtFrm","/mainPopup/popDetail.do", "POST");
}

/*
 * 등록 페이지 이동
 * */
function goPopRegist() {
	location.href='/mainPopup/popRegist.do';
}
</script>

<!-- 다른 페이지로의 이동을 위한 폼 -->
<form id="mgmtFrm" name="mgmtFrm">
	<input type="hidden" name="popSeq" id="popSeq">
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
				<div class="bundle filter_finder">
					<table class="tbl_filter">					
						<colgroup>
							<col style="width:100px">
							<col>
							<col style="width:100px">
							<col>
						</colgroup>
						<tbody>
							<form id="frm" name="frm">
								<tr>
									<th scope="row">제목</th>
									<td><input type="text" id="popTitle" name="popTitle" value="" title="제목" class="w100"></td>
									<th scope="row">게재기간</th>
									<td>
										<div class="field_period">
											<span class="dateNum">
												<input type="text" id="popUseStartDate" name="popUseStartDate" value="" title="시작">
												<button type="button" class="btn_calendar"><i class="fas fa-sharp fa-light fa-calendar"></i><span class="blind">달력</span></button>
											</span>
											<span class="symbol">~</span>
											<span class="dateNum">
												<input type="text" id="popUseEndDate" name="popUseEndDate" value="" title="종료">
												<button type="button" class="btn_calendar"><i class="fas fa-sharp fa-light fa-calendar"></i><span class="blind">달력</span></button>
											</span>
										</div>
									</td>
								</tr>
							</form>							
						</tbody>
					</table>
					<div class="btnC filter_finder_btn">
						<button type="button" class="btn_default bg_gray btn_bbs" onclick="javascript:refresh();">초기화</button>
						<button type="button" class="btn_default bg_navy btn_bbs" onclick="javascript:getMainPopupList(1);">검색</button>
					</div>
				</div>
	
				<div class="bundle">
					<div class="heading">
						<h4 class="title">팝업 목록</h4>
					</div>
					<div class="bbs_info">
						<div class="part_l">
							<div class="total">
								<p class="cases">전체<span class="count" id="totCnt"></span>건</p>
								<p class="page">페이지<span class="current_page"></span><span class="symbol">/</span><span class="total_page"></span></p>
							</div>
						</div>
						<div class="part_r">
							<select name="" id="" title="페이지별 노출갯수" onchange="javascript:setPageSize(this.value);">
								<option value="10" selected>10개씩</option>
								<option value="20">20개씩</option>
								<option value="30">30개씩</option>
							</select>							
							<button type="button" class="btn_default bg_blue" onclick="javascript:goPopRegist();">등록</button>
						</div>
					</div>
					<table class="tbl_list">
						<caption>목록</caption>
						<colgroup>
							<col style="width:5%">
							<col>
							<col style="width:8%">
							<col style="width:20%">
							<col style="width:8%">
							<col style="width:15%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">번호</th>
								<th scope="col">제목</th>
								<th scope="col">등록자</th>
								<th scope="col">팝업게재기간</th>
								<th scope="col">노출여부</th>
								<th scope="col">등록일시</th>
							</tr>
						</thead>
						<tbody id="popupList">							
						</tbody>
					</table>
					<div class="paging">
					</div>
				</div>
				<!-- 내용 : e -->
			</div>
		</div>
		<!-- //content_area -->
	</div>
</div>
<!-- e : container -->