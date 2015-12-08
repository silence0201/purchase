# ************************************************************
# Sequel Pro SQL dump
# Version 4135
#
# http://www.sequelpro.com/
# http://code.google.com/p/sequel-pro/
#
# Host: localhost (MySQL 5.5.42)
# Database: kuguan
# Generation Time: 2015-12-06 14:41:37 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table demand
# ------------------------------------------------------------

DROP TABLE IF EXISTS `demand`;

CREATE TABLE `demand` (
  `DemandID` varchar(20) NOT NULL DEFAULT '',
  `ItemID` varchar(20) NOT NULL,
  `Number` int(11) NOT NULL,
  `Account` double(11,2) NOT NULL,
  `Statement` enum('有货','采购','未采购') NOT NULL DEFAULT '未采购',
  `DemandTime` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`DemandID`),
  KEY `demand-item` (`ItemID`),
  CONSTRAINT `demand-item` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='需求单信息\nDemandD  需求单ID  varchar(20) NOT NULL    主键\nItemID        物品ID     varchar(20) NOT NULL\nNumber     数量         int(11) NOT NULL\nAccount    金额          double(11,2) NOT NULL\nStatement  状态         enum(''有货'',''采购'') NOT NULL';

LOCK TABLES `demand` WRITE;
/*!40000 ALTER TABLE `demand` DISABLE KEYS */;

INSERT INTO `demand` (`DemandID`, `ItemID`, `Number`, `Account`, `Statement`, `DemandTime`)
VALUES
	('R0001','A006',7,350.00,'有货','2015-05-21'),
	('R0002','B001',5,1350.00,'采购','2015-09-21');

/*!40000 ALTER TABLE `demand` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table export
# ------------------------------------------------------------

DROP TABLE IF EXISTS `export`;

CREATE TABLE `export` (
  `ExportID` varchar(20) NOT NULL,
  `RequestID` varchar(20) NOT NULL DEFAULT '',
  `StockmanID` varchar(20) NOT NULL DEFAULT '',
  `RequestmanID` varchar(20) NOT NULL,
  `Exporttime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ExportID`),
  KEY `export-stockman` (`StockmanID`),
  KEY `export-requestman` (`RequestmanID`),
  KEY `export-request` (`RequestID`),
  CONSTRAINT `export-request` FOREIGN KEY (`RequestID`) REFERENCES `request` (`RequestID`),
  CONSTRAINT `export-requestman` FOREIGN KEY (`RequestmanID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `export-stockman` FOREIGN KEY (`StockmanID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='出库单信息\nExportID       出库单ID   varchar(20) NOT NULL  主键\nRequestID     申请单ID   varchar(20) NOT NULL\nStockmanID   库管员ID  varchar(20) NOT NULL\nRequestmanID 申请人ID  varchar(20) NOT NULL\nExporttime   出货时间     varchar(20) DEFAULT NULL';

LOCK TABLES `export` WRITE;
/*!40000 ALTER TABLE `export` DISABLE KEYS */;

INSERT INTO `export` (`ExportID`, `RequestID`, `StockmanID`, `RequestmanID`, `Exporttime`)
VALUES
	('E00001','100001','S00001','R00012','2015-11-22'),
	('E00002','300001','S00004','R00031','2015-02-22');

/*!40000 ALTER TABLE `export` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table import
# ------------------------------------------------------------

DROP TABLE IF EXISTS `import`;

CREATE TABLE `import` (
  `ImportID` varchar(20) NOT NULL,
  `OrderID` varchar(20) NOT NULL,
  `StockmanID` varchar(20) NOT NULL,
  `Importtime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ImportID`),
  KEY `export-order` (`OrderID`),
  KEY `import-stockman` (`StockmanID`),
  CONSTRAINT `export-order` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`),
  CONSTRAINT `import-stockman` FOREIGN KEY (`StockmanID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='入库单\nImportID  入库单ID  varchar(20) NOT NULL   主键\nOrderID    订单ID   varchar(20) NOT NULL\nStockmanID   库管员ID  varchar(20) NOT NULL\nImporttime    出库时间   varchar(20) DEFAULT NULL';

LOCK TABLES `import` WRITE;
/*!40000 ALTER TABLE `import` DISABLE KEYS */;

INSERT INTO `import` (`ImportID`, `OrderID`, `StockmanID`, `Importtime`)
VALUES
	('I00001','P00001','S00001','2015-09-25');

/*!40000 ALTER TABLE `import` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `ItemID` varchar(20) NOT NULL,
  `Itemname` varchar(45) NOT NULL,
  `Unitprice` double(11,2) NOT NULL,
  `ItemsInventory` int(11) NOT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品信息\nItemID   物品ID   varchar(20) NOT NULL  主键\nItemname  物品名称   varchar(45) NOT NULL\nUnitprice    单价   double(11,2) NOT NULL\nItemsInventory   库存   int(11) NOT NULL';

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;

INSERT INTO `item` (`ItemID`, `Itemname`, `Unitprice`, `ItemsInventory`)
VALUES
	('A001','锡膏',150.00,56),
	('A002','红胶',200.00,12),
	('A003','印刷电路版',200.00,33),
	('A004','润滑油脂',100.00,89),
	('A005','高温油',300.00,66),
	('A006','焊接笔',50.00,15),
	('A007','锡线',160.00,13),
	('A008','洗板水',230.00,32),
	('A009','工业酒精',90.00,45),
	('B001','硒鼓',270.00,78),
	('B002','墨盒',130.00,89),
	('B003','打印机',800.00,0),
	('B004','回形针',5.00,56),
	('B005','票据夹',12.00,13),
	('B006','固体胶',1.50,0),
	('B007','印泥',10.00,56),
	('B008','票据',2.00,89),
	('B009','文件夹',5.00,56),
	('B010','记事本',1.50,444);

/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `OrderID` varchar(20) NOT NULL,
  `Statement` enum('采购中','采购完成') NOT NULL DEFAULT '采购中',
  `Ordertime` varchar(20) DEFAULT NULL,
  `DemandID` varchar(20) NOT NULL,
  `orderManID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `order-demand` (`DemandID`),
  KEY `order-user` (`orderManID`),
  CONSTRAINT `order-demand` FOREIGN KEY (`DemandID`) REFERENCES `demand` (`DemandID`),
  CONSTRAINT `order-user` FOREIGN KEY (`orderManID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单信息\nOrderID   订单号  varchar(20) NOT NULL  主键\nStatement   订单状态  enum(''采购完成'',''采购中'',''未采购'') NOT NULL\nOrdertime    订单生成时间   varchar(20) DEFAULT NULL\nDemandID    需求单ID  varchar(20) NOT NULL';

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;

INSERT INTO `order` (`OrderID`, `Statement`, `Ordertime`, `DemandID`, `orderManID`)
VALUES
	('P00001','采购完成','2015-02-03','R0001','P00001'),
	('P00002','采购中','2015-09-06','R0002','P00002');

/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table relationship
# ------------------------------------------------------------

DROP TABLE IF EXISTS `relationship`;

CREATE TABLE `relationship` (
  `SupplierID` varchar(20) NOT NULL,
  `ItemID` varchar(20) NOT NULL,
  `Quality` varchar(20) NOT NULL,
  `Statement` enum('充足','少量','缺货') NOT NULL,
  PRIMARY KEY (`SupplierID`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应关系\nSupplierID  供应商ID  主键\nItemID   物品ID   主键\nQuality   质量  varchar(20) NOT NULL\nStatement    状态  enum(''充足'',''少量'',''缺货'') NOT NULL';

LOCK TABLES `relationship` WRITE;
/*!40000 ALTER TABLE `relationship` DISABLE KEYS */;

INSERT INTO `relationship` (`SupplierID`, `ItemID`, `Quality`, `Statement`)
VALUES
	('A001','A001','A','充足'),
	('A001','A002','B','充足'),
	('A001','A005','A','缺货'),
	('A001','A006','A','缺货'),
	('A001','A007','C','少量'),
	('A002','A002','A','缺货'),
	('A002','A004','B','缺货'),
	('A002','A005','C','少量'),
	('A002','A006','C','充足'),
	('A002','A007','A','少量'),
	('A003','A001','B','缺货'),
	('A003','A003','C','少量'),
	('A003','A007','A','充足'),
	('A003','A008','A','少量'),
	('A003','A009','A','少量'),
	('A004','A001','A','少量'),
	('A004','A002','B','缺货'),
	('A004','A003','C','少量'),
	('A004','A004','A','充足'),
	('A004','A005','C','少量'),
	('A005','A005','A','少量'),
	('A005','A006','A','少量'),
	('A005','A007','A','少量'),
	('A005','A008','B','缺货'),
	('A005','A009','C','少量'),
	('B001','B001','A','少量'),
	('B001','B002','B','缺货'),
	('B001','B003','C','少量'),
	('B001','B004','A','充足'),
	('B001','B005','A','少量'),
	('B001','B006','A','少量'),
	('B002','B001','A','少量'),
	('B002','B005','B','缺货'),
	('B002','B006','C','少量'),
	('B002','B007','A','充足'),
	('B002','B008','C','少量'),
	('B002','B009','A','少量'),
	('B002','B010','A','少量');

/*!40000 ALTER TABLE `relationship` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table request
# ------------------------------------------------------------

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `RequestID` varchar(20) NOT NULL,
  `ItemID` varchar(20) NOT NULL,
  `Number` int(11) NOT NULL,
  `Totalaccount` double(11,2) NOT NULL,
  `RequestmanID` varchar(20) NOT NULL,
  `Requesttime` varchar(20) DEFAULT NULL,
  `Requeststatement` enum('拒绝','未审核','通过','完成') DEFAULT NULL,
  `AuditorID` varchar(20) DEFAULT NULL,
  `Audittime` varchar(20) DEFAULT NULL,
  `DemandlistID` varchar(20) DEFAULT NULL,
  `Reason` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`RequestID`),
  KEY `request-item` (`ItemID`),
  KEY `request-user` (`RequestmanID`),
  KEY `autor-user` (`AuditorID`),
  KEY `request-demand` (`DemandlistID`),
  CONSTRAINT `autor-user` FOREIGN KEY (`AuditorID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `request-demand` FOREIGN KEY (`DemandlistID`) REFERENCES `demand` (`DemandID`),
  CONSTRAINT `request-item` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`),
  CONSTRAINT `request-user` FOREIGN KEY (`RequestmanID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='申请单号\nRequestID  申请单ID  varchar(20) NOT NULL  主键\nItemID    商品ID   varchar(20) NOT NULL\nNumber   商品数量  int(11) NOT NULL\nTotalaccount  总数量  double(11,2) NOT NULL\nRequestmanID   申请人ID   varchar(20) NOT NULL\nRequesttime  申请时间   varchar(20) DEFAULT NULL\nRequeststatement  申请状态   enum(''拒绝'',''未审核'',''通过'') NOT NULL\nAuditorID     审核人ID  varchar(20) DEFAULT NULL\nAudittime  审核时间  varchar(20) DEFAULT NULL\nDemandlistID    需求单ID  varchar(20) DEFAULT NULL\nReason   申请理由  varchar(60) DEFAULT NULL';

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;

INSERT INTO `request` (`RequestID`, `ItemID`, `Number`, `Totalaccount`, `RequestmanID`, `Requesttime`, `Requeststatement`, `AuditorID`, `Audittime`, `DemandlistID`, `Reason`)
VALUES
	('100001','B001',5,1350.00,'R00012','2015-02-03','通过','D00001','2015-02-15','R0002',NULL),
	('100002','B002',3,390.00,'R00013','2015-03-06','未审核',NULL,NULL,NULL,NULL),
	('100003','B006',80,120.00,'R00011','2015-04-02','未审核',NULL,NULL,NULL,NULL),
	('200001','B004',15,75.00,'R00021','2015-06-03','未审核',NULL,NULL,NULL,NULL),
	('200002','B002',2,260.00,'R00022','2015-06-23','未审核',NULL,NULL,NULL,NULL),
	('200003','B009',18,90.00,'R00025','2015-06-30','未审核',NULL,NULL,NULL,NULL),
	('300001','B006',70,105.00,'R00033','2015-07-05','未审核',NULL,NULL,NULL,NULL),
	('300002','B004',60,300.00,'R00031','2015-08-20','未审核',NULL,NULL,NULL,NULL),
	('300003','B007',20,200.00,'R00032','2015-05-02','未审核',NULL,NULL,NULL,NULL),
	('300004','B005',30,360.00,'R00033','2015-03-23','未审核',NULL,NULL,NULL,NULL),
	('400001','B010',40,60.00,'R00041','2015-05-23','未审核',NULL,NULL,NULL,NULL),
	('400002','A003',3,600.00,'R00042','2015-04-25','未审核',NULL,NULL,NULL,NULL),
	('400003','A005',4,1200.00,'R00044','2015-09-17','拒绝','P00001','2015-09-21',NULL,NULL),
	('400004','A006',7,350.00,'R00042','2015-10-23','完成','P00002','2015-10-30','R0001',NULL),
	('500001','A008',20,4600.00,'R00051','2015-08-02','拒绝','P00003','2015-08-10',NULL,NULL),
	('500002','A004',15,1500.00,'R00052','2015-12-03','未审核',NULL,NULL,NULL,NULL);

/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table supplier
# ------------------------------------------------------------

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `SupplierID` varchar(20) NOT NULL DEFAULT '',
  `Suppliername` varchar(45) NOT NULL,
  `Contacts` varchar(20) NOT NULL,
  `Telnumber` varchar(20) NOT NULL,
  `Address` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商信息\nSupplierID   供应商ID  varchar(20) NOT NULL DEFAULT  主键\nSuppliername  供应商名字  varchar(45) NOT NULL\nContacts   供应商联系人  varchar(20) NOT NULL\nTelnumber   供应商联系方式  varchar(20) NOT NULL';

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;

INSERT INTO `supplier` (`SupplierID`, `Suppliername`, `Contacts`, `Telnumber`, `Address`)
VALUES
	('A001','东莞永安科技有限公司','倪正文','13756234455','东莞'),
	('A002','博蓝电子有限公司','汉斐斐','13556224433','上海'),
	('A003','邦士电子有限公司','席秀美','13755336699','广州'),
	('A004','上海凝睿有限公司','丁高歌','13711556633','上海'),
	('A005','捷科电子有限公司','力高明','13777556633','北京'),
	('B001','东安文具有限公司','从芳洁','13777556622','杭州'),
	('B002','苏光文具有限公司','荣和泽','13711223333','北京'),
	('B003','光谷文具有限公司','钟楼乐','18566345269','深圳');

/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `UserID` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Username` varchar(20) NOT NULL,
  `Telnumber` varchar(20) NOT NULL,
  `Position` varchar(40) NOT NULL,
  `E-mail` varchar(20) NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表\nUserID   用户ID  varchar(20) NOT NULL   主键\nPassword  用户密码  varchar(20) NOT NULL\nUsername  用户名  varchar(20) NOT NULL\nTelnumber  用户电话  varchar(20) NOT NULL\nPosition    用户职位  varchar(40) NOT NULL\nE-mail   用户邮箱  varchar(20) NOT NULL';

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`UserID`, `Password`, `Username`, `Telnumber`, `Position`, `E-mail`)
VALUES
	('D00001','123456789','李浩鹏','13712365633','市场部门经理','yhp@pecr.com'),
	('D00002','123456789','李铁刚','13756633284','财务部门经理','ytg@pecr.com'),
	('D00003','123456789','李文轩','13566325698','生产部门经理','ywx@pecr.com'),
	('D00004','123456789','李慧','18956332289','研发部门经理','yh@pecr.com'),
	('D00005','123456789','李子豪','13914476959','销售部门经理','lzh@pecr.com'),
	('M00001','123456789','赵彦龙','1375632489','总经理','zyl@pecr.com'),
	('P00001','123456789','无子安','13214955374','采购员','wza@pecr.com'),
	('P00002','123456789','将玉山','13606451724','采购员','jys@pecr.com'),
	('P00003','123456789','友阳羽','13128149687','采购员','yyy@pecr.com'),
	('P00004','123456789','闵雪松','13337246940','采购员','mxs@pecr.com'),
	('P00005','123456789','中星文','13177913127','采购员','zxw@pecr.com'),
	('R00011','123456789','王长旭','13371653414','市场部门申请员','wcx@pecr.com'),
	('R00012','123456789','王鸿朗','13091099159','市场部门申请员','whl@pecr.com'),
	('R00013','123456789','王星华','13041307225','市场部门申请员','wxh@pecr.com'),
	('R00014','123456789','王欣荣','13825315788','市场部门申请员','wxr@pecr.com'),
	('R00015','123456789','王子墨','13476266540','市场部门申请员','wzm@pecr.com'),
	('R00021','123456789','宋运骏','13131494168','财务部门申请员','syy@pecr.com'),
	('R00022','123456789','宋志专','13869891844','财务部门申请员','szc@pecr.com'),
	('R00023','123456789','宋鹏涛','13489250914','财务部门申请员','spt@pecr.com'),
	('R00024','123456789','宋飞昂','13638691102','财务部门申请员','sfy@pecr.com'),
	('R00025','123456789','宋高阳','13769791722','财务部门申请员','sgy@pecr.com'),
	('R00031','123456789','宫正祥','13482859232','生产部门申请员','gzx@pecr.com'),
	('R00032','123456789','红子平','13704095804','生产部门申请员','hzp@pecr.com'),
	('R00033','123456789','崇鹏天','13455441446','生产部门申请员','zpt@pecr.com'),
	('R00034','123456789','于和豫','13287383596','生产部门申请员','yhy@pecr.com'),
	('R00035','123456789','董智阳','13137894871','生产部门申请员','dzy@pecr.com'),
	('R00041','123456789','肖晋鹏','13849971987','研发部门申请员','xjp@pecr.com'),
	('R00042','123456789','傅和志','13471835919','研发部门申请员','phz@pecr.com'),
	('R00043','123456789','代乐成','13844349114','研发部门申请员','dlc@pecr.com'),
	('R00044','123456789','毕德义','13435566214','研发部门申请员','bdy@pecr.com'),
	('R00045','123456789','以兴思','13129287173','研发部门申请员','yxs@pecr.com'),
	('R00051','123456789','田绍元','13858579863','销售部门申请员','tsy@pecr.com'),
	('R00052','123456789','白心水','13396305510','销售部门申请员','bxs@pecr.com'),
	('R00053','123456789','官天泽','13510038329','销售部门申请员','gtz@pecr.com'),
	('R00054','123456789','红文康','13672414200','销售部门申请员','hwk@pecr.com'),
	('R00055','123456789','马嘉禧','13952003686','销售部门申请员','mjx@pecr.com'),
	('S00001','123456789','丹修然','13575230336','库管员','dxr@pecr.com'),
	('S00002','123456789','业运凯','13050824494','库管员','yyh@pecr.com'),
	('S00003','123456789','秦弘光','13777162072','库管员','thg@pecr.com'),
	('S00004','123456789','似成弘','13774621083','库管员','sch@pecr.com'),
	('S00005','123456789','袭英卫','13773745327','库管员','qyw@pecr.com');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
