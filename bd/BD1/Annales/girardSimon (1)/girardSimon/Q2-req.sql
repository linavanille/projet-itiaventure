SELECT intitule
    FROM soirees S, lieux L
    WHERE S.idL = L.idL
    AND L.type = 'SalleMunicipale';
