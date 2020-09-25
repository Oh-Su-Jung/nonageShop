insert into worker values('admin', 'admin', 'ȫ����', '010-777-7777');
insert into worker values('pinksung', 'pinksung', '����', '010-999-9696');

SELECT NO, NAME, KIND, PRICE, SALE_PRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT;

SELECT NO, NAME, KIND, PRICE, SALE_PRICE, MARGIN, CONTENT, IMAGE, DEL_YN, BEST_YN, REG_DATE FROM PRODUCT WHERE KIND = 2;

insert into product(name, kind, price, sale_price, margin, content, image) values('ũ�α״��Ϻ���', '2', '40000', '50000', '10000', '�����϶� ũ�α״��Ϻ��� �Դϴ�.', 'w2.jpg');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('�պ���', '2', 40000, 50000, 10000,'������ �պ��� �Դϴ�.', 'w-28.jpg', 'n');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '��', '1', '10000', '12000', '2000', '���������� ��', 'w-26.jpg', 'n');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('������', '4', '5000', '5500', '500', '����� �������Դϴ�.', 'w-25.jpg', 'y');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('ȸ����', '1', '10000', '12000', '2000', '���������� ��', 'w9.jpg', 'n');
insert into product(name, kind, price, sale_price, margin, content, image) values('��������', '2', '12000', '18000', '6000', '������ ����', 'w4.jpg');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '��ũ����', '3', '5000', '5500', '500', '������� �����Դϴ�.', 'w-10.jpg', 'y');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values('������', '3', '5000', '5500', '500', '����� �������Դϴ�.', 'w11.jpg', 'y');
insert into product(name, kind, price, sale_price, margin, content, image) values( '����Ŀ��', '4', '15000', '20000', '5000', 'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w1.jpg');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '����', '3', '5000', '5500', '500', '������� �����Դϴ�.', 'w-09.jpg','n');
insert into product(name, kind, price, sale_price, margin, content, image, best_yn) values( '����Ŀ��', '5', '15000', '20000', '5000', 'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w-05.jpg','n');

SELECT * FROM QNA;

INSERT INTO QNA (SUBJECT, CONTENT, ID) VALUES('�׽�Ʈ', '��������1', 'one');
INSERT INTO QNA (SUBJECT, CONTENT, ID) VALUES('�׽�Ʈ2', '��������2', 'one');

update qna SET REP = '�亯����', REP_YN = '2';


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

INSERT INTO MEMBER(ID, PWD, NAME, ZIP_NUM, ADDRESS, PHONE) VALUES('one', '1111', '�質��', '133-110', '����ü�����������1�� 1����21ȣ', '017-777-7777');
insert into member(id, pwd, name, zip_num, address, phone) values('two', '2222', '�̹���', '130-120', '����ü��ı����2�� ������ ����Ʈ 201�� 505ȣ', '011-123-4567');