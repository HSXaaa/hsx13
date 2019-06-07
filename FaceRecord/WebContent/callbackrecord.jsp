<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人脸识别记录系统</title> 
</head>
<body>

<form action="${pageContext.request.contextPath }/face/record.do" method="post" enctype="x-www-form-urlencoded">
  personId：<input type="text" name="personId" value=""><br/>
  deviceKey:<input type="text" name="deviceKey" value=""><br/>
  type：<input type="text" name="type" value=""><br/>
  ip：<input type="text" name="ip" value=""><br/>
  time：<input type="text" name="time" value=""><br/>
  path：<input type="text" name="path" value=""><br/>
  <input type="submit" value="callback"/>
</form>

</body>
</html>