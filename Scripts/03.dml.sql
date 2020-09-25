insert into worker values('admin', 'admin', '홍관리', '010-777-7777');
insert into worker values('pinksung', 'pinksung', '명강사', '010-999-9696');

SELECT NO, NAME, KIND, PRICE, SALE_PRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT;

SELECT NO, NAME, KIND, PRICE, SALE_PRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT WHERE KIND = 2;

insert into product(name, kind, price, sale_price, margin, content, image) values('크로그다일부츠', '2', '40000', '50000', '10000', '오지니랄 크로그다일부츠 입니다.', 'w2.jpg');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('롱부츠', '2', 40000, 50000, 10000,'따뜻한 롱부츠 입니다.', 'w-28.jpg', 'n');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w-26.jpg', 'n');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('슬리퍼', '4', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w-25.jpg', 'y');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('회색힐', '1', '10000', '12000', '2000', '여성용전용 힐', 'w9.jpg', 'n');
insert into product(name, kind, price, sale_price, margin, content, image) values('여성부츠', '2', '12000', '18000', '6000', '여성용 부츠', 'w4.jpg');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '핑크샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-10.jpg', 'y');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('슬리퍼', '3', '5000', '5500', '500', '편안한 슬리퍼입니다.', 'w11.jpg', 'y');
insert into product(name, kind, price, sale_price, margin, content, image) values( '스니커즈', '4', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w1.jpg');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '샌달', '3', '5000', '5500', '500', '사계절용 샌달입니다.', 'w-09.jpg','n');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '스니커즈', '5', '15000', '20000', '5000', '활동성이 좋은 스니커즈입니다.', 'w-05.jpg','n');

SELECT * FROM QNA;

INSERT INTO QNA (SUBJECT, CONTENT, ID) VALUES('테스트', '질문내용1', 'one');
INSERT INTO QNA (SUBJECT, CONTENT, ID) VALUES('테스트2', '질문내용2', 'one');

update qna SET REP = '답변내용', REP_YN = '2';


-- VIEW
create or replace view new_pro_view
as
SELECT no, name, sale_price, image 
from( select rownum, no, name, sale_price, image 
      from product  
      where del_yn='y' 
      order by reg_date desc)
where  rownum <=4;

SELECT NO, NAME, SALE_PRICE, IMAGE FROM NEW_PRO_VIEW;

-- BEST
create or replace view best_pro_view
as
SELECT no, name, sale_price, image 
from( select rownum, no, name, sale_price, image 
      from product  
      where best_yn='y' 
      order by reg_date desc)
where  rownum <=4;

SELECT NO, NAME, SALE_PRICE, IMAGE FROM BEST_PRO_VIEW;

SELECT NO, PNO, MEMBER_ID, QUANTITY, RESULT_YN, REG_DATE FROM CART;

CREATE OR REPLACE VIEW CART_VIEW
AS
SELECT O.NO , O.MEMBER_ID, O.PNO, M.NAME MNAME, P.NAME PNAME, O.QUANTITY, O.REG_DATE, P.SALE_PRICE , O.RESULT_YN 
FROM CART O, MEMBER M, PRODUCT P 
WHERE O.MEMBER_ID = M.ID AND O.PNO = P.NO
AND RESULT_YN='1';

SELECT NO, MEMBER_ID, PNO, MNAME, PNAME, QUANTITY, REG_DATE, SALE_PRICE, RESULT_YN FROM cart_view;

CREATE OR REPLACE VIEW ORDER_VIEW
AS
SELECT d.NO dno, o.NO ono, o.id mid, o.ORDER_DATE , d.PNO pno, d.quantity, m.name mname, m.zip_num, m.address, m.phone, p.name pname, p.SALE_PRICE , d.RESULT_YN result   
  FROM orders o JOIN order_detail d ON o.NO = d.ONO JOIN member m ON o.ID =m.ID JOIN product p ON d.PNO = p.NO;
 
SELECT * FROM ORDER_VIEW;
SELECT ONO, MID, MNAME, PHONE, ZIP_NUM, ADDRESS, DNO, ORDER_DATE, RESULT, PNO, PNAME, QUANTITY, SALE_PRICE  FROM ORDER_VIEW;

SELECT ONO, MID, MNAME, PHONE, ZIP_NUM, ADDRESS, DNO, ORDER_DATE, RESULT, PNO, PNAME, QUANTITY, SALE_PRICE  
  FROM ORDER_VIEW 
 WHERE MID = ? AND RESULT LIKE ? AND ONO = ?;



--------------------------------------------------------------------------------------------------------------------------------------------

SELECT ID, PWD, NAME, EMAIL, ZIP_NUM, ADDRESS, PHONE, LEAVE_YN, JOIN_DATE FROM MEMBER;

INSERT INTO MEMBER(ID, PWD, NAME, ZIP_NUM, ADDRESS, PHONE) VALUES('one', '1111', '김나리', '133-110', '서울시성동구성수동1가 1번지21호', '017-777-7777');
insert into member(id, pwd, name, zip_num, address, phone) values('two', '2222', '이백합', '130-120', '서울시송파구잠실2동 리센츠 아파트 201동 505호', '011-123-4567');