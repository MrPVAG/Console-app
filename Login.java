package Interaction;

import java.util.Scanner;

public class Login {
    int option;
    public void loginMethod(){
        System.out.println("***Welcome Super Admin***");
        System.out.println("Enter Username and Password");
        Scanner sc= new Scanner(System.in);
        System.out.print("Username:");
        String username=sc.nextLine();
        System.out.print("Password:");
        String password=sc.nextLine();
       
        if(username.equals("admin") && password.equals("aDmI3$")){
            MainApplication obj = new MainApplication();
            obj.adminOptions();
        }
        
        else{
            System.out.println("Username or Password is incorrect.Press 1 to try again or press any key to exit.");
            option=sc.nextInt();
            if(option==1)
                loginMethod();
            else
                System.out.println("Bye Bye");
        }
        }
    
    public static void main(String[] args) {
        Login obj = new Login();
        obj.loginMethod();
    }
    
}
