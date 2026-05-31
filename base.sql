CREATE DATABASE IF NOT EXISTS ProyectoP;
USE ProyectoP;


CREATE TABLE IF NOT EXISTS Persona (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS Articulo (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio_base DECIMAL(10, 2) NOT NULL,
    iva DECIMAL(5, 2) NOT NULL
);


CREATE TABLE IF NOT EXISTS ProductoFisico (
    articulo_id INT PRIMARY KEY,
    stock INT NOT NULL,
    CONSTRAINT fk_art_producto FOREIGN KEY (articulo_id) 
        REFERENCES Articulo(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Servicio (
    articulo_id INT PRIMARY KEY,
    minutos INT NOT NULL,
    urgente BOOLEAN NOT NULL,
    CONSTRAINT fk_art_servicio FOREIGN KEY (articulo_id) 
        REFERENCES Articulo(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Cliente (
    persona_id INT PRIMARY KEY,
    fidelidad INT,
    email VARCHAR(150),
    CONSTRAINT fk_persona_cliente FOREIGN KEY (persona_id) 
        REFERENCES Persona(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Pedido (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    precio_total DECIMAL(10, 2),
    cliente_id INT NOT NULL,
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) 
        REFERENCES Cliente(persona_id)
);


CREATE TABLE IF NOT EXISTS LineasPedido (
    pedido_id INT,
    numero_linea INT,
    articulo_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY (pedido_id, numero_linea),
    CONSTRAINT fk_linea_pedido FOREIGN KEY (pedido_id) 
        REFERENCES Pedido(id) ON DELETE CASCADE,
    CONSTRAINT fk_linea_articulo FOREIGN KEY (articulo_id) 
        REFERENCES Articulo(id)
);


CREATE TABLE IF NOT EXISTS AlertaStock (
    articulo_id INT PRIMARY KEY,
    nombre_articulo VARCHAR(255),
    stock_actual INT,
    fecha_alerta DATETIME,
    CONSTRAINT fk_alerta_articulo FOREIGN KEY (articulo_id) 
        REFERENCES Articulo(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Empleado (
    persona_id INT PRIMARY KEY,
    
    dni VARCHAR(20) NOT NULL UNIQUE,
    nss VARCHAR(20),
    puesto VARCHAR(100),
    calle VARCHAR(150),
    numero VARCHAR(10),
    ciudad VARCHAR(100),
    provincia VARCHAR(100),
    cp VARCHAR(10),
    
    categoria INT,
    grupo INT,
    nivel INT,
    
    fecha_contrato DATE,
    antiguedad_anterior DATE,
    
    FOREIGN KEY (persona_id) REFERENCES Cliente(persona_id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Horas_Mensuales (
    persona_id INT,
    anio INT NOT NULL,
    mes INT NOT NULL,
    horas_trabajadas DECIMAL(5,2) DEFAULT 0.00,
    
    PRIMARY KEY (persona_id, anio, mes),
    FOREIGN KEY (persona_id) REFERENCES Empleado(persona_id) ON DELETE CASCADE
);




CREATE OR REPLACE VIEW v_cliente AS
SELECT 
    p.id AS cliente_id,
    p.nombre,
    p.apellidos,
    p.telefono,
    c.fidelidad,
    c.email
FROM Persona p
JOIN Cliente c ON p.id = c.persona_id;


CREATE OR REPLACE VIEW v_cliente_only AS
SELECT 
    p.id AS cliente_id,
    p.nombre,
    p.apellidos,
    p.telefono,
    c.fidelidad,
    c.email
FROM Persona p
JOIN Cliente c ON p.id = c.persona_id
LEFT JOIN Empleado e ON c.persona_id = e.persona_id
WHERE e.persona_id IS NULL;


CREATE OR REPLACE VIEW v_artifisico AS
SELECT 
    a.id AS articulo_id,
    a.nombre,
    a.precio_base,
    a.iva,
    f.stock
FROM Articulo a
JOIN ProductoFisico f ON a.id = f.articulo_id;


CREATE OR REPLACE VIEW v_artiservicio AS
SELECT 
    a.id AS articulo_id,
    a.nombre,
    a.precio_base,
    a.iva,
    s.minutos,
    s.urgente
FROM Articulo a
JOIN Servicio s ON a.id = s.articulo_id;


CREATE OR REPLACE VIEW v_pedidos AS
SELECT 
    lp.pedido_id,
    lp.numero_linea,
    p.fecha AS fecha_pedido,
    per.nombre AS nombre_cliente,
    per.apellidos AS apellidos_cliente,
    a.nombre AS nombre_articulo,
    lp.cantidad,
    lp.precio_unitario AS precio_unidad,
    (lp.cantidad * lp.precio_unitario) AS subtotal_linea
FROM LineasPedido lp
JOIN Pedido p ON lp.pedido_id = p.id
JOIN Cliente c ON p.cliente_id = c.persona_id
JOIN Persona per ON c.persona_id = per.id
JOIN Articulo a ON lp.articulo_id = a.id;


CREATE OR REPLACE VIEW v_empleado AS
SELECT 
    -- Datos personales (de la tabla Persona)
    p.id AS empleado_id,
    p.nombre,
    p.apellidos,
    p.telefono,
    
    -- Datos de contacto extra (de la tabla Cliente)
    c.email,
    c.fidelidad,
    -- Datos laborales y de ubicación (de la tabla Empleado)
    e.dni,
    e.nss,
    e.puesto,
    e.calle,
    e.numero,
    e.ciudad,
    e.provincia,
    e.cp,
    e.categoria,
    e.grupo,
    e.nivel,
    e.fecha_contrato,
    e.antiguedad_anterior
FROM Persona p
JOIN Cliente c ON p.id = c.persona_id
JOIN Empleado e ON c.persona_id = e.persona_id;



DELIMITER $$

CREATE TRIGGER t_stock_bajo
AFTER UPDATE ON ProductoFisico
FOR EACH ROW
BEGIN
    DECLARE var_nombre_articulo VARCHAR(255);

    IF NEW.stock < 3 THEN
        
        SELECT nombre INTO var_nombre_articulo 
        FROM Articulo 
        WHERE id = NEW.articulo_id;
        
        REPLACE INTO AlertaStock (articulo_id, nombre_articulo, stock_actual, fecha_alerta)
        VALUES (NEW.articulo_id, var_nombre_articulo, NEW.stock, NOW());
        
    END IF;
END$$

DELIMITER ;


DELIMITER $$

CREATE TRIGGER t_stock_repuesto
AFTER UPDATE ON ProductoFisico
FOR EACH ROW
BEGIN

    IF NEW.stock >= 3 AND OLD.stock < 3 THEN
        
        DELETE FROM AlertaStock 
        WHERE articulo_id = NEW.articulo_id;
        
    END IF;
END$$

DELIMITER ;