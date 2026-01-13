<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax</title>
</head>
<body>

	<a href="javascript:" id="sync-link">동기 방식 호출 (All Refresh)</a>
	&nbsp;&nbsp;
	<a href="javascript:" id="async-link">비동기 방식 호출 (Partial Refresh)</a>
	<hr>
	<div id="container">	
	</div>

	<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
	<script>
	$(function() { // 브라우저가 html을 읽고 객체 트리를 완성한 후 자동으로 호출
		$('#sync-link').on('click', (event) => {
			event.preventDefault(); // 이 이벤트를 발생시킨 객체의 기본 기능 비활성화
			location.href = 'request-data'; // 주소입력 or 링크클릭과 같은 효과
		});
		$('#async-link').on('click', (event) => {
			event.preventDefault(); // 이 이벤트를 발생시킨 객체의 기본 기능 비활성화
			
			// 1. jQuery 기능 사용
			/*
 			$.ajax({
				url: 'request-data',
				method: 'get',
				success: function(data, status, xhr) {
					const container = $('#container');
					$('<div></div>')
						.css({ "border": "solid 1px", 
							   "padding": "10px", 
							   "margin-top": "2px" 
						 })
						.text(data)
						.appendTo( container );
				},
				fail: function(xhr, status, err) {
					
				}
			}); 
			*/
			
			// 2. fetch 함수 사용
			fetch('request-data')
				.then(response => response.text())
				.then(data => {
					const container = $('#container');
					$('<div></div>')
						.css({ "border": "solid 1px", 
							   "padding": "10px", 
							   "margin-top": "2px" 
						 })
						.text(data)
						.appendTo( container );
				});
			
		});
	});
	</script>

</body>
</html>







