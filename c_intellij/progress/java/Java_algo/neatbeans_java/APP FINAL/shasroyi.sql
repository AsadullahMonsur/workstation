-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 08, 2019 at 05:48 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `shasroyi`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `AdminName` text NOT NULL,
  `AdminPass` text NOT NULL,
  `AdminNumber` text NOT NULL,
  `AdminType` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`AdminName`, `AdminPass`, `AdminNumber`, `AdminType`) VALUES
('Sakib', '1234', '01918779365', 'System Admin');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `Name` text NOT NULL,
  `Price` int(11) NOT NULL,
  `Details` text NOT NULL,
  `ShopName` text NOT NULL,
  `Distance` int(11) NOT NULL,
  `ShopAddress` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`Name`, `Price`, `Details`, `ShopName`, `Distance`, `ShopAddress`) VALUES
('Samung S8', 60000, 'Camera 12MP, bettery - 3500MAH', 'North Tower', 5, 'Uttara,Sector 7,Dhaka'),
('Samsung S8', 50000, 'Joss features', 'Rajuk Commercial Complex', 2, 'Uttara sector 7, Dhaka'),
('Samsung S8', 55000, 'Onek features', 'Bashundhara Complex', 20, 'Bashundha'),
('Iphone 11', 110000, 'Joss features', 'North Tower', 5, 'Uttara,Sector 7,Dhaka'),
('Samsung S7', 35000, 'Sheiram features', 'ZamZam Tower', 3, 'Uttara Sector 11,Dhaka'),
('Samsung S7', 30000, 'Makhon features', 'Polwel Carneshon', 7, 'Uttara Sector 6,Dhaka'),
('Samsung S7', 35000, 'nice features', 'Maskot Plaza', 5, 'Uttara Sector 7,Dhaka'),
('Samsung S7', 35000, 'kono kotha hobe na features', 'Zamuna future park', 15, 'Bashundhara,Dhaka'),
('iphone 10', 80000, '2 Ta camera ase vaya', 'North Tower', 4, 'Uttara Sector 7'),
('iphone 7', 40000, 'Nice camera', 'North Tower', 4, 'Uttara Sector 7'),
('iphone 10', 70000, 'Shei Camera mama', 'Rajuk Comercial', 4, 'Uttara Sector 7'),
('Samsung S10', 90000, '3 Ta camera ase vaya', 'North Tower', 4, 'Uttara Sector 7'),
('Samsung note 10', 110000, 'Makkhon mobile, stick ase', 'North Tower', 4, 'Uttara Sector 7'),
('Samsung note 10', 105000, 'Shesi mobile, stick ase, Camera 3 ta', 'Rajuk Commercial', 5, 'Uttara Sector 7'),
('Samsung A7', 30000, 'Joss jinis', 'North Tower', 4, 'Uttara Sector 7'),
('Samsung C7', 20000, 'Budget er moddhe nice', 'Rajuk Commercial', 5, 'Uttara Sector 7'),
('Samsung E7', 30000, 'Aluminiam body, nice lage dekhte', 'Polwel Carnesion', 6, 'Uttara Sector 8');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserName` text NOT NULL,
  `UserNumber` text NOT NULL,
  `UserEmail` text NOT NULL,
  `UserPassword` text NOT NULL,
  `UserAddress` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserName`, `UserNumber`, `UserEmail`, `UserPassword`, `UserAddress`) VALUES
('hrk.sakib', '01918779365', 'hrk.sakib@gmail.com', 'hrk.sakib', 'Uttara,Dhaka'),
('Sakib.md', '01918654874', 'sakib@gmail.com', '258', 'Uttara'),
('Sakib.Ahmed', '0151484197', 'Sakib.Ahmed@gmail.com', '1234', 'Bashundhara, Dhaka'),
('md.sakib', '01918779365', 'hrk.sakib@gmail.com', 'sakib', 'Uttara, Dhaka'),
('kabir', '018194655165', 'kabir@gmail.com', 'kabir', 'Gulshan'),
('Md.Sakib.Khan', '01918779365', 'hrk.sakib@gmail.com', '1234', 'Uttara Sector 7, Dhaka');

-- --------------------------------------------------------

--
-- Table structure for table `userpurchaselist`
--

CREATE TABLE `userpurchaselist` (
  `UserName` text NOT NULL,
  `ProductName` text NOT NULL,
  `ProductPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userpurchaselist`
--

INSERT INTO `userpurchaselist` (`UserName`, `ProductName`, `ProductPrice`) VALUES
('hrk.sakib', 'Samsung S7', 35000),
('hrk.sakib', 'SamsungS8', 60000),
('md.sakib', 'Samsung S7', 30000),
('md.sakib', 'Samsung S8', 55000),
('kabir', 'Samsung S8', 55000),
('kabir', 'Samsung S7', 30000),
('hrk.sakib', 'iphone 11', 110000),
('Md.Sakib.Khan', 'Samsung S7', 30000),
('Md.Sakib.Khan', 'Iphone 10', 70000);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
