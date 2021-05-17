# <center>PokéArena - Weekly 1</center>

<center>04/05/2021</center>

L'objectif des weekly est d'**encadrer l'avancement du projet**, d'**aider** ceux qui nécessitent de l'aide, et surtout faire en sorte que l'ensemble de l'équipe reste sur la même longueur d'onde durant l'intégralité du projet. Comme leur nom l'indique, ces réunions auront lieu chaque semaine et nous permettrons de nous rapprocher d'un avancement en "**mode agile**".

## L'avancée globale

S'agissant de notre toute première réunion, rien n'est vraiment fonctionnel aujourd'hui.  Connaissant l'intitulé du projet depuis quelques temps, chaque membre a eu le temps de réfléchir sur ce dont il souhaitait se pencher. C'est justement le principe du weekly d'aujourd'hui: 

> -   **mise en commun** des idées pour préciser notre travail,
> -   **division** des principales parties **en tâches** (ou user stories),
> -   **répartition des tâches** en fonction des préférences de chacun,
> -   **réflexion commune** quant à l'architecture du code,
> -   **outils de collaboration**.


## Mise en commun des idées

Le plus dur lorsque on souhaite reprendre un concept tel que celui de Pokémon, c'est d'en **fixer les limites**. Nous avons tous des idées diverses et variées, voici celles que nous avons retenues:

> -   **simulateur de combat**, pas de système de capture de Pokémon,
> -   limité aux **Pokémon de la** **4e Génération** (493 premiers de la liste),
> -   limité aux **attaques de Pokémon de la 1ere génération** dans un premier temps, à réfléchir une fois terminé,
> -   **fidèle au vrai jeu** à 100% (statistiques, effets...),
> -   jeu **en solo ou à plusieurs** (solo contre des IA, multijoueur en local ou en ligne),
> -   jeu en ligne **hébergé par les joueurs**,
> -   **différents modes de jeu** (6V6, 3V3, Pokémon aléatoires...),
> -   création de **sets de Pokémon prédéfinis** (le joueur ne choisis pas les spécificités de ses Pokémon), permettant de jouer rapidement,
> -   **système de création et d'enregistrement de sets** pour permettre aux joueurs de créer leurs propres stratégies de A à Z,
> -   **interface utilisateur** simple dans les menus, fidèle au jeu dans les combats,
> -   utilisation des **musiques originales**,
> -   utilisation des **modèles de Pokémon originaux**.

## Division en tâches

Au vu de la quantité de travail et de sa diversité, facile de tout diviser en parties. Voici celles que nous avons établies:

> -   développement du **système de combat** (avec ses données et des algorithmes),
> -   création des **graphismes** au complet,
> -   mise en place de la **partie réseau**, permettant aux joueurs de jouer en LAN ou WAN,
> -   formatage de la **base de données** des Pokémon et de leurs attaques , objets, talents, statistiques...
> -   **schématisation technique** du projet,
> -   **rédaction** et livraison des weekly et du rapport final.

## Répartition des tâches

Nos user stories identifiées, il nous convient d'en choisir le **poids** et d'y affilier des **membres** de notre équipe.

Dans un premier temps le plus complexe semble être le **développement global du système de combat**, sur lequel nous avons choisi de mettre un **poids de 50**. Cette tâche sera surtout la plus longue, au vu de l'immense nombre d'objets, de talents et d'attaques de Pokémon à intégrer. Il se peut que cette sous équipe tourne, de façon à limiter la redondance de ce travail. Nous choisissons de confier cette tâche à **Nathan** et **Mohamed** dans un premier temps.

Avec un **poids de 30**, le développement des **graphismes** nous parait être une seconde grande étape de développement. Pour rappel, l'équipe qui s'occupera des graphismes devra intégrer l'interface utilisateur, les design de Pokémon, les animations et les musiques de combat. **Mathis** et **Camille** s'en occuperont au début, et cette partie ne devrait pas durer tout le long du projet.

Un élément très important dans Pokémon est la **base de données** à exploiter. Les jeux principaux intégrant 898 espèces de Pokémon et 764 attaques (sans compter talents, objets...), il est extrêmement important d'avoir quelqu'un de 100% dévoué à son formatage pour en tirer une utilisation optimale. Nous avons choisi un **poids de 25** pour cette mission, et l'avons confié à **Lény**.

La **partie réseau** nous semble très importante, puisque c'est celle qui va donner le plus de plu value à notre jeu. En effet, pouvoir jouer à Pokémon en ligne depuis n'importe quelle plateforme sera notre plus grosse différence avec des projets déjà exitants. Ne connaissant pas exactement encore les solutions à mettre en oeuvre, nous avons choisi de lui affilier un **poids de 30** et de le donner à **Louis**.

La **partie rédaction**, liée aux weekly, à la documentation et au rapport final nous paraît prédominante puisque c'est celle-ci qui mettra en avant le fruit de notre travail. Son **poids est de 25** et est pour l'instant occtroyé à **Mathis**, le "propriétaire de produit" en langage agile. Pour le **rapport final**, le travail sera sûrement partagé avec **Camille**.

Enfin la partie de **schématisation technique** du projet nous semble relativement courte et basique. Elle **pèsera donc 5**. **Médérick** s'en occupera.

## Réfléxion commune (code)

Afin de rester cohérents dans notre vision du projet, nous avons choisi d'axer notre fin de semaine sur **l'architecture globale du code**. Nous avons finalement établi un schéma UML complet (bien que non définitif) de notre structure globale :

![uml](https://cdn.discordapp.com/attachments/757522319307571271/843867573296693268/UMLPokemon.png)

Vert: class

Jaune: interface

Bleu: abstract class

Rouge: enum



## Outils de collaboration

Dans un projet long avec groupe de taille moyenne comme le notre, il est nécessaire de **bien se mettre d'accord sur nos outils de collaboration dès le départ**.

De manière générale, nous avons besoin d'un hébergeur de fichier lourd, un hébergeur et versionneur de code, ainsi que d'un programme de suivi de l'activité. Nous avons donc choisi de nous tourner vers **Drive**, **Github** et **Trello**, créés en première séance.
