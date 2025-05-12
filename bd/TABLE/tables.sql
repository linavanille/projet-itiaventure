CREATE TABLE Monde(
	nomMonde VARCHAR(20) PRIMARY KEY
	);
		
CREATE TABLE Piece(
	nomPiece VARCHAR(20) PRIMARY KEY,
	nomMonde VARCHAR(20) not null,
	FOREIGN KEY (nomMonde) REFERENCES Monde(nomMonde) ON DELETE CASCADE
	);
		
CREATE TABLE Porte(
	nomPorte VARCHAR(20) PRIMARY KEY, 
	etat VARCHAR(11) CHECK (etat IN('FERME','OUVERT')) not null,
	piece1 VARCHAR(20) not null,
	piece2 VARCHAR(20) CHECK(piece1 <> piece2) not null,
	nomMonde VARCHAR(20) not null, 
	FOREIGN KEY (nomMonde) REFERENCES Monde(nomMonde) ON DELETE CASCADE,
	FOREIGN KEY (piece1) REFERENCES Piece(nomPiece) ON DELETE CASCADE,
	FOREIGN KEY (piece2) REFERENCES piece(nompiece) ON DELETE CASCADE
	); 


	
CREATE TABLE PiedDeBiche(
	nomPDB VARCHAR(20) PRIMARY KEY, 
	estDeplacabe BOOL not null
	);
	
CREATE TABLE JoueurHumain(
	nomJoueur VARCHAR(20) PRIMARY KEY, 
	pointVie INTEGER CHECK (pointVie >= 0) not null, 
	pointForce INTEGER CHECK (pointForce >= 0) not null, 
	nomPiece VARCHAR(20) not null,
	nomMonde VARCHAR(20) not null,
	FOREIGN KEY (nomPiece) REFERENCES Piece(nomPiece) ON DELETE CASCADE,
	FOREIGN KEY (nomMonde) REFERENCES Monde(nomMonde) ON DELETE CASCADE
	);

CREATE TABLE ContientPDB(
	nomPDB VARCHAR(20) not null UNIQUE,
	nomPiece VARCHAR(20) not null,
	FOREIGN KEY (nomPDB) REFERENCES PiedDeBiche(nomPDB) ON DELETE CASCADE, 
	FOREIGN KEY (nomPiece) REFERENCES Piece(nomPiece) ON DELETE CASCADE	
	);
	
CREATE TABLE PossedePDB(
	nomPDB VARCHAR(20) not null UNIQUE,
	nomJoueur VARCHAR(20) not null,
	FOREIGN KEY (nomPDB) REFERENCES PiedDeBiche(nomPDB) ON DELETE CASCADE, 
	FOREIGN KEY (nomJoueur) REFERENCES JoueurHumain(nomJoueur) ON DELETE CASCADE
	);
