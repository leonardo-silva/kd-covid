-- -----------------------------------------------------
-- Schema kd-covid
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kd-covid`;
CREATE SCHEMA IF NOT EXISTS `kd-covid` DEFAULT CHARACTER SET utf8;
USE `kd-covid`;

-- -----------------------------------------------------
-- Table `kd-covid`.`PATIENT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `kd-covid`.`PATIENT` (
  `hasSymptom` TINYINT NOT NULL,
  `hasFever` TINYINT NOT NULL,
  `hasSmellTasteLoss` TINYINT NOT NULL,
  `hasRunningNose` TINYINT NOT NULL,
  `hasTiredness` TINYINT NOT NULL,
  `hasCough` TINYINT NOT NULL,
  `hasBreathProblem` TINYINT NOT NULL,
  `hasPurpleMouth` TINYINT NOT NULL,
  `hasSoreThroat` TINYINT NOT NULL,
  `hasChestPressure` TINYINT NOT NULL,
  `hasNOASymptom` TINYINT NOT NULL,
  `duration1to3Days` TINYINT NOT NULL,
  `duration4to7Days` TINYINT NOT NULL,
  `duration8to10Days` TINYINT NOT NULL,
  `duration11to14Days` TINYINT NOT NULL,
  `duration14PlusDays` TINYINT NOT NULL,
  `wentOutOfCity` TINYINT NOT NULL,
  `hadContactWithOutsider` TINYINT NOT NULL,
  `hadContactWithInfected` TINYINT NOT NULL,
  `hadLast14DaysNOA` TINYINT NOT NULL,
  `age1to15Years` TINYINT NOT NULL,
  `age16to30Years` TINYINT NOT NULL,
  `age31to45Years` TINYINT NOT NULL,
  `age46to60Years` TINYINT NOT NULL,
  `age60PlusYears` TINYINT NOT NULL,
  `hasDiabetes` TINYINT NOT NULL,
  `hasHeartProblem` TINYINT NOT NULL,
  `hasChronicKidney` TINYINT NOT NULL,
  `hasChronicRespiratory` TINYINT NOT NULL,
  `hasHighPressure` TINYINT NOT NULL,
  `hasCancer` TINYINT NOT NULL,
  `dontHavePriorDisease` TINYINT NOT NULL,
  `priorDiseasesDWA` TINYINT NOT NULL,
  `male` TINYINT NOT NULL,
  `female` TINYINT NOT NULL,
  `otherGender` TINYINT NOT NULL,
  `citySalinas` TINYINT NOT NULL,
  `cityAracuai` TINYINT NOT NULL,
  `cityTaiobeiras` TINYINT NOT NULL,
  `cityCoronelMurta` TINYINT NOT NULL,
  `citySaoJoaoDoParaiso` TINYINT NOT NULL,
  `cityJanauba` TINYINT NOT NULL,
  `cityPorteirinha` TINYINT NOT NULL,
  `cityMontesClaros` TINYINT NOT NULL,
  `otherCity` TINYINT NOT NULL,
  `neighborhoodName` VARCHAR(40) NOT NULL,
  `zipCode` CHAR(10) NOT NULL,
  `fullNameDWA` TINYINT NOT NULL,
  `fullName` VARCHAR(40) NULL,
  `phoneDWA` TINYINT NOT NULL,
  `phone` CHAR(16) NULL,
  `emailDWA` TINYINT NOT NULL,
  `email` VARCHAR(40) NULL)
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
    
    IF (NEW.phoneDWA AND NEW.emailDWA) OR (phone = '' AND email = '')
		THEN
			SIGNAL SQLSTATE '02000' SET MESSAGE_TEXT = phoneOrEmailErrorMessage;
	ELSEIF NOT(NEW.citySalinas XOR NEW.cityAracuai XOR NEW.cityTaiobeiras XOR NEW.cityCoronelMurta XOR NEW.citySaoJoaoDoParaiso XOR NEW.cityJanauba XOR NEW.cityPorteirinha XOR NEW.cityMontesClaros XOR NEW.otherCity)
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
DELIMITER ;