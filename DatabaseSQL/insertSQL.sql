
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
(DEFAULT, 'Lindemans gate 2', 7052, 'ingunn@sund.no', 75394721);

INSERT INTO Private_customer(last_name, first_name, customer_id) VALUES
('Ole', 'Ola', 3),
('John', 'Jan', 4),
('Hansen', 'Kari', 6),
('Uybengkee', 'Nicole', 8),
('Kirkhorn', 'Knut', 9),
('Sund', 'Ingunn', 10);


INSERT INTO Company(company_name, customer_id) VALUES
('Catering AS', 1),
('Telenor', 2),
('VG', 5),
('Company.no', 7);

INSERT INTO Orders(payment_status, order_date, delivery_date, delivery_time, address, zip, take_away, other_request, made, ingredients_purchased, delivered, customer_id) VALUES
(1, '2015-03-12', '2015-03-16', 14.5, 'Prinsens gate 6', 7011, 0, 'Warm food.', 1, 1, 1, 3),
(1, '2015-08-12', '2015-08-16', 11.5, 'Prinsens gate 6', 7011, 0, NULL, 1, 1, 1, 2),
(1, '2016-04-20', '2016-04-23', 7, 'Olav Tryggvasons gate 33', 7011, 0, NULL, 1, 1, 1, 6),
(0, '2016-04-21', '2016-04-27', 16, 'Lindemans gate 2', 7052, 0, 'Local food.', 0, 1, 0, 10),
(0, '2016-04-21', '2016-04-26', 19.5, 'Søndre gate 2', 7011, 0, 'Fast delivery.', 0, 0, 0, 1),
(1, '2016-04-25', '2016-04-25', 9, 'Sverdrups vei 33', 7020, 1, NULL, 0, 1, 0, 2),
(1, '2016-04-21', '2016-04-26', 10.5, 'Lindemans gate 5', 7052, 0, NULL, 0, 0, 0, 3);
