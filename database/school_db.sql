CREATE DATABASE IF NOT EXISTS school_database CHARACTER SET utf8 COLLATE utf8_spanish_ci;

SHOW CREATE DATABASE school_database;

USE school_database;

CREATE TABLE IF NOT EXISTS STUDENTS (
    student_id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(30) NOT NULL,
    first_name VARCHAR(25) NOT NULL,
    last_name VARCHAR(25) NOT NULL,
    PRIMARY KEY (student_id)
) ENGINE=INNODB CHARACTER SET utf8 COLLATE utf8_spanish_ci;