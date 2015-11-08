//禁止提交按钮多次提交
$(function() {
	$('form').on('submit', function() {
		showLoading();
	});
});

// 禁止ajax执行过程中页面的其他操作
$(document).ajaxStart(function() {
	showLoading();
}).ajaxStop(function() {
	hideLoading();
});

// ajax请求开始显示进度窗体
function showLoading() {
	var background = $('<div id="loading_div" style="z-index: 999998; position: fixed; left: 0px; top: 0px; width: 100%; background-color:#ffffff; filter: alpha(opacity=0); -moz-opacity: 0; -khtml-opacity: 0; opacity: 0;"></div>');
	var div = $('<div id="loading_pic" style="z-index: 999999; position: fixed; height: 60px; width: 100%; text-align:center;"><img src="../assets/img/loading.gif"/></div>');
	$(document.body).append(div);
	$(document.body).append(background);
	background.height($(window).height());
	div.css({
		top : (background.outerHeight() - div.outerHeight()) / 2 + "px"
	});
}
// ajax请求结束移除进度窗体
function hideLoading() {
	$("#loading_div", document.body).remove();
	$("#loading_pic", document.body).remove();
}