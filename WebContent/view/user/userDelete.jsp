<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="/webMybatis/css/haruFonts.css">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link type="text/css" href="/webMybatis/css/loginForm.css" rel="stylesheet" />
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"> 

<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' />
<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
<link href="/webMybatis/css/style.css" rel="stylesheet" />
<link href="/webMybatis/css/default.css" rel="stylesheet" />
<title>Insert title here</title>
<style type="text/css">
img{
	display : block;
	margin: 0 auto;
}
</style>

<script type="text/javascript">
function userDelete() {
	var v = document.userDeleteForm;
	v.submit();
}
function goback(){
	history.go(-1);
	}
</script>
</head>
<body>
<jsp:include page="/view/menu/top.jsp"></jsp:include>
<jsp:include page="/view/menu/side.jsp"></jsp:include>

<div class="container">
<div id="wrapper">
<div class="login">
<div class="container">
  <div class="col-lg-6 col-lg-offset-3">
    <h1 class="horizontal" style="font-family:GoodFont;">ȸ��Ż��</h1>
    <div class="inner-form">
    <form role="form" name="userDeleteForm" method="post" action="userDeleteP">
    <input type="hidden" name="m_id" value="${vo.m_id}">
    <input type="hidden" name="m_num" value="${vo.m_num}">
    <div class="row">
      
      <div class="col-lg-12">
				<label class="control-label required" for="phone">��й�ȣ<sup style="color:red">*</sup></label>
		        <input id="m_pwd" name="m_pwd" type="password" class="form-control2">
		    </div>
    	<div class="col-lg-12" style="text-align: center">
            <input type="button" class="btn22" onclick="userDelete();" value=ȸ��Ż��>
            <input type="button" class="btn22" onclick="goback();" value="��������">
        </div>
     </div>
</form>
</div> <!-- inner-form -->
</div></div></div></div></div>
<jsp:include page="/view/menu/footer.jsp"></jsp:include>
</body>
</html>