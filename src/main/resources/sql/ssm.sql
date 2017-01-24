/*
Navicat MySQL Data Transfer

Source Server         : wanjing
Source Server Version : 50635
Source Host           : 120.25.225.47:3306
Source Database       : ssm

Target Server Type    : MYSQL
Target Server Version : 50635
File Encoding         : 65001

Date: 2017-01-24 14:43:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary` (
  `dictType` varchar(255) NOT NULL,
  `dictId` int(11) NOT NULL AUTO_INCREMENT,
  `dictFather` varchar(255) DEFAULT NULL COMMENT '父级字段名',
  `dictName` varchar(50) NOT NULL,
  `dictValue` varchar(5) NOT NULL,
  `isFixed` int(255) NOT NULL,
  PRIMARY KEY (`dictId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news` (
  `NewsId` int(24) NOT NULL AUTO_INCREMENT,
  `NewsType` varchar(255) DEFAULT NULL,
  `NewsTitle` varchar(255) DEFAULT NULL,
  `NewsAuthor` varchar(255) DEFAULT NULL,
  `NewsContent` varchar(20305) DEFAULT NULL,
  `EditorTime` date DEFAULT NULL,
  `NewsProgram` varchar(255) DEFAULT NULL COMMENT '新闻栏目',
  `NewsStatus` varchar(255) DEFAULT '0' COMMENT '新闻状态',
  PRIMARY KEY (`NewsId`)
) ENGINE=InnoDB AUTO_INCREMENT=1346 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `yhId` int(11) NOT NULL AUTO_INCREMENT,
  `yhxm` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `yhxb` varchar(255) DEFAULT NULL,
  `yhyx` varchar(255) DEFAULT NULL,
  `yhzh` varchar(255) DEFAULT NULL,
  `yhjs` varchar(255) DEFAULT NULL,
  `cjsj` date DEFAULT NULL,
  PRIMARY KEY (`yhId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
DROP TRIGGER IF EXISTS `editortime`;
DELIMITER ;;
CREATE TRIGGER `editortime` BEFORE INSERT ON `news` FOR EACH ROW BEGIN 
SET New.EditorTime=CURDATE();
END
;;
DELIMITER ;
DROP TRIGGER IF EXISTS `cjsj`;
DELIMITER ;;
CREATE TRIGGER `cjsj` BEFORE INSERT ON `sysuser` FOR EACH ROW begin 
SET new.cjsj=CURDATE();
end
;;
DELIMITER ;
