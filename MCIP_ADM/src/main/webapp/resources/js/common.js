/* jquery */
$(function(){  
	$(".lnb ul li.menuLi").bind("mouseenter focusin", function(){
		$(this).children('ul').stop(true, true).slideDown();
		$(this).addClass('on');
	});
	$(".lnb ul li.menuLi").bind("mouseleave focusout", function(){

	},function () {  

		    if ($(this).hasClass("active") != true) {
				$(this).children('ul').stop(true, true).slideUp();
				$(this).removeClass('on'); 
		    }
	});  
}); 


$(function(){ 
	$('.layertop a').click(function() {
		$('.layerPop').css('display','none');		
	});	
});

/** 
 * int Date > String Date 
 * yyyy년 MM월 dd일 a/p hh시 mm분
 */
function dateToString(data){
	return new Date(data).format("yyyy년 MM월 dd일 a/p hh시 mm분");
}
function dateToStringF(data, format){
	return new Date(data).format(format);
}

/**
 * 특수문자 치환 함수
 */
function getStrCnvr(obj){
	for (var key in obj) {
		if(typeof obj[key] == 'string'){
			let str;
			str = obj[key].replace(/&apos;/gi, "\'");
			str = str.replace(/&lt;/gi, "<");
			str = str.replace(/&gt;/gi, ">");
			str = str.replace(/&amp;/gi, "&");
			str = str.replace(/&nbsp;/gi, " ");
			str = str.replace(/&quot;/gi, "\"");
			obj[key] = str;
		}
		if(typeof obj[key] == 'object'){
			var obj2 = obj[key];
			for (var key2 in obj2) {
				if(typeof obj2[key2] == 'string'){
					let str2;
					str2 = obj2[key2];
					str2 = str2.replace(/&apos;/gi, "\'");
					str2 = str2.replace(/&lt;/gi, "<");
					str2 = str2.replace(/&gt;/gi, ">");
					str2 = str2.replace(/&amp;/gi, "&");
					str2 = str2.replace(/&nbsp;/gi, " ");
					str2 = str2.replace(/&quot;/gi, "\"");
					obj2[key2] = str2;					
				}
			}	
		}
	}
	return obj;
} 
/**
 * ajax common
 */
function commonAjax(url, data, successListener, errorListener,
		beforeSendListener, completeListener, pValue) {
	$.ajax({

		type : "POST",
		url : url,
		data : data,
		dataType : "json",
		async: false,
		processData: false,
		//contentType: "application/json; charset=utf-8",
		beforeSend : function(xmlHttpRequest) {
			loadingStart(xmlHttpRequest);
			// 요청 전 선작업
			if (null != beforeSendListener) {
				beforeSendListener(pValue);
			}
		},
		success : function(data) {
			// 성공 시
			getStrCnvr(data);
			successListener(data, pValue);
		},
		error : function(data, status) {
			console.log(data);
			if(data.status == 500){
				// alert("DataAccessException이 발생하였습니다.");
				commonSweetAlert("DataAccessException이 발생하였습니다.", 'error');
			}else if(data.status == 501){
				// alert("TransactionException이 발생하였습니다.");
				commonSweetAlert("TransactionException이 발생하였습니다.", 'error');
			}else if(data.status == 502){
				// alert("EgovBizException이 발생하였습니다.");
				commonSweetAlert("EgovBizException이 발생하였습니다.", 'error');
			}else if(data.status == 503){
				// alert("AccessDeniedException이 발생하였습니다.");
				commonSweetAlert("AccessDeniedException이 발생하였습니다.", 'error');
			}else{
				// 오류 발생
				if (null != errorListener) {
				errorListener(data, pValue);
				}
			}
		},
		complete : function() {
			loadingStop();
			// 요청 완료 시
			if (null != completeListener) {
				completeListener(pValue);
			}
		}
	});

}
function commonSjax(url, data, type, successListener, errorListener,
		beforeSendListener, completeListener, pValue, asyncYN) {
	$.ajax({

		type : type,
		url : url,
		data : data,
		dataType : "json",
		async: asyncYN,
		processData: false,
		//contentType: "application/json; charset=utf-8",
		beforeSend : function(xmlHttpRequest) {
			loadingStart(xmlHttpRequest);
			// 요청 전 선작업
			if (null != beforeSendListener) {
				beforeSendListener(pValue);
			}
		},
		success : function(data) {
			// 성공 시
			successListener(data, pValue);
		},
		error : function(data, status) {
			if(data.status == 500){
				// alert("DataAccessException이 발생하였습니다.");
				commonSweetAlert("DataAccessException이 발생하였습니다.", 'error');
			}else if(data.status == 501){
				// alert("TransactionException이 발생하였습니다.");
				commonSweetAlert("TransactionException이 발생하였습니다.", 'error');
			}else if(data.status == 502){
				// alert("EgovBizException이 발생하였습니다.");
				commonSweetAlert("EgovBizException이 발생하였습니다.", 'error');
			}else if(data.status == 503){
				// alert("AccessDeniedException이 발생하였습니다.");
				commonSweetAlert("AccessDeniedException이 발생하였습니다.", 'error');
			}else{
				// 오류 발생
				if (null != errorListener) {
				errorListener(data, pValue);
				}
			}
		},
		complete : function() {
			loadingStop();
			// 요청 완료 시
			if (null != completeListener) {
				completeListener(pValue);
			}
		}
	});

}
function commonAjaxSubmit(url, frmId, dataType, successListener, errorListener, beforeSendListener, completeListener, pValue) {
	var submitOption = {
			anync			: true
			, url 			: url
			, type 			: "POST"
	        , dataType		: dataType	       		// 데이터 타입 json
	        , beforeSend : function(xmlHttpRequest) {
				loadingStart(xmlHttpRequest);
				// 요청 전 선작업
				if (null != beforeSendListener) {
					beforeSendListener(pValue);
				}
			},
			success : function(data) {
				// 성공 시
				successListener(data, pValue);
			},
			error : function(data, status) {
				if(data.status == 500){
					// alert("DataAccessException이 발생하였습니다.");
					commonSweetAlert("DataAccessException이 발생하였습니다.", 'error');
				}else if(data.status == 501){
					// alert("TransactionException이 발생하였습니다.");
					commonSweetAlert("TransactionException이 발생하였습니다.", 'error');
				}else if(data.status == 502){
					// alert("EgovBizException이 발생하였습니다.");
					commonSweetAlert("EgovBizException이 발생하였습니다.", 'error');
				}else if(data.status == 503){
					// alert("AccessDeniedException이 발생하였습니다.");
					commonSweetAlert("AccessDeniedException이 발생하였습니다.", 'error');
				}else{
					// 오류 발생
					if (null != errorListener) {
						errorListener(data, pValue);
					}
				}
			},
			complete : function() {
				loadingStop();
				// 요청 완료 시
				if (null != completeListener) {
					completeListener(pValue);
				}
			}
	    };
	    // frmApply Form Data값을 testAjax.html 으로 ajax 전송
	    $("#"+frmId).ajaxSubmit(submitOption);
}

/**
 * Browser 체크관련 정보
 */
var appname = navigator.appName;
var useragent  = navigator.userAgent;IEver = navigator.appVersion.split("; ")[1];
if(appname == "Microsoft Internet Explorer") appname = "IE";

// 브라우져
var IE = appname =="IE";
var IE55 = (IEver == "MSIE 5.5");
var IE6 = (IEver == "MSIE 6.0");
var IE7 = (IEver == "MSIE 7.0");
var IE8 = (IEver == "MSIE 8.0");
var IE9 = (IEver == "MSIE 9.0");
var IE10 = (IEver == "MSIE 10.0");
var IE11 = (IEver == "WOW64");
var EDGE = (IEver == "WIN64");

// OS
var XP = (useragent.match(/Windows NT 5.1/) == "Windows NT 5.1");
var VISTA = (useragent.match(/Windows NT 6.0/) == "Windows NT 5.1");
var WIN7 = (useragent.match(/Windows NT 6.1/) == "Windows NT 6.1");
var WIN8 = (useragent.match(/Windows NT 6.2/) == "Windows NT 6.2"); 
var WIN81 = (useragent.match(/Windows NT 6.3/) == "Windows NT 4.3"); 
var WIN10 = (useragent.match(/Windows NT 10.0/) == "Windows NT 10.0"); 
var WINPHONE81 = (useragent.match(/Windows PHONE 8.1/) == "Windows NT 5.1"); 
var WINPHONE10 = (useragent.match(/Windows PHONE 10.0/) == "Windows NT 5.1"); 
var ANDROID = (useragent.match(/android/) == "Windows NT 5.1"); 
var BLACKBERRY = (useragent.match(/blackberry/) == "Windows NT 5.1"); 
var IPHONE = (useragent.match(/iphone/) == "Windows NT 5.1"); 
var IPAD = (useragent.match(/ipad/) == "Windows NT 5.1"); 
var IPOD = (useragent.match(/ipod/) == "Windows NT 5.1"); 
var MAC = (useragent.match(/mac/) == "Windows NT 5.1"); 
var LINUX = (useragent.match(/x11/) == "Windows NT 5.1"); 
var WINETC = (useragent.match(/windows/) == "Windows NT 5.1"); 
var GOOGLEBOT = (useragent.match(/googlebot/) == "Windows NT 5.1"); 
var TIZEN = (useragent.match(/tizen/) == "Windows NT 5.1"); 

function getBrowserType(){
	 var agt = navigator.userAgent.toLowerCase();
	 if (agt.indexOf("chrome") != -1) return 'Chrome'; 
	 if (agt.indexOf("opera") != -1) return 'Opera'; 
	 if (agt.indexOf("staroffice") != -1) return 'Star Office'; 
	 if (agt.indexOf("webtv") != -1) return 'WebTV'; 
	 if (agt.indexOf("beonex") != -1) return 'Beonex'; 
	 if (agt.indexOf("chimera") != -1) return 'Chimera'; 
	 if (agt.indexOf("netpositive") != -1) return 'NetPositive'; 
	 if (agt.indexOf("phoenix") != -1) return 'Phoenix'; 
	 if (agt.indexOf("firefox") != -1) return 'Firefox'; 
	 if (agt.indexOf("safari") != -1) return 'Safari'; 
	 if (agt.indexOf("skipstone") != -1) return 'SkipStone'; 
	 if (agt.indexOf("msie") != -1) return 'Internet Explorer';
	 if (agt.indexOf("rv:11.0") != -1) return 'Internet Explorer';
	 if (agt.indexOf("netscape") != -1) return 'Netscape'; 
	 if (agt.indexOf("mozilla/5.0") != -1) return 'Mozilla'; 
}


/**
 * name에 맞춘 Value 변경
 * @param _name
 * @param _val
 */

function setValue(_name,_val){
	$("input[name="+_name+"]").val(_val);
}
/**
 * frm 폼 action에 맞추어 실행 
 * @param _action
 */
function goAction(frm,_action, method){
	$("form[name=" + frm + "]").attr("method", method).attr("action",_action).submit();
}
/**
 * 준비 중입니다.
 */
function noLink(){
	// alert("준비중입니다.");
	commonSweetAlert("준비중입니다.", 'info');
}
/**
 * 영문 / 숫자 / 특수문자 조합
 * @param userpw
 * @returns {Boolean}
 */
function userPWEngNumSchar(userpw, userid) {
	var alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var alpha2 = "abcdefghijklmnopqrstuvwxyz";
	var number = "1234567890";
	var sChar = "-_=+\|()*&^%$#@!~`?></;,.:'";

	var sChar_Count = 0;
	var alphaCheck = false;
	var alpha2Check = false;
	var numberCheck = false;

	if (8 < userpw.length) {
		for ( var i = 0; i < userpw.length; i++) {
			if (sChar.indexOf(userpw.charAt(i)) != -1) {
				sChar_Count++;
			}
			if (alpha.indexOf(userpw.charAt(i)) != -1) {
				alphaCheck = true;
			}
			if (alpha2.indexOf(userpw.charAt(i)) != -1) {
				alpha2Check = true;
			}
			if (number.indexOf(userpw.charAt(i)) != -1) {
				numberCheck = true;
			}
		}

		var temp = "";
		for(var i=0; i < userpw.length; i++) {
			temp = userpw.charAt(i);
			if(temp == userpw.charAt(i+1) && temp == userpw.charAt(i+2)) {
				// alert("동일한 문자를 3회 이상 반복 사용할 수 없습니다.");
				commonSweetAlert("동일한 문자를 3회 이상 반복 사용할 수 없습니다.", 'info');
				return false;
			}
		}

		var cnt=0, cnt2=1, cnt3=1;
        for(i=0;i < userpw.length;i++){
			temp_pass1 = userpw.charAt(i);
			
			next_pass = (parseInt(temp_pass1.charCodeAt(0)))+1;
			temp_p = userpw.charAt(i+1);
			temp_pass2 = (parseInt(temp_p.charCodeAt(0)));
			if (temp_pass2 == next_pass)
			    cnt2 = cnt2 + 1;
			else
			    cnt2 = 1;
			if (temp_pass1 == temp_p)
			    cnt3 = cnt3 + 1;
			else
			    cnt3 = 1;
			
			if (cnt2 > 2) break;
			if (cnt3 > 2) break;
        }
        if (cnt2 > 2){
        	// alert("비밀번호에 연속된 문자나 순차적인 숫자를 3자 이상 사용해서는 안됩니다.");
			commonSweetAlert("비밀번호에 연속된 문자나 순차적인 숫자를 3자 이상 사용해서는 안됩니다.", 'info');
			return false;
        }
		
		if (sChar_Count < 1 || alphaCheck != true || numberCheck != true || alpha2Check != true) {
			// alert("비밀번호는 9자 이상 영문,숫자,특수문자의 혼합으로 입력해야 합니다.");
			commonSweetAlert("비밀번호는 9자 이상 영문,숫자,특수문자의 혼합으로 입력해야 합니다.", 'info');
			return false;
		}

		if(userpw == userid) {	
			// alert("아이디와 동일한 비밀번호는 사용할 수 없습니다.");
			commonSweetAlert("아이디와 동일한 비밀번호는 사용할 수 없습니다.", 'info');
			return false;
		}
		
	} else {
		// alert("비밀번호는 9자 이상 영대문자,영소문자,숫자,특수문자의 혼합으로 입력해야 합니다.");
		commonSweetAlert("비밀번호는 9자 이상 영대문자,영소문자,숫자,특수문자의 혼합으로 입력해야 합니다.", 'info');
		return false;
	}

	return true;
}

/**
 * 숫자만 입력
 * @param obj
 */
function regOnlyNum(obj){
	var exp = new RegExp('^[0-9]*$');
	var val = obj.value;
	if(val!=''){
		if(!exp.test(val)){
			// alert("숫자만 입력이 가능합니다.");
			commonSweetAlert("숫자만 입력이 가능합니다.", 'info');
			obj.value = "";
		}
	}
}
/**
 * 틀수 문자 입력 불가
 * @param obj
 * @returns
 */
function regsCharNo(obj){
	var sChar = "-=+|*&^%$#@!~`?><;:";
	var data = obj.value;
	for ( var i = 0; i < data.length; i++) {
		if (sChar.indexOf(data.charAt(i)) != -1) {
			// alert("몇몇 특수문자는 입력이 불가능합니다.");
			commonSweetAlert("몇몇 특수문자는 입력이 불가능합니다.", 'info');
			obj.value = "";
			return false;
		}
	}
}
/**
 * 숫자 및 영문 입력
 * @param obj
 * @returns {Boolean}
 */
function regExEngNum(obj){
	var exp = new RegExp('^[A-Za-z]+[0-9]*$');
	var val = obj.value;
	if(val!=''){
		if(!exp.test(val)){			
			// alert("영문/숫자만 입력이 가능합니다.");
			commonSweetAlert("영문/숫자만 입력이 가능합니다.", 'info');
			// obj.value = "";
			return false;
		}
	}
}
/**
 * 영문(소문자) 및 숫자 입력
 * @param obj
 * @returns {Boolean}
 */
function regExEngSmallNum(obj){
	var exp = new RegExp('^[a-z]+[0-9]*$');
	var val = obj.value;
	if(val!=''){
		if(!exp.test(val)){
			// alert("영문(소문자)/숫자만 입력이 가능합니다.");
			commonSweetAlert("영문(소문자)/숫자만 입력이 가능합니다.", 'info');
			// obj.value = "";
			return false;
		}
	}
}
/**
 * 영문(대문자) 및 숫자 입력
 * @param obj
 * @returns {Boolean}
 */
function regExEngBigNum(obj){
	var exp = new RegExp('^[A-Z0-9]*$');
	var val = obj.value;
	if(val!=''){
		if(!exp.test(val)){			
			// alert("영문/숫자만 입력이 가능합니다.");
			commonSweetAlert("영문/숫자만 입력이 가능합니다.", 'info');
			// obj.value = "";
			return false;
		}
	}
}

/**
 * 파일 다운로드
 * @param seq
 * @param fileName
 * @param tempFileName
 */
function fileDown(seq, fileName, tempFileName){
	$form = $('<form name="fileFrm" id="fileFrm" method="POST" action="/fileDown"></form>');
	$form.append('<input type="hidden" name="eFSeq" value="' + seq + '" />')
	$form.append('<input type="hidden" name="eOriFileName" value="' + fileName + '" />')
	$form.append('<input type="hidden" name="eSaveFileName" value="' + tempFileName + '" />')
	$("body").append($form);
	$form.submit();
	$form.remove();
}
/**
 * excel 보고서 다운로드
 * @param type
 * @returns
 */
function excelDown(type,seq){
	$form = $('<form name="fileFrm" id="fileFrm" method="POST" action="/security/excelDown"></form>');
	$form.append('<input type="hidden" name="pValue" value="' + type + '" />')
	$form.append('<input type="hidden" name="seq" value="' + seq + '" />')
	$("body").append($form);
	$form.submit();
	$form.remove();
}
/**
 * 이메일 형식 체크
 * @param val
 * @returns {Boolean}
 */
function regEmailChkFull(val){
	var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	
	if(val!=''){
		if (!regExp.test(val)) {//0708 함동균 수정.
			// alert("올바른 이메일 주소를 입력해주세요.");
			commonSweetAlert("올바른 이메일 주소를 입력해주세요.", 'info');
			return false;
		}
		else {
			return true;
		}
	}
}


/**
 * 휴대폰 번호 체크
 * @param val
 * @returns {Boolean}
 */
function regPhoneChk(val){
	var regExp = /^(01[016789]{1}|02|0[3-9]{1}[0-9]{1})-?([0-9]{3,4})-?([0-9]{4})$/;
	
	if(val!=''){		
		if (!regExp.test(val)) {//0708 함동균 수정.
			// alert("올바른 전화번호를 입력해주세요.");
			return false;
		}
		else {
			return true;
		    
		}
	}
}
/**
 * 전화번호 체크
 * @param val
 * @returns
 */
function regCellChk(val){	
	var regExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	if(val != ''){		
		if (!regExp.test(val)) { // 0708 함동균 수정.
			// alert("올바른 휴대폰 번호를 입력해주세요.");
			return false;
		}
		else {
			return true;
		}
	}
		
}

/**
 * trim
 * @param s
 * @returns
 */
function trim(s) {
	s += '';
	return s.replace(/^\s*|\s*$/g, '');
}

Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};
/**
 * 날짜 비교함수 
 * @param startDate
 * @param endDate
 * @returns {Boolean}
 */
function checkSearchDate(startDate, endDate){
	if(startDate === "" || endDate === ""){
		return false;
	}
	try {
		var arr1 = startDate.split("-");
		var arr2 = endDate.split("-");
		var date1 = new Date(arr1[0], arr1[1], arr1[2]);
		var date2 = new Date(arr2[0], arr2[1], arr2[2]);
		if(date1 > date2){
			return false
		}else{
			return true
		}
	} catch (e) {
		return false;
	}
}
/**
 * 페이지 이동
 * @param url
 */
function movePage(url){ 
	$form = $('<form id="movePageFrm" method="GET"></form>');
	$form.attr("action", url);
	$("body").append($form);
	$form.submit();
}
/**
 * 오늘 년월일 정보 호출
 * @returns
 */
function getToday(){
	var d = new Date();
	return  d.getFullYear() +"년 " + (d.getMonth() + 1) + "월" + d.getDate() + "일";
	
}
/**
 * 체크박스 전체 선택 / 해제 ( disabled 된것 제외 )
 * @param obj 대표 체크박스
 * @param targetName	선택 / 해제 될 체크박스의 name 속성
 */
function fnChkAll(obj, targetName){
	$("input[type=checkbox][name="+targetName+"]").each(function(){
		var attrCheck = $(this).attr("disabled");
		if(typeof attrCheck == "undefined"){
			$(this).prop("checked", $(obj).is(":checked"));
		}
	});
}
/**
 * 체크박스 전체 선택 / 해제 ( disabled 된것 제외 )
 * @param obj 대표 체크박스
 * @param targetClass	선택 / 해제 될 체크박스의 name 속성
 */
function fnChkAllClass(obj, targeClass){
	$("input[type=checkbox][class="+targeClass+"]").each(function(){
		var attrCheck = $(this).attr("disabled");
		if(typeof attrCheck == "undefined"){
			$(this).prop("checked", $(obj).is(":checked"));
		}
	});
}
/**
 * 특수문자 치환 함수
 * @param String 원본문자
 */
function stringTansSChar(str) {
	var result = "";
	for ( var i = 0; i < str.length; i++) {
		if(str.charAt(i) == "&"){
			result += "&#38;";
		}else if(str.charAt(i) == "#"){
			result += "&#35;";
		}else if(str.charAt(i) == "!"){
			result += "&#33;";
		}else if(str.charAt(i) == "\""){
			result += "&#34;";
		}else if(str.charAt(i) == "$"){
			result += "&#36;";
		}else if(str.charAt(i) == "%"){
			result += "&#37;";
		}else if(str.charAt(i) == "'"){
			result += "&#39;";
		}else if(str.charAt(i) == "("){
			result += "&#40;";
		}else if(str.charAt(i) == ")"){
			result += "&#41;";
		}else if(str.charAt(i) == "*"){
			result += "&#42;";
		}else if(str.charAt(i) == "+"){
			result += "&#43;";
		}else if(str.charAt(i) == ","){
			result += "&#44;";
		}else if(str.charAt(i) == "-"){
			result += "&#45;";
		}else if(str.charAt(i) == "."){
			result += "&#46;";
		}else if(str.charAt(i) == "/"){
			result += "&#47;";
		}else if(str.charAt(i) == ":"){
			result += "&#58;";
		}else if(str.charAt(i) == ";"){
			result += "&#59;";
		}else if(str.charAt(i) == "<"){
			result += "&#60;";
		}else if(str.charAt(i) == "="){
			result += "&#61;";
		}else if(str.charAt(i) == ">"){
			result += "&#62;";
		}else if(str.charAt(i) == "?"){
			result += "&#63;";
		}else if(str.charAt(i) == "@"){
			result += "&#64;";
		}else if(str.charAt(i) == "["){
			result += "&#91;";
		}else if(str.charAt(i) == "\\"){
			result += "&#92;";
		}else if(str.charAt(i) == "]"){
			result += "&#93;";
		}else if(str.charAt(i) == "^"){
			result += "&#94;";
		}else if(str.charAt(i) == "_"){
			result += "&#95;";
		}else if(str.charAt(i) == "`"){
			result += "&#96;";
		}else if(str.charAt(i) == "{"){
			result += "&#123;";
		}else if(str.charAt(i) == "|"){
			result += "&#124;";
		}else if(str.charAt(i) == "}"){
			result += "&#125;";
		}else if(str.charAt(i) == "~"){
			result += "&#126;";
		}else{
			result += str.charAt(i);
		}
	}
	return result;
}

/**
 * obj 데이터 확인용
 * @param obj
 */
function showObj(obj) {
	var str = "";
	for(key in obj) {
		str += key+"="+obj[key]+" ";
	}

	// alert(str);
	commonSweetAlert(str, 'info');
	return;
}

/**
 * 로딩바 start stop
 * @returns
 */
function loadingStart(xhr) {
	$('body').loading({
		theme: 'dark'
		/*, stoppable: true*/
	});
	//xhr.setRequestHeader("AJAX", "true"); /* 2020-10-13 Ajax Error 확인하기 위해 넣었으나 문제 발생으로 주석처리 */
}
function loadingStop () {
    $('body').loading('stop');
}
/**
 * Ajax 수신 데이터 자동 셋팅
 * @param data
 * @param frm
 * @returns
 */
function ajaxDataSetting(data, frm){
	for(var key in data){
		$("#" + frm + " input").each(function(){
			if($(this).attr("name") === key){
				dataSetV($(this), key, data);
			}else if($(this).attr("id") === key){
				dataSetV($(this), key, data);
			}
		});
		$("#" + frm + " select").each(function(){
			if($(this).attr("name") === key){
				$(this).val(data[key]);
			}
		});
		
		$("#" + frm + " textarea").each(function(){
			if($(this).attr("name") === key){
				$(this).val(data[key]);
			}else if($(this).attr("id") === key){
				$(this).val(data[key]);
			}
		})
	}
}
/**
 * Name과 ID에 동일하게 같이 돌수 있는 함수로 사용하기 위해 적출
 * @param obj
 * @returns
 */
function dataSetV(obj, key, data){
	if(obj.attr("type") === "radio"){
		if(obj.val() === data[key]){
			obj.prop("checked", true);

			var id = obj.attr("id");
			
			obj.parent().parent().find('label').removeClass('checking');
			$('label[for="'+id+'"]').addClass('checking');   
		}else{
			obj.prop("checked", false);
		}
	}else if(obj.attr("type") === "checkbox"){
		if(obj.val() === data[key]){
			obj.prop("checked", true);
		}					
	}else if(typeof obj.attr("class") !== "undefined" && obj.attr("class").indexOf("datePicker") > -1){
		obj.val(dateToStringF(data[key], "yyyy-MM-dd"));
	}else if(key === "regDate" || key === "modDate" || key.indexOf("Date") > -1){
		obj.val(dateToStringF(data[key], "yyyy-MM-dd HH:mm"));
	}else{
		obj.val(data[key]);
		console.log(key);
		console.log(data[key]);
	}
}
/**
 * id : frm 인 데이터의 data-type="enc"인 정보 암호화
 * @param frm
 * @returns
 */
function dataSetEncript(frm){
	$("#"+frm +" input").each(function(){
		var rsa = new RSAKey();
	    rsa.setPublic($("#keyModulus").val(), $("#keyExponent").val());
		if($(this).attr("id") !== "undefined" && $(this).data("type") === "enc" ){
			var id = $(this).attr("id");
		    $("input[name=" + id + "]","#"+frm).val(rsa.encrypt($(this).val()));
		}
	});
}

/**
 * 공통 팝업(sweetalert)
 * msg : 알럿 내용
 * flag : 결과 구분
 *   - success : 성공
 *   - error : 실패
 *   - warning : 워링 
 *   - info : 정보
 */
function commonSweetAlert(msg, flag) {
	swal(flag, msg, flag);
}

/**
 * confirm 알럿(sweetalert)
 * @param msg
 * @param cancleBtnStr
 * @param confirmBtnStr
 * @param confirmListener
 * @param cancleListener
 * @returns
 */
function sweetConfirmAlert(msg, cancleBtnStr, confirmBtnStr, doTran, confirmListener, cancleListener) {
	swal({
		title : 'Confirm?'
		, text : msg
		, icon : 'info'
		, closeOnClickOutside : false
		, buttons : {
			cancle : {
				text : cancleBtnStr
				, value : false
				, className : 'btn btn-outline-primary'
			},
			confirm : {
				text : confirmBtnStr
				, value : true
				, className : 'btn btn-outline-primary'
			}
		}
	}).then((result) => {
		if(result) {
			if(confirmListener != null) {
				if(doTran != null) {
					confirmListener(doTran);
				} else {
					confirmListener();					
				}
			} else {
				swal('Error', '에러가 발생하였습니다..', 'error');
			}
		} else {
			if(cancleListener != null) {
				cancleListener();
			} else {
				swal('Info', cancleBtnStr+' 하였습니다.', 'info');
			}
		}
	});
	
}

/**
 * Layer Data Init
 * @param id - form id
 * @returns
 */
function cleanzingData(id){
	$("#" + id +" input").not("input[name=useYN], input[name=delYN]" ).each(function(){
		var type = $(this).attr("type");
		if($(this).attr("readonly")){
			$(this).attr("readonly", false);
		}
		if(type != "radio"){
			// text 초기화
			$(this).val("");
		}else if(type != "number"){
			// 숫자 형태 초기화
			$(this).val(0);
		}else{
			// 라디오 버튼 초기화
			$(this).attr("checked",false);
		}
	});
	if($("input:radio[name='useYN']", "#"+id).length > 0){
		$("input:radio[name='useYN']", "#"+id).prop("checked", false);
	}
	if($("input:radio[name='delYN']", "#"+id).length > 0){
		$("input:radio[name='delYN']", "#"+id).prop("checked", false);
	}

	$("#" + id +" textarea").each(function(){
		$(this).html("");
	});
}
