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
#### INSERT INTO bundle(name, price, expiration, availability) VALUES ('Offer3', 2.0,'2019-10-16','2019-09-20');
#### INSERT INTO bundle(name, price, expiration, availability) VALUES ('Offer4', 4.0,'2019-10-16','2019-09-20');
## 4. Fix Database Connection Settings
Go to src/main/resources and edit the ![application.properties](https://imgur.com/k5A9tV9) file so that username and password match the settings of your database

## 5. The project should be able to run 
To test it go to your browser and type http://localhost:8080/bundle. If all bundles appear that means the project has been succesfully set up!

## 6. Download "Advanced REST client"
Download "Advanced REST client" extension for Google Chrome to test the API locally.

## 7. How to use API - Requests and Responses

#### a. View all bundles
Request: GET - localhost:8080/bundle  
Response: All bundles in JSON format

#### b. Create new bundle
Request: POST - localhost:8080/bundle  
   
Body:{  
"name": "nameOfBundle",  
  "price": 0.0,  
  "expiration" : "2019-10-31",  
"availability": "2019-10-16"  
}  
  
  Response: The newly created bundle in JSON format
#### c. Delete bundle with code 1
Request: DELETE - localhost:8080/bundle/1
Response: TRUE if successfully deleted
