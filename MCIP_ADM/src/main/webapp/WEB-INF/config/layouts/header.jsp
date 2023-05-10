<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>

<!-- gnb -->
<header id="hd">
	<h1 <c:if test="${topMenu eq 'dash'}">onclick="$(document).fullScreen(true);"</c:if>></h1>
	<c:choose>
		<c:when test="${topMenu eq 'dash'}">
	<button class="mega-menu toggle"><i class="icon-menu"></i></button>
		</c:when>
		<c:otherwise>
	<button class="mega-menu home" onclick="javascript:movePage('http://sub.erionet.com:8888/index?');"><i class="icon-home"></i></button>
		</c:otherwise>
	</c:choose>
	<h2 class="menu-title">
		<a href="" class="color-yellow">
			<i class="icon-control"></i>
			<c:choose>
				<c:when test="${topMenu eq 'dash'}">DashBoard</c:when>
				<c:when test="${topMenu eq 'monitoring'}">관제관리</c:when>
				<c:when test="${topMenu eq 'workApproval'}">작업관리</c:when>
				<c:when test="${topMenu eq 'serviceAdmin'}">서비스관리</c:when>
				<c:when test="${topMenu eq 'stats'}">통계 및 이력</c:when>
				<c:when test="${topMenu eq 'admnsMenu'}">관리자메뉴</c:when>
				<c:otherwise>DashBoard</c:otherwise>
			</c:choose>
		</a>
	</h2>
</header>
