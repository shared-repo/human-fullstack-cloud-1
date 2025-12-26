-- 1. 작업 데이터베이스 변경
USE kamebook;

-- 2. insert
DESC members;

insert into members (memid, memname, passwd, passwdmdt)
values ('jdk', '장동건', 'Pa$$w0rd', now()); -- now() == current_timestamp()

-- ---------------------------
-- 다중 행 insert
insert into members (memid, memname, passwd, passwdmdt)
values 	('jdk', '장동건', 'Pa$$w0rd', now()),
		('ky', '공유', 'Pa$$w0rd', now()),
        ('pjm', '박정민', 'Pa$$w0rd', now()),
        ('psj', '박서준', 'Pa$$w0rd', now());


select * from members;

-- 3. UPDATE
--    [ 형식 ]
--    update 테이블이름
--    set 컬럼1 = 값1, ...
--    [ where 조건식 ]
update members
set passwd = 'newpasswd', passwdmdt = now()
where memid = 'jdk';

-- 4. DELETE
--    [ 형식 ]
--    delete from 테이블이름
--    [ where 조건식 ]
delete from members
where enterdtm > '2025-12-25';

select * from members;



