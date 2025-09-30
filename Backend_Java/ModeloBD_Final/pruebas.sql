select * from pa_propuestas_de_pago;
select * from pa_usuarios;
select * from pa_paises;
select * from pa_entidades_bancarias;
delete from pa_paises;
delete from pa_usuarios;
delete from pa_usuario_pais_acceso;
delete from pa_entidades_bancarias;
delete from pa_propuestas_de_pago;
delete from pa_acreedores;
delete from pa_cuentas_acreedor;
delete from pa_cuentas_propias;
delete from pa_facturas;
delete from pa_detalles_factura;
delete from pa_detalles_propuesta;
SET SQL_SAFE_UPDATES = 0;
SELECT u.USUARIO_ID, u.CORREO_ELECTRONICO, u.ACTIVO, u.PASSWORD_HASH, u.SUPERUSUARIO, u.FECHA_ELIMINACION, u.USUARIO_ELIMINACION,
                 p.PAIS_ID, p.NOMBRE, p.CODIGO_ISO, p.CODIGO_TELEFONICO, upa.ACCESO
                FROM PA_USUARIOS u LEFT JOIN PA_USUARIO_PAIS_ACCESO upa ON u.USUARIO_ID = upa.USUARIO_ID
                LEFT JOIN PA_PAISES p ON p.PAIS_ID = upa.PAIS_ID;

CREATE OR REPLACE VIEW VW_PROPUESTA_COMPLETA AS
SELECT 
    -- Propuesta
    p.PROPUESTA_DE_PAGO_ID         AS P1_PROPUESTA_DE_PAGO_ID,
    p.FECHA_HORA_CREACION          AS P2_FECHA_HORA_CREACION,
    p.FECHA_HORA_MODIFICACION      AS P3_FECHA_HORA_MODIFICACION,
    p.USUARIO_CREACION             AS P4_USUARIO_CREACION,
    uc.CORREO_ELECTRONICO          AS P5_USUARIO_CREACION_CORREO,
    uc.ACTIVO                      AS P6_USUARIO_CREACION_ACTIVO,
    uc.SUPERUSUARIO                AS P7_USUARIO_CREACION_SUPERUSUARIO,
    p.USUARIO_MODIFICACION         AS P8_USUARIO_MODIFICACION,
    um.CORREO_ELECTRONICO          AS P9_USUARIO_MODIFICACION_CORREO,
    um.ACTIVO                      AS P10_USUARIO_MODIFICACION_ACTIVO,
    um.SUPERUSUARIO                AS P11_USUARIO_MODIFICACION_SUPERUSUARIO,
    p.ESTADO                       AS P12_ESTADO_PROPUESTA,
    p.ENTIDAD_BANCARIA_ID          AS P13_ENTIDAD_BANCARIA_PROPUESTA,
    ebp.NOMBRE                     AS P14_ENTIDAD_BANCARIA_NOMBRE,
    ebp.FORMATO_ACEPTADO           AS P15_ENTIDAD_BANCARIA_FORMATO,
    ebp.CODIGO_SWIFT               AS P16_ENTIDAD_BANCARIA_SWIFT,
    ebp.PAIS_ID                    AS P17_ENTIDAD_BANCARIA_PAIS_ID,
    pbp.NOMBRE                     AS P18_ENTIDAD_BANCARIA_PAIS_NOMBRE,
    -- Detalle de propuesta
    dp.DETALLE_PROPUESTA_ID        AS D1_DETALLE_PROPUESTA_ID,
    dp.MONTO_PAGO                  AS D2_MONTO_PAGO,
    dp.FORMA_PAGO                  AS D3_FORMA_PAGO,
    -- Factura
    f.FACTURA_ID                   AS F1_FACTURA_ID,
    f.NUMERO_FACTURA               AS F2_NUMERO_FACTURA,
    f.FECHA_EMISION                AS F3_FECHA_EMISION,
    f.FECHA_RECEPCION              AS F4_FECHA_RECEPCION,
    f.FECHA_LIMITE_PAGO            AS F5_FECHA_LIMITE_PAGO,
    f.ESTADO                       AS F6_ESTADO_FACTURA,
    f.MONTO_TOTAL                  AS F7_MONTO_TOTAL,
    f.MONTO_IGV                    AS F8_MONTO_IGV,
    f.MONTO_RESTANTE               AS F9_MONTO_RESTANTE,
    f.REGIMEN_FISCAL               AS F10_REGIMEN_FISCAL,
    f.TASA_IVA                     AS F11_TASA_IVA,
    f.OTROS_TRIBUTOS               AS F12_OTROS_TRIBUTOS,
    f.MONEDA_ID                    AS F13_MONEDA_ID,
    f.ACREEDOR_ID                  AS F14_ACREEDOR_ID,
    -- Cuenta acreedor (destino)
    ca.CUENTA_ACREEDOR_ID          AS CA1_CUENTA_ACREEDOR_ID,
    ca.TIPO_CUENTA                 AS CA2_TIPO_CUENTA,
    ca.NUMERO_CUENTA               AS CA3_NUMERO_CUENTA,
    ca.CCI                         AS CA4_CCI,
    ca.ACTIVO                      AS CA5_ACTIVO,
    ca.ACREEDOR_ID                 AS CA6_ACREEDOR_ID,
    ca.ENTIDAD_BANCARIA_ID         AS CA7_ENTIDAD_BANCARIA_ID,
    ca.MONEDA_ID                   AS CA8_MONEDA_ID,
    eba.NOMBRE                     AS CA9_ENTIDAD_BANCARIA_NOMBRE,
    eba.CODIGO_SWIFT               AS CA10_ENTIDAD_BANCARIA_SWIFT,
    eba.PAIS_ID                    AS CA11_ENTIDAD_BANCARIA_PAIS_ID,
    pba.NOMBRE                     AS CA12_ENTIDAD_BANCARIA_PAIS_NOMBRE,
    -- Cuenta propia (origen)
    cp.CUENTA_PROPIA_ID            AS CP1_CUENTA_PROPIA_ID,
    cp.SALDO_DISPONIBLE            AS CP2_SALDO_DISPONIBLE,
    cp.TIPO_CUENTA                 AS CP3_TIPO_CUENTA,
    cp.NUMERO_CUENTA               AS CP4_NUMERO_CUENTA,
    cp.CCI                         AS CP5_CCI,
    cp.ACTIVO                      AS CP6_ACTIVO,
    cp.ENTIDAD_BANCARIA_ID         AS CP7_ENTIDAD_BANCARIA_ID,
    cp.MONEDA_ID                   AS CP8_MONEDA_ID,
    ebo.NOMBRE                     AS CP9_ENTIDAD_BANCARIA_NOMBRE,
    ebo.CODIGO_SWIFT               AS CP10_ENTIDAD_BANCARIA_SWIFT,
    ebo.PAIS_ID                    AS CP11_ENTIDAD_BANCARIA_PAIS_ID,
    pbo.NOMBRE                     AS CP12_ENTIDAD_BANCARIA_PAIS_NOMBRE
FROM PA_PROPUESTAS_DE_PAGO p
JOIN PA_ENTIDADES_BANCARIAS ebp ON ebp.ENTIDAD_BANCARIA_ID = p.ENTIDAD_BANCARIA_ID
JOIN PA_PAISES pbp ON pbp.PAIS_ID = ebp.PAIS_ID
LEFT JOIN PA_USUARIOS uc ON uc.USUARIO_ID = p.USUARIO_CREACION
LEFT JOIN PA_USUARIOS um ON um.USUARIO_ID = p.USUARIO_MODIFICACION
LEFT JOIN PA_DETALLES_PROPUESTA dp ON dp.PROPUESTA_DE_PAGO_ID = p.PROPUESTA_DE_PAGO_ID
LEFT JOIN PA_FACTURAS f ON f.FACTURA_ID = dp.FACTURA_ID
LEFT JOIN PA_CUENTAS_ACREEDOR ca ON ca.CUENTA_ACREEDOR_ID = dp.CUENTA_ACREEDOR_ID
LEFT JOIN PA_ENTIDADES_BANCARIAS eba ON eba.ENTIDAD_BANCARIA_ID = ca.ENTIDAD_BANCARIA_ID
LEFT JOIN PA_PAISES pba ON pba.PAIS_ID = eba.PAIS_ID
LEFT JOIN PA_CUENTAS_PROPIAS cp ON cp.CUENTA_PROPIA_ID = dp.CUENTA_PROPIA_ID
LEFT JOIN PA_ENTIDADES_BANCARIAS ebo ON ebo.ENTIDAD_BANCARIA_ID = cp.ENTIDAD_BANCARIA_ID
LEFT JOIN PA_PAISES pbo ON pbo.PAIS_ID = ebo.PAIS_ID
WHERE dp.FECHA_ELIMINACION IS NULL;