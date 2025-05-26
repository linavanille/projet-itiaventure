# Makefile

# Répertoires
SRC_DIR=src
CLASS_DIR=classes
DOC_DIR=doc
LIB_DIR=lib
PACKAGE=fr.insarouen.iti.prog.aventure
FICHIER_DIR=$(SRC_DIR)/fr/insarouen/iti/prog/aventure/data/fichier

# Cibles principales
.PHONY: all compilation aventure compile-aventure run javadoc jar clean

all: compile-aventure

# Compilation du répertoire 'fichier'
compilation:
	javac -classpath $(CLASS_DIR):/usr/share/java/postgresql-jdbc4.jar -sourcepath $(SRC_DIR) -d $(CLASS_DIR) \
	$$(find $(FICHIER_DIR) -name '*.java')

# Compilation du répertoire 'aventure' sans le répertoire 'fichier'
aventure:
	javac -classpath $(CLASS_DIR):/usr/share/java/postgresql-jdbc4.jar -sourcepath $(SRC_DIR) -d $(CLASS_DIR) \
	$$(find $(SRC_DIR) -name '*.java' | grep -v $(FICHIER_DIR))

# Compilation complète du répertoire 'aventure' avec le driver PostgreSQL
compile-aventure:
	javac -classpath $(CLASS_DIR):/usr/share/java/postgresql-jdbc4.jar -sourcepath $(SRC_DIR) -d $(CLASS_DIR) \
	$$(find $(SRC_DIR) -name '*.java')

# Exécution du jeu avec le driver PostgreSQL
run:
	java -classpath $(CLASS_DIR):/usr/share/java/postgresql-jdbc4.jar $(PACKAGE).Main

# Génération de la javadoc
javadoc:
	javadoc -d $(DOC_DIR) -sourcepath $(SRC_DIR) -subpackages $(PACKAGE)

# Création du jar du projet
jar:
	jar -cvf $(LIB_DIR)/itiaventure.jar -C $(CLASS_DIR) .

# Nettoyer le projet (classes et documentation)
clean:
	rm -rf $(CLASS_DIR)/*
	rm -rf $(DOC_DIR)/*

