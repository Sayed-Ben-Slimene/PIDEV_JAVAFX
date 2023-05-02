/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sportifyfx.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fekih
 */
public class DBConnection {
    
    private static Connection con;
    
    public void getDBConn(){
        synchronized (""){
            try {
                if (DBConnection.getCon() == null || DBConnection.getCon().isClosed()) {
                    try {
                        String url = "jdbc:mysql://localhost/sportify_pidev";
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        setCon(DriverManager.getConnection(url,"root",""));
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }else {
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

 /**
 *
 * @return the con 
 */public static Connection getCon(){
        return con;
    }
    
 /**
 *
 * @param aCon the con to set
 */ public static void setCon(Connection aCon){
        con = aCon;
    }
   
    public static void closeConnection(){
        try{
            con.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
    
    