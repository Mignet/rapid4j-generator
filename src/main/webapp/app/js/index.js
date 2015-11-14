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
				    time : 2000
				});
			}
		});
		//避免form表单提交
		return false;
	});
	$("#dbtype").change(function () { 
		if($(this).children('option:selected').val()=='MySQL'){
			$('#dbhost').val('127.0.0.1');
			$('#dbport').val('3306');
			$('#dbname').val('mysql');
			$('#username').val('root');
			$('#password').val('v5ent');
		}
		if($(this).children('option:selected').val()=='PostgreSQL'){
			$('#dbhost').val('127.0.0.1');
			$('#dbport').val('5432');
			$('#dbname').val('postgres');
			$('#username').val('postgres');
			$('#password').val('postgres');
		}
	}) 
	$("#test").bind("submi", function() {
		$.post("/rest/db/test", $("#dbform").serialize(), function(result) {
			//连接测试成功之后，提交。
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