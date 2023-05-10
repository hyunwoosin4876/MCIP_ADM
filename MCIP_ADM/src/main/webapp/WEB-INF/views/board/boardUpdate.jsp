<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<c:set var="now" value="<%=new java.util.Date()%>" />
<form action="" id="frm" name="frm" enctype="multipart/form-data" method="GET">
	<input type="hidden" name="doTran" />
	<input type="hidden" name="pValue" value='<c:out value="${boardVo.pValue}" />' />
	<input type="hidden" name="bdSeq" value='<c:out value="${boardInfo.bdSeq}" />' />
	<input type="hidden" name="bdSeqPar" value='<c:out value="${boardInfo.bdSeqPar}" />' />
	<input type="hidden" name="dept" value='<c:out value="${boardInfo.dept}" />' />
	 <input type="hidden" name="bdGrProd" value='<c:out value="${boardInfo.bdGrProd}" />' />
	<input type="hidden" name="bdCode" value='<c:out value="${boardVo.bdCode }" />' />
	<input type="hidden" name="fileArr" />
<div class="bx">
	<h3 class="tit">${boardVo.bdName }</h3>
		<table class="tableType01" summary="">
			<colgroup>
				<col style="width:12%;">
				<col style="width:38%;">
				<col style="width:12%;">
				<col style="width:38%;">
			</colgroup>
			<tbody>
				<tr>
					<th>제목</th>
					<td colspan="3" class="txt_le">
						<c:choose>
							<c:when test="${boardInfo.bdSeq eq 0}">
								<span class="input_bx">
									<c:choose>
										<c:when test="${boardVo.pValue eq 'reply'}">
						<input class="input_100p" name="subject" value="RE:<c:out value='${boardParInfo.subject }' />" placeholder="제목을 입력 해 주세요.">
										</c:when>
										<c:otherwise>
						<input class="input_100p" name="subject" value="" placeholder="제목을 입력 해 주세요.">
										</c:otherwise>
									</c:choose>
								</span>
							</c:when>
							<c:otherwise>
						<input class="input_100p" name="subject" value="<c:out value='${boardInfo.subject}' />" <c:if test="${sessionScope.USER_CODE ne boardInfo.regUser}"> readonly="readonly" </c:if>  placeholder="제목을 입력 해 주세요.">
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<c:if test="${boardInfo.noticeYN == 'Y' }">
				<tr>
					<th>상단공지</th>
					<td colspan="3"  class="txt_le">
						<span class="select_bx">
							<select name="noticeListYN" class="select">
								<option value="N" <c:if test="${boardInfo.noticeListYN eq 'N' }"> selected="selected"</c:if>>N</option>
								<option value="Y" <c:if test="${boardInfo.noticeListYN eq 'Y' }"> selected="selected"</c:if>>Y</option>
							</select>
						</span>
					</td>
				</tr>
				</c:if>
				<tr>
					<th>등록자</th>
					<td colspan="3" class="txt_le">
						<c:choose>
							<c:when test="${boardInfo.bdSeq eq 0}">
								<c:out value="${sessionScope.USER_NAME}"/>
							</c:when>
							<c:otherwise>
								<c:out value="${boardInfo.regUserName}" />
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td colspan="3" class="txt_le">
						<c:choose>
							<c:when test="${boardInfo.bdSeq eq 0}">
								<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm"/>
							</c:when>
							<c:otherwise>
								<fmt:formatDate value="${boardInfo.regDate }" pattern="yyyy-MM-dd HH:mm"/>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
				<c:if test="${boardVo.fileYN eq 'Y' }">
				<tr>
					<th>첨부파일<br />
						<a class="btn_xmlup" href="javascript:;" onClick="javascript:filePlus()">추가</a>
					</th>
					<td colspan="3" class="txt_le" id="fileTd">
						<c:if test="${fn:length(boardInfo.bdFiles) > 0 }">
						<c:forEach items="${boardInfo.bdFiles }" var="fList" varStatus="status">
						<span class="input_bx">
							<a href="#" onclick="javascript:fileDown('${fList.fSeq}', '${fList.oriFileName }','${fList.saveFileName }')" >
								<c:out value="${fList.oriFileName }" />
								<input type="hidden" class="fSeq" value='<c:out value="${fList.fSeq }" />' />
							</a>
							<a type='button' onClick='javascript:$(this).parent().remove();' class="btn_del">삭제</a>
						</span>
						</c:forEach>
						</c:if>
						<span class="input_bx">
							<input type="file" name="upload" />
							<a type='button' onClick='javascript:$(this).parent().remove();' class="btn_del">삭제</a>
						</span>
					</td>
				</tr>
				</c:if>
				<c:if test="${boardVo.thumbYN eq 'Y' }">
				<tr>
					<th>썸네일</th>
					<td colspan="3" class="txt_le">
						<span class="input_bx">
						<img alt="${boardInfo.thumbFile.oriFileName }" id="thumbImg" class="wd45 hi60" src="${pageContext.request.contextPath}${boardInfo.thumbFile.fileUrl }" />
						<br />
						<input type="file" name="thumb" accept="image/*" />
						</span>
					</td>
				</tr>
				</c:if>
				<c:if test="${boardVo.imageYN eq 'Y' }">
				<tr>
					<th>이미지</th>
					<td colspan="3" class="txt_le">
						<span class="input_bx">
						<img alt="${boardInfo.reprecenFile.oriFileName }" id="imageImg" class="wd200 hi200" src="${pageContext.request.contextPath}${boardInfo.reprecenFile.fileUrl }" />
						<br />
						<input type="file" name="image" accept="image/*" />
						</span>
					</td>
				</tr>
				</c:if>
				<c:if test="${boardVo.movieYN eq 'Y' }">
				<tr>
					<th>동영상</th>
					<td colspan="3" class="txt_le">
						<span class="input_bx">
						<img alt="${boardInfo.reprecenFile.oriFileName }" class="wd200 hi200" src="${pageContext.request.contextPath}${boardInfo.reprecenFile.fileUrl }" />
						${boardInfo.movieFile.oriFileName }
						<br />
						<input type="file" name="movie" accept="video/*" />
						</span>
					</td>
				</tr>
				</c:if>
				<c:if test="${boardVo.popUpYN eq 'Y' }">
				<tr>
					<th>팝업시작일</th>
					<td class="txt_le">
						<span class="input_bx">
							<input type="text" class="datePicker input_100p" name="startDate" value="<c:out value="${popUpInfo.startDate }" />" />
						</span>
					</td>
					<th>팝업 종료일</th>
					<td class="txt_le">
						<span class="input_bx">" />
							<input type="text" class="datePicker input_100p" name="endDate " value="<c:out value="${popUpInfo.endDate }" />" />
						</span>
					</td>
				</tr>
				<tr>
					<th>가로좌표</th>
					<td class="txt_le">
						<span class="input_bx">
							<input type="text" name="locationX" class="input_100p" value="<c:out value="${popUpInfo.locationX }" />" />
						</span>
					</td>
					<th>세로좌표</th>
					<td class="txt_le">
						<span class="input_bx">
							<input type="text" name="locationY" class="input_100p" value="<c:out value="${popUpInfo.locationY }" />" />
						</span>
					</td>
				</tr>
				<tr>
					<th>가로크기</th>
					<td class="txt_le">
						<span class="input_bx">
							<input type="text" name="sizeX" class="input_100p" value="<c:out value="${popUpInfo.sizeX }" />" />
						</span>
					</td>
					<th>세로크기</th>
					<td class="txt_le">
						<span class="input_bx">
							<input type="text" name="sizeY" class="input_100p" value="<c:out value="${popUpInfo.sizeY }" />" />" />
						</span>
					</td>
				</tr>
				<tr>
					<th>순서</th>
					<td class="txt_le">
						<span class="input_bx">
							<input type="text" name="orderBy" class="input_100p" value="<c:out value="${popUpInfo.orderBy }" />" />
						</span>
					</td>
					<th></th>
					<td class="txt_le"></td>
				</tr>
				</c:if>
				<c:choose>
					<c:when test="${boardVo.pValue eq 'reply' }">
				<tr>
					<th colspan="4">내용</th>
				</tr>
				<tr>
					<td colspan="4" class="txt_le">${boardParInfo.contents }</td>
				</tr>
				<tr>
					<th colspan="4">답변할 내용</th>
				</tr>
				<tr>
					<td colspan="4" class="txt_le">
						<div class="editer">
							<textarea class="itx" style="resize:none;width:100%" name="contents" id="bdContents" rows="20"><c:out value="${boardInfo.contents }" /></textarea>
						</div>
					</td>
				</tr>
					</c:when>
					<c:otherwise>
					<c:choose>
						<c:when test="${boardVo.bannerYN ne 'Y' }">
						<tr>
							<th colspan="4">내용</th>
						</tr>
						<tr>
							<td colspan="4" class="txt_le">
								<div class="editer">
									<textarea class="itx" style="resize:none;width:100%" name="contents" id="bdContents" rows="20"><c:out value="${boardInfo.contents }" /></textarea>
								</div>
							</td>
						</tr>					
						</c:when>
						<c:otherwise>
						<tr>
							<th>배너파일</th>
							<td colspan="3" class="txt_le">
								<span class="input_bx">
								<img alt="${boardInfo.reprecenFile.oriFileName }" id="imageImg" class="wd200 hi200" src="${pageContext.request.contextPath}${boardInfo.reprecenFile.fileUrl }" />
								<br />
								<input type="file" name="image" accept="image/*" />
								</span>
							</td>
						</tr>
						</c:otherwise>
					</c:choose>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
</form>
<div class="btns_cen">
	<c:choose>
		<c:when test="${boardInfo.bdSeq eq 0 }">z
			<a href="javascript:;" onclick="javascript:boardTran('I')" class="btn_reg"><span>등록하기</span></a>			
			<a href="javascript:;" onclick="history.back()" class="btn_can"><span>취소하기</span></a>
		</c:when>
		<c:when test="${boardInfo.bdSeq ne 0 and sessionScope.USER_CODE eq boardInfo.regUser}">
			<a href="javascript:;" onclick="javascript:boardTran('D')" class="btn_del"><span>삭제하기</span></a>
			<a href="javascript:;" onclick="javascript:boardTran('U')" class="btn_edit"><span>수정하기</span></a>
			<c:if test="${boardInfo.replyYN eq 'Y'}">
				<a href="#" onclick="javascript:replyRegForm('${boardInfo.bdSeq}')"  class="btn_edit"><span>답글하기</span></a>
			</c:if>
			<a href="javascript:;" onclick="history.back()" class="btn_can"><span>취소하기</span></a>		
		</c:when>
		<c:otherwise>
			<a href="javascript:;" onclick="javascript:boardList()" class="btn_list"><span>확인하기</span></a>
		</c:otherwise>
	</c:choose>
</div>
<script type="text/javascript" src="/resources/editor/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
<c:if test="${boardVo.bannerYN ne 'Y' }">
var oEditors = [];	
nhn.husky.EZCreator.createInIFrame({	
	oAppRef: oEditors,	
	elPlaceHolder: "bdContents", //textarea에서 지정한 id와 일치해야 합니다.	
	sSkinURI: "/resources/editor/se2/SmartEditor2Skin.html",	
	fCreator: "createSEditor2"

});
</c:if>
$("input[name=thumb]").change(function(){
	if (this.files && this.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#thumbImg').attr('src', e.target.result);
        }
        reader.readAsDataURL(this.files[0]);
    }
});
$("input[name=image]").change(function(){
	if (this.files && this.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#imageImg').attr('src', e.target.result);
        }
        reader.readAsDataURL(this.files[0]);
    }
});
function boardList(){
	goAction("frm", "/board/"+$("input[name=bdCode]", "#frm").val() +"/boardList.do", "GET");
}
function boardTran(doTran){
	if(validationBoard(doTran)){
		$("input[name=doTran]", "#frm").val(doTran);
		<c:if test="${boardVo.bannerYN ne 'Y' }">
		oEditors.getById["bdContents"].exec("UPDATE_CONTENTS_FIELD", []);			// 에디터 편집내용이 ir textarea 로 적용됨
		</c:if>
		commonAjaxSubmit("/board/boardTranAjax.do", "frm", "json", succRegListener);
	}
}
function validationBoard(doTran) {//0710함동균 수정
	if(doTran == 'U'){
//<![CDATA[
<c:if test="${boardVo.fileYN eq 'Y' }">
		var fileArr = "";
		$(".fSeq","#frm").each(function(){
			fileArr += $(this).val()+";";
		});
		$("input[name=fileArr]", "#frm").val(fileArr);
</c:if>
//]]>
	}
	return true;
}
function succRegListener(data){
	alert(data.msgMent);
	if(data.msgCode === '0000'){
		movePage("/board/"+ $("input[name=bdCode]", "#frm").val() + "/boardList.do")
	}
}
function replyRegForm(seq){
	if(typeof seq !== "undefined"){
		$("input[name=bdSeqPar]").val($("input[name=bdSeq]").val());
		$("input[name=bdSeq]").val(0);
		$("input[name=pValue]").val("reply");
		goAction("frm","/board/"+$("input[name=bdCode]", "#frm").val() +"/boardForm.do", "POST");
	}
}
//파일 등록에 대한 html
var objCreateFileTxt = "<span class='input_bx'><input type='file' name='upload' />";
objCreateFileTxt += " <a type='button' onClick='$(this).parent().remove();' class='btn_del' style='text-align:center;''>삭제</a></span>";
function filePlus(){
	var fileObj = $(objCreateFileTxt);
	$("#fileTd").append(fileObj);
}

</script>
