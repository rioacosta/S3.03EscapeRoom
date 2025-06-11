-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-06-2025 a las 10:36:52
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
-- Base de datos: `escaperoomdb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `certificate`
--

CREATE TABLE `certificate` (
  `idCertificate` int(11) NOT NULL,
  `idPlayer` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `dateOfDelivery` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `certificate`
--

INSERT INTO `certificate` (`idCertificate`, `idPlayer`, `name`, `description`, `dateOfDelivery`) VALUES
(1, 1, 'Certificado por la superación de la sala Disn', 'Creías que no lo ibas a conseguir... pero lo lograste! :D Pluto se sentiría orgulloso', '2025-06-07 14:13:31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `decoration`
--

CREATE TABLE `decoration` (
  `idDecoration` int(11) NOT NULL,
  `idRoom_ref` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `material` varchar(45) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `decoration`
--

INSERT INTO `decoration` (`idDecoration`, `idRoom_ref`, `description`, `material`, `price`) VALUES
(1, 1, 'Orejas mickey', 'Plástico', 60.00),
(2, 1, 'Pastel a medio comer', 'Plástico', 47.50),
(3, 2, 'Cuchillo ensangrentado', 'Metal', 35.00),
(5, 20, 'papo', 'madeera', 12.00),
(6, 21, 'martito', 'madera', 12.00),
(7, 22, 'madera y pañal', 'madera', 12.00),
(8, 23, 'aerawe', 'ma', 12.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `escaperoom`
--

CREATE TABLE `escaperoom` (
  `idEscaperoom_ref` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `escaperoom`
--

INSERT INTO `escaperoom` (`idEscaperoom_ref`, `name`) VALUES
(1, 'Escape Room');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hint`
--

CREATE TABLE `hint` (
  `idHint` int(11) NOT NULL,
  `idRoom_ref` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `theme` enum('HORROR','DISNEY','TERROR','') DEFAULT NULL,
  `price` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `hint`
--

INSERT INTO `hint` (`idHint`, `idRoom_ref`, `description`, `theme`, `price`) VALUES
(1, 1, 'Ponte las orejitas y lo verás todo más claro', 'DISNEY', 20.00),
(2, 2, 'Qué hay debajo del sofá? Podría ser...', 'TERROR', 25.00),
(3, 2, 'El cuervo graznó esa media noche', 'TERROR', 17.00),
(9, 20, 'marta y marto', 'HORROR', 12.00),
(10, 21, 'martita', 'HORROR', 12.00),
(11, 22, 'martita y tito', 'HORROR', 12.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `player`
--

CREATE TABLE `player` (
  `idPlayer` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `player`
--

INSERT INTO `player` (`idPlayer`, `name`, `email`) VALUES
(1, 'Romina', 'romi@mail.com'),
(2, 'Nim', 'nim@mail.com'),
(3, 'Perrito Barrios', 'perritoDog@mimail.com'),
(4, 'Perrito Barrios', 'perritoDog@mimail.com'),
(5, 'Totopo con gucamole', 'gucamole@tuNacho.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `room`
--

CREATE TABLE `room` (
  `idRoom` int(11) NOT NULL,
  `idEscaperoom_ref` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `difficulty` varchar(255) DEFAULT NULL,
  `price` decimal(5,2) NOT NULL,
  `theme` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `room`
--

INSERT INTO `room` (`idRoom`, `idEscaperoom_ref`, `name`, `difficulty`, `price`, `theme`) VALUES
(1, 1, 'Sala Disney', 'EASY', 100.00, 'DISNEY'),
(2, 1, 'Sala Terrorífica', 'HARD', 150.00, 'TERROR'),
(19, 1, 'TEST ROOM', 'EASY', 50.00, 'HORROR'),
(20, 1, 'paopo y pepop', 'HARD', 12.00, 'HORROR'),
(21, 1, 'salaTop', 'EASY', 12.00, 'HORROR'),
(22, 1, '0', 'MEDIUM', 12.00, 'HORROR'),
(23, 1, 'marta', 'EASY', 12.00, 'HORROR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tickets`
--

CREATE TABLE `tickets` (
  `idTickets` int(11) NOT NULL,
  `idRoom` int(11) DEFAULT NULL,
  `idPlayer` int(11) DEFAULT NULL,
  `boughtOn` datetime DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Volcado de datos para la tabla `tickets`
--

INSERT INTO `tickets` (`idTickets`, `idRoom`, `idPlayer`, `boughtOn`, `price`) VALUES
(1, 1, 1, '2025-06-07 14:13:31', 35.00),
(2, 2, 2, '2025-06-07 14:13:31', 38.00);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `certificate`
--
ALTER TABLE `certificate`
  ADD PRIMARY KEY (`idCertificate`),
  ADD KEY `idPlayer` (`idPlayer`);

--
-- Indices de la tabla `decoration`
--
ALTER TABLE `decoration`
  ADD PRIMARY KEY (`idDecoration`),
  ADD KEY `IdRoom_idx` (`idRoom_ref`);

--
-- Indices de la tabla `escaperoom`
--
ALTER TABLE `escaperoom`
  ADD PRIMARY KEY (`idEscaperoom_ref`);

--
-- Indices de la tabla `hint`
--
ALTER TABLE `hint`
  ADD PRIMARY KEY (`idHint`),
  ADD KEY `idRoom` (`idRoom_ref`);

--
-- Indices de la tabla `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`idPlayer`);

--
-- Indices de la tabla `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`idRoom`),
  ADD KEY `idEscaperoom_idx` (`idEscaperoom_ref`);

--
-- Indices de la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`idTickets`),
  ADD KEY `IdRoom_idx` (`idRoom`),
  ADD KEY `idPlayer` (`idPlayer`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `certificate`
--
ALTER TABLE `certificate`
  MODIFY `idCertificate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `decoration`
--
ALTER TABLE `decoration`
  MODIFY `idDecoration` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `escaperoom`
--
ALTER TABLE `escaperoom`
  MODIFY `idEscaperoom_ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `hint`
--
ALTER TABLE `hint`
  MODIFY `idHint` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `player`
--
ALTER TABLE `player`
  MODIFY `idPlayer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `room`
--
ALTER TABLE `room`
  MODIFY `idRoom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `idTickets` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `decoration`
--
ALTER TABLE `decoration`
  ADD CONSTRAINT `IdRoom_ref` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`) ON DELETE CASCADE;

--
-- Filtros para la tabla `hint`
--
ALTER TABLE `hint`
  ADD CONSTRAINT `fk_hint_room` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`) ON DELETE CASCADE;

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `idEscaperoom` FOREIGN KEY (`idEscaperoom_ref`) REFERENCES `escaperoom` (`idEscaperoom_ref`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `IdRoom` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`) ON DELETE CASCADE,
  ADD CONSTRAINT `idPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
