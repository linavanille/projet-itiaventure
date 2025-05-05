Select avg(age)
From Participants P, ParticiperCompet PC, Competitions C
Where P.nom = PC.nomPart
AND   PC.idC = C.idC
AND   intitule = 'Drones games';
