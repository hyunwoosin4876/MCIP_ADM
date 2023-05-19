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
		
	</head>
	<body>
		<div id="wrap">
			<t:insertAttribute name="head"/>
			<t:insertAttribute name="header"/>
			<t:insertAttribute name="gnb"/>
			<!-- s : container -->
			<div id="container" class="sub_container"/>
				<div class="inner">
					<t:insertAttribute name="aside"/>
					
					<t:insertAttribute name="page"/>
				</div>	
			</div>
			<!-- e : container -->		
			<t:insertAttribute name="footer"/>
		</div>
	</body>
</html>