<?php
$edad = 18;
$altura = 195.5;
$nombre = "David";

echo "Hola mundo <br>";
print "HOLA MUNDO DESDE PRINT     <br>";

print("HOLA MUNDO DESDE PRINT CON CORCHETE $altura    <br>");

printf("HOLA MUNDO DESDE PRINTF %d", $edad);
$n =  43951789;
$u = -43951789;
$c = 65; // ASCII 65 is 'A'

// notice the double %%, this prints a literal '%' character
printf("%%b = '%b'<br>", $n); // binary representation
printf("%%c = '%c'<br>", $c); // print the ascii character, same as chr() function
printf("%%d = '%d'<br>", $n); // standard integer representation
printf("%%e = '%e'<br>", $n); // scientific notation
printf("%%u = '%u'<br>", $n); // unsigned integer representation of a positive integer
printf("%%u = '%u'<br>", $u); // unsigned integer representation of a negative integer
printf("%%f = '%f'<br>", $n); // floating point representation
printf("%%o = '%o'<br>", $n); // octal representation
printf("%%s = '%s'<br>", $n); // string representation
printf("%%x = '%x'<br>", $n); // hexadecimal representation (lower-case)
printf("%%X = '%X'<br>", $n); // hexadecimal representation (upper-case)

printf("%%+d = '%+d'<br>", $n); // sign specifier on a positive integer
printf("%%+d = '%+d'<br>", $u); // sign specifier on a negative integer
$s = 'monkey';
$t = 'many monkeys';

printf("[%s]<br>",        $s); // standard string output
printf("[%10s]<br>",      $s); // right-justification with spaces
printf("[%-10s]<br>",     $s); // left-justification with spaces
printf("[%010s]<br>",     $s); // zero-padding works on strings too
printf("[%'#10s]<br>",    $s); // use the custom padding character '#'
printf("[%'#*s]<br>", 10, $s); // Provide the padding width as an additional argument
printf("[%10.9s]<br>",    $t); // right-justification but with a cutoff of 8 characters
printf("[%-10.9s]<br>",   $t); // left-justification but with a cutoff of 8 characters

$colores = array("verde","azul","amarillo");
var_dump($colores);

?>