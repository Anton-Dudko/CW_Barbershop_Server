CREATE DATABASE  IF NOT EXISTS `barbershop_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `barbershop_system`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: barbershop_system
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `categorys`
--

DROP TABLE IF EXISTS `categorys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorys` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `parent_category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_category_id` (`parent_category_id`),
  CONSTRAINT `categorys_ibfk_1` FOREIGN KEY (`parent_category_id`) REFERENCES `categorys` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorys`
--

LOCK TABLES `categorys` WRITE;
/*!40000 ALTER TABLE `categorys` DISABLE KEYS */;
INSERT INTO `categorys` VALUES (1,'Женская',NULL),(2,'Женская короткая',1),(3,'Мужская',NULL),(4,'Детская',NULL),(5,'Женская длинная',1),(11,'Женская средняя',1);
/*!40000 ALTER TABLE `categorys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `haircuts`
--

DROP TABLE IF EXISTS `haircuts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `haircuts` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `description` mediumtext NOT NULL,
  `price` double NOT NULL,
  `count` int NOT NULL,
  `style_name` varchar(60) NOT NULL,
  `category_id` int NOT NULL,
  `salon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `salon_id` (`salon_id`),
  CONSTRAINT `haircuts_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `categorys` (`id`),
  CONSTRAINT `haircuts_ibfk_2` FOREIGN KEY (`salon_id`) REFERENCES `salons` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `haircuts`
--

LOCK TABLES `haircuts` WRITE;
/*!40000 ALTER TABLE `haircuts` DISABLE KEYS */;
INSERT INTO `haircuts` VALUES (1,'Стризка под кателок','Обычная такая, под кателок, как говорится',12,60,'Классика',2,2),(2,'Стрижка Стильная Модерн','Фэшн',22,80,'Модерн',2,1),(3,'Стрижка НовыйМодерн','Фэшн 2',23,80,'Модерн',3,1),(4,'Стрижка Завивайка','Завивка бегудями',18,54,'Классика',1,1),(5,'Стрижка налосо','Hard',14,20,'Треш',4,3);
/*!40000 ALTER TABLE `haircuts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `masters`
--

DROP TABLE IF EXISTS `masters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `masters` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `country` varchar(60) NOT NULL,
  `date_of_cooperation` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `masters`
--

LOCK TABLES `masters` WRITE;
/*!40000 ALTER TABLE `masters` DISABLE KEYS */;
INSERT INTO `masters` VALUES (1,'Иван Иванович','Россия','2021-10-10'),(2,'Павел Павлович','Россия','2021-01-16'),(3,'Николай Иванов','Россия','2016-10-22'),(4,'Екатерина Стулькан','Беларусь','2021-12-30'),(5,'Суперваня Иванов','Беларусь','2017-12-31'),(6,'Мухамад Худжуков','Узбекистан','2015-10-10');
/*!40000 ALTER TABLE `masters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `address` varchar(60) NOT NULL,
  `mail` varchar(60) NOT NULL,
  `haircut_id` int NOT NULL,
  `count` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `haircut_id` (`haircut_id`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`haircut_id`) REFERENCES `haircuts` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (1,'2018-11-11','Улица Беды 22','superuser@mail.ru',4,24),(2,'2020-12-11','Улица Немига 11','ivan@mail.ru',4,24),(3,'2018-05-22','Улица Беды 22','ser_her@ya.ru',2,8),(10,'2020-12-11','Улица Немига 11','superuser@mail.ru',4,2),(11,'2021-11-27','Улица Беды 22','adhs@nasd.ru',4,2),(14,'2016-10-10','Улица Немига 11','superuser@mail.ru',1,23),(17,'2018-12-11','Улица Немига 11','sfdsf@sdf.ru',4,24),(18,'2020-12-11','Улица Зверя 48','gsdfsfd@retr.yui',5,21),(19,'2018-12-11','Улица Зверя 48','gfdgf@sdfsd.hj',3,32),(20,'2018-12-11','Улица Немига 11','rteerta@SDFG.HJ',2,8),(21,'2018-12-11','Улица Зверя 48','fhhh@dfg.hu',3,54),(22,'2020-12-11','Улица Немига 11','fghfhg@dfgh.jk',2,34),(23,'2018-12-11','Улица Зверя 48','rewer@ddsf.joi',1,9),(24,'2018-12-11','Улица Немига 11','fhg@sadhj.ru',5,3),(25,'2018-12-11','Улица Зверя 48','dgffg@sdfg.jk',1,5);
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salons`
--

DROP TABLE IF EXISTS `salons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salons` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL,
  `address` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salons`
--

LOCK TABLES `salons` WRITE;
/*!40000 ALTER TABLE `salons` DISABLE KEYS */;
INSERT INTO `salons` VALUES (1,'Барбершоп Style на Немиге','Улица Немига 3 - павильон 25'),(2,'Барбершоп Style Южный','Улица Могилевская 23, помеение 22'),(3,'Барбершоп Style Уручье','Улица Радиальная 11 - квартира 80');
/*!40000 ALTER TABLE `salons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfers`
--

DROP TABLE IF EXISTS `transfers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `master_id` int NOT NULL,
  `haircut_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `supplie_haircuts_masters_id_fk` (`master_id`),
  KEY `supplie_haircuts__haircut_id_fk` (`haircut_id`),
  CONSTRAINT `supplie_haircuts__haircut_id_fk` FOREIGN KEY (`haircut_id`) REFERENCES `haircuts` (`id`),
  CONSTRAINT `supplie_haircuts_masters_id_fk` FOREIGN KEY (`master_id`) REFERENCES `masters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfers`
--

LOCK TABLES `transfers` WRITE;
/*!40000 ALTER TABLE `transfers` DISABLE KEYS */;
INSERT INTO `transfers` VALUES (1,1,1),(2,2,1),(3,2,2),(4,3,4),(5,4,3),(8,1,4),(9,3,2);
/*!40000 ALTER TABLE `transfers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_categories`
--

DROP TABLE IF EXISTS `user_categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_categories` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_categories_categorys_id_fk` (`category_id`),
  KEY `user_categories_users_id_fk` (`user_id`),
  CONSTRAINT `user_categories_categorys_id_fk` FOREIGN KEY (`category_id`) REFERENCES `categorys` (`id`),
  CONSTRAINT `user_categories_users_id_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_categories`
--

LOCK TABLES `user_categories` WRITE;
/*!40000 ALTER TABLE `user_categories` DISABLE KEYS */;
INSERT INTO `user_categories` VALUES (1,2,1),(2,2,2),(3,5,3),(4,5,1);
/*!40000 ALTER TABLE `user_categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `role` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'rootuser','rootuser',0),(2,'justuser','justuser',1),(4,'weekuser','weekuser',2),(5,'qwe','rty',1),(10,'qwerty','123',2),(11,'who','iam',2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-08  2:42:29
