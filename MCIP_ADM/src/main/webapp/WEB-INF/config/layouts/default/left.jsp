<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<nav class="<c:if test="${topMenu eq 'dash'}">main</c:if> snb">
	<ul style="height:740px; overflow-y:scroll;">
		<li><a href="#" class="arrow <c:if test="${topMenu eq 'monitoring'}">on</c:if>"><i class="icon-control <c:if test="${topMenu eq 'monitoring'}">on</c:if>"></i><span>관제관리</span></a>
			<ul class="subnav">
			<c:if test="${sessionScope.USER_AUTH eq 'SECU001001'}">
				<li <c:if test="${leftMenu eq 'evntMtr'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/monitoring/eventMonitoringList.do');">EVENT 모니터링</a></li>
				<li <c:if test="${leftMenu eq 'mainDeviceAdmin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/monitoring/mainControlDeviceList.do');">주 제어 장치 상태</a></li>
				<li <c:if test="${leftMenu eq 'rackAdmin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/monitoring/rackList.do');">렉 &amp; 핸들제어장치 상태</a></li>
			</c:if>
			<c:if test="${sessionScope.USER_AUTH eq 'SECU001001' or sessionScope.USER_AUTH eq 'SECU001002'}">
				<li <c:if test="${leftMenu eq 'deviceAdmin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/monitoring/deviceStatusList.do');">장치 상태 관제</a></li>
			</c:if>
				<li <c:if test="${leftMenu eq 'mstscAdmin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/monitoring/mstscList.do');">장치 원격 제어 관리</a></li>
			</ul>
		</li>
		<li><a href="#" class="arrow <c:if test="${topMenu eq 'workApproval'}">on</c:if>"><i class="icon-work <c:if test="${topMenu eq 'workApproval'}">on</c:if>"></i><span>작업관리</span></a>
			<ul class="subnav">
				<li <c:if test="${leftMenu eq 'workAdmin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/approval/workInfoList.do');">작업관리</a></li>
				<li <c:if test="${leftMenu eq 'approvalAdmin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/approval/approvalList.do');">승인관리</a></li>
			</ul>
		</li>
		<li><a href="#" class="arrow <c:if test="${topMenu eq 'serviceAdmin'}">on</c:if>"><i class="icon-service <c:if test="${topMenu eq 'serviceAdmin'}">on</c:if>"></i><span>서비스관리</span></a>
			<ul class="subnav">
				<c:if test="${sessionScope.USER_AUTH eq 'SECU001001'}">
					<li <c:if test="${leftMenu eq 'lctinMngmn'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/locationList.do');">위치 관리</a></li>
					<li <c:if test="${leftMenu eq 'dvcMngr'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/deviceList.do');">주 제어 장치 관리</a></li>
					<li <c:if test="${leftMenu eq 'hnldMngr'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/handleDeviceNRList.do');">핸들 제어 장치 관리</a></li>
					<li <c:if test="${leftMenu eq 'rackMngr'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/rackInfoList.do');">RACK 관리</a></li>
					<li <c:if test="${leftMenu eq 'unitMngr'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/unitInfoList.do');">RACK 장비 관리</a></li>
					<li <c:if test="${leftMenu eq 'etcDvcMngr'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/etcDeviceList.do');">기타 장비 관리</a></li>
					<li <c:if test="${leftMenu eq 'dvccnMngmn'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/dvcLnkMngList.do');">장치 연계 관리</a></li>
				</c:if>
				<li <c:if test="${leftMenu eq 'cardMngmn'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/cardList.do');">카드 관리</a></li>
				<li <c:if test="${leftMenu eq 'wrkrMngmn'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/service/workerList.do');">작업자 관리</a></li>
			</ul>
		</li>
		<li><a href="#" class="arrow <c:if test="${topMenu eq 'stats'}">on</c:if>"><i class="icon-record <c:if test="${topMenu eq 'stats'}">on</c:if>"></i><span>통계 및 이력</span></a>
			<ul class="subnav">
				<c:if test="${sessionScope.USER_AUTH eq 'SECU001001'}">
					<li <c:if test="${leftMenu eq 'userLogin'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/loginLogList.do');">사용자 로그인</a></li>
					<li <c:if test="${leftMenu eq 'urlLog'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/urlLogList.do');">접속 URL</a></li>
					<li <c:if test="${leftMenu eq 'rackDoor'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/rackDoorList.do');">랙도어</a></li>
					<li <c:if test="${leftMenu eq 'cntdev'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/controlDeviceList.do');">제어장치</a></li>
				</c:if>
				<li <c:if test="${leftMenu eq 'rackStat'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/rackStatsList.do');">랙 관리 이력</a></li>
				<li <c:if test="${leftMenu eq 'fms'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/fmsStatsList.do');">FMS 이력</a></li>
				<li <c:if test="${leftMenu eq 'wrkaprvl'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/stats/workerApprovalList.do');">작업자 승인</a></li>
			</ul>
		</li>
		<li><a href="#" class="arrow <c:if test="${topMenu eq 'admnsMenu'}">on</c:if>"><i class="icon-set <c:if test="${topMenu eq 'admnsMenu'}">on</c:if>"></i><span>관리자 메뉴</span></a>
			<ul class="subnav">
				<li <c:if test="${leftMenu eq 'cmcdMngmn'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/admin/commonCode.do');">공통코드 관리</a></li>
				<li <c:if test="${leftMenu eq 'dpusrMngmn'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/user/userList.do');">부서/사용자관리</a></li>
				<%-- <li <c:if test="${leftMenu eq 'srdTest'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/srdCtrl/srdTest.do');">SRD 테스트(UDP)</a></li>
				<li <c:if test="${leftMenu eq 'srdTest1'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/srdCtrl/srdTest1.do');">SRD 테스트(TCP)</a></li> --%>
				<li <c:if test="${leftMenu eq 'siteInfo'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/admin/siteInfo.do');">Site Info</a></li>
				<li <c:if test="${leftMenu eq 'alarm'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/admin/alarmInfo.do');">알람 관리</a></li>
				<li <c:if test="${leftMenu eq 'server'}">class="on"</c:if>><a href="#" onclick="javascript:movePage('/admin/serverList.do');">서버 관리</a></li>
				<!-- li><a href="">게시판</a></li>
	                     <li><a href="">게시판 타입 관리</a></li>
	                     <li><a href="">권한 관리</a></li>
	                     <li><a href="">URL 관리</a></li -->
			</ul>
		</li>
		<li class="user-menu">
			<ul class="subnav">
				<li class="user-name">
					<a href="">${sessionScope.USER_ID} (${sessionScope.USER_NAME})님</a> 로그인되었습니다.</li>
				<li class="user-info">
					<a href=""><i class="icon-info"></i>내 정보</a> 
					<a href="javascript:goAction('logoutFrm','/logOut.do','POST')"><i class="icon-loguot"></i>로그아웃</a></li>
				<li></li>
			</ul>
		</li>
	</ul>
</nav>
<form id="logoutFrm" name="logoutFrm" >
</form>
<script type="text/javascript">
$(document).ready(function(){
	$(".arrow").click(function(){
		// $(".subnav").slideUp();
		var submenu = $(this).next("ul");
		if(submenu.is(":visible")){
			submenu.slideUp();
		}else{
			submenu.slideDown();
		}
	});
});
</script>