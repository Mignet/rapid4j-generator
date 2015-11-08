<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<base href="<%=basePath%>">
<meta charset="utf-8"/>
 <title>DBMS Manager</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta content="" name="description"/>
<meta content="Mignet" name="author"/>
<meta name="MobileOptimized" content="320">
 <!-- Bootstrap -->
      <link href = "assets/bootstrap/3.3.1/css/bootstrap.min.css" rel = "stylesheet">

      <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
      <!-- WARNING: Respond.js doesn't work if you view the  page via file:// -->

      <!--[if lt IE 9]>
      <script src = "assets/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src = "assets/respond/1.4.2/respond.min.js"></script>
      <![endif]-->
      
<!-- END THEME STYLES -->
<link rel="shortcut icon" href="app/img/favicon.ico"/>
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body>
 <div class = "container">
   <div class="row">
    <div class="col-md-4">
   <!-- left tables tree -->
   <p id="treepanel"></p>
   </div>

   <div class="col-md-8">
   <!-- Textarea -->
   <div class="form-group">
       <textarea class="form-control" id="sql" name="sql" rows="10">select now()</textarea>

       <button id="btnQuery" name="btnQuery" class="btn btn-success">INQUIRE</button>
       <button id="btnGenerate" name="btnGenerate" class="btn btn-danger">GENERATE</button>
    </div>
<div class="table-responsive">
   <table class="table" id="result">
  		<tr> <td>没有记录!</td></tr>
    </table>
</div>

   </div>
   </div>
   </div>

        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src = "assets/libs/jquery/1.11.1/jquery.min.js"></script>

      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src = "assets/bootstrap/3.3.1/js/bootstrap.min.js"></script>

      <!-- Bootstrap组件封装集 -->
      <script src="assets/bootstrapq/bootstrapQ.js"></script>

      <script src="app/js/dashboard.js" type="text/javascript"></script>

</body>
<!-- END BODY -->
</html>