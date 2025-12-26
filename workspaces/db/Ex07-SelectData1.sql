-- 1. 작업 데이터베이스 변경
USE kamebook;

-- SELECT 문의 형식
/*
	SELECT 컬럼1, 컬럼2, 컬럼3, ...
    [FROM 테이블이름]
    [WHERE 조건식]
    [GROUP BY 컬럼1, 컬럼2, ...]
    [HAVING 조건식]
    [ORDER BY 컬럼1, 컬럼2, ...]
*/

-- 2. members 테이블의 모든 컬럼의 데이터 조회
SELECT 
	memid, memname, passwd, passwdmdt, 
    jumin, addr, birthday, jobcd, mileage, 
    stat, enterdtm, leavedtm
FROM members;

SELECT * FROM members; -- 모든 컬럼은 *로 대체할 수 있습니다.

-- 3. 회원아이디, 회원이름, 주소, 생일 컬럼 조회 ( 특정 컬럼 조회 )
SELECT memid, memname, addr, birthday
FROM members;

-- 4. 회원아이디, 회원이름, 주소, 생년월일 컬럼을 컬럼명을 변경해서 조회
SELECT 
	memid as 회원아이디, 
    memname 회원이름, 
    addr 주소, 
    birthday 생년월일
FROM members;

-- 5. 회원아이디, 회원이름, 주소, 생년월일 컬럼을 생년월일 순으로 정렬해서 조회
SELECT memid, memname, addr, birthday
FROM members
-- ORDER BY birthday; -- ASC 생략 : 오름차순 정렬
ORDER BY birthday DESC; -- DESC : 내림차순 정렬

-- 6. 회원아이디, 회원이름, 주소, 생년월일 컬럼을 이름순으로 정렬해서 조회
--    (같은 이름은 생년월일로 정렬)
SELECT memid, memname, addr, birthday
FROM members
-- ORDER BY memname, birthday;
ORDER BY memname ASC, birthday DESC;

-- 7. 회원들의 직업코드 조회
-- SELECT jobcd
SELECT DISTINCT jobcd -- DISTINCT : 중복 제거
FROM members
ORDER BY jobcd;

-- 8-1. 생년월일이 빠른 직원 3명 조회
SELECT * 
FROM members
ORDER BY birthday
LIMIT 3; -- 앞에서 3개만 조회

-- 8-2. 생년월일이 2번째로 빠른 직원부터 3명 조회
SELECT * 
FROM members
ORDER BY birthday
LIMIT 1,3; -- 1번째 부터 3개만 조회 (순서는 0부터 시작)

-- 9. "[memid] memname" 형식의 결과 및 mileage를 1000 증가한 결과 조회
SELECT 
	concat("[" , memid, "] ", memname) 아이디와이름, 
    mileage + 1000 보너스합산마일리지
FROM members; 
	
    
    
    