package Vista;
import Controlador.Funcionalidades;
import Modelo.CRUD;
import Controlador.SettersAndGetters;
import static Vista.EntornoVendedor.LabelEstadoV;

import static Vista.EntornoVendedor.lblusuario;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Factura extends javax.swing.JPanel {
    
    //declarando variables
    private static ResultSet r;
    private String nombree,ru,t,fact,cedula;
    private int cantidad,idcliente;
    private double importe = 0,igv, subtotal, total; 
    private String nombre,telefono,direccion;
    DefaultTableModel model;
    //creando de clases
    Funcionalidades funcionalidad=new Funcionalidades();
    SettersAndGetters set =new SettersAndGetters();
    CRUD crud=new CRUD();
    
    //creando objeto de tabla
    //DefaultTableModel model = new DefaultTableModel();
    

    public Factura() {
        initComponents();
        //this.TablaDetalles.setModel(model);
        fecha();
        DatosEmpresa();
        codigofac();

    }
    
    public void fecha(){
        txtFecha.setText(funcionalidad.fechaActual());      
    }
    
    public void DatosEmpresa(){
     try{
        r = crud.obtenerDatosEmpresa();
        while (r.next()) {
           nombree=r.getString(1);
           ru=r.getString(2);
           t=r.getString(3);
           lblnombre.setText(nombree);
           lblruc.setText(ru);
           lbltelefono.setText(t);
           //falta direccion y nombre propietario en la tabla hacer el cambio del propiertario
        }
        r.close();
        
     }catch(Exception e)
     {
        LabelEstadoV.setText("Error: "+e); 
     }       
}
 
    public void codigofac(){
        try{
           fact=crud.obtenerCodigoFactura();
           lblnumfact.setText(fact);       
        }catch(Exception e)
        {
           LabelEstadoV.setText("Error: "+e); 
        }       
} 
   
    private void agregar(){
        
       try {
            model = (DefaultTableModel) TablaDetalles.getModel();
            String descripcion = "" + TextNombrep.getText() + " marca " + TextMarca.getText();
            importe = Double.parseDouble(TextImporte.getText());
            int filas = TablaDetalles.getRowCount();

            int idpro=Integer.parseInt(TextCodigop.getText());           
            int cantbd=crud.consultarCantidad(idpro);
            
            for (int i = 0; i < filas; i++) 
            {
                System.out.println(filas);
                if (descripcion.equalsIgnoreCase(TablaDetalles.getValueAt(i, 2).toString())) 
                {
                    JOptionPane.showMessageDialog(this, "Ud. ya Esta Utilizando este Producto");
                    model.removeRow(TablaDetalles.getSelectedRow());
                }
            }
            if((Integer.parseInt(TextCantidad.getText()))>cantbd){
              JOptionPane.showMessageDialog(null,"Existen solo "+cantbd+" articulos en stock");  
            }           
            else if(importe > 0) {
                Object[] registros = {
                    TextCodigop.getText(),TextCantidad.getText(),descripcion, TextPrecio.getText(), importe
                };
                model.addRow(registros);
                calcularTotales();
            }
            limpiarSeccionArticulo();
            

        } catch (NumberFormatException | HeadlessException e) 
        {
            JOptionPane.showMessageDialog(null,"acaso es este?"+ e);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error al agregar los articulos...."+ ex);
        }
   }
   
    private void cantidad(){
        try {
            cantidad = Integer.parseInt(TextCantidad.getText());
            double precio = Double.parseDouble(TextPrecio.getText());
            if (cantidad > 0) {
                importe = precio * cantidad;
            }
            TextImporte.setText(String.valueOf(importe));
        }
        catch (NumberFormatException e) 
        {
            LabelEstadoV.setText("Error es aqui: "+e); 
        }
   }

    private void vender(){
        try{
            //datos universales dentro del metodo vender
            cedula = TextCedula.getText();
            
            //primer consulta de verificacion de la existencia del cliente
            idcliente=crud.obteneriIDclienteParametro(cedula);
            
            //verificar si el cliente no existe en la base de datos / de ser asi lo registramos
            //la verficiacion es: diferente de 0 no existe y diferente de 1 no es el consumidor final
            //el descarte es que si no es ni 0 ni 1 es otro cliente que ya existe pero k no es el consumidor final
            //de modo que se registra la id del cliente pero no se crea un nuevo registro en el cliente
            if(idcliente!=0&&idcliente!=1){//se comprueba si el cliente es nuevo
                RegistrarFacturaDetalle();
            }
            if(idcliente==0){//si el cliente
                set.setCedula(cedula);
                set.setNombre(TextNombre.getText());
                set.setTelefono(TextTelefono.getText());
                set.setDireccion(TextDireccion.getText());
                crud.registrarCliente(set);  
                RegistrarFacturaDetalle();
            }
            if(idcliente==1){

                idcliente = 1;
                RegistrarFacturaDetalle();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error: "+ e);
        }          
    }
    private void RegistrarFacturaDetalle(){
        try
        {
            String usuar =(lblusuario.getText());
            int codEmpleado = crud.obteneriIDEmpleado(usuar); 
            idcliente = crud.obteneriIDclienteParametro(cedula);
            System.out.println("Id cliente dentro del metodo llamado: "+idcliente);
            set.setIdvendedor(codEmpleado);
            set.setIdcliente(idcliente);

            crud.registrarFactura(set);

            int filas = TablaDetalles.getRowCount();
                for (int i = 0; i < filas; i++) {

                    set.setIdfactura(Integer.parseInt(lblnumfact.getText()));
                    set.setIdproducto(Integer.parseInt(TablaDetalles.getValueAt(i, 0).toString()));
                    set.setCantidad(Integer.parseInt(TablaDetalles.getValueAt(i, 1).toString()));
                    set.setValorunitario(Double.parseDouble(TablaDetalles.getValueAt(i, 3).toString()));
                    set.setValortotal(Double.parseDouble(TablaDetalles.getValueAt(i, 4).toString()));

                    crud.registrarDetalleFactura(set);             
                    }
            codigofac();
            limpiacontroles();
            
            JOptionPane.showMessageDialog(null, "Venta registrada Correctamente ");
          
        }catch(Exception e)
        {
            System.out.println("Lo que sea"+e);
        }

    }
    

    public void eliminar(){
    
        try
        {            
            int[] fila = TablaDetalles.getSelectedRows();
            for(int i=0;i<fila.length;i++){
            model.removeRow(fila[i]-i);
        }
        }
        catch(HeadlessException ext)
        {
            LabelEstadoV.setText("Error: "+ext); 
        }
    }

    //metodo para verificar la existencia de un usuario
    public void verificaUsuario(){
        try{
            cedula = TextCedula.getText().toLowerCase(); 
            String cedu = crud.compruebaCliente(cedula);
            //if usuario nuevo
                if (cedula.equals(cedu)) 
                {
                    LabelEstadoV.setText("cedula correcta");
                    r = crud.buscarClientes(cedula); 

                    while (r.next()) {
                    nombre=r.getString(3);
                    telefono=r.getString(4);
                    direccion=r.getString(5);
                    TextNombre.setText(nombre);
                    TextTelefono.setText(telefono);
                    TextDireccion.setText(direccion);
                    
                    LabelEstadoV.setText("Cliente conocido....");
                    DesabilitarTextoCliente();
                    } 
                }
                if (!(cedula).equals(cedu)) {
                    HabilitarTextoCliente();
                    LimpiarDatosCliente();
                    LabelEstadoV.setText("Cliente nuevo....");                    
                }
                if((cedula).equals("1111")){
                    nombre="Consumidor Final";
                    telefono="555555555";
                    direccion="Sin Direccion";
                    TextNombre.setText(nombre);
                    TextTelefono.setText(telefono);
                    TextDireccion.setText(direccion);  
                    LabelEstadoV.setText("Consumidor Final....");
                    DesabilitarTextoCliente();
                }
        }
        catch(Exception e){
            LabelEstadoV.setText("Error al comprobar existencia de cliente...."+e);
        }
         
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanelFactura = new javax.swing.JPanel();
        DatosEmpresa = new javax.swing.JPanel();
        lblnombre = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblruc = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        lblbpropietario = new javax.swing.JLabel();
        NumeroFactura = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblnumfact = new javax.swing.JLabel();
        DatosCliente = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        TextNombre = new javax.swing.JTextField();
        TextCedula = new javax.swing.JTextField();
        TextDireccion = new javax.swing.JTextField();
        TextTelefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaDetalles = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        TextTotal = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        TextNombrep = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        TextCantidad = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TextImporte = new javax.swing.JTextField();
        TextMarca = new javax.swing.JTextField();
        TextPrecio = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        TextCodigop = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        TextSubtotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        TextIva = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        PanelControles = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        Vender = new javax.swing.JButton();
        txtFecha = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1200, 575));

        PanelFactura.setBackground(new java.awt.Color(255, 255, 255));
        PanelFactura.setBorder(javax.swing.BorderFactory.createCompoundBorder(null, javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))));

        DatosEmpresa.setBackground(new java.awt.Color(255, 255, 255));
        DatosEmpresa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblnombre.setFont(new java.awt.Font("Baskerville Old Face", 0, 24)); // NOI18N
        lblnombre.setText("Nombre Empresa");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Icono Factura.png"))); // NOI18N

        jLabel3.setText("RUC:");

        jLabel4.setText("TELEFONO:");

        jLabel5.setText("PROPIETARIO:");

        lblruc.setText("numero");

        lbltelefono.setText("telefono");

        lblbpropietario.setText("nombre propietario");

        javax.swing.GroupLayout DatosEmpresaLayout = new javax.swing.GroupLayout(DatosEmpresa);
        DatosEmpresa.setLayout(DatosEmpresaLayout);
        DatosEmpresaLayout.setHorizontalGroup(
            DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DatosEmpresaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(DatosEmpresaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblbpropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblruc, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(DatosEmpresaLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lblnombre))))
        );
        DatosEmpresaLayout.setVerticalGroup(
            DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DatosEmpresaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(DatosEmpresaLayout.createSequentialGroup()
                        .addComponent(lblnombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(lblruc, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lbltelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(DatosEmpresaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblbpropietario, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        NumeroFactura.setBackground(new java.awt.Color(255, 255, 255));
        NumeroFactura.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        NumeroFactura.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FACTURA");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(93, 93, 93)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        NumeroFactura.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1, 14, 268, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("N°");
        NumeroFactura.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 84, -1, -1));

        lblnumfact.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblnumfact.setText("00000");
        NumeroFactura.add(lblnumfact, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 84, -1, -1));

        DatosCliente.setBackground(new java.awt.Color(255, 255, 255));
        DatosCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        DatosCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setText("Nombre:");
        DatosCliente.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 50, -1));

        jLabel8.setText("Direccion:");
        DatosCliente.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, -1, -1));

        jLabel9.setText("Cedula:");
        DatosCliente.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel11.setText("Telefono:");
        DatosCliente.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));
        DatosCliente.add(TextNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 228, -1));

        TextCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextCedulaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextCedulaKeyTyped(evt);
            }
        });
        DatosCliente.add(TextCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 227, -1));
        DatosCliente.add(TextDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 320, -1));

        TextTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextTelefonoKeyTyped(evt);
            }
        });
        DatosCliente.add(TextTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 320, -1));

        TablaDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo Producto", "Cantidad", "Descripcion", "Precio Unitario", "Precio Total"
            }
        ));
        TablaDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaDetallesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(TablaDetalles);
        if (TablaDetalles.getColumnModel().getColumnCount() > 0) {
            TablaDetalles.getColumnModel().getColumn(0).setHeaderValue("Codigo Producto");
            TablaDetalles.getColumnModel().getColumn(1).setHeaderValue("Cantidad");
            TablaDetalles.getColumnModel().getColumn(2).setHeaderValue("Descripcion");
            TablaDetalles.getColumnModel().getColumn(3).setHeaderValue("Precio Unitario");
            TablaDetalles.getColumnModel().getColumn(4).setHeaderValue("Precio Total");
        }

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel12.setText("Iva:");

        TextTotal.setEditable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle del producto"));

        TextNombrep.setEditable(false);

        jLabel13.setText("Producto:");

        jLabel14.setText("Precio:");

        TextCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextCantidadKeyTyped(evt);
            }
        });

        jLabel15.setText("Marca:");

        jLabel16.setText("Cantidad:");

        jLabel17.setText("Importe:");

        TextImporte.setEditable(false);

        TextMarca.setEditable(false);

        TextPrecio.setEditable(false);

        jLabel18.setText("IdProducto:");

        TextCodigop.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(TextCodigop, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(27, 27, 27)
                        .addComponent(TextNombrep, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(TextPrecio))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(TextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(TextCantidad)
                    .addComponent(TextImporte, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
                .addContainerGap(105, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(TextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextNombrep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(TextImporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(TextPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(TextCodigop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel19.setText("TOTAL:");

        TextSubtotal.setEditable(false);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel20.setText("Subtotal:");

        TextIva.setEditable(false);

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelFacturaLayout = new javax.swing.GroupLayout(PanelFactura);
        PanelFactura.setLayout(PanelFacturaLayout);
        PanelFacturaLayout.setHorizontalGroup(
            PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFacturaLayout.createSequentialGroup()
                .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFacturaLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(PanelFacturaLayout.createSequentialGroup()
                                    .addComponent(DatosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(49, 49, 49)
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(NumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(DatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(PanelFacturaLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(TextIva, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jLabel20)
                        .addGap(28, 28, 28)
                        .addComponent(TextSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(28, 28, 28)
                        .addComponent(TextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PanelFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelFacturaLayout.setVerticalGroup(
            PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelFacturaLayout.createSequentialGroup()
                .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFacturaLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DatosEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelFacturaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)))
                .addComponent(DatosCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(TextSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(TextTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TextIva, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelControles.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Controles", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Search.png"))); // NOI18N
        btnBuscar.setText("BUSCAR");
        btnBuscar.setBorderPainted(false);
        btnBuscar.setContentAreaFilled(false);
        btnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Good-work.png"))); // NOI18N
        btnAgregar.setText("AGREGAR");
        btnAgregar.setBorderPainted(false);
        btnAgregar.setContentAreaFilled(false);
        btnAgregar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Error.png"))); // NOI18N
        btnQuitar.setText("QUITAR");
        btnQuitar.setBorderPainted(false);
        btnQuitar.setContentAreaFilled(false);
        btnQuitar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelControlesLayout = new javax.swing.GroupLayout(PanelControles);
        PanelControles.setLayout(PanelControlesLayout);
        PanelControlesLayout.setHorizontalGroup(
            PanelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelControlesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(PanelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnQuitar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        PanelControlesLayout.setVerticalGroup(
            PanelControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelControlesLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnQuitar)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        Vender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/Vender (2).png"))); // NOI18N
        Vender.setBorderPainted(false);
        Vender.setContentAreaFilled(false);
        Vender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VenderActionPerformed(evt);
            }
        });

        txtFecha.setFont(new java.awt.Font("Castellar", 0, 36)); // NOI18N
        txtFecha.setText("Fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(txtFecha))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PanelControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(Vender, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelControles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(Vender, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(PanelFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        agregar();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        try {
            BuscarProductoFactura factu = new BuscarProductoFactura();
            factu.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void TextCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextCantidadKeyReleased
        try
        {
            cantidad();
            if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            {
                if(TextCantidad.getText().equals(""))
                {
                    LabelEstadoV.setText("No existe producto alguno...."); 
                }
            else
            {
                agregar();            
            }
            }
        }
        catch(Exception e)
        {
            LabelEstadoV.setText("Error: "+e); 
        }
       
    }//GEN-LAST:event_TextCantidadKeyReleased

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        model.removeRow(TablaDetalles.getSelectedRow());
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void VenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VenderActionPerformed
        vender();
    }//GEN-LAST:event_VenderActionPerformed

    private void TextCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextCedulaKeyTyped
        try{
        
            char c1=evt.getKeyChar();                    
            if(Character.isLetter(c1)) {
              getToolkit().beep();    
              evt.consume();             
              JOptionPane.showMessageDialog(null,"Ingrese solo numeros");      
            } 
          else if (TextCedula.getText().length() == 13) {
            JOptionPane.showMessageDialog(null, "Demaciados numeros");
            TextCedula.grabFocus();
            TextCedula.setText("");
        }
          
        }catch(HeadlessException e){
            JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_TextCedulaKeyTyped

    private void TextTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextTelefonoKeyTyped
          char c2=evt.getKeyChar();                    
          if(Character.isLetter(c2)) {
              getToolkit().beep();    
              evt.consume();             
              JOptionPane.showMessageDialog(null,"Ingrese solo numeros");
               
          } 
    }//GEN-LAST:event_TextTelefonoKeyTyped

    private void TextCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextCantidadKeyTyped
          char c3=evt.getKeyChar();                    
          if(Character.isLetter(c3)) {
              getToolkit().beep();    
              evt.consume();             
              JOptionPane.showMessageDialog(null,"Ingrese solo numeros");
               
          } 
    }//GEN-LAST:event_TextCantidadKeyTyped

    private void TextCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextCedulaKeyReleased
        
        //En este metodo se aplica una logica distinta al de vender para verificar la existencia del usuario
        // se comprueba si es igual a 1111 y si es falsa el numero de cedula
        //luego se comprueba si el numero es  valido
        //por ultimo se comprueba si el numero es invalido
        cedula= TextCedula.getText();
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(cedula.equals(""))
            {
               JOptionPane.showMessageDialog(null, "No ha digitado cedula","Alerta",JOptionPane.WARNING_MESSAGE); 
            }
            else
            {       
                if((cedula.equals("1111")) && (funcionalidad.validadorDeCedula(cedula)==false)){
                    verificaUsuario();
                }
                if(funcionalidad.validadorDeCedula(cedula)==true){
                    verificaUsuario();
                }
                if((!cedula.equals("1111")) &&funcionalidad.validadorDeCedula(cedula)==false){
                    JOptionPane.showMessageDialog(null,"La cedula es incorrecta");
                }
            }

        }
    }//GEN-LAST:event_TextCedulaKeyReleased

    private void TablaDetallesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaDetallesKeyReleased
        // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_DELETE)
    {
        eliminar();
    }
    }//GEN-LAST:event_TablaDetallesKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String cedu=TextCedula.getText();
        if(funcionalidad.validadorDeCedula(cedula)==false){
            System.out.println("Falso");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel DatosCliente;
    private javax.swing.JPanel DatosEmpresa;
    private javax.swing.JPanel NumeroFactura;
    private javax.swing.JPanel PanelControles;
    private javax.swing.JPanel PanelFactura;
    private javax.swing.JTable TablaDetalles;
    private javax.swing.JTextField TextCantidad;
    private javax.swing.JTextField TextCedula;
    public static javax.swing.JTextField TextCodigop;
    private javax.swing.JTextField TextDireccion;
    private javax.swing.JTextField TextImporte;
    private javax.swing.JTextField TextIva;
    public static javax.swing.JTextField TextMarca;
    private javax.swing.JTextField TextNombre;
    public static javax.swing.JTextField TextNombrep;
    public static javax.swing.JTextField TextPrecio;
    private javax.swing.JTextField TextSubtotal;
    private javax.swing.JTextField TextTelefono;
    private javax.swing.JTextField TextTotal;
    private javax.swing.JButton Vender;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblbpropietario;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lblnumfact;
    private javax.swing.JLabel lblruc;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JLabel txtFecha;
    // End of variables declaration//GEN-END:variables

    private void HabilitarTextoCliente(){              
        TextNombre.setEditable(true);
        TextTelefono.setEditable(true);
        TextDireccion.setEditable(true);
    }
    private void DesabilitarTextoCliente(){              
        TextNombre.setEditable(false);
        TextTelefono.setEditable(false);
        TextDireccion.setEditable(false);
    }
        
    private void limpiarSeccionArticulo(){
        TextNombrep.setText("");
        TextMarca.setText("");
        TextPrecio.setText("0.0");
        TextCantidad.setText("");
        TextImporte.setText("");
        TextCodigop.setText("");
        TextPrecio.setText("");
    }
    private void LimpiarDatosCliente(){
        TextNombre.setText("");
        TextTelefono.setText("");
        TextDireccion.setText("");        
    }
    private void limpiacontroles() {
        
        TextCedula.setText("");
        TextNombre.setText("");
        TextTelefono.setText("");
        TextDireccion.setText("");
        TextIva.setText("");
        TextSubtotal.setText("");
        TextTotal.setText("");     

        int filas = TablaDetalles.getRowCount();
        for (int i = 0; filas > i; i++) {
            model.removeRow(0);
        }

    }

    private void calcularTotales() {
        try {
            int i;
            subtotal = 0;
            igv = 0;
            total = 0;
            int filas = model.getRowCount();
            for (i = 0; i < filas; i++) {
                total += Double.parseDouble(TablaDetalles.getValueAt(i, 4).toString());
            }
            igv = total * 0.18;
            subtotal = total - igv;
            TextIva.setText(String.format("%.2f", igv));
            TextSubtotal.setText(String.format("%.2f", subtotal));
            TextTotal.setText(String.format("%.2f", total));
        } catch (NumberFormatException e) {
            LabelEstadoV.setText(e.getMessage());
        }
    }

}
