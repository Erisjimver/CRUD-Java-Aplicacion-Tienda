package Modelo;

import Vista.Caja;
import java.sql.SQLException;

public class NewMain {
static boolean ced;
public static int x,y;
    public static void main(String[] args) throws SQLException, Exception {
        // Verificando conexion
       // Funcionalidades e = new Funcionalidades();

        //Conexion c=new Conexion();
        //CRUD crud = new CRUD();
        //String cedu="1111";
        //String cedula = crud.consultarCompruebaCliente(cedu);
        //System.out.println(cedula);
        //Caja caja = new Caja();
        //String fec="29/04/2020";
        //caja.buscarFacturasDetallesDia(fec);
        //System.out.println(crud.consultarInversion());
        
        for( x = 0 ; x<=480 ; x+=5 ){            
            System.out.println();
          // System.out.println(x,y);
            //g.drawOval(x+5, 310-(x+5), 5, 5);  
            //System.out.println(x+5);
        }
            
    X1();
    Y1();
    

    } 
    private int X(){
    
        int horizontal=0;
        for( x = 0 ; x<=480 ; x+=5 ){            
           System.out.println(x);
        }
        return horizontal;
    }
    
    private int Y(){
    
        int vertical=0;
        for( y = 310 ; y>=0 ; y-=5 ){            
           System.out.println(y);
        }
        return vertical;
    }
    
    private static void X1(){
    
        int horizontal=0;
        for( x = 0 ; x<=480 ; x+=5 ){            
           System.out.println(x);
        }
    }
    
    private static void Y1(){
    
        int horizontal=0;
        for( y = 310 ; y>=0 ; y-=5 ){            
           System.out.println(y);
        }
    }
}

    
