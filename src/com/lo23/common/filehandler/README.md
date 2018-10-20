# Guide d'utilisation des FileHandler

Les classes `FileHandler` permettent de représenter des fichiers dans l'application et 
contiennent les métadonnées nécessaires sur le fichier.

Ce fichier readme a pour but de vous guider dans le choix de la classe héritée de 
`FileHandler` dont vous avez besoin. Il existe 3 classes dans ce package.


## FileHandler

La classe `FileHandler` est la classe de base. Elle contient les métadonnées de base sur le
fichier. Cette classe est la plus légère à passer en argument et sur le réseau et suffit
pour identifier un fichier.

**!!! Attention !!!**
> La classe `FileHandler` ne réalise aucune opération pour vous. Elle s'attend à recevoir
un hash du fichier déjà calculé (idem pour les tailles et nombre de blocks). Ces informations
doivent avoir été calculées AVANT de créer le FileHandler.

### Validité des paramètres

La classe `FileHandler` s'attend à recevoir un `hash` de longueur supérieure à 0, et des
paramètres `size` et `nbBlocks` strictements supérieurs à 0 lors de son initialisation.
Dans le cas contraire elle lèvera une exception de type `IllegalArgumentException`.


## FileHandlerInfos

La classe `FileHandlerInfos` hérite de `FileHandler` et contient les tags, commentaires 
et notes du fichier. Elle est beaucoup plus lourde que sa classe mère et ne devrait être
utilisée vraiment que lorsque qu'il est nécessaire de récupérer un fichier avec **toutes**
ses métadonnées.

**!!! Attention !!!**
> Cette classe ne réalise aucun contrôle sur lors de l'ajout d'un tag, d'une note ou d'un
commentaire. Si vous voulez éviter qu'un utilisateur note deux fois le même fichier par 
exemple, VOUS devez vous assurer qu'il n'a pas déjà entré une note pour ce fichier avant 
d'ajouter sa note. Lors du retrait d'un tag, d'une note ou d'un commentaire, **il n'est pas
nécessaire** de vérifier avant l'opération si l'élément que vous allez retirer existe bien
dans son vecteur de tag/note/commentaire.

> Les getter des listes de tag/note/commentaire renvoient une copie des listes, pas une
référence vers la liste stockée dans la classe.


## FileHandlerStats

La classe `FileHandlerStats` hérite de `FileHandlerInfos`. Elle contient les statistiques
(actuellement juste le nombre de téléchargements) d'un fichier. C'est la classe la plus 
complète. Elle ne devrait être utilisée que lorsqu'il est nécessaire de travailler avec les 
statistiques du fichier.