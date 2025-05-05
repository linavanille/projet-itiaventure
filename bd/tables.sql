CREATE TABLE Monde(
	nomMonde VARCHAR(20) PRIMARY KEY
	);	
	
CREATE TABLE Porte(
	nomPorte VARCHAR(20) PRIMARY KEY, 
	etat INTEGER, 
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)
	); 

CREATE TABLE Piece(
	nomPiece VARCHAR(20) PRIMARY KEY,
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)
	);
	
CREATE TABLE PiedDeBiche(
	nomPDB VARCHAR(20) PRIMARY KEY, 
	estDeplacabe BOOL, 
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)
	);
	
CREATE TABLE JoueurHumain(
	nomJoueur VARCHAR(20) PRIMARY KEY, 
	pointVie INTEGER, 
	pointForce INTEGER, 
	nomPiece VARCHAR(20) REFERENCES Piece(nomPiece),
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)
	);
	
CREATE TABLE PossedePorte(
	nomPorte VARCHAR(20) REFERENCES Porte(nomPorte), 
	nomPiece VARCHAR(20) REFERENCES Piece(nomPiece),
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)	
	);
	
CREATE TABLE ContientPDB(
	nomPDB VARCHAR(20) REFERENCES PiedDeBiche(nomPDB), 
	nomPiece VARCHAR(20) REFERENCES Piece(nomPiece),
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)	
	);
	
CREATE TABLE PossedePDB(
	nomPDB VARCHAR(20) REFERENCES PiedDeBiche(nomPDB), 
	nomJoueur VARCHAR(20) REFERENCES JoueurHumain(nomJoueur),
	nomMonde VARCHAR(20) REFERENCES Monde(nomMonde)	
	);
