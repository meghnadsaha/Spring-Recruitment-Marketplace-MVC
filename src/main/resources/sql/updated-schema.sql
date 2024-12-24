-- SELECT * FROM `unihyr-test`.helpdesk;

-- CREATE DATABASE `unihyr-test`;
-- CREATE USER 'unihyr-test'@'localhost' IDENTIFIED BY 'unihyr-test';
-- GRANT ALL PRIVILEGES ON `unihyr-test`.* TO 'unihyr-test'@'localhost';
-- FLUSH PRIVILEGES;
-- SHOW GRANTS FOR 'unihyr-test'@'localhost';
-- SELECT User, Host FROM mysql.user WHERE User = 'unihyr-test';


USE `unihyr-test`;

-- DROP TABLE IF EXISTS `billingdetails`;
CREATE TABLE `billingdetails` (
  `billId` int NOT NULL AUTO_INCREMENT,
  `adminPaidStatus` bit(1) NOT NULL DEFAULT b'0',  -- Default value set to 0 (false)
  `billableCTC` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `candidatePerson` varchar(255) DEFAULT 'Unknown',  -- Default value for candidate name
  `clientAddress` varchar(255) DEFAULT 'Not Provided',  -- Default value for client address
  `clientId` varchar(255) NOT NULL,  -- No default, as this is required
  `clientName` varchar(255) NOT NULL,  -- No default, as this is required
  `clientPaidStatus` bit(1) NOT NULL DEFAULT b'0',  -- Default value set to 0 (false)
  `consInvoicePath` varchar(255) DEFAULT 'Not Available',  -- Default value for consultant invoice path
  `consultantId` varchar(255) NOT NULL,  -- No default, as this is required
  `consultantName` varchar(255) NOT NULL,  -- No default, as this is required
  `createDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Default value set to the current timestamp
  `deleteDate` datetime DEFAULT NULL,  -- Default value is NULL
  `expectedJoiningDate` datetime DEFAULT NULL,  -- Default value is NULL
  `fee` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `feePercentForClient` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `feePercentToAdmin` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `invoicePath` varchar(255) DEFAULT 'Not Available',  -- Default value for invoice path
  `joiningDate` datetime DEFAULT NULL,  -- Default value is NULL
  `location` varchar(255) NOT NULL DEFAULT 'Unknown',  -- Default value for location
  `offerAcceptedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Default value set to the current timestamp
  `paidDate` datetime DEFAULT NULL,  -- Default value is NULL
  `paymentDueDateForAd` datetime DEFAULT NULL,  -- Default value is NULL
  `paymentDueDateForCo` datetime DEFAULT NULL,  -- Default value is NULL
  `position` varchar(255) NOT NULL DEFAULT 'Not Specified',  -- Default value for position
  `postId` bigint NOT NULL,  -- No default, as this is required
  `postProfileId` bigint NOT NULL,  -- No default, as this is required
  `submittedDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Default value set to the current timestamp
  `tax` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `totalAmount` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `totalCTC` double NOT NULL DEFAULT 0.0,  -- Default value set to 0.0
  `verificationStatus` bit(1) DEFAULT b'0',  -- Default value set to 0 (false)
  `ppid` bigint NOT NULL,
  PRIMARY KEY (`billId`),
  KEY `FK_o67e889i3fg2bx3e5lurfgce2` (`ppid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


USE `unihyr-test`;

DROP TABLE IF EXISTS `inbox`;

CREATE TABLE `inbox` (
  `inboxId` bigint NOT NULL AUTO_INCREMENT,
  `client` varchar(255) DEFAULT 'Unknown',  -- Default value for client name
  `consultant` varchar(255) DEFAULT 'Unknown',  -- Default value for consultant name
  `createDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Default value set to the current timestamp
  `isViewed` bit(1) NOT NULL DEFAULT b'0',  -- Default value set to 0 (false)
  `message` longtext NOT NULL,  -- No default value for the message field
  `ppid` bigint NOT NULL,  -- No default, as this is required
  PRIMARY KEY (`inboxId`),
  KEY `FK_i4mv31fmbm179dp40j9mdnfqf` (`ppid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- Updated Schema with Default Values
USE `unihyr-test`;


DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_id` bigint NOT NULL AUTO_INCREMENT,
  `addition_detail` longtext,  -- Retaining one version of the column
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
  PRIMARY KEY (`post_id`),
  KEY `FK_oo7nkko4cqwxynprkm6v7dd9n` (`client_id`),  -- Corrected column name
  KEY `FK_s6yaf2kyxkii4kifj8w0erhab` (`last_modifier`)  -- Corrected column name
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;




USE `unihyr-test`;


USE `unihyr-test`;

DROP TABLE IF EXISTS `logininfo`;
CREATE TABLE `logininfo` (
  `lid` int NOT NULL AUTO_INCREMENT,
  `forgotpwdid` varchar(255) DEFAULT NULL,
  `isLogin` bit(1) DEFAULT NULL,  -- Default value is NULL
  `isactive` varchar(255) NOT NULL,
  `login_date` datetime DEFAULT NULL,  -- Default value is NULL
  `logout_date` datetime DEFAULT NULL,  -- Default value is NULL
  `modification_date` datetime DEFAULT NULL,  -- Default value is NULL
  `password` varchar(255) NOT NULL,
  `userid` varchar(255) NOT NULL,
  PRIMARY KEY (`lid`),
  UNIQUE KEY `UK_896wc63te16rs2f433gk0u5yf` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `userrole`;
CREATE TABLE `userrole` (
  `sn` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(255) NOT NULL,
  `userrole` varchar(255) NOT NULL,
  PRIMARY KEY (`sn`),
  KEY `FK_1wkm7n1qkph4j0iaihjd6q7yd` (`userid`),
  CONSTRAINT `FK_1wkm7n1qkph4j0iaihjd6q7yd` FOREIGN KEY (`userid`) REFERENCES `logininfo` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

USE `unihyr-test`;

DROP TABLE IF EXISTS `registration`;
CREATE TABLE `registration` (
  `lid` int NOT NULL AUTO_INCREMENT,
  `about` longtext DEFAULT NULL,
  `accountNo` varchar(255) DEFAULT '',
  `consultName` varchar(255) DEFAULT '',
  `consultant_type` bit(1) NOT NULL DEFAULT b'0',
  `contact` varchar(255) DEFAULT '',
  `contractDate` datetime DEFAULT NULL,
  `contractNo` varchar(255) DEFAULT '',
  `contractPath` varchar(255) DEFAULT '',
  `contractorIP` varchar(255) DEFAULT '',
  `designation` varchar(255) DEFAULT '',
  `emptyField` varchar(255) DEFAULT '',
  `feeCommission` double DEFAULT 0,
  `feePercent1` double DEFAULT 0,
  `feePercent2` double DEFAULT 0,
  `feePercent3` double DEFAULT 0,
  `feePercent4` double DEFAULT 0,
  `feePercent5` double DEFAULT 0,
  `firmType` varchar(255) DEFAULT '',
  `firstTime` bit(1) DEFAULT b'0',
  `hoAddress` varchar(255) DEFAULT '',
  `ifscCode` varchar(255) DEFAULT '',
  `logo` varchar(255) DEFAULT '',
  `modification_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT '',
  `noofpeoples` int NOT NULL DEFAULT 0,
  `officeAddress` varchar(255) DEFAULT '',
  `officeLocations` varchar(255) DEFAULT '',
  `organizationName` varchar(255) DEFAULT '',
  `panno` varchar(255) DEFAULT '',
  `paymentDays` int DEFAULT 0,
  `regdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `revenue` int NOT NULL DEFAULT 0,
  `slab1` varchar(255) DEFAULT '',
  `slab2` varchar(255) DEFAULT '',
  `slab3` varchar(255) DEFAULT '',
  `slab4` varchar(255) DEFAULT '',
  `slab5` varchar(255) DEFAULT '',
  `stno` varchar(255) DEFAULT '',
  `userid` varchar(255) NOT NULL,
  `usersRequired` int DEFAULT 0,
  `websiteUrl` varchar(255) DEFAULT '',
  `yearsInIndusrty` int DEFAULT 0,
  `parent_admin` varchar(255) DEFAULT '',
  `log_lid` int DEFAULT NULL,
  PRIMARY KEY (`lid`),
  UNIQUE KEY `UK_1qsyuwabln4wobyrdcr2vhmhk` (`userid`),
  KEY `FK_24n4r47bsq45udkkhtaylc4o5` (`parent_admin`),
  KEY `FK_ogsrr5jr8avxwhefr2abb5cf7` (`log_lid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

USE `unihyr-test`;

DROP TABLE IF EXISTS `postconsultant`;
CREATE TABLE `postconsultant` (
  `pcid` bigint NOT NULL AUTO_INCREMENT,
  `createDate` datetime DEFAULT NULL,
  `feePercent` double DEFAULT NULL,
  `percentile` double DEFAULT NULL,
  `lid` int DEFAULT NULL,
  `postId` bigint NOT NULL DEFAULT '0',
  PRIMARY KEY (`pcid`),
  UNIQUE KEY `unique_post_lid` (`postId`, `lid`),  -- Unique constraint for postId and lid
  KEY `fk_registration` (`lid`),  -- Index for foreign key reference to registration
  KEY `idx_postId` (`postId`),  -- Index for postId
  CONSTRAINT `fk_registration` FOREIGN KEY (`lid`) REFERENCES `registration` (`lid`),  -- Foreign key for lid
  CONSTRAINT `fki2c3sge65n3rqoblikf7h3ilo` FOREIGN KEY (`postId`) REFERENCES `post` (`post_id`)  -- Foreign key for postId
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `candidateprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidateprofile` (
  `profileId` bigint NOT NULL AUTO_INCREMENT,
  `contact` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string if no contact provided
  `countryCode` varchar(255) DEFAULT NULL,  -- Can be NULL if not provided
  `ctcComments` varchar(255) DEFAULT '',  -- Default to empty string
  `currentCTC` double DEFAULT 0,  -- Default to 0 if not provided
  `currentLocation` varchar(255) DEFAULT '',  -- Default to empty string if not provided
  `currentOrganization` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string
  `currentRole` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string
  `date` datetime DEFAULT CURRENT_TIMESTAMP,  -- Default to current timestamp if not provided
  `dateofbirth` varchar(255) DEFAULT '',  -- Default to empty string if not provided
  `deleteDate` datetime DEFAULT NULL,  -- Can be NULL if not provided
  `email` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string if no email provided
  `expectedCTC` double DEFAULT 0,  -- Default to 0 if not provided
  `experience` double DEFAULT 0,  -- Default to 0 if not provided
  `name` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string if name not provided
  `noticePeriod` int DEFAULT 0,  -- Default to 0 if not provided
  `preferredLocation` varchar(255) DEFAULT '',  -- Default to empty string
  `published` datetime DEFAULT NULL,  -- Can be NULL if not provided
  `qualification_pg` varchar(255) DEFAULT '',  -- Default to empty string
  `qualification_ug` varchar(255) DEFAULT '',  -- Default to empty string
  `resumePath` varchar(255) DEFAULT '',  -- Default to empty string if not provided
  `resumeText` longtext,  -- No default value allowed
  `screeningNote` longtext,  -- No default value allowed
  `willingToRelocate` varchar(255) NOT NULL DEFAULT 'No',  -- Default to 'No' assuming most candidates may not be willing
  `consultantId` varchar(255) DEFAULT '',  -- Default to empty string if no consultant assigned
  PRIMARY KEY (`profileId`),
  KEY `FK_t2oml92s166ejqf272en1r8o3` (`consultantId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

-- Updated Schema with Default Values
USE `unihyr-test`;
-- Updated Schema with Default Values
USE `unihyr-test`;

DROP TABLE IF EXISTS `postprofile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;

CREATE TABLE `postprofile` (
  `ppid` bigint NOT NULL AUTO_INCREMENT,
  `accepted` datetime DEFAULT NULL,
  `actionPerformerId` varchar(255) DEFAULT '',
  `declinedDate` datetime DEFAULT NULL,
  `joinDate` datetime DEFAULT NULL,
  `joinDropDate` datetime DEFAULT NULL,
  `modificationDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `offerDate` datetime DEFAULT NULL,
  `offerDropDate` datetime DEFAULT NULL,
  `processStatus` varchar(255) DEFAULT '',
  `recruited` datetime DEFAULT NULL,
  `rejectReason` varchar(255) DEFAULT '',
  `rejected` datetime DEFAULT NULL,
  `submitted` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `viewStatus` bit(1) DEFAULT 0,
  `withdrawDate` datetime DEFAULT NULL,
  `postId` bigint NOT NULL DEFAULT 0,  -- If this is the only post ID column, remove `post_id`
  `profileId` bigint NOT NULL DEFAULT 0,
  PRIMARY KEY (`ppid`),
  UNIQUE KEY `UK_postProfile` (`postId`,`profileId`),
  KEY `FK_profile` (`profileId`),
  CONSTRAINT `fk_post` FOREIGN KEY (`postId`) REFERENCES `post` (`post_id`),
  CONSTRAINT `fk_candidateProfile` FOREIGN KEY (`profileId`) REFERENCES `candidateprofile` (`profileId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





DROP TABLE IF EXISTS `industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `industry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `createDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,  -- Set default to current timestamp
  `deleteDate` datetime DEFAULT NULL,  -- Default to NULL
  `industry` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string
  `lastModifier` varchar(255) DEFAULT '',  -- Default to empty string
  `modifyDate` datetime DEFAULT NULL,  -- Default to NULL
  `userid` varchar(255) NOT NULL DEFAULT '',  -- Default to empty string
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2kakuuaut6opkniy7a31yvviv` (`industry`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `user_industry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_industry` (
  `lid` int NOT NULL DEFAULT 0,  -- Default to 0 if no lid provided (assuming it's a foreign key)
  `id` int NOT NULL DEFAULT 0,  -- Default to 0 if no id provided (assuming it's a foreign key)
  PRIMARY KEY (`lid`,`id`),
  KEY `FK_nv0pau98ms9ehugp4aa7ornec` (`id`),
  CONSTRAINT `FK_95hkx57trl245kp5vg5pphygc` FOREIGN KEY (`lid`) REFERENCES `registration` (`lid`),
  CONSTRAINT `FK_nv0pau98ms9ehugp4aa7ornec` FOREIGN KEY (`id`) REFERENCES `industry` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

