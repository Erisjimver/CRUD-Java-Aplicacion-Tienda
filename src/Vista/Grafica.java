package Vista;

import java.awt.Color;
import java.awt.Graphics;

public final class Grafica extends javax.swing.JPanel {

    //declarando variables
    private int limiteancho,limitealto,ventaestimada,inversion,capital,x2=0,y2=310;
    public Grafica() {
        this.limiteancho=0;
        initComponents();
    }
    
        public void CargarCoordenadas(int limiteancho,int ve,int inv,int cap){
        this.limiteancho=limiteancho; 
        this.ventaestimada=ve;
        this.inversion=inv;
        this.capital=cap;
    }

//----------------------------------------------------------------------------//
//----------------------DIBUJAR PARAMETROS DE INICIO -------------------------//
//----------------------------------------------------------------------------//
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); 
        //establece color
        g.setColor(Color.GREEN);
        
        //DIBUJAR LINEA MEDIA
        g.drawLine(0, 157, 480, 158);

        g.setColor(Color.CYAN);
        
        //DIBUJAR PUNTO BOT 
        g.drawOval(0, 310, 5, 5);
        
        //DIBUJAR PUNTO TOP 
        g.drawOval(467, 0, 5, 5);
               
        //DIBUJAR PUNTO MID 
        g.drawOval(234, 154, 5, 5);
        
        //dibujar linea de ovalos para mostrar ruta
        g.setColor(Color.ORANGE);
        for(int x1 = 0 ; x1<=480 ; x1+=5 ){            
           g.drawOval(x2+=15, y2-=10, 5, 5);
        }


    }
    
    
    //este metodo permite repintar todo nuevamente cada vez que precion un click o que cargue la ventana
    public void Dibujar(){
        repaint();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 314, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMousePressed
        // TODO add your handling code here:
            //*************DIBUJAR PUNTOS
       
    //CREAR LIENZO
    Graphics g1 = this.getGraphics();
    g1.setColor(Color.GREEN);
    //DIBUJAR PUNTO
    //g.drawRect(evt.getX(), evt.getY(), 5, 5);
    g1.drawRect(evt.getX(), evt.getY(), 5, 5);

        System.out.println("X :"+evt.getX());
        System.out.println("X :"+evt.getY());
    //g.drawRect(5, 5, 5, 5);
    }//GEN-LAST:event_formMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
