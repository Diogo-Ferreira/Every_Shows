# Every Shows
![artists view](https://github.com/Diogo-Ferreira/Every_Shows/blob/master/every_Shows_artists.png?raw=true)

![artists view](https://raw.githubusercontent.com/Diogo-Ferreira/Every_Shows/master/every_shows_shows.png)

## Motivation
Dans la cadre du cours de développement mobile, une application pour Android va être réalisée. Le délai pour mener à bien ce projet est d’un semestre. Il a été décidé de développer une application permettant de référencer les concerts se déroulant dans la région de l’utilisateur. Ce projet sera réalisé avec Android 5.0 Lollipop.
## But final
Le but final est de créer une application permettant de déterminer la localisation de l’utilisateur. Ainsi, il sera possible de l’informer, suivant ses préférences musicales, des concerts ayant lieu à proximité.
## Objectifs
### Primaires
* Récupérer la liste des artistes présents dans la bibliothèque physique de l'utilisateur
* Stocker cette liste dans une base de données SQLite locale
* Pour chaque artiste, rechercher les prochains concerts près de la position de l'utilisateur (Utilisation GPS et API telle que songkick)
* Afficher une liste détaillée des concerts
* Afficher une liste détaillée de chaque artiste (Utilisation API last.fm)


### Secondaires
* Service afin d'afficher une notification lors d'un nouveau concert (Sans devoir ouvrir l'application), et ajouts automatiques des nouveaux artistes, toujours sans ouvrir l'application.
* Proposer des artistes similaires ? (last.fm similar artists)
* Si vraiment le temps intégrer le support de beacons : Par exemple si un utilisateur s'approche d'un stand TicketCorner qui possède un beacon, il peut recevoir une promotion ou offre similaire sur un des ses artistes préférés...


## Technos

* Api songkick pour les dates de concerts
* Api last.fm pour les infos artises
* RecyclerView
* [Fresco](http://frescolib.org/) lib créée et utilisée par Facebook pour gérer efficacement les images. 

## Délai
Le projet a été commencé le mardi 20 septembre 2016 et se terminera le mardi 17 janvier 2017.
