DECLARE
    v_age NUMBER;
BEGIN
    FOR c IN (SELECT CustomerID, DOB FROM Customers) LOOP

        v_age := FLOOR(MONTHS_BETWEEN(SYSDATE, c.DOB)/12);

        IF v_age > 60 THEN

            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = c.CustomerID;

        END IF;

    END LOOP;

    COMMIT;
END;
/


ALTER TABLE Customers
ADD IsVIP VARCHAR2(5);

BEGIN

    FOR c IN (SELECT CustomerID, Balance FROM Customers) LOOP

        IF c.Balance > 10000 THEN

            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = c.CustomerID;

        END IF;

    END LOOP;

    COMMIT;
END;
/


BEGIN

    FOR l IN
    (
        SELECT CustomerID, LoanID, EndDate
        FROM Loans
        WHERE EndDate BETWEEN SYSDATE AND SYSDATE + 30
    )
    LOOP

        DBMS_OUTPUT.PUT_LINE(
        'Reminder: Loan '
        || l.LoanID
        || ' for Customer '
        || l.CustomerID
        || ' is due soon.');

    END LOOP;

END;
/


