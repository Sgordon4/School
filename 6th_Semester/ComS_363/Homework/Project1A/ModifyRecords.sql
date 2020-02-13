UPDATE students SET name = "Scott" WHERE ssn = "746897816";
UPDATE major SET name = "Computer Science" WHERE snum = (SELECT snum FROM students WHERE ssn = "746897816");
SET SQL_SAFE_UPDATES=0;
DELETE FROM register WHERE regtime = "Spring2015";