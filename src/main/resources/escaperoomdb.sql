-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: escaperoomdb
-- ------------------------------------------------------
-- Server version	8.0.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `certificate`
--

DROP TABLE IF EXISTS `certificate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `certificate` (
  `idCertificate` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `dateOfDelivery` date DEFAULT NULL,
  `idPlayer` int NOT NULL,
  PRIMARY KEY (`idCertificate`),
  KEY `idPlayer` (`idPlayer`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `certificate`
--

LOCK TABLES `certificate` WRITE;
/*!40000 ALTER TABLE `certificate` DISABLE KEYS */;
INSERT INTO `certificate` VALUES (8,'Braulio','por apoyar siempre a su equipo','2025-06-10',2);
/*!40000 ALTER TABLE `certificate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `decoration`
--

DROP TABLE IF EXISTS `decoration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `decoration` (
  `idDecoration` int NOT NULL AUTO_INCREMENT,
  `idRoom_ref` int NOT NULL,
  `description` varchar(100) NOT NULL,
  `material` varchar(45) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`idDecoration`),
  KEY `IdRoom_idx` (`idRoom_ref`),
  CONSTRAINT `IdRoom_ref` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `decoration`
--

LOCK TABLES `decoration` WRITE;
/*!40000 ALTER TABLE `decoration` DISABLE KEYS */;
/*!40000 ALTER TABLE `decoration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `escaperoom`
--

DROP TABLE IF EXISTS `escaperoom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `escaperoom` (
  `idEscaperoom_ref` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`idEscaperoom_ref`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `escaperoom`
--

LOCK TABLES `escaperoom` WRITE;
/*!40000 ALTER TABLE `escaperoom` DISABLE KEYS */;
/*!40000 ALTER TABLE `escaperoom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hint`
--

DROP TABLE IF EXISTS `hint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hint` (
  `idHint` int NOT NULL AUTO_INCREMENT,
  `idRoom_ref` int NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `theme` enum('HORROR','DISNEY','TERROR','') DEFAULT NULL,
  `price` decimal(5,2) NOT NULL,
  PRIMARY KEY (`idHint`),
  KEY `idRoom` (`idRoom_ref`),
  CONSTRAINT `fk_hint_room` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`),
  CONSTRAINT `hint_ibfk_1` FOREIGN KEY (`idRoom_ref`) REFERENCES `room` (`idRoom`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hint`
--

LOCK TABLES `hint` WRITE;
/*!40000 ALTER TABLE `hint` DISABLE KEYS */;
/*!40000 ALTER TABLE `hint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `player`
--

DROP TABLE IF EXISTS `player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `player` (
  `idPlayer` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idPlayer`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `player`
--

LOCK TABLES `player` WRITE;
/*!40000 ALTER TABLE `player` DISABLE KEYS */;
INSERT INTO `player` VALUES (2,'Braulio','bra@ulio.com');
/*!40000 ALTER TABLE `player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `idRoom` int NOT NULL AUTO_INCREMENT,
  `idEscaperoom_ref` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `difficulty` varchar(255) DEFAULT NULL,
  `price` decimal(5,2) NOT NULL,
  `theme` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idRoom`),
  KEY `idEscaperoom_idx` (`idEscaperoom_ref`),
  CONSTRAINT `idEscaperoom` FOREIGN KEY (`idEscaperoom_ref`) REFERENCES `escaperoom` (`idEscaperoom_ref`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tickets`
--

DROP TABLE IF EXISTS `tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tickets` (
  `idTickets` int NOT NULL AUTO_INCREMENT,
  `idRoom` int DEFAULT NULL,
  `idPlayer` int DEFAULT NULL,
  `boughtOn` datetime DEFAULT NULL,
  `price` decimal(2,0) DEFAULT NULL,
  PRIMARY KEY (`idTickets`),
  KEY `IdRoom_idx` (`idRoom`),
  KEY `idPlayer` (`idPlayer`),
  CONSTRAINT `idPlayer` FOREIGN KEY (`idPlayer`) REFERENCES `player` (`idPlayer`),
  CONSTRAINT `IdRoom` FOREIGN KEY (`idRoom`) REFERENCES `room` (`idRoom`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tickets`
--

LOCK TABLES `tickets` WRITE;
/*!40000 ALTER TABLE `tickets` DISABLE KEYS */;
/*!40000 ALTER TABLE `tickets` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-10 18:10:19
