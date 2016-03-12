/* test queries */

INSERT INTO employee (emp_id, last_name, first_name, tlf, hiredate, position_id, username, password, email_address)
    VALUES (01, 'Sund', 'Ingunn', '98765432', '20150304', 1, 'ingunnsu', 'ingunnIzc00l', 'ingunnsu@yahoo.no');

INSERT INTO positions (position_id, position_name,emp_id) VALUES (1, 'Cashier', 01);

SELECT employee.*, positions.position_name
FROM employee
LEFT OUTER JOIN positions
ON employee.emp_id = positions.emp_id;

INSERT INTO recipe(recipe_id, recipe_name)
VALUES
    (1,'Pancakes'),
    (2,'Tomato Soup'),
    (3,'Spaghetti');

INSERT INTO ingredients (ingredient_id, ingredient_name)
VALUES
    (1, "Egg"),
    (2, "Milk"),
    (3, "Flour"),
    (4, "Tomatoes"),
    (5, "Cheese"),
    (6, "Lettuce"),
    (7, "Beef");

SELECT * FROM `ingredients` GROUP BY ingredient_id;

INSERT INTO recipe_ingredients
    (recipe_id, ingredient_id, amount)
VALUES
    (1,1,'8 pcs'),
    (1,3,'2 cups'),
    (2,4,'2 tbsp'),
    (3,4,'2 tsp'),
    (3,5,'2 cups');

SELECT *
FROM recipe
ORDER BY recipe_id;

INSERT INTO recipe_instructions (recipe_id, step_number, description) VALUES (1, 1, "Whisk together milk, butter, and egg.")
INSERT INTO recipe_instructions (recipe_id, step_number, description) VALUES (1, 2, "Heat a large skillet.")
