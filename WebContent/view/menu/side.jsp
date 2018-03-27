<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% 
	String id = (String)session.getAttribute("id");
%>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function changeView(value) {
	
	var id = "<%=(String)session.getAttribute("id")%>";
		if(id == "null"){
			alert("�α��� �� �̿�ٶ��ϴ�");
		}else{
			if(value == "1") {// �Է�������
				location.href="writeForm";
			}else if(value == "2") {// ����������
			    location.href="writeList";
			}else if(value == "3") {// ���������
			    location.href="chart";
			}else if(value == "4") {// ����������
			    location.href="calendar";
			}else if(value == "8") {// ȸ������������
			    location.href="main.jsp?contentPage=/admin/list.jsp";
			}
		}
	}
</script>
</head>
<body>

<div class="container">
<div class="row">
					<div class="col-lg-12">
						<div class="row">
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<!-- <h4>�Ϸ�,����</h4> -->
										<div class="icon">
											<i class="fa fa-edit fa-3x"></i>
											<!-- <i class="fa fa-desktop fa-3x"></i> -->
										</div>
										<p>
											����� ����
										</p>

									</div>
									<div class="box-bottom" onclick="changeView('1');">
										<a href="#" >�Ϸ�,����</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<!-- <h4>�Ϸ�,����</h4> -->
										<div class="icon">
											<i class="fa fa-desktop fa-3x"></i>
											
										</div>
										<p>
											����, �Ϻ� ����
										</p>

									</div>
									<div class="box-bottom" onclick="changeView('2');">
										<a href="#">�Ϸ�,����</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<!-- <h4>�Ϸ�,���</h4> -->
										<div class="icon">
											<i class="fa fa-pagelines fa-3x"></i>
										</div>
										<p>������, ���� ���</p>

									</div>
									<div class="box-bottom" onclick="changeView('3');">
										<a href="#">�Ϸ�,���</a>
									</div>
								</div>
							</div>
							<div class="col-lg-3">
								<div class="box">
									<div class="box-gray aligncenter">
										<!-- <h4>�Ϸ�,�޷�</h4> -->
										<div class="icon">
											<i class="fa fa-code fa-3x"></i>
										</div>
										<p>���ΰ��� ����</p>

									</div>
									<div class="box-bottom" onclick="changeView('4');">
										<a href="#">�Ϸ�,�޷�</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</div>

</body>
</html>