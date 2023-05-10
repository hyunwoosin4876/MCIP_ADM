<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
		<title><t:insertAttribute name="title" ignore="true" /></title>
		<t:insertAttribute name="stylecss" />
		<t:insertAttribute name="scriptjs" />
	</head>
	<body>
		<div id="wrap">
			<div id="header">
				<t:insertAttribute name="header"/>
			</div>
			<div id="container">
				<div id="contents_all">
					<t:insertAttribute name="content"/>
				</div>		
			</div>
			<t:insertAttribute name="footer"/>
			</div>
			<!--레이어 시 뒤 배경 -->
			<div class="modal_bg none"></div>
			<!--//레이어 시 뒤 배경 -->
	</body>
</html>