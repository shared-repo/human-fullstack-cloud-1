<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>TODO 수정</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 800px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="todo-list.html">TODO 관리</a>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">TODO 수정</h2>

    <form action="/mywebref/todo/edit" method="post">
    	<input type="hidden" name="idx" value="${ toDo.idx }">
        <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" name="title" class="form-control" value="${ toDo.title }">
        </div>

        <div class="mb-3">
            <label class="form-label">내용</label>
            <textarea name="content" class="form-control" rows="5">${ toDo.content }</textarea>
        </div>

        <div class="mb-4">
            <label class="form-label">상태</label>
            <select name="completed" class="form-select">
                <option ${ not toDo.completed ? "selected" : "" } value="0">진행중</option>
                <option ${ toDo.completed ? "selected" : "" } value="1">완료</option>
            </select>
        </div>

        <div class="text-end">
            <a href="/mywebref/todo/detail/${ toDo.idx }" class="btn btn-secondary">취소</a>
            <%-- <a href="/mywebref/todo/detail?idx=${ toDo.idx }" class="btn btn-secondary">취소</a> --%>
            <button type="submit" class="btn btn-primary">수정</button>
        </div>
    </form>
</div>

</body>
</html>
    