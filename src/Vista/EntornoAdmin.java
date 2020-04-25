package Vista;

import Modelo.CRUD;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class EntornoAdmin extends javax.swing.JFrame {

    CRUD crud=new CRUD();
    

public class IEntornoAdmin extends javax.swing.JPanel {
 
public IEntornoAdmin() {
this.setSize(1195,575);
} 
@Override
public void paint(Graphics grafico) {
Dimension height = getSize();
ImageIcon Img = new ImageIcon(getClass().getResource("/Vista/Imagenes/2. FondoPantalla.jpg"));  
grafico.drawImage(Img.getImage(), 0, 0, height.width, height.height,null);
setOpaque(false);
super.paintComponent(grafico);
}
}
    public EntornoAdmin() {
        initComponents();
        this.setLocationRelativeTo(null);
        IEntornoAdmin Imagen = new IEntornoAdmin();
        Imagen.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(Imagen,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
        setIconImage(new ImageIcon(getClass().getResource("/Vista/Imagenes/Icono.png")).getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        PanelPrincipal = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbluserad = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        LabelEstado = new javax.swing.JLabel();
        Menu = new javax.swing.JMenuBar();
        MenuInicio = new javax.swing.JMenu();
        Principal = new javax.swing.JMenuItem();
        CerrarCecion = new javax.swing.JMenuItem();
        MenuSalir = new javax.swing.JMenuItem();
        MenuInventario = new javax.swing.JMenu();
        MenuNuevaCategoria = new javax.swing.JMenuItem();
        MenuNuevoProducto = new javax.swing.JMenuItem();
        MenuCaja = new javax.swing.JMenu();
        Efectivo = new javax.swing.JMenuItem();
        Facturas = new javax.swing.JMenuItem();
        MenuReportes = new javax.swing.JMenu();
        EmpleadosItem = new javax.swing.JMenuItem();
        SucursalesItem = new javax.swing.JMenuItem();
        ClientesItem = new javax.swing.JMenuItem();
        FacturaItem = new javax.swing.JMenuItem();
        MenuAdministrador = new javax.swing.JMenu();
        ManuUsuario = new javax.swing.JMenuItem();
        MenuSucursal = new javax.swing.JMenuItem();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Zona Movil");
        setBackground(new java.awt.Color(36, 47, 65));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setName("Entorno"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelPrincipal.setBackground(new java.awt.Color(36, 47, 65));
        PanelPrincipal.setPreferredSize(new java.awt.Dimension(1200, 580));

        javax.swing.GroupLayout PanelPrincipalLayout = new javax.swing.GroupLayout(PanelPrincipal);
        PanelPrincipal.setLayout(PanelPrincipalLayout);
        PanelPrincipalLayout.setHorizontalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        PanelPrincipalLayout.setVerticalGroup(
            PanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );

        getContentPane().add(PanelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        getContentPane().add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USUARIO EN LINEA:");

        lbluserad.setForeground(new java.awt.Color(255, 255, 255));
        lbluserad.setText("NOMBRE DE USUARIO");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        LabelEstado.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(LabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(lbluserad)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbluserad))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(LabelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 584, 1200, 50));

        Menu.setBackground(new java.awt.Color(97, 212, 195));

        MenuInicio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Inicio.png"))); // NOI18N
        MenuInicio.setText("Inicio");

        Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. principal.png"))); // NOI18N
        Principal.setText("Principal");
        Principal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrincipalActionPerformed(evt);
            }
        });
        MenuInicio.add(Principal);

        CerrarCecion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. cerrar sesion.png"))); // NOI18N
        CerrarCecion.setText("Cerrar Sesion");
        CerrarCecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarCecionActionPerformed(evt);
            }
        });
        MenuInicio.add(CerrarCecion);

        MenuSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. cancelar.png"))); // NOI18N
        MenuSalir.setText("Salir");
        MenuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSalirActionPerformed(evt);
            }
        });
        MenuInicio.add(MenuSalir);

        Menu.add(MenuInicio);

        MenuInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Inventario.png"))); // NOI18N
        MenuInventario.setText("Inventario");

        MenuNuevaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. categorias.png"))); // NOI18N
        MenuNuevaCategoria.setText("Categorias");
        MenuNuevaCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNuevaCategoriaActionPerformed(evt);
            }
        });
        MenuInventario.add(MenuNuevaCategoria);

        MenuNuevoProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. agregarP.png"))); // NOI18N
        MenuNuevoProducto.setText("Nuevo Producto");
        MenuNuevoProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNuevoProductoActionPerformed(evt);
            }
        });
        MenuInventario.add(MenuNuevoProducto);

        Menu.add(MenuInventario);

        MenuCaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Ingresos.png"))); // NOI18N
        MenuCaja.setText("Caja");

        Efectivo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. efectivo.png"))); // NOI18N
        Efectivo.setText("Efectivo");
        Efectivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EfectivoActionPerformed(evt);
            }
        });
        MenuCaja.add(Efectivo);

        Facturas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. facturas.png"))); // NOI18N
        Facturas.setText("Facturas");
        Facturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacturasActionPerformed(evt);
            }
        });
        MenuCaja.add(Facturas);

        Menu.add(MenuCaja);

        MenuReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Reportes.png"))); // NOI18N
        MenuReportes.setText("Reportes");

        EmpleadosItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. archivo (1).png"))); // NOI18N
        EmpleadosItem.setText("Reporte de empleados");
        EmpleadosItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpleadosItemActionPerformed(evt);
            }
        });
        MenuReportes.add(EmpleadosItem);

        SucursalesItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. archivo (2).png"))); // NOI18N
        SucursalesItem.setText("Reporte de sucursales");
        SucursalesItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SucursalesItemActionPerformed(evt);
            }
        });
        MenuReportes.add(SucursalesItem);

        ClientesItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. archivo (2).png"))); // NOI18N
        ClientesItem.setText("Reportes de clientes");
        MenuReportes.add(ClientesItem);

        FacturaItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. archivo (2).png"))); // NOI18N
        FacturaItem.setText("Reporte Facturas");
        FacturaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FacturaItemActionPerformed(evt);
            }
        });
        MenuReportes.add(FacturaItem);

        Menu.add(MenuReportes);

        MenuAdministrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/#Usuarios.png"))); // NOI18N
        MenuAdministrador.setText("Administrador");

        ManuUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. usuario.png"))); // NOI18N
        ManuUsuario.setText("Crear Usuario");
        ManuUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ManuUsuarioActionPerformed(evt);
            }
        });
        MenuAdministrador.add(ManuUsuario);

        MenuSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/Imagenes/1. Sucursal.png"))); // NOI18N
        MenuSucursal.setText("Sucursal");
        MenuSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSucursalActionPerformed(evt);
            }
        });
        MenuAdministrador.add(MenuSucursal);

        Menu.add(MenuAdministrador);

        setJMenuBar(Menu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CerrarCecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarCecionActionPerformed
  
        Login log=new Login();
        log.setVisible(true);
        log.pack();
        this.setVisible(false);
        
    }//GEN-LAST:event_CerrarCecionActionPerformed

    private void EfectivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EfectivoActionPerformed
        // TODO add your handling code here:
        Facturas in = new Facturas();
        in.setSize(1195,575);
        in.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(in,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();        
    }//GEN-LAST:event_EfectivoActionPerformed

    private void ManuUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManuUsuarioActionPerformed
    try {
        Usuarios pro=new Usuarios();
        pro.setSize(1195,575);
        pro.setLocation(2,2);  
        PanelPrincipal.removeAll();
        PanelPrincipal.add(pro,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    } catch (Exception ex) {
        Logger.getLogger(EntornoAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_ManuUsuarioActionPerformed

    private void MenuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_MenuSalirActionPerformed

    private void MenuSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSucursalActionPerformed
        Sucursal s=new Sucursal();
        s.setSize(1195,575);
        s.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(s,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    }//GEN-LAST:event_MenuSucursalActionPerformed

    private void MenuNuevaCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNuevaCategoriaActionPerformed
        Categorias cat=new Categorias();
        cat.setSize(1195,575);
        cat.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(cat,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    }//GEN-LAST:event_MenuNuevaCategoriaActionPerformed

    private void MenuNuevoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNuevoProductoActionPerformed
    try {
        Productos p= new Productos();
        p.setSize(1195,575);
        p.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(p,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();
    } catch (Exception ex) {
        Logger.getLogger(EntornoAdmin.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_MenuNuevoProductoActionPerformed

    private void PrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrincipalActionPerformed
        // TODO add your handling code here:
        IEntornoAdmin Imagen = new IEntornoAdmin();
        Imagen.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(Imagen,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();

    }//GEN-LAST:event_PrincipalActionPerformed

    private void EmpleadosItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmpleadosItemActionPerformed
      //  crud.Reportes("C:\\Users\\Isra\\Documents\\NetBeansProjects\\ZonaMovil\\src\\Reportes\\Vendedores.jrxml");
        crud.Reportes("C:\\Users\\Ichigo\\Documents\\NetBeansProjects\\Proyectos\\ZonaMovil\\src\\Reportes\\Vendedores.jrxml");
        
    }//GEN-LAST:event_EmpleadosItemActionPerformed

    private void SucursalesItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SucursalesItemActionPerformed
        crud.Reportes("C:\\Users\\Ichigo\\Documents\\NetBeansProjects\\Proyectos\\ZonaMovil\\src\\Reportes\\Sucursal.jrxml");
    }//GEN-LAST:event_SucursalesItemActionPerformed

    private void FacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacturasActionPerformed
        // TODO add your handling code here:
        Facturas in = new Facturas();
        in.setSize(1195,575);
        in.setLocation(2,2);
        PanelPrincipal.removeAll();
        PanelPrincipal.add(in,BorderLayout.CENTER);
        PanelPrincipal.revalidate();
        PanelPrincipal.repaint();       
    }//GEN-LAST:event_FacturasActionPerformed

    private void FacturaItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FacturaItemActionPerformed
        crud.Reportes("C:\\Users\\Ichigo\\Documents\\NetBeansProjects\\Proyectos\\ZonaMovil\\src\\Reportes\\Facturas.jrxml");
    }//GEN-LAST:event_FacturaItemActionPerformed


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntornoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntornoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntornoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntornoAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EntornoAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CerrarCecion;
    private javax.swing.JMenuItem ClientesItem;
    private javax.swing.JMenuItem Efectivo;
    private javax.swing.JMenuItem EmpleadosItem;
    private javax.swing.JMenuItem FacturaItem;
    private javax.swing.JMenuItem Facturas;
    public static javax.swing.JLabel LabelEstado;
    private javax.swing.JMenuItem ManuUsuario;
    private javax.swing.JMenuBar Menu;
    private javax.swing.JMenu MenuAdministrador;
    private javax.swing.JMenu MenuCaja;
    private javax.swing.JMenu MenuInicio;
    private javax.swing.JMenu MenuInventario;
    private javax.swing.JMenuItem MenuNuevaCategoria;
    private javax.swing.JMenuItem MenuNuevoProducto;
    private javax.swing.JMenu MenuReportes;
    private javax.swing.JMenuItem MenuSalir;
    private javax.swing.JMenuItem MenuSucursal;
    public static javax.swing.JPanel PanelPrincipal;
    private javax.swing.JMenuItem Principal;
    private javax.swing.JMenuItem SucursalesItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    public static javax.swing.JLabel lbluserad;
    // End of variables declaration//GEN-END:variables
}
