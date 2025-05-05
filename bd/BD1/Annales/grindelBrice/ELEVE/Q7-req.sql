Select intitule
From Competitions C, ParticiperCompet CP
Where C.idC = CP.idC
Group by intitule
Having sum(prix) > 2000;
