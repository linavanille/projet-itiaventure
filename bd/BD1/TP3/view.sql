CREATE VIEW listeDesChantiersEnCours(numeroChantier, descriptionChantier, nbJoursRestants) AS
SELECT CH.id, CH.description, CH.dateFinPrevue - CH.dateDeb 
FROM CHANTIER CH
WHERE dateDeb < CURRENT_DATE;

