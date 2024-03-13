<?php

class BaseDato {

    private $conexion = null;
    private $sentencia = null;
    private $executed = false;

    private static $instanciaUnica = null;

    private function __construct(){}

    public static function obtenerInstancia() {
        if (self::$instanciaUnica == null)
        {
          self::$instanciaUnica = new BaseDato();
        }

        return self::$instanciaUnica;
    }

    function inicializa(
        $basedatos,
        $usuario, 
        $pass,
        $serverIp = 'localhost',
        $charset  = 'utf8mb4',
        $options  = null
    ) {
        $cadenaConexion = "mysql:host=$serverIp;dbname=$basedatos;charset=$charset";

        if($options == null){
            $options = [
              PDO::ATTR_EMULATE_PREPARES   => false, 
              PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
              PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
            ];
        }

        try {
          $this->conexion = new PDO($cadenaConexion, $usuario, $pass, $options);
        } catch (Exception $e) {
          error_log($e->getMessage());
          exit('No ha sido posible la conexión');
        }
    }
    function ejecuta(string $sql, ...$parametros) {
        $this->sentencia = $this->conexion->prepare($sql);

        if($parametros == null){
            $this->executed = $this->sentencia->execute();
            return;
        }

        if($parametros != null && is_array($parametros[0])) {
            $parametros = $parametros[0]; // Si nos pasan un array lo usamos como parámetro
        }

        $this->executed = $this->sentencia->execute($parametros);
    }

    function obtenDatos(){
        return $this->sentencia->fetchAll();
    }

    function getLastId(){
        return $this->conexion->lastInsertId();
    }

    function getExecuted(){
        return $this->executed;
    }

    function __destruct(){
        $this->conexion = null;
    }
}
?>