-- phpMyAdmin SQL Dump
-- version 4.4.13.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost:3306
-- Tiempo de generación: 17-11-2015 a las 01:18:13
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.5.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ausiasyield2015`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoriaarticulo`
--

CREATE TABLE IF NOT EXISTS `categoriaarticulo` (
  `id` int(11) NOT NULL COMMENT 'id',
  `nombre` varchar(255) DEFAULT NULL COMMENT 'nombre',
  `descripcion` varchar(255) DEFAULT NULL COMMENT 'descripcion'
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `categoriaarticulo`
--

INSERT INTO `categoriaarticulo` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Automóviles', 'En esta categoría podrás encontrar todo el contenido sobre automóviles.'),
(2, 'Deportes', 'En esta categoría podrás encontrar todo el contenido sobre deportes.'),
(3, 'Gastronomía', 'En esta categoría podrás encontrar todo el contenido sobre gastronomía.'),
(4, 'Naturaleza', 'En esta categoría podrás encontrar todo el contenido sobre naturaleza.'),
(5, 'Ciencia', 'En esta categoría podrás encontrar todo el contenido sobre ciencias.'),
(6, 'Política', 'En esta categoría podrás encontrar todo el contenido sobre política.'),
(7, 'Libros', 'En esta categoría podrás encontrar todo el contenido sobre libros.'),
(8, 'Cine', 'En esta categoría podrás encontrar todo el contenido sobre cine.'),
(9, 'Videojuegos', 'En esta categoría podrás encontrar todo el contenido sobre videojuegos.'),
(10, 'Tecnología', 'En esta categoría podrás encontrar todo el contenido sobre tecnología.'),
(11, 'Historia', 'En esta categoría podrás encontrar todo el contenido sobre historia.'),
(12, 'Arte', 'En esta categoría podrás encontrar todo el contenido sobre arte.'),
(13, 'Meteorología', 'En esta categoría podrás encontrar todo el contenido sobre meteorología.'),
(14, 'Turismo', 'En esta categoría podrás encontrar todo el contenido sobre zonas turísticas.'),
(15, 'Humor', 'En esta categoría podrás encontrar todo el contenido sobre humor.'),
(16, 'Cultura', 'En esta categoría podrás encontrar todo el contenido sobre cultura.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentario`
--

CREATE TABLE IF NOT EXISTS `comentario` (
  `id` int(11) NOT NULL COMMENT 'id',
  `contenido` text COMMENT 'contenido',
  `nombreautor` varchar(255) DEFAULT NULL COMMENT 'nombre autor',
  `id_documento` int(11) DEFAULT NULL COMMENT 'id documento'
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `comentario`
--

INSERT INTO `comentario` (`id`, `contenido`, `nombreautor`, `id_documento`) VALUES
(1, 'No me hace mucha gracia que te hagas amigo de mi hermana.', 'Nando Laorden', NULL),
(2, 'Crea tablas pa la estantería del vino', 'Iván García', NULL),
(3, 'Allahu Akbar', 'Santiago Ferrandis', NULL),
(4, 'Normalizao', 'Fernando Cantos', NULL),
(5, '¿Y la europea?', 'Mariano Rajoy', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documento`
--

CREATE TABLE IF NOT EXISTS `documento` (
  `id` int(6) NOT NULL COMMENT 'Num.',
  `titulo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Titulo',
  `contenido` longtext COLLATE utf8_unicode_ci COMMENT 'Contenido',
  `alta` date DEFAULT NULL COMMENT 'Alta',
  `cambio` date DEFAULT NULL COMMENT 'Cambio',
  `hits` int(10) DEFAULT NULL COMMENT 'Hits',
  `id_usuario` int(6) DEFAULT NULL COMMENT 'Usuario',
  `etiquetas` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Etiquetas',
  `publicado` tinyint(1) DEFAULT NULL COMMENT 'Publicado',
  `portada` tinyint(1) DEFAULT NULL COMMENT 'Portada',
  `destacado` tinyint(1) DEFAULT NULL COMMENT 'Destacado',
  `id_tipodocumento` int(6) DEFAULT NULL COMMENT 'Tipo'
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `documento`
--

INSERT INTO `documento` (`id`, `titulo`, `contenido`, `alta`, `cambio`, `hits`, `id_usuario`, `etiquetas`, `publicado`, `portada`, `destacado`, `id_tipodocumento`) VALUES
(1, 'drdrdrtttwaaaa', '%3D%20Entrega%20de%20premios%20%3DsffsdfIntroducci%C3%B3n%20*%20ksjdlk%20*%20klk%C3%B1%20*%20k%C3%B1lk%C3%B1l//gdfg//dfgdfwdfgdfdfgdfgdfgdfgdfgdfgggggggggggggg%20dgdfgdfgdg%20dfgdfgdfgdfgddfgdgdfdd', '2014-10-26', '2014-10-26', 1, 0, 'das1a', 1, 1, 1, 0),
(2, 'doc%20de%20rafa1aaa', '%3DMi%20documento%2001%3D%0D%0A%0D%0A%20...', '2014-09-21', '2014-09-21', 411, 4, 'pepe', 1, 1, 0, 2),
(3, 'wwww1111', 'ggtttqqqd%20asdasdas%20das', '2014-09-28', '2014-09-28', 22, 5, 'pepe', 1, 1, 0, 3),
(4, 'sss', 'www', '2014-09-11', '2014-09-11', 0, 3, 'pepe', 1, 1, 0, 2),
(5, 'DFGDFG', 'HDFHDF', '2014-09-16', '2014-09-16', 1, 2, 'pepe', 1, 1, 0, 5),
(6, 'Mi doc', '[http://github.com|Github] \r\nhola =miau= [http://intel.com|Intel] \r\n hola ====jujujuj==== [http://google.es|Google]\r\n\r\n\r\n=rafa=\r\n\r\nHolaaaaaa\r\n\r\n==segundo titulo==\r\n\r\n<table class="table">\r\n<th><td>hola</td></th>\r\n</table>', '2013-11-19', NULL, 6, 5, 'rafa', NULL, NULL, NULL, NULL),
(7, 'doc de rafa', '=Mi documento 01=\r\n\r\n[http://materialesdaw-raznar.rhcloud.com|metriales daw] de rafa', '2013-11-20', NULL, 4, 4, 'rafa', NULL, NULL, NULL, NULL),
(8, 'swxxa%20sss%20s%20s%20', '', '2014-09-16', '2014-09-16', 0, 5, 'pepe', 1, 1, 0, 0),
(9, '', 'ssdwdwdss%20sssssss', '2014-09-16', '2014-09-16', 0, 3, 'rafa', 1, 1, 0, 0),
(10, 'DFGDFG', 'HDFHDF', '2013-11-20', NULL, 1, 1, 'rafa', NULL, NULL, NULL, NULL),
(19, 'assaaaaa', 'wedaaaa%20aaaaaaaaa%20cccc0000', '2014-10-15', '2014-10-15', 161, 4, 'aqw', 1, 1, 0, 9),
(22, 'd', 'dsds', '2014-09-21', '2014-09-21', 11, 1, 'ana', 0, 0, 0, 4),
(23, 'd', 'dsds', '2014-09-21', '2014-09-21', 11, 1, 'ana', 0, 0, 0, 4),
(24, 'd', 'dsds', '2014-09-21', '2014-09-21', 11, 1, 'ana', 0, 0, 0, 4),
(25, 'c', 'sd', '2014-09-21', '2014-09-21', 2, 2, 'das', 1, 1, 1, 7),
(26, 'c', 'sd', '2014-09-21', '2014-09-21', 2, 2, 'das', 1, 1, 1, 7),
(27, 'c', 'sd', '2014-09-21', '2014-09-21', 2, 2, 'das', 1, 1, 1, 7),
(28, 'c', 'sd', '2014-09-21', '2014-09-21', 2, 2, 'das', 1, 1, 1, 7),
(29, 'dgdfgdfgdf', 'gdf', '2014-09-21', '2014-09-21', 555, 1, 'rafa', 0, 0, 0, 2),
(35, 'das', 'asdasd%20asdasd', '2014-09-28', '2014-09-28', 111, 2, 'ddd', 1, 1, 1, 10),
(36, 'dasd', 'asdas%20', '2014-09-28', '2014-09-28', 22, 1, 'ddd', 0, 1, 0, 2),
(37, 'ads', 'asd', '2014-09-28', '2014-09-28', 2, 1, 'das', 0, 0, 0, 3),
(38, 'fd', 'fsdfsd', '2014-09-28', '2014-09-28', 2, 1, 'das', 0, 0, 0, 3),
(39, 'fadf', 'sdf', '2014-09-28', '2014-09-28', 2222, 1, 'ana', 1, 1, 0, 3),
(41, 'fsd', 'sdf', '2014-09-28', '2014-09-28', 3, 1, 'ana', 0, 0, 0, 1),
(45, 'sdf', 'sdfaaaaaaaaaa', '2014-09-30', '2014-09-30', 3333, 1, 'fdsfsdf', 1, 1, 0, 1),
(46, 'hfghf', 'hf', '2014-10-19', '2014-10-19', 4, 1, 'hfg', 1, 1, 1, 6),
(47, 'hfgh', 'fhgh', '2014-10-24', '2014-10-24', 55, 1, '12%20rrr', 1, 1, 1, 2),
(48, 'gggggggg', 'hhhhhhhhhhh', '2014-10-25', '2014-10-25', 333, 2, 'gfdgdf%20rrrr', 1, 1, 1, 5),
(49, 'dfaaaaaaaaaaag', 'aaaaaaaaadfg', '2015-10-08', '2015-10-08', 1, 0, 'aaaaaaaaaaaaaaa', 0, 0, 1, 0),
(50, 'sfdf', 'sdfs', '2015-10-08', '2015-10-08', 1, 0, 'fsdfs', 0, 0, 0, 0),
(51, 'dfg', 'dfg', '2015-10-08', '2015-10-08', 1, 0, 'sdfsdfsfsdf', 0, 0, 0, 0),
(52, 'dasd', 'dasda', '2015-10-08', '2015-10-08', 1, 0, 'dasd', 0, 0, 0, 0),
(53, 'qqqqqqqqqqq', 'qqqqqqqqqqqqq', '2015-10-08', '2015-10-08', 1, 0, '', 1, 1, 1, 0),
(54, 'gdf', 'dfg', '2015-10-08', '2015-10-08', 1, 0, 'fsdf', 0, 0, 0, 0),
(55, 'qq%20qqqq%20qqqqqqqqqq%20qqqqq', 'ww%20wwwwwww%20wwwwwwwwww', '2015-10-08', '2015-10-08', 1, 0, 'wwwww%20wwww', 1, 1, 1, 0),
(56, 'bcv', 'cvb', '2015-10-08', '2015-10-08', 1, 0, 'vxc', 0, 0, 0, 0),
(57, 'das', 'asdasd', '2015-10-08', '2015-10-08', 1, 0, 'qqw', 1, 1, 1, 0),
(58, 'f', 'kjkj', '2015-10-08', '2015-10-08', 7, 0, '7yuty', 1, 0, 0, 0),
(59, 'fd%20fsdfsdf%20%20sdf%20s', '%20sdf%20sdfsd', '2015-10-20', '2015-10-08', 1, 0, '%20gdfgd%20f%20gdfg%20d', 0, 1, 0, 0),
(60, '12', 'www%20ccc', '2015-10-08', '2015-10-08', 1, 0, 'eded', 0, 0, 0, 0),
(61, 'wwwe', 'eeew', '2015-10-08', '2015-10-08', 1, 0, 'das', 0, 0, 0, 0),
(62, 'da', 'dasd', '2015-10-08', '2015-10-08', 1, 0, 'das', 0, 0, 0, 0),
(63, 'fgh', 'fgh', '2015-10-08', '2015-10-08', 1, 0, 'dfg', 0, 0, 0, 0),
(64, 'sad', 'asd', '2015-10-08', '2015-10-08', 1, 0, 'fsd', 0, 0, 0, 0),
(65, 'gdf', 'dgf', '2015-10-08', '2015-10-08', 1, 0, 'fds', 0, 0, 0, 0),
(66, 'gd', 'gd', '2015-10-08', '2015-10-08', 1, 0, 'dsf', 0, 0, 0, 0),
(67, 'sdf', 'fsd', '2015-10-08', '2015-10-08', 1, 0, 'sfd', 0, 0, 0, 0),
(68, 'das', 'ddddd', '2015-10-08', '2015-10-08', 1, 0, 'aaq', 1, 1, 1, 0),
(69, 'sdf', 'sdf', '2015-10-08', '2015-10-08', 1, 0, 'f', 0, 0, 0, 0),
(70, 'qqq', 'fff', '2015-10-09', '2015-10-09', 1, 0, 'fff', 0, 0, 1, 0),
(71, 'uuu', 'uuu', '2015-10-09', '2015-10-09', 2, 0, 'uu', 1, 0, 0, 0),
(72, 'kk', 'kkk', '2015-10-09', '2015-10-09', 1, 0, 'r', 0, 0, 0, 0),
(73, '11111111', '11111', '2015-10-10', '2015-10-10', 1, 0, '1', 1, 1, 1, 0),
(74, '11111111', '11111', '2015-10-10', '2015-10-10', 1, 0, '1', 1, 1, 1, 0),
(75, 'aaaa', 'aaaa', '2015-10-10', '2015-10-10', 1, 0, 'aaa', 0, 1, 0, 0),
(76, 'aaa', 'aaa', '2015-10-10', '2015-10-10', 1, 0, 'aaaaa', 0, 1, 0, 0),
(77, 'aaaaaa', 'aaaaa', '2015-10-10', '2015-10-10', 1, 0, 'aaa', 1, 1, 1, 0),
(78, 'aaaaaaaa', 'aaaaaaaaaaa', '2015-10-10', '2015-10-10', 1, 0, 'aaa', 1, 1, 1, 0),
(79, 'aa', 'aa', '2015-10-10', '2015-10-10', 1, 0, 'aa', 0, 1, 1, 0),
(80, 'h', 'h', '2015-10-11', '2015-10-11', 1, 0, 'a', 0, 1, 0, 0),
(81, 'a', 'aserrr', '2015-10-11', '2015-10-11', 1, 0, 'fsd', 0, 0, 1, 0),
(82, 'a', 'aserrr', '2015-10-11', '2015-10-11', 1, 0, 'fsd', 0, 0, 1, 0),
(83, 'a', 'aserrr', '2015-10-11', '2015-10-11', 1, 0, 'fsd', 0, 0, 1, 0),
(84, 'sss', 'ssss', '2015-10-08', '2015-10-11', 1, 0, 'ssss', 0, 0, 1, 0),
(91, 'Cuestionario de prueba', '1.-¿cual es el sentido de la vida?\r\na-ninguno, vamos a morir!\r\nb-43\r\nc-tetas\r\nd-ns/nc\r\n\r\n2.-¿qué come el veloz murciélago?\r\na-cardillo y kiwi\r\nb-huevos con patatas\r\nc-paella,ché\r\nd-ns/nc\r\n\r\n3.-¿qué tocaba la cigüeña tras el palenque de paja?\r\na-la zambomba\r\nb-el saxofón\r\nc-la flauta\r\nd-ns/nc\r\n\r\n4.-¿existe Dios?\r\na.-si\r\nb.-no\r\nc.-jamás\r\nd.-yo soy Dios\r\n\r\n5.-¿rajoy es subnormal?\r\na.-si\r\nb.-¿acaso el papa es un boludo?\r\nc.-por supuesto\r\nd.-no me cabe duda', '2015-11-11', '2015-11-11', 1, 15, 'cuestionario,prueba,pregunta,respuesta,opcion', 1, 1, 1, 12),
(92, 'Cuestionario de Prueba 2!!!!111', 'muchas preguntas', '2015-11-17', '2015-11-17', 5, 5, 'cuestionario,prueba,111', 1, 1, 1, 12);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `documentocategoriaarticulo`
--

CREATE TABLE IF NOT EXISTS `documentocategoriaarticulo` (
  `id` int(11) NOT NULL COMMENT 'id',
  `id_documento` int(11) DEFAULT NULL COMMENT 'id documento',
  `id_categoriaarticulo` int(11) DEFAULT NULL COMMENT 'id categoriaarticulo',
  `descripcion` varchar(255) DEFAULT NULL COMMENT 'descripcion'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado`
--

CREATE TABLE IF NOT EXISTS `estado` (
  `id` int(11) NOT NULL COMMENT 'Identificador',
  `tipo` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Estado'
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `estado`
--

INSERT INTO `estado` (`id`, `tipo`) VALUES
(1, 'Estoy contento'),
(2, 'Estoy feliz'),
(3, 'Estoy happy'),
(4, 'Estoy triste'),
(5, 'Estoy con fiebre'),
(6, 'Tengo fiebre'),
(7, 'En el gimnasio'),
(8, 'De quintos'),
(9, 'En el cine'),
(10, 'Estudiando'),
(11, 'En el trabajo'),
(12, 'Durmiendo'),
(13, 'En el baño...'),
(14, 'En el medico'),
(15, 'De fiesta'),
(16, 'Confuso'),
(17, 'Deprimido'),
(18, 'Fantastico'),
(19, 'OP'),
(20, 'rafa es el mejor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcion`
--

CREATE TABLE IF NOT EXISTS `opcion` (
  `id` int(11) NOT NULL,
  `id_pregunta` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `opcion`
--

INSERT INTO `opcion` (`id`, `id_pregunta`, `descripcion`) VALUES
(46, 17, 'ninguno, vamos a morir!'),
(47, 17, '43'),
(50, 17, 'tetas'),
(51, 17, 'ns/nc'),
(54, 18, 'cardillo y kiwi'),
(55, 18, 'huevos con patatas'),
(58, 18, 'paella,ché!'),
(59, 18, 'ns/nc'),
(62, 19, 'La zambomba'),
(63, 19, 'El saxofón'),
(66, 19, 'La flauta'),
(67, 19, 'NS/NC'),
(70, 20, 'Sí'),
(71, 20, 'No'),
(74, 20, 'Jamás'),
(75, 20, 'Yo soy Dios'),
(78, 21, 'Sí'),
(79, 21, '¿Acaso el Papa es un boludo?'),
(82, 21, 'Por supuesto'),
(83, 21, 'No me cabe duda'),
(84, 22, 'Prepara la vaselina'),
(85, 22, 'No te preocupes'),
(86, 22, 'Es inevitable'),
(87, 22, 'Prepara el popper'),
(88, 23, 'Rubias'),
(89, 23, 'morenas'),
(90, 23, 'paco'),
(91, 23, 'cabras'),
(92, 24, 'teenagers'),
(93, 24, 'paco'),
(94, 24, 'ancianos'),
(95, 24, 'cabras'),
(96, 25, 'holiiii'),
(97, 25, 'adios'),
(98, 26, 'muy mucho'),
(99, 26, 'que va');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `post`
--

CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL COMMENT 'Num.',
  `id_usuario` int(11) DEFAULT NULL COMMENT 'Usuario',
  `id_documento` int(11) DEFAULT NULL COMMENT 'Documento',
  `fecha` datetime DEFAULT NULL COMMENT 'Publicacion',
  `mensaje` text COLLATE utf8_unicode_ci
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `post`
--

INSERT INTO `post` (`id`, `id_usuario`, `id_documento`, `fecha`, `mensaje`) VALUES
(1, 3, 1, '2015-11-02 09:07:59', NULL),
(2, 5, 2, '2015-10-04 07:46:22', NULL),
(3, 8, 58, '2015-09-29 05:11:35', NULL),
(4, 2, 26, '2015-09-27 16:14:13', NULL),
(5, 6, 8, '2015-09-04 00:00:00', NULL),
(6, 27, 58, '2015-09-16 18:32:45', NULL),
(7, 8, 28, '2015-09-15 06:39:18', NULL),
(8, 17, 27, '2015-09-14 08:14:59', NULL),
(9, 19, 73, '2015-11-28 11:14:40', NULL),
(10, 1, 29, '2015-11-01 08:17:28', NULL),
(11, 14, 28, NULL, NULL),
(12, 20, 48, '2015-11-17 09:18:20', NULL),
(13, 26, 77, '2015-09-08 07:17:00', NULL),
(14, 29, 57, '2015-09-29 07:15:38', NULL),
(15, 13, 5, '2015-11-23 13:15:15', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pregunta`
--

CREATE TABLE IF NOT EXISTS `pregunta` (
  `id` int(11) NOT NULL,
  `id_documento` int(11) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `pregunta`
--

INSERT INTO `pregunta` (`id`, `id_documento`, `descripcion`) VALUES
(17, 91, '¿cual es el sentido de la vida?'),
(18, 91, '¿qué come el veloz murciélago?'),
(19, 91, '¿qué tocaba la cigüeña tras el palenque de paja?'),
(20, 91, '¿existe Dios?'),
(21, 91, '¿rajoy es subnormal?'),
(22, 92, '¿nos van a dar por el culo mañana?'),
(23, 92, '¿rubias o morenas?'),
(24, 92, '¿cual es tu cliente objetivo?'),
(25, 92, '¿hola?'),
(26, 92, '¿julio es un cansino?');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuesta`
--

CREATE TABLE IF NOT EXISTS `respuesta` (
  `id` int(11) NOT NULL,
  `id_opcion` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  `fechaHoraAlta` datetime DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `respuesta`
--

INSERT INTO `respuesta` (`id`, `id_opcion`, `id_usuario`, `fechaHoraAlta`) VALUES
(10, 47, 1, '2015-11-11 00:00:00'),
(11, 54, 1, '2015-11-11 00:00:00'),
(12, 63, 1, '2015-11-11 00:00:00'),
(13, 75, 1, '2015-11-11 17:20:45'),
(14, 79, 1, '2015-11-11 06:29:21'),
(15, 85, 0, NULL),
(16, 85, 0, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipodocumento`
--

CREATE TABLE IF NOT EXISTS `tipodocumento` (
  `id` int(6) NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `privado` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipodocumento`
--

INSERT INTO `tipodocumento` (`id`, `descripcion`, `privado`) VALUES
(1, 'oferta de trabajo', 1),
(2, 'currículum', 1),
(3, 'actividad', 1),
(4, 'experiencia', 1),
(5, 'formación', 1),
(6, 'valía', 1),
(7, 'inscripción', 1),
(8, 'solicitud', 1),
(9, 'bolsa de trabajo', 1),
(10, 'idiomas', 1),
(11, 'wer', 1),
(12, 'cuestionario', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipousuario`
--

CREATE TABLE IF NOT EXISTS `tipousuario` (
  `id` int(11) NOT NULL COMMENT 'Identificador',
  `descripcion` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Descripción'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tipousuario`
--

INSERT INTO `tipousuario` (`id`, `descripcion`) VALUES
(1, 'Administrador'),
(2, 'Usuario'),
(3, 'Visitante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(6) NOT NULL COMMENT 'Identificador',
  `login` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Nombre de usuario',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Contraseña',
  `id_tipousuario` int(11) DEFAULT NULL COMMENT 'Tipo de usuario',
  `id_estado` int(11) DEFAULT NULL COMMENT 'Estado',
  `ciudad` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Ciudad',
  `firma` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Firma',
  `skin` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT 'Plantilla'
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `login`, `password`, `id_tipousuario`, `id_estado`, `ciudad`, `firma`, `skin`) VALUES
(1, 'pepe', 'pepe', 2, 1, 'Valencia', 'is my life and do what I want', 'main'),
(2, 'juan', 'juan', 3, 3, 'Madrid', 'http://criticalandia.com críticas de entretenimiento, listas, opiniones...', 'main'),
(3, 'maria', 'maria', 3, 6, 'Barcelona', 'If you love something, set it free. Unless it''''s a tiger.', 'main'),
(4, 'antonia', 'antonia', 3, 7, 'Sevilla', '"El único límite a nuestros logros de mañana está en nuestras dudas de hoy."', 'main'),
(5, 'edu', 'edu', 3, 13, 'Zaragoza', 'Plataforma: ORGULLLO CADISTA no.58', 'main'),
(6, 'jose', 'jose', 3, 19, 'Teruel', 'Ironía: Figura literaria mediante la cual se da a entender lo contrario de lo que se dice.', 'main'),
(7, 'silvia', 'silvia', 3, 6, 'Huesca', 'Paso de firmas', 'main'),
(8, 'pedro', 'pedro', 3, 8, 'Alicante', 'Camisetas y calzado www.pedidoshicks.com', 'main'),
(9, 'raquel', 'raquel', 3, 15, 'Castellón', 'PEÑA COLCHONERA Socio número 629', 'main'),
(10, 'daniel', 'daniel', 3, 12, 'Almería', '"Obsesionado es tan sólo la palabra que usan los perezosos para describir a los dedicados"', 'main'),
(11, 'rafael', 'rafael', 1, 17, 'A Coruña', 'Ista ye a mia tierra, a mia fabla', 'main'),
(12, 'juan', 'juan', 3, 14, 'Barcelona', 'No todos los catalanes somos independentistas', 'main'),
(13, 'elena', 'elena', 3, 19, 'Bilbao', 'Buenas tardes', 'main'),
(14, 'luis', 'luis', 3, 4, 'Lugo', 'Preparado para cualquier combate', 'main'),
(15, 'alba', 'alba', 3, 5, 'Cuenca', 'Si tienes un Ibiza o un Cordoba, este es tu club: www.clubseatcordoba.com', 'main'),
(16, 'amparo', 'amparo', 3, 7, 'Ciudad Real', 'No hay dos sin tres', 'main'),
(17, 'ambrosio', 'ambrosio', 3, 8, 'Guadalajara', 'Tesis+Antítesis=Síntesis. Problema+Acción = Solución.', 'main'),
(18, 'luisa', 'luisa', 3, 1, 'Huelva', 'Y yo me iré. y se quedará mi huerto con su verde árbol, y con su pozo blanco. Y yo me iré.. Y se quedarán los pájaros cantando', 'main'),
(19, 'leon', 'leon', 3, 3, 'Granada', 'La Infanta no sabía nada y punto.', 'main'),
(20, 'rosa', 'rosa', 3, 2, 'Cádiz', 'Viva España', 'main'),
(21, 'capcom', 'capcom', 3, 17, 'Jerez', 'La gente cree que soy una mala persona, pero no es cierto, yo tengo el corazón de un niño...en un frasco con formol encima de mi escritorio.', 'main'),
(22, 'teleco', 'teleco', 3, 18, 'Vallecas', 'Foreros de la Comunidad de Madrid Nº25', 'main'),
(23, 'mercadona', 'mercadona', 3, 13, 'Jaén', 'Y veréis el resurgir poderoso del guerrero, sin miedo a leyes ni a nostalgias.', 'main'),
(24, 'vistaprint', 'vistaprint', 3, 15, 'Valencia', 'Codeados.com Diseño y Desarrollo web, Imagen Corporativa, SEO, Marketing Digital', 'main'),
(25, 'google', 'google', 3, 16, 'California', 'Viva google +', 'main'),
(26, 'konami', 'konami', 3, 6, 'Tokio', 'Viva Castolo, Minanda y Ximelez', 'main'),
(27, 'orange', 'orange', 3, 7, 'París', 'Viva movistar', 'main'),
(28, 'samsung', 'samsung', 3, 8, 'Cuenca', 'Viva el iPhone 6', 'main'),
(29, 'gigabyte', 'gigabyte', 3, 10, 'Oviedo', 'Viva gigabyte', 'main'),
(30, 'microsoft', 'microsoft', 3, 10, 'Albacete', 'La xbox ONE es la MEJOR CONSOLA', 'main');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoriaarticulo`
--
ALTER TABLE `categoriaarticulo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `comentario`
--
ALTER TABLE `comentario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `documento`
--
ALTER TABLE `documento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `documentocategoriaarticulo`
--
ALTER TABLE `documentocategoriaarticulo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estado`
--
ALTER TABLE `estado`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `opcion`
--
ALTER TABLE `opcion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `respuesta`
--
ALTER TABLE `respuesta`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoriaarticulo`
--
ALTER TABLE `categoriaarticulo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `comentario`
--
ALTER TABLE `comentario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `documento`
--
ALTER TABLE `documento`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Num.',AUTO_INCREMENT=93;
--
-- AUTO_INCREMENT de la tabla `documentocategoriaarticulo`
--
ALTER TABLE `documentocategoriaarticulo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id';
--
-- AUTO_INCREMENT de la tabla `estado`
--
ALTER TABLE `estado`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador',AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `opcion`
--
ALTER TABLE `opcion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=100;
--
-- AUTO_INCREMENT de la tabla `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Num.',AUTO_INCREMENT=16;
--
-- AUTO_INCREMENT de la tabla `pregunta`
--
ALTER TABLE `pregunta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT de la tabla `respuesta`
--
ALTER TABLE `respuesta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `tipodocumento`
--
ALTER TABLE `tipodocumento`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `tipousuario`
--
ALTER TABLE `tipousuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Identificador',AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'Identificador',AUTO_INCREMENT=31;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;