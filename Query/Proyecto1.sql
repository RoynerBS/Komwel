-- Borrado de tablas 
/*
DROP TABLE Bitacora;
DROP TABLE Detalle_Orden;
DROP TABLE Orden;
DROP TABLE Usuario;
DROP TABLE Inventario;
DROP TABLE Producto;
DROP TABLE Coleccion;
DROP TABLE Categoria;
DROP TABLE Talla;
DROP TABLE Marca;




 




*/
-- creacion de tablas

CREATE TABLE Marca
(
    id NUMBER PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Talla
(
    id NUMBER PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Categoria
(
    id NUMBER PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);


CREATE TABLE Coleccion
(
    id NUMBER PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL
);

CREATE TABLE Producto
(
    id NUMBER PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    id_marca NUMBER NOT NULL,
    id_talla NUMBER NOT NULL,
    id_categoria NUMBER NOT NULL,
    id_coleccion NUMBER NOT NULL,
    CONSTRAINT fk_id_marca FOREIGN KEY (id_marca) REFERENCES Marca(id),
    CONSTRAINT fk_id_talla FOREIGN KEY (id_talla) REFERENCES Talla(id),
    CONSTRAINT fk_id_categoria FOREIGN KEY (id_categoria) REFERENCES Categoria(id),
    CONSTRAINT fk_id_coleccion FOREIGN KEY (id_coleccion) REFERENCES Coleccion(id)
);

CREATE TABLE Inventario
(
    id NUMBER PRIMARY KEY NOT NULL,
    cantidad INT NOT NULL,
    id_producto NUMBER NOT NULL,
    CONSTRAINT fk_id_producto FOREIGN KEY (id_producto) REFERENCES Producto(id)
);

CREATE TABLE Usuario
(
    id NUMBER PRIMARY KEY NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    apellido VARCHAR(20) NOT NULL,
    correo VARCHAR(40) NOT NULL,
    contrasena VARCHAR(20) NOT NULL,
    is_admin NUMBER(1,0) NOT NULL
);

CREATE TABLE Orden
(
    id NUMBER PRIMARY KEY NOT NULL,
    fecha DATE,
    id_usuario INT NOT NULL,
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

CREATE TABLE Detalle_Orden
(
    id_producto NUMBER NOT NULL,
    id_orden NUMBER NOT NULL,
    PRIMARY KEY (id_producto, id_orden),
    CONSTRAINT fk_id_producto FOREIGN KEY (id_producto) REFERENCES producto(id),
    CONSTRAINT fk_id_orden FOREIGN KEY (id_orden) REFERENCES orden(id)
);


CREATE TABLE Bitacora
(
    id NUMBER PRIMARY KEY NOT NULL,
    fecha DATE,
    id_usuario NUMBER NOT NULL,
    sentencia VARCHAR(200) NOT NULL,
    CONSTRAINT fk_id_usuario_bitacora FOREIGN KEY (id_usuario) REFERENCES Usuario(id)
);

-- SECUENCIAS 
CREATE SEQUENCE sec_Bitacora
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Categoria
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Producto
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Orden
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Usuario
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Inventario
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Coleccion
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Talla
start with 1
increment by 1
maxvalue 99999
minvalue 1;

CREATE SEQUENCE sec_Marca
start with 1
increment by 1
maxvalue 99999
minvalue 1;


CREATE OR REPLACE PACKAGE CONSULTA IS

FUNCTION iniciar_sesion (i_email IN VARCHAR, i_contrasena IN VARCHAR) RETURN INT;
FUNCTION verificar_correo (i_email IN VARCHAR) RETURN INT;
END CONSULTA; 

CREATE OR REPLACE PACKAGE BODY CONSULTA IS
--INICIAR SESION

FUNCTION iniciar_sesion (i_email IN VARCHAR, i_contrasena IN VARCHAR)
RETURN INT
IS
    resultado INT; 
BEGIN 

    IF (i_email IS NOT NULL AND i_contrasena IS NOT NULL) THEN
       SELECT COUNT(*) INTO resultado FROM usuario WHERE email = i_email AND contrasena = i_contrasena;
    ELSE
       resultado := 0;
    END IF;
    
    RETURN resultado; 
END iniciar_sesion;

--SELECT iniciar_sesion('Aivy','123') FROM DUAL;

-- VERIFICAR CORREO
FUNCTION verificar_correo (i_email IN VARCHAR)
RETURN INT
IS
    resultado INT; 
BEGIN 

    IF (i_email IS NOT NULL) THEN
       SELECT COUNT(*) INTO resultado FROM usuario WHERE email = i_email;
    ELSE
       resultado := 0;
    END IF;
    
    RETURN resultado; 
END verificar_correo;

end CONSULTA;




CREATE OR REPLACE PACKAGE CRUD IS
    --Marca
    
    --Producto
    FUNCTION verificar_producto(nombre_n VARCHAR) RETURN int;
    PROCEDURE agregar_producto(nombre_ag IN VARCHAR, descripcion_ag IN VARCHAR, precio_ag IN DECIMAL,  id_categoria_ag IN NUMBER,id_coleccion_ag IN NUMBER, id_marca_ag IN NUMBER, id_talla_ag IN NUMBER);
    PROCEDURE actualizar_producto(id_act IN NUMBER, nombre_act IN VARCHAR, descripcion_act IN VARCHAR, precio_act IN DECIMAL, id_categoria_act IN NUMBER, id_coleccion_act IN NUMBER, id_marca_act IN NUMBER, id_talla_act IN NUMBER); 
    PROCEDURE borrar_producto(id_borrar_producto IN NUMBER);
    PROCEDURE agregar_usuario(nombre_agregar IN VARCHAR, apellido_agregar IN VARCHAR, correo_agregar IN VARCHAR, contrasena_agregar IN VARCHAR);
    
END CRUD;

------------------BODY----------------------------------

CREATE OR REPLACE PACKAGE BODY CRUD IS

    FUNCTION verificar_producto(nombre_n VARCHAR) RETURN int
    AS resultado int;
    BEGIN 
        IF (nombre_n IS NOT NULL) THEN
        SELECT COUNT(*) INTO resultado FROM PRODUCTO WHERE PRODUCTO.NOMBRE = nombre_n;
        ELSE
            resultado := 0;
        END IF;
    
        RETURN resultado; 
    END verificar_producto;
    
    PROCEDURE agregar_producto(nombre_ag IN VARCHAR, descripcion_ag IN VARCHAR, precio_ag IN DECIMAL,  id_categoria_ag IN NUMBER,id_coleccion_ag IN NUMBER, id_marca_ag IN NUMBER, id_talla_ag IN NUMBER)
    IS
        id_marca NUMBER;
        id_talla NUMBER;
        id_categoria NUMBER;
        id_coleccion NUMBER;
    BEGIN
        
        IF (id_marca IS NOT NULL AND id_talla IS NOT NULL AND id_categoria IS NOT NULL AND id_coleccion IS NOT NULL) THEN
            INSERT INTO Producto(id, nombre, descripcion, precio,id_categoria, id_coleccion, id_marca, id_talla )
            VALUES (sec_Producto.nextval, nombre_ag, descripcion_ag, precio_ag, id_categoria_ag, id_coleccion_ag, id_marca_ag, id_talla_ag);
        END IF;
    END agregar_producto;
    
    
    PROCEDURE actualizar_producto(id_act IN NUMBER, nombre_act IN VARCHAR, descripcion_act IN VARCHAR, precio_act IN DECIMAL, id_categoria_act IN NUMBER, id_coleccion_act IN NUMBER, id_marca_act IN NUMBER, id_talla_act IN NUMBER) 
    IS
        id_marca NUMBER;
        id_talla NUMBER;
        id_categoria NUMBER;
        id_coleccion NUMBER;
    BEGIN
        IF (id_marca IS NOT NULL AND id_talla IS NOT NULL AND id_categoria IS NOT NULL AND id_coleccion IS NOT NULL) THEN
            UPDATE Producto 
            SET nombre = nombre_act,
                descripcion = descripcion_act,
                precio = precio_act,
                id_marca = id_marca_act,
                id_talla = id_talla_act,
                id_categoria = id_categoria_act,
                id_coleccion = id_coleccion_act
            WHERE id = id_act;
            COMMIT;
        END IF;
    END actualizar_producto;
    
    PROCEDURE borrar_producto(id_borrar_producto IN NUMBER) 
    IS
    BEGIN
        DELETE FROM PRODUCTO   p 
        WHERE id_borrar_producto = p.id;
        COMMIT;
    END borrar_producto;
    
    PROCEDURE agregar_usuario(nombre_agregar IN VARCHAR, apellido_agregar IN VARCHAR, correo_agregar IN VARCHAR, contrasena_agregar IN VARCHAR)  
    IS
    BEGIN
        INSERT INTO Usuario VALUES (sec_Usuario.nextval,nombre_agregar,apellido_agregar,correo_agregar,contrasena_agregar,0);
    END agregar_usuario;
    
END CRUD;


/*
SET SERVEROUTPUT ON;
declare
   result_n INT;
begin
   -- Call the function
   
   result_n := CRUD.actualizar_inventario(2,10,2);
   dbms_output.put_line(result_n);
end;
*/

-------------------------------TRIGGER----------------------------------------
CREATE OR REPLACE TRIGGER producto_bitacora_trg
    AFTER 
    UPDATE OR DELETE 
    ON Producto
    FOR EACH ROW    
DECLARE
   l_transaction VARCHAR(30);
   id_agregar INT;
   
BEGIN
   l_transaction := CASE  
         WHEN UPDATING THEN 'Actualizado'
         WHEN DELETING THEN 'Borrado'
   END;
   SELECT MAX(id)INTO id_agregar FROM Bitacora;
       
   INSERT INTO Bitacora VALUES ((id_agregar + 1),current_date,l_transaction,2, :NEW.id);
END;

--TEST
SELECT * FROM Bitacora;

UPDATE Producto
SET nombre = 'Tennis', descripcion = 'Grande', precio = 180.0, id_marca = 2, id_talla = 2, id_categoria = 2, id_imagen = 2, id_coleccion = 2
WHERE id = 2;
