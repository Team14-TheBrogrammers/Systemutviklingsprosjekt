/* Database */

DROP TABLE IF EXISTS Positions;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Menu;
DROP TABLE IF EXISTS Recipe_instructions;
DROP TABLE IF EXISTS Recipe_ingredients;
DROP TABLE IF EXISTS Ingredients;
DROP TABLE IF EXISTS Recipe;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Private_customer;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Address_zip;

CREATE TABLE Address_zip(
  address_id INTEGER NOT NULL AUTO_INCREMENT,
  address VARCHAR(30) NOT NULL,
  zip INTEGER(4) NOT NULL,
  PRIMARY KEY(address_id)
);

CREATE TABLE Customer(
  customer_id INTEGER AUTO_INCREMENT NOT NULL,
  address_id INTEGER NOT NULL,
  PRIMARY KEY(customer_id),
  FOREIGN KEY(address_id) REFERENCES Address_zip(address_id)
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

CREATE TABLE Order(
  order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  payment_status BOOLEAN,
  order_date DATE,
  delivery_date DATE,
  delivery_time DOUBLE,
  address VARCHAR(30) NOT NULL,
  total_price INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Recipe(
  recipe_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY
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

CREATE TABLE Recipe_instruction(
  recipe_name VARCHAR(30) NOT NULL,
  step_number INTEGER NOT NULL,
  description VARCHAR(100),
  PRIMARY KEY (recipe_name, step_number),
  CONSTRAINT recipeIns_fk FOREIGN KEY (recipe_name)
  REFERENCES Recipe(recipe_name)
);

CREATE TABLE Menu(
  recipe_name VARCHAR(30) NOT NULL,
  price INTEGER NOT NULL,
  PRIMARY KEY(recipe_name, price),
  CONSTRAINT menu_fk FOREIGN KEY (recipe_name)
  REFERENCES Recipe(recipe_name)
);

CREATE TABLE Position(
  position_id INTEGER AUTO_INCREMENT NOT NULL,
  position_name VARCHAR(10) NOT NULL,
  PRIMARY KEY(position_id),
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
  REFERENCES Postition(position_id)
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
INSERT INTO Position(position_name) VALUES('Manager');
INSERT INTO Position(position_name) VALUES('Cashier');
INSERT INTO Position(position_name) VALUES('Cook');
INSERT INTO Position(position_name) VALUES('Driver');

INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address)
VALUES('Sund', 'Ingunn', 98765432, '2015-03-04', 1, 'ingunnsu', 'ingunnIzc00l', 'ingunnsu@yahoo.no');

INSERT INTO Ingredient(ingredient_name) VALUES('Egg');
INSERT INTO Ingredient(ingredient_name) VALUES('Milk');
INSERT INTO Ingredient(ingredient_name) VALUES('Flour');
INSERT INTO Ingredient(ingredient_name) VALUES('Tomatoes');
INSERT INTO Ingredient(ingredient_name) VALUES('Cheese');
INSERT INTO Ingredient(ingredient_name) VALUES('Lettuce');
INSERT INTO Ingredient(ingredient_name) VALUES('Beef');

INSERT INTO Recipe(recipe_name) VALUES('Pancakes');
INSERT INTO Recipe(recipe_name) VALUES('Spaghetti');
INSERT INTO Recipe(recipe_name) VALUES('Tomato Soup');

INSERT INTO Recipe_ingredient(recipe_id, ingredient_name, quantity) VALUES(1, 'Egg', '8 pcs');
INSERT INTO Recipe_ingredient(recipe_id, ingredient_name, quantity) VALUES(1, 'Milk', '2 cups');
--INSERT INTO Recipe_ingredients(recipe_id, ingredient_name, quantity) VALUES(2, 'Tomatoes, '2 tbsp');
--INSERT INTO Recipe_ingredients(recipe_id, ingredient_name, quantity) VALUES(3, 4, '2 tsp');
--INSERT INTO Recipe_ingredients(recipe_id, ingredient_name, quantity) VALUES(3, 5, '2 cups');

INSERT INTO Recipe_instruction(recipe_id, step_number, description) VALUES(1, 1, 'Whisk together milk, butter, and egg.');
INSERT INTO Recipe_instruction(recipe_id, step_number, description) VALUES(1, 2, 'Heat a large skillet.');

INSERT INTO Customer(customer_id) VALUES(DEFAULT);
INSERT INTO Address_zip(address, zip) VALUES('asdasDASDASD', 1234);
INSERT INTO Customer(address_id) VALUES(1)
INSERT INTO Private_customer(last_name, first_name, phone, address, zip, email_address, customer_id)
  VALUES('Sund', 'Ingunn', 12345678, 'Asdasds', 123, 'ingunn@sund.no', 1);
INSERT INTO Private_customer(private_id, last_name, first_name, phone, email_address, customer_id)


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