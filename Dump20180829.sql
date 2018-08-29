-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: video_learning
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `user_info`
--

DROP TABLE IF EXISTS `user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'Yohane',NULL),(2,'Yosoro',NULL),(3,'Yosoro',NULL),(4,'Yosoro',NULL),(5,'哈哈哈哈',NULL),(6,'娃娃啊哇哇',NULL),(7,'哇哇哇哇啊',NULL),(8,'哈哈哈哈',NULL),(9,'12345',NULL),(10,'123456',NULL),(11,'1234',NULL),(12,'哇哇哇哇哇',NULL),(13,'23www',NULL),(14,'441242142',NULL),(15,'哈哈哈哈哈',NULL);
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_private`
--

DROP TABLE IF EXISTS `user_private`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_private` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_mail` varchar(32) DEFAULT NULL,
  `pwd` varchar(16) DEFAULT NULL,
  `user_type` int(11) DEFAULT '2',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `email` (`user_mail`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_private`
--

LOCK TABLES `user_private` WRITE;
/*!40000 ALTER TABLE `user_private` DISABLE KEYS */;
INSERT INTO `user_private` VALUES (1,'10@qq.com','monstar10',1),(2,'101120@qq.com','monstar10',2),(3,'1011220@qq.com','monstar10',2),(4,'123@qq.com','monstar10',2),(5,'1011123@qq.com','monstar10',2),(6,'1011124@qq.com','monstar10',2),(7,'1011125@qq.com','monstar10',2),(8,'1@qq.com','monstar10',2),(9,'2@qq.com','monstar10',2),(10,'3@qq.com','monstar10',2),(11,'1234@qq.com','monstar10',2),(12,'555@qq.com','monstar10',2),(13,'666@qq.com','monstar10',2),(14,'000@qq.com','monstar10',2),(15,'999@qq.com','monstar10',2);
/*!40000 ALTER TABLE `user_private` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video` (
  `video_id` varchar(64) NOT NULL,
  `upload_time` datetime DEFAULT NULL,
  `view_count` int(11) DEFAULT '0',
  `video_name` varchar(64) DEFAULT NULL,
  `video_describe` varchar(256) DEFAULT NULL,
  `video_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`video_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

LOCK TABLES `video` WRITE;
/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES ('20ad44cc5ffa43cd9792fd5980c6006b','2018-08-28 16:00:29',0,'进阶1','进阶1',183013268),('43a904ff58574f4ab85d879151401fe3','2018-08-21 11:40:51',0,'video2',NULL,47937346),('69ce63826bae4155b653fce4644aebd7','2018-08-21 21:51:57',0,'video1',NULL,NULL),('6ce411a321cd487e9c485391db91246b','2018-08-22 15:04:54',0,'update testing 1 ','update testing 1',NULL),('71a7d378950e4fc3aaf21b5d5c108e07','2018-08-22 17:30:00',0,'测试另外一个数据','这也只是个测试',47937346),('78d5b7ad4a9440b6be6874e2cdb5d3ef','2018-08-22 10:11:49',0,'阿凡达沙发沙发','范德萨发生范德萨',47937346),('7da8588988a54cb994352345fa0b4e6d','2018-08-22 17:14:48',0,'测试视频类型','测试而已',NULL),('90ec8e99b0ac4f2b96e4945708c79a2d','2018-08-21 17:36:51',0,'no title','nonono',47937346),('97f78e40b5db4f21b1c6ee81a83bbca1','2018-08-22 17:24:57',0,'test again','test again for video type',604717018),('abd0ab9e67cd444b86e2b85d11f838b0','2018-08-22 15:09:37',0,'再来一个视频测试','这又是一个视频测试',NULL),('e10017b003e0415199461ca1149cab15','2018-08-28 16:05:15',0,'Neo4J进阶2','Neo4J进阶2',183013268);
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `video_type`
--

DROP TABLE IF EXISTS `video_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `video_type` (
  `type_id` bigint(20) NOT NULL,
  `type_name` varchar(32) NOT NULL,
  `father_id` bigint(20) DEFAULT NULL,
  `image` varchar(25) DEFAULT 'default.jpg',
  `type_description` varchar(256) DEFAULT '暂无内容',
  `create_time` datetime DEFAULT NULL,
  `sub_count` int(11) DEFAULT '0',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video_type`
--

LOCK TABLES `video_type` WRITE;
/*!40000 ALTER TABLE `video_type` DISABLE KEYS */;
INSERT INTO `video_type` VALUES (47937346,'PS从入门到放弃',590519275,'default.jpg','暂无内容',NULL,0),(77432627,'Ruby编程',981464339,'default.jpg','暂无内容',NULL,0),(81572649,'Neo4J实战',194664612,'default.jpg','暂无内容','2018-08-27 21:16:44',0),(130065624,'OpenCV 从入门到精通',856815613,'default.jpg','暂无内容',NULL,0),(140083011,'大数据',NULL,'default.jpg','暂无内容',NULL,4),(143379331,'人工智能实战',140083011,'143379331.jpg','暂无内容','2018-08-27 20:57:15',0),(179823586,'理论课程入门',856815613,'default.jpg','暂无内容',NULL,0),(183013268,'Neo4进阶',194664612,'183013268.jpg','暂无内容','2018-08-27 21:05:15',0),(186716560,'人工智能导论',140083011,'186716560.jpg','暂无内容','2018-08-27 20:52:38',0),(194664612,'数据库',NULL,'default.jpg','暂无内容',NULL,7),(212293937,'C#进阶',981464339,'default.jpg','暂无内容',NULL,0),(399201945,'C#精通',981464339,'default.jpg','暂无内容',NULL,0),(420529280,'Redis进阶',194664612,'420529280.jpg','Redis进阶','2018-08-29 10:03:27',0),(442960982,'大数据实战',140083011,'442960982.jpg','暂无内容','2018-08-27 21:17:49',0),(557007444,'Python编程',981464339,'default.jpg','暂无内容',NULL,0),(558809724,'C++',981464339,'558809724.jpg','暂无内容',NULL,0),(590519275,'UI设计',NULL,'default.jpg','暂无内容',NULL,3),(604717018,'Java程序设计',981464339,'604717018.jpg','暂无内容',NULL,0),(616284757,'C++精通',981464339,'default.jpg','暂无内容',NULL,0),(636101138,'Oracle',194664612,'636101138.jpg','暂无内容',NULL,0),(663550564,'理论课程入门',140083011,'default.jpg','暂无内容',NULL,0),(712059381,'页面设计',590519275,'default.jpg','暂无内容',NULL,0),(717831082,'Java精通',981464339,'default.jpg','暂无内容',NULL,0),(729273249,'Neo4j入门',194664612,'729273249.jpg','暂无内容','2018-08-27 21:04:07',0),(774120617,'C#从入门到放弃',981464339,'default.jpg','暂无内容',NULL,0),(781399923,'Redis',194664612,'781399923.jpg','暂无内容',NULL,0),(800714125,'C语言编程',981464339,'default.jpg','暂无内容',NULL,0),(856815613,'图像识别',NULL,'default.jpg','暂无内容',NULL,2),(910904341,'Mysql',194664612,'910904341.jpg','暂无内容',NULL,0),(928215171,'前端从入门到放弃',590519275,'928215171.png',NULL,'2018-08-27 21:33:10',0),(981464339,'程序设计语言',NULL,'default.jpg','暂无内容',NULL,10);
/*!40000 ALTER TABLE `video_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'video_learning'
--
/*!50003 DROP PROCEDURE IF EXISTS `add_new_user` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_new_user`(in email varchar(32),in upwd varchar(16),
							   in nickname varchar(16),out result int)
BEGIN
	declare userId int ;
    declare mail_exist int;
    declare info_count int;
	start transaction;
    select COUNT(*) into mail_exist from user_private where user_mail = email;
    if mail_exist != 0 then
		select 0 into result;
        rollback;
	else
		insert into user_private(user_mail,pwd,user_type) values(email,upwd,2);
		select user_id into userId from user_private where user_mail = email;
        insert into user_info (user_id,user_name) values(userId,nickname);
		select 1 into result ;
        commit;
    end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `add_new_video_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `add_new_video_type`(in typeId bigint(20),in typeName varchar(32),
																 in parentId long,in descr varchar(256),in new_image varchar(32),out result int)
BEGIN
	declare isParentAdd int default 1;
    declare beforeCount int default 0;
    declare afterCount int;
    declare insertCount int;
    start transaction;
    select COUNT(*) into beforeCount from video_type where father_id = parentId;
	insert into video_type (type_id,type_name,father_id,image,create_time,type_description) 
    values (typeId,typeName,parentId,new_image,now(),descr);
    if parentId <> -1 then
        update video_type set sub_count = sub_count + 1 where type_id = parentId;
        commit;
        select COUNT(*) into afterCount from video_type where father_id = parentId;
        select beforeCount < afterCount into isParentAdd;
	else
		commit;
    end if;
    select COUNT(*) into insertCount from video_type where type_id = typeId;
    select isParentAdd & insertCount into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_sub_video_type` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_sub_video_type`(in subType bigint(20),out result int)
BEGIN
	declare bc int;
    declare ac int;
    declare isSubstract int;
    declare parentId int;
    declare extinct int;
    start transaction;
    select father_id into parentId from video_type where type_id = subType;
    select count(*) into bc from video_type where father_id = parentId;
    delete from video_type where type_id = subType;
    update video_type set sub_count =sub_count - 1 where type_id = parentId ;
    commit;
    select count(*) into ac from video_type where father_id = parentId;
	select ac> bc into isSubstract;
    select COUNT(*) = 0 into extinct from video_type where type_id = subType;
    select (isSubstract=0)& (extinct=1) into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_video` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_video`(in videoId long,out result int)
BEGIN
	#the variable for sub_id 
	declare sub_count int;
    declare parent_count int;
	declare done int default 0;
    declare sub_id int;
    #the variable for video_id by the sub_id
    declare video_sub_id varchar(64);
    #the variable for video_id by the father_id
	declare video_parent_id varchar(64);
    
    declare sub_cursor cursor for select type_id from video_type where father_id = videoId;
    declare video_parent_cursor cursor for select video_id from video where video_type = videoId;
    declare video_sub_cursor cursor for select video_id from video where video_type in(select type_id from video_type where father_id = videoId);

	declare continue handler for not found set done = 1;
    
    #set the video_type = null where video_type equals videoId
    open video_parent_cursor;
    fetch video_parent_cursor into video_parent_id;
    while done <> 1 do
		update video set video_type = null where video_id = video_parent_id;
        fetch video_parent_cursor into video_parent_id;
	end while;
    close video_parent_cursor;
    set done=0;
    
    #set the video_tupe = null where video_type's parent_type equals videoId
    open video_sub_cursor;
    fetch video_sub_cursor into video_sub_id ;
    while done <> 1 do
		update video  set video_type = null where video_id = video_sub_id;
        fetch video_sub_cursor into video_sub_id;
    end while;
    close video_sub_cursor;
    set done=0;
    
    #delete video type and it's subtypes
    delete from video_type where type_id = videoId;
    open sub_cursor;
    fetch sub_cursor into sub_id;
    while done<>1 do
		delete from video_type where type_id = sub_id;
        fetch sub_cursor into sub_id;
	end while;
    close sub_cursor;
commit;
    select count(*) into sub_count from video_type where father_id = videoId;
    select count(*) into parent_count from video_type where type_id = videoId;
    select (sub_count = 0 )& (parent_count = 0 )into result;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-08-29 10:41:42
