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
DROP TABLE IF EXISTS Adress_zip;

CREATE TABLE Adress_zip(
  adress_id INTEGER NOT NULL AUTO_INCREMENT,
  address VARCHAR(30) NOT NULL,
  zip INTEGER(4) NOT NULL,
  PRIMARY KEY(adress_id)
);

CREATE TABLE Customer(
  customer_id INTEGER AUTO_INCREMENT NOT NULL,
  adress_id INTEGER NOT NULL,
  PRIMARY KEY(customer_id),
  FOREIGN KEY(adress_id) REFERENCES Adress_zip(adress_id)
);

CREATE TABLE Private_customer(
  private_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  phone CHAR(8)NOT NULL,
  email_adress VARCHAR(20),

  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Company(
  company_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  phone CHAR(8) NOT NULL,
  email_adress VARCHAR(20),

  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Orders(
  order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  payment_status BOOLEAN,
  delivery_date DATE,
  delivery_time DOUBLE,
  adress VARCHAR(30) NOT NULL,
  total_price INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Recipe(
  recipe_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  recipe_name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE Ingredients (
  ingredient_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ingredient_name VARCHAR(30) UNIQUE NOT NULL
);

CREATE TABLE Recipe_ingredients (
  recipe_id INTEGER NOT NULL,
  ingredient_id INTEGER NOT NULL,
  quantity CHAR(10),
  PRIMARY KEY (recipe_id, ingredient_id),
  CONSTRAINT recipeIng_fk FOREIGN KEY (recipe_id) REFERENCES Recipe(recipe_id)
);

CREATE TABLE Recipe_instructions(
  recipe_id INTEGER NOT NULL,
  step_number INTEGER NOT NULL,
  description VARCHAR(100),
  PRIMARY KEY (recipe_id,step_number),
  CONSTRAINT recipeIns_fk FOREIGN KEY (recipe_id) REFERENCES Recipe(recipe_id)
);

CREATE TABLE Menu(
  price INTEGER NOT NULL,
  recipe_id INTEGER NOT NULL,
  CONSTRAINT menu_fk FOREIGN KEY (recipe_id) REFERENCES Recipe(recipe_id)
);

CREATE TABLE Employee(
  emp_id INTEGER AUTO_INCREMENT PRIMARY KEY,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  phone CHAR(8) NOT NULL,
  hiredate DATE,
  position_id INTEGER NOT NULL,
  username VARCHAR(10) NOT NULL,
  password VARCHAR(15) NOT NULL,
  email_address VARCHAR(30)
);

CREATE TABLE Positions(
  position_id INTEGER AUTO_INCREMENT NOT NULL,
  position_name VARCHAR(10) NOT NULL,
  PRIMARY KEY(position_id)
);

/*Insert setninger: */
INSERT INTO Positions(position_name) VALUES('Cachier');

INSERT INTO Employee(last_name, first_name, phone, hiredate, position_id, username, password, email_address)
VALUES('Sund', 'Ingunn', 98765432, '2015-03-04', 1, 'ingunnsu', 'ingunnIzc00l', 'ingunnsu@yahoo.no');

INSERT INTO Ingredients(ingredient_name) VALUES('Egg');
INSERT INTO Ingredients(ingredient_name) VALUES('Milk');
INSERT INTO Ingredients(ingredient_name) VALUES('Flour');
INSERT INTO Ingredients(ingredient_name) VALUES('Tomatoes');
INSERT INTO Ingredients(ingredient_name) VALUES('Cheese');
INSERT INTO Ingredients(ingredient_name) VALUES('Lettuce');
INSERT INTO Ingredients(ingredient_name) VALUES('Beef');

INSERT INTO Recipe(recipe_name) VALUES('Pancakes');
INSERT INTO Recipe(recipe_name) VALUES('Spaghetti');
INSERT INTO Recipe(recipe_name) VALUES('Tomato Soup');

INSERT INTO Recipe_ingredients(recipe_id, ingredient_id, quantity) VALUES(1, 1, '8 pcs');
INSERT INTO Recipe_ingredients(recipe_id, ingredient_id, quantity) VALUES(1, 2, '2 cups');
INSERT INTO Recipe_ingredients(recipe_id, ingredient_id, quantity) VALUES(2, 4, '2 tbsp');
INSERT INTO Recipe_ingredients(recipe_id, ingredient_id, quantity) VALUES(3, 4, '2 tsp');
INSERT INTO Recipe_ingredients(recipe_id, ingredient_id, quantity) VALUES(3, 5, '2 cups');

INSERT INTO Recipe_instructions(recipe_id, step_number, description) VALUES(1, 1, 'Whisk together milk, butter, and egg.');
INSERT INTO Recipe_instructions(recipe_id, step_number, description) VALUES(1, 2, 'Heat a large skillet.');

INSERT INTO Customer(customer_id) VALUES(DEFAULT);
INSERT INTO Adress_zip(address, zip) VALUES('asdasDASDASD', 1234);
INSERT INTO Customer(adress_id) VALUES(1)
INSERT INTO Private_customer(last_name, first_name, phone, adress, zip, email_adress, customer_id)
  VALUES('Sund', 'Ingunn', 12345678, 'Asdasds', 123, 'ingunn@sund.no', 1);
INSERT INTO Private_customer(private_id, last_name, first_name, phone, email_adress, customer_id)


---SQL-setninger under her:
SELECT MAX(order_id) AS count FROM Orders;
--INSERT:
INSERT INTO Orders(payment_status, delivery_date, delivery_time, adress, total_price, customer_id) VALUES();

INSERT INTO Orders(delivery_date, delivery_time, adress, total_price, customer_id)
VALUES('2015-1-1', '2.0', 'kikijij', 1, 1);

INSERT INTO Orders(delivery_date, delivery_time, adress, total_price, customer_id)
VALUES(?, ?, ?, ?, ?);

--Oppdatere deliverytime til en order:
UPDATE Orders SET delivery_date = '2015-01-02', delivery_time = 3 WHERE order_id = 1;


--Hent ut om customer eksiterer:
SELECT * FROM Customer WHERE customer_id = 1;