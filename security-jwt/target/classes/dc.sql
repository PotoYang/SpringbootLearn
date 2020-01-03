/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.59 : Database - delivery_center
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`delivery_center` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `delivery_center`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(11) DEFAULT NULL,
  `permission_name` varchar(50) DEFAULT NULL,
  `permission_url` varchar(200) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`permission_name`,`permission_url`,`parent_id`) values (1,'首页','/index',0),(2,'内容商管理','/cp',0),(3,'视频管理','/video',0),(4,'报表管理','/statistics',0),(5,'后台用户','/user',0),(6,'系统管理','/sys',0),(101,'系统运行情况','/index/runCondition',1),(102,'媒资数据','/index/mediaStatistics',1),(103,'内容商运行情况','/index/cpCondition',1),(104,'待处理事项','/index/issue',1),(105,'视频上传','/index/upload',1);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL,
  `memo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`memo`) values (1,'系统管理员',''),(2,'内容商','');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `role_id` int(11) DEFAULT NULL,
  `permission_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`role_id`,`permission_id`) values (1,1),(1,2),(1,4),(1,5),(1,6),(2,1),(2,3),(2,4),(2,5),(2,6),(1,101),(1,103),(2,102),(2,104),(2,105);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `login_name` varchar(50) DEFAULT NULL COMMENT '登录名',
  `user_password` char(133) DEFAULT NULL,
  `enabled` tinyint(4) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cp_id` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`user_name`,`login_name`,`user_password`,`enabled`,`email`,`cp_id`,`create_time`,`modify_time`) values (1,'admin','admin','21232f297a57a5a743894a0e4a801fc3',1,'admin@ipanel.cn','','2018-09-04 02:33:28','2018-09-04 02:33:31'),(2,'yangycy','yangycy','e235e9024ec022f542a4951eda08b092',1,'yangycy@ipanel.cn','1','2018-09-04 02:34:02','2018-09-04 02:34:04'),(3,'zhaolei','zhaolei','zhaolei',1,'','2','2018-09-04 02:42:57','2018-09-04 02:43:04');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(2,2),(3,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
