package Interaction;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;
public class User1Login {
    int option=0, c=0, msg_id=0,fileNumber=0;;
    Scanner sc = new Scanner(System.in);
    Connection connection=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    String msg;
    java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    
    int n;

    public User1Login() {
        connection=DatabaseConnectivity.db_Connection();
    }
    
    public void user1Role(){
        
        System.out.println("To performed assigned role to you. Press");
        System.out.println("1. To view the transacted data between the users");
        System.out.println("2. To edit the transacted data between the users");
        System.out.println("3. To communicate with user2");
        System.out.println("4. Exit");
        c=sc.nextInt();
        mainMethod();
    }
    public void showTransData(){
        System.out.println("***Data Displayed***");
        String viewquery="select * from transacted_details";
        try {
            pst=connection.prepareStatement(viewquery);
            rs=pst.executeQuery();
            System.out.println("___________________________________________________");
            System.out.println("MsgID\tDate\t\tSender\tReceiver  Message");
            System.out.println("---------------------------------------------------");
            while(rs.next()){
                System.out.println(rs.getString("MsgID")+"\t"+rs.getString("Date")+"\t"+
                        rs.getString("Sender")+"\t"+rs.getString("Receiver")+"\t  "+rs.getString("Message"));
            }
            System.out.println("___________________________________________________");
        } catch (Exception e) {System.out.println(e);}        
    }
    public void user1LoginMethod(){
        System.out.println("***Welcome User1***");
        System.out.println("Enter Username and Password");
        Scanner sc= new Scanner(System.in);
        System.out.print("Username:");
        String username=sc.nextLine();
        System.out.print("Password:");
        String password=sc.nextLine();
        
        if(username.equals("user1") && password.equals("user1")){
            user1Role();
        }
        else{
            System.out.println("Username or Password is incorrect. Press 1 to try again or press any key to exit.");
            option=sc.nextInt();
            if(option==1){
                user1LoginMethod();     
            }
        }
    }
    public void mainMethod(){
        switch(c){
            case 1:
                showTransData();
                user1Role();
            case 2:
                showTransData();
                System.out.print("Enter MsgID to edit the Message: ");
                msg_id=sc.nextInt();
                System.out.print("Enter new Message: ");
                msg=sc.next();
                msg+=sc.nextLine();
                String updatequery="update transacted_details set Message=? where MsgID=?";
                try {
                    pst=connection.prepareStatement(updatequery);
                    pst.setString(1, msg);
                    pst.setInt(2, msg_id);
                    pst.execute();
                    System.out.println("\tData Updated.");
                    showTransData();
                    user1Role();
                } catch (Exception e) {System.out.println(e);}
            case 3:
                try {
                
                } catch (Exception e) {System.out.println(e);}
                
                System.out.print("Enter Message to send user2: ");
                
                msg=sc.next();
                msg+=sc.nextLine();
                String imsgquery="insert into transacted_details(Date,Sender,Receiver,Message) values(?,?,?,?)";
                try {
                    pst=connection.prepareStatement(imsgquery);
                    pst.setDate(1, sqlDate);
                    pst.setString(2, "user1");
                    pst.setString(3, "user2");
                    pst.setString(4, msg);
                    pst.execute();
                    System.out.println("Message Sent.");
                    
                    fileNumber = fileNumber+1;
                    FileWriter fw = new FileWriter("Person" + fileNumber + ".txt");
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println("Date: "+java.time.LocalDate.now());
                    pw.println("Sender: user1");
                    pw.println("Receiver: user2");
                    pw.println("Message: "+msg);
                    pw.close();
                    System.out.println("File Created");
                    showTransData();
                    user1Role();
                } catch (Exception e) {System.out.println(e);}
            case 4:
                System.exit(0);
                
            default:
                System.out.println("Enter a valid option.");
                user1Role();
                    
                }
        } 
    public static void main(String[] args) throws Exception{
        User1Login obj = new User1Login();
        obj.user1LoginMethod();
    }

    private String String(LocalDate now) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    private String String(Date sqlDate) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

