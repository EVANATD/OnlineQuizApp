import java.sql.*;
import java.util.Scanner;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;

public class App {
    public static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static PreparedStatement ps;
    private static boolean isDone;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/QUIZ_APP", "root", "elizabeth@2001");
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
                selectCategory();
            } else {
                System.out.println("\nInvalid username or password, please try again.");
                signUp();
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
               // selectQuiz();
                selectCategory();
            }
        } catch (Exception e) {
            System.out.println(e);
}
    }
    static void mcq(int option){
        

    }
    //public void
     private static void selectCategory() {
        Scanner input = new Scanner(System.in);
int choice1;
             try {
        //         conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //stmt = con.createStatement();
                
        /// Retrieve list of categories from the database
             ResultSet rs1 = stmt.executeQuery("SELECT * FROM categories");
                
        //         // Display categories to the user
          System.out.println("Select a category:");
        //  while (rs.next()) {
        //    int categoryId = rs.getInt("cat_id");
        //    String categoryName = rs.getString("cat_name");

        //      System.out.println(categoryId + ". " + categoryName);
        //  }
             //Scanner input = new Scanner(System.in);
            System.out.println("Select a quiz type:\n1. Multiple Choice Questions\n2. True or False\n3. One Word");
            int quizType = input.nextInt();
            input.nextLine();
            // System.out.println("Select a subject:\n1. History\n2. Geography\n3. Maths");
            // int subject = input.nextInt();
            // input.nextLine();
            String sql = "SELECT * FROM History WHERE cat_id = ? ";
            PreparedStatement stmt = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, quizType);
            //stmt.setInt(2, );
            ResultSet rs = stmt.executeQuery();
            int count = 1;
            while (rs.next()) {
                System.out.println(count + ". " + rs.getString("Hisq"));
                count++;
            }
            
            // Prompting the user to answer the questions
            rs.beforeFirst();
            count = 1;
            while (rs.next()) {
                System.out.print(count + ". ");
                switch (quizType) {
                    case 1:
                        int option=Integer.parseInt(input.nextLine());
                        mcq(option);
                        break;
                    case 2:
                        TorF(rs);
                        break;
                        case 3:
                        OneWord(rs);
                        break;
                    default:
                        System.out.println("Invalid quiz type.");
                        break;
                }
         }

//              choice1 = input.nextInt();
//             input.nextLine();
// switch (choice1) {
//                 case 1:
//                 mcq();
//                 case 2:
//                 TorF();
//            case 3:
//            OneWord();
//            default:
//                     System.out.println("\nInvalid choice, please try again.");
//                     break;
// }

        // }
                
        //         // Prompt user to enter a category
         //  Scanner scanner = new Scanner(System.in);
        // int selectedCategoryId = scanner.nextInt();
                
        //         // Retrieve list of quizzes for the selected category
          // rs = stmt.executeQuery("SELECT * FROM quizzes WHERE cat_id = " + selectedCategoryId);
              
    


}catch (Exception e) {
    System.out.println(e);
}
}
private static void selectQuiz()  {
    try{
    ResultSet rs1 = stmt.executeQuery("SELECT * FROM quizzes ");
//     // Display quizzes in selected category
  System.out.println("Select a quiz:");
  while (rs1.next()) {
    int QuizId = rs1.getInt("quiz_id");
    String QuizName = rs1.getString("quiz_name");
      System.out.println(QuizId + ". " + QuizName);
  }
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

//     return quizId;
    }catch (Exception e) {
        System.out.println(e);
    }
}
private static void mcq(ResultSet rs) {
    // System.out.println("\n Select from the below topics:");
    // System.out.println("\n1.History");
    // System.out.println("\n1.Geography");
    // System.out.println("\n1.Maths");
    // Scanner input = new Scanner(System.in);
    // int choice1;
    // choice1 = input.nextInt();
    //         input.nextLine();
    // switch (choice1) {
    //     case 1:
    //     hist();
    //     case 2:
    //     geo();
    //     case 3:
    //     math();
    //     default:
    //     System.out.println("\nInvalid choice, please try again.");
    //     break;


    // System.out.println(rs.getString("option1"));
    //     System.out.println(rs.getString("option2"));
    //     System.out.println(rs.getString("option3"));
    //     System.out.println(rs.getString("option4"));
    

}
private static void TorF(ResultSet rs) {
    System.out.println("True or False?");
    //   System.out.println("\n Select from the below topics:");
    //   System.out.println("\n1.History");
    //   System.out.println("\n1.Geography");
    //   System.out.println("\n1.Maths");
    //   Scanner input = new Scanner(System.in);
    //   int choice1;
    //   choice1 = input.nextInt();
    //           input.nextLine();
    //   switch (choice1) {
    //       case 1:
    //       hist();
    //       case 2:
    //       geo();
    //       case 3:
    //       math();
    //       default:
    //       System.out.println("\nInvalid choice, please try again.");
    //       break;

    //   }


}
private static void OneWord(ResultSet rs) {
    System.out.println("Enter your answer:");
    // System.out.println("\n Select from the below topics:");
    // System.out.println("\n1.History");
    // System.out.println("\n1.Geography");
    // System.out.println("\n1.Maths");
    // Scanner input = new Scanner(System.in);
    // int choice1;
    // choice1 = input.nextInt();
    //         input.nextLine();
    // switch (choice1) {
    //     case 1:
    //     hist();
    //     case 2:
    //     geo();
    //     case 3:
    //     math();
    //     default:
    //     System.out.println("\nInvalid choice, please try again.");
    //     break;
    // }

}
private static void hist(){


}

private static void geo(){
    
}
private static void math(){
    
}
}
