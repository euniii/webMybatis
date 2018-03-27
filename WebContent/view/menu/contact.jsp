<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="description" content="" />
	<link rel="stylesheet" href="/webMybatis/css/haruFonts.css">
	<!-- css -->
	<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
	<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
	<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
	<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
	<link href="/webMybatis/css/style.css" rel="stylesheet" />
	<!-- Theme skin -->
	<link href="/webMybatis/css/default.css" rel="stylesheet" />
	
<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' />
<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
<link href="/webMybatis/css/style.css" rel="stylesheet" />
<link href="/webMybatis/css/default.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>

<jsp:include page="/view/menu/top.jsp"></jsp:include>
<jsp:include page="/view/menu/side.jsp"></jsp:include>

<div class="container">
	<div align="center" style="width:1130px; height:60px; padding-top:15px; background-color: #F2F2F2; margin-bottom: 80px;">
		<b style="font-family: GoodFont; font-size: 25px;"> (��)�Ϸ�,���� ã�ƿ��ô� ��</b>
	</div>
		<section id="content">
			<div class="map">
				<div id="google-map" data-latitude="37.556510" data-longitude="126.919516"></div>
				<!-- <div id="google-map" data-latitude="40.713732" data-longitude="-74.0092704"></div> -->
			</div>
			
		</section>
		<!-- ���� -->
<jsp:include page="/view/menu/footer2.jsp"></jsp:include>
	
	<a href="#" class="scrollup"><i class="fa fa-angle-up active"></i></a>
	<!-- javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="/webMybatis/js/jquery.js"></script>
	<script src="/webMybatis/js/jquery.easing.1.3.js"></script>
	<script src="/webMybatis/js/bootstrap.min.js"></script>
	<script src="/webMybatis/js/jquery.fancybox.pack.js"></script>
	<script src="/webMybatis/js/jquery.fancybox-media.js"></script>
	<script src="/webMybatis/js/google-code-prettify/prettify.js"></script>
	<script src="/webMybatis/js/portfolio/jquery.quicksand.js"></script>
	<script src="/webMybatis/js/portfolio/setting.js"></script>
	<script src="/webMybatis/js/jquery.flexslider.js"></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD8HeI8o-c1NppZA-92oYlXakhDPYR7XMY"></script>
	<script src="/webMybatis/js/animate.js"></script>
	<script src="/webMybatis/js/custom.js"></script>
	<script>
		jQuery(document).ready(function($) {
			//Google Map
			var get_latitude = $('#google-map').data('latitude');
			var get_longitude = $('#google-map').data('longitude');

			function initialize_google_map() {
				var myLatlng = new google.maps.LatLng(get_latitude, get_longitude);
				var mapOptions = {
					zoom: 14,
					scrollwheel: false,
					center: myLatlng
				};
				var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);
				var marker = new google.maps.Marker({
					position: myLatlng,
					map: map
				});
			}
			google.maps.event.addDomListener(window, 'load', initialize_google_map);
		});
	</script>
	<script src="contactform/contactform.js"></script></div>
</body>
</html>