<%@ page import="com.demoweb.dto.MemberDto"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8" %>
    	 
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

    	<div id="header" 
    		 style="background-color:${ not empty param.bgcolor ? param.bgcolor : '' }">    	
            <div class="title">
                <a href="/springdemoweb3/home">DEMO WEBSITE</a>
            </div>
            <div class="links">
            
            <%-- 
            <c:if test="${ loginuser == null }">
            	<a href="/springdemoweb3/account/login">로그인</a>
                <a href="/springdemoweb3/account/register">회원가입</a>
            </c:if>
            <c:if test="${ loginuser != null }">
				${ loginuser.memberId }( ${ sessionScope.loginuser.email } )
            	<a href="/springdemoweb3/account/logout">로그아웃</a>
            </c:if> 
            --%>
            <c:choose>
           	<c:when test="${ empty loginuser }">
            	<a href="/springdemoweb3/account/login">로그인</a>
                <a href="/springdemoweb3/account/register">회원가입</a>
           	</c:when>
           	<c:otherwise>
           		${ loginuser.memberId }( ${ sessionScope.loginuser.email } )
            	<a href="/springdemoweb3/account/logout">로그아웃</a>
           	</c:otherwise>
            </c:choose>
            
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
		