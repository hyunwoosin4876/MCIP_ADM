<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="ko">
	<head>
	    <meta charset="utf-8" />
	    <meta name="Keywords" content="">
		<meta name="Description" content="">
		<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1" />   
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title><t:insertAttribute name="title" ignore="true" /></title>
		<t:insertAttribute name="scriptjs" />
		<t:insertAttribute name="stylecss" />
	</head>
	<body class="login_bg">
		<t:insertAttribute name="contents"/>
		<t:insertAttribute name="footer"/>
	</body>
</html>