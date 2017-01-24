# Gestion de tournoi de Badminton
IUT Nantes 
Fichier de Lancement projet/src/main/app/App.java
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







