-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Май 15 2018 г., 19:52
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

DROP DATABASE IF EXISTS airline;
CREATE DATABASE airline;
USE airline;


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
-- Структура таблицы `countries`
--

CREATE TABLE `countries` (
  `name` varchar(60) NOT NULL,
  `iso3166` varchar(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `countries`
--

INSERT INTO `countries` (`name`, `iso3166`) VALUES
('Андорра', 'AD'),
('Объединенные Арабские Эмираты', 'AE'),
('Афганистан', 'AF'),
('Антигуа и Барбуда', 'AG'),
('Ангуила', 'AI'),
('Албания', 'AL'),
('Армения', 'AM'),
('Нидерландские Антильские острова', 'AN'),
('Ангола', 'AO'),
('Антарктида', 'AQ'),
('Аргентина', 'AR'),
('Американское Самоа', 'AS'),
('Австрия', 'AT'),
('Австралия', 'AU'),
('Аруба', 'AW'),
('Аландские острова', 'AX'),
('Азербайджан', 'AZ'),
('Босния и Герцеговина', 'BA'),
('Барбадос', 'BB'),
('Бангладеш', 'BD'),
('Бельгия', 'BE'),
('Буркина-Фасо', 'BF'),
('Болгария', 'BG'),
('Бахрейн', 'BH'),
('Бурунди', 'BI'),
('Бенин', 'BJ'),
('Сен-Бартелеми', 'BL'),
('Бермудские острова', 'BM'),
('Бруней', 'BN'),
('Боливия', 'BO'),
('Бонэйр, Синт-Эстатиус и Саба', 'BQ'),
('Бразилия', 'BR'),
('Багамские острова', 'BS'),
('Бутан', 'BT'),
('Остров Буве', 'BV'),
('Ботсвана', 'BW'),
('Беларусь', 'BY'),
('Белиз', 'BZ'),
('Канада', 'CA'),
('Кокосовые острова', 'CC'),
('Демократическая Республика Конго', 'CD'),
('Центральноафриканская Республика', 'CF'),
('Конго', 'CG'),
('Швейцария', 'CH'),
('Кот-д’Ивуар', 'CI'),
('Острова Кука', 'CK'),
('Чили', 'CL'),
('Камерун', 'CM'),
('Китай', 'CN'),
('Колумбия', 'CO'),
('Коста-Рика', 'CR'),
('Куба', 'CU'),
('Зеленый Мыс', 'CV'),
('Кюрасао', 'CW'),
('Остров Рождества', 'CX'),
('Кипр', 'CY'),
('Чехия', 'CZ'),
('Германия', 'DE'),
('Джибути', 'DJ'),
('Дания', 'DK'),
('Доминика', 'DM'),
('Доминиканская Республика', 'DO'),
('Алжир', 'DZ'),
('Эквадор', 'EC'),
('Эстония', 'EE'),
('Египт', 'EG'),
('Западная Сахара', 'EH'),
('Эритрея', 'ER'),
('Испания', 'ES'),
('Эфиопия', 'ET'),
('Финляндия', 'FI'),
('Фиджи', 'FJ'),
('Фолклендские острова', 'FK'),
('Микронезия', 'FM'),
('Фареры', 'FO'),
('Франция', 'FR'),
('Габон', 'GA'),
('Соединенное Королевство', 'GB'),
('Гренада', 'GD'),
('Грузия', 'GE'),
('Французская Гвинея', 'GF'),
('Гернси', 'GG'),
('Гана', 'GH'),
('Гибралтар', 'GI'),
('Гренландия', 'GL'),
('Гамбия', 'GM'),
('Гвинея', 'GN'),
('Гваделупа', 'GP'),
('Экваториальная Гвинея', 'GQ'),
('Греция', 'GR'),
('Южная Георгия и Южные Сандвичевы Острова', 'GS'),
('Гватемала', 'GT'),
('Гуам', 'GU'),
('Гвинея-Бисау', 'GW'),
('Гайана', 'GY'),
('Гонконг', 'HK'),
('Херд и Макдональд', 'HM'),
('Гондурас', 'HN'),
('Хорватия', 'HR'),
('Гаити', 'HT'),
('Венгрия', 'HU'),
('Индонезия', 'ID'),
('Ирландия', 'IE'),
('Израиль', 'IL'),
('Остров Мэн', 'IM'),
('Индия', 'IN'),
('Британская территория в Индийском океане', 'IO'),
('Ирак', 'IQ'),
('Иран', 'IR'),
('Исландия', 'IS'),
('Италия', 'IT'),
('Джерси', 'JE'),
('Ямайка', 'JM'),
('Иордания', 'JO'),
('Япония', 'JP'),
('Кения', 'KE'),
('Киргизстан', 'KG'),
('Камбоджа', 'KH'),
('Кирибати', 'KI'),
('Коморос', 'KM'),
('Сент-Китс и Невис', 'KN'),
('Северная Корея', 'KP'),
('Южная Корея', 'KR'),
('Кувейт', 'KW'),
('Острова Кайман', 'KY'),
('Казахстан', 'KZ'),
('Лаос', 'LA'),
('Ливан', 'LB'),
('Сент-Люсия', 'LC'),
('Лихтенштейн', 'LI'),
('Шри-Ланка', 'LK'),
('Либерия', 'LR'),
('Лесото', 'LS'),
('Литва', 'LT'),
('Люксембург', 'LU'),
('Латвия', 'LV'),
('Ливия', 'LY'),
('Марокко', 'MA'),
('Монако', 'MC'),
('Молдова', 'MD'),
('Черногория', 'ME'),
('Сен-Мартен', 'MF'),
('Мадагаскар', 'MG'),
('Маршалловы Острова', 'MH'),
('Македония', 'MK'),
('Мали', 'ML'),
('Мьянма', 'MM'),
('Монголия', 'MN'),
('Macao', 'MO'),
('Северные Марианские Острова', 'MP'),
('Мартиника', 'MQ'),
('Мавритания', 'MR'),
('Монтсерат', 'MS'),
('Мальта', 'MT'),
('Маврикий', 'MU'),
('Мальдивы', 'MV'),
('Малави', 'MW'),
('Мексика', 'MX'),
('Малайзия', 'MY'),
('Мозамбик', 'MZ'),
('Намибия', 'NA'),
('Новая Каледония', 'NC'),
('Нигер', 'NE'),
('Остров Норфолк', 'NF'),
('Нигерия', 'NG'),
('Никарагуа', 'NI'),
('Нидерланды', 'NL'),
('Норвегия', 'NO'),
('Непал', 'NP'),
('Науру', 'NR'),
('Нию', 'NU'),
('Новая Зеландия', 'NZ'),
('Оман', 'OM'),
('Панама', 'PA'),
('Перу', 'PE'),
('Французская Полинезия', 'PF'),
('Папуа - Новая Гвинея', 'PG'),
('Филиппины', 'PH'),
('Пакистан', 'PK'),
('Польша', 'PL'),
('Сен-Пьер и Микелон', 'PM'),
('Острова Питкэрн', 'PN'),
('Пуэрто-Рико', 'PR'),
('Государство Палестина', 'PS'),
('Португалия', 'PT'),
('Палау', 'PW'),
('Парагвай', 'PY'),
('Катар', 'QA'),
('Реюньон', 'RE'),
('Румыния', 'RO'),
('Сербия', 'RS'),
('Россия', 'RU'),
('Руанда', 'RW'),
('Саудовская Аравия', 'SA'),
('Соломоновы Острова', 'SB'),
('Сейшельские Острова', 'SC'),
('Судан', 'SD'),
('Швеция', 'SE'),
('Сингапур', 'SG'),
('Острова Святой Елены, Вознесения и Тристан-да-Кунья', 'SH'),
('Словения', 'SI'),
('Шпицберген и Ян-Майен', 'SJ'),
('Словакия', 'SK'),
('Сьерра-Леоне', 'SL'),
('Сан-Марино', 'SM'),
('Сенегал', 'SN'),
('Сомали', 'SO'),
('Суринам', 'SR'),
('Южный Судан', 'SS'),
('Сан-Томе и Принсипи', 'ST'),
('Сальвадор', 'SV'),
('Синт-Мартен', 'SX'),
('Сирия', 'SY'),
('Свазиленд', 'SZ'),
('Тёркс и Кайкос', 'TC'),
('Чад', 'TD'),
('французские южные территории', 'TF'),
('Того', 'TG'),
('Таиланд', 'TH'),
('Таджикистан', 'TJ'),
('Токелау', 'TK'),
('Восточный Тимор', 'TL'),
('Туркменистан', 'TM'),
('Тунис', 'TN'),
('Тонга', 'TO'),
('Турция', 'TR'),
('Тринидад и Тобаго', 'TT'),
('Тувалу', 'TV'),
('Тайвань', 'TW'),
('Танзания', 'TZ'),
('Украина', 'UA'),
('Уганда', 'UG'),
('Внешние малые острова (США)', 'UM'),
('Соединенные Штаты', 'US'),
('Уругвай', 'UY'),
('Узбекистан', 'UZ'),
('Ватикан', 'VA'),
('Сент-Винсент и Гренадины', 'VC'),
('Венесуэла', 'VE'),
('Британские Виргинские острова', 'VG'),
('Виргинские острова Соединенных Штатов', 'VI'),
('Вьетнам', 'VN'),
('Вануату', 'VU'),
('Уоллис и Футуна', 'WF'),
('Самоа', 'WS'),
('Йемен', 'YE'),
('Майотте', 'YT'),
('Южная Африка', 'ZA'),
('Замбия', 'ZM'),
('Зимбабве', 'ZW');

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
-- Структура таблицы `flight_date`
--

CREATE TABLE `flight_date` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `flightId` int(11) NOT NULL,
  `freeEconomyPlaces` int(11) NOT NULL,
  `freeBusinessPlaces` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `flight_date_place`
--

CREATE TABLE `flight_date_place` (
  `flightDateId` int(11) NOT NULL,
  `place` varchar(50) NOT NULL,
  `row` int(10) NOT NULL,
  `free` tinyint(1) NOT NULL DEFAULT '1',
  `type` enum('BUSINESS','ECONOMY','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `flightDateId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `passenger`
--

CREATE TABLE `passenger` (
  `ticket` int(11) NOT NULL,
  `orderId` int(11) NOT NULL,
  `firstName` varchar(60) NOT NULL,
  `lastName` varchar(60) NOT NULL,
  `nationality` varchar(60) NOT NULL,
  `passport` varchar(60) NOT NULL,
  `price` int(11) NOT NULL,
  `orderClass` enum('BUSINESS','ECONOMY','','') NOT NULL,
  `place` varchar(11) NOT NULL,
  `row` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Структура таблицы `plane`
--

CREATE TABLE `plane` (
  `name` varchar(40) NOT NULL,
  `businessRows` int(11) NOT NULL,
  `economyRows` int(11) NOT NULL,
  `placesInBusinessRow` int(11) NOT NULL,
  `placesInEconomyRow` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `plane`
--

INSERT INTO `plane` (`name`, `businessRows`, `economyRows`, `placesInBusinessRow`, `placesInEconomyRow`) VALUES
('Аэрокарета', 2, 0, 2, 0),
('Боинг', 4, 4, 6, 6),
('Кукурузник', 2, 4, 2, 4),
('Летающая антилопа', 0, 1, 0, 4),
('Пепелац', 2, 2, 2, 2);

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
  `userType` enum('ADMIN','CLIENT','','') NOT NULL,
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
  ADD UNIQUE KEY `uuid` (`uuid`),
  ADD KEY `cookies_ibfk_1` (`id`);

--
-- Индексы таблицы `countries`
--
ALTER TABLE `countries`
  ADD UNIQUE KEY `iso3166` (`iso3166`);

--
-- Индексы таблицы `flight`
--
ALTER TABLE `flight`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `flightName` (`flightName`),
  ADD KEY `planeName` (`planeName`);

--
-- Индексы таблицы `flight_date`
--
ALTER TABLE `flight_date`
  ADD PRIMARY KEY (`id`),
  ADD KEY `flight_date_ibfk_1` (`flightId`);

--
-- Индексы таблицы `flight_date_place`
--
ALTER TABLE `flight_date_place`
  ADD KEY `flight_date_place_ibfk_1` (`flightDateId`);

--
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `orders_ibfk_2` (`userId`),
  ADD KEY `orders_ibfk_3` (`flightDateId`);

--
-- Индексы таблицы `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`ticket`),
  ADD KEY `passenger_ibfk_1` (`orderId`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT для таблицы `flight`
--
ALTER TABLE `flight`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `flight_date`
--
ALTER TABLE `flight_date`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=165;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `passenger`
--
ALTER TABLE `passenger`
  MODIFY `ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT для таблицы `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
-- Ограничения внешнего ключа таблицы `cookies`
--
ALTER TABLE `cookies`
  ADD CONSTRAINT `cookies_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `flight`
--
ALTER TABLE `flight`
  ADD CONSTRAINT `flight_ibfk_1` FOREIGN KEY (`planeName`) REFERENCES `plane` (`name`);

--
-- Ограничения внешнего ключа таблицы `flight_date`
--
ALTER TABLE `flight_date`
  ADD CONSTRAINT `flight_date_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `flight_date_place`
--
ALTER TABLE `flight_date_place`
  ADD CONSTRAINT `flight_date_place_ibfk_1` FOREIGN KEY (`flightDateId`) REFERENCES `flight_date` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_3` FOREIGN KEY (`flightDateId`) REFERENCES `flight_date` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `passenger`
--
ALTER TABLE `passenger`
  ADD CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
