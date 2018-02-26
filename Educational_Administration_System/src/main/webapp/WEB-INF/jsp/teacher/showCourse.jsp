<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>课程信息显示</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="<%=request.getContextPath() %>/js/jquery-3.2.1.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script>

	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="top.jsp"/>
	<!-- 中间主体 -->
	<div class="container" id="content">
		<div class="row">
			<jsp:include page="menu.jsp"/>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 class="col-md-5">我教授的课程</h1>
							<form class="bs-example bs-example-form col-md-5" role="form" style="margin: 20px 0 10px 0;" action="/teacher/selectCourse" id="form1" method="post">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="请输入课程名" name="findByName">
									<span class="input-group-addon btn" onclick="document.getElementById('form1').submit" id="sub">搜索</span>
								</div>
							</form>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>课程号</th>
									<th>课程名称</th>
									<th>授课老师编号</th>
									<th>上课时间</th>
									<th>上课地点</th>
									<th>周数</th>
									<th>课程类型</th>
									<th>学分</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
					        
					        	<c:forEach items="${pageInfo.list}" var="course">
								<tr>
									<td>${course.courseid}</td>
									<td>${course.coursename}</td>
									<td>${course.teacherid}</td>
									<td>${course.coursetime}</td>
									<td>${course.classroom}</td>
									<td>${course.courseweek}</td>
									<td>${course.coursetype}</td>
									<td>${course.score}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='/teacher/gradeCourse?id=${course.courseid}'">成绩</button>
									</td>
								</tr>
							</c:forEach>
					        
					        </tbody>
				    </table>
				    <div class="panel-footer">
				    
				    	<nav aria-label="Page navigation">
						  <ul class="pager">
							<li><a href="${pageInfo.action }?page=1">首页</a></li>
						    <li><a href="${pageInfo.action }?page=${pageInfo.page-1 }">上一页</a></li>
						    <li><a href="${pageInfo.action }?page=${pageInfo.page+1 }">下一页</a></li>
						    <li><a href="${pageInfo.action }?page=${pageInfo.totalPage }">尾页</a></li>
						  </ul>
						</nav>
				    
				    </div>
				</div>

			</div>
		</div>
	</div>
	<div class="container" id="footer">
		<div class="row">
			<div class="col-md-12"></div>
		</div>
	</div>
</body>
	<script type="text/javascript">
		$("#nav li:nth-child(1)").addClass("active")
		$("#nav li:nth-child(1) span").text(${pageInfo.total});
        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });
        
        if(${pageInfo.page} == ${pageInfo.totalPage}){
        	$(".pager li:nth-child(3)").addClass("disabled");
        	$(".pager li:last-child").addClass("disabled");
        };
        if(${pageInfo.page} == ${1}){
        	$(".pager li:nth-child(1)").addClass("disabled");
        	$(".pager li:nth-child(2)").addClass("disabled");
        }
	</script>
</html>