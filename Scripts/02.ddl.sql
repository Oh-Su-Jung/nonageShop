SELECT USER FROM DUAL;


/* ��ǰ
 * no ������ ����
 */
CREATE TABLE product (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		name VARCHAR2(100),
		kind CHAR(1),
		price NUMBER,
		sale_price NUMBER,
		margin NUMBER,
		content VARCHAR2(1000),
		image VARCHAR2(100) DEFAULT 'default.jpg',
		del_yn CHAR(1) DEFAULT 'y',
		best_yn CHAR(1) DEFAULT 'n',
		reg_date DATE DEFAULT sysdate
);

-- ȸ��
CREATE TABLE member (
		id VARCHAR2(20) NOT NULL PRIMARY KEY,
		pwd VARCHAR2(20),
		name VARCHAR2(100),
		email VARCHAR2(40),
		zip_num CHAR(7),
		address VARCHAR2(100),
		phone CHAR(13),
		leave_yn CHAR(1) DEFAULT 'y',
		join_date DATE DEFAULT sysdate
);

-- ��ٱ���
-- no sequ
CREATE TABLE cart (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		pno NUMBER(5),
		member_id VARCHAR2(20),
		quantity NUMBER(5) DEFAULT 1,
		result_yn CHAR(1) DEFAULT '1',
		reg_date DATE DEFAULT sysdate
);

-- �ֹ�
CREATE TABLE orders (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		id VARCHAR2(20),
		order_date DATE DEFAULT sysdate
);

-- �ֹ���
-- no sequence
CREATE TABLE order_detail (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		oNo NUMBER(5),
		pNo NUMBER(5),
		quantity NUMBER(5),
		result_yn CHAR(1) DEFAULT '1'
);

-- QNA �Խ���
-- no sequence
CREATE TABLE qna (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		subject VARCHAR2(100),
		content VARCHAR2(1000),
		rep VARCHAR2(1000),
		id VARCHAR2(20),
		rep_yn CHAR(1) DEFAULT '1',
		writer_date DATE DEFAULT sysdate
);

-- �ּ�
CREATE TABLE address (
		zip_num CHAR(7),
		sido VARCHAR2(100),
		gugun VARCHAR2(100),
		dong VARCHAR2(100),
		zip_code VARCHAR2(100),
		bunji VARCHAR2(100)
);

-- ������
CREATE TABLE worker (
		id VARCHAR2(20) NOT NULL PRIMARY KEY,
		pwd VARCHAR2(20),
		name VARCHAR2(100),
		phone CHAR(13)
);

---------------------------------------------------------------------------

-- ��ٱ���
ALTER TABLE cart
	ADD CONSTRAINT FK_product_TO_cart -- ��ǰ -> ��ٱ���
	FOREIGN KEY (
		pno -- ��ǰ��ȣ
	)
	REFERENCES product ( -- ��ǰ
		no -- ��ǰ��ȣ
	);

-- ��ٱ���
ALTER TABLE cart
	ADD CONSTRAINT FK_member_TO_cart -- ȸ�� -> ��ٱ���
	FOREIGN KEY (
		member_id -- ȸ�����̵�
	)
	REFERENCES member ( -- ȸ��
		id -- ȸ�����̵�
	);

-- �ֹ�
ALTER TABLE orders
	ADD CONSTRAINT FK_member_TO_orders -- ȸ�� -> �ֹ�
	FOREIGN KEY (
		id -- �ֹ��ھ��̵�
	)
	REFERENCES member ( -- ȸ��
		id -- ȸ�����̵�
	);

-- �ֹ���
ALTER TABLE order_detail
	ADD CONSTRAINT FK_orders_TO_order_detail -- �ֹ� -> �ֹ���
	FOREIGN KEY (
		oNo -- �ֹ���ȣ
	)
	REFERENCES orders ( -- �ֹ�
		no -- �ֹ���ȣ
	);

-- �ֹ���
ALTER TABLE order_detail
	ADD CONSTRAINT FK_product_TO_order_detail -- ��ǰ -> �ֹ���
	FOREIGN KEY (
		pNo -- ��ǰ��ȣ
	)
	REFERENCES product ( -- ��ǰ
		no -- ��ǰ��ȣ
	);
	



-- ������ ����

-- PRODUCT(NO)
CREATE SEQUENCE PRODUCT_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_PRODUCT_NO_SEQ
BEFORE INSERT ON PRODUCT
FOR EACH ROW
BEGIN 
		IF Inserting AND :NEW.NO IS NULL THEN 
			SELECT PRODUCT_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
		END IF;
END;

-- CART(NO)
CREATE SEQUENCE CART_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_CART_NO_SEQ
BEFORE INSERT ON CART
FOR EACH ROW
BEGIN 
		IF Inserting AND :NEW.NO IS NULL THEN 
			SELECT CART_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
		END IF;
END;

-- ORDER
DROP SEQUENCE ORDERS_NO_SEQ;
CREATE SEQUENCE ORDERS_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_ORDERS_NO_SEQ
BEFORE INSERT ON ORDERS
FOR EACH ROW 
BEGIN 
	IF Inserting AND :NEW.NO IS NULL THEN 
		SELECT ORDERS_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
	END IF;
END; 

-- ORDER_DETAIL(NO)
DROP SEQUENCE ORDER_DETAIL_NO_SEQ;
CREATE SEQUENCE ORDER_DETAIL_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_ORDER_DETAIL_NO_SEQ
BEFORE INSERT ON ORDER_DETAIL
FOR EACH ROW
BEGIN 
		IF Inserting AND :NEW.NO IS NULL THEN 
			SELECT ORDER_DETAIL_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
		END IF;
END;

-- QNA(NO)
CREATE SEQUENCE QNA_NO_SEQ
	START WITH 1
	INCREMENT BY 1;

CREATE OR REPLACE TRIGGER TRI_QNA_NO_SEQ
BEFORE INSERT ON QNA
FOR EACH ROW
BEGIN 
		IF Inserting AND :NEW.NO IS NULL THEN 
			SELECT QNA_NO_SEQ.NEXTVAL INTO :NEW.NO FROM DUAL;
		END IF;
END;