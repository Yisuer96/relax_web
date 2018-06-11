-- ----------------------------
-- Table structure for inquiry
-- ----------------------------
DROP TABLE IF EXISTS `inquiry`;
CREATE TABLE `inquiry` (
`sender_id`  int(11) NOT NULL ,
`receiver_id`  int(11) NOT NULL ,
`message`  varchar(255) DEFAULT '' ,
`send_time`  datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP ,
PRIMARY KEY (`sender_id`, `receiver_id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Add Column order_num for Table specialist
-- ----------------------------
ALTER TABLE `specialist`
ADD COLUMN `order_num`  int NOT NULL DEFAULT 0 AFTER `introduction`;