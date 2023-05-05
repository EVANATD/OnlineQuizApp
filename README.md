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










