<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
function logout() {
	alert("�α׾ƿ��Ǿ����ϴ�.");
	  location.href="logout";
    }
function View2(){
        location.href="adList";
}
function myhome(){
    location.href="userView";
}
function login(){
	var v = document.loginForm;
	if(v.login_id.value==""){
		alert("���̵� �Է��ϼ���");
		v.login_id.focus();
		return;
	}
	if(v.login_pwd.value==""){
		alert("��й�ȣ�� �Է��ϼ���");
		v.login_pwd.focus();
		return;
	}
	v.submit();
}

function join(){
	var frm = document.joinForm;
	
	if(frm.join_id.value==""){
		alert("���̵� �Է��ϼ���");
		v.join_id.focus();
		return;
	}if(frm.join_pwd.value==""){
		alert("��й�ȣ�� �Է��ϼ���");
		v.join_pwd.focus();
		return;
	}if(frm.join_pwd2.value==""){
		alert("��й�ȣ�� �ѹ��� �Է��ϼ���");
		v.join_pwd2.focus();
		return;
	}if(frm.join_name.value==""){
		alert("�̸��� �Է��ϼ���");
		v.join_name.focus();
		return;
	}
	frm.submit();
	
}

 //���̵� �ߺ�üũ
$(function(){
   $('#join_id').blur(function(){
      $.ajax({
         type:"POST",
         url:"/webMybatis/view/join/checkSignup.jsp",
         /* url:"checkSignup", */
         data:{
            "join_id":$('#join_id').val() //m_id 
         },
         success:function(data){	//data : checkSignup���� �Ѱ��� �����
            if($.trim(data)=="YES"){
               if($('#join_id').val()!=''){ //�Է��� ���� �������� (���� ���°�쿡�� ���â�� ���ͼ� ����)
               alert("��밡���� ���̵��Դϴ�.");
               }
            }else{
               if($('#join_id').val()!=''){
                  alert("�ߺ��� ���̵��Դϴ�.");
                  $('#join_id').val('');
                  $('#join_id').focus();
               }
            }
         }
      })
   })
   //��й�ȣ Ȯ��
   $('#join_pwd2').blur(function(){
      if($('#join_pwd').val() != $('#join_pwd2').val()){
    	  if($('#join_pwd2').val()!=''){
	    	  alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
    		  $('#join_pwd2').val('');
              $('#join_pwd2').focus();
    	  }
      }
   })
});

</script>
<title>�Ϸ�,����</title>
</head>
<body>
<div id="wrapper">
<!-- start header -->
<header>
<div class="navbar navbar-default navbar-static-top">
<div class="container">
 	<div class="navbar-header">
    	<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
        	 <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand"><span>�Ϸ�,</span><b style="font-size: 40px;">����</b></a>
     </div>
               
 <!-- �α��� �ȵȰ�� -->
<c:if test="${empty id}"> 
<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
		<li class=""><a href="index">Home</a></li>
        <li><a onclick="document.getElementById('id01').style.display='block'" style="cursor:pointer;">�α���</a></li>
        <li><a onclick="document.getElementById('id02').style.display='block'" style="cursor:pointer;">ȸ������</a></li>
        <li><a href="contact" style="cursor:pointer;">ȸ����ġ</a></li>
    </ul>
</div>
</c:if>

<!-- �Ϲݻ���� �α��� -->
<c:if test="${id ne 'admin' && not empty id}">
<div class="navbar-collapse collapse">
	<ul class="nav navbar-nav">
		<li class=""><a href=#>${name}�� ȯ���մϴ�.</a></li>
		<li class=""><a href="index">Home</a></li>
		<li><a onclick="myhome()" style="cursor:pointer;">����Ȩ</a></li>
		<li><a href="contact" style="cursor:pointer;">ȸ����ġ</a></li>
		<li><a onclick="logout()" style="cursor:pointer;">�α׾ƿ�</a></li>
	</ul>
</div>
</c:if>
                  
<!-- ���� �α��� -->
<c:if test="${id eq 'admin'}"> 
<div class="navbar-collapse collapse">
    <ul class="nav navbar-nav">
    	<li class=""><a href=#>${name}�� ȯ���մϴ�.</a></li>
        <li class=""><a href="admin">Home</a></li>
        <li><a onclick="View2()" style="cursor:pointer;">ȸ������</a></li>
        <li><a href="contact" style="cursor:pointer;">ȸ����ġ</a></li>
        <li><a onclick="logout()" style="cursor:pointer;">�α׾ƿ�</a></li>
	</ul>
</div>
</c:if>
                  
</div></div></header>
      
      
<!-- �α��� ��޼ҽ� -->
<div class="w3-container">
<div id="id01" class="w3-modal">
<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="width:600px">
<div class="w3-container w3-border-top w3-padding-16 w3-light-grey " style="text-align: center; margin-top: 10px;">
	<b style="font-size: 24px;">�� �� ��</b>
    <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
</div>
<form method="post" name="loginForm" action="login">
<div class="w3-container">   
<div class="w3-section"><br>
	<label><b>���̵�</b></label>
    	<input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="Enter UserID" id="login_id" name="login_id" required>
    <label><b>��й�ȣ</b></label>
        <input class="w3-input w3-border" type="password" placeholder="Enter Password" id="login_pwd" name="login_pwd" required>
    <button class="w3-button w3-block w3-section w3-padding" type="button" onclick="login();" style="background-color: #d25a5a; color: white; margin-top: 10px; height: 50px; font-size: 20px;">login</button>
</div></div></form></div></div></div> <!-- �α��� �� -->



<!-- ȸ������ ��޼ҽ� -->
<div class="w3-container">
<div id="id02" class="w3-modal">
<div class="w3-modal-content w3-card-4 w3-animate-zoom" style="width:600px">
<div class="w3-container w3-border-top w3-padding-16 w3-light-grey " style="text-align: center; margin-top: 10px;">
	<b style="font-size: 24px;">ȸ �� �� ��</b>
    <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
</div>
<form name="joinForm" id="joinForm" method="post" action="join">
	<div class="w3-container">
	<div class="w3-section" style="margin-left: 100px;">
<table>
	<tr>
		<td><label><b>���̵�</b><sub style="color: red;">*&nbsp;</sub></label></td>
        <td><input class="w3-border w3-margin-bottom" type="text" placeholder="Enter UserID" id="join_id" name="join_id" style="height: 30px;" required>&nbsp;&nbsp;
    </tr>
    <tr>
        <td><label><b>��й�ȣ</b><sub style="color: red;">*</sub></label></td>&nbsp;
		<td><input class="w3-border w3-margin-bottom" type="password" placeholder="Enter Password" id="join_pwd" name="join_pwd" style="height: 30px;"  required></td>
	</tr>
	<tr>
		<td><label><b>��й�ȣ Ȯ��</b><sub style="color: red;">*&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</sub></label></td>&nbsp;
		<td><input class="w3-border w3-margin-bottom" type="password" placeholder="Enter Password" id="join_pwd2" name="join_pwd2"style="height: 30px;"  required></td>
	</tr>
    <tr>   
		<td><label><b>�̸�</b><sub style="color: red;">*</sub></label></td>&nbsp;
		<td><input class="w3-border w3-margin-bottom" type="text" placeholder="Enter Name" id="join_name" name="join_name" style="height: 30px;"  required></td>
	</tr>   
	<tr>
		<td><label><b>�������</b></label></td>&nbsp;
        <td><input class="w3-border w3-margin-bottom" type="text" placeholder="Enter BirthDay" id="join_birth" name="join_birth" style="height: 30px;"  required></td>
	</tr>
	<tr>
		<td><label><b>�� �ѵ��ݾ�</b></label></td>&nbsp;
        <td><input class="w3-border w3-margin-bottom" type="text" placeholder="Enter Money" id="join_money" name="join_money" style="height: 30px;"  required></td>
	</tr>
	<tr>   
		<td><label><b>ȸ�����</b></label></td>&nbsp;
		<td><input class="w3-border w3-margin-bottom" type="text" id="join_level" name="join_level" value="����ȸ��" style="background-color: #d6d9da; height: 30px;" readonly></td>
	</tr>   
	<tr>
		<td><label><b>�̸����ּ�</b></label></td>&nbsp;
		<td><input class="w3-border w3-margin-bottom" type="email" placeholder="Enter Email" id="join_email" name="join_email" style="height: 30px;" required></td>
	</tr>
</table><br>
</div><!-- w3-section -->
</div><!-- w3-container -->
	<button class="w3-button w3-block w3-section w3-padding" type="button" style="background-color: #d25a5a; color: white; margin-top: 10px; height: 80px; font-size: 20px;" onclick="join();">Join</button>
</form></div></div></div> <!-- ȸ������ �� -->