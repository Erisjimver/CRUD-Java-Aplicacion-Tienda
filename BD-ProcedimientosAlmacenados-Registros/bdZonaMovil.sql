disconnect;
connect system/59291;

drop tablespace TspZonaMovil including contents and datafiles; 
drop user proyecto cascade;


create tablespace TspZonaMovil
datafile 'data_ZonaMovil.dbf'
size  30M autoextend on;

create user proyecto
identified by 59291
default tablespace TspZonaMovil;

grant connect  to proyecto;
grant resource to proyecto;
grant create view to proyecto;
disconnect;

connect proyecto/59291;



CREATE TABLE Empresa
(
	IdEmpresa          number NOT NULL PRIMARY KEY,
	Ruc                NVARCHAR2(13) NOT NULL,
	Nombre_Empresa     NVARCHAR2(40) NOT NULL,
	Telefono_Empresa   nvarchar2(10) NOT NULL,
	Direccion_Empresa  NVARCHAR2(40) NOT NULL,
	Email_Empresa      nvarchar2(40) not null
);

----------------------------Empresa------------------------------------------------------
insert into Empresa values(1,'1207130889001','Zona Movil','052970665','Av.Seminario','empresa@gmail.com');


CREATE TABLE Tipo_Usuario
(
	IdTipo_Usuario     number NOT NULL PRIMARY KEY,
    Tipo_Usuario       NVARCHAR2(13)  NOT NULL
);

----------------------------Tipo_Usuario-------------------------------------------------
insert into Tipo_Usuario values(1,'Administrador');	
insert into Tipo_Usuario values(2,'Vendedor');


CREATE TABLE Empleado
(
	IdEmpleado        number NOT NULL PRIMARY KEY,
	IdTipo_Usuario	  number not null,	
	IdEmpresa         number NOT NULL,
	Nombre_Usuario    nvarchar2(15) not null,
	Contrasena        NVARCHAR2(15) NOT NULL,	
	Cedula_Empleado   NVARCHAR2(10) NOT NULL,
	Nombres           NVARCHAR2(40) NOT NULL,
	Apellidos         NVARCHAR2(40) NOT NULL,
	Telefono          NVARCHAR2(13) Not null,
    Direccion         NVARCHAR2(40) NOT NULL,
	constraint CF_Empresa_Empleado foreign key(IdEmpresa) references Empresa (IdEmpresa),
	constraint CF_Tipo_usuario_Empleado foreign key(IdTipo_Usuario) references Tipo_Usuario (IdTipo_Usuario)
);
insert into Empleado values(1,1,1,'Admin','59291','1207130889','Israel','Jimenez','0980640987','5 de agosto');
insert into Empleado values(2,2,1,'User','59291','1207130888','Manuel','Cordoba','0998477454','Malecon y 9 de octubre');

CREATE TABLE Categoria
(
	IdCategoria      number NOT NULL PRIMARY KEY,
	descripcion      NVARCHAR2(20) NOT NULL
);


CREATE TABLE Producto
(
	IdProducto        number NOT NULL PRIMARY KEY,
	IdCategoria       number NOT NULL,
	Nombre_Producto   NVARCHAR2 (40) NOT NULL,
	Marca			  NVARCHAR2(15)NOT NULL,
	Costo			  number not null,
	Precio_Venta      number NOT NULL,
	Stock             number NOT NULL,			  
    Fecha_Ingreso     DATE NOT NULL,
	constraint CF_Categoria_Producto foreign key(IdCategoria) references Categoria(IdCategoria)
);

CREATE TABLE Cliente
( 
	IdCliente         number NOT NULL PRIMARY KEY,
	Cedula_Cliente    NVARCHAR2(13) NOT NULL,
	Nombre_Cliente    NVARCHAR2(40) NOT NULL,
	Telefono_Cliente  NVARCHAR2(15) NOT NULL,
	Direccion_Cliente NVARCHAR2(40) NOT NULL,
	Email_Cliente     NVARCHAR2(40) NOT NULL
);
insert into Cliente values(1,'1111','Consumidor Final','s/n','s/d','s/e');

CREATE TABLE Factura
(
	IdFactura         number NOT NULL PRIMARY KEY,
	IdEmpleado        number NOT NULL,
	IdCliente         number NOT NULL,
	Fecha_Emision      date NOT NULL,
constraint CF_Cliente_Factura foreign key(IdCliente) references Cliente(IdCliente),
constraint CF_Empleado_DFactura foreign key(IdEmpleado) references Empleado(IdEmpleado)
);
  

CREATE TABLE Detalle_Venta
(
  IdDetalle_Venta	   number NOT NULL PRIMARY KEY,
  IdFactura            number NOT NULL,
  IdProducto		   number NOT NULL,
  Cantidad             number NOT NULL,
  Valor_Unitario	   number NOT NULL,
  Valor_Total		   number NOT NULL,
constraint CF_Factura_Detalle_Ventas foreign key(IdFactura) references Factura (IdFactura),
constraint CF_Producto_Detalle_Venta foreign key(IdProducto) references Producto (IdProducto)
);

create table ControlEventos(
  usuario varchar2(13),
  fecha date,
  codigo number,
  precio_anterior number,
  precio_nuevo number
 );
 
create table ControlPrecios(
  mensaje varchar2(40),
  precio_anterior number,
  precio_nuevo number,
  fecha date
 );

--select dv.IdDetalle_Venta, dv.Cantidad, dv.Detalle,dv.ValorTotal from Factura f inner join Detalle_Venta dv on dv.IdFactura = f.IdFactura where f.fecha='26/08/2017';
--commit;