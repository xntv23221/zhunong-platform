-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: zhunong79djy629
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `zhunong79djy629`
--

/*!40000 DROP DATABASE IF EXISTS `zhunong79djy629`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `zhunong79djy629` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `zhunong79djy629`;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `address` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '地址',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '收货人',
  `phone` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '电话',
  `isdefault` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '是否默认地址[是/否]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='地址';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` VALUES (1,'2026-01-15 13:42:49',11,'宇宙银河系金星1号','金某','13823888881','是'),(2,'2026-01-15 13:42:49',12,'宇宙银河系木星1号','木某','13823888882','是'),(3,'2026-01-15 13:42:49',13,'宇宙银河系水星1号','水某','13823888883','是'),(4,'2026-01-15 13:42:49',14,'宇宙银河系火星1号','火某','13823888884','是'),(5,'2026-01-15 13:42:49',15,'宇宙银河系土星1号','土某','13823888885','是'),(6,'2026-01-15 13:42:49',16,'宇宙银河系月球1号','月某','13823888886','是'),(7,'2026-01-15 13:42:49',17,'宇宙银河系黑洞1号','黑某','13823888887','是'),(8,'2026-01-15 13:42:49',18,'宇宙银河系地球1号','地某','13823888888','是');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caigoupeisong`
--

DROP TABLE IF EXISTS `caigoupeisong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caigoupeisong` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuanliaomingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料名称',
  `yuanliaofenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料分类',
  `yuanliaozhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '原料照片',
  `youxiaochengfen` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '有效成分',
  `jiage` double DEFAULT NULL COMMENT '价格',
  `kucun` int(11) DEFAULT NULL COMMENT '采购数量',
  `caigouzongjia` double DEFAULT NULL COMMENT '采购总价',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `peisongshijian` datetime DEFAULT NULL COMMENT '配送时间',
  `peisongbeizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '配送备注',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农民账号',
  `peisongzhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '配送状态',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='采购配送';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caigoupeisong`
--

LOCK TABLES `caigoupeisong` WRITE;
/*!40000 ALTER TABLE `caigoupeisong` DISABLE KEYS */;
INSERT INTO `caigoupeisong` VALUES (1,'2026-01-15 13:42:49','绿盛芹菜杀菌剂','杀菌剂','upload/caigoupeisong_绿盛芹菜杀菌剂1.jpg,upload/caigoupeisong_绿盛芹菜杀菌剂2.jpg,upload/caigoupeisong_绿盛芹菜杀菌剂3.jpg','百菌清75%',37,182,1,'南京大学','2026-01-15 21:42:49','配送备注1','101','配送中',1,1),(2,'2026-01-15 13:42:49','鑫达葡萄专用肥','复合肥','upload/caigoupeisong_鑫达葡萄专用肥1.jpg,upload/caigoupeisong_鑫达葡萄专用肥2.jpg,upload/caigoupeisong_鑫达葡萄专用肥3.jpg','氮磷钾10-15-20',78,92,2,'山东大学','2026-01-15 21:42:49','配送备注2','102','配送中',2,2),(3,'2026-01-15 13:42:49','绿源白菜杀菌剂','杀菌剂','upload/caigoupeisong_绿源白菜杀菌剂1.jpg,upload/caigoupeisong_绿源白菜杀菌剂2.jpg,upload/caigoupeisong_绿源白菜杀菌剂3.jpg','甲基硫菌灵50%',44,195,3,'吉林大学','2026-01-15 21:42:49','配送备注3','103','配送中',3,3),(4,'2026-01-15 13:42:49','金禾油菜种子','种子','upload/caigoupeisong_金禾油菜种子1.jpg,upload/caigoupeisong_金禾油菜种子2.jpg,upload/caigoupeisong_金禾油菜种子3.jpg','双低油菜杂交种',39,167,4,'华中科技大学','2026-01-15 21:42:49','配送备注4','105','配送中',4,4),(5,'2026-01-15 13:42:49','锐锋辣椒杀虫剂','杀虫剂','upload/caigoupeisong_锐锋辣椒杀虫剂1.jpg,upload/caigoupeisong_锐锋辣椒杀虫剂2.jpg,upload/caigoupeisong_锐锋辣椒杀虫剂3.jpg','噻虫嗪25%',49,89,5,'复旦大学','2026-01-15 21:42:49','配送备注5','108','配送中',5,5),(6,'2026-01-15 13:42:49','德丰红薯专用肥','复合肥','upload/caigoupeisong_德丰红薯专用肥1.jpg,upload/caigoupeisong_德丰红薯专用肥2.jpg,upload/caigoupeisong_德丰红薯专用肥3.jpg','氮磷钾8-12-20',38,191,6,'清华大学','2026-01-15 21:42:49','配送备注6','106','配送中',6,6),(7,'2026-01-15 13:42:49','益农番茄叶面肥','叶面肥','upload/caigoupeisong_益农番茄叶面肥1.jpg,upload/caigoupeisong_益农番茄叶面肥2.jpg,upload/caigoupeisong_益农番茄叶面肥3.jpg','氨基酸18%',55,120,7,'上海交通大学','2026-01-15 21:42:49','配送备注7','104','配送中',7,7),(8,'2026-01-15 13:42:49','速壮甜瓜叶面肥','叶面肥','upload/caigoupeisong_速壮甜瓜叶面肥1.jpg,upload/caigoupeisong_速壮甜瓜叶面肥2.jpg,upload/caigoupeisong_速壮甜瓜叶面肥3.jpg','腐殖酸叶面肥',75,88,8,'天津大学','2026-01-15 21:42:49','配送备注8','107','配送中',8,8);
/*!40000 ALTER TABLE `caigoupeisong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caigoupingjia`
--

DROP TABLE IF EXISTS `caigoupingjia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caigoupingjia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuanliaomingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料名称',
  `yuanliaofenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料分类',
  `yuanliaozhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '原料照片',
  `youxiaochengfen` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '有效成分',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `pingjiazhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '评价状态',
  `pingjiashijian` datetime DEFAULT NULL COMMENT '评价时间',
  `pingjianeirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '评价内容',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农民账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='采购评价';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caigoupingjia`
--

LOCK TABLES `caigoupingjia` WRITE;
/*!40000 ALTER TABLE `caigoupingjia` DISABLE KEYS */;
INSERT INTO `caigoupingjia` VALUES (1,'2026-01-15 13:42:49','绿盛芹菜杀菌剂','杀菌剂','upload/caigoupingjia_绿盛芹菜杀菌剂1.jpg,upload/caigoupingjia_绿盛芹菜杀菌剂2.jpg,upload/caigoupingjia_绿盛芹菜杀菌剂3.jpg','百菌清75%','南京大学','优','2026-01-15 21:42:49','评价内容1','101'),(2,'2026-01-15 13:42:49','鑫达葡萄专用肥','复合肥','upload/caigoupingjia_鑫达葡萄专用肥1.jpg,upload/caigoupingjia_鑫达葡萄专用肥2.jpg,upload/caigoupingjia_鑫达葡萄专用肥3.jpg','氮磷钾10-15-20','山东大学','优','2026-01-15 21:42:49','评价内容2','102'),(3,'2026-01-15 13:42:49','绿源白菜杀菌剂','杀菌剂','upload/caigoupingjia_绿源白菜杀菌剂1.jpg,upload/caigoupingjia_绿源白菜杀菌剂2.jpg,upload/caigoupingjia_绿源白菜杀菌剂3.jpg','甲基硫菌灵50%','吉林大学','优','2026-01-15 21:42:49','评价内容3','103'),(4,'2026-01-15 13:42:49','金禾油菜种子','种子','upload/caigoupingjia_金禾油菜种子1.jpg,upload/caigoupingjia_金禾油菜种子2.jpg,upload/caigoupingjia_金禾油菜种子3.jpg','双低油菜杂交种','华中科技大学','优','2026-01-15 21:42:49','评价内容4','105'),(5,'2026-01-15 13:42:49','锐锋辣椒杀虫剂','杀虫剂','upload/caigoupingjia_锐锋辣椒杀虫剂1.jpg,upload/caigoupingjia_锐锋辣椒杀虫剂2.jpg,upload/caigoupingjia_锐锋辣椒杀虫剂3.jpg','噻虫嗪25%','复旦大学','优','2026-01-15 21:42:49','评价内容5','108'),(6,'2026-01-15 13:42:49','德丰红薯专用肥','复合肥','upload/caigoupingjia_德丰红薯专用肥1.jpg,upload/caigoupingjia_德丰红薯专用肥2.jpg,upload/caigoupingjia_德丰红薯专用肥3.jpg','氮磷钾8-12-20','清华大学','优','2026-01-15 21:42:49','评价内容6','106'),(7,'2026-01-15 13:42:49','益农番茄叶面肥','叶面肥','upload/caigoupingjia_益农番茄叶面肥1.jpg,upload/caigoupingjia_益农番茄叶面肥2.jpg,upload/caigoupingjia_益农番茄叶面肥3.jpg','氨基酸18%','上海交通大学','优','2026-01-15 21:42:49','评价内容7','104'),(8,'2026-01-15 13:42:49','速壮甜瓜叶面肥','叶面肥','upload/caigoupingjia_速壮甜瓜叶面肥1.jpg,upload/caigoupingjia_速壮甜瓜叶面肥2.jpg,upload/caigoupingjia_速壮甜瓜叶面肥3.jpg','腐殖酸叶面肥','天津大学','优','2026-01-15 21:42:49','评价内容8','107');
/*!40000 ALTER TABLE `caigoupingjia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caigoushouhuo`
--

DROP TABLE IF EXISTS `caigoushouhuo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `caigoushouhuo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuanliaomingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料名称',
  `yuanliaofenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料分类',
  `yuanliaozhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '原料照片',
  `youxiaochengfen` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '有效成分',
  `jiage` double DEFAULT NULL COMMENT '价格',
  `kucun` int(11) DEFAULT NULL COMMENT '采购数量',
  `caigouzongjia` double DEFAULT NULL COMMENT '采购总价',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `shouhuoshijian` datetime DEFAULT NULL COMMENT '收货时间',
  `shouhuobeizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '收货备注',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农民账号',
  `crossuserid` bigint(20) DEFAULT NULL COMMENT '跨表用户id',
  `crossrefid` bigint(20) DEFAULT NULL COMMENT '跨表主键id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='采购收货';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caigoushouhuo`
--

LOCK TABLES `caigoushouhuo` WRITE;
/*!40000 ALTER TABLE `caigoushouhuo` DISABLE KEYS */;
INSERT INTO `caigoushouhuo` VALUES (1,'2026-01-15 13:42:49','绿盛芹菜杀菌剂','杀菌剂','upload/caigoushouhuo_绿盛芹菜杀菌剂1.jpg,upload/caigoushouhuo_绿盛芹菜杀菌剂2.jpg,upload/caigoushouhuo_绿盛芹菜杀菌剂3.jpg','百菌清75%',37,182,1,'南京大学','2026-01-15 21:42:49','收货备注1','101',1,1),(2,'2026-01-15 13:42:49','鑫达葡萄专用肥','复合肥','upload/caigoushouhuo_鑫达葡萄专用肥1.jpg,upload/caigoushouhuo_鑫达葡萄专用肥2.jpg,upload/caigoushouhuo_鑫达葡萄专用肥3.jpg','氮磷钾10-15-20',78,92,2,'山东大学','2026-01-15 21:42:49','收货备注2','102',2,2),(3,'2026-01-15 13:42:49','绿源白菜杀菌剂','杀菌剂','upload/caigoushouhuo_绿源白菜杀菌剂1.jpg,upload/caigoushouhuo_绿源白菜杀菌剂2.jpg,upload/caigoushouhuo_绿源白菜杀菌剂3.jpg','甲基硫菌灵50%',44,195,3,'吉林大学','2026-01-15 21:42:49','收货备注3','103',3,3),(4,'2026-01-15 13:42:49','金禾油菜种子','种子','upload/caigoushouhuo_金禾油菜种子1.jpg,upload/caigoushouhuo_金禾油菜种子2.jpg,upload/caigoushouhuo_金禾油菜种子3.jpg','双低油菜杂交种',39,167,4,'华中科技大学','2026-01-15 21:42:49','收货备注4','105',4,4),(5,'2026-01-15 13:42:49','锐锋辣椒杀虫剂','杀虫剂','upload/caigoushouhuo_锐锋辣椒杀虫剂1.jpg,upload/caigoushouhuo_锐锋辣椒杀虫剂2.jpg,upload/caigoushouhuo_锐锋辣椒杀虫剂3.jpg','噻虫嗪25%',49,89,5,'复旦大学','2026-01-15 21:42:49','收货备注5','108',5,5),(6,'2026-01-15 13:42:49','德丰红薯专用肥','复合肥','upload/caigoushouhuo_德丰红薯专用肥1.jpg,upload/caigoushouhuo_德丰红薯专用肥2.jpg,upload/caigoushouhuo_德丰红薯专用肥3.jpg','氮磷钾8-12-20',38,191,6,'清华大学','2026-01-15 21:42:49','收货备注6','106',6,6),(7,'2026-01-15 13:42:49','益农番茄叶面肥','叶面肥','upload/caigoushouhuo_益农番茄叶面肥1.jpg,upload/caigoushouhuo_益农番茄叶面肥2.jpg,upload/caigoushouhuo_益农番茄叶面肥3.jpg','氨基酸18%',55,120,7,'上海交通大学','2026-01-15 21:42:49','收货备注7','104',7,7),(8,'2026-01-15 13:42:49','速壮甜瓜叶面肥','叶面肥','upload/caigoushouhuo_速壮甜瓜叶面肥1.jpg,upload/caigoushouhuo_速壮甜瓜叶面肥2.jpg,upload/caigoushouhuo_速壮甜瓜叶面肥3.jpg','腐殖酸叶面肥',75,88,8,'天津大学','2026-01-15 21:42:49','收货备注8','107',8,8);
/*!40000 ALTER TABLE `caigoushouhuo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT 'nongchanpinxinxi' COMMENT '商品表名',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `buynumber` int(11) NOT NULL COMMENT '购买数量',
  `price` double DEFAULT NULL COMMENT '单价',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商户名称',
  `goodtype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '类型',
  PRIMARY KEY (`id`),
  KEY `price` (`price`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`price`) REFERENCES `nongchanpinxinxi` (`price`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='购物车表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chargerecord`
--

DROP TABLE IF EXISTS `chargerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chargerecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色',
  `amount` double NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='充值记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chargerecord`
--

LOCK TABLES `chargerecord` WRITE;
/*!40000 ALTER TABLE `chargerecord` DISABLE KEYS */;
INSERT INTO `chargerecord` VALUES (1,'2026-01-15 13:42:49',1,'用户名1','角色1',1),(2,'2026-01-15 13:42:49',2,'用户名2','角色2',2),(3,'2026-01-15 13:42:49',3,'用户名3','角色3',3),(4,'2026-01-15 13:42:49',4,'用户名4','角色4',4),(5,'2026-01-15 13:42:49',5,'用户名5','角色5',5),(6,'2026-01-15 13:42:49',6,'用户名6','角色6',6),(7,'2026-01-15 13:42:49',7,'用户名7','角色7',7),(8,'2026-01-15 13:42:49',8,'用户名8','角色8',8);
/*!40000 ALTER TABLE `chargerecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(200) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  `type` int(11) DEFAULT NULL COMMENT '参数类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL,1),(2,'picture2','upload/picture2.jpg',NULL,1),(3,'picture3','upload/picture3.jpg',NULL,1),(11,'baidu','{\"appId\":\"49214550\",\"apiKey\":\"7Otjpv2kn0ljQk45qXOXh5MO\",\"secretKey\":\"BMfbXRbTIVaB4C3SbRTtGqDv1wHDvyXS\"}',NULL,2),(21,'bLoginBackgroundImg','',NULL,3),(22,'bRegisterBackgroundImg','',NULL,3),(23,'bIndexBackgroundImg','',NULL,3),(24,'bTopLogo','upload/project-logo.png',NULL,3),(25,'bHomeLogo','upload/project-logo.png',NULL,3),(26,'fLoginBackgroundImg','',NULL,3),(27,'fRegisterBackgroudImg','',NULL,3),(28,'fTopLogo','upload/project-logo.png',NULL,3);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussjishuzhishiku`
--

DROP TABLE IF EXISTS `discussjishuzhishiku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussjishuzhishiku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='技术知识库评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussjishuzhishiku`
--

LOCK TABLES `discussjishuzhishiku` WRITE;
/*!40000 ALTER TABLE `discussjishuzhishiku` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussjishuzhishiku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discussnongchanpinxinxi`
--

DROP TABLE IF EXISTS `discussnongchanpinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `discussnongchanpinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `refid` bigint(20) NOT NULL COMMENT '关联表id',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `avatarurl` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `nickname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复内容',
  `thumbsupnum` int(11) DEFAULT '0' COMMENT '赞',
  `crazilynum` int(11) DEFAULT '0' COMMENT '踩',
  `istop` int(11) DEFAULT '0' COMMENT '置顶(1:置顶,0:非置顶)',
  `tuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '赞用户ids',
  `cuserids` longtext COLLATE utf8mb4_unicode_ci COMMENT '踩用户ids',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='农产品信息评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discussnongchanpinxinxi`
--

LOCK TABLES `discussnongchanpinxinxi` WRITE;
/*!40000 ALTER TABLE `discussnongchanpinxinxi` DISABLE KEYS */;
/*!40000 ALTER TABLE `discussnongchanpinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gaoxiao`
--

DROP TABLE IF EXISTS `gaoxiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gaoxiao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gaoxiaomingcheng` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '高校名称',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `gaoxiaodizhi` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校地址',
  `gaoxiaodianhua` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校电话',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '高校封面',
  `gaoxiaojianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '高校简介',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `gaoxiaomingcheng` (`gaoxiaomingcheng`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='高校';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gaoxiao`
--

LOCK TABLES `gaoxiao` WRITE;
/*!40000 ALTER TABLE `gaoxiao` DISABLE KEYS */;
INSERT INTO `gaoxiao` VALUES (31,'2026-01-15 13:42:48','南京大学','123456','江苏省南京市鼓楼区','13023456789','upload/gaoxiao_南京大学1.jpg,upload/gaoxiao_南京大学2.jpg,upload/gaoxiao_南京大学3.jpg','历史悠久的综合性大学文科和理科实力突出天文学物理学等学科在国内外享有盛誉学术氛围严谨',200),(32,'2026-01-15 13:42:48','山东大学','123456','山东省济南市历下区','15012345678','upload/gaoxiao_山东大学1.jpg,upload/gaoxiao_山东大学2.jpg,upload/gaoxiao_山东大学3.jpg','位于济南的综合性高校数学材料医学等学科实力强劲注重培养学生的综合素质和创新能力',200),(33,'2026-01-15 13:42:48','吉林大学','123456','吉林省长春市南关区','13290123456','upload/gaoxiao_吉林大学1.jpg,upload/gaoxiao_吉林大学2.jpg,upload/gaoxiao_吉林大学3.jpg','位于长春的综合性大学学科覆盖广车辆工程化学等学科实力突出为东北地区经济社会发展培养大量人才',200),(34,'2026-01-15 13:42:48','华中科技大学','123456','湖北省武汉市洪山区','13012345678','upload/gaoxiao_华中科技大学1.jpg,upload/gaoxiao_华中科技大学2.jpg,upload/gaoxiao_华中科技大学3.jpg','以工科和医学为特色的综合性大学机械光学医学等学科实力强劲注重培养学生的工程实践能力',200),(35,'2026-01-15 13:42:48','复旦大学','123456','上海市杨浦区','15178901234','upload/gaoxiao_复旦大学1.jpg,upload/gaoxiao_复旦大学2.jpg,upload/gaoxiao_复旦大学3.jpg','位于上海的顶尖高校历史悠久学术氛围浓厚医学文科理科等学科实力强劲国际交流频繁',200),(36,'2026-01-15 13:42:48','清华大学','123456','北京市海淀区','13567890123','upload/gaoxiao_清华大学1.jpg,upload/gaoxiao_清华大学2.jpg,upload/gaoxiao_清华大学3.jpg','中国著名高等学府工科实力位居全国前列注重产学研结合培养了大量工程技术和管理人才',200),(37,'2026-01-15 13:42:48','上海交通大学','123456','上海市闵行区','13276543210','upload/gaoxiao_上海交通大学1.jpg,upload/gaoxiao_上海交通大学2.jpg,upload/gaoxiao_上海交通大学3.jpg','中国一流综合性大学工科和医学领域处于领先地位科研设施先进注重培养学生的实践能力和创新精神',200),(38,'2026-01-15 13:42:48','天津大学','123456','天津市南开区','13987654321','upload/gaoxiao_天津大学1.jpg,upload/gaoxiao_天津大学2.jpg,upload/gaoxiao_天津大学3.jpg','中国著名工科高校化学工程建筑学等学科处于全国领先水平注重培养学生的工程实践能力和创新精神',200);
/*!40000 ALTER TABLE `gaoxiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jishuzhishiku`
--

DROP TABLE IF EXISTS `jishuzhishiku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jishuzhishiku` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhishimingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '知识名称',
  `zhishifenlei` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '知识分类',
  `zhuanyelingyu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业领域',
  `zhishizhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '知识照片',
  `zhishifujian` longtext COLLATE utf8mb4_unicode_ci COMMENT '知识附件',
  `zhishishipin` longtext COLLATE utf8mb4_unicode_ci COMMENT '知识视频',
  `zhishineirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '知识内容',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='技术知识库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jishuzhishiku`
--

LOCK TABLES `jishuzhishiku` WRITE;
/*!40000 ALTER TABLE `jishuzhishiku` DISABLE KEYS */;
INSERT INTO `jishuzhishiku` VALUES (1,'2026-01-15 13:42:48','小麦秸秆还田腐熟技术','土壤改良技术','粮食作物种植','upload/jishuzhishiku_小麦秸秆还田腐熟技术1.jpg,upload/jishuzhishiku_小麦秸秆还田腐熟技术2.jpg,upload/jishuzhishiku_小麦秸秆还田腐熟技术3.jpg','','','小麦收获后将秸秆粉碎还田，配合腐熟剂加速秸秆分解，增加土壤有机质含量，改善土壤结构，减少秸秆焚烧污染','南京大学',0,1),(2,'2026-01-15 13:42:48','玉米病虫害绿色防控集成技术','植保技术','粮食作物种植','upload/jishuzhishiku_玉米病虫害绿色防控集成技术1.jpg,upload/jishuzhishiku_玉米病虫害绿色防控集成技术2.jpg,upload/jishuzhishiku_玉米病虫害绿色防控集成技术3.jpg','','','整合物理、生物、农业防控手段，替代传统化学防治，减少农药残留，保障玉米安全生产，提升产品竞争力','山东大学',0,2),(3,'2026-01-15 13:42:48','果树花期防冻害技术','防灾减灾技术','果树种植','upload/jishuzhishiku_果树花期防冻害技术1.jpg,upload/jishuzhishiku_果树花期防冻害技术2.jpg,upload/jishuzhishiku_果树花期防冻害技术3.jpg','','','在果树花期遭遇低温时，采用物理防护和化学调控结合的方式，减轻冻害对花器的损伤，保障坐果率，减少产量损失','吉林大学',0,3),(4,'2026-01-15 13:42:48','生猪精准营养饲喂技术','养殖技术','畜牧养殖','upload/jishuzhishiku_生猪精准营养饲喂技术1.jpg,upload/jishuzhishiku_生猪精准营养饲喂技术2.jpg,upload/jishuzhishiku_生猪精准营养饲喂技术3.jpg','','','根据生猪不同生长阶段的营养需求，精准配制全价饲料，按需饲喂，提高饲料转化率，降低养殖成本，缩短出栏周期','华中科技大学',0,4),(5,'2026-01-15 13:42:48','果树病虫害生物防治技术','植保技术','果树种植','upload/jishuzhishiku_果树病虫害生物防治技术1.jpg,upload/jishuzhishiku_果树病虫害生物防治技术2.jpg,upload/jishuzhishiku_果树病虫害生物防治技术3.jpg','','','利用赤眼蜂、瓢虫等天敌昆虫，结合生物农药和诱虫植物，构建生态防控体系，减少化学农药使用，保障果实质量安全','复旦大学',0,5),(6,'2026-01-15 13:42:48','畜禽粪污沼气发酵技术','废弃物资源化技术','循环农业','upload/jishuzhishiku_畜禽粪污沼气发酵技术1.jpg,upload/jishuzhishiku_畜禽粪污沼气发酵技术2.jpg,upload/jishuzhishiku_畜禽粪污沼气发酵技术3.jpg','','','将畜禽粪污接入沼气池，经微生物发酵产生沼气用于照明供暖，沼渣沼液作为有机肥料还田，实现粪污零排放和资源循环','清华大学',0,6),(7,'2026-01-15 13:42:48','果树根域限制栽培技术','栽培技术','果树种植','upload/jishuzhishiku_果树根域限制栽培技术1.jpg,upload/jishuzhishiku_果树根域限制栽培技术2.jpg,upload/jishuzhishiku_果树根域限制栽培技术3.jpg','','','通过容器或物理屏障限制果树根系生长范围，精准控制水肥供应，促进花芽分化，提高果实含糖量，适配葡萄、柑橘等果树','上海交通大学',0,7),(8,'2026-01-15 13:42:48','蔬菜集约化育苗技术','育苗技术','蔬菜种植','upload/jishuzhishiku_蔬菜集约化育苗技术1.jpg,upload/jishuzhishiku_蔬菜集约化育苗技术2.jpg,upload/jishuzhishiku_蔬菜集约化育苗技术3.jpg','','','采用穴盘育苗方式，配合基质消毒和精准环境调控，培育壮苗，提高移栽成活率，缩短田间生长周期，适配规模化蔬菜生产','天津大学',0,8);
/*!40000 ALTER TABLE `jishuzhishiku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nongchanpinleixing`
--

DROP TABLE IF EXISTS `nongchanpinleixing`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nongchanpinleixing` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `nongchanpinleixing` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '农产品类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nongchanpinleixing` (`nongchanpinleixing`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='农产品类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nongchanpinleixing`
--

LOCK TABLES `nongchanpinleixing` WRITE;
/*!40000 ALTER TABLE `nongchanpinleixing` DISABLE KEYS */;
INSERT INTO `nongchanpinleixing` VALUES (1,'2026-01-15 13:42:48','水果类'),(2,'2026-01-15 13:42:48','禽蛋制品'),(3,'2026-01-15 13:42:48','坚果干货'),(4,'2026-01-15 13:42:48','调味香料'),(5,'2026-01-15 13:42:48','粮食谷物'),(6,'2026-01-15 13:42:48','蔬菜类'),(7,'2026-01-15 13:42:48','畜禽肉类'),(8,'2026-01-15 13:42:48','农产品类型8');
/*!40000 ALTER TABLE `nongchanpinleixing` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nongchanpinxinxi`
--

DROP TABLE IF EXISTS `nongchanpinxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nongchanpinxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `nongchanpinmingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农产品名称',
  `nongchanpinleixing` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农产品类型',
  `nongchanpinzhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '农产品照片',
  `chanpinguige` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '产品规格',
  `yingyangchengfen` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '营养成分',
  `waiguantezheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '外观特征',
  `chanpinjieshao` longtext COLLATE utf8mb4_unicode_ci COMMENT '产品介绍',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农民账号',
  `onelimittimes` int(11) DEFAULT '-1' COMMENT '单限',
  `alllimittimes` int(11) DEFAULT '-1' COMMENT '库存',
  `price` double NOT NULL DEFAULT '0' COMMENT '价格',
  `clicktime` datetime DEFAULT NULL COMMENT '最近点击时间',
  `discussnum` int(11) DEFAULT '0' COMMENT '评论数',
  `onshelves` int(11) DEFAULT '1' COMMENT '是否上架(1:上架，0:下架)',
  `storeupnum` int(11) DEFAULT '0' COMMENT '收藏数',
  PRIMARY KEY (`id`),
  KEY `nongchanpinxinxi_price` (`price`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='农产品信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nongchanpinxinxi`
--

LOCK TABLES `nongchanpinxinxi` WRITE;
/*!40000 ALTER TABLE `nongchanpinxinxi` DISABLE KEYS */;
INSERT INTO `nongchanpinxinxi` VALUES (1,'2026-01-15 13:42:49','江苏东台西瓜','水果类','upload/nongchanpinxinxi_江苏东台西瓜1.jpg,upload/nongchanpinxinxi_江苏东台西瓜2.jpg,upload/nongchanpinxinxi_江苏东台西瓜3.jpg','每个8-10斤','水分、维生素C','果皮翠绿、条纹清晰','果肉鲜红，沙甜多汁','101',25,37,182,'2026-01-15 21:42:49',0,1,1),(2,'2026-01-15 13:42:49','山东农家土鸡蛋','禽蛋制品','upload/nongchanpinxinxi_山东农家土鸡蛋1.jpg,upload/nongchanpinxinxi_山东农家土鸡蛋2.jpg,upload/nongchanpinxinxi_山东农家土鸡蛋3.jpg','30个/盒','优质蛋白、卵磷脂','蛋壳厚实、色泽均匀','蛋黄饱满，蛋香浓郁','102',29,78,92,'2026-01-15 21:42:49',0,1,2),(3,'2026-01-15 13:42:49','山西吕梁核桃','坚果干货','upload/nongchanpinxinxi_山西吕梁核桃1.jpg,upload/nongchanpinxinxi_山西吕梁核桃2.jpg,upload/nongchanpinxinxi_山西吕梁核桃3.jpg','1斤/袋','不饱和脂肪酸、锌','外壳薄脆、果仁饱满','口感香醇，无苦涩味','103',22,44,195,'2026-01-15 21:42:49',0,1,3),(4,'2026-01-15 13:42:49','四川汉源花椒','调味香料','upload/nongchanpinxinxi_四川汉源花椒1.jpg,upload/nongchanpinxinxi_四川汉源花椒2.jpg,upload/nongchanpinxinxi_四川汉源花椒3.jpg','100g/瓶','挥发油、生物碱','色泽红亮、颗粒饱满','麻香浓郁，去腥提味','105',5,39,167,'2026-01-15 21:42:49',0,1,4),(5,'2026-01-15 13:42:49','河北坝上莜麦','粮食谷物','upload/nongchanpinxinxi_河北坝上莜麦1.jpg,upload/nongchanpinxinxi_河北坝上莜麦2.jpg,upload/nongchanpinxinxi_河北坝上莜麦3.jpg','15斤/袋','膳食纤维、蛋白质','颗粒均匀、色泽浅黄','口感筋道，适合煮粥磨粉','108',26,49,89,'2026-01-15 21:42:49',0,1,5),(6,'2026-01-15 13:42:49','云南高山小土豆','蔬菜类','upload/nongchanpinxinxi_云南高山小土豆1.jpg,upload/nongchanpinxinxi_云南高山小土豆2.jpg,upload/nongchanpinxinxi_云南高山小土豆3.jpg','10斤/袋','淀粉、钾元素','个头均匀、表皮光滑','沙糯香甜，适合炖炒蒸','106',10,38,191,'2026-01-15 21:42:49',0,1,6),(7,'2026-01-15 13:42:49','广东清远鸡','畜禽肉类','upload/nongchanpinxinxi_广东清远鸡1.jpg,upload/nongchanpinxinxi_广东清远鸡2.jpg,upload/nongchanpinxinxi_广东清远鸡3.jpg','每只3-4斤','蛋白质、氨基酸','羽毛光亮、体型紧凑','肉质细嫩，鸡味浓郁','104',1,55,120,'2026-01-15 21:42:49',0,1,7),(8,'2026-01-15 13:42:49','海南文昌椰子','水果类','upload/nongchanpinxinxi_海南文昌椰子1.jpg,upload/nongchanpinxinxi_海南文昌椰子2.jpg,upload/nongchanpinxinxi_海南文昌椰子3.jpg','单个重量2-3斤','电解质、维生素B族','外壳坚硬、色泽棕褐','椰汁清甜，椰肉Q弹','107',21,75,88,'2026-01-15 21:42:49',0,1,8);
/*!40000 ALTER TABLE `nongchanpinxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nongmin`
--

DROP TABLE IF EXISTS `nongmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nongmin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `nongminzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '农民账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `nongminxingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '农民姓名',
  `xingbie` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `lianxidianhua` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `nongminzhanghao` (`nongminzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='农民';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nongmin`
--

LOCK TABLES `nongmin` WRITE;
/*!40000 ALTER TABLE `nongmin` DISABLE KEYS */;
INSERT INTO `nongmin` VALUES (21,'2026-01-15 13:42:48','101','123456','徐天宇','女','13023456789','upload/nongmin_南京大学1.jpg,upload/nongmin_南京大学2.jpg,upload/nongmin_南京大学3.jpg',200),(22,'2026-01-15 13:42:48','102','123456','孙伟','女','15012345678','upload/nongmin_山东大学1.jpg,upload/nongmin_山东大学2.jpg,upload/nongmin_山东大学3.jpg',200),(23,'2026-01-15 13:42:48','103','123456','马超','男','13290123456','upload/nongmin_吉林大学1.jpg,upload/nongmin_吉林大学2.jpg,upload/nongmin_吉林大学3.jpg',200),(24,'2026-01-15 13:42:48','105','123456','王若曦','女','13012345678','upload/nongmin_华中科技大学1.jpg,upload/nongmin_华中科技大学2.jpg,upload/nongmin_华中科技大学3.jpg',200),(25,'2026-01-15 13:42:48','108','123456','刘芳','男','15178901234','upload/nongmin_复旦大学1.jpg,upload/nongmin_复旦大学2.jpg,upload/nongmin_复旦大学3.jpg',200),(26,'2026-01-15 13:42:48','106','123456','张嘉怡','男','13567890123','upload/nongmin_清华大学1.jpg,upload/nongmin_清华大学2.jpg,upload/nongmin_清华大学3.jpg',200),(27,'2026-01-15 13:42:48','104','123456','杨洁','男','13276543210','upload/nongmin_上海交通大学1.jpg,upload/nongmin_上海交通大学2.jpg,upload/nongmin_上海交通大学3.jpg',200),(28,'2026-01-15 13:42:48','107','123456','陈浩','女','13987654321','upload/nongmin_天津大学1.jpg,upload/nongmin_天津大学2.jpg,upload/nongmin_天津大学3.jpg',200);
/*!40000 ALTER TABLE `nongmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nongyeyuanliao`
--

DROP TABLE IF EXISTS `nongyeyuanliao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nongyeyuanliao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuanliaomingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料名称',
  `yuanliaofenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料分类',
  `yuanliaozhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '原料照片',
  `youxiaochengfen` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '有效成分',
  `jiage` double DEFAULT NULL COMMENT '价格',
  `kucun` int(11) DEFAULT NULL COMMENT '库存',
  `shiyongshuoming` longtext COLLATE utf8mb4_unicode_ci COMMENT '使用说明',
  `yuanliaojieshao` longtext COLLATE utf8mb4_unicode_ci COMMENT '原料介绍',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='农业原料';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nongyeyuanliao`
--

LOCK TABLES `nongyeyuanliao` WRITE;
/*!40000 ALTER TABLE `nongyeyuanliao` DISABLE KEYS */;
INSERT INTO `nongyeyuanliao` VALUES (1,'2026-01-15 13:42:49','绿盛芹菜杀菌剂','杀菌剂','upload/nongyeyuanliao_绿盛芹菜杀菌剂1.jpg,upload/nongyeyuanliao_绿盛芹菜杀菌剂2.jpg,upload/nongyeyuanliao_绿盛芹菜杀菌剂3.jpg','百菌清75%',37,182,'生长期间定期喷雾','保护型杀菌剂预防多种病害','南京大学'),(2,'2026-01-15 13:42:49','鑫达葡萄专用肥','复合肥','upload/nongyeyuanliao_鑫达葡萄专用肥1.jpg,upload/nongyeyuanliao_鑫达葡萄专用肥2.jpg,upload/nongyeyuanliao_鑫达葡萄专用肥3.jpg','氮磷钾10-15-20',78,92,'基肥每亩60公斤膨果期追肥30公斤','促进果实着色提高甜度','山东大学'),(3,'2026-01-15 13:42:49','绿源白菜杀菌剂','杀菌剂','upload/nongyeyuanliao_绿源白菜杀菌剂1.jpg,upload/nongyeyuanliao_绿源白菜杀菌剂2.jpg,upload/nongyeyuanliao_绿源白菜杀菌剂3.jpg','甲基硫菌灵50%',44,195,'定植后定期喷雾预防','广谱杀菌防治霜霉病软腐病','吉林大学'),(4,'2026-01-15 13:42:49','金禾油菜种子','种子','upload/nongyeyuanliao_金禾油菜种子1.jpg,upload/nongyeyuanliao_金禾油菜种子2.jpg,upload/nongyeyuanliao_金禾油菜种子3.jpg','双低油菜杂交种',39,167,'育苗每亩用种0.5公斤直播1.5公斤','抗寒抗病含油率高','华中科技大学'),(5,'2026-01-15 13:42:49','锐锋辣椒杀虫剂','杀虫剂','upload/nongyeyuanliao_锐锋辣椒杀虫剂1.jpg,upload/nongyeyuanliao_锐锋辣椒杀虫剂2.jpg,upload/nongyeyuanliao_锐锋辣椒杀虫剂3.jpg','噻虫嗪25%',49,89,'定植后喷雾预防虫害发生','防治蚜虫蓟马粉虱','复旦大学'),(6,'2026-01-15 13:42:49','德丰红薯专用肥','复合肥','upload/nongyeyuanliao_德丰红薯专用肥1.jpg,upload/nongyeyuanliao_德丰红薯专用肥2.jpg,upload/nongyeyuanliao_德丰红薯专用肥3.jpg','氮磷钾8-12-20',38,191,'基肥每亩40公斤结薯期追肥15公斤','提高淀粉含量耐储存','清华大学'),(7,'2026-01-15 13:42:49','益农番茄叶面肥','叶面肥','upload/nongyeyuanliao_益农番茄叶面肥1.jpg,upload/nongyeyuanliao_益农番茄叶面肥2.jpg,upload/nongyeyuanliao_益农番茄叶面肥3.jpg','氨基酸18%',55,120,'坐果后每周一次连喷3次','快速补充养分促进结果','上海交通大学'),(8,'2026-01-15 13:42:49','速壮甜瓜叶面肥','叶面肥','upload/nongyeyuanliao_速壮甜瓜叶面肥1.jpg,upload/nongyeyuanliao_速壮甜瓜叶面肥2.jpg,upload/nongyeyuanliao_速壮甜瓜叶面肥3.jpg','腐殖酸叶面肥',75,88,'坐果后每7天喷一次连喷2-3次','增强抗逆性促进果实发育','天津大学');
/*!40000 ALTER TABLE `nongyeyuanliao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orderid` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `goodid` bigint(20) NOT NULL COMMENT '商品id',
  `goodname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品名称',
  `goodtype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商品类型',
  `picture` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '图片',
  `buynumber` int(11) DEFAULT NULL COMMENT '购买数量',
  `price` double DEFAULT NULL COMMENT '单价',
  `total` double DEFAULT NULL COMMENT '总价',
  `type` int(11) DEFAULT NULL COMMENT '支付类型',
  `status` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '订单状态',
  `address` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
  `tel` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '电话',
  `consignee` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT 'nongchanpinxinxi' COMMENT '商品表名',
  `logistics` longtext COLLATE utf8mb4_unicode_ci COMMENT '物流',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商户名称',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户角色',
  `returnreason` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '退货原因',
  PRIMARY KEY (`id`),
  UNIQUE KEY `orderid` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storeup`
--

DROP TABLE IF EXISTS `storeup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storeup` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `refid` bigint(20) DEFAULT NULL COMMENT '商品id',
  `tablename` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '表名',
  `name` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `picture` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片',
  `type` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '类型',
  `inteltype` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '推荐类型',
  `remark` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storeup`
--

LOCK TABLES `storeup` WRITE;
/*!40000 ALTER TABLE `storeup` DISABLE KEYS */;
/*!40000 ALTER TABLE `storeup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(255) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `username` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `role` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '管理员' COMMENT '角色',
  `image` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2026-01-15 13:42:49','admin','admin','管理员','upload/image1.jpg');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yonghu`
--

DROP TABLE IF EXISTS `yonghu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yonghu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yonghuzhanghao` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `yonghuxingming` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户姓名',
  `xingbie` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `lianxidianhua` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `money` double DEFAULT '0' COMMENT '余额',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yonghuzhanghao` (`yonghuzhanghao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消费者/采购商端';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yonghu`
--

LOCK TABLES `yonghu` WRITE;
/*!40000 ALTER TABLE `yonghu` DISABLE KEYS */;
INSERT INTO `yonghu` VALUES (11,'2026-01-15 13:42:48','002','123456','徐天宇','女','13023456789','upload/yonghu_南京大学1.jpg,upload/yonghu_南京大学2.jpg,upload/yonghu_南京大学3.jpg',200),(12,'2026-01-15 13:42:48','003','123456','孙伟','女','15012345678','upload/yonghu_山东大学1.jpg,upload/yonghu_山东大学2.jpg,upload/yonghu_山东大学3.jpg',200),(13,'2026-01-15 13:42:48','004','123456','马超','男','13290123456','upload/yonghu_吉林大学1.jpg,upload/yonghu_吉林大学2.jpg,upload/yonghu_吉林大学3.jpg',200),(14,'2026-01-15 13:42:48','006','123456','王若曦','女','13012345678','upload/yonghu_华中科技大学1.jpg,upload/yonghu_华中科技大学2.jpg,upload/yonghu_华中科技大学3.jpg',200),(15,'2026-01-15 13:42:48','005','123456','刘芳','男','15178901234','upload/yonghu_复旦大学1.jpg,upload/yonghu_复旦大学2.jpg,upload/yonghu_复旦大学3.jpg',200),(16,'2026-01-15 13:42:48','007','123456','张嘉怡','男','13567890123','upload/yonghu_清华大学1.jpg,upload/yonghu_清华大学2.jpg,upload/yonghu_清华大学3.jpg',200),(17,'2026-01-15 13:42:48','001','123456','杨洁','男','13276543210','upload/yonghu_上海交通大学1.jpg,upload/yonghu_上海交通大学2.jpg,upload/yonghu_上海交通大学3.jpg',200),(18,'2026-01-15 13:42:48','008','123456','陈浩','女','13987654321','upload/yonghu_天津大学1.jpg,upload/yonghu_天津大学2.jpg,upload/yonghu_天津大学3.jpg',200);
/*!40000 ALTER TABLE `yonghu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yuanliaocaigou`
--

DROP TABLE IF EXISTS `yuanliaocaigou`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yuanliaocaigou` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuanliaomingcheng` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料名称',
  `yuanliaofenlei` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '原料分类',
  `yuanliaozhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '原料照片',
  `youxiaochengfen` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '有效成分',
  `jiage` double DEFAULT NULL COMMENT '价格',
  `kucun` int(11) DEFAULT NULL COMMENT '采购数量',
  `caigouzongjia` double DEFAULT NULL COMMENT '采购总价',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `caigoushijian` datetime DEFAULT NULL COMMENT '采购时间',
  `caigoubeizhu` longtext COLLATE utf8mb4_unicode_ci COMMENT '采购备注',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农民账号',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  `ispay` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '未支付' COMMENT '是否支付',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='原料采购';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yuanliaocaigou`
--

LOCK TABLES `yuanliaocaigou` WRITE;
/*!40000 ALTER TABLE `yuanliaocaigou` DISABLE KEYS */;
INSERT INTO `yuanliaocaigou` VALUES (1,'2026-01-15 13:42:49','绿盛芹菜杀菌剂','杀菌剂','upload/yuanliaocaigou_绿盛芹菜杀菌剂1.jpg,upload/yuanliaocaigou_绿盛芹菜杀菌剂2.jpg,upload/yuanliaocaigou_绿盛芹菜杀菌剂3.jpg','百菌清75%',37,182,1,'南京大学','2026-01-15 21:42:49','采购备注1','101','是','','已支付'),(2,'2026-01-15 13:42:49','鑫达葡萄专用肥','复合肥','upload/yuanliaocaigou_鑫达葡萄专用肥1.jpg,upload/yuanliaocaigou_鑫达葡萄专用肥2.jpg,upload/yuanliaocaigou_鑫达葡萄专用肥3.jpg','氮磷钾10-15-20',78,92,2,'山东大学','2026-01-15 21:42:49','采购备注2','102','是','','已支付'),(3,'2026-01-15 13:42:49','绿源白菜杀菌剂','杀菌剂','upload/yuanliaocaigou_绿源白菜杀菌剂1.jpg,upload/yuanliaocaigou_绿源白菜杀菌剂2.jpg,upload/yuanliaocaigou_绿源白菜杀菌剂3.jpg','甲基硫菌灵50%',44,195,3,'吉林大学','2026-01-15 21:42:49','采购备注3','103','是','','已支付'),(4,'2026-01-15 13:42:49','金禾油菜种子','种子','upload/yuanliaocaigou_金禾油菜种子1.jpg,upload/yuanliaocaigou_金禾油菜种子2.jpg,upload/yuanliaocaigou_金禾油菜种子3.jpg','双低油菜杂交种',39,167,4,'华中科技大学','2026-01-15 21:42:49','采购备注4','105','是','','已支付'),(5,'2026-01-15 13:42:49','锐锋辣椒杀虫剂','杀虫剂','upload/yuanliaocaigou_锐锋辣椒杀虫剂1.jpg,upload/yuanliaocaigou_锐锋辣椒杀虫剂2.jpg,upload/yuanliaocaigou_锐锋辣椒杀虫剂3.jpg','噻虫嗪25%',49,89,5,'复旦大学','2026-01-15 21:42:49','采购备注5','108','是','','已支付'),(6,'2026-01-15 13:42:49','德丰红薯专用肥','复合肥','upload/yuanliaocaigou_德丰红薯专用肥1.jpg,upload/yuanliaocaigou_德丰红薯专用肥2.jpg,upload/yuanliaocaigou_德丰红薯专用肥3.jpg','氮磷钾8-12-20',38,191,6,'清华大学','2026-01-15 21:42:49','采购备注6','106','是','','已支付'),(7,'2026-01-15 13:42:49','益农番茄叶面肥','叶面肥','upload/yuanliaocaigou_益农番茄叶面肥1.jpg,upload/yuanliaocaigou_益农番茄叶面肥2.jpg,upload/yuanliaocaigou_益农番茄叶面肥3.jpg','氨基酸18%',55,120,7,'上海交通大学','2026-01-15 21:42:49','采购备注7','104','是','','已支付'),(8,'2026-01-15 13:42:49','速壮甜瓜叶面肥','叶面肥','upload/yuanliaocaigou_速壮甜瓜叶面肥1.jpg,upload/yuanliaocaigou_速壮甜瓜叶面肥2.jpg,upload/yuanliaocaigou_速壮甜瓜叶面肥3.jpg','腐殖酸叶面肥',75,88,8,'天津大学','2026-01-15 21:42:49','采购备注8','107','是','','已支付');
/*!40000 ALTER TABLE `yuanliaocaigou` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yuanliaofenlei`
--

DROP TABLE IF EXISTS `yuanliaofenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `yuanliaofenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `yuanliaofenlei` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '原料分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `yuanliaofenlei` (`yuanliaofenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='原料分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yuanliaofenlei`
--

LOCK TABLES `yuanliaofenlei` WRITE;
/*!40000 ALTER TABLE `yuanliaofenlei` DISABLE KEYS */;
INSERT INTO `yuanliaofenlei` VALUES (1,'2026-01-15 13:42:48','杀菌剂'),(2,'2026-01-15 13:42:48','复合肥'),(3,'2026-01-15 13:42:48','种子'),(4,'2026-01-15 13:42:48','杀虫剂'),(5,'2026-01-15 13:42:48','叶面肥'),(6,'2026-01-15 13:42:48','原料分类6'),(7,'2026-01-15 13:42:48','原料分类7'),(8,'2026-01-15 13:42:48','原料分类8');
/*!40000 ALTER TABLE `yuanliaofenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhishifenlei`
--

DROP TABLE IF EXISTS `zhishifenlei`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhishifenlei` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `zhishifenlei` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '知识分类',
  PRIMARY KEY (`id`),
  UNIQUE KEY `zhishifenlei` (`zhishifenlei`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='知识分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhishifenlei`
--

LOCK TABLES `zhishifenlei` WRITE;
/*!40000 ALTER TABLE `zhishifenlei` DISABLE KEYS */;
INSERT INTO `zhishifenlei` VALUES (1,'2026-01-15 13:42:48','土壤改良技术'),(2,'2026-01-15 13:42:48','植保技术'),(3,'2026-01-15 13:42:48','防灾减灾技术'),(4,'2026-01-15 13:42:48','养殖技术'),(5,'2026-01-15 13:42:48','废弃物资源化技术'),(6,'2026-01-15 13:42:48','栽培技术'),(7,'2026-01-15 13:42:48','育苗技术'),(8,'2026-01-15 13:42:48','知识分类8');
/*!40000 ALTER TABLE `zhishifenlei` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhuanjia`
--

DROP TABLE IF EXISTS `zhuanjia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhuanjia` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `zhuanjiamingcheng` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专家名称',
  `zhuanjiazhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '专家照片',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `zhuanyefangxiang` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业方向',
  `congyenianxian` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '从业年限',
  `yanjiulingyu` longtext COLLATE utf8mb4_unicode_ci COMMENT '研究领域',
  `gerenjianjie` longtext COLLATE utf8mb4_unicode_ci COMMENT '个人简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='专家';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhuanjia`
--

LOCK TABLES `zhuanjia` WRITE;
/*!40000 ALTER TABLE `zhuanjia` DISABLE KEYS */;
INSERT INTO `zhuanjia` VALUES (1,'2026-01-15 13:42:48','南京大学','陈志强','upload/zhuanjia_zhuanjiazhaopian1.jpg,upload/zhuanjia_zhuanjiazhaopian2.jpg,upload/zhuanjia_zhuanjiazhaopian3.jpg','女','水产养殖','16年','淡水鱼虾生态养殖水产病害绿色防控','专注淡水水产养殖技术研究擅长鱼虾蟹生态养殖模式创新和病害防治'),(2,'2026-01-15 13:42:48','山东大学','朱云霞','upload/zhuanjia_zhuanjiazhaopian2.jpg,upload/zhuanjia_zhuanjiazhaopian3.jpg,upload/zhuanjia_zhuanjiazhaopian4.jpg','女','蜂业','17年','蜜蜂养殖蜂产品深加工技术研究','深耕养蜂行业多年精通蜜蜂养殖技术和蜂产品深加工研发'),(3,'2026-01-15 13:42:48','吉林大学','刘红梅','upload/zhuanjia_zhuanjiazhaopian3.jpg,upload/zhuanjia_zhuanjiazhaopian4.jpg,upload/zhuanjia_zhuanjiazhaopian5.jpg','男','土壤肥料学','20年','土壤改良新型肥料研发与应用','致力于土壤改良和新型肥料研发应用对盐碱地治理和有机肥推广有丰富经验'),(4,'2026-01-15 13:42:48','华中科技大学','徐志刚','upload/zhuanjia_zhuanjiazhaopian4.jpg,upload/zhuanjia_zhuanjiazhaopian5.jpg,upload/zhuanjia_zhuanjiazhaopian6.jpg','女','农业经济管理','19年','农业产业规划农村经济发展研究','专注农业产业经济研究和农村经济发展规划为农业产业升级提供理论支持'),(5,'2026-01-15 13:42:48','复旦大学','吴玉梅','upload/zhuanjia_zhuanjiazhaopian5.jpg,upload/zhuanjia_zhuanjiazhaopian6.jpg,upload/zhuanjia_zhuanjiazhaopian7.jpg','男','农业气象','21年','农业气象灾害预警作物气象适应性研究','长期研究农业气象灾害预警与应对措施为农业生产提供精准气象服务支持'),(6,'2026-01-15 13:42:48','清华大学','韩明远','upload/zhuanjia_zhuanjiazhaopian6.jpg,upload/zhuanjia_zhuanjiazhaopian7.jpg,upload/zhuanjia_zhuanjiazhaopian8.jpg','男','中药材栽培','22年','道地中药材规范化种植中药材品质调控','专注道地中药材种植技术研究与推广在中药材规范化种植和品质提升上成果丰硕'),(7,'2026-01-15 13:42:48','上海交通大学','林晓峰','upload/zhuanjia_zhuanjiazhaopian7.jpg,upload/zhuanjia_zhuanjiazhaopian8.jpg,upload/zhuanjia_zhuanjiazhaopian1.jpg','男','节水农业','18年','高效节水灌溉旱作农业技术集成','致力于节水灌溉技术研究与推广在旱作农业和高效节水灌溉模式上有深入研究'),(8,'2026-01-15 13:42:48','天津大学','周俊峰','upload/zhuanjia_zhuanjiazhaopian8.jpg,upload/zhuanjia_zhuanjiazhaopian1.jpg,upload/zhuanjia_zhuanjiazhaopian2.jpg','女','经济林培育','14年','经济林栽培林下经济模式创新','致力于经济林培育和林业生态建设在果树种植和林下经济模式开发上经验丰富');
/*!40000 ALTER TABLE `zhuanjia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zhuanjiayuyue`
--

DROP TABLE IF EXISTS `zhuanjiayuyue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zhuanjiayuyue` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gaoxiaomingcheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '高校名称',
  `zhuanjiamingcheng` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专家名称',
  `zhuanjiazhaopian` longtext COLLATE utf8mb4_unicode_ci COMMENT '专家照片',
  `zhuanyefangxiang` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业方向',
  `yuyueshijian` datetime DEFAULT NULL COMMENT '预约时间',
  `zixunneirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '咨询内容',
  `nongminzhanghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '农民账号',
  `sfsh` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT '待审核' COMMENT '是否审核',
  `shhf` longtext COLLATE utf8mb4_unicode_ci COMMENT '审核回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='专家预约';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zhuanjiayuyue`
--

LOCK TABLES `zhuanjiayuyue` WRITE;
/*!40000 ALTER TABLE `zhuanjiayuyue` DISABLE KEYS */;
INSERT INTO `zhuanjiayuyue` VALUES (1,'2026-01-15 13:42:49','南京大学','陈志强','upload/zhuanjiayuyue_zhuanjiazhaopian1.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian2.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian3.jpg','水产养殖','2026-01-15 21:42:49','咨询内容1','101','是',''),(2,'2026-01-15 13:42:49','山东大学','朱云霞','upload/zhuanjiayuyue_zhuanjiazhaopian2.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian3.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian4.jpg','蜂业','2026-01-15 21:42:49','咨询内容2','102','是',''),(3,'2026-01-15 13:42:49','吉林大学','刘红梅','upload/zhuanjiayuyue_zhuanjiazhaopian3.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian4.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian5.jpg','土壤肥料学','2026-01-15 21:42:49','咨询内容3','103','是',''),(4,'2026-01-15 13:42:49','华中科技大学','徐志刚','upload/zhuanjiayuyue_zhuanjiazhaopian4.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian5.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian6.jpg','农业经济管理','2026-01-15 21:42:49','咨询内容4','105','是',''),(5,'2026-01-15 13:42:49','复旦大学','吴玉梅','upload/zhuanjiayuyue_zhuanjiazhaopian5.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian6.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian7.jpg','农业气象','2026-01-15 21:42:49','咨询内容5','108','是',''),(6,'2026-01-15 13:42:49','清华大学','韩明远','upload/zhuanjiayuyue_zhuanjiazhaopian6.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian7.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian8.jpg','中药材栽培','2026-01-15 21:42:49','咨询内容6','106','是',''),(7,'2026-01-15 13:42:49','上海交通大学','林晓峰','upload/zhuanjiayuyue_zhuanjiazhaopian7.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian8.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian1.jpg','节水农业','2026-01-15 21:42:49','咨询内容7','104','是',''),(8,'2026-01-15 13:42:49','天津大学','周俊峰','upload/zhuanjiayuyue_zhuanjiazhaopian8.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian1.jpg,upload/zhuanjiayuyue_zhuanjiazhaopian2.jpg','经济林培育','2026-01-15 21:42:49','咨询内容8','107','是','');
/*!40000 ALTER TABLE `zhuanjiayuyue` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-02-06 18:15:38
