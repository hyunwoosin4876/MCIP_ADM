<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="ko" style="
    margin-top: 10px;
    margin-left: 10px;
    margin-right: 10px;
">
	<head>
		<meta charset="UTF-8"/>
		<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"> 
		<title>eVms</title>
		
		<script>
		  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
		  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
		  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
		  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
		
		  ga('create', 'UA-50466528-1', 'ksslc.or.kr');
		  ga('send', 'pageview');
		
		</script>
	</head>
	<link rel="stylesheet" href="/resources/css/jquery-ui.css">
	<script type="text/javascript" src="/resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.form.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.fullscreen-min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
	<script type="text/javascript" src="/resources/js/ui.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>	
	<script type="text/javascript" src="/resources/js/jquery-loading/jquery.loading.js"></script>
	<link rel="stylesheet" href="/resources/css/common.css">
	<link rel="stylesheet" href="/resources/css/util.css">
	<link href="/resources/js/jquery-loading/jquery.loading.css" rel="stylesheet">
	<%-- <body onload="update_qrcode('${nsMarketUrl}','${nsStoreUrl}');"> --%>
	<body>
			
			
			<div id="popup">
				<div id="content">
				<t:insertAttribute name="popupContents"/>		
				</div>										
			</div>
			
			
	</body>
</html>