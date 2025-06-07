-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: escaperoomdb
-- ------------------------------------------------------
-- Server version	8.0.42

CREATE SCHEMA IF NOT EXISTS `escaperoomdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `escaperoomdb`;


--
-- Table structure for table `escaperoom`
--

CREATE TABLE IF NOT EXISTS `escaperoom` (
  `idEscaperoom` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idEscaperoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `idRoom` int NOT NULL AUTO_INCREMENT,
  `idEscaperoom_ref` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `dificulty` enum('EASY','MEDIUM','HARD') NOT NULL,
  `price` decimal(2,0) NOT NULL,
  PRIMARY KEY (`idRoom`),
  KEY `idEscaperoom_idx` (`idEscaperoom_ref`),
  CONSTRAINT `idEscaperoom` FOREIGN KEY (`idEscaperoom_ref`) REFERENCES `escaperoom` (`idEscaperoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `decoration`
--

CREATE TABLE IF NOT EXISTS `decoration` (
  `idDecoration` int NOT NULL AUTO_INCREMENT,
  `idRoom_ref` int NOT NULL,
  `description` varchar(100) NOT NULL,
  `material` varchar(45) DEFAULT NULL,
  `price` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`idDecoration`),
  KEY `IdRoom_idx` (`idRoom_ref`),
  CONSTRAINT `IdRoom_ref` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `hint`
--

CREATE TABLE IF NOT EXISTS `hint` (
  `idHint` int NOT NULL AUTO_INCREMENT,
  `idRoom` int NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `theme` varchar(45) DEFAULT NULL,
  `price` decimal(2,0) NOT NULL,
  PRIMARY KEY (`idHint`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `player`
--

CREATE TABLE IF NOT EXISTS `player` (
  `idPlayer` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPlayer`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;


--
-- Table structure for table `tickets`
--

CREATE TABLE IF NOT EXISTS `tickets` (
  `idTickets` int NOT NULL,
  `idRoom` int DEFAULT NULL,
  `idPlayer` int DEFAULT NULL,
  `boughtOn` datetime DEFAULT NULL,
  `price` decimal(2,0) DEFAULT NULL,
  KEY `IdPlayer_idx` (`idPlayer`),
  KEY `IdRoom_idx` (`idRoom`),
  CONSTRAINT `IdPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`),
  CONSTRAINT `IdRoom` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
