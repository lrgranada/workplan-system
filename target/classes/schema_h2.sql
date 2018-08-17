DROP table if exists TRANSACTION_REPORT;

DROP SEQUENCE if exists TRANSACTION_REPORT_SEQ;

CREATE SEQUENCE TRANSACTION_REPORT_SEQ
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE TRANSACTION_REPORT (
	ID NUMBER DEFAULT TRANSACTION_REPORT_SEQ.NEXTVAL NOT NULL,
    TRANS_DTE DATE NULL,
 	TRANS_ID VARCHAR2(20) NULL,
 	PHONE_NBR VARCHAR2(20) NULL,
 	AMOUNT NUMBER(10,2) NULL,
 	FORM_TYPE VARCHAR2(10) NULL,RET_DATE DATE NULL,
 	TIN VARCHAR2(12),
 	BRANCH_CD VARCHAR2(5) NULL,
 	CREATED_DTE TIMESTAMP DEFAULT SYSDATE
 );
 
