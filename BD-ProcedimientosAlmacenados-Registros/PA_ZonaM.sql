----------------------------------------------------------------------------------------------------------------------
---------------------------------------EMPRESA------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------

---registro a empresa
create or replace procedure RegistrarEmpresa
(
	IdEmpresa          in number,
	Ruc                in varchar2,
	Nombre_Empresa     in varchar2,
	Telefono_empresa   in varchar2,
	Direccion          in varchar2,
	Email_empresa      in varchar2
)
as
begin
  insert into Empresa values(IdEmpresa,Ruc,Nombre_Empresa,Telefono_empresa,Direccion,email_empresa);
  	commit;
end RegistrarEmpresa;
/

---actualizar Empresa o sucursal
create or replace procedure ActualizarEmpresa
(
pIdEmpresa in number,
pRuc in varchar2,
pNombre_Empresa in varchar2,
pTelefono_Empresa in varchar2,
pDireccion_Empresa in varchar2,
pEmail_Empresa in varchar2
) 
as 
begin
  update Empresa set Ruc=pRuc, Nombre_Empresa=pNombreEmpresa, Telefono_Empresa=pTelefono_Empresa,Direccion_Empresa=pDireccion_Empresa,Email_Empresa=pEmail_Empresa 
  where IdEmpresa=pIdEmpresa;
  commit;
end ActualizarEmpresa;
/

---eliminar empresa /sucursal
create or replace procedure EliminarEmpresa
(
  pid_empresa in number
)
as
begin
	delete from Empresa where IdEmpresa = pid_empresa;
end EliminarEmpresa;
/

-----------------------------------------------------------------------------------------------------------------------
---------------------------------------EMPLEADO------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------
---secuencia para incremento
CREATE SEQUENCE secuencia_id_empleado
START WITH 3
INCREMENT BY 1 
NOMAXVALUE;

---registro de empleados
create or replace procedure RegistrarEmpleado
(
  IdTipo_Usuario in number, 
  IdEmpresa in number,
  Nombre_Usuario in varchar2,
  Contrasena in varchar2,
  Cedula in varchar2,
  Nombres in varchar2, 
  Apellidos in varchar2,
  Telefono in varchar2, 
  Direccion in varchar2
)
as
begin
  insert into Vendedor values(secuencia_id_empleado.nextval,IdTipo_Usuario,IdEmpresa,Nombre_Usuario,Contrasena,Cedula,Nombres,Apellidos,Telefono,Direccion);
  	commit;
end RegistrarEmpleado;
/

---actualizar Empleado
create or replace procedure ActualizarEmpleado
(
pIdVendedor in number,
pIdTipo_Usuario in number,
pIdEmpresa in number,
pNombre_Usuario in varchar2,
pContrasena  in varchar2,
pCedula_Empleado in varchar2,
pNombre_cedula in varchar2,
pNombres in varchar2,
pApellidos in varchar2,
pTelefono in varchar2,
pDireccion in varchar2
)
as 
begin
  update Vendedor set IdTipo_Usuario=pIdTipo_Usuario, IdEmpresa=pIdEmpresa, Nombre_Usuario=pNombre_Usuario, Contrasena=pContrasena, Cedula_Empleado=pCedula_Empleado,
  Nombres=pNombres, Apellidos=pApellidos, Telefono = pTelefono, Direccion=pDireccion 
  where IdVendedor=pIdVendedor;
  commit;
end ActualizarEmpleado;
/

---Eliminar de empleados
create or replace procedure EliminarEmpleado
(
  id_empleado in number
)
as
begin
	delete from Vendedor where IdEmpleado = id_empleado;
end EliminarEmpleado;
/


-----------------------------------------------------------------------------------------------------------------------
---------------------------------------CATEGORIA-----------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------------------
 
---secuencia id categoria
CREATE SEQUENCE secuencia_id_categoria
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;


---registar categoria	
create or replace procedure RegistrarCategoria
(
 Descripcion in varchar2
 )
 as
 begin
 insert into Categoria values(secuencia_id_categoria.nextval,Descripcion);
 end RegistrarCategoria;
 /

---actualizar categorias
create or replace procedure ActualizarCategoria
(
pIdCategoria in number,
pDescripcion in varchar2
) 
as 
begin
  update Categoria set Descripcion=pDescripcion 
  where IdCategoria=pIdCategoria;
end ActualizarCategoria;
/

---Eliminar de empleados
create or replace procedure EliminarCategoria
(
  id_categoria in number
)
as
begin
	delete from Categoria where IdCategoria = id_categoria;
	commit;
end EliminarCategoria;
/

----------------------------------------------------------------------------------------------------------------------
---------------------------------------PRODUCTO-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------

---secuencia id productos
CREATE SEQUENCE secuencia_id_producto
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;

---registrar productos
 create or replace procedure RegistrarProducto
(
 IdCategoria in number,
 Nombre_Producto in varchar2,
 Marca in varchar2,
 Costo in number,
 Precio_Venta in number,
 Stock in number
)
as 
begin 
insert into Producto values(secuencia_id_producto.nextval,IdCategoria,Nombre_Producto,Marca,Costo,Precio_Venta,Stock,sysdate);
commit;
end RegistrarProducto;
/


---actualizar productos
create or replace procedure ActualizarProducto 
(
pIdProducto in number,
pNombre_Producto in varchar2,
pMarca in varchar2,
pCosto in number,
pPrecio_Venta in number,
pStock in number
) 
as 
begin
  update Producto set Nombre_Producto=pNombre_Producto, Marca=pMarca, Costo=pCosto, Precio_Venta=pPrecio_Venta, Stock=pStock 
  where IdProducto=pIdProducto;
  commit;
end ActualizarProducto;
/

--EXEC ActualizarProductos(1,'auriculares','sansung',2.5,5,10);

---eliminar productos
create or replace procedure EliminarProducto
(
  id_producto in number
)
as
begin
	delete from Producto where idproducto = id_producto;
end EliminarProducto;
/

----------------------------------------------------------------------------------------------------------------------
---------------------------------------CLIENTES-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------

---secuencia id clientes
CREATE SEQUENCE secuencia_id_cliente
START WITH 2--iniciar en dos y registrar el 1 en una sentencia aparte
INCREMENT BY 1 
NOMAXVALUE;

---registrar clientes
create or replace procedure RegistrarCliente
(
 Cedula_CLiente in varchar2,
 Nombre_Cliente in varchar2,
 Telefono_Cliente in varchar2,
 Direccion_Cliente in varchar2,
 Email_Cliente in varchar2
)
as
begin
insert into Cliente values(secuencia_id_cliente.nextval,Cedula_Cliente,Nombre_Cliente,Telefono_Cliente,Direccion_CLiente,Email_Cliente);
commit;
end RegistrarCliente;
/
EXEC RegistrarCliente('1207130888','Manuel','0980640987','Quevedo','email_cliente@gmail.com');

---actualizar clientes
create or replace procedure ActualizarCliente
(
pIdCliente in number,
pNombre in varchar2,
pTelefono in varchar2,
pDireccion in varchar2,
pEmail in varchar2
)
as 
begin
  update Producto set Nombre_Cliente=pNombre, Telefono_CLiente=pTelefono, Direccion_CLiente=pDireccion,Email_Cliente=pEmail 
  where IdCliente=pIdCliente;
commit;
end ActualizarCliente;
/

----------------------------------------------------------------------------------------------------------------------
---------------------------------------FACTURAS-----------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------

---secuencia id factura
CREATE SEQUENCE secuencia_idfactura
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;

---registrar Factura
create or replace procedure RegistrarFactura
(
 IdEmpleado in number,
 IdCliente in number
)
as
begin
insert into Factura values(secuencia_idfactura.nextval,IdEmpleado,IdCliente,sysdate);
	commit;
end RegistrarFactura;
/

----------------------------------------------------------------------------------------------------------------------
---------------------------------------DETALLE VENTAS-----------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------  

---secuencia id detalle venta
CREATE SEQUENCE secuencia_iddetalle_venta
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;

---registrar Detalle de venta
create or replace procedure RegistrarDetalleV
( 
 IdFactura in number, 
 IdProducto in number, 
 Cantidad in number, 
 Valor_Unitario in number, 
 Valor_Total in number
)
as
begin
insert into Detalle_Venta values(secuencia_iddetalle_venta.nextval,IdFactura,IdProducto,Cantidad,Valor_Unitario,Valor_Total);
commit;
end RegistrarDetalleV;
/

--EXEC RegistrarDetalleV(1,1,1,5,5);

----------------------------------------------------------------------------------------------------------------------
---------------------------------------PROCEDIMIENTOS ADICIONALES-----------------------------------------------------
---------------------------------------------------------------------------------------------------------------------- 
--procedimiento para vender

 create or replace trigger tr_insertar_venta
 before insert
  on Detalle_Venta
  for each row
 declare
   canti number:=null;
 begin
     update Producto set Stock=Stock-:new.Cantidad where IdProducto=:new.IdProducto; 
	-- commit;
 end tr_insertar_venta;
 /


--------------------------
--drop tablespace TspZonaMovil including contents and datafiles;
--start C:\Users\Home\Documents\NetBeansProjects\Java-Aplicacion-registro-consulta-venta-master\BD-ProcedimientosAlmacenados-Registros\bdZonaMovil.sql;
--SELECT table_name, tablespace_name, num_rows FROM user_tables;
--describe productos; esto sirve para desciribr las columnas de una tabla
--clear screen  // sirve para borrar la pantalla de la consola de oracle

--select IdEmpleado to_char(FECHA_EMISION, 'DD/MM/YYYY') as FECHA from Factura where FECHA_EMISION=sysdate;

--SELECT sysdate,
--    to_char((from_tz(to_timestamp(to_char(sysdate, 'YYYY-MM-DD HH:MI:SS PM'), 'YYYY-MM-DD HH:MI:SS PM') ,'America/New_York') at time zone 'UTC'),'YYYY-MM-DD"T"HH24:MI:SS.ff3"Z"') "iso8601"
--FROM dual

--SELECT * FROM Factura WHERE fecha_emision > to_date('28/04/2020', 'DD/MM/YYYY') ;

--consulta de factura por fecha
--select f.IdFactura, dv.Cantidad, p.Nombre_Producto,dv.Valor_Total,f.Fecha_Emision from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto =  dv.IdProducto where f.Fecha_Emision > to_date('28/04/2020','DD/MM/YYYY') order by f.Fecha_Emision desc;  

/*
select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO, dv.valor_unitario, dv.Valor_Total, f.Fecha_Emision as FECHA_COMPRA 
from Factura f 
inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura 
inner join Producto p on p.IdProducto = dv.IdProducto
inner join Cliente c on c.idCliente = f.idCliente
order by f.Fecha_Emision desc;

*/

--select f.IdFactura as CODIGO, dv.Cantidad as CANTIDAD, c.Nombre_Cliente as CLIENTE, p.Nombre_Producto as NOMBRE_PRODUCTO,dv.Valor_Total,f.Fecha_Emision as FECHA_COMPRA from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura inner join Producto p on p.IdProducto = dv.IdProducto inner join Cliente c on c.idCliente = f.idCliente order by f.Fecha_Emision desc;

