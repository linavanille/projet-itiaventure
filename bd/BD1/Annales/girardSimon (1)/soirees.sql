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
CHECK (type IN('SalleMunicipale', 'Zenith', 'Theatre', 'Bar', 'Boite')) 
);


CREATE TABLE Soirees(
idS SERIAL PRIMARY KEY,
intitule VARCHAR(30) NOT NULL,
description VARCHAR(1000),
type VARCHAR(20),
idL INTEGER REFERENCES Lieux(idL) ON DELETE CASCADE, 
CHECK (type IN('Anniversaire', 'Vernissage', 'Gala', 'Mousse'))
);
 
CREATE TABLE Invites(
nom VARCHAR(30) PRIMARY KEY,
age INTEGER NOT NULL,
adresse VARCHAR(50),
telephone VARCHAR(10)
);

CREATE TABLE OrganisePar(
idO SERIAL REFERENCES Organisateurs(idO) ON DELETE CASCADE,
idS SERIAL REFERENCES Soirees(idS) ON DELETE CASCADE,
date DATE NOT NULL,
CONSTRAINT cleRealiserPour PRIMARY KEY (idO, idS,date)
);

CREATE TABLE Participer(
idS SERIAL REFERENCES Soirees(idS) ON DELETE CASCADE,
nomInv VARCHAR(30) REFERENCES Invites(nom) ON DELETE CASCADE,
prix REAL NOT NULL,
CONSTRAINT cleContenir PRIMARY KEY (idS, nomInv)
);

/* Organisateurs */
/*1*/insert into Organisateurs (nom, type, telephone) values ('Andre Dupuis','Particulier',NULL);
/*2*/insert into Organisateurs (nom, type, telephone) values ('INSA', 'Autre','0232959700' );
/*3*/insert into Organisateurs (nom, type, telephone) values ('Capgemini', 'Entreprise','0299274545' );
/*4*/insert into Organisateurs (nom, type, telephone) values ('Blob Assurance', 'Entreprise','0285457412' );

/* Lieux */
/*1*/insert into Lieux (nom, type, description, nbPlaces) values ('Kindarena','SalleMunicipale', 'concert/événement sportif', 6000);
/*2*/insert into Lieux (nom, type, description, nbPlaces) values ('Salle VHugo','SalleMunicipale', NULL, 7);
/*3*/insert into Lieux (nom, type, description, nbPlaces) values ('Zenith Rouen', 'Zenith', 'salle de concerts',8000);
/*4*/insert into Lieux (nom, type, description, nbPlaces) values ('Hangar 23', 'Zenith', 'salle de concerts', 200);
/*5*/insert into Lieux (nom, type, description, nbPlaces) values ('Le chat vert', 'Bar', 'jazz', 50);


/* Soirees */
/*1*/insert into Soirees(intitule, description, type, idL) values ('La soirée de dede', 'fiesta', 'Anniversaire', 2);
/*2*/insert into Soirees(intitule, description, type, idL) values ('Gala INSA', 'soirée de fin d''étude', 'Gala', 1);
/*3*/insert into Soirees(intitule, description, type, idL) values ('Inauguration locaux Rouen', NULL, 'Vernissage', 3);
/*4*/insert into Soirees(intitule, description, type, idL) values ('Vocuhila trio', 'NULL', 'Gala', 5);


/* Invites */
/*Ma mie*/insert into Invites values ('Ma mie', 25, '2 rue des Lilas', '0685954514');
/*Laure Aidubois*/insert into Invites values ('Laure Aidubois', 30, '80 avenue Chanzy', '0698523210');
/*Albert Gamotte*/insert into Invites values ('Albert Gamotte', 40, 'rue du renard', '0235457410');
/*Bebert*/insert into Invites values ('Bebert', 52, 'impasse des lauriers', '0698523300');
/*Maggie Mauve*/insert into Invites values ('Maggie Mauve', 18, 'rue des papillons', '0656254185');
/*Lance Pierre*/insert into Invites values ('Lance Pierre', 34, 'bd des belges', '0685412032');
/*Mamie*/insert into Invites values ('Mamie', 84, 'rue des mimosas', '0452154563');
/*Bruno Zieuvair*/insert into Invites values ('Bruno Zieuvair', 84, 'rue des mimosas', '0452154563');
/*Sacha Touille*/insert into Invites values ('Sacha Touille', 18, 'avenue des blaireaux', '0685412300');
/*Walter Claussette*/insert into Invites values ('Walter Claussette',30 , '53 impasse du moulin', '0235149585');
/*Laure Eole*/insert into Invites values ('Laure Eole', 22, '22 rue des Palmiers', '0687521932');
/*Luc Surieux*/insert into Invites values ('Luc Surieux',20 , '102 avenue Marcel Samba', '0687545513');
/*Ludovic Tuaille*/insert into Invites values ('Ludovic Tuaille',32 , '45 rue des Barbapapas', '0687542310');
/*Marguerite Fanet*/insert into Invites values ('Marguerite Fanet',45 , '95 avenue de Caen', '0632100074');
/*Tex Agère*/insert into Invites values ('Tex Agère', 36, '66 bd du Madrillet', '0622548874');
/*Sissi Troënne*/insert into Invites values ('Sissi Troënne',20 , '1095 rue des oiseaux', '0654875211');
/*Lina Chevée*/insert into Invites values ('Lina Chevée',25 , '23 impasse des aviateurs', '0187524195');
/*Mo Bylette*/insert into Invites values ('Mo Bylette', 52, '33 rue du bonheur', '0198745620');
/*Otto Bus*/insert into Invites values ('Otto Bus',26 , '951 avenue des framboises', '0245741284');
/*Olaf Herme*/insert into Invites values ('Olaf Herme',25 , '10 bd général Lee', '0654872033');
/*Mousse Line*/insert into Invites values ('Mousse Line',37 , '57 rue du bar', '0685412374');
/*Thomas Teauju*/insert into Invites values ('Thomas Teauju',29 , '63 rue des moutons', '0654859762');
/*Théo Rifumeuse*/insert into Invites values ('Théo Rifumeuse',24 , '44 rue des bateaux', '0687951230');
/*Zélie Coptaire*/insert into Invites values ('Zélie Coptaire',40 , '133 avenue des maronniers', '0645882010');
/*Amanda Maire*/insert into Invites values ('Amanda Maire',20 , '30 impasse de la ferme', '0245841230');
/*Caty Mini*/insert into Invites values ('Caty Mini',18 , '2 bd de l''europe', '0284561132');
/*David Cicode*/insert into Invites values ('David Cicode', 35, '74 rue de la république', '0674213300');
/*Djemal Partou*/insert into Invites values ('Djemal Partou',50 , '15 rue des charcutiers', '0256849510');
/*Edith Avuleur*/insert into Invites values ('Edith Avuleur',21 , '99 rue du muguet', '0654752210');
/*Fidel Oposte*/insert into Invites values ('Fidel Oposte', 42, '123 rue des chinchillas', '0654123000');
/*Géo Graff*/insert into Invites values ('Géo Graff',30, '2 impasse des sapins', '0678854120');
/*Fredéric Ochet */insert into Invites values ('Frederic Ochet',18 , '31 rue des lapins', '0214752012');


/* organisePar idorganisateur ; idsoiree ; date*/
insert into OrganisePar values (1, 1, '27/02/2016');
insert into OrganisePar values (2, 2, '20/02/2016'); 
insert into OrganisePar values (3, 3, '30/03/2016');    
insert into OrganisePar values (3, 4, '27/02/2016');

/* Participer idsoiree ; nominvite ; prixsoiree*/
/* soiree dede */
insert into Participer values (1, 'Ma mie', 0);
insert into Participer values (1, 'Mamie', 0);
insert into Participer values (1, 'Maggie Mauve', 0);
insert into Participer values (1, 'Bebert', 0);
insert into Participer values (1, 'Laure Aidubois', 0);
insert into Participer values (1, 'Lance Pierre', 0);
insert into Participer values (1, 'Albert Gamotte', 0);
insert into Participer values (1, 'Bruno Zieuvair', 0);
insert into Participer values (1, 'Sacha Touille', 0);

/* soiree gala insa */
insert into Participer values (2, 'Laure Aidubois', 20);
insert into Participer values (2, 'Laure Eole', 20);
insert into Participer values (2, 'Luc Surieux', 10);
insert into Participer values (2, 'Tex Agère', 20);
insert into Participer values (2, 'Sissi Troënne', 15);
insert into Participer values (2, 'Lina Chevée', 20);
insert into Participer values (2, 'Olaf Herme', 10);
insert into Participer values (2, 'Théo Rifumeuse', 30);
insert into Participer values (2, 'Amanda Maire', 20);
insert into Participer values (2, 'Sacha Touille', 0);

/* soiree capgemini */
insert into Participer values (3, 'Walter Claussette',50 );
insert into Participer values (3, 'Ludovic Tuaille', 50);
insert into Participer values (3, 'Tex Agère', 50);
insert into Participer values (3, 'Mo Bylette', 50);
insert into Participer values (3, 'Mousse Line', 40);
insert into Participer values (3, 'Thomas Teauju', 40);
insert into Participer values (3, 'Zélie Coptaire', 50);
insert into Participer values (3, 'David Cicode', 50);
insert into Participer values (3, 'Djemal Partou', 0);
insert into Participer values (3, 'Edith Avuleur', 0);
insert into Participer values (3, 'Fidel Oposte', 0);
insert into Participer values (3, 'Géo Graff', 50);
insert into Participer values (3, 'Sacha Touille', 0);


/* soiree chat vert */
insert into Participer values (4, 'Sacha Touille', 30);
insert into Participer values (4, 'Walter Claussette', 30);
insert into Participer values (4, 'Marguerite Fanet', 30);
insert into Participer values (4, 'Otto Bus', 30);
insert into Participer values (4, 'Olaf Herme', 30);
insert into Participer values (4, 'Caty Mini', 30);
insert into Participer values (4, 'Frederic Ochet', 30);
insert into Participer values (4, 'Bruno Zieuvair', 10);

