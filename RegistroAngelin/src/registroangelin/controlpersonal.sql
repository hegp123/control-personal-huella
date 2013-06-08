-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 09-06-2013 a las 01:08:24
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT=0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `controlpersonal`
--

DELIMITER $$
--
-- Funciones
--
DROP FUNCTION IF EXISTS `es_festivo`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `es_festivo`(
  `pfecha` date
) RETURNS tinyint(1)
BEGIN
  RETURN EXISTS (SELECT * FROM FESTIVOS WHERE fecha = pfecha);
END$$

DROP FUNCTION IF EXISTS `NOMBRE_DIA`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `NOMBRE_DIA`(DIA Integer) RETURNS varchar(50) CHARSET latin1
RETURN 
  CASE DIA   
     WHEN 1 THEN 'Domingo'
     WHEN 2 THEN 'Lunes'
     WHEN 3 THEN 'Martes'
     WHEN 4 THEN 'Miércoles'
     WHEN 5 THEN 'Jueves'
     WHEN 6 THEN 'Viernes'
     WHEN 7 THEN 'Sábado'
     ELSE null END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `dedos_digitalpersona`
--

DROP TABLE IF EXISTS `dedos_digitalpersona`;
CREATE TABLE IF NOT EXISTS `dedos_digitalpersona` (
  `fingerindex` int(11) NOT NULL,
  `descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`fingerindex`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `dedos_digitalpersona`
--

INSERT INTO `dedos_digitalpersona` (`fingerindex`, `descripcion`) VALUES
(1, 'MEÑIQUE IZQUIERDO'),
(2, 'ANULAR IZQUIERDO'),
(4, 'MEDIO IZQUIERDO'),
(8, 'INDICE IZQUIERDO'),
(16, 'PULGAR IZQUIERDO'),
(32, 'PULGAR DERECHO'),
(64, 'INDICE DERECHO'),
(128, 'MEDIO DERECHO'),
(256, 'ANULAR DERECHO'),
(512, 'MEÑIQUE DERECHO');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `festivos`
--

DROP TABLE IF EXISTS `festivos`;
CREATE TABLE IF NOT EXISTS `festivos` (
  `fecha` date NOT NULL COMMENT 'FECHA FESTIVO',
  `descripcion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`fecha`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `festivos`
--

INSERT INTO `festivos` (`fecha`, `descripcion`) VALUES
('2013-01-01', 'Año Nuevo'),
('2013-01-07', 'Día de los Reyes Magos'),
('2013-03-24', 'Domingo de Ramos'),
('2013-03-25', 'Día de San José'),
('2013-03-28', 'Jueves Santo'),
('2013-03-29', 'Viernes Santo'),
('2013-03-31', 'Domingo de Resurrección'),
('2013-05-01', 'Día del Trabajo'),
('2013-05-13', 'Día de la Ascensión'),
('2013-06-03', 'Corpus Christi'),
('2013-06-10', 'Sagrado Corazón'),
('2013-07-01', 'San Pedro y San Pablo'),
('2013-07-20', 'Día de la Independencia'),
('2013-08-07', 'Batalla de Boyacá'),
('2013-08-19', 'La asunción de la Virgen'),
('2013-10-14', 'Día de la Raza'),
('2013-11-04', 'Todos los Santos'),
('2013-11-11', 'Independencia de Cartagena'),
('2013-12-08', 'Día de la Inmaculada Concepción'),
('2013-12-25', 'Día de Navidad'),
('2014-01-01', 'Año Nuevo'),
('2014-01-06', 'Día de los Reyes Magos'),
('2014-03-24', 'Día de San José'),
('2014-04-13', 'Domingo de Ramos'),
('2014-04-17', 'Jueves Santo'),
('2014-04-18', 'Viernes Santo'),
('2014-04-20', 'Domingo de Resurrección'),
('2014-05-01', 'Día del Trabajo'),
('2014-06-02', 'Día de la Ascensión'),
('2014-06-23', 'Corpus Christi'),
('2014-06-30', 'Sagrado Corazón'),
('2014-07-20', 'Día de la Independencia'),
('2014-08-07', 'Batalla de Boyacá'),
('2014-08-18', 'La asunción de la Virgen'),
('2014-10-13', 'Día de la Raza'),
('2014-11-03', 'Todos los Santos'),
('2014-11-17', 'Independencia de Cartagena'),
('2014-12-08', 'Día de la Inmaculada Concepción'),
('2014-12-25', 'Día de Navidad');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `id_gen`
--

DROP TABLE IF EXISTS `id_gen`;
CREATE TABLE IF NOT EXISTS `id_gen` (
  `gen_name` varchar(80) NOT NULL DEFAULT '',
  `gen_val` int(11) DEFAULT NULL,
  PRIMARY KEY (`gen_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `id_gen`
--

INSERT INTO `id_gen` (`gen_name`, `gen_val`) VALUES
('PersonaID', 108),
('RegistroPersonaID', 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE IF NOT EXISTS `personas` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id del registro',
  `NOMBRES` varchar(50) NOT NULL,
  `APELLIDOS` varchar(50) NOT NULL,
  `TELEFONO` varchar(100) DEFAULT NULL,
  `DIRECCION` varchar(100) DEFAULT NULL,
  `NUMERO_IDENTIFICACION` varchar(20) NOT NULL,
  `SALARIO` double NOT NULL,
  `ESTADO` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=109 ;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`ID`, `NOMBRES`, `APELLIDOS`, `TELEFONO`, `DIRECCION`, `NUMERO_IDENTIFICACION`, `SALARIO`, `ESTADO`) VALUES
(108, 'Javier A.', 'Ramirez', '8989080', 'Calle 100', '80090989', 500000, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona_huellas`
--

DROP TABLE IF EXISTS `persona_huellas`;
CREATE TABLE IF NOT EXISTS `persona_huellas` (
  `ID_PERSONA` int(11) NOT NULL,
  `TEMPLATE_HUELLA` blob NOT NULL,
  `fingerIndex` int(11) NOT NULL,
  PRIMARY KEY (`ID_PERSONA`,`fingerIndex`),
  KEY `fk_personahuella_dedosdigitalpersona` (`fingerIndex`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona_huellas`
--

INSERT INTO `persona_huellas` (`ID_PERSONA`, `TEMPLATE_HUELLA`, `fingerIndex`) VALUES
(108, 0x00f86d01c82ae3735cc24c2e143fba371ab20a80b798faaa59eecbc4d904971475e41042526d34d93ed6dca3d957e9bb3dca147e3d1c90f82b566699523f027439497261ad0645e200c035987f264ea51fd93b92115973361891017e7daa6ab2eace2327ea0519408e69f17f9aa2ae97350589cf8183dfb627af00334743fb0c9d1536f3d8bd563ff86870bfc867e22c90b213da2ee89c236758d3cfe2d9a9c5a36ee81510ba7a59ced03930abca9edb5bb7fa4f383c943fc01e772a15bd7e7ac22585d7ff42749d6e4cc62927c2022932e82e6ddfcb5c1e625ce62552740f7a501f3f2d8c11bf3f7c51f05df7b081638fb49146f390c37b98b5ac56da2448906b42e5c8420a804135cea48e0e895955853e4d4f5fff5e4cc89d66edcb87bd4d5f9f3397a79548031e6074e17080fd09306c8d53ee08aa07549e08e04f1d6f817002dde5682cff1473e169cb4425a1e8fd81f46f992367c8061f5be0e6ba8c321e70334bc4edd636a0f4956a5c0dfbc8f16f00f85e01c82ae3735cc34b20f939c12ffaeb1fb9979eb8c7a91fe96d5d38ced295f138e267ac805c73fee876d85bba487e59764724d73843d5f53be0df0a004c22d9a3b93cf71afe2cf9adbc39a68be43aa4c67e9d96481a55f380ad15d13b9a3fdacf933886d4e58cf236e50934a02a5a804791c4f2b03064610f7505220ac5dcbb368fa39adc2630aef08ab1d464e1bb862d7efb26072019aef0e5b5f52e2b136877d558f1072d91359e01f93d5958b040a06fc46c58058c3718ae9d4b36a09ea54568d834ab3c682c30515879567df967cf1c2ce35c77098c61b01e688b81193771591f2b93f89f7c5636c9aa64d364bb243fd8cc7a8503a9a6b2626cb753eb235d9d8223695ea50b737d04bc5dbf016b512d90db314e9933cb10abdb6ea3204d0c8cb7dde2d4c2282be6eeb8b88efd1afca94ee4ef473ddc5bbec0741d2164d63dea17b1a32d78a7083e98a6cbeaa271751bc7b3b244b52b168afadc4b5f74a46f00f86901c82ae3735cf34d1665a8ad0f254bc81386273dd658506a097a201dc4c9cc21537c9eca8ab53e78bf3ad8bf0ce22df7f13cfdb8cc50a8f63dc67ab7d0b3ec0cc0c3599a2ed4a375eebf81ba0040ca5496870bf1ad2a55541933a3e78109de45b9168bf8610113a6945d3ad3eafb7e35b66ddf549d4fdcae0db86eededa57948ab14e896ed61d522d60a0391d21f6d50447810716a2df89d559b2ef6fa50ae72cc1b934c624f62c00060a8de7beecb10e3ed5c314cb587e1004a94dbcbf16969521358748da08f406e041bcfcef6c0193a304ae9e2970ca9e79c924e637290e4b06d913cd82d5fa6fba00e54e16b114d196a584688ff1f536da1d73c34d86e0d704832c893c1741a638d58821e9f7d729a07b07ebc005ff9c51597c69d537642013c26e0fb02844e5dc56450d35b5506dc6855eb230bc6068d4a415895ba7d9ed8306e0b71cb5608b6f06de33dea9cd70b12578cff86bcd8dacfaba7ffba29d5da99f4f4b0891cdac3c46f00e84e01c82ae3735cdb4c029f14be5db4b6fea416f390a71fc3a644e684b68d054b96a54a09a479fc17eb91b22612a3867cf3adb7bfce6364fb045b23be536aaab3aa4bfd66ccaad40c60245fecc1ba91f9549b52b2d6bf5b48805cc361af777eefe3910b2aa331f4ff0fe7cd71f623501109bef7bb4fbfe167d2f6d273e2e42981e70158b5d9057343c09850e7c43ee60fc4430d6cadc931d34acd90ebe250ba26fd9dc7e1843b8c18b484104a50c18b70c730e02bdc7a0424c7f48672f3f6b3cd0631c0bfc5dee40c092c06e6a7644c95c6e2abeacd6e86748fc0892659611d9ffd72a2acd481eca62bfe9ca3fd46be0e3a85cd05e443f8b3d2e66dfd28f714a4bd3884246be1ac1c61b1b62ff180064ac0eff4a3235f5aff59d203b06aa910fa9f2342b91f756c5bc5d288cb5088e29fa7d51c2059830284c71cb68f30eaf6e9f9318f380d817f517e0662e524fd1fec6f00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 64),
(108, 0x00f86101c82ae3735cc24f1652c7d3ef9500ffafaf5e708dc84d83180eec1907c12a362da4ba244c7535209e93c4a1df844c22a4371129a535304ddd47236306eca4f7179e11e5e96a048d4ca497aa43a93f501f01529534f2aa194cfd8570c33a5d122f09e8d4a716bb8f0d9f7ab53f234669b8aadc3909ed9a30c8bbadd0cc3a28474b244faac03e3b349a262f132f1304927c5541459f0fc102b5f6c65d9ebaeefc53599da821d42e6f8378ebfc90152521513017f9eff385132424ffb8c289869949437087bd5f8f076bf5a6e0fd13d8497b09d93ca2cd477746b436f623f22c9355aa3321fbf95518f29ded14e5689d9bf5ba03c4c2e07e42cf75320d682fb2b805e15d8e10e3f3e1db1d7accd935170c6c45c4a9b7104dd1a56287a9082e902ec0207089ab0282b4a61c6bfdf33bfbd976510f9e7c3c5675960dcaf7ccdf952aba143d7f23fc6a6d293ef34a7a0fbfb14c5cfe7e4b9470097c00c41f66cc4f0c13fc6f00f85d01c82ae3735cf74e701f950d7b40c710d2117d900c217311eba1e72e428a01c689dfc2f8761c913f3f5bf1fd4f637f0d0e11d8c434e289fb1f53e98b200f1b18310dab9c4cda522b14160e3945fe11f8f4ace276ef24f36de9a65d9328977bf354c68fe0b2fa68f602ff89ed73639906ae1a4156e79b216797d98d91ba7d27182d7591613761c8d8ab6eaddca19b63bde2bc79e8f36da8e2f35ca81b5fa8c8e1e196acaba78937adc8381491c1748ff0db6d8361d2e93256dbabed47cfe542b30fd7f8f1b3ff94826277f655ed9a4c20cb7e12fa59facedb9dd7b6610e0fc47c2bc6ca1b27e2f4c5143e7be9ba31f72b3d0276c6f6fcfe484bc3a6d23decec3b33ad1ea8b2a3b1caca5bfb70b457197ea5b517b43c02841050f2c40ea5a6fd6108b438f9b5eac12dca34e8eecfdfc19c318ad537a46bb55f1617fe30e514fd7b969a7a0a0c9685d0c88d49d80a3246bc885b1ef1395c9daab2110b2225096f00f85301c82ae3735cf14e7b041c699e7dc3a14186adc8fe3256c91d872ff5c70de035ade41574203bdd5bc86b16d6871e37bf79d974d955a29a784e97e6000ed03dacecf504d986edc20232870904ea5b790e3854f3cebf14260869f285bb2ff8d966020b6b84a77ba4b44addc75c5aedbdcccd07649cf9b6afebb88412712ea0823c180eef442f692d881bf28b7363926044a3242a4c1d02722744d77804c65b9fbdbe66c9b6a49a91a21dd8c47cf756b3aa42d4df8b3d09d631d8200a69125d078628ff4914d26939577d044d1b71442a698d749352deb8f28143ae17688cda036c790b01fcb52c0722a7b497ca38c2712d49c7cf6b0358ed78573f78e0e2dcbb48d8d8457c33d14c1a11652813fd6cb2863ba57f3b8aeb36b48db6332126ee1cad54eb8b8f320f43b35039a6baae559b617e78a209b987205facf94365c5d64d47de1b91617c35da20143a73ef539dc02ab31cc8236f00e86001c82ae3735cfe4b7bcd7b3b62bc041a2338794c2035ca835c1d914b76d8e38f4e6c23dc4d31cccec05ee191289679a37d4fca845f37f2ef70c145d7979971e01602b3bd4ce20dd7e7f98c66adc8464947ca77c3808df4a7c1c2df07a644daac06088c99b36e27fd50ff851becd9abfbef11782fc8b23ed9a1a60b36d427af2c12c5eed5346a5d00bd68f86fed63d6e815aa8b5859548acce5c80012c6a8418b8671cdf2ba9976aadbc10773530cde9131e4d669c89c9a0202593336411112300e0b19bca474f8981bf843b226bf7fa0e927b98333b781afd45cc4f4b5a833accd3f5803c3dee380b931758e24aadb5fc780abb201edfc1cfd769501404dfb5a60991d3a73155655209f808f166c32d7685e549201ce79276c716eca7900f70d11a1f4ff14892cf13655f5221d4b17950748363619d7c42ed0e3eb7d779a9aa117e2318cb73066a254bd6a6cc6da998488e94b8b65efbdfd610b936989b414f0486f000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 128);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registros_persona`
--

DROP TABLE IF EXISTS `registros_persona`;
CREATE TABLE IF NOT EXISTS `registros_persona` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ID_PERSONA` int(11) NOT NULL,
  `FECHA` date NOT NULL,
  `HORA_ENTRADA` time NOT NULL,
  `HORA_SALIDA` time DEFAULT NULL,
  `AUTO` decimal(1,0) NOT NULL COMMENT 'Registro automatico (1) Registro Ingresado manual (0) por inconsistencias.',
  `OBSERVACIONES` text,
  `CONTABILIZADO` decimal(1,0) NOT NULL DEFAULT '0' COMMENT '1: Contabilizado 0:No contabilizado',
  PRIMARY KEY (`ID`),
  KEY `FK_REGISTROS_PERSONA_PERSONAS` (`ID_PERSONA`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=27 ;

--
-- Volcado de datos para la tabla `registros_persona`
--

INSERT INTO `registros_persona` (`ID`, `ID_PERSONA`, `FECHA`, `HORA_ENTRADA`, `HORA_SALIDA`, `AUTO`, `OBSERVACIONES`, `CONTABILIZADO`) VALUES
(16, 108, '2013-05-01', '09:43:39', '09:46:43', 1, 'Registro desde de App.', 0),
(17, 108, '2013-05-14', '09:54:08', '10:05:57', 1, 'Registro desde de App.', 0),
(18, 108, '2013-05-15', '10:08:19', '10:14:47', 1, 'Registro desde de App.', 0),
(19, 108, '2013-05-16', '10:17:01', '10:17:41', 1, 'Registro desde de App.', 0),
(20, 108, '2013-05-17', '10:17:51', '10:18:01', 1, 'Registro desde de App.', 0),
(21, 108, '2013-05-20', '10:18:10', '10:18:22', 1, 'Registro desde de App.', 0),
(22, 108, '2013-05-20', '10:18:31', '10:18:38', 1, 'Registro desde de App.', 0),
(23, 108, '2013-05-20', '10:19:48', '10:20:21', 1, 'Registro desde de App.', 0),
(24, 108, '2013-05-20', '10:20:00', '10:37:00', 0, 'Registro desde de App.', 0),
(25, 108, '2013-05-20', '10:43:57', '10:44:52', 1, 'Registro desde de App.', 0),
(26, 108, '2013-05-20', '10:46:29', '10:46:39', 1, 'Registro desde de App.', 0);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `persona_huellas`
--
ALTER TABLE `persona_huellas`
  ADD CONSTRAINT `fk_personahuella_dedosdigitalpersona` FOREIGN KEY (`fingerIndex`) REFERENCES `dedos_digitalpersona` (`fingerindex`),
  ADD CONSTRAINT `FK_PERSONA_HUELLAS_PERSONAS` FOREIGN KEY (`ID_PERSONA`) REFERENCES `personas` (`ID`);

--
-- Filtros para la tabla `registros_persona`
--
ALTER TABLE `registros_persona`
  ADD CONSTRAINT `FK_REGISTROS_PERSONA_PERSONAS` FOREIGN KEY (`ID_PERSONA`) REFERENCES `personas` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
