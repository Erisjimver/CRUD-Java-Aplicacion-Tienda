package Controlador;
public class SettersAndGetters {
 
    
    
///////////////////////////    variables    ///////////////////////////////////
    
    //producto
    private String  nombreproducto, marca;   
    private double costo,precio;
    private int idcategorias,stock;
    
    //sucursal
    private String ruc,sucursal,direccionEmpresa;
    private int idEmpresa,telefonoEmpresa;
    
    //clientes
    private String nombre,direccion,telefono,cedula;
    
    //detalle factura
    double valorunitario,valortotal;
    int idfactura,idproducto,cantidad;
    
    //categoria
    private int idCategoria;
    private String descripcion;
    
    //usuarios
    private int idTipoUsuarioV,idEmpresaV;
    private String contrasenaV,cedulaV,nombresV,apellidosV,telefonoV,direccionV;
    
    //factura
    private int idvendedor,idcliente;
    
 /////////////////////////////////////////////////////////////////////////////
 ////////////////////////////////////////////////////////////////////////////
    
    
/////////////////////////////SETTERS AND GETTERS //////////////////////////////
    
    //get y set de productos
    public String getNombreproducto() {
        return nombreproducto;
    }
    

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getIdcategorias() {
        return idcategorias;
    }

    public void setIdcategorias(int idcategorias) {
        this.idcategorias = idcategorias;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    

    //get y set de categorias
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
    
    public int getIdCategoria() {
        return idCategoria;    
    } 
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getDescripcion() {
        return descripcion;
    }


    //get y set sucursal_empresa
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(int telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }


    
    //get y set detalle factura
    

    public double getValorunitario() {
        return valorunitario;
    }

    public void setValorunitario(double valorunitario) {
        this.valorunitario = valorunitario;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    public int getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(int idfactura) {
        this.idfactura = idfactura;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
    //set y get clientes
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

 ////set y get de vendedores///

    public int getIdTipoUsuarioV() {
        return idTipoUsuarioV;
    }

    public void setIdTipoUsuarioV(int idTipoUsuarioV) {
        this.idTipoUsuarioV = idTipoUsuarioV;
    }

    public int getIdEmpresaV() {
        return idEmpresaV;
    }

    public void setIdEmpresaV(int idEmpresaV) {
        this.idEmpresaV = idEmpresaV;
    }

    public String getContrasenaV() {
        return contrasenaV;
    }

    public void setContrasenaV(String contrasenaV) {
        this.contrasenaV = contrasenaV;
    }

    public String getCedulaV() {
        return cedulaV;
    }

    public void setCedulaV(String cedulaV) {
        this.cedulaV = cedulaV;
    }

    public String getNombresV() {
        return nombresV;
    }

    public void setNombresV(String nombresV) {
        this.nombresV = nombresV;
    }

    public String getApellidosV() {
        return apellidosV;
    }

    public void setApellidosV(String apellidosV) {
        this.apellidosV = apellidosV;
    }

    public String getTelefonoV() {
        return telefonoV;
    }

    public void setTelefonoV(String telefonoV) {
        this.telefonoV = telefonoV;
    }

    public String getDireccionV() {
        return direccionV;
    }

    public void setDireccionV(String direccionV) {
        this.direccionV = direccionV;
    }
    
    //set y get factura
    public int getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(int idvendedor) {
        this.idvendedor = idvendedor;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    
}
