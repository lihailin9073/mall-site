/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.201
Source Server Version : 80021
Source Host           : 192.168.1.201:3306
Source Database       : mall-site

Target Server Type    : MYSQL
Target Server Version : 80021
File Encoding         : 65001

Date: 2021-10-10 23:27:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_ads
-- ----------------------------
DROP TABLE IF EXISTS `m_ads`;
CREATE TABLE `m_ads` (
  `ad_id` smallint unsigned NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告名称',
  `ad_link` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告链接',
  `ad_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告图片/视频',
  `start_time` int NOT NULL DEFAULT '0' COMMENT '播放开始时间',
  `end_time` int NOT NULL DEFAULT '0' COMMENT '播放结束时间',
  `link_man` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告联系人',
  `link_email` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告联系电话',
  `link_phone` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `click_count` mediumint unsigned NOT NULL DEFAULT '0' COMMENT '点击次数',
  `ad_bg_code` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '背景颜色：用于前端的网页呈现',
  `media_type` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '媒体类型：0=图片广告，1=视频广告',
  `ad_type` smallint NOT NULL DEFAULT '0' COMMENT '广告类型：0=PC端广告，1=移动端广告',
  `position_id` smallint unsigned NOT NULL DEFAULT '0' COMMENT '广告位ID',
  `enabled` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否开启：0=未开启，1=已开启',
  PRIMARY KEY (`ad_id`),
  KEY `position_id` (`position_id`),
  KEY `enabled` (`enabled`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='广告表';

-- ----------------------------
-- Records of m_ads
-- ----------------------------
INSERT INTO `m_ads` VALUES ('1', 'PC首页顶部Banner广告', 'https://www.vvic.com/shop/54099', 'http://img.vvic.com/img/1632985875423_52713.jpg', '1435564800', '1849664000', '陈晓蓓', 'chenxiaobei@vvic.com', '020-6389637', '184', '#000333', '0', '0', '6', '1');

-- ----------------------------
-- Table structure for m_ads_position
-- ----------------------------
DROP TABLE IF EXISTS `m_ads_position`;
CREATE TABLE `m_ads_position` (
  `position_id` tinyint unsigned NOT NULL AUTO_INCREMENT,
  `position_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告位名称',
  `ad_width` smallint unsigned NOT NULL DEFAULT '0' COMMENT '广告位宽度',
  `ad_height` smallint unsigned NOT NULL DEFAULT '0' COMMENT '广告位高度',
  `position_style` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '广告位样式：即读取广告图片/视频的方式',
  `position_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '广告位说明',
  PRIMARY KEY (`position_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of m_ads_position
-- ----------------------------
INSERT INTO `m_ads_position` VALUES ('6', 'PC：首页顶部Banner栏目广告位', '1840', '150', '<table cellpadding=\"0\" cellspacing=\"0\">\r\n{foreach from=$ads item=ad}\r\n<tr><td>{$ad}</td></tr>\r\n{/foreach}\r\n</table>', '位于PC端商城首页顶部Banner栏目的广告位，适用于播放显著的、展现性很强的宣传广告，比如：双11的爆品广告、营销活动广告等');

-- ----------------------------
-- Table structure for m_brand
-- ----------------------------
DROP TABLE IF EXISTS `m_brand`;
CREATE TABLE `m_brand` (
  `id` varchar(40) NOT NULL COMMENT '角色 ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌名称',
  `icon` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌Logo',
  `story` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品牌故事',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of m_brand
-- ----------------------------
INSERT INTO `m_brand` VALUES ('1', '香奈儿', 'http://test.demo.com/brand-logo.png', '品牌故事描述', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `m_brand` VALUES ('5', '江南布衣', 'http://test.demo.com/brand-logo.png', '品牌故事描述', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- Table structure for m_category
-- ----------------------------
DROP TABLE IF EXISTS `m_category`;
CREATE TABLE `m_category` (
  `id` varchar(40) NOT NULL COMMENT '角色 ID',
  `parent_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '父ID，0=顶级菜单',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '角色名称',
  `icon` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '品类图标',
  `keyword` varchar(128) DEFAULT NULL COMMENT '搜索关键词',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of m_category
-- ----------------------------
INSERT INTO `m_category` VALUES ('1', '0', '女装', 'http://baidu.com/logo.png', '女装,裙子', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `m_category` VALUES ('2', '0', '男装', 'http://baidu.com/logo.png', '男装,裤子', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `m_category` VALUES ('3', '0', '童装', 'http://baidu.com/logo.png', '童装', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- Table structure for m_goods
-- ----------------------------
DROP TABLE IF EXISTS `m_goods`;
CREATE TABLE `m_goods` (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '店铺名称',
  `price` decimal(16,2) DEFAULT NULL COMMENT '销售价格',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of m_goods
-- ----------------------------
INSERT INTO `m_goods` VALUES ('1001', '实拍蓝色小香风羊毛编织粗花呢法式收腰名媛风外套高品质', '9.90', '2021-10-01 11:23:32', '2021-10-01 11:23:32');

-- ----------------------------
-- Table structure for m_menu
-- ----------------------------
DROP TABLE IF EXISTS `m_menu`;
CREATE TABLE `m_menu` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `pid` int unsigned NOT NULL DEFAULT '0' COMMENT '菜单或权限父ID: 0为顶层菜单',
  `action` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '权限URI，比如：/user/edit',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限（菜单）名称',
  `icon` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单的图标css类如: fa-home',
  `type` int NOT NULL DEFAULT '3' COMMENT '权限类型：1目录，2菜单，3=按钮（即权限码）',
  `sort` int unsigned NOT NULL DEFAULT '999' COMMENT '排序',
  `is_show` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '1' COMMENT '是否显示：0=隐藏，1=显示',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=239 DEFAULT CHARSET=utf8 COMMENT='消费者端菜单权限表';

-- ----------------------------
-- Records of m_menu
-- ----------------------------
INSERT INTO `m_menu` VALUES ('11', '0', '/express/find', '快递查找', 'default-home', '3', '11', '1');
INSERT INTO `m_menu` VALUES ('12', '0', '/express/edit', '快递变更', 'default-home', '3', '12', '1');
INSERT INTO `m_menu` VALUES ('13', '0', '/express/search', '快递搜索', 'default-home', '3', '999', '1');
INSERT INTO `m_menu` VALUES ('14', '0', '/express/delete', '快递移除', 'default-home', '3', '999', '1');
INSERT INTO `m_menu` VALUES ('15', '0', '/express/add', '快递添加', 'default-home', '3', '999', '1');
INSERT INTO `m_menu` VALUES ('16', '0', '/auth/get-menus', '快递查找', 'default-home', '3', '11', '1');

-- ----------------------------
-- Table structure for m_order
-- ----------------------------
DROP TABLE IF EXISTS `m_order`;
CREATE TABLE `m_order` (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务ID',
  `order_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `number` int DEFAULT NULL COMMENT '商品数量',
  `unit_price` decimal(16,2) DEFAULT NULL COMMENT '单价',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
  `pay_status` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '支付状态：0=未支付，1=已支付，2=已退款',
  `order_status` enum('0','1','2','3','4','5') CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '0' COMMENT '订单状态：0=待支付，1=待发货，2=已发货，3=已收货，4=已完成，5=已取消',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of m_order
-- ----------------------------
INSERT INTO `m_order` VALUES ('3157984654', '202111132239', '2', '9.90', '19.80', '0', '0', '2021-10-01 11:37:45', '2021-10-01 11:37:45');

-- ----------------------------
-- Table structure for m_role
-- ----------------------------
DROP TABLE IF EXISTS `m_role`;
CREATE TABLE `m_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键：角色ID',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '会员标志码：user=普通用户，vip=高级用户，svip=贵宾用户',
  `remark` varchar(256) NOT NULL DEFAULT '' COMMENT '角色描述备注',
  `is_delete` enum('0','1') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否删除：0=未删除，1=已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='消费者端角色表';

-- ----------------------------
-- Records of m_role
-- ----------------------------
INSERT INTO `m_role` VALUES ('1', '普通会员', 'user', '默认会员：用户注册后默认的会员级别', '0');
INSERT INTO `m_role` VALUES ('2', 'VIP会员', 'vip', '高级用户：通过付费购买、系统赠送等方式获取', '0');
INSERT INTO `m_role` VALUES ('3', 'SVIP会员', 'svip', '贵宾会员：通过付费购买方式获得', '0');

-- ----------------------------
-- Table structure for m_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `m_role_menu`;
CREATE TABLE `m_role_menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色 ID',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of m_role_menu
-- ----------------------------
INSERT INTO `m_role_menu` VALUES ('1', '1', '11');
INSERT INTO `m_role_menu` VALUES ('2', '1', '12');
INSERT INTO `m_role_menu` VALUES ('3', '1', '16');

-- ----------------------------
-- Table structure for m_shop
-- ----------------------------
DROP TABLE IF EXISTS `m_shop`;
CREATE TABLE `m_shop` (
  `id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务ID',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '店铺名称',
  `logo` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '店铺Logo',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of m_shop
-- ----------------------------
INSERT INTO `m_shop` VALUES ('1', '海澜之家', 'http://image.wzliulan.com/001.jpg', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `m_shop` VALUES ('2', 'ZARA', 'http://image.wzliulan.com/002.jpg', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `m_shop` VALUES ('3', '韩都衣舍', 'http://image.wzliulan.com/002.jpg', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- Table structure for m_sms
-- ----------------------------
DROP TABLE IF EXISTS `m_sms`;
CREATE TABLE `m_sms` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `content` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '短信内容',
  `type` int NOT NULL DEFAULT '0' COMMENT '短信类型：0=登录验证码，1=注册验证码，2=支付验证码',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='短信表';

-- ----------------------------
-- Records of m_sms
-- ----------------------------
INSERT INTO `m_sms` VALUES ('33', '13802780104', '您的登录验证码：3333', '0', '2021-10-03 10:59:58');
INSERT INTO `m_sms` VALUES ('34', '13802780104', '您的登录验证码：3333', '0', '2021-10-03 11:00:00');
INSERT INTO `m_sms` VALUES ('35', '13802780104', '您的登录验证码：3333', '0', '2021-10-03 11:00:01');
INSERT INTO `m_sms` VALUES ('36', '13802780104', '您的登录验证码：3', '0', '2021-10-03 11:00:53');
INSERT INTO `m_sms` VALUES ('37', '13802780104', '您的登录验证码是：804800', '0', '2021-10-03 11:09:17');
INSERT INTO `m_sms` VALUES ('38', '13802780104', '您的登录验证码是：508100', '0', '2021-10-03 11:09:22');
INSERT INTO `m_sms` VALUES ('39', '13802780104', '您的登录验证码是：889500', '0', '2021-10-03 11:12:10');
INSERT INTO `m_sms` VALUES ('40', '13802780104', '您的登录验证码是：703735', '0', '2021-10-03 11:26:51');
INSERT INTO `m_sms` VALUES ('41', '13802780104', '您的登录验证码是：339536', '0', '2021-10-03 11:26:55');
INSERT INTO `m_sms` VALUES ('42', '13802780104', '您的登录验证码是：962393', '0', '2021-10-03 11:26:55');
INSERT INTO `m_sms` VALUES ('43', '13802780104', '您的登录验证码是：139577', '0', '2021-10-03 11:26:55');
INSERT INTO `m_sms` VALUES ('44', '13802780104', '您的登录验证码是：114070', '0', '2021-10-03 11:26:56');
INSERT INTO `m_sms` VALUES ('45', '13802780104', '您的登录验证码是：249516', '0', '2021-10-03 11:26:57');
INSERT INTO `m_sms` VALUES ('46', '13802780104', '您的登录验证码是：276639', '0', '2021-10-03 11:26:57');
INSERT INTO `m_sms` VALUES ('47', '13802780104', '您的登录验证码是：687227', '0', '2021-10-03 11:26:57');
INSERT INTO `m_sms` VALUES ('48', '13802780104', '您的登录验证码是：707553', '0', '2021-10-03 11:26:58');
INSERT INTO `m_sms` VALUES ('49', '13802780104', '您的登录验证码是：800368', '0', '2021-10-03 11:26:58');
INSERT INTO `m_sms` VALUES ('50', '13802780104', '您的登录验证码是：940076', '0', '2021-10-03 11:26:58');
INSERT INTO `m_sms` VALUES ('51', '13802780104', '您的登录验证码是：658631', '0', '2021-10-03 11:26:58');
INSERT INTO `m_sms` VALUES ('52', '13802780104', '您的登录验证码是：364003', '0', '2021-10-03 11:26:58');
INSERT INTO `m_sms` VALUES ('53', '13802780104', '您的登录验证码是：939715', '0', '2021-10-03 11:26:58');
INSERT INTO `m_sms` VALUES ('54', '13802780104', '您的登录验证码是：595077', '0', '2021-10-03 11:26:59');
INSERT INTO `m_sms` VALUES ('55', '13802780104', '您的登录验证码是：787527', '0', '2021-10-03 11:27:00');
INSERT INTO `m_sms` VALUES ('56', '13802780104', '您的登录验证码是：971402', '0', '2021-10-03 11:27:01');
INSERT INTO `m_sms` VALUES ('57', '13802780104', '您的登录验证码是：936078', '0', '2021-10-03 11:27:02');
INSERT INTO `m_sms` VALUES ('58', '13802780104', '您的登录验证码是：556327', '0', '2021-10-03 12:12:26');

-- ----------------------------
-- Table structure for m_supplier
-- ----------------------------
DROP TABLE IF EXISTS `m_supplier`;
CREATE TABLE `m_supplier` (
  `id` varchar(40) NOT NULL COMMENT '用户 ID',
  `username` varchar(60) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码，加密存储, admin/1234',
  `is_account_non_expired` int DEFAULT '1' COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int DEFAULT '1' COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int DEFAULT '1' COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int DEFAULT '1' COMMENT '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(60) DEFAULT NULL COMMENT '昵称',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像url',
  `mobile` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of m_supplier
-- ----------------------------
INSERT INTO `m_supplier` VALUES ('10', 'zara', '803c78ea0f4a98174a044dbe474cdb00', '1', '1', '1', '1', 'ZARA中国', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '16888886666', 'test1@qq.com', '2023-08-08 11:11:11', '2020-12-28 10:51:52');
INSERT INTO `m_supplier` VALUES ('100', 'hlzj', '123456', '0', '1', '0', '0', '海澜之家', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '13579246813', '13579246813@139.com', '2020-04-11 21:37:25', '2020-04-17 16:43:19');
INSERT INTO `m_supplier` VALUES ('1000', 'hellokoko', '578e277051b1d8653a1c95f6080b27a2', '1', '1', '1', '1', 'Hello KOKO', null, null, null, '2021-07-28 17:30:06', '2021-07-28 17:30:05');
INSERT INTO `m_supplier` VALUES ('10000', 'sooshow', 'd7ecd3369d40914cf2aa473c60834433', '1', '1', '1', '1', '搜秀', null, null, null, '2021-09-28 10:52:16', '2021-09-28 10:52:16');

-- ----------------------------
-- Table structure for m_token
-- ----------------------------
DROP TABLE IF EXISTS `m_token`;
CREATE TABLE `m_token` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `token` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录令牌',
  `expired` bigint NOT NULL DEFAULT '60' COMMENT '有效期（毫秒）',
  `device` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名',
  `type` int NOT NULL DEFAULT '0' COMMENT '令牌类型：0=消费者令牌',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `token` (`token`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='访问令牌表';

-- ----------------------------
-- Records of m_token
-- ----------------------------
INSERT INTO `m_token` VALUES ('22', '053f6a2f-7013-4bfb-8b1e-60580c51257d', '86400', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36 Edg/90.0.818.66', '1', '2', '2021-05-23 17:54:05');
INSERT INTO `m_token` VALUES ('23', '44249aad-2b08-4ad0-ae17-1b6db0580f4d', '3600', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36 Edg/91.0.864.37', '0', '1', '2021-06-13 10:28:03');
INSERT INTO `m_token` VALUES ('24', 'a8122bfe-47c1-4650-9d3c-7d92e69fc360', '3600', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '0', '1', '2021-10-02 11:30:56');
INSERT INTO `m_token` VALUES ('32', '40b44efa-77a7-4aa1-bf13-98ab28e8f4cb', '3600000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '0', '1', '2021-10-03 12:16:13');
INSERT INTO `m_token` VALUES ('35', 'b12fa403-6d7d-446a-9fe7-b7ec2fc0f4e0', '3600000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '0', '1', '2021-10-06 22:44:54');
INSERT INTO `m_token` VALUES ('36', 'bfb25348-094b-4504-b8ff-e9b47b7553ec', '3600000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '0', '1', '2021-10-07 09:04:31');
INSERT INTO `m_token` VALUES ('37', 'a80801fc-acc5-4693-ba39-a9fd6453c00c', '3600000', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36', '0', '1', '2021-10-08 08:56:16');

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码',
  `real_name` varchar(32) NOT NULL COMMENT '真实姓名',
  `logo` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '' COMMENT '用户头像',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `is_delete` enum('0','1') NOT NULL DEFAULT '0' COMMENT '是否删除：0=未删除，1=已删除',
  `create_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='消费者端用户表';

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES ('1', 'xiaoxiao', 'e10adc3949ba59abbe56e057f20f883e', '小小', '0', '13802780104', '0', '2021-10-02 10:58:43', '2021-10-02 10:58:43');
INSERT INTO `m_user` VALUES ('2', 'momo', 'e10adc3949ba59abbe56e057f20f883e', '默默', '0', '18902484680', '0', '2021-10-03 10:04:37', '2021-10-03 10:04:37');
INSERT INTO `m_user` VALUES ('3', 'qingqing', 'e10adc3949ba59abbe56e057f20f883e', '芹芹', '0', '18922723056', '0', '2021-10-03 10:04:58', '2021-10-03 10:04:58');

-- ----------------------------
-- Table structure for m_user_role
-- ----------------------------
DROP TABLE IF EXISTS `m_user_role`;
CREATE TABLE `m_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` int unsigned NOT NULL COMMENT '角色表id',
  `role_id` int unsigned NOT NULL COMMENT '用户表id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_id` (`user_id`,`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='总后台用户绑定角色表';

-- ----------------------------
-- Records of m_user_role
-- ----------------------------
INSERT INTO `m_user_role` VALUES ('1', '1', '1');
INSERT INTO `m_user_role` VALUES ('2', '2', '2');
INSERT INTO `m_user_role` VALUES ('3', '3', '3');

-- ----------------------------
-- Table structure for sys_admin
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin`;
CREATE TABLE `sys_admin` (
  `id` varchar(40) NOT NULL COMMENT '用户 ID',
  `username` varchar(60) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码，加密存储, admin/1234',
  `is_account_non_expired` int DEFAULT '1' COMMENT '帐户是否过期(1 未过期，0已过期)',
  `is_account_non_locked` int DEFAULT '1' COMMENT '帐户是否被锁定(1 未过期，0已过期)',
  `is_credentials_non_expired` int DEFAULT '1' COMMENT '密码是否过期(1 未过期，0已过期)',
  `is_enabled` int DEFAULT '1' COMMENT '帐户是否可用(1 可用，0 删除用户)',
  `nick_name` varchar(60) DEFAULT NULL COMMENT '昵称',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '头像url',
  `mobile` varchar(20) DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '注册邮箱',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `pwd_update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '密码更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `mobile` (`mobile`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of sys_admin
-- ----------------------------
INSERT INTO `sys_admin` VALUES ('1', 'admin', '803c78ea0f4a98174a044dbe474cdb00', '1', '1', '1', '1', '平台超管', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '16888886666', 'test1@qq.com', '2023-08-08 11:11:11', '2020-12-28 10:51:52', '2020-04-10 09:41:51');
INSERT INTO `sys_admin` VALUES ('10', 'zhangsan', '123456', '0', '1', '0', '0', '平台客服-01', 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', '13579246813', '13579246813@139.com', '2020-04-11 21:37:25', '2020-04-17 16:43:19', '2020-04-11 21:37:25');
INSERT INTO `sys_admin` VALUES ('1420315616434208769', 'lisi', '578e277051b1d8653a1c95f6080b27a2', '1', '1', '1', '1', null, null, null, null, '2021-07-28 17:30:06', '2021-07-28 17:30:05', '2021-07-28 17:30:05');
INSERT INTO `sys_admin` VALUES ('1442683548006875137', 'zhangruojun', 'd7ecd3369d40914cf2aa473c60834433', '1', '1', '1', '1', null, null, null, null, '2021-09-28 10:52:16', '2021-09-28 10:52:16', '2021-09-28 10:52:16');

-- ----------------------------
-- Table structure for sys_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_admin_role`;
CREATE TABLE `sys_admin_role` (
  `id` varchar(40) NOT NULL COMMENT '主键 ID',
  `user_id` varchar(40) DEFAULT NULL COMMENT '用户 ID',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系表';

-- ----------------------------
-- Records of sys_admin_role
-- ----------------------------
INSERT INTO `sys_admin_role` VALUES ('1287965845408120100', '1', '1');
INSERT INTO `sys_admin_role` VALUES ('1287965845408120101', '10', '5');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(40) NOT NULL COMMENT '菜单 ID',
  `parent_id` varchar(40) DEFAULT NULL COMMENT '父菜单 ID (0为顶级菜单)',
  `name` varchar(60) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端路由：比如vuejs的路由',
  `type` int DEFAULT '1' COMMENT '类型(1目录，2菜单，3按钮=权限码)',
  `code` varchar(60) DEFAULT NULL COMMENT '授权标识符',
  `icon` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '前端的图标',
  `sort` int DEFAULT '1' COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单信息表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1251060960026705920', '0', '首页', '/dashboard', '1', 'index', 'el-icon-home', '1', '', '2023-08-08 11:11:11', '2020-04-24 22:28:38');
INSERT INTO `sys_menu` VALUES ('1251060960026705921', '0', '资讯管理', '/content', '1', 'content', 'el-icon-content', '2', null, '2020-04-17 16:12:31', '2020-12-23 11:27:20');
INSERT INTO `sys_menu` VALUES ('1251060960026705922', '0', '课程管理', '/school', '1', 'school', 'el-icon-school', '2', null, '2020-04-17 16:12:31', '2020-12-23 11:27:20');
INSERT INTO `sys_menu` VALUES ('1251060960026705923', '0', '投资管理', '/investment', '1', 'investment', 'el-icon-investment', '2', null, '2020-04-17 16:12:31', '2020-12-23 11:27:20');
INSERT INTO `sys_menu` VALUES ('1251060960026705924', '0', '广告管理', '/ads', '1', 'ads', 'el-icon-ads', '2', null, '2020-04-17 16:12:31', '2020-12-23 11:27:20');
INSERT INTO `sys_menu` VALUES ('1251060960026705925', '0', '系统管理', '/system', '1', 'system', 'el-icon-system', '4', null, '2023-08-08 11:11:11', '2020-04-24 22:28:56');
INSERT INTO `sys_menu` VALUES ('1251060960026705935', '0', '官方网站', 'http://www.abroadpass.com', '2', 'public', 'el-icon-public', '5', null, '2020-04-24 10:36:42', '2020-04-24 15:58:34');
INSERT INTO `sys_menu` VALUES ('1251061181913776128', '1251060960026705921', '分类管理', '/content/category', '2', 'category', 'el-icon-category', '3', null, '2020-04-17 16:12:44', '2020-04-24 10:33:28');
INSERT INTO `sys_menu` VALUES ('1251061181913776129', '1251060960026705921', '标签管理', '/content/label', '2', 'label', 'el-icon-label', '2', null, '2020-04-17 16:13:24', '2020-04-24 10:33:14');
INSERT INTO `sys_menu` VALUES ('1251061181913776130', '1251060960026705921', '文章管理', '/content/article', '2', 'article', 'el-icon-article', '2', null, '2020-04-17 16:13:24', '2020-04-24 10:33:14');
INSERT INTO `sys_menu` VALUES ('1251061228965478501', '1251060960026705922', '课程频道管理', '/school/category', '2', 'school-category', 'el-icon-school-category', '1', null, '2020-04-17 16:13:35', '2020-04-24 15:40:53');
INSERT INTO `sys_menu` VALUES ('1251061228965478502', '1251060960026705922', '课程视频管理', '/school/course', '2', 'course', 'el-icon-course', '1', null, '2020-04-17 16:13:35', '2020-04-24 15:40:53');
INSERT INTO `sys_menu` VALUES ('1251061228965478580', '1251060960026705923', '投资人管理', '/investment/investor', '2', 'investor', 'el-icon-investor', '3', null, '2020-04-17 16:54:22', '2020-04-24 22:20:46');
INSERT INTO `sys_menu` VALUES ('1251061228965478581', '1251060960026705923', '投资项目管理', '/investment/project', '2', 'project', 'el-icon-project', '3', null, '2020-04-17 16:54:22', '2020-04-24 22:20:46');
INSERT INTO `sys_menu` VALUES ('1251061228965478601', '1251060960026705924', '广告位管理', '/ads/position', '2', 'position', 'el-icon-position', '1', null, '2023-08-08 11:11:11', '2020-07-10 16:28:26');
INSERT INTO `sys_menu` VALUES ('1251061228965478602', '1251060960026705924', '广告内容管理', '/ads/content', '2', 'ads-content', 'el-icon-ads-content', '2', null, '2023-08-08 11:11:11', '2020-07-10 16:28:36');
INSERT INTO `sys_menu` VALUES ('1251061228965478603', '1251060960026705925', '系统用户管理', '/system/admin', '2', 'admin', 'el-icon-admin', '3', null, '2023-08-08 11:11:11', '2020-07-10 16:28:43');
INSERT INTO `sys_menu` VALUES ('1251061280744161260', '1251060960026705925', '系统角色管理', '/system/role', '2', 'role', 'el-icon-role', '2', null, '2020-04-17 16:53:04', '2020-07-10 16:23:17');
INSERT INTO `sys_menu` VALUES ('1251061280744161261', '1251060960026705925', '系统菜单管理', '/system/menu', '2', 'menu', 'el-icon-menu', '4', null, '2020-04-17 16:53:29', '2020-07-10 16:23:03');
INSERT INTO `sys_menu` VALUES ('1251061280744161262', '1251061181913776128', '分类新增', '', '3', 'content:category:add', '', '4', null, '2020-04-17 16:53:17', '2020-07-10 16:22:53');
INSERT INTO `sys_menu` VALUES ('1251061280744161263', '1251061181913776128', '分类删除', '', '3', 'content:category:delete', '', '1', null, '2020-04-17 16:52:45', '2020-07-10 16:23:26');
INSERT INTO `sys_menu` VALUES ('1251061280744161264', '1251061181913776128', '分类修改', '', '3', 'content:category:update', '', '1', null, '2020-04-17 16:52:45', '2020-07-10 16:23:26');
INSERT INTO `sys_menu` VALUES ('1251061280744161270', '1251061181913776128', '分类查找', '', '3', 'content:category:find', '', '2', null, '2020-04-17 16:50:40', '2020-07-10 16:24:30');
INSERT INTO `sys_menu` VALUES ('1251061280744161271', '1251061181913776128', '分类搜索', '', '3', 'content:category:search', '', '4', null, '2020-04-17 16:51:24', '2020-07-10 16:24:20');
INSERT INTO `sys_menu` VALUES ('1251061280744161272', '1251061181913776129', '标签新增', '', '3', 'content:label:add', '', '3', null, '2020-04-17 16:50:53', '2020-07-10 16:24:37');
INSERT INTO `sys_menu` VALUES ('1251061280744161273', '1251061181913776129', '标签删除', '', '3', 'content:label:delete', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161274', '1251061181913776129', '标签修改', '', '3', 'content:label:update', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161275', '1251061181913776129', '标签查找', '', '3', 'content:label:find', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161276', '1251061181913776129', '标签搜索', '', '3', 'content:label:search', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161277', '1251061181913776130', '文章新增', '', '3', 'content:article:add', '', '3', null, '2020-04-17 16:50:53', '2020-07-10 16:24:37');
INSERT INTO `sys_menu` VALUES ('1251061280744161278', '1251061181913776130', '文章删除', '', '3', 'content:article:delete', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161279', '1251061181913776130', '文章修改', '', '3', 'content:article:update', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161280', '1251061181913776130', '文章查找', '', '3', 'content:article:find', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161281', '1251061181913776130', '文章搜索', '', '3', 'content:article:search', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');
INSERT INTO `sys_menu` VALUES ('1251061280744161380', '#', '用户新增', '', '3', 'user:add', '', '1', null, '2020-04-17 16:50:24', '2020-07-10 16:24:24');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(40) NOT NULL COMMENT '角色 ID',
  `name` varchar(60) DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(200) DEFAULT NULL COMMENT '角色说明',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色信息表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'ROLE_SUPPER', '系统超级管理员，拥有所有的权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');
INSERT INTO `sys_role` VALUES ('5', 'ROLE_SYSTEM', '系统普通管理员，拥有大部分的权限', '2023-08-08 11:11:11', '2023-08-08 11:11:11');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` varchar(40) NOT NULL COMMENT '主键 ID',
  `role_id` varchar(40) DEFAULT NULL COMMENT '角色 ID',
  `menu_id` varchar(40) DEFAULT NULL COMMENT '菜单 ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('1287965925087313921', '1', '1251060960026705920');
INSERT INTO `sys_role_menu` VALUES ('1287965925087313922', '1', '1251060960026705921');
INSERT INTO `sys_role_menu` VALUES ('1287965925087313923', '1', '1251060960026705922');
INSERT INTO `sys_role_menu` VALUES ('1287965925087313924', '1', '1251060960026705923');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508225', '1', '1251060960026705924');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508226', '1', '1251060960026705925');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508227', '1', '1251060960026705926');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508228', '1', '1251060960026705927');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508229', '1', '1251060960026705939');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508230', '1', '1251060960026705940');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508231', '1', '1251061181913776128');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508232', '1', '1251061181913776129');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508233', '1', '1251061181913776130');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508234', '1', '1251060960026705920');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508235', '1', '1251060960026705921');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508236', '1', '1251060960026705922');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508237', '1', '1251060960026705923');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508238', '1', '1251060960026705924');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508239', '1', '1251060960026705925');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508240', '1', '1251060960026705935');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508241', '1', '1251061181913776128');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508242', '1', '1251061181913776129');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508243', '1', '1251061181913776130');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508244', '1', '1251061228965478501');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508245', '1', '1251061228965478502');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508246', '1', '1251061228965478580');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508247', '1', '1251061228965478581');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508248', '1', '1251061228965478601');
INSERT INTO `sys_role_menu` VALUES ('1287965925091508249', '1', '1251061228965478602');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702529', '1', '1251061228965478603');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702530', '1', '1251061280744161260');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702531', '1', '1251061280744161261');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702532', '1', '1251061280744161262');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702533', '1', '1251061280744161263');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702534', '1', '1251061280744161264');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702535', '1', '1251061280744161270');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702536', '1', '1251061280744161271');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702537', '1', '1251061280744161272');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702538', '1', '1251061280744161273');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702539', '1', '1251061280744161274');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702540', '1', '1251061280744161275');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702541', '1', '1251061280744161276');
INSERT INTO `sys_role_menu` VALUES ('1287965925095702542', '1', '1251061280744161277');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942914', '1', '1251061280744161278');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942915', '1', '1251061280744161279');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942916', '1', '1251061280744161280');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942917', '1', '1251061280744161281');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942918', '1', '1251061280744161380');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942938', '1', '001');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942939', '1', '001');
INSERT INTO `sys_role_menu` VALUES ('1343031779291942940', '1', '001');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943900', '5', '1251060960026705920');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943901', '5', '1251060960026705921');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943902', '5', '1251061181913776128');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943903', '5', '1251061181913776129');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943904', '5', '1251061181913776130');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943905', '5', '1251061280744161262');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943906', '5', '1251061280744161263');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943907', '5', '1251061280744161264');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943908', '5', '1251061280744161270');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943909', '5', '1251061280744161271');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943910', '5', '1251061280744161272');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943911', '5', '1251061280744161273');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943912', '5', '1251061280744161274');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943913', '5', '1251061280744161275');
INSERT INTO `sys_role_menu` VALUES ('1343031779291943914', '5', '1251061280744161276');
