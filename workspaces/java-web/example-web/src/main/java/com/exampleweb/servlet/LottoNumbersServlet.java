package com.exampleweb.servlet;

import java.io.IOException;

import com.exampleweb.dao.LottoDao;
import com.exampleweb.dto.LottoDto;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/lotto-numbers-by-round" })
public class LottoNumbersServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청 데이터 읽기
		String sRound = req.getParameter("round");
		int round = Integer.parseInt(sRound);
		
		// 2. 요청 처리
		LottoDao dao = new LottoDao();
		LottoDto lotto = dao.selectLottoByRnd(round);		
		
		// 3-1. JSP에 전달할 데이터를 request 객체에 저장
		req.setAttribute("lotto", lotto);		
		
		// 3-2. JSP로 forward
		req.getRequestDispatcher("12.lotto.jsp").forward(req, resp);
		
		// or
		// 4. 다른 서블릿으로 redirect
		
	}

}










