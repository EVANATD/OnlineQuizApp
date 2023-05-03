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
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/a", "root", "1234");
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
