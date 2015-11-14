$(document).ready(function(){
      // 普通tree
      $('#treepanel').bstree({url:'rest/db',height:'auto',open:false});
  	$("#btnQuery").bind("click", function() {
		$.post("/rest/db/inquire", {"sql":$("#sql").val()}, function(result) {
			if (result.success) {
				//构造结果表格
				$("#result").html();
				var table,th,td = "";
				var thead = " <thead><tr>";
				var thead_ = "</tr></thead>";
				var tbody = " <tbody>";
				var tbody_ = " </tbody>";
				if(result.data.length==0){
					td +="<td>没有记录!</td>";
				}else{
					$.each(result.data,function(i,item){
						td += "<tr>";
						$.each(item,function(key,value){
							if(i==0){
								th += "<th>"+key+"</th>";
							}
							td +="<td>"+value+"</td>";
						});
						td+"</tr>"
					});
				}
				table = thead+th+thead_+tbody+td+tbody_;
				$("#result").html(table);
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