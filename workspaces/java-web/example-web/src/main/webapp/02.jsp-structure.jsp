<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date" %>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8" %>
    	 
<%!
String formatDate() {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	return sdf.format(new Date());
}
%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP Page Structure</title>
</head>
<body>

	<% int number = (int)Math.ceil(Math.random() * 100); %>
	<h2>오늘의 행운 숫자 1 : <% out.println(number); %></h2>
	<h2>오늘의 행운 숫자 2 : <%= number %></h2> <%-- = : out.println --%>
	<h2>현재 시각은 <%= new Date() %></h2>
	<h2>현재 시각은 <%= formatDate() %></h2>
	<!-- HTML 주석 : 이 주석은 서버에서는 문자열로 인식되어 처리됩니다 (브라우저에 전달 및 표시) -->
	<%-- 서버 주석 : 이 주석은 서버에서 주석으로 인식되어 무시됩니다 (브라우저에 전달 및 표시 X) --%>

</body>
</html>