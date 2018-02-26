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
					    	<h1 class="col-md-5">已选该课程学生名单</h1>
						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
									<th>学号</th>
									<th>姓名</th>
									<th>分数</th>
									<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
								<c:forEach items="${pageInfo.list}" var="gradeInfo">
								<tr>
									<td>${gradeInfo.userid}</td>
									<td>${gradeInfo.username}</td>
									<td>${gradeInfo.mark}</td>
									<td>
										<c:if test="${gradeInfo.mark == null }">
											<button class="btn btn-default btn-xs btn-info" onClick="location.href='/teacher/mark?studentid=${gradeInfo.userid}&courseid=${gradeInfo.courseid}'">打分</button>
										</c:if>
										<c:if test="${gradeInfo.mark != null }">
											已打分
										</c:if>
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

        function confirmd() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }
		
		 if(${pageInfo.page} == ${pageInfo.totalPage}){
	        	$(".pager li:nth-child(3)").addClass("disabled");
	        	$(".pager li:last-child").addClass("disabled");
	        };
	        if(${pageInfo.page} == ${1}){
	        	$(".pager li:nth-child(1)").addClass("disabled");
	        	$(".pager li:nth-child(2)").addClass("disabled");
	        }

        $("#sub").click(function () {
            $("#form1").submit();
        });
	</script>
</html>