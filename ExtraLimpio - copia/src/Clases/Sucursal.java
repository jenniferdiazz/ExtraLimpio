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
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Nicolas
 */
public class Sucursal {
    private int idSucursal ;
    private String nombre_sucursal;
    private int idComuna;
    private String direccion;
    private String correo;

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    
    public String getNombre_sucursal() {
        return nombre_sucursal;
    }
    
    public void setNombre_sucursal(String nombre_sucursal) {
        this.nombre_sucursal = nombre_sucursal;
    }
    
    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public String toString(){
        return this.nombre_sucursal;
    }
    
    public static Vector<Sucursal> mostrarSucursales(){
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConnection();
        
        Vector<Sucursal> sucursales = new Vector<Sucursal>();
        Sucursal dat = null;
        
        try{
            String sql = "SELECT * from sucursal"; 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
           
            dat = new Sucursal();
            dat.setIdSucursal(0);
            dat.setNombre_sucursal("Selecione Sucursal");
            dat.setIdComuna(0);
            dat.setDireccion("");
            dat.setCorreo("");
            sucursales.add(dat);
            
            while (rs.next()){
                
            dat = new Sucursal();
            dat.setIdSucursal(rs.getInt("ID_SUCURSAL"));
            dat.setNombre_sucursal(rs.getString("NOMBRE_SUCURSAL"));
            dat.setIdComuna(rs.getInt("FK_COMUNA"));
            dat.setDireccion(rs.getString("DIRECCION_SUCURSAL"));
            dat.setCorreo(rs.getString("CORREO_SUCURSAL"));
            sucursales.add(dat);
                
            }
            rs.close();
        } catch(SQLException ex){
            
            JOptionPane.showMessageDialog(null, ex.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        
       return sucursales;
    }
    public static Sucursal primerSucursal(){
        Vector<Sucursal> s = new Vector<Sucursal>();
        s = mostrarSucursales();
        return s.get(0);
    }
    
      public static int idIncrement(){
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion bd = new Conexion();
        try{
            ps = bd.getConnection().prepareStatement("SELECT MAX(id_sucursal) from sucursal");
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt(1) + 1 ;
                
            }
                       
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            
            try{
                ps.close();
                rs.close();
                bd.desconectar();
            } catch (Exception e){
                
            }
        }
        return id;
    }
    public static int idIncrementTelSuc(){
         int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion bd = new Conexion();
        try{
            ps = bd.getConnection().prepareStatement("SELECT MAX(ID_telefonos_sucursal) from telefonos_sucursal");
            rs = ps.executeQuery();
            
            while(rs.next()){
                id = rs.getInt(1) + 1 ;
                
            }
                       
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
        }
        finally{
            
            try{
                ps.close();
                rs.close();
                bd.desconectar();
            } catch (Exception e){
                
            }
        }
        return id;
    }
    public static ResultSet getTabla(String Consulta){
        Connection c = Conexion.getConnection();
        Statement st;
        ResultSet datos = null;
       try{
          st = c.createStatement();
          datos = st.executeQuery(Consulta);
       } catch (Exception e ){
           JOptionPane.showMessageDialog(null,"Error :"+e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
       }
        
        return datos;
    }
    public static void Eliminardato(String id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion bd = new Conexion();
        String sql = "DELETE from sucursal where id_sucursal = "+ id ;
        int res = 0; 
        try{
            ps = bd.getConnection().prepareStatement(sql);
            res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null,"Registro Eliminado");
            }
      } catch (SQLException e ){
            JOptionPane.showMessageDialog(null,"Error :"+e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
      } 
        
        }

  
   
    
    
}
