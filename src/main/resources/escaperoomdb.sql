SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

CREATE DATABASE IF NOT EXISTS `escaperoomdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `escaperoomdb`;


/* ---- ESCAPEROOM ---- */

CREATE TABLE `escaperoom` (
  `idEscaperoom_ref` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



/* ---- ROOM ---- */

CREATE TABLE `room` (
  `idRoom` int(11) NOT NULL,
  `idEscaperoom_ref` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `difficulty` varchar(255) DEFAULT NULL,
  `price` decimal(5,2) NOT NULL,
  `theme` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



/* ---- DECORATION ---- */

CREATE TABLE `decoration` (
  `idDecoration` int(11) NOT NULL,
  `idRoom_ref` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `material` varchar(45) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



/* ---- PLAYER ---- */

CREATE TABLE `player` (
  `idPlayer` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



/* ---- TICKETS ---- */

CREATE TABLE `tickets` (
  `idTickets` int(11) NOT NULL,
  `idRoom` int(11) DEFAULT NULL,
  `idPlayer` int(11) DEFAULT NULL,
  `boughtOn` datetime DEFAULT NULL,
  `price` decimal(2,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


/* ---- HINT ---- */

CREATE TABLE `hint` (
  `idHint` int(11) NOT NULL,
  `idRoom_ref` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `theme` enum('HORROR','DISNEY','TERROR','') DEFAULT NULL,
  `price` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


/* ---- CERTIFICATE ---- */

CREATE TABLE `certificate` (
  `idCertificate` int(11) NOT NULL,
  `idPlayer_ref` int(11) NOT NULL,
  `name`varchar(45)NOT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


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
-- Indices de la tabla `certificate`
--
ALTER TABLE `certificate`
  ADD PRIMARY KEY (`idCertificate`),
  ADD KEY `idPlayer` (`idPlayer_ref`);


--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `decoration`
--
ALTER TABLE `decoration`
  MODIFY `idDecoration` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `escaperoom`
--
ALTER TABLE `escaperoom`
  MODIFY `idEscaperoom_ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `hint`
--
ALTER TABLE `hint`
  MODIFY `idHint` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `player`
--
ALTER TABLE `player`
  MODIFY `idPlayer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `room`
--
ALTER TABLE `room`
  MODIFY `idRoom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `tickets`
--
ALTER TABLE `tickets`
  MODIFY `idTickets` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `certificate`
--
ALTER TABLE `certificate`
  MODIFY `idCertificate` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;


--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `decoration`
--
ALTER TABLE `decoration`
  ADD CONSTRAINT `IdRoom_ref` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`);

--
-- Filtros para la tabla `hint`
--
ALTER TABLE `hint`
  ADD CONSTRAINT `fk_hint_room` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`),
  ADD CONSTRAINT `hint_ibfk_1` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`);

--
-- Filtros para la tabla `room`
--
ALTER TABLE `room`
  ADD CONSTRAINT `idEscaperoom` FOREIGN KEY (`idEscaperoom_ref`) REFERENCES `escaperoom` (`idEscaperoom_ref`);

--
-- Filtros para la tabla `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `IdRoom` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`),
  ADD CONSTRAINT `idPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
