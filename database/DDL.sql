-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema kd-covid
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kd-covid` ;

-- -----------------------------------------------------
-- Schema kd-covid
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kd-covid` DEFAULT CHARACTER SET utf8 ;
USE `kd-covid` ;

-- -----------------------------------------------------
-- Table `kd-covid`.`PATIENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kd-covid`.`PATIENT` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `hasSymptom` TINYINT NOT NULL,
  `hasFever` TINYINT NULL,
  `hasSmellTasteLoss` TINYINT NULL,
  `hasRunningNose` TINYINT NULL,
  `hasTiredness` TINYINT NULL,
  `hasCough` TINYINT NULL,
  `hasBreathProblem` TINYINT NULL,
  `hasPurpleMouth` TINYINT NULL,
  `hasSoreThroat` TINYINT NULL,
  `hasChestPressure` TINYINT NULL,
  `hasDiarrhea` TINYINT NULL,
  `hasNOASymptom` TINYINT NULL,
  `duration1to3Days` TINYINT NULL,
  `duration4to7Days` TINYINT NULL,
  `duration8to10Days` TINYINT NULL,
  `duration11to14Days` TINYINT NULL,
  `duration14PlusDays` TINYINT NULL,
  `wentOutOfCity` TINYINT NULL,
  `hadContactWithOutsider` TINYINT NULL,
  `hadContactWithInfected` TINYINT NULL,
  `hadLast14DaysNOA` TINYINT NULL,
  `age1to15Years` TINYINT NULL,
  `age16to30Years` TINYINT NULL,
  `age31to45Years` TINYINT NULL,
  `age46to60Years` TINYINT NULL,
  `age60PlusYears` TINYINT NULL,
  `hasDiabetes` TINYINT NULL,
  `hasHeartProblem` TINYINT NULL,
  `hasChronicKidney` TINYINT NULL,
  `hasChronicRespiratory` TINYINT NULL,
  `hasHighPressure` TINYINT NULL,
  `hasCancer` TINYINT NULL,
  `dontHavePriorDisease` TINYINT NULL,
  `priorDiseasesDWA` TINYINT NULL,
  `male` TINYINT NULL,
  `female` TINYINT NULL,
  `otherGender` TINYINT NULL,
  `otherCity` TINYINT NULL,
  `cityName` VARCHAR(30) NOT NULL,
  `neighborhoodName` VARCHAR(40) NULL,
  `zipCode` CHAR(10) NULL,
  `fullNameDWA` TINYINT NULL,
  `fullName` VARCHAR(40) NULL,
  `phone` CHAR(16) NULL,
  `email` VARCHAR(40) NULL,
  `visitedPoints` MULTIPOINT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP(),
  `updated_at` TIMESTAMP NULL,
  `android_id` VARCHAR(25) NULL,
  `resultCode` TINYINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

USE `kd-covid`;

DELIMITER $$
USE `kd-covid`$$
CREATE DEFINER = CURRENT_USER TRIGGER `kd-covid`.`PATIENT_BEFORE_INSERT` BEFORE INSERT ON `PATIENT` FOR EACH ROW
BEGIN

	/* Error messages */
	DECLARE phoneOrEmailErrorMessage TEXT DEFAULT 'Você deve informar um número de telefone ou um endereço de e-mail!';
	DECLARE cityErrorMessage TEXT DEFAULT 'Você deve selecionar uma cidade!';
    DECLARE genderErrorMessage TEXT DEFAULT 'Você deve informar o seu gênero!';
	/* Error messages */

	DECLARE phone VARCHAR(16) DEFAULT TRIM(NEW.phone);
	DECLARE email VARCHAR(40) DEFAULT TRIM(NEW.email);
	DECLARE cityName VARCHAR(30) DEFAULT TRIM(NEW.cityName);
    
    IF (phone = '' AND email = '')
		THEN
			SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = phoneOrEmailErrorMessage;
	ELSEIF (NEW.cityName = '')
		THEN
			SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = cityErrorMessage;
	ELSEIF NOT(NEW.male XOR NEW.female XOR NEW.otherGender)
		THEN
			SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = genderErrorMessage;
    ELSE
		SET NEW.phone = phone;
		SET NEW.email = email;
	END IF;
    
END$$

USE `kd-covid`$$
CREATE DEFINER = CURRENT_USER TRIGGER `kd-covid`.`PATIENT_BEFORE_UPDATE` BEFORE UPDATE ON `PATIENT` FOR EACH ROW
BEGIN
	SET NEW.updated_at = CURRENT_TIMESTAMP();
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
