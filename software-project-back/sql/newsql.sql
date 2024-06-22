
use shared_umbrella;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
-- 创建用户表 (Users)
CREATE TABLE Users (
                       UserID INT PRIMARY KEY AUTO_INCREMENT, -- 用户ID，主键，自增
                       Username VARCHAR(50) NOT NULL, -- 用户名，不能为空
                       Password VARCHAR(100) NOT NULL, -- 密码，不能为空
                       Name VARCHAR(100) NOT NULL, -- 姓名，不能为空
                       Phone VARCHAR(20),-- 电话号码
                       Role     tinyint default 0 not null,-- 角色，默认0，0为普通用户，1为管理员
                       isDelete   tinyint default 0 not null,-- 是否删除，默认0，0为未删除，1为已删除
                       CreateTime DATETIME NOT NULL, -- 创建时间
                       UpdateTime DATETIME NOT NULL -- 更新时间
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建雨伞表 (Umbrellas)
CREATE TABLE Umbrellas (
                           UmbrellaID INT PRIMARY KEY AUTO_INCREMENT, -- 雨伞ID，主键，自增
                           Name VARCHAR(100) NOT NULL, -- 雨伞名称，不能为空
                           Type VARCHAR(50), -- 雨伞类型
                           Status ENUM('可租借', '已借出', '需要维修') NOT NULL,-- 雨伞状态，枚举类型
                           CreateTime DATETIME NOT NULL, -- 创建时间
                           UpdateTime DATETIME NOT NULL -- 更新时间
) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建租借记录表 (Rental Records)
CREATE TABLE RentalRecords (
                               RecordID INT PRIMARY KEY AUTO_INCREMENT, -- 记录ID，主键，自增
                               UserID INT NOT NULL, -- 用户ID，外键关联到Users表的UserID
                               UmbrellaID INT NOT NULL, -- 雨伞ID，外键关联到Umbrellas表的UmbrellaID
                               RentalTime DATETIME NOT NULL, -- 租借时间
                               ReturnTime DATETIME, -- 归还时间
                               Status ENUM('租借中', '已归还', '逾期') NOT NULL-- 租借状态，枚举类型
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 创建逾期记录表 (Late Return Records)
CREATE TABLE LateReturnRecords (
                                   RecordID INT PRIMARY KEY AUTO_INCREMENT, -- 记录ID，主键，自增
                                   UserID INT NOT NULL, -- 用户ID，外键关联到Users表的UserID
                                   UmbrellaID INT NOT NULL, -- 雨伞ID，外键关联到Umbrellas表的UmbrellaID
                                   LateTime DATETIME NOT NULL, -- 逾期时间
                                   FineAmount DECIMAL(10, 2) NOT NULL, -- 罚款金额
                                   Status ENUM('未处理', '已处理') NOT NULL -- 处理状态，枚举类型
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;


-- 创建支付记录表 (Payment Records)
CREATE TABLE PaymentRecords (
                                RecordID INT PRIMARY KEY AUTO_INCREMENT, -- 记录ID，主键，自增
                                UserID INT NOT NULL, -- 用户ID，外键关联到Users表的UserID
                                PaymentTime DATETIME NOT NULL, -- 支付时间
                                Amount DECIMAL(10, 2) NOT NULL, -- 支付金额
                                PaymentMethod VARCHAR(50) -- 支付方式
)CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

