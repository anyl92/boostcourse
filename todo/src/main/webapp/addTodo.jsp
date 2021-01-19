<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할일 등록</title>
</head>
<body>
<div>할일 등록</div>
<form action="" method="post" name="addForm">
<div>어떤 일인가요?</div>
<input name="todo">
<div>누가 할일인가요?</div>
<input name="person">
<div>우선순위를 선택하세요</div>

	<a href="#"><button>이전</button></a>
	<input type="button" value="제출" onclick="addTodo();" />
	<input type="button" value="내용지우기" onclick="delTodo();" />
</form>
<script>
	function addTodo() {
		const formdata = document.addForm;
		todo = formdata.todo.value;
		person = formdata.person.value;
		console.log(formdata, '?_?')
		formdata.submit();
	}
</script>
</body>
</html>