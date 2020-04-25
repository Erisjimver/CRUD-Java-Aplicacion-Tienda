package Vista;

import Modelo.CRUD;
import Modelo.Conexion;
import static Vista.EntornoAdmin.LabelEstado;
import static Vista.BuscarProFactura.r;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public final class ProductosBuscar extends javax.swing.JPanel {
    
    //declarar variables
    static Statement s;
    static ResultSet rs;
    int cantidadColumnas;
    
    //crear objetos de clase
    Conexion f = new Conexion(); 
    Connection c=f.conexion();
    CRUD crud=new CRUD();
    
    //crear objetos de tabla
    DefaultTableModel modelo = new DefaultTableModel();
    
    public ProductosBuscar() {
        initComponents();
        this.TablaProductos.setModel(modelo);
        buscarColumnas();
        f.conexion();   
    }
    

    
   public void buscarColumnas()
    {      
        try{
           rs = f.consultar("select IdProducto,NombreProducto,Marca,Precio,Stock,FechaIngreso from Producto order by IdProducto"); 
            ResultSetMetaData rsd = rs.getMetaData();
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
   
    public void buscarProductos(){
        
           try { 
            while(rs.next()){ 
              Object [] fila = new Object[cantidadColumnas];
              for (int i=0;i<cantidadColumnas;i++)
              fila[i] = rs.getObject(i+1);
               modelo.addRow(fila);
               fila=null;
            } 
           // rs.close();//cerrar result-set 
        } 
          catch (SQLException ex) { 
           LabelEstado.setText("Error: "+ex); 
        }    
    }
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();
        BtnBuscar = new javax.swing.JButton();
        TextBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1198, 579));

        jPanel1.setBackground(new java.awt.Color(1, 198, 83));

        jLabel2.setFont(new java.awt.Font("Century751 No2 BT", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PRODUCTOS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(518, 518, 518)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel2)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));

        TablaProductos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaProductos.setForeground(new java.awt.Color(51, 51, 51));
        TablaProductos.setGridColor(new java.awt.Color(255, 255, 255));
        TablaProductos.setRowHeight(20);
        TablaProductos.setSelectionBackground(new java.awt.Color(1, 198, 83));
        jScrollPane1.setViewportView(TablaProductos);

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

        TextBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextBuscarKeyTyped(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Elephant", 0, 18)); // NOI18N
        jLabel1.setText("NOMBRE DEL PRODUCTO: ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(477, 477, 477))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
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
            buscarProductos();
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void TextBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextBuscarKeyTyped
        // TODO add your handling code here:
                try {
            String nombre = TextBuscar.getText();
       //     String nombre = TextBuscar.getText().toLowerCase();
            modelo = (DefaultTableModel) TablaProductos.getModel();
            r = crud.buscarProducto(nombre);
            while (TablaProductos.getRowCount() > 0) {
                modelo.removeRow(0);
            }
            if (TextBuscar.getText().isEmpty()) {

            } else {
                while (r.next()) {
                    Object[] regi = {        
                        r.getInt(1), r.getInt(2), r.getString(3), r.getString(4),
                        r.getDouble(5),r.getDouble(6), r.getInt(7), r.getDate(8)
                    };
                    modelo.addRow(regi);
                }
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_TextBuscarKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTextField TextBuscar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
