<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Register</title>
	<link rel='Stylesheet' href='/springdemoweb4/resources/styles/default.css' />
	<link rel='Stylesheet' href='/springdemoweb4/resources/styles/input.css' />
</head>
<body>

	<div id='pageContainer'>
		
		<jsp:include page="/WEB-INF/views/modules/header.jsp"></jsp:include>
				
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>
		        <!-- <form id="registerform" action="/springdemoweb4/account/register" method="post"> --><!-- 절대경로표시 -->
		        <form id="registerform" action="register" method="post"><!-- 상대경로표시 -->
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" style="width:180px" />
		                    <button id="btn-dup-check" style="width:90px">중복검사</button>
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" id="passwd" name="passwd" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호 확인</th>
		                <td>
		                    <input type="password" id="confirm" name="confirm" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<input type="text" id="email" name="email" style="width:280px" />
		                </td>
		            </tr>
		                       		            
		        </table>
		        <div class="buttons">
		        	<input id="register" type="button" value="등록" style="height:25px" />
		        	<input id="cancel" type="button" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>   	
	</div>

	<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
	<script type="text/javascript">
	$(function() {
		
		let isIdValid = false;
		
		$('input#register').on('click', function(event) {
			event.preventDefault(); // 현재 이벤트를 발생시킨 요소의 기본 동작 수행 차단 ( 예: <a>인 경우 href로 이동하는 동작 수행 차단 )
			
			if (!isIdValid) {
				alert('아이디 중복검사 실패');
				return;
			}
			
			// 입력 유효성 검사
			const id = $('#memberId').val();
			if (id.length == 0) {
				alert('아이디를 입력하세요');
				$('#memberId').focus();
				return;
			}
			const id_re = /^[A-Za-z0-9]{8,20}$/; // new RegExp("^[A-Za-z0-9]$") 
			if (!id_re.test(id)) { // 영문자, 숫자 조합 8 ~ 20 -> ( 입력의 내용 검사 X --> 입력의 형식 검사 O ) --> 정규표현식
				alert('아이디 형식 오류 (8 ~ 20개의 영문자 숫자 조합)');
				$('#memberId').focus();
				return;
			}
			
			const email = $('#email').val();
			if (email.length == 0) {
				alert('이메일을 입력하세요');
				$('#email').focus();
				return;
			}
			const email_re = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
			if (!email_re.test(email)) {
				alert('이메일 형식 오류');
				$('#email').focus();
				return;
			}
			
			
			$('#registerform').submit(); // 서버로 전송
		});
		
		// 1.
		// id="btn-dup-check" 인 버튼을 찾아서 click 이벤트 연결
		// 이벤트 처리기에서 비동기 요청 
		// - url 상대경로: dup-check?memberid=입력한아이디
		// - url 절대경로: /springdemoweb4/account/dup-check?memberid=입력한아이디
		// - method : get
		// - 요청 결과는 alert으로 출력
		$('#btn-dup-check').on('click', (event) => {
			event.preventDefault();
			const memberId = $('#memberId').val();
			fetch('dup-check?memberid=' + memberId)
				.then(response => response.text())
				.then(data => {
					if (data == 'valid') {
						isIdValid = true;
						alert("사용 가능한 아이디입니다.");
					} else { 
						isIdValid = false;
						alert("이미 사용중인 아이디입니다.");
					}
					
				});
		});
		
		$('#memberId').on('keyup', (event) => {
			// console.log( $('#memberId').val() );
			isIdValid = false;
		});
		
		
		
		
	});
	</script>

</body>
</html>




























