/*
Navicat SQL Server Data Transfer

Source Server         : SQLSERVER
Source Server Version : 105000
Source Host           : 172.16.0.202:1433
Source Database       : golte_dev
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 105000
File Encoding         : 65001

Date: 2019-05-10 15:15:47
*/


-- ----------------------------
-- Table structure for g_annex
-- ----------------------------
DROP TABLE [dbo].[g_annex]
GO
CREATE TABLE [dbo].[g_annex] (
[TYPE] nchar(10) NOT NULL ,
[ASSOCIATION_ID] bigint NOT NULL ,
[NAME] nchar(128) NOT NULL ,
[URL] nchar(256) NOT NULL ,
[CREATE_TIME] datetime NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_annex', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'附件表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'附件表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_annex', 
'COLUMN', N'TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'类型 (1合同附件 2商家附件 3商家评估附件 4合同终止附件)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'类型 (1合同附件 2商家附件 3商家评估附件 4合同终止附件)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_annex', 
'COLUMN', N'ASSOCIATION_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'关联ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'ASSOCIATION_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'关联ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'ASSOCIATION_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_annex', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'附件名称
'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'附件名称
'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_annex', 
'COLUMN', N'URL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'附件地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'URL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'附件地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_annex'
, @level2type = 'COLUMN', @level2name = N'URL'
GO

-- ----------------------------
-- Table structure for g_area
-- ----------------------------
DROP TABLE [dbo].[g_area]
GO
CREATE TABLE [dbo].[g_area] (
[ID] bigint NOT NULL ,
[AREA_NO] nvarchar(50) NOT NULL ,
[NAME] nvarchar(50) NOT NULL ,
[F_ID] bigint NOT NULL ,
[F_NO] nvarchar(50) NOT NULL ,
[AREA_STATUS] nvarchar(50) NOT NULL DEFAULT ((1)) ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中国行政地区表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中国行政地区表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'AREA_NO')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'地区编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'AREA_NO'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'地区编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'AREA_NO'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'地区名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'地区名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'F_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上级ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'F_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上级ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'F_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'F_NO')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上级编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'F_NO'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上级编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'F_NO'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'AREA_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态(0无效 1有效)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'AREA_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态(0无效 1有效)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'AREA_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_area', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_area'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO

-- ----------------------------
-- Table structure for g_central_city
-- ----------------------------
DROP TABLE [dbo].[g_central_city]
GO
CREATE TABLE [dbo].[g_central_city] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CENTRAL_CITY_NAME] nvarchar(100) NOT NULL ,
[EMP_ID] bigint NOT NULL ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(50) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_central_city]', RESEED, 34)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'CENTRAL_CITY_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'EMP_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市负责人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'EMP_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市负责人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'EMP_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态(0 删除 1未删除)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态(0 删除 1未删除)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_central_city', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_central_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO

-- ----------------------------
-- Table structure for g_city
-- ----------------------------
DROP TABLE [dbo].[g_city]
GO
CREATE TABLE [dbo].[g_city] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CENTRAL_CITY_ID] bigint NULL ,
[CITY_NAME] nvarchar(100) NULL ,
[CITY_PRINCIPAL] bigint NULL ,
[BUSINESS_PRINCIPAL] bigint NULL ,
[YEAR_TARGET] decimal(15,2) NULL ,
[DEL_STATUS] nvarchar(10) NULL ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(20) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(20) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_city]', RESEED, 39)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'CENTRAL_CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'CITY_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CITY_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CITY_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'CITY_PRINCIPAL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市分公司负责人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CITY_PRINCIPAL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市分公司负责人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CITY_PRINCIPAL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'BUSINESS_PRINCIPAL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'经营部分负责人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'BUSINESS_PRINCIPAL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'经营部分负责人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'BUSINESS_PRINCIPAL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'YEAR_TARGET')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'当年年度指标(单位：万元)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'YEAR_TARGET'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'当年年度指标(单位：万元)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'YEAR_TARGET'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态(0 删除 1未删除)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态(0 删除 1未删除)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO

-- ----------------------------
-- Table structure for g_city_entry
-- ----------------------------
DROP TABLE [dbo].[g_city_entry]
GO
CREATE TABLE [dbo].[g_city_entry] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CENTRAL_CITY_ID] bigint NOT NULL ,
[CITY_ID] bigint NOT NULL ,
[ENTER_PERSON] bigint NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_city_entry]', RESEED, 82)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_entry', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市录入人员表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市录入人员表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_entry', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_entry', 
'COLUMN', N'CENTRAL_CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_entry', 
'COLUMN', N'CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_entry', 
'COLUMN', N'ENTER_PERSON')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'录入人员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'ENTER_PERSON'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'录入人员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_entry'
, @level2type = 'COLUMN', @level2name = N'ENTER_PERSON'
GO

-- ----------------------------
-- Table structure for g_city_year_target
-- ----------------------------
DROP TABLE [dbo].[g_city_year_target]
GO
CREATE TABLE [dbo].[g_city_year_target] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CITY_ID] bigint NOT NULL ,
[YEAR] nvarchar(100) NOT NULL ,
[YEAR_TARGET] decimal(15,2) NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_city_year_target]', RESEED, 11)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_year_target', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市年度指标表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市年度指标表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_year_target', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_year_target', 
'COLUMN', N'CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_year_target', 
'COLUMN', N'YEAR')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'年度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'YEAR'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'年度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'YEAR'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_city_year_target', 
'COLUMN', N'YEAR_TARGET')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'年度指标(单位：万元)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'YEAR_TARGET'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'年度指标(单位：万元)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_city_year_target'
, @level2type = 'COLUMN', @level2name = N'YEAR_TARGET'
GO

-- ----------------------------
-- Table structure for g_configure
-- ----------------------------
DROP TABLE [dbo].[g_configure]
GO
CREATE TABLE [dbo].[g_configure] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[COG_KEY] nvarchar(64) NOT NULL ,
[COG_VALUE] nvarchar(512) NULL ,
[COG_NAME] nvarchar(128) NULL ,
[COG_EXTEND_ONE] nvarchar(256) NULL ,
[COG_EXTEND_TWO] nvarchar(256) NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_configure]', RESEED, 35)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'COG_KEY')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'key --唯一'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_KEY'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'key --唯一'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_KEY'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'COG_VALUE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_VALUE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_VALUE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'COG_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'COG_EXTEND_ONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'扩展字段一'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_EXTEND_ONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'扩展字段一'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_EXTEND_ONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'COG_EXTEND_TWO')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'扩展字段二'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_EXTEND_TWO'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'扩展字段二'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'COG_EXTEND_TWO'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间--年月日时分秒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间--年月日时分秒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_configure', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间--年月日时分秒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间--年月日时分秒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_configure'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO

-- ----------------------------
-- Table structure for g_contract
-- ----------------------------
DROP TABLE [dbo].[g_contract]
GO
CREATE TABLE [dbo].[g_contract] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CODE] nvarchar(32) NULL ,
[NAME] nvarchar(64) NOT NULL ,
[CENTRAL_CITY_ID] bigint NULL ,
[CITY_ID] bigint NULL ,
[MERCHANT_ID] bigint NOT NULL ,
[MERCHANT_CONTACT] nvarchar(50) NULL ,
[MERCHANT_CONTACT_PHONE] nvarchar(32) NULL ,
[SIGNING_CONTACT] nvarchar(50) NULL ,
[SIGNING_CONTACT_PHONE] nvarchar(32) NULL ,
[SIGNING_TIME] datetime NULL ,
[DEADLINE_START_TIME] datetime NULL ,
[DEADLINE_END_TIME] datetime NULL ,
[AMOUNT] decimal(11,2) NOT NULL ,
[MARGIN] decimal(11,2) NOT NULL ,
[SIGNING_TYPE] nvarchar(10) NULL ,
[CONTRACT_TYPE] nvarchar(10) NULL ,
[DARFT_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[PAYBACK_TYPE] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[CONTRACT_STATUS] nvarchar(10) NOT NULL DEFAULT ((2)) ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[APPROVAL_TIME] datetime NULL ,
[TERMINATION_TIME] datetime NULL ,
[REMARKS] nvarchar(256) NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract]', RESEED, 111)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CENTRAL_CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'MERCHANT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合作商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合作商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'MERCHANT_CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'MERCHANT_CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'SIGNING_CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'SIGNING_CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'DEADLINE_START_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同期限开始时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_START_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同期限开始时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_START_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'DEADLINE_END_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同期限结束时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_END_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同期限结束时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_END_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同总额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同总额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'MARGIN')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'履约保证金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MARGIN'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'履约保证金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'MARGIN'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'SIGNING_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约类型 1新合同 2续签'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约类型 1新合同 2续签'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CONTRACT_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同类别 1临时摆展'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同类别 1临时摆展'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'DARFT_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'草稿状态 0正式 1草稿'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DARFT_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'草稿状态 0正式 1草稿'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DARFT_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'PAYBACK_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款类型 1未回款 2部分回款 3已回款'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款类型 1未回款 2部分回款 3已回款'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CONTRACT_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同状态 1未开始 2进行中 3变更审批中 4终止审批中 5已结束 6已终止'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同状态 1未开始 2进行中 3变更审批中 4终止审批中 5已结束 6已终止'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态  0删除 1未删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态  0删除 1未删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'APPROVAL_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'过审时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'过审时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'TERMINATION_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'终止时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'终止时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'REMARKS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO

-- ----------------------------
-- Table structure for g_contract_approval
-- ----------------------------
DROP TABLE [dbo].[g_contract_approval]
GO
CREATE TABLE [dbo].[g_contract_approval] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[TYPE] nvarchar(10) NOT NULL ,
[SUPPER_EMPLOYEE_ID] nvarchar(125) NOT NULL ,
[STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[OPINION] nvarchar(256) NULL ,
[CREATE_TIME] datetime NULL ,
[APPROVAL_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[APPROVE_EMPLOYEE_ID] bigint NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_approval]', RESEED, 60)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同审批表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同审批表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批类型 1终止 2资源变更'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批类型 1终止 2资源变更'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'SUPPER_EMPLOYEE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上级审批人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'SUPPER_EMPLOYEE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上级审批人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'SUPPER_EMPLOYEE_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批状态 1未审批 2通过 3未通过'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批状态 1未审批 2通过 3未通过'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'OPINION')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批意见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'OPINION'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批意见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'OPINION'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'APPROVAL_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_approval', 
'COLUMN', N'APPROVE_EMPLOYEE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批操作人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVE_EMPLOYEE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批操作人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVE_EMPLOYEE_ID'
GO

-- ----------------------------
-- Table structure for g_contract_old
-- ----------------------------
DROP TABLE [dbo].[g_contract_old]
GO
CREATE TABLE [dbo].[g_contract_old] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[CODE] nvarchar(32) NOT NULL ,
[NAME] nvarchar(64) NOT NULL ,
[MERCHANT_ID] bigint NOT NULL ,
[MERCHANT_CONTACT] nvarchar(50) NOT NULL ,
[MERCHANT_CONTACT_PHONE] nvarchar(32) NULL ,
[SIGNING_CONTACT] nvarchar(50) NOT NULL ,
[SIGNING_CONTACT_PHONE] nvarchar(32) NULL ,
[SIGNING_TIME] datetime NULL ,
[DEADLINE_START_TIME] datetime NOT NULL ,
[DEADLINE_END_TIME] datetime NOT NULL ,
[AMOUNT] decimal(18,2) NOT NULL ,
[MARGIN] decimal(18,2) NOT NULL ,
[SIGNING_TYPE] nvarchar(10) NOT NULL ,
[CONTRACT_TYPE] nvarchar(10) NULL ,
[REMARKS] nvarchar(255) NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL ,
[APPROVAL_ID] bigint NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_old]', RESEED, 20)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'CODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'MERCHANT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合作商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合作商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'MERCHANT_CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'MERCHANT_CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'SIGNING_CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'SIGNING_CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'SIGNING_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'DEADLINE_START_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同期限开始时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_START_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同期限开始时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_START_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'DEADLINE_END_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同期限结束时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_END_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同期限结束时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_END_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同总额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同总额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'MARGIN')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'履约保证金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MARGIN'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'履约保证金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'MARGIN'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'SIGNING_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'CONTRACT_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同类别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同类别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'REMARKS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_old', 
'COLUMN', N'APPROVAL_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_old'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
GO

-- ----------------------------
-- Table structure for g_contract_project
-- ----------------------------
DROP TABLE [dbo].[g_contract_project]
GO
CREATE TABLE [dbo].[g_contract_project] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[PROJECT_ID] bigint NOT NULL ,
[POINT_ID] bigint NULL ,
[TOLL_MODE] nvarchar(10) NOT NULL ,
[PAYMENT_CYCLE] nvarchar(10) NULL ,
[UNIT_PRICE] decimal(11,2) NULL ,
[NUMBER] int NULL ,
[SUBTOTAL] decimal(11,2) NULL ,
[STATUS] nvarchar(10) NOT NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL ,
[CHANGE_PAYBACK_AMOUNT] decimal(11,2) NULL ,
[UNIT_NAME] nvarchar(25) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_project]', RESEED, 222)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'POINT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'点位ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'点位ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'TOLL_MODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收费模式 1一次性 2固定 3佣金 4其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'TOLL_MODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收费模式 1一次性 2固定 3佣金 4其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'TOLL_MODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'PAYMENT_CYCLE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'付款周期 1一年年 2半年 3季度 4月 5两年 6三年 7四年 8五年'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'PAYMENT_CYCLE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'付款周期 1一年年 2半年 3季度 4月 5两年 6三年 7四年 8五年'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'PAYMENT_CYCLE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'UNIT_PRICE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'UNIT_PRICE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'UNIT_PRICE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'NUMBER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'NUMBER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'NUMBER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'SUBTOTAL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'小计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'SUBTOTAL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'小计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'SUBTOTAL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目状态 0失效 1有效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目状态 0失效 1有效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'CHANGE_PAYBACK_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'变更前已回款金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'CHANGE_PAYBACK_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'变更前已回款金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'CHANGE_PAYBACK_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project', 
'COLUMN', N'UNIT_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'UNIT_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project'
, @level2type = 'COLUMN', @level2name = N'UNIT_NAME'
GO

-- ----------------------------
-- Table structure for g_contract_project_old
-- ----------------------------
DROP TABLE [dbo].[g_contract_project_old]
GO
CREATE TABLE [dbo].[g_contract_project_old] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[APPROVAL_ID] bigint NOT NULL ,
[CONTRACT_ID] bigint NOT NULL ,
[PROJECT_ID] bigint NOT NULL ,
[POINT_ID] bigint NULL ,
[TOLL_MODE] nvarchar(10) NULL ,
[PAYMENT_CYCLE] nvarchar(10) NULL ,
[UNIT_PRICE] decimal(11,2) NULL ,
[NUMBER] int NULL ,
[SUBTOTAL] decimal(11,2) NULL ,
[CREATE_TIME] datetime NULL ,
[CONTRACT_PROJECT_ID] bigint NULL ,
[UNIT_NAME] nvarchar(25) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_project_old]', RESEED, 25)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'APPROVAL_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'POINT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'点位ID 三级点位ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'点位ID 三级点位ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'TOLL_MODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收费模式1一次性 2固定 3佣金 4其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'TOLL_MODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收费模式1一次性 2固定 3佣金 4其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'TOLL_MODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'PAYMENT_CYCLE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'付款周期 1年 2半年 3季度 4月'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'PAYMENT_CYCLE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'付款周期 1年 2半年 3季度 4月'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'PAYMENT_CYCLE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'UNIT_PRICE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'UNIT_PRICE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'UNIT_PRICE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'NUMBER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'NUMBER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'NUMBER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'SUBTOTAL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'小计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'SUBTOTAL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'小计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'SUBTOTAL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_old', 
'COLUMN', N'UNIT_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'UNIT_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_old'
, @level2type = 'COLUMN', @level2name = N'UNIT_NAME'
GO

-- ----------------------------
-- Table structure for g_contract_project_up
-- ----------------------------
DROP TABLE [dbo].[g_contract_project_up]
GO
CREATE TABLE [dbo].[g_contract_project_up] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[APPROVAL_ID] bigint NOT NULL ,
[CONTRACT_ID] bigint NOT NULL ,
[TYPE] nvarchar(10) NULL ,
[PROJECT_ID] bigint NOT NULL ,
[CONTRACT_PROJECT_ID] bigint NULL ,
[POINT_ID] bigint NULL ,
[TOLL_MODE] nvarchar(10) NULL ,
[PAYMENT_CYCLE] nvarchar(10) NULL ,
[UNIT_PRICE] decimal(11,2) NULL ,
[NUMBER] int NULL ,
[SUBTOTAL] decimal(11,2) NULL ,
[REASON] nvarchar(255) NULL ,
[EFFECTIVE_TIME] datetime NULL ,
[CREATE_TIME] datetime NULL ,
[UNIT_NAME] nvarchar(25) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_project_up]', RESEED, 38)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同变更项目表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同变更项目表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'APPROVAL_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'变更类型 1添加 2移除 3变更单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'变更类型 1添加 2移除 3变更单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'操作合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'操作合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'POINT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'点位ID 三级点位ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'点位ID 三级点位ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'TOLL_MODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收费模式1一次性 2固定 3佣金 4其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'TOLL_MODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收费模式1一次性 2固定 3佣金 4其他'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'TOLL_MODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'PAYMENT_CYCLE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'付款周期 1年 2半年 3季度 4月'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'PAYMENT_CYCLE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'付款周期 1年 2半年 3季度 4月'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'PAYMENT_CYCLE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'UNIT_PRICE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'UNIT_PRICE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源单价'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'UNIT_PRICE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'NUMBER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'NUMBER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源数量'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'NUMBER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'SUBTOTAL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'小计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'SUBTOTAL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'小计'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'SUBTOTAL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'REASON')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'变更原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'REASON'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'变更原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'REASON'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'EFFECTIVE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'变更生效时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'EFFECTIVE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'变更生效时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'EFFECTIVE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_project_up', 
'COLUMN', N'UNIT_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'UNIT_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'单位'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_project_up'
, @level2type = 'COLUMN', @level2name = N'UNIT_NAME'
GO

-- ----------------------------
-- Table structure for g_contract_resource
-- ----------------------------
DROP TABLE [dbo].[g_contract_resource]
GO
CREATE TABLE [dbo].[g_contract_resource] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[CONTRACT_PROJECT_ID] bigint NOT NULL ,
[RESOURCE_ID] bigint NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_resource]', RESEED, 226)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目资源表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目资源表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource', 
'COLUMN', N'RESOURCE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
GO

-- ----------------------------
-- Table structure for g_contract_resource_old
-- ----------------------------
DROP TABLE [dbo].[g_contract_resource_old]
GO
CREATE TABLE [dbo].[g_contract_resource_old] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[CONTRACT_PROJECT_ID] bigint NOT NULL ,
[RESOURCE_ID] bigint NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_resource_old]', RESEED, 26)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_old', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_old', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_old', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_old', 
'COLUMN', N'RESOURCE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_old'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
GO

-- ----------------------------
-- Table structure for g_contract_resource_up
-- ----------------------------
DROP TABLE [dbo].[g_contract_resource_up]
GO
CREATE TABLE [dbo].[g_contract_resource_up] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[CONTRACT_PROJECT_ID] bigint NOT NULL ,
[RESOURCE_ID] bigint NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_resource_up]', RESEED, 38)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_up', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同资源变更表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同资源变更表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_up', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_up', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_up', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'操作合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'操作合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_resource_up', 
'COLUMN', N'RESOURCE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_resource_up'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
GO

-- ----------------------------
-- Table structure for g_contract_termination
-- ----------------------------
DROP TABLE [dbo].[g_contract_termination]
GO
CREATE TABLE [dbo].[g_contract_termination] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[APPROVAL_ID] bigint NOT NULL ,
[CONTRACT_ID] bigint NOT NULL ,
[NOT_RECOVERED_AMOUNT] decimal(11,2) NOT NULL ,
[REASON] nvarchar(255) NOT NULL ,
[TERMINATION_TIME] datetime NOT NULL ,
[CREATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_termination]', RESEED, 14)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同终止表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同终止表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'APPROVAL_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'NOT_RECOVERED_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'未收回款项'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'NOT_RECOVERED_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'未收回款项'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'NOT_RECOVERED_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'REASON')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'终止原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'REASON'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'终止原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'REASON'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'TERMINATION_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'终止时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'终止时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_termination', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_termination'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO

-- ----------------------------
-- Table structure for g_contract_up
-- ----------------------------
DROP TABLE [dbo].[g_contract_up]
GO
CREATE TABLE [dbo].[g_contract_up] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[CODE] nvarchar(32) NOT NULL ,
[NAME] nvarchar(64) NOT NULL ,
[MERCHANT_ID] bigint NOT NULL ,
[MERCHANT_CONTACT] nvarchar(50) NOT NULL ,
[MERCHANT_CONTACT_PHONE] nvarchar(32) NULL ,
[SIGNING_CONTACT] nvarchar(50) NOT NULL ,
[SIGNING_CONTACT_PHONE] nvarchar(32) NULL ,
[SIGNING_TIME] datetime NULL ,
[DEADLINE_START_TIME] datetime NOT NULL ,
[DEADLINE_END_TIME] datetime NOT NULL ,
[AMOUNT] decimal(18,2) NOT NULL ,
[MARGIN] decimal(18,2) NOT NULL ,
[SIGNING_TYPE] nvarchar(10) NOT NULL ,
[CONTRACT_TYPE] nvarchar(10) NULL ,
[DARFT_STATUS] nvarchar(10) NOT NULL ,
[PAYBACK_TYPE] nvarchar(10) NOT NULL ,
[CONTRACT_STATUS] nvarchar(10) NOT NULL ,
[DEL_STATUS] nvarchar(10) NOT NULL ,
[APPROVAL_TIME] datetime NULL ,
[TERMINATION_TIME] datetime NULL ,
[TERMINATION_REASON] nvarchar(255) NULL ,
[REMARKS] nvarchar(255) NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL ,
[APPROVAL_ID] bigint NOT NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_contract_up]', RESEED, 49)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'CODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'MERCHANT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合作商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合作商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'MERCHANT_CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'MERCHANT_CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'SIGNING_CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'SIGNING_CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'SIGNING_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'DEADLINE_START_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同期限开始时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_START_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同期限开始时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_START_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'DEADLINE_END_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同期限结束时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_END_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同期限结束时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DEADLINE_END_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同总额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同总额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'MARGIN')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'履约保证金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MARGIN'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'履约保证金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'MARGIN'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'SIGNING_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'签约类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'签约类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'SIGNING_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'CONTRACT_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同类别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同类别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'DARFT_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'草稿状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DARFT_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'草稿状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DARFT_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'PAYBACK_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款类型'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'CONTRACT_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'APPROVAL_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'过审时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'过审时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'TERMINATION_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'终止时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'终止时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'TERMINATION_REASON')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'终止原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_REASON'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'终止原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'TERMINATION_REASON'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'REMARKS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_contract_up', 
'COLUMN', N'APPROVAL_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_contract_up'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_ID'
GO

-- ----------------------------
-- Table structure for g_employee
-- ----------------------------
DROP TABLE [dbo].[g_employee]
GO
CREATE TABLE [dbo].[g_employee] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[EMP_NAME] nvarchar(50) NOT NULL ,
[EMP_ACCOUNT_NUMBER] nvarchar(50) NOT NULL ,
[EMP_PASSWORD] nvarchar(128) NOT NULL ,
[EMP_EMAIL] nvarchar(128) NOT NULL ,
[EMP_JOB_TITLE] nvarchar(128) NULL ,
[EMP_SORT_VALUE] int NOT NULL DEFAULT ((5)) ,
[EMP_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[CREATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[UPDATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_employee]', RESEED, 7)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'用户名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'用户名'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_ACCOUNT_NUMBER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'帐号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_ACCOUNT_NUMBER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'帐号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_ACCOUNT_NUMBER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_PASSWORD')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'密码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_PASSWORD'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'密码'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_PASSWORD'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_EMAIL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'邮箱'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_EMAIL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'邮箱'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_EMAIL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_JOB_TITLE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'职称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_JOB_TITLE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'职称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_JOB_TITLE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_SORT_VALUE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'排序值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_SORT_VALUE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'排序值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_SORT_VALUE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'EMP_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态 1有效，0无效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态 1有效，0无效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'EMP_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态(0删除 1未删除)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态(0删除 1未删除)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_employee', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_employee'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO

-- ----------------------------
-- Table structure for g_exception
-- ----------------------------
DROP TABLE [dbo].[g_exception]
GO
CREATE TABLE [dbo].[g_exception] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[EXCEPTION_URL] nvarchar(512) NULL ,
[EXCEPTION_CONTENT] ntext NULL ,
[CREATE_USER] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_exception]', RESEED, 1007)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_exception', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'异常信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'异常信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_exception', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_exception', 
'COLUMN', N'EXCEPTION_URL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'url'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'EXCEPTION_URL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'url'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'EXCEPTION_URL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_exception', 
'COLUMN', N'EXCEPTION_CONTENT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'错误内容'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'EXCEPTION_CONTENT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'错误内容'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'EXCEPTION_CONTENT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_exception', 
'COLUMN', N'CREATE_USER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'CREATE_USER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'CREATE_USER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_exception', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_exception'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO

-- ----------------------------
-- Table structure for g_excitation
-- ----------------------------
DROP TABLE [dbo].[g_excitation]
GO
CREATE TABLE [dbo].[g_excitation] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NULL ,
[TYPE] nvarchar(10) NOT NULL ,
[CONTRACT_TYPE] nvarchar(10) NOT NULL ,
[PROJECT_ID] bigint NOT NULL ,
[PROPERTY_PROFIT] decimal(11,2) NOT NULL ,
[PROFIT_INDEX] nvarchar(15) NULL ,
[TAX_PERCENTAGE] float(53) NOT NULL ,
[TAX] decimal(11,2) NOT NULL ,
[MANAGEMENT_AMOUNT] decimal(11,2) NULL ,
[OTHER_COST] decimal(11,2) NULL ,
[CARDINAL_NUMBER] nvarchar(15) NOT NULL ,
[ROLE_ID] bigint NULL ,
[EXCITATION_PERCENTAGE] float(53) NOT NULL ,
[SHOULD_AMOUNT] decimal(11,2) NULL ,
[ARRIVAL_CONVERSION] decimal(11,2) NULL ,
[OTHER_DEDUCTION] decimal(11,2) NULL ,
[ACTUAL_AMOUNT] decimal(11,2) NOT NULL ,
[MONTH] nvarchar(10) NULL ,
[DEL_STATUS] nvarchar(10) NULL ,
[STATUS] nvarchar(10) NULL ,
[MEMBER_NAME] nvarchar(50) NOT NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_excitation]', RESEED, 326)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'激励信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'激励信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'奖励类型 (1年度 2月度)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'奖励类型 (1年度 2月度)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'CONTRACT_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同类别 (1广告资源 2场地资源 3临时摆展 4服务产 5其他)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同类别 (1广告资源 2场地资源 3临时摆展 4服务产 5其他)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'PROPERTY_PROFIT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'物业利润收入'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'PROPERTY_PROFIT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'物业利润收入'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'PROPERTY_PROFIT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'PROFIT_INDEX')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'年初利润指标'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'PROFIT_INDEX'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'年初利润指标'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'PROFIT_INDEX'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'TAX_PERCENTAGE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税金百分比'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'TAX_PERCENTAGE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税金百分比'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'TAX_PERCENTAGE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'TAX')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'税金 (物业利润收入/税金百分比)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'TAX'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'税金 (物业利润收入/税金百分比)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'TAX'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'MANAGEMENT_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'管理酬金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'MANAGEMENT_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'管理酬金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'MANAGEMENT_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'OTHER_COST')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'其他成本'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'OTHER_COST'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'其他成本'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'OTHER_COST'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'CARDINAL_NUMBER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'激励基数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CARDINAL_NUMBER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'激励基数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CARDINAL_NUMBER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'ROLE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'激励角色ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'ROLE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'激励角色ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'ROLE_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'EXCITATION_PERCENTAGE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'激励百分比 (系统配置)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'EXCITATION_PERCENTAGE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'激励百分比 (系统配置)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'EXCITATION_PERCENTAGE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'SHOULD_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'应激励金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'SHOULD_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'应激励金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'SHOULD_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'ARRIVAL_CONVERSION')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'到岗折算'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'ARRIVAL_CONVERSION'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'到岗折算'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'ARRIVAL_CONVERSION'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'OTHER_DEDUCTION')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'其他扣除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'OTHER_DEDUCTION'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'其他扣除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'OTHER_DEDUCTION'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'ACTUAL_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'实际激励金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'ACTUAL_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'实际激励金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'ACTUAL_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'MONTH')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'激励月度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'MONTH'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'激励月度'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'MONTH'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态 0删除 1未删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态 0删除 1未删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'MEMBER_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'激励人员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'MEMBER_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'激励人员'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'MEMBER_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_excitation', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_excitation'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO

-- ----------------------------
-- Table structure for g_merchant
-- ----------------------------
DROP TABLE [dbo].[g_merchant]
GO
CREATE TABLE [dbo].[g_merchant] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CODE] nvarchar(64) NOT NULL ,
[NAME] nvarchar(64) NOT NULL ,
[LEVEL] nvarchar(10) NOT NULL ,
[CONTACT] nvarchar(50) NOT NULL ,
[CONTACT_PHONE] nvarchar(50) NOT NULL ,
[CONTACT_2] nvarchar(50) NULL ,
[CONTACT_PHONE_2] nvarchar(50) NULL ,
[CITY] nvarchar(64) NOT NULL ,
[CONPANY_ADDRESS] nvarchar(128) NOT NULL ,
[DETAILED_ADDRESS] nvarchar(256) NOT NULL ,
[SUPPLIER_TYPE] nvarchar(50) NOT NULL DEFAULT ((0)) ,
[REMARKS] nvarchar(256) NULL ,
[DEL_STATUS] nvarchar(10) NULL ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CENTRAL_CITY_ID] bigint NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_merchant]', RESEED, 228)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家信息表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'LEVEL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家等级 （1全国 2城市 3项目）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'LEVEL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家等级 （1全国 2城市 3项目）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'LEVEL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CONTACT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'联系人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CONTACT_PHONE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT_PHONE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'联系方式'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT_PHONE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CONTACT_2')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'联系人2'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT_2'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'联系人2'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT_2'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CONTACT_PHONE_2')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'联系方式2'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT_PHONE_2'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'联系方式2'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONTACT_PHONE_2'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CITY')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市 (省 市)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CITY'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市 (省 市)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CITY'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CONPANY_ADDRESS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'公司地址 (省 市 区)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONPANY_ADDRESS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'公司地址 (省 市 区)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CONPANY_ADDRESS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'DETAILED_ADDRESS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'详细地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'DETAILED_ADDRESS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'详细地址'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'DETAILED_ADDRESS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'SUPPLIER_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否合格供应商 (0不合格 1合格)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'SUPPLIER_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否合格供应商 (0不合格 1合格)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'SUPPLIER_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'REMARKS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant', 
'COLUMN', N'CENTRAL_CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
GO

-- ----------------------------
-- Table structure for g_merchant_evaluation
-- ----------------------------
DROP TABLE [dbo].[g_merchant_evaluation]
GO
CREATE TABLE [dbo].[g_merchant_evaluation] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[MERCHANT_ID] bigint NOT NULL ,
[COOPERATION_EVALUATION] nvarchar(256) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_merchant_evaluation]', RESEED, 34)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant_evaluation', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家评估表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant_evaluation'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家评估表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant_evaluation'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant_evaluation', 
'COLUMN', N'MERCHANT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant_evaluation'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant_evaluation'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_merchant_evaluation', 
'COLUMN', N'COOPERATION_EVALUATION')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合作评估'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant_evaluation'
, @level2type = 'COLUMN', @level2name = N'COOPERATION_EVALUATION'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合作评估'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_merchant_evaluation'
, @level2type = 'COLUMN', @level2name = N'COOPERATION_EVALUATION'
GO

-- ----------------------------
-- Table structure for g_message_push
-- ----------------------------
DROP TABLE [dbo].[g_message_push]
GO
CREATE TABLE [dbo].[g_message_push] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[MES_TITLE] nvarchar(128) NOT NULL ,
[MES_CONTENT] ntext NULL ,
[MES_RECEIVE_EMP_ID] bigint NOT NULL ,
[MES_STATUS] nvarchar(10) NOT NULL ,
[CREATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_message_push]', RESEED, 21)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_message_push', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'消息推送表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'消息推送表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_message_push', 
'COLUMN', N'MES_CONTENT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'消息内容'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'MES_CONTENT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'消息内容'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'MES_CONTENT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_message_push', 
'COLUMN', N'MES_RECEIVE_EMP_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'接收人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'MES_RECEIVE_EMP_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'接收人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'MES_RECEIVE_EMP_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_message_push', 
'COLUMN', N'MES_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否已读 (0未读 1已读)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'MES_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否已读 (0未读 1已读)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'MES_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_message_push', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间(年月日时分秒)'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_message_push'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO

-- ----------------------------
-- Table structure for g_payback_approval
-- ----------------------------
DROP TABLE [dbo].[g_payback_approval]
GO
CREATE TABLE [dbo].[g_payback_approval] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[PAYBACK_PLAN_ID] bigint NOT NULL ,
[EXTENSION_DAY_NUM] int NULL ,
[EXTENSION_AMOUNT] decimal(11,2) NOT NULL ,
[EXTENSION_REASON] nvarchar(255) NOT NULL ,
[SUPPER_EMPLOYEE_ID] nvarchar(125) NOT NULL ,
[STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[OPINION] nvarchar(255) NULL ,
[EXTENSION_TIME] datetime NOT NULL ,
[CREATE_TIME] datetime NULL ,
[APPROVAL_TIME] datetime NULL ,
[APPROVE_EMPLOYEE_ID] bigint NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_payback_approval]', RESEED, 8)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款延期审批表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款延期审批表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'PAYBACK_PLAN_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款计划ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_PLAN_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款计划ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_PLAN_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'EXTENSION_DAY_NUM')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延时天数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_DAY_NUM'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延时天数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_DAY_NUM'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'EXTENSION_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延期金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延期金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'EXTENSION_REASON')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延期原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_REASON'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延期原因'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_REASON'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'SUPPER_EMPLOYEE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'上级审批人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'SUPPER_EMPLOYEE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'上级审批人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'SUPPER_EMPLOYEE_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批状态 1未审批 2通过 3未通过'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批状态 1未审批 2通过 3未通过'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'OPINION')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批意见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'OPINION'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批意见'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'OPINION'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'EXTENSION_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延期回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延期回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'APPROVAL_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVAL_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_approval', 
'COLUMN', N'APPROVE_EMPLOYEE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'审批操作人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVE_EMPLOYEE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'审批操作人ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_approval'
, @level2type = 'COLUMN', @level2name = N'APPROVE_EMPLOYEE_ID'
GO

-- ----------------------------
-- Table structure for g_payback_plan
-- ----------------------------
DROP TABLE [dbo].[g_payback_plan]
GO
CREATE TABLE [dbo].[g_payback_plan] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[CENTRAL_CITY_ID] bigint NOT NULL ,
[CITY_ID] bigint NOT NULL ,
[NAME] nvarchar(64) NOT NULL ,
[MERCHANT_ID] bigint NOT NULL ,
[CONTRACT_PROJECT_ID] bigint NOT NULL ,
[RECEIVABLE_PAYBACK] decimal(11,2) NULL ,
[PAYBACK_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[EXTENSION_STATUS] nvarchar(10) NOT NULL DEFAULT ((0)) ,
[BEFORE_REMIND] nvarchar(10) NOT NULL DEFAULT ((0)) ,
[EXTENSION_REMIND] nvarchar(10) NOT NULL DEFAULT ((0)) ,
[STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[EXTENSION_DAY_NUM] int NULL ,
[EXTENSION_AMOUNT] decimal(11,2) NULL ,
[EXTENSION_TIME] datetime NULL ,
[PAYBACK_TIME] datetime NOT NULL ,
[REMITTANCE_TIME] datetime NULL ,
[REMARKS] nvarchar(255) NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL ,
[EDIT_STATUS] nvarchar(10) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_payback_plan]', RESEED, 221)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款计划表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款计划表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'CENTRAL_CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'MERCHANT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'商家ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'MERCHANT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'RECEIVABLE_PAYBACK')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'应收回款'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'RECEIVABLE_PAYBACK'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'应收回款'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'RECEIVABLE_PAYBACK'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'PAYBACK_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款状态 1未回款 2部分回款 3已回款'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款状态 1未回款 2部分回款 3已回款'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'EXTENSION_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延期状态 0不延期 1延期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延期状态 0不延期 1延期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'BEFORE_REMIND')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款提前提醒 0未提醒 1已提醒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'BEFORE_REMIND'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款提前提醒 0未提醒 1已提醒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'BEFORE_REMIND'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'EXTENSION_REMIND')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款延时提醒 0未提醒 1已提醒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_REMIND'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款延时提醒 0未提醒 1已提醒'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_REMIND'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款计划状态 0失效 1有效 （PS：合同项目被删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款计划状态 0失效 1有效 （PS：合同项目被删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除标志 0未删除 1已删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除标志 0未删除 1已删除'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'EXTENSION_DAY_NUM')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款延时天数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_DAY_NUM'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款延时天数'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_DAY_NUM'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'EXTENSION_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延期金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延期金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'EXTENSION_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'延期回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'延期回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EXTENSION_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'PAYBACK_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'计划回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'计划回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'REMITTANCE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'汇款日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'REMITTANCE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'汇款日期'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'REMITTANCE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'REMARKS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'备注'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'REMARKS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_plan', 
'COLUMN', N'EDIT_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'是否可编辑 0可编辑 1不可编辑'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EDIT_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'是否可编辑 0可编辑 1不可编辑'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_plan'
, @level2type = 'COLUMN', @level2name = N'EDIT_STATUS'
GO

-- ----------------------------
-- Table structure for g_payback_record
-- ----------------------------
DROP TABLE [dbo].[g_payback_record]
GO
CREATE TABLE [dbo].[g_payback_record] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CONTRACT_ID] bigint NOT NULL ,
[PAYBACK_PLAN_ID] bigint NOT NULL ,
[CONTRACT_PROJECT_ID] bigint NOT NULL ,
[PAYBACK_AMOUNT] decimal(11,2) NOT NULL ,
[PAYBACK_INVOICE_CODE] nvarchar(64) NOT NULL ,
[LAST_PAYMENT] decimal(11,2) NULL ,
[RECEIPT_MEMBER] nvarchar(50) NOT NULL ,
[PAYBACK_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_NAME] nvarchar(50) NULL ,
[CREATE_TIME] datetime NULL ,
[UPDATE_TIME] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_payback_record]', RESEED, 44)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款记录表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款记录表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'CONTRACT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'PAYBACK_PLAN_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款计划ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_PLAN_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款计划ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_PLAN_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'CONTRACT_PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'合同项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CONTRACT_PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'PAYBACK_AMOUNT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_AMOUNT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款金额'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_AMOUNT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'PAYBACK_INVOICE_CODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款发票号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_INVOICE_CODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款发票号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_INVOICE_CODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'LAST_PAYMENT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'滞纳金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'LAST_PAYMENT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'滞纳金'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'LAST_PAYMENT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'RECEIPT_MEMBER')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'收款人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'RECEIPT_MEMBER'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'收款人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'RECEIPT_MEMBER'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'PAYBACK_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'回款时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'PAYBACK_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_payback_record', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'修改时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_payback_record'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO

-- ----------------------------
-- Table structure for g_resource
-- ----------------------------
DROP TABLE [dbo].[g_resource]
GO
CREATE TABLE [dbo].[g_resource] (
[ID] bigint NOT NULL ,
[RES_NAME] nvarchar(50) NOT NULL ,
[RES_URL] nvarchar(256) NOT NULL ,
[RES_TYPE] nvarchar(10) NOT NULL ,
[RES_PARTEN_ID] bigint NOT NULL ,
[RES_ICON] nvarchar(64) NOT NULL ,
[RES_DESCRIPTION] nvarchar(256) NULL ,
[RES_SEQ] int NOT NULL DEFAULT ((5)) ,
[RES_STATUS] nvarchar(10) NOT NULL ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(50) NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_URL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'url'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_URL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'url'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_URL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_TYPE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源类型 1模块 2页面 3按钮	'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_TYPE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源类型 1模块 2页面 3按钮	'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_TYPE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_PARTEN_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'父节点'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_PARTEN_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'父节点'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_PARTEN_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_ICON')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'图标'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_ICON'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'图标'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_ICON'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_DESCRIPTION')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_DESCRIPTION'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_DESCRIPTION'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_SEQ')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'排序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_SEQ'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'排序'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_SEQ'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'RES_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'状态 0无效 1有效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'状态 0无效 1有效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'RES_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO

-- ----------------------------
-- Table structure for g_resource_point
-- ----------------------------
DROP TABLE [dbo].[g_resource_point]
GO
CREATE TABLE [dbo].[g_resource_point] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[NAME] nvarchar(100) NOT NULL ,
[PARENT_ID] bigint NOT NULL ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[LEVEL] int NOT NULL ,
[PROJECT_ID] bigint NULL ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(20) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(20) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_resource_point]', RESEED, 154)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源点位表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源点位表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'点位名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'点位名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'PARENT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'父节点ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'PARENT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'父节点ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'PARENT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态（0 删除 1未删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态（0 删除 1未删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'LEVEL')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'LEVEL'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'级别'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'LEVEL'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_point', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_point'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO

-- ----------------------------
-- Table structure for g_resource_project
-- ----------------------------
DROP TABLE [dbo].[g_resource_project]
GO
CREATE TABLE [dbo].[g_resource_project] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[CENTRAL_CITY_ID] bigint NOT NULL ,
[CITY_ID] bigint NOT NULL ,
[PROJECT_NAME] nvarchar(100) NOT NULL ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(20) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(20) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_resource_project]', RESEED, 44)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源项目表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源项目表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID（自增ID）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID（自增ID）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'CENTRAL_CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'中心城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CENTRAL_CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'CITY_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'城市ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CITY_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'PROJECT_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'PROJECT_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'PROJECT_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态（0 删除 1未删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态（0 删除 1未删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO

-- ----------------------------
-- Table structure for g_resource_project_detail
-- ----------------------------
DROP TABLE [dbo].[g_resource_project_detail]
GO
CREATE TABLE [dbo].[g_resource_project_detail] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[PROJECT_ID] bigint NOT NULL ,
[POINT_ID] bigint NOT NULL ,
[ASSETS_CODE] nvarchar(100) NOT NULL ,
[ASSETS_NAME] nvarchar(100) NOT NULL ,
[ASSETS_UNIT] nvarchar(100) NOT NULL ,
[DEL_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(20) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(20) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_resource_project_detail]', RESEED, 108)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源项目明细表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源项目明细表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键ID（自增ID）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键ID（自增ID）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'PROJECT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'项目ID'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'PROJECT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'POINT_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'点位资源ID（三级点位ID）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'点位资源ID（三级点位ID）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'POINT_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'ASSETS_CODE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ASSETS_CODE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源编号'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ASSETS_CODE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'ASSETS_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ASSETS_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ASSETS_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'ASSETS_UNIT')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源规格/平方'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ASSETS_UNIT'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源规格/平方'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'ASSETS_UNIT'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'DEL_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'删除状态（0 删除 1未删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'删除状态（0 删除 1未删除）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'DEL_STATUS'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'CREATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'CREATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'CREATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'创建人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'CREATE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'UPDATE_TIME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新时间（年月日时分秒）'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'UPDATE_TIME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_project_detail', 
'COLUMN', N'UPDATE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'更新人'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_project_detail'
, @level2type = 'COLUMN', @level2name = N'UPDATE_NAME'
GO

-- ----------------------------
-- Table structure for g_resource_relationship
-- ----------------------------
DROP TABLE [dbo].[g_resource_relationship]
GO
CREATE TABLE [dbo].[g_resource_relationship] (
[ROLE_ID] bigint NOT NULL ,
[RESOURCE_ID] bigint NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_relationship', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色与权限中间关联表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_relationship'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色与权限中间关联表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_relationship'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_relationship', 
'COLUMN', N'ROLE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_relationship'
, @level2type = 'COLUMN', @level2name = N'ROLE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_relationship'
, @level2type = 'COLUMN', @level2name = N'ROLE_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_resource_relationship', 
'COLUMN', N'RESOURCE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'资源id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_relationship'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'资源id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_resource_relationship'
, @level2type = 'COLUMN', @level2name = N'RESOURCE_ID'
GO

-- ----------------------------
-- Table structure for g_role
-- ----------------------------
DROP TABLE [dbo].[g_role]
GO
CREATE TABLE [dbo].[g_role] (
[ID] bigint NOT NULL IDENTITY(1,1) ,
[ROLE_NAME] nvarchar(50) NOT NULL ,
[ROLE_DES] nvarchar(128) NULL ,
[ROLE_SORT_VALUE] int NOT NULL DEFAULT ((5)) ,
[ROLE_STATUS] nvarchar(10) NOT NULL DEFAULT ((1)) ,
[CREATE_TIME] datetime NULL ,
[CREATE_NAME] nvarchar(50) NULL ,
[UPDATE_TIME] datetime NULL ,
[UPDATE_NAME] nvarchar(50) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[g_role]', RESEED, 11)
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role', 
'COLUMN', N'ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'主键'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role', 
'COLUMN', N'ROLE_NAME')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_NAME'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色名称'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_NAME'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role', 
'COLUMN', N'ROLE_DES')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_DES'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色描述'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_DES'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role', 
'COLUMN', N'ROLE_SORT_VALUE')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'排序值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_SORT_VALUE'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'排序值'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_SORT_VALUE'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role', 
'COLUMN', N'ROLE_STATUS')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色状态 0无效 1有效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_STATUS'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色状态 0无效 1有效'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role'
, @level2type = 'COLUMN', @level2name = N'ROLE_STATUS'
GO

-- ----------------------------
-- Table structure for g_role_relationship
-- ----------------------------
DROP TABLE [dbo].[g_role_relationship]
GO
CREATE TABLE [dbo].[g_role_relationship] (
[EMP_ID] bigint NOT NULL ,
[ROLE_ID] bigint NOT NULL 
)


GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role_relationship', 
NULL, NULL)) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'人员角色关系表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role_relationship'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'人员角色关系表'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role_relationship'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role_relationship', 
'COLUMN', N'EMP_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'人员id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role_relationship'
, @level2type = 'COLUMN', @level2name = N'EMP_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'人员id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role_relationship'
, @level2type = 'COLUMN', @level2name = N'EMP_ID'
GO
IF ((SELECT COUNT(*) from fn_listextendedproperty('MS_Description', 
'SCHEMA', N'dbo', 
'TABLE', N'g_role_relationship', 
'COLUMN', N'ROLE_ID')) > 0) 
EXEC sp_updateextendedproperty @name = N'MS_Description', @value = N'角色id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role_relationship'
, @level2type = 'COLUMN', @level2name = N'ROLE_ID'
ELSE
EXEC sp_addextendedproperty @name = N'MS_Description', @value = N'角色id'
, @level0type = 'SCHEMA', @level0name = N'dbo'
, @level1type = 'TABLE', @level1name = N'g_role_relationship'
, @level2type = 'COLUMN', @level2name = N'ROLE_ID'
GO

-- ----------------------------
-- Indexes structure for table g_area
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_area
-- ----------------------------
ALTER TABLE [dbo].[g_area] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_central_city
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_central_city
-- ----------------------------
ALTER TABLE [dbo].[g_central_city] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_city
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_city
-- ----------------------------
ALTER TABLE [dbo].[g_city] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_city_entry
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_city_entry
-- ----------------------------
ALTER TABLE [dbo].[g_city_entry] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_city_year_target
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_city_year_target
-- ----------------------------
ALTER TABLE [dbo].[g_city_year_target] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_configure
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_configure
-- ----------------------------
ALTER TABLE [dbo].[g_configure] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract
-- ----------------------------
ALTER TABLE [dbo].[g_contract] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_approval
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_approval
-- ----------------------------
ALTER TABLE [dbo].[g_contract_approval] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_old
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_old
-- ----------------------------
ALTER TABLE [dbo].[g_contract_old] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_project
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_project
-- ----------------------------
ALTER TABLE [dbo].[g_contract_project] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_project_old
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_project_old
-- ----------------------------
ALTER TABLE [dbo].[g_contract_project_old] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_project_up
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_project_up
-- ----------------------------
ALTER TABLE [dbo].[g_contract_project_up] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_resource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_resource
-- ----------------------------
ALTER TABLE [dbo].[g_contract_resource] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_resource_old
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_resource_old
-- ----------------------------
ALTER TABLE [dbo].[g_contract_resource_old] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_resource_up
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_resource_up
-- ----------------------------
ALTER TABLE [dbo].[g_contract_resource_up] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_termination
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_termination
-- ----------------------------
ALTER TABLE [dbo].[g_contract_termination] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_contract_up
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_contract_up
-- ----------------------------
ALTER TABLE [dbo].[g_contract_up] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_employee
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_employee
-- ----------------------------
ALTER TABLE [dbo].[g_employee] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_exception
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_exception
-- ----------------------------
ALTER TABLE [dbo].[g_exception] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_excitation
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_excitation
-- ----------------------------
ALTER TABLE [dbo].[g_excitation] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_merchant
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_merchant
-- ----------------------------
ALTER TABLE [dbo].[g_merchant] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_merchant_evaluation
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_merchant_evaluation
-- ----------------------------
ALTER TABLE [dbo].[g_merchant_evaluation] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_message_push
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_message_push
-- ----------------------------
ALTER TABLE [dbo].[g_message_push] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_payback_approval
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_payback_approval
-- ----------------------------
ALTER TABLE [dbo].[g_payback_approval] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_payback_plan
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_payback_plan
-- ----------------------------
ALTER TABLE [dbo].[g_payback_plan] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_payback_record
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_payback_record
-- ----------------------------
ALTER TABLE [dbo].[g_payback_record] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_resource
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_resource
-- ----------------------------
ALTER TABLE [dbo].[g_resource] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_resource_point
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_resource_point
-- ----------------------------
ALTER TABLE [dbo].[g_resource_point] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_resource_project
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_resource_project
-- ----------------------------
ALTER TABLE [dbo].[g_resource_project] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_resource_project_detail
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_resource_project_detail
-- ----------------------------
ALTER TABLE [dbo].[g_resource_project_detail] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_resource_relationship
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_resource_relationship
-- ----------------------------
ALTER TABLE [dbo].[g_resource_relationship] ADD PRIMARY KEY ([ROLE_ID], [RESOURCE_ID])
GO

-- ----------------------------
-- Indexes structure for table g_role
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_role
-- ----------------------------
ALTER TABLE [dbo].[g_role] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table g_role_relationship
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table g_role_relationship
-- ----------------------------
ALTER TABLE [dbo].[g_role_relationship] ADD PRIMARY KEY ([EMP_ID], [ROLE_ID])
GO
