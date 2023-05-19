$(function(){
	
	$.datepicker.regional['ko'] = {
		prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년',
		dateFormat:'yy-mm-dd'

	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);

	$('.datepicker').datepicker();

	$('.btn_calendar').on('click', function(){
			$(this).prev().focus();
	});


		
	$(".timepicker").timepicker({

		lang : 'ko',
		//timeFormat: 'HH:mm',
		timeFormat: 'p h:mm ',
		interval: 1,
		startTime: '00:00',
		//dynamic: false,
		dropdown: true,
		scrollbar: true

	

	});
	
	/*
	$(".datetimepicker").datetimepicker({
		dateFormat:'yy-mm-dd',
		monthNamesShort:[ '1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월' ],
		dayNamesMin:[ '일', '월', '화', '수', '목', '금', '토' ],
		changeMonth:true,
		changeYear:true,
		showMonthAfterYear:true,

		// timepicker 설정
		timeFormat:'HH:mm:ss',
		controlType:'select',
		oneLine:true,
	});
	*/


});
