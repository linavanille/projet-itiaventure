SELECT intitule
    FROM soirees S, participer P
    WHERE S.idS = P.idS
    GROUP BY intitule
    HAVING SUM(prix) > 200;
