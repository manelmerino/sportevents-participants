CREATE TABLE `participant` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'identificador numèric del corredor',
  `created_at` datetime NOT NULL COMMENT 'data de creació del registre',
  `updated_at` datetime NOT NULL COMMENT 'data del últim canvi del registre',
  `tax_id` varchar(11) COLLATE utf8_spanish_ci NOT NULL COMMENT 'codi identificació fiscal',
  `birthday` date NOT NULL COMMENT 'data de naixement del participant',
  `name` varchar(25) COLLATE utf8_spanish_ci NOT NULL COMMENT 'nom del participant',
  `first_surname` varchar(25) COLLATE utf8_spanish_ci NOT NULL COMMENT 'primer cognom del participant',
  `second_surname` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'segón cognom del participant',
  `gender` enum('Home','Dona') COLLATE utf8_spanish_ci NOT NULL COMMENT 'gènere del participant',
  `address` varchar(80) COLLATE utf8_spanish_ci NOT NULL COMMENT 'adreça on viu el participant (carrer, número, pis...)',
  `zip_code` varchar(7) COLLATE utf8_spanish_ci NOT NULL COMMENT 'codi postal de l''adreça del participant',
  `city` varchar(45) COLLATE utf8_spanish_ci NOT NULL COMMENT 'nom de la població corresponent a l''adreça postal del participant',
  `country` varchar(25) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'païs de l''adreça del participant',
  `personal_phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'telèfon del participant',
  `emergency_phone` varchar(15) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'telèfon en cas de urgència durant la cursa',
  `email` varchar(45) COLLATE utf8_spanish_ci DEFAULT NULL COMMENT 'correu electrònic del participant',
  `active` int(1) NOT NULL COMMENT 'estat de les dades del participant. Actives o inactives',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci COMMENT='dades dels corredors que s''han inscrit en alguna cursa';