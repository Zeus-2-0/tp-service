drop table if exists `tpdb`.`trading_partner_detail`;
CREATE TABLE IF NOT EXISTS `tpdb`.`trading_partner_detail` (
    `trading_partner_sk` VARCHAR(36) NOT NULL COMMENT 'UUID will be the primary key',
    `trading_partner_id` VARCHAR(100) NOT NULL COMMENT 'The id of the trading partner that is shared externally',
    `name` VARCHAR(100) NULL COMMENT 'Name of the trading partner',
    `description` VARCHAR(100) NULL COMMENT 'A short description about the trading partner',
    `sender_id` VARCHAR(100) NOT NULL COMMENT 'The sender id that is associated with the trading partner',
    `receiver_id` VARCHAR(100) NOT NULL COMMENT 'Receiver id associated with the trading partner',
    `line_of_business_type_code` VARCHAR(10) NOT NULL COMMENT 'The line of business associated with the trading partner',
    `state_type_code` VARCHAR(10) NOT NULL COMMENT 'The state associated with the trading partner',
    `marketplace_type_code` VARCHAR(10) NOT NULL COMMENT 'The marketplace type code associated with the trading partner',
    `created_date` DATETIME NOT NULL COMMENT 'Date of creation of the record',
    `updated_date` DATETIME NOT NULL COMMENT 'Date that the record was updated',
    PRIMARY KEY (`trading_partner_sk`),
    UNIQUE INDEX `trading_partner_id_UNIQUE` (`trading_partner_id` ASC) VISIBLE)
    ENGINE = InnoDB
    COMMENT = 'This table will contain the details of a trading partner'