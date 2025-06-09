-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 07, 2025 at 02:14 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4


USE `escaperoomdb`;


INSERT INTO `escaperoom` (`idEscaperoom_ref`, `name`) VALUES
(1, 'Escape Room');


INSERT INTO `room` (`idRoom`, `idEscaperoom_ref`, `name`, `difficulty`, `price`, `theme`) VALUES
(1, 1, 'Sala Disney', 'EASY', 100.00, 'DISNEY');


INSERT INTO `decoration` (`idDecoration`, `idRoom_ref`, `description`, `material`, `price`) VALUES
(1, 1, 'Orejas mickey', 'plastiquete', 60.00);


INSERT INTO `hint` (`idHint`, `idRoom_ref`, `description`, `theme`, `price`) VALUES
(1, 1, 'Ponte las orejitas y lo verás todo más claro', 'DISNEY', 20.00);


INSERT INTO `player` (`idPlayer`, `name`, `email`) VALUES
(1, 'Romina', 'romi@mail.com');


INSERT INTO `tickets` (`idTickets`, `idRoom`, `idPlayer`, `boughtOn`, `price`) VALUES
(1, 1, 1, '2025-06-07 14:13:31', 35);

