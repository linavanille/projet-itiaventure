SELECT nom
    FROM invites, participer
    WHERE nom = nomInv
    GROUP BY nom
    HAVING COUNT(idS) = (SELECT COUNT(idS) FROM soirees);
