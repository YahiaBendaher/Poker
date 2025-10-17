# Slicing du Projet : Dojo - La main de Poker

| Numéro | Titre de la slice (orienté métier) | Qui | Tests d'acceptation | Difficulté (F,PF) | Date Début | Date Fin | Tests |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Phase 1 : Logique de comparaison des valeurs numériques** | | | | | | | |
| 1 | Prendre 2 valeurs et retourner la plus haute | Rafia | Entrée : 5, 8 -> Sortie : 8 | F | | | |
| 2 | Gérer l'égalité entre 2 valeurs | Ilyes | Entrée : 7, 7 -> Sortie : "Égalité" | F | | | |
| 3 | Créer et afficher une "main" de 2 cartes (valeurs) | Amasine | Créer une main avec [5, 9] -> Afficher : Main: [5, 9] | F | | | |
| 4 | Trouver la carte la plus haute dans une main de 2 cartes | Yahia | Entrée : Main = [5, 9] -> Sortie : 9 | F | | | |
| 5 | Comparer deux mains de 2 cartes sur leur meilleure carte | Rafia et Ilyes | M1=[5,8], M2=[3,9] -> Sortie : "Main 2 gagne" | F | | | |
| 6 | Départager deux mains de 2 cartes avec le "kicker" | Amasine | M1=[5,9], M2=[7,9] -> Sortie : "Main 2 gagne" | F | | | |
| 7 | Étendre la comparaison "carte haute" à 5 cartes | Toute l'équipe | M1=[2,5,8,10,14], M2=[3,6,7,11,13] -> "Main 1 gagne" | PF | | | |
| **Phase 2 : Introduction progressive de la Paire** | | | | | | | |
| 8 | Détecter une paire dans une main de 2 cartes | Yahia | Entrée : [7, 7] -> Sortie : "Paire de 7" | F | | | |
| 9 | Détecter une paire dans une main de 3 cartes | Rafia | Entrée : [2, 7, 7] -> Sortie : "Paire de 7" | F | | | |
| 10 | Détecter une paire dans une main de 5 cartes | Ilyes | Entrée : [9, 4, 11, 4, 13] -> Sortie : "Paire de 4" | F | | | |
| 11 | Une Paire bat une simple Carte Haute | Amasine | M1=[5,5,2,3,4], M2=[14,12,9,6,8] -> "Main 1 gagne" | F | | | |
| 12 | Comparer deux Paires de valeurs différentes | Yahia et Ilyes | M1=[8,8,2,3,5], M2=[6,6,12,9,4] -> "Main 1 gagne" | F | | | |
| 13 | Comparer les kickers de deux Paires identiques | Rafia et Amasine | M1=[8,8,12,3,2], M2=[8,8,10,9,4] -> "Main 1 gagne" | PF | | | |
| **Phase 3 : Introduction progressive des Deux Paires** | | | | | | | |
| 14 | Détecter deux paires dans une main de 4 cartes | Yahia | Entrée : [5, 5, 8, 8] -> Sortie : "Deux Paires" | F | | | |
| 15 | Détecter deux paires dans une main de 5 cartes | Rafia | Entrée : [5, 5, 8, 8, 2] -> Sortie : "Deux Paires" | F | | | |
| 16 | Deux Paires battent une Paire | Ilyes | M1=[5,5,8,8,2], M2=[14,14,10,9,4] -> "Main 1 gagne" | F | | | |
| 17 | Comparer deux "Deux Paires" sur la paire la plus élevée | Amasine | M1=[10,10,8,8,4], M2=[9,9,7,7,13] -> "Main 1 gagne" | F | | | |
| 18 | Comparer deux "Deux Paires" sur la deuxième paire | Yahia | M1=[10,10,8,8,4], M2=[10,10,7,7,13] -> "Main 1 gagne" | F | | | |
| 19 | Comparer deux "Deux Paires" sur le kicker | Rafia | M1=[10,10,8,8,13], M2=[10,10,8,8,4] -> "Main 1 gagne" | F | | | |
| **Phase 4 : Introduction progressive du Brelan** | | | | | | | |
| 20 | Détecter un brelan dans une main de 3 cartes | Ilyes | Entrée : [7, 7, 7] -> Sortie : "Brelan de 7" | F | | | |
| 21 | Détecter un brelan dans une main de 5 cartes | Amasine | Entrée : [7, 7, 7, 2, 9] -> Sortie : "Brelan de 7" | F | | | |
| 22 | Un Brelan bat Deux Paires | Yahia | M1=[4,4,4,2,3], M2=[14,14,13,13,10] -> "Main 1 gagne" | F | | | |
| 23 | Comparer deux Brelans de valeurs différentes | Rafia | M1=[7,7,7,2,3], M2=[5,5,5,12,10] -> "Main 1 gagne" | F | | | |
| **Phase 5 : Introduction progressive de la Suite** | | | | | | | |
| 24 | Détecter si 3 valeurs sont consécutives | Ilyes | Entrée : [5, 6, 7] -> Sortie : "Consécutives" | F | | | |
| 25 | Détecter si 5 valeurs sont consécutives (Suite) | Amasine | Entrée : [5, 6, 7, 8, 9] -> Sortie : "Suite" | PF | | | |
| 26 | Une Suite bat un Brelan | Yahia et Rafia | M1=[5,6,7,8,9], M2=[14,14,14,2,3] -> "Main 1 gagne" | F | | | |
| **Phase 6 : Intégration des cartes complètes et gestion des erreurs** | | | | | | | |
| 27 | Représenter une carte (valeur et couleur) et l'afficher | Ilyes | Créer carte valeur=A, couleur=Pi -> Afficher "APi" | F | | | |
| 28 | Lire une main depuis une chaîne de caractères | Amasine | Entrée : "2Tr 6Ca 7Ca 8Tr APi" -> Crée une main de 5 cartes | PF | | | |
| 29 | [Erreur] Gérer une chaîne avec un nombre incorrect de cartes | Yahia | Entrée : "5Pi 7Co 8Tr 2D" -> Sortie : "Erreur : Une main doit contenir 5 cartes." | F | | | |
| 30 | [Erreur] Gérer une valeur de carte invalide | Rafia | Entrée : "1Pi 7Co 8Tr 9D 2H" -> Sortie : "Erreur : Valeur '1' invalide." | F | | | |
| 31 | [Erreur] Gérer une couleur de carte invalide | Ilyes | Entrée : "5X 7Co 8Tr 9D 2H" -> Sortie : "Erreur : Couleur 'X' invalide." | F | | | |
| 32 | [Erreur] Gérer une main avec des cartes dupliquées | Amasine | Entrée : "5Pi 7Co 8Tr 5Pi 2D" -> Sortie : "Erreur : Carte '5Pi' en double." | PF | | | |
| 33 | Adapter la détection de Paire avec les objets Carte | Yahia | Entrée : "5Tr 5Co 8Pi 2Ca 3Tr" -> Sortie : "Paire de 5" | F | | | |
| 34 | Adapter la détection de Deux Paires avec les objets Carte | Rafia | Entrée : "5Tr 5Co 8Pi 8Ca 2Tr" -> Sortie : "Deux Paires" | F | | | |
| 35 | Adapter la détection de Brelan avec les objets Carte | Ilyes | Entrée : "7Tr 7Co 7Pi 2Ca 3Tr" -> Sortie : "Brelan de 7" | F | | | |
| 36 | Adapter la détection de Suite avec les objets Carte | Amasine | Entrée : "5Tr 6Co 7Pi 8Ca 9Tr" -> Sortie : "Suite" | PF | | | |
| **Phase 7 : Introduction progressive de la Couleur** | | | | | | | |
| 37 | Détecter si 2 cartes ont la même couleur | Yahia | Entrée : ["2Pi", "5Pi"] -> Sortie : "Même couleur" | F | | | |
| 38 | Détecter si 3 cartes ont la même couleur | Rafia | Entrée : ["2Pi", "5Pi", "8Pi"] -> Sortie : "Même couleur" | F | | | |
| 39 | Détecter une Couleur (5 cartes de même couleur) | Ilyes | Entrée : "2Pi 5Pi 8Pi JPi APi" -> Sortie : "Couleur" | F | | | |
| 40 | Une Couleur bat une Suite | Amasine | M1=["2Pi", ...] (Couleur), M2=["5Tr", ...] (Suite) -> "Main 1 gagne" | F | | | |
| 41 | Comparer deux Couleurs avec la règle de la carte haute | Yahia | M1=["2Pi", ..., "APi"], M2=["3Co", ...,"RCo"] -> "Main 1 gagne" | F | | | |
| **Phase 8 : Introduction progressive du Full** | | | | | | | |
| 42 | Vérifier si une main contient un Brelan | Rafia | (Réutiliser la logique de la slice 37) Entrée : ["5Pi", "5Co", "5Tr", "8Ca", "2Co"] -> Sortie : "Contient Brelan de 5" | F | | | |
| 43 | Vérifier si les 2 cartes restantes forment une Paire | Ilyes | Entrée : ["5Pi", "5Co", "5Tr", "8Ca", "8Co"] -> Isoler ["8Ca", "8Co"] -> Sortie : "Contient Paire de 8" | PF | | | |
| 44 | Détecter un Full (contient un Brelan ET une Paire) | Amasine | Entrée : ["5Pi", "5Co", "5Tr", "8Ca", "8Co"] -> Sortie : "Full" | F | | | |
| 45 | Un Full bat une Couleur | Yahia | M1=["5Pi", ...] (Full), M2=["2Pi", ...] (Couleur) -> "Main 1 gagne" | F | | | |
| 46 | Comparer deux Fulls sur la valeur du Brelan | Rafia | M1=[5,5,5,8,8], M2=[4,4,4,14,14] -> "Main 1 gagne" | F | | | |
| **Phase 9 : Introduction progressive du Carré** | | | | | | | |
| 47 | Détecter un Carré (4 cartes identiques) | Ilyes | Entrée : ["7Pi", "7Co", "7Tr", "7Ca", "2Co"] -> Sortie : "Carré" | F | | | |
| 48 | Un Carré bat un Full | Amasine | M1=["7Pi", ...] (Carré), M2=["APi", ...] (Full) -> "Main 1 gagne" | F | | | |
| 49 | Comparer deux Carrés de valeurs différentes | Yahia | M1=[8,8,8,8,2], M2=[7,7,7,7,14] -> "Main 1 gagne" | F | | | |
| **Phase 10 : Introduction progressive de la Quinte Flush** | | | | | | | |
| 50 | Vérifier si une main est une Suite | Rafia | (Réutiliser la logique de la slice 38) Entrée : ["5Pi", "6Pi", "7Pi", "8Pi", "9Pi"] -> Sortie : "Est une Suite" | F | | | |
| 51 | Vérifier si une main est une Couleur | Ilyes | (Réutiliser la logique de la slice 41) Entrée : ["5Pi", "6Pi", "7Pi", "8Pi", "9Pi"] -> Sortie : "Est une Couleur" | F | | | |
| 52 | Détecter une Quinte Flush (est une Suite ET une Couleur) | Amasine | Entrée : ["5Pi", "6Pi", "7Pi", "8Pi", "9Pi"] -> Sortie : "Quinte Flush" | PF | | | |
| 53 | Une Quinte Flush bat un Carré | Yahia | M1=["5Pi", ...] (Quinte Flush), M2=["ACo", ...] (Carré) -> "Main 1 gagne" | F | | | |
| 54 | Comparer deux Quinte Flush sur la carte la plus haute | Rafia | M1=["6Pi", ..., "10Pi"], M2=["5Co", ..., "9Co"] -> "Main 1 gagne" | F | | | |
| 55 | Détecter une Quinte Flush Royale (Quinte Flush à l'As) | Toute l'équipe | Entrée : ["10Pi", "VPi", "DPi", "RPi", "APi"] -> Sortie : "Quinte Flush Royale" | F | | | |
| **Phase 11 : Interaction Utilisateur et Finalisation** | | | | | | | |
| 56 | [Affichage] Afficher le nom de la combinaison de la main gagnante | Yahia | M1=[5,5,2,3,4], M2=[14,12,9,6,8] -> "Main 1 gagne avec : Paire de 5" | F | | | |
| 57 | [Interaction] Demander à l'utilisateur de saisir la Main 1 | Rafia | Affiche "Entrez la Main 1 : " et lit l'entrée utilisateur. | F | | | |
| 58 | [Interaction] Demander à l'utilisateur de saisir la Main 2 | Ilyes | Affiche "Entrez la Main 2 : " et lit l'entrée utilisateur. | F | | | |
| 59 | [Affichage] Afficher le résultat final de façon claire | Amasine | Affiche : "Main 1: [2Pi...APi] (Couleur) bat Main 2: [5Tr...9Tr] (Suite)" | F | | | |
| 60 | [Interaction] Créer une boucle pour rejouer | Toute l'équipe | Après un résultat, demande "Comparer d'autres mains ? (o/n)". | PF | | | |