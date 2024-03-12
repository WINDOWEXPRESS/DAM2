<?php
function pedirPoblacionId($poblacionSelected,$ruta){
    $poblacionId = "";
    if(!empty($poblacionSelected)){
        $file = fopen($ruta,'r');
        if( $file != false){
            while(($data = fgetcsv($file)) != false){
                if($data[2] == $poblacionSelected){
                    $poblacionId = $data[1];
                    break;
                }
            }
            fclose($file);
        }
    }
    return $poblacionId;
}
?>