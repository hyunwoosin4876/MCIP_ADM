$(document).ready(function() {

	// href="#"
	$(document).on('click', 'a[href="#"]', function(e) {
		e.preventDefault();
	});

	// 사이드 메뉴 보기
	$(".mega-menu").click(function() {
		if ($(this).hasClass("toggle")) {
			$(".snb").toggleClass("on");
		} else {
			location.href = "/"
		}
	});
	
});

/**
 * 트리 구조 선택 on
 *  - t : this
 * @param t
 * @returns
 */
function setTreeOn(t) {
	var btn = $(t).parent("li");
	var root = $(btn).parent("ul");
	var root2 = $(root).parent("li");
	var root3 = $(root2).parent("ul");
	$("#treemenu .on").removeClass("on");
	$(root3).find("li").removeClass("on");
	$(root).children("li").removeClass("on");
	$(btn).addClass("on");
}

