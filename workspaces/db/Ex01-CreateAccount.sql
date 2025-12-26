-- SQL의 주석

-- 1. 사용자 계정 만들기 ( DCL - Data Control Language )
--    계정형식 : 사용자명@접속하는컴퓨터IP
CREATE USER human@localhost IDENTIFIED BY "human";
-- CREATE USER human@"192.168.0.53" IDENTIFIED BY "human";
CREATE USER human@"%" IDENTIFIED BY "human";

-- 2. 사용자에게 권한 부여
--    human 계정에 sakila, world 데이터베이스에 대한 모든 사용 권한 부여

GRANT ALL PRIVILEGES ON sakila.* TO human@localhost;
GRANT ALL PRIVILEGES ON sakila.* TO human@"%";
GRANT ALL PRIVILEGES ON world.* TO human@localhost;
GRANT ALL PRIVILEGES ON world.* TO human@"%";