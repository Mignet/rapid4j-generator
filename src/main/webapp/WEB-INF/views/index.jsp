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
 <title>Select DBMS</title>
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
   <form id="dbform" class="form-horizontal" action="/rest/db/login" method="post">
   <fieldset>

   <!-- Form Name -->
   <legend>Currently DBMS</legend>

   <!-- Select Basic -->
   <div class="form-group">
     <label class="col-md-4 control-label" for="dbtype">数据库TYPE</label>
     <div class="col-md-4">
       <select id="dbtype" name="dbtype" class="form-control">
         <option value="PostgreSQL">PostgreSQL</option>
         <option value="MySQL">MySQL</option>
       </select>
     </div>
   </div>

   <!-- Text input-->
   <div class="form-group">
     <label class="col-md-4 control-label" for="dbhost">数据库HOST</label>
     <div class="col-md-4">
     <input id="dbhost" name="dbhost" type="text" value="127.0.0.1" class="form-control input-md" required="">
     </div>
   </div>

   <!-- Text input-->
   <div class="form-group">
     <label class="col-md-4 control-label" for="dbport">数据库PORT</label>
     <div class="col-md-4">
     <input id="dbport" name="dbport" type="text" value="5432" class="form-control input-md" required="">
     </div>
   </div>

   <!-- Text input-->
   <div class="form-group">
     <label class="col-md-4 control-label" for="dbname">数据库NAME</label>
     <div class="col-md-4">
     <input id="dbname" name="dbname" type="text" value="postgres" class="form-control input-md" required="">
     </div>
   </div>

   <!-- Text input-->
   <div class="form-group">
     <label class="col-md-4 control-label" for="username">用户名</label>
     <div class="col-md-4">
     <input id="username" name="username" type="text" value="postgres" class="form-control input-md" required="">
     </div>
   </div>

   <!-- Password input-->
   <div class="form-group">
     <label class="col-md-4 control-label" for="password">密码</label>
     <div class="col-md-4">
       <input id="password" name="password" type="password" value="postgres" class="form-control input-md" required="">
     </div>
   </div>

   <!-- Button (Double) -->
   <div class="form-group">
     <label class="col-md-4 control-label" for="test"></label>
     <div class="col-md-8">
       <button id="test" name="test" class="btn btn-success" onclick="return false;">TEST</button>
       <button id="submit" name="submit" class="btn btn-danger">LOGIN</button>
     </div>
   </div>

   </fieldset>
   </form>
   </div>

      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src = "assets/libs/jquery/1.11.1/jquery.min.js"></script>

      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src = "assets/bootstrap/3.3.1/js/bootstrap.min.js"></script>

      <!-- Bootstrap组件封装集 -->
      <script src="assets/bootstrapq/bootstrapQ.js"></script>
      <script src="app/js/index.js" type="text/javascript"></script>

</body>
<!-- END BODY -->
</html>
