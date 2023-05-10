<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

 <form action="" id="frm" name="frm">
	<fieldset>
		<legend>검색</legend>
		<div class="cont_se">
			<div class="le">
			</div>
			<div class="ri">
				<span class="select_bx_in">
					<select class="select" title="">
						<option>선택</option>
						<option>등록자</option>
						<option>제목</option>
						<option>내용</option>
					</select>
				</span>
				<span class="input_bx_in">
					<input type="text" class="input_200" />
				</span>
				<a href="" class="btn_se">검색</a>
			</div>
		</div>
	</fieldset>
	<div class="bx">
		총갯수 : <c:out value="${boardTotCnt }" /></br>
		<table class="tableType01" summary="">
			<colgroup>
				<col style="width:25px;">
				<col style="width:40px;">
				<col style="width:70px;">
				<col style="width:100px;">
				<col style="width:*;">
				<col style="width:80px;">
				<col style="width:40px;">
				<col style="width:100px;">
				<col style="width:100px;">
			</colgroup>
			<tbody>
				<tr>
					<th><input type="checkbox" class="checkSeq" id="allCheck" /></th>
					<th>No</th>
					<th>게시판명</th>
					<th>제목</th>
					<th>조회수</th>
					<th>등록자</th>
					<th>등록일</th>
				</tr>
			<c:forEach items="${boardList}" var="list" varStatus="status">
				<tr>
					<td>
						<input type="checkbox" class="checkSeq" name="" value="" />
					</td>
					<td><c:out value="${list.comCode}" /></td>
					<td><c:out value="${list.boardSeq}" /></td>
					<td><c:out value="${list.comCode }" /></td>
					<td><c:out value="${list.boardTitle }" /></td>
					<td><c:out value="${list.regId }"/></td>
					<td><c:out value="${list.regDt }"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</form>