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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quiz", "root", "elizabeth@2001");
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
        } catch (Exception e) {
            System.out.println(e);
}
    }
}



// // agnal (category)?
// public void selectCategory() {
//     try {
//         conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//         stmt = conn.createStatement();
        
//         // Retrieve list of categories from the database
//         ResultSet rs = stmt.executeQuery("SELECT * FROM categories");
        
//         // Display categories to the user
//         System.out.println("Select a category:");
//         while (rs.next()) {
//             int categoryId = rs.getInt("category_id");
//             String categoryName = rs.getString("category_name");
//             System.out.println(categoryId + ". " + categoryName);
//         }
        
//         // Prompt user to enter a category
//         Scanner scanner = new Scanner(System.in);
//         int selectedCategoryId = scanner.nextInt();
        
//         // Retrieve list of quizzes for the selected category
//         rs = stmt.executeQuery("SELECT * FROM quizzes WHERE category_id = " + selectedCategoryId);
        
//         // Display quizzes to the user
//         System.out.println("Select a quiz:");
//         while (rs.next()) {
//             int quizId = rs.getInt("quiz_id");
//             String quizName = rs.getString("quiz_name");
//             System.out.println(quizId + ". " + quizName);
//         }
        
//         // Prompt user to enter a quiz
//         int selectedQuizId = scanner.nextInt();
        
//         // Close database connection
//         rs.close();
//         stmt.close();
//         conn.close();
        
//         // Call the function to display the first question of the selected quiz
//         displayQuestion(selectedQuizId);
//     } catch (SQLException e) {
//         e.printStackTrace();
//     }
// }

// private void displayQuestion(int quizId) {
//     // TODO: Implement code to display the first question of the selected quiz
// }
// }

//elizabeth
// private static int selectQuiz(int categoryId) throws SQLException {
//     // Display quizzes in selected category
//     System.out.println("Select a quiz:");
//     String query = "SELECT * FROM quizzes WHERE category_id = ?";
//     PreparedStatement stmt = conn.prepareStatement(query);
//     stmt.setInt(1, categoryId);
//     ResultSet rs = stmt.executeQuery();
//     int i = 1;
//     while (rs.next()) {
//         System.out.println(i + ". " + rs.getString("name"));
//         i++;
//     }
//     // Prompt user for selection
//     int selection = scanner.nextInt();
//     if (selection < 1 || selection > i - 1) {
//         return -1;
//     }
//     rs.absolute(selection);
//     int quizId = rs.getInt("id");
//     rs.close();
//     stmt.close();

//     return quizId;
// }