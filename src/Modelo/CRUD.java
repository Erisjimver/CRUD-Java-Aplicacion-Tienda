package Modelo;

import Controlador.SettersAndGetters;

import static Vista.EntornoAdmin.LabelEstado;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class CRUD {
static Statement s;
static ResultSet rs;
private static PreparedStatement ps;
private static CallableStatement sc;
Conexion cn=new Conexion(); 
Connection c= cn.conexion();

////////////////////////////////////////////////////////////////////////////////
/////////////////////////////CRUD///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

//metodos registrar vendedor
    public void registrarEmpleado(SettersAndGetters to) throws Exception{
        
        try
        {
            ps = c.prepareCall("CALL RegistrarVendedor(?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, to.getIdTipoUsuarioV());
            ps.setInt(2, to.getIdEmpresaV());
            ps.setString(3, to.getContrasenaV());      
            ps.setString(4, to.getCedulaV());
            ps.setString(5, to.getNombresV());
            ps.setString(6, to.getApellidosV());
            ps.setString(7, to.getTelefonoV());
            ps.setString(8, to.getDireccionV());

            ps.execute();
            
            LabelEstado.setText("Registro exitoso del empleado"); 
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro del Empleado: "+e);
        }
        
    }

//metodos para registrar categorias
    public void registrarCategorias(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL RegistrarCategorias(?)");
            
            ps.setString(1, to.getDescripcion());  
            
            ps.execute();
            
            LabelEstado.setText("Registro exitoso de la categoria"); 
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error de registro de la categoria: "+e); 
        }

    } 
   
//metodos para registrar productos
    public void registrarProductos(SettersAndGetters to) throws Exception{
 
        try
        { 
            ps = c.prepareCall("CALL RegistrarProductos(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdcategorias());
            ps.setString(2, to.getNombreproducto());      
            ps.setString(3, to.getMarca());
            ps.setDouble(4, to.getCosto());
            ps.setDouble(5, to.getPrecio());
            ps.setInt(6, to.getStock()); 
            
            ps.execute();
            
            LabelEstado.setText("Registro del producto exitoso"); 
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro del Producto: "+e);
        }
    }

//Metodo para registrar clientes
    public void registrarCliente(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarClientes(?,?,?,?)");
            
            ps.setString(1, to.getCedula());
            ps.setString(2, to.getNombre());
            ps.setString(3, to.getTelefono());
            ps.setString(4, to.getDireccion()); 
            
            ps.execute();  
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro del Cliente: "+e);
        }
        
    }

//metodo para registrar factura    
    public void registrarFactura(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarFactura(?,?)");
            
            ps.setInt(1, to.getIdvendedor());
            ps.setInt(2, to.getIdcliente());
            
            ps.execute();
            
        }catch(SQLException e){
            
            LabelEstado.setText("Error de registro de ka factura: "+e);
        }
    }
    
//metodos para ingresar los detalles de la de la venta factura
    public void registrarDetalleFactura(SettersAndGetters to) throws Exception{
        
        try
        {
            ps = c.prepareCall("CALL RegistrarDetalleV(?,?,?,?,?)");
            
            ps.setInt(1, to.getIdfactura());   
            ps.setInt(2, to.getIdproducto()); 
            ps.setInt(3, to.getCantidad());
            ps.setDouble(4, to.getValorunitario());
            ps.setDouble(5, to.getValortotal()); 

            ps.execute();
            
        }catch(SQLException e){
            LabelEstado.setText("Error de registro del detalle de la venta y factura: "+e);
        }
    }
   
//metodos para registrar sucursal_empresa
    public void registrarSucursal(SettersAndGetters to) throws Exception{
        try
        {
            ps= c.prepareCall("CALL RegistrarEmpresa(?,?,?,?,?)");

            ps.setInt(1, to.getIdEmpresa());  
            ps.setString(2, to.getRuc());   
            ps.setString(3, to.getSucursal());  
            ps.setInt(4, to.getTelefonoEmpresa());  
            ps.setString(5, to.getDireccionEmpresa()); 

            ps.execute();
            
            LabelEstado.setText("Registro exitoso de la nueva sucursal"); 
            
        }
        catch(HeadlessException | SQLException e)
        {
            LabelEstado.setText("Error de registro de la sucursal: "+e); 
        }

    }
    
//----------------------------------------------------------------------------//   
//---------------------- Actualizaciones -------------------------------------//
//----------------------------------------------------------------------------//

 //metodo para actualizar Empleado
       
    public void actualizaEmpleado(SettersAndGetters to) throws Exception{
 
        try{
            ps = c.prepareCall("CALL ActualizarEmpleados(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, to.getIdvendedor());
            ps.setInt(2, to.getIdTipoUsuarioV());
            ps.setInt(3, to.getIdEmpresaV());
            ps.setString(4, to.getContrasenaV()); 
            //cs.setString(5, to.getCedulaV());
            ps.setInt(5, Integer.parseInt(to.getCedulaV()));
            ps.setString(6, to.getNombresV());
            ps.setString(7, to.getApellidosV());
            ps.setString(8, to.getTelefonoV());
            ps.setString(9, to.getDireccionV());
        
            ps.execute();
            
            LabelEstado.setText("Actualizacion de empleado exitosa");  
            
        }catch(SQLException e)
        {
            LabelEstado.setText("Error al actualizar los datos del empleado: "+e); 
        }
    }

//metodos para actualizar categorias
    public void actualizarCategorias(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL RegistrarCategorias(?)");
            
            ps.setString(1, to.getDescripcion());  
            
            ps.execute();
            
            LabelEstado.setText("Actualizacion exitosa de la categoria"); 
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al actualizar la categoria: "+e); 
        }

    } 
    
//metodos para actualizar productos
    public void actualizaProductos(SettersAndGetters to) throws Exception{
 
        try
        { 
            ps = c.prepareCall("CALL RegistrarProductos(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdcategorias());
            ps.setString(2, to.getNombreproducto());      
            ps.setString(3, to.getMarca());
            ps.setDouble(4, to.getCosto());
            ps.setDouble(5, to.getPrecio());
            ps.setInt(6, to.getStock()); 
            
            ps.execute();
            LabelEstado.setText("Actualizacion del producto exitosa"); 
        }catch(SQLException e){
            LabelEstado.setText("Error al actualizar el Producto: "+e);
        }
    }

//Metodo para actualizar clientes
    public void actualizaCliente(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarClientes(?,?,?,?)");
            
            ps.setString(1, to.getCedula());
            ps.setString(2, to.getNombre());
            ps.setString(3, to.getTelefono());
            ps.setString(4, to.getDireccion()); 
            
            ps.execute();
            
            LabelEstado.setText("Actualizacion del cliente se realizo con exito"); 
            
        }catch(SQLException e){
            LabelEstado.setText("Error al actualizar el cliente: "+e);
        }
        
    }


    
    
//----------------------------------------------------------------------------//   
//------------------------------ Eliminar ------------------------------------//
//----------------------------------------------------------------------------//
   
//Eliminar Empleados
    public void eliminarEmpleado(int idvendedor) throws Exception {
 
        try
        {
            ps = c.prepareCall("call EliminarEmpleado(?)");
            ps.setInt(1, idvendedor);
            ps.execute();
            LabelEstado.setText("Empleado eliminado con exito "); 
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Empleado: "+e); 
        }
       
    }      

//metodos para eliminar categorias
    public void eliminaCategoria(int idcategoria){
        
        try
        {
            ps = c.prepareCall("call EliminarCategoria(?)");
            ps.setInt(1, idcategoria);
            ps.execute();
            
            LabelEstado.setText("Categoria eliminada con exito "); 
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Categoria: "+e); 
        }
        
    } 
   
//metodos para eliminar productos
    public void eliminaProducto(int idproducto){
        
        try
        {
            ps = c.prepareCall("call EliminarProducto(?)");
            ps.setInt(1, idproducto);
            ps.execute();
            
            LabelEstado.setText("Producto eliminado con exito "); 
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Producto: "+e); 
        }
        
    }  
    
//----------------------------------------------------------------------------//
//--------------------------- CONSULTAS --------------------------------------//
//----------------------------------------------------------------------------//

   
//Consulta de los clientes    
    public ResultSet buscarClientes(String cedula) throws Exception {
   
        ps = c.prepareStatement("SELECT * FROM Clientes where cedula= ?");
        ps.setString(1, cedula);
        rs = ps.executeQuery();
        return rs;
    }
    
    public ResultSet buscarClientes1(String cedula) throws Exception {
        String sql="SELECT * FROM Clientes where cedula= ?";             
        return cn.consultar(sql);
    }
    
    
//Consulta de los productos por nombre usando like   
    public ResultSet buscarProducto(String nombre) throws Exception {
        nombre = '%' + nombre + '%';
        PreparedStatement ps = c.prepareStatement("SELECT * FROM producto where nombreproducto like ?");
        ps.setString(1, nombre);
        rs = ps.executeQuery();
        return rs;
    }
    
//consulta cantidad de productos
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

//consulta id del empleado con parametro  
    public String obteneriIDEmpleado(String usuario) throws Exception 
    {
    String idempleado="";
        s = c.createStatement();
        rs = s.executeQuery("select IdVendedor from Vendedor where nombres='"+usuario+"'");
        if(rs.next()){
        idempleado=rs.getString(1);
        }
        return idempleado;
    }
    
//consulta id del empleado sin parametro
    public String obteneriIDEmpleadoNoParametro() throws Exception 
    {
    String idempleado="";
        s = c.createStatement();
        rs = s.executeQuery("select max(IdVendedor)+1 from Vendedor");
        if(rs.next()){
        idempleado=rs.getString(1);
        }
        return idempleado;
    }  
    
//consulta el nombre de usuario
    public String obtenerNombreUsuario(String cedula) throws Exception {
    String nombre="";
        s = c.createStatement();
        rs = s.executeQuery("select nombres from vendedor where cedula='"+cedula+"'");
        if(rs.next()){
        nombre=rs.getString(1);
        }
        c.close();
        return nombre;
    }
    
//Busqueda de reportes"    
    public void Reportes(String Cadena){
    try{
         JasperReport reporte = JasperCompileManager.compileReport(Cadena); 
         JasperPrint print = JasperFillManager.fillReport(reporte,null,this.c);
         JasperViewer.viewReport(print,false);   
            
       }catch(JRException e){
          System.out.println("error"+e);   
        }
    }    
  
        
    
    
}//fin de la clase
