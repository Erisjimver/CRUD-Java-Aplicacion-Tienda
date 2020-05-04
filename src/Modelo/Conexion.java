package Modelo;
import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {   
    Connection conexion=null;
    static Statement s;
    static ResultSet rs;

    public Connection conexion(){

            try { 
                Class.forName("oracle.jdbc.OracleDriver");    
                String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
                conexion=DriverManager.getConnection(BaseDeDatos,"proyecto","59291");

                if (conexion != null) {
                    System.out.println("Conectando a Base de Datos...");
                }
            } catch (ClassNotFoundException | SQLException e) {
                JOptionPane.showMessageDialog(null,"ERROR EN CONECTAR LA BASE DE DATOS"+e);
            }
            return conexion;    
    }

    public void cerrarconexion() throws SQLException { 
        System.out.println("cerrando conexion");
                conexion.close();       
    }
}