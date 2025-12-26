-- 1. 작업 데이터베이스 변경
use kamebook;

-- 2. create : 테이블 생성

create table person
(
	-- 컬럼이름 자료형 option1, option2, option3, ....
    pno int not null,
    pname varchar(50) not null,
    phone varchar(15) null,
    email varchar(50) null,
    address varchar(100) null,
    age int not null check ( age > 14 ),
    gender varchar(10) not null check( gender in ('male', 'female') ),
    regidate datetime default (now())
);

-- 3. alter : 테이블 수정

alter table person
add column company varchar (50) null;

desc person;

alter table person
change column company company varchar(100) null;

desc person;

alter table person
modify column company varchar(150) null;

desc person;

alter table person
drop column company;

desc person;

-- 4. drop : 테이블 제거
drop table person;

-- /////////////////////////////////////////////////////////////////

-- 5. create with pk (primary key) and fk (foreign key - 관계를 맺는 컬럼)

CREATE TABLE `order_h2` (
	`orderno` CHAR(9) PRIMARY KEY, -- PRIMARY KEY : 자동으로 NOT NULL
	`orddt` DATE NOT NULL,
	`memid` VARCHAR(9) NOT NULL REFERENCES members(memid), -- foreign key (mysql에서는 실제 효과 없음)
	`ordamt` DECIMAL(7,0) NOT NULL DEFAULT '0',
	`cancelyn` ENUM('Y','N') NOT NULL DEFAULT 'N',
	`canceldtm` DATETIME NULL,
	`insdtm` DATETIME NOT NULL DEFAULT current_timestamp(),
	`moddtm` DATETIME NULL
);
desc order_h2;

-- **********

CREATE TABLE `order_h3` (
	`orderno` CHAR(9) NOT NULL,
	`orddt` DATE NOT NULL,
	`memid` VARCHAR(9) NOT NULL,
	`ordamt` DECIMAL(7,0) NOT NULL DEFAULT '0',
	`cancelyn` ENUM('Y','N') NOT NULL DEFAULT 'N',
	`canceldtm` DATETIME NULL,
	`insdtm` DATETIME NOT NULL DEFAULT current_timestamp(),
	`moddtm` DATETIME NULL,
    CONSTRAINT `PK_ORDER_H3` PRIMARY KEY (`orderno`),
    CONSTRAINT `FK_MEMBER_ORDER_H3` FOREIGN KEY (`memid`) REFERENCES `members`(`memid`)
);
desc order_h3;

-- **********

CREATE TABLE `order_h4` (
	`orderno` CHAR(9) NOT NULL,
	`orddt` DATE NOT NULL,
	`memid` VARCHAR(9) NOT NULL,
	`ordamt` DECIMAL(7,0) NOT NULL DEFAULT '0',
	`cancelyn` ENUM('Y','N') NOT NULL DEFAULT 'N',
	`canceldtm` DATETIME NULL,
	`insdtm` DATETIME NOT NULL DEFAULT current_timestamp(),
	`moddtm` DATETIME NULL
);
ALTER TABLE order_h4
ADD CONSTRAINT PK_ORDER_H4 PRIMARY KEY (orderno);
ALTER TABLE order_h4
ADD CONSTRAINT FK_MEMBERS_ORDER_H4 FOREIGN KEY (memid) REFERENCES members(memid);
desc order_h4;

-- **********

CREATE TABLE `order_h5` (
	`orderno` CHAR(9) NOT NULL,
	`orddt` DATE NOT NULL,
	`memid` VARCHAR(9) NOT NULL,
	`ordamt` DECIMAL(7,0) NOT NULL DEFAULT '0',
	`cancelyn` ENUM('Y','N') NOT NULL DEFAULT 'N',
	`canceldtm` DATETIME NULL,
	`insdtm` DATETIME NOT NULL DEFAULT current_timestamp(),
	`moddtm` DATETIME NULL,
    -- CONSTRAINT `PK_ORDER_H3` PRIMARY KEY (`orderno`),
    -- CONSTRAINT `FK_MEMBER_ORDER_H3` FOREIGN KEY (`memid`) REFERENCES `members`(`memid`)
    PRIMARY KEY (`orderno`),
    FOREIGN KEY (`memid`) REFERENCES `members`(`memid`)
);
desc order_h5;

-- *********

CREATE TABLE `order_d2` (
	`orderno` CHAR(9) NOT NULL,
	`goodscd` CHAR(5) NOT NULL,
	`unitcd` CHAR(2) NULL,
	`unitprice` DECIMAL(5,0) NOT NULL DEFAULT '0',
	`qty` DECIMAL(3,0) NOT NULL DEFAULT '0',
	`amt` DECIMAL(7,0) NOT NULL DEFAULT '0',
	`insdtm` DATETIME NOT NULL DEFAULT current_timestamp(),
	`moddtm` DATETIME NULL,
	CONSTRAINT PK_ORDER_D2 PRIMARY KEY (`orderno`, `goodscd`),
    CONSTRAINT FK_ORDER_H5_ORDER_D2 FOREIGN KEY (orderno) REFERENCES order_h5(orderno),
    CONSTRAINT FK_GOODSINFO_ORDER_D2 FOREIGN KEY (goodscd) REFERENCES goodsinfo(goodscd)
);
desc order_d2;











