-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Počítač: 127.0.0.1
-- Vytvořeno: Stř 01. úno 2017, 06:57
-- Verze serveru: 10.1.16-MariaDB
-- Verze PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Databáze: `pprorezervace`
--
CREATE DATABASE IF NOT EXISTS `pprorezervace` DEFAULT CHARACTER SET utf8 COLLATE utf8_czech_ci;
USE `pprorezervace`;

-- --------------------------------------------------------

--
-- Struktura tabulky `address`
--

CREATE TABLE `address` (
  `id` int(11) NOT NULL,
  `street` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `city` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `postal_code` varchar(50) COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `address`
--

INSERT INTO `address` (`id`, `street`, `city`, `postal_code`) VALUES
(1, 'Klasa street 1', 'Buranove', '11111'),
(5, 'You street', 'BHDD', '500005'),
(6, 'tst', 'test', '50000'),
(7, 'Nase street', 'V nasem meste', '50000'),
(8, 't', 't', '50000'),
(9, 'Naše druhá ulice', 'A naše druhé m?sto', '99999'),
(10, 'te', 'te', 'te');

-- --------------------------------------------------------

--
-- Struktura tabulky `court`
--

CREATE TABLE `court` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `address_id` int(11) NOT NULL,
  `active` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `court`
--

INSERT INTO `court` (`id`, `name`, `address_id`, `active`) VALUES
(5, 'Tenisový kurt', 7, 1),
(6, 'Volejbalový kurt', 9, 1);

-- --------------------------------------------------------

--
-- Struktura tabulky `reservation`
--

CREATE TABLE `reservation` (
  `id` int(11) NOT NULL,
  `date_start` datetime NOT NULL,
  `date_end` datetime NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `note` varchar(100) COLLATE utf8_czech_ci DEFAULT NULL,
  `court_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `reservation`
--

INSERT INTO `reservation` (`id`, `date_start`, `date_end`, `created`, `note`, `court_id`) VALUES
(1, '2017-02-02 11:00:00', '2017-02-02 13:00:00', '2017-02-01 06:20:35', NULL, 5),
(2, '2017-02-02 13:00:00', '2017-02-02 15:00:00', '2017-02-01 06:20:38', NULL, 5),
(3, '2017-02-02 17:00:00', '2017-02-02 19:00:00', '2017-02-01 06:20:41', NULL, 5),
(4, '2017-01-31 14:00:00', '2017-01-31 16:00:00', '2017-02-01 06:20:45', NULL, 5),
(5, '2017-01-31 10:00:00', '2017-01-31 12:00:00', '2017-02-01 06:20:48', NULL, 5),
(8, '2017-01-31 04:00:00', '2017-01-31 06:00:00', '2017-02-01 06:21:12', NULL, 5),
(9, '2017-01-31 06:00:00', '2017-01-31 08:00:00', '2017-02-01 06:21:15', NULL, 5),
(12, '2017-02-03 02:00:00', '2017-02-03 04:00:00', '2017-02-01 06:32:48', NULL, 5),
(13, '2017-02-01 08:00:00', '2017-02-01 09:30:00', '2017-02-01 06:33:06', NULL, 5),
(14, '2017-02-02 06:30:00', '2017-02-02 09:00:00', '2017-02-01 06:42:15', 'Pavel Srše?', 5);

-- --------------------------------------------------------

--
-- Struktura tabulky `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `type` enum('player','admin','forbidden') COLLATE utf8_czech_ci NOT NULL DEFAULT 'forbidden'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `role`
--

INSERT INTO `role` (`id`, `type`) VALUES
(1, 'player'),
(2, 'admin'),
(3, 'forbidden');

-- --------------------------------------------------------

--
-- Struktura tabulky `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `type` enum('free','paid') COLLATE utf8_czech_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `ticket`
--

INSERT INTO `ticket` (`id`, `name`, `type`) VALUES
(1, 'Volná permice', 'free'),
(2, 'Jednotková permice', 'paid');

-- --------------------------------------------------------

--
-- Struktura tabulky `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `surname` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_czech_ci NOT NULL,
  `address_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `email` varchar(70) COLLATE utf8_czech_ci NOT NULL,
  `ticket_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `user`
--

INSERT INTO `user` (`id`, `name`, `surname`, `username`, `password`, `address_id`, `role_id`, `email`, `ticket_id`) VALUES
(2, 'Pan', 'šéf', 'admin', 'admin', 1, 2, 'jenda@jenda.cz', 1),
(3, 'Tonda', 'Hluboký', 'player', 'player', 1, 1, 'fenda@fenda.cz', 1),
(4, 'Jenda', 'Nezbedný', 'testt', 'testt', 5, 3, 'jouda@jouda.cz', 1),
(5, 'Pavel', 'Sršeň', 'let', 'let', 8, 1, 'pepa@pepa.cz', 2);

-- --------------------------------------------------------

--
-- Struktura tabulky `user_reservation`
--

CREATE TABLE `user_reservation` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reservation_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_czech_ci;

--
-- Vypisuji data pro tabulku `user_reservation`
--

INSERT INTO `user_reservation` (`id`, `user_id`, `reservation_id`) VALUES
(1, 2, 1),
(2, 2, 2),
(3, 2, 3),
(4, 2, 4),
(5, 2, 5),
(8, 5, 8),
(9, 5, 9),
(12, 2, 12),
(13, 2, 13),
(14, 5, 14);

--
-- Klíče pro exportované tabulky
--

--
-- Klíče pro tabulku `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `court`
--
ALTER TABLE `court`
  ADD PRIMARY KEY (`id`),
  ADD KEY `address_id` (`address_id`);

--
-- Klíče pro tabulku `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `court_id` (`court_id`);

--
-- Klíče pro tabulku `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `type` (`type`);

--
-- Klíče pro tabulku `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`);

--
-- Klíče pro tabulku `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `address_id` (`address_id`),
  ADD KEY `role_id` (`role_id`),
  ADD KEY `ticket_id_2` (`ticket_id`);

--
-- Klíče pro tabulku `user_reservation`
--
ALTER TABLE `user_reservation`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `reservation_id` (`reservation_id`);

--
-- AUTO_INCREMENT pro tabulky
--

--
-- AUTO_INCREMENT pro tabulku `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT pro tabulku `court`
--
ALTER TABLE `court`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT pro tabulku `reservation`
--
ALTER TABLE `reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pro tabulku `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pro tabulku `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pro tabulku `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pro tabulku `user_reservation`
--
ALTER TABLE `user_reservation`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- Omezení pro exportované tabulky
--

--
-- Omezení pro tabulku `court`
--
ALTER TABLE `court`
  ADD CONSTRAINT `court_ibfk_2` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON UPDATE CASCADE;

--
-- Omezení pro tabulku `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`court_id`) REFERENCES `court` (`id`) ON UPDATE CASCADE;

--
-- Omezení pro tabulku `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_10` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`),
  ADD CONSTRAINT `user_ibfk_6` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `user_ibfk_8` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON UPDATE CASCADE;

--
-- Omezení pro tabulku `user_reservation`
--
ALTER TABLE `user_reservation`
  ADD CONSTRAINT `user_reservation_ibfk_3` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `user_reservation_ibfk_4` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
