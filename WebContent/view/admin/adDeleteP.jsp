<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<c:if test="${passcheck == 1 }">
<script>
	alert("Ż�� ó�� �Ǿ����ϴ�");
	location.href = "adList";
</script>
</c:if>

<c:if test="${passcheck != 1 }">
<script type="text/javascript">
alert("��й�ȣ�� ���� �ʽ��ϴ�");
history.go(-1);
</script>
</c:if>
