package ClasesRecibeParametros;

import Conexion.Conexion;
import Auxiliares.SettersAndGetters;
import static PackAdmin.EntornoAdmin.LabelEstado;
import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MetodoIngreso {
static Statement s;
static ResultSet rs;
private static PreparedStatement ps;
private static CallableStatement cs;
Conexion cn=new Conexion(); 
Connection c= cn.conexion();

////////////////////////////////////////////////////////////////////////////////
/////////////////////////////SECCION REGISTROS//////////////////////////////////
    
//metodos registrar vendedor
    public void registrarVendedor(SettersAndGetters to) throws Exception{
 
        cs = c.prepareCall("CALL RegistrarVendedor(?,?,?,?,?,?,?,?)");
        cs.setInt(1, to.getIdTipoUsuarioV());
        cs.setInt(2, to.getIdEmpresaV());
        cs.setString(3, to.getContrasenaV());      
        cs.setString(4, to.getCedulaV());
        cs.setString(5, to.getNombresV());
        cs.setString(6, to.getApellidosV());
        cs.setString(7, to.getTelefonoV());
        cs.setString(8, to.getDireccionV());
        
        cs.execute();
    }
    
//metodos para registrar productos
    public void registrarProductos(SettersAndGetters to) throws Exception{
 
        cs = c.prepareCall("CALL RegistrarProductos(?,?,?,?,?,?)");

        cs.setInt(1, to.getIdcategorias());
        cs.setString(2, to.getNombreproducto());      
        cs.setString(3, to.getMarca());
        cs.setDouble(4, to.getCosto());
        cs.setDouble(5, to.getPrecio());
        cs.setInt(6, to.getStock()); 
        cs.execute();
    }

//Metodo para registrar clientes
    public void registrarCliente(SettersAndGetters to) throws Exception {
        cs = c.prepareCall("CALL RegistrarClientes(?,?,?,?)");
        cs.setString(1, to.getCedula());
        cs.setString(2, to.getNombre());
        cs.setString(3, to.getTelefono());
        cs.setString(4, to.getDireccion());   
        cs.execute();
    }

//metodo para registrar factura    
    public void registrarFactura(SettersAndGetters to) throws Exception {
        cs = c.prepareCall("CALL RegistrarFactura(?,?)");
        cs.setInt(1, to.getIdvendedor());
        cs.setInt(2, to.getIdcliente());
        cs.execute();
    }
//metodos para ingresar los detalles de la factura 
    public void registrarDetalleFactura(SettersAndGetters to) throws Exception{
 
        cs = c.prepareCall("CALL RegistrarDetalleV(?,?,?,?,?)");
        cs.setInt(1, to.getIdfactura());   
        cs.setInt(2, to.getIdproducto()); 
        cs.setInt(3, to.getCantidad());
        cs.setDouble(4, to.getValorunitario());
        cs.setDouble(5, to.getValortotal()); 
        cs.execute();
        
    }

//metodos para registrar categorias
    public void registrarCategorias(SettersAndGetters to) throws Exception{
        try
        {
        ps= c.prepareCall("CALL RegistrarCategorias(?)");
        ps.setString(1, to.getDescripcion());   
        ps.execute();
        }
        catch(HeadlessException | SQLException e)
        {
            LabelEstado.setText("Error de registro: "+e); 
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
        }
        catch(HeadlessException | SQLException e)
        {
            LabelEstado.setText("Error de registro: "+e); 
        }

    } 

}
