package Vista;
import Modelo.Conexion;
import Controlador.GuardID;
import Controlador.GuardIDE;
import Controlador.SettersAndGetters;
import Modelo.MetodoIngreso;
import static Vista.EntornoAdmin.LabelEstado;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public final class Usuarios extends javax.swing.JPanel {

    //variables
   private static ResultSet r;
    private static Statement st;
    private static PreparedStatement ps;
    private String cod,contrasena,cedula,nombres,apellidos,telefono,direccion;
    int idsucur=0,cantidadColumnas,idv;
    
    //invocacion de clases   
    Conexion cn=new Conexion();  
    Connection c= cn.conexion();
    DefaultTableModel modelo = new DefaultTableModel();
    SettersAndGetters set=new SettersAndGetters();
    MetodoIngreso mi=new MetodoIngreso();

    private Component rootPane;
    
    public Usuarios() {
        initComponents();     
        this.TablaSucursal.setModel(modelo);
        buscarColumnas();
        obtenerIdVendedor();
        FillComboUsuario();
        FillComboEmpresa();
    }
    
    
    public void buscarColumnas(){      
        try{ 
            r = cn.consultar("select IdVendedor,Cedula,Nombres,Apellidos,Telefono,Direccion from Vendedor"); 
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

    public void buscarEmpleados(){
        limpiarTabla();
       try
       {
            r = cn.consultar("select IdVendedor,Cedula,Nombres,Apellidos,Telefono,Direccion from Vendedor order by IdVendedor");  
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
    
    public void registrar() throws Exception{
    
        contrasena = Password.getText();
        cedula= TextCedula.getText();
        nombres = TextNombres.getText();
        apellidos =TextApellidos.getText();
        telefono = TextNumeroTelf.getText();
        direccion = TextDireccion.getText();
        
        if(contrasena.equals("")||cedula.equals("")||nombres.equals("")||apellidos.equals("")||telefono.equals("")||direccion.equals(""))
        {
            LabelEstado.setText("Existen campos sin llenar..."); 
        }
        else
        {
        GuardID tu=(GuardID) ComboUsuario.getSelectedItem();
        GuardIDE ne=(GuardIDE) ComboEmpresa.getSelectedItem();
        try{
            String id = tu.getID();
            String ide = ne.getID();                    
                
                set.setIdTipoUsuarioV(Integer.parseInt(id));   
                set.setIdEmpresaV(Integer.parseInt(ide));
                set.setContrasenaV(contrasena);
                set.setCedulaV(cedula);
                set.setNombresV(nombres);
                set.setApellidosV(apellidos);
                set.setTelefonoV(telefono);
                set.setDireccionV(direccion);
                
            mi.registrarVendedor(set);
            
            JOptionPane.showMessageDialog(null, "Vendedor registrado correctamente");
            limpiar();
            obtenerIdVendedor();
        
        }
        catch(SQLException | NumberFormatException | HeadlessException ex)
        {
            LabelEstado.setText("Error: "+ex); 
        }
        }
    }
       
    public void eliminar(){
    
    int fila = TablaSucursal.getSelectedRow();
        try
        {
            ps = c.prepareStatement("delete from Empleados where IdEmpresa=?");
            ps.setString(1, TablaSucursal.getValueAt(fila, 0).toString());
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(rootPane, "Sucursal eliminada...................");
            }
            buscarEmpleados();
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
        TextIdVendedor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        ComboUsuario = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        ComboEmpresa = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        Password = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        TextCedula = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TextNombres = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TextApellidos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        TextNumeroTelf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
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
                    .addComponent(jLabel2)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 546, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(250, 250, 250))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)))
        );

        Crear.setBackground(new java.awt.Color(36, 47, 65));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("ID EMPLEADO:");

        TextIdVendedor.setEditable(false);
        TextIdVendedor.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextIdVendedor.setBorder(null);
        TextIdVendedor.setDisabledTextColor(new java.awt.Color(204, 204, 204));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 204, 204));
        jLabel11.setText("TIPO DE USUARIO:");

        ComboUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 204, 204));
        jLabel12.setText("SUCURSAL:");

        ComboEmpresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 204));
        jLabel9.setText("CONTRASEÑA:");

        Password.setText("jPasswordField1");
        Password.setBorder(null);
        Password.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                PasswordFocusGained(evt);
            }
        });
        Password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PasswordMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("CEDULA:");

        TextCedula.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextCedula.setBorder(null);
        TextCedula.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextCedulaFocusGained(evt);
            }
        });
        TextCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextCedulaMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("NOMBRES:");

        TextNombres.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextNombres.setBorder(null);
        TextNombres.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextNombres.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextNombresFocusGained(evt);
            }
        });
        TextNombres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextNombresMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("APELLIDOS:");

        TextApellidos.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextApellidos.setBorder(null);
        TextApellidos.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextApellidos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextApellidosFocusGained(evt);
            }
        });
        TextApellidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextApellidosMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("TELEFONO:");

        TextNumeroTelf.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextNumeroTelf.setBorder(null);
        TextNumeroTelf.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextNumeroTelf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextNumeroTelfFocusGained(evt);
            }
        });
        TextNumeroTelf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextNumeroTelfMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Century Gothic", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 204, 204));
        jLabel10.setText("DIRECCION:");

        TextDireccion.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextDireccion.setBorder(null);
        TextDireccion.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        TextDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TextDireccionFocusGained(evt);
            }
        });
        TextDireccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TextDireccionMouseClicked(evt);
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
                .addContainerGap()
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(BtnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel12)
                                .addComponent(jLabel9)
                                .addComponent(jLabel7)
                                .addComponent(jLabel5)
                                .addComponent(jLabel14)
                                .addComponent(jLabel10)
                                .addComponent(jLabel11)))
                        .addGap(33, 33, 33)
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(CrearLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(BtnBuscarCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(ComboEmpresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ComboUsuario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TextIdVendedor)
                                .addComponent(TextNombres)
                                .addComponent(TextApellidos)
                                .addComponent(TextNumeroTelf)
                                .addComponent(TextDireccion)
                                .addGroup(CrearLayout.createSequentialGroup()
                                    .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Password)
                                        .addComponent(TextCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                                    .addGap(92, 92, 92))))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        CrearLayout.setVerticalGroup(
            CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrearLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(TextIdVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ComboEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Password, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9))
                .addGap(12, 12, 12)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(TextCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(TextNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(TextApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(TextNumeroTelf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(TextDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrearLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CrearLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(CrearLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnRegistrar)
                            .addComponent(BtnBuscarCategorias, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap(19, Short.MAX_VALUE))
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
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
        );
        BuscarLayout.setVerticalGroup(
            BuscarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BuscarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1189, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Crear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 512, Short.MAX_VALUE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Crear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 101, Short.MAX_VALUE)
                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRegistrarActionPerformed
       
       try
       {
           registrar();
       } 
       catch (Exception ex) 
       {
           Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }//GEN-LAST:event_BtnRegistrarActionPerformed

    private void BtnBuscarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarCategoriasActionPerformed
    
        buscarEmpleados();
        
    }//GEN-LAST:event_BtnBuscarCategoriasActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed

        eliminar();
        
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void TablaSucursalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TablaSucursalKeyReleased
        
        
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        actualizar();

    }
    }//GEN-LAST:event_TablaSucursalKeyReleased

    private void PasswordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PasswordFocusGained
        Password.setText("");
    }//GEN-LAST:event_PasswordFocusGained

    private void PasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PasswordMouseClicked
        Password.setText("");
    }//GEN-LAST:event_PasswordMouseClicked

    private void TextCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextCedulaFocusGained
        TextCedula.setText("");
    }//GEN-LAST:event_TextCedulaFocusGained

    private void TextCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextCedulaMouseClicked
        TextCedula.setText("");
    }//GEN-LAST:event_TextCedulaMouseClicked

    private void TextNombresFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextNombresFocusGained
        TextNombres.setText("");
    }//GEN-LAST:event_TextNombresFocusGained

    private void TextNombresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextNombresMouseClicked
        TextNombres.setText("");
    }//GEN-LAST:event_TextNombresMouseClicked

    private void TextApellidosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextApellidosFocusGained
        TextApellidos.setText("");
    }//GEN-LAST:event_TextApellidosFocusGained

    private void TextApellidosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextApellidosMouseClicked
        TextApellidos.setText("");
    }//GEN-LAST:event_TextApellidosMouseClicked

    private void TextNumeroTelfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextNumeroTelfFocusGained
        TextNumeroTelf.setText("");
    }//GEN-LAST:event_TextNumeroTelfFocusGained

    private void TextNumeroTelfMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextNumeroTelfMouseClicked
        TextNumeroTelf.setText("");
    }//GEN-LAST:event_TextNumeroTelfMouseClicked

    private void TextDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TextDireccionFocusGained
        TextDireccion.setText("");
    }//GEN-LAST:event_TextDireccionFocusGained

    private void TextDireccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TextDireccionMouseClicked
        TextDireccion.setText("");
    }//GEN-LAST:event_TextDireccionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnBuscarCategorias;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnRegistrar;
    private javax.swing.JPanel Buscar;
    private javax.swing.JComboBox ComboEmpresa;
    private javax.swing.JComboBox ComboUsuario;
    private javax.swing.JPanel Crear;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTable TablaSucursal;
    private javax.swing.JTextField TextApellidos;
    private javax.swing.JTextField TextCedula;
    private javax.swing.JTextField TextDireccion;
    private javax.swing.JTextField TextIdVendedor;
    private javax.swing.JTextField TextNombres;
    private javax.swing.JTextField TextNumeroTelf;
    private javax.swing.JPanel header;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables

public void limpiar(){

    TextNombres.setText("");
    TextApellidos.setText("");
    TextNumeroTelf.setText("");
    TextDireccion.setText("");
    TextCedula.setText("");
}
    public void obtenerIdVendedor(){
        
    try{       
        st = c.createStatement();
        r = st.executeQuery("select max(IdVendedor)+1 from Vendedor");
        if(r.next()){
        idv=r.getInt(1);
        cod=String.valueOf(idv);
        TextIdVendedor.setText(cod); 
        }
    }
    catch(SQLException e){
        LabelEstado.setText("Error: "+e);
    }
    }
    
    

public void FillComboUsuario()
    {
    DefaultComboBoxModel value;
      try {         
         r= cn.consultar("select Tipo_Usuario,IdTipo_Usuario from Tipo_Usuario");   
         value =new DefaultComboBoxModel();
         ComboUsuario.setModel(value);
         while (r.next()) { 
           value.addElement(new GuardID(r.getString(1),r.getString(2)));
         }
         r.close();
        } catch (SQLException e) {
          LabelEstado.setText("Error: "+e);
        }
  
    } 
public void FillComboEmpresa()
    {
    DefaultComboBoxModel value;
      try {         
            r= cn.consultar("select NombreEmpresa,IdEmpresa from Empresa");     
            value =new DefaultComboBoxModel();
            ComboEmpresa.setModel(value);
         while (r.next()) {         
           value.addElement(new GuardIDE(r.getString(1),r.getString(2)));
         }
         r.close();
        } catch (SQLException e) {
          LabelEstado.setText("Error: "+e);
        }
  
    }
}
