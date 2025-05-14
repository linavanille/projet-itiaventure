CREATE TABLE Monde(
	nomMonde VARCHAR(30) PRIMARY KEY
	);
		
CREATE TABLE Piece(
	nomPiece VARCHAR(30) PRIMARY KEY,
	nomMonde VARCHAR(30) not null,
	FOREIGN KEY (nomMonde) REFERENCES Monde(nomMonde) ON DELETE CASCADE
	);
		
CREATE TABLE Porte(
	nomPorte VARCHAR(30) PRIMARY KEY, 
	etat VARCHAR(11) CHECK (etat IN('FERME','OUVERT', 'VERROUILLE')) not null,
	piece1 VARCHAR(30) not null,
	piece2 VARCHAR(30) CHECK(piece1 <> piece2) not null,
	nomMonde VARCHAR(30) not null, 
	FOREIGN KEY (nomMonde) REFERENCES Monde(nomMonde) ON DELETE CASCADE,
	FOREIGN KEY (piece1) REFERENCES Piece(nomPiece) ON DELETE CASCADE,
	FOREIGN KEY (piece2) REFERENCES piece(nompiece) ON DELETE CASCADE
	); 
	
CREATE TABLE PiedDeBiche(
	nomPDB VARCHAR(30) PRIMARY KEY, 
	estDeplacabe VARCHAR(6) not null
	);
	
CREATE TABLE JoueurHumain(
	nomJoueur VARCHAR(30) PRIMARY KEY, 
	pointVie INTEGER CHECK (pointVie >= 0) not null, 
	pointForce INTEGER CHECK (pointForce >= 0) not null, 
	nomPiece VARCHAR(30) not null,
	nomMonde VARCHAR(30) not null,
	FOREIGN KEY (nomPiece) REFERENCES Piece(nomPiece) ON DELETE CASCADE,
	FOREIGN KEY (nomMonde) REFERENCES Monde(nomMonde) ON DELETE CASCADE
	);

CREATE TABLE ContientPDB(
	nomPDB VARCHAR(30) not null UNIQUE,
	nomPiece VARCHAR(30) not null,
	FOREIGN KEY (nomPDB) REFERENCES PiedDeBiche(nomPDB) ON DELETE CASCADE, 
	FOREIGN KEY (nomPiece) REFERENCES Piece(nomPiece) ON DELETE CASCADE	
	);
	
CREATE TABLE PossedePDB(
	nomPDB VARCHAR(30) not null UNIQUE,
	nomJoueur VARCHAR(30) not null,
	FOREIGN KEY (nomPDB) REFERENCES PiedDeBiche(nomPDB) ON DELETE CASCADE, 
	FOREIGN KEY (nomJoueur) REFERENCES JoueurHumain(nomJoueur) ON DELETE CASCADE
	);
