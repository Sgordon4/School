
insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1001, 654651234, 'Randy', 'M', '2000-12-01', '301 E Hall', 5152700988, '121 Main', 7083066321);

insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1002, 123097834, 'Victor', 'M', '2000-05-06', '270 W Hall', 5151234578, '701 Walnut', 7080366333);

insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1003, 978012431, 'John', 'M', '1999-07-08', '201 W Hall', 5154132805, '888 University', 5152012011);

insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1004, 746897816, 'Seth', 'M', '1998-12-30', '199 N Hall', 5158891504, '21 Green', 5154132907);

insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1005, 186032894, 'Nicole', 'F', '2001-04-01', '178 S Hall', 5158891155, '13 Gray', 5157162071);

insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1006, 534218752, 'Becky', 'F', '2001-05-16', '12 N Hall', 5157083698, '189 Clark', 2034367632);

insert into students (snum, ssn, name, gender, dob, c_addr, c_phone, p_addr, p_phone) values (1007, 432609519, 'Kevin', 'M', '2000-08-12', '75 E Hall', 5157082497, '89 National', 7182340772);



insert into departments (code, name, phone, college) values (401, 'Computer Science', 5152982801, 'LAS');

insert into departments (code, name, phone, college) values (402, 'Mathematics', 5152982802, 'LAS');

insert into departments (code, name, phone, college) values (403, 'Chemical Engineering', 5152982803, 'Engineering');

insert into departments (code, name, phone, college) values (404, 'Landscape Architect', 5152982804, 'Design');



insert into degrees (name, level, department_code) values ('Computer Science', 'BS', 401);

insert into degrees (name, level, department_code) values ('Software Engineering', 'BS', 401);

insert into degrees (name, level, department_code) values ('Computer Science', 'MS', 401);

insert into degrees (name, level, department_code) values ('Computer Science', 'PhD', 401);

insert into degrees (name, level, department_code) values ('Applied Mathematics', 'MS', 402);

insert into degrees (name, level, department_code) values ('Chemical Engineering', 'BS', 403);

insert into degrees (name, level, department_code) values ('Landscape Architect', 'BS', 404);



insert into major (snum, name, level) values (1001, 'Computer Science', 'BS');

insert into major (snum, name, level) values (1002, 'Software Engineering', 'BS');

insert into major (snum, name, level) values (1003, 'Chemical Engineering', 'BS');

insert into major (snum, name, level) values (1004, 'Landscape Architect', 'BS');

insert into major (snum, name, level) values (1005, 'Computer Science', 'MS');

insert into major (snum, name, level) values (1006, 'Applied Mathematics', 'MS');

insert into major (snum, name, level) values (1007, 'Computer Science', 'PhD');



insert into minor (snum, name, level) values (1007, 'Applied Mathematics', 'MS');

insert into minor (snum, name, level) values (1005, 'Applied Mathematics', 'MS');

insert into minor (snum, name, level) values (1001, 'Software Engineering', 'BS');



insert into courses (number, name, description, credithours, level, department_code) values (113, 'Spreadsheet', 'Microsoft
Excel and Access', 3, 'Undergraduate', 401);

insert into courses (number, name, description, credithours, level, department_code) values (311, 'Algorithm', 'Design and Analysis', 3, 'Undergraduate', 401);

insert into courses (number, name, description, credithours, level, department_code) values (531, 'Theory of Computation', 'Theorem and Probability', 3, 'Graduate', 401);

insert into courses (number, name, description, credithours, level, department_code) values (363, 'Database', 'Design Principle', 3, 'Undergraduate', 401);

insert into courses (number, name, description, credithours, level, department_code) values (412, 'Water Management', 'Water Management', 3, 'Undergraduate', 404);

insert into courses (number, name, description, credithours, level, department_code) values (228, 'Special Topics', 'Interesting Topics about CE', 3, 'Undergraduate', 403);

insert into courses (number, name, description, credithours, level, department_code) values (101, 'Calculus', 'Limit and Derivative', 3, 'Undergraduate', 402);



insert into register (snum, course_number, regtime, grade) values (1001, 363, 'Fall2015', 3);

insert into register (snum, course_number, regtime, grade) values (1002, 311, 'Fall2015', 4);

insert into register (snum, course_number, regtime, grade) values (1003, 228, 'Fall2015', 4);

insert into register (snum, course_number, regtime, grade) values (1004, 363, 'Spring2015', 3);

insert into register (snum, course_number, regtime, grade) values (1005, 531, 'Spring2015', 4);

insert into register (snum, course_number, regtime, grade) values (1006, 363, 'Fall2015', 3);

insert into register (snum, course_number, regtime, grade) values (1007, 531, 'Spring2015', 4);











