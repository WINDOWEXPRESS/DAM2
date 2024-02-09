
<?php

$curl = curl_init();

curl_setopt_array($curl, array(
  CURLOPT_URL => "https://opendata.aemet.es/opendata/api/observacion/convencional/datos/estacion/3195?api_key=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJkbWF0ZW9zamltZW5lekBlZHVjYS5tYWRyaWQub3JnIiwianRpIjoiNGJkYmViZDgtNTdkOC00ZTU0LThkM2QtMDZjODAxNTMzNzQ0IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE3MDU2Nzg3NzYsInVzZXJJZCI6IjRiZGJlYmQ4LTU3ZDgtNGU1NC04ZDNkLTA2YzgwMTUzMzc0NCIsInJvbGUiOiIifQ.zNog23P-GVt36SbEZaHbbCAZcTeimdVHDYO4hgiqXDk",
  CURLOPT_RETURNTRANSFER => true,
  CURLOPT_ENCODING => "",
  CURLOPT_MAXREDIRS => 10,
  CURLOPT_TIMEOUT => 30,
  CURLOPT_HTTP_VERSION => CURL_HTTP_VERSION_1_1,
  CURLOPT_CUSTOMREQUEST => "GET",
  CURLOPT_HTTPHEADER => array(
    "cache-control: no-cache"
  ),
));

$response = curl_exec($curl);
$err = curl_error($curl);

curl_close($curl);

if ($err) {
  echo "cURL Error #:" . $err;
} else {
  echo $response;
}
