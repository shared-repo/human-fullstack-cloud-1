use labdb;

-- where절에 subquery 사용해서 아래 질의를 완성하세요

-- 1. '박지성' 고객의 구매 총액
SELECT '박지성', SUM(saleprice) `구매 총액`
FROM orders o
WHERE custid = (SELECT custid
				FROM customer 
                WHERE name = '박지성');

-- 2. 가장 비싼 도서의 이름을 조회 ( book 테이블 사용 )
select *
from book
where price = (	select MAX(price) 
				from book);

-- 3. 구매실적이 있는 고객 조회

select *
from customer
where custid in (select distinct custid 
				 from orders);
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
-- 4. 대한미디어 출판사에서 출간한 도서를 구매한 고객 조회 ( book, orders, customer )