-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 15, 2019 at 02:43 PM
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
-- Database: `test_sv_1`
--

-- --------------------------------------------------------

--
-- Table structure for table `booked_item_list`
--

CREATE TABLE `booked_item_list` (
  `Pickup_id` varchar(10) NOT NULL,
  `Item_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `booking_details`
--

CREATE TABLE `booking_details` (
  `User_id` varchar(10) NOT NULL,
  `Pickup_person_id` int(10) NOT NULL,
  `Booking_date` datetime NOT NULL,
  `Pickup_date` datetime NOT NULL,
  `Booking_id` varchar(10) NOT NULL,
  `Pickup_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `company_details`
--

CREATE TABLE `company_details` (
  `Company_id` varchar(10) NOT NULL,
  `Company_name` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `Contact_no` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `company_sale_details`
--

CREATE TABLE `company_sale_details` (
  `Company_id` varchar(10) NOT NULL,
  `Sales_id` varchar(10) NOT NULL,
  `Sales_amount` double NOT NULL,
  `Date` datetime NOT NULL,
  `Sale_status` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `Booking_id` varchar(10) DEFAULT NULL,
  `Cancellation_id` varchar(10) DEFAULT NULL,
  `Feedback` varchar(200) DEFAULT 'NULL'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `item_details`
--

CREATE TABLE `item_details` (
  `Item_id` varchar(10) NOT NULL,
  `Item_name` varchar(30) NOT NULL,
  `Item_rate` float NOT NULL,
  `Item_measure` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `order_cancel`
--

CREATE TABLE `order_cancel` (
  `Cancellation_id` varchar(10) NOT NULL,
  `Booking_id` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `payment_details`
--

CREATE TABLE `payment_details` (
  `Payment_id` varchar(20) NOT NULL,
  `Payment_amount` float NOT NULL,
  `Reware_id` varchar(10) DEFAULT 'NULL',
  `User_id` varchar(10) NOT NULL,
  `Reware_amount` float DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pickup_person_details`
--

CREATE TABLE `pickup_person_details` (
  `Name` varchar(50) NOT NULL,
  `Contact_no` int(15) NOT NULL,
  `Pickup_person_id` int(10) NOT NULL,
  `Aadhar_no` int(12) NOT NULL,
  `Salary` float NOT NULL,
  `Rating` int(5) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user_details`
--

CREATE TABLE `user_details` (
  `Name` varchar(50) NOT NULL,
  `Address` varchar(100) DEFAULT 'NULL',
  `Contact_no` varchar(15) NOT NULL,
  `email_id` varchar(50) NOT NULL,
  `User_id` varchar(10) NOT NULL,
  `Password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booked_item_list`
--
ALTER TABLE `booked_item_list`
  ADD KEY `Booked_item_list_fk0` (`Pickup_id`),
  ADD KEY `Booked_item_list_fk1` (`Item_id`);

--
-- Indexes for table `booking_details`
--
ALTER TABLE `booking_details`
  ADD PRIMARY KEY (`Booking_id`),
  ADD UNIQUE KEY `User_id` (`User_id`),
  ADD UNIQUE KEY `Booking_id` (`Booking_id`),
  ADD KEY `Booking_details_fk1` (`Pickup_person_id`);

--
-- Indexes for table `company_details`
--
ALTER TABLE `company_details`
  ADD PRIMARY KEY (`Company_id`),
  ADD UNIQUE KEY `Contact_no` (`Contact_no`);

--
-- Indexes for table `company_sale_details`
--
ALTER TABLE `company_sale_details`
  ADD PRIMARY KEY (`Sales_id`),
  ADD KEY `Company_sale_details_fk0` (`Company_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD KEY `Feedback_fk0` (`Booking_id`),
  ADD KEY `Feedback_fk1` (`Cancellation_id`);

--
-- Indexes for table `item_details`
--
ALTER TABLE `item_details`
  ADD PRIMARY KEY (`Item_id`);

--
-- Indexes for table `order_cancel`
--
ALTER TABLE `order_cancel`
  ADD PRIMARY KEY (`Cancellation_id`),
  ADD KEY `Order_cancel_fk0` (`Booking_id`);

--
-- Indexes for table `payment_details`
--
ALTER TABLE `payment_details`
  ADD PRIMARY KEY (`Payment_id`),
  ADD KEY `Payment_details_fk0` (`User_id`);

--
-- Indexes for table `pickup_person_details`
--
ALTER TABLE `pickup_person_details`
  ADD PRIMARY KEY (`Pickup_person_id`),
  ADD UNIQUE KEY `Contact_no` (`Contact_no`),
  ADD UNIQUE KEY `Aadhar_no` (`Aadhar_no`);

--
-- Indexes for table `user_details`
--
ALTER TABLE `user_details`
  ADD PRIMARY KEY (`User_id`),
  ADD UNIQUE KEY `Contact_no` (`Contact_no`),
  ADD UNIQUE KEY `email_id` (`email_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pickup_person_details`
--
ALTER TABLE `pickup_person_details`
  MODIFY `Contact_no` int(15) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booked_item_list`
--
ALTER TABLE `booked_item_list`
  ADD CONSTRAINT `Booked_item_list_fk0` FOREIGN KEY (`Pickup_id`) REFERENCES `booking_details` (`Booking_id`),
  ADD CONSTRAINT `Booked_item_list_fk1` FOREIGN KEY (`Item_id`) REFERENCES `item_details` (`Item_id`);

--
-- Constraints for table `booking_details`
--
ALTER TABLE `booking_details`
  ADD CONSTRAINT `Booking_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `user_details` (`User_id`),
  ADD CONSTRAINT `Booking_details_fk1` FOREIGN KEY (`Pickup_person_id`) REFERENCES `pickup_person_details` (`Pickup_person_id`);

--
-- Constraints for table `company_sale_details`
--
ALTER TABLE `company_sale_details`
  ADD CONSTRAINT `Company_sale_details_fk0` FOREIGN KEY (`Company_id`) REFERENCES `company_details` (`Company_id`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `Feedback_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details` (`Booking_id`),
  ADD CONSTRAINT `Feedback_fk1` FOREIGN KEY (`Cancellation_id`) REFERENCES `order_cancel` (`Cancellation_id`);

--
-- Constraints for table `order_cancel`
--
ALTER TABLE `order_cancel`
  ADD CONSTRAINT `Order_cancel_fk0` FOREIGN KEY (`Booking_id`) REFERENCES `booking_details` (`Booking_id`);

--
-- Constraints for table `payment_details`
--
ALTER TABLE `payment_details`
  ADD CONSTRAINT `Payment_details_fk0` FOREIGN KEY (`User_id`) REFERENCES `user_details` (`User_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
