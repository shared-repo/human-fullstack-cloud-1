-- 1. 데이터베이스 만들기 ( DDL )

CREATE DATABASE kamebook; -- 무조건 생성
CREATE DATABASE IF NOT EXISTS kamebook; -- 없으면 생성

-- 2. 데이터베이스 제거 ( DDL )

DROP DATABASE kamebook; -- 무조건 제거
DROP DATABASE IF EXISTS kamebook; -- 있으면 제거

-- 3. 새로 만든 데이터베이스에 대한 사용 권한 부여
--    ( CREATE DATABASE 구문 실행 후에 테스트 )

CREATE DATABASE IF NOT EXISTS kamebook;

GRANT ALL PRIVILEGES ON kamebook.* TO human@localhost;
GRANT ALL PRIVILEGES ON kamebook.* TO human@`%`;


-- labdb  데이터베이스 만들고 권한 부여 ( DDL )

CREATE DATABASE IF NOT EXISTS labdb;

GRANT ALL PRIVILEGES ON labdb.* TO human@localhost;
GRANT ALL PRIVILEGES ON labdb.* TO human@`%`;
