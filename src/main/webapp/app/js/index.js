$(document).ready(function() {
	$("#test").bind("click", function() {
		$.post("/rest/db/test", $("#dbform").serialize(), function(result) {
			if (result.success) {
				// 普通消息提示条
				bootstrapQ.msg(result.message);
			} else {
				bootstrapQ.msg({
				    msg  : result.message,
				    type : 'danger',
				    time : 3000
				});
			}
		});
		//避免form表单提交
		return false;
	});
	$("#test").bind("submi", function() {
		$.post("/rest/db/test", $("#dbform").serialize(), function(result) {
			if (result.success) {
				$("#dbform").submit();
			} else {
				bootstrapQ.msg({
					msg  : result.message,
					type : 'danger',
					time : 3000
				});
			}
		});
		//避免form表单提交
		return false;
	});
});