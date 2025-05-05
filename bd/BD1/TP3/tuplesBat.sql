--------------------
-- TABLE ENTREPRISE_BAT
--------------------
INSERT INTO ENTREPRISE_BAT VALUES('QuiFaitBienSonBoulot', 1950, '2 rue des Mimosa, Rouen');
INSERT INTO ENTREPRISE_BAT VALUES('DUPUIS', 1990, '5 avenue des Lilas, Paris');
INSERT INTO ENTREPRISE_BAT VALUES('A-MOI', 2013, '82 impasse des Rosiers, Marseille');

--------------------
-- EMPLOYE
--------------------
INSERT INTO EMPLOYE (nom, prenom, date_naiss, statut)
		VALUES ('MAUVE', 'Maggie', '19/03/1985', 'CDI');
INSERT INTO EMPLOYE (nom, prenom, date_naiss, statut)
		VALUES ('ZIEUVER', 'Bruno', '28/06/1960', 'vacataire');
INSERT INTO EMPLOYE (nom, prenom, date_naiss, statut)
		VALUES ('LANCE', 'Pierre', '10/02/1978', 'vacataire');
INSERT INTO EMPLOYE (nom, prenom, date_naiss, statut)
		VALUES ('AIDUBOIS', 'Laure', '03/06/1955', 'CDD');
INSERT INTO EMPLOYE (nom, prenom, date_naiss, statut)
		VALUES ('MANSOIF', 'Gerard', '16/04/1966', 'CDI');		
		
--------------------
-- EMPLOIE
--------------------
INSERT INTO EMPLOIE VALUES ('QuiFaitBienSonBoulot', 1, '20/06/2013');
INSERT INTO EMPLOIE VALUES ('QuiFaitBienSonBoulot', 2, '13/09/1990');
INSERT INTO EMPLOIE VALUES ('DUPUIS', 3, '10/01/2012');
INSERT INTO EMPLOIE VALUES ('DUPUIS', 4, '15/02/2012');
INSERT INTO EMPLOIE VALUES ('A-MOI', 5, '01/04/2000');

--------------------
-- TABLE CHANTIER --
--------------------
INSERT INTO CHANTIER (description, localisation,dateDeb,dateFinPrevue, salaireParJour)
        VALUES ('Lotissement des oiseaux', (5.2,2.0),'01/01/2022','15/02/2023',11);
INSERT INTO CHANTIER (description, localisation,dateDeb,dateFinPrevue, salaireParJour)
        VALUES ('Quartier Le chêne Jaunet', (15.6,6.0),'15/12/2021','26/12/2024',12.5);
INSERT INTO CHANTIER (description, localisation,dateDeb,dateFinPrevue, salaireParJour)
        VALUES ('Maison des anciens', (20.3, 18.4), '20/12/2022', '01/01/2024', 18);
INSERT INTO CHANTIER (description, localisation,dateDeb,dateFinPrevue, salaireParJour)
        VALUES ('Quartier des poètes', (90.3, 1.4), '02/02/2023', '01/12/2023', 10);

--------------------
-- TABLE GERE nom entreprise, idChantier ------
--------------------
INSERT INTO GERE VALUES ('QuiFaitBienSonBoulot', 1);
INSERT INTO GERE VALUES ('QuiFaitBienSonBoulot', 2);
INSERT INTO GERE VALUES ('DUPUIS', 3);
INSERT INTO GERE VALUES ('A-MOI', 4);

--------------------
-- TABLE REALISE id employe, idChantier ---
--------------------
INSERT INTO REALISE VALUES (1, 1);
INSERT INTO REALISE VALUES (2, 2);
INSERT INTO REALISE VALUES (3, 3);
INSERT INTO REALISE VALUES (4, 3);
INSERT INTO REALISE VALUES (5, 4);



		
