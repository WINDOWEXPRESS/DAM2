CREATE OR REPLACE TRIGGER LimitarDetalles
BEFORE INSERT ON sales_order
FOR EACH ROW
DECLARE
    vCountDetalles NUMBER;
BEGIN
    -- Contar los detalles actuales para el pedido
    SELECT COUNT(*) INTO vCountDetalles FROM sales_order WHERE order_id = :NEW.order_id;
    
    IF vCountDetalles >= 5 THEN
        RAISE_APPLICATION_ERROR(-20001, 'No se pueden agregar más de 5 detalles a este pedido.');
    END IF;
END;
/