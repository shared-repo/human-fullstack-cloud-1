<%@page import="com.demoweb.dto.MemberDto"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8" %>
    	 
    	<% String bgColor = request.getParameter("bgcolor"); %>
    	<% bgColor = (bgColor != null && bgColor.length() > 0) ? bgColor : ""; %>
    	
		<div id="header" style="background-color:<%= bgColor %>">    	
            <div class="title">
                <a href="/springdemoweb3/home">DEMO WEBSITE</a>
            </div>
            <div class="links">
            <% MemberDto loginUser = (MemberDto)session.getAttribute("loginuser"); %>
            <% if (loginUser == null) { %>
            	<a href="/springdemoweb3/account/login">로그인</a>
                <a href="/springdemoweb3/account/register">회원가입</a>
            <% } else {%>
            	<%= loginUser.getMemberId() %>
            	(<%= loginUser.getEmail() %>)
            	<a href="/springdemoweb3/account/logout">로그아웃</a>
            <% } %>
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="/springdemoweb3/admin/user">사용자관리</a></li>
					<li><a href="/springdemoweb3/mail/list">메일보내기</a></li>
					<li><a href="/springdemoweb3/library/list">자료실</a></li>
					<li><a href="/springdemoweb3/board/list">게시판</a></li>
                </ul>
            </div>
		</div>
		
		<div style="border:solid 1px;text-align:right;padding:5px">
            [TOTAL : <%= application.getAttribute("total") %>]
            [CURRENT: <%= application.getAttribute("current") %>]
		</div>
		