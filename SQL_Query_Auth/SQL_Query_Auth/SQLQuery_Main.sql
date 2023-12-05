CREATE TABLE Admin ( 
  Admin_ID INT PRIMARY KEY, 
  Username VARCHAR(20) NOT NULL UNIQUE, 
  Password VARCHAR(100) NOT NULL 
);

CREATE TABLE Employee (
  Employee_ID INT PRIMARY KEY, 
  Username VARCHAR(20) NOT NULL UNIQUE,
  Password VARCHAR(100) NOT NULL,
  Full_Name NVARCHAR(50) NOT NULL, 
  Date_of_Birth DATE NOT NULL, 
  Gender NVARCHAR(10) CHECK (Gender IN ('Nam', 'Nu', 'Khác')), 
  Role NVARCHAR(20) NOT NULL CHECK (Role IN ('Bán hàng', 'Kho')), 
  Salary INT NOT NULL CHECK (Salary >= 0)  
);

CREATE TABLE Category (
  Category_ID INT PRIMARY KEY,
  Category_Name NVARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Product (
  Product_ID INT PRIMARY KEY, 
  Product_Name NVARCHAR(100) NOT NULL, 
  Category_ID INT,
  Unit_Price DECIMAL(10,2) NOT NULL CHECK (Unit_Price >= 0),
  Quantity_In_Stock INT NOT NULL CHECK (Quantity_In_Stock >= 0), 
  Description NVARCHAR(200),
  Manufacture_Date DATE NOT NULL,
  Expiry_Date DATE NOT NULL,
  Entry_Date DATE NOT NULL,
  Created_At datetime NOT NULL,
  Updated_At datetime,
  FOREIGN KEY (Category_ID) REFERENCES Category (Category_ID)
);
GO

ALTER TABLE Product
ADD CONSTRAINT DF_Created_At 
DEFAULT GETDATE() FOR Created_At
GO

CREATE TRIGGER Update_Product ON Product
AFTER UPDATE
AS
BEGIN
UPDATE Product
SET Updated_At = GETDATE()
FROM Product
INNER JOIN inserted
ON Product.Product_ID = inserted.Product_ID;
END;
GO

CREATE TABLE Customer ( 
  Customer_ID INT PRIMARY KEY, 
  Full_Name NVARCHAR(50) NOT NULL, 
  Address NVARCHAR(100) NOT NULL, 
  Phone VARCHAR(15) NOT NULL UNIQUE, 
  Email VARCHAR(50)
);

CREATE TABLE Invoice ( 
  Invoice_ID INT PRIMARY KEY, 
  Date DATE NOT NULL, 
  Total_Amount DECIMAL(10,2) NOT NULL CHECK (Total_Amount >= 0),
  Customer_Cash DECIMAL(10,2),
  Return_Money decimal(10,2) default 0,
  Payment_Method NVARCHAR(20) CHECK (Payment_Method IN ('Tien mat', 'The', 'Chuyen khoan')),
  Created_At datetime NOT NULL,
  Employee_ID INT NULL,
  Customer_ID INT,
  FOREIGN KEY (Employee_ID) REFERENCES Employee (Employee_ID) ON DELETE SET NULL,
  FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
);
GO

ALTER TABLE Invoice
ADD CONSTRAINT Inv_Created_At 
DEFAULT GETDATE() FOR Created_At
GO

CREATE TABLE Contain (
  Invoice_ID INT, 
  Product_ID INT, 
  Quantity INT NOT NULL CHECK (Quantity > 0), 
  PRIMARY KEY (Invoice_ID, Product_ID), 
  FOREIGN KEY (Invoice_ID) REFERENCES Invoice (Invoice_ID), 
  FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID) 
);


--Tạo Procedure để tính doanh thu 7 ngày gần nhất:
CREATE PROCEDURE GetRevenueForLast7Days
AS
BEGIN
    -- Tạo bảng tạm thời chứa tất cả các ngày trong 7 ngày gần nhất
    DECLARE @DateTable TABLE (Date DATE);
    DECLARE @StartDate DATE = DATEADD(day, -7, GETDATE());
    DECLARE @EndDate DATE = GETDATE();
    WHILE @StartDate <= @EndDate
    BEGIN
        INSERT INTO @DateTable VALUES (@StartDate);
        SET @StartDate = DATEADD(day, 1, @StartDate);
    END

    -- Truy vấn doanh thu cho mỗi ngày, set doanh thu là 0 cho những ngày không có dữ liệu
    SELECT d.Date, ISNULL(SUM(i.Total_Amount), 0) as Revenue
    FROM @DateTable d
    LEFT JOIN Invoice i ON d.Date = i.Date
    GROUP BY d.Date
    ORDER BY d.Date;
END

--Tạo Procedure để tính doanh thu 4 tuần gần nhất:
CREATE PROCEDURE GetRevenueForLast4Cycles
AS
BEGIN
    -- Tạo bảng tạm thời chứa tất cả các chu kỳ 7 ngày trong 4 chu kỳ gần nhất
    DECLARE @CycleTable TABLE (CycleStart DATE, CycleEnd DATE);
    DECLARE @StartDate DATE = DATEADD(day, -28, GETDATE());
    DECLARE @EndDate DATE = GETDATE();
    WHILE @StartDate < @EndDate
    BEGIN
        INSERT INTO @CycleTable VALUES (@StartDate, DATEADD(day, 7, @StartDate));
        SET @StartDate = DATEADD(day, 7, @StartDate);
    END

    -- Truy vấn doanh thu cho mỗi chu kỳ, set doanh thu là 0 cho những chu kỳ không có dữ liệu
    SELECT c.CycleStart, ISNULL(SUM(i.Total_Amount), 0) as Total_Revenue
    FROM @CycleTable c
    LEFT JOIN Invoice i ON i.Date >= c.CycleStart AND i.Date < c.CycleEnd
    GROUP BY c.CycleStart
    ORDER BY c.CycleStart;
END