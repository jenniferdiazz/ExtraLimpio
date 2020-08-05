
package ConectorBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    private static Connection conn;
    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String user = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/a_extra_limpio";
    
    public Conexion() {
       conn = null;
       try{
           Class.forName(driver);
           conn = DriverManager.getConnection(url, user, password);
           
       } catch (ClassNotFoundException | SQLException e){
               JOptionPane.showMessageDialog(null,"Error al conectar con Base de Datos "+e.toString());
   
       }
        
}
    public static Connection getConnection(){
        return conn;
    }
    
    public void desconectar(){
        conn = null;      
    }
}
