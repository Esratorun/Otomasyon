-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                11.6.2-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- tablo yapısı dökülüyor hastaneotomasyonudb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tcno` char(11) NOT NULL,
  `sifre` varchar(250) NOT NULL,
  `isim` varchar(250) NOT NULL,
  `type` enum('bashekim','doktor','hasta') NOT NULL DEFAULT 'hasta',
  PRIMARY KEY (`id`),
  UNIQUE KEY `tcno` (`tcno`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

-- hastaneotomasyonudb.user: ~17 rows (yaklaşık) tablosu için veriler indiriliyor
REPLACE INTO `user` (`id`, `tcno`, `sifre`, `isim`, `type`) VALUES
	(1, '22627292248', 'esra123456', 'Esra Torun', 'bashekim'),
	(2, '11111111110', 'mavi123456', 'Mira Mavi', 'doktor'),
	(3, '22222222221', 'ata1234567', 'Ata Korkmaz', 'doktor'),
	(5, '33333333332', 'eray123456', 'Eray Kara', 'doktor'),
	(6, '44444444443', 'damla12345', 'Damla Akar', 'doktor'),
	(17, '12312457890', 'mehmet1773', 'Mehmet Ayancı', 'doktor'),
	(20, '54643666547', 'demir12345', 'Demir Madenci', 'doktor'),
	(21, '233442313', '1231234', 'Akın Alkım', 'doktor'),
	(23, '52483217157', 'egedenizi7', 'Ege Deniz', 'doktor'),
	(24, '57854294775', 'helinle584', 'Helin Demirci', 'doktor'),
	(26, '23423', '234234', 'Mine Sakin', 'doktor'),
	(27, '1', '44fksdsf', 'Sude Naz', 'doktor'),
	(28, '57845', '7fesf', 'Nazli Hekimoglu', 'doktor'),
	(29, '123789', '12332', 'Azra Celik', 'doktor'),
	(30, '54132', '4542', 'Asil Asilkan', 'doktor'),
	(37, '12345', 'qweqd', 'Mihrimah Kayacan', 'doktor'),
	(38, '4566544566', 'sena456654', 'Sena Şenay', 'hasta'),
	(41, '123', '123', 'Ali Çelik', 'hasta');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
