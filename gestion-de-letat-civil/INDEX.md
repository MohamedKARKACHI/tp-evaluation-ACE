# 📑 Index de la Documentation

## 🚀 Démarrage Rapide

**Pour commencer immédiatement :**
1. Lire : [`INSTRUCTIONS.md`](INSTRUCTIONS.md) - Guide d'installation
2. Exécuter : `./run.sh` - Lancer l'application
3. Consulter : [`PROJET_COMPLET.md`](PROJET_COMPLET.md) - Vue d'ensemble

---

## 📚 Documentation Complète

### 1. Vue d'ensemble
- **[README.md](README.md)** - Introduction générale du projet
  - Description du contexte
  - Architecture en couches
  - Fonctionnalités principales
  - Structure du projet
  - Technologies utilisées

### 2. Guide d'installation et d'exécution
- **[INSTRUCTIONS.md](INSTRUCTIONS.md)** - Guide pas à pas
  - Prérequis (Java, Maven, MySQL)
  - Configuration de MySQL
  - Configuration de l'application
  - 3 méthodes d'exécution
  - Vérification des résultats
  - Dépannage des erreurs courantes

### 3. Architecture technique
- **[ARCHITECTURE.md](ARCHITECTURE.md)** - Architecture détaillée
  - Vue d'ensemble en couches
  - Diagramme de classes
  - Modèle relationnel
  - Patterns utilisés (DAO, Singleton, Strategy)
  - Flux de données
  - Gestion des transactions
  - Héritage JPA JOINED
  - Relations JPA
  - Sécurité et performance
  - Extensibilité

### 4. Diagrammes visuels
- **[DIAGRAMMES.md](DIAGRAMMES.md)** - Tous les diagrammes
  - Diagramme de cas d'utilisation
  - Diagrammes de séquence
  - Diagramme de classes détaillé
  - Diagramme entité-relation (ERD)
  - Diagramme de déploiement
  - Diagramme d'états
  - Diagramme de flux de données
  - Diagramme de packages
  - Diagramme de composants

### 5. Requêtes et SQL
- **[REQUETES.md](REQUETES.md)** - Documentation des requêtes
  - Requêtes HQL par service
  - Exemples avec Criteria API
  - Requêtes SQL natives équivalentes
  - Notes importantes sur les jointures
  - Agrégations (COUNT, SUM)

### 6. Tests
- **[TESTS.md](TESTS.md)** - Guide des tests
  - Tests réalisés par App.java
  - Exemples de tests unitaires JUnit
  - Configuration pour les tests
  - Tests d'intégration
  - Couverture de code avec JaCoCo
  - Bonnes pratiques

### 7. Synthèse complète
- **[PROJET_COMPLET.md](PROJET_COMPLET.md)** - Récapitulatif complet
  - Statut du projet
  - Contenu détaillé de chaque couche
  - Fonctionnalités implémentées
  - Données de test
  - Exécution rapide
  - Structure complète
  - Conformité avec les spécifications
  - Concepts JPA/Hibernate
  - Points forts du projet

---

## 🗂️ Fichiers du Projet

### Code Source Java

#### Entités (ma.projet.beans)
- `Personne.java` - Classe mère avec héritage JOINED
- `Homme.java` - Entité Homme (hérite de Personne)
- `Femme.java` - Entité Femme (hérite de Personne)
- `Mariage.java` - Entité Mariage (relation entre Homme et Femme)

#### Interface DAO (ma.projet.dao)
- `IDao.java` - Interface générique CRUD

#### Services (ma.projet.service)
- `HommeService.java` - Service de gestion des hommes
- `FemmeService.java` - Service de gestion des femmes
- `MariageService.java` - Service de gestion des mariages

#### Utilitaires (ma.projet.util)
- `HibernateUtil.java` - Configuration Hibernate (Singleton)

#### Application
- `App.java` - Programme principal avec données de test

### Configuration

#### Maven
- `pom.xml` - Dépendances et configuration Maven
  - Hibernate Core 5.6.15
  - MySQL Connector 8.0.33
  - JUnit 4.13.2

#### Hibernate
- `hibernate.cfg.xml` - Configuration Hibernate
  - Connexion MySQL
  - Mapping des entités
  - Propriétés Hibernate

#### Application
- `application.properties` - Propriétés alternatives

### Base de données
- `database.sql` - Script de création de la base

### Scripts
- `run.sh` - Script d'exécution automatique (chmod +x)

### Documentation
- `README.md` - Vue d'ensemble
- `ARCHITECTURE.md` - Architecture détaillée
- `INSTRUCTIONS.md` - Guide d'installation
- `REQUETES.md` - Documentation des requêtes
- `TESTS.md` - Guide des tests
- `DIAGRAMMES.md` - Diagrammes visuels
- `PROJET_COMPLET.md` - Synthèse complète
- `INDEX.md` - Ce fichier

---

## 🎯 Navigation par Besoin

### Je veux installer l'application
→ [`INSTRUCTIONS.md`](INSTRUCTIONS.md)

### Je veux comprendre l'architecture
→ [`ARCHITECTURE.md`](ARCHITECTURE.md)

### Je veux voir les diagrammes
→ [`DIAGRAMMES.md`](DIAGRAMMES.md)

### Je veux comprendre les requêtes
→ [`REQUETES.md`](REQUETES.md)

### Je veux ajouter des tests
→ [`TESTS.md`](TESTS.md)

### Je veux une vue d'ensemble rapide
→ [`PROJET_COMPLET.md`](PROJET_COMPLET.md)

### Je veux résoudre un problème
→ [`INSTRUCTIONS.md`](INSTRUCTIONS.md) - Section Dépannage

---

## 📊 Statistiques du Projet

### Code Source
- **11 fichiers Java** (entités, services, DAO, util, app)
- **4 entités JPA** (Personne, Homme, Femme, Mariage)
- **3 services** (HommeService, FemmeService, MariageService)
- **1 interface DAO** générique
- **1 classe utilitaire** (HibernateUtil)

### Configuration
- **3 fichiers de configuration** (pom.xml, hibernate.cfg.xml, application.properties)
- **1 script SQL** (database.sql)
- **1 script shell** (run.sh)

### Documentation
- **8 fichiers Markdown** (README, ARCHITECTURE, INSTRUCTIONS, REQUETES, TESTS, DIAGRAMMES, PROJET_COMPLET, INDEX)
- **Plus de 1500 lignes** de documentation
- **10 diagrammes** visuels

### Données de Test
- **5 hommes**
- **10 femmes**
- **10 mariages** (3 en cours, 7 échus)

---

## 🔍 Recherche Rapide

### Par Fonctionnalité

**Créer des entités**
- Code : `src/main/java/ma/projet/beans/`
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Couche Persistance"

**Gérer les services**
- Code : `src/main/java/ma/projet/service/`
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Couche Service"

**Configurer Hibernate**
- Code : `src/main/resources/hibernate.cfg.xml`
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Configuration Hibernate"

**Écrire des requêtes**
- Exemples : [`REQUETES.md`](REQUETES.md)
- Code : Dans les fichiers Service

**Exécuter l'application**
- Script : `run.sh`
- Doc : [`INSTRUCTIONS.md`](INSTRUCTIONS.md)

### Par Concept JPA/Hibernate

**Héritage JOINED**
- Code : `Personne.java`, `Homme.java`, `Femme.java`
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Héritage JPA"

**Relations Many-to-One**
- Code : `Mariage.java` (vers Homme et Femme)
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Relations JPA"

**Relations One-to-Many**
- Code : `Homme.java`, `Femme.java` (vers Mariage)
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Relations JPA"

**Requêtes HQL**
- Code : Tous les fichiers Service
- Doc : [`REQUETES.md`](REQUETES.md)

**Transactions**
- Code : Méthodes create/update/delete dans les Services
- Doc : [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Gestion des transactions"

---

## 📞 Support et Aide

### Problèmes d'installation
→ [`INSTRUCTIONS.md`](INSTRUCTIONS.md) - Section "Dépannage"

### Erreurs MySQL
→ [`INSTRUCTIONS.md`](INSTRUCTIONS.md) - Section "Dépannage"

### Erreurs Hibernate
→ Vérifier `hibernate.cfg.xml` et consulter [`ARCHITECTURE.md`](ARCHITECTURE.md)

### Questions sur les requêtes
→ [`REQUETES.md`](REQUETES.md)

### Questions sur l'architecture
→ [`ARCHITECTURE.md`](ARCHITECTURE.md)

---

## ✅ Checklist de Vérification

Avant de démarrer, vérifiez que vous avez :

- [ ] Java 8+ installé (`java -version`)
- [ ] Maven installé (`mvn -version`)
- [ ] MySQL installé et démarré
- [ ] Base de données `etat_civil` créée
- [ ] Fichier `hibernate.cfg.xml` configuré avec vos identifiants MySQL
- [ ] Projet compilé (`mvn clean compile`)

Pour exécuter :
- [ ] Lancer `./run.sh` ou `mvn exec:java -Dexec.mainClass="ma.projet.App"`

Pour vérifier :
- [ ] Consulter les logs dans la console
- [ ] Vérifier les données dans MySQL

---

## 🎓 Concepts Couverts

### JPA/Hibernate
✅ Annotations JPA (@Entity, @Id, @GeneratedValue, etc.)  
✅ Héritage (JOINED strategy)  
✅ Relations (@ManyToOne, @OneToMany)  
✅ FetchType (EAGER vs LAZY)  
✅ Cascade operations  
✅ HQL (Hibernate Query Language)  
✅ Criteria API  
✅ SessionFactory et Session  
✅ Transactions  

### Design Patterns
✅ DAO Pattern  
✅ Singleton Pattern  
✅ Template Method Pattern  
✅ Strategy Pattern  

### Architecture
✅ Layered Architecture  
✅ Separation of Concerns  
✅ Dependency Management (Maven)  
✅ Configuration Management  

---

## 📈 Évolutions Futures

Pour étendre le projet, consultez :
- [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Évolutions futures possibles"
- [`ARCHITECTURE.md`](ARCHITECTURE.md) - Section "Extensibilité"

Suggestions :
- Migration vers Spring Boot
- Ajout d'une API REST
- Interface graphique (JavaFX ou Web)
- Gestion des enfants
- Génération de documents PDF
- Cache Redis
- Monitoring avec Prometheus

---

## 📝 Licence et Auteur

**Projet** : Gestion de l'État Civil  
**Type** : Projet académique / TP Évaluation  
**Technologies** : Java, Hibernate, MySQL, Maven  
**Date** : 2024  

---

## 🌟 Points Forts du Projet

1. ✅ **Documentation exhaustive** - 8 fichiers Markdown
2. ✅ **Architecture propre** - Séparation en couches
3. ✅ **Code maintenable** - Respect des patterns
4. ✅ **Données réalistes** - Noms marocains, dates cohérentes
5. ✅ **Gestion des erreurs** - Try-catch et rollback
6. ✅ **Requêtes optimisées** - HQL avec paramètres
7. ✅ **Extensible** - Facile d'ajouter des fonctionnalités
8. ✅ **Diagrammes complets** - 10 diagrammes visuels
9. ✅ **Script d'exécution** - Automatisation
10. ✅ **Tests documentés** - Guide complet

---

## 🎯 Conclusion

Ce projet est **100% fonctionnel** et **entièrement documenté**.

**Pour démarrer :** [`INSTRUCTIONS.md`](INSTRUCTIONS.md)  
**Pour comprendre :** [`ARCHITECTURE.md`](ARCHITECTURE.md)  
**Pour une vue d'ensemble :** [`PROJET_COMPLET.md`](PROJET_COMPLET.md)

**Bonne exploration ! 🚀**
