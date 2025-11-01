DELIMITER $$

DROP PROCEDURE IF EXISTS pa_sp_reporte_propuestas_de_pago $$
CREATE PROCEDURE pa_sp_reporte_propuestas_de_pago(
    IN p_pais_id             INT,   -- filtro por país (del banco de la propuesta)
    IN p_entidad_bancaria_id INT    -- filtro por banco de la propuesta
)
BEGIN
    INSERT INTO pa_reporte_propuestas_de_pago (
        PROPUESTA_DE_PAGO_ID,
        DETALLE_PROPUESTA_ID,
        ACREEDOR_ID,
        CUENTA_ACREEDOR_ID,
        CUENTA_PROPIA_ID,
        ENTIDAD_BANCARIA_ID,
        PAIS_ID,
        MONEDA_ID,
        MONTO_PAGO,
        CODIGO_MONEDA,
        RAZON_SOCIAL,
        NUMERO_CUENTA_ACREEDOR,
        NUMERO_CUENTA_PROPIA,
        NOMBRE_ENTIDAD_BANCARIA,
        NOMBRE_PAIS
    )
    SELECT
        src.PROPUESTA_DE_PAGO_ID,
        src.DETALLE_PROPUESTA_ID,
        src.ACREEDOR_ID,
        src.CUENTA_ACREEDOR_ID,
        src.CUENTA_PROPIA_ID,
        src.ENTIDAD_BANCARIA_ID,
        src.PAIS_ID,
        src.MONEDA_ID,
        src.MONTO_PAGO,
        src.CODIGO_MONEDA,
        src.RAZON_SOCIAL,
        src.NUMERO_CUENTA_ACREEDOR,
        src.NUMERO_CUENTA_PROPIA,
        src.NOMBRE_ENTIDAD_BANCARIA,
        src.NOMBRE_PAIS
    FROM (
        SELECT
            p.PROPUESTA_DE_PAGO_ID,
            dp.DETALLE_PROPUESTA_ID,
            a.ACREEDOR_ID,
            ca.CUENTA_ACREEDOR_ID,
            cp.CUENTA_PROPIA_ID,
            p.ENTIDAD_BANCARIA_ID,
            eb.PAIS_ID,
            ca.MONEDA_ID,
            dp.MONTO_PAGO,
            m.CODIGO_ISO                       AS CODIGO_MONEDA,
            a.RAZON_SOCIAL,
            ca.NUMERO_CUENTA                   AS NUMERO_CUENTA_ACREEDOR,
            cp.NUMERO_CUENTA                   AS NUMERO_CUENTA_PROPIA,
            eb.NOMBRE                          AS NOMBRE_ENTIDAD_BANCARIA,
            pa.NOMBRE                          AS NOMBRE_PAIS
        FROM PA_PROPUESTAS_DE_PAGO p
        JOIN PA_DETALLES_PROPUESTA  dp ON dp.PROPUESTA_DE_PAGO_ID = p.PROPUESTA_DE_PAGO_ID
        JOIN PA_CUENTAS_ACREEDOR    ca ON ca.CUENTA_ACREEDOR_ID  = dp.CUENTA_ACREEDOR_ID
        JOIN PA_CUENTAS_PROPIAS     cp ON cp.CUENTA_PROPIA_ID    = dp.CUENTA_PROPIA_ID
        JOIN PA_ACREEDORES          a  ON a.ACREEDOR_ID          = ca.ACREEDOR_ID
        JOIN PA_ENTIDADES_BANCARIAS eb ON eb.ENTIDAD_BANCARIA_ID = p.ENTIDAD_BANCARIA_ID
        JOIN PA_PAISES              pa ON pa.PAIS_ID             = eb.PAIS_ID
        JOIN PA_MONEDAS             m  ON m.MONEDA_ID            = ca.MONEDA_ID
        WHERE (p_pais_id IS NULL OR eb.PAIS_ID = p_pais_id)
          AND (p_entidad_bancaria_id IS NULL OR p.ENTIDAD_BANCARIA_ID = p_entidad_bancaria_id)
        -- ORDER BY es innecesario para INSERT; si lo quieres, déjalo aquí dentro
        -- ORDER BY p.PROPUESTA_DE_PAGO_ID, dp.DETALLE_PROPUESTA_ID
    ) AS src
    ON DUPLICATE KEY UPDATE
        ACREEDOR_ID             = src.ACREEDOR_ID,
        CUENTA_ACREEDOR_ID      = src.CUENTA_ACREEDOR_ID,
        CUENTA_PROPIA_ID        = src.CUENTA_PROPIA_ID,
        ENTIDAD_BANCARIA_ID     = src.ENTIDAD_BANCARIA_ID,
        PAIS_ID                 = src.PAIS_ID,
        MONEDA_ID               = src.MONEDA_ID,
        MONTO_PAGO              = src.MONTO_PAGO,
        CODIGO_MONEDA           = src.CODIGO_MONEDA,
        RAZON_SOCIAL            = src.RAZON_SOCIAL,
        NUMERO_CUENTA_ACREEDOR  = src.NUMERO_CUENTA_ACREEDOR,
        NUMERO_CUENTA_PROPIA    = src.NUMERO_CUENTA_PROPIA,
        NOMBRE_ENTIDAD_BANCARIA = src.NOMBRE_ENTIDAD_BANCARIA,
        NOMBRE_PAIS             = src.NOMBRE_PAIS;
END $$
DELIMITER ;


-- Regenerar todo (limpiando antes)
CALL pa_sp_reporte_propuestas_de_pago(NULL, NULL);

-- Solo Perú (PAIS_ID=1) + BCP (10), acumulando (sin borrar)
CALL pa_sp_reporte_propuestas_de_pago(1, 10);

DELIMITER $$

DROP PROCEDURE IF EXISTS pa_sp_reporte_facturas_pendientes $$
CREATE PROCEDURE pa_sp_reporte_facturas_pendientes(
    IN p_acreedor_id INT,   -- NULL = sin filtro
    IN p_pais_id     INT,   -- NULL = sin filtro
    IN p_moneda_id   INT    -- NULL = sin filtro
)
BEGIN
  INSERT INTO pa_reportes_facturas_pendientes (
      FACTURA_ID,
      ACREEDOR_ID,
      PAIS_ID,
      MONEDA_ID,
      NUMERO_FACTURA,
      RAZON_SOCIAL,
      NOMBRE_PAIS,
      CODIGO_MONEDA,
      FECHA_LIMITE_PAGO,
      DIAS_VENCIMIENTO,
      RANGO_VENCIMIENTO,
      MONTO_RESTANTE
  )
  SELECT
      src.FACTURA_ID,
      src.ACREEDOR_ID,
      src.PAIS_ID,
      src.MONEDA_ID,
      src.NUMERO_FACTURA,
      src.RAZON_SOCIAL,
      src.NOMBRE_PAIS,
      src.CODIGO_MONEDA,
      src.FECHA_LIMITE_PAGO,
      src.DIAS_VENCIMIENTO,
      src.RANGO_VENCIMIENTO,
      src.MONTO_RESTANTE
  FROM (
      SELECT
          f.FACTURA_ID,
          a.ACREEDOR_ID,
          pa.PAIS_ID,
          f.MONEDA_ID,
          f.NUMERO_FACTURA,
          a.RAZON_SOCIAL,
          pa.NOMBRE                           AS NOMBRE_PAIS,
          m.CODIGO_ISO                        AS CODIGO_MONEDA,
          f.FECHA_LIMITE_PAGO,
          DATEDIFF(f.FECHA_LIMITE_PAGO, CURDATE()) AS DIAS_VENCIMIENTO,
          CASE
            WHEN DATEDIFF(f.FECHA_LIMITE_PAGO, CURDATE()) BETWEEN 0 AND 30  THEN '0-30'
            WHEN DATEDIFF(f.FECHA_LIMITE_PAGO, CURDATE()) BETWEEN 31 AND 60 THEN '31-60'
            WHEN DATEDIFF(f.FECHA_LIMITE_PAGO, CURDATE()) BETWEEN 61 AND 90 THEN '61-90'
            ELSE '+90'
          END                                AS RANGO_VENCIMIENTO,
          f.MONTO_RESTANTE
      FROM PA_FACTURAS f
      JOIN PA_ACREEDORES a ON a.ACREEDOR_ID = f.ACREEDOR_ID
      JOIN PA_MONEDAS   m ON m.MONEDA_ID    = f.MONEDA_ID
      JOIN PA_PAISES   pa ON pa.PAIS_ID     = a.PAIS_ID
      WHERE f.ESTADO = 'PENDIENTE'
		AND DATEDIFF(f.FECHA_LIMITE_PAGO, CURDATE()) >= 0
        AND (p_acreedor_id IS NULL OR a.ACREEDOR_ID = p_acreedor_id)
        AND (p_pais_id     IS NULL OR pa.PAIS_ID    = p_pais_id)
        AND (p_moneda_id   IS NULL OR f.MONEDA_ID   = p_moneda_id)
  ) AS src
  ON DUPLICATE KEY UPDATE
      ACREEDOR_ID        = src.ACREEDOR_ID,
      PAIS_ID            = src.PAIS_ID,
      MONEDA_ID          = src.MONEDA_ID,
      NUMERO_FACTURA     = src.NUMERO_FACTURA,
      RAZON_SOCIAL       = src.RAZON_SOCIAL,
      NOMBRE_PAIS        = src.NOMBRE_PAIS,
      CODIGO_MONEDA      = src.CODIGO_MONEDA,
      FECHA_LIMITE_PAGO  = src.FECHA_LIMITE_PAGO,
      DIAS_VENCIMIENTO   = src.DIAS_VENCIMIENTO,
      RANGO_VENCIMIENTO  = src.RANGO_VENCIMIENTO,
      MONTO_RESTANTE     = src.MONTO_RESTANTE;
END $$
DELIMITER ;

-- Regenerar todo (limpiando antes)
CALL pa_sp_reporte_facturas_pendientes(NULL, NULL, NULL);

-- Solo acreedor 100 en Perú (1), moneda PEN (1), acumulando
CALL pa_sp_reporte_facturas_pendientes(100, 1, 1);

select * from pa_reporte_propuestas_de_pago;
select * from pa_reportes_facturas_pendientes;

drop procedure pa_reporte_propuestas_de_pago;