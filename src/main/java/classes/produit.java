/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Connection.SingletonConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 *
 * @author hp
 */
public class produit {
    public int idP;
    public String codeB;
    public String nom;
    public double prixUnit;
    public String description;
    public String dateExp;
    public String dateDe;
    //on definit un constructeur vide 
    public produit(){
    
    }
    //on definit un constructeur full params

    public produit(int idP,String codeB, String nom, double prixUnit, String description, String dateExp, String dateDe) {
        this.idP=idP;
        this.codeB = codeB;
        this.nom = nom;
        this.prixUnit = prixUnit;
        this.description = description;
        this.dateExp = dateExp;
        this.dateDe = dateDe;
    }
    //on definit les getters et setters

    public String getCodeB() {
        return codeB;
    }

    public void setCodeB(String codeB) {
        this.codeB = codeB;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrixUnit() {
        return prixUnit;
    }

    public void setPrixUnit(double prixUnit) {
        this.prixUnit = prixUnit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateExp() {
        return dateExp;
    }

    public void setDateExp(String dateExp) {
        this.dateExp = dateExp;
    }

    public String getDateDe() {
        return dateDe;
    }

    public void setDateDe(String dateDe) {
        this.dateDe = dateDe;
    }
//on definit la methode toString 
    @Override
    public String toString() {
        return "produit{" + "codeB=" + codeB + ", nom=" + nom + ", prixUnit=" + prixUnit + ", description=" + description + ", dateExp=" + dateExp + ", dateDe=" + dateDe + '}';
    }
    public static boolean ajouterProduit(produit p) throws SQLException{
    boolean res=false;
    int r=0;
    try{
    //Etape1: on etablit la connexion
   java.sql.Connection connection=SingletonConnection.getConnection();
    //Etape2: Prepared Statement
    String SQL="Insert into  projetintegre.produit values(null,?,?,?,?,?,?)";
    PreparedStatement pstmt=connection.prepareStatement(SQL);
    
			
    //Etape3:setting attributes
     pstmt.setString(1, p.nom);
     pstmt.setString(2, p.codeB); 
     pstmt.setString(3, p.description);
     pstmt.setString(4, p.dateExp);
     pstmt.setString(5, p.dateDe);
     pstmt.setDouble(6, p.prixUnit);
     
    //Etape4:ExecuteUpdate
     r = pstmt.executeUpdate();
    if(r==1){
    res=true;
    }
    pstmt.close();
			//connection.close();
    
    
    }
    catch(Exception e){
        e.printStackTrace();
    System.out.println(e.getMessage());
    
    }return res;
}
    public static LinkedList<produit> getAllPrd(){
            
		LinkedList<produit> listeP = new LinkedList<produit>();
		String req = "SELECT * FROM produit ";
                commande c = null;
		try{
                    java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement();
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			
		
			while (rs.next()) {
                               int idProd = rs.getInt(1);
                               String NomProd = rs.getString(2);
                                String CodeB = rs.getString(3);
                                
                                 String Des = rs.getString(4);
                                  String DateExp = rs.getString(5);
                                   String DateDent = rs.getString(6);
                                     int PrixU = rs.getInt(7);
                             
                               produit p = new produit(idProd,CodeB,NomProd,PrixU,Des,DateExp,DateDent);
			     
                               listeP.add(p);       
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
    
    
     public static boolean supprimerProduit(int id){
		boolean res = false;
		int resultat1 = 0;
		String req = "DELETE " +
				     "FROM produit " +
				     "WHERE idProduit ="+id ;
		try{
			java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			resultat1 = stmt.executeUpdate(req);
			if (resultat1 == 1) 
                        {res = true;
                        }
                        
			//conn.close();
			stmt.close();
		}
		catch (SQLException e){
                    System.out.println("Problème de suppression du produit ");
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		
		return res;
	} 
     public static int nbrPrd (){
        LinkedList<produit> lc =getAllPrd();
        
        return lc.size();
    }
     
      public static LinkedList<DES> getInfoPrd(String NomProd){
            
		LinkedList<DES> listeP = new LinkedList<DES>();
		String req = "SELECT nomProd,Description FROM produit where nomProd like'% "+NomProd+"%'";
                commande c = null;
		try{
                    java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement();
 			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(req);
			
		
			while (rs.next()) {
                            
                               String nomProd = rs.getString(1);
                                String Des = rs.getString(2);
                              
                                
                                 
                             
                               DES p = new DES(nomProd,Des );
			     
                               listeP.add(p);       
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
    
   
    }




