Select D1.idD, D2.idD
From Drones D1, Drones D2, Possede Po1, Possede Po2
Where D1.idD < D2.idD
AND   D1.idD = Po1.idD
AND   D2.idD = Po2.idD
AND   Po1.dateAcquisition = Po2.dateAcquisition;
