/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farhan.apache.poi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

/**
 *
 * @author thirthfamous
 */
public class Database {

     // JDBC driver name and database URL
   public static void main(String[] args) {
   Connection con = MyConnection.getConnection();
   Statement stmt = null;
   try{
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
      stmt = con.createStatement();
      
      String sql = "SELECT nrp, nama, alamat FROM mahasiswa";
      ResultSet rs = stmt.executeQuery(sql);
      
      //STEP 5: Extract data from result set
      while(rs.next()){
         //Retrieve by column name
         String nrp  = rs.getString("nrp");
         String nama = rs.getString("nama");
         String alamat = rs.getString("alamat");

         //Display values
         System.out.print("NRP: " + nrp);
         System.out.print(", Nama: " + nama);
         System.out.println(", Alamat: " + alamat);
      }
      rs.close();
      con.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }
   }//end try
}