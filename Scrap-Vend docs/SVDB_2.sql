CREATE TABLE `booked_item_list` (
	`Item_id` varchar(10) NOT NULL,
	`Booking_id` varchar(10) NOT NULL,
	`Item_qty` FLOAT(5) NOT NULL,
	`Item_amount` FLOAT(5) NOT NULL,
	`Pickup_timestamp` TIMESTAMP NOT NULL
);

CREATE TABLE `booking_details` (
	`User_id` varchar(10) NOT NULL,
	`Booking_date_time` DATETIME NOT NULL,
	`Scheduled_pickup_date_time` DATETIME NOT NULL,
	`Pickup_date_time` DATETIME,
	`Booking_id` varchar(10) NOT NULL,
	`Pickup_person_id` varchar(10) NOT NULL,
	`Pickup_status` varchar(20) NOT NULL,
	`Pickup_person_rating` int(5),
	PRIMARY KEY (`Booking_id`)
);

CREATE TABLE `company_details` (
	`Company_id` varchar(10) NOT NULL,
	`Company_name` varchar(50) NOT NULL,
	`Address` varchar(50) NOT NULL,
	`Contact_no` varchar(50) NOT NULL,
	PRIMARY KEY (`Company_id`)
);

CREATE TABLE `company_sale_details` (
	`Company_id` varchar(10) NOT NULL,
	`Sales_id` varchar(10) NOT NULL,
	`Sales_amount` FLOAT(20) NOT NULL,
	`Date` DATETIME NOT NULL,
	`Sale_status` varchar(20) NOT NULL
);

CREATE TABLE `item_details` (
	`Item_id` varchar(10) NOT NULL,
	`Item_name` varchar(30) NOT NULL,
	`Item_rate` FLOAT(10) NOT NULL,
	`Item_measure` varchar(20) NOT NULL,
	`Item_image` blob,
	PRIMARY KEY (`Item_id`)
);

CREATE TABLE `order_cancel` (
	`Booking_id` varchar(10) NOT NULL,
	`Cancellation_id` varchar(10) NOT NULL,
	`Feedback` varchar(255),
	PRIMARY KEY (`Cancellation_id`)
);

CREATE TABLE `payment_details` (
	`Payment_id` varchar(10) NOT NULL,
	`Payment_amount` FLOAT(10) NOT NULL,
	`Booking_id` varchar(10) NOT NULL,
	`Payment_mode` varchar(20) DEFAULT 'online',
	`Payment_status` varchar(20) DEFAULT 'pending',
	PRIMARY KEY (`Payment_id`)
);

CREATE TABLE `pickup_person_details` (
	`Name` varchar(50) NOT NULL,
	`Contact_no` int(15) NOT NULL,
	`Aadhar_no` int(12) NOT NULL,
	`Pickup_person_id` varchar(10) NOT NULL,
	`Salary` FLOAT(10) NOT NULL,
	`Rating` int(5) DEFAULT '0',
	`Pickup_person_status` varchar(25) DEFAULT 'working',
	PRIMARY KEY (`Pickup_person_id`)
);

CREATE TABLE `user_details` (
	`Name` varchar(50) NOT NULL,
	`Address` varchar(100) DEFAULT 'NULL',
	`Contact_no` varchar(15) NOT NULL,
	`email_id` varchar(50) NOT NULL,
	`User_id` varchar(10) NOT NULL,
	`Password` varchar(50) NOT NULL,
	PRIMARY KEY (`User_id`)
);

CREATE TABLE `pickup_person_record` (
	`Pickup_person_id` varchar(10) NOT NULL,
	`Date` TIMESTAMP NOT NULL,
	`"10:00AM-12:00PM"` BOOLEAN DEFAULT 'TRUE',
	`"12:00PM-02:00PM"` BOOLEAN DEFAULT 'TRUE',
	`"02:00PM-04:00PM"` BOOLEAN DEFAULT 'TRUE',
	`"04:00PM-06:00PM"` BOOLEAN DEFAULT 'TRUE'
);

CREATE TABLE `contact_us` (
	`Author` varchar(30) NOT NULL,
	`Email` varchar(30) NOT NULL,
	`Contact_no` int(15) NOT NULL,
	`Subject` varchar(50) NOT NULL,
	`Message` varchar(255) NOT NULL
);

ALTER TABLE `booked_item_list` ADD CONSTRAINT `booked_item_list_fk0` FOREIGN KEY (`Item_id`) REFERENCES `item_details`(`Item_id`);

ALTER TABLE `booked_item_list` ADD CONSTRAINT `booked_item_list_fk1` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details`(`Booking_id`);

ALTER TABLE `booking_details` ADD CONSTRAINT `booking_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `user_details`(`User_id`);

ALTER TABLE `booking_details` ADD CONSTRAINT `booking_details_fk1` FOREIGN KEY (`Pickup_person_id`) REFERENCES `pickup_person_details`(`Pickup_person_id`);

ALTER TABLE `company_sale_details` ADD CONSTRAINT `company_sale_details_fk0` FOREIGN KEY (`Company_id`) REFERENCES `company_details`(`Company_id`);

ALTER TABLE `order_cancel` ADD CONSTRAINT `order_cancel_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details`(`Booking_id`);

ALTER TABLE `payment_details` ADD CONSTRAINT `payment_details_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details`(`Booking_id`);

ALTER TABLE `pickup_person_record` ADD CONSTRAINT `pickup_person_record_fk0` FOREIGN KEY (`Pickup_person_id`) REFERENCES `pickup_person_details`(`Pickup_person_id`);

