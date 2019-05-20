package Modelo;

import Modelo.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class Consultas {
static Statement s;
static ResultSet rs;
Conexion cn=new Conexion(); 
Connection c= cn.conexion();



  
    public void Reportes(String Cadena){
    try{
         JasperReport reporte = JasperCompileManager.compileReport(Cadena); 
         JasperPrint print = JasperFillManager.fillReport(reporte,null,this.c);
         JasperViewer.viewReport(print,false);   
            
       }catch(JRException e){
          System.out.println("error"+e);   
        }
    }  
    
//Consulta de los pclientes    
    public ResultSet buscarClientes(String cedula) throws Exception {
   
        PreparedStatement ps = c.prepareStatement("SELECT * FROM Clientes where cedula= ?");
        ps.setString(1, cedula);
        rs = ps.executeQuery();
        return rs;
    }
    
//Consulta de los productos por nombre usando like   
    public ResultSet buscarProducto(String nombre) throws Exception {
        nombre = '%' + nombre + '%';
        PreparedStatement ps = c.prepareStatement("SELECT * FROM producto where nombreproducto like ?");
        ps.setString(1, nombre);
        rs = ps.executeQuery();
        return rs;
    }
    
    public int consultarCantidad(int idproducto) throws Exception {
       int bdcantidad=0;
        s = c.createStatement();
        rs = s.executeQuery("select stock from Producto where IdProducto="+idproducto+"");
        while(rs.next()){
        bdcantidad=rs.getInt(1);
        }
        return bdcantidad;
        }
    
    
//consulta id del cliente
    public String obteneriIDcliente() throws Exception {
    String idcliente="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdCliente) from Clientes");
        if(rs.next()){
        idcliente=rs.getString(1);
        }
        return idcliente;
    }

//consulta id del empleado   
    public String obteneriIDEmpleado(String usuario) throws Exception 
    {
    String idempleado="";
        s = c.createStatement();
        rs = s.executeQuery("select IdVendedor from Vendedor where cedula='"+usuario+"'");
        if(rs.next()){
        idempleado=rs.getString(1);
        }
        return idempleado;
    }

}
