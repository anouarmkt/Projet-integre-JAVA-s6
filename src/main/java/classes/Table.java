/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Connection.SingletonConnection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author hp
 */
public class Table {
    
    
      public static DefaultTableModel generateTableModel(ResultSet rs) throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();
        //Columns names
        Vector<String> colNames = new Vector<String>();
        int colCount = metaData.getColumnCount();
        for (int i = 1; i <= colCount; i++) {
            colNames.add(metaData.getColumnName(i));
        }

        //Data
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()){
            Vector<Object> row = new Vector<>();
            for (int i = 1; i <= colCount; i++) {
                row.add(rs.getObject(i));
            }
            data.add(row);
        }

        return new DefaultTableModel(data,colNames);
    }
   
    
}
