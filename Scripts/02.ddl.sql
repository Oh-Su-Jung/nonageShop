SELECT USER FROM DUAL;


/* 상품
 * no 시퀀스 생성
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

-- 회원
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

-- 장바구니
-- no sequ
CREATE TABLE cart (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		pno NUMBER(5),
		member_id VARCHAR2(20),
		quantity NUMBER(5) DEFAULT 1,
		result_yn CHAR(1) DEFAULT '1',
		reg_date DATE DEFAULT sysdate
);

-- 주문
CREATE TABLE orders (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		id VARCHAR2(20),
		order_date DATE DEFAULT sysdate
);

-- 주문상세
-- no sequence
CREATE TABLE order_detail (
		no NUMBER(5) NOT NULL PRIMARY KEY,
		oNo NUMBER(5),
		pNo NUMBER(5),
		quantity NUMBER(5),
		result_yn CHAR(1) DEFAULT '1'
);

-- QNA 게시판
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

-- 주소
CREATE TABLE address (
		zip_num CHAR(7),
		sido VARCHAR2(100),
		gugun VARCHAR2(100),
		dong VARCHAR2(100),
		zip_code VARCHAR2(100),
		bunji VARCHAR2(100)
);

-- 관리자
CREATE TABLE worker (
		id VARCHAR2(20) NOT NULL PRIMARY KEY,
		pwd VARCHAR2(20),
		name VARCHAR2(100),
		phone CHAR(13)
);

---------------------------------------------------------------------------

-- 장바구니
ALTER TABLE cart
	ADD CONSTRAINT FK_product_TO_cart -- 상품 -> 장바구니
	FOREIGN KEY (
		pno -- 상품번호
	)
	REFERENCES product ( -- 상품
		no -- 상품번호
	);

-- 장바구니
ALTER TABLE cart
	ADD CONSTRAINT FK_member_TO_cart -- 회원 -> 장바구니
	FOREIGN KEY (
		member_id -- 회원아이디
	)
	REFERENCES member ( -- 회원
		id -- 회원아이디
	);

-- 주문
ALTER TABLE orders
	ADD CONSTRAINT FK_member_TO_orders -- 회원 -> 주문
	FOREIGN KEY (
		id -- 주문자아이디
	)
	REFERENCES member ( -- 회원
		id -- 회원아이디
	);

-- 주문상세
ALTER TABLE order_detail
	ADD CONSTRAINT FK_orders_TO_order_detail -- 주문 -> 주문상세
	FOREIGN KEY (
		oNo -- 주문번호
	)
	REFERENCES orders ( -- 주문
		no -- 주문번호
	);

-- 주문상세
ALTER TABLE order_detail
	ADD CONSTRAINT FK_product_TO_order_detail -- 상품 -> 주문상세
	FOREIGN KEY (
		pNo -- 상품번호
	)
	REFERENCES product ( -- 상품
		no -- 상품번호
	);
	



-- 시퀀스 생성

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