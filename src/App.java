import java.util.*;
import java.sql.*;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner input=new Scanner(System.in);
        System.out.println("\033[34mPress 1 for registration \npress 2 for login\033[0m");
        int s=Integer.parseInt(input.nextLine());
        String username=null;
        int user_id=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","elizabeth@2001");
            if (s==1){
                username=userregistration();
            }else if(s==2){
                username=login();
            }else{
                System.out.println("\033[31mEnter a valid input\033[0m");
            }
            
            int n;
            do{
                PreparedStatement prepnew= connection.prepareStatement("SELECT user_id FROM users WHERE username=?");
            prepnew.setString(1,username);
            ResultSet rest = prepnew.executeQuery();
            rest.next();
            user_id=rest.getInt("user_id");
            int cat_id=categories();
            int score=questions(cat_id);
           // System.out.println("Score:"+score);

            
            System.out.println("==================================");
System.out.println("|        Username      |  Score   |");
System.out.println("==================================");
System.out.printf("| %20s | %6d |\n", username, score);
System.out.println("==================================");



            
            
            scoretableupdate(score,user_id);
            
            System.out.println("Do you wish to attempt again \n1.Yes \n2.No");
            n=Integer.parseInt(input.nextLine());
            
        }while(n!=2);
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
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","elizabeth@2001");
            Scanner input=new Scanner(System.in);
            System.out.println("\033[34mEnter your details to register:\033[0m");
            System.out.println("\033[35mEnter Username: \033[0m");
            username = input.nextLine();
            String[] userDetails = new String[2];
            userDetails[0] = username;
            PreparedStatement prep4= connection.prepareStatement("SELECT * FROM users WHERE username=?");
            prep4.setString(1,username);
            ResultSet resultSet = prep4.executeQuery();
            if(resultSet.next()) {
                System.out.println("\033[31mUsername already exists!!!\033[0m");
                System.out.println("\033[34mEnter another username:  \033[0m");
                username = input.nextLine();
                userDetails[0] = username;
                System.out.println("\033[36mEnter password:\033[0m");
                String password=input.nextLine();
                userDetails[0]=username;
                userDetails[1]=password;
                PreparedStatement prep5= connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                prep5.setString(1,username);
                prep5.setString(2,password);
                prep5.executeUpdate();
               System.out.println("\033[32mSuccess\033[0m");
            }else{
                System.out.println("\033[36mEnter password:\033[0m");
                String password=input.nextLine();
                userDetails[0] =username; 
                userDetails[1]=password;
                PreparedStatement prep6= connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)");
                prep6.setString(1,username);
                prep6.setString(2,password);
                prep6.executeUpdate();
                prep6.executeUpdate();
            }  System.out.println("\033[32mUser \033[0m" + userDetails[0] + "\033[32mregistered successfully\033[0m");
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
            System.out.println("\033[35mPlease enter your username:\033[0m");
            
            username = input.nextLine();
            System.out.println("\033[36mPlease enter your password:\033[0m");
            password = input.nextLine();
            boolean isDone=false;
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","elizabeth@2001");
                PreparedStatement prepstmt = connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
                prepstmt.setString(1,username);
                prepstmt.setString(2,password);
                ResultSet rsnew=prepstmt.executeQuery();
                rsnew=prepstmt.executeQuery();
                if (rsnew.next()) {
                    System.out.println("\033[32mWelcome,\033[0m " + username + "!");
                    isDone = true;
                } else {
                    System.out.println("\033[31mInvalid username or password, please try again.\033[0m");
                    userregistration();
                }
            }catch (Exception e) {
                System.out.println(e);
            }
            return username;
        }
    static int categories(){
        Scanner input=new Scanner(System.in);
        System.out.println("\033[34mEnter which category you would be interested to answer questions in : \n1.TRUE or FALSE \n2.MCQ \n3.One Word\033[0m ");
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
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","elizabeth@2001");
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
                    System.out.println("\033[32mThe answer is correct\033[0m");
                    countofcorrectanswers++;
                }else{
                    System.out.println("\033[31mThe answer is incorrect\033[0m");
                }
                
        }}catch(Exception e){
            System.out.println(e);
        }
        
        return countofcorrectanswers;
    }

    static void scoretableupdate(int score,int user_id){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","elizabeth@2001");
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
            Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/Quiz_App","root","elizabeth@2001");
            PreparedStatement prep2 = connection.prepareStatement("SELECT username FROM users WHERE user_id = ?");
            prep2.setInt(1,user_id);
            ResultSet rs2=prep2.executeQuery();
            rs2.next();
            String username=rs2.getString("username");
            PreparedStatement prep3 = connection.prepareStatement("SELECT MAX(score) AS max_score FROM scores WHERE user_id = ?");
            prep3.setInt(1,user_id);
            ResultSet rs3=prep3.executeQuery();
            rs3.next();
            int score=rs3.getInt("max_score");
            
          
         System.out.println("===================================");
         System.out.println("|         Highest Score           |");
          
           System.out.println("==================================");
           System.out.println("|        Username      |   Score   |");
           System.out.println("==================================");
           System.out.printf("| %20s | %6d |\n", username, score);
           System.out.println("==================================");

        }catch(Exception e){
            System.out.println(e);
        }
    }

    
    
}