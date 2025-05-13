INSERT INTO Avion (id, modele, date_fabrication) VALUES
(1, 'Airbus A320', '2015-04-25'),
(2, 'Boeing 737', '2018-06-12'),
(3, 'Embraer E195', '2020-09-09');


INSERT INTO TypeSiege (id, libelle) VALUES
(1, 'Economique'),
(2, 'Affaires'),
(3, 'Première classe');

INSERT INTO Siege (id, libelle, id_avion, id_typesiege, nbr) VALUES
(1, 'Siège 1A', 1, 1, 10),
(2, 'Siège 1B', 1, 1, 10),
(3, 'Siège 2A', 1, 2, 4),
(4, 'Siège 2B', 1, 2, 4),
(5, 'Siège 3A', 2, 1, 12),
(6, 'Siège 3B', 2, 1, 12),
(7, 'Siège 4A', 2, 3, 6),
(8, 'Siège 4B', 2, 3, 6),
(9, 'Siège 5A', 3, 2, 8),
(10, 'Siège 5B', 3, 2, 8);

INSERT INTO VilleDesservie (id, ville) VALUES
(1, 'Paris'),
(2, 'New York'),
(3, 'Tokyo'),
(4, 'Londres'),
(5, 'Berlin'),
(6, 'Madrid'),
(7, 'Rome'),
(8, 'Los Angeles'),
(9, 'Dubai'),
(10, 'Sydney');

INSERT INTO Utilisateur(id,leveluser,nom,password) VALUES(default, 10,'Admin','admin');
