-- ���� �߰� �� ���� �ο�


-- ������ Ȯ��
SELECT USER FROM DUAL;

-- ���� ����
DROP USER nonage CASCADE;
CREATE USER nonage IDENTIFIED BY rootroot;

-- ���� �߰�
GRANT CONNECT, RESOURCE, CREATE SYNONYM, CREATE VIEW TO nonage;

-- ���� Ȯ��
SELECT *
  FROM DBA_USERS
WHERE USERNAME = 'NONAGE';