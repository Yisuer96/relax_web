ALTER TABLE `post_reply`
CHANGE COLUMN `re-reply_id` `re_reply_id`  int(11) NULL DEFAULT '-1' AFTER `post_id`;

ALTER TABLE `article_reply`
CHANGE COLUMN `re-reply_id` `re_reply_id`  int(11) NULL DEFAULT '-1' AFTER `article_id`;