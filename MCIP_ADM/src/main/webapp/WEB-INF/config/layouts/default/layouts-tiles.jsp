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
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title><t:insertAttribute name="title" ignore="true" /></title>
		<t:insertAttribute name="scriptjs" />
		<t:insertAttribute name="stylecss" />
	</head>
	<body>
		<nav>
		  <a href="#ct" class="skip sr-only sr-only-focusable">본문 내용바로가기</a>
		</nav>
		<div id="wrap">
			<t:insertAttribute name="header"/>
			<t:insertAttribute name="left"/>
			<div id="ct">
				<div id="content">
					<t:insertAttribute name="content"/>
				</div>		
			</div> 
		</div>
	</body>
</html>