/* Database */

DROP TABLE IF EXISTS Order_recipe;
DROP TABLE IF EXISTS Recipe_instruction;
DROP TABLE IF EXISTS Recipe_ingredient;
DROP TABLE IF EXISTS Private_customer;
DROP TABLE IF EXISTS Company;
DROP TABLE IF EXISTS Orders;
DROP TABLE IF EXISTS Employee;
DROP TABLE IF EXISTS Positions;
DROP TABLE IF EXISTS Customer;
DROP TABLE IF EXISTS Stock;
DROP TABLE IF EXISTS Ingredient;
DROP TABLE IF EXISTS Recipe;
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
  email_address VARCHAR(40),
  phone INTEGER(8) UNIQUE NOT NULL,
  PRIMARY KEY(customer_id),
  FOREIGN KEY(zip) REFERENCES Postal(zip)
);

CREATE TABLE Private_customer(
  private_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Company(
  company_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  company_name VARCHAR(30) NOT NULL,
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
  customer_id INTEGER NOT NULL,
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id)
);

CREATE TABLE Recipe(
  recipe_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY,
  recipe_type ENUM ('VEGAN', 'VEGETARIAN', 'PESCATARIAN', 'MEATLOVER'),
  price DOUBLE NOT NULL
);

CREATE TABLE Ingredient(
  ingredient_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY
);

CREATE TABLE Stock(
  stock_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ingredient_name VARCHAR(30) UNIQUE NOT NULL,
  quantity DOUBLE NOT NULL ,
  measurement VARCHAR(20) NOT NULL,
  FOREIGN KEY (ingredient_name) REFERENCES Ingredient(ingredient_name)
);

CREATE TABLE Recipe_ingredient (
  recipe_name VARCHAR(30) NOT NULL,
  ingredient_name VARCHAR(30) NOT NULL,
  quantity CHAR(30),
  PRIMARY KEY (recipe_name,ingredient_name),
  CONSTRAINT recipeIng_fk FOREIGN KEY (recipe_name)
  REFERENCES Recipe(recipe_name)
    ON DELETE CASCADE,
  CONSTRAINT recipeIng_fk2 FOREIGN KEY (ingredient_name)
  REFERENCES Ingredient(ingredient_name)
    ON DELETE CASCADE
);

CREATE TABLE Recipe_instruction(
  recipe_name VARCHAR(30) NOT NULL,
  step_number INTEGER NOT NULL,
  description VARCHAR(500),
  PRIMARY KEY (recipe_name, step_number),
  CONSTRAINT recipeIns_fk FOREIGN KEY (recipe_name)
  REFERENCES Recipe(recipe_name)
    ON DELETE CASCADE
);

CREATE TABLE Order_recipe(
  order_id INTEGER NOT NULL,
  recipe_name VARCHAR(30) NOT NULL,
  quantity INTEGER NOT NULL,
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
  phone INTEGER(8) UNIQUE NOT NULL,
  date_of_employment DATE,
  position_id INTEGER NOT NULL,
  username VARCHAR(10) NOT NULL,
  password VARCHAR(15) NOT NULL,
  email_address VARCHAR(30),
  CONSTRAINT employee_fk FOREIGN KEY(position_id)
  REFERENCES Positions(position_id)
);

CREATE TABLE Frequency(
  frequency_id INTEGER NOT NULL AUTO_INCREMENT,
  frequency_day INTEGER NOT NULL,
  frequency_week INTEGER NOT NULL,
  frequency_month INTEGER NOT NULL,
  PRIMARY KEY (frequency_id)
);

CREATE TABLE Subscriptions(
  subs_id INTEGER AUTO_INCREMENT NOT NULL,
  frequency INTEGER NOT NULL,
  order_id INTEGER NOT NULL,
  customer_id INTEGER NOT NULL,
  frequency_id INTEGER NOT NULL,
  PRIMARY KEY(subs_id),
  FOREIGN KEY(order_id) REFERENCES Orders(order_id),
  FOREIGN KEY(customer_id) REFERENCES Customer(customer_id),
  FOREIGN KEY(frequency_id) REFERENCES Frequency(frequency_id)
);

/*Insert setninger: */
INSERT INTO Positions(position_name) VALUES('Manager');
INSERT INTO Positions(position_name) VALUES('Cashier');
INSERT INTO Positions(position_name) VALUES('Cook');
INSERT INTO Positions(position_name) VALUES('Driver');

INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address)
VALUES('Sund', 'Ingunn', 98765432, '2015-03-04', 1, 'ingunnsu', 'ingunnIzc00l', 'ingunnsu@yahoo.no');

INSERT INTO Recipe (recipe_name, recipe_type, price)
VALUES
  ('Pancakes', 'VEGETARIAN', 100),
  ('Tomato Soup', 'VEGETARIAN', 100),
  ('Spaghetti bolognese', 'MEATLOVER', 100);

INSERT INTO Ingredient (ingredient_name)
VALUES
  ('Egg'),
  ('Milk'),
  ('All-purpose flour'),
  ('Tomatoes'),
  ('Cheese'),
  ('Baking powder'),
  ('Salt'),
  ('Onion'),
  ('Vegetable oil'),
  ('Olive oil'),
  ('Lettuce'),
  ('Tomato purée'),
  ('Beef'),
  ('White sugar'),
  ('Brown sugar');

INSERT INTO Recipe_ingredient (recipe_name, ingredient_name, quantity)
VALUES
  ('Pancakes', 'Egg', '1 pc'),
  ('Pancakes', 'Milk', '1 cup'),
  ('Pancakes', 'All-purpose flour', '1 cup'),
  ('Pancakes', 'Salt', '1 teaspoon'),
  ('Pancakes', 'White sugar', '2 tablespoons'),
  ('Pancakes', 'Vegetable oil', '2 tablespoons'),
  ('Tomato soup', 'Onion', '1 pc'),
  ('Tomato soup', 'Olive oil', '2 tablespoon'),
  ('Tomato soup', 'Tomato purée', '2 teaspoons');

INSERT INTO Recipe_instruction (recipe_name, step_number, description)
VALUES
  ('Pancakes', 1, 'In a large bowl, mix flour, sugar, baking powder and salt.
    Make a well in the center, and pour in milk, egg and oil. Mix until smooth.'),
  ('Pancakes', 2, 'Heat a lightly oiled griddle or frying pan over medium high heat.
    Pour or scoop the batter onto the griddle, using approximately 1/4 cup for each pancake.
    Brown on both sides and serve hot.'),
  ('Tomato soup', 1, 'Prepare your vegetables'),
  ('Tomato soup', 2, 'Spoon 2 tbsp olive oil into a large heavy-based pan and heat it over a low heat.'),
  ('Tomato soup', 3, 'When the pan is ready, add 2 tsp of tomato purée, then stir it around so it turns the vegetables red.');

INSERT INTO Stock(ingredient_name, quantity, measurement) VALUES ("Egg", 60, "Pcs");
INSERT INTO Stock(ingredient_name, quantity, measurement) VALUES ("Milk", 20, "L");
INSERT INTO Stock(ingredient_name, quantity, measurement) VALUES ("All-purpose flour", 40, "Kg");

INSERT INTO Postal VALUES(1234, 'Trondheim');

INSERT INTO Customer(address, zip, email_address, phone) VALUES('Trondheims gate 1', 1234, 'ingunn@sund.no', 23123333);
INSERT INTO Customer(address, zip, email_address, phone) VALUES('Trondheims gate 1', 1234, 'saasds@asdasd.no', 21312333);
INSERT INTO Private_customer(last_name, first_name, customer_id)
VALUES('Sund', 'Ingunn', 1);



/**

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
**/
/**--Sette inn i Customer tabell:**/

/*
INSERT INTO Customer(address, zip, phone, email_address) VALUES(?, ?, ?, ?);

/** Sette inn i Private Customer tabell:

INSERT INTO Private_customer(private_id, last_name, first_name, customer_id) VALUES(?, ?, ?, ?);

SELECT * FROM Customer NATURAL JOIN Company;
SELECT * FROM Company WHERE customer_id = ?;
SELECT * FROM Private_customer WHERE customer_id = ?;


/** Select orders to a customer
SELECT * FROM Customer NATURAL JOIN Orders WHERE customer_id = ?;

/** Select recipes matching order
SELECT * FROM Recipe NATURAL JOIN Order_recipe WHERE Order_recipe.order_id = ?;

/** Hente ut Company og Private_customer:
SELECT * FROM Company NATURAL JOIN Customer WHERE customer_id  = ?;

SELECT * FROM Private_customer NATURAL JOIN Customer WHERE customer_id = ?;


/** Update Customer addresse
UPDATE Customer SET address = ? AND zip = ? WHERE customer_id = ?;

/** Check if a zip address is in the dataabse
SELECT * FROM Postal WHERE zip = ?;

/** Select all customer ids
SELECT customer_id FROM Customer;

/** Check if a phone is in use
SELECT phone FROM Customer WHERE phone = ?;

/**Sette inn verdi i Customer tabell:
INSERT INTO Customer(address, zip) VALUES('Trondheims gate 2', 1234);
/** i private customer:
INSERT INTO Private_customer(last_name, first_name, phone, email_address, customer_id)
VALUES('Sund', 'Ingunn', 12345678, 'ingunn@sund.no', 1);

/** i company:
INSERT INTO Company(company_name, customer_id) VALUES('', 2);

*/