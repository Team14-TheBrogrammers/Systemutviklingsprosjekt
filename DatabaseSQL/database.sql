DROP TABLE IF EXISTS private_customer;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS address_zip;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS recipe_ingredients;
DROP TABLE IF EXISTS ingredients;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS recipe_instructions;
DROP TABLE IF EXISTS recipe;
DROP TABLE IF EXISTS positions;
DROP TABLE IF EXISTS employee;

CREATE TABLE private_customer(
  customer_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  tlf CHAR(8)NOT NULL,
  address VARCHAR(30) NOT NULL,
  zip INTEGER(4) NOT NULL,
  email_address VARCHAR(20)
);

CREATE TABLE company(
  company_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NOT NULL,
  tlf CHAR(8) NOT NULL,
  address VARCHAR(30) NOT NULL,
  zip CHAR(4) NOT NULL,
  email_address VARCHAR(20)
);


CREATE TABLE orders(
  order_id INTEGER PRIMARY KEY AUTO_INCREMENT,
  payment_status BIT,
  order_date DATE,
  delivery_date DATE,
  delivery_time DOUBLE,
  address VARCHAR(30) NOT NULL,
  total_price INTEGER NOT NULL
);

CREATE TABLE recipe(
  recipe_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY
);

CREATE TABLE ingredients (
  ingredient_name VARCHAR(30) UNIQUE NOT NULL PRIMARY KEY
);

CREATE TABLE recipe_ingredients (
  recipe_name VARCHAR(30) NOT NULL,
  ingredient_name VARCHAR(30) NOT NULL,
  quantity CHAR(30),
  PRIMARY KEY (recipe_name,ingredient_name),
  CONSTRAINT recipeIng_fk FOREIGN KEY (recipe_name)
  REFERENCES recipe(recipe_name),
  CONSTRAINT recipeIng_fk2 FOREIGN KEY (ingredient_name)
  REFERENCES ingredients(ingredient_name)
);

CREATE TABLE recipe_instructions(
  recipe_name VARCHAR(30) NOT NULL,
  step_number INTEGER NOT NULL,
  description VARCHAR(100),
  PRIMARY KEY (recipe_name, step_number),
  CONSTRAINT recipeIns_fk FOREIGN KEY (recipe_name)
  REFERENCES recipe(recipe_name)
);

CREATE TABLE menu(
  price INTEGER NOT NULL,
  recipe_name VARCHAR(30) NOT NULL,
  CONSTRAINT menu_fk FOREIGN KEY (recipe_name)
  REFERENCES recipe(recipe_name)
);

CREATE TABLE employee(
  emp_id INTEGER PRIMARY KEY,
  last_name VARCHAR(30) NOT NULL,
  first_name VARCHAR(30) NOT NULL,
  tlf CHAR(8)NOT NULL,
  hiredate DATE,
  position_id INTEGER,
  username VARCHAR(10) NOT NULL,
  password VARCHAR(15) NOT NULL,
  email_address VARCHAR(30)
);

CREATE TABLE positions(
  position_id INTEGER NOT NULL,
  position_name VARCHAR(10) NOT NULL,
  emp_id INTEGER,
  CONSTRAINT positions_fk FOREIGN KEY (emp_id)
  REFERENCES employee(emp_id)
);

