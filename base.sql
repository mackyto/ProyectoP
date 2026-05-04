CREATE DATABASE IF NOT EXISTS ProyectoP;
USE ProyectoP;

CREATE TABLE IF NOT EXISTS personas (
    id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS articulos (
    id INT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio_base DECIMAL(10, 2) NOT NULL,
    iva DECIMAL(5, 2) NOT NULL
);


CREATE TABLE IF NOT EXISTS productos_fisicos (
    articulo_id INT PRIMARY KEY,
    stock INT NOT NULL,
    CONSTRAINT fk_art_producto FOREIGN KEY (articulo_id) 
        REFERENCES articulos(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS servicios (
    articulo_id INT PRIMARY KEY,
    minutos INT NOT NULL,
    urgente BOOLEAN NOT NULL,
    CONSTRAINT fk_art_servicio FOREIGN KEY (articulo_id) 
        REFERENCES articulos(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS clientes (
    persona_id INT PRIMARY KEY,
    fidelidad INT,
    email VARCHAR(150),
    CONSTRAINT fk_persona_cliente FOREIGN KEY (persona_id) 
        REFERENCES personas(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS pedidos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    precio_total DECIMAL(10, 2),
    cliente_id INT NOT NULL,
    CONSTRAINT fk_pedido_cliente FOREIGN KEY (cliente_id) 
        REFERENCES clientes(persona_id)
);


CREATE TABLE IF NOT EXISTS lineas_pedido (
    pedido_id INT,
    numero_linea INT,
    articulo_id INT NOT NULL,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    
    PRIMARY KEY (pedido_id, numero_linea),
    CONSTRAINT fk_linea_pedido FOREIGN KEY (pedido_id) 
        REFERENCES pedidos(id) ON DELETE CASCADE,
    CONSTRAINT fk_linea_articulo FOREIGN KEY (articulo_id) 
        REFERENCES articulos(id)
);



CREATE OR REPLACE VIEW v_clientes AS
SELECT 
    p.id AS cliente_id,
    p.nombre,
    p.apellidos,
    p.telefono,
    c.fidelidad,
    c.email
FROM personas p
JOIN clientes c ON p.id = c.persona_id;

