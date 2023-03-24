
-- Borrado de tablas 
/*
DROP TABLE marca;
DROP TABLE Talla;
DROP TABLE Categoria;
DROP TABLE Imagen;
DROP TABLE Coleccion;
DROP TABLE Producto;
DROP TABLE Producto_X_Coleccion; 
DROP TABLE Inventario;
DROP TABLE Usuario;
DROP TABLE Orden;
DROP TABLE Producto_X_Orden;
DROP TABLE Bitacora;
*/
-- creacion de tablas

CREATE TABLE Marca
(
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    logo_path VARCHAR(30) NOT NULL
);

CREATE TABLE Talla
(
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Categoria
(
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Imagen
(
    id_producto INT PRIMARY KEY NOT NULL,
    imagen_path VARCHAR(30) NOT NULL
);

CREATE TABLE Coleccion
(
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Producto
(
    id INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(30) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_marca INT NOT NULL,
    id_talla INT NOT NULL,
    id_categoria INT NOT NULL,
    id_imagen INT NOT NULL,
    id_coleccion INT NOT NULL,
    CONSTRAINT fk_id_marca FOREIGN KEY (id_marca) REFERENCES Marca(id),
    CONSTRAINT fk_id_talla FOREIGN KEY (id_talla) REFERENCES Talla(id),
    CONSTRAINT fk_id_categoria FOREIGN KEY (id_categoria) REFERENCES Categoria(id),
    CONSTRAINT fk_id_imagen FOREIGN KEY (id_imagen) REFERENCES Imagen(id_producto),
    CONSTRAINT fk_id_coleccion FOREIGN KEY (id_coleccion) REFERENCES Coleccion(id)
);

CREATE TABLE Producto_X_Coleccion
(
    id_producto INT NOT NULL,
    id_coleccion INT NOT NULL,
    PRIMARY KEY (id_producto, id_coleccion)
);

CREATE TABLE Inventario
(
    id INT PRIMARY KEY NOT NULL,
    cantidad INT NOT NULL,
    id_producto INT NOT NULL,
    CONSTRAINT fk_id_producto FOREIGN KEY (id_producto) REFERENCES Producto(id)
);

CREATE TABLE Usuario
(
    cedula INT PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    correo VARCHAR(20) NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    telefono INT NOT NULL,
    id_producto INT NOT NULL,
    is_admin NUMBER(1,0) NOT NULL
);

CREATE TABLE Orden
(
    id INT PRIMARY KEY NOT NULL,
    fecha DATE,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(cedula)
);

CREATE TABLE Producto_X_Orden
(
    id_producto INT NOT NULL,
    id_orden INT NOT NULL,
    PRIMARY KEY (id_producto, id_orden)
);

CREATE TABLE Bitacora
(
    id INT PRIMARY KEY NOT NULL,
    fecha DATE,
    descripcion VARCHAR(30) NOT NULL,
    id_usuario INT NOT NULL,
    id_producto INT NOT NULL,
    CONSTRAINT fk_id_usuario_bitacora FOREIGN KEY (id_usuario) REFERENCES Usuario(cedula),
    CONSTRAINT fk_id_producto_bitacora FOREIGN KEY (id_producto) REFERENCES Producto(id)
);

--PROCEDIMIENTOS Y FUNCIONES

--INICIAR SESION

CREATE OR REPLACE FUNCTION iniciar_sesion (nombre_usuario IN VARCHAR, contr IN VARCHAR)
RETURN INT
IS
    resultado INT; 
BEGIN 

    IF (nombre_usuario IS NOT NULL AND contr IS NOT NULL) THEN
       SELECT COUNT(*) INTO resultado FROM usuario WHERE nombre = nombre_usuario AND contrasena = contr;
    ELSE
       resultado := 0;
    END IF;
    
    RETURN resultado; 
END;

--SELECT iniciar_sesion('Aivy','123') FROM DUAL;

--PACKAGE CRUD

CREATE OR REPLACE PACKAGE CRUD IS
    --Marca
    FUNCTION agregar_marca(id INT, nombre_n VARCHAR, logo_path VARCHAR) RETURN INT;
    FUNCTION actualizar_marca(id_actualizar INT, nuevo_nombre VARCHAR, nuevo_logo VARCHAR) RETURN INT;
    
    --Talla
    FUNCTION agregar_talla(id_talla INT, nombre_talla VARCHAR) RETURN INT;
    FUNCTION actualizar_talla(id_actualizar_talla INT, nuevo_nombre_talla VARCHAR) RETURN INT;

     --Coleccion
    FUNCTION agregar_coleccion(id_coleccion INT, nombre_coleccion VARCHAR) RETURN INT;
    FUNCTION actualizar_coleccion(id_actualizar_coleccion INT, nuevo_nombre_coleccion VARCHAR) RETURN INT;

    --Categoria
    FUNCTION agregar_categoria(id_categoria INT, nombre_categoria VARCHAR) RETURN INT;
    FUNCTION actualizar_categoria(id_actualizar_categoria INT, nuevo_nombre_categoria VARCHAR) RETURN INT;
    
    --Imagen
    FUNCTION agregar_imagen(id_producto_imagen INT, path_imagen VARCHAR) RETURN INT;
    FUNCTION actualizar_imagen(id_actualizar_imagen INT, nuevo_path_imagen VARCHAR) RETURN INT;
    
    --Usuario
    FUNCTION agregar_usuario(cedula_agregar INT, nombre_agregar VARCHAR, apellido_agregar VARCHAR, correo_agregar VARCHAR, contrasena_agregar VARCHAR, telefono_agregar INT, is_admin_agregar NUMBER) RETURN INT;
    FUNCTION actualizar_usuario(cedula_act INT, nombre_act VARCHAR, apellido_act VARCHAR, correo_act VARCHAR, contrasena_act VARCHAR, telefono_act INT, is_admin_act NUMBER) RETURN INT;   
    
    --Producto
    FUNCTION agregar_producto(id_ag INT, nombre_ag VARCHAR, descripcion_ag VARCHAR, precio_ag DECIMAL, id_marca_ag INT, id_talla_ag INT, id_categoria_ag INT, id_imagen_ag INT, id_coleccion_ag INT) RETURN INT;
    FUNCTION actualizar_producto(id_act INT, nombre_act VARCHAR, descripcion_act VARCHAR, precio_act DECIMAL, id_marca_act INT, id_talla_act INT, id_categoria_act INT, id_imagen_act INT, id_coleccion_act INT) RETURN INT;
    
    --Orden
    FUNCTION agregar_orden(id_ag INT, fecha_ag DATE, id_usuario_ag INT) RETURN INT;
    FUNCTION actualizar_orden(id_act INT, fecha_act DATE, id_usuario_act INT) RETURN INT;
    
    --Bitacora
    FUNCTION agregar_bitacora(id_ag INT, fecha_ag DATE, descripcion_ag VARCHAR, id_usuario_ag INT, id_producto_ag INT) RETURN INT;
    FUNCTION actualizar_bitacora(id_act INT, fecha_act DATE, descripcion_act VARCHAR, id_usuario_act INT, id_producto_act INT) RETURN INT;
    
    --Inventario
    FUNCTION agregar_inventario(id_ag INT, cantidad_ag INT, id_producto_ag INT) RETURN INT;
    FUNCTION actualizar_inventario(id_act INT, cantidad_act INT, id_producto_act INT) RETURN INT;
    
END CRUD;

------------------BODY----------------------------------

CREATE OR REPLACE PACKAGE BODY CRUD IS
    --Marca
    FUNCTION agregar_marca(id INT, nombre_n VARCHAR, logo_path VARCHAR) RETURN INT
    AS
        id_n INT;
    BEGIN
        INSERT INTO Marca VALUES (id, nombre_n, logo_path);
        SELECT m.id INTO id_n FROM Marca m WHERE m.nombre = nombre_n;
        IF (id_n) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_marca;
    
    FUNCTION actualizar_marca(id_actualizar INT, nuevo_nombre VARCHAR, nuevo_logo VARCHAR) RETURN INT
    AS
        id_n INT;
    BEGIN
        UPDATE Marca m
        SET m.nombre = nuevo_nombre, m.logo_path = nuevo_logo
        WHERE m.id = id_actualizar;
        COMMIT;
        
        SELECT m.id INTO id_n FROM Marca m WHERE m.nombre = nuevo_nombre AND m.logo_path = nuevo_logo;
        
        IF (id_n) IS NOT NULL
        THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
        
    END actualizar_marca;
    
    --Talla
    FUNCTION agregar_talla(id_talla INT, nombre_talla VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        INSERT INTO Talla VALUES (id_talla, nombre_talla);
        SELECT id INTO id_nuevo FROM Talla WHERE nombre = nombre_talla;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_talla;
    
    FUNCTION actualizar_talla(id_actualizar_talla INT, nuevo_nombre_talla VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        UPDATE Talla 
        SET nombre = nuevo_nombre_talla
        WHERE id = id_actualizar_talla;
        COMMIT;
        
        SELECT id INTO id_nuevo FROM Talla WHERE nombre = nuevo_nombre_talla;
        
        IF (id_nuevo) IS NOT NULL
        THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
        
    END actualizar_talla;    
    
    --Colecion
    FUNCTION agregar_coleccion(id_coleccion INT, nombre_coleccion VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        INSERT INTO Coleccion VALUES (id_coleccion, nombre_coleccion);
        SELECT id INTO id_nuevo FROM Coleccion WHERE nombre = nombre_coleccion;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_coleccion;
    
    FUNCTION actualizar_coleccion(id_actualizar_coleccion INT, nuevo_nombre_coleccion VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        UPDATE Coleccion 
        SET nombre = nuevo_nombre_coleccion
        WHERE id = id_actualizar_coleccion;
        COMMIT;
        
        SELECT id INTO id_nuevo FROM Coleccion WHERE nombre = nuevo_nombre_coleccion;
        
        IF (id_nuevo) IS NOT NULL
        THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
        
    END actualizar_coleccion;    
    
    
    --Categoria
    FUNCTION agregar_categoria(id_categoria INT, nombre_categoria VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        INSERT INTO Categoria VALUES (id_categoria, nombre_categoria);
        SELECT id INTO id_nuevo FROM Categoria WHERE nombre = nombre_categoria;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_categoria;
    
    FUNCTION actualizar_categoria(id_actualizar_categoria INT, nuevo_nombre_categoria VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        UPDATE Categoria 
        SET nombre = nuevo_nombre_categoria
        WHERE id = id_actualizar_categoria;
        COMMIT;
        
        SELECT id INTO id_nuevo FROM Categoria WHERE nombre = nuevo_nombre_categoria;
        
        IF (id_nuevo) IS NOT NULL
        THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
        
    END actualizar_categoria;


    --Imagen
    FUNCTION agregar_imagen(id_producto_imagen INT, path_imagen VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        INSERT INTO Imagen VALUES (id_producto_imagen, path_imagen);
        SELECT id_producto INTO id_nuevo FROM Imagen WHERE imagen_path = path_imagen;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_imagen;
    
    FUNCTION actualizar_imagen(id_actualizar_imagen INT, nuevo_path_imagen VARCHAR) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        UPDATE Imagen 
        SET imagen_path = nuevo_path_imagen
        WHERE id_producto = id_actualizar_imagen;
        COMMIT;
        
        SELECT id_producto INTO id_nuevo FROM Imagen WHERE imagen_path = nuevo_path_imagen;
        
        IF (id_nuevo) IS NOT NULL
        THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
        
    END actualizar_imagen;


    --Usuario
    FUNCTION agregar_usuario(cedula_agregar INT, nombre_agregar VARCHAR, apellido_agregar VARCHAR, correo_agregar VARCHAR, contrasena_agregar VARCHAR, telefono_agregar INT, is_admin_agregar NUMBER) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        INSERT INTO Usuario VALUES (cedula_agregar,nombre_agregar,apellido_agregar,correo_agregar,contrasena_agregar,telefono_agregar,is_admin_agregar);
        SELECT cedula INTO id_nuevo FROM Usuario WHERE cedula = cedula_agregar;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_usuario;
    
    FUNCTION actualizar_usuario(cedula_act INT, nombre_act VARCHAR, apellido_act VARCHAR, correo_act VARCHAR, contrasena_act VARCHAR, telefono_act INT, is_admin_act NUMBER) RETURN INT
    AS
        id_nuevo INT;
    BEGIN
        UPDATE Usuario 
        SET nombre = nombre_act,
            apellido = apellido_act,
            correo = correo_act,
            contrasena = contrasena_act,
            telefono = telefono_act,
            is_admin = is_admin_act
        WHERE cedula = cedula_act;
        COMMIT;
        
        SELECT cedula INTO id_nuevo FROM Usuario WHERE cedula = cedula_act;
        
        IF (id_nuevo) IS NOT NULL
        THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
        
    END actualizar_usuario;
    
    --Producto
    FUNCTION agregar_producto(id_ag INT, nombre_ag VARCHAR, descripcion_ag VARCHAR, precio_ag DECIMAL, id_marca_ag INT, id_talla_ag INT, id_categoria_ag INT, id_imagen_ag INT, id_coleccion_ag INT) RETURN INT
    AS
        id_nuevo INT;
        id_marca INT;
        id_talla INT;
        id_categoria INT;
        id_imagen INT;
        id_coleccion INT;
    BEGIN
        
        SELECT id INTO id_marca FROM Marca WHERE id = id_marca_ag;
        SELECT id INTO id_talla FROM Talla WHERE id = id_talla_ag;
        SELECT id INTO id_categoria FROM Categoria WHERE id = id_categoria_ag;
        SELECT id_producto INTO id_imagen FROM Imagen WHERE id_producto = id_imagen_ag;
        SELECT id INTO id_coleccion FROM Coleccion WHERE id = id_coleccion_ag;
        
        IF (id_marca IS NOT NULL AND id_talla IS NOT NULL AND id_categoria IS NOT NULL AND id_imagen IS NOT NULL AND id_coleccion IS NOT NULL) THEN
            INSERT INTO Producto
            VALUES (id_ag, nombre_ag, descripcion_ag, precio_ag, id_marca_ag, id_talla_ag, id_categoria_ag, id_imagen_ag, id_coleccion_ag);
        ELSE
            RETURN 0;
        END IF;
        
        
        SELECT id INTO id_nuevo FROM Producto WHERE id = id_ag;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_producto;
    
    
    FUNCTION actualizar_producto(id_act INT, nombre_act VARCHAR, descripcion_act VARCHAR, precio_act DECIMAL, id_marca_act INT, id_talla_act INT, id_categoria_act INT, id_imagen_act INT, id_coleccion_act INT) RETURN INT
    AS
        id_nuevo INT;
        id_marca INT;
        id_talla INT;
        id_categoria INT;
        id_imagen INT;
        id_coleccion INT;
    BEGIN
        
        SELECT id INTO id_marca FROM Marca WHERE id = id_marca_act;
        SELECT id INTO id_talla FROM Talla WHERE id = id_talla_act;
        SELECT id INTO id_categoria FROM Categoria WHERE id = id_categoria_act;
        SELECT id_producto INTO id_imagen FROM Imagen WHERE id_producto = id_imagen_act;
        SELECT id INTO id_coleccion FROM Coleccion WHERE id = id_coleccion_act;
        
        IF (id_marca IS NOT NULL AND id_talla IS NOT NULL AND id_categoria IS NOT NULL AND id_imagen IS NOT NULL AND id_coleccion IS NOT NULL) THEN
            UPDATE Producto 
            SET nombre = nombre_act,
                descripcion = descripcion_act,
                precio = precio_act,
                id_marca = id_marca_act,
                id_talla = id_talla_act,
                id_categoria = id_categoria_act,
                id_imagen = id_imagen_act,
                id_coleccion = id_coleccion_act
            WHERE id = id_act;
            COMMIT;

        ELSE
            RETURN 0;
        END IF;
        
        
        SELECT id INTO id_nuevo FROM Producto WHERE id = id_act;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END actualizar_producto;
    
    --Orden
    FUNCTION agregar_orden(id_ag INT, fecha_ag DATE, id_usuario_ag INT) RETURN INT
    AS
        id_nuevo INT;
        id_usuario INT;
    BEGIN
        SELECT cedula INTO id_usuario FROM Usuario WHERE cedula = id_usuario_ag;
        
        IF (id_usuario IS NOT NULL) THEN
            INSERT INTO Orden VALUES (id_ag, fecha_ag, id_usuario_ag);
        ELSE
            RETURN 0;
        END IF;
        
        SELECT id INTO id_nuevo FROM Orden WHERE id = id_ag;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_orden;
    
    
    FUNCTION actualizar_orden(id_act INT, fecha_act DATE, id_usuario_act INT) RETURN INT
        AS
        id_nuevo INT;
        id_usuario_e INT;
    BEGIN
        SELECT cedula INTO id_usuario_e FROM Usuario WHERE cedula = id_usuario_act;
        
        IF (id_usuario_e IS NOT NULL) THEN
            UPDATE Orden
            SET fecha = fecha_act, id_usuario = id_usuario_act
            WHERE id = id_act;
        ELSE
            RETURN 0;
        END IF;
        
        SELECT id INTO id_nuevo FROM Orden WHERE id = id_act;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END actualizar_orden;
    
    --Bitacora
    FUNCTION agregar_bitacora(id_ag INT, fecha_ag DATE, descripcion_ag VARCHAR, id_usuario_ag INT, id_producto_ag INT) RETURN INT
    AS
        id_nuevo INT;
        id_usuario_e INT;
        id_producto_e INT;
    BEGIN
        SELECT cedula INTO id_usuario_e FROM Usuario WHERE cedula = id_usuario_ag;
        SELECT id INTO id_producto_e FROM Producto WHERE id = id_producto_ag;
        
        IF (id_usuario_e IS NOT NULL AND id_producto_e IS NOT NULL) THEN
            INSERT INTO Bitacora VALUES (id_ag, fecha_ag, descripcion_ag, id_usuario_ag, id_producto_ag);
        ELSE
            RETURN 0;
        END IF;
        
        SELECT id INTO id_nuevo FROM Bitacora WHERE id = id_ag;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_bitacora;
    

    FUNCTION actualizar_bitacora(id_act INT, fecha_act DATE, descripcion_act VARCHAR, id_usuario_act INT, id_producto_act INT) RETURN INT
        AS
        id_nuevo INT;
        id_usuario_e INT;
        id_producto_e INT;
    BEGIN
        SELECT cedula INTO id_usuario_e FROM Usuario WHERE cedula = id_usuario_act;
        SELECT id INTO id_producto_e FROM Producto WHERE id = id_producto_act;
        
        IF (id_usuario_e IS NOT NULL AND id_producto_e IS NOT NULL) THEN
            UPDATE Bitacora
            SET fecha = fecha_act, descripcion = descripcion_act, id_usuario = id_usuario_act, id_producto = id_producto_act
            WHERE id = id_act;
        ELSE
            RETURN 0;
        END IF;
        
        SELECT id INTO id_nuevo FROM Bitacora WHERE id = id_act;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END actualizar_bitacora;
    
    --Inventario
    FUNCTION agregar_inventario(id_ag INT, cantidad_ag INT, id_producto_ag INT) RETURN INT
    AS
        id_nuevo INT;
        id_producto_e INT;
    BEGIN
        SELECT id INTO id_producto_e FROM Producto WHERE id = id_producto_ag;
        
        IF (id_producto_e IS NOT NULL) THEN
            INSERT INTO Inventario VALUES (id_ag, cantidad_ag, id_producto_ag);
        ELSE
            RETURN 0;
        END IF;
        
        SELECT id INTO id_nuevo FROM Inventario WHERE id = id_ag;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END agregar_inventario;
    
    FUNCTION actualizar_inventario(id_act INT, cantidad_act INT, id_producto_act INT) RETURN INT
    AS
        id_nuevo INT;
        id_producto_e INT;
    BEGIN
        SELECT id INTO id_producto_e FROM Producto WHERE id = id_producto_act;
        
        IF (id_producto_e IS NOT NULL) THEN
            UPDATE Inventario
            SET cantidad = cantidad_act, id_producto = id_producto_act
            WHERE id = id_act;
        ELSE
            RETURN 0;
        END IF;
        
        SELECT id INTO id_nuevo FROM Inventario WHERE id = id_act;
        IF (id_nuevo) IS NOT NULL THEN
            RETURN 1;
        ELSE
            RETURN 0;
        END IF;
    END actualizar_inventario;
    
END CRUD;



-- ALTER TABLE Usuario DROP COLUMN id_producto;


SET SERVEROUTPUT ON;
declare
   result_n INT;
begin
   -- Call the function
   
   result_n := CRUD.actualizar_inventario(2,10,2);
   dbms_output.put_line(result_n);
end;

select * from Inventario;
select * from Producto;

--ALTER TABLE Usuario DROP COLUMN id_producto;

select current_date from dual;


