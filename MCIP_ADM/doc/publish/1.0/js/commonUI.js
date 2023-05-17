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
	
	//파일추가
	addFileAttach();
	$('.btn_fileAdd').off('click').on('click', function(){
		addFileAttach('.file_group');
	});
});


//파일추가
function addFileAttach(targetGroup) {
	var file = '<div class="file_attach">' 
	+'   <label class="label"><input type="file" class="upload_hidden"></label>'  
	+'   <input value="" placeholder="파일검색" class="upload_name">'
	+'   <button type="button" class="btn_fileDel">파일삭제</button>'
	+'</div>';
	$(targetGroup).append(file);
	setAttachEvent();
}

//파일추가 이벤트 설정
function setAttachEvent() {
	$('.upload_hidden').off('change').on('change', function(){
		if(window.FileReader){
		var filename = $(this)[0].files[0].name;
		} else {
		var filename = $(this).val().split('/').pop().split('\\').pop();
		}
		$(this).parent().siblings('.upload_name').val(filename);
	});

	$('.btn_fileDel').off('click').on('click', function(){
		var attachCnt = $(this).parents('.file_group').find('.file_attach').length;
		$(this).parents('.file_attach').remove();
		if(attachCnt == 1) addFileAttach('.file_group');
	});

	$('.file_attach .upload_name').off('click').on('click', function(){
		$(this).parents('.file_attach').find('.upload_hidden').click();
	});
}