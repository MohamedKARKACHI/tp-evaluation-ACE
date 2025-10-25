# 📋 Projet Complet - Gestion de l'État Civil

## ✅ Statut du projet : TERMINÉ

---

## 📦 Contenu du projet

### 1. Couche Persistance (Entités JPA)
✅ **Personne.java** - Classe mère avec héritage JOINED
- Attributs : id, nom, prenom, dateNaissance, telephone, adresse
- Annotations JPA : @Entity, @Inheritance, @Id, @GeneratedValue

✅ **Homme.java** - Hérite de Personne
- Relation @OneToMany avec Mariage

✅ **Femme.java** - Hérite de Personne
- Relation @OneToMany avec Mariage

✅ **Mariage.java** - Entité de relation
- Attributs : id, dateDebut, dateFin, nbrEnfants
- Relations @ManyToOne avec Homme et Femme

### 2. Couche DAO
✅ **IDao.java** - Interface générique
- Méthodes : create, update, delete, findById, findAll

### 3. Couche Service
✅ **HommeService.java**
- Implémente IDao<Homme>
- Méthodes spécifiques :
  - `getEpousesEntreDeuxDates(Homme, Date, Date)`
  - `getNombreHommesMarieQuatreFemmes(Date, Date)`

✅ **FemmeService.java**
- Implémente IDao<Femme>
- Méthodes spécifiques :
  - `getFemmePlusAgee()`
  - `getNombreEnfantsEntreDeuxDates(Femme, Date, Date)`
  - `getFemmesMarieeDeuxFoisOuPlus()`

✅ **MariageService.java**
- Implémente IDao<Mariage>
- Méthodes spécifiques :
  - `getMariagesParHomme(Homme)`
  - `getMariagesEnCours()`
  - `getMariagesEchus()`

### 4. Utilitaires
✅ **HibernateUtil.java** - Gestion de SessionFactory (Singleton)

### 5. Configuration
✅ **hibernate.cfg.xml** - Configuration Hibernate
- Connexion MySQL
- Mapping des entités
- Propriétés Hibernate

✅ **application.properties** - Propriétés alternatives

✅ **pom.xml** - Dépendances Maven
- Hibernate Core 5.6.15
- MySQL Connector 8.0.33
- JUnit 4.13.2

### 6. Programme de test
✅ **App.java** - Programme principal avec données de test
- Création de 5 hommes
- Création de 10 femmes
- Création de 10 mariages
- Affichage de tous les résultats demandés

### 7. Documentation
✅ **README.md** - Vue d'ensemble du projet
✅ **ARCHITECTURE.md** - Architecture détaillée
✅ **INSTRUCTIONS.md** - Guide d'installation et d'exécution
✅ **REQUETES.md** - Documentation des requêtes HQL/SQL
✅ **TESTS.md** - Guide des tests
✅ **database.sql** - Script de création de la base
✅ **run.sh** - Script d'exécution automatique

---

## 🎯 Fonctionnalités implémentées

### Programme de test réalisé :

1. ✅ **Créer 10 femmes et 5 hommes**
   - Données conformes aux spécifications
   - Femmes : SAFI Fatima, AMAL Nadia, MAFA Amina, etc.

2. ✅ **Afficher la liste des femmes**
   - Requête : `FROM Femme`

3. ✅ **Afficher la femme la plus âgée**
   - Requête : `FROM Femme f ORDER BY f.dateNaissance ASC`
   - Résultat attendu : ZAHRA Samira (22/02/1988)

4. ✅ **Afficher les épouses d'un homme entre deux dates**
   - Requête avec BETWEEN et WHERE
   - Exemple : Épouses de SAID Ahmed entre 01/01/1989 et 31/12/1991

5. ✅ **Afficher le nombre d'enfants d'une femme entre deux dates**
   - Requête avec SUM et agrégation
   - Exemple : Enfants de SAFI Fatima

6. ✅ **Afficher les femmes mariées deux fois ou plus**
   - Requête avec GROUP BY et HAVING
   - Résultat : KARIMA Salma (mariée 2 fois)

7. ✅ **Afficher le nombre d'hommes mariés à 4 femmes entre deux dates**
   - Requête complexe avec GROUP BY et HAVING COUNT = 4
   - Utilise les données de test de ALAMI Hassan

8. ✅ **Afficher les mariages d'un homme avec tous les détails**
   - Jointures avec Homme et Femme
   - Affichage complet : dates, enfants, informations de la femme

---

## 📊 Données de test

### Hommes (5)
| ID | Nom | Prénom | Date Naissance | Ville |
|----|-----|--------|----------------|-------|
| 1 | SAID | Ahmed | 15/05/1980 | Casablanca |
| 2 | ALAMI | Hassan | 20/08/1975 | Rabat |
| 3 | BENNANI | Youssef | 10/03/1985 | Fes |
| 4 | IDRISSI | Karim | 25/11/1978 | Marrakech |
| 5 | TAZI | Omar | 05/07/1982 | Tanger |

### Femmes (10)
| ID | Nom | Prénom | Date Naissance | Ville |
|----|-----|--------|----------------|-------|
| 1 | SAFI | Fatima | 03/09/1990 | Casablanca |
| 2 | AMAL | Nadia | 03/09/1995 | Rabat |
| 3 | MAFA | Amina | 04/11/2000 | Fes |
| 4 | KARIMA | Salma | 03/09/1989 | Marrakech |
| 5 | LAILA | Meriem | 15/06/1992 | Tanger |
| 6 | ZAHRA | Samira | 22/02/1988 | Agadir |
| 7 | HANANE | Khadija | 30/04/1993 | Oujda |
| 8 | SOUKAINA | Rachida | 12/12/1991 | Meknes |
| 9 | IMANE | Latifa | 08/08/1994 | Kenitra |
| 10 | NOUR | Zineb | 18/01/1996 | Tetouan |

### Mariages (10)
**Mariages en cours (dateFin = NULL) :**
- SAID Ahmed ↔ SAFI Fatima (03/09/1990, 4 enfants)
- ALAMI Hassan ↔ AMAL Nadia (03/09/1995, 2 enfants)
- BENNANI Youssef ↔ MAFA Amina (04/11/2000, 3 enfants)
- ALAMI Hassan ↔ IMANE Latifa (08/08/1994, 1 enfant)
- IDRISSI Karim ↔ KARIMA Salma (18/01/1996, 2 enfants)

**Mariages échus (dateFin != NULL) :**
- SAID Ahmed ↔ KARIMA Salma (03/09/1989 → 03/09/1990, 0 enfant)
- IDRISSI Karim ↔ LAILA Meriem (15/06/1992 → 20/08/2000, 1 enfant)
- TAZI Omar ↔ ZAHRA Samira (22/02/1988 → 10/05/1995, 2 enfants)
- ALAMI Hassan ↔ HANANE Khadija (30/04/1993 → 15/12/2005, 1 enfant)
- ALAMI Hassan ↔ SOUKAINA Rachida (12/12/1991 → 25/06/2010, 0 enfant)

---

## 🚀 Exécution rapide

### Prérequis
```bash
# Vérifier Java
java -version

# Vérifier Maven
mvn -version

# Vérifier MySQL
mysql --version
```

### Installation
```bash
# 1. Créer la base de données
mysql -u root -p
CREATE DATABASE etat_civil;
EXIT;

# 2. Compiler et exécuter
./run.sh
```

### Ou avec Maven directement
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="ma.projet.App"
```

---

## 📁 Structure du projet

```
gestion-de-letat-civil/
├── src/
│   ├── main/
│   │   ├── java/ma/projet/
│   │   │   ├── beans/
│   │   │   │   ├── Personne.java       ✅
│   │   │   │   ├── Homme.java          ✅
│   │   │   │   ├── Femme.java          ✅
│   │   │   │   └── Mariage.java        ✅
│   │   │   ├── dao/
│   │   │   │   └── IDao.java           ✅
│   │   │   ├── service/
│   │   │   │   ├── HommeService.java   ✅
│   │   │   │   ├── FemmeService.java   ✅
│   │   │   │   └── MariageService.java ✅
│   │   │   ├── util/
│   │   │   │   └── HibernateUtil.java  ✅
│   │   │   └── App.java                ✅
│   │   └── resources/
│   │       ├── hibernate.cfg.xml       ✅
│   │       └── application.properties  ✅
│   └── test/
│       └── java/ma/projet/
│           └── AppTest.java
├── pom.xml                              ✅
├── run.sh                               ✅
├── database.sql                         ✅
├── README.md                            ✅
├── ARCHITECTURE.md                      ✅
├── INSTRUCTIONS.md                      ✅
├── REQUETES.md                          ✅
├── TESTS.md                             ✅
└── .gitignore                           ✅
```

---

## 🔧 Technologies utilisées

- **Java** : 8+
- **Maven** : Gestion des dépendances
- **Hibernate** : 5.6.15.Final (ORM)
- **MySQL** : 8.0+ (Base de données)
- **JPA** : Annotations pour la persistance
- **HQL** : Hibernate Query Language

---

## 📝 Conformité avec les spécifications

### ✅ Couche Persistance
- [x] Entités dans `ma.projet.beans`
- [x] Annotations JPA
- [x] Héritage JOINED (Personne → Homme/Femme)
- [x] Relations Many-to-One et One-to-Many

### ✅ Configuration
- [x] `application.properties` créé
- [x] `HibernateUtil` dans `ma.projet.util`
- [x] Base de données MySQL configurée

### ✅ Couche Service
- [x] Interface `IDao` dans `ma.projet.dao`
- [x] Services implémentés : HommeService, FemmeService, MariageService
- [x] Toutes les méthodes demandées

### ✅ Programme de test
- [x] 10 femmes créées
- [x] 5 hommes créés
- [x] Affichage de la liste des femmes
- [x] Affichage de la femme la plus âgée
- [x] Affichage des épouses d'un homme entre deux dates
- [x] Affichage du nombre d'enfants d'une femme
- [x] Affichage des femmes mariées 2 fois ou plus
- [x] Affichage du nombre d'hommes mariés à 4 femmes
- [x] Affichage des mariages d'un homme avec détails

### ✅ Exemple d'affichage attendu
```
Nom : SAFI SAID    Date Debut : 03/09/1990    Nbr Enfants : 4
Mariages En Cours :
1. Femme : AMAL ALI    Date Debut : 03/09/1995    Nbr Enfants : 2
2. Femme : MAFA ALADUI Date Debut : 04/11/2000    Nbr Enfants : 3

Mariages échus :
1. Femme : KARIMA ALAMI Date Debut : 03/09/1989
   Date Fin : 03/09/1990    Nbr Enfants : 0
```

---

## 🎓 Concepts JPA/Hibernate utilisés

1. **Héritage** : JOINED strategy
2. **Relations** : @ManyToOne, @OneToMany
3. **Cascade** : Gestion automatique
4. **FetchType** : EAGER pour éviter lazy loading issues
5. **Transactions** : Gestion manuelle avec commit/rollback
6. **HQL** : Requêtes orientées objet
7. **Criteria API** : Alternative documentée
8. **SessionFactory** : Pattern Singleton

---

## 📈 Résultats attendus

Lors de l'exécution, vous devriez voir :

```
========================================
PROGRAMME DE TEST - GESTION ETAT CIVIL
========================================

1. LISTE DES FEMMES:
--------------------
- SAFI Fatima (Née le: 03/09/1990)
- AMAL Nadia (Née le: 03/09/1995)
- MAFA Amina (Née le: 04/11/2000)
...

2. FEMME LA PLUS ÂGÉE:
----------------------
- ZAHRA Samira (Née le: 22/02/1988)

3. ÉPOUSES DE SAID Ahmed ENTRE 01/01/1989 ET 31/12/1991:
--------------------------------------------------
- Femme: KARIMA Salma | Date Début: 03/09/1989 | Nbr Enfants: 0
- Femme: SAFI Fatima | Date Début: 03/09/1990 | Nbr Enfants: 4

...

========================================
FIN DU PROGRAMME DE TEST
========================================
```

---

## 🔍 Vérification

Pour vérifier que tout fonctionne :

```bash
# 1. Vérifier la compilation
mvn clean compile

# 2. Vérifier la base de données
mysql -u root -p etat_civil
SHOW TABLES;
SELECT COUNT(*) FROM personne;  -- Devrait retourner 15
SELECT COUNT(*) FROM mariage;   -- Devrait retourner 10

# 3. Exécuter l'application
./run.sh
```

---

## 📚 Documentation complète

Consultez les fichiers suivants pour plus de détails :

- **README.md** : Introduction et vue d'ensemble
- **ARCHITECTURE.md** : Architecture détaillée avec diagrammes
- **INSTRUCTIONS.md** : Guide d'installation pas à pas
- **REQUETES.md** : Toutes les requêtes HQL et SQL
- **TESTS.md** : Guide pour les tests unitaires

---

## ✨ Points forts du projet

1. ✅ **Architecture propre** : Séparation en couches
2. ✅ **Code maintenable** : Respect des patterns DAO et Singleton
3. ✅ **Documentation complète** : 5 fichiers de documentation
4. ✅ **Données de test réalistes** : Noms marocains, dates cohérentes
5. ✅ **Gestion des erreurs** : Try-catch et rollback
6. ✅ **Requêtes optimisées** : HQL avec paramètres
7. ✅ **Extensible** : Facile d'ajouter de nouvelles fonctionnalités

---

## 🎯 Conclusion

Le projet est **100% fonctionnel** et répond à toutes les spécifications demandées :

✅ Couche persistance avec JPA  
✅ Configuration Hibernate  
✅ Services avec interface DAO  
✅ Programme de test complet  
✅ Documentation exhaustive  
✅ Script d'exécution automatique  

**Le projet est prêt à être utilisé et démontré !**
