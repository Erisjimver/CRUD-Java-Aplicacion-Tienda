package Conexion;
import java.sql.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
public class Conexion {   
Connection conexion=null;
static Statement s;
static ResultSet rs;

public Connection conexion(){
    
        try { 
            Class.forName("oracle.jdbc.OracleDriver");    
            String BaseDeDatos = "jdbc:oracle:thin:@localhost:1521:XE";
            conexion=DriverManager.getConnection(BaseDeDatos,"proyecto","59291");
                
            if (conexion != null) {
                System.out.println("Conectando a Base de Datos...");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"ERROR EN CONECTAR LA BASE DE DATOS"+e);
        }
        return conexion;    
}

public void cerrarconexion() throws SQLException {       
            conexion.close();       
    }


public ResultSet consultar(String sql) { 
        try { 
            s = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = s.executeQuery(sql); 
             
        } catch (SQLException e) { 
            return null; 
        } 
        return rs; 
    }

    public void resportesPDF(String ruta, String archi) {
        try {
            String rutaInforme = ruta;
            JasperPrint informe = JasperFillManager.fillReport(rutaInforme, null, conexion);
            JasperExportManager.exportReportToPdfFile(informe, archi);

            JasperViewer ventanavisor = new JasperViewer(informe, false);
            ventanavisor.setTitle("Facturas");
            ventanavisor.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL EXPORTAR DOCUMENTO");
        }
    }
}

