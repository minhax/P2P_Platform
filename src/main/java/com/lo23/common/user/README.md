# Guide d'utilisation des User

Les classes `User` permettent de représenter des utilisateurs dans l'application et 
contiennent les métadonnées nécessaires sur un utilisateur.

Ce fichier readme a pour but de vous guider dans le choix de la classe héritée de 
`User` dont vous avez besoin. Il existe 4 classes dans ce package.


## User

La classe `User` est la classe de base. Elle est la classe la plus légère car elle ne contient
que l'UUID et le login de l'utilisateur. C'est le minimum nécessaire pour représenter un 
utilisateur.

**!!! Attention !!!**
> La classe `User` génére automatiquement l'UUID de l'utilisateur.

### Validité des paramètres

La classe `User` s'attend à recevoir un `login` de longueur supérieure à 0 lors de son 
initialisation. Dans le cas contraire elle lèvera une exception de type 
`IllegalArgumentException`.


## UserIdentity

La classe `UserIdentity` hérite de `User` et contient le prénom, le nom et l'âge de
l'utilisateur. Elle est plus lourde que sa classe mère et ne devrait être
utilisée vraiment que lorsque qu'il est nécessaire d'accéder aux informations sur l'identité
de l'utilisateur.

### Validité des paramètres

La classe `UserIdentity` s'attend à recevoir un `nom` et un `prénom` de longueur 
supérieure à 0 lors de son initialisation, ainsi qu'un age supérieur à 0. Dans le cas 
contraire elle lèvera une exception de type `IllegalArgumentException`.


## UserStats

La classe `UserStats` hérite de `UserIdentity`. Elle contient les statistiques
(actuellement le nombre de fichiers proposés et téléchargés) d'un utilisateur. Elle ne 
devrait être utilisée que lorsqu'il est nécessaire de travailler avec les 
statistiques de l'utilisateur.


## UserAccount

La classe `UserAccount` est la classe la plus complète pour représenter le compte d'un
utilisateur. Elle contient le hash du mot de passe de l'utilisateur. Elle contient 
également une liste des fichiers qu'il propose actuellement et l'adresse IP du dernier
serveur auquel il s'est connecté à fin de le sauvegarder lorsqu'il quitte l'application.
Cette classe ne doit JAMAIS transiter sur le réseau : elle est strictement réservée à un
usage local. Limitez autant que possible l'utilisation de cette classe au sein de 
l'application, en raison de sa taille et des informations sensibles qu'elle contient. 

**!!! Attention !!!**
> La classe `UserAccount` ne génère pas automatiquement le hash du mot de passe : vous
DEVEZ lui passer le hash déjà calculé.

### Validité des paramètres

La classe `User` s'attend à recevoir un `password` de longueur supérieure à 0 lors de son 
initialisation. Dans le cas contraire elle lèvera une exception de type 
`IllegalArgumentException`.
