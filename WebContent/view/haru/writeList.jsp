<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
input{
	height: 30px;
}
td .view-message{
	background-color: #4a8aa0;
	color: white; 
	font-size: 16px
}



</style>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loosedtd">
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/webMybatis/css/haruFonts.css">
<link type="text/css" href="/webMybatis/css/mem.css" rel="stylesheet" />
<link type="text/css" href="/webMybatis/css/bootstrap.css" rel="stylesheet" />
<link type="text/css" href="/webMybatis/css/main.css" rel="stylesheet" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- ��¥���� ��ũ��Ʈ -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script><!-- ��¥�� -->
<script src="/webMybatis/js/search.js" type="text/javascript" charset="euc-kr"></script> 

<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' />
<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
<link href="/webMybatis/css/style.css" rel="stylesheet" />
<link href="/webMybatis/css/default.css" rel="stylesheet" />
<title>Insert title here</title>

<script>
function main_search(frm){
	//ȸ���˻����
	var frm = document.search;
	
	frm.submit();
}

//��¥���� �ڹٽ�ũ��Ʈ
 $(function() {
 	  $( "#main_writeday" ).datepicker({
 	    dateFormat: 'yymmdd'
 	  });
 	});
 $(function() {
	  $( "#main_writeday2" ).datepicker({
	    dateFormat: 'yymmdd'
	  });
	});
 
</script>
</head>
<body>
<jsp:include page="/view/menu/top2.jsp"></jsp:include>
<jsp:include page="/view/menu/side.jsp"></jsp:include>

<div class="container2" style="margin-top: 30px;">
<div id="wrapper">
<section id="content">
<div class="container">
 <div class="mail-box">
 <aside class="lg-side"></aside></div>
 <div style="text-align: center; font-family:'GoodFont'; ">
	 <b style="font-size:40px;">${name}���� ��볻�� (�� ${count}��)</b> <br>
	 <b style="font-size:25px; color:red">���� : ${mainboard.tot_spend}��</b>&nbsp;&nbsp;&nbsp;&nbsp;
	 <b style="font-size:25px; color:blue"> ����: ${mainboard.tot_import}��</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
 </div>
 
 <div style="height: 3px; background-color: #00000029; margin-top: 7px;"></div><!-- �� -->
 <br>
<!-- �˻���� -->
 <form name ="search" method="post" align="right">
 		��¥ : <input type="text" id="main_writeday" name="main_writeday" style="width: 100px;"> 
 							~ <input type="text" id="main_writeday2" name="main_writeday2" style="width: 100px;">&nbsp;&nbsp;
		�׸� : <select name="key1" style="height: 30px;" onchange="doChange(this, 'main_account')">
		<option>�׸���</option>
		<option value="1">����</option>
		<option value="2">����</option>
		</select>
		<select name="key2" id="main_account" style="height: 30px;">
		<option>��������</option>
		</select>
		<input type="button" value="�˻�" onclick="main_search(form)" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<%-- <b>���������� : ${mainboard.sum_spend}&nbsp;&nbsp;&nbsp;&nbsp;����������: ${mainboard.sum_import}</b> --%>
</form><!-- �˻����  ��-->
	
<div class="inbox-body">
<div class="mail-option">
<c:if test="${count == 0}">
<table class="table table-inbox table-hover">
	  <tr height="30">
		 <td colspan="6" style="text-align: center;">��ϵ� �Խù��� �����ϴ�.</td>
	  </tr>
</table>
</c:if>

<c:if test="${count !=0 }">
<table class="table table-inbox table-hover">
       <tr class="unread">
         <td class="view-message" align="center" width="10%" style="background-color: #4a8aa0;color: white; font-size: 16px;">��ȣ</td>
         <td class="view-message" align="center" width="20%" style="background-color: #4a8aa0;color: white; font-size: 16px;">��¥</td>
         <td class="view-message" align="center" width="15%" style="background-color: #4a8aa0;color: white; font-size: 16px;">�׸�</td>
         <td class="view-message" align="center" width="15%" style="background-color: #4a8aa0;color: white; font-size: 16px;">����</td>
         <td class="view-message" align="center" width="30%" style="background-color: #4a8aa0;color: white; font-size: 16px;">����</td>
         <td class="view-message" align="center" width="10%" style="background-color: #4a8aa0;color: white; font-size: 16px;">�ݾ�</td>
      </tr>
<c:forEach var="mainboard" items="${mainList}">
	  <tr height="30">
		<td align="center" width="10%">${mainboard.main_num}</td><!-- ��ȣ -->
		<td align="center" width="20%">
				<a href="content?num=${mainboard.main_num}">${mainboard.main_writeday}
				</a></td><!-- ��¥ -->
		<td align="center" width="15%">${mainboard.main_option}</td><!-- �׸� -->
		<td align="center" width="15%">${mainboard.main_account}</td><!-- ���� -->
		<td align="center" width="30%">${mainboard.main_content}</td><!-- ���� -->
		<td align="center" width="10%" style="text-align: right;">${mainboard.main_price}</td><!-- �ݾ� -->
	  </tr>
	  </c:forEach>
	</table></c:if>
	
	<!-- �ϴ� ������ -->
	<div class="w3-center" style="margin-top: 20px; text-align: center;">
	<c:if test="${count>0}">
	<c:if test="${startPage > bottomLine }">
		<a href="writeList?pageNum=${startPage - bottomLine}">[����]</a> 
	</c:if>
	
	<c:forEach var="i" begin="${startPage}" end="${endPage}">	
	<a href="writeList?pageNum=${i}">
	<c:if test="${i !=  currentPage}">[${i}]</c:if>
	<c:if test="${i ==  currentPage}">
	<font color='red'>[${i}]</font></c:if></a>
	</c:forEach>
	
	<c:if test="${endPage < pageCount}">
		<a href="writeList?pageNum=${startPage+bottomLine}">[����]</a>
	</c:if>	
		
</c:if>	
	</div>
	</div>
	</div>
	</div>
	</div></div>
<jsp:include page="/view/menu/footer.jsp"></jsp:include>
</body>
</html>