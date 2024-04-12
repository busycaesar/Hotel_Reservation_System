DROP TABLE IF EXISTS RoomsReserved;
DROP TABLE IF EXISTS Receipts;
DROP TABLE IF EXISTS Reservations;
DROP TABLE IF EXISTS Customers;
DROP TABLE IF EXISTS AdminCredentials;
DROP TABLE IF EXISTS DiscountOptions;
DROP TABLE IF EXISTS RoomsDescription;
DROP TABLE IF EXISTS Rooms;

CREATE TABLE IF NOT EXISTS RoomDescription (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	roomType STRING NOT NULL,
	totalRooms INTEGER NOT NULL,
	maxGuestsAllowed INTEGER NOT NULL,
	costPerDay DOUBLE NOT NULL);

CREATE TABLE IF NOT EXISTS Rooms (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	roomType STRING NOT NULL,
    booked INTEGER NOT NULL);

CREATE TABLE IF NOT EXISTS AdminCredentials (
    id INTEGER PRIMARY KEY,
	password TEXT NOT NULL);

CREATE TABLE IF NOT EXISTS DiscountOptions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
	discountOption DOUBLE NOT NULL);

INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('SINGLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DOUBLE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('DELUX', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('PENTHOUSE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('PENTHOUSE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('PENTHOUSE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('PENTHOUSE', 0);
INSERT INTO ROOMS (roomType, booked) VALUES ('PENTHOUSE', 0);

INSERT INTO ROOMDESCRIPTION (roomType, totalRooms, maxGuestsAllowed, costPerDay) 
                     VALUES ('SINGLE', 10, 1, 30);
INSERT INTO ROOMDESCRIPTION (roomType, totalRooms, maxGuestsAllowed, costPerDay) 
                     VALUES ('DOUBLE', 8, 2, 50);
INSERT INTO ROOMDESCRIPTION (roomType, totalRooms, maxGuestsAllowed, costPerDay) 
                     VALUES ('DELUX', 7, 4, 120);
INSERT INTO ROOMDESCRIPTION (roomType, totalRooms, maxGuestsAllowed, costPerDay) 
                     VALUES ('PENTHOUSE', 5, 6, 250);

INSERT INTO DISCOUNTOPTIONS (discountOption) VALUES (0.0);
INSERT INTO DISCOUNTOPTIONS (discountOption) VALUES (5.0);
INSERT INTO DISCOUNTOPTIONS (discountOption) VALUES (7.0);
INSERT INTO DISCOUNTOPTIONS (discountOption) VALUES (13.0);
INSERT INTO DISCOUNTOPTIONS (discountOption) VALUES (15.0);

INSERT INTO ADMINCREDENTIALS (id, password) VALUES (11, '1234');
INSERT INTO ADMINCREDENTIALS (id, password) VALUES (12, '1234');