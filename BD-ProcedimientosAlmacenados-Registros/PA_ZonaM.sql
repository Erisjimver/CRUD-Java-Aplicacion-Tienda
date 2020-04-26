----------------------------------------------------------------------------------------------------------------------
---------REGISTRA
----------------------------------------------------------------------------------------------------------------------
--registro a empresa
create or replace procedure RegistrarEmpresa
(
	IdEmpresa          in number,
	Ruc                in varchar2,
	NombreEmpresa      in varchar2,
	Telefono           in number,
	Direccion          in varchar2
)
as
begin
  insert into Empresa values(IdEmpresa,Ruc,NombreEmpresa,Telefono,Direccion);
end RegistrarEmpresa;
/
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
------secuencia para incremento
CREATE SEQUENCE secuencia_idvendedor
START WITH 3
INCREMENT BY 1 
NOMAXVALUE;

--registro de empleados
create or replace procedure RegistrarVendedor
(
  IdTipo_Usuario in number, 
  IdEmpresa in number,
  Contrasena in varchar2,
  Cedula in varchar2,
  Nombres in varchar2, 
  Apellidos in varchar2,
  Telefono in varchar2, 
  Direccion in varchar2 
)
as
begin
  insert into Vendedor values(secuencia_idvendedor.nextval,IdTipo_Usuario,IdEmpresa,Contrasena,Cedula,Nombres,Apellidos,Telefono,Direccion);
end RegistrarVendedor;
/

---actualizar Empleado
create or replace procedure ActualizarEmpleados 
(
pIdVendedor in number,
pIdTipo_Usuario in number,
pIdEmpresa in number,
pContrasena  in varchar2,
pCedula in number,
pNombres in varchar2,
pApellidos in varchar2,
pTelefono in varchar2,
pDireccion in varchar2
) 
as 
begin
  update Vendedor set IdTipo_Usuario=pIdTipo_Usuario, IdEmpresa=pIdEmpresa, Contrasena=pContrasena, Cedula=pCedula,
  Nombres=pNombres, Apellidos=pApellidos, Telefono = pTelefono, Direccion=pDireccion 
  where IdVendedor=pIdVendedor;
end ActualizarEmpleados;
/
--Delete User
--Eliminar de empleados
create or replace procedure EliminarEmpleado
(
  id_empleado in number
)
as
begin
	delete from Vendedor where idvendedor = id_empleado;
end EliminarEmpleado;
/


/*
----triger disparar secuencia
CREATE TRIGGER tr_idvendedor
  BEFORE INSERT ON Vendedor
  FOR EACH ROW
  BEGIN
    SELECT secuencia_idvendedor.nextval INTO :new.idvendedor FROM dual;
  END
;
/*/
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
 --secuencia id categoria
CREATE SEQUENCE secuencia_idcategoria
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
--registar categoria	
create or replace procedure RegistrarCategorias
(
 Descripcion in varchar2
 )
 as
 begin
 insert into Categoria values(secuencia_idcategoria.nextval,Descripcion);
 end RegistrarCategorias;
 /
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
 --secuencia id productos
CREATE SEQUENCE secuencia_idproducto
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;

--registrar productos
create or replace procedure RegistrarProductos
(
 IdCategoria in number,
 NombreProducto in varchar2,
 Marca in varchar2,
 Costo in number,
 Precio in number,
 Stock in number
)
as 
begin 
insert into Producto values(secuencia_idproducto.nextval,IdCategoria,NombreProducto,Marca,Costo,Precio,Stock,sysdate);
end RegistrarProductos;
/
--eliminar productos
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
----------------------------------------------------------------------------------------------------------------------
--secuencia id clientes
CREATE SEQUENCE secuencia_idclientes
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;

--registrar clientes
create or replace procedure RegistrarClientes
(
 Cedula in number,
 Nombre in varchar2,
 Telefono in number,
 Direccion in varchar2
)
as
begin
insert into Clientes values(secuencia_idclientes.nextval,Cedula,Nombre,Telefono,Direccion);
end RegistrarClientes;
/

----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
--secuencia id factura
CREATE SEQUENCE secuencia_idfactura
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;
--registrar Factura

create or replace procedure RegistrarFactura
(
 IdEmpleado in number,
 IdCliente in number
)
as
begin
insert into Factura values(secuencia_idfactura.nextval,IdEmpleado,IdCliente,sysdate);
end RegistrarFactura;
/
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------   
--secuencia id detalle venta
CREATE SEQUENCE secuencia_iddetalle_venta
START WITH 1
INCREMENT BY 1 
NOMAXVALUE;

--registrar Detalle de venta
create or replace procedure RegistrarDetalleV
	( IdFactura in number, 
		IdProducto in number, 
		Cantidad in number, 
		ValorUnitario in number, 
		ValorTotal in number)
as
begin
insert into Detalle_Venta values(secuencia_iddetalle_venta.nextval,IdFactura,IdProducto,Cantidad,ValorUnitario,ValorTotal);
commit;
end RegistrarDetalleV;
/
--EXEC RegistrarDetalleV(2,1,1,2,2);
----------------------------------------------------------------------------------------------------------------------
-------------------ACTUALIZA------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
---actualizar productos
create or replace procedure ActualizarProductos 
(
pIdProducto in number,
pNombreProducto in varchar2,
pMarca in varchar2,
pCosto in number,
pPrecio in number,
pStock in number
) 
as 
begin
  update Producto set NombreProducto=pNombreProducto, Marca=pMarca, Costo=pCosto, Precio=pPrecio, Stock=pStock 
  where IdProducto=pIdProducto;
  commit;
end ActualizarProductos;
/
EXEC ActualizarProductos(1,'auriculares','sansung',2.5,5,10);
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
---actualizar categorias
create or replace procedure ActualizarCategorias
(
pIdCategoria in number,
pNombre in varchar2,
pDescripcion in varchar2
) 
as 
begin
  update Categoria set Nombre=pNombre, Descripcion=pDescripcion 
  where IdCategoria=pIdCategoria;
end ActualizarCategorias;
/
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
---actualizar Empresa o sucursal
create or replace procedure ActualizarSucursal
(
pIdEmpresa in number,
pRuc in varchar2,
pNombreEmpresa in varchar2,
pTelefono in number,
pDireccion in varchar2
) 
as 
begin
  update Empresa set Ruc=pRuc, NombreEmpresa=pNombreEmpresa, Telefono=pTelefono,Direccion=pDireccion 
  where IdEmpresa=pIdEmpresa;
end ActualizarSucursal;
/

---------------------------------------------------------------------------------------------------------------------
-----------------------------------------Procedimientos adicionales -------------------------------------------------
---------------------------------------------------------------------------------------------------------------------

--procedimiento para vender

 create or replace trigger tr_insertar_venta
 before insert
  on Detalle_Venta
  for each row
 declare
   canti number:=null;
 begin
     update Producto set Stock=Stock-:new.Cantidad where IdProducto=:new.IdProducto; 
--	 commit;
 end tr_insertar_venta;
 /

--el commit me dio problemas no se porque luego lo averiguo

 --select p.NombreProducto,p.Marca,p.Precio,p.Stock,c.Nombre from Producto p inner join Categoria c on p.IdCategorias = c.IdCategorias where p.NombreProducto like 'A%' or c.Nombre like'M%';
