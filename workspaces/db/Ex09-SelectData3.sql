-- 1. 작업 데이터베이스 변경
USE kamebook;

SELECT * FROM order_h;
-- 2. 전체 주문 총액, 평균, 최고액, 최저액, 건수 조회 ( 집계함수 )
SELECT SUM(ordamt), AVG(ordamt), MAX(ordamt), MIN(ordamt), COUNT(ordamt)
FROM order_h;

-- 3. 고객별 주문 총액, 평균, 최고액, 최저액, 건수 조회 ( 집계함수 )
SELECT memid, SUM(ordamt), AVG(ordamt), MAX(ordamt), MIN(ordamt), COUNT(ordamt)
FROM order_h
GROUP BY memid;

-- 4. 주문 건수가 2건 이상인 고객의 
--    고객별 주문 총액, 평균, 최고액, 최저액, 건수 조회
SELECT memid, SUM(ordamt), AVG(ordamt), MAX(ordamt), MIN(ordamt), COUNT(ordamt)
FROM order_h
-- WHERE COUNT(ordamt) >= 2 -- 오류 : 집계함수는 group by 이후에 사용 가능
GROUP BY memid
HAVING COUNT(ordamt) >= 2; -- HAVING : group by 이후에 실행하는 조건절





