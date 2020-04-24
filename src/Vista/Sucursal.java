package Vista;
import Modelo.Conexion;
import Controlador.SettersAndGetters;
import Modelo.MetodoIngreso;
import static Vista.EntornoAdmin.LabelEstado;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Sucursal extends javax.swing.JPanel {
    private static ResultSet r;
    private static Statement st;
    private static PreparedStatement ps;
    private String cod,ruc,sucursal,telefono,direccion;
    int idsucur=0,cantidadColumnas;

    Conexion cn=new Conexion();  
    Connection c= cn.conexion();
    DefaultTableModel modelo = new DefaultTableModel();

    private Component rootPane;
    
    public Sucursal() {
        initComponents();     
        this.TablaSucursal.setModel(modelo);
        buscarColumnas();
        idSucursal();
    }
    
    
    public void buscarColumnas(){      
        try{ 
            r = cn.consultar("select IdEmpresa,Ruc,NombreEmpresa,Telefono,Direccion from Empresa order by IdEmpresa"); 
            ResultSetMetaData rsd = r.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsd.getColumnLabel(i));
            }           
        }
        catch(SQLException e)
        {
           LabelEstado.setText("Error: "+e); 
        }
    }

    public void buscarSucursales(){
        limpiarTabla();
       try
       {
            r = cn.consultar("select IdEmpresa,Ruc,NombreEmpresa,Telefono,Direccion from Empresa order by IdEmpresa");  
            while(r.next()){ 
              Object [] fila = new Object[cantidadColumnas];
              for (int i=0;i<cantidadColumnas;i++)
              fila[i] = r.getObject(i+1);
               modelo.addRow(fila);

            } 
       }
       catch(SQLException e)
       {
          LabelEstado.setText("Error: "+e);  
       } 
    }
     
    public void limpiarTabla(){
          
          try{
          
            int a =modelo.getRowCount()-1;           
            for(int i=a; i>=0; i--){

                modelo.removeRow(i );
            }
 
          }catch(Exception e){
             LabelEstado.setText("Error: "+e); 
          }
      }
    
    public void registrar(){        
        
        try
        {
            ruc = TextRUC.getText();
            sucursal=TextSucursal.getText();
            telefono=TextTelefono.getText();
            direccion=TextDireccion.getText();
                          
            if(ruc.equals("")||sucursal.equals("")||telefono.equals("")||direccion.equals(""))
            {
              LabelEstado.setText("Existen datos no ingresados...");   
            }
            else
            {
                
                SettersAndGetters pp=new SettersAndGetters();
                MetodoIngreso mi=new MetodoIngreso();
                pp.setIdEmpresa(idsucur);
                pp.setRuc(ruc);
                pp.setSucursal(sucursal);
                pp.setTelefonoEmpresa(Integer.parseInt(telefono));
                pp.setDireccionEmpresa(direccion);
                
                mi.registrarSucursal(pp);
            
            JOptionPane.showMessageDialog(null, "Sucursal registrada....");
           
            limpiar();
            idSucursal();
            
            }
        }
        catch(Exception e)
        {         
            LabelEstado.setText("Error: "+e); 
        }
    }
       
    public void eliminar(){
    
    int fila = TablaSucursal.getSelectedRow();
        try
        {
            ps = c.prepareStatement("delete from Empresa where IdEmpresa=?");
            ps.setString(1, TablaSucursal.getValueAt(fila, 0).toString());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(rootPane, "Sucursal eliminada...................");
            }
            buscarSucursales();
        }
        catch(SQLException | HeadlessException ext)
        {
            LabelEstado.setText("Error: "+ext); 
        }
    }
    
    public void actualizar(){
        
        try{

        int fila = TablaSucursal.getSelectedRow();

            ps = c.prepareCall("{call ActualizarSucursal(?,?,?,?,?)}");
            ps.setInt(1, Integer.parseInt(TablaSucursal.getValueAt(fila, 0).toString()));
            ps.setString(2, TablaSucursal.getValueAt(fila, 1).toString());
            ps.setString(3, TablaSucursal.getValueAt(fila, 2).toString());
            ps.setInt(4, Integer.parseInt(TablaSucursal.getValueAt(fila, 3).toString()));
            ps.setString(5, TablaSucursal.getValueAt(fila, 4).toString());
            int i=ps.executeUpdate();
            if(i==1){
                JOptionPane.showMessageDialog(rootPane, "Sucursal actualizada");
                c.close();
            }
        }
        
        catch(SQLException | NumberFormatException | HeadlessException ex)
        {
            LabelEstado.setText("Error: "+ex);   
        }
    } 
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Crear = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        TextCOS = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TextRUC = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextSucursal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        TextTelefono = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        TextDireccion = new javax.swing.JTextField();
        BtnRegistrar = new javax.swing.JButton();
        BtnBuscarCategorias = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        Buscar = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaSucursal = new javax.swing.JTable();

        setBackground(new java.awt.Color(36, 47, 65));

        header.setBackground(new java.awt.Color(97, 212, 195));
        header.setPreferredSize(new java.awt.Dimension(838, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Keeping UI alive");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ZonaMovil");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("SUCURSAL");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 560, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(250, 250, 250))))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        Crear.setBackground(new java.awt.Color(36, 47, 65));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("CODIGO DE SUCURSAL:");

        TextCOS.setEditable(false);

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("RUC:");

        TextRUC.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextRUC.setBorder(null);
        TextRUC.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextRUC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextRUCMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("NOMBRE DE LA SUCURSAL:");

        TextSucursal.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextSucursal.setBorder(null);
        TextSucursal.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextSucursal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextSucursalMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("TELEFONO:");

        TextTelefono.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextTelefono.setBorder(null);
        TextTelefono.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextTelefonoMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("DIRECCION:");

        TextDireccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextDireccion.setBorder(null);
        TextDireccion.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextDireccionMouseClicked(evt);
            }
        });

        BtnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/#bloc.png"))); // NOI18N
        BtnRegistrar.setBorderPainted(false);
        BtnRegistrar.setContentAreaFilled(false);
        BtnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnRegistrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnRegistrar.setIconTextGap(-4);
        BtnRegistrar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtnRegistrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnRegistrarActionPerformed(evt);
            }
        });

        BtnBuscarCategorias.setForeground(new java.awt.Color(255, 255, 255));
        BtnBuscarCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/#busqueda.png"))); // NOI18N
        BtnBuscarCategorias.setBorderPainted(false);
        BtnBuscarCategorias.setContentAreaFilled(false);
        BtnBuscarCategorias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnBuscarCategorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnBuscarCategorias.setIconTextGap(-4);
        BtnBuscarCategorias.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtnBuscarCategorias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnBuscarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarCategoriasActionPerformed(evt);
            }
        });

        BtnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/#Error.png"))); // NOI18N
        BtnEliminar.setBorderPainted(false);
        BtnEliminar.setContentAreaFilled(false);
        BtnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnEliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnEliminar.setIconTextGap(-4);
        BtnEliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtnEliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CrearLayout = new javax.swing.GroupLayout(Crear);
        Crear.setLayout(CrearLayout);
        CrearLayout.setHorizontalGroup(
            CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearLayout.createSequentialGroup()
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel10))
                                .addGap(21, 21, 21)
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(TextRUC)
                                    .addComponent(TextCOS, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextSucursal))
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12))
                                .addGap(97, 97, 97)
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextDireccion)
                                    .addComponent(TextTelefono)))))
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(BtnBuscarCategorias)
                        .addGap(52, 52, 52)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CrearLayout.setVerticalGroup(
            CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(TextCOS, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10)
                            .addComponent(TextRUC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(TextTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12))
                .addGap(66, 66, 66)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BtnBuscarCategorias)
                    .addComponent(BtnEliminar)
                    .addComponent(BtnRegistrar))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        Buscar.setBackground(new java.awt.Color(36, 47, 65));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        TablaSucursal.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaSucursal.setForeground(new java.awt.Color(51, 51, 51));
        TablaSucursal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        TablaSucursal.setGridColor(new java.awt.Color(255, 255, 255));
        TablaSucursal.setRowHeight(20);
        TablaSucursal.setSelectionBackground(new java.awt.Color(1, 198, 83));
        TablaSucursal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaSucursalKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TablaSucursal);

        javax.swing.GroupLayout BuscarLayout = new javax.swing.GroupLayout(Buscar);
        Buscar.setLayout(BuscarLayout);
        BuscarLayout.setHorizontalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE))
        );
        BuscarLayout.setVerticalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Crear, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 455, Short.MAX_VALUE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Crear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 134, Short.MAX_VALUE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
       
        registrar();
        
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnBuscarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCategoriasActionPerformed
    
        buscarSucursales();
        
    }//GEN-LAST:event_BtnBuscarCategoriasActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed

        eliminar();
        
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void TablaSucursalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaSucursalKeyReleased
        
        
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        actualizar();

    }
    }//GEN-LAST:event_TablaSucursalKeyReleased

    private void TextRUCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextRUCMouseClicked
        TextRUC.setText("");
    }//GEN-LAST:event_TextRUCMouseClicked

    private void TextSucursalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextSucursalMouseClicked
        TextSucursal.setText("");
    }//GEN-LAST:event_TextSucursalMouseClicked

    private void TextTelefonoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextTelefonoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TextTelefonoMouseClicked

    private void TextDireccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextDireccionMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_TextDireccionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarCategorias;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JPanel Buscar;
    private javax.swing.JPanel Crear;
    private javax.swing.JTable TablaSucursal;
    private javax.swing.JTextField TextCOS;
    private javax.swing.JTextField TextDireccion;
    private javax.swing.JTextField TextRUC;
    private javax.swing.JTextField TextSucursal;
    private javax.swing.JTextField TextTelefono;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

    public void limpiar(){
            TextRUC.setText("");
            TextSucursal.setText("");
            TextTelefono.setText("");
            TextDireccion.setText("");
    }

    public void idSucursal(){

     try{
        st = c.createStatement();
        r = st.executeQuery("select max(IdEmpresa)+1 from Empresa");
       // r = st.executeQuery("select IdUsuarios from Usuarios");
        if(r.next()){
        idsucur=r.getInt(1);
        cod=String.valueOf(idsucur);
        TextCOS.setText(cod); 
        }
     }
        catch(SQLException e){
            LabelEstado.setText("Error: "+e);
        }
      }
}
