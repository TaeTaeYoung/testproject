<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(function(){
		
		$.ajax({
			'url':'http://localhost:8080/test/list.do',
			'dataType':'json',
			'data':{
				'a':'123',
				'b':123,
			},
			'type':'post',
			success:function(result){
				var a1 = result.data;
				console.log(a1);
				
				var htmlTag="";
				a1.map(function(value, index){
					console.log(index, value);
					htmlTag+="<tr>";
					htmlTag+="<td>"+value.num+"</td>";
					htmlTag+="<td>"+value.content+"</td>";
					htmlTag+="<td>"+value.sub+"</td>";
					htmlTag+="</tr>";
				});
				$('#wrap').html(htmlTag);
			}
		});
	});
</script>
</head>
<body>
<h1>index</h1>
	<table>
		<thead>
			<tr>
				<th>num</th>
				<th>content</th>
				<th>sub</th>
			</tr>
		</thead>
		<tbody id="wrap">
		
		</tbody>
	</table>
</body>
</html>