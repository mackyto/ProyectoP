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