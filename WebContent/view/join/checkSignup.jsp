<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="member.memDAO"%>
<%
	String id = request.getParameter("join_id");
	String str = "";

	memDAO dao = memDAO.getInstance();

	int idcheck = dao.idCheck(id);
	
	if(idcheck==1){ //�̹� �����ϴ� ����
		str = "NO";	
		out.print(str);
	}else{	//��� ������ ����
		str = "YES";	
		out.print(str);
	}
	
%>
