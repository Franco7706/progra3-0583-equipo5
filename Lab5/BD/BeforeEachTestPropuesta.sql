insert into pa_paises (nombre,codigo_iso,codigo_telefonico) values ("Peru","PE","+51");
insert into pa_entidades_bancarias (nombre,formato_aceptado,codigo_swift,pais_id) values("BCP","XML","102030123",1);

delete from pa_detalles_propuesta;
delete from pa_propuestas_de_pago;
SET SQL_SAFE_UPDATES = 0;
select * from pa_propuestas_de_pago;

select * from pa_entidades_bancarias;

select * from pa_paises;

select * from vw_propuesta_completa;

select * from pa_acreedores;
delete from pa_acreedores;
delete from pa_entidades_bancarias;

INSERT INTO PA_ACREEDORES (RAZON_SOCIAL,RUC,DIRECCION_FISCAL,CONDICION,PLAZO_DE_PAGO,ACTIVO,PAIS_ID)
VALUES ("franco","franco","franco","franco",30,"S",1);

SELECT a.ACREEDOR_ID, a.RAZON_SOCIAL, a.RUC, a.DIRECCION_FISCAL, a.CONDICION,
                a.PLAZO_DE_PAGO, a.ACTIVO, a.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO 
                FROM PA_ACREEDORES a JOIN PA_PAISES p ON a.PAIS_ID=p.PAIS_ID WHERE a.ACREEDOR_ID=1;
delete from pa_paises;
delete from pa_entidades_bancarias;
delete from pa_propuestas_de_pago;

select * from pa_monedas;
select * from pa_paises;
select * from pa_tipos_de_cambio;
delete from pa_monedas;


delete from pa_paises;
delete from pa_entidades_bancarias;
delete from pa_acreedores;
delete from pa_cuentas_acreedor;

delete from pa_propuestas_de_pago;
select * from pa_usuarios;
select * from pa_detalles_factura;
select * from pa_cuentas_propias;
delete from pa_cuentas_propias;
delete from pa_entidades_bancarias;
delete from pa_paises;
delete from pa_usuarios;
delete from pa_monedas;
delete from pa_tipos_de_cambio;
delete from pa_detalles_factura;
delete from pa_facturas;
delete from pa_acreedores;
delete from pa_detalles_propuesta;
insert into pa_usuarios values (999, "xd", "S", "xd", "N");
SELECT u.USUARIO_ID, u.CORREO_ELECTRONICO, u.ACTIVO, u.PASSWORD_HASH, u.SUPERUSUARIO, p.PAIS_ID,
                 p.NOMBRE AS PAIS, p.CODIGO_ISO, p.CODIGO_TELEFONICO, upa.ACCESO
                 FROM PA_USUARIOS u LEFT JOIN PA_USUARIO_PAIS_ACCESO upa ON u.USUARIO_ID = upa.USUARIO_ID
				 LEFT JOIN PA_PAISES p ON p.PAIS_ID = upa.PAIS_ID;
                