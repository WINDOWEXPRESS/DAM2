DECLARE
    v_codigo_articulo NUMBER;
    v_precio NUMBER;
    v_descripcion VARCHAR2(255);
BEGIN
    -- Solicitar el c�digo del art�culo por teclado
    v_codigo_articulo := &codigo_articulo; -- &codigo_articulo es un marcador para la entrada de datos

    -- Verificar si el art�culo est� pedido en la tabla de detalles
    SELECT precio, descripcion INTO v_precio, v_descripcion
    FROM detalles
    WHERE codigo_articulo = v_codigo_articulo;

    -- Si se encontr� el art�culo en la tabla de detalles, insertarlo en la tabla temporal
    INSERT INTO tabla_temporal (codigo_articulo, precio, descripcion)
    VALUES (v_codigo_articulo, v_precio, v_descripcion);

    -- Si no se encuentra el art�culo en detalles, lanzar una excepci�n
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20001, 'El art�culo (' || v_codigo_articulo || ') no lo ha pedido ning�n cliente');
END;
/