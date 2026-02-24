# 🃏 Dojo – La main de Poker

Projet réalisé dans le cadre du **cours PS5 – Projet de développement (Semestre 5)** à **Polytech Nice Sophia-Antipolis**.

## Objectif du projet

Développer un programme en **Java** capable de **comparer deux mains de poker** et de déterminer laquelle est la plus forte selon les règles classiques du poker.

---

## État actuel de la livraison

La livraison contient une **implémentation complète** du comparateur de mains de poker.  
Elle permet :

- de lire deux mains depuis l’entrée standard,
- d’évaluer chaque main selon **toutes les règles** décrites (carte haute → brelan royal),
- de comparer les deux mains,
- d’afficher :

    - **Main 1 gagne**,
    - **Main 2 gagne**,
    - **Égalité**,
    - ainsi que **la raison de la victoire** (ex. : paire de 5, brelan, carte la plus élevée, etc.).

Les slices déclinent toutes les fonctionnalités prévues, et **tous les tests unitaires présents passent avec succès**.

---
## Exemple d’exécution

```text
Main 1: 2Tr 6Ca 7Ca 8Tr APi
Main 2: 3Tr 5Ca 9Ca DCo RCo
Main 1 gagne avec carte la plus élevée (As)
```

---

## Installation et exécution

### 1️⃣ Cloner le projet
```bash
git clone https://github.com/pns-si3-projects/dojo-fr.pns.poker-25-26-fise-25-26-fr.pns.poker-team-f-se.git
cd dojo-fr.pns.poker-25-26-fise-25-26-fr.pns.poker-team-f-se
```

### 2️⃣ Compiler le code

```bash
 javac -d out $(find src -name "*.java")
```

### 3️⃣ Lancer le programme
```bash
java -cp out fr.pns.poker.Main
```
---

##  Exécution des tests unitaires

Les tests se trouvent dans `Test/` et utilisent **JUnit**.

### Compiler les tests :
```bash
javac -cp lib/junit-platform-console-standalone-1.10.0.jar -d out @((Get-ChildItem -Recurse -Filter *.java).FullName)
```

### Lancer les tests :
```bash
java -jar lib/junit-platform-console-standalone-1.10.0.jar --class-path out --scan-class-path
```

(Depuis IntelliJ : clic droit sur le dossier `test` → *Run tests*.)
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
