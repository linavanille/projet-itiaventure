SELECT idS, COUNT(nomInv)
    FROM participer
    GROUP BY ids
    HAVING COUNT(nomInv)=(SELECT MIN(S.count) FROM (SELECT COUNT(nomInv) FROM participer GROUP BY idS) S);
