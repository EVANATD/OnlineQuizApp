create database Quiz_App;
use Quiz_App;

CREATE TABLE users (
   user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL
);

CREATE TABLE categories (
  cat_id INT PRIMARY KEY,
  cat_name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE History (
  His_id INT PRIMARY KEY,
  Hisq VARCHAR(5000) NOT NULL,
  cat_id INT,
  FOREIGN KEY (cat_id)  REFERENCES categories(cat_id)
);

CREATE TABLE Maths (
  Maths_id INT PRIMARY KEY,
  Mathsq VARCHAR(5000) NOT NULL,
  cat_id INT,
  FOREIGN KEY (cat_id)  REFERENCES categories(cat_id)
);

CREATE TABLE Geography (
  geo_id INT PRIMARY KEY,
  geoq VARCHAR(5000) NOT NULL,
  cat_id INT,
  FOREIGN KEY (cat_id)  REFERENCES categories(cat_id)
);

INSERT INTO categories (cat_id, cat_name)VALUES (1, 'True or False'),
       (2, 'MCQ'),
       (3, 'one word');
       
INSERT INTO History (His_id, Hisq, cat_id)
VALUES (1, 'When did India get freedom?', 2),
       (2, 'What is the second colour of national flag?', 2),
	   (3, 'Who was the first Prime Minister of India?', 2),
	   (4, 'When is Gandhi Jayanthi celebrated in India', 2),
	   (5, 'Which is the national sport of India?', 2),
       (6, 'When did India get freedom?', 3),
       (7, 'What is the second colour of national flag?', 3),
	   (8, 'Who was the first Prime Minister of India?', 3),
	   (9, 'When is Gandhi Jayanthi celebrated in India', 3),
	   (10, 'Which is the national sport of India?', 3),
       (11, 'Independence day is on 15th Aug', 1),
       (12, 'The second colour of national flag is white?', 1),
	   (13, 'Jawaharlal Nehru is the first Prime Minister of India?', 1),
	   (14, 'Gandhi Jayanthi is celebrated on 2nd Oct', 1),
	   (15, 'Hockey is the national sport of India?', 1);
       select*from History;
       
INSERT INTO Geography (geo_id, geoq, cat_id)
VALUES (1, 'Which is the tallest mountain in the world', 2),
       (2, 'Which country has the highest population?', 2),
	   (3, 'Which is the largest desert in the world?', 2),
	   (4, 'Which is the coldest continent in the world', 2),
	   (5, 'which planet is close to earth?', 2),
       (6, 'Which is the tallest mountain in the world', 3),
       (7, 'Which country has the highest population?', 3),
	   (8, 'Which is the largest desert in the world?', 3),
	   (9, 'Which is the coldest continent in the world?',3),
	   (10, 'which planet is close to earth?', 3),
       (11, 'Mount Everest is the tallest mountain in the world?', 1),
       (12, 'India has the highest population?', 1),
	   (13, 'Sahara Desert is the largest desert in the world?', 1),
	   (14, 'Antarctica is the coldest continent in the world', 1),
	   (15, 'The planet Venus is close to earth?', 1);
       select*from Geography;
INSERT INTO Maths (Maths_id, Mathsq, cat_id)
VALUES (1, 'The even number just before 18 is?', 2),
       (2, 'Prime number between 24 and 30 ?', 2),
	   (3, 'A rational number can be represented in the form of?', 2),
	   (4, 'The odd number after 21 is?', 2),
	   (5, ' The value of ½ + ¼ is equal to?', 2),
       (6, 'The even number just before 18 is?', 3),
       (7, 'Prime number between 24 and 30 ?', 3),
	   (8, 'A rational number can be represented in the form of?', 3),
	   (9, 'The odd number after 21 is?', 3),
	   (10, ' The value of ½ + ¼ is equal to?', 3),
       (11, 'The even number just before 18 is 15?', 1),
       (12, 'Prime number between 24 and 30 is 29?', 1),
	   (13, 'A rational number can be represented in the form of pq?', 1),
	   (14, 'The odd number after 21 is 22?', 1),
	   (15, ' The value of ½ + ¼ is equal to 3/4?', 1);
       select*from Maths;
       
UPDATE History SET Hisq = 'Independence day is on 14th Aug' WHERE His_id = 11;
UPDATE History SET Hisq = 'Gandhi Jayanthi is celebrated on 22nd Oct' WHERE His_id = 14;
UPDATE History SET Hisq = 'Hockey is not the national sport of India' WHERE His_id = 12;
select*from History;

UPDATE Geography SET geoq = 'Antarctica is the hottest continent in the world'WHERE geo_id = 14;
UPDATE Geography SET geoq = 'China has the highest population'WHERE geo_id = 12;
DROP TABLE Maths;
DROP TABLE Geography;
show tables;

CREATE TABLE Answer (
  ans_id INT PRIMARY KEY,
  answer VARCHAR(5000) NOT NULL,
  His_id INT,
  FOREIGN KEY (His_id)  REFERENCES History(His_id)
);
INSERT INTO Answer (ans_id,answer,His_id)
VALUES (1, '1947', 1),
       (2, 'WHITE', 2),
	   (3, 'JAWAHARLAL NEHRU', 3),
	   (4, '2 OCTOBER', 4),
	   (5, 'HOCKEY', 5),
       (6, '1947', 6),
       (7, 'WHITE', 7),
	   (8, 'JAWAHARLAL NEHRU', 8),
	   (9, '2 OCTOBER', 9),
	   (10, 'HOCKEY', 10),
       (11, 'FALSE', 11),
       (12, 'TRUE', 12),
	   (13, 'TRUE', 13),
	   (14, 'FALSE', 14),
	   (15, 'FALSE' , 15);
       SELECT*FROM Answer;
       
CREATE TABLE scores (
score_id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
His_id INT NOT NULL,
score INT NOT NULL,
PRIMARY KEY (score_id),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (His_id) REFERENCES History(His_id)
);

show tables;
select*from History;



UPDATE History SET Hisq = 'When did India get freedom?   options:1)1898  2)1990  3)1947'WHERE His_id = 1;
UPDATE History SET Hisq = 'What is the second colour of national flag?   options:1)WHITE  2)RED  3)GREEN'WHERE His_id = 2;
UPDATE History SET Hisq = 'Who was the first Prime Minister of India?   options:1)MAHATMA GANDHI  2)TAGORE  3)JAWAHARLAL NEHRU'WHERE His_id = 3;
UPDATE History SET Hisq = 'When is Gandhi Jayanthi celebrated in India?   options:1)22 OCTOBER  2)2 OCTOBER  3)20 OCTOBER'WHERE His_id = 4;
UPDATE History SET Hisq = 'Which is the national sport of India?   options:1)FOOTBALL  2)BASKET BALL  3)HOCKEY'WHERE His_id = 5;
UPDATE Answer SET answer = '3'WHERE ans_id = 1;
UPDATE Answer SET answer = '1'WHERE ans_id = 2;
UPDATE Answer SET answer = '3'WHERE ans_id = 3;
UPDATE Answer SET answer = '2'WHERE ans_id = 4;
UPDATE Answer SET answer = '3'WHERE ans_id = 5;  

select*from History;     
select*from categories
;
desc History;
desc Answer;

SELECT answer FROM Answer WHERE His_id = 1;

alter table scores
drop column His_id;
drop table scores;   

CREATE TABLE scores (
score_id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
score INT NOT NULL,
PRIMARY KEY (score_id),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);               
desc scores;
select*from scores;
select*from answer;
show tables;
desc users;
SELECT * FROM users;
desc users;
select * from scores;
select MAX(score) as max_score from scores where user_id = 1;
select score from scores where user_id=1;
select MAX(score) as max_score from scores where user_id=1;
show tables;
