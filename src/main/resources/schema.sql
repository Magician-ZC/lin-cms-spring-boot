/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.7.39-log : Database - lin-cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lin-cms` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `lin-cms`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  `author` varchar(30) DEFAULT NULL,
  `summary` varchar(1000) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `book` */

insert  into `book`(`id`,`title`,`author`,`summary`,`image`,`create_time`,`update_time`,`delete_time`,`is_deleted`) values (1,'深入理解计算机系统','Randal E.Bryant','从程序员的视角，看计算机系统！\n本书适用于那些想要写出更快、更可靠程序的程序员。通过掌握程序是如何映射到系统上，以及程序是如何执行的，读者能够更好的理解程序的行为为什么是这样的，以及效率低下是如何造成的。','https://img3.doubanio.com/lpic/s1470003.jpg','2022-12-01 14:57:23.528','2022-12-02 18:33:29.270',NULL,1),(2,'C程序设计语言','（美）Brian W. Kernighan','在计算机发展的历史上，没有哪一种程序设计语言像C语言这样应用广泛。本书原著即为C语言的设计者之一Dennis M.Ritchie和著名计算机科学家Brian W.Kernighan合著的一本介绍C语言的权威经典著作。','https://img3.doubanio.com/lpic/s1106934.jpg','2022-12-01 14:57:23.536','2022-12-01 14:57:23.536',NULL,0);

/*Table structure for table `device` */

DROP TABLE IF EXISTS `device`;

CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_id` varchar(50) NOT NULL,
  `device_tag` varchar(50) DEFAULT NULL,
  `online` int(30) DEFAULT NULL,
  `task_status` int(30) DEFAULT NULL,
  `version` int(30) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `device` */

insert  into `device`(`id`,`device_id`,`device_tag`,`online`,`task_status`,`version`,`create_time`,`update_time`,`delete_time`,`is_deleted`) values (1,'123','321',0,0,1,'2022-12-02 15:28:14.215','2022-12-02 18:36:43.126',NULL,0),(2,'1234','1234',1,1,1,'2022-12-02 19:58:36.227','2022-12-02 19:58:36.227',NULL,0);

/*Table structure for table `ins_account_info` */

DROP TABLE IF EXISTS `ins_account_info`;

CREATE TABLE `ins_account_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` int(30) DEFAULT NULL,
  `task_status` int(30) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `ins_account_info` */

/*Table structure for table `ins_send_user_info` */

DROP TABLE IF EXISTS `ins_send_user_info`;

CREATE TABLE `ins_send_user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `status` int(30) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `ins_send_user_info` */

/*Table structure for table `lin_file` */

DROP TABLE IF EXISTS `lin_file`;

CREATE TABLE `lin_file` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `path` varchar(500) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'LOCAL' COMMENT 'LOCAL 本地，REMOTE 远程',
  `name` varchar(100) NOT NULL,
  `extension` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  `md5` varchar(40) DEFAULT NULL COMMENT 'md5值，防止上传重复文件',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `md5_del` (`md5`,`delete_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_file` */

/*Table structure for table `lin_group` */

DROP TABLE IF EXISTS `lin_group`;

CREATE TABLE `lin_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '分组名称，例如：搬砖者',
  `info` varchar(255) DEFAULT NULL COMMENT '分组信息：例如：搬砖的人',
  `level` tinyint(2) NOT NULL DEFAULT '3' COMMENT '分组级别 1：root 2：guest 3：user  root（root、guest分组只能存在一个)',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_del` (`name`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_group` */

insert  into `lin_group`(`id`,`name`,`info`,`level`,`create_time`,`update_time`,`delete_time`,`is_deleted`) values (1,'root','超级用户组',1,'2022-12-01 14:57:23.629','2022-12-01 14:57:23.629',NULL,0),(2,'guest','游客组',2,'2022-12-01 14:57:23.632','2022-12-01 14:57:23.632',NULL,0);

/*Table structure for table `lin_group_permission` */

DROP TABLE IF EXISTS `lin_group_permission`;

CREATE TABLE `lin_group_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `group_id` int(10) unsigned NOT NULL COMMENT '分组id',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`),
  KEY `group_id_permission_id` (`group_id`,`permission_id`) USING BTREE COMMENT '联合索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_group_permission` */

/*Table structure for table `lin_log` */

DROP TABLE IF EXISTS `lin_log`;

CREATE TABLE `lin_log` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `message` varchar(450) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  `username` varchar(24) DEFAULT NULL,
  `status_code` int(11) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  `path` varchar(50) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_log` */

/*Table structure for table `lin_permission` */

DROP TABLE IF EXISTS `lin_permission`;

CREATE TABLE `lin_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(60) NOT NULL COMMENT '权限名称，例如：访问首页',
  `module` varchar(50) NOT NULL COMMENT '权限所属模块，例如：人员管理',
  `mount` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0：关闭 1：开启',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_permission` */

insert  into `lin_permission`(`id`,`name`,`module`,`mount`,`create_time`,`update_time`,`delete_time`,`is_deleted`) values (1,'查询日志记录的用户','日志',1,'2022-12-01 14:58:59.633','2022-12-01 14:58:59.633',NULL,0),(2,'查询所有日志','日志',1,'2022-12-01 14:58:59.685','2022-12-01 14:58:59.685',NULL,0),(3,'搜索日志','日志',1,'2022-12-01 14:58:59.710','2022-12-01 14:58:59.710',NULL,0),(4,'删除图书','图书',1,'2022-12-01 14:58:59.771','2022-12-01 14:58:59.771',NULL,0),(5,'删除设备','设备',1,'2022-12-02 15:26:27.569','2022-12-02 15:26:27.569',NULL,0);

/*Table structure for table `lin_user` */

DROP TABLE IF EXISTS `lin_user`;

CREATE TABLE `lin_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(24) NOT NULL COMMENT '用户名，唯一',
  `nickname` varchar(24) DEFAULT NULL COMMENT '用户昵称',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像url',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_del` (`username`,`delete_time`),
  UNIQUE KEY `email_del` (`email`,`delete_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_user` */

insert  into `lin_user`(`id`,`username`,`nickname`,`avatar`,`email`,`create_time`,`update_time`,`delete_time`,`is_deleted`) values (1,'root','root',NULL,NULL,'2022-12-01 14:57:23.596','2022-12-01 14:57:23.596',NULL,0),(2,'123',NULL,NULL,'123@123.com','2022-12-01 15:43:05.656','2022-12-01 15:43:05.656',NULL,0);

/*Table structure for table `lin_user_group` */

DROP TABLE IF EXISTS `lin_user_group`;

CREATE TABLE `lin_user_group` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `group_id` int(10) unsigned NOT NULL COMMENT '分组id',
  PRIMARY KEY (`id`),
  KEY `user_id_group_id` (`user_id`,`group_id`) USING BTREE COMMENT '联合索引'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_user_group` */

insert  into `lin_user_group`(`id`,`user_id`,`group_id`) values (1,1,1),(2,2,2);

/*Table structure for table `lin_user_identity` */

DROP TABLE IF EXISTS `lin_user_identity`;

CREATE TABLE `lin_user_identity` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `identity_type` varchar(100) NOT NULL,
  `identifier` varchar(100) DEFAULT NULL,
  `credential` varchar(100) DEFAULT NULL,
  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `delete_time` datetime(3) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `lin_user_identity` */

insert  into `lin_user_identity`(`id`,`user_id`,`identity_type`,`identifier`,`credential`,`create_time`,`update_time`,`delete_time`,`is_deleted`) values (1,1,'USERNAME_PASSWORD','root','pbkdf2sha256:64000:18:24:n:yUnDokcNRbwILZllmUOItIyo9MnI00QW:6ZcPf+sfzyoygOU8h/GSoirF','2022-12-01 14:57:23.624','2022-12-01 14:57:23.624',NULL,0),(2,2,'USERNAME_PASSWORD','123','pbkdf2sha256:64000:18:24:n:97X1bseI0ORCe61n24Oj/OTGZnW9oZAo:HwZrEDx3KdFffjmi2cjItNiO','2022-12-01 15:43:05.766','2022-12-01 15:43:05.766',NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
