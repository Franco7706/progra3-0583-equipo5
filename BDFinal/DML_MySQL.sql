-- =============================================
-- DML para Sistema de Pago a Acreedores
-- Motor: MySQL
-- Países: Perú, México, Colombia
-- =============================================

USE softpacbd;

-- =============================================
-- 1. INSERCIÓN DE USUARIOS
-- =============================================
INSERT INTO PA_USUARIOS (USUARIO_ID, CORREO_ELECTRONICO, NOMBRE_DE_USUARIO, NOMBRE, APELLIDOS, ACTIVO, PASSWORD_HASH, SUPERUSUARIO) VALUES
(1, 'admin@softpac.com', 'admin', 'Administrador', 'Sistema', 'S', 'hash_admin_123', 'S'),
(2, 'jperez@softpac.com', 'jperez', 'Juan', 'Pérez García', 'S', 'hash_jperez_456', 'N'),
(3, 'mgomez@softpac.com', 'mgomez', 'María', 'Gómez López', 'S', 'hash_mgomez_789', 'N'),
(4, 'crodriguez@softpac.com', 'crodriguez', 'Carlos', 'Rodríguez Silva', 'S', 'hash_crodriguez_012', 'N'),
(5, 'lmartinez@softpac.com', 'lmartinez', 'Laura', 'Martínez Torres', 'S', 'hash_lmartinez_345', 'N'),
(6, 'asanchez@softpac.com', 'asanchez', 'Ana', 'Sánchez Vega', 'S', 'hash_asanchez_678', 'N');

-- =============================================
-- 2. INSERCIÓN DE PAÍSES (Solo Perú, México, Colombia)
-- =============================================
INSERT INTO PA_PAISES (NOMBRE, CODIGO_ISO, CODIGO_TELEFONICO) VALUES
('Perú', 'PE', '+51'),
('México', 'MX', '+52'),
('Colombia', 'CO', '+57');

-- =============================================
-- 3. INSERCIÓN DE ACCESO DE USUARIOS A PAÍSES
-- =============================================
INSERT INTO PA_USUARIO_PAIS_ACCESO (USUARIO_ID, PAIS_ID, ACCESO) VALUES
-- Admin tiene acceso a todos los países
(1, 1, 'S'), (1, 2, 'S'), (1, 3, 'S'),
-- Juan Pérez - Perú y Colombia
(2, 1, 'S'), (2, 3, 'S'),
-- María Gómez - Perú y México
(3, 1, 'S'), (3, 2, 'S'),
-- Carlos Rodríguez - México y Colombia
(4, 2, 'S'), (4, 3, 'S'),
-- Laura Martínez - Solo Perú
(5, 1, 'S'),
-- Ana Sánchez - Colombia y México
(6, 2, 'S'), (6, 3, 'S');

-- =============================================
-- 4. INSERCIÓN DE MONEDAS (PEN, MXN, COP, USD, EUR)
-- =============================================
INSERT INTO PA_MONEDAS (NOMBRE, CODIGO_ISO, SIMBOLO) VALUES
('Sol Peruano', 'PEN', 'S/'),
('Peso Mexicano', 'MXN', '$'),
('Peso Colombiano', 'COP', '$'),
('Dólar Estadounidense', 'USD', '$'),
('Euro', 'EUR', '€');

-- =============================================
-- 5. INSERCIÓN DE ENTIDADES BANCARIAS
-- =============================================
INSERT INTO PA_ENTIDADES_BANCARIAS (NOMBRE, FORMATO_ACEPTADO, CODIGO_SWIFT, PAIS_ID) VALUES
-- Perú
('Banco de Crédito del Perú', 'TXT', 'BCPLPEPL', 1),
('Interbank', 'CSV', 'BINPPEPL', 1),
('BBVA Perú', 'XLS', 'BCONPEPL', 1),
('Scotiabank Perú', 'TXT', 'BSUDPEPL', 1),
('Banco Pichincha', 'CSV', 'PINPPEPL', 1),
-- México
('BBVA México', 'XLS', 'BCMRMXMM', 2),
('Banco Santander México', 'CSV', 'BMSXMXMM', 2),
('Banorte', 'TXT', 'MENOMXMT', 2),
('HSBC México', 'CSV', 'BIMEMXMM', 2),
('Scotiabank México', 'TXT', 'MBCOMXMM', 2),
-- Colombia
('Bancolombia', 'CSV', 'COLOCOBM', 3),
('Banco de Bogotá', 'XLS', 'BBOPCOBM', 3),
('Davivienda', 'TXT', 'CAFECOBM', 3),
('BBVA Colombia', 'CSV', 'BBVACOBM', 3),
('Banco de Occidente', 'TXT', 'OCCICOBM', 3);

-- =============================================
-- 6. INSERCIÓN DE ACREEDORES
-- =============================================
INSERT INTO PA_ACREEDORES (RAZON_SOCIAL, RUC, DIRECCION_FISCAL, CONDICION, PLAZO_DE_PAGO, ACTIVO, PAIS_ID) VALUES
-- Perú
('Corporación Textil SAC', '20123456789', 'Av. Argentina 1234, Lima', 'Habido', 30, 'S', 1),
('Inversiones Mineras del Sur SA', '20234567890', 'Calle Los Pinos 567, Arequipa', 'Habido', 45, 'S', 1),
('Servicios Logísticos Unidos EIRL', '20345678901', 'Jr. Cusco 890, Lima', 'Habido', 30, 'S', 1),
('Tecnología y Sistemas SAC', '20456789012', 'Av. Javier Prado 2345, Lima', 'Habido', 15, 'S', 1),
('Distribuidora Industrial del Norte SAC', '20567890123', 'Av. Industrial 456, Trujillo', 'Habido', 30, 'S', 1),
('Comercializadora Peruana SA', '20678901234', 'Calle Comercio 789, Lima', 'Habido', 60, 'S', 1),
-- México
('Distribuidora Nacional SA de CV', 'DNM120101AB', 'Av. Reforma 1234, Ciudad de México', 'Activo', 30, 'S', 2),
('Industrias del Norte SA de CV', 'IDN130202DE', 'Blvd. Insurgentes 567, Monterrey', 'Activo', 45, 'S', 2),
('Servicios Empresariales MX SA de CV', 'SEM140303GH', 'Av. Universidad 890, Guadalajara', 'Activo', 30, 'S', 2),
('Tecnología Avanzada SA de CV', 'TAV150404JK', 'Calle Reforma 234, Querétaro', 'Activo', 15, 'S', 2),
('Comercializadora del Bajío SA de CV', 'CDB160505MN', 'Av. Central 567, León', 'Activo', 60, 'S', 2),
-- Colombia
('Comercializadora Andina SAS', '900123456-1', 'Carrera 7 No. 123-45, Bogotá', 'Activo', 30, 'S', 3),
('Productos Industriales Colombia SA', '900234567-2', 'Calle 100 No. 20-30, Medellín', 'Activo', 60, 'S', 3),
('Distribuidora Cafetera SAS', '900345678-3', 'Av. El Poblado 456, Medellín', 'Activo', 30, 'S', 3),
('Servicios Logísticos del Caribe SAS', '900456789-4', 'Calle 72 No. 10-20, Barranquilla', 'Activo', 45, 'S', 3),
('Tecnología Digital Colombia SAS', '900567890-5', 'Carrera 15 No. 93-40, Bogotá', 'Activo', 15, 'S', 3);

-- =============================================
-- 7. INSERCIÓN DE CUENTAS DE ACREEDORES
-- =============================================
INSERT INTO PA_CUENTAS_ACREEDOR (TIPO_CUENTA, NUMERO_CUENTA, CCI, ACTIVO, ACREEDOR_ID, ENTIDAD_BANCARIA_ID, MONEDA_ID) VALUES
-- Perú - Corporación Textil SAC
('Cuenta Corriente', '19100123456789', '00219110012345678901', 'S', 1, 1, 1),
('Cuenta Corriente', '19100123456790', '00219110012345679002', 'S', 1, 1, 4),
-- Perú - Inversiones Mineras del Sur SA
('Cuenta Corriente', '20020234567890', '00320220234567890123', 'S', 2, 2, 1),
('Cuenta Corriente', '20020234567891', '00320220234567891234', 'S', 2, 2, 4),
-- Perú - Servicios Logísticos Unidos EIRL
('Cuenta Corriente', '01130345678901', '00101130345678901234', 'S', 3, 3, 1),
-- Perú - Tecnología y Sistemas SAC
('Cuenta Corriente', '00940456789012', '00200940456789012345', 'S', 4, 4, 1),
('Cuenta Corriente', '00940456789013', '00200940456789013456', 'S', 4, 4, 4),
-- Perú - Distribuidora Industrial del Norte SAC
('Cuenta Corriente', '80150567890123', '00280150567890123456', 'S', 5, 5, 1),
-- Perú - Comercializadora Peruana SA
('Cuenta Corriente', '19100678901234', '00219110678901234567', 'S', 6, 1, 1),
-- México - Distribuidora Nacional SA de CV
('Cuenta Corriente', '0123456789', '012180001234567890', 'S', 7, 6, 2),
('Cuenta Corriente', '0123456790', '012180001234567891', 'S', 7, 6, 4),
-- México - Industrias del Norte SA de CV
('Cuenta Corriente', '0234567890', '014320002345678901', 'S', 8, 7, 2),
-- México - Servicios Empresariales MX SA de CV
('Cuenta Corriente', '0345678901', '072580003456789012', 'S', 9, 8, 2),
('Cuenta Corriente', '0345678902', '072580003456789013', 'S', 9, 8, 4),
-- México - Tecnología Avanzada SA de CV
('Cuenta Corriente', '0456789012', '021180004567890123', 'S', 10, 9, 2),
-- México - Comercializadora del Bajío SA de CV
('Cuenta Corriente', '0567890123', '044320005678901234', 'S', 11, 10, 2),
-- Colombia - Comercializadora Andina SAS
('Cuenta Corriente', '12345678901', '00700123456789012', 'S', 12, 11, 3),
('Cuenta Corriente', '12345678902', '00700123456789013', 'S', 12, 11, 4),
-- Colombia - Productos Industriales Colombia SA
('Cuenta Corriente', '23456789012', '00100234567890123', 'S', 13, 12, 3),
-- Colombia - Distribuidora Cafetera SAS
('Cuenta Corriente', '34567890123', '05100345678901234', 'S', 14, 13, 3),
('Cuenta Corriente', '34567890124', '05100345678901235', 'S', 14, 13, 4),
-- Colombia - Servicios Logísticos del Caribe SAS
('Cuenta Corriente', '45678901234', '01300456789012345', 'S', 15, 14, 3),
-- Colombia - Tecnología Digital Colombia SAS
('Cuenta Corriente', '56789012345', '00700567890123456', 'S', 16, 15, 3);

-- =============================================
-- 8. INSERCIÓN DE CUENTAS PROPIAS
-- =============================================
INSERT INTO PA_CUENTAS_PROPIAS (SALDO_DISPONIBLE, TIPO_CUENTA, NUMERO_CUENTA, CCI, ACTIVO, ENTIDAD_BANCARIA_ID, MONEDA_ID) VALUES
-- Perú
(500000.00, 'Cuenta Corriente', '19199900001111', '00219119990000111122', 'S', 1, 1),
(250000.00, 'Cuenta Corriente', '19199900001112', '00219119990000111223', 'S', 1, 4),
(350000.00, 'Cuenta Corriente', '20099900002222', '00320299990000222233', 'S', 2, 1),
(180000.00, 'Cuenta Corriente', '20099900002223', '00320299990000222334', 'S', 2, 4),
(420000.00, 'Cuenta Corriente', '01199900003333', '00101199990000333344', 'S', 3, 1),
-- México
(800000.00, 'Cuenta Corriente', '0999000011', '012180009990000111', 'S', 6, 2),
(400000.00, 'Cuenta Corriente', '0999000012', '012180009990000112', 'S', 6, 4),
(550000.00, 'Cuenta Corriente', '0999000022', '014320009990000222', 'S', 7, 2),
(320000.00, 'Cuenta Corriente', '0999000023', '014320009990000223', 'S', 7, 4),
(650000.00, 'Cuenta Corriente', '0999000033', '072580009990000333', 'S', 8, 2),
-- Colombia
(1200000.00, 'Cuenta Corriente', '99900001111', '00709999000011112', 'S', 11, 3),
(600000.00, 'Cuenta Corriente', '99900001112', '00709999000011113', 'S', 11, 4),
(850000.00, 'Cuenta Corriente', '99900002222', '00109999000022223', 'S', 12, 3),
(480000.00, 'Cuenta Corriente', '99900002223', '00109999000022224', 'S', 12, 4),
(720000.00, 'Cuenta Corriente', '99900003333', '05109999000033334', 'S', 13, 3);

-- =============================================
-- 9. INSERCIÓN DE FACTURAS
-- =============================================
INSERT INTO PA_FACTURAS (NUMERO_FACTURA, FECHA_EMISION, FECHA_RECEPCION, FECHA_LIMITE_PAGO, ESTADO, MONTO_TOTAL, MONTO_IGV, MONTO_RESTANTE, REGIMEN_FISCAL, TASA_IVA, OTROS_TRIBUTOS, MONEDA_ID, ACREEDOR_ID) VALUES
-- Perú - Facturas en PEN
('F001-00001234', '2024-09-15 10:30:00', '2024-09-16 14:20:00', '2024-10-15 23:59:59', 'Pendiente', 11800.00, 1800.00, 11800.00, 'Régimen General', 18.00, 0.00, 1, 1),
('F001-00001235', '2024-09-20 11:15:00', '2024-09-21 09:45:00', '2024-10-20 23:59:59', 'Pendiente', 23600.00, 3600.00, 23600.00, 'Régimen General', 18.00, 0.00, 1, 2),
('F001-00001236', '2024-10-01 14:00:00', '2024-10-02 10:30:00', '2024-10-31 23:59:59', 'Pendiente', 8260.00, 1260.00, 8260.00, 'Régimen General', 18.00, 0.00, 1, 3),
('F001-00001237', '2024-10-05 09:20:00', '2024-10-06 15:10:00', '2024-10-20 23:59:59', 'Pendiente', 17700.00, 2700.00, 17700.00, 'Régimen General', 18.00, 0.00, 1, 4),
-- Perú - Facturas en USD
('F001-00001238', '2024-09-18 13:45:00', '2024-09-19 11:20:00', '2024-10-18 23:59:59', 'Pendiente', 8850.00, 1350.00, 8850.00, 'Régimen General', 18.00, 0.00, 4, 1),
('F001-00001239', '2024-09-25 10:00:00', '2024-09-26 08:30:00', '2024-11-09 23:59:59', 'Pendiente', 14160.00, 2160.00, 14160.00, 'Régimen General', 18.00, 0.00, 4, 2),
-- México - Facturas en MXN
('A-00002001', '2024-09-12 12:30:00', '2024-09-13 16:45:00', '2024-10-12 23:59:59', 'Pendiente', 46400.00, 7400.00, 46400.00, 'Régimen General', 16.00, 0.00, 2, 7),
('A-00002002', '2024-09-22 15:20:00', '2024-09-23 10:15:00', '2024-10-22 23:59:59', 'Pendiente', 69600.00, 11100.00, 69600.00, 'Régimen General', 16.00, 0.00, 2, 8),
('A-00002003', '2024-10-03 11:00:00', '2024-10-04 14:30:00', '2024-11-02 23:59:59', 'Pendiente', 34800.00, 5550.00, 34800.00, 'Régimen General', 16.00, 0.00, 2, 9),
-- México - Facturas en USD
('A-00002004', '2024-09-28 09:45:00', '2024-09-29 13:20:00', '2024-10-28 23:59:59', 'Pendiente', 11600.00, 1850.00, 11600.00, 'Régimen General', 16.00, 0.00, 4, 7),
-- Colombia - Facturas en COP
('FV-000030001', '2024-09-10 10:15:00', '2024-09-11 15:30:00', '2024-10-10 23:59:59', 'Pendiente', 11400000.00, 1800000.00, 11400000.00, 'Régimen Común', 19.00, 0.00, 3, 12),
('FV-000030002', '2024-09-17 14:20:00', '2024-09-18 09:40:00', '2024-11-16 23:59:59', 'Pendiente', 28500000.00, 4500000.00, 28500000.00, 'Régimen Común', 19.00, 0.00, 3, 13),
('FV-000030003', '2024-10-02 11:30:00', '2024-10-03 16:15:00', '2024-11-01 23:59:59', 'Pendiente', 17100000.00, 2700000.00, 17100000.00, 'Régimen Común', 19.00, 0.00, 3, 14),
-- Colombia - Facturas en USD
('FV-000030004', '2024-09-24 13:00:00', '2024-09-25 10:45:00', '2024-10-24 23:59:59', 'Pendiente', 9500.00, 1500.00, 9500.00, 'Régimen Común', 19.00, 0.00, 4, 12),
('FV-000030005', '2024-10-08 15:45:00', '2024-10-09 11:20:00', '2024-10-23 23:59:59', 'Pendiente', 7600.00, 1200.00, 7600.00, 'Régimen Común', 19.00, 0.00, 4, 15);

-- =============================================
-- 10. INSERCIÓN DE DETALLES DE FACTURAS
-- =============================================
INSERT INTO PA_DETALLES_FACTURA (SUBTOTAL, DESCRIPCION, FACTURA_ID) VALUES
-- Detalles F001-00001234
(6000.00, 'Telas de algodón importado - 500 metros', 1),
(4000.00, 'Hilos de poliéster - 200 conos', 1),
-- Detalles F001-00001235
(12000.00, 'Equipos de perforación minera', 2),
(8000.00, 'Explosivos controlados para minería', 2),
-- Detalles F001-00001236
(4500.00, 'Servicio de transporte terrestre - 10 rutas', 3),
(2500.00, 'Almacenamiento temporal de mercancía', 3),
-- Detalles F001-00001237
(10000.00, 'Licencias de software empresarial - 20 usuarios', 4),
(5000.00, 'Mantenimiento y soporte técnico anual', 4),
-- Detalles F001-00001238
(4500.00, 'Telas sintéticas importadas - 300 metros', 5),
(3000.00, 'Accesorios textiles diversos', 5),
-- Detalles F001-00001239
(8000.00, 'Maquinaria de construcción minera', 6),
(4000.00, 'Repuestos y accesorios', 6),
-- Detalles A-00002001
(25000.00, 'Productos electrónicos - Lote A', 7),
(14000.00, 'Accesorios tecnológicos - Lote B', 7),
-- Detalles A-00002002
(35000.00, 'Maquinaria industrial pesada', 8),
(23500.00, 'Sistema de automatización', 8),
-- Detalles A-00002003
(18000.00, 'Servicios de consultoría empresarial - 3 meses', 9),
(11250.00, 'Capacitación de personal', 9),
-- Detalles A-00002004
(6000.00, 'Material de oficina importado', 10),
(3750.00, 'Mobiliario ejecutivo', 10),
-- Detalles FV-000030001
(6000000.00, 'Café especial de exportación - 5000 kg', 11),
(3600000.00, 'Embalaje y certificaciones de calidad', 11),
-- Detalles FV-000030002
(15000000.00, 'Equipamiento industrial completo', 12),
(9000000.00, 'Instalación y puesta en marcha', 12),
-- Detalles FV-000030003
(9000000.00, 'Productos agrícolas procesados', 13),
(5400000.00, 'Transporte especializado refrigerado', 13),
-- Detalles FV-000030004
(5000.00, 'Software de gestión empresarial', 14),
(3000.00, 'Implementación y capacitación', 14),
-- Detalles FV-000030005
(4000.00, 'Servicios de desarrollo web', 15),
(2400.00, 'Mantenimiento y hosting anual', 15);

-- =============================================
-- 11. INSERCIÓN DE PROPUESTAS DE PAGO
-- =============================================
INSERT INTO PA_PROPUESTAS_DE_PAGO (FECHA_HORA_CREACION, ESTADO, ENTIDAD_BANCARIA_ID, FECHA_HORA_MODIFICACION, USUARIO_MODIFICACION, USUARIO_CREACION) VALUES
-- Propuesta 1 - Perú - BCP
('2024-10-10 09:00:00', 'Pendiente', 1, '2024-10-10 10:30:00', 1, 2),
-- Propuesta 2 - Perú - Interbank
('2024-10-12 10:15:00', 'Pendiente', 2, '2024-10-12 10:15:00', 3, 3),
-- Propuesta 3 - México - BBVA México
('2024-10-14 11:30:00', 'Pendiente', 6, '2024-10-14 14:20:00', 1, 4),
-- Propuesta 4 - Colombia - Bancolombia
('2024-10-15 13:45:00', 'Pendiente', 11, '2024-10-15 13:45:00', 6, 6),
-- Propuesta 5 - Perú - BBVA Perú
('2024-10-18 08:30:00', 'Anulada', 3, '2024-10-18 09:45:00', 1, 2);

-- =============================================
-- 12. INSERCIÓN DE DETALLES DE PROPUESTAS
-- =============================================
INSERT INTO PA_DETALLES_PROPUESTA (MONTO_PAGO, FORMA_PAGO, PROPUESTA_DE_PAGO_ID, FACTURA_ID, CUENTA_ACREEDOR_ID, CUENTA_PROPIA_ID) VALUES
-- Propuesta 1 - Pago de facturas peruanas
(11800.00, 'T', 1, 1, 1, 1),  -- F001-00001234 en PEN
(8850.00, 'T', 1, 5, 2, 2),   -- F001-00001238 en USD
-- Propuesta 2 - Pago parcial de factura peruana
(15000.00, 'T', 2, 2, 3, 3),  -- F001-00001235 pago parcial en PEN
(8260.00, 'T', 2, 3, 5, 5),   -- F001-00001236 en PEN
-- Propuesta 3 - Pago de facturas mexicanas
(46400.00, 'T', 3, 7, 9, 6),  -- A-00002001 en MXN
(11600.00, 'T', 3, 10, 10, 7), -- A-00002004 en USD
-- Propuesta 4 - Pago de facturas colombianas
(11400000.00, 'T', 4, 11, 17, 11), -- FV-000030001 en COP
(9500.00, 'T', 4, 14, 18, 12),     -- FV-000030004 en USD
-- Propuesta 5 - Pago de facturas peruanas
(17700.00, 'T', 5, 4, 6, 1),  -- F001-00001237 en PEN
(14160.00, 'T', 5, 6, 4, 2);  -- F001-00001239 en USD

-- =============================================
-- 13. INSERCIÓN DE SUPERUSIARIO 
-- =============================================
INSERT INTO PA_USUARIOS
    (CORREO_ELECTRONICO, NOMBRE_DE_USUARIO, 
     NOMBRE, APELLIDOS, ACTIVO, PASSWORD_HASH, SUPERUSUARIO,
     FECHA_ELIMINACION, USUARIO_ELIMINACION)
VALUES
    ('diego@hotmail.com', 'DiegoA',
     'Diego', 'Ayala de la Cruz', 'S',
     '$2a$10$Y2Rbizz7ddp.Fd0RP7pmN.aktufJnff/iMFs2AdAXD9tfc3bdAZgC',
     'S', NULL, NULL);

