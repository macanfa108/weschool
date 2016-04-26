<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE >
<html>
  <head>
    <title>海大微校园</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="../../css/mui.min.css">
	
  </head>
  
  <body>
  	adfas
    <h1>${bookInfo.name }</h1><!-- 图书名 -->
    <div>${bookInfo.author }</div><!-- 作者 -->
    <div>${bookInfo.publisher }</div><!-- 出版社 -->
    <div>${bookInfo.date }</div><!-- 出版日期 -->
    <div>${bookInfo.content }</div><!-- 图书简介 -->
    <div>${bookInfo.isbn }</div><!-- ISBN -->
    <div>${bookInfo.price }</div><!-- 价格 -->
    <table>
    <tr>
    <th>馆藏地</th>
    <th>索取号</th>
    <th>状态</th>
    </tr>
    <c:forEach items="${bookInfo.locations}" var="location">
    <tr>
    <td>${location.local }</td>
    <td>${location.num }</td>
    <td>${location.status }</td>
    </tr>
    </c:forEach>
    </table>
    
  </body>
</html>