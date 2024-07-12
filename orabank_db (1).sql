-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 12 juil. 2024 à 10:41
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `orabank_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `alias`
--

CREATE TABLE `alias` (
  `id` int(11) NOT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `date_de_creation` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `statut` bit(1) DEFAULT NULL,
  `type` enum('ParAdresse','ParTelephone') DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `compte_bancaire_id` int(11) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `alias`
--

INSERT INTO `alias` (`id`, `alias`, `date_de_creation`, `description`, `statut`, `type`, `client_id`, `compte_bancaire_id`, `telephone`) VALUES
(13, '57b41d6b-ea0c-496f-85ff-41ab025dc419', '2024-05-23 15:50:20.000000', 'myAlias', b'1', 'ParTelephone', 2, 1, '55555555');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id` int(11) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `date_de_fin_de_contrat` datetime(6) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `pays` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `adresse`, `date_de_fin_de_contrat`, `nom`, `pays`, `telephone`) VALUES
(1, '123 Main Street', '2024-12-31 00:00:00.000000', 'John Doe', 'USA', '555-1234'),
(2, '36 r k b', '2024-04-25 10:26:43.000000', 'yassine', 'tunisie', '25814763');

-- --------------------------------------------------------

--
-- Structure de la table `compte_bancaire`
--

CREATE TABLE `compte_bancaire` (
  `id` int(11) NOT NULL,
  `date_de_creation` datetime(6) DEFAULT NULL,
  `libelle` varchar(255) DEFAULT NULL,
  `numero_compte` varchar(255) DEFAULT NULL,
  `rib` varchar(255) DEFAULT NULL,
  `solde` float NOT NULL,
  `statut` bit(1) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `compte_bancaire`
--

INSERT INTO `compte_bancaire` (`id`, `date_de_creation`, `libelle`, `numero_compte`, `rib`, `solde`, `statut`, `client_id`, `iban`) VALUES
(1, '2017-05-12 10:52:00.000000', 'compte bfi 1', '123456789', 'bfi_123456789', 99700, b'1', 2, NULL),
(2, '2023-05-25 17:03:49.000000', 'bfi 2', '987654321', 'bfi_258147396', 100300, b'1', 2, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `compte_bancaire_id` int(11) DEFAULT NULL,
  `utilisateur_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `contact`
--

INSERT INTO `contact` (`id`, `nom`, `compte_bancaire_id`, `utilisateur_id`) VALUES
(1, 'Med', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `demande_paiement`
--

CREATE TABLE `demande_paiement` (
  `id` int(11) NOT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `date_modif` datetime(6) DEFAULT NULL,
  `etat_demande` enum('Traité','EnAttente') DEFAULT NULL,
  `montant` float NOT NULL,
  `destinataire_id` int(11) DEFAULT NULL,
  `expediteur_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `demande_paiement`
--

INSERT INTO `demande_paiement` (`id`, `date_creation`, `date_modif`, `etat_demande`, `montant`, `destinataire_id`, `expediteur_id`, `description`) VALUES
(1, '2024-07-11 12:59:34.000000', NULL, 'EnAttente', 100, 13, 13, 'cc');

-- --------------------------------------------------------

--
-- Structure de la table `devise`
--

CREATE TABLE `devise` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `symbole` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `devise`
--

INSERT INTO `devise` (`id`, `nom`, `code`, `symbole`) VALUES
(1, 'Leke', 'ALL', 'Lek'),
(2, 'Dollars', 'USD', '$'),
(3, 'Afghanis', 'AFN', '؋'),
(4, 'Pesos', 'ARS', '$'),
(5, 'Guilders', 'AWG', 'ƒ'),
(6, 'Dollars', 'AUD', '$'),
(7, 'New Manats', 'AZN', 'ман'),
(8, 'Dollars', 'BSD', '$'),
(9, 'Dollars', 'BBD', '$'),
(10, 'Rubles', 'BYR', 'p.'),
(11, 'Euro', 'EUR', '€'),
(12, 'Dollars', 'BZD', 'BZ$'),
(13, 'Dollars', 'BMD', '$'),
(14, 'Bolivianos', 'BOB', '$b'),
(15, 'Convertible Marka', 'BAM', 'KM'),
(16, 'Pula', 'BWP', 'P'),
(17, 'Leva', 'BGN', 'лв'),
(18, 'Reais', 'BRL', 'R$'),
(19, 'Pounds', 'GBP', '£'),
(20, 'Dollars', 'BND', '$'),
(21, 'Riels', 'KHR', '៛'),
(22, 'Dollars', 'CAD', '$'),
(23, 'Dollars', 'KYD', '$'),
(24, 'Pesos', 'CLP', '$'),
(25, 'Yuan Renminbi', 'CNY', '¥'),
(26, 'Pesos', 'COP', '$'),
(27, 'Colón', 'CRC', '₡'),
(28, 'Kuna', 'HRK', 'kn'),
(29, 'Pesos', 'CUP', '₱'),
(30, 'Koruny', 'CZK', 'Kč'),
(31, 'Kroner', 'DKK', 'kr'),
(32, 'Pesos', 'DOP ', 'RD$'),
(33, 'Dollars', 'XCD', '$'),
(34, 'Pounds', 'EGP', '£'),
(35, 'Colones', 'SVC', '$'),
(36, 'Pounds', 'FKP', '£'),
(37, 'Dollars', 'FJD', '$'),
(38, 'Cedis', 'GHC', '¢'),
(39, 'Pounds', 'GIP', '£'),
(40, 'Quetzales', 'GTQ', 'Q'),
(41, 'Pounds', 'GGP', '£'),
(42, 'Dollars', 'GYD', '$'),
(43, 'Lempiras', 'HNL', 'L'),
(44, 'Dollars', 'HKD', '$'),
(45, 'Forint', 'HUF', 'Ft'),
(46, 'Kronur', 'ISK', 'kr'),
(47, 'Rupees', 'INR', 'Rp'),
(48, 'Rupiahs', 'IDR', 'Rp'),
(49, 'Rials', 'IRR', '﷼'),
(50, 'Pounds', 'IMP', '£'),
(51, 'New Shekels', 'ILS', '₪'),
(52, 'Dollars', 'JMD', 'J$'),
(53, 'Yen', 'JPY', '¥'),
(54, 'Pounds', 'JEP', '£'),
(55, 'Tenge', 'KZT', 'лв'),
(56, 'Won', 'KPW', '₩'),
(57, 'Won', 'KRW', '₩'),
(58, 'Soms', 'KGS', 'лв'),
(59, 'Kips', 'LAK', '₭'),
(60, 'Lati', 'LVL', 'Ls'),
(61, 'Pounds', 'LBP', '£'),
(62, 'Dollars', 'LRD', '$'),
(63, 'Switzerland Francs', 'CHF', 'CHF'),
(64, 'Litai', 'LTL', 'Lt'),
(65, 'Denars', 'MKD', 'ден'),
(66, 'Ringgits', 'MYR', 'RM'),
(67, 'Rupees', 'MUR', '₨'),
(68, 'Pesos', 'MXN', '$'),
(69, 'Tugriks', 'MNT', '₮'),
(70, 'Meticais', 'MZN', 'MT'),
(71, 'Dollars', 'NAD', '$'),
(72, 'Rupees', 'NPR', '₨'),
(73, 'Guilders', 'ANG', 'ƒ'),
(74, 'Dollars', 'NZD', '$'),
(75, 'Cordobas', 'NIO', 'C$'),
(76, 'Nairas', 'NGN', '₦'),
(77, 'Krone', 'NOK', 'kr'),
(78, 'Rials', 'OMR', '﷼'),
(79, 'Rupees', 'PKR', '₨'),
(80, 'Balboa', 'PAB', 'B/.'),
(81, 'Guarani', 'PYG', 'Gs'),
(82, 'Nuevos Soles', 'PEN', 'S/.'),
(83, 'Pesos', 'PHP', 'Php'),
(84, 'Zlotych', 'PLN', 'zł'),
(85, 'Rials', 'QAR', '﷼'),
(86, 'New Lei', 'RON', 'lei'),
(87, 'Rubles', 'RUB', 'руб'),
(88, 'Pounds', 'SHP', '£'),
(89, 'Riyals', 'SAR', '﷼'),
(90, 'Dinars', 'RSD', 'Дин.'),
(91, 'Rupees', 'SCR', '₨'),
(92, 'Dollars', 'SGD', '$'),
(93, 'Dollars', 'SBD', '$'),
(94, 'Shillings', 'SOS', 'S'),
(95, 'Rand', 'ZAR', 'R'),
(96, 'Rupees', 'LKR', '₨'),
(97, 'Kronor', 'SEK', 'kr'),
(98, 'Dollars', 'SRD', '$'),
(99, 'Pounds', 'SYP', '£'),
(100, 'New Dollars', 'TWD', 'NT$'),
(101, 'Baht', 'THB', '฿'),
(102, 'Dollars', 'TTD', 'TT$'),
(103, 'Lira', 'TRY', '₺'),
(104, 'Liras', 'TRL', '£'),
(105, 'Dollars', 'TVD', '$'),
(106, 'Hryvnia', 'UAH', '₴'),
(107, 'Pesos', 'UYU', '$U'),
(108, 'Sums', 'UZS', 'лв'),
(109, 'Bolivares Fuertes', 'VEF', 'Bs'),
(110, 'Dong', 'VND', '₫'),
(111, 'Rials', 'YER', '﷼'),
(112, 'Zimbabwe Dollars', 'ZWD', 'Z$'),
(113, 'Rupees', 'INR', '₹');

-- --------------------------------------------------------

--
-- Structure de la table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `destinataire_alias` varchar(255) DEFAULT NULL,
  `expediteur_alias` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `type` enum('success','info') DEFAULT NULL,
  `demande_paiement_id` int(11) DEFAULT NULL,
  `transfert_id` int(11) DEFAULT NULL,
  `lu` bit(1) DEFAULT NULL,
  `status` enum('lu','nonLu') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `notification`
--

INSERT INTO `notification` (`id`, `date`, `destinataire_alias`, `expediteur_alias`, `message`, `titre`, `type`, `demande_paiement_id`, `transfert_id`, `lu`, `status`) VALUES
(31, '2024-07-10 15:20:20.000000', '57b41d6b-ea0c-496f-85ff-41ab025dc419', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 au compte 57b41d6b-ea0c-496f-85ff-41ab025dc419 a été créé avec succés', 'Un nouveau transfert a été créé', 'info', NULL, 67, b'0', 'lu'),
(32, '2024-07-10 15:20:20.000000', '57b41d6b-ea0c-496f-85ff-41ab025dc419', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 de la part du compte 123456789 a été créé avec succés', 'Un nouveau transfert a été créé', NULL, NULL, 67, b'0', 'lu'),
(33, '2024-07-10 22:25:00.000000', '57b41d6b-ea0c-496f-85ff-41ab025dc419', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 au compte 57b41d6b-ea0c-496f-85ff-41ab025dc419 a été créé avec succés', 'Un nouveau transfert a été créé', 'info', NULL, 68, b'0', 'lu'),
(34, '2024-07-10 22:25:00.000000', '57b41d6b-ea0c-496f-85ff-41ab025dc419', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 de la part du compte 123456789 a été créé avec succés', 'Un nouveau transfert a été créé', NULL, NULL, 68, b'0', 'lu'),
(35, '2024-07-10 23:30:27.000000', '', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 au compte 57b41d6b-ea0c-496f-85ff-41ab025dc419 a été créé avec succés', 'Un nouveau transfert a été créé', 'info', NULL, 70, b'0', 'lu'),
(36, '2024-07-10 23:30:27.000000', '', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 de la part du compte 123 a été créé avec succés', 'Un nouveau transfert a été créé', NULL, NULL, 70, b'0', 'nonLu'),
(37, '2024-07-10 23:31:33.000000', '', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 au compte 57b41d6b-ea0c-496f-85ff-41ab025dc419 a été créé avec succés', 'Un nouveau transfert a été créé', 'info', NULL, 71, b'0', 'lu'),
(38, '2024-07-10 23:31:33.000000', '', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Un nouveau transfert de 100.0 de la part du compte anh a été créé avec succés', 'Un nouveau transfert a été créé', NULL, NULL, 71, b'0', 'nonLu'),
(39, '2024-07-11 12:59:36.000000', '57b41d6b-ea0c-496f-85ff-41ab025dc419', '57b41d6b-ea0c-496f-85ff-41ab025dc419', 'Une nouvelle demande de paiement de 100.0 de la part de   57b41d6b-ea0c-496f-85ff-41ab025dc419 a été créé avec succés', 'Une nouvelle demande de paiement a été créé', 'info', 1, NULL, b'0', 'nonLu'),
(40, '2024-07-11 12:59:36.000000', NULL, NULL, NULL, 'Un nouveau transfert a été créé', NULL, 1, NULL, b'0', 'nonLu');

-- --------------------------------------------------------

--
-- Structure de la table `token`
--

CREATE TABLE `token` (
  `id` int(11) NOT NULL,
  `expired` bit(1) NOT NULL,
  `insertion_date` datetime(6) DEFAULT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` enum('BEARER') DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `token`
--

INSERT INTO `token` (`id`, `expired`, `insertion_date`, `revoked`, `token`, `token_type`, `user_id`) VALUES
(41, b'1', '2024-06-30 23:21:21.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MTk4Njg4ODEsImlhdCI6MTcxOTc4MjQ4MX0.Ie4xYlWRP2CKOXbK2FauyjkprSCwKzl6xinngAxzOPw', 'BEARER', 1),
(42, b'1', '2024-06-30 23:23:48.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MTk4NjkwMjgsImlhdCI6MTcxOTc4MjYyOH0.WwEJcH9N2t5OwKowDHUOdIb-wIziqFvHttrZg1CfFcE', 'BEARER', 1),
(43, b'1', '2024-06-30 23:25:20.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MTk4NjkxMjAsImlhdCI6MTcxOTc4MjcyMH0.O09HnUZkyIdgfizEkRg9mXtPmZsN7z9k0cxvO2cq-b4', 'BEARER', 1),
(44, b'1', '2024-06-30 23:26:35.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MTk4NjkxOTUsImlhdCI6MTcxOTc4Mjc5NX0.d7EQkUsaBr2K4gcAw_X8ey0-st-BXKJ79PEPc_T_m2Y', 'BEARER', 1),
(45, b'1', '2024-07-01 23:32:02.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MTk5NTU5MjEsImlhdCI6MTcxOTg2OTUyMX0.lSBvRc4CIrQPpqMLZdhvpOVeYd7-_5eptnq0lQYU0nk', 'BEARER', 1),
(46, b'1', '2024-07-02 12:36:59.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MjAwMDMwMTgsImlhdCI6MTcxOTkxNjYxOH0.0nLu9firNfN4YUk6z8bqWZOc9b_U-6adEzCsbVLH-DE', 'BEARER', 1),
(47, b'1', '2024-07-03 19:49:13.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MjAxMTUzNDksImlhdCI6MTcyMDAyODk0OX0.AoDaVCMp83tAVBsPqHv3OwtFCIy7oSh6igfx7eSu5Is', 'BEARER', 1),
(48, b'1', '2024-07-05 20:24:22.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MjAyOTAyNjIsImlhdCI6MTcyMDIwMzg2Mn0.SwHf6_uVBRGYd5LJm4lf4Ax8bQ47vWN1gqvIcbWBbg8', 'BEARER', 1),
(50, b'1', '2024-07-10 14:15:09.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MjA3MDAxMDksImlhdCI6MTcyMDYxMzcwOX0.hZtZSqoJZvOJF_jeYxhDQWZpFCGAopBbQRaYpn0NgGg', 'BEARER', 1),
(51, b'1', '2024-07-11 14:00:02.000000', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MjA3ODU2MDIsImlhdCI6MTcyMDY5OTIwMn0.a9PsPt7toMerRXqTZ_zM9MbGVcIxlwi1ty35081vfdg', 'BEARER', 1),
(52, b'0', '2024-07-11 14:57:27.000000', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqb2huLmRvZSIsInJvbGUiOiJbUk9MRV9BZG1pbl0iLCJleHAiOjE3MjA3ODkwNDYsImlhdCI6MTcyMDcwMjY0Nn0.wDsDHk4TH3XGqL22N-zevQ72_lDzqxuxNUNvqSHX0H8', 'BEARER', 1);

-- --------------------------------------------------------

--
-- Structure de la table `transfert`
--

CREATE TABLE `transfert` (
  `id` int(11) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `frais_de_transaction` float NOT NULL,
  `montant` float NOT NULL,
  `reference` varchar(255) DEFAULT NULL,
  `alias_id` int(11) DEFAULT NULL,
  `devise_id` int(11) DEFAULT NULL,
  `status_transfert` enum('Entrant','Sortant') DEFAULT NULL,
  `pays_banque` varchar(255) DEFAULT NULL,
  `nom_banque` varchar(255) DEFAULT NULL,
  `nom_institut_fin` varchar(255) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `type_transfert` enum('ParAlias','ParIBAN','ParAutreCompte') DEFAULT NULL,
  `reference_banque` varchar(255) DEFAULT NULL,
  `destinataire_id` int(11) DEFAULT NULL,
  `expediteur_id` int(11) DEFAULT NULL,
  `etat_transfert` enum('Traité','EnAttente') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `transfert`
--

INSERT INTO `transfert` (`id`, `date`, `description`, `frais_de_transaction`, `montant`, `reference`, `alias_id`, `devise_id`, `status_transfert`, `pays_banque`, `nom_banque`, `nom_institut_fin`, `iban`, `type_transfert`, `reference_banque`, `destinataire_id`, `expediteur_id`, `etat_transfert`) VALUES
(22, '2024-06-11 13:14:55.000000', 'dd', 5, 100, 'some-reference', NULL, 1, 'Sortant', 'France', 'atb', NULL, NULL, 'ParIBAN', 'addadz2', NULL, 13, NULL),
(23, '2024-06-11 13:16:26.000000', 'kkk', 5, 4444, 'some-reference', NULL, 1, 'Sortant', 'France', NULL, 'bfi', NULL, 'ParAutreCompte', NULL, NULL, 13, NULL),
(24, '2024-06-24 10:45:06.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(27, '2024-06-24 11:02:47.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(28, '2024-06-24 11:09:19.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(29, '2024-06-24 11:12:20.000000', 'cc', 5, 10000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(30, '2024-06-24 15:14:13.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(31, '2024-06-24 15:15:33.000000', 'cc', 5, 44444, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(32, '2024-06-24 15:24:25.000000', 'cc', 5, 44444, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(33, '2024-06-24 15:29:32.000000', 'cc', 5, 1001, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(34, '2024-06-24 16:13:15.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(35, '2024-06-24 16:14:16.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(36, '2024-06-24 16:32:19.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(37, '2024-06-24 16:36:02.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(38, '2024-06-24 17:40:25.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(39, '2024-06-24 17:45:14.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(40, '2024-06-24 17:49:25.000000', 'cc', 5, 11, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(41, '2024-06-24 17:54:49.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(42, '2024-06-24 17:58:46.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(43, '2024-06-24 19:10:09.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(44, '2024-06-24 19:12:13.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(45, '2024-06-24 19:13:56.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(46, '2024-06-24 19:16:26.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(47, '2024-06-24 19:16:59.000000', 'cc', 5, 10, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(48, '2024-06-27 10:29:53.000000', NULL, 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(49, '2024-06-27 10:45:02.000000', NULL, 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(50, '2024-06-27 16:31:44.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(51, '2024-06-27 16:44:46.000000', 'cc', 5, 10, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(52, '2024-06-28 12:54:34.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(53, '2024-06-28 12:57:49.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(54, '2024-06-28 13:01:43.000000', 'cc', 5, 10, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(55, '2024-06-28 13:02:16.000000', 'cc', 5, 10, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(56, '2024-06-29 15:40:28.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(57, '2024-06-29 15:56:20.000000', 'cc', 5, 10000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(58, '2024-06-29 15:57:12.000000', 'cc', 5, 10000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(59, '2024-06-29 15:58:34.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(60, '2024-06-29 16:00:43.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(61, '2024-06-29 16:00:58.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(62, '2024-06-29 16:01:13.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(63, '2024-06-29 16:02:14.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(64, '2024-06-29 16:15:37.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(65, '2024-06-29 16:17:23.000000', 'cc', 5, 1000, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(66, '2024-06-29 16:18:39.000000', 'cc', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(67, '2024-07-10 15:20:20.000000', '', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(68, '2024-07-10 22:25:00.000000', '', 5, 100, 'some-reference', NULL, 1, 'Sortant', NULL, NULL, NULL, NULL, 'ParAlias', NULL, 13, 13, NULL),
(69, '2024-07-10 22:32:09.000000', 'hello', 5, 100, 'some-reference', NULL, 1, 'Sortant', 'Mali', 'bna', NULL, NULL, 'ParIBAN', '1234', NULL, 13, NULL),
(70, '2024-07-10 23:30:27.000000', '111', 5, 100, 'some-reference', NULL, 1, 'Sortant', 'Togo', 'bna', NULL, NULL, 'ParIBAN', '123', NULL, 13, NULL),
(71, '2024-07-10 23:31:33.000000', '111', 5, 100, 'some-reference', NULL, 1, 'Sortant', 'Germany', NULL, 'anh', NULL, 'ParAutreCompte', NULL, NULL, 13, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `langue` varchar(255) DEFAULT NULL,
  `mot_de_passe` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role` enum('Admin','Utilisateur') DEFAULT NULL,
  `statut` bit(1) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `client_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `langue`, `mot_de_passe`, `nom`, `prenom`, `role`, `statut`, `telephone`, `username`, `client_id`) VALUES
(1, 'johndoe@example.com', 'en', '$2a$10$XkZMaWAcw8kKdFsq46J8bunFTr3.Nxh5i0P5wP5POtVxnUiH6VVv.', 'Doe', 'John', 'Admin', b'1', '555-1234', 'john.doe', 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `alias`
--
ALTER TABLE `alias`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_t0gb40au46ac4r8h4s5uc1fjl` (`compte_bancaire_id`),
  ADD KEY `FK1xr278mcrgegukvaxwwgt7uhw` (`client_id`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `compte_bancaire`
--
ALTER TABLE `compte_bancaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK439jqs6ey6ujpkdgy8f3ef4ll` (`client_id`);

--
-- Index pour la table `contact`
--
ALTER TABLE `contact`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9pxqh5n5q7a34709wi8lms3ed` (`compte_bancaire_id`),
  ADD KEY `FK8j4lp42ic7exi1rh48317j0mq` (`utilisateur_id`);

--
-- Index pour la table `demande_paiement`
--
ALTER TABLE `demande_paiement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKln1e7u5x44o442f3qpjpwxm9j` (`destinataire_id`),
  ADD KEY `FKs2202ct2tn3xyktmypo2dq7pa` (`expediteur_id`);

--
-- Index pour la table `devise`
--
ALTER TABLE `devise`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKs935uxsgv0hm0ll69aw7pt9fq` (`demande_paiement_id`),
  ADD KEY `FKcimsab72td85kwbfg1dd5vww1` (`transfert_id`);

--
-- Index pour la table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  ADD KEY `FKidn7cwvi9r6begnea6k0o486i` (`user_id`);

--
-- Index pour la table `transfert`
--
ALTER TABLE `transfert`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKm6j5rv6pis1929c0382lsyxej` (`alias_id`),
  ADD KEY `FKjd9y0507itb2tmnhncb9ose9y` (`devise_id`),
  ADD KEY `FKnsllb4d9fwf65k30u9amhjhn0` (`destinataire_id`),
  ADD KEY `FKdmfhkhu7ocmcu3pd24hndeb2v` (`expediteur_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8b28kmujgtm655whf9b1s4nfm` (`client_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `alias`
--
ALTER TABLE `alias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `compte_bancaire`
--
ALTER TABLE `compte_bancaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `contact`
--
ALTER TABLE `contact`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `demande_paiement`
--
ALTER TABLE `demande_paiement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `devise`
--
ALTER TABLE `devise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=114;

--
-- AUTO_INCREMENT pour la table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT pour la table `token`
--
ALTER TABLE `token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- AUTO_INCREMENT pour la table `transfert`
--
ALTER TABLE `transfert`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `alias`
--
ALTER TABLE `alias`
  ADD CONSTRAINT `FK1xr278mcrgegukvaxwwgt7uhw` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `FKkl2p5tmogotxxwq151own2akc` FOREIGN KEY (`compte_bancaire_id`) REFERENCES `compte_bancaire` (`id`);

--
-- Contraintes pour la table `compte_bancaire`
--
ALTER TABLE `compte_bancaire`
  ADD CONSTRAINT `FK439jqs6ey6ujpkdgy8f3ef4ll` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);

--
-- Contraintes pour la table `contact`
--
ALTER TABLE `contact`
  ADD CONSTRAINT `FK8j4lp42ic7exi1rh48317j0mq` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`),
  ADD CONSTRAINT `FK9pxqh5n5q7a34709wi8lms3ed` FOREIGN KEY (`compte_bancaire_id`) REFERENCES `compte_bancaire` (`id`);

--
-- Contraintes pour la table `demande_paiement`
--
ALTER TABLE `demande_paiement`
  ADD CONSTRAINT `FKln1e7u5x44o442f3qpjpwxm9j` FOREIGN KEY (`destinataire_id`) REFERENCES `alias` (`id`),
  ADD CONSTRAINT `FKs2202ct2tn3xyktmypo2dq7pa` FOREIGN KEY (`expediteur_id`) REFERENCES `alias` (`id`);

--
-- Contraintes pour la table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FKcimsab72td85kwbfg1dd5vww1` FOREIGN KEY (`transfert_id`) REFERENCES `transfert` (`id`),
  ADD CONSTRAINT `FKs935uxsgv0hm0ll69aw7pt9fq` FOREIGN KEY (`demande_paiement_id`) REFERENCES `demande_paiement` (`id`);

--
-- Contraintes pour la table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `FKidn7cwvi9r6begnea6k0o486i` FOREIGN KEY (`user_id`) REFERENCES `utilisateur` (`id`);

--
-- Contraintes pour la table `transfert`
--
ALTER TABLE `transfert`
  ADD CONSTRAINT `FKdmfhkhu7ocmcu3pd24hndeb2v` FOREIGN KEY (`expediteur_id`) REFERENCES `alias` (`id`),
  ADD CONSTRAINT `FKjd9y0507itb2tmnhncb9ose9y` FOREIGN KEY (`devise_id`) REFERENCES `devise` (`id`),
  ADD CONSTRAINT `FKm6j5rv6pis1929c0382lsyxej` FOREIGN KEY (`alias_id`) REFERENCES `alias` (`id`),
  ADD CONSTRAINT `FKnsllb4d9fwf65k30u9amhjhn0` FOREIGN KEY (`destinataire_id`) REFERENCES `alias` (`id`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `FK8b28kmujgtm655whf9b1s4nfm` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
