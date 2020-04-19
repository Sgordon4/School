CREATE DATABASE  IF NOT EXISTS `test`;
USE `test`;
DROP TABLE IF EXISTS `tweet`;
CREATE TABLE `tweet` (
  `tid` bigint NOT NULL,
  `textbody` text,
  `retweet_count` int(11) DEFAULT NULL,
  `retweeted` tinyint(1) DEFAULT NULL,
  `posted` datetime DEFAULT NULL,
  PRIMARY KEY (`tid`));
 
 DROP TABLE IF EXISTS `newtweet`;
  CREATE TABLE `newtweet` (
  `tid` bigint NOT NULL,
  `textbody` text,
  `retweet_count` int(11) DEFAULT NULL,
  `retweeted` tinyint(1) DEFAULT NULL,
  `day_posted` int DEFAULT NULL,
  `month_posted` int DEFAULT NULL,
  `year_posted` int default NULL,
  PRIMARY KEY (`tid`));