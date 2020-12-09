package Modelo;

import Controlador.Funcionalidades;
import Controlador.SettersAndGetters;
import static Vista.EntornoAdmin.LabelEstado;
import static Vista.EntornoVendedor.LabelEstadoV;
import Vista.Login;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class CRUD {
    
//declarando variables
    static Statement s;
    static ResultSet rs;
    private int bdcantidad=0,idClienteI=0, idEmpleadoI=0;
    private String mensaje,tipoUsuario,nombreEmpleado,idEmpleadoS,cedulaClienteS,idProductoS,idCategoriaS,idSucursalS,codigoFacturaS="0",inversionS,totalVentaS,totalVentaEstimadaS;
    private static PreparedStatement ps;
    
//creando objeto de clases
    Conexion cn=new Conexion(); 
    Connection c= cn.conexion();
    Funcionalidades w = new Funcionalidades();
////////////////////////////////////////////////////////////////////////////////
/////////////////////////////CRUD///////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////

//metodos para registrar sucursal_empresa
    public void registrarSucursal(SettersAndGetters to) throws Exception{
        try
        {
            ps= c.prepareCall("CALL RegistrarEmpresa(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdEmpresa());  
            ps.setString(2, to.getRuc());   
            ps.setString(3, to.getNombreEmpresa());  
            ps.setString(4, to.getTelefonoEmpresa());  
            ps.setString(5, to.getDireccionEmpresa());
            ps.setString(6, to.getEmailEmpresa());

            ps.execute();
            mensaje = "Registro exitoso de la nueva sucursal..";
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
        }
        catch(HeadlessException | SQLException e)
        {
            mensaje = "Error de registro de la sucursal: "+e;
            LabelEstado.setText(mensaje);
            w.escribirLog(mensaje);
        }

    }
    
//metodos registrar vendedor
    public void registrarEmpleado(SettersAndGetters to) throws Exception{
        
        try
        {
            ps = c.prepareCall("CALL RegistrarEmpleado(?,?,?,?,?,?,?,?,?)");
            
            ps.setInt(1, to.getIdTipoUsuario());
            ps.setInt(2, to.getIdEmpresa());
            ps.setString(3, to.getNombreUsuario());
            ps.setString(4, to.getContrasena());      
            ps.setString(5, to.getCedulaEmpleado());
            ps.setString(6, to.getNombresEmpleado());
            ps.setString(7, to.getApellidosEmpleado());
            ps.setString(8, to.getTelefonoEmpleado());
            ps.setString(9, to.getDireccionEmpleado());

            ps.execute();
            
            mensaje = "Registro exitoso del empleado..";
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
            
        }catch(SQLException e){
            mensaje = "Error de registro del Empleado: "+e;
            LabelEstado.setText(mensaje);
            w.escribirLog(mensaje);
        }
        
    }

//metodos para registrar categorias
    public void registrarCategorias(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL RegistrarCategoria(?)");
            
            ps.setString(1, to.getDescripcion());  
            
            ps.execute();
            mensaje = "Registro exitoso de la categoria..";
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
            
        }
        catch(SQLException e)
        {
            mensaje = "Error en registrarCategorias: "+e;
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
        }

    } 
   
//metodos para registrar productos
    public void registrarProductos(SettersAndGetters to) throws Exception{
 
        try
        { 
            ps = c.prepareCall("CALL RegistrarProducto(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdCategoria());
            ps.setString(2, to.getNombreProducto());      
            ps.setString(3, to.getMarca());
            ps.setDouble(4, to.getCosto());
            ps.setDouble(5, to.getPrecioVenta());
            ps.setInt(6, to.getStock()); 
            
            ps.execute();
            
            mensaje = "Registro del producto exitoso..";
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
            
        }catch(SQLException e){
            
            mensaje = "Error en registrarProductos: "+e;
            LabelEstado.setText(mensaje);
            w.escribirLog(mensaje);
        }
    }

//Metodo para registrar clientes
    public void registrarCliente(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarCliente(?,?,?,?,?)");
            
            ps.setString(1, to.getCedulaCliente());
            ps.setString(2, to.getNombreCliente());
            ps.setString(3, to.getTelefonoCliente());
            ps.setString(4, to.getDireccionCliente());
            ps.setString(5, to.getEmailCliente());
            
            ps.execute();  
            mensaje = "Registro de cliente exitoso..";
            w.escribirLog(mensaje);
        }catch(SQLException e){
            mensaje = "Error en registrarCliente(): "+e;
            w.escribirLog(mensaje);
        }
        
    }

//metodo para registrar factura    
    public void registrarFactura(SettersAndGetters to) throws Exception {
        
        try
        {
            ps = c.prepareCall("CALL RegistrarFactura(?,?)");
            
            ps.setInt(1, to.getIdEmpleado());
            ps.setInt(2, to.getIdCliente());
            
            ps.execute();
            
            mensaje = "Registro exitoso de la factura..";
            w.escribirLog(mensaje);
            
        }catch(SQLException e){
            mensaje = "Error en registrarFactura: "+e;
            w.escribirLog(mensaje);
        }
    }
    
//metodos para ingresar los detalles de la de la venta factura
    public void registrarDetalleFactura(SettersAndGetters to) throws Exception{
        
        try
        {
            ps = c.prepareCall("CALL RegistrarDetalleV(?,?,?,?,?)");
            
            ps.setInt(1, to.getIdFactura());   
            ps.setInt(2, to.getIdProducto()); 
            ps.setInt(3, to.getCantidad());
            ps.setDouble(4, to.getValorUnitario());
            ps.setDouble(5, to.getValorTotal()); 
          
            ps.execute();
            
            mensaje = "Registro exitoso del detalle de la factura..";
            w.escribirLog(mensaje);
            
        }catch(SQLException e){
            mensaje = "Error en registrarDetalleFactura: "+e;
            w.escribirLog(mensaje);
        }
    }
   

//----------------------------------------------------------------------------//   
//---------------------- Actualizaciones -------------------------------------//
//----------------------------------------------------------------------------//
    
//metodos para actualizar Sucursal
    public void actualizarSucursal(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL ActualizarSucursal(?,?,?,?,?)");
            ps.setInt(1, to.getIdEmpresa());
            ps.setString(2, to.getRuc());  
            ps.setString(3, to.getNombreEmpresa()); 
            ps.setString(4, to.getTelefonoEmpresa());
            ps.setString(5, to.getDireccionEmpresa()); 
            
            ps.execute();
            
            if(ps.execute()==false){
                mensaje = "Actualizacion exitosa de la sucursal..";
                LabelEstado.setText(mensaje);
                w.escribirLog(mensaje);
            } 
            
        }
        catch(SQLException e)
        {
            mensaje = "Error al actualizar la sucursal: "+e;
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
        }

    } 
    
//metodo para actualizar Empleado      
    public void actualizarEmpleado(SettersAndGetters to){
 
        try{
            ps = c.prepareCall("CALL ActualizarEmpleado(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, to.getIdEmpleado());
            ps.setInt(2, to.getIdTipoUsuario());
            ps.setInt(3, to.getIdEmpresa());
            ps.setString(4, to.getNombreUsuario()); 
            ps.setString(5, to.getContrasena()); 
            ps.setInt(6, Integer.parseInt(to.getCedulaEmpleado()));
            ps.setString(7, to.getNombresEmpleado());
            ps.setString(8, to.getApellidosEmpleado());
            ps.setString(9, to.getTelefonoEmpleado());
            ps.setString(10, to.getDireccionEmpleado());
        
            ps.execute();
            
            if(ps.execute()==false){
                
                mensaje = "Actualizacion exitosa de la sucursal..";              
                LabelEstado.setText(mensaje);     
                w.escribirLog(mensaje);
            }    
            
        }catch(SQLException e)
        {
            mensaje = "Error al actualizar los datos del empleado: "+e;
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
        }
    }

//metodos para actualizar Categoria
    public void actualizarCategoria(SettersAndGetters to){
        try
        {
            ps= c.prepareCall("CALL ActualizarCategoria(?,?)");
            ps.setInt(1, to.getIdCategoria());
            ps.setString(2, to.getDescripcion());  
            
            ps.execute();
            
            if(ps.execute()==false){
                mensaje = "Actualizacion exitosa de la categoria..";
                LabelEstado.setText(mensaje); 
                w.escribirLog(mensaje);
            }            
            
        }
        catch(SQLException e)
        {
            mensaje = "Error al actualizar la categoria: "+e;
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
        }

    } 
    
//metodos para actualizar productos
    public void actualizarProductos(SettersAndGetters to){
 
        try
        { 
            ps = c.prepareCall("call ActualizarProducto(?,?,?,?,?,?)");

            ps.setInt(1, to.getIdProducto());
            ps.setString(2, to.getNombreProducto());      
            ps.setString(3, to.getMarca());
            ps.setDouble(4, to.getCosto());
            ps.setDouble(5, to.getPrecioVenta());
            ps.setInt(6, to.getStock());
            
            ps.execute();
            
            if(ps.execute()==false){
                LabelEstado.setText("Actualizacion del producto exitosa");
                w.escribirLog(mensaje);
            }

        }catch(SQLException e){
            LabelEstado.setText("Error al actualizar el Producto: "+e);
            w.escribirLog(mensaje);
        }
    }

//Metodo para actualizar Clientes
    public void actualizarCliente(SettersAndGetters to){
        
        try
        {
            ps = c.prepareCall("CALL ActualizarCliente(?,?,?,?,?)");
            
            ps.setInt(1, to.getIdCliente());
            ps.setString(2, to.getNombreCliente());
            ps.setString(3, to.getTelefonoCliente());
            ps.setString(4, to.getDireccionCliente());
            ps.setString(5, to.getEmailCliente());
            
            ps.execute();
            
            if(ps.execute()==false){
                mensaje = "Actualizacion del cliente se realizo con exito..";
                JOptionPane.showMessageDialog(null, mensaje,"MENSAJE",JOptionPane.INFORMATION_MESSAGE);
                LabelEstadoV.setText(mensaje); 
                w.escribirLog(mensaje);
            }
            
        }catch(SQLException e){
            mensaje  = "Error al actualizar el cliente: "+e;
            LabelEstadoV.setText(mensaje);
            w.escribirLog(mensaje);
        }   
    }

       
//----------------------------------------------------------------------------//   
//------------------------------ Eliminar ------------------------------------//
//----------------------------------------------------------------------------//

//Eliminar Sucursal
    public void eliminarSucursal(int id_empresa){
 
        try
        {
            ps = c.prepareCall("call EliminarEmpresa(?)");
            ps.setInt(1, id_empresa);
            ps.execute();
            
            if(ps.execute()==false){  
                mensaje = "Sucursal eliminada con exito..";
                LabelEstado.setText(mensaje);  
                w.escribirLog(mensaje);
            }
            
        }
        catch(SQLException e)
        {
            LabelEstado.setText("Error al eliminar Sucursal: "+e);
            w.escribirLog(mensaje);
        }
       
    }
    
//Eliminar Empleados
    public void eliminarEmpleado(int idempleado){
 
        try
        {
            ps = c.prepareCall("call EliminarEmpleado(?)");
            ps.setInt(1, idempleado);
            ps.execute();
            
            if(ps.execute()==false){
                mensaje = "Empleado eliminado con exito..";
                LabelEstado.setText(mensaje); 
                w.escribirLog(mensaje);
            }
            
        }
        catch(SQLException e)
        {
            mensaje = "Error al eliminar Empleado: "+e; 
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);            
        }
       
    }      

//metodos para eliminar categorias
    public void eliminaCategoria(int idcategoria){
        
        try
        {
            ps = c.prepareCall("call EliminarCategoria(?)");
            ps.setInt(1, idcategoria);
            ps.execute();
            
            if(ps.execute()==false){
                mensaje = "Categoria eliminada con exito..";
                LabelEstado.setText(mensaje);
                w.escribirLog(mensaje);
            }
            
        }
        catch(SQLException e)
        {
            mensaje = "Error al eliminar Categoria: "+e;
            LabelEstado.setText(mensaje);
            w.escribirLog(mensaje);
        }
        
    } 
    
//metodos para eliminar productos
    public void eliminaProducto(int idproducto){
        
        try
        {
            ps = c.prepareCall("call EliminarProducto(?)");
            ps.setInt(1, idproducto);
            ps.execute();
            
            if(ps.execute()==false){
                mensaje = "Producto eliminado con exito..";
                LabelEstado.setText(mensaje);
                w.escribirLog(mensaje);
            }          
            
        }
        catch(SQLException e)
        {
            mensaje = "Error al eliminar Producto: "+e;
            LabelEstado.setText(mensaje); 
            w.escribirLog(mensaje);
        }        
    } 
    
    
//----------------------------------------------------------------------------//
//--------------------------- CONSULTAS --------------------------------------//
//----------------------------------------------------------------------------//

//consulta del tipo de usuario para el LOGIN    
    public String consultarTipoUsuario(String usuario, String contrasena){        
        String sql="select tp.Tipo_Usuario from Empleado v inner join Tipo_Usuario tp on v.IdTipo_Usuario = tp.IdTipo_Usuario where v.cedula_empleado='"+usuario+"' and v.Contrasena='"+contrasena+"'";         
        try{       
            rs = consultar(sql);
            rs.next();
            tipoUsuario=rs.getString(1);
        
        }catch(SQLException e){
            Login.lblestadoLogin.setText(" error e tipo usuario"+e);
        }
        return tipoUsuario;
    }   
               
   
//----------------------------------------------------------------------------//
//-------------------------- INICIA CONSULTAS FACTURA ------------------------//
//----------------------------------------------------------------------------//
//consultar todas las facturas / indice
    public ResultSet consultarFacturas(){    
        String sql="select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total,f.Fecha_Emision as FECHA_COMPRA from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente order by f.Fecha_Emision desc";                    
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturas(): "+ e);
        }
      return rs;
    }
    
//consultar todas las facturas por ID
    public ResultSet consultarFacturasId(int idfactura){
        String sql= "select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total,f.Fecha_Emision as FECHA_COMPRA from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.IdFactura='"+idfactura+"'";
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturasID(): "+ e);
        }
      return rs;        
    }
    
//consultar todas las facturas por fecha
    public ResultSet consultarFacturasFecha(String fecha){
        String sql= "select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total,f.Fecha_Emision as FECHA_COMPRA from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('"+fecha+"','DD/MM/YYYY') and f.Fecha_Emision < to_date('"+fecha+"','DD/MM/YYYY')+1 order by f.Fecha_Emision desc";  
        //SELECT * FROM Factura WHERE fecha_emision > to_date('28/04/2020', 'DD/MM/YYYY') ;
        try{
                rs = consultar(sql);  
                                
        }catch(Exception e){
            System.out.println("Error en consultarFacturasFecha(String fecha): "+ e);
        }

      return rs;          
    } 
    
//consultar todas las facturas y sus detalles por fecha 
    public ResultSet consultarFacturasDetalleTodo(){
        String sql= "select f.IdFactura as ID, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as PRODUCTO,dv.valor_unitario as V_UNITARIO,dv.Valor_Total as TOTAL from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente order by f.Fecha_Emision desc";  
        //SELECT * FROM Factura WHERE fecha_emision > to_date('28/04/2020', 'DD/MM/YYYY') ;
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturasFecha(String fecha): "+ e);
        }
      return rs;          
    } 

//consultar todas las facturas y sus detalles por fecha 
    public ResultSet consultarFacturasDetalleDia(String fecha){
        String sql= "select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('"+fecha+"','DD/MM/YYYY') and f.Fecha_Emision < to_date('"+fecha+"','DD/MM/YYYY')+1 order by f.Fecha_Emision desc";  
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturasFecha(String fecha): "+ e);
        }
      return rs;          
    } 
    
//consultar todas las facturas y sus detalles por fecha 
    public ResultSet consultarFacturasDetalleMes(String mes){
        String sql= "select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where extract(month from f.fecha_emision) = '"+mes+"' order by f.Fecha_Emision desc";  
        //SELECT * FROM Factura WHERE fecha_emision > to_date('28/04/2020', 'DD/MM/YYYY') ;
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturasFecha(String fecha): "+ e);
        }
      return rs;          
    }
    
//consultar todas las facturas y sus detalles por fecha 
    public ResultSet consultarFacturasDetalleAnio(int year){
        String sql= "select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where extract(year from f.fecha_emision) = '"+year+"' order by f.Fecha_Emision desc";  
        //SELECT * FROM Factura WHERE fecha_emision > to_date('28/04/2020', 'DD/MM/YYYY') ;
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturasFecha(String fecha): "+ e);
        }
      return rs;          
    }
    
//consultar todas las facturas y sus detalles por fecha 
    public ResultSet consultarFacturasDetalleSemana(){
        String sql= "select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.fecha_emision > TRUNC(sysdate, 'iw') and f.fecha_emision < (TRUNC(sysdate, 'iw') + 7 - 1/86400) order by f.Fecha_Emision desc";  
        //SELECT * FROM Factura WHERE fecha_emision > to_date('28/04/2020', 'DD/MM/YYYY') ;
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarFacturasFecha(String fecha): "+ e);
        }
      return rs;          
    }
//----------------------------------------------------------------------------//
//-------------------------- TERMINA CONSULTAS CAJA --------------------------//
//----------------------------------------------------------------------------//
    
//se buscan todos los productos buscados para llenar indice de BuscarProductoFactura
    public ResultSet consultarProductoPorCategoria(String clave){
        String sql= "select p.IdProducto,p.Nombre_Producto,p.Marca,p.Precio_Venta,p.Stock from Producto p inner join Categoria c on p.IdCategoria = c.IdCategoria where c.descripcion='"+clave+"'";  
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarProductoPorCategoria(String clave): "+ e);
        }
      return rs;         
    }  
    
//Consulta de los productos por nombre usando like   
    public ResultSet consultarProducto(String nombre){       
        try{
            nombre = '%' + nombre + '%';
            ps = c.prepareStatement("SELECT * FROM producto where nombre_producto like ?");
            ps.setString(1, nombre);
            rs = ps.executeQuery();
        }catch(SQLException e){
            System.out.println("Error en consultarProducto(String nombre): "+ e);
        }
      return rs; 
    }

//Consulta de los productos  
    public ResultSet consultarTodosProducto(){
        String sql = "select * from producto order by idproducto";
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarTodosProducto(): "+ e);
        }
      return rs;         
    }
    
//Consulta de inversion en todos lso productos registrados    
     public String consultarInversion(){
        String sql = "select sum(costo * stock) as costo from producto";
        try{
            rs = consultar(sql);
            while(rs.next()){
                inversionS = rs.getString(1);
            }
        }catch(SQLException e){
            System.out.println("Error en consultarInversion(): "+ e);
        }
      return inversionS;         
    } 
 //Consulta de inversion en todos lso productos registrados    
     public String consultarTotalVentasEstimada(){
        String sql = "select sum(precio_venta * stock) as total_venta from producto";
        try{
            rs = consultar(sql);
            while(rs.next()){
                totalVentaEstimadaS = rs.getString(1);
            }
        }catch(SQLException e){
            System.out.println("Error en consultarTotalVentasEstimada(): "+ e);
        }
      return totalVentaEstimadaS;         
    }     
//Consulta el total de dinero en ventas realizadas
     public String consultarTotalVentasReales(){
        String sql = "select sum(dv.valor_total) from factura f inner join detalle_venta dv on f.idfactura = dv.idfactura";
        try{
            rs = consultar(sql);
            while(rs.next()){
                totalVentaS = rs.getString(1);
            }
        }catch(SQLException e){
            System.out.println("Error en consultarTotalVentasReales(): "+ e);
        }
      return totalVentaS;         
    } 
     
//Consultar id_emopleado y Tipo Empleado de Empleado para el jcombobox de TipoEmpleado    
    public ResultSet consultarLlenarComboTipoEmpleado(){
        String sql="select IdTipo_Usuario,Tipo_Usuario from Tipo_Usuario";             
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarLlenarComboTipoEmpleado(): "+ e);
        }
      return rs;         
    }
    
//Consulta todos los datos del empleado
    public ResultSet consultarTodosEmpleados(){
        String sql="select * from Empleado order by IdEmpleado";            
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarTodosEmpleados() : "+ e);
        }
      return rs;          
    } 

//Consulta todos los datos de la empresa
    public ResultSet consultarSucursales(){
        String sql="select * from Empresa order by IdEmpresa ";            
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarSucursales(): "+ e);
        }
      return rs;         
    }
    
//Consultar id_empresa nombre empresa para el combo sucursal    
    public ResultSet consultarLlenarComboSucursal(){
        String sql="select IdEmpresa, Nombre_Empresa from Empresa";             
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarLlenarComboSucursal(): "+ e);
        }
      return rs;          
    }

//Consultar id_categoria y descripcion de la ca categoria para el jcombobox de productos    
    public ResultSet consultarLlenarComboCategoria(){
        String sql="select IdCategoria, descripcion from Categoria order by idcategoria";             
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarLlenarComboCategoria(): "+ e);
        }
      return rs;          
    }
 
//Consulta de los clientes por cedula  
    public ResultSet consultarClientes(String cedula){           
        try{
            ps = c.prepareStatement("SELECT * FROM Cliente where cedula_Cliente= ?");
            ps.setString(1, cedula);
            rs = ps.executeQuery();
        }catch(SQLException e){
            System.out.println("Error en consultarClientes(String cedula): "+ e);
        }
      return rs;   
    }
    
//Consulta de los clientes por cedula   
    public ResultSet consultarTodosClientes(){           
        String sql= "select * from Cliente";  
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarTodosClientes(): "+ e);
        }
      return rs;     
    }
    
//consulta id del empleado con parametro  
    public ResultSet consultarDatosEmpresa(){        
        //String sql= "select Nombre_Empresa,Ruc,Telefono_Empresa from Empresa"; 
        String sql= "select * from Empresa";  
        try{
            rs = consultar(sql);
        }catch(Exception e){
            System.out.println("Error en consultarDatosEmpresa(): "+ e);
        }
      return rs;        
    }
  
//consulta cantidad de productos
    public int consultarStock(int idproducto){
       try
       {
        rs = consultar("select stock from Producto where IdProducto="+idproducto+"");
            while(rs.next()){
            bdcantidad=rs.getInt(1);
            }           
       }
       catch(SQLException e){
           System.out.println("Error en consultarCantidad(int idproducto): "+ e);
       }
        return bdcantidad;
    }
        
//consulta id del cliente por cedula
    public int consultarIdClienteParametro(String cedula){
        try
        {
        rs = consultar("select IdCliente from Cliente where cedula_cliente='"+cedula+"'");
            if(rs.next()){
                idClienteI=rs.getInt(1);
            }            
        }
        catch(SQLException e){
            System.out.println("Error en obteneriIDclienteParametro(String cedula): "+ e);
        }
        return idClienteI;
    }
  
//consulta booleano cliente por cedula
    public String consultarCompruebaCliente(String cedula){
        try
        {
        rs = consultar("select cedula_cliente from Cliente where cedula_cliente='"+cedula+"'");

            if(!rs.next()){
                cedulaClienteS="Vacio";                 
            }
            else{
                cedulaClienteS = rs.getString(1);
            }        
        }    
        catch(SQLException e){
           System.out.println("Error en consultarCompruebaCliente(String cedula): "+ e);
        }
        return cedulaClienteS;
    }    

//consulta id+1 del producto
    public String consultarIdProducto(){
        try
        {
            rs = consultar("select max(IdProducto)+1 from Producto");
            if(rs.next()){
                idProductoS=rs.getString(1);
            }             
        }
        catch(SQLException e){
           System.out.println("Error en obteneriIdProducto(): "+ e);
        }
        return idProductoS;
    }

//consulta id de la categoria
    public String consultarIdCategoria() {
        try
        {      
        rs = consultar("select max(IdCategoria)+1 from Categoria");
            if(rs.next()){
                idCategoriaS=rs.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Error en obteneriIdCategoria(): "+ e);
        }
        return idCategoriaS;
    }  

//consulta id de la Sucursal
    public String consultarIdSucursal(){
        try
        {
        rs = consultar("select max(IdEmpresa)+1 from Empresa");
            if(rs.next()){
                idSucursalS=rs.getString(1);
            }            
        }
        catch(SQLException e){
            System.out.println("Error en obteneriIdSucursal(): "+ e);
        }
        return idSucursalS;
    }

//consulta id del empleado con parametro  
    public int consultarIdEmpleado(String usuario){     
        try
        {
        rs = consultar("select IdEmpleado from Empleado where nombres='"+usuario+"'");
            if(rs.next()){
                idEmpleadoI=rs.getInt(1);
            }            
        }
        catch(SQLException e){
            System.out.println("Error en obteneriIDEmpleado(String usuario): "+ e);
        }
        return idEmpleadoI;
    }

//consulta id del empleado sin parametro
    public String consultarIdEmpleadoNoParametro(){           
        try
        {
        rs = consultar("select max(IdEmpleado)+1 from Empleado");
            if(rs.next()){
                idEmpleadoS=rs.getString(1);
            }        
        }
        catch(SQLException e){
            System.out.println("Error en obteneriIDEmpleadoNoParametro(): "+ e);
        }
        return idEmpleadoS;
    }
    
//consulta Codigo de la factura
    public String consultarCodigoFactura(){
        try
        {
        rs = consultar("select max(IdFactura)+1 from Factura");
            while(rs.next()){
            codigoFacturaS=rs.getString(1);
            }
        }
        catch(SQLException e){
            System.out.println("Error en obteneriIDEmpleado(String usuario): "+ e);           
        }
        return codigoFacturaS;
    }     
     
//consulta el nombre de usuario
    public String consultarNombreUsuario(String cedula){
        try
        {   
        rs = consultar("select nombres from Empleado where cedula_empleado='"+cedula+"'");
            if(rs.next()){
                nombreEmpleado=rs.getString(1);
            }  
        }
        catch(SQLException e){
            System.out.println("Error en obtenerNombreUsuario(String cedula): "+ e);
        }
        return nombreEmpleado;
    }
    
////////////////////////////////////////////////////////////////////////////////
///////////////////Metodo generico para consultas resul set ////////////////////
////////////////////////////////////////////////////////////////////////////////
    
//metodo consultar usado por la mayoria de las consultas
    public ResultSet consultar(String sql) { 
            try
            { 
                s = c.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                rs = s.executeQuery(sql); 

            } catch (SQLException e) { 
                return null; 
            } 
            return rs; 
    }        
    
//Busqueda de reportes  
    public void Reportes(String Cadena){
    try{
         JasperReport reporte = JasperCompileManager.compileReport(Cadena); 
         JasperPrint print = JasperFillManager.fillReport(reporte,null,this.c);
         JasperViewer.viewReport(print,false);   
            
       }catch(JRException e){
          System.out.println("error"+e);   
        }
    } 
    
    //se crea el reporte enviando parametros
    public void ReportesParametros(String Cadena){
    try{
        
        Map<String, String> parametros = new HashMap<>();
        parametros.put("cantidad", "1");
        parametros.put("detalle", "Auticulares");
        parametros.put("valor_unitario", "5");
        parametros.put("valor_total", "5");
        
        JasperReport reporte = JasperCompileManager.compileReport(Cadena); 
        JasperPrint print = JasperFillManager.fillReport(reporte,parametros,this.c);
        JasperViewer.viewReport(print,false);   
            
       }catch(JRException e){
          System.out.println("error"+e);   
        }
    }
    //se crea el reporte enviando parametros
    public void ReportesParametros1(String cantidad, String detalle, String valor_unitario, String valor_total){
    String rutaInforme = "C:\\Users\\Home\\Documents\\NetBeansProjects\\Java-Aplicacion-registro-consulta-venta-master\\src\\Vista\\Reportes\\facturaParamatros.jrxml";    
 
    try{
        
        Map<String, String> parametros = new HashMap<>();
        parametros.put("cantidad", cantidad);
        parametros.put("detalle", detalle);
        parametros.put("valor_unitario", valor_unitario);
        parametros.put("valor_total", valor_total);
        
        JasperReport reporte = JasperCompileManager.compileReport(rutaInforme); 
        JasperPrint print = JasperFillManager.fillReport(reporte,parametros,this.c);
        JasperViewer.viewReport(print,false);   
            
       }catch(JRException e){
          System.out.println("error"+e);   
        }
    }
    
    public void resportesPDF(String ruta, String archi) {
        try {
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, c);
            JasperExportManager.exportReportToPdfFile(informe, archi);

            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Facturas");
            ventanavisor.setVisible(true);
        } catch (JRException e) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
        }
    } 
    

}//fin de la clase
