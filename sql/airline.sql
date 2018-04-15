

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
('Antigua and Barbuda', 'AG'),
('Ангуила', 'AI'),
('Албания', 'AL'),
('Армения', 'AM'),
('Нидерландские Антильские острова', 'AN'),
('Ангола', 'AO'),
('Antarctica', 'AQ'),
('Аргентина', 'AR'),
('American Samoa', 'AS'),
('Австрия', 'AT'),
('Австралия', 'AU'),
('Аруба', 'AW'),
('Åland Islands', 'AX'),
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
('Saint Barthélemy', 'BL'),
('Бермудские острова', 'BM'),
('Бруней', 'BN'),
('Боливия', 'BO'),
('Bonaire, Sint Eustatius and Saba', 'BQ'),
('Бразилия', 'BR'),
('Багамские острова', 'BS'),
('Бутан', 'BT'),
('Bouvet Island', 'BV'),
('Ботсвана', 'BW'),
('Беларусь', 'BY'),
('Белиз', 'BZ'),
('Канада', 'CA'),
('Cocos Islands', 'CC'),
('The Democratic Republic Of Congo', 'CD'),
('Центральноафриканская Республика', 'CF'),
('Конго', 'CG'),
('Швейцария', 'CH'),
('Кот-д\'Ивуар', 'CI'),
('Cook Islands', 'CK'),
('Чили', 'CL'),
('Камерун', 'CM'),
('Китай', 'CN'),
('Колумбия', 'CO'),
('Коста-Рика', 'CR'),
('Куба', 'CU'),
('Зеленый Мыс', 'CV'),
('Curaçao', 'CW'),
('Christmas Island', 'CX'),
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
('Falkland Islands', 'FK'),
('Микронезия', 'FM'),
('Faroe Islands', 'FO'),
('Франция', 'FR'),
('Габон', 'GA'),
('Соединенное Королевство', 'GB'),
('Grenada', 'GD'),
('Грузия', 'GE'),
('Французская Гвинея', 'GF'),
('Guernsey', 'GG'),
('Гана', 'GH'),
('Gibraltar', 'GI'),
('Greenland', 'GL'),
('Гамбия', 'GM'),
('Гвинея', 'GN'),
('Гваделупа', 'GP'),
('Экваториальная Гвинея', 'GQ'),
('Греция', 'GR'),
('South Georgia And The South Sandwich Islands', 'GS'),
('Гватемала', 'GT'),
('Guam', 'GU'),
('Гвинея-Бисау', 'GW'),
('Гайана', 'GY'),
('Гонконг', 'HK'),
('Heard Island And McDonald Islands', 'HM'),
('Гондурас', 'HN'),
('Хорватия', 'HR'),
('Гаити', 'HT'),
('Венгрия', 'HU'),
('Индонезия', 'ID'),
('Ирландия', 'IE'),
('Израиль', 'IL'),
('Isle Of Man', 'IM'),
('Индия', 'IN'),
('British Indian Ocean Territory', 'IO'),
('Ирак', 'IQ'),
('Иран', 'IR'),
('Исландия', 'IS'),
('Италия', 'IT'),
('Jersey', 'JE'),
('Ямайка', 'JM'),
('Иордания', 'JO'),
('Япония', 'JP'),
('Кения', 'KE'),
('Киргизстан', 'KG'),
('Камбоджа', 'KH'),
('Кирибати', 'KI'),
('Коморос', 'KM'),
('Saint Kitts And Nevis', 'KN'),
('Северная Корея', 'KP'),
('Южная Корея', 'KR'),
('Кувейт', 'KW'),
('Cayman Islands', 'KY'),
('Казахстан', 'KZ'),
('Лаос', 'LA'),
('Ливан', 'LB'),
('Saint Lucia', 'LC'),
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
('Montenegro', 'ME'),
('Saint Martin', 'MF'),
('Мадагаскар', 'MG'),
('Marshall Islands', 'MH'),
('Македония', 'MK'),
('Мали', 'ML'),
('Мьянма', 'MM'),
('Монголия', 'MN'),
('Macao', 'MO'),
('Northern Mariana Islands', 'MP'),
('Мартиника', 'MQ'),
('Мавритания', 'MR'),
('Монтсерат', 'MS'),
('Мальта', 'MT'),
('Маврикий', 'MU'),
('Maldives', 'MV'),
('Malawi', 'MW'),
('Мексика', 'MX'),
('Малайзия', 'MY'),
('Мозамбик', 'MZ'),
('Намибия', 'NA'),
('Новая Каледония', 'NC'),
('Нигер', 'NE'),
('Norfolk Island', 'NF'),
('Нигерия', 'NG'),
('Никарагуа', 'NI'),
('Нидерланды', 'NL'),
('Норвегия', 'NO'),
('Непал', 'NP'),
('Nauru', 'NR'),
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
('Saint Pierre And Miquelon', 'PM'),
('Pitcairn', 'PN'),
('Пуэрто-Рико', 'PR'),
('Palestine', 'PS'),
('Португалия', 'PT'),
('Palau', 'PW'),
('Парагвай', 'PY'),
('Катар', 'QA'),
('Reunion', 'RE'),
('Румыния', 'RO'),
('Serbia', 'RS'),
('Россия', 'RU'),
('Руанда', 'RW'),
('Саудовская Аравия', 'SA'),
('Solomon Islands', 'SB'),
('Сейшельские Острова', 'SC'),
('Судан', 'SD'),
('Швеция', 'SE'),
('Сингапур', 'SG'),
('Saint Helena', 'SH'),
('Словения', 'SI'),
('Svalbard And Jan Mayen', 'SJ'),
('Словакия', 'SK'),
('Сьерра-Леоне', 'SL'),
('San Marino', 'SM'),
('Сенегал', 'SN'),
('Сомали', 'SO'),
('Суринам', 'SR'),
('South Sudan', 'SS'),
('Sao Tome And Principe', 'ST'),
('Сальвадор', 'SV'),
('Sint Maarten (Dutch part)', 'SX'),
('Сирия', 'SY'),
('Свазиленд', 'SZ'),
('Turks And Caicos Islands', 'TC'),
('Чад', 'TD'),
('французские южные территории', 'TF'),
('Того', 'TG'),
('Таиланд', 'TH'),
('Таджикистан', 'TJ'),
('Токелау', 'TK'),
('Timor-Leste', 'TL'),
('Туркменистан', 'TM'),
('Тунис', 'TN'),
('Тонга', 'TO'),
('Турция', 'TR'),
('Тринидад и Тобаго', 'TT'),
('Tuvalu', 'TV'),
('Тайвань', 'TW'),
('Танзания', 'TZ'),
('Украина', 'UA'),
('Уганда', 'UG'),
('United States Minor Outlying Islands', 'UM'),
('Соединенные Штаты', 'US'),
('Уругвай', 'UY'),
('Узбекистан', 'UZ'),
('Ватикан', 'VA'),
('Saint Vincent And The Grenadines', 'VC'),
('Венесуэла', 'VE'),
('Британские Виргинские острова', 'VG'),
('Виргинские острова Соединенных Штатов', 'VI'),
('Вьетнам', 'VN'),
('Вануату', 'VU'),
('Wallis And Futuna', 'WF'),
('Samoa', 'WS'),
('Йемен', 'YE'),
('Майотте', 'YT'),
('Южная Африка', 'ZA'),
('Замбия', 'ZM'),
('Зимбабве', 'ZW');

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
-- Структура таблицы `orders`
--

CREATE TABLE `orders` (
  `orderId` int(11) NOT NULL,
  `flightId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `totalPrice` int(11) NOT NULL,
  `date` date NOT NULL
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
  `place` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- Структура таблицы `places`
--

CREATE TABLE `places` (
  `place` varchar(10) NOT NULL,
  `row` int(10) NOT NULL,
  `date` date NOT NULL,
  `flightId` int(11) NOT NULL,
  `free` tinyint(1) NOT NULL DEFAULT '1',
  `type` enum('BUSINESS','ECONOMY','','') NOT NULL
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
('Airbus  A319', 5, 16, 4, 6);

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
-- Индексы таблицы `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderId`),
  ADD KEY `orders_ibfk_1` (`flightId`),
  ADD KEY `orders_ibfk_2` (`userId`);

--
-- Индексы таблицы `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`ticket`),
  ADD KEY `passenger_ibfk_1` (`orderId`);

--
-- Индексы таблицы `places`
--
ALTER TABLE `places`
  ADD KEY `flightId` (`flightId`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT для таблицы `day_flight`
--
ALTER TABLE `day_flight`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT для таблицы `flight`
--
ALTER TABLE `flight`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT для таблицы `orders`
--
ALTER TABLE `orders`
  MODIFY `orderId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT для таблицы `passenger`
--
ALTER TABLE `passenger`
  MODIFY `ticket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT для таблицы `schedule`
--
ALTER TABLE `schedule`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

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
-- Ограничения внешнего ключа таблицы `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `passenger`
--
ALTER TABLE `passenger`
  ADD CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `orders` (`orderId`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `places`
--
ALTER TABLE `places`
  ADD CONSTRAINT `places_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE;

--
-- Ограничения внешнего ключа таблицы `schedule`
--
ALTER TABLE `schedule`
  ADD CONSTRAINT `schedule_ibfk_1` FOREIGN KEY (`flightId`) REFERENCES `flight` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
