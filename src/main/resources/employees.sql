
CREATE TABLE `employees` (
  `emp_no` int NOT NULL,
  `birth_date` date NOT NULL,
  `first_name` varchar(14) NOT NULL,
  `last_name` varchar(16) NOT NULL,
  `gender` enum('M','F') NOT NULL,
  `hire_date` date NOT NULL,
  PRIMARY KEY (`emp_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10001,'1953-09-02','Georgi','Facello','M','1986-06-26');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10002,'1964-06-02','Bezalel','Simmel','F','1985-11-21');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10003,'1959-12-03','Parto','Bamford','M','1986-08-28');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10004,'1954-05-01','Chirstian','Koblick','M','1986-12-01');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10005,'1955-01-21','Kyoichi','Maliniak','M','1989-09-12');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10006,'1953-04-20','Anneke','Preusig','F','1989-06-02');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10007,'1957-05-23','Tzvetan','Zielinski','F','1989-02-10');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10008,'1958-02-19','Saniya','Kalloufi','M','1994-09-15');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10009,'1952-04-19','Sumant','Peac','F','1985-02-18');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10010,'1963-06-01','Duangkaew','Piveteau','F','1989-08-24');
INSERT INTO `employees` (`emp_no`,`birth_date`,`first_name`,`last_name`,`gender`,`hire_date`) VALUES (10011,'1972-02-29','Jiang','David','M','1990-02-20');