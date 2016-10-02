/*
Navicat MySQL Data Transfer

Source Server         : king
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : studyonline

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2016-10-02 09:41:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL COMMENT '第几章',
  `name` varchar(255) DEFAULT NULL COMMENT '章节名',
  `content` longtext COMMENT '内容',
  `video_path` varchar(255) DEFAULT NULL COMMENT '视频地址',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 0：未审核  1：通过审核 2：未通过审核',
  `course_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `chapter_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chapter
-- ----------------------------

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `content` longtext,
  `release_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '课程名',
  `star` double DEFAULT NULL COMMENT '课程评分',
  `difficulty` tinyint(4) DEFAULT NULL COMMENT '0：初级 1：中级 2：高级',
  `introduction` longtext COMMENT '课程简介',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime DEFAULT NULL COMMENT '课程创建时间',
  `chapter_num` int(11) DEFAULT NULL COMMENT '课程章节数',
  `cover_pic` varchar(255) DEFAULT NULL COMMENT '课程封面图片',
  `status` tinyint(4) DEFAULT '0' COMMENT '课程状态 0：未审核  1：通过审核  2：未通过审核',
  `type_id` int(11) DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `type_id` (`type_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `type` (`id`),
  CONSTRAINT `course_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '课程名', null, '1', '课程介绍', '2016-09-27 09:46:33', '2016-09-27 09:46:33', null, null, '0', '3', '7');
INSERT INTO `course` VALUES ('2', '课程名', null, '2', '课程介绍', '2016-09-27 09:51:11', '2016-09-27 09:51:11', null, 'pic/76051474941071502.jpg', '0', '5', '7');
INSERT INTO `course` VALUES ('3', '课程名', null, '2', '课程介绍', '2016-09-27 09:52:43', '2016-09-27 09:52:43', null, 'pic/22721474941163307.jpg', '0', '5', '7');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `reciver_id` int(11) DEFAULT NULL,
  `content` longtext,
  PRIMARY KEY (`id`),
  KEY `sender_id` (`sender_id`),
  KEY `reciver_id` (`reciver_id`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`reciver_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for progress
-- ----------------------------
DROP TABLE IF EXISTS `progress`;
CREATE TABLE `progress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chapter_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chapter_id` (`chapter_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `progress_ibfk_1` FOREIGN KEY (`chapter_id`) REFERENCES `chapter` (`id`),
  CONSTRAINT `progress_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of progress
-- ----------------------------

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '类型名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('1', '前端开发');
INSERT INTO `type` VALUES ('2', '后端开发');
INSERT INTO `type` VALUES ('3', 'android开发');
INSERT INTO `type` VALUES ('4', 'ios开发');
INSERT INTO `type` VALUES ('5', '服务器运行维护');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户的昵称',
  `type` tinyint(4) DEFAULT NULL COMMENT '用户类型   1：学员，2：被冻结的学员， 3：待审核教师， 4：未通过审核教师， 5：通过审核的教师， 6：被冻结的教师， 7：管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'user910', null, null, null);
INSERT INTO `user` VALUES ('2', 'user998', null, null, null);
INSERT INTO `user` VALUES ('3', 'user518', null, null, null);
INSERT INTO `user` VALUES ('4', 'aaa', 'asd', null, null);
INSERT INTO `user` VALUES ('5', '123', 'be2cdc95285f0748f252eb8c3ab26bd20b08bc5b7fd725e766c205c6', null, null);
INSERT INTO `user` VALUES ('6', '1234', '34bbaa578b37147dd80054be4d2afd432fef61517ca2d832ecaddb80', null, '1');
INSERT INTO `user` VALUES ('7', 'teacher1', '207b5edb409d30c9bb297033c584f05d2fdb895f3c76b57184c19d9e', null, '3');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `realname` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `card_id` varchar(30) DEFAULT NULL COMMENT '证件号',
  `card_pic` varchar(255) DEFAULT NULL COMMENT '证件照地址',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `education` tinyint(4) DEFAULT NULL COMMENT '学历',
  `company` varchar(255) DEFAULT NULL COMMENT '工作单位',
  `address` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '真实姓名', '证件号', 'pic/33541474872488441.txt', '邮箱', '手机号', '3', '曾工作单位', null, '7');
INSERT INTO `user_info` VALUES ('2', '真实姓名', '证件号', 'pic/10831474872652057.txt', '邮箱', '手机号', '3', '曾工作单位', null, '7');
INSERT INTO `user_info` VALUES ('3', '真实姓名', '证件号', 'pic/36721474873073783.doc', '邮箱', '手机号', '2', '曾工作单位', null, '7');
INSERT INTO `user_info` VALUES ('4', '真实姓名', '证件号', 'pic/96001474873720919.doc', '邮箱', '手机号', '2', '曾工作单位', null, '7');
INSERT INTO `user_info` VALUES ('5', '真实姓名', '证件号', 'pic/14941474873814629.doc', '邮箱', '手机号', '2', '曾工作单位', null, '7');
INSERT INTO `user_info` VALUES ('6', '真实姓名', '证件号', 'pic/28061474873910876.doc', '邮箱', '手机号', '2', '曾工作单位', null, '7');
INSERT INTO `user_info` VALUES ('7', '真实姓名', '证件号', 'pic/63781474874310092.doc', '邮箱', '手机号', '2', '曾工作单位', '', null);
INSERT INTO `user_info` VALUES ('8', '真实姓名', '证件号', 'pic/57571474874333811.doc', '邮箱', '手机号', '2', '曾工作单位', '居住地', null);
SET FOREIGN_KEY_CHECKS=1;
