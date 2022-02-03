drop table if exists `tpdb`.`security_user`;
drop table if exists `tpdb`.`role`;
drop table if exists `tpdb`.`authority`;
drop table if exists `tpdb`.`user_role`;
drop table if exists `tpdb`.`role_authority`;
CREATE TABLE IF NOT EXISTS `tpdb`.`security_user` (
    `user_id` VARCHAR(36) NOT NULL COMMENT 'User id for the user',
    `username` VARCHAR(100) NOT NULL COMMENT 'Username of the user',
    `password` VARCHAR(200) NOT NULL COMMENT 'Password of the user',
    `account_not_expired` BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Indicates if the account is expired or not',
    `account_not_locked` BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Indicates if the account is locked or not',
    `credentials_not_expired` BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Indicates if the credentials is expired or not',
    `enabled` BOOLEAN NOT NULL DEFAULT 1 COMMENT 'Indicates if the account is enabled or not',
    `created_date` DATETIME NOT NULL COMMENT 'The date when the user was created',
    `updated_date` DATETIME NOT NULL COMMENT 'The date when the user was last updated',
    PRIMARY KEY (`user_id`))
    ENGINE = InnoDB
    COMMENT = 'List of users';

CREATE TABLE IF NOT EXISTS `tpdb`.`role` (
    `role_id` VARCHAR(36) NOT NULL COMMENT 'The role id for the role',
    `role_name` VARCHAR(100) NOT NULL COMMENT 'The role name associated with the role',
    `created_date` DATETIME NOT NULL COMMENT 'The date when the role was created',
    `updated_date` DATETIME NOT NULL COMMENT 'The date last when the role was updated',
    PRIMARY KEY (`role_id`))
    ENGINE = InnoDB
    COMMENT = 'The table that contains the roles for the service';

CREATE TABLE IF NOT EXISTS `tpdb`.`authority` (
    `authority_id` VARCHAR(36) NOT NULL COMMENT 'The authority id associated with the authority',
    `permission` VARCHAR(100) NOT NULL COMMENT 'The permission that is associated with the authority',
    `created_date` DATETIME NOT NULL COMMENT 'The date when the authority was created',
    `updated_date` DATETIME NOT NULL COMMENT 'The date when the authority was last updated',
    PRIMARY KEY (`authority_id`))
    ENGINE = InnoDB
    COMMENT = 'The table that contains the list of authorities';

CREATE TABLE IF NOT EXISTS `tpdb`.`user_role` (
    `user_id` VARCHAR(36) NOT NULL COMMENT 'User id that is mapped to the role',
    `role_id` VARCHAR(36) NOT NULL COMMENT 'Role that is mapped to the user',
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `tpdb`.`security_user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `tpdb`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'The mapping table, to map the users with respective roles.';

CREATE TABLE IF NOT EXISTS `tpdb`.`role_authority` (
    `role_id` VARCHAR(36) NOT NULL COMMENT 'Role that is mapped to the authority',
    `authority_id` VARCHAR(36) NOT NULL COMMENT 'Authority that is mapped to the role',
    INDEX `role_auth_id_idx` (`role_id` ASC) VISIBLE,
    INDEX `authority_id_idx` (`authority_id` ASC) VISIBLE,
    CONSTRAINT `role_auth_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `tpdb`.`role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    CONSTRAINT `authority_id`
    FOREIGN KEY (`authority_id`)
    REFERENCES `tpdb`.`authority` (`authority_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    COMMENT = 'The mapping table, to map roles to authorities.';