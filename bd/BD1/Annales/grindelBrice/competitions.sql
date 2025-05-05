CREATE TABLE Organisateurs(
idO SERIAL PRIMARY KEY,
nom VARCHAR(20) UNIQUE NOT NULL, 
type VARCHAR(20),
telephone VARCHAR(10)
CHECK (type IN ('Entreprise', 'Particulier', 'Autre'))
);

CREATE TABLE Lieux(
idL SERIAL PRIMARY KEY,
nom VARCHAR(20) NOT NULL,
type VARCHAR(20),
description VARCHAR(40),
nbPlaces INTEGER
CHECK (type IN('ParkingSousTerrain', 'Aerodrome', 'SalleSpecialisee')) 
);


CREATE TABLE Competitions(
idC SERIAL PRIMARY KEY,
intitule VARCHAR(30) NOT NULL,
description VARCHAR(1000),
type VARCHAR(20),
idL INTEGER REFERENCES Lieux(idL) ON DELETE CASCADE, 
CHECK (type IN('Pro', 'Amateur', 'WorldCup'))
);
 
CREATE TABLE Participants(
nom VARCHAR(30) PRIMARY KEY,
age INTEGER NOT NULL,
adresse VARCHAR(50),
telephone VARCHAR(10)
);

CREATE TABLE Drones(
idD SERIAL PRIMARY KEY,
couleur VARCHAR(15),
puissance INTEGER NOT NULL, -- en KV (nombre de tours que peut faire le moteur en 1 minute et pour 1 volt)
CHECK (couleur IN('rouge', 'vert', 'bleu', 'jaune', 'rose', 'noir', 'multicolor'))
);

CREATE TABLE Possede(
nomPartD VARCHAR(30) REFERENCES Participants (nom) ON DELETE CASCADE, 
idD INTEGER REFERENCES Drones (idD) ON DELETE CASCADE, 
dateAcquisition DATE NOT NULL
);


CREATE TABLE OrganisePar(
idO SERIAL REFERENCES Organisateurs(idO) ON DELETE CASCADE,
idC SERIAL REFERENCES Competitions(idC) ON DELETE CASCADE,
date DATE NOT NULL,
CONSTRAINT cleOrganiserPar PRIMARY KEY (idO, idC,date)
);

CREATE TABLE ParticiperCompet(
idC SERIAL REFERENCES Competitions(idC) ON DELETE CASCADE,
nomPart VARCHAR(30) REFERENCES Participants(nom) ON DELETE CASCADE,
prix REAL NOT NULL,
CONSTRAINT cleContenir PRIMARY KEY (idC, nomPart)
);

/* Organisateurs */
/*1*/insert into Organisateurs (nom, type, telephone) values ('Andre Dupuis','Particulier',NULL);
/*2*/insert into Organisateurs (nom, type, telephone) values ('FPV Air', 'Autre','0232959700' );
/*3*/insert into Organisateurs (nom, type, telephone) values ('THALES', 'Entreprise','0299274545' );
/*4*/insert into Organisateurs (nom, type, telephone) values ('Blob Assurance', 'Entreprise','0285457412' );

/* Lieux */
/*1*/insert into Lieux (nom, type, description, nbPlaces) values ('Kindarena','ParkingSousTerrain', 'le dimanche seulement', 600);
/*2*/insert into Lieux (nom, type, description, nbPlaces) values ('Salle VHugo','SalleSpecialisee', NULL, 70);
/*3*/insert into Lieux (nom, type, description, nbPlaces) values ('Boos', 'Aerodrome', 'avions et drones',800);
/*4*/insert into Lieux (nom, type, description, nbPlaces) values ('Hangar 23', 'SalleSpecialisee', NULL, 200);
/*5*/insert into Lieux (nom, type, description, nbPlaces) values ('Centre Valoris', 'ParkingSousTerrain', 'le dimanche seulement', 50);


/* Competitions */
/*1*/insert into Competitions(intitule, description, type, idL) values ('Drones games', 'Wing,  Individual Quad', 'Amateur', 2);
/*2*/insert into Competitions(intitule, description, type, idL) values ('FPV Air Race', 'Freestyle, Team,  Individual Quad', 'Pro', 1);
/*3*/insert into Competitions(intitule, description, type, idL) values ('Aloha Cup', 'Individual Quad, Team, Freestyle, Wing', 'WorldCup', 3);
/*4*/insert into Competitions(intitule, description, type, idL) values ('Drones Action', NULL, 'Amateur', 5);


/* Participants */
/*Ma mie*/insert into Participants values ('Ma mie', 25, '2 rue des Lilas', '0685954514');
/*Laure Aidubois*/insert into Participants values ('Laure Aidubois', 30, '80 avenue Chanzy', '0698523210');
/*Albert Gamotte*/insert into Participants values ('Albert Gamotte', 40, 'rue du renard', '0235457410');
/*Bebert*/insert into Participants values ('Bebert', 52, 'impasse des lauriers', '0698523300');
/*Maggie Mauve*/insert into Participants values ('Maggie Mauve', 18, 'rue des papillons', '0656254185');
/*Lance Pierre*/insert into Participants values ('Lance Pierre', 34, 'bd des belges', '0685412032');
/*Mamie*/insert into Participants values ('Mamie', 84, 'rue des mimosas', '0452154563');
/*Bruno Zieuvair*/insert into Participants values ('Bruno Zieuvair', 84, 'rue des mimosas', '0452154563');
/*Sacha Touille*/insert into Participants values ('Sacha Touille', 18, 'avenue des blaireaux', '0685412300');
/*Walter Claussette*/insert into Participants values ('Walter Claussette',30 , '53 impasse du moulin', '0235149585');
/*Laure Eole*/insert into Participants values ('Laure Eole', 22, '22 rue des Palmiers', '0687521932');
/*Luc Surieux*/insert into Participants values ('Luc Surieux',20 , '102 avenue Marcel Samba', '0687545513');
/*Ludovic Tuaille*/insert into Participants values ('Ludovic Tuaille',32 , '45 rue des Barbapapas', '0687542310');
/*Marguerite Fanet*/insert into Participants values ('Marguerite Fanet',45 , '95 avenue de Caen', '0632100074');
/*Tex Agère*/insert into Participants values ('Tex Agère', 36, '66 bd du Madrillet', '0622548874');
/*Sissi Troënne*/insert into Participants values ('Sissi Troënne',20 , '1095 rue des oiseaux', '0654875211');
/*Lina Chevée*/insert into Participants values ('Lina Chevée',25 , '23 impasse des aviateurs', '0187524195');
/*Mo Bylette*/insert into Participants values ('Mo Bylette', 52, '33 rue du bonheur', '0198745620');
/*Otto Bus*/insert into Participants values ('Otto Bus',26 , '951 avenue des framboises', '0245741284');
/*Olaf Herme*/insert into Participants values ('Olaf Herme',25 , '10 bd général Lee', '0654872033');
/*Mousse Line*/insert into Participants values ('Mousse Line',37 , '57 rue du bar', '0685412374');
/*Thomas Teauju*/insert into Participants values ('Thomas Teauju',29 , '63 rue des moutons', '0654859762');
/*Théo Rifumeuse*/insert into Participants values ('Théo Rifumeuse',24 , '44 rue des bateaux', '0687951230');
/*Zélie Coptaire*/insert into Participants values ('Zélie Coptaire',40 , '133 avenue des maronniers', '0645882010');
/*Amanda Maire*/insert into Participants values ('Amanda Maire',20 , '30 impasse de la ferme', '0245841230');
/*Caty Mini*/insert into Participants values ('Caty Mini',18 , '2 bd de l''europe', '0284561132');
/*David Cicode*/insert into Participants values ('David Cicode', 35, '74 rue de la république', '0674213300');
/*Djemal Partou*/insert into Participants values ('Djemal Partou',50 , '15 rue des charcutiers', '0256849510');
/*Edith Avuleur*/insert into Participants values ('Edith Avuleur',21 , '99 rue du muguet', '0654752210');
/*Fidel Oposte*/insert into Participants values ('Fidel Oposte', 42, '123 rue des chinchillas', '0654123000');
/*Géo Graff*/insert into Participants values ('Géo Graff',30, '2 impasse des sapins', '0678854120');
/*Fredéric Ochet */insert into Participants values ('Frederic Ochet',18 , '31 rue des lapins', '0214752012');


/* Drones couleur; puissance */
insert into Drones(couleur, puissance) values ('multicolor', 700);
insert into Drones(couleur, puissance) values ('rouge', 1400);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('rouge', 1100);
insert into Drones(couleur, puissance) values ('bleu', 700);
insert into Drones(couleur, puissance) values ('multicolor', 750);
insert into Drones(couleur, puissance) values ('noir', 700);
insert into Drones(couleur, puissance) values ('rouge', 1400);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('jaune', 1100);
insert into Drones(couleur, puissance) values ('bleu', 700);
insert into Drones(couleur, puissance) values ('multicolor', 1000);
insert into Drones(couleur, puissance) values ('rouge', 1400);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('jaune', 1100);
insert into Drones(couleur, puissance) values ('bleu', 800);
insert into Drones(couleur, puissance) values ('noir', 1000);
insert into Drones(couleur, puissance) values ('rouge', 1200);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('multicolor', 950);
insert into Drones(couleur, puissance) values ('bleu', 800);
insert into Drones(couleur, puissance) values ('noir', 1000);
insert into Drones(couleur, puissance) values ('rose', 850);
insert into Drones(couleur, puissance) values ('bleu', 800);
insert into Drones(couleur, puissance) values ('noir', 1000);
insert into Drones(couleur, puissance) values ('rouge', 1200);
insert into Drones(couleur, puissance) values ('bleu', 800);
insert into Drones(couleur, puissance) values ('noir', 1000);
insert into Drones(couleur, puissance) values ('rouge', 1200);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('multicolor', 950);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('jaune', 900);
insert into Drones(couleur, puissance) values ('bleu', 800);
insert into Drones(couleur, puissance) values ('noir', 1200);
insert into Drones(couleur, puissance) values ('rouge', 800);
insert into Drones(couleur, puissance) values ('rose', 700);
insert into Drones(couleur, puissance) values ('multicolor', 1200);
insert into Drones(couleur, puissance) values ('bleu', 800);
insert into Drones(couleur, puissance) values ('noir', 1000);


/* Possede nomPartD ; idD; dateAcquisition */
insert into Possede values ('Ma mie', 1, '27/02/2016');
insert into Possede values ('Laure Aidubois', 2,'30/03/2016' );
insert into Possede values ('Albert Gamotte', 3, '10/03/2015');
insert into Possede values ('Albert Gamotte', 4, '15/05/2016');
insert into Possede values ('Bebert',5 , '30/03/2016');
insert into Possede values ('Bebert', 6, '15/06/2016');
insert into Possede values ('Bebert', 7, '25/05/2016');
insert into Possede values ('Maggie Mauve', 8, '10/03/2016');
insert into Possede values ('Lance Pierre', 9, '15/08/2015');
insert into Possede values ('Mamie', 10, '11/01/2016');
insert into Possede values ('Bruno Zieuvair',11 , '27/02/2016');
insert into Possede values ('Sacha Touille', 12, '11/02/2016');
insert into Possede values ('Walter Claussette',13 , '18/09/2016');
insert into Possede values ('Walter Claussette', 14, '11/01/2014');
insert into Possede values ('Laure Eole', 15, '01/02/2014');
insert into Possede values ('Luc Surieux', 16, '11/12/2016');
insert into Possede values ('Ludovic Tuaille',17 , '10/04/2015');
insert into Possede values ('Marguerite Fanet', 18, '30/03/2016');
insert into Possede values ('Tex Agère',19 , '16/11/2015');
insert into Possede values ('Sissi Troënne',20 , '27/02/2016');
insert into Possede values ('Lina Chevée', 21, '17/04/2013');
insert into Possede values ('Mo Bylette', 22, '17/04/2016');
insert into Possede values ('Otto Bus', 23, '27/05/2013');
insert into Possede values ('Olaf Herme', 24, '30/03/2016');
insert into Possede values ('Mousse Line', 25, '08/06/2015');
insert into Possede values ('Thomas Teauju', 26, '08/07/2016');
insert into Possede values ('Théo Rifumeuse', 27, '12/10/2015');
insert into Possede values ('Zélie Coptaire', 28, '22/10/2016');
insert into Possede values ('Amanda Maire', 29,'12/10/2016' );
insert into Possede values ('Caty Mini', 30, '26/01/2016');
insert into Possede values ('David Cicode',31 , '28/02/2016');
insert into Possede values ('Djemal Partou', 32, '31/03/2016');
insert into Possede values ('Edith Avuleur', 33, '29/06/2016');
insert into Possede values ('Fidel Oposte', 34, '04/09/2016');
insert into Possede values ('Géo Graff', 35, '28/04/2015');
insert into Possede values ('Frederic Ochet',36 , '20/02/2014');

/* organisePar idorganisateur ; idsoiree ; date*/
insert into OrganisePar values (1, 1, '27/02/2017');
insert into OrganisePar values (2, 2, '20/02/2017'); 
insert into OrganisePar values (3, 3, '30/03/2017');    
insert into OrganisePar values (3, 4, '27/02/2017');

/* ParticiperCompet idC ; nomPart ; prix*/
/* competition Drones games */
insert into ParticiperCompet values (1, 'Ma mie', 0);
insert into ParticiperCompet values (1, 'Mamie', 0);
insert into ParticiperCompet values (1, 'Maggie Mauve', 0);
insert into ParticiperCompet values (1, 'Bebert', 0);
insert into ParticiperCompet values (1, 'Laure Aidubois', 0);
insert into ParticiperCompet values (1, 'Lance Pierre', 0);
insert into ParticiperCompet values (1, 'Albert Gamotte', 0);
insert into ParticiperCompet values (1, 'Bruno Zieuvair', 0);
insert into ParticiperCompet values (1, 'Sacha Touille', 0);

/* FPV Air Race */
insert into ParticiperCompet values (2, 'Laure Aidubois', 200);
insert into ParticiperCompet values (2, 'Laure Eole', 200);
insert into ParticiperCompet values (2, 'Luc Surieux', 100);
insert into ParticiperCompet values (2, 'Tex Agère', 200);
insert into ParticiperCompet values (2, 'Sissi Troënne', 150);
insert into ParticiperCompet values (2, 'Lina Chevée', 200);
insert into ParticiperCompet values (2, 'Olaf Herme', 100);
insert into ParticiperCompet values (2, 'Théo Rifumeuse', 300);
insert into ParticiperCompet values (2, 'Amanda Maire', 200);
insert into ParticiperCompet values (2, 'Sacha Touille', 0);

/* Aloha Cup */
insert into ParticiperCompet values (3, 'Walter Claussette',500 );
insert into ParticiperCompet values (3, 'Ludovic Tuaille', 500);
insert into ParticiperCompet values (3, 'Tex Agère', 500);
insert into ParticiperCompet values (3, 'Mo Bylette', 500);
insert into ParticiperCompet values (3, 'Mousse Line', 400);
insert into ParticiperCompet values (3, 'Thomas Teauju', 400);
insert into ParticiperCompet values (3, 'Zélie Coptaire', 500);
insert into ParticiperCompet values (3, 'David Cicode', 500);
insert into ParticiperCompet values (3, 'Djemal Partou', 0);
insert into ParticiperCompet values (3, 'Edith Avuleur', 0);
insert into ParticiperCompet values (3, 'Fidel Oposte', 0);
insert into ParticiperCompet values (3, 'Géo Graff', 500);
insert into ParticiperCompet values (3, 'Sacha Touille', 0);


/* Drones Action */
insert into ParticiperCompet values (4, 'Sacha Touille', 300);
insert into ParticiperCompet values (4, 'Walter Claussette', 300);
insert into ParticiperCompet values (4, 'Marguerite Fanet', 300);
insert into ParticiperCompet values (4, 'Otto Bus', 300);
insert into ParticiperCompet values (4, 'Olaf Herme', 300);
insert into ParticiperCompet values (4, 'Caty Mini', 300);
insert into ParticiperCompet values (4, 'Frederic Ochet', 300);
insert into ParticiperCompet values (4, 'Bruno Zieuvair', 100);

