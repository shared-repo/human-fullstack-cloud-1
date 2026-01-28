<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>TODO 목록</title>

    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Korean Font -->
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700&display=swap" rel="stylesheet">

    <style>
        body {
            font-family: 'Noto Sans KR', sans-serif;
            background-color: #f8f9fa;
        }
        .container {
            max-width: 1000px;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/mywebref/todo/list">TODO 관리</a>
        <div class="navbar-nav">
            <a class="nav-link" href="/mywebref/todo/write">TODO 등록</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">TODO 목록</h2>

    <table class="table table-bordered table-hover bg-white">
        <thead class="table-light">
            <tr>
                <th style="width: 10%">번호</th>
                <th>제목</th>
                <th style="width: 20%">등록일</th>
                <th style="width: 15%">상태</th>
            </tr>
        </thead>
        <tbody>
        	<c:forEach var="toDo" items="${ todos }">
            <tr>
                <td>${ toDo.idx }</td>
                <td>
                    <a href="/mywebref/todo/detail?idx=${ toDo.idx }" class="text-decoration-none">
                        ${ toDo.title }
                    </a>
                    <br>
                    <a href="/mywebref/todo/detail/${ toDo.idx }" class="text-decoration-none">
                        ${ toDo.title }
                    </a>
                </td>
                <td>
                <fmt:formatDate value="${toDo.writeDate }" pattern="yyyy-MM-dd" />
                </td>
                <td>
                	<c:choose>
                	<c:when test="${ not toDo.completed }">
                    	<span class="badge bg-warning text-dark">진행중</span>
                    </c:when>
                    <c:otherwise>
                    	<span class="badge bg-success">완료</span>
                    </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            </c:forEach>
            
        </tbody>
    </table>
</div>

</body>
</html>
