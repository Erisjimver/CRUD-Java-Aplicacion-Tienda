package Vista;
import Modelo.CRUD;
import static Vista.EntornoAdmin.LabelEstado;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Facturas extends javax.swing.JPanel {

    //declaracion de variables
    private static ResultSet rs;
    private String num;
    private int cantidadColumnas;
    
    //creando objeto de la tabla pro defecto
    DefaultTableModel modelo = new DefaultTableModel();
    
    //creando objeto de las clases
    CRUD crud = new CRUD();
    
    public Facturas() {
        initComponents();
        this.TablaFactura.setModel(modelo);
        llenarIndiceTabla();
  
    }
    
    
   public void llenarIndiceTabla()
    {      
        try{
            rs=crud.consultarFacturas();
            ResultSetMetaData rsd = rs.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsd.getColumnLabel(i));
            }           
        } catch (SQLException ex) {
            LabelEstado.setText("No existe indice: "+ex);
        }
    } 
   

    //verifica si ha sido seleccionada una opcion
    private void verificaSeleccion(){
        
        try 
        {
            if(RadioButtonNF.isSelected()==false&&RadioButtonFecha.isSelected()==false&&RadioButtonTodo.isSelected()==false)
            {
               //LabelEstado.setText("No ha selecionado ninguna opcion");//mensaje de alerta
               JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna opcion","Alerta",JOptionPane.WARNING_MESSAGE); 
            }
            else
            {
                if(RadioButtonTodo.isSelected()==true){
                        rs= crud.consultarFacturas();
                        consultar();//se llama al metodo que realiza la crud
                }
                if(RadioButtonNF.isSelected()==true&&TextNumeroFactura.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "No ha digitado el codigo factura","Alerta",JOptionPane.WARNING_MESSAGE); 
                }
                if((RadioButtonNF.isSelected()==true&&!TextNumeroFactura.getText().equals(""))){
                        num=TextNumeroFactura.getText();
                        rs = crud.consultarFacturasId(Integer.parseInt(num));
                        consultar();//se llama al metodo que realiza la crud
                }
                if((RadioButtonFecha.isSelected()==true)&&DateChooserFactura.getDate()==null){
                    JOptionPane.showMessageDialog(null, "No ha selecionado una fecha","Alerta",JOptionPane.WARNING_MESSAGE); 
                }
                if((RadioButtonFecha.isSelected()==true)&&DateChooserFactura.getDate()!=null){
                        java.util.Date fecha=DateChooserFactura.getDate();
                        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
                        String fec=formatofecha.format(fecha);

                        rs=crud.consultarFacturasFecha(fec);
                        consultar();//se llama al metodo que realiza la crud
                }
            }
        }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null, "Error en verificaSeleccion(): "+e,"Alerta",JOptionPane.WARNING_MESSAGE);
        }        
    }
    
    
    public void consultar(){
 
           try { 
                       
                while(rs.next())
                { 
                    Object [] fila = new Object[cantidadColumnas];
                    for (int i=0;i<cantidadColumnas;i++)
                    fila[i] = rs.getObject(i+1);
                    modelo.addRow(fila);
                }
        } 
           catch (SQLException ex) 
        {
            Logger.getLogger(EntornoAdmin.class.getName()).log(Level.SEVERE, null, ex);
           // LabelEstado.setText("El error es en consultar(): "+ex.toString());
        }  
    }
    
    public void radioButtoNumeroFactura(){
        
        try{
                TextNumeroFactura.setEditable(true);
                TextNumeroFactura.requestFocus();
                DateChooserFactura.setDate(null);
                DateChooserFactura.setEnabled(false); 
    
            }
        catch(Exception e)
            {
                LabelEstado.setText("Error: "+e);
            }
    }
    
    public void radioButtonFecha(){
        
        try{        
          
                DateChooserFactura.setEnabled(true);
                TextNumeroFactura.setEditable(false);
                TextNumeroFactura.setText("");

        }
        catch(Exception e)
            {
                LabelEstado.setText("Error: "+e);
            }
        
    }
    
    public void radioButtonTodo(){
        
        try{

            DateChooserFactura.setEnabled(false);
            DateChooserFactura.setDate(null);
            TextNumeroFactura.setText("");
            TextNumeroFactura.setEditable(false);     
            
        }
        catch(Exception e)
        {
            LabelEstado.setText("Error: "+e);
        }
    }
    
    public void limpiarTabla(){
            int filas = TablaFactura.getRowCount();
            for (int i = 0; filas > i; i++) {
            modelo.removeRow(0);
        }
            
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        GrupoIngresos = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFactura = new javax.swing.JTable();
        BtnBuscar = new javax.swing.JButton();
        RadioButtonNF = new javax.swing.JRadioButton();
        RadioButtonFecha = new javax.swing.JRadioButton();
        TextNumeroFactura = new javax.swing.JTextField();
        RadioButtonTodo = new javax.swing.JRadioButton();
        BtnLimpiar = new javax.swing.JButton();
        DateChooserFactura = new com.toedter.calendar.JDateChooser();

        jToolBar1.setRollover(true);

        setPreferredSize(new java.awt.Dimension(1198, 579));

        jPanel1.setBackground(new java.awt.Color(1, 198, 83));

        jLabel2.setFont(new java.awt.Font("Century751 No2 BT", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("FACTURAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(528, 528, 528)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        TablaFactura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaFactura.setForeground(new java.awt.Color(51, 51, 51));
        TablaFactura.setGridColor(new java.awt.Color(255, 255, 255));
        TablaFactura.setRowHeight(20);
        TablaFactura.setSelectionBackground(new java.awt.Color(1, 198, 83));
        jScrollPane1.setViewportView(TablaFactura);

        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#busqueda.png"))); // NOI18N
        BtnBuscar.setBorderPainted(false);
        BtnBuscar.setContentAreaFilled(false);
        BtnBuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BtnBuscar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        BtnBuscar.setIconTextGap(-4);
        BtnBuscar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        BtnBuscar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        GrupoIngresos.add(RadioButtonNF);
        RadioButtonNF.setText("Buscar por numero de factura:");
        RadioButtonNF.setContentAreaFilled(false);
        RadioButtonNF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonNFActionPerformed(evt);
            }
        });

        GrupoIngresos.add(RadioButtonFecha);
        RadioButtonFecha.setText("Buscar por fecha:");
        RadioButtonFecha.setContentAreaFilled(false);
        RadioButtonFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonFechaActionPerformed(evt);
            }
        });

        TextNumeroFactura.setEditable(false);
        TextNumeroFactura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextNumeroFacturaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextNumeroFacturaKeyTyped(evt);
            }
        });

        GrupoIngresos.add(RadioButtonTodo);
        RadioButtonTodo.setText("Mostrar todo");
        RadioButtonTodo.setContentAreaFilled(false);
        RadioButtonTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RadioButtonTodoActionPerformed(evt);
            }
        });

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

        DateChooserFactura.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioButtonFecha)
                            .addComponent(RadioButtonNF))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(TextNumeroFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(DateChooserFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(RadioButtonTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(498, 498, 498))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RadioButtonNF)
                            .addComponent(TextNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioButtonFecha)
                            .addComponent(DateChooserFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RadioButtonTodo))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(BtnLimpiar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                        .addComponent(BtnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
    //        if(RadioButtonNF.isSelected()==false&&RadioButtonFecha.isSelected()==false&&RadioButtonTodo.isSelected()==false)
   //         {
               //LabelEstado.setText("No ha selecionado ninguna opcion");//mensaje de alerta
     //          JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna opcion","Alerta",JOptionPane.WARNING_MESSAGE); 
      //      }else{
                limpiarTabla();
                verificaSeleccion();
     //       }       
        
        
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void RadioButtonNFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonNFActionPerformed
    
        radioButtoNumeroFactura();

    }//GEN-LAST:event_RadioButtonNFActionPerformed

    private void RadioButtonFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonFechaActionPerformed

        radioButtonFecha();
    
    }//GEN-LAST:event_RadioButtonFechaActionPerformed

    private void RadioButtonTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RadioButtonTodoActionPerformed

        radioButtonTodo();

    }//GEN-LAST:event_RadioButtonTodoActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
       
        limpiarTabla();

    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void TextNumeroFacturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNumeroFacturaKeyTyped
          char ca=evt.getKeyChar(); 
          
          if(Character.isLetter(ca)) {
              getToolkit().beep();    
              evt.consume();             
              JOptionPane.showMessageDialog(null,"Ingrese solo numeros");      
          } 
    }//GEN-LAST:event_TextNumeroFacturaKeyTyped

    private void TextNumeroFacturaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNumeroFacturaKeyReleased
    String numero = TextNumeroFactura.getText();
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){

            if(numero.equals(""))
            {
                 JOptionPane.showMessageDialog(null, "No ha digitado el codigo factura","Alerta",JOptionPane.WARNING_MESSAGE); 
            }
            else
            {
                limpiarTabla();      
                num=TextNumeroFactura.getText();
                rs = crud.consultarFacturasId(Integer.parseInt(num));
                consultar();//se llama al metodo que realiza la crud
            }
        }
    }//GEN-LAST:event_TextNumeroFacturaKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnLimpiar;
    private com.toedter.calendar.JDateChooser DateChooserFactura;
    private javax.swing.ButtonGroup GrupoIngresos;
    private javax.swing.JRadioButton RadioButtonFecha;
    private javax.swing.JRadioButton RadioButtonNF;
    private javax.swing.JRadioButton RadioButtonTodo;
    private javax.swing.JTable TablaFactura;
    private javax.swing.JTextField TextNumeroFactura;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
