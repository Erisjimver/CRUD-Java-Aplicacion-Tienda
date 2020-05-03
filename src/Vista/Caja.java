package Vista;
import Controlador.Funcionalidades;
import Controlador.SettersAndGetters;
import Modelo.CRUD;
import static Vista.EntornoAdmin.LabelEstado;
import static Vista.EntornoVendedor.LabelEstadoV;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public final class Caja extends javax.swing.JPanel {

    //declarando variables
    private static ResultSet r;
    int idsucur=0,cantidadColumnas,idv,total=0,totalVentaEstimada,totalInversion,totalVendido,capital;
    
    //invocacion de clases   
    Funcionalidades fun = new Funcionalidades();
    SettersAndGetters set=new SettersAndGetters();
    CRUD crud=new CRUD();

    //creando objetos de la tabla, y los jComboBox
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel comboEmpresa = new DefaultComboBoxModel();
    
    
    public Caja() throws Exception {
        initComponents();     
        this.TablaFacturasDetalles.setModel(modelo);
        buscarColumnas();
        agregarOyenteDia();
        agregarOyenteMes();
        agregarOyenteAnio();
        consultaInversion();
        consultaTotalVenta();
        consultaTotalVentaEstimada();
        calculaCapital();
        generarBarras();
        
    }
    
    public void generarBarras(){
        try{
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            
            ds.addValue(totalVentaEstimada, "Venta Estimada", "");
            ds.addValue(totalInversion, "Inversion", "");
            ds.addValue(totalVendido, "Total Vendido", "");
            ds.addValue(capital, "Capital", "");
            
            JFreeChart jf = ChartFactory.createBarChart3D("Graficas estadisticas", "Diferencia de caja", "Efectivo", ds, PlotOrientation.VERTICAL,true,true,true);
            ChartPanel  f = new ChartPanel(jf);
            f.setSize(690,350);
            f.setLocation(0,0);   
            PanelGraficas.removeAll();
            PanelGraficas.add(f,BorderLayout.CENTER);
            PanelGraficas.revalidate();
            PanelGraficas.repaint();
    
        }catch(Exception e){
            System.out.println(""+e);
        }
    }
    public void generarBarrasBoton(String boton){
        try{
            DefaultCategoryDataset ds = new DefaultCategoryDataset();
            
            ds.addValue(totalVentaEstimada, "Venta Estimada", "");
            ds.addValue(totalInversion, "Inversion", "");
            ds.addValue(totalVendido, "Total Vendido", "");
            ds.addValue(total, boton, "");
            ds.addValue(capital, "Capital", "");
            
            JFreeChart jf = ChartFactory.createBarChart3D("Graficas estadisticas", "Diferencia de caja", "Efectivo", ds, PlotOrientation.VERTICAL,true,true,true);
            ChartPanel  f = new ChartPanel(jf);
            f.setSize(690,350);
            f.setLocation(0,0);   
            PanelGraficas.removeAll();
            PanelGraficas.add(f,BorderLayout.CENTER);
            PanelGraficas.revalidate();
            PanelGraficas.repaint();
    
        }catch(Exception e){
            System.out.println(""+e);
        }
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
            total();
            generarBarrasBoton("Dia");
        }catch(Exception e){
            LabelEstadoV.setText("Error en Caja/buscarFacturasDetallesDia1(): "+e);
        }
    } 
    
    private void buscarFacturasDetallesMes(String mes){
        try{
            limpiarTabla();
            r=crud.consultarFacturasDetalleMes(mes);
            llenaTabla();
            total();
            generarBarrasBoton("Mes");
        }catch(Exception e){
            LabelEstadoV.setText("Error en Caja/buscarFacturasDetallesMes1(): "+e);
        }
    }
    
    private void buscarFacturasDetallesAnio(int anio){
        try{
            limpiarTabla();
            r=crud.consultarFacturasDetalleAnio(anio);
            llenaTabla();
            total();
            generarBarrasBoton("Año");
        }catch(Exception e){
            LabelEstadoV.setText("Error en Caja/buscarFacturasDetallesMes1(): "+e);
        }
    }
    
    private void buscarFacturasDetallesSemana(){
        try{
            limpiarTabla();
            r=crud.consultarFacturasDetalleSemana();
            llenaTabla();
            total();
            generarBarrasBoton("Esta semana");
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
                labeltotal.setText("$ 0");
            } 
          }catch(Exception e){
             LabelEstado.setText("Error en Caja/limpiarTabla(): "+e); 
          }
      }   
   
    //se escucha el evento de la seleccion del dia
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
    
    //se escucha el evento de la seleccion del mes
    private void agregarOyenteMes(){
        MonthChooser.addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().compareTo("month") == 0) {
                int mes = (MonthChooser.getMonth());
                buscarFacturasDetallesMes(concatena(mes));
            }            
        });
    }   
    
    //Se escucha eventos del la selecion del añó
    private void agregarOyenteAnio(){
        YearChooser.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if (evt.getPropertyName().compareTo("year") == 0) {
                    int year = (YearChooser.getYear());            
                    buscarFacturasDetallesAnio(year);
                }
            }
        });
    }
    
    //convierte el mes en una cifra de dos digitos
    private String concatena(int m){
        int me = m+1;
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
    
    private void total(){
        total=0;
        int filas = TablaFacturasDetalles.getRowCount();
        for (int i = 0; i < filas; i++) {
        total += Integer.parseInt(TablaFacturasDetalles.getValueAt(i, 5).toString());
        labeltotal.setText("$ " +String.valueOf(total));  
       
        }
    }
    
    //pasivo
    private void consultaInversion(){
        if(crud.consultarInversion().equals("")){
            labelinversion.setText("$ 0.0");
        }else{
            totalInversion=Integer.parseInt(crud.consultarInversion());
            labelinversion.setText("$ "+totalInversion);
        }     
    }
    //total de las ventas realizadas por el momento
    private void consultaTotalVenta(){
        if(crud.consultarTotalVentasReales().equals("")){
            labeltotalventa.setText("$ 0.0");
        }else{
            totalVendido=Integer.parseInt(crud.consultarTotalVentasReales());
            labeltotalventa.setText("$ "+totalVendido);
        }     
    }
    //activo
    private void consultaTotalVentaEstimada(){
        if(crud.consultarTotalVentasEstimada().equals("")){
            labeltotalventaestimada.setText("$ 0.0");
        }else{
            totalVentaEstimada = Integer.parseInt(crud.consultarTotalVentasEstimada());
            labeltotalventaestimada.setText("$ "+totalVentaEstimada);
        }     
    } 
        private void calculaCapital(){
        if(totalVentaEstimada == 0 && totalInversion ==0){
            labelcapital.setText("$ 0.0");
        }else{
            capital = totalVentaEstimada-totalInversion;
            labelcapital.setText("$ "+String.valueOf(capital));
        }     
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
        TablaFacturasDetalles = new javax.swing.JTable();
        labeltotal = new javax.swing.JLabel();
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
        DateChooserFactura = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        labelinversion = new javax.swing.JLabel();
        labeltotalventa = new javax.swing.JLabel();
        labelcapital = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        labeltotalventaestimada = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        PanelGraficas = new javax.swing.JPanel();

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("DATOS ESTADISTICOS DE LA EMPRESA");

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        TablaFacturasDetalles.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaFacturasDetalles.setForeground(new java.awt.Color(0, 153, 153));
        TablaFacturasDetalles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        TablaFacturasDetalles.setGridColor(new java.awt.Color(255, 255, 255));
        TablaFacturasDetalles.setRowHeight(20);
        TablaFacturasDetalles.setSelectionBackground(new java.awt.Color(1, 198, 83));
        TablaFacturasDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaFacturasDetallesKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TablaFacturasDetalles);

        labeltotal.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labeltotal.setText("$ 0");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("INVERTIDO:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("CAPITAL:");

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setForeground(new java.awt.Color(0, 153, 153));
        jButton1.setText("HOY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 50));

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setForeground(new java.awt.Color(0, 153, 153));
        jButton3.setText("SEMANA");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 180, 50));

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setForeground(new java.awt.Color(0, 153, 153));
        jButton4.setText("MES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 180, 50));

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setForeground(new java.awt.Color(0, 153, 153));
        jButton5.setText("AÑO");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, 170, 50));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("TOTAL VENDIDO:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("TOTAL:");

        labelinversion.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelinversion.setText("$ 0.0");

        labeltotalventa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labeltotalventa.setText("$ 0.0");

        labelcapital.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelcapital.setText("$ 0.0");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("VENTA ESTIMADA:");

        labeltotalventaestimada.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labeltotalventaestimada.setText("$ 0.0");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 153), 1, true));

        PanelGraficas.setBackground(new java.awt.Color(255, 255, 255));
        PanelGraficas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));

        javax.swing.GroupLayout PanelGraficasLayout = new javax.swing.GroupLayout(PanelGraficas);
        PanelGraficas.setLayout(PanelGraficasLayout);
        PanelGraficasLayout.setHorizontalGroup(
            PanelGraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PanelGraficasLayout.setVerticalGroup(
            PanelGraficasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelGraficas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelGraficas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout BuscarLayout = new javax.swing.GroupLayout(Buscar);
        Buscar.setLayout(BuscarLayout);
        BuscarLayout.setHorizontalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarLayout.createSequentialGroup()
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addComponent(YearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(MonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(DateChooserFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(BuscarLayout.createSequentialGroup()
                                .addGap(152, 152, 152)
                                .addComponent(jLabel3))
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(BuscarLayout.createSequentialGroup()
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel9))
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(labeltotal, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labeltotalventa)
                        .addGap(9, 9, 9)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labeltotalventaestimada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelinversion, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelcapital, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        BuscarLayout.setVerticalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BuscarLayout.createSequentialGroup()
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(YearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(MonthChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DateChooserFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5))
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(6, 6, 6)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(BuscarLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labeltotal)
                            .addComponent(jLabel8)
                            .addComponent(labeltotalventa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labeltotalventaestimada, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel5)
                                .addComponent(labelinversion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7)
                                .addComponent(labelcapital, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9)))))
        );

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

    private void TablaFacturasDetallesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaFacturasDetallesKeyReleased

        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
              //  actualizar();
            } catch (Exception ex) {
                Logger.getLogger(Caja.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_TablaFacturasDetallesKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        buscarFacturasDetallesDia(fun.fechaActual());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        buscarFacturasDetallesMes(fun.obtenerMes());
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        buscarFacturasDetallesAnio(fun.obtenerAño());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        buscarFacturasDetallesSemana();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Buscar;
    private com.toedter.calendar.JDateChooser DateChooserFactura;
    private javax.swing.JLabel Empleados;
    private com.toedter.calendar.JMonthChooser MonthChooser;
    private javax.swing.JPanel PanelGraficas;
    private javax.swing.JTable TablaFacturasDetalles;
    private com.toedter.calendar.JYearChooser YearChooser;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelcapital;
    private javax.swing.JLabel labelinversion;
    private javax.swing.JLabel labeltotal;
    private javax.swing.JLabel labeltotalventa;
    private javax.swing.JLabel labeltotalventaestimada;
    // End of variables declaration//GEN-END:variables


}
