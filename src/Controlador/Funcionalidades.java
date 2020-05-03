package Controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import static Vista.EntornoVendedor.lblusuario;
import java.util.HashMap;
import java.util.Map;
public class Funcionalidades {
    
 public String m,m2,fech;  
 private int annio, mes;
    
    Calendar c1 = Calendar.getInstance();   
    Calendar calendario = new GregorianCalendar();
    Map<String,Integer> categorias = new HashMap<>();
    Map<String,Integer> sucursales = new HashMap<>();
    Map<String,Integer> empleados = new HashMap<>();
    
    public String fechaActual()
    {  
    java.util.Date fecha=new java.util.Date();
    SimpleDateFormat formatoFecha=new SimpleDateFormat("dd/MM/YYYY");
    
    m=formatoFecha.format(fecha);
    fech=String.format(m);
    return fech;   
    }
    
    public int obtenerAño() {
       annio = c1.get(Calendar.YEAR);
       return annio;
    }
    
    public String obtenerMes(){
        mes = c1.get(Calendar.MONTH)+1;
        String mesS= String.valueOf(mes);
        return mesS;
    }
    

    //Validacion si la cedula es correcta    
    public boolean validadorDeCedula(String cedula) {
    boolean cedulaCorrecta;

            try {

                if (cedula.length() == 10) // Constantes App.LongitudCedula
                {
                    int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
                    if (tercerDigito < 6) 
                    {
                    // Coeficientes de validación cédula
                    // El decimo digito se lo considera dígito verificador
                    int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
                    int verificador = Integer.parseInt(cedula.substring(9,10));
                    int suma = 0;
                    int digito =0;

                        for (int i = 0; i < (cedula.length() - 1); i++) 
                        {
                            digito = Integer.parseInt(cedula.substring(i, i + 1))* coefValCedula[i];
                            suma += ((digito % 10) + (digito / 10));
                        }

                        if ((suma % 10 == 0) && (suma % 10 == verificador)) 
                        {
                            cedulaCorrecta = true;
                        }
                        else cedulaCorrecta = (10 - (suma % 10)) == verificador;
                    } 
                    else 
                    {
                        cedulaCorrecta = false;
                    }
                }
                else{cedulaCorrecta = false;}
                } 
            catch (NumberFormatException nfe) 
                {
                cedulaCorrecta = false;
                lblusuario.setText(nfe.toString());
                } 
            catch (Exception err) 
                {
                    JOptionPane.showMessageDialog(null,"Una excepcion ocurrio en el proceso de validadcion"+err);
                    //System.out.println("Una excepcion ocurrio en el proceso de validadcion");
                    cedulaCorrecta = false;
                }
            
            //verificacion final del resultado es ecir retornar verdadero o falso dependiendo del resultado
            if (!cedulaCorrecta) 
            {
                    //JOptionPane.showMessageDialog(null,"La Cédula ingresada es Incorrecta");
                    return cedulaCorrecta;
            }
            else{return cedulaCorrecta;}
    }


    //Diccionario de las ID de las categorias de lso productos
    public void DiccionarioCategoria(SettersAndGetters to) throws Exception{       
       
        categorias.put(to.getDescripcion(),to.getIdCategoria());
        //System.out.println(categorias.keySet());
        //System.out.println(categorias.get("Accesorios"));
    }
    
    public int obtenerIdCategoria(String clave){
        int resultado;
        
            resultado=categorias.get(clave);
        
        return resultado;
    }

    
    //Diccionario de las ID de las categorias de las sucursales
    public void DiccionarioSucursal(SettersAndGetters to) throws Exception{       
       
        sucursales.put(to.getNombreEmpresa(),to.getIdEmpresa());
        //System.out.println(categorias.keySet());
        //System.out.println(categorias.get("Accesorios"));
    }
    
    public int obtenerIdSucursal(String clave){
        int resultado;
        
            resultado=sucursales.get(clave);
        
        return resultado;
    }
 
    //Diccionario de las ID de las categorias de los empleados
    public void DiccionarioEmpleado(SettersAndGetters to) throws Exception{       
       
        empleados.put(to.getTipoUsuario(),to.getIdTipoUsuario());
        //System.out.println(categorias.keySet());
        //System.out.println(categorias.get("Accesorios"));
    }
    
    public int obtenerIdEmpleado(String clave){
        int resultado;
        
            resultado = empleados.get(clave);
        
        return resultado;
    }
    
    
    
}//fin de la clase
