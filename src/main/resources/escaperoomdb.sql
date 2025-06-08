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
  `idEscaperoom` int(11) NOT NULL,
  `name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


/* ---- ROOM ---- */

CREATE TABLE `room` (
  `idRoom` int(11) NOT NULL,
  `idEscaperoom_ref` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `difficulty` enum('EASY','MEDIUM','HARD') NOT NULL,
  `price` decimal(5,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


/* ---- DECORATION ---- */

CREATE TABLE `decoration` (
  `idDecoration` int(11) NOT NULL,
  `idRoom_ref` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `material` varchar(45) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;


/* ---- HINT ---- */

CREATE TABLE `hint` (
  `idHint` int(11) NOT NULL,
  `idRoom` int(11) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `theme` enum('HORROR','DISNEY','TERROR','') DEFAULT NULL,
  `price` decimal(5,2) NOT NULL
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
  `price` decimal(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;



ALTER TABLE `decoration`
  ADD PRIMARY KEY (`idDecoration`),
  ADD KEY `IdRoom_idx` (`idRoom_ref`);

ALTER TABLE `escaperoom`
  ADD PRIMARY KEY (`idEscaperoom`);

ALTER TABLE `hint`
  ADD PRIMARY KEY (`idHint`),
  ADD KEY `idRoom` (`idRoom`);

ALTER TABLE `player`
  ADD PRIMARY KEY (`idPlayer`);

ALTER TABLE `room`
  ADD PRIMARY KEY (`idRoom`),
  ADD KEY `idEscaperoom_idx` (`idEscaperoom_ref`);

ALTER TABLE `tickets`
  ADD PRIMARY KEY (`idTickets`),
  ADD KEY `IdRoom_idx` (`idRoom`),
  ADD KEY `idPlayer` (`idPlayer`);


ALTER TABLE `decoration`
  MODIFY `idDecoration` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `escaperoom`
  MODIFY `idEscaperoom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `hint`
  MODIFY `idHint` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `player`
  MODIFY `idPlayer` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `room`
  MODIFY `idRoom` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

ALTER TABLE `tickets`
  MODIFY `idTickets` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


ALTER TABLE `decoration`
  ADD CONSTRAINT `IdRoom_ref` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`);

ALTER TABLE `hint`
  ADD CONSTRAINT `hint_ibfk_1` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`);

ALTER TABLE `room`
  ADD CONSTRAINT `idEscaperoom` FOREIGN KEY (`idEscaperoom_ref`) REFERENCES `escaperoom` (`idEscaperoom`);

ALTER TABLE `tickets`
  ADD CONSTRAINT `IdRoom` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`),
  ADD CONSTRAINT `idPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
