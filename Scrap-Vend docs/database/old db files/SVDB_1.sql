CREATE TABLE `booked_item_list` (
	`Pickup_id` varchar(10) NOT NULL,
	`Item_id` varchar(10) NOT NULL
);

CREATE TABLE `booking_details` (
	`User_id` varchar(10) NOT NULL,
	`Pickup_person_id` int(10) NOT NULL,
	`Booking_date` DATETIME NOT NULL,
	`Pickup_date` DATETIME NOT NULL,
	`Booking_id` varchar(10) NOT NULL,
	`Pickup_status` varchar(20) NOT NULL
);

CREATE TABLE `company_details` (
	`Company_id` varchar(10) NOT NULL,
	`Company_name` varchar(50) NOT NULL,
	`Address` varchar(50) NOT NULL,
	`Contact_no` varchar(50) NOT NULL
);

CREATE TABLE `company_sale_details` (
	`Company_id` varchar(10) NOT NULL,
	`Sales_id` varchar(10) NOT NULL,
	`Sales_amount` double NOT NULL,
	`Date` DATETIME NOT NULL,
	`Sale_status` varchar(20) NOT NULL
);

CREATE TABLE `feedback` (
	`Booking_id` varchar(10),
	`Cancellation_id` varchar(10),
	`Feedback` varchar(200) DEFAULT 'NULL'
);

CREATE TABLE `item_details` (
	`Item_id` varchar(10) NOT NULL,
	`Item_name` varchar(30) NOT NULL,
	`Item_rate` FLOAT NOT NULL,
	`Item_measure` varchar(20) NOT NULL
);

CREATE TABLE `order_cancel` (
	`Cancellation_id` varchar(10) NOT NULL,
	`Booking_id` varchar(10) NOT NULL
);

CREATE TABLE `payment_details` (
	`Payment_id` varchar(20) NOT NULL,
	`Payment_amount` FLOAT NOT NULL,
	`Reware_id` varchar(10) DEFAULT 'NULL',
	`User_id` varchar(10) NOT NULL,
	`Reware_amount` FLOAT DEFAULT '0'
);

CREATE TABLE `pickup_person_details` (
	`Name` varchar(50) NOT NULL,
	`Contact_no` int(15) NOT NULL,
	`Pickup_person_id` int(10) NOT NULL,
	`Aadhar_no` int(12) NOT NULL,
	`Salary` FLOAT NOT NULL,
	`Rating` int(5) DEFAULT '0'
);

CREATE TABLE `user_details` (
	`Name` varchar(50) NOT NULL,
	`Address` varchar(100) DEFAULT 'NULL',
	`Contact_no` varchar(15) NOT NULL,
	`email_id` varchar(50) NOT NULL,
	`User_id` varchar(10) NOT NULL,
	`Password` varchar(50) NOT NULL
);

ALTER TABLE `booked_item_list` ADD CONSTRAINT `booked_item_list_fk0` FOREIGN KEY (`Pickup_id`) REFERENCES `booking_details`(`Booking_id`);

ALTER TABLE `booked_item_list` ADD CONSTRAINT `booked_item_list_fk1` FOREIGN KEY (`Item_id`) REFERENCES `item_details`(`Item_id`);

ALTER TABLE `booking_details` ADD CONSTRAINT `booking_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `user_details`(`User_id`);

ALTER TABLE `booking_details` ADD CONSTRAINT `booking_details_fk1` FOREIGN KEY (`Pickup_person_id`) REFERENCES `pickup_person_details`(`Pickup_person_id`);

ALTER TABLE `company_sale_details` ADD CONSTRAINT `company_sale_details_fk0` FOREIGN KEY (`Company_id`) REFERENCES `company_details`(`Company_id`);

ALTER TABLE `feedback` ADD CONSTRAINT `feedback_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details`(`Booking_id`);

ALTER TABLE `feedback` ADD CONSTRAINT `feedback_fk1` FOREIGN KEY (`Cancellation_id`) REFERENCES `order_cancel`(`Cancellation_id`);

ALTER TABLE `order_cancel` ADD CONSTRAINT `order_cancel_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details`(`Booking_id`);

ALTER TABLE `payment_details` ADD CONSTRAINT `payment_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `user_details`(`User_id`);

