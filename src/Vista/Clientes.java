package Vista;
import Controlador.Funcionalidades;
import Controlador.SettersAndGetters;
import Modelo.CRUD;
import static Vista.EntornoAdmin.LabelEstado;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public final class Clientes extends javax.swing.JPanel {

    //declarando variables
    private static ResultSet r;
    private String cedula;
    int idsucur=0,cantidadColumnas,idv;
    
    //invocacion de clases   
    Funcionalidades fun = new Funcionalidades();
    SettersAndGetters set=new SettersAndGetters();
    CRUD crud=new CRUD();

    //creando objetos de la tabla, y los jComboBox
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel comboEmpresa = new DefaultComboBoxModel();
    
    
    public Clientes() throws Exception {
        initComponents();     
        this.TablaClientes.setModel(modelo);
        buscarColumnas();
        TextCedula.setHorizontalAlignment(JTextField.RIGHT);
    }
    
    
    private void buscarColumnas(){      
        try{ 
            r = crud.consultarTodosClientes();
            ResultSetMetaData rsd = r.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsd.getColumnLabel(i));
            }           
        }
        catch(SQLException e)
        {
           LabelEstado.setText("Error en buscarColumnas(): "+e); 
        }
    }

    private void buscarClientes(){
        limpiarTabla();
       try
       {
           r=crud.consultarTodosClientes();
            while(r.next()){ 
                Object [] fila = new Object[cantidadColumnas];
                for (int i=0;i<cantidadColumnas;i++)
                fila[i] = r.getObject(i+1);             
                modelo.addRow(fila);
            } 
       }
       catch(SQLException e)
       {
          LabelEstado.setText("Error en buscarClientes(): "+e);  
       } 
    }

    private void buscarClientesCedula(){
        limpiarTabla();
       try
       {
           cedula = TextCedula.getText();
           r=crud.consultarClientes(cedula);
            while(r.next()){ 
                Object [] fila = new Object[cantidadColumnas];
                for (int i=0;i<cantidadColumnas;i++)
                fila[i] = r.getObject(i+1);             
                modelo.addRow(fila);
            } 
       }
       catch(SQLException e)
       {
          LabelEstado.setText("Error en buscarClientes(): "+e);  
       } 
    }
     
    private void limpiarTabla(){         
          try{          
            int a =modelo.getRowCount()-1;           
            for(int i=a; i>=0; i--){
                modelo.removeRow(i );
            } 
          }catch(Exception e){
             LabelEstado.setText("Error en limpiarTabla(): "+e); 
          }
      }

    private void actualizar(){
      
        try{

        int fila = TablaClientes.getSelectedRow();
            
        set.setIdCliente(Integer.parseInt(TablaClientes.getValueAt(fila, 0).toString()));
        set.setNombreCliente(TablaClientes.getValueAt(fila, 2).toString());
        set.setTelefonoCliente(TablaClientes.getValueAt(fila, 3).toString());
        set.setDireccionCliente(TablaClientes.getValueAt(fila, 4).toString());
        set.setEmailCliente(TablaClientes.getValueAt(fila, 5).toString());
            
            crud.actualizarCliente(set);
            buscarClientes();
            
        }
        
        catch(NumberFormatException ex)
        {
            LabelEstado.setText("Error en actualizar(): "+ex);   
        }
    }                
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Empleados = new javax.swing.JLabel();
        Buscar = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        TextCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        BtnLimpiar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

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

        Empleados.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Empleados.setForeground(new java.awt.Color(255, 255, 255));
        Empleados.setText("Clientes");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(Empleados)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(Empleados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Buscar.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Search.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("INGRESE CEDULA DEL CLIENTE:");

        TextCedula.setBackground(new java.awt.Color(97, 212, 195));
        TextCedula.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        TextCedula.setForeground(new java.awt.Color(255, 255, 255));
        TextCedula.setBorder(new javax.swing.border.MatteBorder(null));
        TextCedula.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        TextCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextCedulaKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/jovenes.png"))); // NOI18N

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        TablaClientes.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaClientes.setForeground(new java.awt.Color(51, 51, 51));
        TablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        TablaClientes.setGridColor(new java.awt.Color(255, 255, 255));
        TablaClientes.setRowHeight(20);
        TablaClientes.setSelectionBackground(new java.awt.Color(1, 198, 83));
        TablaClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaClientesKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TablaClientes);

        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Error.png"))); // NOI18N
        BtnLimpiar.setBorderPainted(false);
        BtnLimpiar.setContentAreaFilled(false);
        BtnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnLimpiar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnLimpiar.setIconTextGap(-4);
        BtnLimpiar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout BuscarLayout = new javax.swing.GroupLayout(Buscar);
        Buscar.setLayout(BuscarLayout);
        BuscarLayout.setHorizontalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131)
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addComponent(TextCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(123, 239, Short.MAX_VALUE))
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        BuscarLayout.setVerticalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BuscarLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(TextCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(BtnLimpiar)))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
            .addComponent(Buscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TablaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaClientesKeyReleased
        
        
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        try {
            actualizar();
        } catch (Exception ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    }//GEN-LAST:event_TablaClientesKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(TextCedula.getText().equals("")){
            buscarClientes();
        }
        else{
            buscarClientesCedula();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        limpiarTabla();
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void TextCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextCedulaKeyReleased
        if(TextCedula.getText().equals("")){
            buscarClientes();
        }
        else{
            buscarClientesCedula();
        }
    }//GEN-LAST:event_TextCedulaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JPanel Buscar;
    private javax.swing.JLabel Empleados;
    private javax.swing.JTable TablaClientes;
    private javax.swing.JTextField TextCedula;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables


}
