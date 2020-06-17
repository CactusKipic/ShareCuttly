# ShareCuttly
Application réductrice de liens avec l'API cutt.ly, permettant d'obtenir un lien raccourci redirigeant vers le lien voulu tout en permettant d'en retirer les statistiques de trafics au travers du lien raccourci.

Fonctionnalités:
* Raccourcir des liens en utilisant l'API cutt.ly
* Permettre la requête de nom de lien spécifique à l'API pour les liens raccourcis
* Enregistrer les requêtes, afin de garder un historique des liens raccourcis
* Permettre l'affichage et la gestion de l'historique de requête (Affichage, suppression, copie d'éléments)
* Récupérer et afficher les détails de trafics sur un lien raccourci présent dans l'historique

Fonctionnalités prévues mais non implémenté:
* Réglages quant à l'enregistrement des requêtes **|** Permettre de désactiver l'enregistrement des requêtes faites depuis l'application ou depuis la fonction **partage** d'Android
  * Implémenté dans l'enregistrement des requêtes, mais modification des paramètres non implémentées
* Option de raccourcissement de lien puis de repartage immédiat **|** Dans les options de partage, avoir la possibilités de raccourcir un lien avec ShareCuttly puis réexécuter la fonction de partage Android pour partager le lien raccourci sur d'autre application

## Écran d'accueil
Une fois l'application ouverte, le premier écran est l'écran où il est possible de raccourcir un lien. Il est possible de naviguer entre les écrans avec le menu en haut à gauche.

![Écran d'accueil de l'application](https://i.imgur.com/DNNOJNO.png)

## Raccourcir un lien
Pour raccourcir un lien il suffit, au choix, d'écrire une URL ou de la copier dans l'emplacement juste au dessus des boutons, ou d'utiliser le bouton "Paste & Short" pour utiliser l'élément du presse papier comme lien à raccourcir. Il est possible de faire la demande d'un nom d'URL spéciale avec l'emplacement de texte juste au dessus, si le nom est disponible il sera associé au lien.
Une fois un des boutons cliqué le lien raccourci sera affiché, en cas de succès, à la place sur lien qui vient d'être raccourci et copié dans le presse papier, en plus d'être enregistré dans l'historique.

![Raccourcir un lien](https://i.imgur.com/CMZ5k4j.png "Raccourcir un lien")  ![Lien raccourci avec succès](https://i.imgur.com/m4ym9Oz.png)

## Navigation
Voici le menu pour naviguer entre les deux principales vues de l'application:

![Menu de navigation](https://i.imgur.com/0lbMqW3.png)

## Historique des liens raccourcis
L'application garde un historique des liens raccourcis afin de pouvoir en récupérer les statistiques d'utilisations, en plus de permettre de les retrouver. Il est possible de copier un lien raccourcis en maintenant une pression sur l'élément souhaité.
Chaque élément affiche le nom donné par l'API au lien raccourcis, la date à droite, puis l'URL long, celui raccourci, et l'url raccourci qui redirige vers l'url long.

![Historique des liens](https://i.imgur.com/4fGjJ8K.png)

## Détails des éléments d'historique
En cliquant sur un des éléments dans l'historique on affiche les détails de ce dernier. Sur cette page on peut voir en plus clair tous les éléments précédemment affiché sur les éléments de l'historique. On peut également faire une requête de statistique pour le lien.
La vue permet soit de retourner à l'écran précédent grâce au bouton en haut à gauche, ou de supprimer l'élément affiché, cela avec une demande de confirmation pour éviter les appuis accidentels.

![Détails d'un élément d'historique](https://i.imgur.com/mHqB5Eq.png) ![Statistique de l'élément d'historique](https://i.imgur.com/9HLfwA2.png)

## Partage de lien depuis d'autres applis
ShareCuttly permet également de raccourcir des liens depuis les options de partage qu'on peut par exemple retrouver sur les navigateurs web. Utiliser l'option de partage de ShareCuttly raccourcira le lien à partager et copiera le lien raccourci dans le presse-papier.
Ici l'option de partage de l'application a été mise en valeur par un carré jaune.

!![Option de partage de ShareCuttly](https://i.imgur.com/PGTxKoO.png)
