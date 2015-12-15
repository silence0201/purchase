/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : kuguan_2015-12-06

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2015-12-12 15:49:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `clear`
-- ----------------------------
DROP TABLE IF EXISTS `clear`;
CREATE TABLE `clear` (
  `RequestClear` int(11) NOT NULL DEFAULT '0',
  `PurchaseClear` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`RequestClear`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of clear
-- ----------------------------
INSERT INTO `clear` VALUES ('0', '0');

-- ----------------------------
-- Table structure for `demand`
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand` (
  `DemandID` int(20) NOT NULL AUTO_INCREMENT,
  `ItemID` varchar(20) NOT NULL,
  `Number` int(11) NOT NULL,
  `Account` double(11,2) NOT NULL,
  `Statement` enum('有货','采购','未采购') NOT NULL DEFAULT '未采购',
  `DemandTime` varchar(22) DEFAULT NULL,
  PRIMARY KEY (`DemandID`),
  KEY `demand-item` (`ItemID`),
  CONSTRAINT `demand-item` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8 COMMENT='需求单信息\r\nDemandD  需求单ID  varchar(20) NOT NULL    主键\r\nItemID        物品ID     varchar(20) NOT NULL\r\nNumber     数量         int(11) NOT NULL\r\nAccount    金额          double(11,2) NOT NULL\r\nStatement  状态         enum(''有货'',''采购'') NOT NULL';

-- ----------------------------
-- Records of demand
-- ----------------------------
INSERT INTO `demand` VALUES ('10001', 'A006', '7', '350.00', '有货', '2015-05-21');
INSERT INTO `demand` VALUES ('10002', 'B001', '5', '1350.00', '采购', '2015-09-21');

-- ----------------------------
-- Table structure for `export`
-- ----------------------------
DROP TABLE IF EXISTS `export`;
CREATE TABLE `export` (
  `ExportID` int(20) NOT NULL AUTO_INCREMENT,
  `RequestID` int(20) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=100000005 DEFAULT CHARSET=utf8 COMMENT='出库单信息\r\nExportID       出库单ID   varchar(20) NOT NULL  主键\r\nRequestID     申请单ID   varchar(20) NOT NULL\r\nStockmanID   库管员ID  varchar(20) NOT NULL\r\nRequestmanID 申请人ID  varchar(20) NOT NULL\r\nExporttime   出货时间     varchar(20) DEFAULT NULL';

-- ----------------------------
-- Records of export
-- ----------------------------
INSERT INTO `export` VALUES ('10000001', '100001', 'S00001', 'R00012', '2015-11-22');
INSERT INTO `export` VALUES ('10000002', '300001', 'S00004', 'R00031', '2015-02-22');
INSERT INTO `export` VALUES ('100000003', '100001', 'S00002', 'R00042', '2015-12-06');
INSERT INTO `export` VALUES ('100000004', '100002', 'S00001', 'R00042', '2015-12-06');

-- ----------------------------
-- Table structure for `import`
-- ----------------------------
DROP TABLE IF EXISTS `import`;
CREATE TABLE `import` (
  `ImportID` int(20) NOT NULL AUTO_INCREMENT,
  `OrderID` int(20) NOT NULL,
  `StockmanID` varchar(20) NOT NULL,
  `Importtime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ImportID`),
  KEY `export-order` (`OrderID`),
  KEY `import-stockman` (`StockmanID`),
  CONSTRAINT `import-order` FOREIGN KEY (`OrderID`) REFERENCES `order` (`OrderID`),
  CONSTRAINT `import-stockman` FOREIGN KEY (`StockmanID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=10000012 DEFAULT CHARSET=utf8 COMMENT='入库单\r\nImportID  入库单ID  varchar(20) NOT NULL   主键\r\nOrderID    订单ID   varchar(20) NOT NULL\r\nStockmanID   库管员ID  varchar(20) NOT NULL\r\nImporttime    出库时间   varchar(20) DEFAULT NULL';

-- ----------------------------
-- Records of import
-- ----------------------------
INSERT INTO `import` VALUES ('10000001', '100001', 'S00001', '2015-09-25');
INSERT INTO `import` VALUES ('10000011', '100002', 'S00001', '2015-12-06');

-- ----------------------------
-- Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `ItemID` varchar(20) NOT NULL,
  `Itemname` varchar(45) NOT NULL,
  `Unitprice` double(11,2) NOT NULL,
  `ItemsInventory` int(11) NOT NULL,
  PRIMARY KEY (`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物品信息\nItemID   物品ID   varchar(20) NOT NULL  主键\nItemname  物品名称   varchar(45) NOT NULL\nUnitprice    单价   double(11,2) NOT NULL\nItemsInventory   库存   int(11) NOT NULL';

-- ----------------------------
-- Records of item
-- ----------------------------
INSERT INTO `item` VALUES ('A001', '锡膏', '150.00', '56');
INSERT INTO `item` VALUES ('A002', '红胶', '200.00', '12');
INSERT INTO `item` VALUES ('A003', '印刷电路版', '200.00', '33');
INSERT INTO `item` VALUES ('A004', '润滑油脂', '100.00', '89');
INSERT INTO `item` VALUES ('A005', '高温油', '300.00', '66');
INSERT INTO `item` VALUES ('A006', '焊接笔', '50.00', '15');
INSERT INTO `item` VALUES ('A007', '锡线', '160.00', '13');
INSERT INTO `item` VALUES ('A008', '洗板水', '230.00', '32');
INSERT INTO `item` VALUES ('A009', '工业酒精', '90.00', '45');
INSERT INTO `item` VALUES ('B001', '硒鼓', '270.00', '78');
INSERT INTO `item` VALUES ('B002', '墨盒', '130.00', '89');
INSERT INTO `item` VALUES ('B003', '打印机', '800.00', '0');
INSERT INTO `item` VALUES ('B004', '回形针', '5.00', '56');
INSERT INTO `item` VALUES ('B005', '票据夹', '12.00', '13');
INSERT INTO `item` VALUES ('B006', '固体胶', '1.50', '0');
INSERT INTO `item` VALUES ('B007', '印泥', '10.00', '56');
INSERT INTO `item` VALUES ('B008', '票据', '2.00', '89');
INSERT INTO `item` VALUES ('B009', '文件夹', '5.00', '56');
INSERT INTO `item` VALUES ('B010', '记事本', '1.50', '444');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `OrderID` int(20) NOT NULL AUTO_INCREMENT,
  `Statement` enum('采购中','采购完成') NOT NULL DEFAULT '采购中',
  `Ordertime` varchar(20) DEFAULT NULL,
  `DemandID` int(20) NOT NULL,
  `orderManID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `order-demand` (`DemandID`),
  KEY `order-user` (`orderManID`),
  CONSTRAINT `oeder-demand` FOREIGN KEY (`DemandID`) REFERENCES `demand` (`DemandID`),
  CONSTRAINT `order-user` FOREIGN KEY (`orderManID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=100003 DEFAULT CHARSET=utf8 COMMENT='订单信息\r\nOrderID   订单号  varchar(20) NOT NULL  主键\r\nStatement   订单状态  enum(''采购完成'',''采购中'',''未采购'') NOT NULL\r\nOrdertime    订单生成时间   varchar(20) DEFAULT NULL\r\nDemandID    需求单ID  varchar(20) NOT NULL';

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('100001', '采购完成', '2015-02-03', '10001', 'P00001');
INSERT INTO `order` VALUES ('100002', '采购中', '2015-09-06', '10002', 'P00002');

-- ----------------------------
-- Table structure for `purchasecount`
-- ----------------------------
DROP TABLE IF EXISTS `purchasecount`;
CREATE TABLE `purchasecount` (
  `UserID` varchar(20) NOT NULL,
  `Count` int(11) DEFAULT '0',
  `Account` double(11,2) DEFAULT '0.00',
  PRIMARY KEY (`UserID`),
  CONSTRAINT `purchasecount_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchasecount
-- ----------------------------
INSERT INTO `purchasecount` VALUES ('P00001', '0', '0.00');
INSERT INTO `purchasecount` VALUES ('P00002', '0', '0.00');
INSERT INTO `purchasecount` VALUES ('P00003', '0', '0.00');
INSERT INTO `purchasecount` VALUES ('P00004', '0', '0.00');
INSERT INTO `purchasecount` VALUES ('P00005', '0', '0.00');

-- ----------------------------
-- Table structure for `relationship`
-- ----------------------------
DROP TABLE IF EXISTS `relationship`;
CREATE TABLE `relationship` (
  `SupplierID` varchar(20) NOT NULL,
  `ItemID` varchar(20) NOT NULL,
  `Quality` varchar(20) NOT NULL,
  `Statement` enum('充足','少量','缺货') NOT NULL,
  PRIMARY KEY (`SupplierID`,`ItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应关系\nSupplierID  供应商ID  主键\nItemID   物品ID   主键\nQuality   质量  varchar(20) NOT NULL\nStatement    状态  enum(''充足'',''少量'',''缺货'') NOT NULL';

-- ----------------------------
-- Records of relationship
-- ----------------------------
INSERT INTO `relationship` VALUES ('A001', 'A001', 'A', '充足');
INSERT INTO `relationship` VALUES ('A001', 'A002', 'B', '充足');
INSERT INTO `relationship` VALUES ('A001', 'A005', 'A', '缺货');
INSERT INTO `relationship` VALUES ('A001', 'A006', 'A', '缺货');
INSERT INTO `relationship` VALUES ('A001', 'A007', 'C', '少量');
INSERT INTO `relationship` VALUES ('A002', 'A002', 'A', '缺货');
INSERT INTO `relationship` VALUES ('A002', 'A004', 'B', '缺货');
INSERT INTO `relationship` VALUES ('A002', 'A005', 'C', '少量');
INSERT INTO `relationship` VALUES ('A002', 'A006', 'C', '充足');
INSERT INTO `relationship` VALUES ('A002', 'A007', 'A', '少量');
INSERT INTO `relationship` VALUES ('A003', 'A001', 'B', '缺货');
INSERT INTO `relationship` VALUES ('A003', 'A003', 'C', '少量');
INSERT INTO `relationship` VALUES ('A003', 'A007', 'A', '充足');
INSERT INTO `relationship` VALUES ('A003', 'A008', 'A', '少量');
INSERT INTO `relationship` VALUES ('A003', 'A009', 'A', '少量');
INSERT INTO `relationship` VALUES ('A004', 'A001', 'A', '少量');
INSERT INTO `relationship` VALUES ('A004', 'A002', 'B', '缺货');
INSERT INTO `relationship` VALUES ('A004', 'A003', 'C', '少量');
INSERT INTO `relationship` VALUES ('A004', 'A004', 'A', '充足');
INSERT INTO `relationship` VALUES ('A004', 'A005', 'C', '少量');
INSERT INTO `relationship` VALUES ('A005', 'A005', 'A', '少量');
INSERT INTO `relationship` VALUES ('A005', 'A006', 'A', '少量');
INSERT INTO `relationship` VALUES ('A005', 'A007', 'A', '少量');
INSERT INTO `relationship` VALUES ('A005', 'A008', 'B', '缺货');
INSERT INTO `relationship` VALUES ('A005', 'A009', 'C', '少量');
INSERT INTO `relationship` VALUES ('B001', 'B001', 'A', '少量');
INSERT INTO `relationship` VALUES ('B001', 'B002', 'B', '缺货');
INSERT INTO `relationship` VALUES ('B001', 'B003', 'C', '少量');
INSERT INTO `relationship` VALUES ('B001', 'B004', 'A', '充足');
INSERT INTO `relationship` VALUES ('B001', 'B005', 'A', '少量');
INSERT INTO `relationship` VALUES ('B001', 'B006', 'A', '少量');
INSERT INTO `relationship` VALUES ('B002', 'B001', 'A', '少量');
INSERT INTO `relationship` VALUES ('B002', 'B005', 'B', '缺货');
INSERT INTO `relationship` VALUES ('B002', 'B006', 'C', '少量');
INSERT INTO `relationship` VALUES ('B002', 'B007', 'A', '充足');
INSERT INTO `relationship` VALUES ('B002', 'B008', 'C', '少量');
INSERT INTO `relationship` VALUES ('B002', 'B009', 'A', '少量');
INSERT INTO `relationship` VALUES ('B002', 'B010', 'A', '少量');

-- ----------------------------
-- Table structure for `request`
-- ----------------------------
DROP TABLE IF EXISTS `request`;
CREATE TABLE `request` (
  `RequestID` int(20) NOT NULL AUTO_INCREMENT,
  `ItemID` varchar(20) NOT NULL,
  `Number` int(11) NOT NULL,
  `Totalaccount` double(11,2) NOT NULL,
  `RequestmanID` varchar(20) NOT NULL,
  `Requesttime` varchar(20) DEFAULT NULL,
  `Requeststatement` enum('拒绝','未审核','通过','完成') DEFAULT NULL,
  `AuditorID` varchar(20) DEFAULT NULL,
  `Audittime` varchar(20) DEFAULT NULL,
  `DemandlistID` int(20) DEFAULT NULL,
  `Reason` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`RequestID`),
  KEY `request-item` (`ItemID`),
  KEY `request-user` (`RequestmanID`),
  KEY `autor-user` (`AuditorID`),
  KEY `request-demand` (`DemandlistID`),
  CONSTRAINT `autor-user` FOREIGN KEY (`AuditorID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `request-demand` FOREIGN KEY (`DemandlistID`) REFERENCES `demand` (`DemandID`),
  CONSTRAINT `request-item` FOREIGN KEY (`ItemID`) REFERENCES `item` (`ItemID`),
  CONSTRAINT `request-user` FOREIGN KEY (`RequestmanID`) REFERENCES `user` (`UserID`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`RequestmanID`) REFERENCES `requestcount` (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=500003 DEFAULT CHARSET=utf8 COMMENT='申请单号\r\nRequestID  申请单ID  varchar(20) NOT NULL  主键\r\nItemID    商品ID   varchar(20) NOT NULL\r\nNumber   商品数量  int(11) NOT NULL\r\nTotalaccount  总数量  double(11,2) NOT NULL\r\nRequestmanID   申请人ID   varchar(20) NOT NULL\r\nRequesttime  申请时间   varchar(20) DEFAULT NULL\r\nRequeststatement  申请状态   enum(''拒绝'',''未审核'',''通过'') NOT NULL\r\nAuditorID     审核人ID  varchar(20) DEFAULT NULL\r\nAudittime  审核时间  varchar(20) DEFAULT NULL\r\nDemandlistID    需求单ID  varchar(20) DEFAULT NULL\r\nReason   申请理由  varchar(60) DEFAULT NULL';

-- ----------------------------
-- Records of request
-- ----------------------------
INSERT INTO `request` VALUES ('100001', 'B001', '5', '1350.00', 'R00012', '2015-02-03', '通过', 'D00001', '2015-02-15', '10002', null);
INSERT INTO `request` VALUES ('100002', 'B002', '3', '390.00', 'R00013', '2015-03-06', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('100003', 'B006', '80', '120.00', 'R00011', '2015-04-02', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('200001', 'B004', '15', '75.00', 'R00021', '2015-06-03', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('200002', 'B002', '2', '260.00', 'R00022', '2015-06-23', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('200003', 'B009', '18', '90.00', 'R00025', '2015-06-30', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('300001', 'B006', '70', '105.00', 'R00033', '2015-07-05', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('300002', 'B004', '60', '300.00', 'R00031', '2015-08-20', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('300003', 'B007', '20', '200.00', 'R00032', '2015-05-02', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('300004', 'B005', '30', '360.00', 'R00033', '2015-03-23', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('400001', 'B010', '40', '60.00', 'R00041', '2015-05-23', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('400002', 'A003', '3', '600.00', 'R00042', '2015-04-25', '未审核', null, null, null, null);
INSERT INTO `request` VALUES ('400003', 'A005', '4', '1200.00', 'R00044', '2015-09-17', '拒绝', 'P00001', '2015-09-21', null, null);
INSERT INTO `request` VALUES ('400004', 'A006', '7', '350.00', 'R00042', '2015-10-23', '完成', 'P00002', '2015-10-30', '10001', null);
INSERT INTO `request` VALUES ('500001', 'A008', '20', '4600.00', 'R00051', '2015-08-02', '拒绝', 'P00003', '2015-08-10', null, null);
INSERT INTO `request` VALUES ('500002', 'A004', '15', '1500.00', 'R00052', '2015-12-03', '未审核', null, null, null, null);

-- ----------------------------
-- Table structure for `requestcount`
-- ----------------------------
DROP TABLE IF EXISTS `requestcount`;
CREATE TABLE `requestcount` (
  `UserID` varchar(20) NOT NULL,
  `Count` int(11) DEFAULT '0',
  `RealCount` int(11) DEFAULT '0',
  `RequestAccount` double(11,2) DEFAULT '0.00',
  `RealAccount` double(11,2) DEFAULT '0.00',
  `RemainAccount` double(11,2) DEFAULT '30000.00',
  PRIMARY KEY (`UserID`),
  CONSTRAINT `requestcount_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of requestcount
-- ----------------------------
INSERT INTO `requestcount` VALUES ('R00011', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00012', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00013', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00014', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00015', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00021', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00022', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00023', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00024', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00025', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00031', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00032', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00033', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00034', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00035', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00041', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00042', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00043', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00044', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00045', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00051', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00052', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00053', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00054', '0', '0', '0.00', '0.00', '30000.00');
INSERT INTO `requestcount` VALUES ('R00055', '0', '0', '0.00', '0.00', '30000.00');

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `SupplierID` varchar(20) NOT NULL DEFAULT '',
  `Suppliername` varchar(45) NOT NULL,
  `Contacts` varchar(20) NOT NULL,
  `Telnumber` varchar(20) NOT NULL,
  `Address` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`SupplierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商信息\nSupplierID   供应商ID  varchar(20) NOT NULL DEFAULT  主键\nSuppliername  供应商名字  varchar(45) NOT NULL\nContacts   供应商联系人  varchar(20) NOT NULL\nTelnumber   供应商联系方式  varchar(20) NOT NULL';

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('A001', '东莞永安科技有限公司', '倪正文', '13756234455', '东莞');
INSERT INTO `supplier` VALUES ('A002', '博蓝电子有限公司', '汉斐斐', '13556224433', '上海');
INSERT INTO `supplier` VALUES ('A003', '邦士电子有限公司', '席秀美', '13755336699', '广州');
INSERT INTO `supplier` VALUES ('A004', '上海凝睿有限公司', '丁高歌', '13711556633', '上海');
INSERT INTO `supplier` VALUES ('A005', '捷科电子有限公司', '力高明', '13777556633', '北京');
INSERT INTO `supplier` VALUES ('B001', '东安文具有限公司', '从芳洁', '13777556622', '杭州');
INSERT INTO `supplier` VALUES ('B002', '苏光文具有限公司', '荣和泽', '13711223333', '北京');
INSERT INTO `supplier` VALUES ('B003', '光谷文具有限公司', '钟楼乐', '18566345269', '深圳');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
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

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('D00001', '123456789', '李浩鹏', '13712365633', '市场部门经理', 'yhp@pecr.com');
INSERT INTO `user` VALUES ('D00002', '123456789', '李铁刚', '13756633284', '财务部门经理', 'ytg@pecr.com');
INSERT INTO `user` VALUES ('D00003', '123456789', '李文轩', '13566325698', '生产部门经理', 'ywx@pecr.com');
INSERT INTO `user` VALUES ('D00004', '123456789', '李慧', '18956332289', '研发部门经理', 'yh@pecr.com');
INSERT INTO `user` VALUES ('D00005', '123456789', '李子豪', '13914476959', '销售部门经理', 'lzh@pecr.com');
INSERT INTO `user` VALUES ('M00001', '123456789', '赵彦龙', '1375632489', '总经理', 'zyl@pecr.com');
INSERT INTO `user` VALUES ('P00001', '123456789', '无子安', '13214955374', '采购员', 'wza@pecr.com');
INSERT INTO `user` VALUES ('P00002', '123456789', '将玉山', '13606451724', '采购员', 'jys@pecr.com');
INSERT INTO `user` VALUES ('P00003', '123456789', '友阳羽', '13128149687', '采购员', 'yyy@pecr.com');
INSERT INTO `user` VALUES ('P00004', '123456789', '闵雪松', '13337246940', '采购员', 'mxs@pecr.com');
INSERT INTO `user` VALUES ('P00005', '123456789', '中星文', '13177913127', '采购员', 'zxw@pecr.com');
INSERT INTO `user` VALUES ('R00011', '123456789', '王长旭', '13371653414', '市场部门申请员', 'wcx@pecr.com');
INSERT INTO `user` VALUES ('R00012', '123456789', '王鸿朗', '13091099159', '市场部门申请员', 'whl@pecr.com');
INSERT INTO `user` VALUES ('R00013', '123456789', '王星华', '13041307225', '市场部门申请员', 'wxh@pecr.com');
INSERT INTO `user` VALUES ('R00014', '123456789', '王欣荣', '13825315788', '市场部门申请员', 'wxr@pecr.com');
INSERT INTO `user` VALUES ('R00015', '123456789', '王子墨', '13476266540', '市场部门申请员', 'wzm@pecr.com');
INSERT INTO `user` VALUES ('R00021', '123456789', '宋运骏', '13131494168', '财务部门申请员', 'syy@pecr.com');
INSERT INTO `user` VALUES ('R00022', '123456789', '宋志专', '13869891844', '财务部门申请员', 'szc@pecr.com');
INSERT INTO `user` VALUES ('R00023', '123456789', '宋鹏涛', '13489250914', '财务部门申请员', 'spt@pecr.com');
INSERT INTO `user` VALUES ('R00024', '123456789', '宋飞昂', '13638691102', '财务部门申请员', 'sfy@pecr.com');
INSERT INTO `user` VALUES ('R00025', '123456789', '宋高阳', '13769791722', '财务部门申请员', 'sgy@pecr.com');
INSERT INTO `user` VALUES ('R00031', '123456789', '宫正祥', '13482859232', '生产部门申请员', 'gzx@pecr.com');
INSERT INTO `user` VALUES ('R00032', '123456789', '红子平', '13704095804', '生产部门申请员', 'hzp@pecr.com');
INSERT INTO `user` VALUES ('R00033', '123456789', '崇鹏天', '13455441446', '生产部门申请员', 'zpt@pecr.com');
INSERT INTO `user` VALUES ('R00034', '123456789', '于和豫', '13287383596', '生产部门申请员', 'yhy@pecr.com');
INSERT INTO `user` VALUES ('R00035', '123456789', '董智阳', '13137894871', '生产部门申请员', 'dzy@pecr.com');
INSERT INTO `user` VALUES ('R00041', '123456789', '肖晋鹏', '13849971987', '研发部门申请员', 'xjp@pecr.com');
INSERT INTO `user` VALUES ('R00042', '123456789', '傅和志', '13471835919', '研发部门申请员', 'phz@pecr.com');
INSERT INTO `user` VALUES ('R00043', '123456789', '代乐成', '13844349114', '研发部门申请员', 'dlc@pecr.com');
INSERT INTO `user` VALUES ('R00044', '123456789', '毕德义', '13435566214', '研发部门申请员', 'bdy@pecr.com');
INSERT INTO `user` VALUES ('R00045', '123456789', '以兴思', '13129287173', '研发部门申请员', 'yxs@pecr.com');
INSERT INTO `user` VALUES ('R00051', '123456789', '田绍元', '13858579863', '销售部门申请员', 'tsy@pecr.com');
INSERT INTO `user` VALUES ('R00052', '123456789', '白心水', '13396305510', '销售部门申请员', 'bxs@pecr.com');
INSERT INTO `user` VALUES ('R00053', '123456789', '官天泽', '13510038329', '销售部门申请员', 'gtz@pecr.com');
INSERT INTO `user` VALUES ('R00054', '123456789', '红文康', '13672414200', '销售部门申请员', 'hwk@pecr.com');
INSERT INTO `user` VALUES ('R00055', '123456789', '马嘉禧', '13952003686', '销售部门申请员', 'mjx@pecr.com');
INSERT INTO `user` VALUES ('S00001', '123456789', '丹修然', '13575230336', '库管员', 'dxr@pecr.com');
INSERT INTO `user` VALUES ('S00002', '123456789', '业运凯', '13050824494', '库管员', 'yyh@pecr.com');
INSERT INTO `user` VALUES ('S00003', '123456789', '秦弘光', '13777162072', '库管员', 'thg@pecr.com');
INSERT INTO `user` VALUES ('S00004', '123456789', '似成弘', '13774621083', '库管员', 'sch@pecr.com');
INSERT INTO `user` VALUES ('S00005', '123456789', '袭英卫', '13773745327', '库管员', 'qyw@pecr.com');
