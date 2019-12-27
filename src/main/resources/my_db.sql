-- 创建用户表
CREATE TABLE `user` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR ( 32 ) NOT NULL COMMENT '用户名称',
`birthday` DATETIME DEFAULT NULL COMMENT '生日',
`sex` CHAR ( 1 ) DEFAULT NULL COMMENT '性别',
`address` VARCHAR ( 256 ) DEFAULT NULL COMMENT '地址',
PRIMARY KEY (`id`) 
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 插入用户表
INSERT INTO `user`(`id`,`username`,`birthday`,`sex`,`address`) VALUES(20,'小明','1996-01-01 15:00:00','男','北京'),
(21,'小红','1997-01-01 15:00:00','女','深圳'),
(22,'小李','1998-01-01 15:00:00','男','上海');


-- 测试1对多
CREATE TABLE `user2` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR ( 32 ) NOT NULL COMMENT '用户名称',
`birthday` DATETIME DEFAULT NULL COMMENT '生日',
`sex` CHAR ( 1 ) DEFAULT NULL COMMENT '性别',
`address` VARCHAR ( 256 ) DEFAULT NULL COMMENT '地址',
PRIMARY KEY (`id`) 
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 插入用户表
INSERT INTO `user2`(`id`,`username`,`birthday`,`sex`,`address`) VALUES(20,'小明','1996-01-01 15:00:00','男','北京'),
(21,'小红','1997-01-01 15:00:00','女','深圳'),
(22,'小李','1998-01-01 15:00:00','男','上海');

-- 创建账户表
CREATE TABLE `account2` (
`id` INT NOT NULL,
`uid` INT NOT NULL,
`money` DOUBLE DEFAULT NULL,
PRIMARY KEY (`id`),
CONSTRAINT fk_name FOREIGN KEY (`uid`) REFERENCES `user2`(`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

InSERT INTO `account2`(`id`,`uid`,`money`)VALUES(1,20,1000),(2,20,3000),(3,22,200);


-- 以下为测试多对多
CREATE TABLE `user3` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR ( 32 ) NOT NULL COMMENT '用户名称',
`birthday` DATETIME DEFAULT NULL COMMENT '生日',
`sex` CHAR ( 1 ) DEFAULT NULL COMMENT '性别',
`address` VARCHAR ( 256 ) DEFAULT NULL COMMENT '地址',
PRIMARY KEY (`id`) 
) ENGINE = INNODB DEFAULT CHARSET = utf8;

-- 插入用户表
INSERT INTO `user3`(`id`,`username`,`birthday`,`sex`,`address`) VALUES(20,'小明','1996-01-01 15:00:00','男','北京'),
(21,'小红','1997-01-01 15:00:00','女','深圳'),
(22,'小李','1998-01-01 15:00:00','男','上海');

-- 角色表
CREATE TABLE `role`(
    `id` INT NOT NULL,
    `name` VARCHAR ( 32 ) NOT NULL COMMENT '角色名称',
    `description` VARCHAR ( 256 ) DEFAULT NULL,
    PRIMARY KEY (`id`) 
) ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO `role`(`id`,`name`,`description`)VALUES(1,'校长','管理学校'),(2,'院长','管理学院'),(3,'总经理','管理公司');

CREATE TABLE `user_role`(
    `uid` INT NOT NULL COMMENT '用户id',
    `rid` INT NOT NULL COMMENT '角色id',
    PRIMARY KEY(`uid`,`rid`),
    CONSTRAINT fk_name1 FOREIGN KEY (`uid`) REFERENCES `user3`(`id`),
    CONSTRAINT fk_name2 FOREIGN KEY (`rid`) REFERENCES `role`(`id`)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO `user_role`(`uid`,`rid`)VALUES(20,1),(20,3),(21,2);