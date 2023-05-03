import java.sql.*;
import java.util.Scanner;

public class App {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement ps;
    private static boolean isDone;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/QUIZ_A", "root", "username");
            stmt = con.createStatement();
            isDone = false;

            showMainMenu();

            con.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void showMainMenu() {
        Scanner input = new Scanner(System.in);
        int choice;

        while (!isDone) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Login");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    signUp();
                    break;
                case 3:
                    isDone = true;
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice, please try again.");
                    break;
            }
        }
    }

    private static void login() {
        Scanner input = new Scanner(System.in);
        String username, password;

        System.out.println("\nPlease enter your username:");
        username = input.nextLine();

        System.out.println("Please enter your password:");
        password = input.nextLine();

        try {
            String query = "SELECT * FROM users WHERE username=? AND password=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nWelcome, " + username + "!");
                isDone = true;
            } else {
                System.out.println("\nInvalid username or password, please try again.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void signUp() {
        Scanner input = new Scanner(System.in);
        String username, password;

        System.out.println("\nPlease enter your desired username:");
        username = input.nextLine();

        try {
            String query = "SELECT * FROM users WHERE username=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\nUsername already exists, please choose another.");
            } else {
                System.out.println("Please enter your desired password:");
                password = input.nextLine();

                String insertQuery = "INSERT INTO users(username, password) VALUES(?, ?)";
                ps = con.prepareStatement(insertQuery);
                ps.setString(1, username);
                ps.setString(2, password);
                ps.executeUpdate();

                System.out.println("\nUser created successfully!");
            }
        } finally{

        }
           // Select a quiz category
           System.out.println("Please select a quiz category:");
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM categories");
           while (rs.next()) {
               System.out.println(rs.getInt("cat_id") + ". " + rs.getString("cat_name"));
           }
           int categoryId = Integer.parseInt(System.console().readLine());

           // Select a quiz
           System.out.println("Please select a quiz:");
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM quizzes WHERE cat_id=" + categoryId);
           while (rs.next()) {
               System.out.println(rs.getInt("quiz_id") + ". " + rs.getString("quiz_name"));
           }
           int quizId = Integer.parseInt(System.console().readLine());

           // Answer questions
           int score = 0;
           stmt = con.createStatement();
           rs = stmt.executeQuery("SELECT * FROM questions WHERE quiz_id=" + quizId);
           while (rs.next()) {
               // Display the current question and possible answers
               int questionId = rs.getInt("table_id");
               String questionText = rs.getString("question");
               System.out.println(questionText);
               stmt = con.createStatement();
               ResultSet answers = stmt.executeQuery("SELECT * FROM answers WHERE table_id=" + questionId);
               while (answers.next()) {
                   int answerId = answers.getInt("ans_id");
                   String answerText = answers.getString("answers");
                   System.out.println(answerId + ". " + answerText);
               }
   
               // Prompt the user to select an answer
               int attempts = 0;
               boolean answered = false;
               while (!answered && attempts < 3) {
                   System.out.print("Enter your answer: ");
                   int answerChoice = Integer.parseInt(System.console().readLine());
   
                   // Check if the answer is correct
                   stmt = con.createStatement();
                   rs = stmt.executeQuery("SELECT * FROM answers WHERE ans_id=" + answerChoice + " AND correct=true");
                   if (rs.next()) {
                       System.out.println("Correct!");
                       score++;
                       answered = true;
                   } else {
                       System.out.println("Incorrect. Please try again.");
                       attempts++;
                   }
               }
           }
   
           // Display the final score and prompt the user to retry or return to the main menu
           System.out.println("Quiz complete! Your score is: " + score);
           System.out.println("Please select an option:");
           System.out.println("1. Retry quiz");
           System.out.println("2. Main menu");
   
           int choice = Integer.parseInt(System.console().readLine());
           switch (choice) {
               case 1:
                   // Restart the quiz
                   break;
               case 2:
                   // Return to the main menu
                   break;
               default:
                   System.out.println("Invalid choice. Returning to main menu.");
                   break;
           }
   
           // Close the database resources
           try{
           rs.close();
           stmt.close();
           con.close();
         }catch (SQLException se) {
           // Handle JDBC errors
           se.printStackTrace();
       } catch (Exception e) {
           // Handle other errors
           e.printStackTrace();
       }
    }
          finally{
           // Release JDBC resources in reverse order
           try {
               if (rs != null) {
                   rs.close();
               }
           } catch (SQLException se2) {
           }
           try {
               if (stmt != null) {
                   stmt.close();
               }
           } catch (SQLException se2) {
           }
           try {
               if (con != null) {
                   con.close();
               }


        }
           } catch (SQLException se) {
               se.printStackTrace();
           }catch (Exception e) {
            System.out.println(e);
        }
       }
   }

