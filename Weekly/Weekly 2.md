# <center>PokéArena - Weekly 2</center>

<center>14/05/2021</center>

L'objectif des weekly est d'**encadrer l'avancement du projet**, d'**aider** ceux qui nécessitent de l'aide, et surtout faire en sorte que l'ensemble de l'équipe reste sur la même longueur d'onde durant l'intégralité du projet. Comme leur nom l'indique, ces réunions auront lieu chaque semaine et nous permettrons de nous rapprocher d'un avancement en "**mode agile**".

## L'avancée globale

Cette première semaine complète de travail nous a permis de nous **diviser en petits groupes pour la première fois**, conformément à nos user stories. Pour rappel, les tâches à accomplir désignées étaient les suivantes:

> -   développement du **système de combat** (avec ses données et des algorithmes),
> -   création des **graphismes** au complet,
> -   mise en place de la **partie réseau**, permettant aux joueurs de jouer en LAN ou WAN,
> -   formatage de la **base de données** des Pokémon et de leurs attaques , objets, talents, statistiques...
> -   **schématisation technique** du projet,
> -   **rédaction** et livraison des weekly et du rapport final.

Chaque petit groupe a alors pu découvrir plus précisément quelles étaient les difficultés des tâches qu'il avait à accomplir, et les communiquer afin de nous mettre d'accord sur leur résolution.


## Avancée des User Stories

Le **reformatage de la base de données** a été entamé par Lény. Nous avons décidé de sa forme (il s'agira d'une base LightSQL), mais reste à déterminer sa structure précise. Pour se faire, Lény va principalement devoir s'entretenir avec l'équipe de développement des combats, formée de Nathan et Mohamed.

Ces derniers ont pu continuer à se mettre d'accord sur l'**architecture du code** qu'ils allaient adopter, et commencer à développer les classes de base du projet. C'est principalement un travail de répartition qui fut l'objet de cette première semaine de travail.

Pour ce qui est du jeu en réseau, Louis a longuement pu se documenter sur la **technologie de communication réseau** à utiliser. Pour se faire, un POC a permis d'identifier et de spécifier les alternatives possibles. Bien que le fonctionnement global de notre système de jeu en réseau soit déjà bien réfléchi, **il reste à choisir le protocole qui permettra son implémentation**.

Enfin l'**interface graphique** est en cours d'élaboration. Pour l'instant, il ne s'agit que de "**mockups**", permettant à Mathis et Camille de partager leurs idées et de se mettre d'accord sur les grandes lignes. Ils ont également profité de leurs séances en salle de cours pour commencer à manipuler l'outil "Scene Builder", permettant la création d'interfaces graphiques JavaFX de manière visuelle. Pour l'instant, cette partie du travail en est encore à l'**état de l'art**.

## Difficultés rencontrées

Cette première semaine de travail nous a déjà permis d'**identifier les principales problématiques de notre sujet**.

Premièrement, **la base de données risque d'être un enjeu majeur de notre réussite**, tant la base de Pokémon est dense (Pokémon, attaques, objets, talents...). C'est aussi pour cette raison qu'il convient de bien délimiter notre projet, de façon à ce que notre équipe de développement ne se perde pas dans quelque chose d'initialement trop ambitieux. **Nous préférons avancer à tatons et nous adapter en fonction de notre avancée**.

Pour ce qui est de l'interface utilisateur et de la partie réseau, la **problématique majeure est principalement liée à notre manque d'expérience sur le sujet**. Nous en découvrons les spécificités jour après jour et notre avancement actuel ne nous permet pas encore de nous positionner sur la complexité de nos tâches. 

## Chemin à suivre

Bien que nos user stories soient délimitées, et notre équipe partitionnée en conséquence, **il est essentiel de conserver une bonne cohésion** et de toujours se mettre au courant du travail des autres pour se coordonner. 

D'ici au prochain weekly, nous espérons que Lény et Nathan/Mohamed réussiront à **fixer un plan précis concernant la base de données**. Il serait souhaitable qu'une version "light", exploitable au cours du développement, soit rapidement accessible.

**Le développement des combats devrait se poursuivre sur la lancée déjà initiée**. Aucun blocage n'a été identifié jusqu'à maintenant.

**La partie réseau devrait quant à elle être complètement spécifiée**, et nous espérons que son développement pourra débuter pour la semaine suivante.

Enfin **la partie graphique devrait devenir plus concrète cette semaine**. Une première version devrait pouvoir être livrable.

