/*SELECT nom, nbPlaces
    FROM lieux
    GROUP BY nom, nbPlaces
    HAVING nbPlaces=(SELECT MAX(nbPlaces) FROM lieux);*/

SELECT nom, nbPlaces
    FROM lieux
    WHERE nbPlaces=(SELECT MAX(nbPlaces) FROM lieux);
