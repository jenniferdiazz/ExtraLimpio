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
 * @author Nicolas
 */
public class Comuna {
    
    private int id_comuna;
    private String nombre;
    private int comuna_provincia_id;

    /**
     * Get the value of provincia_region_id
     *
     * @return the value of provincia_region_id
     */
    public int getComuna_Provincia_id() {
        return comuna_provincia_id;
    }

    /**
     * Set the value of provincia_region_id
     *
     * @param provincia_region_id new value of provincia_region_id
     */
    public void setComuna_Provincia_id(int provincia_region_id) {
        this.comuna_provincia_id = provincia_region_id;
    }

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_region) {
        this.id_comuna = id_region;
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
    
    public Vector<Comuna> mostrarComunas(Integer idProvincia){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConnection();
        
        Vector<Comuna> comunas = new Vector<Comuna>();
        Comuna dat = null;
        
        try{
            String sql = "SELECT * from comuna where comuna_provincia_id = " + idProvincia; 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            dat = new Comuna();
            dat.setId_comuna(0);
            dat.setNombre("Selecione Comuna");
            dat.setComuna_Provincia_id(0);
            comunas.add(dat);
            
            while (rs.next()){
                
            dat = new Comuna();
            dat.setId_comuna(rs.getInt("COMUNA_ID"));
            dat.setNombre(rs.getString("COMUNA_NOMBRE"));
            dat.setComuna_Provincia_id(rs.getInt("COMUNA_PROVINCIA_ID"));
            comunas.add(dat);
                
            }
            rs.close();
        } catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
       return comunas;
    }
}
