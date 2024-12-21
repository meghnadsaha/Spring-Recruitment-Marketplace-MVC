CREATE DATABASE  IF NOT EXISTS `unihyr` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `unihyr`;
-- MySQL dump 10.13  Distrib 8.0.40, for macos14 (arm64)
--
-- Host: localhost    Database: unihyr
-- ------------------------------------------------------
-- Server version	9.1.0

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
-- Table structure for table `billingdetails`
--

DROP TABLE IF EXISTS `billingdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `billingdetails` (
  `billId` int NOT NULL AUTO_INCREMENT,
  `adminPaidStatus` bit(1) NOT NULL,
  `billableCTC` double NOT NULL,
  `candidatePerson` varchar(255) DEFAULT NULL,
  `clientAddress` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) NOT NULL,
  `clientName` varchar(255) NOT NULL,
  `clientPaidStatus` bit(1) NOT NULL,
  `consInvoicePath` varchar(255) DEFAULT NULL,
  `consultantId` varchar(255) NOT NULL,
  `consultantName` varchar(255) NOT NULL,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `expectedJoiningDate` datetime DEFAULT NULL,
  `fee` double NOT NULL,
  `feePercentForClient` double NOT NULL,
  `feePercentToAdmin` double NOT NULL,
  `invoicePath` varchar(255) DEFAULT NULL,
  `joiningDate` datetime DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `offerAcceptedDate` datetime NOT NULL,
  `paidDate` datetime DEFAULT NULL,
  `paymentDueDateForAd` datetime DEFAULT NULL,
  `paymentDueDateForCo` datetime DEFAULT NULL,
  `position` varchar(255) NOT NULL,
  `postId` bigint NOT NULL,
  `postProfileId` bigint NOT NULL,
  `submittedDate` datetime NOT NULL,
  `tax` double NOT NULL,
  `totalAmount` double NOT NULL,
  `totalCTC` double NOT NULL,
  `verificationStatus` bit(1) DEFAULT NULL,
  `ppid` bigint NOT NULL,
  PRIMARY KEY (`billId`),
  KEY `FK_o67e889i3fg2bx3e5lurfgce2` (`ppid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `billingdetails`
--

LOCK TABLES `billingdetails` WRITE;
/*!40000 ALTER TABLE `billingdetails` DISABLE KEYS */;
/*!40000 ALTER TABLE `billingdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `candidateprofile`
--

DROP TABLE IF EXISTS `candidateprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidateprofile` (
  `profileId` bigint NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) NOT NULL,
  `countryCode` varchar(255) DEFAULT NULL,
  `ctcComments` varchar(255) DEFAULT NULL,
  `currentCTC` double DEFAULT NULL,
  `currentLocation` varchar(255) DEFAULT NULL,
  `currentOrganization` varchar(255) NOT NULL,
  `currentRole` varchar(255) NOT NULL,
  `date` datetime DEFAULT NULL,
  `dateofbirth` varchar(255) DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `expectedCTC` double DEFAULT NULL,
  `experience` double DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `noticePeriod` int DEFAULT NULL,
  `preferredLocation` varchar(255) DEFAULT NULL,
  `published` datetime DEFAULT NULL,
  `qualification_pg` varchar(255) DEFAULT NULL,
  `qualification_ug` varchar(255) DEFAULT NULL,
  `resumePath` varchar(255) DEFAULT NULL,
  `resumeText` longtext,
  `screeningNote` longtext,
  `willingToRelocate` varchar(255) NOT NULL,
  `consultantId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`profileId`),
  KEY `FK_t2oml92s166ejqf272en1r8o3` (`consultantId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidateprofile`
--

LOCK TABLES `candidateprofile` WRITE;
/*!40000 ALTER TABLE `candidateprofile` DISABLE KEYS */;
INSERT INTO `candidateprofile` VALUES (1,'9876543210','91','Developer',22,'Delhi','Developer','Developer','2024-12-22 03:56:55','2024-12-09',NULL,'AditiSrivastava@example.com',23,22,'Aditi Srivastava',0,'Any Location','2024-12-22 03:56:55','Master of Arts in History','Bachelor of Science in Nursing','c2a7fe18-aca4-49d8-8b6c-5a0a73838a32Aditi-Srivastava_RESUME1.docx','','Developer','Yes','cons_user@example.com');
/*!40000 ALTER TABLE `candidateprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configvarialbes`
--

DROP TABLE IF EXISTS `configvarialbes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `configvarialbes` (
  `configId` int NOT NULL AUTO_INCREMENT,
  `modificationDate` datetime DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `varName` varchar(255) DEFAULT NULL,
  `varValue` longtext,
  PRIMARY KEY (`configId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configvarialbes`
--

LOCK TABLES `configvarialbes` WRITE;
/*!40000 ALTER TABLE `configvarialbes` DISABLE KEYS */;
/*!40000 ALTER TABLE `configvarialbes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contactus`
--

DROP TABLE IF EXISTS `contactus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contactus` (
  `contactusid` bigint NOT NULL AUTO_INCREMENT,
  `company` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `msgDate` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `seenDate` datetime DEFAULT NULL,
  `usertype` varchar(255) NOT NULL,
  PRIMARY KEY (`contactusid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contactus`
--

LOCK TABLES `contactus` WRITE;
/*!40000 ALTER TABLE `contactus` DISABLE KEYS */;
/*!40000 ALTER TABLE `contactus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `globalrating`
--

DROP TABLE IF EXISTS `globalrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `globalrating` (
  `sn` bigint NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `industryId` int NOT NULL,
  `industrycoverage` double NOT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `shortlistRatio` double NOT NULL,
  `turnAround` double NOT NULL,
  `consultantId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_6h5uif6h7b56v20bdn4ui02fc` (`consultantId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `globalrating`
--

LOCK TABLES `globalrating` WRITE;
/*!40000 ALTER TABLE `globalrating` DISABLE KEYS */;
/*!40000 ALTER TABLE `globalrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `globalratingpercentile`
--

DROP TABLE IF EXISTS `globalratingpercentile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `globalratingpercentile` (
  `sn` bigint NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `industryId` int NOT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `offerDrop` int DEFAULT NULL,
  `offerJoin` int DEFAULT NULL,
  `percentile` double DEFAULT NULL,
  `percentileCl` double DEFAULT NULL,
  `percentileInC` double DEFAULT NULL,
  `percentileOd` double DEFAULT NULL,
  `percentileSh` double DEFAULT NULL,
  `percentileTr` double DEFAULT NULL,
  `consultantId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_nq6l6kcyeigfqpoypm5qlc37m` (`consultantId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `globalratingpercentile`
--

LOCK TABLES `globalratingpercentile` WRITE;
/*!40000 ALTER TABLE `globalratingpercentile` DISABLE KEYS */;
INSERT INTO `globalratingpercentile` VALUES (1,'2024-12-20 16:17:12',NULL,8,'2024-12-20 16:17:12',0,0,0,0,0,0,0,0,'contact@xyzconsultancy.com'),(2,'2024-12-22 00:51:46',NULL,8,'2024-12-22 00:51:46',0,0,0,5,5,5,5,5,'cons_user@example.com'),(3,'2024-12-22 00:57:54',NULL,8,'2024-12-22 00:57:54',0,0,0,0,0,0,0,0,'consumer_manager@example.com');
/*!40000 ALTER TABLE `globalratingpercentile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `helpdesk`
--

DROP TABLE IF EXISTS `helpdesk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `helpdesk` (
  `hdid` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `msgDate` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `seenDate` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`hdid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `helpdesk`
--

LOCK TABLES `helpdesk` WRITE;
/*!40000 ALTER TABLE `helpdesk` DISABLE KEYS */;
/*!40000 ALTER TABLE `helpdesk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inbox`
--

DROP TABLE IF EXISTS `inbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inbox` (
  `inboxId` bigint NOT NULL AUTO_INCREMENT,
  `client` varchar(255) DEFAULT NULL,
  `consultant` varchar(255) DEFAULT NULL,
  `createDate` datetime NOT NULL,
  `isViewed` bit(1) NOT NULL,
  `message` longtext NOT NULL,
  `ppid` bigint NOT NULL,
  PRIMARY KEY (`inboxId`),
  KEY `FK_i4mv31fmbm179dp40j9mdnfqf` (`ppid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inbox`
--

LOCK TABLES `inbox` WRITE;
/*!40000 ALTER TABLE `inbox` DISABLE KEYS */;
/*!40000 ALTER TABLE `inbox` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `industry`
--

DROP TABLE IF EXISTS `industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `industry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `industry` varchar(255) NOT NULL,
  `lastModifier` varchar(255) DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `userid` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2kakuuaut6opkniy7a31yvviv` (`industry`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `industry`
--

LOCK TABLES `industry` WRITE;
/*!40000 ALTER TABLE `industry` DISABLE KEYS */;
INSERT INTO `industry` VALUES (1,'2024-12-20 10:00:00',NULL,'Information Technology','admin','2024-12-20 10:00:00','user123'),(2,'2024-12-20 10:05:00',NULL,'Healthcare','admin','2024-12-20 10:05:00','user124'),(3,'2024-12-20 10:10:00',NULL,'Finance','admin','2024-12-20 10:10:00','user125'),(4,'2024-12-20 10:15:00',NULL,'Manufacturing','admin','2024-12-20 10:15:00','user126'),(5,'2024-12-20 10:20:00',NULL,'Education','admin','2024-12-20 10:20:00','user127'),(6,'2024-12-20 10:25:00',NULL,'Retail','admin','2024-12-20 10:25:00','user128'),(7,'2024-12-20 10:30:00',NULL,'Telecommunications','admin','2024-12-20 10:30:00','user129'),(8,'2024-12-20 10:35:00',NULL,'Construction','admin','2024-12-20 10:35:00','user130');
/*!40000 ALTER TABLE `industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `localrating`
--

DROP TABLE IF EXISTS `localrating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `localrating` (
  `sn` bigint NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `industryId` int NOT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `ratingParamValue` varchar(255) NOT NULL,
  `status` bit(1) NOT NULL,
  `postId` bigint DEFAULT NULL,
  `ratingParamId` int DEFAULT NULL,
  `consultantId` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_1mcc8t037d2xrd0oxe0a08ajq` (`ratingParamId`),
  KEY `FK_hy3vtpfpncgoaw4lytnxf36wl` (`consultantId`),
  KEY `FK_5mc04tladyx1lv4mgea3ovxnw` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `localrating`
--

LOCK TABLES `localrating` WRITE;
/*!40000 ALTER TABLE `localrating` DISABLE KEYS */;
/*!40000 ALTER TABLE `localrating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `lid` int NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logininfo`
--

DROP TABLE IF EXISTS `logininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logininfo` (
  `lid` int NOT NULL AUTO_INCREMENT,
  `forgotpwdid` varchar(255) DEFAULT NULL,
  `isLogin` bit(1) DEFAULT NULL,
  `isactive` varchar(255) NOT NULL,
  `login_date` datetime DEFAULT NULL,
  `logout_date` datetime DEFAULT NULL,
  `modification_date` datetime DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  PRIMARY KEY (`lid`),
  UNIQUE KEY `UK_896wc63te16rs2f433gk0u5yf` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logininfo`
--

LOCK TABLES `logininfo` WRITE;
/*!40000 ALTER TABLE `logininfo` DISABLE KEYS */;
INSERT INTO `logininfo` VALUES (1,NULL,NULL,'false',NULL,NULL,NULL,'$2a$10$fUVqlQgLxITIdzP67hsiZeL/FkcCc.v42Jm2/UksUwrrwnpr85S4G','johndoe@example.com'),(2,NULL,_binary '\0','false',NULL,'2024-12-22 00:38:40',NULL,'$2a$10$l.Bq6RNh3L1wRjE3KFY0MebFvHFcczJpnAo3Qm9J37hdro6vqk82a','contact@xyzconsultancy.com'),(3,NULL,_binary '','true','2024-12-22 00:40:51',NULL,NULL,'$2a$10$.Iu2mJeaINMLeR3TGfP8kOlwGKrtH3Ovk1IYlAKT6ZgHCW/8MdInq','johndoe@example.com1'),(4,NULL,NULL,'false',NULL,NULL,NULL,'$2a$10$gQNFHkPK6A1mNmOZSAtwreWNwxjmaSOkRC0ZKi5/3cykAnjYroMuy','rohitsharma@gmail.com'),(5,NULL,_binary '','true','2024-12-22 04:08:16',NULL,NULL,'$2a$10$f1AvpmduIU/qg0m3sXwk0.4rRhGt4XSr7hYFcNGuFmHW1DuEBJeOS','admin@example.com'),(6,NULL,_binary '\0','true',NULL,'2024-12-22 04:09:41',NULL,'$2a$10$97oZSKtWmk1mPmk6Ctp3ieqzhvyCYs5iThc9Zge7PPrpXFDHoVxPW','cons_user@example.com'),(7,NULL,_binary '\0','true',NULL,'2024-12-22 04:10:01',NULL,'$2a$10$yxyPhbQkXVyG6aGGaQaIguaLxFtw2P4c3eZGN7apkdwrYRBb.xrEe','consumer_manager@example.com'),(8,NULL,_binary '\0','true',NULL,'2024-12-22 04:00:16',NULL,'$2a$10$Y0ISeAIRQCxZiQxof8jOw.3CGzLNy9NZ3y0qZjvJVi5Ek6xqrzpYa','employee_user@example.com'),(9,NULL,_binary '\0','true',NULL,'2024-12-22 04:08:11',NULL,'$2a$10$8H0IsS5SYR1GHMJ7jDx8Ze/9zSglToxrOqs9ABGDuXaRfKXuOrr5e','employee_manager@example.com');
/*!40000 ALTER TABLE `logininfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `notificationId` int NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `notification` varchar(255) DEFAULT NULL,
  `readStatus` bit(1) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notificationId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (1,'2024-12-20 22:40:42','New job position <a href=\'consviewjd?pid=2\' >Java Technical Lead/ Lead Java Backend Developer</a> has been added',_binary '','contact@xyzconsultancy.com'),(2,'2024-12-20 23:04:20','New job position <a href=\'consviewjd?pid=3\' >Site Reliability Engineer ( SRE )</a> has been added',_binary '','contact@xyzconsultancy.com'),(3,'2024-12-22 02:00:36','New job position <a href=\'consviewjd?pid=6\' >Physician-Ramaiah Memorial Hospital-3</a> has been added',_binary '\0','contact@xyzconsultancy.com'),(4,'2024-12-22 02:00:36','New job position <a href=\'consviewjd?pid=6\' >Physician-Ramaiah Memorial Hospital-3</a> has been added',_binary '\0','consumer_manager@example.com'),(5,'2024-12-22 04:01:07','New job position <a href=\'consviewjd?pid=9\' >Java Technical Lead2</a> has been added',_binary '\0','contact@xyzconsultancy.com'),(6,'2024-12-22 04:01:07','New job position <a href=\'consviewjd?pid=9\' >Java Technical Lead2</a> has been added',_binary '\0','consumer_manager@example.com'),(7,'2024-12-22 04:01:18','New job position <a href=\'consviewjd?pid=5\' >Physician-Ramaiah Memorial Hospital-2</a> has been added',_binary '\0','contact@xyzconsultancy.com'),(8,'2024-12-22 04:01:18','New job position <a href=\'consviewjd?pid=5\' >Physician-Ramaiah Memorial Hospital-2</a> has been added',_binary '\0','consumer_manager@example.com'),(9,'2024-12-22 04:01:20','New job position <a href=\'consviewjd?pid=8\' >Software Engineer1</a> has been added',_binary '\0','contact@xyzconsultancy.com'),(10,'2024-12-22 04:01:20','New job position <a href=\'consviewjd?pid=8\' >Software Engineer1</a> has been added',_binary '\0','consumer_manager@example.com'),(11,'2024-12-22 04:01:21','New job position <a href=\'consviewjd?pid=7\' >Physician-Ramaiah Memorial Hospital-4</a> has been added',_binary '\0','contact@xyzconsultancy.com'),(12,'2024-12-22 04:01:21','New job position <a href=\'consviewjd?pid=7\' >Physician-Ramaiah Memorial Hospital-4</a> has been added',_binary '\0','consumer_manager@example.com'),(13,'2024-12-22 04:08:34','New job position <a href=\'consviewjd?pid=11\' >Java Technical Lead/ Lead Java Backend Developer</a> has been added',_binary '\0','contact@xyzconsultancy.com'),(14,'2024-12-22 04:08:34','New job position <a href=\'consviewjd?pid=11\' >Java Technical Lead/ Lead Java Backend Developer</a> has been added',_binary '\0','consumer_manager@example.com');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persistent_logins` (
  `series` varchar(255) NOT NULL,
  `last_used` datetime NOT NULL,
  `token` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT,
  `addition_detail` longtext,
  `close_date` datetime(6) DEFAULT NULL,
  `close_request_client` varchar(255) DEFAULT NULL,
  `close_request_consultant` varchar(255) DEFAULT NULL,
  `comment` longtext,
  `create_date` datetime(6) DEFAULT NULL,
  `criteria` varchar(255) DEFAULT NULL,
  `ctc_max` double DEFAULT '0',
  `ctc_min` double DEFAULT '0',
  `delete_date` datetime(6) DEFAULT NULL,
  `edit_summary` longtext,
  `exp_max` double DEFAULT '0',
  `exp_min` double DEFAULT '0',
  `fee_percent` double DEFAULT '0',
  `function` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '0',
  `job_code` varchar(255) DEFAULT NULL,
  `join_close_date` datetime(6) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `modify_date` datetime(6) DEFAULT NULL,
  `no_of_posts` int DEFAULT '0',
  `no_of_posts_filled` int DEFAULT '0',
  `no_of_posts_joined` int DEFAULT '0',
  `open_again_date` datetime(6) DEFAULT NULL,
  `poster_id` varchar(255) DEFAULT NULL,
  `profile_par_day` int DEFAULT '0',
  `published` datetime(6) DEFAULT NULL,
  `qualification_pg` varchar(255) DEFAULT NULL,
  `qualification_ug` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `update_info` varchar(255) DEFAULT NULL,
  `uploadjd` varchar(255) DEFAULT NULL,
  `uploadjdaudio` varchar(255) DEFAULT NULL,
  `variable_pay_comment` longtext,
  `verify_date` datetime(6) DEFAULT NULL,
  `work_hour_end_hour` varchar(255) DEFAULT NULL,
  `work_hour_end_min` varchar(255) DEFAULT NULL,
  `work_hour_start_hour` varchar(255) DEFAULT NULL,
  `work_hour_start_min` varchar(255) DEFAULT NULL,
  `client_id` varchar(255) DEFAULT NULL,
  `last_modifier` varchar(255) DEFAULT NULL,
  `additionDetail` longtext,
  `closeDate` datetime DEFAULT NULL,
  `closeRequestClient` varchar(255) DEFAULT NULL,
  `closeRequestConsultant` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `editSummary` longtext,
  `feePercent` double DEFAULT '0',
  `isActive` bit(1) DEFAULT b'0',
  `jobCode` varchar(255) DEFAULT NULL,
  `joinCloseDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `noOfPosts` int DEFAULT '0',
  `noOfPostsFilled` int DEFAULT '0',
  `noOfPostsJoined` int DEFAULT '0',
  `openAgainDate` datetime DEFAULT NULL,
  `posterId` varchar(255) DEFAULT NULL,
  `profileParDay` int DEFAULT '0',
  `updateInfo` varchar(255) DEFAULT NULL,
  `variablePayComment` longtext,
  `verifyDate` datetime DEFAULT NULL,
  `workHourEndHour` varchar(255) DEFAULT NULL,
  `workHourEndMin` varchar(255) DEFAULT NULL,
  `workHourStartHour` varchar(255) DEFAULT NULL,
  `workHourStartMin` varchar(255) DEFAULT NULL,
  `clientId` varchar(255) DEFAULT NULL,
  `lastModifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FK_oo7nkko4cqwxynprkm6v7dd9n` (`clientId`),
  KEY `FK_s6yaf2kyxkii4kifj8w0erhab` (`lastModifier`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,NULL,NULL,NULL,NULL,NULL,'2024-12-20 19:59:41.000000',NULL,0,0,NULL,NULL,0,0,0,NULL,0,NULL,NULL,NULL,'2024-12-20 19:59:41.000000',0,0,0,NULL,NULL,0,'2024-12-20 19:59:41.000000',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-20 19:59:41',NULL,NULL,0,_binary '\0',NULL,NULL,'2024-12-20 19:59:41',0,0,0,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,NULL,NULL,NULL,NULL,'',NULL,NULL,1,0,NULL,NULL,1,0,0,'Individual Contributor',0,NULL,NULL,'Delhi',NULL,0,0,0,NULL,NULL,0,'2024-12-20 20:02:18.661000','PhD in Computer Science','Bachelor of Engineering','Java Technical Lead/ Lead Java Backend Developer',NULL,'eda24854-8c78-4194-b81b-d271686a4c29Jogarao-Mantha_Java-Developer_TalentOnLease.docx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'',NULL,NULL,NULL,'2024-12-20 20:02:19',NULL,NULL,0,_binary '','JOH0002',NULL,NULL,1,0,0,NULL,'johndoe@example.com1',1,NULL,'1','2024-12-20 22:40:42','11:59 PM',NULL,'00:00 AM',NULL,'johndoe@example.com1',NULL),(3,'<h3>Role: Site Reliability Engineer</h3>\r\n\r\n<p><strong>Industry Type</strong>: Investment Banking / Venture Capital / Private Equity<br />\r\n<strong>Department</strong>: Engineering - Software &amp; QA<br />\r\n<strong>Employment Type</strong>: Full Time, Permanent<br />\r\n<strong>Role Category</strong>: DevOps</p>\r\n\r\n<hr />\r\n<h3><strong>Role Description</strong></h3>\r\n\r\n<p>The Site Reliability Engineer (SRE) in this role will work within the <strong>Project Management &amp; Change Execution</strong> function. This will involve ensuring the successful transition and integration of new technologies, processes, and strategies into the organization. Key responsibilities include:</p>\r\n\r\n<ul>\r\n	<li>\r\n	<p><strong>Change Vision and Strategy</strong>: Aligning with business goals, understanding and documenting requirements for the change mandate, and facilitating a sustainable change vision in partnership with business and infrastructure teams.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Monitoring and Reporting</strong>: Managing dependencies across multiple projects, providing regular updates on project/product roadmaps, RAID status, financial data, and tracking project progress to support decision-making.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Removing Blockers and Adoption Support</strong>: Identifying obstacles to team success and working to resolve them. Ensuring end-user adoption by closely collaborating with business stakeholders.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Creating and Executing Change Artefacts</strong>: Developing change management documentation, including business cases, communication plans, and training materials. Monitoring and overseeing the execution of change processes to minimize business disruption.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Training &amp; Mentoring</strong>: Providing guidance on change management methodologies, agile processes, project methodologies, and continuous deployment practices.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Collaboration</strong>: Training on the job with teams in Mumbai and global teams in New York and Frankfurt.</p>\r\n	</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Key Responsibilities</strong></h3>\r\n\r\n<p>As a <strong>DevOps Senior Engineer</strong>, you will play a crucial role in:</p>\r\n\r\n<ul>\r\n	<li>\r\n	<p><strong>Continuous Delivery Implementation</strong>: Contributing to the software delivery pipeline, improving the overall delivery process, and automating development and release processes.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Automation &amp; Improvement</strong>: Collaborating with software engineers to ensure that development follows best practices, and optimizing CI/CD processes.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Support and Monitoring</strong>: Developing and integrating tools for build, deployment, and environment monitoring. Working closely with developers and QA teams to manage automated testing and deployments across various environments.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Troubleshooting &amp; Optimization</strong>: Identifying potential issues and optimization opportunities within the delivery pipeline and implementing effective solutions.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Collaboration with Technical Teams</strong>: Liaising with other technical teams, conducting research, and evaluating necessary software for maintaining development environments.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Routine Maintenance</strong>: Performing ongoing maintenance of application systems, and handling release management activities.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Technical Guidance</strong>: Providing guidance on development and operational best practices, and educating team members on key processes.</p>\r\n	</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Skills and Experience</strong></h3>\r\n\r\n<p>To be successful in this role, you should have:</p>\r\n\r\n<ul>\r\n	<li>\r\n	<p><strong>Experience with complex, mission-critical applications</strong>: Handling applications with high availability requirements (99.9% uptime).</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Proficiency in CI/CD and Release Management</strong>: Experience with industry-standard processes for build, deploy, and release management (Jenkins, GitHub Actions, etc.).</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Cloud Experience</strong>: Proficiency with cloud-based infrastructures like GCP, AWS, or Azure. Familiarity with technologies such as Kubernetes, Terraform, and Confluent Kafka.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Scripting and Automation</strong>: Strong knowledge of Shell scripting, Python, and Linux. Experience with automation tools like Ansible.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Monitoring Tools</strong>: Hands-on experience with monitoring tools such as Splunk, Grafana, Prometheus, etc.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Application and Middleware Knowledge</strong>: Understanding of web servers (Apache), application servers, load balancers, and middleware (IBM MQ, Solace, Kafka).</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Container Management</strong>: Experience with OpenShift and container orchestration.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Database Management</strong>: Experience with DBMS (e.g., Oracle, SQL) and SQL queries.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Development Tools</strong>: Familiarity with version control systems (Git, BitBucket), build tools (Maven, Gradle), and software configuration management tools (Artifactory).</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Deployment Strategies</strong>: Experience with BlueGreen, Canary, and A/B testing deployment strategies.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Agile Methodologies</strong>: Solid understanding of Agile practices and experience working in Agile environments.</p>\r\n	</li>\r\n	<li>\r\n	<p><strong>Collaboration Skills</strong>: Strong communication skills, a proactive can-do attitude, and the ability to work effectively in cross-functional teams.</p>\r\n	</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Education</strong></h3>\r\n\r\n<ul>\r\n	<li><strong>UG</strong>: Any Graduate</li>\r\n	<li><strong>PG</strong>: Any Postgraduate</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Preferred Key Skills</strong></h3>\r\n\r\n<ul>\r\n	<li><strong>Site Reliability Engineering</strong></li>\r\n	<li><strong>Java</strong></li>\r\n	<li><strong>Azure</strong></li>\r\n	<li><strong>GitHub</strong></li>\r\n	<li><strong>Apache Webserver</strong></li>\r\n	<li><strong>Kafka</strong></li>\r\n	<li><strong>Grafana</strong></li>\r\n	<li><strong>DevOps</strong></li>\r\n	<li><strong>Git</strong></li>\r\n	<li><strong>Linux</strong></li>\r\n	<li><strong>Terraform</strong></li>\r\n	<li><strong>GCP</strong></li>\r\n	<li><strong>Incident Management</strong></li>\r\n	<li><strong>Shell Scripting</strong></li>\r\n	<li><strong>CI/CD</strong></li>\r\n	<li><strong>BitBucket</strong></li>\r\n	<li><strong>Splunk</strong></li>\r\n	<li><strong>AWS</strong></li>\r\n	<li><strong>Kubernetes</strong></li>\r\n	<li><strong>Python</strong></li>\r\n</ul>\r\n\r\n<p>This role is well-suited for someone with a strong technical background, experience in cloud technologies, DevOps practices, and a passion for automation and improving delivery processes in high-stakes environments.</p>\r\n',NULL,NULL,NULL,'Job highlights\r\n\r\n    Experience with high-end complex solutions and mission-critical (9% uptime) applications\r\n    Our tech stack: GCP (GKE,CloudSQL,CloudMonitoring etc),but any other public cloud experience is relevant,Kubernetes,Terraform,Confluent Kafka,GitHub Actions,Helm\r\n    Good understanding of infrastructure and platform components: Shell scripting,Python,Linux\r\n    Your skills and experience',NULL,NULL,22,20,NULL,NULL,5,3,0,'Individual Contributor',0,NULL,NULL,'Mumbai,Hyderabad,Pune,Chennai,Kolkata',NULL,0,0,0,NULL,NULL,0,'2024-12-20 23:03:46.114000','PhD in Computer Science','Bachelor of Engineering','Site Reliability Engineer ( SRE )',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-20 23:03:46',NULL,NULL,0,_binary '','JOH0003',NULL,NULL,4,0,0,NULL,'johndoe@example.com1',4,NULL,'','2024-12-20 23:04:20','11:59 PM',NULL,'00:00 AM',NULL,'johndoe@example.com1',NULL),(4,'<h2>Job description</h2>\r\n\r\n<p>Ramaiah Memorial Hospital is looking for Physician to join our dynamic team and embark on a rewarding career journey</p>\r\n\r\n<ul>\r\n	<li>Conduct thorough medical histories and physical examinations of patients to diagnose medical conditions</li>\r\n	<li>Order and interpret diagnostic tests, such as X-rays, blood tests, and MRI scans, to aid in the diagnosis of medical conditions</li>\r\n	<li>Develop treatment plans for patients, which may include medications, lifestyle changes, or surgical procedures</li>\r\n	<li>Educate patients about their medical conditions and treatment plans, as well as preventive care</li>\r\n	<li>Work with other medical professionals, such as nurses and specialists, to coordinate patient care</li>\r\n	<li>Maintain accurate medical records and documentation for each patient</li>\r\n</ul>\r\n\r\n<p>Role: <a href=\"https://www.naukri.com/critical-care-physician-jobs\" target=\"_blank\">Critical Care Physician</a></p>\r\n\r\n<p>Industry Type: <a href=\"https://www.naukri.com/medical-services-hospital-jobs\" target=\"_blank\">Medical Services / Hospital</a></p>\r\n\r\n<p>Department: <a href=\"https://www.naukri.com/healthcare-life-sciences-jobs\" target=\"_blank\">Healthcare &amp; Life Sciences</a></p>\r\n\r\n<p>Employment Type: Full Time, Permanent</p>\r\n\r\n<p>Role Category: Doctor</p>\r\n\r\n<p>Education</p>\r\n\r\n<p>UG: Any Graduate</p>\r\n\r\n<p>PG: Any Postgraduate</p>\r\n',NULL,NULL,NULL,'Key Skills\r\nmedical writingmrihealth care servicescritical careclinical data managementmedicinenabhhospital administrationpatient carehealthcaremedical devicesclinical researchmedical transcriptionmedical recordscasualtymdtdoctor activities\r\n',NULL,NULL,20,10,NULL,NULL,20,10,0,'Individual Contributor',0,NULL,NULL,'Delhi',NULL,0,0,0,NULL,NULL,0,NULL,'Any Post Graduation','Bachelor of Science','Physician-Ramaiah Memorial Hospital',NULL,'51f9918e-e04c-4c38-be69-2d66acd2bca8.pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:10:50','2024-12-22 01:40:51',NULL,14,_binary '\0',NULL,NULL,NULL,1,0,0,NULL,'employee_user@example.com',11,NULL,'','2024-12-22 01:12:53','11:59 PM',NULL,'00:00 AM',NULL,'employee_user@example.com',NULL),(5,'',NULL,NULL,NULL,'NA',NULL,NULL,20,10,NULL,NULL,20,10,0,'Individual Contributor',0,NULL,NULL,'Mumbai',NULL,0,0,0,NULL,NULL,0,NULL,'Master of Arts','Bachelor of Engineering','Physician-Ramaiah Memorial Hospital-2',NULL,'37b43118-a180-4a79-9a74-3cff4044a770Aditi-Srivastava_RESUME1.docx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:50:49','2024-12-22 04:06:33',NULL,14,_binary '\0',NULL,NULL,NULL,12,0,0,NULL,'employee_manager@example.com',10,NULL,'NA','2024-12-22 04:01:18','11:59 PM',NULL,'00:00 AM',NULL,'employee_manager@example.com',NULL),(6,'',NULL,NULL,NULL,'NA',NULL,NULL,30,20,NULL,NULL,20,10,0,'Individual Contributor',0,NULL,NULL,'Pune',NULL,0,0,0,NULL,NULL,0,NULL,'PhD in Computer Science','Bachelor of Engineering','Physician-Ramaiah Memorial Hospital-3',NULL,'f350dd19-d31d-4115-b4c9-d2eea805f4abAditi-Srivastava_RESUME1.docx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:28:19','2024-12-22 04:06:43',NULL,12,_binary '\0',NULL,NULL,NULL,10,0,0,NULL,'employee_manager@example.com',12,NULL,'NA','2024-12-22 02:00:36','11:59 PM',NULL,'00:00 AM',NULL,'employee_manager@example.com',NULL),(7,'<p>Here&#39;s a clear and structured solution to address the <code>LazyInitializationException</code>. Follow these steps:</p>\r\n\r\n<hr />\r\n<h3><strong>Solution: Using <code>@Transactional</code></strong></h3>\r\n\r\n<p>Ensure that the method accessing the lazy collection runs within an active Hibernate session by marking the method with <code>@Transactional</code>.</p>\r\n\r\n<p>Example:</p>\r\n\r\n<pre>\r\n<code>@Service\r\npublic class RegistrationService {\r\n\r\n    @Autowired\r\n    private RegistrationRepository registrationRepository;\r\n\r\n    @Transactional\r\n    public Registration getRegistrationWithPostConsultants(Long id) {\r\n        // Fetch the Registration entity, including lazy-loaded collections\r\n        Registration registration = registrationRepository.findById(id)\r\n            .orElseThrow(() -&gt; new EntityNotFoundException(&quot;Registration not found&quot;));\r\n\r\n        // Access the lazy-loaded collection to initialize it\r\n        registration.getPostConsultants().size(); // Forces initialization\r\n\r\n        return registration;\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p><strong>Key Points:</strong></p>\r\n\r\n<ul>\r\n	<li><code>@Transactional</code> ensures that the Hibernate session remains open during the method execution.</li>\r\n	<li>Accessing the lazy collection (<code>getPostConsultants()</code>) ensures it is loaded.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Solution: Using JPQL with <code>JOIN FETCH</code></strong></h3>\r\n\r\n<p>Fetch the parent entity and its lazy-loaded collection in a single query using JPQL.</p>\r\n\r\n<p>Example:</p>\r\n\r\n<pre>\r\n<code>@Repository\r\npublic interface RegistrationRepository extends JpaRepository&lt;Registration, Long&gt; {\r\n\r\n    @Query(&quot;SELECT r FROM Registration r JOIN FETCH r.postConsultants WHERE r.id = :id&quot;)\r\n    Registration findWithPostConsultants(@Param(&quot;id&quot;) Long id);\r\n}\r\n</code></pre>\r\n\r\n<p>Service Layer:</p>\r\n\r\n<pre>\r\n<code>@Service\r\npublic class RegistrationService {\r\n\r\n    @Autowired\r\n    private RegistrationRepository registrationRepository;\r\n\r\n    public Registration getRegistrationWithPostConsultants(Long id) {\r\n        return registrationRepository.findWithPostConsultants(id);\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p><strong>Key Points:</strong></p>\r\n\r\n<ul>\r\n	<li><code>JOIN FETCH</code> ensures the <code>postConsultants</code> collection is loaded eagerly for this query only.</li>\r\n	<li>Avoids the need to access the collection explicitly to initialize it.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Solution: Use DTOs for API Responses</strong></h3>\r\n\r\n<p>For APIs, fetch only the required data and map it to a Data Transfer Object (DTO) to avoid exposing the lazy-loaded collections directly.</p>\r\n\r\n<p>Example:</p>\r\n\r\n<pre>\r\n<code>public class RegistrationDTO {\r\n    private Long id;\r\n    private String name;\r\n    private List&lt;String&gt; postConsultantNames;\r\n\r\n    // Constructor\r\n    public RegistrationDTO(Registration registration) {\r\n        this.id = registration.getId();\r\n        this.name = registration.getName();\r\n        this.postConsultantNames = registration.getPostConsultants()\r\n                                               .stream()\r\n                                               .map(PostConsultants::getName)\r\n                                               .collect(Collectors.toList());\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p>Service Layer:</p>\r\n\r\n<pre>\r\n<code>@Service\r\npublic class RegistrationService {\r\n\r\n    @Autowired\r\n    private RegistrationRepository registrationRepository;\r\n\r\n    @Transactional\r\n    public RegistrationDTO getRegistrationDTO(Long id) {\r\n        Registration registration = registrationRepository.findById(id)\r\n            .orElseThrow(() -&gt; new EntityNotFoundException(&quot;Registration not found&quot;));\r\n\r\n        return new RegistrationDTO(registration);\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<hr />\r\n<h3><strong>Solution: Configure Fetch Type</strong></h3>\r\n\r\n<p>If the collection is always needed, consider setting <code>fetch = FetchType.EAGER</code>.</p>\r\n\r\n<p>Entity Mapping:</p>\r\n\r\n<pre>\r\n<code>@OneToMany(mappedBy = &quot;registration&quot;, fetch = FetchType.EAGER)\r\nprivate List&lt;PostConsultants&gt; postConsultants;\r\n</code></pre>\r\n\r\n<p><strong>Key Points:</strong></p>\r\n\r\n<ul>\r\n	<li>Eager loading ensures the collection is always loaded with the parent entity.</li>\r\n	<li>Use this approach carefully as it can impact performance.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Recommended Approach</strong></h3>\r\n\r\n<p>For most scenarios:</p>\r\n\r\n<ol>\r\n	<li>Use <strong><code>@Transactional</code></strong> for service methods.</li>\r\n	<li>Use <strong><code>JOIN FETCH</code></strong> for specific queries where collections are required.</li>\r\n	<li>Use DTOs for returning data in APIs, avoiding exposing Hibernate entities directly.</li>\r\n</ol>\r\n\r\n<p>These practices ensure efficient data access while maintaining good performance and preventing lazy initialization issues.</p>\r\n',NULL,NULL,NULL,'NA',NULL,NULL,10,1,NULL,NULL,2,1,0,'Individual Contributor',0,NULL,NULL,'Mumbai',NULL,0,0,0,NULL,NULL,0,NULL,'PhD in Computer Science','Bachelor of Engineering','Physician-Ramaiah Memorial Hospital-4',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:33:02','2024-12-22 04:06:40',NULL,11,_binary '\0',NULL,NULL,NULL,10,0,0,NULL,'employee_manager@example.com',10,NULL,'1','2024-12-22 04:01:21','11:59 PM',NULL,'00:00 AM',NULL,'employee_manager@example.com',NULL),(8,'<p>1</p>\r\n',NULL,NULL,NULL,'1',NULL,NULL,11,1,NULL,NULL,11,1,0,'Individual Contributor',0,NULL,NULL,'Hyderabad',NULL,0,0,0,NULL,NULL,0,NULL,'Master of Arts','Bachelor of Science','Software Engineer1',NULL,'3d196b56-cfe4-410d-b5b1-52ada73ceec1.pdf',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:34:20','2024-12-22 04:06:37',NULL,11,_binary '\0',NULL,NULL,NULL,1,0,0,NULL,'employee_manager@example.com',1,NULL,'11','2024-12-22 04:01:20','11:59 PM',NULL,'00:00 AM',NULL,'employee_manager@example.com',NULL),(9,'',NULL,NULL,NULL,'2',NULL,NULL,23,22,NULL,NULL,22,2,0,'Individual Contributor',0,NULL,NULL,'Pune',NULL,0,0,0,NULL,NULL,0,NULL,'Master of Arts','Bachelor of Arts','Java Technical Lead2',NULL,'ad080824-c6cd-4f05-ba4c-5a237e9a9937Aditi-Srivastava_RESUME1.docx',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:36:28','2024-12-22 04:06:35',NULL,12,_binary '\0',NULL,NULL,NULL,2,0,0,NULL,'employee_manager@example.com',2,NULL,'22','2024-12-22 04:01:07','11:59 PM',NULL,'00:00 AM',NULL,'employee_manager@example.com',NULL),(10,'<p>Here&#39;s a clear and structured solution to address the <code>LazyInitializationException</code>. Follow these steps:</p>\r\n\r\n<hr />\r\n<h3><strong>Solution: Using <code>@Transactional</code></strong></h3>\r\n\r\n<p>Ensure that the method accessing the lazy collection runs within an active Hibernate session by marking the method with <code>@Transactional</code>.</p>\r\n\r\n<p>Example:</p>\r\n\r\n<pre>\r\n<code>@Service\r\npublic class RegistrationService {\r\n\r\n    @Autowired\r\n    private RegistrationRepository registrationRepository;\r\n\r\n    @Transactional\r\n    public Registration getRegistrationWithPostConsultants(Long id) {\r\n        // Fetch the Registration entity, including lazy-loaded collections\r\n        Registration registration = registrationRepository.findById(id)\r\n            .orElseThrow(() -&gt; new EntityNotFoundException(&quot;Registration not found&quot;));\r\n\r\n        // Access the lazy-loaded collection to initialize it\r\n        registration.getPostConsultants().size(); // Forces initialization\r\n\r\n        return registration;\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p><strong>Key Points:</strong></p>\r\n\r\n<ul>\r\n	<li><code>@Transactional</code> ensures that the Hibernate session remains open during the method execution.</li>\r\n	<li>Accessing the lazy collection (<code>getPostConsultants()</code>) ensures it is loaded.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Solution: Using JPQL with <code>JOIN FETCH</code></strong></h3>\r\n\r\n<p>Fetch the parent entity and its lazy-loaded collection in a single query using JPQL.</p>\r\n\r\n<p>Example:</p>\r\n\r\n<pre>\r\n<code>@Repository\r\npublic interface RegistrationRepository extends JpaRepository&lt;Registration, Long&gt; {\r\n\r\n    @Query(&quot;SELECT r FROM Registration r JOIN FETCH r.postConsultants WHERE r.id = :id&quot;)\r\n    Registration findWithPostConsultants(@Param(&quot;id&quot;) Long id);\r\n}\r\n</code></pre>\r\n\r\n<p>Service Layer:</p>\r\n\r\n<pre>\r\n<code>@Service\r\npublic class RegistrationService {\r\n\r\n    @Autowired\r\n    private RegistrationRepository registrationRepository;\r\n\r\n    public Registration getRegistrationWithPostConsultants(Long id) {\r\n        return registrationRepository.findWithPostConsultants(id);\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p><strong>Key Points:</strong></p>\r\n\r\n<ul>\r\n	<li><code>JOIN FETCH</code> ensures the <code>postConsultants</code> collection is loaded eagerly for this query only.</li>\r\n	<li>Avoids the need to access the collection explicitly to initialize it.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Solution: Use DTOs for API Responses</strong></h3>\r\n\r\n<p>For APIs, fetch only the required data and map it to a Data Transfer Object (DTO) to avoid exposing the lazy-loaded collections directly.</p>\r\n\r\n<p>Example:</p>\r\n\r\n<pre>\r\n<code>public class RegistrationDTO {\r\n    private Long id;\r\n    private String name;\r\n    private List&lt;String&gt; postConsultantNames;\r\n\r\n    // Constructor\r\n    public RegistrationDTO(Registration registration) {\r\n        this.id = registration.getId();\r\n        this.name = registration.getName();\r\n        this.postConsultantNames = registration.getPostConsultants()\r\n                                               .stream()\r\n                                               .map(PostConsultants::getName)\r\n                                               .collect(Collectors.toList());\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<p>Service Layer:</p>\r\n\r\n<pre>\r\n<code>@Service\r\npublic class RegistrationService {\r\n\r\n    @Autowired\r\n    private RegistrationRepository registrationRepository;\r\n\r\n    @Transactional\r\n    public RegistrationDTO getRegistrationDTO(Long id) {\r\n        Registration registration = registrationRepository.findById(id)\r\n            .orElseThrow(() -&gt; new EntityNotFoundException(&quot;Registration not found&quot;));\r\n\r\n        return new RegistrationDTO(registration);\r\n    }\r\n}\r\n</code></pre>\r\n\r\n<hr />\r\n<h3><strong>Solution: Configure Fetch Type</strong></h3>\r\n\r\n<p>If the collection is always needed, consider setting <code>fetch = FetchType.EAGER</code>.</p>\r\n\r\n<p>Entity Mapping:</p>\r\n\r\n<pre>\r\n<code>@OneToMany(mappedBy = &quot;registration&quot;, fetch = FetchType.EAGER)\r\nprivate List&lt;PostConsultants&gt; postConsultants;\r\n</code></pre>\r\n\r\n<p><strong>Key Points:</strong></p>\r\n\r\n<ul>\r\n	<li>Eager loading ensures the collection is always loaded with the parent entity.</li>\r\n	<li>Use this approach carefully as it can impact performance.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3><strong>Recommended Approach</strong></h3>\r\n\r\n<p>For most scenarios:</p>\r\n\r\n<ol>\r\n	<li>Use <strong><code>@Transactional</code></strong> for service methods.</li>\r\n	<li>Use <strong><code>JOIN FETCH</code></strong> for specific queries where collections are required.</li>\r\n	<li>Use DTOs for returning data in APIs, avoiding exposing Hibernate entities directly.</li>\r\n</ol>\r\n\r\n<p>These practices ensure efficient data access while maintaining good performance and preventing lazy initialization issues.</p>\r\n',NULL,NULL,NULL,'NA',NULL,NULL,33,22,NULL,NULL,5,1,0,'Individual Contributor',0,NULL,NULL,'Pune,Chennai',NULL,0,0,0,NULL,NULL,0,'2024-12-22 01:42:12.061000','Master of Arts','Bachelor of Arts','Aws Devops Engineer(SRE)',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 01:42:12',NULL,NULL,12,_binary '','EMP0010',NULL,NULL,4,0,0,NULL,'employee_user@example.com',2,NULL,'na','2024-12-22 01:42:30','11:59 PM',NULL,'00:00 AM',NULL,'employee_user@example.com',NULL),(11,'<p>Here is a sample job description for a <strong>Java Technical Lead / Lead Java Backend Developer</strong>:</p>\r\n\r\n<hr />\r\n<h3>Job Title: Java Technical Lead / Lead Java Backend Developer</h3>\r\n\r\n<p>Location: [Insert Location]</p>\r\n\r\n<p>Department: Engineering / Technology</p>\r\n\r\n<p>Reports To: [Manager Title]</p>\r\n\r\n<hr />\r\n<h3>Job Overview:</h3>\r\n\r\n<p>We are looking for an experienced <strong>Java Technical Lead / Lead Java Backend Developer</strong> to join our dynamic engineering team. In this role, you will be responsible for designing, developing, and maintaining scalable backend systems, guiding a team of engineers, and ensuring that the technical solutions align with our business goals. You will also be expected to drive best practices, perform code reviews, and mentor junior developers.</p>\r\n\r\n<hr />\r\n<h3>Key Responsibilities:</h3>\r\n\r\n<ul>\r\n	<li><strong>Technical Leadership:</strong> Lead a team of backend developers and provide technical direction, ensuring the delivery of high-quality, scalable, and reliable Java applications.</li>\r\n	<li><strong>System Design &amp; Architecture:</strong> Design and develop robust, scalable, and secure backend systems, ensuring the architecture meets both current and future business needs.</li>\r\n	<li><strong>Code Development &amp; Maintenance:</strong> Write clean, efficient, and reusable code; ensure that code is properly documented and aligned with industry best practices.</li>\r\n	<li><strong>Mentorship &amp; Coaching:</strong> Guide junior and mid-level developers, assist with troubleshooting, and ensure they follow coding standards and best practices.</li>\r\n	<li><strong>Collaboration:</strong> Work closely with product managers, front-end developers, and other stakeholders to define technical solutions and deliver features that meet business requirements.</li>\r\n	<li><strong>Performance Optimization:</strong> Identify performance bottlenecks and ensure systems are optimized for speed, scalability, and reliability.</li>\r\n	<li><strong>CI/CD &amp; Testing:</strong> Work on integrating CI/CD pipelines, testing strategies, and ensuring high code coverage with unit and integration tests.</li>\r\n	<li><strong>Code Reviews:</strong> Perform and participate in code reviews, ensuring high code quality, adherence to standards, and that the best practices are followed.</li>\r\n	<li><strong>Tech Stack Management:</strong> Keep up to date with the latest industry trends, frameworks, and tools, and apply them to improve our development processes and systems.</li>\r\n	<li><strong>Production Support:</strong> Ensure smooth production operations and take part in troubleshooting and resolving production issues when they arise.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3>Required Skills and Qualifications:</h3>\r\n\r\n<ul>\r\n	<li><strong>Education:</strong> Bachelor&#39;s or Master&rsquo;s degree in Computer Science, Engineering, or a related field.</li>\r\n	<li><strong>Experience:</strong>\r\n	<ul>\r\n		<li>7+ years of professional experience in Java development with a strong focus on backend technologies.</li>\r\n		<li>3+ years of experience in a technical leadership role, leading teams in designing and developing backend systems.</li>\r\n	</ul>\r\n	</li>\r\n	<li><strong>Technical Skills:</strong>\r\n	<ul>\r\n		<li>Strong proficiency in Java and Java-based frameworks such as Spring, Spring Boot, Hibernate, etc.</li>\r\n		<li>Experience in designing and building RESTful APIs and microservices.</li>\r\n		<li>Expertise in relational databases (MySQL, PostgreSQL) and NoSQL databases (MongoDB, Cassandra).</li>\r\n		<li>Experience with message brokers (Kafka, RabbitMQ, etc.) and event-driven architecture.</li>\r\n		<li>Familiarity with cloud platforms (AWS, Azure, Google Cloud) and containerization technologies (Docker, Kubernetes).</li>\r\n		<li>Strong understanding of design patterns, system design, and architecture principles.</li>\r\n		<li>Experience with CI/CD pipelines and tools (Jenkins, GitLab CI, etc.).</li>\r\n		<li>Proficiency with version control systems, especially Git.</li>\r\n		<li>Knowledge of testing frameworks (JUnit, TestNG, Mockito) and test automation.</li>\r\n		<li>Familiarity with Agile methodologies (Scrum, Kanban).</li>\r\n	</ul>\r\n	</li>\r\n	<li><strong>Soft Skills:</strong>\r\n	<ul>\r\n		<li>Strong leadership and mentoring skills with the ability to guide a team of developers.</li>\r\n		<li>Excellent problem-solving skills and the ability to troubleshoot complex issues.</li>\r\n		<li>Strong communication skills with the ability to effectively collaborate with cross-functional teams.</li>\r\n		<li>Ability to thrive in a fast-paced, collaborative, and constantly changing environment.</li>\r\n	</ul>\r\n	</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3>Preferred Qualifications:</h3>\r\n\r\n<ul>\r\n	<li>Experience with cloud-native architectures and Kubernetes.</li>\r\n	<li>Familiarity with DevOps practices and tools.</li>\r\n	<li>Exposure to frontend technologies (React, Angular) is a plus.</li>\r\n	<li>Familiarity with Agile/Scrum development methodologies.</li>\r\n	<li>Experience with distributed systems and performance tuning.</li>\r\n</ul>\r\n\r\n<hr />\r\n<h3>Benefits:</h3>\r\n\r\n<ul>\r\n	<li>Competitive salary and benefits package.</li>\r\n	<li>Opportunity to work with cutting-edge technologies and innovative projects.</li>\r\n	<li>Career development and growth opportunities.</li>\r\n	<li>Collaborative and dynamic work environment.</li>\r\n	<li>[Insert other company-specific benefits, such as remote work options, health insurance, etc.]</li>\r\n</ul>\r\n\r\n<hr />\r\n<p><strong>How to Apply:</strong></p>\r\n\r\n<p>Please submit your resume and cover letter to [Insert application email or portal link]. In your cover letter, feel free to explain why you&#39;re excited about this role and how your background aligns with the needs of our team.</p>\r\n\r\n<hr />\r\n<p>This JD provides a comprehensive outline for the <strong>Java Technical Lead / Lead Java Backend Developer</strong> position. You can modify it according to the specific requirements of your company or organization.</p>\r\n',NULL,NULL,NULL,'NA',NULL,NULL,22,11,NULL,NULL,11,1,0,'Individual Contributor',0,NULL,NULL,'Delhi,Mumbai,Hyderabad,Pune,Bangalore,Chennai,Kolkata,Gurgaon,Noida',NULL,0,0,0,NULL,NULL,0,'2024-12-22 04:08:07.505000','Any Post Graduation','Bachelor of Science','Java Technical Lead/ Lead Java Backend Developer',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2024-12-22 04:08:08',NULL,NULL,11,_binary '','EMP0011',NULL,NULL,1,0,0,NULL,'employee_manager@example.com',2,NULL,'NA','2024-12-22 04:08:34','11:59 PM',NULL,'00:00 AM',NULL,'employee_manager@example.com',NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postconsultant`
--

DROP TABLE IF EXISTS `postconsultant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postconsultant` (
  `pcid` bigint NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `feePercent` double DEFAULT NULL,
  `percentile` double DEFAULT NULL,
  `lid` int DEFAULT NULL,
  `postId` bigint NOT NULL DEFAULT '0',
  `post_id` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`pcid`),
  UNIQUE KEY `unique_post_lid` (`postId`,`lid`),
  UNIQUE KEY `UK_39ep1y6vsqkqoynb7rhmflm4b` (`post_id`,`lid`),
  KEY `fk_registration` (`lid`),
  KEY `idx_postId` (`postId`),
  CONSTRAINT `fk4invahok1g2dc6w2abvbkwhvo` FOREIGN KEY (`lid`) REFERENCES `registration` (`lid`),
  CONSTRAINT `fk_registration` FOREIGN KEY (`lid`) REFERENCES `registration` (`lid`),
  CONSTRAINT `fki2c3sge65n3rqoblikf7h3ilo` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postconsultant`
--

LOCK TABLES `postconsultant` WRITE;
/*!40000 ALTER TABLE `postconsultant` DISABLE KEYS */;
INSERT INTO `postconsultant` VALUES (1,'2024-12-22 03:54:53',6,0,6,0,6);
/*!40000 ALTER TABLE `postconsultant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postprofile`
--

DROP TABLE IF EXISTS `postprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postprofile` (
  `ppid` bigint NOT NULL AUTO_INCREMENT,
  `accepted` datetime DEFAULT NULL,
  `actionPerformerId` varchar(255) DEFAULT NULL,
  `declinedDate` datetime DEFAULT NULL,
  `joinDate` datetime DEFAULT NULL,
  `joinDropDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT NULL,
  `offerDate` datetime DEFAULT NULL,
  `offerDropDate` datetime DEFAULT NULL,
  `processStatus` varchar(255) DEFAULT NULL,
  `recruited` datetime DEFAULT NULL,
  `rejectReason` varchar(255) DEFAULT NULL,
  `rejected` datetime DEFAULT NULL,
  `submitted` datetime NOT NULL,
  `viewStatus` bit(1) DEFAULT NULL,
  `withdrawDate` datetime DEFAULT NULL,
  `postId` bigint NOT NULL,
  `profileId` bigint NOT NULL,
  `post_id` bigint NOT NULL,
  PRIMARY KEY (`ppid`),
  UNIQUE KEY `UK_ne63eeg09m7y0yfaiubqrj5f9` (`postId`,`profileId`),
  UNIQUE KEY `UK_nufic8tuai0j4ihflpn55n1mo` (`post_id`,`profileId`),
  KEY `FK_s4her1i2milnbu0puwc2rumtv` (`profileId`),
  CONSTRAINT `fk4ry34wun8xg5nrp0aki0kdd4p` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`),
  CONSTRAINT `FK_fwo1upr92e7yr9ilrgrhdvy3f` FOREIGN KEY (`post_id`) REFERENCES `candidateprofile` (`profileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postprofile`
--

LOCK TABLES `postprofile` WRITE;
/*!40000 ALTER TABLE `postprofile` DISABLE KEYS */;
/*!40000 ALTER TABLE `postprofile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualification`
--

DROP TABLE IF EXISTS `qualification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualification` (
  `qId` bigint NOT NULL AUTO_INCREMENT,
  `creationDate` datetime DEFAULT NULL,
  `qTitle` varchar(255) NOT NULL,
  `qType` varchar(255) NOT NULL,
  PRIMARY KEY (`qId`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualification`
--

LOCK TABLES `qualification` WRITE;
/*!40000 ALTER TABLE `qualification` DISABLE KEYS */;
INSERT INTO `qualification` VALUES (1,'2024-12-20 17:41:47','Bachelor of Science','UG'),(2,'2024-12-20 17:41:47','Master of Arts','PG'),(3,'2024-12-20 17:41:47','PhD in Computer Science','PG'),(4,'2024-12-20 17:41:47','Bachelor of Engineering','UG'),(5,'2024-12-20 17:41:47','MBA','PG'),(6,'2024-12-20 17:41:47','Master of Technology','PG'),(7,'2024-12-20 17:41:47','Bachelor of Arts','UG'),(8,'2024-12-20 17:41:47','PhD in Physics','PG'),(9,'2024-12-20 17:41:47','Diploma in Computer Science','UG'),(10,'2024-12-20 17:41:47','Postgraduate Diploma in Management','PG'),(11,'2024-12-20 17:41:47','Bachelor of Business Administration','UG'),(12,'2024-12-20 17:41:47','Postgraduate Diploma in HR','PG'),(13,'2024-12-20 17:41:47','Bachelor of Commerce','UG'),(14,'2024-12-20 17:41:47','Master of Fine Arts','PG'),(15,'2024-12-20 17:41:47','Bachelor of Design','UG'),(16,'2024-12-20 17:41:47','Master of Philosophy','PG'),(17,'2024-12-20 17:41:47','Bachelor of Law','UG'),(18,'2024-12-20 17:41:47','Postgraduate Diploma in IT','PG'),(19,'2024-12-20 17:41:47','Bachelor of Medicine','UG'),(20,'2024-12-20 17:41:47','Master of Education','PG'),(21,'2024-12-20 17:41:47','Bachelor of Nursing','UG'),(22,'2024-12-20 17:41:47','Postgraduate Diploma in Marketing','PG'),(23,'2024-12-20 17:41:47','Bachelor of Psychology','UG'),(24,'2024-12-20 17:41:47','Master of Architecture','PG'),(25,'2024-12-20 17:41:47','Bachelor of Social Work','UG'),(26,'2024-12-20 17:41:47','Master of Journalism','PG'),(27,'2024-12-20 17:41:47','Bachelor of Science in Information Technology','UG'),(28,'2024-12-20 17:41:47','Postgraduate Diploma in Project Management','PG'),(29,'2024-12-20 17:41:47','Bachelor of Electrical Engineering','UG'),(30,'2024-12-20 17:41:47','Master of Health Administration','PG'),(31,'2024-12-20 17:41:47','Bachelor of Mechanical Engineering','UG'),(32,'2024-12-20 17:41:47','Master of International Relations','PG'),(33,'2024-12-20 17:41:47','Bachelor of Civil Engineering','UG'),(34,'2024-12-20 17:41:47','Master of Public Health','PG'),(35,'2024-12-20 17:41:47','Bachelor of Biotechnology','UG'),(36,'2024-12-20 17:41:47','Master of Commerce','PG'),(37,'2024-12-20 17:41:47','Bachelor of Hotel Management','UG'),(38,'2024-12-20 17:41:47','Master of Business Administration','PG'),(39,'2024-12-20 17:41:47','Bachelor of Fine Arts','UG'),(40,'2024-12-20 17:41:47','Master of Arts in History','PG'),(41,'2024-12-20 17:41:47','Bachelor of Education','UG'),(42,'2024-12-20 17:41:47','Master of Science in Nursing','PG'),(43,'2024-12-20 17:41:47','Bachelor of Computer Applications','UG'),(44,'2024-12-20 17:41:47','Master of Tourism and Hospitality','PG'),(45,'2024-12-20 17:41:47','Bachelor of Science in Nursing','UG'),(46,'2024-12-20 17:41:47','Postgraduate Diploma in Digital Marketing','PG'),(47,'2024-12-20 17:41:47','Bachelor of Pharmacy','UG'),(48,'2024-12-20 17:41:47','Master of Science in Biochemistry','PG'),(49,'2024-12-20 17:41:47','Bachelor of Journalism','UG'),(50,'2024-12-20 17:41:47','Master of Applied Economics','PG'),(51,'2024-12-20 17:41:47','Bachelor of Agriculture','UG'),(52,'2024-12-20 17:41:47','Postgraduate Diploma in Financial Management','PG'),(53,'2024-12-20 17:41:47','Bachelor of Veterinary Science','UG'),(54,'2024-12-20 17:41:47','Master of Science in Chemistry','PG'),(55,'2024-12-20 17:41:47','Bachelor of Fashion Design','UG'),(56,'2024-12-20 17:41:47','Postgraduate Diploma in Data Science','PG'),(57,'2024-12-20 17:41:47','Bachelor of Science in Environmental Science','UG'),(58,'2024-12-20 17:41:47','Master of Science in Physics','PG'),(59,'2024-12-20 17:41:47','Bachelor of Animation','UG'),(60,'2024-12-20 17:41:47','Master of Technology in Computer Science','PG'),(61,'2024-12-20 17:41:47','Bachelor of Design','UG'),(62,'2024-12-20 17:41:47','Postgraduate Diploma in Renewable Energy','PG'),(63,'2024-12-20 17:41:47','Bachelor of Animation','UG'),(64,'2024-12-20 17:41:47','Master of Environmental Science','PG'),(65,'2024-12-20 17:41:47','Bachelor of Physical Therapy','UG'),(66,'2024-12-20 17:41:47','Master of Business Analysis','PG'),(67,'2024-12-20 17:41:47','Bachelor of Science in Mathematics','UG'),(68,'2024-12-20 17:41:47','Postgraduate Diploma in Artificial Intelligence','PG'),(69,'2024-12-20 17:41:47','Bachelor of Music','UG'),(70,'2024-12-20 17:41:47','Master of Psychology','PG'),(71,'2024-12-20 17:41:47','Bachelor of Technology','UG'),(72,'2024-12-20 17:41:47','Postgraduate Diploma in Robotics','PG'),(73,'2024-12-20 17:41:47','Bachelor of Accounting','UG'),(74,'2024-12-20 17:41:47','Master of Social Work','PG'),(75,'2024-12-20 17:41:47','Bachelor of Science in Zoology','UG'),(76,'2024-12-20 17:41:47','Master of Public Administration','PG'),(77,'2024-12-20 17:41:47','Bachelor of Science in Geology','UG'),(78,'2024-12-20 17:41:47','Postgraduate Diploma in Cloud Computing','PG'),(79,'2024-12-20 17:41:47','Bachelor of Philosophy','UG'),(80,'2024-12-20 17:41:47','Master of Engineering','PG'),(81,'2024-12-20 17:41:47','Bachelor of Science in Agriculture','UG'),(82,'2024-12-20 17:41:47','Postgraduate Diploma in Information Security','PG'),(83,'2024-12-20 17:41:47','Bachelor of Science in Psychology','UG'),(84,'2024-12-20 17:41:47','Master of Arts in Sociology','PG'),(85,'2024-12-20 17:41:47','Bachelor of Science in Marine Biology','UG'),(86,'2024-12-20 17:41:47','Master of Arts in Linguistics','PG'),(87,'2024-12-20 17:41:47','Bachelor of Medical Sciences','UG'),(88,'2024-12-20 17:41:47','Postgraduate Diploma in UX Design','PG'),(89,'2024-12-20 17:41:47','Bachelor of Food Technology','UG'),(90,'2024-12-20 17:41:47','Master of Technology in Data Science','PG'),(91,'2024-12-20 17:41:47','Bachelor of Information Technology','UG'),(92,'2024-12-20 17:41:47','Postgraduate Diploma in Business Analytics','PG'),(93,'2024-12-20 17:41:47','Bachelor of Science in Chemistry','UG'),(94,'2024-12-20 17:41:47','Master of Science in Microbiology','PG'),(95,'2024-12-20 17:41:47','Bachelor of Law in International Relations','UG'),(96,'2024-12-20 17:41:47','Master of Science in Data Science','PG'),(97,'2024-12-20 17:41:47','Bachelor of Arts in Philosophy','UG'),(98,'2024-12-20 17:41:47','Postgraduate Diploma in FinTech','PG'),(99,'2024-12-20 17:41:47','Bachelor of Business Administration in Finance','UG'),(100,'2024-12-20 17:41:47','Master of Science in Business Administration','PG'),(101,'2024-12-20 17:41:47','Bachelor of Social Science','UG'),(102,'2024-12-20 17:41:47','Postgraduate Diploma in Cyber Security','PG'),(103,'2024-12-20 17:41:47','Bachelor of Science in Nursing','UG'),(104,'2024-12-20 17:41:47','Master of Technology in Biotechnology','PG'),(105,'2024-12-20 17:41:47','Bachelor of Business Administration in Marketing','UG'),(106,'2024-12-20 17:41:47','Master of Arts in Political Science','PG'),(107,'2024-12-20 17:41:47','Bachelor of Physical Education','UG'),(108,'2024-12-20 17:41:47','Postgraduate Diploma in Leadership','PG'),(109,'2024-12-20 17:41:47','Bachelor of Electrical Engineering','UG'),(110,'2024-12-20 17:41:47','Master of Science in Food Technology','PG'),(111,'2024-12-20 17:41:47','Bachelor of Hotel Management and Catering','UG'),(112,'2024-12-20 17:41:47','Master of Arts in Business Communication','PG'),(113,'2024-12-20 17:41:47','Bachelor of Marine Engineering','UG'),(114,'2024-12-20 17:41:47','Master of Management Studies','PG'),(115,'2024-12-20 17:41:47','Bachelor of Construction Management','UG'),(116,'2024-12-20 17:41:47','Postgraduate Diploma in Business Strategy','PG'),(117,'2024-12-20 17:41:47','Bachelor of Commerce in Accountancy','UG'),(118,'2024-12-20 17:41:47','Master of Science in Engineering','PG'),(119,'2024-12-20 17:41:47','Bachelor of Science in Software Engineering','UG'),(120,'2024-12-20 17:41:47','Master of Science in Food Safety','PG'),(121,'2024-12-20 17:41:47','Bachelor of Business Administration in HR','UG'),(122,'2024-12-20 17:41:47','Postgraduate Diploma in Digital Transformation','PG'),(123,'2024-12-20 17:41:47','Bachelor of Biotechnology Engineering','UG'),(124,'2024-12-20 17:41:47','Master of Arts in Journalism','PG'),(125,'2024-12-20 17:41:47','Bachelor of Psychology','UG'),(126,'2024-12-20 17:41:47','Master of Science in HRM','PG'),(127,'2024-12-20 17:41:47','Bachelor of Commerce in Finance','UG'),(128,'2024-12-20 17:41:47','Master of Science in Agriculture','PG'),(129,'2024-12-20 17:41:47','Bachelor of Engineering in Electronics','UG'),(130,'2024-12-20 17:41:47','Postgraduate Diploma in Business Innovation','PG'),(131,'2024-12-20 17:41:47','Bachelor of Fine Arts in Performing Arts','UG'),(132,'2024-12-20 17:41:47','Master of Arts in Education','PG'),(133,'2024-12-20 17:41:47','Bachelor of Technology in Information Science','UG'),(134,'2024-12-20 17:41:47','Postgraduate Diploma in Financial Technology','PG');
/*!40000 ALTER TABLE `qualification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingcalculation`
--

DROP TABLE IF EXISTS `ratingcalculation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratingcalculation` (
  `sn` bigint NOT NULL AUTO_INCREMENT,
  `profileId` bigint DEFAULT NULL,
  `postId` bigint DEFAULT NULL,
  `ratingParamId` int DEFAULT NULL,
  `consultantId` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_awdsiybse2ix4n9fvoiyy54n9` (`profileId`),
  KEY `FK_qhkxsq8jk12fur8rcapqxf5h8` (`ratingParamId`),
  KEY `FK_bojtgtrsvdduo3wekvjdq7ols` (`consultantId`),
  KEY `FK_igddi8rs25t2lcrhku3gj3wgr` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingcalculation`
--

LOCK TABLES `ratingcalculation` WRITE;
/*!40000 ALTER TABLE `ratingcalculation` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratingcalculation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ratingparameter`
--

DROP TABLE IF EXISTS `ratingparameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratingparameter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL,
  `deleteDate` datetime DEFAULT NULL,
  `modifyDate` datetime DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `weightage` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7cspuowc1jbk2fl0m8amdf9n3` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratingparameter`
--

LOCK TABLES `ratingparameter` WRITE;
/*!40000 ALTER TABLE `ratingparameter` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratingparameter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `registration`
--

DROP TABLE IF EXISTS `registration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registration` (
  `lid` int NOT NULL AUTO_INCREMENT,
  `about` longtext,
  `accountNo` varchar(255) DEFAULT NULL,
  `consultName` varchar(255) DEFAULT NULL,
  `consultant_type` bit(1) NOT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `contractDate` datetime DEFAULT NULL,
  `contractNo` varchar(255) DEFAULT NULL,
  `contractPath` varchar(255) DEFAULT NULL,
  `contractorIP` varchar(255) DEFAULT NULL,
  `designation` varchar(255) DEFAULT NULL,
  `emptyField` varchar(255) DEFAULT NULL,
  `feeCommission` double DEFAULT NULL,
  `feePercent1` double DEFAULT NULL,
  `feePercent2` double DEFAULT NULL,
  `feePercent3` double DEFAULT NULL,
  `feePercent4` double DEFAULT NULL,
  `feePercent5` double DEFAULT NULL,
  `firmType` varchar(255) DEFAULT NULL,
  `firstTime` bit(1) DEFAULT NULL,
  `hoAddress` varchar(255) DEFAULT NULL,
  `ifscCode` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `modification_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `noofpeoples` int NOT NULL,
  `officeAddress` varchar(255) DEFAULT NULL,
  `officeLocations` varchar(255) DEFAULT NULL,
  `organizationName` varchar(255) DEFAULT NULL,
  `panno` varchar(255) DEFAULT NULL,
  `paymentDays` int DEFAULT NULL,
  `regdate` datetime NOT NULL,
  `revenue` int NOT NULL,
  `slab1` varchar(255) DEFAULT NULL,
  `slab2` varchar(255) DEFAULT NULL,
  `slab3` varchar(255) DEFAULT NULL,
  `slab4` varchar(255) DEFAULT NULL,
  `slab5` varchar(255) DEFAULT NULL,
  `stno` varchar(255) DEFAULT NULL,
  `userid` varchar(255) NOT NULL,
  `usersRequired` int DEFAULT NULL,
  `websiteUrl` varchar(255) DEFAULT NULL,
  `yearsInIndusrty` int DEFAULT NULL,
  `parent_admin` varchar(255) DEFAULT NULL,
  `log_lid` int DEFAULT NULL,
  PRIMARY KEY (`lid`),
  UNIQUE KEY `UK_1qsyuwabln4wobyrdcr2vhmhk` (`userid`),
  KEY `FK_24n4r47bsq45udkkhtaylc4o5` (`parent_admin`),
  KEY `FK_ogsrr5jr8avxwhefr2abb5cf7` (`log_lid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registration`
--

LOCK TABLES `registration` WRITE;
/*!40000 ALTER TABLE `registration` DISABLE KEYS */;
INSERT INTO `registration` VALUES (1,'ABC Technologies specializes in software development and IT consulting, offering innovative solutions to global clients.',NULL,NULL,_binary '\0','9046348344','2024-12-21 23:50:35',NULL,'f7bd1829-62e1-4a7c-a27b-c6de03e91be6Sikhar-Resume-Detailed.pdf',NULL,'HR Manager','4',0,4,4,4,4,4,NULL,NULL,'',NULL,NULL,NULL,'John Doe',10,'123, Tech Park, Bangalore','Delhi','ABC Technologies Pvt. Ltd.',NULL,4,'2024-12-20 16:07:00',10,'11','4','4','4','4',NULL,'johndoe@example.com',4,'www.abctech.com',0,NULL,1),(2,'XYZ Consultancy Services has been providing HR, recruitment, and outsourcing solutions to a wide range of industries. With over 5 years of experience, we help businesses meet their workforce requirements effectively.','123456789012','XYZ Consultancy Services',_binary '','9876543210','2024-12-22 00:42:38',NULL,'c16b80d5-e117-46e3-bb05-996ed7fca29bSikhar-Resume-Detailed.pdf',NULL,NULL,'1',1,0,0,0,0,0,'Pvt Limited',_binary '',NULL,'SBIN0001234',NULL,NULL,'Sarah Johnson',50,'    Bangalore Office: 45, Tech Avenue, 3rd Floor, Koramangala, Bangalore\r\n    Delhi Office: 22, Connaught Place, New Delhi\r\n    Mumbai Office: 5th Floor, Andheri West, Mumbai','Mumbai',NULL,'ABCDE1234F',1,'2024-12-22 00:44:55',0,NULL,NULL,NULL,NULL,NULL,'ABCD1234567890','contact@xyzconsultancy.com',0,NULL,10,NULL,2),(3,'johndoe@example.com1',NULL,NULL,_binary '\0','9876543210',NULL,NULL,NULL,NULL,'HR Manager',NULL,0,0,0,0,0,0,NULL,_binary '','',NULL,NULL,NULL,'johndoe@example.com1',10,'johndoe@example.com1','Kolkata','johndoe@example.com1',NULL,NULL,'2024-12-20 17:19:39',10,NULL,NULL,NULL,NULL,NULL,NULL,'johndoe@example.com1',0,'johndoe@example.com1',0,NULL,3),(4,NULL,NULL,NULL,_binary '\0',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,0,0,0,0,0,NULL,NULL,NULL,NULL,NULL,NULL,'rohit sharma',0,NULL,NULL,NULL,NULL,NULL,'2024-12-20 23:28:46',0,NULL,NULL,NULL,NULL,NULL,NULL,'rohitsharma@gmail.com',0,NULL,0,'contact@xyzconsultancy.com',4),(5,'',NULL,NULL,_binary '\0','9876543210',NULL,NULL,NULL,NULL,'ADMIN',NULL,0,0,0,0,0,0,NULL,NULL,'',NULL,NULL,NULL,'admin@example.com',10,'admin@example.com','Noida','ADMIN',NULL,NULL,'2024-12-22 00:47:31',10,NULL,NULL,NULL,NULL,NULL,NULL,'admin@example.com',0,'www.google.com',0,NULL,5),(6,'','123456789012','cons_user@example.com',_binary '','9876543210','2024-12-22 00:51:04',NULL,'d4fdfc1a-d34e-4c43-b470-69fdea8a25c0Sikhar-Resume-Detailed.pdf',NULL,NULL,'',11.1,0,0,0,0,0,'Pvt Limited',_binary '',NULL,'SBIN0001234',NULL,NULL,'cons_user@example.com',50,'cons_user@example.com','Delhi,Mumbai,Hyderabad,Pune,Bangalore,Chennai,Kolkata,Gurgaon,Noida',NULL,'ABCDE1234F',1,'2024-12-22 00:50:27',0,NULL,NULL,NULL,NULL,NULL,'ABCD1234567890','cons_user@example.com',1,NULL,10,NULL,6),(7,'consumer_manager@example.com','123456789012','consumer_manager@example.com',_binary '','9876543210','2024-12-22 00:58:25',NULL,'cf0a85eb-ac8f-42c6-a62b-e3c90bc7736cSikhar-Resume-Detailed.pdf',NULL,NULL,'1',1,0,0,0,0,0,'Proprietor',_binary '',NULL,'SBIN0001234',NULL,NULL,'consumer_manager@example.com',50,'consumer_manager@example.com','Delhi,Mumbai,Hyderabad,Pune,Bangalore,Chennai,Kolkata,Gurgaon,Noida',NULL,'ABCDE1234F',11,'2024-12-22 00:57:54',0,NULL,NULL,NULL,NULL,NULL,'ABCD1234567890','consumer_manager@example.com',1,NULL,10,NULL,7),(8,'',NULL,NULL,_binary '\0','9876543210','2024-12-22 01:07:19',NULL,'d27a8311-3831-454e-9c9f-a253489f17e3Sikhar-Resume-Detailed.pdf',NULL,'HR Manager','No refundable',0,10,11,12,13,14,NULL,_binary '','',NULL,NULL,NULL,'employee_user@example.com',11,'employee_user@example.com','Bangalore','employee_user@example.com',NULL,14,'2024-12-22 01:05:33',10,'Slab 1','Slab 2','Slab 3','Slab 4','Slab 5',NULL,'employee_user@example.com',2,'employee_user@example.com',0,NULL,8),(9,'',NULL,NULL,_binary '\0','9876543211','2024-12-22 01:15:20',NULL,'b03ea0aa-47b2-486b-b6a3-7b556867904fSikhar-Resume-Detailed.pdf',NULL,'employee_manager@example.com','NA',0,10,11,12,13,14,NULL,_binary '','',NULL,NULL,NULL,'employee_manager@example.com',11,'employee_manager@example.com','Pune','employee_manager@example.com',NULL,4,'2024-12-22 01:14:21',10,'Slab 1','Slab 2','Slab 3','Slab 4','Slab 5',NULL,'employee_manager@example.com',2,'www.google.com',0,NULL,9);
/*!40000 ALTER TABLE `registration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialsharing`
--

DROP TABLE IF EXISTS `socialsharing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socialsharing` (
  `id` int NOT NULL AUTO_INCREMENT,
  `api_key` varchar(255) DEFAULT NULL,
  `oauth_expires` varchar(255) DEFAULT NULL,
  `oauth_token` varchar(255) DEFAULT NULL,
  `socialMediaName` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialsharing`
--

LOCK TABLES `socialsharing` WRITE;
/*!40000 ALTER TABLE `socialsharing` DISABLE KEYS */;
/*!40000 ALTER TABLE `socialsharing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_industry`
--

DROP TABLE IF EXISTS `user_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_industry` (
  `lid` int NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`lid`,`id`),
  KEY `FK_nv0pau98ms9ehugp4aa7ornec` (`id`),
  CONSTRAINT `FK_95hkx57trl245kp5vg5pphygc` FOREIGN KEY (`lid`) REFERENCES `registration` (`lid`),
  CONSTRAINT `FK_nv0pau98ms9ehugp4aa7ornec` FOREIGN KEY (`id`) REFERENCES `industry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_industry`
--

LOCK TABLES `user_industry` WRITE;
/*!40000 ALTER TABLE `user_industry` DISABLE KEYS */;
INSERT INTO `user_industry` VALUES (6,1),(7,1),(6,2),(7,2),(2,3),(6,3),(7,3),(6,4),(7,4),(6,5),(7,5),(6,6),(7,6),(5,7),(6,7),(7,7),(2,8),(3,8),(6,8),(7,8),(9,8);
/*!40000 ALTER TABLE `user_industry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userrole`
--

DROP TABLE IF EXISTS `userrole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userrole` (
  `sn` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `userrole` varchar(255) NOT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_1wkm7n1qkph4j0iaihjd6q7yd` (`userid`),
  CONSTRAINT `FK_1wkm7n1qkph4j0iaihjd6q7yd` FOREIGN KEY (`userid`) REFERENCES `logininfo` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userrole`
--

LOCK TABLES `userrole` WRITE;
/*!40000 ALTER TABLE `userrole` DISABLE KEYS */;
INSERT INTO `userrole` VALUES (1,'johndoe@example.com','ROLE_EMP_MANAGER'),(2,'contact@xyzconsultancy.com','ROLE_CON_MANAGER'),(3,'johndoe@example.com1','ROLE_ADMIN'),(4,'rohitsharma@gmail.com','ROLE_EMP_USER'),(5,'admin@example.com','ROLE_ADMIN'),(6,'cons_user@example.com','ROLE_CON_USER'),(7,'consumer_manager@example.com','ROLE_CON_MANAGER'),(8,'employee_user@example.com','ROLE_EMP_MANAGER'),(9,'employee_manager@example.com','ROLE_EMP_MANAGER');
/*!40000 ALTER TABLE `userrole` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-22  4:11:42
