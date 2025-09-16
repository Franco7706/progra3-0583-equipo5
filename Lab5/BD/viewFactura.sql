-- Crea o reemplaza la vista que reúne cabecera, moneda, acreedor, país y detalles
CREATE OR REPLACE VIEW VW_FACTURA_COMPLETA AS
SELECT
  /*  1 */ f.FACTURA_ID                          AS FACTURA_ID,
  /*  2 */ f.NUMERO_FACTURA                      AS NUMERO_FACTURA,
  /*  3 */ f.FECHA_EMISION                       AS FECHA_EMISION,
  /*  4 */ f.FECHA_RECEPCION                     AS FECHA_RECEPCION,
  /*  5 */ f.FECHA_LIMITE_PAGO                   AS FECHA_LIMITE_PAGO,
  /*  6 */ f.ESTADO                              AS ESTADO,
  /*  7 */ f.MONTO_TOTAL                         AS MONTO_TOTAL,
  /*  8 */ f.MONTO_IGV                           AS MONTO_IGV,
  /*  9 */ f.MONTO_RESTANTE                      AS MONTO_RESTANTE,
  /* 10 */ f.REGIMEN_FISCAL                      AS REGIMEN_FISCAL,
  /* 11 */ f.TASA_IVA                            AS TASA_IVA,          
  /* 12 */ f.OTROS_TRIBUTOS                      AS OTROS_TRIBUTOS,
  /* Moneda */
  /* 13 */ m.MONEDA_ID                           AS MONEDA_ID,
  /* 14 */ m.NOMBRE                              AS MONEDA_NOMBRE,
  /* 15 */ m.CODIGO_ISO                          AS MONEDA_CODIGO_ISO,
  /* 16 */ m.SIMBOLO                             AS MONEDA_SIMBOLO,
  /* Acreedor */
  /* 17 */ a.ACREEDOR_ID                         AS ACREEDOR_ID,
  /* 18 */ a.RAZON_SOCIAL                        AS ACREEDOR_RAZON_SOCIAL,
  /* 19 */ a.RUC                                 AS ACREEDOR_RUC,
  /* 20 */ a.DIRECCION_FISCAL                    AS ACREEDOR_DIRECCION_FISCAL,
  /* 21 */ a.CONDICION                           AS ACREEDOR_CONDICION,
  /* 22 */ a.PLAZO_DE_PAGO                       AS ACREEDOR_PLAZO_DE_PAGO,
  /* 23 */ a.ACTIVO                              AS ACREEDOR_ACTIVO,
  /* País del acreedor */
  /* 24 */ p.PAIS_ID                             AS PAIS_ID,
  /* 25 */ p.NOMBRE                              AS PAIS_NOMBRE,
  /* Detalles (pueden ser NULL si no hay detalles) */
  /* 26 */ df.DETALLE_FACTURA_ID                 AS DETALLE_FACTURA_ID,
  /* 27 */ df.SUBTOTAL                           AS DETALLE_SUBTOTAL,
  /* 28 */ df.DESCRIPCION                        AS DETALLE_DESCRIPCION
FROM PA_FACTURAS f
JOIN PA_MONEDAS        m  ON m.MONEDA_ID   = f.MONEDA_ID
JOIN PA_ACREEDORES     a  ON a.ACREEDOR_ID = f.ACREEDOR_ID
JOIN PA_PAISES         p  ON p.PAIS_ID     = a.PAIS_ID
LEFT JOIN PA_DETALLES_FACTURA df ON df.FACTURA_ID = f.FACTURA_ID;