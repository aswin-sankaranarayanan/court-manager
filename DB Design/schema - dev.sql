-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema  court_manager_dev
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS ` court_manager_dev` ;

-- -----------------------------------------------------
-- Schema  court_manager_dev
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS ` court_manager_dev` DEFAULT CHARACTER SET utf8 ;
USE ` court_manager_dev` ;

-- -----------------------------------------------------
-- Table ` court_manager_dev`.`CLIENT`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`CLIENT` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`CLIENT` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(100) NOT NULL,
  `OWNER` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `LAST_UPDATED_ON` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL DEFAULT 'ADMIN',
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `NAME_UNIQUE` (`NAME` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` court_manager_dev`.`CLIENT_BRANCH`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`CLIENT_BRANCH` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`CLIENT_BRANCH` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `LOCATION` VARCHAR(45) NOT NULL,
  `MONTHLY_FEE` FLOAT NOT NULL,
  `GUEST_FEE` FLOAT NOT NULL,
  `COURT_FEE` FLOAT NOT NULL,
  `CLIENT_FK` INT NOT NULL,
  `LAST_UPDATED_ON` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL DEFAULT 'ADMIN',
  PRIMARY KEY (`ID`),
  INDEX `fk_CLIENT_BRANCH_CLIENT_idx` (`CLIENT_FK` ASC),
  CONSTRAINT `fk_CLIENT_BRANCH_CLIENT`
    FOREIGN KEY (`CLIENT_FK`)
    REFERENCES ` court_manager_dev`.`CLIENT` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` court_manager_dev`.`BATCH`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`BATCH` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`BATCH` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `TYPE` VARCHAR(45) NOT NULL,
  `START_TIME` TIME NOT NULL,
  `END_TIME` TIME NOT NULL,
  `BRANCH_FK` INT NOT NULL,
  `LAST_UPDATED_ON` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` VARCHAR(45) NULL DEFAULT 'ADMIN',
  PRIMARY KEY (`ID`),
  INDEX `fk_BATCH_CLIENT_BRANCH1_idx` (`BRANCH_FK` ASC),
  CONSTRAINT `fk_BATCH_CLIENT_BRANCH1`
    FOREIGN KEY (`BRANCH_FK`)
    REFERENCES ` court_manager_dev`.`CLIENT_BRANCH` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` court_manager_dev`.`MEMBERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`MEMBERS` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`MEMBERS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NAME` VARCHAR(45) NOT NULL,
  `CONTACT_NUMBER` VARCHAR(45) NOT NULL,
  `CURRENT_PAYMENT_CYCLE` INT NOT NULL,
  `NEXT_PAYMENT` DATE NOT NULL,
  `LAST_PAYMENT` DATE NULL,
  `IS_PAYMENT_DUE` TINYINT NULL,
  `TOTAL_DUE` FLOAT NULL,
  `LAST_UPDATED_ON` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` VARCHAR(45) NULL DEFAULT 'ADMIN',
  `BATCH_FK` INT NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `CONTACT_NUMBER_UNIQUE` (`CONTACT_NUMBER` ASC),
  INDEX `fk_MEMBERS_BATCH1_idx` (`BATCH_FK` ASC),
  CONSTRAINT `fk_MEMBERS_BATCH1`
    FOREIGN KEY (`BATCH_FK`)
    REFERENCES ` court_manager_dev`.`BATCH` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` court_manager_dev`.`MEMBER_PAYMENTS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`MEMBER_PAYMENTS` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`MEMBER_PAYMENTS` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `PAID_ON` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `ACKNOWLEDGED_BY` VARCHAR(45) NOT NULL,
  `LAST_UPDATED_ON` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL DEFAULT 'ADMIN',
  `MEMBERS_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `fk_MEMBER_PAYMENTS_MEMBERS1_idx` (`MEMBERS_ID` ASC),
  CONSTRAINT `fk_MEMBER_PAYMENTS_MEMBERS1`
    FOREIGN KEY (`MEMBERS_ID`)
    REFERENCES ` court_manager_dev`.`MEMBERS` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` court_manager_dev`.`GUEST`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`GUEST` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`GUEST` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `CONTACT_DETAILS` VARCHAR(45) NOT NULL,
  `START_TIME` TIME NOT NULL,
  `END_TIME` TIME NOT NULL,
  `FEE` FLOAT NOT NULL,
  `FEE_PAID_ON` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `IS_DUE` TINYINT NOT NULL DEFAULT 0,
  `LAST_UPDATED_ON` DATETIME NOT NULL,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL,
  `BRANCH_FK` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`, `START_TIME`),
  INDEX `fk_GUEST_CLIENT_BRANCH1_idx` (`BRANCH_FK` ASC),
  CONSTRAINT `fk_GUEST_CLIENT_BRANCH1`
    FOREIGN KEY (`BRANCH_FK`)
    REFERENCES ` court_manager_dev`.`CLIENT_BRANCH` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table ` court_manager_dev`.`CLIENT_USERS`
-- -----------------------------------------------------
DROP TABLE IF EXISTS ` court_manager_dev`.`CLIENT_USERS` ;

CREATE TABLE IF NOT EXISTS ` court_manager_dev`.`CLIENT_USERS` (
  `ID` INT NOT NULL,
  `NAME` VARCHAR(45) NOT NULL,
  `EMAIL` VARCHAR(45) NOT NULL,
  `BRANCH_FK` INT NOT NULL,
  `IS_ADMIN` TINYINT NOT NULL DEFAULT 0,
  `LAST_UPDATED_ON` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATED_BY` VARCHAR(45) NOT NULL DEFAULT 'ADMIN',
  PRIMARY KEY (`ID`),
  INDEX `fk_CLIENT_USERS_CLIENT_BRANCH1_idx` (`BRANCH_FK` ASC),
  CONSTRAINT `fk_CLIENT_USERS_CLIENT_BRANCH1`
    FOREIGN KEY (`BRANCH_FK`)
    REFERENCES ` court_manager_dev`.`CLIENT_BRANCH` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
