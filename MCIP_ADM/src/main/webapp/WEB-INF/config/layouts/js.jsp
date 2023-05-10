<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<script type="text/javascript" src="/resources/js/jquery.min.js"></script>	
	<script type="text/javascript" src="/resources/js/jquery.form.js"></script>
	<script type="text/javascript" src="/resources/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery.fullscreen-min.js"></script>
	<script type="text/javascript" src="/resources/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources/js/ui.js"></script>
	<script type="text/javascript" src="/resources/js/common.js"></script>
	<script type="text/javascript" src="/resources/js/rsa/jsbn.js"></script>
	<script type="text/javascript" src="/resources/js/rsa/rsa.js"></script>
	<script type="text/javascript" src="/resources/js/rsa/prng4.js"></script>
	<script type="text/javascript" src="/resources/js/rsa/rng.js"></script>
	<script type="text/javascript" src="/resources/js/jquery-loading/jquery.loading.js"></script>
	
	<!-- <script type="text/javascript" src="/resources/js/ui/jquery-sparkline/jquery.sparkline.js"></script> -->

	<!-- <script type="text/javascript" src="/resources/js/paging.js"></script> -->
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