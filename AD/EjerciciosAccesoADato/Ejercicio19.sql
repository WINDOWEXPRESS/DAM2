DECLARE
    v_codigo_articulo NUMBER;
    v_precio NUMBER;
    v_descripcion VARCHAR2(255);
BEGIN
    -- Solicitar el código del artículo por teclado
    v_codigo_articulo := &codigo_articulo; -- &codigo_articulo es un marcador para la entrada de datos

    -- Verificar si el artículo está pedido en la tabla de detalles
    SELECT precio, descripcion INTO v_precio, v_descripcion
    FROM detalles
    WHERE codigo_articulo = v_codigo_articulo;

    -- Si se encontró el artículo en la tabla de detalles, insertarlo en la tabla temporal
    INSERT INTO tabla_temporal (codigo_articulo, precio, descripcion)
    VALUES (v_codigo_articulo, v_precio, v_descripcion);

    -- Si no se encuentra el artículo en detalles, lanzar una excepción
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RAISE_APPLICATION_ERROR(-20001, 'El artículo (' || v_codigo_articulo || ') no lo ha pedido ningún cliente');
END;
/