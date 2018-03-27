<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<style>
input{
	height: 30px;
}
</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/webMybatis/css/haruFonts.css">
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' />
<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
<link href="/webMybatis/css/style.css" rel="stylesheet" />
<link href="/webMybatis/css/default.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link type="text/css" href="/webMybatis/css/mem.css" rel="stylesheet" />
<link type="text/css" href="/webMybatis/css/bootstrap.css" rel="stylesheet" />
<link type="text/css" href="/webMybatis/css/main.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">

<title>ȸ�����</title>

<script>
function mem_search(frm){
	//ȸ���˻����
	var frm = document.search;
	frm.submit();
}
function goAdJoin(){
	//ȸ�����
	location.href = "adJoin"; 
}
</script>
</head>
<body>
<!-- ž�޴� -->
<jsp:include page="/view/menu/top.jsp"></jsp:include>

<div class="container2" style="margin-top: 30px;">
<div id="wrapper">
<section id="content">
<div class="container">
<div class="mail-box">
<aside class="lg-side"></aside></div><br>
<div style="text-align: center;"><h1 style="font-family:GoodFont;">Haru ȸ������ (ȸ���� ${count} ��)</h1></div>
<div style="height: 3px; background-color: #00000029;  margin-bottom: 8px"></div><!-- �� -->
<div class="inbox-body">
<div class="mail-option">
	<table class="table table-inbox table-hover">
	<c:if test="${count ==0 }">
		<tr height="30">
			<td colspan="6" style="text-align: center;">��ϵ� ����ڰ� �����ϴ�.</td>
		</tr>
	</c:if>
	</table>
	
	<c:if test="${count !=0 }">
	<table class="table table-inbox table-hover">
        <tr class="unread">
            <td class="view-message" align="center" width="50" style="background-color: #4a8aa0;color: white; font-size: 16px;">ȸ����ȣ</td>
            <td class="view-message" align="center" width="50" style="background-color: #4a8aa0;color: white; font-size: 16px;">���̵�</td>
            <td class="view-message" align="center" width="50" style="background-color: #4a8aa0;color: white; font-size: 16px;">�̸�</td>
            <td class="view-message" align="center" width="70" style="background-color: #4a8aa0;color: white; font-size: 16px;">�������</td>
            <td class="view-message" align="center" width="50" style="background-color: #4a8aa0;color: white; font-size: 16px;">�����Ͻ�</td>
            <td class="view-message" align="center" width="50" style="background-color: #4a8aa0;color: white; font-size: 16px;">ȸ�����</td>
		</tr>
	<c:forEach var="member" items="${memList}">   
		<tr height="30">
		<!-- �󼼺��� ������ num�� �Ѱ��ش� -->
			<td align="center" width="50">${member.m_num}</td>
			<td align="center" width="50">
				<a href="adView?num=${member.m_num}&pageNum=${currentPage}">${member.m_id}</a></td>
			<td align="center" width="50">${member.m_name}</td>
			<td align="center" width="70">${member.m_birth}</td>
			<td align="center" width="50">${member.m_reg_date}</td>
			<td align="center" width="50">${member.m_level}</td>
		</tr>
	</c:forEach>
	</table></c:if>
	
	<!-- �ϴ� ������ -->
	<div class="w3-center" style="margin-top: 20px; text-align: center;">
	<c:if test="${count>0}">
	<c:if test="${startPage > bottomLine }">
		<a href="adList?pageNum=${startPage - bottomLine}">[����]</a> 
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">	
	<a href="adList?pageNum=${i}">
	<c:if test="${i !=  currentPage}">[${i}]</c:if>
	<c:if test="${i ==  currentPage}">
	<font color='red'>[${i}]</font></c:if></a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<a href="adList?pageNum=${startPage+bottomLine}">[����]</a>
	</c:if>	
		
</c:if>
	</div>
	<br>

	
	<!-- ȸ�� �˻� -->
	<form name ="search" method="post" style="text-align: center;">
		<select name="keyField" style="height: 30px;">
		<option>����</option>
		<option value="m_id">���̵�</option>
		<option value="m_name">�̸�</option>
		<option value="m_level">ȸ�����</option>
		</select>
		<input type="text" size="20" name="keyWord">
		<input type="button" value="�˻�" onclick="mem_search(form)" />&nbsp;
		<input type="button" onclick="goAdJoin()" value="ȸ�����">
	</form>
	</div>
	</div>
	</div></section>
	</div></div>
<!-- ���� -->
<jsp:include page="/view/menu/footer.jsp"></jsp:include>

</body>
</html>