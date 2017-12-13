/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.farhan.apache.poi;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author thirthfamous Code ini adalah cara untuk men generate record ke excel.
 */
public class GenerateExcel {

    public static void main(String[] args) throws Exception {
        Connection con = MyConnection.getConnection();
        Statement stmt = null;
        //create blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet("Mahasiswa Info");
        //Create row object
        XSSFRow row;
        Map<String, Object[]> empinfo = new TreeMap<>();

        stmt = con.createStatement();

        String sql = "SELECT nrp, nama, alamat FROM mahasiswa";
        ResultSet rs = stmt.executeQuery(sql);
        
        empinfo.put("1", new Object[]{"NRP", "NAMA", "ALAMAT"});
        int keys = 1;
        while (rs.next()) {
                //Retrieve by column name
                keys++;
                String nrp = rs.getString("nrp");
                String nama = rs.getString("nama");
                String alamat = rs.getString("alamat");
                
                empinfo.put(String.valueOf(keys) , new Object[]{nrp, nama, alamat});
            }

        Set<String> keyid = empinfo.keySet();
        int rowid = 0;
        for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectarr = empinfo.get(key);
            int cellid = 0;

            for (Object obj : objectarr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String) obj);
            }
        }

        //create file system using specific name
        FileOutputStream out = new FileOutputStream(new File("mahasiswa.xlsx"));

        //write operation workbook using file out object 
        workbook.write(out);
        out.close();
        System.out.println("Created");
    }

}
