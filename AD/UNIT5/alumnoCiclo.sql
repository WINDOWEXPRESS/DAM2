
CREATE TABLE ciclo(
    codciclo varchar(3) PRIMARY KEY,
    denciclo varchar(70),
    grado varchar(8)
); INSERT INTO ciclo
VALUES(
    'DAM',
    'Dearrollo de aplicaciones multiplataforma',
    'superior'
);
INSERT INTO ciclo
VALUES(
    'ASIR',
    'Administración de sistemas informáticas y redes',
    'superior'
);
INSERT INTO ciclo
VALUES(
    'SMR',
    'Sistemas microinformáticos y redes',
    'medio'
);
CREATE TABLE alumno(
    numexpdte VARCHAR(4) PRIMARY KEY,
    nombre VARCHAR(25),
    dni VARCHAR(9),
    ciclo VARCHAR(3),
    fecnac DATE,
    CONSTRAINT ciclo_fk_KEY FOREIGN KEY(ciclo) REFERENCES ciclo(codciclo)
); INSERT INTO alumno
VALUES(
    '1',
    'maria gomez',
    '222222',
    'DAM',
    '01/10/07'
);
INSERT INTO alumno
VALUES(
    '2',
    'Juan',
    '9999999',
    'ASIR',
    '01/01/08'
);
INSERT INTO alumno
VALUES(
    '3',
    'Antonio Gomez',
    '44444444',
    'DAM',
    '01/02/08'
);
INSERT INTO alumno
VALUES(
    '4',
    'Ana lopez',
    '888888888',
    'DAM',
    '01/03/08'
);