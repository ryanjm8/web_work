<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//DB 에서 불러온 친구 목록이라고 가정하자
	List<String> names = new ArrayList<>();
	names.add("김구라");
	names.add("해골");
	names.add("원숭이");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/friend/list.jsp</title>
</head>
<body>
	<h3>친구 목록 입니다</h3>
	<ul>
		<%for(int i=0; i<names.size(); i++){ %>
			<li><%=names.get(i) %></li>	
		<%} %>
	</ul>
	<h3>친구 목록 입니다.</h3>
	<ul>
		<%for(String tmp:names){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
</body>
</html>