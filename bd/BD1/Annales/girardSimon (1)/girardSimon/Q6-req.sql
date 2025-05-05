SELECT AVG(age)
    FROM soirees S, participer P, invites
    WHERE intitule = 'Gala INSA'
    AND S.idS = P.idS
    AND P.nomInv = nom;
