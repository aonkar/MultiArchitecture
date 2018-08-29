CREATE TABLE `user_management`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `mobile` VARCHAR(45) NULL,
  `age` INT NOT NULL,
  `category` VARCHAR(45) NOT NULL,
  `active` BOOLEAN NOT NULL DEFAULT true,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user_id`)); 