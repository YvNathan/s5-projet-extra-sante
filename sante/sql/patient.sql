CREATE TABLE patient (
    id              SERIAL PRIMARY KEY,
    nom             VARCHAR(100) NOT NULL,
    prenom          VARCHAR(100) NOT NULL,
    date_naissance  DATE NOT NULL,
    sexe            VARCHAR(1) NOT NULL CHECK (sexe IN ('M', 'F')),
    telephone       VARCHAR(30),
    email           VARCHAR(150),
    adresse         TEXT,
    groupe_sanguin  VARCHAR(5)
);