<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$.ajax({
			'url':'http://localhost:8080/test/list.do',
			'dataType':'json',
			'data':{
				'a':'123'
			},
			'type':'get',
			success:function(result){
				console.log(result);
			}
		});
	});
</script>
	
</head>
<body>
<h1>리스트 페이지</h1>
	<table>
		<thead>
			<tr>
				<th>num</th>
				<th>id</th>
				<th>sub</th>
			</tr>
		</thead>
		<tbody id="list">
			<c:forEach items="${list }" var="bean">
			<tr>
				<c:url value="detail.do" var="detail">
					<c:param name="num" value="${bean.num }"></c:param>
				</c:url>
				
				<td><a href="${detail }">${bean.num }</a></td>
				<td><a href="${detail }">${bean.id }</a></td>
				<td><a href="${detail }">${bean.sub }</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>