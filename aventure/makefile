# Makefile

# Variables
SRC_DIR=src
CLASS_DIR=classes
DOC_DIR=doc
LIB_DIR=lib
PACKAGE=fr.insarouen.iti.prog.aventure

# Règles
all: compile

compile:
	javac -classpath $(CLASS_DIR) -sourcepath $(SRC_DIR) -d $(CLASS_DIR) $(shell find $(SRC_DIR) -name '*.java')

javadoc:
	javadoc -d $(DOC_DIR) -sourcepath $(SRC_DIR) -subpackages $(PACKAGE)

clean:
	rm -rf $(CLASS_DIR)/*
	rm -rf $(DOC_DIR)/*
	
play:
	javac -classpath ./classes:/usr/share/java/postgresql-jdbc4.jar -sourcepath ./src -d ./classes src/fr/insarouen/iti/prog/aventure/Main.java
	java -classpath classes fr.insarouen.iti.prog.aventure.Main
	
jar:
	jar -cvf $(LIB_DIR)/itiaventure.jar -C $(CLASS_DIR) .
		
bd:
	javac -classpath ./classes:/usr/share/java/postgresql-jdbc4.jar -sourcepath ./src -d ./classes src/fr/insarouen/iti/prog/aventure/MainBD.java
	java -classpath ./classes:/usr/share/java/postgresql-jdbc4.jar fr.insarouen.iti.prog.aventure.MainBD

.PHONY: all compile javadoc clean play jar

