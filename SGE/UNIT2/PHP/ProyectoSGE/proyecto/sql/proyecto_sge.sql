-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-02-2024 a las 19:27:10
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_sge`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admini` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `contrasenia` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `libros`
--

CREATE TABLE `libros` (
  `ID` int(11) NOT NULL,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `isbn` bigint(20) NOT NULL,
  `prestado` tinyint(1) DEFAULT 0 COMMENT 'Estado de que si esta prestado o no.\r\n0 es no prestado\r\n1 es prestado'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `libros`
--

INSERT INTO `libros` (`ID`, `titulo`, `autor`, `isbn`, `prestado`) VALUES
(1, 'TCP/IP Illustrated', 'Stevens W', 132141232, 0),
(2, 'Advan Programming for Unix environment', 'Stevens W', 512512312, 0),
(3, 'Data on the Web', 'Abiteboul Serge', 3415161234, 0),
(4, 'Economics of Technology for Digital TV', 'Gerbarg Darcy CITI', 4124512321, 0),
(5, 'TODOS LOS LUGARES QUE MANTUVIMOS EN SECRETO', 'INMA RUBIALES', 2910026421889, 0),
(6, 'JAQUE MATE AL AMOR', 'ALI HAZELWOOD', 9788418945465, 1),
(7, 'TU CEREBRO TIENE HAMBRE', 'BOTICARIA GARCIA', 9788408282334, 0),
(8, 'ONE PIECE Nº 05 (3 EN 1)', 'EIICHIRO ODA', 9788411610773, 0),
(9, 'NOS TRATAMOS BIEN', 'LUCIA SERRANO', 9788448865207, 1),
(10, 'TU CUERPO ES TUYO', 'LUCIA SERRANO', 9788418599408, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `prestamos`
--

CREATE TABLE `prestamos` (
  `idLibro` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `idPrestacion` int(11) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFinal` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `ID` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contrasenia` varchar(255) NOT NULL,
  `provincia` varchar(255) NOT NULL,
  `poblacion` varchar(255) NOT NULL,
  `poblacion_id` varchar(255) NOT NULL,
  `idioma` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admini`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `libros`
--
ALTER TABLE `libros`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indices de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD PRIMARY KEY (`idPrestacion`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`ID`);

ALTER TABLE `usuarios`
MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

  ALTER TABLE `usuarios`
  ADD UNIQUE (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `admin`
--
ALTER TABLE `admini`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `libros`
--
ALTER TABLE `libros`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `prestamos`
--
ALTER TABLE `prestamos`
  MODIFY `idPrestacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `prestamos`
--
ALTER TABLE `prestamos`
  ADD CONSTRAINT `foreign key libro` FOREIGN KEY (`idLibro`) REFERENCES `libros` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
