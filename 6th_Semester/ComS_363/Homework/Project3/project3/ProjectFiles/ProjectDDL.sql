CREATE DATABASE  IF NOT EXISTS `coms363_proj3`;
USE `coms363_proj3`;

DROP TABLE IF EXISTS `mentioned`;
DROP TABLE IF EXISTS `urlused`;
DROP TABLE IF EXISTS `tagged`;
DROP TABLE IF EXISTS `tweets`;
DROP TABLE IF EXISTS `user`;


CREATE TABLE IF NOT EXISTS user(
screen_name VARCHAR(40) NOT NULL,
name VARCHAR(40) DEFAULT NULL,
sub_category VARCHAR(40) DEFAULT NULL,
category VARCHAR(40) DEFAULT NULL,
ofstate VARCHAR(40) DEFAULT NULL,
numFollowers INT DEFAULT NULL, 
numFollowing INT DEFAULT NULL,
PRIMARY KEY (screen_name),
UNIQUE (screen_name)
);


CREATE TABLE IF NOT EXISTS tweets(
tid bigint NOT NULL,
textbody TEXT NOT NULL,
retweet_count INT DEFAULT NULL,
posted DATETIME NOT NULL,
posting_user VARCHAR(40) NOT NULL,
PRIMARY KEY (tid),
UNIQUE (tid),
FOREIGN KEY (posting_user)
	REFERENCES user (screen_name)
);


CREATE TABLE IF NOT EXISTS tagged(
tid bigint,
hastagname VARCHAR(40),
FOREIGN KEY (tid)
	REFERENCES tweets (tid)
);


CREATE TABLE IF NOT EXISTS urlused(
tid bigint,
url TEXT,
FOREIGN KEY (tid)
	REFERENCES tweets (tid)
);


CREATE TABLE IF NOT EXISTS mentioned(
tid bigint,
screen_name VARCHAR(40),
FOREIGN KEY (tid)
	REFERENCES tweets (tid),
FOREIGN KEY (screen_name)
	REFERENCES user (screen_name)
);