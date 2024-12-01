CREATE DATABASE site_cosmus;
USE site_cosmus;

CREATE TABLE users(
	`id` VARCHAR(100) UNIQUE NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `email` VARCHAR(70) NOT NULL,
    `password` VARCHAR(240) NOT NULL,
    `role` VARCHAR(32) NOT NULL
);

CREATE TABLE posts(
	`id` VARCHAR(100) UNIQUE NOT NULL,
    `userId` VARCHAR(100) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    `file` MEDIUMBLOB NOT NULL,
    
    FOREIGN KEY (`userId`) REFERENCES users(`id`)
)
