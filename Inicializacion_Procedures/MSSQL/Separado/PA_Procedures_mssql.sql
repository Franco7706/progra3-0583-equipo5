CREATE OR ALTER PROCEDURE pa_sp_reporte_propuestas_de_pago
    @p_pais_id             INT = NULL,   -- filtro por país (del banco de la propuesta)
    @p_entidad_bancaria_id INT = NULL    -- filtro por banco de la propuesta
AS
BEGIN
    SET NOCOUNT ON;

    MERGE pa_reporte_propuestas_de_pago AS tgt
    USING (
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
            m.CODIGO_ISO                 AS CODIGO_MONEDA,
            a.RAZON_SOCIAL,
            ca.NUMERO_CUENTA             AS NUMERO_CUENTA_ACREEDOR,
            cp.NUMERO_CUENTA             AS NUMERO_CUENTA_PROPIA,
            eb.NOMBRE                    AS NOMBRE_ENTIDAD_BANCARIA,
            pa.NOMBRE                    AS NOMBRE_PAIS
        FROM PA_PROPUESTAS_DE_PAGO      AS p
        JOIN PA_DETALLES_PROPUESTA      AS dp ON dp.PROPUESTA_DE_PAGO_ID = p.PROPUESTA_DE_PAGO_ID
        JOIN PA_CUENTAS_ACREEDOR        AS ca ON ca.CUENTA_ACREEDOR_ID  = dp.CUENTA_ACREEDOR_ID
        JOIN PA_CUENTAS_PROPIAS         AS cp ON cp.CUENTA_PROPIA_ID    = dp.CUENTA_PROPIA_ID
        JOIN PA_ACREEDORES              AS a  ON a.ACREEDOR_ID          = ca.ACREEDOR_ID
        JOIN PA_ENTIDADES_BANCARIAS     AS eb ON eb.ENTIDAD_BANCARIA_ID = p.ENTIDAD_BANCARIA_ID
        JOIN PA_PAISES                  AS pa ON pa.PAIS_ID             = eb.PAIS_ID
        JOIN PA_MONEDAS                 AS m  ON m.MONEDA_ID            = ca.MONEDA_ID
        WHERE (@p_pais_id IS NULL OR eb.PAIS_ID = @p_pais_id)
          AND (@p_entidad_bancaria_id IS NULL OR p.ENTIDAD_BANCARIA_ID = @p_entidad_bancaria_id)
    ) AS src
    ON  tgt.PROPUESTA_DE_PAGO_ID = src.PROPUESTA_DE_PAGO_ID
    AND tgt.DETALLE_PROPUESTA_ID = src.DETALLE_PROPUESTA_ID
    WHEN MATCHED THEN
        UPDATE SET
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
            NOMBRE_PAIS             = src.NOMBRE_PAIS
    WHEN NOT MATCHED THEN
        INSERT (
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
        VALUES (
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
        );
END
GO

CREATE OR ALTER PROCEDURE pa_sp_reporte_facturas_pendientes
    @p_acreedor_id INT = NULL,  -- NULL = sin filtro
    @p_pais_id     INT = NULL,  -- NULL = sin filtro
    @p_moneda_id   INT = NULL   -- NULL = sin filtro
AS
BEGIN
    SET NOCOUNT ON;

    MERGE pa_reporte_facturas_pendientes AS tgt
    USING (
        SELECT
            f.FACTURA_ID,
            a.ACREEDOR_ID,
            pa.PAIS_ID,
            f.MONEDA_ID,
            f.NUMERO_FACTURA,
            a.RAZON_SOCIAL,
            pa.NOMBRE                                              AS NOMBRE_PAIS,
            m.CODIGO_ISO                                           AS CODIGO_MONEDA,
            f.FECHA_LIMITE_PAGO,
            DATEDIFF(DAY, GETDATE(), f.FECHA_LIMITE_PAGO)          AS DIAS_VENCIMIENTO,   -- días restantes
            CASE
              WHEN DATEDIFF(DAY, GETDATE(), f.FECHA_LIMITE_PAGO) BETWEEN 0 AND 30  THEN '0-30'
              WHEN DATEDIFF(DAY, GETDATE(), f.FECHA_LIMITE_PAGO) BETWEEN 31 AND 60 THEN '31-60'
              WHEN DATEDIFF(DAY, GETDATE(), f.FECHA_LIMITE_PAGO) BETWEEN 61 AND 90 THEN '61-90'
              ELSE '+90'
            END                                                    AS RANGO_VENCIMIENTO,
            f.MONTO_RESTANTE
        FROM PA_FACTURAS f
        JOIN PA_ACREEDORES a ON a.ACREEDOR_ID = f.ACREEDOR_ID
        JOIN PA_MONEDAS   m ON m.MONEDA_ID    = f.MONEDA_ID
        JOIN PA_PAISES   pa ON pa.PAIS_ID     = a.PAIS_ID
        WHERE f.ESTADO = 'PENDIENTE'
          AND DATEDIFF(DAY, GETDATE(), f.FECHA_LIMITE_PAGO) >= 0   -- ✅ solo por vencer
          AND (@p_acreedor_id IS NULL OR a.ACREEDOR_ID = @p_acreedor_id)
          AND (@p_pais_id     IS NULL OR pa.PAIS_ID    = @p_pais_id)
          AND (@p_moneda_id   IS NULL OR f.MONEDA_ID   = @p_moneda_id)
    ) AS src
    ON  tgt.FACTURA_ID = src.FACTURA_ID
    WHEN MATCHED THEN
        UPDATE SET
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
            MONTO_RESTANTE     = src.MONTO_RESTANTE
    WHEN NOT MATCHED THEN
        INSERT (
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
        VALUES (
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
        );
END
GO