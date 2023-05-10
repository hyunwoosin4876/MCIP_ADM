<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<script type="text/javascript" src="/resources/js/jquery-3.3.1.js"></script>	
	<script type="text/javascript" src="/resources/js/jquery.form.js"></script>	 
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/ui/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
	<script type="text/javascript" src="/resources/js/ui.js"></script>
	<script type="text/javascript" src="/resources/js/paging.js"></script>
	<script type="text/javascript" src="/resources/js/ui/jquery-sparkline/jquery.sparkline.js"></script>
	<script type="text/javascript" src="/resources/js/ui/jquery-loading/jquery.loading.js"></script>
	<script type="text/javascript" src="/resources/js/chart/c3-0.6.2/c3.min.js"></script>
	<script type="text/javascript" src="/resources/js/chart/c3-0.6.2/d3.v5.min.js" charset="utf-8"></script>
	
	<link href="/resources/js/ui/jquery-loading/jquery.loading.css" rel="stylesheet">

<script type="text/javascript">
//<![CDATA[
	// resourcePath
	var resourcePath = '${pageContext.request.contextPath}';
	// message, location
	$(function() {
		<c:if test="${not empty message}">
			alert("${message}");
		</c:if>
						
		<c:if test="${not empty location}">
		window.location.href = resourcePath + "${location}";
		</c:if>
	});
//]]>
</script>