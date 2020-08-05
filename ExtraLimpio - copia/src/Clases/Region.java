/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import ConectorBD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;


/**
 * 
 * @author Leica
 */
public class Region {
    
    private int id_region;
    private String nombre;

    public int getId_region() {
        return id_region;
    }

    public void setId_region(int id_region) {
        this.id_region = id_region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String toString(){
        return this.nombre;
    }
    
    public Vector<Region> mostrarRegiones(){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConnection();
        
        Vector<Region> regiones = new Vector<Region>();
        Region dat = null;
        
        try{
            String sql = "SELECT region_id, region_nombre from region"; 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            dat = new Region();
            dat.setId_region(0);
            dat.setNombre("Selecione Region");
            regiones.add(dat);
            
            while (rs.next()){
                
            dat = new Region();
            dat.setId_region(rs.getInt("REGION_ID"));
            dat.setNombre(rs.getString("REGION_NOMBRE"));
            regiones.add(dat);
                
            }
            rs.close();
        } catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
       return regiones;
    }
}
