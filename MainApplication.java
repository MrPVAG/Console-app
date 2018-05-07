package Interaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Scanner;

public class MainApplication {
    Connection connection=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    int uID,c=0;
    String uname;
    Scanner sc =new Scanner(System.in);

    public MainApplication() {
        connection=DatabaseConnectivity.db_Connection();
    }
    public void adminOptions(){
        System.out.println("\n***Enter any option***");
        System.out.println("1. To create a user");
        System.out.println("2. To delete a user");
        System.out.println("3. To update a user");
        System.out.println("4. To assign a role to user");
        System.out.println("5. To exit");
        mainApplication();
    }
    public void role(){
        System.out.println("***Assign a role***");
        System.out.println("1. To view the transacted data b/w the users");
        System.out.println("2. To edit the transacted data b/w the users");
        System.out.println("3. To delete the transacted data b/w the users");
    }
    public void showusers(){
        String userquery="select * from user_details";
                try {
                    pst=connection.prepareStatement(userquery);
                    rs=pst.executeQuery();
                    System.out.println("_________________________");
                    System.out.println("ID\tUser Name");
                    System.out.println("-------------------------");
                    while(rs.next()){
                        System.out.println(rs.getString("ID")+"\t"+rs.getString("UserName"));
                    }
                    System.out.println("__________________________");
                    rs.close();pst.close();
                } catch (Exception e) {}         
    }
    public void mainApplication(){
        c=sc.nextInt();
        switch(c){
            case 1:
                showusers();
                System.out.print("Enter Username to create user: ");
                uname=sc.next();
                uname+=sc.nextLine();
                String iuserquery="insert into login(UserName) value(?)";
                try {
                    pst=connection.prepareStatement(iuserquery);
                    pst.setString(1, uname);
                    pst.execute();
                    
                } catch (Exception e) {}
                System.out.println("\tUser Created.");
                showusers();
                adminOptions();
              
                
            case 2:
                showusers();
                
                System.out.print("Enter User ID to delete the user: ");
                uID=sc.nextInt();
                String duserquery="delete from user_details where ID=?";
                try {
                    pst=connection.prepareStatement(duserquery);
                    pst.setInt(1, uID);
                    pst.execute();
                    
                } catch (Exception e) {}
                System.out.println("User Deleted");
                showusers();
                adminOptions();
            case 3:
                showusers();
                System.out.print("Enter User ID to edit user name: ");
                uID=sc.nextInt();
                System.out.print("Enter new name: ");
                uname=sc.next();
                uname+=sc.nextLine();
                String updateuser="update user_details set UserName=? where ID=?";
                try {
                    pst=connection.prepareStatement(updateuser);
                    pst.setString(1, uname);
                    pst.setInt(2, uID);
                    pst.execute();
                    System.out.println("User details updated.");
                    showusers();
                    adminOptions();
                } catch (Exception e) {
                }
            case 4:
                showusers();
                System.out.print("Select the user to assign the role.\nEnter User ID: ");
                int id;
                id=sc.nextInt();
                role();
                int role; role=sc.nextInt();
                System.out.println("Role assigned to user.");
                adminOptions();
            case 5:
                System.exit(0);
            default:
                System.out.println("Enter a valid option.");
                adminOptions();
        }    
        }
    private String String(LocalDate now) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    }

    
