/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Connection.SingletonConnection;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import panels.GestionComptes;
import static panels.GestionComptes.tblCmpt;
import static panels.SupprimerCompte.tblSupCmpt;



/**
 *
 * @author hp
 */
public class Compte {
     private int id,age,admin;
    private String nom,prenom,tel,adresse,email,motdepasse,login;
   
    
    public Compte(int id,String nom,String prenom,int age,String adresse,String email,String login,int admin,String motdepasse,String tel){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.tel = tel;
        this.adresse = adresse;
        this.email = email;
        this.admin=admin;
         this.login = login;
        this.motdepasse = motdepasse;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getAdmin() {
        return admin;
    }

    public  String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public String getLogin() {
        return login;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public  void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public  void setTel(String tel) {
        this.tel = tel;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public  void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    

     public static  void listerCCompte(java.awt.event.ActionEvent evt) {
       String req = "SELECT * FROM compte ";
                
		try{
                    java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement();
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			
      DefaultTableModel modelDyn = Table.generateTableModel(rs);
            tblCmpt.setModel(modelDyn);
			rs.close();
                        stmt.close();
			
			}catch(SQLException e){
				System.out.println("Problème durant la récupération de la liste "
                                        + "des produits");
			    System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: "     + e.getSQLState());
			    System.out.println("VendorError: "  + e.getErrorCode());
			}
    }
public static void listerSupCompte(java.awt.event.ActionEvent evt) {
       String req = "SELECT * FROM compte ";
                Compte cpt= null;
		try{
                    java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement();
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			
      DefaultTableModel modelDyn = Table.generateTableModel(rs);
            tblSupCmpt.setModel(modelDyn);
			rs.close();
                        stmt.close();
			
			}catch(SQLException e){
				System.out.println("Problème durant la récupération de la liste "
                                        + "des produits");
			    System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: "     + e.getSQLState());
			    System.out.println("VendorError: "  + e.getErrorCode());
			}
    } 
    
     public static boolean enregistrerCompte(Compte c){
            boolean res = false;
            int r = 0;
            try{
                Connection conn =SingletonConnection.getConnection();
                String SQL = "INSERT INTO projetintegre.compte VALUES(null,?,?,?,?,?,0,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(SQL);
                pstmt.setString(1, c.nom);
                pstmt.setString(2, c.prenom);
                pstmt.setInt(3, c.age);
                pstmt.setString(4, c.adresse);
                pstmt.setString(5, c.email);
                pstmt.setString(6, c.login);
             
                pstmt.setString(7, c.motdepasse);
                    pstmt.setString(8, c.tel);
                  
                r = pstmt.executeUpdate();
                if(r==1){
                    res = true;
                }
               
                 pstmt.close();
			//conn.close();

            } catch (SQLException e){
                System.out.print("Probleme d'ajout de compte");
                
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
            }
           
            
            return res;
    }
     
      public static boolean enregistrerCompteAdmin(Compte c){
            boolean res = false;
            int r = 0;
            try{
                Connection conn =SingletonConnection.getConnection();
                String SQL = "INSERT INTO projetintegre.compte VALUES(null,?,?,?,?,?,1,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(SQL);
                pstmt.setString(1, c.nom);
                pstmt.setString(2, c.prenom);
                pstmt.setInt(3, c.age);
                pstmt.setString(4, c.adresse);
                pstmt.setString(5, c.email);
                pstmt.setString(6, c.login);
             
                pstmt.setString(7, c.motdepasse);
                    pstmt.setString(8, c.tel);
                 //  listerCCompte();
                 //  listerSupCompte();
                r = pstmt.executeUpdate();
                if(r==1){
                    res = true;
                }
                pstmt.close();
			//conn.close();
            } catch (SQLException e){
                System.out.print("Probleme d'ajout de compte");
                
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
            }
            return res;
    }

      
      public static int authentification (String login, String password) {
		int result = -1;
		String req = "SELECT Admin FROM compte WHERE Login ='"+login+"' and Motdepass='"+ password +"'";
		// 1er etape connexion
		Connection cnx = SingletonConnection.getConnection();
		// creation du statement 
		try {
			Statement stm = cnx.createStatement();
			ResultSet rs = stm.executeQuery(req);
			rs.next();
			int admin = rs.getInt(1);	
			result = admin;
                        
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
      
      public static LinkedList<Compte> getAllComptes(){
            
		LinkedList<Compte> listeP = new LinkedList<Compte>();
		String req = "SELECT * FROM compte ";
                Compte cpt= null;
		try{
                    java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement();
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			
		
			while (rs.next()) {
                               int idCompte = rs.getInt(1);
                               String Nom = rs.getString(2);
                                String Prenom = rs.getString(3);
                                
                                 int Age = rs.getInt(4);
                                  String Adresse = rs.getString(5);
                                   String Email = rs.getString(6);
                                     int Admin = rs.getInt(7);
                                      String Login = rs.getString(8);
                                       String motdepass = rs.getString(9);
                                        String Tel = rs.getString(10);
                                     
                             
                                cpt = new Compte(idCompte,Nom,Prenom,Age,Adresse,Email,Login,Admin,motdepass,Tel);
                                
                                
                               
			     
                               listeP.add(cpt);       
			}
			//conn.close();
			rs.close();
			stmt.close();
                       
			}catch(SQLException e){
				System.out.println("Problème durant la récupération de la liste "
                                        + "des produits");
			    System.out.println("SQLException: " + e.getMessage());
			    System.out.println("SQLState: "     + e.getSQLState());
			    System.out.println("VendorError: "  + e.getErrorCode());
			}
		return listeP; 
	}
      
        public static boolean supprimerCompte(int id){
		boolean res = false;
		int resultat1 = 0;
		String req = "DELETE " +
				     "FROM compte " +
				     "WHERE idCompte="+id ;
		try{
			java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			resultat1 = stmt.executeUpdate(req);
			if (resultat1 != 0) res = true;
			//conn.close();
                       // listerCCompte();
                        // listerSupCompte();
			stmt.close();
		}
		catch (SQLException e){
                    System.out.println("Problème de suppression du compte ");
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		
		return res;
	} 
    public static int nbrComptes (){
        LinkedList<Compte> lc =getAllComptes();
        
        return lc.size();
    }
}
