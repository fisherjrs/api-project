delimiter $$

CREATE TABLE `prices` (
  `id` int(11) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `last_updated` datetime DEFAULT NULL,
  `creation_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4$$

