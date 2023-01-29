/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Connection.SingletonConnection;
import Menu.images.Authentification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;

/**
 *
 * @author hp
 */
public class commande {
    
    public  int id,idProd,qty;
    public String nomClient,nomProduit,Date;

    public commande(int id,int idProd,String nomProduit,String nomClient,int qty,String Date) {
        this.id = id;
        this.idProd = idProd;
        this.qty = qty;
        this.nomClient = nomClient;
         this.nomProduit= nomProduit;
                this.Date = Date;
    }

    public commande() {
    }
    
    public static boolean effectuerCom(commande c){
       
		
                boolean res = false;
               
                int etat = 0;
		// connect to database 
		try{
			java.sql.Connection conn = SingletonConnection.getConnection();
                         String req = "INSERT INTO projetintegre.commande VALUES(null,'"+c.idProd+"','"+c.nomProduit+"','"+c.nomClient+"','"+c.qty+"','"+c.Date+"')";
			 /*PreparedStatement pstmt = conn.prepareStatement(req);
                         pstmt.setInt(1, c.idProd);
                         pstmt.setString(2,c.nomProduit);
                         pstmt.setString(3,c.nomClient); 
                         pstmt.setInt(4, c.qty); 
                         pstmt.setString(5,c.Date);*/
                         Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			etat = stmt.executeUpdate(req);
                        
                        
			 //etat = pstmt.executeUpdate(req);
			if(etat == 1){
                            res = true; 
                        }	
			stmt.close();
		//conn.close();
		}
		catch (SQLException e){
			System.out.println("*** Probleme d'ajout du nv commande ***");
			//System.out.println(req);
                        System.out.println("SQLException: " + e.getMessage());
                        System.out.println("SQLState: "     + e.getSQLState());
                        System.out.println("VendorError: "  + e.getErrorCode());
		}
		return res;
    }
      
        
    public static LinkedList<commande> getAllCommandes(){
            
		LinkedList<commande> listeP = new LinkedList<commande>();
		String req = "SELECT * FROM commande ";
                commande c = null;
		try{
			java.sql.Connection conn = SingletonConnection.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  
                                                ResultSet.CONCUR_UPDATABLE);
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			while (rs.next()) {
                               int id = rs.getInt(1);
                                int idP = rs.getInt(2);
                               String nom = rs.getString(3);
                               String nomP = rs.getString(4);
                               
                                int qy = rs.getInt(5);
                               
                                String date = rs.getString(6);
                               
			       c = new commande(id, idP, nom,nomP,qy,date);
                               listeP.add(c);       
			}
			//conn.close();
			rs.close();
			//
                       stmt.close();
                       
			}catch(SQLException e){
				System.out.println("Problème durant la récupération de la liste "
                                        + "des participants");
			    System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: "     + e.getSQLState());
			    System.out.println("VendorError: "  + e.getErrorCode());
			}
		return listeP; 
	}
     public static int nbrCom (){
        LinkedList<commande> lc =getAllCommandes();
        
        return lc.size();
    }
    }
    

