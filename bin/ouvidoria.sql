SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ouvidoria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ouvidoria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ouvidoria` DEFAULT CHARACTER SET utf8 ;
USE `ouvidoria` ;

-- -----------------------------------------------------
-- Table `ouvidoria`.`paciente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ouvidoria`.`paciente` (
  `prontuario` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`prontuario`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ouvidoria`.`familiar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ouvidoria`.`familiar` (
  `cpfPaciente` VARCHAR(15) NOT NULL,
  `prontuarioPaciente` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`cpfPaciente`),
  INDEX `prontuario_idx` (`prontuarioPaciente` ASC) VISIBLE,
  CONSTRAINT `prontuario`
    FOREIGN KEY (`prontuarioPaciente`)
    REFERENCES `ouvidoria`.`paciente` (`prontuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ouvidoria`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ouvidoria`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cpf` VARCHAR(15) NOT NULL,
  `nome` VARCHAR(80) NULL,
  `senha` VARCHAR(32) NOT NULL,
  `email` VARCHAR(255) NULL,
  `data_nascimento` VARCHAR(45) NULL,
  `tipo_usuario` ENUM('PACIENTE', 'FAMILIAR', 'OUVIDOR') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ouvidoria`.`manifestacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ouvidoria`.`manifestacao` (
  `protocolo` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(500) NULL,
  `titulo` VARCHAR(32) NOT NULL,
  `id_usuario` INT NOT NULL,
  `tipo_manifestacao` VARCHAR(16) NOT NULL,
  `status_manifestacao` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`protocolo`)
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ouvidoria`.`resposta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ouvidoria`.`resposta` (
  `idResposta` INT NOT NULL AUTO_INCREMENT,
  `protocolo` INT NOT NULL,
  `descricao` VARCHAR(500) NULL,
  `idOuvidor` INT NULL,
  `nomeOuvidor` VARCHAR(80) NULL,
  `dataResposta` DATETIME NOT NULL,
  `autor` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idResposta`),
  INDEX `protocolo_idx` (`protocolo` ASC) VISIBLE,
  INDEX `idUsuario_idx` (`idOuvidor` ASC) VISIBLE,
  INDEX `idOuvidor_idx` (`idOuvidor` ASC) VISIBLE,
  CONSTRAINT `protocolo`
    FOREIGN KEY (`protocolo`)
    REFERENCES `ouvidoria`.`manifestacao` (`protocolo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
) ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `ouvidoria`.`ouvidor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ouvidoria`.`ouvidor` (
  `idOuvidor` INT NOT NULL,
  PRIMARY KEY (`idOuvidor`)
) ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
