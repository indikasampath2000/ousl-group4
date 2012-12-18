-- MySQL dump 10.13  Distrib 5.5.24, for debian-linux-gnu (i686)
--
-- Host: localhost    Database: notification_api_demo
-- ------------------------------------------------------
-- Server version	5.5.24-0ubuntu0.12.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bid`
--

DROP TABLE IF EXISTS `bid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `listing_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `value` decimal(20,2) NOT NULL,
  `created_on` datetime NOT NULL,
  `edited_on` datetime NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `bid_unique` (`listing_id`,`user_id`,`value`,`status`),
  KEY `listing_id` (`listing_id`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bid_ibfk_1` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `bid_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid`
--

LOCK TABLES `bid` WRITE;
/*!40000 ALTER TABLE `bid` DISABLE KEYS */;
INSERT INTO `bid` VALUES (1,1,1,0.00,'2012-12-03 07:00:00','2012-12-03 07:00:00',1);
/*!40000 ALTER TABLE `bid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bid_max`
--

DROP TABLE IF EXISTS `bid_max`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bid_max` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `listing_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `max_bid` decimal(20,2) NOT NULL,
  `edited_on` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `listing_id` (`listing_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `bid_max_ibfk_1` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `bid_max_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bid_max`
--

LOCK TABLES `bid_max` WRITE;
/*!40000 ALTER TABLE `bid_max` DISABLE KEYS */;
INSERT INTO `bid_max` VALUES (1,1,1,100.00,'2012-12-03 07:00:00'),(2,1,1,200.00,'2012-12-03 07:02:00');
/*!40000 ALTER TABLE `bid_max` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(500) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Consumer Electronics','this category has electronic appliances',1),(2,'Fashion','this category has clothes, shoes and other fashion items',1),(3,'Jewellery & Watches','this category has luxury personal ornaments, such as necklaces, rings, bracelets etc',1),(4,'Vehicles','this category has motor vehicles',1),(5,'Other','miscellaneous items',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `country_code` char(2) CHARACTER SET latin1 DEFAULT NULL,
  `name` varchar(64) CHARACTER SET latin1 DEFAULT NULL,
  `phone_code` varchar(5) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (-1,'0','Worldwide','0'),(1,'AF','Afghanistan','93'),(14,'AU','Australia','61'),(18,'BH','Bahrain','973'),(19,'BD','Bangladesh','880'),(22,'BE','Belgium','32'),(26,'BT','Bhutan','975'),(31,'BR','Brazil','55'),(39,'CA','Canada','1'),(45,'CN','China','86'),(57,'CY','Cyprus','357'),(59,'DK','Denmark','45'),(73,'FI','Finland','358'),(74,'FR','France','33'),(81,'DE','Germany','49'),(85,'GR','Greece','30'),(97,'HK','Hong Kong','852'),(100,'IN','India','91'),(101,'ID','Indonesia','62'),(102,'IR','Iran','98'),(103,'IQ','Iraq','964'),(105,'IL','Israel','972'),(106,'IT','Italy','39'),(108,'JP','Japan','81'),(115,'KW','Kuwait','965'),(130,'MY','Malaysia','60'),(131,'MV','Maldives','960'),(150,'NP','Nepal','977'),(154,'NZ','New Zealand','64'),(161,'NO','Norway','47'),(162,'OM','Oman','968'),(163,'PK','Pakistan','92'),(170,'PH','Philippines','63'),(175,'QA','Qatar','974'),(178,'RU','Russia','7'),(193,'SG','Singapore','65'),(198,'ZA','South Africa','27'),(201,'LK','Sri Lanka','94'),(207,'CH','Switzerland','41'),(209,'TW','Taiwan','886'),(212,'TH','Thailand','66'),(225,'AE','United Arab Emirates','971'),(226,'GB','United Kingdom','44'),(227,'US','United States','1');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` char(12) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sms_search_name` varchar(12) NOT NULL,
  `description` text NOT NULL,
  `condition_id` text NOT NULL,
  `image_path` varchar(255) DEFAULT NULL,
  `created_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `item_id` (`item_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,'IT000001',1,'Dell XPS 15 3RD Gen','Dell XPS','If you think the 15-inch Apple MacBook Pro is cool but you want a Windows PC with a slightly softer touch then the Dell XPS 15 might be just the notebook you need.','1','images/dell.jpg','2012-12-02 18:30:00',1),(2,'IT000002',1,'Apple iPhone5 4G','iphone','So much more than before. And so much less, too.\nA remarkably slim design that still makes room for a larger display and a faster chip. Ultrafast wireless that doesn’t sacrifice battery life.','1','images/apple.jpg','2012-12-02 18:30:00',1),(3,'IT000003',1,'Nikon’s D7000 DSLR camera','Nikon','16.2 MP DX-format CMOS sensor, 6 fps continuous shooting and breathtaking Full 1080p HD Movies with full time autofocus','1','images/dslr.jpg','2012-12-02 18:30:00',1),(4,'IT000004',1,'Woman new style ladies handbags bangkok','handbag','Professional manufacturer in lady handbag, High quality bags','1','images/handbag.jpg','2012-12-02 18:30:00',1),(5,'IT000005',1,'Acer Aspire AM1860 Desktop','Acer Aspire','Dual Core 2.7Ghz,2GB,500GB HDD','1','images/desktop.jpg','2012-12-02 18:30:00',1),(6,'IT000006',1,'Toyota Camry 2011 Brand new car','Toyota Camry','New Era Of Camry Sedan. Fresh and inspiring, the Toyota Camry shapes a new global quality standard for mid-size sedans. Its dynamic physical presence and exhilarating performance stimulate your desire to drive.','1','images/car.jpg','2012-12-02 18:30:00',1),(7,'IT000007',1,'Apple iPad (fourth-generation)','Apple iPad','The latest iPad adds several tweaks and improvements to secure its position at the top of the tablet heap.','1','images/ipad.jpg','2012-12-02 18:30:00',1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listing`
--

DROP TABLE IF EXISTS `listing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) NOT NULL,
  `listing_time_id` int(11) NOT NULL,
  `category_id` bigint(20) NOT NULL,
  `type` int(11) NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `starting_price` decimal(20,2) DEFAULT '0.00',
  `quantity` smallint(6) NOT NULL,
  `quantity_remain` int(11) NOT NULL,
  `start_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `end_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `status` tinyint(4) NOT NULL,
  `payment_instructions` text NOT NULL,
  `shipping_cost` decimal(20,2) NOT NULL,
  `shipping_instructions` text NOT NULL,
  `return_instructions` text NOT NULL,
  `item_location` char(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `listing_time_id` (`listing_time_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `listing_ibfk_7` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `listing_ibfk_8` FOREIGN KEY (`listing_time_id`) REFERENCES `listing_time` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `listing_ibfk_9` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listing`
--

LOCK TABLES `listing` WRITE;
/*!40000 ALTER TABLE `listing` DISABLE KEYS */;
INSERT INTO `listing` VALUES (1,1,6,1,1,50000.00,45000.00,1,1,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card, Money Order',1000.00,'Delivery with in 7 days','No return','Colombo'),(2,2,6,1,1,80000.00,70000.00,2,2,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card, Money Order',1000.00,'Delivery with in 7 days','No return','Colombo'),(3,3,6,1,1,65000.00,55000.00,1,1,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card, Money Order',1000.00,'Delivery with in 7 days','No return','Colombo'),(4,4,6,2,1,15000.00,10000.00,1,1,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card, Money Order',1000.00,'Delivery with in 7 days','No return','Colombo'),(5,5,6,1,1,30000.00,20000.00,1,1,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card, Money Order',1000.00,'Delivery with in 7 days','No return','Colombo'),(6,6,6,4,1,2500000.00,1500000.00,1,1,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card',1000.00,'Delivery with in 7 days','No return','Colombo'),(7,7,6,1,1,100000.00,90000.00,1,1,'2012-12-03 07:00:00','2012-12-27 07:00:00',1,'CeyPay, Bank, Credit card',1000.00,'Delivery with in 7 days','No return','Colombo');
/*!40000 ALTER TABLE `listing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listing_time`
--

DROP TABLE IF EXISTS `listing_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listing_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `time` float NOT NULL,
  `enabled` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listing_time`
--

LOCK TABLES `listing_time` WRITE;
/*!40000 ALTER TABLE `listing_time` DISABLE KEYS */;
INSERT INTO `listing_time` VALUES (1,'3 Day',3,1),(2,'7 Day',7,1),(3,'6 Hours',0.25,1),(6,'14 Days',14,1),(7,'1 Day',1,1);
/*!40000 ALTER TABLE `listing_time` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `transaction_id` char(16) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `user_to_id` bigint(20) NOT NULL,
  `listing_id` bigint(20) NOT NULL,
  `type` tinyint(4) NOT NULL,
  `amount` decimal(20,2) DEFAULT '0.00',
  `status` tinyint(4) NOT NULL,
  `purchase_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `payment_date` datetime DEFAULT '0000-00-00 00:00:00',
  `payment_receive_date` datetime DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `payment_user_id` (`user_id`),
  KEY `payment_listing_id` (`listing_id`),
  KEY `user_to_id` (`user_to_id`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`user_to_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION,
  CONSTRAINT `payment_listing_id` FOREIGN KEY (`listing_id`) REFERENCES `listing` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `payment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payment_id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `shipped_date` date DEFAULT NULL,
  `is_shipped` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `payment_id` (`payment_id`),
  CONSTRAINT `purchase_ibfk_4` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (-2,'ROLE_USER','Default role for all Users'),(-1,'ROLE_ADMIN','Administrator role (can edit Users)'),(1,'ROLE_BUYER',NULL),(2,'ROLE_SELLER',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `address_line1` varchar(255) NOT NULL,
  `address_line2` varchar(255) NOT NULL,
  `address_line3` varchar(255) NOT NULL,
  `gender` char(1) NOT NULL,
  `dob` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `mobile_no` varchar(12) NOT NULL,
  `land_no` varchar(12) DEFAULT NULL,
  `secret_question` varchar(255) DEFAULT NULL,
  `secret_answer` varchar(255) DEFAULT NULL,
  `account_enabled` tinyint(1) DEFAULT '0',
  `version` int(11) DEFAULT '0',
  `verification_code` int(11) DEFAULT '0',
  `join_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `account_expired` tinyint(1) DEFAULT '0',
  `account_locked` tinyint(1) DEFAULT '0',
  `credentials_expired` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'indikasampath2000','260ca9dd8a4577fc00b7bd5810298076','Indika','Sampath','indikasampath2000@yahoo.com','B1/3, Sucharitha Mawatha','Colombo 12','Colombo','M','1986-03-07 00:00:00','0716424744',NULL,'What is the brand of your phone?','Android',1,1,123,'2012-08-04 00:00:00',0,0,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `user_role_user_id` (`user_id`),
  KEY `user_role_role_id` (`role_id`),
  CONSTRAINT `user_role_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_role_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,-2),(1,-1),(1,1),(1,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-12-13 23:13:07
