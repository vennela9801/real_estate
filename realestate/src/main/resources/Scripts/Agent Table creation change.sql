CREATE TABLE `databaseProject1`.`home` (
  `HomeID` int NOT NULL AUTO_INCREMENT,
  `owner` varchar(225) DEFAULT NULL,
  `rate` int DEFAULT NULL,
  `Floorspace`int DEFAULT NULL,
  `Floors` int DEFAULT NULL,
  `Bathrooms` int DEFAULT NULL,
  `Bedrooms` int DEFAULT NULL,
  `Landsize` int DEFAULT NULL,
  `Yearconstructed` int DEFAULT NULL,
  `Hometype` varchar(50) DEFAULT NULL,
  `description` text,
  `Address_id` int not null,
  `AgentID` int not null,
  `availabilitystatus` varchar(45) DEFAULT 'Available',
  PRIMARY KEY (`HomeID`),
  UNIQUE KEY `HomeID_UNIQUE` (`HomeID`),
  KEY `AddressID_idx` (`Address_id`),
  CONSTRAINT `Address_id` FOREIGN KEY (`Address_id`) REFERENCES `address` (`Address_id`),
  CONSTRAINT `AgentID` FOREIGN KEY (`AgentID`) REFERENCES `agent` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `address` (
  `Address_id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `zip` int NOT NULL,
  `county` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT 'MI',
  PRIMARY KEY (`Address_id`),
  KEY `address_idx` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



Select home.HomeID, ho.OwnerName,home.FloorSpace, home.Floors, home.Bathrooms, home.LandSize, home.YearConstructed, home.HomeType,
 address.Address_id, address.address, address.city, address.county, address.zip, home.rate
From databaseProject1.home, databaseProject1.address , databaseProject1.homeowners ho
Where home.Address_id = address.Address_id and  
ho.OwnerName = 'Asem Jhon' and 
address.city = 'Sterling Heights';


CREATE TABLE `databaseProject1`.`hometype` (
  `HomeTypeID` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  PRIMARY KEY (`HomeTypeID`),
  UNIQUE KEY `HomeTypeID_UNIQUE` (`HomeTypeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `databaseProject1`.`homeowners` (
  `ownerId` int not null AUTO_INCREMENT,
  `SSN` varchar(16) NOT NULL,
  `OwnerName` varchar(225) NOT NULL,
  `NumOfDependents` int DEFAULT NULL,
  `Age` int DEFAULT NULL,
  `Income` decimal(5,4) DEFAULT NULL,
  `Profession` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ownerId`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `databaseProject1`.`agent` (
  `AgentID` int NOT NULL AUTO_INCREMENT,
  `AgentFName` varchar(45) NOT NULL,
  `AgentLName` varchar(45) DEFAULT NULL,
  `contact` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `address` varchar(45) NOT NULL,
  `zip` int default 0,
  `city` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT 'MI',
  PRIMARY KEY (`AgentID`),
  UNIQUE KEY `AgentID_UNIQUE` (`AgentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

