Select OP1.date, count(PC1.nomPart)
From OrganisePar OP1, OrganisePar OP2, ParticiperCompet PC1, ParticiperCompet PC2
Where OP1.idC = PC1.idC
AND   OP2.idC = PC2.idC
AND   OP1.idO != OP2.idO
AND   PC1.idC < PC2.idC
AND   PC1.nomPart = PC2.nomPart
Group by OP1.date;
