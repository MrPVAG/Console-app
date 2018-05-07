package Interaction;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class DatabaseConnectivity {
Connection conn=null;
    public static Connection db_Connection(){
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            String url="jdbc:mysql://localhost:3306/interaction_data";
            String uname="root";
            String pass="panosmx5";
            Connection conn=DriverManager.getConnection(url,uname,pass);
            return conn;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }}    
}
