-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.2:3307
-- Generation Time: Aug 25, 2019 at 08:06 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_sv_2`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_details`
--

CREATE TABLE `admin_details` (
  `name` varchar(20) NOT NULL,
  `admin_id` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin_details`
--

INSERT INTO `admin_details` (`name`, `admin_id`, `Username`) VALUES
('aishwarya', 5, 'aishwaryaadmin'),
('noman', 6, 'nomanadmin'),
('poornima', 7, 'poornimaadmin'),
('saif', 8, 'saifadmin');

-- --------------------------------------------------------

--
-- Table structure for table `booked_item_list`
--

CREATE TABLE `booked_item_list` (
  `Item_id` int(11) NOT NULL,
  `Booking_id` int(11) NOT NULL,
  `Item_qty` float NOT NULL,
  `Item_amount` float NOT NULL,
  `Pickup_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_details`
--

CREATE TABLE `booking_details` (
  `User_id` int(11) NOT NULL,
  `Booking_date_time` datetime NOT NULL,
  `Scheduled_pickup_date_time` datetime NOT NULL,
  `Pickup_date_time` datetime DEFAULT NULL,
  `Booking_id` int(11) NOT NULL,
  `Pickup_person_id` int(11) NOT NULL,
  `Pickup_status` varchar(20) NOT NULL,
  `Pickup_person_rating` int(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking_details`
--

INSERT INTO `booking_details` (`User_id`, `Booking_date_time`, `Scheduled_pickup_date_time`, `Pickup_date_time`, `Booking_id`, `Pickup_person_id`, `Pickup_status`, `Pickup_person_rating`) VALUES
(1, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 1, 1, 'Pending', NULL),
(1, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 2, 2, 'Pending', NULL),
(1, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 3, 3, 'Pending', NULL),
(1, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 4, 4, 'Pending', NULL),
(2, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 5, 1, 'Pending', NULL),
(2, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 6, 2, 'Pending', NULL),
(2, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 7, 3, 'Pending', NULL),
(2, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 8, 4, 'Pending', NULL),
(3, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 9, 1, 'Pending', NULL),
(3, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 10, 2, 'Pending', NULL),
(3, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 11, 3, 'Pending', NULL),
(3, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 12, 4, 'Pending', NULL),
(4, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 13, 1, 'Pending', NULL),
(4, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 14, 2, 'Pending', NULL),
(4, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 15, 3, 'Pending', NULL),
(4, '2019-08-03 20:59:42', '2019-08-07 20:59:58', '2019-08-14 21:00:25', 16, 4, 'Pending', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `company_details`
--

CREATE TABLE `company_details` (
  `Company_id` int(11) NOT NULL,
  `Company_name` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Contact_no` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company_details`
--

INSERT INTO `company_details` (`Company_id`, `Company_name`, `Address`, `Contact_no`) VALUES
(1, 'recycling company one', 'noida', '1234567890'),
(2, 'recycling company two', 'noida', '2345678901'),
(3, 'racycling company three', 'noida', '3456789012'),
(4, 'recycling company four', 'noida', '4567890123'),
(5, 'recycling company five', 'noida', '5678901234');

-- --------------------------------------------------------

--
-- Table structure for table `company_sale_details`
--

CREATE TABLE `company_sale_details` (
  `Company_id` int(11) NOT NULL,
  `Sales_id` int(11) NOT NULL,
  `Sales_amount` float NOT NULL,
  `Date` datetime NOT NULL,
  `Sale_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `contact_us`
--

CREATE TABLE `contact_us` (
  `Author` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Contact_no` int(15) NOT NULL,
  `Subject` varchar(50) NOT NULL,
  `Message` varchar(255) NOT NULL,
  `Contact_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contact_us`
--

INSERT INTO `contact_us` (`Author`, `Email`, `Contact_no`, `Subject`, `Message`, `Contact_id`) VALUES
('Author one', 'authorone@email.com', 1234412331, 'tiran', 'db rest', 1),
('Author two', 'any', 12930, 'test', 'afasf', 2),
('Author three', 'authorone@email.com', 1234412331, 'tiran', 'db rest', 3);

-- --------------------------------------------------------

--
-- Table structure for table `item_details`
--

CREATE TABLE `item_details` (
  `Item_id` int(11) NOT NULL,
  `Item_name` varchar(30) NOT NULL,
  `Item_rate` float NOT NULL,
  `Item_measure` varchar(20) NOT NULL,
  `Item_image` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `item_details`
--

INSERT INTO `item_details` (`Item_id`, `Item_name`, `Item_rate`, `Item_measure`, `Item_image`) VALUES
(1, 'newspaper', 10, 'Kg', NULL),
(2, 'plastic', 15, 'Kg', NULL),
(3, 'copper', 30, 'Kg', NULL),
(4, 'glass bottle', 5, 'piece', NULL),
(5, 'rubber tyre', 50, 'piece', NULL),
(6, 'iron', 35, 'Kg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `login_info`
--

CREATE TABLE `login_info` (
  `Username` varchar(50) NOT NULL,
  `Role` int(11) NOT NULL DEFAULT '2',
  `password` char(32) NOT NULL,
  `email` varchar(100) NOT NULL,
  `contact_no` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='role 0 - user 1 - pickuPerson 2 - admin';

--
-- Dumping data for table `login_info`
--

INSERT INTO `login_info` (`Username`, `Role`, `password`, `email`, `contact_no`) VALUES
('aishwaryaadmin', 0, 'admin', 'aishwaryaadminemail', '040311804418'),
('aishwaryapickup', 1, 'pickup', 'aishwaryapickupemail', '140311804418'),
('aishwaryauser', 2, 'user', 'aishwaryauseremail', '240311804418'),
('nomanadmin', 0, 'admin', 'nomanadminemail', '0111111155'),
('nomanpickup', 1, 'pickup', 'nomanpickupemail', '1111111155'),
('nomanuser', 2, 'user', 'nomanuseremail', '2111111155'),
('poornimaadmin', 0, 'admin', 'poornimaadminemail', '0111111156'),
('poornimapickup', 1, 'pickup', 'poornimapickupemail', '1111111156'),
('poornimauser', 2, 'user', 'poornimauseremail', '2111111156'),
('saifadmin', 0, 'admin', 'saifadminemail', '0111111157'),
('saifpickup', 1, 'pickup', 'saifpickupemail', '1111111157'),
('saifuser', 2, 'user', 'saifuseremail', '2111111157');

-- --------------------------------------------------------

--
-- Table structure for table `order_cancel`
--

CREATE TABLE `order_cancel` (
  `Booking_id` int(11) NOT NULL,
  `Cancellation_id` int(11) NOT NULL,
  `Feedback` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payment_details`
--

CREATE TABLE `payment_details` (
  `Payment_id` int(11) NOT NULL,
  `Payment_amount` float NOT NULL,
  `Booking_id` int(11) NOT NULL,
  `Payment_mode` varchar(20) DEFAULT 'online',
  `Payment_status` varchar(20) DEFAULT 'pending'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pickup_person_details`
--

CREATE TABLE `pickup_person_details` (
  `Name` varchar(50) NOT NULL,
  `Aadhar_no` varchar(12) NOT NULL,
  `Pickup_person_id` int(11) NOT NULL,
  `Salary` float NOT NULL,
  `Rating` int(5) DEFAULT '0',
  `Pickup_person_status` varchar(25) DEFAULT 'working',
  `Username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pickup_person_details`
--

INSERT INTO `pickup_person_details` (`Name`, `Aadhar_no`, `Pickup_person_id`, `Salary`, `Rating`, `Pickup_person_status`, `Username`) VALUES
('aishwarya', '40311804418', 1, 1000, 0, 'working', 'aishwaryapickup'),
('noman', '02111804418', 2, 1000, 0, 'working', 'nomanpickup'),
('poornima', '02511804418', 3, 1000, 0, 'working', 'poornimapickup'),
('saif', '03511804418', 4, 500, 0, 'working', 'saifpickup');

-- --------------------------------------------------------

--
-- Table structure for table `pickup_person_record`
--

CREATE TABLE `pickup_person_record` (
  `Pickup_person_id` int(11) NOT NULL,
  `Date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `10:00AM-12:00PM` tinyint(4) DEFAULT '1',
  `12:00PM-02:00PM` tinyint(4) DEFAULT '1',
  `02:00PM-04:00PM` tinyint(4) DEFAULT '1',
  `04:00PM-06:00PM` tinyint(4) DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `Name` varchar(50) NOT NULL,
  `Address` varchar(100) DEFAULT 'NULL',
  `User_id` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_details`
--

INSERT INTO `user_details` (`Name`, `Address`, `User_id`, `Username`) VALUES
('aishwarya', 'noida', 1, 'aishwaryauser'),
('noman', 'noida', 2, 'nomanuser'),
('saif', 'noida', 3, 'saifuser'),
('poornima', 'noida', 4, 'poornimauser');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_details`
--
ALTER TABLE `admin_details`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `admin_admin_id_uindex` (`admin_id`),
  ADD KEY `admin___fk1` (`Username`);

--
-- Indexes for table `booked_item_list`
--
ALTER TABLE `booked_item_list`
  ADD KEY `booked_item_list_fk0` (`Item_id`),
  ADD KEY `booked_item_list_fk1` (`Booking_id`);

--
-- Indexes for table `booking_details`
--
ALTER TABLE `booking_details`
  ADD PRIMARY KEY (`Booking_id`),
  ADD UNIQUE KEY `booking_details_Booking_id_uindex` (`Booking_id`),
  ADD KEY `booking_details_fk0` (`User_id`),
  ADD KEY `booking_details_fk1` (`Pickup_person_id`);

--
-- Indexes for table `company_details`
--
ALTER TABLE `company_details`
  ADD PRIMARY KEY (`Company_id`),
  ADD UNIQUE KEY `company_details_Company_id_uindex` (`Company_id`);

--
-- Indexes for table `company_sale_details`
--
ALTER TABLE `company_sale_details`
  ADD PRIMARY KEY (`Sales_id`),
  ADD UNIQUE KEY `company_sale_details_Sales_id_uindex` (`Sales_id`),
  ADD KEY `company_sale_details_fk0` (`Company_id`);

--
-- Indexes for table `contact_us`
--
ALTER TABLE `contact_us`
  ADD PRIMARY KEY (`Contact_id`),
  ADD UNIQUE KEY `contact_id_UNIQUE` (`Contact_id`);

--
-- Indexes for table `item_details`
--
ALTER TABLE `item_details`
  ADD PRIMARY KEY (`Item_id`);

--
-- Indexes for table `login_info`
--
ALTER TABLE `login_info`
  ADD PRIMARY KEY (`Username`),
  ADD UNIQUE KEY `login_info_Username_uindex` (`Username`),
  ADD UNIQUE KEY `login_info_contact_no_uindex` (`contact_no`),
  ADD UNIQUE KEY `login_info_email_uindex` (`email`);

--
-- Indexes for table `order_cancel`
--
ALTER TABLE `order_cancel`
  ADD PRIMARY KEY (`Cancellation_id`),
  ADD UNIQUE KEY `order_cancel_Cancellation_id_uindex` (`Cancellation_id`),
  ADD KEY `order_cancel___fk0` (`Booking_id`);

--
-- Indexes for table `payment_details`
--
ALTER TABLE `payment_details`
  ADD PRIMARY KEY (`Payment_id`),
  ADD KEY `payment_details_fk0` (`Booking_id`);

--
-- Indexes for table `pickup_person_details`
--
ALTER TABLE `pickup_person_details`
  ADD PRIMARY KEY (`Pickup_person_id`),
  ADD UNIQUE KEY `pickup_person_details_Aadhar_no_uindex` (`Aadhar_no`),
  ADD UNIQUE KEY `pickup_person_details_Pickup_person_id_uindex` (`Pickup_person_id`),
  ADD UNIQUE KEY `pickup_person_details_Username_uindex` (`Username`);

--
-- Indexes for table `pickup_person_record`
--
ALTER TABLE `pickup_person_record`
  ADD KEY `pickup_person_record_fk0` (`Pickup_person_id`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`User_id`),
  ADD UNIQUE KEY `user_details_User_id_uindex` (`User_id`),
  ADD UNIQUE KEY `user_details_Username_uindex` (`Username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_details`
--
ALTER TABLE `admin_details`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `booking_details`
--
ALTER TABLE `booking_details`
  MODIFY `Booking_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `company_details`
--
ALTER TABLE `company_details`
  MODIFY `Company_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `company_sale_details`
--
ALTER TABLE `company_sale_details`
  MODIFY `Sales_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `contact_us`
--
ALTER TABLE `contact_us`
  MODIFY `Contact_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `item_details`
--
ALTER TABLE `item_details`
  MODIFY `Item_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `order_cancel`
--
ALTER TABLE `order_cancel`
  MODIFY `Cancellation_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pickup_person_details`
--
ALTER TABLE `pickup_person_details`
  MODIFY `Pickup_person_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `user_details`
--
ALTER TABLE `user_details`
  MODIFY `User_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin_details`
--
ALTER TABLE `admin_details`
  ADD CONSTRAINT `admin___fk0` FOREIGN KEY (`Username`) REFERENCES `login_info` (`Username`);

--
-- Constraints for table `booked_item_list`
--
ALTER TABLE `booked_item_list`
  ADD CONSTRAINT `booked_item_list___fk0` FOREIGN KEY (`Item_id`) REFERENCES `item_details` (`Item_id`),
  ADD CONSTRAINT `booked_item_list___fk1` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details` (`Booking_id`);

--
-- Constraints for table `booking_details`
--
ALTER TABLE `booking_details`
  ADD CONSTRAINT `booking_details___fk0` FOREIGN KEY (`User_id`) REFERENCES `user_details` (`User_id`),
  ADD CONSTRAINT `booking_details___fk1` FOREIGN KEY (`Pickup_person_id`) REFERENCES `pickup_person_details` (`Pickup_person_id`);

--
-- Constraints for table `company_sale_details`
--
ALTER TABLE `company_sale_details`
  ADD CONSTRAINT `company_sale_details___fk0` FOREIGN KEY (`Company_id`) REFERENCES `company_details` (`Company_id`);

--
-- Constraints for table `order_cancel`
--
ALTER TABLE `order_cancel`
  ADD CONSTRAINT `order_cancel___fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details` (`Booking_id`);

--
-- Constraints for table `payment_details`
--
ALTER TABLE `payment_details`
  ADD CONSTRAINT `payment_details___fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details` (`Booking_id`);

--
-- Constraints for table `pickup_person_details`
--
ALTER TABLE `pickup_person_details`
  ADD CONSTRAINT `pickup_person_details___fk0` FOREIGN KEY (`Username`) REFERENCES `login_info` (`Username`);

--
-- Constraints for table `pickup_person_record`
--
ALTER TABLE `pickup_person_record`
  ADD CONSTRAINT `pickup_person_record___fk0` FOREIGN KEY (`Pickup_person_id`) REFERENCES `pickup_person_details` (`Pickup_person_id`);

--
-- Constraints for table `user_details`
--
ALTER TABLE `user_details`
  ADD CONSTRAINT `user_details___fk0` FOREIGN KEY (`Username`) REFERENCES `login_info` (`Username`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
