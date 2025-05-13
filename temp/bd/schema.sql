CREATE TABLE Avion(
    id SERIAL PRIMARY KEY,
    modele VARCHAR(100) NOT NULL,
    date_fabrication DATE
);


CREATE TABLE TypeSiege(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE Siege(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(100),
    id_avion INTEGER,
    id_typesiege INTEGER,
    nbr INTEGER DEFAULT 0,
    FOREIGN KEY(id_typesiege) REFERENCES typesiege(id),
    FOREIGN KEY(id_avion) REFERENCES avion(id)
);


CREATE TABLE VilleDesservie(
    id SERIAL PRIMARY KEY,
    ville VARCHAR(100) NOT NULL
);


CREATE TABLE Vol(
    id SERIAL PRIMARY KEY,
    id_avion INTEGER,
    id_ville_dep INTEGER,
    id_ville_arr INTEGER,
    datedepart DATE,
    heuredepart TIME,
    duree TIME,
    FOREIGN KEY(id_avion) REFERENCES avion(id),
    FOREIGN KEY(id_ville_dep) REFERENCES villedesservie(id),
    FOREIGN KEY(id_ville_arr) REFERENCES villedesservie(id)
);
CREATE TABLE PrixVol(
    id SERIAL PRIMARY KEY,
    id_vol INTEGER,
    id_typesiege INTEGER,
    prix DECIMAL(16,2) DEFAULT 0,
    daty TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY(id_vol) REFERENCES vol(id),
    FOREIGN KEY(id_typesiege) REFERENCES typesiege(id)
);