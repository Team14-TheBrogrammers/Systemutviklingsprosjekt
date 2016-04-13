/* Database */

DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Positions;
DROP TABLE IF EXISTS Recipe_orders;
DROP TABLE IF EXISTS Instruction;
DROP TABLE IF EXISTS Recipe_ingredient;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Private_customer;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Postal;


CREATE TABLE Postal(
  zip INTEGER(4) NOT NULL,
  postal VARCHAR(30) NOT NULL,
  PRIMARY KEY(zip)
);

CREATE TABLE Customer(
  customer_id INTEGER AUTO_INCREMENT NOT NULL,
  address VARCHAR(30) NOT NULL,
  zip INTEGER(4) NOT NULL,
  PRIMARY KEY(customer_id),
  FOREIGN KEY(zip) REFERENCES Postal(zip)
);

CREATE TABLE Private_customer(
  private_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  phone CHAR(8)NOT NULL,
  email_address VARCHAR(20),
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Company(
  company_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  phone CHAR(8) NOT NULL,
  email_address VARCHAR(20),
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Orders(
  order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  payment_status BOOLEAN,
  order_date DATE NOT NULL,
  delivery_date DATE NOT NULL,
  delivery_time DOUBLE NOT NULL,
  address VARCHAR(30),
  zip INTEGER(4),
  total_price INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Recipe(
  recipe_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY,
  recipe_type VARCHAR (20) NOT NULL,
  price DOUBLE NOT NULL
);

CREATE TABLE Ingredient (
  ingredient_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY
);

CREATE TABLE Recipe_ingredient (
  recipe_name VARCHAR(30) NOT NULL,
  ingredient_name VARCHAR(30) NOT NULL,
  quantity CHAR(30),
  PRIMARY KEY (recipe_name,ingredient_name),
  CONSTRAINT recipeIng_fk FOREIGN KEY (recipe_name)
  REFERENCES Recipe(recipe_name),
  CONSTRAINT recipeIng_fk2 FOREIGN KEY (ingredient_name)
  REFERENCES Ingredient(ingredient_name)
);

CREATE TABLE Instruction(
  recipe_name VARCHAR(30) NOT NULL,
  step_number INTEGER NOT NULL,
  description VARCHAR(100),
  PRIMARY KEY (recipe_name, step_number),
  CONSTRAINT recipeIns_fk FOREIGN KEY (recipe_name)
  REFERENCES Recipe(recipe_name)
);

CREATE TABLE Recipe_orders(
  order_id INTEGER NOT NULL,
  recipe_name VARCHAR(30) NOT NULL,
  quantity CHAR(30),
  PRIMARY KEY(order_id, recipe_name),
  FOREIGN KEY(order_id) REFERENCES Orders(order_id),
  FOREIGN KEY(recipe_name) REFERENCES Recipe(recipe_name)
);

CREATE TABLE Positions(
  position_id INTEGER AUTO_INCREMENT NOT NULL,
  position_name VARCHAR(10) NOT NULL,
  PRIMARY KEY(position_id)
);

CREATE TABLE Employee(
  emp_id INTEGER AUTO_INCREMENT PRIMARY KEY,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  phone INTEGER(8) NOT NULL,
  date_of_employment DATE,
  position_id INTEGER NOT NULL,
  username VARCHAR(10) NOT NULL,
  password VARCHAR(15) NOT NULL,
  email_address VARCHAR(30),
  CONSTRAINT employee_fk FOREIGN KEY(position_id)
  REFERENCES Positions(position_id)
);

CREATE TABLE Order_recipe(
  order_id INTEGER NOT NULL,
  recipe_name VARCHAR(30) NOT NULL,
  quantity INTEGER NOT NULL,
  PRIMARY KEY(order_id, recipe_name),
  FOREIGN KEY(order_id) REFERENCES Order(order_id),
  FOREIGN KEY(recipe_name) REFERENCES Recipe(recipe_name)
);

/*Insert setninger: */
INSERT INTO Positions(position_name) VALUES('Manager');
INSERT INTO Positions(position_name) VALUES('Cashier');
INSERT INTO Positions(position_name) VALUES('Cook');
INSERT INTO Positions(position_name) VALUES('Driver');

INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address)
VALUES('Sund', 'Ingunn', 98765432, '2015-03-04', 1, 'ingunnsu', 'ingunnIzc00l', 'ingunnsu@yahoo.no');

INSERT INTO Ingredient(ingredient_name) VALUES('Egg');
INSERT INTO Ingredient(ingredient_name) VALUES('Milk');
INSERT INTO Ingredient(ingredient_name) VALUES('Flour');
INSERT INTO Ingredient(ingredient_name) VALUES('Tomatoes');
INSERT INTO Ingredient(ingredient_name) VALUES('Cheese');
INSERT INTO Ingredient(ingredient_name) VALUES('Lettuce');
INSERT INTO Ingredient(ingredient_name) VALUES('Beef');
INSERT INTO Ingredient(ingredient_name) VALUES('Meat');

INSERT INTO Recipe(recipe_name, price) VALUES('Pancakes', 20);
INSERT INTO Recipe(recipe_name, price) VALUES('Spaghetti', 45);
INSERT INTO Recipe(recipe_name, price) VALUES('Tomato Soup', 10);

INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES('Pancakes', 'Egg', '8 pcs');
INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES('Pancakes', 'Milk', '2 cups');
INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES('Tomato Soup', 'Tomatoes', '2 tbsp');
INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES('Spaghetti', 'Meat', '2 tsp');
INSERT INTO Recipe_ingredient(recipe_name, ingredient_name, quantity) VALUES('Spaghetti', 'Cheese', '2 cups');

INSERT INTO Instruction(recipe_name, step_number, description) VALUES('Pancakes', 1, 'Whisk together milk, butter, and egg.');
INSERT INTO Instruction(recipe_name, step_number, description) VALUES('Pancakes', 2, 'Heat a large skillet.');

INSERT INTO Postal(zip, postal) VALUES(1234, 'Trondheim');

INSERT INTO Customer(customer_id, address, zip) VALUES(DEFAULT, 'Trondheims gate 1', 1234);
INSERT INTO Customer(address, zip) VALUES('Trondheims gate 2', 1234);
INSERT INTO Private_customer(last_name, first_name, phone, email_address, customer_id)
  VALUES('Sund', 'Ingunn', 12345678, 'ingunn@sund.no', 1);


---SQL-setninger under her:
SELECT MAX(order_id) AS count FROM Orders;
--INSERT:
INSERT INTO Orders(payment_status, delivery_date, delivery_time, address, total_price, customer_id) VALUES();

INSERT INTO Orders(delivery_date, delivery_time, address, total_price, customer_id)
VALUES('2015-1-1', '2.0', 'kikijij', 1, 1);

INSERT INTO Orders(delivery_date, delivery_time, address, total_price, customer_id)
VALUES(?, ?, ?, ?, ?);

--Oppdatere deliverytime til en order:
UPDATE Orders SET delivery_date = '2015-01-02', delivery_time = 3 WHERE order_id = 1;


--Hent ut om customer eksiterer:
SELECT * FROM Customer WHERE customer_id = 1;

--Hente ut orders
SELECT * FROM Orders;

--Registrere en ny manager:
INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address)
  VALUES();