DROP TABLE alumno;
DROP TABLE ciclo;
 
CREATE TABLE `ciclo` (
 codciclo              varCHAR(3)  NOT NULL,
 denciclo               varCHAR(70),
 grado                 varCHAR(28),
 PRIMARY KEY (codciclo));

INSERT INTO `ciclo`  VALUES ('DAI','Dearrollo de aplicaciones inform�ticas','superior');
INSERT INTO `ciclo` ( VALUES ('ASI','Administraci�n aplicaciones inform�ticas','superior');
INSERT INTO `ciclo`  VALUES ('ESI','Explotaci�n de sistemas inform�ticos','medio');
 
 
CREATE TABLE `alumno` (
 numexpdte varchar(4)  , nombre varchar(25),
 dni varchar(9),ciclo varchar(3),
 PRIMARY KEY (numexpdte)
 CONSTRAINT ciclo_FOREIGN_KEY FOREIGN KEY (codciclo) REFERENCES ciclo (codciclo)
);

  
