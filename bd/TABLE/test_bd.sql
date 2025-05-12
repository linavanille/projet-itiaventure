INSERT INTO Monde VALUES ('LeMondeDeLina');
INSERT INTO Piece VALUES ('SalleDeTP','LeMondeDeLina');
INSERT INTO Piece VALUES ('salle', 'LeMondeDeLina');
INSERT INTO Porte VALUES ('Porte','FERME','SalleDeTP','salle','LeMondeDeLina');
INSERT INTO PiedDeBiche values ('PDB',True);
INSERT INTO JoueurHumain values ('Lina', 100, 100, 'SalleDeTP','LeMondeDeLina');
INSERT INTO contientPDB values ('PDB', 'SalleDeTP');
INSERT INTO possedePDB values ('PDB','Lina');
