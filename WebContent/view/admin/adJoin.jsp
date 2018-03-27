<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' />
<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
<link href="/webMybatis/css/style.css" rel="stylesheet" />
<link href="/webMybatis/css/default.css" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link type="text/css" href="/webMybatis/css/loginForm.css" rel="stylesheet" />
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<head>
<style type="text/css">
img{
	display : block;
	margin: 0 auto;
}
</style>
<script type="text/javascript">
function adJoinForm() {
	var v = document.adJoin;
	if(v.ad_m_id.value==""){
		alert("���̵� �Է��ϼ���");
		v.ad_m_id.focus();
		return;
	}
	if(v.ad_m_pwd.value==""){
		alert("��й�ȣ�� �Է��ϼ���");
		v.ad_m_pwd.focus();
		return;
	}
	if(v.ad_m_name.value==""){
		alert("�̸��� �Է��ϼ���");
		v.ad_m_name.focus();
		return;
	}
	v.submit();
} 


function adJoinRewrite(){	//�ٽ� �ۼ�
	 document.adJoin.reset();
}
function adminList(){	//ȸ��������� �̵�
	location.href = "adList"; 
}

</script>
<title>������ ȸ�����</title>
</head>
<body>
<!-- ž�޴� -->
<jsp:include page="/view/menu/top.jsp"></jsp:include>

<div class="container">
	<div id="wrapper">
<div class="login">
<div class="container">
  <div class="col-lg-6 col-lg-offset-3">
    <h1 class="horizontal">ȸ�����</h1><!-- <div class="icon text-center"><i class="fa fa-paper-plane fa-2x" aria-hidden="true"></i></div> -->
    <div class="inner-form">
    <form name="adJoin" method="post" action="adJoinPro">
    <div class="row">
      <div class="col-lg-12">
		<label class="control-label required" for="phone">���̵�<sup style="color:red">*</sup></label>
        <input id="ad_m_id" name="ad_m_id" type="text" class="form-control3" placeholder="Enter ID">
	  </div>
	  <div class="col-lg-12">
                <label class="control-label required" for="password">��й�ȣ<sup style="color:red">*</sup></label>
                <input id="ad_m_pwd" name="ad_m_pwd" type="password" class="form-control3" placeholder="Enter password">
            </div>
	  
		    <div class="col-lg-12">
		        <label class="control-label required" for="name">�̸�<sup style="color:red">*</sup></label>
		        <input id="ad_m_name" name="ad_m_name" type="text" class="form-control3" placeholder="Enter Name">
		    </div>
            <div class="col-lg-12">
                <label class="control-label required" for="phone">�������</label>
                <input id="ad_m_birth" name="ad_m_birth" type="text" class="form-control3" placeholder="Enter BirthDay">
            </div>
            <div class="col-lg-12">
                <label class="control-label required" for="email">Email</label>
                <input id="ad_m_email" name="ad_m_email" type="text" class="form-control3" placeholder="Enter Email Address">
            </div>
        <div class="col-lg-12">
                <label class="control-label required" for="name" >ȸ�����</label>
                <select name="ad_m_level" style="width: 380px; height: 40px; margin-bottom: 30px;">
			        <option value="����ȸ��">����ȸ��</option>
			        <option value="��ȸ��">��ȸ��</option>
			        <option value="���ȸ��">���ȸ��</option>
			        <option value="������">������</option>
			   </select>
        </div>
        <div class="col-lg-12">
                <label class="control-label required" for="email">�� �ѵ� �ݾ�</label>
                <input id="ad_m_money" name="ad_m_money" type="text" class="form-control3" placeholder="Enter Money">
            </div>
    	<div class="col-lg-12" style="text-align: center">
            <input type="button" class="btn33" onclick="adJoinForm();" value="ȸ�����">
            <input type="button" class="btn33" onclick="adJoinRewrite();" value="�ٽ��ۼ�">
            <input type="button" class="btn33" onclick="adminList();" value="ȸ�����">
        </div>
     </div>
</form>
</div> <!-- inner-form -->
</div></div></div>    
</div></div>
<!-- ���� -->
<jsp:include page="/view/menu/footer.jsp"></jsp:include>
</body>
</html>