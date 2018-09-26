CREATE DATABASE computerpart;
USE computerpart;
DROP TABLE IF EXISTS `computer_part`;

CREATE TABLE computer_part (
	`id` INT(8) NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(40) NOT NULL,
    `quantity` INT(255) NOT NULL,
	`required` BIT(1) NOT NULL,
	PRIMARY KEY (id))
	ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO computer_part(name, quantity, required) VALUES ('gggg', 55, true);
