-- ----------------------------
-- Add Key 'day' to Table preorder_status
-- ----------------------------
ALTER TABLE `preorder_status`
DROP PRIMARY KEY,
ADD PRIMARY KEY (`timeslot_id`, `specialist_id`, `day`);

-- ----------------------------
-- Change Key to 'inquiry_id' to Table inquiry
-- ----------------------------
ALTER TABLE `inquiry`
ADD COLUMN `inquiry_id`  int NOT NULL AUTO_INCREMENT FIRST ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`inquiry_id`);