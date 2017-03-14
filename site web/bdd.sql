CREATE TABLE joueurs
(
    prenom VARCHAR,
    nom VARCHAR,
    sexe ENUM('homme', 'femme'),
    anciennete ENUM('nouveau', 'ancien'),
    ddn DATE,
    niveau ENUM('indefini', 'debutant', 'intermediaire', 'confirme')
)