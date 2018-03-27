<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

<script type="text/javascript">
function haruUpdate(){
	var v = document.haruUpdatePro;
	v.submit();
}

function haruwriteList(){
	location.href = "writeList"; 
	}
</script>

<title>haru �����ϱ�</title>
</head>

<body>
<jsp:include page="/view/menu/top.jsp"></jsp:include>
<jsp:include page="/view/menu/side.jsp"></jsp:include>

<div class="container">
	<div id="wrapper">
<div class="login">
<div class="container">
  <div class="col-lg-6 col-lg-offset-3">
    <h1 class="horizontal" style="font-family: GoodFont">����� �����ϱ�</h1>
    <div class="inner-form">
    <form role="form" name="haruUpdatePro" method="post" action="haruUpdatePro">
    <div class="row">
      <div class="col-lg-12">
		<label class="control-label required" for="phone">��ȣ<sup style="color:red">*</sup></label>
		<input id="main_num" name="main_num" type="text" class="form-control2" value="${vo.main_num}"  readonly/>
	  </div>
      <div class="col-lg-12">
		<label class="control-label required" for="phone">��¥<sup style="color:red">*</sup></label>
		<input id="main_writeday" name="main_writeday" type="text" class="form-control3" value="${vo.main_writeday}"  />
	  </div>
	  <div class="col-lg-12">
		<label class="control-label required" for="name">�׸�<sup style="color:red">*</sup></label>
		<input id="main_option" name="main_option" type="text" class="form-control2" value="${vo.main_option}" readonly/>
	  </div>
      <div class="col-lg-12">
        <label class="control-label required" for="phone">����</label>
        <input id="main_account" name="main_account" type="text" class="form-control2" value="${vo.main_account}" readonly/>
      </div>
      <div class="col-lg-12">
        <label class="control-label required" for="email">����</label>
        <input id="main_content" name="main_content" type="text" class="form-control3" value="${vo.main_content}" />
      </div>
      <div class="col-lg-12">
        <label class="control-label required" for="name">�ݾ�</label>
        <input id="main_price" name="main_price" type="text" class="form-control3" value="${vo.main_price}" />
      </div>
      <div class="col-lg-12" style="text-align: center;">
            <input type="button" class="btn33" onclick="haruUpdate();" value="�����Ϸ�" >
            <input type="button" class="btn33" onclick="haruwriteList();" value="���">
        </div>
     </div>
</form>
</div> <!-- inner-form -->
</div></div></div>  </div></div>

<jsp:include page="/view/menu/footer.jsp"></jsp:include>
</body>
</html>