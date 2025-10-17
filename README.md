# 🃏 Dojo – La main de Poker

Projet réalisé dans le cadre du **cours PS5 – Projet de développement (Semestre 5)** à **Polytech Nice Sophia-Antipolis**.

## Objectif du projet

Développer un programme en **Java** capable de **comparer deux mains de poker** et de déterminer laquelle est la plus forte selon les règles classiques du poker.

---

## Description du problème

Le programme lit deux mains de poker depuis l’entrée standard et affiche le résultat :
- **Main gagnante** (Main 1 ou Main 2) ;
- ou **égalité**, si les mains sont identiques en force.

Chaque main contient 5 cartes issues d’un jeu standard de 52 cartes :
- **Couleurs** : Trèfle (Tr), Carreau (Ca), Cœur (Co), Pique (Pi)  
- **Valeurs** : 2, 3, 4, 5, 6, 7, 8, 9, 10, V, D, R, A  

---

## Classement des mains (de la plus faible à la plus forte)

1. **Carte haute**  
2. **Paire**  
3. **Deux paires**  
4. **Brelan**  
5. **Suite**  
6. **Couleur**  
7. **Full**  
8. **Carré**  
9. **Quinte flush**  
10. **Quinte flush royale**  
11. **Brelan royal** (3 As)

---

## Exemple d’exécution

```text
Main 1: 2Tr 6Ca 7Ca 8Tr APi
Main 2: 3Tr 5Ca 9Ca DCo RCo
Résultat: Main 1 gagne avec carte la plus élevée (As)
```

---

## Installation et exécution

### 1️⃣ Cloner le projet
```bash
git clone https://github.com/pns-si3-projects/dojo-poker-25-26-fise-25-26-poker-team-f-se.git
cd dojo-poker-25-26-fise-25-26-poker-team-f-se
```

### 2️⃣ Compiler le code
Depuis le dossier `src/` :
```bash
javac Main.java
```

### 3️⃣ Lancer le programme
```bash
java Main
```

---

## Organisation du projet

Le projet est découpé en plusieurs **slices** disponibles dans le fichier **slices.md**.

---

## Équipe

- Ahmed BELAID
- Amacine IREJDALEN
- Yahia BENDAHER
- Rafia BEN SLAMA RACHED

---

## Année universitaire

2025 – 2026  
Polytech Nice Sophia-Antipolis  
FISE 25–26 – Team F-SE
