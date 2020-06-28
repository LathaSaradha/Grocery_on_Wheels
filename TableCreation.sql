/*
Creation of database

*/

create database grocery_on_wheels;
use grocery_on_wheels;


/*Table Creation in Database

Users Table
*/	


CREATE TABLE Users(
UserID Integer NOT NULL, 
UserName Varchar(50), 
AptNo Varchar (10), 
StreetName Varchar(50),
City Varchar(25),
State Varchar(2), 
ZipCode Integer,
DateOfBirth Date DEFAULT 20000101,
PhoneNo Varchar(25),
EmailID Varchar(50) NOT NULL,
PRIMARY KEY (UserID)
);

/*

PaymentCardDetails Table
*/	


CREATE TABLE PaymentCardDetails(
UserID Integer  NOT NULL,
PaymentSourceID Integer  NOT NULL, 
           	 	BankName Varchar(50), 
CardNum Varchar(25)  NOT NULL, 
AptNum Varchar (10), 
Street_Name Varchar(50),
City Varchar(50),
State Varchar(2), 
ZipCode Integer,
PRIMARY KEY (PaymentSourceID,UserID),
FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE CASCADE);


/*
OrderDetails Table

*/

CREATE TABLE OrderDetails(
UserID Integer  NOT NULL,
OrderID Integer  NOT NULL, 
TotalAmount Real  NOT NULL, 
OrderDate Date  NOT NULL,  
OrderTime TIMESTAMP  NOT NULL, 
PRIMARY KEY (OrderID),
FOREIGN KEY (UserID) REFERENCES Users (UserID) 
);


/*
INVENTORY TABLE 

*/
CREATE TABLE inventory(
              item_id Int NOT NULL PRIMARY KEY, 
                 price Real NOT NULL,
                 date_of_expiry date NOT NULL,
                 	company_name varchar(30) NOT NULL,
                 	item_qty Int ,
                 	item_name varchar(30) NOT NULL,	
                 	discount Int				 
                	);

/*
UserCart Table

*/
CREATE TABLE UserCart (
UserID Integer  NOT NULL, 
CartID Integer  NOT NULL,  
Item_id Integer  NOT NULL,
ItemQtyforCart Integer, 
Price Real  NOT NULL,
PRIMARY KEY (UserID,CartID,Item_id),
FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE CASCADE,
FOREIGN KEY (item_id) REFERENCES Inventory(item_id)
);


/*
VANDETAILS Table

*/
CREATE TABLE van( van_id Int NOT NULL PRIMARY KEY,
                 driver_name varchar(30) NOT NULL,
                 model_name varchar(30) NOT NULL,
                 vehicle_Identification varchar(10) NOT NULL  
                );


/* VANSCHEDULE TABLE 	

*/		

CREATE TABLE vanschedule(
van_id Int NOT NULL,
address_id Int NOT NULL,
schedule_id Int NOT NULL,
 	street_name varchar(30) NOT NULL ,
 	city varchar(30),
 		state varchar(20),
 zipcode Int NOT NULL,
 date_of_delivery date,
 gps_location varchar(50) NOT NULL,
departure_time varchar(30) NOT NULL,
arrival_time varchar(30) NOT NULL, 
PRIMARY KEY (address_id,schedule_id),
FOREIGN KEY (van_id) REFERENCES van (van_id) ON DELETE CASCADE	 
  	);


/*
CARRY	TABLE	
*/
CREATE TABLE carry(
                van_id Int NOT NULL,
                OrderID Integer NOT NULL,
	  item_id Int NOT NULL,
	 PRIMARY KEY (van_id,OrderID,item_id),
               FOREIGN KEY (van_id)  REFERENCES van(van_id) ON DELETE CASCADE,
FOREIGN KEY (OrderID) REFERENCES OrderDetails(OrderID) ON DELETE CASCADE,
FOREIGN KEY (item_id) REFERENCES inventory(item_id) ON DELETE CASCADE
		  );




