SELECT O1.date, COUNT(P1.nomInv)
    FROM participer P1, participer P2, organisePar O1, organisePar O2
    WHERE P1.nomInv = P2.nomInv
    AND P1.idS < P2.idS
    AND P1.idS = O1.idS
    AND P2.idS = O2.idS
    AND O1.date = O2.date
    GROUP BY O1.date;
