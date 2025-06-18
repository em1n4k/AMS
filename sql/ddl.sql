-- Creation of the students table
CREATE TABLE IF NOT EXISTS Students (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    facultyNumber VARCHAR(100) NOT NULL
);

-- Creation of the teachers table
CREATE TABLE IF NOT EXISTS Teachers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    patronymic VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

-- Creation of the attendance table
CREATE TABLE IF NOT EXISTS Attendance (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    date DATE NOT NULL,
    status ENUM('PRESENT','ABSENT','VALID_REASON','LATE') NOT NULL,
    FOREIGN KEY (student_id) REFERENCES Students(id)
        ON DELETE CASCADE
);

-- Creation of the administrators table
CREATE TABLE IF NOT EXISTS Admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
