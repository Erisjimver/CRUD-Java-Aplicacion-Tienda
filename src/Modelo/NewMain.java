package Modelo;

import Controlador.Funcionalidades;
import java.sql.SQLException;

public class NewMain {
static boolean ced;
    public static void main(String[] args) throws SQLException {
        // Verificando conexion
        Funcionalidades e = new Funcionalidades();
        Conexion c=new Conexion();
        c.conexion();
        c.cerrarconexion();
        
        ced = e.validadorDeCedula("1207130889");
        if (ced ==true){
            System.out.println("Es correcta");
        }
        else
        {
            System.out.println("Es falsa");
        }
    }
    
}
