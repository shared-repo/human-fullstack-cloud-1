package com.exampleweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

// @WebServlet(urlPatterns = { "/calculate" })
@WebServlet("/calculate")
public class CalculatorServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 데이터 읽기
		int operand1 = Integer.parseInt(request.getParameter("operand1"));
		int operand2 = Integer.parseInt(request.getParameter("operand2"));
		String op = request.getParameter("op");
		
		// 2. 데이터 처리
		double result = 0; // 초기화 : 변수를 만들면서 특정 값을 저장하는 것
		boolean valid = true;
		switch (op) { 
		case "+":
			result = operand1 + operand2;
			break;
		case "-":
			result = operand1 - operand2;
			break;
		case "*":
			result = operand1 * operand2;
			break;
		case "/":
			result = (double) operand1 / operand2;
			break;
		case "%":
			result = operand1 % operand2; // % : 나눗셈의 나머지를 반환하는 연산
			break;
		default:			
			valid = false;
		}
		// 3. JSP에서 읽을 수 있도록 데이터 저장
		request.setAttribute("operation", 
				String.format("%d %s %d", operand1, op, operand2));
		if (valid) {
			request.setAttribute("result", result);
		} else {
			request.setAttribute("result", "유효하지 않은 연산자");
		}
		// 4. JSP로 forward
		request	.getRequestDispatcher("06.calc-result2.jsp")
				.forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.
		// 2.
		// 3.
		// 4. 
		req	.getRequestDispatcher("06.calculator.jsp")
			.forward(req, resp);
	}

}
