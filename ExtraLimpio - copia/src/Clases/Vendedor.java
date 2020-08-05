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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * La clase vendedor esta asociada a la entidad "Vendedor" de la base de datos
 * conteniendo los mismos atributos y realiza las respectivas sentencias sql que
 * impliquen a la tabla vendedor
 *
 * @author Nicolas
 */
public class Vendedor {

    private int ID_vendedor;
    private String rut_vendedor;
    private String nombre_vendedor;
    private String apellido_paterno_vendedor;
    private String apellido_materno_vendedor;
    private int telefono_vendedor;
    private String direccion_vendedor;
    private int Id_sucursal;

    public int getID_vendedor() {
        return ID_vendedor;
    }

    public void setID_vendedor(int ID_vendedor) {
        this.ID_vendedor = ID_vendedor;
    }

    public String getRut_vendedor() {
        return rut_vendedor;
    }

    public void setRut_vendedor(String rut_vendedor) {
        this.rut_vendedor = rut_vendedor;
    }

    public String getNombre_vendedor() {
        return nombre_vendedor;
    }

    public void setNombre_vendedor(String nombre_vendedor) {
        this.nombre_vendedor = nombre_vendedor;
    }

    public String getApellido_paterno_vendedor() {
        return apellido_paterno_vendedor;
    }

    public void setApellido_paterno_vendedor(String apellido_paterno_vendedor) {
        this.apellido_paterno_vendedor = apellido_paterno_vendedor;
    }

    public String getApellido_materno_vendedor() {
        return apellido_materno_vendedor;
    }

    public void setApellido_materno_vendedor(String apellido_materno_vendedor) {
        this.apellido_materno_vendedor = apellido_materno_vendedor;
    }

    public int getTelefono_vendedor() {
        return telefono_vendedor;
    }

    public void setTelefono_vendedor(int telefono_vendedor) {
        this.telefono_vendedor = telefono_vendedor;
    }

    public String getDireccion_vendedor() {
        return direccion_vendedor;
    }

    public void setDireccion_vendedor(String direccion_vendedor) {
        this.direccion_vendedor = direccion_vendedor;
    }

    public int getId_sucursal() {
        return Id_sucursal;
    }

    public void setId_sucursal(int Id_sucursal) {
        this.Id_sucursal = Id_sucursal;
    }

    /**
     * Metodo que rescata el ultimo valor para id_vendedor y devuelve el valor
     * siguente al obtenido
     *
     * @return
     */
    public static int idIncrement() {
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion bd = new Conexion();
        try {
            ps = bd.getConnection().prepareStatement("SELECT MAX(ID_vendedor) from vendedor");
            rs = ps.executeQuery();

            while (rs.next()) {
                id = rs.getInt(1) + 1;

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {

            try {
                ps.close();
                rs.close();
                bd.desconectar();
            } catch (Exception e) {

            }
        }
        return id;

    }

    /**
     * Metodo que realizara el insert en la tabla "Vendedor" recibiendo los
     * respectivos parametro para la realizacion de este.
     *
     * @param id parametro que estara asociado a id_vendedor
     * @param rut parametro que estara asociado a rut_vendedor
     * @param nom parametro que estara asociado a nombre_vendedor
     * @param apepat parametro que estara asociado a apellido_paterno_vendedor
     * @param apemat parametro que estara asociado a apellido_materno_vendedor
     * @param dir parametro que estara asociado a direccion_vendedor
     * @param tel parametro que estara asociado a telefono_vendedor
     * @param idsuc parametro que estara asociado a fk_sucursal
     * @return nos devolvera un valor int que indira la cantidad de operaciones
     * que se realizaron para poder hacer una verificacion una vez terminado el
     * metodo
     */
    public static int agregarVendedor(int id, String rut, String nom, String apepat, String apemat, String dir, int tel, int idsuc) {

        int res = 0;
        try {
            PreparedStatement ps;
            Conexion c = new Conexion();
            Connection reg = c.getConnection();
            ps = reg.prepareStatement("INSERT INTO vendedor(ID_vendedor, rut_vendedor,nombre_vendedor,apellido_paterno_vendedor,apellido_materno_vendedor,direccion_vendedor,telefono_vendedor,fk_sucursal) "
                    + "VALUES (" + id + ", '" + rut + "', '" + nom + "', '" + apepat + "', '" + apemat + "', '" + dir + "'," + tel + "," + idsuc + ")");
            res = ps.executeUpdate();
            reg.close();
            return res;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error : " + e.toString());
        }

        return res;
    }

    /**
     * Metodo que retornara el modelo que se le asignara a la tabla en la
     * pantalla vendedor con los datos que contenga la tabla "Vendedor" en la
     * base de datos
     *
     * @param mod Modelo de la tabla que usaremos
     * @param r verificacion si agregar mas columnas
     * @return
     */
    public static DefaultTableModel datostabla(TableModel mod, boolean r) {
        PreparedStatement ps, prep;
        ResultSet rs, res;
        Connection conn = Conexion.getConnection();
        DefaultTableModel model = (DefaultTableModel) mod;
        if (r == true) {
            model.addColumn("ID");
            model.addColumn("RUT");
            model.addColumn("Nombre");
            model.addColumn("Apellido Paterno");
            model.addColumn("Apellido Materno");
            model.addColumn("Direccion");
            model.addColumn("Telefono");
            model.addColumn("Sucursal");
        }

        try {
            String sql = "select * from vendedor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                prep = conn.prepareStatement("select * from sucursal where id_sucursal=" + rs.getString("fk_sucursal"));
                res = prep.executeQuery();
                while (res.next()) {
                    model.addRow(new Object[]{rs.getString("id_vendedor"), rs.getString("rut_vendedor"), rs.getString("nombre_vendedor"),
                        rs.getString("apellido_paterno_vendedor"), rs.getString("apellido_materno_vendedor"), rs.getString("direccion_vendedor"),
                         rs.getString("telefono_vendedor"), res.getString("nombre_sucursal")});
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :" + e.toString());

        }
        return model;
    }

    /**
     * Metodo que nos devolvera el valor obtenido de la busqueda que se realizo
     * en la tabla de vendedores
     *
     * @param b Valor obtenido del combobox que nos indica a que valor haremos
     * la busqueda
     * @param p Valor que esta asociado a la busqueda
     * @param mod modelo de la tabla a utilizar
     * @return
     */
    public static DefaultTableModel busqueda(String b, String p, TableModel mod) {

        String where = "";

        if (!"".equals(b)) {
            where = "WHERE " + p + " = '" + b + "'";
        }

        PreparedStatement ps, prep;
        ResultSet rs, res;
        Connection conn = Conexion.getConnection();
        DefaultTableModel model = (DefaultTableModel) mod;

        try {
            String sql = "select * from vendedor " + where;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {

                prep = conn.prepareStatement("select * from sucursal where id_sucursal=" + rs.getString("fk_sucursal"));
                res = prep.executeQuery();

                while (res.next()) {
                    model.addRow(new Object[]{rs.getString("id_vendedor"), rs.getString("rut_vendedor"), rs.getString("nombre_vendedor"),
                        rs.getString("apellido_paterno_vendedor"), rs.getString("apellido_materno_vendedor"), rs.getString("direccion_vendedor"),
                         rs.getString("telefono_vendedor"), res.getString("nombre_sucursal")});

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error :" + e.toString());

        }
        return model;
    }

    /**
     * Metodo que realizara la respectiva sentencia de DELETE en la base de
     * datos asociado al parametro recibido
     *
     * @param id id que se buscara para eliminar de la tabla
     */
    public static void eliminar(String id) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion bd = new Conexion();
        String sql = "DELETE from vendedor where id_vendedor = " + id;
        int res = 0;
        try {
            ps = bd.getConnection().prepareStatement(sql);
            res = ps.executeUpdate();
            if (res > 0) {
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error :" + e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metodo que realizara el respectivo UPDATE en la tabla vendedor de la base
     * de datos recibiendo los respectivos parametros para su realizacion
     *
     * @param id parametro asociado a id_vendedor
     * @param rut parametro asociado a rut_vendedor
     * @param nom parametro asociado a nombre_vendedor
     * @param apepat parametro asociado a apellido_paterno_vendedor
     * @param apemat parametro asociado a apellido_materno_vendedor
     * @param dir parametro asociado a direccion_vendedor
     * @param tel parametro asociado a telefono_vendedor
     * @param idsuc parametro que estara asociado a fk_sucursal
     * @return nos devolvera un valor int que indira la cantidad de operaciones
     * que se realizaron para poder hacer una verificacion una vez terminado el
     * metodo
     */
    public static int updateVendedor(int id, String rut, String nom, String apepat, String apemat, String dir, int tel, int idsuc) {
        int res = 0;
        try {
            PreparedStatement ps;
            Conexion c = new Conexion();
            Connection reg = c.getConnection();
            ps = reg.prepareStatement("UPDATE vendedor set rut_vendedor='" + rut + "', nombre_vendedor='" + nom + "', apellido_paterno_vendedor='" + apepat + "'"
                    + ", apellido_materno_vendedor='" + apemat + "', direccion_vendedor='" + dir + "', telefono_vendedor=" + tel + ", fk_sucursal=" + idsuc
                    + " WHERE id_vendedor=" + id);
            res = ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error : " + e.toString());
        }
        return res;
    }

}
