select * from pa_propuestas_de_pago;
select * from pa_usuarios;
select * from pa_paises;
select * from pa_entidades_bancarias;

SELECT u.USUARIO_ID, u.CORREO_ELECTRONICO, u.ACTIVO, u.PASSWORD_HASH, u.SUPERUSUARIO, u.FECHA_ELIMINACION, u.USUARIO_ELIMINACION,
                 p.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO, upa.ACCESO
                FROM PA_USUARIOS u LEFT JOIN PA_USUARIO_PAIS_ACCESO upa ON u.USUARIO_ID = upa.USUARIO_ID
                LEFT JOIN PA_PAISES p ON p.PAIS_ID = upa.PAIS_ID;