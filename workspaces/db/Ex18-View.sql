-- 작업 데이터베이스 변경
use kamebook;

-- view 
create view simple_members
as
	select memid, memname, addr, mileage, stat
    from members;
    
select * from simple_members;

create view order_stats_by_member
as
	SELECT m.memid, m.memname, SUM(od.unitprice*od.qty) 주문총액, AVG(od.unitprice*od.qty) 평균주문액
	FROM members m
	INNER JOIN order_h oh 
	ON m.memid = oh.memid
	INNER JOIN order_d od
	ON oh.orderno = od.orderno
	GROUP BY m.memid, m.memname
	ORDER BY m.memid;
    
select * from order_stats_by_member;