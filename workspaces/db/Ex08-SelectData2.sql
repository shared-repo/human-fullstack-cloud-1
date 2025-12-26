-- 1. 작업 데이터베이스 변경
USE kamebook;

SELECT * FROM members;
-- 2. mileage가 0이 아닌 회원 조회
SELECT * 
FROM members
-- WHERE mileage != 0;
WHERE mileage <> 0;

-- 3. mileage가 0인 회원 조회
SELECT * 
FROM members
WHERE mileage = 0; -- SQL에서 동일성 비교 연산자로 = 사용 ( 자바는 == )

-- 4. 2000년 이후에 출생한 회원 조회
SELECT * 
FROM members
WHERE birthday >= '2000-01-01'; -- 날짜 데이터는 '사용해서 표현

-- 5. 2002년에 출생한 회원 조회
SELECT * 
FROM members
-- WHERE birthday >= '2002-01-01' && birthday <= '2002-12-31';
-- WHERE birthday >= '2002-01-01' AND birthday <= '2002-12-31';
WHERE birthday BETWEEN '2002-01-01' AND '2002-12-31';

-- 6. jobcd가 1, 4, 9인 회원 조회
SELECT * 
FROM members
-- WHERE jobcd = 1 OR jobcd = 4 OR jobcd = 9;
WHERE jobcd IN (1, 4, 9);

-- 7. jobcd가 1, 4, 9가 아닌 회원 조회
SELECT * 
FROM members
-- WHERE jobcd != 1 AND jobcd != 4 AND jobcd != 9;
-- WHERE NOT (jobcd = 1 OR jobcd = 4 OR jobcd = 9); -- NOT : 자바의 !연산자
WHERE jobcd NOT IN (1, 4, 9);

-- 8. 인천에 거주하는 회원 조회
SELECT *
FROM members
WHERE addr LIKE '%인천%'; -- addr 컬럼의 값에 '인천'을 포함하는 경우

-- 9. 이름에 '갑'이 포함된 회원 조회
SELECT *
FROM members
WHERE memname LIKE '%갑%'; -- % : 0개 이상의 문자

-- 10. 이름의 두 번째 글자가 '갑'인 회원 조회
SELECT *
FROM members
WHERE memname LIKE '_갑%'; -- _ : 1개의 문자

-- 11. 주민번호가 없는 회원 조회
select * 
from members
-- where jumin = NULL; -- NULL을 비교연산자로 직접 비교할 수 없습니다.
where jumin IS NULL; -- NULL을 비교할 경우에는 IS NULL 사용





