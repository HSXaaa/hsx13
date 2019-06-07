<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人脸识别记录后台管理系统</title> 
<link href="${pageContext.request.contextPath }/css/main.d8e0d294.css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery1.42.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/Validform_v5.3.2_min.js"></script>

<script language="javascript">
   $(function(){   	
    $("#deleteRecord").submit(function(){
    	if($("#recordId").val()==""){	
    		alert("记录编号不能为空！");
    		return false;
    	}else{
    		return true;
    	}
    })
  })
</script>

<script language="javascript">
    function deleteall(){
        if (confirm("确定删除所有记录？")){
            window.open("${pageContext.request.contextPath }/server/deleteall.do","_blank")
        }
    }
</script>

</head>
<body>
<main class="content-wrapper">

<header class="white-text-container section-container">
  <div class="text-center">
     <h1>人脸识别记录后台管理系统</h1>
  </div>
</header>

<div class="container">

  <div class="card">
  
   <div class="card-block">
   
    <h2 align="center"><a href="${pageContext.request.contextPath }/server/read.do" target="_blank">查看所有记录</a></h2>
    
    <div align="center">
      <form action="${pageContext.request.contextPath }/server/delete.do" method="post" id="deleteRecord">
      <input type="text" name="recordId" value="" placeholder="记录编号id，eg：1" style="width:200px; height:30px;" id="recordId"/><br/>
	  <input type="submit" value="根据编号删除记录"/>	
      </form>
    </div>
    <br/>
    <h2 align="center"><a onclick="deleteall()">删除所有记录</a></h2>
    
   </div>
   
  </div>
  
</div>

</main>

<footer class="footer-container white-text-container text-center">
  <div class="container">
    <div class="row">
      <div class="col-xs-12">
        <p><small>&copy; Copyright 2018-2019 hsxdy123. All Rights Reserved</small></p>
      </div>
    </div>
  </div>
</footer>
		
<script>
  document.addEventListener("DOMContentLoaded", function (event) {
     scrollRevelation('.card');
  });
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/main.bc58148c.js"></script>
</body>
</html>