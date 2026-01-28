<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>    

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>TODO 상세</title>

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
        <a class="navbar-brand" href="list.html">TODO 관리</a>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">TODO 상세</h2>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title mb-3">${ toDo.title }</h5>
			
            <p class="text-muted">등록일 : <fmt:formatDate value="${ toDo.writeDate }" pattern="yyyy-MM-dd" /></p>
			<hr>
			
<c:set var="enter" value="
" />
            
            <p class="mt-3">
                ${ fn:replace(toDo.content, enter, "<br>") }
            </p>         
               
			<c:choose>
				<c:when test="${ toDo.completed }">
					<span class="badge bg-success">완료</span>
				</c:when>
				<c:otherwise>
					<span class="badge bg-warning text-dark">진행중</span>
				</c:otherwise>
			</c:choose>
        </div>
    </div>

    <div class="mt-4 text-end">
        <a href="/mywebref/todo/list" class="btn btn-secondary">목록</a>
        <a href="/mywebref/todo/edit/${ toDo.idx }" class="btn btn-primary">수정</a>
        <%-- <a href="/mywebref/todo/edit?idx=${ toDo.idx }" class="btn btn-primary">수정</a> --%>
        <a href="#" id="btn-delete" class="btn btn-danger">삭제</a>
    </div>
</div>

<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
<script>
	$(function() {
		
		$('#btn-delete').on('click', (event) => {
			event.preventDefault();
			const y = confirm('${ toDo.idx }번 할 일을 삭제할까요');
			if (!y) {
				return;
			}
			
			location.href = "/mywebref/todo/delete/${toDo.idx}";
			// location.href = "/mywebref/todo/delete?idx=${toDo.idx}";
		});
		
	});
</script>

</body>
</html>
    