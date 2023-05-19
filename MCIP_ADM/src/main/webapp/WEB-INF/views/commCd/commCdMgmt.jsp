<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<script>
let upList;
let dwList;

/*
 * page Ready
 * */
$( document ).ready(function() {
	getUpCommCdList();
});



/*
 * 상위코드 조회
 * */
function getUpCommCdList() {
	
	var params = $("#frm").serialize();
	
	console.log(params);
	commonAjax("/commCd/upCommCdListAjax.do", params,
		function(data){
			upList = data.commCdList;
			setUpList(data.commCdList);
		}
	);
}

/*
 * 하위코드 조회
 * */
function getDwCommCdList(commCd) {
	var params = $("#frm").serialize();
	    params += "&commCd="+commCd;
	console.log(params);
	commonAjax("/commCd/dwCommCdListAjax.do", params,
		function(data){
			dwList = data.commCdList;
			setDwList(data.commCdList);
		}
	);
}

/*
 * 상위코드 그리드셋
 * */
function setUpList(data){
	var row = "";
	$(data).each(function(idx, val){
		row += "<tr>";
		row += 	 "<td class=\"tc\" onClick='upListSelRow("+idx+")'>"+val.commCd+"</td>";
		row += 	 "<td onClick='upListSelRow("+idx+")'>"+val.cdNm+"</td>";
		row += 	 "<td  onClick='upListSelRow("+idx+")'class=\"tc\">"+val.cdUseYn+"</td>";
		row += 	 "<td class=\"tc\"><button type=\"button\" data-toggle=\"modal\" data-target=\"#modal_edit\" class=\"btn_default bg_orange\" onClick=\"popUpdModal("+idx+",'U','U')\">수정</button></td>";
		row += "</tr>";
	})
	
	$("#upCommCdList").html(row);
	
}


/*
 * 하위코드 그리드셋
 * */
function setDwList(data){
	var row = "";
	$(data).each(function(idx, val){
		row += "<tr>";
		row += 	 "<td class=\"tc\" onClick='upListSelRow("+idx+")'>"+val.commCd+"</td>";
		row += 	 "<td onClick='upListSelRow("+idx+")'>"+val.cdNm+"</td>";
		row += 	 "<td  onClick='upListSelRow("+idx+")'class=\"tc\">"+val.cdUseYn+"</td>";
		row += 	 "<td class=\"tc\"><button type=\"button\" data-toggle=\"modal\" data-target=\"#modal_edit\" class=\"btn_default bg_orange\" onClick=\"popUpdModal("+idx+",'D','U')\">수정</button></td>";
		row += "</tr>";
	})
	
	$("#dwCommCdList").html(row);

}

function upListSelRow(idx) {
	$("#upCd").html(upList[idx].commCd);
	$("#upCdNm").html(upList[idx].cdNm);
	
	$("#parentCdM").val(upList[idx].commCd);
	$("#parentCdNmM").val(upList[idx].cdNm);
	
	
	getDwCommCdList(upList[idx].commCd);
}

/*
 * 수정, 등록창 띄우기
 * upDwType : 상위, 하위코드 구분
 * insUpdType : 등록, 수정 구분
 */
function popUpdModal(idx, upDwType, insUpdType) {
	$("#upDwType").val(upDwType);
	$("#insUpdType").val(insUpdType);
	
	//상위코드일 경우 정렬 안보여줌
	if(upDwType == "U" && insUpdType == "U") {
		$("#commCdM").val(upList[idx].commCd);
		$("#orderTd").hide();
	}else if(upDwType == "D" && insUpdType == "U") {
		$("#commCdM").val(dwList[idx].commCd);
		$("#orderTd").show();
	}
	
	//수정일 경우에만 상세조회
	if($("#insUpdType").val() == "U") {
		var params = $("#modal_frm").serialize();
		
		console.log("#####getCommCdDetailAjax params : " + params);
		commonAjax("/commCd/getCommCdDetailAjax.do", params,
			function(data){
				console.log("####getCommCdDetailAjax retrun  : " + data);
				setDetail(data.commCdDetail);
			}
		);
	}
	
}


/*
 * 상세보기 셋팅
 */
function setDetail(detailData) {
	
	//$("#commCdM").val(detailData.commCd);
	$("#cdNmM").val(detailData.cdNm);
	$("#parentCdM").val(detailData.parentCd);
	$("#parentCdNmM").val(detailData.parentCdNm);
	$("#cdOrderM").val(detailData.cdOrder);
	$("#cdUseYnM").val(detailData.cdUseYn);

}

/*
 * 저장
 */
function saveCommCd() {
	if($("#insUpdType").val() == "I") {
		insertCommCd();
	}else if($("#insUpdType").val() == "U") {
		updateCommCd();
	}
}

/*
 * 수정처리
 */
function updateCommCd() {
	var params = $("#modal_frm").serialize();
	
	console.log("#####updCommCdAjax params : " + params);
	commonAjax("/commCd/updCommCdAjax.do", params,
		function(data){
			console.log("####updCommCdAjax retrun  : " + data);
			if(data.result.msgCode == "0000") {
				alert("정상적으로 수정되었습니다.");
				$("#modal_edit").hide();
				if($("#upDwType").val() == "U") {
					getUpCommCdList();
				}else if($("#upDwType").val() == "D") {
					getDwCommCdList($("#parentCdM").val());
				}
				
			}else {
				
			}
		}
	);

}

/*
 * 등록처리
 */
function insertCommCd() {
	var params = $("#modal_frm").serialize();
	
	console.log("#####insCommCdAjax params : " + params);
	commonAjax("/commCd/insCommCdAjax.do", params,
		function(data){
			console.log("####insCommCdAjax retrun  : " + data);
			if(data.result.msgCode == "0000") {
				alert("정상적으로 등록되었습니다.");
				$("#modal_edit").hide();
				$('modal_frm').each(function() {
					this.reset();
				});
				
				if($("#upDwType").val() == "U") {
					getUpCommCdList();
				}else if($("#upDwType").val() == "D") {
					getDwCommCdList($("#parentCdM").val());
				}
				
			}else {
				
			}
		}
	);

}
</script>

<form id="frm" name="frm">
<div class="content_area">
	<div class="location">
		<h3 class="page_title">코드관리</h3>
		<p class="local"><span class="home">HOME</span><span>사이트관리</span><span class="current">코드관리</span></p>
	</div>
	<div class="content">
	<!-- 내용 : s -->

		<div class="row_half">
			<div class="part_l">
				<div class="bundle">
					<div class="heading">
						<h4 class="title">코드그룹목록</h4>
						<div class="controller">
							<button type="button" data-toggle="modal" data-target="#modal_edit" class="btn_default bg_blue" onClick="popUpdModal('0','U','I')">코드그룹추가</button>
						</div>
					</div>
					<div class="tbl_scroll_wrap" style="height:498px;">
						<table class="tbl_view ">
							<caption>상위코드그룹목록</caption> 						
							<colgroup>
								<col style="width:90px">
								<col>
								<col style="width:100px">
								<col style="width:90px">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">코드<span class="ico_necessary"><span class="blind">필수</span></span></th>
									<th scope="col">코드그룹명<span class="ico_necessary"><span class="blind">필수</span></span></th>
									<th scope="col" colspan="2">관리</th>
								</tr>
							</thead>
							<tbody id="upCommCdList">
							</tbody>
						</table>
					</div>
				</div>
			</div>	
			<div class="part_r">
				<div class="bundle mb20">
					<div class="heading">
						<h4 class="title">코드그룹</h4>
					</div>
					<table class="tbl_view">
						<caption>상세</caption> 						
						<colgroup>
							<col style="width:100px">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">코드</th>
								<td><div id='upCd'> </div></td>
							</tr>
							<tr>
								<th scope="row">코드 그룹 명</th>
								<td><div id='upCdNm'> </div></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="bundle">
					<div class="heading">
						<h4 class="title">하위 코드</h4>
						<div class="controller">
							<button type="button" data-toggle="modal" data-target="#modal_edit" class="btn_default bg_blue" onClick="popUpdModal('0','D','I')">하위 코드 추가</button>
						</div>
					</div>
					<div class="tbl_scroll_wrap" style="height:300px;">
						<table class="tbl_view">
							<caption>하위 코드목록</caption> 						
							<colgroup>
								<col style="width:90px">
								<col>
								<col style="width:100px">
								<col style="width:90px">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">코드<span class="ico_necessary"><span class="blind">필수</span></span></th>
									<th scope="col">코드그룹명<span class="ico_necessary"><span class="blind">필수</span></span></th>
									<th scope="col" colspan="2">관리</th>
								</tr>
							</thead>
							<tbody id="dwCommCdList">
							</tbody>
						</table>
					</div>
				</div>
			</div>	
		</div>	
			

	<!-- 내용 : e -->
	</div>
</div>
<!-- //content_area -->
</form>

<form id="modal_frm" name="modal_frm">
<!-- //modal start -->
<div class="modal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static" aria-labelledby="modal_edit" id="modal_edit">
<div class="modal-dialog modal-dialog-centered">
	<div class="modal-content">
		<!-- Modal Header -->
		<div class="modal-header">
			<h4 class="modal-title">제목</h4>
			<button type="button" data-dismiss="modal" class="close"><i class="fas fa-sharp fa-light fa-xmark"></i><span class="blind">닫기</span></button>
		</div>
		<!-- Modal body -->
		<div class="modal-body">
			<table class="tbl_view">
				<caption>상세</caption> 						
				<colgroup>
					<col style="width:100px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">코드<span class="ico_necessary"><span class="blind">필수</span></span></th>
						<td>
							<input type="text" id="commCdM" name="commCdM" class="w100">
							<input type="hidden" id="parentCdM" name="parentCdM">
							<input type="hidden" id="parentCdNmM" name="parentCdNmM">
							<input type="hidden" id="upDwType" name="upDwType">
							<input type="hidden" id="insUpdType" name="insUpdType">
						</td>
					</tr>
					<tr>
						<th scope="row">코드 그룹 명<span class="ico_necessary"><span class="blind">필수</span></span></th>
						<td>
							<input type="text" id="cdNmM" name="cdNmM" class="w100">
						</td>
					</tr>
					<tr style="display:none" id="orderTd">
						<th scope="row">정렬순서</th>
						<td>
							<input type="text" id="cdOrderM" name="cdOrderM" class="w100">
						</td>
					</tr>
					<tr>
						<th scope="row">사용여부</th>
						<td>
							<select name="cdUseYnM" id="cdUseYnM">
								<option value="Y">사용</option>
								<option value="N">사용중지</option>
							</select>
						</td>
					</tr>
					<tr>
				</tr>
				</tbody>
			</table>
		</div>
		<!-- Modal Footer -->
		<div class="modal-footer tc">
			<button type="button" data-dismiss="modal" class="btn_default">닫기</button>
			<button type="button" class="btn_default bg_navy" onClick="saveCommCd()">저장</button>
		</div>
	</div>
	<!-- //modal-content -->
</div>
</div>
</form>
<!-- //modal -->
