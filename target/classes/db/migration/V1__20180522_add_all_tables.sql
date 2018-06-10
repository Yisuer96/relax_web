-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `specialist_id` int(11) NOT NULL,
  `publish_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(255) DEFAULT '',
  `content` varchar(255) DEFAULT '',
  PRIMARY KEY (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for article_reply
-- ----------------------------
DROP TABLE IF EXISTS `article_reply`;
CREATE TABLE `article_reply` (
  `article_reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `specialist_id` int(11) NOT NULL,
  `article_id` int(11) DEFAULT -1,
  `re-reply_id` int(11) DEFAULT -1,
  `reply_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) DEFAULT '',
  PRIMARY KEY (`article_reply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `comment_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) DEFAULT '',
  `rating` double DEFAULT 0,
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NOT NULL,
  `specialist_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `order_status` int(11) NOT NULL DEFAULT 0,
  `consulting_way` int(11) NOT NULL,
  `publish_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `complete_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `sum` double DEFAULT 0,
  `location` varchar(255) DEFAULT '',
  `description` varchar(255) DEFAULT '',
  `consulting_start_time`  datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `consulting_finish_time`  datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `publish_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `title` varchar(255) DEFAULT '',
  `content` varchar(255) DEFAULT '',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for post_reply
-- ----------------------------
DROP TABLE IF EXISTS `post_reply`;
CREATE TABLE `post_reply` (
  `post_reply_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) DEFAULT -1,
  `re-reply_id` int(11) DEFAULT -1,
  `reply_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `content` varchar(255) DEFAULT '',
  PRIMARY KEY (`post_reply_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for preorder_status
-- ----------------------------
DROP TABLE IF EXISTS `preorder_status`;
CREATE TABLE `preorder_status` (
  `timeslot_id` int(11) NOT NULL,
  `specialist_id` int(11) NOT NULL,
  `is_ordered` tinyint(11) DEFAULT 0,
  `is_free`  tinyint(11) DEFAULT 1,
  `day`  int(11) NOT NULL,
  PRIMARY KEY (`timeslot_id`,`specialist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for specialist
-- ----------------------------
DROP TABLE IF EXISTS `specialist`;
CREATE TABLE `specialist` (
  `specialist_id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` double DEFAULT 5,
  `qualification` varchar(255) DEFAULT '',
  `employ_length` int(11) DEFAULT 0,
  `introduction` varchar(255) DEFAULT '',
  PRIMARY KEY (`specialist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for specialist_type
-- ----------------------------
DROP TABLE IF EXISTS `specialist_type`;
CREATE TABLE `specialist_type` (
  `specialist_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  PRIMARY KEY (`specialist_id`,`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for timeslot
-- ----------------------------
DROP TABLE IF EXISTS `timeslot`;
CREATE TABLE `timeslot` (
  `timeslot_id` int(11) NOT NULL AUTO_INCREMENT,
  `concrete_time` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`timeslot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) DEFAULT '',
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `real_name` varchar(255) DEFAULT '',
  `identity` int(11) NOT NULL DEFAULT 0,
  `remainder` double DEFAULT 0,
  `portrait` varchar(255) DEFAULT '',
  `relax_degree` double DEFAULT 80,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
