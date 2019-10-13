-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  Dim 13 oct. 2019 à 17:20
-- Version du serveur :  5.7.23
-- Version de PHP :  7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `segabank`
--

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(5) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`id`, `code`, `adresse`) VALUES
(1, 215, '5 Boulevard Victor Hugo, 44000 Nantes'),
(2, 112, '5 rue Geaorges Pompidou, 85000 La Roche sur Yon'),
(3, 114, '10 rue du General de Gaulle, 35000 Rennes');

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solde` double NOT NULL,
  `idAgence` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idAgence` (`idAgence`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compte`
--

INSERT INTO `compte` (`id`, `solde`, `idAgence`) VALUES
(2, 1521, 3),
(3, 10, 2),
(4, 155555, 1),
(5, 520, 1),
(6, 45522, 1),
(7, 520, 1),
(8, 152, 1),
(9, 51565, 1),
(10, 5664, 1);

-- --------------------------------------------------------

--
-- Structure de la table `compteepargne`
--

DROP TABLE IF EXISTS `compteepargne`;
CREATE TABLE IF NOT EXISTS `compteepargne` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCompte` int(11) NOT NULL,
  `tauxInteret` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idCompte` (`idCompte`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `compteepargne`
--

INSERT INTO `compteepargne` (`id`, `idCompte`, `tauxInteret`) VALUES
(2, 4, 1.5),
(3, 8, 5.2);

-- --------------------------------------------------------

--
-- Structure de la table `comptepayant`
--

DROP TABLE IF EXISTS `comptepayant`;
CREATE TABLE IF NOT EXISTS `comptepayant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCompte` int(11) NOT NULL,
  `tauxRetrait` double NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`),
  KEY `idCompte` (`idCompte`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `comptepayant`
--

INSERT INTO `comptepayant` (`id`, `idCompte`, `tauxRetrait`) VALUES
(1, 3, 5),
(3, 10, 5);

-- --------------------------------------------------------

--
-- Structure de la table `comptesimple`
--

DROP TABLE IF EXISTS `comptesimple`;
CREATE TABLE IF NOT EXISTS `comptesimple` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idCompte` int(11) NOT NULL,
  `decouvert` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `comptesimple_ibfk_1` (`idCompte`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `comptesimple`
--

INSERT INTO `comptesimple` (`id`, `idCompte`, `decouvert`) VALUES
(4, 5, -90);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `montant` double NOT NULL,
  `typeTransaction` enum('depot','retrait') NOT NULL,
  `idCompte` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `compte`
--
ALTER TABLE `compte`
  ADD CONSTRAINT `compte_ibfk_1` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`id`);

--
-- Contraintes pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD CONSTRAINT `compteepargne_ibfk_1` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`id`);

--
-- Contraintes pour la table `comptepayant`
--
ALTER TABLE `comptepayant`
  ADD CONSTRAINT `comptepayant_ibfk_1` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`id`);

--
-- Contraintes pour la table `comptesimple`
--
ALTER TABLE `comptesimple`
  ADD CONSTRAINT `comptesimple_ibfk_1` FOREIGN KEY (`idCompte`) REFERENCES `compte` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
