<!-- deletePro -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core"%>

<!-- ȸ����� ����/���� �޽��� -->
<c:if test="${cnt == 1}">
	<script type="text/javascript">
	alert("ȸ����� �Ǿ����ϴ�.");
	location.href = "index";
	</script>
</c:if> 

<c:if test="${cnt == 0}">
<script type="text/javascript">
alert("ȸ����� �����߽��ϴ�.");
history.go(-1);
</script>
</c:if> 
