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

<title>haru 수정하기</title>
</head>

<body>
<jsp:include page="/view/menu/top.jsp"></jsp:include>
<jsp:include page="/view/menu/side.jsp"></jsp:include>

<div class="container">
	<div id="wrapper">
<div class="login">
<div class="container">
  <div class="col-lg-6 col-lg-offset-3">
    <h1 class="horizontal" style="font-family: GoodFont">가계부 수정하기</h1>
    <div class="inner-form">
    <form role="form" name="haruUpdatePro" method="post" action="haruUpdatePro">
    <div class="row">
      <div class="col-lg-12">
		<label class="control-label required" for="phone">번호<sup style="color:red">*</sup></label>
		<input id="main_num" name="main_num" type="text" class="form-control2" value="${vo.main_num}"  readonly/>
	  </div>
      <div class="col-lg-12">
		<label class="control-label required" for="phone">날짜<sup style="color:red">*</sup></label>
		<input id="main_writeday" name="main_writeday" type="text" class="form-control3" value="${vo.main_writeday}"  />
	  </div>
	  <div class="col-lg-12">
		<label class="control-label required" for="name">항목<sup style="color:red">*</sup></label>
		<input id="main_option" name="main_option" type="text" class="form-control2" value="${vo.main_option}" readonly/>
	  </div>
      <div class="col-lg-12">
        <label class="control-label required" for="phone">계정</label>
        <input id="main_account" name="main_account" type="text" class="form-control2" value="${vo.main_account}" readonly/>
      </div>
      <div class="col-lg-12">
        <label class="control-label required" for="email">내용</label>
        <input id="main_content" name="main_content" type="text" class="form-control3" value="${vo.main_content}" />
      </div>
      <div class="col-lg-12">
        <label class="control-label required" for="name">금액</label>
        <input id="main_price" name="main_price" type="text" class="form-control3" value="${vo.main_price}" />
      </div>
      <div class="col-lg-12" style="text-align: center;">
            <input type="button" class="btn33" onclick="haruUpdate();" value="수정완료" >
            <input type="button" class="btn33" onclick="haruwriteList();" value="목록">
        </div>
     </div>
</form>
</div> <!-- inner-form -->
</div></div></div>  </div></div>

<jsp:include page="/view/menu/footer.jsp"></jsp:include>
</body>
</html>