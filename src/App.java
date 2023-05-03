import java.util.*;
import java.sql.*;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        System.out.println("Press 1 for registration and press 2 for login");
        int s=Integer.parseInt(input.nextLine());
        String username=null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","1234");
            if (s==1){
                username=userregistration();
            }else if(s==2){
                username=login();
            }else{
                System.out.println("Enter a valid input");
            }
            PreparedStatement prepnew= connection.prepareStatement("SELECT user_id FROM users WHERE username=?");
            prepnew.setString(1,username);
            ResultSet rest = prepnew.executeQuery();
            rest.next();
            int user_id=rest.getInt("user_id");
            int cat_id=categories();
            int score=questions(cat_id);
            System.out.println(score);
            
            scoretableupdate(score,user_id);
            display(user_id);
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        
       
    }

    static String userregistration()  {
        String username=null;
        // Scanner input=new Scanner(System.in);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","1234");
            Scanner input=new Scanner(System.in);
            System.out.println("Enter your details to register:");
            System.out.println("Enter Username: ");
            username = input.nextLine();
            String[] userDetails = new String[2];
            userDetails[0] = username;
            PreparedStatement prep4= connection.prepareStatement("SELECT * FROM users WHERE username=?");
            prep4.setString(1,username);
            ResultSet resultSet = prep4.executeQuery();
            if(resultSet.next()) {
                System.out.println("Username already exists!!!");
                System.out.println("Enter another username:  ");
                username = input.nextLine();
                userDetails[0] = username;
                System.out.println("Enter password:");
                String password=input.nextLine();
                userDetails[0]=username;
                userDetails[1]=password;
                PreparedStatement prep5= connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                prep5.setString(1,username);
                prep5.setString(2,password);
                prep5.executeUpdate();
               System.out.println("Success");
            }else{
                System.out.println("Enter password:");
                String password=input.nextLine();
                userDetails[0] =username; 
                userDetails[1]=password;
                PreparedStatement prep6= connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                prep6.setString(1,username);
                prep6.setString(2,password);
                prep6.executeUpdate();
                prep6.executeUpdate();
            }  System.out.println("User " + userDetails[0] + " registered successfully");
            //   System.out.println("Success");
                // String username=userDetails[0];
            } catch(Exception e){
                System.out.println(e);
            }
            return username;
        }

        static String login() {
            Scanner input = new Scanner(System.in);
            String username, password;
            System.out.println("\nPlease enter your username:");
            username = input.nextLine();
            System.out.println("Please enter your password:");
            password = input.nextLine();
            boolean isDone=false;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","1234");
                PreparedStatement prepstmt = connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                prepstmt.setString(1,username);
                prepstmt.setString(2,password);
                ResultSet rsnew=prepstmt.executeQuery();
                rsnew=prepstmt.executeQuery();
                if (rsnew.next()) {
                    System.out.println("\nWelcome, " + username + "!");
                    isDone = true;
                } else {
                    System.out.println("\nInvalid username or password, please try again.");
                    userregistration();
                }
            }catch (Exception e) {
                System.out.println(e);
            }
            return username;
        }
    static int categories(){
        Scanner input=new Scanner(System.in);
        System.out.println("Enter which category you would be interested to answer questions in : \n1.TRUE or FALSE \n2.MCQ \n3.One Word ");
        int cat_id=Integer.parseInt(input.nextLine());
        return cat_id;
    }
    static int questions(int cat_id){
        Scanner input=new Scanner(System.in);
        // String[][] qna=new String[5][2];
        String answer=null;
        int His_id=0;
        int countofcorrectanswers=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","1234");
            PreparedStatement prepstatement = connection.prepareStatement("SELECT His_id,hisq FROM History WHERE cat_id=?");
            prepstatement.setInt(1,cat_id);
            ResultSet rs=prepstatement.executeQuery();
            int i=0;
            while(rs.next() && i<5){
                String question=rs.getString("hisq");
                System.out.println(question);
                answer=input.nextLine();
                His_id=rs.getInt("His_id");
                PreparedStatement prepstatement1 = connection.prepareStatement("SELECT answer FROM Answer WHERE His_id = ?");
            prepstatement1.setInt(1,His_id);
            ResultSet rs1=prepstatement1.executeQuery();
            rs1.next();
            String storedanswer=rs1.getString("answer");
            
                if(answer.equalsIgnoreCase(storedanswer)){
                    System.out.println("The answer is correct");
                    countofcorrectanswers++;
                }else{
                    System.out.println("The answer is incorrect");
                }
                
        }}catch(Exception e){
            System.out.println(e);
        }
        
        return countofcorrectanswers;
    }

    static void scoretableupdate(int score,int user_id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","1234");
            PreparedStatement prep = connection.prepareStatement("INSERT INTO scores (user_id,score) VALUES (?, ?)");
            prep.setInt(1,user_id);
            prep.setInt(2,score);
            prep.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }   
    }

    static void display(int user_id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","1234");
            PreparedStatement prep2 = connection.prepareStatement("SELECT username FROM users WHERE user_id = ?");
            prep2.setInt(1,user_id);
            ResultSet rs2=prep2.executeQuery();
            rs2.next();
            String username=rs2.getString("username");
            PreparedStatement prep3 = connection.prepareStatement("SELECT score FROM scores WHERE user_id = ?");
            prep3.setInt(1,user_id);
            ResultSet rs3=prep3.executeQuery();
            rs3.next();
            int score=rs3.getInt("score");
            System.out.println(username+" : "+score);


        }catch(Exception e){
            System.out.println(e);
        }
    }

    
    
}