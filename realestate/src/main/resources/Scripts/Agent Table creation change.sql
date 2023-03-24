
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

