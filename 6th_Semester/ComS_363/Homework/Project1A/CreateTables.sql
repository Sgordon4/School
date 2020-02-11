CREATE TABLE students (
    snum INTEGER,
    ssn INTEGER,
    name VARCHAR(10),
    gender VARCHAR(1),
    dob DATETIME,
    c_addr VARCHAR(20),
    c_phone VARCHAR(10),
    p_addr VARCHAR(20),
    p_phone VARCHAR(10),
    PRIMARY KEY (ssn),
    UNIQUE (snum)
);


CREATE TABLE departments (
    code INTEGER,
    name VARCHAR(50),
    phone VARCHAR(10),
    college VARCHAR(20),
    PRIMARY KEY (code),
    UNIQUE (name)
);


CREATE TABLE degrees (
    name VARCHAR(50),
    level VARCHAR(5),
    department_code INTEGER,
    PRIMARY KEY (name , level),
    FOREIGN KEY (department_code)
        REFERENCES departments (code)
);


CREATE TABLE courses (
    number INTEGER,
    name VARCHAR(50),
    description VARCHAR(50),
    credithours INTEGER,
    department_code INTEGER,
    PRIMARY KEY (number),
    UNIQUE (name),
    FOREIGN KEY (department_code)
        REFERENCES departments (code)
);


CREATE TABLE register (
    snum integer,
    course_number integer,
    regtime VARCHAR(20),
    grade INTEGER,
    PRIMARY KEY (snum , course_number),
    FOREIGN KEY (snum)
        REFERENCES students (snum)
);


CREATE TABLE major (
    snum INTEGER,
    name VARCHAR(50),
    level VARCHAR(5),
    PRIMARY KEY (snum , name , level),
    FOREIGN KEY (snum)
        REFERENCES students (snum),
    FOREIGN KEY (name)
        REFERENCES degrees (name),
    FOREIGN KEY (level)
        REFERENCES degrees (level)
);


CREATE TABLE minor (
    snum INTEGER,
    name VARCHAR(50),
    level VARCHAR(5),
    PRIMARY KEY (snum , name , level),
    FOREIGN KEY (snum)
        REFERENCES students (snum),
    FOREIGN KEY (name)
        REFERENCES degrees (name),
    FOREIGN KEY (level)
        REFERENCES degrees (level)
);









