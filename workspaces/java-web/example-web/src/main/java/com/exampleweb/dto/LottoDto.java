package com.exampleweb.dto;

// -- 로또 당첨번호 테이블 만들기
/*
CREATE TABLE lotto
(
	rnd int not null primary key,
    gameDate date not null,
    number1 smallint not null,
    number2 smallint not null,
    number3 smallint not null,
    number4 smallint not null,
    number5 smallint not null,
    number6 smallint not null,
    bonus smallint not null
);
*/

// 데이터베이스의 Lotto 테이블 데이터를 저장하는 DTO 클래스
import java.text.SimpleDateFormat;
import java.util.Date;

public class LottoDto { 
	
	private int rnd;
	private Date gameDate;
	private int number1;
	private int number2;
	private int number3;
	private int number4;
	private int number5;
	private int number6;
	private int bonus;
	
	public int getRnd() {
		return rnd;
	}
	public void setRnd(int rnd) {
		this.rnd = rnd;
	}
	public Date getGameDate() {
		return gameDate;
	}
	public void setGameDate(Date gameDate) {
		this.gameDate = gameDate;
	}
	public int getNumber1() {
		return number1;
	}
	public void setNumber1(int number1) {
		this.number1 = number1;
	}
	public int getNumber2() {
		return number2;
	}
	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	public int getNumber3() {
		return number3;
	}
	public void setNumber3(int number3) {
		this.number3 = number3;
	}
	public int getNumber4() {
		return number4;
	}
	public void setNumber4(int number4) {
		this.number4 = number4;
	}
	public int getNumber5() {
		return number5;
	}
	public void setNumber5(int number5) {
		this.number5 = number5;
	}
	public int getNumber6() {
		return number6;
	}
	public void setNumber6(int number6) {
		this.number6 = number6;
	}
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
	
	// SimpleDateFormat : 날짜 -> 특정형식문자열, 특정형식문자열 -> 날짜 변환 도구
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
	
	@Override
	public String toString() {
		return String.format("[%d][%s][%d][%d][%d][%d][%d][%d]-[%d]",
				rnd, sdf.format(gameDate), number1, number2, number3, number4, number5, number6, bonus);
	}
	
	
	

}
