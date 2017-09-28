# Gestion de tournoi de Badminton /  Badminton Tournament Manager
IUT Nantes
See below for English version


Fichier de Lancement : Badminton.jar


## Manuel d'utilisation
#### Au commencement: 
 - Le nom du tournoi par défaut est a la date du jour. Sinon entrez le nom voulu, celui ci ne pourra plus ëtre changé.
- Choisisser le nombre de terrains diponible (peut être changé a tout moment)

#### Onglet Joueurs
Affiche les joueurs. Possibliliter de trier par colone en cliquant sur l'entëte de la colone. Cliquer sur un joueur pour modifier ses informations.
Modifier la présence d'un joueur: Décocher la case présent si un joueur n'est pas en mesure de jouer au tour suivant.
#### Onglet tournois
Genénérer un tour en cliquant sur nouveau tour dans le menu de gauche. Vous pouver changer les joueurs a volontée. Entrer les score et valider le score sur chaque terrain. Si un terrain n'est pas validé le match ne sera pas pris en compte. Quand tout les terrains sont grisés, générer un nouveau tour.
/!\ Si vous entrer des valeurs négatives ou des symboles, le score se remet a zero par défaut, avant de valider les scores vérifier ce que vous avez entré

#### Barre Latérale de Navigation
- Le chronomètre marche indépendament du reste de l'application il est juste indicatif. Une notification sonore indique la fin du chrono
- Nouveau tour: Génère de manière automatique les paires. Ces paires sont Visibles sur l'onglet Tournoi.
- Ajouter un Match: entrez manuellement un match
- Classement: affiche le classement général (Possibilitée de choisir entre classement général, des joueurs nouveau et des joueurs anciens dans la fenêtre)
- Reset Score: remet les scores a 0
 
#### Barre de Menu
##### Fichier
-   Nouveau (Ctrl+N) : ecrase le tournoi en cours et le remplace par un tournoi vide
-   Importer Joueurs (Ctrl+I) 
-   Exporter Joueurs (Ctrl+E) 
-   Enregister sous (Ctrl+MAJ+S) : BIENTOT DISPONIBLE - enregitre un tournoi en cours
-   Exporter Classement (Ctrl+E) : BIENTOT DISPONIBLE - 
    exporte le classement sous csv avec le nom et le score de tout les joueurs.


##### Edition

-   Nouveau Joueur (Ctrl+J) : ajouter un joueur
-   Changer Nombre Terrain (Ctrl+T) : 
    changer le nombre de terrains. Le changement sera effectif au prochain tour
-   

___________________________________________________________________
 This application was designed to enter scores during a two-person team sport tournament (Specifically Badmindon but it can be used for any other game)
 You cas export/import players form a csv file that can be edited in a spreadsheet editor.
 You cas export the rankings in the sme format.
 The tournament can be saved in XML to be imported in the app later and keep tracks on who played with whom.
 The scoring system we choose: The winner get the difference between his score and the looser side's. The looser loose the same amount of point.
 The apps generate automatically pairs out of the players. The pairs are design to prevent players to play too often with the same person twice as well as making pairs of balanced level.

Launching the project in an IDE: projet/src/main/app/App.java
## User Manual
#### Starting the app:
- The default name of the tournament is set to be the date. It can be changed here but won't be changable anytime later (though you can choose the name of the file you'll import the tournament to later)
- Choose the number of courts available (this will be editable any time.)

#### Player Tab
Displays the players with their name their scores and whether they are present or not (or able to play)
Clicking on a player displays a window to edit their infos.
Modifing the presence of a player make them be available/or not for the upcoming rounds. It can be done at any time. A player that is unchecked will not be taken in by the  paire generation algorithm.


#### Grounds Tab
Generates pairs automatically and displays on the grounds the players that will play this round.
To have the pairs displayed, click on "new Round". The grounds will be displayed.
Enter the scores on each ground and validate them.
Once all the played matches are validated. Re-click on "New Round" to laung the next round.
/!\ Inserting symbols or negative values will causes the scores to be zero when the field is deselected. Before Validating check that the field was not re-setted.


#### Left side Menu
- Stopwatch
- New Round: Generation on a new round
- Add Match: adds a match outside of any round with manually seleceted players
- Ranking: see the ranking at any time, you can choose to view only the new players or everyone.
- Reset Score: Set all the scores at 0

#### Top Menu bar
##### File
-   New (Ctrl+N) : Override the tournament with a new empty one (Make sure to safe before using)
-   Importer Players (Ctrl+I)
-   Exporter Players (Ctrl+E)
-   Save as (Ctrl+MAJ+S) : SOON - Save the tournament
-   Exporter Ranking (Ctrl+E) : SOON - Seve the ranking in a csv file

##### Edit

-   New PLayer (Ctrl+J)
-   Change grouds number (Ctrl+T) :









