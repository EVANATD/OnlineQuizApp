# ONLINE QUIZ APP

### Description:
This code is a quiz application written in Java that connects with a MySQL database to store and retrieve user data. The code allows users to register, log in, and take quizzes. The program presents a list of categories to choose from, and then displays a series of multiple-choice questions for the selected category. After answering all questions, the program displays the user's score, saves it to a file, and updates their score in the database.The code uses several Java libraries, including java.util, java.sql, and java.io, to handle user input, database interactions, and file I/O operations. The program is structured as a single class with several methods, each responsible for particular aspect of the application.
               The main() method is responsible for handling user input and controlling the flow of the program. It prompts the user to choose registration or login, and then calls the appropriate method based on the user's input. If the user chooses to register, the userregistration() method is called, which allows the user to enter their username and password and stores the data in the database. If the user chooses to log in, the login() method is called, which allows the user to enter their username and password and checks if they match the data in the database.Once the user is logged in, the main() method presents a list of categories to choose from and prompts the user to select one. The categories() method is responsible for retrieving the list of categories from the database and displaying them to the user. Once the user selects a category, the questions() method is called, which retrieves a set of questions from the database for the selected category and presents them to the user one at a time. The user's answers are stored and the program calculates the user's score based on the number of correct answers.After the user completes the quiz, the main() method displays the user's score in a table format, saves it to a file, and updates the user's score in the database using the scoretableupdate() method. Finally, the program prompts the user to either attempt the quiz again or exit the program.

user table pic



CREATE TABLE users (
   user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(50) NOT NULL
);


This code creates a table called "users" in a MySQL database. The table has three columns: "user_id", "username", and "password". The "user_id" column is set as the primary key, which means it uniquely identifies each row in the table. The "username" column is set as a unique and non-null field, which means that it cannot contain duplicate values and cannot be left empty. The "password" column is also non-null, which means that it must contain a value for each row in the table. Additionally, the "user_id" column is set to auto-increment, which means that it will automatically assign a new value for each new row added to the table, starting from 1 and incrementing by 1 each time.

When the user registers the user's name is added into this user table.If same name exist the user is asked to use another name for registering.

If the user selects login the user is asked for username and password. If the username don’t exist it will ask to register. If it already exists it will ask which category the user want to choose.


category table pic






CREATE TABLE categories (
  cat_id INT PRIMARY KEY,
  cat_name VARCHAR(50) UNIQUE NOT NULL
);

This code creates a table called "categories" in a MySQL database.The table has two columns: "cat_id" and "cat_name".The "cat_id column" is defined as an integer data type and is used as the primary key for the table. Primary keys are used to uniquely identify each record in the table.The "cat_name" column is defined as a varchar data type. It is set to be unique and not null, which means that it must have a value and cannot be duplicated in the table.

After selecting the the category the user is provided with questions to attempt.
This questions are stored in the history table.

 History table pic 




CREATE TABLE History (
  His_id INT PRIMARY KEY,
  Hisq VARCHAR(5000) NOT NULL,
  cat_id INT,
  FOREIGN KEY (cat_id)  REFERENCES categories(cat_id)
);

The above code creates a table named "History" with three columns - His_id, Hisq, and cat_id. His_id is set as the primary key for the table. Hisq is a VARCHAR data type column with a maximum length of 5000 characters and cannot be null. cat_id is an integer data type column that can have a foreign key constraint referencing the primary key column "cat_id" in the "categories" table. This means that each record in the "History" table must have a valid "cat_id" value that exists in the "categories" table.

When the user starts to attampt the question the user is provided whether the answer is correct or not.
The answers are stored in the answer table .

Answer table pic


CREATE TABLE Answer (
  ans_id INT PRIMARY KEY,
  answer VARCHAR(5000) NOT NULL,
  His_id INT,
  FOREIGN KEY (His_id)  REFERENCES History(His_id)
);


This MYSQL statement creates a table named "Answer" with three columns: "ans_id,” "answer,” and "His_id.” The "ans_id" column is an integer primary key uniquely identifying each row in the table. The "answer" column is a string of up to 5000 characters and is not nullable, meaning it must have a value for each row. The "His_id" column is a foreign key that references the "His_id" column of the "History" table. This table is likely used to store the answer to a question in the quiz application, with each row representing an answer to a specific question in the "History" table. The "ans_id" serves as a unique identifier for each answer, and the "His_id" foreign key links each answer to a specific question in the "History" table.


After attemopting each quiz the score is added to the score table.

scores table pic




CREATE TABLE scores (
score_id INT NOT NULL AUTO_INCREMENT,
user_id INT NOT NULL,
His_id INT NOT NULL,
score INT NOT NULL,
PRIMARY KEY (score_id),
FOREIGN KEY (user_id) REFERENCES users(user_id),
FOREIGN KEY (His_id) REFERENCES History(His_id)
);



This SQL statement creates a  table named "scores" with four columns: "score_id,” "user_id,” "His_id,” and "score,".where the "score_id" column is an auto-incremented integer that serves as the primary key for this table. The "user_id" column is an integer that represents the user who achieved the score. This column is set not to allow null values and is linked to the "user_id" column in the "users" table through a foreign key constraint. The "His_id" column is an integer that represents the history ID of the quiz taken by the user. This column is set not to allow null values and is linked to the "His_id" column in the "History" table through a foreign key constraint. The "score" column is an integer that stores the score achieved by the user for a particular quiz. This column is set not to allow null values. .

After quiz the score is displayed in the score table.


After this the user is asked whether to attempt again or not.If yes the quiz starts again else the user exits.


Finally, the users highest score is displayed.