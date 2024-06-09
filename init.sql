-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS studentManagement;

-- Check if the user exists before creating it
-- CREATE USER IF NOT EXISTS 'root'@'%' IDENTIFIED BY 'root2024';
--
-- -- Grant privileges to the user
-- GRANT ALL PRIVILEGES ON studentManagement.* TO 'root'@'%';

-- Flush privileges to ensure that all changes are applied
       -- Table structure for table `courses`
--

CREATE TABLE `courses` (
                           `Title` varchar(100) DEFAULT NULL,
                           `Id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`Title`, `Id`) VALUES
                                          ('Computer sci', 1),
                                          ('Food Science', 2),
                                          ('IT', 3),
                                          ('telecom', 4);

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
                            `Name` varchar(100) DEFAULT NULL,
                            `RegNo` varchar(100) NOT NULL,
                            `CourseId` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`Name`, `RegNo`, `CourseId`) VALUES
                                                         ('mwambia', 'FS01/3005/2020', 2),
                                                         ('Rogers', 'IS17/3005/2019', 3),
                                                         ('brian', 'J17/3235/2019', 1),
                                                         ('Munene', 'TL17/2235/2019', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
    ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
    ADD PRIMARY KEY (`RegNo`),
  ADD KEY `CourseId` (`CourseId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
    MODIFY `Id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `students`
--
ALTER TABLE `students`
    ADD CONSTRAINT `students_ibfk_1` FOREIGN KEY (`CourseId`) REFERENCES `courses` (`Id`);
COMMIT;
FLUSH PRIVILEGES;
