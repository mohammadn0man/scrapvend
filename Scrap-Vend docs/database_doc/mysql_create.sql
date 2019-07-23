CREATE TABLE `User_details` (
	`Name` varchar(50) NOT NULL,
	`Address` varchar(100) DEFAULT 'NULL',
	`Contact_no` (15) NOT NULL UNIQUE,
	`email_id` varchar(50) NOT NULL UNIQUE,
	`User_id` varchar(10) NOT NULL,
	`Password` varchar(50) NOT NULL,
	PRIMARY KEY (`User_id`)
);

CREATE TABLE `Booking_details` (
	`User_id` varchar(10) NOT NULL UNIQUE,
	`Pickup_person_id` varchar(10) NOT NULL,
	`Booking_date` DATE(10) NOT NULL,
	`Pickup_date` DATE(10) NOT NULL,
	`Booking_id` varchar(10) NOT NULL UNIQUE,
	`Pickup_status` varchar(20) NOT NULL,
	PRIMARY KEY (`Booking_id`)
);

CREATE TABLE `Payment_details` (
	`Payment_id` varchar(20) NOT NULL,
	`Payment_amount` FLOAT(20) NOT NULL,
	`Reware_id` varchar(10) DEFAULT 'NULL',
	`User_id` varchar(10) NOT NULL,
	`Reware_amount` FLOAT(20) DEFAULT 'NULL',
	PRIMARY KEY (`Payment_id`)
);

CREATE TABLE `Item_details` (
	`Item_id` varchar(10) NOT NULL,
	`Item_name` varchar(30) NOT NULL,
	`Item_rate` FLOAT(20) NOT NULL,
	`Item_measure` varchar(20) NOT NULL,
	PRIMARY KEY (`Item_id`)
);

CREATE TABLE `Booked_item_list` (
	`Pickup_id` varchar(10) NOT NULL,
	`Item_id` varchar(10) NOT NULL
);

CREATE TABLE `Company_details` (
	`Company_id` varchar(10) NOT NULL,
	`Company_name` varchar(50) NOT NULL,
	`Address` varchar(50) NOT NULL,
	`Contact_no` varchar(50) NOT NULL UNIQUE,
	PRIMARY KEY (`Company_id`)
);

CREATE TABLE `Company_sale_details` (
	`Company_id` varchar(10) NOT NULL,
	`Sales_id` varchar(10) NOT NULL,
	`Sales_amount` FLOAT(50) NOT NULL,
	`Date` DATE(10) NOT NULL,
	`Sale_status` varchar(20) NOT NULL,
	PRIMARY KEY (`Sales_id`)
);

CREATE TABLE `Order_cancel` (
	`Cancellation_id` varchar(10) NOT NULL,
	`Booking_id` varchar(10) NOT NULL,
	PRIMARY KEY (`Cancellation_id`)
);

CREATE TABLE `Feedback` (
	`Booking_id` varchar(10),
	`Cancellation_id` varchar(10),
	`Feedback` varchar(200) DEFAULT 'NULL'
);

CREATE TABLE `Pickup_person_details` (
	`Name` varchar(50) NOT NULL,
	`Contact_no` INT(15) NOT NULL UNIQUE,
	`Pickup_person_id` varchar(10) NOT NULL AUTO_INCREMENT,
	`Aadhar_no` INT(12) NOT NULL UNIQUE,
	`Salary` FLOAT(20) NOT NULL UNIQUE,
	`Rating` INT(5) DEFAULT 'NULL',
	PRIMARY KEY (`Pickup_person_id`)
);

ALTER TABLE `Booking_details` ADD CONSTRAINT `Booking_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `User_details`(`User_id`);

ALTER TABLE `Booking_details` ADD CONSTRAINT `Booking_details_fk1` FOREIGN KEY (`Pickup_person_id`) REFERENCES `Pickup_person_details`(`Pickup_person_id`);

ALTER TABLE `Payment_details` ADD CONSTRAINT `Payment_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `User_details`(`User_id`);

ALTER TABLE `Booked_item_list` ADD CONSTRAINT `Booked_item_list_fk0` FOREIGN KEY (`Pickup_id`) REFERENCES `Booking_details`(`Booking_id`);

ALTER TABLE `Booked_item_list` ADD CONSTRAINT `Booked_item_list_fk1` FOREIGN KEY (`Item_id`) REFERENCES `Item_details`(`Item_id`);

ALTER TABLE `Company_sale_details` ADD CONSTRAINT `Company_sale_details_fk0` FOREIGN KEY (`Company_id`) REFERENCES `Company_details`(`Company_id`);

ALTER TABLE `Order_cancel` ADD CONSTRAINT `Order_cancel_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `Booking_details`(`Booking_id`);

ALTER TABLE `Feedback` ADD CONSTRAINT `Feedback_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `Booking_details`(`Booking_id`);

ALTER TABLE `Feedback` ADD CONSTRAINT `Feedback_fk1` FOREIGN KEY (`Cancellation_id`) REFERENCES `Order_cancel`(`Cancellation_id`);

