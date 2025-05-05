SELECT S1.idS, S2.idS
    FROM soirees S1, soirees S2, organisePar O1, organisePar O2
    WHERE S1.idS < S2.idS
    AND O1.idS = S1.idS
    AND O2.ids = S2.idS
    AND O1.date = O2.date;
