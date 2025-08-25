CREATE DATABASE  IF NOT EXISTS `customs` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `customs`;
-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: localhost    Database: customs
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `transportId` varchar(45) DEFAULT NULL,
  `declarationId` varchar(45) DEFAULT NULL,
  `vesselOrFlight` varchar(45) DEFAULT NULL,
  `portOfLoading` varchar(45) DEFAULT NULL,
  `portOfDischarge` varchar(45) DEFAULT NULL,
  `departureDate` datetime DEFAULT NULL,
  `arrivalDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
INSERT INTO `transport` VALUES (5,'Evergreen (長榮海運)-DEC20250825-001','DEC20250825-001','Evergreen (長榮海運)','Kaohsiung Port (高雄港)','Kaohsiung Port (高雄港)','2025-08-06 02:42:53','2025-08-20 02:42:55'),(6,'Evergreen (長榮海運)-DEC20250825-001','DEC20250825-001','Evergreen (長榮海運)','Kaohsiung Port (高雄港)','Kaohsiung Port (高雄港)','2025-08-06 02:42:53','2025-08-20 02:42:55'),(7,'Yang Ming (陽明海運)-DEC20250825-002','DEC20250825-002','Yang Ming (陽明海運)','Busan Port (釜山港)','Keelung Port (基隆港)','2025-08-26 06:11:18','2025-08-30 06:11:22'),(8,'Evergreen (長榮海運)-DEC20250825-003','DEC20250825-003','Evergreen (長榮海運)','Shanghai Port (上海港)','Kaohsiung Port (高雄港)','2025-08-26 06:23:34','2025-08-28 06:23:36');
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-25  7:17:14
