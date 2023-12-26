create table DiscardedGood
(
ID int not null identity(0,1) primary key,
Product_Name nvarchar(100) not null,
Unit_Price decimal(10, 2) not null,
Quantity_When_Discarded int not null,
Manufactory_Date date not null,
Expiry_Date date not null,
Entry_Date date not null,
Discarded_Date date not null
)
