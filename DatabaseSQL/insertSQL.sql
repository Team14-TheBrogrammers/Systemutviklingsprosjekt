INSERT INTO Postal VALUES
(7010, 'TRONDHEIM'),
(7011, 'TRONDHEIM'),
(7012, 'TRONDHEIM'),
(7013, 'TRONDHEIM'),
(7014, 'TRONDHEIM'),
(7015, 'TRONDHEIM'),
(7016, 'TRONDHEIM'),
(7017, 'TRONDHEIM'),
(7018, 'TRONDHEIM'),
(7019, 'TRONDHEIM'),
(7020, 'TRONDHEIM'),
(7021, 'TRONDHEIM'),
(7022, 'TRONDHEIM'),
(7023, 'TRONDHEIM'),
(7024, 'TRONDHEIM'),
(7025, 'TRONDHEIM'),
(7026, 'TRONDHEIM'),
(7027, 'TRONDHEIM'),
(7028, 'TRONDHEIM'),
(7029, 'TRONDHEIM'),
(7030, 'TRONDHEIM'),
(7031, 'TRONDHEIM'),
(7032, 'TRONDHEIM'),
(7033, 'TRONDHEIM'),
(7034, 'TRONDHEIM'),
(7035, 'TRONDHEIM'),
(7036, 'TRONDHEIM'),
(7037, 'TRONDHEIM'),
(7038, 'TRONDHEIM'),
(7039, 'TRONDHEIM'),
(7040, 'TRONDHEIM'),
(7041, 'TRONDHEIM'),
(7042, 'TRONDHEIM'),
(7043, 'TRONDHEIM'),
(7044, 'TRONDHEIM'),
(7045, 'TRONDHEIM'),
(7046, 'TRONDHEIM'),
(7047, 'TRONDHEIM'),
(7048, 'TRONDHEIM'),
(7049, 'TRONDHEIM'),
(7050, 'TRONDHEIM'),
(7051, 'TRONDHEIM'),
(7052, 'TRONDHEIM'),
(7053, 'RANHEIM'),
(7054, 'RANHEIM'),
(7055, 'RANHEIM'),
(7056, 'RANHEIM'),
(7057, 'JONSVATNET'),
(7058, 'JAKOBSLI'),
(7059, 'JAKOBSLI'),
(7067, 'TRONDHEIM'),
(7068, 'TRONDHEIM'),
(7069, 'TRONDHEIM'),
(7070, 'BOSBERG'),
(7071, 'TRONDHEIM'),
(7072, 'HEIMDAL'),
(7074, 'SPONGDAL'),
(7075, 'TILLER'),
(7078, 'SAUPSTAD'),
(7079, 'FLATÅSEN'),
(7080, 'HEIMDAL'),
(7081, 'SJETNEMARKA'),
(7082, 'KATTEM'),
(7083, 'LEINSTRAND'),
(7088, 'HEIMDAL'),
(7089, 'HEIMDAL'),
(7091, 'TILLER'),
(7092, 'TILLER'),
(7093, 'TILLER'),
(7097, 'SAUPSTAD'),
(7098, 'SAUPSTAD'),
(7099, 'FLATÅSEN');

INSERT INTO Positions(position_name) VALUES('Manager');
INSERT INTO Positions(position_name) VALUES('Cashier');
INSERT INTO Positions(position_name) VALUES('Cook');
INSERT INTO Positions(position_name) VALUES('Driver');

INSERT INTO Employee(last_name, first_name, phone, date_of_employment, position_id, username, password, email_address) VALUES
('Kirkhorn', 'Knut', 12345678, '2016-01-01', 1, 'knut', 'erkul', 'knut@kirkhorn.no'),
('Sund', 'Ingunn', 32125678, '2016-01-01', 4, 'ingunn', 'ingunn', 'ingunn@sund.no'),
('Uybengkee', 'Nicole', 54364233, '2016-01-01', 2, 'Nicole', 'Nic', 'nicolee@hotmail.com'),
('Påsche', 'Anders', 54682390, '2016-04-25', 3, 'Anders', 'anderrrs', 'anderss@hotmail.com');

INSERT INTO Customer(customer_id, address, zip, email_address, phone) VALUES
(DEFAULT, 'Ferjemannsveien 10', 7014, 'cateringAS@hotmail.com', 67543976),
(DEFAULT, 'Ferjemannsveien 2', 7014, 'fjeremann@online.no', 45292912),
(DEFAULT, 'Kongens gate 10', 7011, 'cashisking@outlook.com', 67254213),
(DEFAULT, 'Kongens gate 3', 7011, 'janjohn@online.no', 98234272),
(DEFAULT, 'Falkenborgvegen 9', 7044, 'VGVG@hotmail.com', 99009292),
(DEFAULT, 'Falkenborgvegen 1', 7044, 'KariHansen@outlook.com', 90321690),
(DEFAULT, 'Østre Rosten', 7075, 'companyno@company.no', 56721243),
(DEFAULT, 'Strindvegen 40', 7052, 'nicoleee@hotmail.com', 60253154),
(DEFAULT, 'Strindvegen 20', 7052, 'kkirkhorn@kirkhorn.no', 89523464),
(DEFAULT, 'Lindemans gate 2', 7052, 'ingunn@sund.no', 75394720),
(DEFAULT, 'Lokes veg 22', 7033, 'PetterP@hotmail.no', 75394721),
(DEFAULT, 'Christian Bloms veg 4', 7058, 'SaraOlsen@outlook.no', 75394722);

INSERT INTO Private_customer(last_name, first_name, customer_id) VALUES
('Ole', 'Ola', 3),
('John', 'Jan', 4),
('Hansen', 'Kari', 6),
('Uybengkee', 'Nicole', 8),
('Kirkhorn', 'Knut', 9),
('Sund', 'Ingunn', 10),
('Pettersen', 'Petter', 11),
('Olsen', 'Sara', 12);


INSERT INTO Company(company_name, customer_id) VALUES
('Catering AS', 1),
('Telenor', 2),
('VG', 5),
('Company.no', 7);

INSERT INTO Orders(payment_status, order_date, delivery_date, delivery_time, address, zip, take_away, other_request, made, ingredients_purchased, delivered, customer_id) VALUES
(1, '2015-03-12', '2015-03-16', 14.5, 'Prinsens gate 6', 7011, 0, 'Warm food.', 1, 1, 1, 3),
(1, '2015-08-12', '2015-08-16', 11.5, 'Prinsens gate 6', 7011, 0, NULL, 1, 1, 1, 2),
(1, '2016-04-20', '2016-04-23', 7, 'Olav Tryggvasons gate 33', 7011, 0, NULL, 1, 1, 1, 6),
(1, '2016-04-25', '2016-04-25', 9, 'Sverdrups vei 33', 7020, 1, NULL, 0, 1, 0, 2),
(0, '2016-04-21', '2016-04-26', 19.5, 'Søndre gate 2', 7011, 0, 'Fast delivery.', 0, 0, 0, 1),
(1, '2016-04-21', '2016-04-26', 10.5, 'Lindemans gate 5', 7052, 0, NULL, 0, 0, 0, 3),
(1, '2016-04-21', '2016-04-27', 16, 'Lindemans gate 2', 7052, 0, 'Local food.', 0, 1, 0, 10),
(0, '2016-04-21', '2016-04-28', 10, 'Lokes veg 22', 7033, 0, 'Warm food.', 0, 0, 0, 11),
(0, '2016-04-21', '2016-04-28', 10.5, 'Strindvegen 40', 7052, 0, 'Fast delivery.', 0, 0, 0, 2),
(0, '2016-04-21', '2016-04-28', 17, 'Kongens gate 10', 7011, 0, NULL, 0, 0, 0, 7),
(0, '2016-04-21', '2016-04-28', 12, 'Falkenborgvegen 1', 7044, 1, NULL, 0, 0, 0, 11),
(0, '2016-04-21', '2016-04-28', 19, 'Kongens gate 2', 7011, 1, NULL, 0, 0, 0, 3),
(0, '2016-04-21', '2016-04-28', 9.5, 'Ferjemannsveien 2', 7014, 1, NULL, 0, 0, 0, 10),
(0, '2016-04-21', '2016-04-30', 11, 'Kong Øysteins veg 43', 7046, 0, NULL, 0, 0, 0, 2),
(0, '2016-04-21', '2016-04-30', 13.5, 'Gamle Oslovei 70', 7020, 0, 'Local Food', 0, 0, 0, 9),
(0, '2016-04-21', '2016-04-30', 14, 'Dronningens gate 12', 7011, 0, NULL, 0, 0, 0, 3);


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

INSERT INTO Stock(ingredient_name, quantity, measurement) VALUES 
('Egg', 60, 'Pcs'),
('Milk', 20, 'L'),
('All-purpose flour', 40, 'Kg'),
('Tomatoes', 0, 'Kg'),
('Cheese', 3.5, 'Kg'),
('Baking powder', 200, 'g'),
('Salt', 500, 'g'),
('Onion', 4, 'Kg'),
('Vegetable oil', 1, 'L'),
('Olive oil', 1.5, 'L'),
('Lettuce', 3, 'Kg'),
('Tomato purée', 2, 'Kg'),
('Beef', 10, 'Kg'),
('White sugar', 2, 'Kg'),
('Brown sugar', 1.1, 'Kg');

INSERT INTO Recipe (recipe_name, recipe_type, price)
VALUES
  ('Pancakes', 'VEGETARIAN', 100),
  ('Tomato Soup', 'VEGETARIAN', 100),
  ('Spaghetti bolognese', 'MEATLOVER', 100);

INSERT INTO Recipe_ingredient (recipe_name, ingredient_name, quantity)
VALUES
  ('Pancakes', 'Egg', '1'),
  ('Pancakes', 'Milk', '1'),
  ('Pancakes', 'All-purpose flour', '1'),
  ('Pancakes', 'Salt', '1'),
  ('Pancakes', 'White sugar', '2'),
  ('Pancakes', 'Vegetable oil', '2'),
  ('Tomato soup', 'Onion', '1 pc'),
  ('Tomato soup', 'Olive oil', '2'),
  ('Tomato soup', 'Tomato purée', '2');

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
