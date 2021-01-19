<%@ page language="java" contentType="text/html; charset=utf-8" 
	pageEncoding="UTF-8" %>
<html>
<body>
<h2>Hello World!</h2>

<form action="/todo/add"><button>새로운 TODO 등록</button></form>

<% String content = (String) request.getAttribute("getJson"); %>
<%= content %>

</body>

<script>
	var test = <%=request.getAttribute("json")%>
	console.log(test)

</script>
</html>
