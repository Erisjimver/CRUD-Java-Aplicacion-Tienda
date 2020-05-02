package Vista;
import Controlador.Funcionalidades;
import Controlador.SettersAndGetters;
import Modelo.CRUD;
import static Vista.EntornoAdmin.LabelEstado;
import static Vista.EntornoVendedor.LabelEstadoV;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

public final class Caja extends javax.swing.JPanel {

    //declarando variables
    private static ResultSet r;
    int idsucur=0,cantidadColumnas,idv;
    
    //invocacion de clases   
    Funcionalidades fun = new Funcionalidades();
    SettersAndGetters set=new SettersAndGetters();
    CRUD crud=new CRUD();

    //creando objetos de la tabla, y los jComboBox
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel comboEmpresa = new DefaultComboBoxModel();
    
    
    public Caja() throws Exception {
        initComponents();     
        this.TablaClientes.setModel(modelo);
        buscarColumnas();
        agregarOyenteDia();
        agregarOyenteMes();
        agregarOyenteAnio();
        agregarOyenteCalendario();
    }
    
    
    private void buscarColumnas(){      
        try{ 
            r = crud.consultarFacturasDetalleTodo();
            ResultSetMetaData rsd = r.getMetaData();
            cantidadColumnas = rsd.getColumnCount();
            for (int i = 1; i <= cantidadColumnas; i++) {
            modelo.addColumn(rsd.getColumnLabel(i));
            }           
        }
        catch(SQLException e)
        {
           LabelEstado.setText("Error en Caja/buscarColumnas(): "+e); 
        }
    }
 
    private void buscarFacturasDetallesDia(String dia){
        try{
            limpiarTabla();
            r=crud.consultarFacturasDetalleDia(dia);
            llenaTabla();
        }catch(Exception e){
            LabelEstadoV.setText("Error en Caja/buscarFacturasDetallesDia1(): "+e);
        }
    } 
    
    private void buscarFacturasDetallesMes(String mes){
        try{
            limpiarTabla();
            r=crud.consultarFacturasDetalleMes(mes);
            llenaTabla();
        }catch(Exception e){
            LabelEstadoV.setText("Error en Caja/buscarFacturasDetallesMes1(): "+e);
        }
    }
    
    private void buscarFacturasDetallesAnio(int anio){
        try{
            limpiarTabla();
            r=crud.consultarFacturasDetalleAnio(anio);
            llenaTabla();
        }catch(Exception e){
            LabelEstadoV.setText("Error en Caja/buscarFacturasDetallesMes1(): "+e);
        }
    }
    
    private void llenaTabla(){
        try
       {
            while(r.next()){
                Object [] fila = new Object[cantidadColumnas];
                    for (int i=0;i<cantidadColumnas;i++)
                    fila[i] = r.getObject(i+1);             
                    modelo.addRow(fila);
            }
       }
       catch(SQLException e)
       {
          LabelEstadoV.setText("Error en Caja/llenaTabla(): "+e);  
       } 
    }
    
    public void limpiarTabla(){         
          try{          
            int a =modelo.getRowCount()-1;           
            for(int i=a; i>=0; i--){
                modelo.removeRow(i );
            } 
          }catch(Exception e){
             LabelEstado.setText("Error en Caja/limpiarTabla(): "+e); 
          }
      }   
   
    private void agregarOyenteDia(){
        DateChooserFactura.getDateEditor().addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().compareTo("day") == 0) {
                    //que no haga nada caso contrario si genera una consulta sin generar datos saldra error
                    //asi se envita arruinar el programa
            }
            else{
                if(DateChooserFactura.getDate()==null){

                }else{
                    java.util.Date fecha=DateChooserFactura.getDate();
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                    String fec=formatoDeFecha.format(fecha);
                    LabelEstadoV.setText(fec);
                    buscarFacturasDetallesDia(fec);
                }
            }
        });
    } 
    
    private void agregarOyenteMes(){
        MonthChooser.addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().compareTo("month") == 0) {
                int mes = (MonthChooser.getMonth());
                buscarFacturasDetallesMes(concatena(mes));
            }            
        });
    }   
    
    private void agregarOyenteAnio(){
        YearChooser.addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().compareTo("year") == 0) {
                int year = (YearChooser.getYear());
                System.out.println(year);
               // buscarFacturasDetallesMes1(concatena(mes));
               buscarFacturasDetallesAnio(year);
            }            
        });
    }
    
    private void agregarOyenteCalendario() {

        Calendario.getDayChooser().addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().compareTo("day") == 0) {
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                LabelEstadoV.setText(formatoDeFecha.format(Calendario.getDate()));
                buscarFacturasDetallesDia(formatoDeFecha.format(Calendario.getDate()));
            }
        });
    }
    
    //convierte el mes en una cifra de dos digitos
    private String concatena(int m){
        int me = m+1;
        System.out.println("M + 1: "+me);
        String m2= String.valueOf(me);
        String mes = "";
        
            if(m2.length()==1){
                mes = "0"+me;
            }
            else{
                mes=m2;
            }
        return mes;
    }
    
    private void mousePressed(MouseEvent ev) 
    {
        System.out.println("holas");
    } 
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        header = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        Empleados = new javax.swing.JLabel();
        Buscar = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaClientes = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        MonthChooser = new com.toedter.calendar.JMonthChooser();
        YearChooser = new com.toedter.calendar.JYearChooser();
        Calendario = new com.toedter.calendar.JCalendar();
        DateChooserFactura = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1199, 560));

        header.setBackground(new java.awt.Color(97, 212, 195));
        header.setPreferredSize(new java.awt.Dimension(838, 200));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        Empleados.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        Empleados.setForeground(new java.awt.Color(255, 255, 255));
        Empleados.setText("CAJA");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(528, 528, 528)
                        .addComponent(Empleados)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(Empleados, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Buscar.setBackground(new java.awt.Color(255, 255, 255));
        Buscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("DATOS ESTADISTICOS DE LA EMPRESA");
        Buscar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 0, -1, -1));

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

        Buscar.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 450, 440));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("TOTAL VENTAS:");
        Buscar.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("INVERTIDO:");
        Buscar.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setText("PUNTO MEDIO:");
        Buscar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 480, -1, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("HOY");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 153, 153));
        jButton3.setText("SEMANA");
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 180, 50));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(0, 153, 153));
        jButton4.setText("MES");
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 180, 50));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 153, 153));
        jButton5.setText("AÑO");
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 170, 50));

        Buscar.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(458, 35, -1, -1));
        Buscar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 470, 1200, 10));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        Buscar.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 470, 10, 40));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("TOTAL:");
        Buscar.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 480, -1, -1));
        Buscar.add(MonthChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, -1, -1));
        Buscar.add(YearChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        Buscar.add(Calendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 170, -1, -1));
        Buscar.add(DateChooserFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 300, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
            .addComponent(Buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void TablaClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaClientesKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
              //  actualizar();
            } catch (Exception ex) {
                Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_TablaClientesKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buscar;
    private com.toedter.calendar.JCalendar Calendario;
    private com.toedter.calendar.JDateChooser DateChooserFactura;
    private javax.swing.JLabel Empleados;
    private com.toedter.calendar.JMonthChooser MonthChooser;
    private javax.swing.JTable TablaClientes;
    private com.toedter.calendar.JYearChooser YearChooser;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables


}
