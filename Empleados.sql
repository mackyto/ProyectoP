START TRANSACTION;

-- 1. Insertamos en Persona con los IDs específicos
INSERT INTO Persona (id, nombre, apellidos, telefono) VALUES
(10, 'Cain', 'Jefe Primus', '601888666'),
(1, 'Javier', 'Simarro Olivares', '686972866'),
(11, 'Other', 'Can Throw', '678453434'),
(12, 'Kathobodua', 'Gargaro', '123456789'),
(13, 'Sebas', 'El rey', '665656678');

-- 2. Insertamos en Cliente respetando los IDs de Persona
INSERT INTO Cliente (persona_id, fidelidad, email) VALUES
(10, 5, 'cain@mazcu.fit'),
(1, 5, 'it_max@mazcu.fit'),
(11, 5, 'ten@mazcu.fit'),
(12, 5, 'garo@mazcu.fit'),
(13, 5, 'Sebas@mazcu.fit');

-- 3. Insertamos en Empleado vinculando con los mismos IDs
INSERT INTO Empleado (persona_id, dni, nss, puesto, calle, numero, ciudad, provincia, cp, categoria, grupo, nivel, fecha_contrato, antiguedad_anterior) VALUES
(10, '12345678Z', '46/01234567/89', 'CEO', 'Del bosque', '25', 'Manises', 'Valencia', '46104', 5, 1, 1, '2025-09-01', NULL),
(1, '29160712R', '46/01234568/89', 'IT', 'Colon', '9', 'Puzol', 'Valencia', '46560', 3, 2, 1, '2025-09-01', NULL),
(11, '12345689J', '46/01234567/89', 'Auxiliar', 'Jarafuel', '25', 'Manises', 'Valencia', '46104', 5, 5, 1, '2025-09-01', NULL),
(12, '45789547U', '46/69855219/89', 'RRSS_y_Marqueting', 'Solidario', '14', 'Cataroja', 'Valencia', '46470', 4, 1, 1, '2025-09-01', NULL),
(13, '94623734W', '46/01234567/89', 'Nutricion y Entrenamiento', 'La Eliana', '6', 'Mislata', 'Valencia', '46920', 5, 2, 1, '2025-09-01', NULL);

COMMIT;
