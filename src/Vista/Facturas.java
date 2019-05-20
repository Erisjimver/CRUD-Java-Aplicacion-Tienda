package PackAdmin;
import Modelo.Conexion;
import static PackAdmin.EntornoAdmin.LabelEstado;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Facturas extends javax.swing.JPanel {

    static Statement s;
    static ResultSet rs;
    private String num,consulta,con;
    Conexion f = new Conexion(); 
    Connection c=f.conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    int cantidadColumnas;
    
    public Facturas() {
        initComponents();
        this.TablaFactura.setModel(modelo);
        BuscarProductos();
        f.conexion();   
    }
    
    
   public void BuscarProductos()
    {      
        try{
           rs = f.consultar("select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto");  
            ResultSetMetaData rsd = rs.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsd.getColumnLabel(i));
            }           
        }
        catch(SQLException e)
        {
           System.out.println("error: "+e); 
        }
    }
 
    public void BuscarProductos2()
    {

        try{
            num=TextNF.getText();
            
            if(RadioButtonNF.isSelected()==false&&RadioButtonFecha.isSelected()==false&&RadioButtonTodo.isSelected()==false)
            {
               LabelEstado.setText("No ha selecionado ninguna opcion");//mensaje de alerta
            }
            else
            {
            
                    if(RadioButtonTodo.isSelected())
                    {
                        LabelEstado.setText("");//mensaje de alerta
                        consulta= "select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto";
                        consultar(consulta);//se llama al metodo que realiza la consulta
                    }
            
                  //  if(RadioButtonNF.isSelected()==true&&num.equals("")) 
                    if(RadioButtonNF.isSelected()==true) 
                    {
                        LabelEstado.setText("");//mensaje de alerta
                        if(num.equals(""))
                        {
                            LabelEstado.setText("Ingrese el numero de la factura"); 
                        }
                        else
                        {
                            consulta= "select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto where f.IdFactura='"+num+"'";
                            consultar(consulta);//se llama al metodo que realiza la consulta
                        }
                    }

                    if((RadioButtonFecha.isSelected()==true))
                    {
                        if(DateChooserFactura.getDate()==null)
                        {
                        LabelEstado.setText("No ha seleccionado fecha alguna");//mensaje de alerta 
                        }
                        else
                        {
                        java.util.Date fecha=DateChooserFactura.getDate();
                        SimpleDateFormat formatofecha= new SimpleDateFormat("dd/MM/YYYY");
                        String fec=formatofecha.format(fecha);

                        consulta= "select f.IdFactura, dv.Cantidad, p.NombreProducto,dv.ValorTotal,f.FechaEmision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto where f.FechaEmision='"+fec+"'";  
                        consultar(consulta);//se llama al metodo que realiza la consulta
                        }
                    }
           
            }
  
        }
        catch(HeadlessException e)
        {
            LabelEstado.setText(e.toString());
        }
    }
    
    public void consultar(String conect){
           con=conect;
           try { 
                rs =f.consultar(con);         
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
            LabelEstado.setText(ex.toString());
        }  
    }
    
    public void radioButtoNumeroFactura(){
        
        try{
            
                TextNF.setEnabled(true);
                TextNF.requestFocus();
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
                TextNF.setEnabled(false);
                TextNF.setText("");

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
            TextNF.setText("");
            TextNF.setEnabled(false);     
            
        }
        catch(Exception e)
        {
            LabelEstado.setText("Error: "+e);
        }
    }
    
    public void borrarTabla(){
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
        TextNF = new javax.swing.JTextField();
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
                .addGap(518, 518, 518)
                .addComponent(jLabel2)
                .addContainerGap(554, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        TablaFactura.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaFactura.setForeground(new java.awt.Color(51, 51, 51));
        TablaFactura.setGridColor(new java.awt.Color(255, 255, 255));
        TablaFactura.setRowHeight(20);
        TablaFactura.setSelectionBackground(new java.awt.Color(1, 198, 83));
        jScrollPane1.setViewportView(TablaFactura);

        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/#busqueda.png"))); // NOI18N
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

        TextNF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TextNFKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextNFKeyTyped(evt);
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

        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/#Error.png"))); // NOI18N
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
                            .addComponent(TextNF, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(DateChooserFactura, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(RadioButtonTodo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(BtnBuscar)
                .addGap(30, 30, 30)
                .addComponent(BtnLimpiar)
                .addGap(447, 447, 447))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscar)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(RadioButtonNF)
                            .addComponent(TextNF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(RadioButtonFecha)
                            .addComponent(DateChooserFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(RadioButtonTodo))
                    .addComponent(BtnLimpiar))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE))
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
        BuscarProductos2();
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
       
        borrarTabla();

    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void TextNFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNFKeyTyped
          char ca=evt.getKeyChar(); 
          
          if(Character.isLetter(ca)) {
              getToolkit().beep();    
              evt.consume();             
              JOptionPane.showMessageDialog(null,"Ingrese solo numeros");      
          } 
    }//GEN-LAST:event_TextNFKeyTyped

    private void TextNFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextNFKeyReleased
        
    if(evt.getKeyCode()==KeyEvent.VK_ENTER)
    {
           BuscarProductos2();
    }
  /*  if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
        System.exit(0);
    }
     else{
                    
    }*/
    }//GEN-LAST:event_TextNFKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnLimpiar;
    private com.toedter.calendar.JDateChooser DateChooserFactura;
    private javax.swing.ButtonGroup GrupoIngresos;
    private javax.swing.JRadioButton RadioButtonFecha;
    private javax.swing.JRadioButton RadioButtonNF;
    private javax.swing.JRadioButton RadioButtonTodo;
    private javax.swing.JTable TablaFactura;
    private javax.swing.JTextField TextNF;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
