-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: db
-- Tiempo de generación: 13-04-2022 a las 15:47:19
-- Versión del servidor: 8.0.28
-- Versión de PHP: 8.0.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `peliculaspoo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Actor`
--

CREATE TABLE `Actor` (
  `id` int NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apodo` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `Actor`
--

INSERT INTO `Actor` (`id`, `nombre`, `apodo`) VALUES
(1, 'Marlee Matlin', 'Marlee Matlin'),
(2, 'Troy Kotsur', 'Troy Kotsur'),
(3, 'Daniel Durant', 'Daniel Durant'),
(4, 'Caitriona Balfe', 'Caitriona Balfe'),
(5, 'Judi Dench', 'Judi Dench'),
(6, 'Jamie Dornan', 'Jamie Dornan'),
(7, 'Leonardo DiCaprio', 'Leonardo DiCaprio'),
(8, 'Jennifer Lawrence', 'Jennifer Lawrence'),
(9, 'Rob Morgan', 'Rob Morgan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ActorPelicula`
--

CREATE TABLE `ActorPelicula` (
  `id_pelicula` int NOT NULL,
  `id_actor` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `ActorPelicula`
--

INSERT INTO `ActorPelicula` (`id_pelicula`, `id_actor`) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 4),
(2, 5),
(2, 6),
(3, 7),
(3, 8),
(3, 9),
(4, 2),
(4, 4),
(4, 6),
(4, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Director`
--

CREATE TABLE `Director` (
  `id` int NOT NULL,
  `nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `apodo` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `Director`
--

INSERT INTO `Director` (`id`, `nombre`, `apodo`) VALUES
(1, 'Sian Heder', 'Sian Heder'),
(2, 'Kenneth Branagh', 'Kenneth Branagh'),
(3, 'Adam McKay', 'Adam McKay');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pelicula`
--

CREATE TABLE `Pelicula` (
  `id` int NOT NULL,
  `id_director` int NOT NULL,
  `titulo_original` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  `titulo_espanol` varchar(150) COLLATE utf8mb4_spanish_ci NOT NULL,
  `anno` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `Pelicula`
--

INSERT INTO `Pelicula` (`id`, `id_director`, `titulo_original`, `titulo_espanol`, `anno`) VALUES
(1, 1, 'CODA', 'CODA: Señales del corazón', 2021),
(2, 2, 'Belfast', 'Belfast', 2021),
(3, 3, 'Don\'t look up', 'No mires arriba', 2021),
(4, 2, 'TEST NAME', 'NOMBRE DE PRUEBA', 2022);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Actor`
--
ALTER TABLE `Actor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ActorPelicula`
--
ALTER TABLE `ActorPelicula`
  ADD KEY `id_actor` (`id_actor`),
  ADD KEY `id_pelicula` (`id_pelicula`);

--
-- Indices de la tabla `Director`
--
ALTER TABLE `Director`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Pelicula`
--
ALTER TABLE `Pelicula`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_director` (`id_director`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Actor`
--
ALTER TABLE `Actor`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `Director`
--
ALTER TABLE `Director`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `Pelicula`
--
ALTER TABLE `Pelicula`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ActorPelicula`
--
ALTER TABLE `ActorPelicula`
  ADD CONSTRAINT `ActorPelicula_ibfk_1` FOREIGN KEY (`id_actor`) REFERENCES `Actor` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `ActorPelicula_ibfk_2` FOREIGN KEY (`id_pelicula`) REFERENCES `Pelicula` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;

--
-- Filtros para la tabla `Pelicula`
--
ALTER TABLE `Pelicula`
  ADD CONSTRAINT `Pelicula_ibfk_1` FOREIGN KEY (`id_director`) REFERENCES `Director` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
