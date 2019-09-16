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

Insert Mock Data
#### INSERT INTO bundle(name, price, expiration, availability) VALUES ('Free4All', 0.0,'2019-10-16','2019-09-20');
#### INSERT INTO bundle(name, price, expiration, availability) VALUES ('Offer2', 5.0,'2019-10-16','2019-09-20');
#### INSERT INTO bundle(name, price, expiration, availability) VALUES ('Bundle', 2.0,'2019-10-16','2019-09-20');
#### INSERT INTO bundle(name, price, expiration, availability) VALUES ('Offer4', 4.0,'2019-10-16','2019-09-20');
## 4. Fix Database Connection Settings
Go to src/main/resources and edit the application.properties file so that username and password match the settings of your database  
![application.properties](https://i.imgur.com/0h0qqTb.png)

## 5. The project should be able to run 
To test it, run the project, go to your browser and type http://localhost:8080/swagger-ui.html. If Swagger UI appears the project has been succesfully set up!
![home](https://i.imgur.com/IMh1kLj.png)
## 6. Swagger UI
Use Swagger User Interface to test the API. There are functions for:
#### a. Retrieving all bundles
The bundles can also be sorted by price with the "orderby" parameter which can take the values "asc"/"desc".
#### b. Creating a new bundle
A new bundle can be created and stored in the database if the required parameters are added to the body in JSON format.
#### c. Searching bundles by name
Bundles can be searched by name with the "name" parameter. The resulting bundles can also be sorted with the "orderby" parameter.
#### d. Deleting a bundle
A specific bundle can be deleted by supplying its code.
#### e. Retrieving bundles by code
A specific bundle can be retrieved by supplying its code.
#### f. Updating the availability date of a bundle
The availability date of a bundle can be updated by suplying its code and the new availability date in the body (JSON format).
