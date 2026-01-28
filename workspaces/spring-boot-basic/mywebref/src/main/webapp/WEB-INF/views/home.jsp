<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Home</title>

    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        .container {
            display: flex;
            gap: 40px;
        }

        .btn {
            border: none;
            background: none;
            cursor: pointer;
        }

        .btn img {
            width: 150px;
            height: 150px;
            object-fit: contain;
            transition: transform 0.2s ease;
        }

        .btn img:hover {
            transform: scale(1.1);
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Todo Manager Button -->
        <button class="btn" onclick="location.href='/mywebref/todo/list'">
            <img src="/mywebref/resources/images/todo.png" alt="Todo Manager">
        </button>

        <!-- Photo Gallery Button -->
        <button class="btn" onclick="location.href='gallery.html'">
            <img src="/mywebref/resources/images/gallery.png" alt="Photo Gallery">
        </button>
    </div>

</body>
</html>
    