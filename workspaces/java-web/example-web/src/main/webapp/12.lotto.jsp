<%@ page import="com.exampleweb.dto.LottoDto" %>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회차별 당첨 번호</title>
</head>
<body>
	<% LottoDto lotto = (LottoDto)request.getAttribute("lotto"); %>
	
	<table border="1" align="center">
		<tr>
			<th colspan="7">
				<%= lotto.getRnd() %>회차 당첨번호 <br>(추첨일 : <%= lotto.getGameDate() %>)
			</th>
		</tr>
		<tr>
			<th style="width:70px;height:70px"><%= lotto.getNumber1() %></th>
			<th style="width:70px;height:70px"><%= lotto.getNumber2() %></th>
			<th style="width:70px;height:70px"><%= lotto.getNumber3() %></th>
			<th style="width:70px;height:70px"><%= lotto.getNumber4() %></th>
			<th style="width:70px;height:70px"><%= lotto.getNumber5() %></th>
			<th style="width:70px;height:70px"><%= lotto.getNumber6() %></th>
			<th style="width:70px;height:70px"><%= lotto.getBonus() %></th>
		</tr>
	</table>


</body>
</html>







