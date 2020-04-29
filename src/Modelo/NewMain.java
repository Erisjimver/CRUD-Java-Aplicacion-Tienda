package Modelo;

import java.sql.SQLException;

public class NewMain {
static boolean ced;
    public static void main(String[] args) throws SQLException, Exception {
        // Verificando conexion
       // Funcionalidades e = new Funcionalidades();

        //Conexion c=new Conexion();
        CRUD crud = new CRUD();
        String cedu="1111";
        String cedula = crud.consultarCompruebaCliente(cedu);
        System.out.println(cedula);

            
    }
    
}