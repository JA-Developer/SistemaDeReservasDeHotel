-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.33 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for hotel_reservas
CREATE DATABASE IF NOT EXISTS `hotel_reservas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `hotel_reservas`;

-- Dumping structure for table hotel_reservas.empleados
CREATE TABLE IF NOT EXISTS `empleados` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) NOT NULL,
  `DNI` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `DNI` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel_reservas.empleados: ~5 rows (approximately)
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` (`Id`, `Nombre`, `DNI`) VALUES
	(1, 'José García', '33344455'),
	(2, 'Laura Sánchez', '66554433'),
	(3, 'David Pérez', '77889900'),
	(4, 'Beatriz Rodríguez', '11223311'),
	(5, 'Ricardo Martínez', '99887700');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;

-- Dumping structure for table hotel_reservas.habitaciones
CREATE TABLE IF NOT EXISTS `habitaciones` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Cantidad_De_Camas` int unsigned NOT NULL,
  `Precio_Por_Hora` decimal(10,0) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel_reservas.habitaciones: ~7 rows (approximately)
/*!40000 ALTER TABLE `habitaciones` DISABLE KEYS */;
INSERT INTO `habitaciones` (`Id`, `Cantidad_De_Camas`, `Precio_Por_Hora`) VALUES
	(1, 1, 100),
	(2, 2, 150),
	(3, 1, 120),
	(4, 2, 180),
	(5, 4, 45),
	(6, 6, 12),
	(7, 1, 1);
/*!40000 ALTER TABLE `habitaciones` ENABLE KEYS */;

-- Dumping structure for table hotel_reservas.huespedes
CREATE TABLE IF NOT EXISTS `huespedes` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) NOT NULL,
  `DNI` varchar(255) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `DNI` (`DNI`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel_reservas.huespedes: ~6 rows (approximately)
/*!40000 ALTER TABLE `huespedes` DISABLE KEYS */;
INSERT INTO `huespedes` (`Id`, `Nombre`, `DNI`) VALUES
	(1, 'Juan Pérez', '12345678'),
	(2, 'Ana Gómez', '87654321'),
	(3, 'Carlos Rodríguez', '11223344'),
	(4, 'María Fernández', '99887766'),
	(5, 'Luis Martínez Castro', '655443322'),
	(6, 'Jesus', '0401200100783'),
	(8, 'Pedro Aguilar', '045329294');
/*!40000 ALTER TABLE `huespedes` ENABLE KEYS */;

-- Dumping structure for table hotel_reservas.pagos
CREATE TABLE IF NOT EXISTS `pagos` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Reserva_Id` bigint unsigned NOT NULL,
  `No_Factura` varchar(255) NOT NULL,
  `Rtn` varchar(255) DEFAULT NULL,
  `Fecha` timestamp NOT NULL,
  `Monto` decimal(10,0) NOT NULL,
  PRIMARY KEY (`Id`),
  UNIQUE KEY `No_Factura` (`No_Factura`),
  KEY `idx_no_factura_pagos` (`No_Factura`),
  KEY `idx_fecha_pagos` (`Fecha`),
  KEY `fk_pagos_reservas` (`Reserva_Id`),
  CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`Reserva_Id`) REFERENCES `reservas` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel_reservas.pagos: ~7 rows (approximately)
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` (`Id`, `Reserva_Id`, `No_Factura`, `Rtn`, `Fecha`, `Monto`) VALUES
	(1, 1, 'FAC12345', '010101010101', '2025-02-01 18:00:00', 400),
	(2, 2, 'FAC12346', '010101010102', '2025-02-02 13:00:00', 450),
	(3, 3, 'FAC12347', '010101010103', '2025-02-03 21:00:00', 600),
	(4, 4, 'FAC12348', '010101010104', '2025-02-04 20:00:00', 360),
	(5, 5, 'FAC12349', '010101010105', '2025-02-06 00:00:00', 1250),
	(6, 3, 'FAC12350', '110101010101', '2025-02-11 00:00:00', 400),
	(8, 1, '235235', '213123', '2025-03-04 00:00:00', 5463);
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;

-- Dumping structure for table hotel_reservas.reservas
CREATE TABLE IF NOT EXISTS `reservas` (
  `Id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Fecha_De_Inicio` timestamp NOT NULL,
  `Horas` int unsigned NOT NULL,
  `Habitacion_Id` bigint unsigned NOT NULL,
  `Huesped_Id` bigint unsigned NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `idx_fecha_reservas` (`Fecha_De_Inicio`),
  KEY `idx_horas_reservas` (`Horas`),
  KEY `fk_reservas_habitaciones` (`Habitacion_Id`),
  KEY `fk_reservas_huespedes` (`Huesped_Id`),
  CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`Habitacion_Id`) REFERENCES `habitaciones` (`Id`),
  CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`Huesped_Id`) REFERENCES `huespedes` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel_reservas.reservas: ~6 rows (approximately)
/*!40000 ALTER TABLE `reservas` DISABLE KEYS */;
INSERT INTO `reservas` (`Id`, `Fecha_De_Inicio`, `Horas`, `Habitacion_Id`, `Huesped_Id`) VALUES
	(1, '2025-02-01 14:00:00', 4, 1, 1),
	(2, '2025-02-02 10:00:00', 3, 2, 2),
	(3, '2025-02-11 00:00:00', 5, 3, 3),
	(4, '2025-02-04 18:00:00', 2, 4, 4),
	(5, '2025-02-05 08:00:00', 6, 5, 5),
	(6, '2025-03-18 00:00:00', 17, 7, 6);
/*!40000 ALTER TABLE `reservas` ENABLE KEYS */;

-- Dumping structure for table hotel_reservas.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password_hash` varchar(256) DEFAULT NULL,
  `rol` enum('admin','recepcionista') DEFAULT 'recepcionista',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table hotel_reservas.usuarios: ~1 rows (approximately)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `username`, `password_hash`, `rol`) VALUES
	(1, 'Admin', 'e3afed0047b08059d0fada10f400c1e5', 'admin');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
