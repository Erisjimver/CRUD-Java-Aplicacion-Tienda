package Vista;
import Modelo.Conexion;
import Controlador.SettersAndGetters;
import Modelo.CRUD;
import static Vista.EntornoAdmin.LabelEstado;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Categorias extends javax.swing.JPanel {
    private static ResultSet r;
    private static PreparedStatement ps;
    private String codigo;
    private int idcate=0;
    
    Conexion cn=new Conexion();  
    Connection c= cn.conexion();

    
    DefaultTableModel modelo = new DefaultTableModel();
    int cantidadColumnas;
    private Component rootPane;
    
    public Categorias() {
        initComponents();     
        this.TablaCategorias.setModel(modelo);
        buscarColumnas();
        idCategoria();
    }
    
    
    public void buscarColumnas(){      
        try{ 
            r = cn.consultar("select IdCategoria,Descripcion from Categoria"); 
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

    public void buscar(){
        limpiarTabla();
       try
       {
            r = cn.consultar("select IdCategoria,Descripcion from Categoria");  
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
            if(TextDescripcion.getText().equals(""))
            {
              LabelEstado.setText("Ingrese una descripcion porfavor...");   
            }
            else
            {
                
                SettersAndGetters pp=new SettersAndGetters();
                CRUD mi=new CRUD();
                pp.setDescripcion(TextDescripcion.getText());
                mi.registrarCategorias(pp);
            
            JOptionPane.showMessageDialog(null, "Categoria registrada....");
           
            limpiar();
            idCategoria();
            
            }
        }
        catch(Exception e)
        {         
            LabelEstado.setText("Error: "+e); 
        }
    }
       
    public void eliminar(){
    
    int fila = TablaCategorias.getSelectedRow();
        try
        {
            ps = c.prepareStatement("delete from Categoria where IdCategoria=?");
            ps.setString(1, TablaCategorias.getValueAt(fila, 0).toString());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(rootPane, "Categoria eliminada");
            }
            buscar();
        }
        catch(SQLException | HeadlessException ext)
        {
            LabelEstado.setText("Error: "+ext); 
        }
    }
    
    public void actualizar(){
        
        try{

            int fila = TablaCategorias.getSelectedRow();
            ps = c.prepareCall("{call ActualizarCategorias(?,?)}");
            ps.setInt(1, Integer.parseInt(TablaCategorias.getValueAt(fila, 0).toString()));
            ps.setString(2, TablaCategorias.getValueAt(fila, 1).toString());
 
            int i=ps.executeUpdate();
            if(i==1){
                JOptionPane.showMessageDialog(rootPane, "Sucursal actualizada");
               /// c.close();

            }
        }catch(SQLException | NumberFormatException | HeadlessException ex)
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
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        TextID = new javax.swing.JTextField();
        TextDescripcion = new javax.swing.JTextField();
        BtnRegistrar = new javax.swing.JButton();
        BtnBuscarCategorias = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        Buscar = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaCategorias = new javax.swing.JTable();

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
        jLabel3.setText("CATEGORIAS");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(977, Short.MAX_VALUE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(521, 521, 521)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(233, 233, 233))))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        Crear.setBackground(new java.awt.Color(36, 47, 65));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("CODIGO CATEGORIA:");

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("DESCRIPCION:");

        TextID.setEditable(false);
        TextID.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextID.setBorder(null);
        TextID.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        TextDescripcion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextDescripcion.setBorder(null);
        TextDescripcion.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextDescripcion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextDescripcionMouseClicked(evt);
            }
        });

        BtnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        BtnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#bloc.png"))); // NOI18N
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
        BtnBuscarCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#busqueda.png"))); // NOI18N
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
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Error.png"))); // NOI18N
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
                .addGap(25, 25, 25)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(30, 30, 30)
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TextDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(47, 47, 47)
                        .addComponent(BtnBuscarCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        CrearLayout.setVerticalGroup(
            CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(TextID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnBuscarCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(58, 58, 58))
        );

        Buscar.setBackground(new java.awt.Color(36, 47, 65));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        TablaCategorias.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaCategorias.setForeground(new java.awt.Color(51, 51, 51));
        TablaCategorias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null}
            },
            new String [] {
                "ID", "Descripcion"
            }
        ));
        TablaCategorias.setGridColor(new java.awt.Color(255, 255, 255));
        TablaCategorias.setRowHeight(20);
        TablaCategorias.setSelectionBackground(new java.awt.Color(1, 198, 83));
        TablaCategorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaCategoriasKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TablaCategorias);

        javax.swing.GroupLayout BuscarLayout = new javax.swing.GroupLayout(Buscar);
        Buscar.setLayout(BuscarLayout);
        BuscarLayout.setHorizontalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
        );
        BuscarLayout.setVerticalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1198, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Crear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 464, Short.MAX_VALUE)
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
                    .addGap(0, 135, Short.MAX_VALUE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TextDescripcionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextDescripcionMouseClicked
        TextDescripcion.setText("");
    }//GEN-LAST:event_TextDescripcionMouseClicked

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
       
        registrar();
        
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnBuscarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCategoriasActionPerformed
    
        buscar();
        
    }//GEN-LAST:event_BtnBuscarCategoriasActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed

        eliminar();
        
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void TablaCategoriasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaCategoriasKeyReleased
              
    if(evt.getKeyCode()==KeyEvent.VK_ENTER)
    {
        actualizar();
    }
    if(evt.getKeyCode()==KeyEvent.VK_DELETE)
    {
        eliminar();
    }
    
    }//GEN-LAST:event_TablaCategoriasKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarCategorias;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JPanel Buscar;
    private javax.swing.JPanel Crear;
    private javax.swing.JTable TablaCategorias;
    private javax.swing.JTextField TextDescripcion;
    private javax.swing.JTextField TextID;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

    public void limpiar(){
    
        TextID.setText("");
        TextDescripcion.setText("");
        
    }

    public void idCategoria(){
     try{
        r = cn.consultar("select max(IdCategoria)+1 from Categoria");
        if(r.next()){
        idcate=r.getInt(1);
        codigo=String.valueOf(idcate);
        TextID.setText(codigo); 
        }
     }catch(SQLException e)
     {
       LabelEstado.setText("Error: "+e); 
     }         
}

}
