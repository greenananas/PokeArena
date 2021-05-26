# <center>PokéArena</center>

![pokearena1](https://user-images.githubusercontent.com/38182562/119683821-46371280-be44-11eb-89b8-7fd982775d0d.png)

Dans l’optique de choisir un sujet qui nous passionne tous, nous nous sommes penchés sur un **simulateur de combat Pokémon**. De manière générale, la stratégie Pokémon fait partie de ces jeux que l’on peut qualifier de “**easy to play, hard to master**”, dans le sens où n’importe quel néophyte peut jouer mais où le jeu est assez complexe pour créer un écart de niveau conséquent entre les joueurs. Il en va de même du point de vue du code. En effet, la base du jeu semble simple à produire, mais le nombre d’éléments à prendre en compte pour rendre le simulateur vraiment complet est immense.



## L'équipe

Nous sommes le groupe 1, et sommes composés de 7 personnes :

> - Nathan Chavas
> - Louis Salagnon
> - Mathis Grisel
> - Lény Neurisse
> - Mohamed Moudjeb
> - Camille Sicot
> - Médérick Poudret




## Les Algorithmes

Beaucoup d’algorithmes sont nécessaires aux différents calculs inhérents aux combats Pokémon. Par exemple, les **dégâts infligés** à un Pokémon adverse tiennent compte de :

> -   la <span style='color:#219dcf'>**puissance**</span> de l’attaque,
> -   les <span style='color:#219dcf'>**statistiques**</span> du lanceur et de la cible,
> -   du <span style='color:#219dcf'>**talent**</span> du lanceur et de la cible,
> -   de l’<span style='color:#219dcf'>**objet**</span> que tiennent le lanceur et la cible,
> -   la <span style='color:#219dcf'>**proximité**</span> de l’attaque (physique, spéciale),
> -   le <span style='color:#219dcf'>**type**</span> de l’attaque,
> -   des coups critiques,
> -   des <span style='color:#219dcf'>**statuts**</span> du lanceur et de la cible,
> -   du <span style='color:#219dcf'>**terrain**</span>…



Il en va de même pour les algorithmes de calculs de **vitesse**, de **précision**, etc…



## Les types

Énormément d’éléments viennent influencer le déroulement d’un combat et impacter nos différents algorithmes.

Les Pokémon possèdent notamment :

> -   4 <span style='color:#219dcf'>**attaques**</span> au maximum,
  > 
  > -   2 <span style='color:#219dcf'>**types**</span> au maximum,
  > 
  > -   1 <span style='color:#219dcf'>**objet**</span> au maximum,
  > 
  > -   1 <span style='color:#219dcf'>**statut**</span>,
  > 
  > -   1 <span style='color:#219dcf'>**talent**</span>,
  > 
  > -   1 <span style='color:#219dcf'>**nature**</span>.



Mais il ne s’agit pas des seuls types nécessaires au déroulement d’un combat :

> -   nous jouons sur un <span style='color:#219dcf'>**terrain**</span> sur lequel il y a des <span style='color:#219dcf'>**effets**</span> et des <span style='color:#219dcf'>**pièges**</span>,
>     
> -   notre <span style='color:#219dcf'>**joueur**</span> possède une <span style='color:#219dcf'>**équipe**</span> de 6 Pokémon au maximum,
>     
> -   il existe différents modes de <span style='color:#219dcf'>**combat**</span> (2v2,3v3…)...



## Le mode en ligne

L’objectif est, à terme, de pouvoir jouer à plusieurs en ligne. Un système de **création de compte** et de **connexion** sera donc nécessaire, de même qu’un outil de **team-building** (équipes de 6). On peut également imaginer la création d’un mode avec des équipes de Pokémon aléatoires, construites par un algorithme qui crée des sets équilibrés. Enfin un **système d’elo** (comme aux échecs) peut être imaginé pour classer les joueurs.



## L'interface graphique

Pour ce point, nous souhaiterions conserver les bases de Pokémon, avec l’affichage d’un **terrain personnalisé**, des **sprites** des Pokémon (importés des jeux originaux), des **barres de vies** et des **animations** des attaques. **JavaFX** nous semble être la bibliothèque la plus appropriée pour cet usage.
