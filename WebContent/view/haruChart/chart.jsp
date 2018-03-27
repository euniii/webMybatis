<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- <%@ include file="array.jsp" %> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
   <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
   <script src="/webMybatis/js/Chart.min.js"></script>
   
<link href="/webMybatis/css/bootstrap.min.css" rel="stylesheet" />
<link rel='stylesheet prefetch' href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css' />
<link href="/webMybatis/css/fancybox/jquery.fancybox.css" rel="stylesheet">
<link href="/webMybatis/css/jcarousel.css" rel="stylesheet" />
<link href="/webMybatis/css/flexslider.css" rel="stylesheet" />
<link href="/webMybatis/css/style.css" rel="stylesheet" />
<link href="/webMybatis/css/default.css" rel="stylesheet" />   

<!-- ��¥���� ��ũ��Ʈ -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script><!-- ��¥�� --> 
<script type="text/javascript">
//��¥���� �ڹٽ�ũ��Ʈ
$(function() {
	  $( "#date1" ).datepicker({
	    dateFormat: 'yymmdd'
	  });
	});

$(function() {
	  $( "#date2" ).datepicker({
	    dateFormat: 'yymmdd'
	  });
	});

</script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/view/menu/top2.jsp"></jsp:include>
<jsp:include page="/view/menu/side.jsp"></jsp:include>

<!-- �˻��׸� -->
<div class="container">
<div id="wrapper">
<form method="post" action="chartView">
<!-- <div style="margin-left: 300px; background-color: gray;">  -->
<div align="center" style="width:1130px; height:60px; padding-top:15px; background-color: #F2F2F2;"> 
<label class="control-label required" for="password">��¥ :</label>
 <input type="text" id="date1" name="date1" style="width: 100px;"> 
       ~ <input type="text" id="date2" name="date2" style="width: 100px;">&nbsp;&nbsp;&nbsp;
<label class="control-label required" for="password">�׸�</label>
   <select class="form-control4" name="main_option" id="main_option" onchange="doChange(this, 'main_account')" style="height: 30px;">
       <option value="default">---�׸���---</option>
       <option value="1">����</option>
       <option value="2">����</option>
   </select>
<input type="submit" value="��ȸ"> 



</div>
<br><br>
<div style="width: 60%; margin-left: 150px;">
<canvas id="canvas" height="500" width="800"></canvas>
</div>

<script type="text/javascript">
   /* var randomScalingFactor = function(){ return Math.round(Math.random()*100)}; */
   var months = ["�ĺ�","�����","��ź�","�ǰ��Ƿ�","��Ȱ��ǰ��","��ȭ��Ȱ��","�̿��Ƿ�"];
   var barChart = null;
   var barChartData = {
      labels : ["�ĺ�","�����","��ź�","�ǰ��Ƿ�","��Ȱ��ǰ��","��ȭ��Ȱ��","�̿��Ƿ�"],
      datasets : [
         {
            fillColor : "rgba(151,187,205,0.5)",
            strokeColor : "rgba(151,187,205,0.8)",
            highlightFill : "rgba(151,187,205,0.75)",
            highlightStroke : "rgba(151,187,205,1)",
            data : ['${foodcnt}','${carcnt}','${phoneCnt}','${healthcnt}','${articlecnt}','${culturalCnt}','${beautycnt}']
         }
      ]

   };

   $(function() {
      var ctx = document.getElementById("canvas").getContext("2d");
      barChart = new Chart(ctx).Bar(barChartData, {
         //Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
         scaleBeginAtZero : false,
         //Boolean - Whether grid lines are shown across the chart
         scaleShowGridLines : true,
         //String - Colour of the grid lines
         scaleGridLineColor : "rgba(0,0,0,0.05)",
         //Number - Width of the grid lines
         scaleGridLineWidth : 1,
         //Boolean - If there is a stroke on each bar
         barShowStroke : false,
         //Number - Pixel width of the bar stroke
         barStrokeWidth : 2,
         //Number - Spacing between each of the X value sets
         barValueSpacing : 20,
         //Number - Spacing between data sets within X values
         barDatasetSpacing : 1,
        /*  onAnimationProgress: function() {
            console.log("onAnimationProgress");
         },
         onAnimationComplete: function() {
            console.log("onAnimationComplete");
         } */
      });
   });

   $("input#btnAdd").on("click", function() {
      barChart.addData(
         [randomScalingFactor(),randomScalingFactor()], 
         months[(barChart.datasets[0].bars.length)%12]
      );
   });

   $("canvas").on("click", function(e) {
      var activeBars = barChart.getBarsAtEvent(e);
      console.log(activeBars);

      for(var i in activeBars) {
         console.log(activeBars[i].value);
      }
   });

</script>
</form>
</div></div> <br><br>  
<jsp:include page="/view/menu/footer.jsp"></jsp:include>   
</body>
</html>