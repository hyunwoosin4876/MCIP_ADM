$(function(){
	
	//LNB
    $('#lnb').find('li').each(function() {
		if($(this).find('ul').length > 0) {
			$(this).addClass("hasDepth");
		}
	});
	$("#lnb ul li.hasDepth > a").on('click',function(){
		$(this).removeAttr('href');
		var element = $(this).parent('li');
		if (element.hasClass('on')) {
			element.removeClass('on');
			element.find('li').removeClass('on');
			element.find('ul').slideUp();
		}else {
			element.addClass('on');
			element.children('ul').slideDown();
			element.siblings('li').children('ul').slideUp();
			element.siblings('li').removeClass('on');
			element.siblings('li').find('li').removeClass('on');
			element.siblings('li').find('ul').slideUp();
		}
	});

});
