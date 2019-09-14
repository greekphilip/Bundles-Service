# Bundles-Service
Spring Boot RESTful API Service.
Create new bundles, delete exisiting ones, update availability dates, query by name, code and price (ascending/descending)
# Instructions - How to run
## 1. Download Files
## 2. Import project to IDE as Maven Project (Tested on Netbeans and Eclipse)
## 3. Set up MySQL database
Install MySQL, open the command-line terminal and run the following commands
#### CREATE DATABASE service_database;
#### USE service_database;
#### CREATE TABLE bundle(code INT UNSIGNED AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, price DOUBLE NOT NULL, expiration DATE, availability DATE NOT NULL);
