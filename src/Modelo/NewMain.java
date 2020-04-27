package Modelo;

import Controlador.Funcionalidades;
import java.sql.SQLException;

public class NewMain {
static boolean ced;
    public static void main(String[] args) throws SQLException, Exception {
        // Verificando conexion
       // Funcionalidades e = new Funcionalidades();

        //Conexion c=new Conexion();
        CRUD crud = new CRUD();
        System.out.println(crud.obteneriIDclienteParametro("1207001114"));
        
        int a = 0;
        int b = 1;
        int c = 2;
        
        int variable=5;
        
        if(variable==0){
            System.out.println("registro a");  
        }
        if(variable ==b){
            System.out.println("Registro b");
        }
        if(variable!=a&&variable!=b){
            System.out.println("Registro c");
        }
            
    }
    
}
