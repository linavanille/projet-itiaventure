Select nom, nbPlaces
From Lieux
Where nbPlaces = (Select max(nbPlaces)
                  From Lieux);
