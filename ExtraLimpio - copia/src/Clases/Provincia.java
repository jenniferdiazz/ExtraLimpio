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
public class Provincia {
    
    private int id_provincia;
    private String nombre;
    private int provincia_region_id;

    /**
     * Get the value of provincia_region_id
     *
     * @return the value of provincia_region_id
     */
    public int getProvincia_region_id() {
        return provincia_region_id;
    }

    /**
     * Set the value of provincia_region_id
     *
     * @param provincia_region_id new value of provincia_region_id
     */
    public void setProvincia_region_id(int provincia_region_id) {
        this.provincia_region_id = provincia_region_id;
    }

    public int getId_provincia() {
        return id_provincia;
    }

    public void setId_provincia(int id_region) {
        this.id_provincia = id_region;
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
    /**
     * Este metodo rescata la lista de provincias asociadas al parametro que recibe
     * @param idRegion id de la region seleccionada
     * @return 
     */
    public Vector<Provincia> mostrarProvincias(Integer idRegion){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConnection();
        
        Vector<Provincia> provincias = new Vector<Provincia>();
        Provincia dat = null;
        
        try{
            String sql = "SELECT * from provincia where provincia_region_id = " + idRegion; 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            dat = new Provincia();
            dat.setId_provincia(0);
            dat.setNombre("Selecione Provincia");
            dat.setProvincia_region_id(0);
            provincias.add(dat);
            
            while (rs.next()){
                
            dat = new Provincia();
            dat.setId_provincia(rs.getInt("PROVINCIA_ID"));
            dat.setNombre(rs.getString("PROVINCIA_NOMBRE"));
            dat.setProvincia_region_id(rs.getInt("PROVINCIA_REGION_ID"));
            provincias.add(dat);
                
            }
            rs.close();
        } catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
       return provincias;
    }
}
