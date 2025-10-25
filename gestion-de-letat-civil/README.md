# Application de Gestion de l'État Civil

## Description
Application Java permettant de gérer les citoyens et leurs relations matrimoniales dans une province.

## Architecture

### 1. Couche Persistance (ma.projet.beans)
- **Personne** : Classe mère avec héritage JOINED
  - id (int)
  - nom (String)
  - prenom (String)
  - dateNaissance (Date)
  - telephone (String)
  - adresse (String)

- **Homme** : Hérite de Personne
  - mariages (List<Mariage>)

- **Femme** : Hérite de Personne
  - mariages (List<Mariage>)

- **Mariage** : Entité de relation
  - id (int)
  - dateDebut (Date)
  - dateFin (Date)
  - nbrEnfants (int)
  - homme (Homme)
  - femme (Femme)

### 2. Couche Service (ma.projet.service)
- **HommeService** : Gestion des hommes
- **FemmeService** : Gestion des femmes
- **MariageService** : Gestion des mariages

### 3. Couche DAO (ma.projet.dao)
- **IDao<T>** : Interface générique pour les opérations CRUD

### 4. Utilitaires (ma.projet.util)
- **HibernateUtil** : Configuration Hibernate

## Configuration

### Base de données MySQL
1. Créer la base de données :
```sql
CREATE DATABASE etat_civil;
```

2. Configurer les paramètres dans `hibernate.cfg.xml` :
```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/etat_civil</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password"></property>
```

## Fonctionnalités

Le programme de test réalise les opérations suivantes :

1. **Créer 10 femmes et 5 hommes**
2. **Afficher la liste des femmes**
3. **Afficher la femme la plus âgée**
4. **Afficher les épouses d'un homme entre deux dates**
5. **Afficher le nombre d'enfants d'une femme entre deux dates**
6. **Afficher les femmes mariées deux fois ou plus**
7. **Afficher le nombre d'hommes mariés à quatre femmes entre deux dates**
8. **Afficher les mariages d'un homme donné avec tous les détails**

## Données de test

### Femmes (10)
- SAFI Fatima (03/09/1990)
- AMAL Nadia (03/09/1995)
- MAFA Amina (04/11/2000)
- KARIMA Salma (03/09/1989)
- Et 6 autres...

### Hommes (5)
- SAID Ahmed
- ALAMI Hassan
- BENNANI Youssef
- IDRISSI Karim
- TAZI Omar

### Mariages
- **Mariages en cours** : 4 mariages sans date de fin
- **Mariages échus** : 6 mariages avec date de fin

## Exécution

### Avec Maven
```bash
mvn clean install
mvn exec:java -Dexec.mainClass="ma.projet.App"
```

### Avec IDE
Exécuter la classe `ma.projet.App`

## Technologies utilisées
- Java 8
- Hibernate 5.6.15
- MySQL 8.0
- Maven

## Structure du projet
```
gestion-de-letat-civil/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ma/
│   │   │       └── projet/
│   │   │           ├── beans/
│   │   │           │   ├── Personne.java
│   │   │           │   ├── Homme.java
│   │   │           │   ├── Femme.java
│   │   │           │   └── Mariage.java
│   │   │           ├── dao/
│   │   │           │   └── IDao.java
│   │   │           ├── service/
│   │   │           │   ├── HommeService.java
│   │   │           │   ├── FemmeService.java
│   │   │           │   └── MariageService.java
│   │   │           ├── util/
│   │   │           │   └── HibernateUtil.java
│   │   │           └── App.java
│   │   └── resources/
│   │       ├── hibernate.cfg.xml
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Remarques
- La base de données sera créée automatiquement grâce à `hibernate.hbm2ddl.auto=update`
- Les données de test sont insérées à chaque exécution
- Le programme affiche les résultats dans la console
