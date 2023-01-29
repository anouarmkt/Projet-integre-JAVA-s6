/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author hp
 */
public class SingletonConnection {
     private static Connection connection;
    
    static{
        try{
              
              
              Class.forName("com.mysql.jdbc.Driver");  
               
                connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projetintegre","root","Anouars6");
            System.out.println("ok");
            
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
    return connection;}
    
    
}
