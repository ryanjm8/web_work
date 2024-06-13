<%@page import="test.friend.dao.friendDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int num = Integer.parseInt(request.getParameter("num"));

	new friendDao().delete(num);

	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/friend/list.jsp");
%>