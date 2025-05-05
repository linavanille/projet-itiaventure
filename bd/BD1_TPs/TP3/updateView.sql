CREATE OR REPLACE RULE listeDesChantiersEnCours AS ON UPDATE TO listeDesChantiersEnCoursUpdate
DO INSTEAD
UPDATE nomDeLaTableConcernee SET attTable = new.attVue WHERE
