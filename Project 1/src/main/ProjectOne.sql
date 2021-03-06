
DROP TABLE REIMBURSEMENTS;
commit work;
DROP TABLE USERLOG;
commit work;
SELECT * FROM PK_USERLOGID WHERE USERLOG = "tabnam";
CREATE TABLE USERLOG (EMAIL VARCHAR2(50), PSWRD VARCHAR2(50),FIRSTNAME VARCHAR2(50),
LASTNAME VARCHAR2(50), PHONENUMBER NUMBER(10,0), ADDRESS VARCHAR2(50), MANAGER NUMBER(1,0),
LOGGEDIN VARCHAR2(7),ACCOUNTNUMBER NUMBER(3,0) CONSTRAINT PK_USERLOGID PRIMARY KEY );

CREATE TABLE REIMBURSEMENTS ( RECEIPT NUMBER(8,0),AMOUNT NUMBER(8,2), REASON VARCHAR2(50),
STATUS NUMBER(1,0), MANAGERID NUMBER(3,0), ACCOUNTNUMBER NUMBER(3,0) CONSTRAINT FK_USERLOGID REFERENCES USERLOG ); 

INSERT INTO USERLOG (EMAIL, PSWRD, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS, MANAGER,
LOGGEDIN, ACCOUNTNUMBER) VALUES ('JSAGREROPEREZ@GMAIL.COM', 'PASSWORD123','JORGE','SAGRERO', 
6614964009, '1530 Cherry Street,Bakersfield CA 93305', 0,'FALSE',1);

INSERT INTO USERLOG (EMAIL, PSWRD, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS, MANAGER,
LOGGEDIN, ACCOUNTNUMBER) VALUES ('CASSANDRA@GMAIL.COM', 'PASSWORD123','CASSANDRA','PENTAGHAST', 
4830923942, '4231 Berry Street,Almond VA 58090', 1,'FALSE',2);

INSERT INTO USERLOG (EMAIL, PSWRD, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS, MANAGER,
LOGGEDIN, ACCOUNTNUMBER) VALUES ('KINGKONG@GMAIL.COM', 'PASSWORD123','Manuel','Perez', 
6617351999, '2313 Grand Oaks Blvd ,Oildale WA 34325', 0,'FALSE',3);

INSERT INTO USERLOG (EMAIL, PSWRD, FIRSTNAME, LASTNAME, PHONENUMBER, ADDRESS, MANAGER,
LOGGEDIN, ACCOUNTNUMBER) VALUES ('SAMARA@GMAIL.COM', 'PASSWORD123','SAMARA','CORVEGA', 
5512324009, '5423 Citadel Ave,Reston CA 34305', 1,'FALSE',4);

--default manager id will be zero to indicate that no manager has approved the reimbursment, and 
-- default value of status is 0 to indicate that the status is pending and has  not been reviewed by a manager
INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (0,0,'default',0,0,1);
INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (0,0,'default',0,0,2);
INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (0,0,'default',0,0,3);
INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (0,0,'default',0,0,4);

INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (1,500,'lunch',0,0,1);

INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (2,1000,'Brunch',0,0,1);

INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (3,200,'PASSED THE OCA',0,0,3);

INSERT INTO REIMBURSEMENTS (RECEIPT,AMOUNT,REASON,STATUS,MANAGERID,ACCOUNTNUMBER) VALUES (4,1000,'RELOCATION EXPENSE',0,0,1);

  --RETURNS TRUE IF THERE IS A MATCH
  CREATE OR REPLACE FUNCTION VALIDATE_USER(USERN USERLOG.EMAIL%TYPE,PSW USERLOG.PSWRD%TYPE)
    RETURN VARCHAR2
AS
    VALID VARCHAR2(7);
    ANY_ROWS_FOUND NUMBER;
BEGIN 

    SELECT COUNT(*)
    INTO ANY_ROWS_FOUND
    FROM USERLOG
    WHERE  ROWNUM = 1 AND USERN = EMAIL AND PSW = PSWRD;
    IF ANY_ROWS_FOUND = 1 THEN
    VALID := 'TRUE';
    ELSE
    VALID := 'FALSE';
    END IF;
    RETURN VALID;
END;
/
SET SERVEROUTPUT ON;

DECLARE
USERN USERLOG.EMAIL%TYPE;
PSW USERLOG.PSWRD%TYPE;
HOLDER VARCHAR2(7);
BEGIN 
    USERN := 'KINGKONG@GMAIL.COM';
    PSW := 'PASSWORD123';
    HOLDER := VALIDATE_USER(USERN,PSW);
   dbms_output.put_line('EMAIL: '  || ' IS TAKEN'|| HOLDER); 
END; 
/
COMMIT WORK;

SELECT * FROM REIMBURSEMENTS WHERE STATUS = 0 AND ACCOUNTNUMBER = 1;
 

SELECT COUNT(DISTINCT CUSTOMERID)FROM INVOICE;