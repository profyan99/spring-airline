-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Мар 23 2018 г., 20:02
-- Версия сервера: 5.6.38
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `airline`
--

-- --------------------------------------------------------

--
-- Структура таблицы `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `position` varchar(30) NOT NULL,
  `foreignKey` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Структура таблицы `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `email` varchar(30) NOT NULL,
  `phone` varchar(30) NOT NULL,
  `foreignKey` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `cookies`
--

CREATE TABLE `cookies` (
  `uuid` varchar(129) NOT NULL,
  `id` int(11) NOT NULL,
  `userType` enum('ADMIN','CLIENT','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `day_flight`
--

CREATE TABLE `day_flight` (
  `id` int(11) NOT NULL,
  `day` date NOT NULL,
  `flightId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `flight`
--

CREATE TABLE `flight` (
  `id` int(11) NOT NULL,
  `flightName` varchar(50) NOT NULL,
  `planeName` varchar(50) NOT NULL,
  `fromTown` varchar(50) NOT NULL,
  `toTown` varchar(50) NOT NULL,
  `start` time NOT NULL,
  `duration` time NOT NULL,
  `priceBusiness` int(11) NOT NULL,
  `priceEconomy` int(11) NOT NULL,
  `approved` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Структура таблицы `plane`
--

CREATE TABLE `plane` (
  `name` varchar(40) NOT NULL,
  `bussinesRows` int(11) NOT NULL,
  `economyRows` int(11) NOT NULL,
  `placesInBusinessRow` int(11) NOT NULL,
  `placesInEconomyRow` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `plane`
--

INSERT INTO `plane` (`name`, `bussinesRows`, `economyRows`, `placesInBusinessRow`, `placesInEconomyRow`) VALUES
('Airbus  A319', 20, 104, 4, 4);

-- --------------------------------------------------------

--
-- Структура таблицы `schedule`
--

CREATE TABLE `schedule` (
  `id` int(11) NOT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `flightId` int(11) NOT NULL,
  `period` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `patronymic` varchar(30) DEFAULT NULL,
  `userType` varchar(20) NOT NULL,
  `login` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `admin_ibfk_1` (`foreignKey`);

--
-- Индексы таблицы `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`),
  ADD KEY `client_ibfk_1` (`foreignKey`);

--
-- Индексы таблицы `cookies`
--
ALTER TABLE `cookies`
  ADD UNIQUE KEY `uuid` (`uuid`);

--
-- Индексы таблицы `day_flight`
--
ALTER TABLE `day_flight`
  ADD PRIMARY KEY (`id`),
  ADD KEY `day_flight_ibfk_1` (`flightId`);

--
-- Индексы таблицы `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `flightName` (`flightName`),
  ADD KEY `planeName` (`planeName`);

--
-- Индексы таблицы `plane`
--
ALTER TABLE `plane`
  ADD UNIQUE KEY `name` (`name`);

--
-- Индексы таблицы `schedule`
--
ALTER TABLE `schedule`
  ADD PRIMARY KEY (`id`),
  ADD KEY `schedule_ibfk_1` (`flightId`);

--
-- Индексы таблицы `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT для таблицы `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT для таблицы `day_flight`
--
ALTER TABLE `day_flight`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1129;

--
-- AUTO_INCREMENT для таблицы `flight`
--
ALTER TABLE `flight`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT для таблицы `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`foreignKey`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`foreignKey`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Ограничения внешнего ключа таблицы `day_flight`
--
ALTER TABLE `day_flight`
  ADD CONSTRAINT `day_flight_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`planeName`) REFERENCES `plane` (`name`);

--
-- Ограничения внешнего ключа таблицы `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
