package Auxiliares;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DatosFechaHora {
    
 public String m,m2,fech;   
   
    Calendar calendario = new GregorianCalendar();
    
    
    public String fechaActual()
    {  
    java.util.Date fecha=new java.util.Date();
    SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
    
    m=formatoFecha.format(fecha);
    fech=String.format(m);
    return fech;   
    } 
    
    
 
    
 
}
