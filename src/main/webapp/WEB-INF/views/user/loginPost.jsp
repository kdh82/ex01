<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		//http://location:8080/ex01/user/loginPost  {pageContext.request.contextPath}
		location.href="${pageContext.request.contextPath}/sboard/listPage";
	</script>
</body>
</html>