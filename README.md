# LO23_Plateforme

Projet réalisé dans le cadre de l'UV LO23.

# Conventions qualité

## Langue
* variables, fonctions : ANGLAIS
* JavaDoc, commentaires, commits : FRANCAIS

## Accolades
* retour à la ligne

```
foo()
{
...
}
```


## Noms de variables et fonctions
* camelCase : nomDeVar, nomDeFoo, NomDeClasse, NomDeFichier

## JavaDoc : méthodes, classes, variables
```
/** 
* Retourne/Affiche/... --> concis
* @param p1 info_1
* ...
* @return valeurRetour
```
Éviter les trucs à rallonge type "Cette méthode sert à..."

## Valeurs numériques
Fichier constantes:
* IHM
* Data
* Com

--> NOM_DE_CONST

## Lisibilité du code
* INDENTEZ SVP
* Optimisez imports
* Commenter chaque bloc d'instruction pour que qqn qui lit seulement les commentaires puisse comprendre ce qui est fait précisément dans une fonction

```
Exemple :
//récupération données user
//parsing données
//envoi données au serveur
//...
```
* noms de variables cohérents
* noms de tests cohérents
* ne pas centraliser tout dans un fichier
* diviser en packages

* app
  * Data
  * DS
  * DC
  * Com
    * CS
    * CC
  * IHM
    * main
    * DL

## Commits
Syntaxe des commentaires : 
* préciser l'équipe entre crochets au début
* expliquer le commit rapidement
* préciser si c'est un Work In Progress (wip) ou non

`[DataS/DataC/ComS/ComC/IHMdl/IHMmain] Méthode/fonction/etc (wip)?`

* NE PAS `add -A` --> commit seulement les fichiers modifiés, pas les métadonnées
  * Je modifie `Classe1.java` et je crée `Classe2.java` ?
  * -> `git add Classe1.java Classe2.java`

## Issues
* suivre le template GitLab

## Test unitaires
* Suivre tutoriel OpenClassroom qui sera partagé 


