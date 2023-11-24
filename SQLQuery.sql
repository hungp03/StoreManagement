CREATE TABLE Admin ( 
  Admin_ID INT PRIMARY KEY, 
  Username VARCHAR(20) NOT NULL UNIQUE, 
  Password VARCHAR(20) NOT NULL 
);

CREATE TABLE Employee (
  Employee_ID INT PRIMARY KEY, 
  Username VARCHAR(20) NOT NULL UNIQUE,
  Password VARCHAR(20) NOT NULL,
  Full_Name NVARCHAR(50) NOT NULL, 
  Date_of_Birth DATE NOT NULL, 
  Gender NVARCHAR(10) CHECK (Gender IN ('Nam', 'Nữ', 'Khác')), 
  Role NVARCHAR(20) NOT NULL CHECK (Role IN ('Bán hàng', 'Kho')), 
  Salary DECIMAL(10,2) NOT NULL CHECK (Salary >= 0)  
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
  FOREIGN KEY (Category_ID) REFERENCES Category (Category_ID)
);

CREATE TABLE Customer ( 
  Customer_ID INT PRIMARY KEY, 
  Full_Name NVARCHAR(50) NOT NULL, 
  Address NVARCHAR(100) NOT NULL, 
  Phone VARCHAR(15) NOT NULL, 
  Email VARCHAR(50) UNIQUE
);

CREATE TABLE Invoice ( 
  Invoice_ID INT PRIMARY KEY, 
  Date DATE NOT NULL, 
  Total_Amount DECIMAL(10,2) NOT NULL CHECK (Total_Amount >= 0),
  Payment_Method NVARCHAR(20) CHECK (Payment_Method IN ('Tiền mặt', 'Thẻ', 'Chuyển khoản')),
  Employee_ID INT,
  Customer_ID INT,
  FOREIGN KEY (Employee_ID) REFERENCES Employee (Employee_ID),
  FOREIGN KEY (Customer_ID) REFERENCES Customer (Customer_ID)
);

CREATE TABLE Contain (
  Invoice_ID INT, 
  Product_ID INT, 
  Quantity INT NOT NULL CHECK (Quantity > 0), 
  PRIMARY KEY (Invoice_ID, Product_ID), 
  FOREIGN KEY (Invoice_ID) REFERENCES Invoice (Invoice_ID), 
  FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID) 
);

CREATE TABLE Refund (
  Refund_ID INT PRIMARY KEY,
  Invoice_ID INT,
  Product_ID INT,
  Refund_Quantity INT NOT NULL CHECK (Refund_Quantity > 0),
  Refund_Date DATE NOT NULL,
  FOREIGN KEY (Invoice_ID) REFERENCES Invoice (Invoice_ID),
  FOREIGN KEY (Product_ID) REFERENCES Product (Product_ID)
);