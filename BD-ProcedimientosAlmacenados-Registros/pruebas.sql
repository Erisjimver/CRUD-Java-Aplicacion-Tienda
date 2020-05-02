select f.IdFactura as ID, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as PRODUCTO,dv.valor_unitario as V_UNITARIO,dv.Valor_Total as TOTAL from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente order by f.Fecha_Emision desc

DateChooserFactura.getDateEditor().addPropertyChangeListener(new java.beans.PropertyChangeListener(){ 
        public void propertyChange(java.beans.PropertyChangeEvent e) {
                //Aquí agregaremos la funcionalidad que queremos
                //por ejemplo al seleccionar una fecha le mostrare un diálogo con la fecha de hoy
               // JOptionPane.showMessageDialog(rootPane, "la fecha es "+new Date());
        }
});
.
select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total,f.Fecha_Emision as FECHA_COMPRA from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('28/04/2020','DD/MM/YYYY') order by f.Fecha_Emision desc



    private void agregarOyente7(){

        DateChooserFactura.addPropertyChangeListener((java.beans.PropertyChangeEvent evt) -> {
            if (evt.getPropertyName().compareTo("day") == 0) {
     
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                LabelEstadoV.setText(formatoDeFecha.format(DateChooserFactura.getDate()));
                System.out.println(formatoDeFecha.format(DateChooserFactura.getDate()));
                System.out.println("llego aca");
            }
        });
    }





        private void agregarOyente1() { 
        try{
        DateChooserFactura.getDateEditor().addPropertyChangeListener(new java.beans.PropertyChangeListener(){ 
        public void propertyChange(java.beans.PropertyChangeEvent e) {
     
                    //   if (e.getPropertyName().compareTo("day") != 0) {
                SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
                LabelEstadoV.setText(formatoDeFecha.format(DateChooserFactura.getDate()));
                buscarFacturasDetallesDia(formatoDeFecha.format(DateChooserFactura.getDate()));
      
     //   }

        }
    });
              }catch(Exception e){
                    System.out.println("no se k pasa"+e);
                    }
    }


--este si funciona para extraer año
select extract(year from fecha_emision) from factura where fecha_emision > to_date('27/04/2020', 'dd/mm/yyyy') and fecha_emision < to_date('29/04/2020', 'dd/mm/yyyy');



select * from factura where fecha_emision > to_date('27/04/2020', 'dd/mm/yyyy') and fecha_emision < to_date('29/04/2020', 'dd/mm/yyyy');

select * from factura where fecha_emision > to_date('29/04/2020', 'dd/mm/yyyy')-1 between fecha_emision < to_date('29/04/2020', 'dd/mm/yyyy')+1;

SELECT * FROM Factura WHERE fecha_emision > to_date('25/04/2020', 'dd/mm/yyyy');



extract(year from fechanacimiento)





select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('28/04/2020','DD/MM/YYYY') and f.Fecha_Emision < to_date('28/04/2020','DD/MM/YYYY')+1 order by f.Fecha_Emision desc;

select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('"+fecha+"','DD/MM/YYYY') and f.Fecha_Emision < to_date('"+fecha+"','DD/MM/YYYY')+1 order by f.Fecha_Emision desc

select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('28/04/2020','DD/MM/YYYY') and f.Fecha_Emision < to_date('28/05/2020','DD/MM/YYYY')+1 order by f.Fecha_Emision desc

select * from factura where fecha_emision > to_date('04','mm') and fecha_emision < to_date('04','mm')+1;
select * from factura where fecha_emision > to_date('04','mm')-1 and fecha_emision < to_date('04','mm')+1;
select * from factura where fecha_emision >= to_date('04','mm');

select * from factura where fecha_emision > to_char(sysdate,'mm');



select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.valor_unitario,dv.Valor_Total from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente where f.Fecha_Emision > to_date('04','MM') order by f.Fecha_Emision desc;