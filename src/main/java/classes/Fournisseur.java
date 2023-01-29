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

/**
 *
 * @author hp
 */
public class Fournisseur {
    private int idFrs;
    private String NomFrs,NumTel,listProd;

    public Fournisseur() {
    }

    public Fournisseur(int idFrs, String NomFrs, String NumTel, String listProd) {
        this.idFrs = idFrs;
        this.NomFrs = NomFrs;
        this.NumTel = NumTel;
        this.listProd = listProd;
    }

    public int getIdFrs() {
        return idFrs;
    }

    public String getNomFrs() {
        return NomFrs;
    }

    public String getNumTel() {
        return NumTel;
    }

    public String getListProd() {
        return listProd;
    }

    public void setIdFrs(int idFrs) {
        this.idFrs = idFrs;
    }

    public void setNomFrs(String NomFrs) {
        this.NomFrs = NomFrs;
    }

    public void setNumTel(String NumTel) {
        this.NumTel = NumTel;
    }

    public void setListProd(String listProd) {
        this.listProd = listProd;
    }
    
     public static boolean AjouterFournisseur(Fournisseur f){
            boolean res = false;
            int r = 0;
            try{
                Connection conn =SingletonConnection.getConnection();
                String SQL = "INSERT INTO fournisseur VALUES(null,?,?,?)";
                PreparedStatement pstmt = conn.prepareStatement(SQL);
                pstmt.setString(1, f.NomFrs);
                pstmt.setString(2, f.NumTel);
                
                pstmt.setString(3, f.listProd);
              
                   
                r = pstmt.executeUpdate();
                if(r==1){
                    res = true;
                }
                pstmt.close();
			//conn.close();
            } catch (SQLException e){
                System.out.print("Probleme d'ajout du fournisseur");
                
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
            }
            return res;
    }
      public static boolean supprimerFrs(String id){
		boolean res = false;
		int resultat1 = 0;
                int idF =Integer.parseInt(id);
		String req = "DELETE " +
				     "FROM fournisseur " +
				     "WHERE idFrs= "+ idF+"" ;
		try{
			java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			resultat1 = stmt.executeUpdate(req);
			if (resultat1 != 0) res = true;
			//conn.close();
			stmt.close();
		}
		catch (SQLException e){
                    System.out.println("Problème de suppression du Fournisseur");
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		
		return res;
	} 
      
       public  boolean updateParticpant(Fournisseur f,int id){
            
		boolean res = false;
		int resultat1 = 0;
		String req = "UPDATE fournisseur SET " +
                                "NomFournisseur = '"        + f.NomFrs         + "', " +
                                "NumTel = '"        + f.NumTel        + "', " +
                                "listProd= '"     + f.listProd +   
				"WHERE id = "    + id ;
                
                System.out.println("Req " + req);
		
                try{
			java.sql.Connection conn =  SingletonConnection.getConnection();
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,   ResultSet.CONCUR_UPDATABLE);
			resultat1 = stmt.executeUpdate(req);
			if (resultat1 != 0) res = true;
			//conn.close();
			stmt.close();	
		}
		catch (SQLException e){
		
		    System.out.println("SQLException: " + e.getMessage());
		    System.out.println("SQLState: "     + e.getSQLState());
		    System.out.println("VendorError: "  + e.getErrorCode());
		}
		
		return res;
	}	
    
}
