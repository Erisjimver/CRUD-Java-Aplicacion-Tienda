package Vista;
import Modelo.Conexion;
import Controlador.SettersAndGetters;
import Controlador.Funcionalidades;
import Modelo.CRUD;
import static Vista.EntornoAdmin.LabelEstado;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Productos extends javax.swing.JPanel {
    private static ResultSet r;
    private static Statement st;
    private static PreparedStatement ps;
    private String cod;
    private int idprod=0;
    
    Conexion cn=new Conexion();  
    Connection c= cn.conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    DefaultComboBoxModel value = new DefaultComboBoxModel();
    SettersAndGetters pp=new SettersAndGetters();
    CRUD mi=new CRUD();
    Funcionalidades fun = new Funcionalidades();
    
    int cantidadColumnas;
    private Component rootPane;
    
    public Productos() throws Exception {
        initComponents();     
        this.TablaProductos.setModel(modelo);
        FillComboCate();
        IdProductos();
        buscarColumnas();
    }
    
    
    public void buscarColumnas(){      
        try{ 
            r = cn.consultar("select IdProducto,NombreProducto,Marca,Precio,Stock,FechaIngreso from Producto order by IdProducto"); 
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

    public void buscarProductos(){
        limpiarTabla();
       try
       {
            r = cn.consultar("select IdProducto,NombreProducto,Marca,Precio,Stock,FechaIngreso from Producto order by IdProducto");  
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
            for(int i=a; i>=0; i--)
            {
                modelo.removeRow(i );
            }
 
          }catch(Exception e){
             LabelEstado.setText("Error: "+e); 
          }
      }
    
    public void registrar(){
    
    String clave= (String) ComboCategoria.getSelectedItem();      
        try
        {
            int valor = fun.obtenerIdCategoria(clave);
    
            pp.setIdcategorias(valor);
            pp.setNombreproducto(TextNombreP.getText());
            pp.setMarca(TextMarca.getText());
            pp.setCosto(Double.parseDouble(TextCosto.getText()));
            pp.setPrecio(Double.parseDouble(TextPrecio.getText()));
            pp.setStock(Integer.parseInt(TextStock.getText()));
        mi.registrarProductos(pp);
            JOptionPane.showMessageDialog(null, "Registro del articulo correcto....");
    LIMPIAR();
    IdProductos();
        }
        catch(Exception e)
        {
            LabelEstado.setText("Asegurese de llenar todos los campos...."+e); 
    
        }
    }
       
    public void eliminar(){
    
    int fila = TablaProductos.getSelectedRow();
        try
        {
            PreparedStatement ps = c.prepareStatement("delete from Producto where IdProducto=?");
            ps.setString(1, TablaProductos.getValueAt(fila, 0).toString());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(rootPane, "Producto eliminado....");
            }
            buscarProductos();
        }
        catch(SQLException | HeadlessException ext)
        {
            LabelEstado.setText("Error: "+ext); 
        }
    }
    
    public void actualizar(){
        
        try{

            int fila = TablaProductos.getSelectedRow();
            ps = c.prepareCall("{call ActualizarProductos(?,?,?,?,?)}");
            
            ps.setInt(1, Integer.parseInt(TablaProductos.getValueAt(fila, 0).toString()));
            ps.setString(2, TablaProductos.getValueAt(fila, 1).toString());
            ps.setString(3, TablaProductos.getValueAt(fila, 2).toString());
            ps.setInt(4, Integer.parseInt(TablaProductos.getValueAt(fila, 3).toString()));
            ps.setInt(5, Integer.parseInt(TablaProductos.getValueAt(fila, 4).toString()));
 
            int i=ps.executeUpdate();
            if(i==1){
                JOptionPane.showMessageDialog(rootPane, "Articulo actualizado........");

            }
        }catch(SQLException | NumberFormatException | HeadlessException ex)
        {
            LabelEstado.setText("Error: "+ex);   
        }
       
    }
    
    public void IdProductos(){
     try{
        r = cn.consultar("select max(IdProducto)+1 from Producto");
        if(r.next()){
        idprod=r.getInt(1);
        cod=String.valueOf(idprod);
        TextCOP.setText(cod); 
        }
     }catch(SQLException e)
     {
       
     }         
}
/*
    public void FillComboCate(){

      try {         
         r = cn.consultar("select descripcion,IdCategoria from Categoria");   
         value =new DefaultComboBoxModel();
          System.out.println(value);
         ComboCategoria.setModel(value);
         while (r.next()) { 
             System.out.println(r.getString(1));
             System.out.println(r.getString(2));
           value.addElement(new GuardIDCate(r.getString(1),r.getString(2)));
         }
         r.close();
        } catch (SQLException ex) {
          System.out.println("error"+ex);
        }
  
    }
   */ 
    public void FillComboCate() throws Exception{

      try {         
         r = cn.consultar("select IdCategoria, descripcion from Categoria");   
        
         ComboCategoria.setModel(value);
         while (r.next()) 
         { 
             //r.getInt(1) es la id de la tabla categorias
             //r.getString(2) es la descripcion de la tabla categoria            
            int clave = r.getInt(1);
            String valor = r.getString(2);
            pp.setIdCategoria(clave);
            pp.setDescripcion(valor);
            fun.DiccionarioCategoria(pp);
            value.addElement(valor);
            //value.addElement(new GuardIDCate(r.getString(2),r.getString(1)));
         }
         r.close();
        } 
      catch (SQLException ex) 
      {
        System.out.println("error"+ex);
      }
  
    }
    public void LIMPIAR(){
    //PARA LIMPIAR LOS CAMPOS DE TEXTOS
    TextNombreP.setText("");
    TextMarca.setText("");
    TextCosto.setText("");
    TextStock.setText("");
    TextPrecio.setText("");
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
        jLabel11 = new javax.swing.JLabel();
        TextCOP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        ComboCategoria = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        TextNombreP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextMarca = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TextCosto = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TextPrecio = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TextStock = new javax.swing.JTextField();
        BtnRegistrar = new javax.swing.JButton();
        BtnBuscarCategorias = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        Buscar = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        TablaProductos = new javax.swing.JTable();

        setBackground(new java.awt.Color(36, 47, 65));

        header.setBackground(new java.awt.Color(97, 212, 195));
        header.setPreferredSize(new java.awt.Dimension(838, 200));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Keeping UI alive");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("PRODUCTOS");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ZonaMovil");

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap(51, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addGap(538, 538, 538)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(243, 243, 243))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(jLabel3)
                    .addContainerGap(967, Short.MAX_VALUE)))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(headerLayout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(jLabel3)
                    .addContainerGap(75, Short.MAX_VALUE)))
        );

        Crear.setBackground(new java.awt.Color(36, 47, 65));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("CODIGO DEL PRODUCTO:");

        TextCOP.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("CATEGORIA:");

        ComboCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("NOMBRE DEL PRODUCTO:");

        TextNombreP.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextNombreP.setBorder(null);
        TextNombreP.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextNombreP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextNombrePMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("MARCA:");

        TextMarca.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextMarca.setBorder(null);
        TextMarca.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextMarcaMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("COSTO:");

        TextCosto.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextCosto.setBorder(null);
        TextCosto.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextCosto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextCostoMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("PRECIO:");

        TextPrecio.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextPrecio.setBorder(null);
        TextPrecio.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextPrecioMouseClicked(evt);
            }
        });
        TextPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextPrecioKeyTyped(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("STOCK");

        TextStock.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextStock.setBorder(null);
        TextStock.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextStock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextStockMouseClicked(evt);
            }
        });
        TextStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TextStockKeyTyped(evt);
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
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel13))
                                .addGap(136, 136, 136)
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(TextPrecio)
                                    .addComponent(TextCosto)
                                    .addComponent(TextStock)))
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextCOP)
                                    .addComponent(ComboCategoria, 0, 159, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CrearLayout.createSequentialGroup()
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel5))
                                .addGap(21, 21, 21)
                                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TextNombreP)
                                    .addComponent(TextMarca)))))
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(BtnBuscarCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        CrearLayout.setVerticalGroup(
            CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextCOP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ComboCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextNombreP, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(TextStock, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnBuscarCategorias, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        Buscar.setBackground(new java.awt.Color(36, 47, 65));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        TablaProductos.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        TablaProductos.setForeground(new java.awt.Color(51, 51, 51));
        TablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        TablaProductos.setGridColor(new java.awt.Color(255, 255, 255));
        TablaProductos.setRowHeight(20);
        TablaProductos.setSelectionBackground(new java.awt.Color(1, 198, 83));
        TablaProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TablaProductosKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(TablaProductos);

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
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1200, Short.MAX_VALUE)
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
                    .addGap(0, 148, Short.MAX_VALUE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
       
        registrar();
        
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnBuscarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCategoriasActionPerformed
    
        buscarProductos();
        
    }//GEN-LAST:event_BtnBuscarCategoriasActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed

        eliminar();
        
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void TablaProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaProductosKeyReleased
            
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        actualizar();
    }
    
    }//GEN-LAST:event_TablaProductosKeyReleased

    private void TextNombrePMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextNombrePMouseClicked
        TextNombreP.setText("");
    }//GEN-LAST:event_TextNombrePMouseClicked

    private void TextMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextMarcaMouseClicked
        TextMarca.setText("");
    }//GEN-LAST:event_TextMarcaMouseClicked

    private void TextCostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextCostoMouseClicked
        TextCosto.setText("");
    }//GEN-LAST:event_TextCostoMouseClicked

    private void TextPrecioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextPrecioMouseClicked
        TextPrecio.setText("");
    }//GEN-LAST:event_TextPrecioMouseClicked

    private void TextPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPrecioKeyTyped
        char caracter=evt.getKeyChar();
        if(Character.isLetter(caracter)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null,"Ingrese solo numeros");

        }
    }//GEN-LAST:event_TextPrecioKeyTyped

    private void TextStockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextStockMouseClicked
        TextStock.setText("");
    }//GEN-LAST:event_TextStockMouseClicked

    private void TextStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextStockKeyTyped
        char caracter1=evt.getKeyChar();
        if(Character.isLetter(caracter1)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null,"Ingrese solo numeros");
        }
    }//GEN-LAST:event_TextStockKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarCategorias;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JPanel Buscar;
    private javax.swing.JComboBox ComboCategoria;
    private javax.swing.JPanel Crear;
    private javax.swing.JTable TablaProductos;
    private javax.swing.JTextField TextCOP;
    private javax.swing.JTextField TextCosto;
    private javax.swing.JTextField TextMarca;
    private javax.swing.JTextField TextNombreP;
    private javax.swing.JTextField TextPrecio;
    private javax.swing.JTextField TextStock;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables



}
