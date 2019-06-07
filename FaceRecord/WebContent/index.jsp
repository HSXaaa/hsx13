<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人脸识别记录系统</title> 
</head>
<body>

<form action="http://192.168.1.104:8090/setIdentifyCallBack" method="post" enctype="x-www-form-urlencoded">
  <input type="hidden" name="pass" value="12345678">
  <input type="hidden" name="callbackUrl" value="http://192.168.1.106:8080/FaceRecord/face/record.do">
  <input type="submit" value="设置419人脸识别记录回调地址"/>
</form>
<br/>

<form action="http://172.28.1.2:8090/setIdentifyCallBack" method="post" enctype="x-www-form-urlencoded">
  <input type="hidden" name="pass" value="12345678">
  <input type="hidden" name="callbackUrl" value="http://172.28.1.11:8080/FaceRecord/face/record.do">
  <input type="submit" value="设置422人脸识别记录回调地址"/>
</form>

</body>
</html>